package org.hl7.fhir.tools.publisher;
/*
Copyright (c) 2011+, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

public class WebMaker {

  /**
   * On the web site, develop content lives at a different address to the main spec. When this version is migrated to 
   * being the DSTU, in the branch for the DSTU, this must be set to ""
   */
  private static final String DSTU_PATH_PORTION = "-Develop";

  private FolderManager folders;
  private String version;
  private IniFile ini;
  private ZipGenerator zip;  
  private Definitions definitions;
  
  public WebMaker(FolderManager folders, String version, IniFile iniFile, Definitions definitions) {
    super();
    this.folders = folders;
    this.version = version;
    this.ini = iniFile;
    this.definitions = definitions;
  }

  private static final String SEARCH_FORM_HOLDER = "<p id=\"srch\"> </p>";
  private static final String SEARCH_LINK = "<div id=\"hl7-search\"> </div>";
  private static final String DISQUS_COMMMENT = "<!-- disqus -->";
  private static final String DISQUS_COMMMENT_PACKED = "<!--disqus-->";

  public void produceHL7Copy() throws Exception {
    List<String> folderList = new ArrayList<String>();
    Utilities.clearDirectory(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"dload");
    Utilities.clearDirectory(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web");
    File fw = new CSFile(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"fhir-web-"+version+".zip");
    if (fw.exists())
      fw.delete();
    File fd = new CSFile(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"fhir-dload-"+version+".zip");
    if (fd.exists())
      fd.delete();
    makeArchives();
    
    String[] files = new CSFile(folders.dstDir).list();
    for (String f : files) {
      if (f.endsWith(".html")) {
        String src = TextFile.fileToString(folders.dstDir+f);
        if (src.contains(SEARCH_FORM_HOLDER)) 
          src = src.replace(SEARCH_FORM_HOLDER, googleSearch());
        if (src.contains(SEARCH_LINK)) 
          src = src.replace(SEARCH_LINK, googleSearchLink(0));
        // not for the DSTU
//        if (src.contains(DISQUS_COMMMENT)) 
//          src = src.replace(DISQUS_COMMMENT, disqusScript());
//        if (src.contains(DISQUS_COMMMENT_PACKED)) 
//          src = src.replace(DISQUS_COMMMENT_PACKED, disqusScript());
        // done
        int i = src.indexOf("</body>");
        if (i > 0)
          src = src.substring(0, i) + google()+src.substring(i);
        try {
          XhtmlDocument doc = new XhtmlParser().parse(src, "html");
          replaceDownloadUrls(doc);
          insertTargetImages(doc, null, f);
          new XhtmlComposer().compose(new FileOutputStream(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+f), doc);
        } catch (Exception e) {
          TextFile.stringToFile(src, "c:\\temp\\test.html");
          throw new Exception("exception processing: "+src+": "+e.getMessage());
        }
      } else if (f.endsWith(".chm") || f.endsWith(".eap") || f.endsWith(".zip")) {
//         if (!f.equals("fhir-spec.zip"))
          Utilities.copyFile(new CSFile(folders.dstDir+f), new CSFile(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"dload"+File.separator+f));
      } else if (!f.matches(Config.VERSION_REGEX) && !f.equals("html") && !f.equals("examples") ) {
        if (new CSFile(folders.dstDir+f).isDirectory()) {
          Utilities.copyDirectory(folders.dstDir+f, folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+f, null);
        } else
          Utilities.copyFile(new CSFile(folders.dstDir+f), new CSFile(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+f));
      }
    }

    folderList.add("dist");
    folderList.add("assets");
    folderList.add("vs");
    folderList.add("cs");
    for (String n : definitions.getBindings().keySet()) {
      BindingSpecification bs = definitions.getBindings().get(n);
      if ((bs.getBinding() == Binding.CodeList && bs.hasInternalCodes()) || (bs.getBinding() == Binding.Special)) {
        String ref = bs.getReference().startsWith("#") ? bs.getReference().substring(1) : bs.getReference();
        String dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+ref;
        folderList.add(ref);
        buildRedirect(n, ref+".html", dn);
        dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"cs"+File.separator+ref;
        buildRedirect(n, ref+".html", dn);
        dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"vs"+File.separator+ref;
        buildRedirect(n, ref+".html", dn);
      }
    }

    for (String n : definitions.getBindings().keySet()) {
      BindingSpecification bs = definitions.getBindings().get(n);
      if ((bs.getBinding() == Binding.ValueSet) && bs.getReference().startsWith("valueset-")) {
        String ref = bs.getReference().substring(9);
        String dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"vs"+File.separator+ref;
        buildRedirect(n, bs.getReference()+".html", dn);
        ValueSet vs = bs.getReferredValueSet();
        if (vs != null && vs.hasDefine() && vs.getDefine().getSystem().startsWith("http://hl7.org/fhir")) {
          dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+vs.getDefine().getSystem().substring(20).replace("/", "\\");
          buildRedirect(n, bs.getReference()+".html", dn);          
        }
      }
    }

    for (String n : definitions.sortedResourceNames()) {
      buildRedirect(n, n.toLowerCase()+".profile.xml.html", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"Profile"+File.separator+n);
      buildRedirect(n, n.toLowerCase()+".html", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+n);
      ResourceDefn r = definitions.getResourceByName(n);
//      for (RegisteredProfile p : r.getProfiles()) {
//        buildRedirect(n, p.getDestFilenameNoExt()+".html", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"Profile"+File.separator+p.getDestFilenameNoExt());
//      }
      for (Operation op : r.getOperations()) {
        buildRedirect(n, "operation-"+r.getName().toLowerCase()+"-"+op.getName()+".html", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"OperationDefinition"+File.separator+r.getName()+"-"+op.getName());
      }
    }
    // todo-profile - build redirects for extensions, profiles, valuesets 
    for (String n : ini.getPropertyNames("redirects")) {
      String dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+n;
      buildRedirect(n, ini.getStringProperty("redirects", n), dn);
      folderList.add(n); 
    }
    zip = new ZipGenerator(fw.getAbsolutePath());
    zip.addFiles(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator, "", null, null);
    for (String v : definitions.getPastVersions())
      zip.addFiles(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v"+v+File.separator, "v"+v+File.separator, null, null);
    for (String n : folderList) 
      zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+n+File.separator, n+File.separator, false);
    zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v2"+File.separator, "v2"+File.separator, false); 
    zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v3"+File.separator, "v3"+File.separator, false); 
    ZipGenerator zipd = new ZipGenerator(fd.getAbsolutePath());
    zipd.addFiles(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"dload"+File.separator, "", null, null);
    zipd.close();    
  }

  private void buildRedirect(String n, String d, String dn) throws Exception {
    Utilities.createDirectory(dn);
    String p = "<html>\r\n<head>\r\n<title>Redirect Page</title>\r\n<meta http-equiv=\"REFRESH\" content=\"0;url=http://hl7.org/implement/standards/fhir/"+
       d+
       "\"></HEAD>\r\n</head>\r\n<body>\r\nThis page is a redirect to http://hl7.org/implement/standards/fhir/"+
       d+
       "\r\n</body>\r\n</html>\r\n";
    TextFile.stringToFile(p, dn+File.separator+"index.html");
  }

  private void insertTargetImages(XhtmlNode node, XhtmlNode parent, String pagename) {
    if (node.getName() != null && (node.getName().equals("h1") || node.getName().equals("h2") || node.getName().equals("h3") || node.getName().equals("h4") || node.getName().equals("h5"))) {
      int i = parent.getChildNodes().indexOf(node)-1;
      while (i > 0 && parent.getChildNodes().get(i).getNodeType() != NodeType.Element)
        i--;
      if (i > 0 && parent.getChildNodes().get(i).getName().equals("a")) {
        String link = parent.getChildNodes().get(i).getAttribute("name");
        parent.getChildNodes().get(i).addText(" ");
        node.addText(" ");
        XhtmlNode a = node.addTag("a");
        a.setAttribute("href", pagename+"#"+link);
        a.setAttribute("title", "link to here");
        XhtmlNode img = a.addTag("img");
        img.attribute("src", "target.png");
      }
    }
    for (XhtmlNode child : node.getChildNodes()) 
      insertTargetImages(child, node, pagename);    
  }

  private String googleSearch() {
    return "<h3>Search the FHIR Specification:</h3>\r\n"+
        "<a name=\"search\"/>\r\n"+
        "<div id=\"cse\" style=\"width: 100%;\">Loading</div>\r\n"+
        "<script src=\"http://www.google.com/jsapi\" type=\"text/javascript\"> </script>\r\n"+
        "<script type=\"text/javascript\"> \r\n"+
        "  google.load('search', '1', {language : 'en', style : google.loader.themes.V2_DEFAULT});\r\n"+
        "  google.setOnLoadCallback(function() {\r\n"+
        "    var customSearchOptions = {};  var customSearchControl = new google.search.CustomSearchControl(\r\n"+
        "      '014445770867062802174:lufw2afc2og', customSearchOptions);\r\n"+
        "    customSearchControl.setResultSetSize(google.search.Search.FILTERED_CSE_RESULTSET);\r\n"+
        "    customSearchControl.draw('cse');\r\n"+
        "  }, true);\r\n"+
        "</script>\r\n";

  }
  
  private String googleSearchLink(int level) {
    String lvl = "";
    for (int i = 0; i < level; i++) 
      lvl = lvl + "../";

    return "<div id=\"hl7-search\">\r\n"+
        "  <a href=\""+lvl+"index.html#search\"><img src=\"assets/images/search.png\"/> Search</a>\r\n"+
			"</div>";
  }
  
  private void makeArchives() throws Exception {
    for (String v : definitions.getPastVersions()) {
      extractZip(folders.archiveDir+"v"+v+".zip", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v"+v+File.separator);
    }
  }

  static final int BUFFER = 2048;

  private void extractZip(String src, String dest) throws Exception {
	  Utilities.createDirectory(dest);
	  ZipFile zf = new ZipFile(src);
	  try {
		  for (Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements();) {
			  ZipEntry ze = e.nextElement();
			  String name = ze.getName();
			  if (name.endsWith(".html") || name.endsWith(".htm") || name.endsWith(".png") || name.endsWith(".css")) {
				  InputStream in = zf.getInputStream(ze);
				  OutputStream out = new FileOutputStream(dest+name);

				  byte data[] = new byte[BUFFER];
				  int count;
				  while((count = in.read(data, 0, BUFFER)) != -1) {
					  out.write(data, 0, count);
				  }

				  out.close();
				  in.close();
			  }
		  }
	  } finally {
		  zf.close();
	  }  
  } 
    

  private void replaceDownloadUrls(XhtmlNode node) {
    if (node.getNodeType() == NodeType.Document || node.getNodeType() == NodeType.Element) {
      if ("a".equals(node.getName()) && node.getAttributes().containsKey("href")) {
        String s = node.getAttributes().get("href");
        String sl = s.toLowerCase();
//        if (sl.equals("fhir-spec.zip")) 
//          node.getAttributes().put("href", "http://hl7.org/documentcenter/public/standards/FHIR"+DSTU_PATH_PORTION+"/fhir-spec.zip");
//        else 
        if (sl.endsWith(".chm") || sl.endsWith(".eap") || sl.endsWith(".zip")) 
          node.getAttributes().put("href", "/documentcenter/public/standards/FHIR"+DSTU_PATH_PORTION+"/"+s);
      }
      for (XhtmlNode child : node.getChildNodes()) 
        replaceDownloadUrls(child);
    }
    
  }

  private String google() {
    return
        "<script src=\"/includes/GoogleAnalyticsAddFileTracking.js\" type=\"text/javascript\"></script>\r\n"+
        "<script type=\"text/javascript\">\r\n"+
        "  var _gaq = _gaq || [];\r\n"+
        "  _gaq.push(['_setAccount', 'UA-676355-1']);\r\n"+
        "  _gaq.push(['_setDomainName', '.hl7.org']);\r\n"+
        "  _gaq.push(['_trackPageview']);\r\n"+
        "  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();\r\n"+
        "</script>\r\n";
  }

  public void addPage(String filename) throws Exception {
    zip.addFileName(filename, folders.dstDir+filename, false);
    zip.close();  
    
  }

  private String disqusScript() {
    String fhirDisqusShortname = ini.getStringProperty("FHIR", "disqus");
    
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"container\">");
    b.append("<hr />");
    b.append("<div id=\"disqus_thread\"></div>");
    b.append("<script type=\"text/javascript\">");
    b.append("var disqus_shortname = '" + fhirDisqusShortname + "';");
    b.append("(function() {");
    b.append("var dsq = document.createElement('script'); dsq.type = 'text/javascript'; dsq.async = true;");
    b.append("dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';");
    b.append("(document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq); })();");
    b.append("</script>");
    b.append("<noscript>Please enable JavaScript to view the <a href=\"http://disqus.com/?ref_noscript\">comments powered by Disqus.</a></noscript>");
    b.append("<a href=\"http://disqus.com\" class=\"dsq-brlink\">comments powered by <span class=\"logo-disqus\">Disqus</span></a>");
    b.append("</div>");
    return b.toString();
  }


}
