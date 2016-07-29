<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:template match="/ImplementationGuide">
    <xsl:text>{</xsl:text>
    <xsl:apply-templates select="page"/>
    <xsl:text>&#xa;}</xsl:text>
	</xsl:template>
  <xsl:template match="page">
    <xsl:param name="level" select="''"/>
    <xsl:param name="breadCrumb" select="''"/>
    <xsl:variable name="newLevel">
      <xsl:choose>
        <xsl:when test="not(parent::page)">0</xsl:when>
        <xsl:when test="parent::page[not(parent::page)]">
          <xsl:value-of select="count(preceding-sibling::page)+1"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat($level, '.', count(preceding-sibling::page)+1)"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="newBreadCrumb" select="concat($breadCrumb, '&lt;li&gt;&lt;a href=''', source/@value, '''&gt;&lt;b&gt;', title/@value, '&lt;/b&gt;&lt;/a&gt;&lt;/li&gt;')"/>
    <xsl:variable name="localBreadCrumb" select="concat($breadCrumb, '&lt;li&gt;&lt;b&gt;', title/@value, '&lt;/b&gt;&lt;/li&gt;')"/>
<!--    <xsl:variable name="id">
      <xsl:choose>
        <xsl:when test="kind/@value='page'">
          <xsl:value-of select="source/@value"/>
        </xsl:when>
        <xsl:when test="kind/@value='example'">
          <xsl:if test="count(type/@value)!=1">
            <xsl:message terminate="yes" select="concat('ERROR: Example ', source/@value, ' does not declare a type')"/>
          </xsl:if>
          <xsl:value-of select="concat(type/@value, '=', substring-before(source/@value, '.html'))"/>
        </xsl:when>
        <xsl:when test="starts-with(source/@value,'valueset-')">
          <xsl:value-of select="concat('ValueSet=', substring-after(substring-before(source/@value, '.html'), 'valueset-'))"/>
        </xsl:when>
        <xsl:when test="starts-with(source/@value,'conformance-')">
          <xsl:value-of select="concat('Conformance=', substring-before(source/@value, '.html'))"/>
        </xsl:when>
        <xsl:when test="starts-with(source/@value,'extension-')">
          <xsl:value-of select="concat('StructureDefinition=', substring-after(substring-before(source/@value, '.html'), 'extension-'))"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat('StructureDefinition=', substring-before(source/@value, '.html'))"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>-->
    <xsl:if test="parent::page">,</xsl:if>
    <xsl:value-of select="concat('&#xa;  &quot;', source/@value, '&quot;: {')"/>
    <xsl:value-of select="concat('&#xa;    &quot;title&quot;: &quot;', title/@value, '&quot;,')"/>
    <xsl:value-of select="concat('&#xa;    &quot;label&quot;: &quot;', $newLevel, '&quot;,')"/>
    <xsl:value-of select="concat('&#xa;    &quot;breadcrumb&quot;: &quot;', $localBreadCrumb, '&quot;')"/>
    <xsl:text>&#xa;  }</xsl:text>
    <xsl:apply-templates select="page">
      <xsl:with-param name="level" select="$newLevel"/>
      <xsl:with-param name="breadCrumb" select="$newBreadCrumb"/>
    </xsl:apply-templates>
  </xsl:template>
</xsl:stylesheet>
