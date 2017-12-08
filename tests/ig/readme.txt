Test IG

This exists to test out the capabilities of the IG publisher

How to build this IG

 where [jpath] is the location of the IG pubisher (from the current build - see downloads), and path is the folder for your local copy of the repository

#1: Use the IG publisher
  java - jar [jpath]org.hl7.fhir.igpublisher.jar -ig [path]resources\rcpa.json -tool jekyll -out [path] -spec http://build.fhir.org/ -watch

#2: Use Jekyll to buidl the output
  go to [path]\html and run jekyll serve

#3 the final output will be in [path]\html\_site

note: you need to install Jekyll. for windows users, see http://jekyll-windows.juthilo.com/









