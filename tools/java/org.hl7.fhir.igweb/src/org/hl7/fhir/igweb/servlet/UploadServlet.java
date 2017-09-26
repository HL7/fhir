package org.hl7.fhir.igweb.servlet;

import java.io.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.io.IOUtils;
//import org.eclipse.jetty.server.Request;
import org.hl7.fhir.igweb.builder.BuilderService;

public class UploadServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(UploadServlet.class);

  @Override
  protected void doPost(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    //String mimeType = theReq.getContentType();
    InputStream fileInputStream = null;

    for (Part part : theReq.getParts()) {
      if (part.getName().equals("file")) {
        fileInputStream = part.getInputStream();
      }
    }
   
    if (fileInputStream == null) {
      throw new ServletException("Mossing part 'file'");
    } 

    byte[] fileBytes = IOUtils.toByteArray(fileInputStream);
    ourLog.info("User uploaded {} bytes", fileBytes.length);
    
    String jobId = BuilderService.INSTANCE.submit(fileBytes);
    
    theResp.sendRedirect("/igweb/working?jobid=" + jobId);
  }

}
