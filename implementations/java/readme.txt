There are two copies of org.hl7.fhir.instance in SVN:
/implementations...
/tools..

the code in org.hl7.fhir.instance is a mix of manual and generated 
code. The master location is /implementations. svn only contains
the manually written code. When you run the publishing tooling, it 
generates the code, and then zips it up and puts it in the publication
pack - that's what get's posted to HL7.org. 

Until you generate using publish.bat/sh, then the /implementations
will be missing most classes, all the generated ones. 

As for /tools, the publishing tool itself uses the generated code. 
You can get yourself in a real mess generating the source code of 
the application that generates the source code. A real mess. So the
publication tool has its own copy of the source code. We copy it over 
into the trunk/tools when
 (a) We know that we need to update the code that 
     the publishing tool itself uses (profile resource changes) and 
 (b) it's passing all the tests.
The code in trunk/tools is therefore out of date compared to the 
specification itself, and you shouldn't use it for anything but the
publication tooling.

The java package org.hl7.fhir.tools must depend on the copy in /tools.
The java packages org.hl7.fhir.convertors and org.hl7.fhir.sentinel
must depend on the copy in /implementations

