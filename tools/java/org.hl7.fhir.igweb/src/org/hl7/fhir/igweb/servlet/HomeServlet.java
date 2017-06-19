package org.hl7.fhir.igweb.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.io.IOUtils;

public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    theResp.setContentType("text/html");
    theResp.setCharacterEncoding("utf-8");

    InputStream inputStream = HomeServlet.class.getResourceAsStream("/org/hl7/fhir/igweb/webres/home.html");
    IOUtils.copy(inputStream, theResp.getOutputStream());
    IOUtils.closeQuietly(theResp.getOutputStream());
  }

}
