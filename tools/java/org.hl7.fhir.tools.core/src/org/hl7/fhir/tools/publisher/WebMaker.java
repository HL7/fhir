package org.hl7.fhir.tools.publisher;
/*
Copyright (c) 2011-2013, HL7, Inc
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

  private FolderManager folders;
  private String version;
  private List<String> past = new ArrayList<String>();
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

  private static final String SEARCH_FORM_HOLDER = "<p id=\"srch\">&nbsp;</p>";

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

    String[] files = new CSFile(folders.dstDir).list();
    for (String f : files) {
      if (f.endsWith(".html")) {
        String src = TextFile.fileToString(folders.dstDir+f);
        if (src.contains("<!--archive-->")) {
          src = src.replace("<!--archive-->", makeArchives());
        } else if (src.contains("<!-- archive -->")) {
          src = src.replace("<!-- archive -->", makeArchives());
        }
        if (src.contains(SEARCH_FORM_HOLDER)) 
          src = src.replace(SEARCH_FORM_HOLDER, googleSearch());
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
      } else if (f.endsWith(".chm") || f.endsWith(".eap") || f.endsWith(".zip")) 
        Utilities.copyFile(new CSFile(folders.dstDir+f), new CSFile(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"dload"+File.separator+f));
      else if (!f.matches(Config.VERSION_REGEX) && !f.equals("html") && !f.equals("examples") ) {
        if (new CSFile(folders.dstDir+f).isDirectory()) {
          Utilities.copyDirectory(folders.dstDir+f, folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+f);
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
        buildRedirect(n, ref+".html", dn);
      }
    }

    for (String n : ini.getPropertyNames("redirects")) {
      String dn = folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+n;
      buildRedirect(n, ini.getStringProperty("redirects", n), dn);
      folderList.add(n); 
    }
    zip = new ZipGenerator(fw.getAbsolutePath());
    zip.addFiles(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator, "", null, null);
    for (String v : past)
      zip.addFiles(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v"+v+File.separator, "v"+v+File.separator, null, null);
    for (String n : folderList) 
      zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+n+File.separator, n+File.separator);
    zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v2"+File.separator, "v2"+File.separator); 
    zip.addFolder(folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v3"+File.separator, "v3"+File.separator); 
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
  private CharSequence makeArchives() throws Exception {
    IniFile ini = new IniFile(folders.rootDir+"publish.ini"); 
    StringBuilder s = new StringBuilder();
    s.append("<h2>Archived Versions of FHIR</h2>");
    s.append("<p>These archives only keep the more significant past versions of FHIR, and only the book form, and are provided for purposes of supporting html diff tools. A full archive history of everything is available <a href=\"http://wiki.hl7.org/index.php?title=FHIR\">through the HL7 gForge archives</a>.</p>");
    s.append("<ul>");
    for (String v : ini.getPropertyNames("Archives")) {
      s.append("<li><a href=\"v"+v+"/index.html\">Version "+v+"</a>, "+ini.getStringProperty("Archives", v)+". (<a " +
      		"href=\"http://www.w3.org/2007/10/htmldiff?doc1=http://www.hl7.org/implement/standards/FHIR/v"+v+"/fhir-book.html&amp;doc2=http://www.hl7.org/implement/standards/FHIR/fhir-book.html\">Diff with current</a>) </li>");
      if (!past.contains(v))
        past.add(v);
      extractZip(folders.archiveDir+"v"+v+".zip", folders.rootDir+"temp"+File.separator+"hl7"+File.separator+"web"+File.separator+"v"+v+File.separator);
    }
    s.append("</ul>");
    return s.toString();
  }

  static final int BUFFER = 2048;

  private void extractZip(String src, String dest) throws Exception {
	  Utilities.createDirectory(dest);
	  ZipFile zf = new ZipFile(src);
	  try {
		  for (Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements();) {
			  ZipEntry ze = e.nextElement();
			  String name = ze.getName();
			  if (name.endsWith(".html") || name.endsWith(".png") || name.endsWith(".css")) {
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
        if (sl.endsWith(".chm") || sl.endsWith(".eap") || sl.endsWith(".zip")) 
          node.getAttributes().put("href", "/documentcenter/public/standards/FHIR/"+s);
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
    zip.addFileName(filename, folders.dstDir+filename);
    zip.close();  
    
  }


}
