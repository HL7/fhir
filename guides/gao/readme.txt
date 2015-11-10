gao-forge.xml is the Implementation guide that can be edited in Forge.
gao.xml is the Resource that is used in the build.
To create gao.xml from gao-forge.xml, run an xslt processor on gao-forge.xml to produce gao.xml. That will fixup 
resource references.

The names StructureDefinition and ValueSet must remain fixed in order to handle this switching back and forth
from FHIR Canonical resource URIs to sourceUris.

I'm not sure what to do about examples yet.

