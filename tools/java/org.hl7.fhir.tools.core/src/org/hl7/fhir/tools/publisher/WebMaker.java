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

  public void produceHL7Copy() throws Exception {
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
