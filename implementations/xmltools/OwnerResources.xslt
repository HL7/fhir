<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
  <xsl:output omit-xml-declaration ="yes" indent="no"/>
  <xsl:variable name="bundle" select="/Bundle"/>
  <xsl:template match="/">
    <groups>
      <xsl:for-each select="distinct-values($bundle/entry/resource/StructureDefinition/publisher/@value)">
        <xsl:sort select="."/>
        <xsl:variable name="name" select="if (contains(., '(')) then substring-before(substring-after(., '('), ')') else ."/>
        <group name="{$name}">
          <xsl:for-each select="$bundle/entry/resource/StructureDefinition[publisher/@value=current()]">
            <xsl:sort select="name/@value"/>
            <resource id="{id/@value}">
              <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/structuredefinition-fmm']">
                <xsl:attribute name="fmm" select="valueInteger/@value"/>
              </xsl:for-each>
              <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/structuredefinition-fmm-no-warnings']">
                <xsl:attribute name="basefmm" select="valueInteger/@value"/>
              </xsl:for-each>
              <xsl:for-each select="distinct-values(snapshot/element/binding/valueSetReference/reference/@value)">
                <valueset name="{substring-after(., '/vs/')}"/>
              </xsl:for-each>
            </resource>
          </xsl:for-each>
        </group>
      </xsl:for-each>
    </groups>
  </xsl:template>
</xsl:stylesheet>
