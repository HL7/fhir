<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fhir="http://hl7.org/fhir" 
  xmlns="http://hl7.org/fhir" version="1.0" exclude-result-prefixes="fhir">

  <!-- Operation indicates what operation to perform:
    swap = switch back and forth between sourceUri and sourceResource/reference formats.  If sourceUri is present
    switch to sourceResource/reference, otherwise switch to sourceUri.
    uri = Force all sources to sourceUri format.
    res = force all sources to sourceResource/reference format.
    -->
  <xsl:param name="op" select="'swap'"/>
  <xsl:variable name="todo">
    <xsl:choose>
      <xsl:when test="$op='swap' and //fhir:sourceUri">res</xsl:when>
      <xsl:when test="$op='swap' and //fhir:sourceReference">uri</xsl:when>
      <xsl:otherwise><xsl:value-of select="$op"/></xsl:otherwise>
    </xsl:choose>
  </xsl:variable>
  
  <xsl:template match="/">
    <xsl:apply-templates/>
  </xsl:template>
  
  <xsl:template match="fhir:sourceUri">
    <xsl:choose>
      <xsl:when test="$todo='res'">
        <!-- Strip .xml suffix off the path -->
        <xsl:variable name="path" select="substring-before(@value,'.xml')"/>
        <sourceReference><reference value='http://hl7.org/fhir/{$path}'/></sourceReference>
      </xsl:when>
      <xsl:otherwise><xsl:copy-of select="."/></xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="fhir:sourceReference">
    <xsl:choose>
      <xsl:when test="$todo='uri'">
        <!-- Strip http://hl7.org/fhir/ prefix off the path -->
        <xsl:variable name="path" select=" substring-after(fhir:reference/@value,'http://hl7.org/fhir/')"/>
        <sourceUri value='{$path}.xml'/>
      </xsl:when>
      <xsl:otherwise><xsl:copy-of select="."/></xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
