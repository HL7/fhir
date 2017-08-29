package org.hl7.fhir.igweb.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.igweb.template.TemplateBuilder;

public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    theResp.setContentType("text/html");
    theResp.setCharacterEncoding("utf-8");

    InputStream inputStream = HomeServlet.class.getResourceAsStream("/org/hl7/fhir/igweb/webres/home.html");
    String page = IOUtils.toString(inputStream);
    page = new TemplateBuilder().process("IGWeb: Online IG Builder", page);
    
    theResp.getOutputStream().write(page.getBytes(Charsets.UTF_8));
    IOUtils.closeQuietly(theResp.getOutputStream());
  }

}
