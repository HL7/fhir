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
import org.hl7.fhir.igweb.builder.BuilderService;
import org.hl7.fhir.igweb.builder.Job;

import ca.uhn.fhir.util.UrlUtil;

public class DownloadServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(DownloadServlet.class);

  @Override
  protected void doGet(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    String jobId = theReq.getParameter("jobid");

    Job job = BuilderService.INSTANCE.getJob(jobId);
    if (job == null) {
      theResp.sendRedirect("/failed?message=" + UrlUtil.escape("Job has expired, please try again."));
      return;
    }

    if (job.getFailureMessage() != null) {
      theResp.sendRedirect("/failed?message=" + UrlUtil.escape(job.getFailureMessage()));
      return;
    }

    if (!job.isFinished()) {
      theResp.setContentType("text/plain");
      theResp.getWriter().append("Working...");
      theResp.getWriter().close();
      return;
    }
    
    theResp.setContentType("application/zip");
	 theResp.addHeader("Content-Disposition", "attachment; filename=\"ig.zip\"");
    job.streamOutputTo(theResp.getOutputStream());
    IOUtils.closeQuietly(theResp.getOutputStream());

  }

}
