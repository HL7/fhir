package org.hl7.fhir.igweb;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.hl7.fhir.igweb.servlet.*;

public class IgWebServer {

  private int myPort;

  public void setPort(int thePort) {
    myPort = thePort;
  }

  public void start() throws Exception {
    Server server = new Server(myPort);
    
    ServletHandler servletHandler = new ServletHandler();
    
    addServlet(servletHandler, new UploadServlet(), "/upload");
    addServlet(servletHandler, new WorkingServlet(), "/working");
    addServlet(servletHandler, new FailedServlet(), "/failed");
    addServlet(servletHandler, new FinishedServlet(), "/finished");
    addServlet(servletHandler, new DownloadServlet(), "/download");
    addServlet(servletHandler, new HomeServlet(), "/");

    HandlerList handlers = new HandlerList();
    handlers.addHandler(servletHandler);
    server.setHandler(handlers);

    server.start();
  }

  private ServletHolder addServlet(ServletHandler servletHandler, Servlet servlet, String path) {
    ServletHolder holder = new ServletHolder(servlet);
    servletHandler.addServletWithMapping(holder, path);
    return holder;
  }
  
}
