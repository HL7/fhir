package org.hl7.fhir.definitions.parsers;

/*-
 * #%L
 * org.hl7.fhir.publisher.core
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.io.File;
import java.io.FileInputStream;

import org.hl7.fhir.r5.context.CanonicalResourceManager;
import org.hl7.fhir.r5.context.IWorkerContext.PackageVersion;
import org.hl7.fhir.r5.formats.IParser;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.ContactDetail;
import org.hl7.fhir.r5.model.ContactPoint;
import org.hl7.fhir.r5.model.UsageContext;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.utilities.Utilities;

public class CodeSystemConvertor {

  private CanonicalResourceManager<CodeSystem> codeSystems;

  public CodeSystemConvertor(CanonicalResourceManager<CodeSystem> codeSystems) {
    super();
    this.codeSystems = codeSystems;
  }

  public void convert(IParser p, ValueSet vs, String name, PackageVersion packageInfo) throws Exception {
    String nname = name.replace("valueset-", "codesystem-");
    if (nname.equals(name))
      nname = Utilities.changeFileExt(name, "-cs.xml");
    if (new File(nname).exists()) {
      FileInputStream input = new FileInputStream(nname);
      CodeSystem cs = CodeSystemUtilities.makeShareable((CodeSystem) p.parse(input));
      if (!cs.hasTitle())
        cs.setTitle(Utilities.capitalize(Utilities.unCamelCase(cs.getName())));

      populate(cs, vs);
//      if (codeSystems.containsKey(cs.getUrl())) 
//        throw new Exception("Duplicate Code System: "+cs.getUrl());
      codeSystems.see(cs, packageInfo);
    }    
  }

  public static void populate(CodeSystem cs, ValueSet vs) {
    if (!vs.hasName())
      throw new Error("No name vs "+vs.getUrl());
    if (!vs.hasDescription())
      throw new Error("No description vs "+vs.getUrl());
    
    if (cs.getUserData("conv-vs") != null)
      throw new Error("This code system has already been converted");
    cs.setUserData("conv-vs", "done");
    vs.setUserData("cs", cs);
    if (vs.hasUserData("filename"))
      cs.setUserData("filename", vs.getUserString("filename").replace("valueset-", "codesystem-"));
    if (vs.hasUserData("path"))
      cs.setUserData("path", vs.getUserString("path").replace("valueset-", "codesystem-"));
    if (vs.hasUserData("committee"))
      cs.setUserData("committee", vs.getUserData("committee"));
    cs.setId(vs.getId());
    cs.setVersion(vs.getVersion());
    cs.setName(vs.getName());
    cs.setTitle(vs.getTitle());
    cs.setStatus(vs.getStatus());
    cs.setExperimentalElement(vs.getExperimentalElement());
    cs.setPublisher(vs.getPublisher());
    for (ContactDetail csrc : vs.getContact()) {
      ContactDetail ctgt = cs.addContact();
      ctgt.setName(csrc.getName());
      for (ContactPoint cc : csrc.getTelecom())
        ctgt.addTelecom(cc);
    }
    cs.setDate(vs.getDate());
    cs.setDescription(vs.getDescription());
    cs.getDescriptionElement().getExtension().addAll(vs.getDescriptionElement().getExtension());
    for (UsageContext cc : vs.getUseContext())
      cs.addUseContext(cc);
    cs.setPurpose(vs.getPurpose());
    cs.setCopyright(vs.getCopyright());
    if (vs.hasCompose() && vs.getCompose().getInclude().size() == 1 && vs.getCompose().getExclude().size() == 0
        && vs.getCompose().getInclude().get(0).getSystem().equals(cs.getUrl()) 
        && !vs.getCompose().getInclude().get(0).hasValueSet()
        && !vs.getCompose().getInclude().get(0).hasConcept()
        && !vs.getCompose().getInclude().get(0).hasFilter())
      cs.setValueSet(vs.getUrl());
    vs.setImmutable(true);
  }

}
