package org.hl7.fhir.igweb.template;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.hl7.fhir.igweb.servlet.HomeServlet;

public class TemplateBuilder {
   public TemplateBuilder() {
   }

   public String process(String title, String body) throws IOException {
      Map<String, String> vars = new HashMap();
      vars.put("title", title);
      vars.put("body", body);
      vars.put("version", "3.1.0-12293");
      StrSubstitutor subs = new StrSubstitutor(vars);
      return subs.replace(body);
   }
}

