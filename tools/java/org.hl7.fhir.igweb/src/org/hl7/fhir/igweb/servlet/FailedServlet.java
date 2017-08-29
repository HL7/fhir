package org.hl7.fhir.igweb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.hl7.fhir.igweb.template.TemplateBuilder;

public class FailedServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    theResp.setContentType("text/html");
    theResp.setCharacterEncoding("utf-8");

    String message = theReq.getParameter("message");
    
    InputStream inputStream = HomeServlet.class.getResourceAsStream("/org/hl7/fhir/igweb/webres/failed.html");
    String page = IOUtils.toString(inputStream);

    Map<String, String> vars = new HashMap<>();
    vars.put("message", message);
    StrSubstitutor subs = new StrSubstitutor(vars);
    page = subs.replace(page);
   
    page = new TemplateBuilder().process("Failed", page);
        
    theResp.getOutputStream().write(page.getBytes(Charsets.UTF_8));
    IOUtils.closeQuietly(theResp.getOutputStream());
  }

}
