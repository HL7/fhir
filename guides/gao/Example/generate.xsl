<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns="http://hl7.org/fhir"
  xmlns:fhir="http://hl7.org/fhir"
  xmlns:xhtml="http://www.w3.org/1999/xhtml"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:template match="/">
    <resource>
      <purpose value="example"/>
      <name>
        <xsl:attribute name="value">
          <xsl:value-of select="translate(/*/fhir:id/@value,'-',' ')"/>
        </xsl:attribute>
      </name>
      <description >
        <name>
          <xsl:attribute name="value">
            <xsl:value-of select="normalize-space(//xhtml:div)"/>
          </xsl:attribute>
        </name>
      </description>
      <acronym value="/*/fhir:id/@value"/>
      <sourceUri value="Example/{/*/fhir:id/@value}.xml"/>
    </resource>
  </xsl:template>
</xsl:stylesheet>
