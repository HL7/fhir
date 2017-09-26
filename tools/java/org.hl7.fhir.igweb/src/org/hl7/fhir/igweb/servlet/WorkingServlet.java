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
import org.hl7.fhir.igweb.template.TemplateBuilder;

import ca.uhn.fhir.util.UrlUtil;

public class WorkingServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(WorkingServlet.class);

  @Override
  protected void doGet(HttpServletRequest theReq, HttpServletResponse theResp) throws ServletException, IOException {
    String jobId = theReq.getParameter("jobid");

    Job job = BuilderService.INSTANCE.getJob(jobId);
    if (job == null) {
      theResp.sendRedirect("/igweb/failed?message=" + UrlUtil.escape("Job has expired, please try again."));
      return;
    }

    if (job.getFailureMessage() != null) {
      theResp.sendRedirect("/igweb/failed?message=" + UrlUtil.escape(job.getFailureMessage()));
      return;
    }

    if (job.isFinished()) {
      theResp.sendRedirect("/igweb/finished?jobid=" + jobId);
      return;
    }

    theResp.setContentType("text/html");
    theResp.setCharacterEncoding("utf-8");

    InputStream inputStream = HomeServlet.class.getResourceAsStream("/org/hl7/fhir/igweb/webres/working.html");
    String page = IOUtils.toString(inputStream);

    Map<String, String> vars = new HashMap<>();
    vars.put("jobid", jobId);
    vars.put("secondsElapsed", Long.toString((System.currentTimeMillis() - job.getCreated().getTime()) / 1000L));
    StrSubstitutor subs = new StrSubstitutor(vars);
    page = subs.replace(page);
    
    page = new TemplateBuilder().process("Working", page);
    
    theResp.getOutputStream().write(page.getBytes(Charsets.UTF_8));
    IOUtils.closeQuietly(theResp.getOutputStream());
  }

}
