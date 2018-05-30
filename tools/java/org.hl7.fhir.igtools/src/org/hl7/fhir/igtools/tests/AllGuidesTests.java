package org.hl7.fhir.igtools.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.igtools.publisher.Publisher;
import org.hl7.fhir.igtools.publisher.Publisher.CacheOption;
import org.hl7.fhir.r4.test.support.TestingUtilities;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RunWith(Parameterized.class)
public class AllGuidesTests {


  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    
    Map<String, String> igs = new HashMap<String, String>();
    IniFile ini =  new IniFile("c:\\work\\igs.ini");
    for (String n : ini.getPropertyNames("igs")) {
      igs.put(n, ini.getStringProperty("igs",  n));
    }

    List<Object[]> objects = new ArrayList<Object[]>(igs.size());
    for (String n : ini.getPropertyNames("igs")) {
      objects.add(new Object[] {n, igs.get(n)});
    }
    return objects;
  }
  

  private String name;
  private String filename;
  
  public AllGuidesTests(String name, String filename) {
    this.name = name;
    this.filename = filename;
  }

  @Test
  public void test() throws Exception {
    System.out.println("=======================================================================================");
    System.out.println("Publish IG "+name);
    Publisher pub = new Publisher();
    pub.setConfigFile(filename);
    pub.setTxServer("http://tx.fhir.org/r4");
    pub.setCacheOption(CacheOption.LEAVE);
    pub.execute();
    System.out.println("=======================================================================================");
    System.out.println("");
  }

  
}
