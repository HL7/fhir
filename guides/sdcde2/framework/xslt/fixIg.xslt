<?xml version="1.0" encoding="UTF-8"?>
<!--
  - Strip off schema declaration and unofficial extension
  -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://hl7.org/fhir" xpath-default-namespace="http://hl7.org/fhir" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xsi xs">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="@xsi:schemaLocation|extension[@url=('http://hl7.org/fhir/tools-profile-spreadsheet')]"/>
  <xsl:template match="resource[extension[@url='http://hl7.org/fhir/StructureDefinition/implementationguide-spreadsheet-profile' and valueBoolean/@value=true()]]"/>
  <xsl:template match="package">
    <xsl:if test="resource[not(extension[@url='http://hl7.org/fhir/StructureDefinition/implementationguide-spreadsheet-profile' and valueBoolean/@value=true()])]">
      <xsl:copy>
        <xsl:apply-templates select="@*|node()"/>
      </xsl:copy>
    </xsl:if>
  </xsl:template>
  <xsl:template match="page[source/@value='artifacts.html']">
    <xsl:variable name="name" select="if (name) then 'name' else 'title'"/>
    <xsl:copy>
      <xsl:apply-templates select="@*|node()[not(self::page)]"/>
      <xsl:if test="not(page[format/@value='generated'])">
        <xsl:copy-of select="page"/>
        <xsl:for-each select="ancestor::ImplementationGuide/package/resource">
          <page>
            <xsl:variable name="type" select="substring-before(sourceReference/reference/@value, '/')"/>
            <xsl:variable name="id" select="substring-after(sourceReference/reference/@value, '/')"/>
            <source value="{if(starts-with($id, 'ext-')) then 'extension-' else if ($type='ValueSet') then 'valueset-' else ''}{$id}.html"/>
            <xsl:element name="{$name}" namespace="http://hl7.org/fhir">
              <xsl:attribute name="value" select="name/@value"/>
            </xsl:element>
            <xsl:variable name="kind">
              <xsl:choose>
                <xsl:when test="$type=('Conformance', 'SearchParameter') or contains($id, 'example')">example</xsl:when>
                <xsl:otherwise>resource</xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <kind value="{$kind}"/>
            <xsl:choose>
              <xsl:when test="$type=('Conformance', 'SearchParameter')">
                <format value="generated-resource"/>
              </xsl:when>
              <xsl:otherwise>
                <format value="generated"/>
              </xsl:otherwise>
            </xsl:choose>
          </page>
        </xsl:for-each>
      </xsl:if>
    </xsl:copy>
  </xsl:template>  
</xsl:stylesheet>
