package org.hl7.fhir.sentinel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.formats.Parser;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Resource;
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
		taggers.add(new TestTagger());
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

	public void execute() {
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
			System.out.println("Error connecting to server: "+e.getLocalizedMessage());
			e.printStackTrace(System.err);
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
		  AtomFeed feed = null;
	    if (Utilities.noString(ini.getStringProperty(server, "cursor")) && timeToQuery())
	      feed = downloadUpdates(client);

	    if (!stop && !Utilities.noString(ini.getStringProperty(server, "cursor"))) {
	    	if (feed == null) {
	    		Parser p = new XmlParser();
	    		feed = p.parseGeneral(new FileInputStream(getWorkingFileName())).getFeed();
	    	}
	      while(!stop && !Utilities.noString(ini.getStringProperty(server, "cursor"))) 
          process(feed, client);
	    } else
	    	Thread.sleep(1000);
  }

  private AtomFeed downloadUpdates(FHIRClient client) throws Exception {
		AtomFeed master = new AtomFeed();
		master.setTitle("working temporary feed");
	  String lasttime = ini.getStringProperty(server, "lasttime");

	  String next = null;
	  int i = 1;
	  do {
	      System.out.println("Downloading Updates (Page "+Integer.toString(i)+")");
	      AtomFeed feed = null;
	      if (next != null)
	        feed = client.fetchFeed(next);
	      else if (!Utilities.noString(lasttime)) {
	      	DateAndTime dd = new DateAndTime(lasttime);
	      	feed = client.history(dd); 
	      } else
	        feed = client.history();
	      if (feed.getLinks().containsKey("fhir-base")) {
	      if (!master.getLinks().containsKey("fhir-base"))
	      	master.getLinks().put("fhir-base", feed.getLinks().get("fhir-base"));
	      else if (!master.getLinks().get("fhir-base").equals(feed.getLinks().get("fhir-base")))
	      	throw new Exception("fhir-base link changed within a fetch");
	      }
        master.getEntryList().addAll(feed.getEntryList());
        if (next == null)
	          lasttime = feed.getUpdated().toString();
        next = feed.getLinks().get("next");
	      i++;
	  } while (!stop && next != null);

    if (!master.getLinks().containsKey("fhir-base"))
    	master.getLinks().put("fhir-base", server);
	  
    ini.setStringProperty(server, "qtime", DateAndTime.now().toString(), null);
    ini.setStringProperty(server, "lasttime", lasttime, null);
    ini.save();
    System.out.println(master.getEntryList().size() == 1 ? "1 update found" : Integer.toString(master.getEntryList().size())+" updates found");

    new XmlComposer().compose(new FileOutputStream(getWorkingFileName()), master, false);
    if (master.getEntryList().isEmpty())
      ini.setStringProperty(server, "cursor", "", null);
    else
    	ini.setIntegerProperty(server, "cursor", master.getEntryList().size()-1, null);
    ini.save();
    return master;
  }

	private void process(AtomFeed feed, FHIRClient client) throws Exception {
	  int i = ini.getIntegerProperty(server, "cursor");
	  AtomEntry<? extends Resource> ae = feed.getEntryList().get(i);
    System.out.println("Processing #"+Integer.toString(i)+" ("+ae.getResource().getResourceType().toString()+"): "+ae.getLinks().get("self"));
	  process(feed, ae, client);
	  i--;
	  if (i < 0)
		  ini.setStringProperty(server, "cursor", "", null);
	  else
		  ini.setIntegerProperty(server, "cursor", i, null);
	  ini.save();
  }

	private void process(AtomFeed feed, AtomEntry<? extends Resource> ae, FHIRClient client) throws Exception {
		List<AtomCategory> added = new ArrayList<AtomCategory>();
		List<AtomCategory> deleted = new ArrayList<AtomCategory>();
		for (Tagger t : taggers) 
			t.process(ae.getResource(), ae.getTags(), added, deleted);
		if (!added.isEmpty())
		  client.createTags(added, ae.getResource().getClass(), feed.getLogicalId(ae), feed.getVersionId(ae));
		if (!deleted.isEmpty())
		  client.deleteTags(deleted, ae.getResource().getClass(), feed.getLogicalId(ae), feed.getVersionId(ae));
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
