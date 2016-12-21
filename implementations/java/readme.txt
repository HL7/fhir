Some of the code in org.hl7.fhir.instance is a generated 
by the build process. 

This causes a bootstrapping problem - the code that is generated
is used by the generator to generate the code. The generated
code is checked into svn so people can run the FHIR build process.
One output from the build process is new java classes. Occasionally,
when needed (changes to resources used directly by the build tooling,
mostly Conformance resources, or when preparing a milestone release,
or for some RI uses), it's necessary to copy the newly generated
code over to the main location and update in svn. 

You need to be careful doing this; you may have to do extensive 
work in the build tool itself, and if the code you copy is not 
generating properly, you may end up having to make 1000s of edits
in order to be able to re-run the generation.  Not fun.

Please talk to Grahame Grieve before trying to update the svn 
code to the latest generated code. 
