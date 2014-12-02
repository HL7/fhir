package org.hl7.fhir.sentinel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;
import org.hl7.fhir.instance.utils.ResourceUtilities;
import org.hl7.fhir.sentinel.taggers.profile.ProfileTagger;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class SentinelWorker {
	
	private String server;
	private String username;
	private String password;
	private boolean reset;
	private boolean stop;
	
	private IniFile ini; 	
	private List<Tagger> taggers = new ArrayList<Tagger>();
	
	public SentinelWorker() {
	  super();
	  init();
  }

	public SentinelWorker(String server, String username, String password,  boolean reset) {
	  super();
	  this.server = server;
	  this.username = username;
	  this.password = password;
	  this.reset = reset;
	  init();
  }
	
	private void init() {
	  stop = false;
		ini = new IniFile(Utilities.path(getWorkingFolder(), "sentinel.ini"));
	  // register taggers
		taggers.add(new ProfileTagger());
//		taggers.add(new HCSTagger());
  }

	private String getWorkingFileName() {
	  return Utilities.path(getWorkingFolder(), "sentinel.xml"); // todo: make that server specific?
  }
	
	private String getWorkingFolder() {
	  return System.getProperty("user.dir");
  }

	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isReset() {
		return reset;
	}
	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public void execute() throws Exception {
		if (reset) {
			ini.setStringProperty(server, "lasttime", "", null);
			ini.setStringProperty(server, "cursor", "", null);
			ini.setStringProperty(server, "qtime", "", null);
			ini.save();
		}
		// trying to connect
	  FHIRClient client = null;
	  Conformance conf = null;
		try {
			System.out.println("Connecting to server: "+server);
			client = makeClient();
			conf = client.getConformanceStatement();
		} catch (Exception e) {
			throw new Exception("Error connecting to server: "+e.getLocalizedMessage(), e);
		}
	  if (conf != null) {
	  	for (Tagger t : taggers) 
	  		t.initialise(client, conf);
	  	
	  	while (!stop) { // at present stop will never be set to false, and the program must be killed
	  		try {
	  			updateResources(client);
	  		} catch (Exception e) {
	  			System.out.println("Error processing results: "+e.getLocalizedMessage());
	  			e.printStackTrace(System.err);
	  		}
	  	}
	  }
  }

	private FHIRClient makeClient() throws URISyntaxException {
	  FHIRSimpleClient client = new FHIRSimpleClient();
	  client.initialize(server);
		return client;
  }

	private void updateResources(FHIRClient client) throws Exception {
		  Bundle feed = null;
	    if (Utilities.noString(ini.getStringProperty(server, "cursor")) && timeToQuery())
	      feed = downloadUpdates(client);

	    if (!stop && !Utilities.noString(ini.getStringProperty(server, "cursor"))) {
	    	if (feed == null) {
	    		IParser p = new XmlParser();
	    		feed = (Bundle) p.parse(new FileInputStream(getWorkingFileName()));
	    	}
	      while(!stop && !Utilities.noString(ini.getStringProperty(server, "cursor"))) 
          process(feed, client);
	    } else
	    	Thread.sleep(1000);
  }

  private Bundle downloadUpdates(FHIRClient client) throws Exception {
  	Bundle master = new Bundle();
	  String lasttime = ini.getStringProperty(server, "lasttime");

	  String next = null;
	  int i = 1;
	  do {
	      System.out.println("Downloading Updates (Page "+Integer.toString(i)+")"+(next != null ? " ("+next+")" : ""));
	      Bundle feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else if (!Utilities.noString(lasttime)) {
	      	DateAndTime dd = new DateAndTime(lasttime);
	      	feed = client.history(dd); 
	      } else
	        feed = client.history();
	      if (feed.getBase() != null) {
	      if (master.getBase() != null)
	      	master.setBase(feed.getBase());
	      else if (!master.getBase().equals(feed.getBase()))
	      	throw new Exception("fhir-base link changed within a fetch");
	      }
        master.getEntry().addAll(feed.getEntry());
        if (next == null)
	          lasttime = feed.getMeta().getLastUpdated().toString();
        next = ResourceUtilities.getLink(feed, "next");
	      i++;
	  } while (!stop && next != null);

    if (master.getBase() == null)
    	master.setBase(server);
	  
    ini.setStringProperty(server, "qtime", DateAndTime.now().toString(), null);
    ini.setStringProperty(server, "lasttime", lasttime, null);
    ini.save();
    System.out.println(master.getEntry().size() == 1 ? "1 update found" : Integer.toString(master.getEntry().size())+" updates found");

    new XmlParser().compose(new FileOutputStream(getWorkingFileName()), master, false);
    if (master.getEntry().isEmpty())
      ini.setStringProperty(server, "cursor", "", null);
    else
    	ini.setIntegerProperty(server, "cursor", master.getEntry().size()-1, null);
    ini.save();
    return master;
  }

	private void process(Bundle feed, FHIRClient client) throws Exception {
	  int i = ini.getIntegerProperty(server, "cursor");
	  Resource ae = feed.getEntry().get(i).getResource();
	  if (ae != null) { // ignore deletions
	  	System.out.println("Processing #"+Integer.toString(i)+" ("+ae.getResourceType().toString()+"): "+ae.getId());
	  	process(feed, ae, client);
	  }
	  i--;
	  if (i < 0)
		  ini.setStringProperty(server, "cursor", "", null);
	  else
		  ini.setIntegerProperty(server, "cursor", i, null);
	  ini.save();
  }

	private void process(Bundle feed, Resource ae, FHIRClient client) throws Exception {
		ResourceMetaComponent added = new ResourceMetaComponent();
		ResourceMetaComponent deleted = new ResourceMetaComponent();
		for (Tagger t : taggers) 
			t.process(ae, ae.getMeta(), added, deleted);
		// todo-bundle
//		if (!added.isEmpty())
//		  client.createTags(added, ae.getClass(), ae.getId(), ae.getMeta().getVersionId());
//		if (!deleted.isEmpty())
//		  client.deleteTags(deleted, ae.getClass(), ae.getId(), feed.getMeta().getVersionId());
  }

	  
	// -- Utility routines --------------------------------
	

	private boolean timeToQuery() throws Exception {
		String s = ini.getStringProperty(server, "qtime");
		if (Utilities.noString(s))
			return true;
	  DateAndTime d = new DateAndTime(s);
    d.add(Calendar.MINUTE, 5); 
    return d.before(DateAndTime.now());
  }

}
