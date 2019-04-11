package org.hl7.fhir.igtools.publisher.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;

import com.google.gson.JsonObject;

public class IGReleaseVersionUpdater {

  private int countTotal = 0;
  private int countUpdated = 0;
  
  private static final String START_HTML_MARKER = "<!--ReleaseHeader--><p id=\"publish-box\">";
  private static final String END_HTML_MARKER = "</p><!--EndReleaseHeader-->";
  private static final String START_PUB_BOX = "<p id=\"publish-box\">";
  private static final String PUB_STYLE = "#publish-box";
  private static final String CSS = "#publish-box {  list-style: none;  padding: 0; }\np#publish-box { background-color: yellow; border:1px solid maroon; padding: 5px;}\nimg#publish-box { text-align: baseline; }\n#markdown-toc li{font-size: 1em;}\n";
  
  private String folder;
  private List<String> ignoreList;
  private Object version;

  public IGReleaseVersionUpdater(String folder, List<String> ignoreList, JsonObject version) {
    this.folder = folder;
    this.ignoreList = ignoreList;
    this.version = version;
  }

  public void updateStatement(String fragment) throws FileNotFoundException, IOException {
    updateFiles(fragment, new File(folder));
  }

  private void updateFiles(String fragment, File dir) throws FileNotFoundException, IOException {
    for (File f : dir.listFiles()) {
      if (ignoreList != null && ignoreList.contains(f.getAbsolutePath()))
        continue;
      if (f.getName().equals("modeldoc"))
        continue;
      if (f.getName().equals("quick"))
        continue;
      if (f.getName().equals("qa.html"))
        continue;
      if (f.getName().equals("history.html"))
        continue;
      if (f.getName().equals("qa-tx.html"))
        continue;
      
      if (f.isDirectory()) {
        updateFiles(fragment, f);
      }
      if (f.getName().endsWith(".html")) {
        String src = TextFile.fileToString(f);
        String o = src;
        int b = src.indexOf(START_HTML_MARKER);
        int e = src.indexOf(END_HTML_MARKER);
        if (b > -1 && e == -1) {
          int i = b;
          while (src.charAt(i+1) != '\n') i++;
          src = src.substring(0, i)+END_HTML_MARKER+src.substring(i);
          e = src.indexOf(END_HTML_MARKER);
        }
        if (b > -1 && e > -1) {
          src = src.substring(0, b+START_HTML_MARKER.length()) + fragment+src.substring(e);
          if (!src.equals(o)) {
            TextFile.stringToFile(src, f, false);
            countUpdated++;
          }
          countTotal++;
        }
      }
    }
  }

  public int getCountTotal() {
    return countTotal;
  }

  public int getCountUpdated() {
    return countUpdated;
  }

  
}
