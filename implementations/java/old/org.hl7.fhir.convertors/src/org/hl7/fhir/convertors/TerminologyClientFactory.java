package org.hl7.fhir.convertors;

import java.net.URISyntaxException;

import org.hl7.fhir.r4.model.FhirPublication;
import org.hl7.fhir.r4.terminologies.TerminologyClient;
import org.hl7.fhir.r4.terminologies.TerminologyClientR4;
import org.hl7.fhir.utilities.Utilities;

public class TerminologyClientFactory {

  public static TerminologyClient makeClient(String url, FhirPublication v) throws URISyntaxException {
    if (v == null)
      return new TerminologyClientR4(checkEndsWith("/r4", url));
    switch (v) {
    case DSTU2016May: return new TerminologyClientR3(checkEndsWith("/r3", url)); // r3 is the least worst match 
    case DSTU1: throw new Error("The version "+v.toString()+" is not currently supported");
    case DSTU2: return new TerminologyClientR2(checkEndsWith("/r2", url));
    case R4: return new TerminologyClientR4(checkEndsWith("/r4", url));
    case STU3: return new TerminologyClientR3(checkEndsWith("/r3", url));
    default: throw new Error("The version "+v.toString()+" is not currently supported");
    }

  }
  private static String checkEndsWith(String term, String url) {
    if (url.endsWith(term))
      return url;
    if (url.startsWith("http://tx.fhir.org"))
      return Utilities.pathURL(url, term);
    if (url.equals("http://local.fhir.org:960"))
      return Utilities.pathURL(url, term);
    return url;
  }

}
