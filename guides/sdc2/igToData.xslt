<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:template match="/ImplementationGuide">
    <xsl:variable name="breadcrumb" select="concat('&lt;li&gt;&lt;a href=''', page/source/@value, '''&gt;&lt;b&gt;', page/title/@value, '&lt;/b&gt;&lt;/a&gt;&lt;/li&gt;')"/>
    <xsl:text>{</xsl:text>
    <xsl:text>&#xa;  &quot;toc.html&quot;: {&#xa;    &quot;title&quot;: &quot;Table of Contents&quot;,&#xa;    &quot;label&quot;: &quot;#&quot;,</xsl:text>
    <xsl:value-of select="concat('&#xa;    &quot;breadcrumb&quot;: &quot;', $breadcrumb, '&quot;&#xa;  }')"/>
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
    <xsl:call-template name="showPage">
      <xsl:with-param name="newLevel" select="$newLevel"/>
      <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
    </xsl:call-template>
    <xsl:if test="kind/@value='resource'">
      <xsl:call-template name="showPage">
        <xsl:with-param name="newLevel" select="$newLevel"/>
        <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
        <xsl:with-param name="suffix" select="'-definitions'"/>
        <xsl:with-param name="description" select="' - Definitions'"/>
      </xsl:call-template>
      <xsl:call-template name="showPage">
        <xsl:with-param name="newLevel" select="$newLevel"/>
        <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
        <xsl:with-param name="suffix" select="'-mappings'"/>
        <xsl:with-param name="description" select="' - Mappings'"/>
      </xsl:call-template>
      <xsl:call-template name="showPage">
        <xsl:with-param name="newLevel" select="$newLevel"/>
        <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
        <xsl:with-param name="suffix" select="'-examples'"/>
        <xsl:with-param name="description" select="' - Examples'"/>
      </xsl:call-template>
      <xsl:call-template name="showPage">
        <xsl:with-param name="newLevel" select="$newLevel"/>
        <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
        <xsl:with-param name="suffix" select="'.profile.xml'"/>
        <xsl:with-param name="description" select="' - Profile XML'"/>
      </xsl:call-template>
      <xsl:call-template name="showPage">
        <xsl:with-param name="newLevel" select="$newLevel"/>
        <xsl:with-param name="localBreadCrumb" select="$localBreadCrumb"/>
        <xsl:with-param name="suffix" select="'.profile.json'"/>
        <xsl:with-param name="description" select="' - Profile JSON'"/>
      </xsl:call-template>
    </xsl:if>
    <xsl:apply-templates select="page">
      <xsl:with-param name="level" select="$newLevel"/>
      <xsl:with-param name="breadCrumb" select="$newBreadCrumb"/>
    </xsl:apply-templates>
  </xsl:template>
  <xsl:template name="showPage">
    <xsl:param name="newLevel"/>
    <xsl:param name="localBreadCrumb"/>
    <xsl:param name="suffix" select="''"/>
    <xsl:param name="description" select="''"/>
    <xsl:variable name="name">
      <xsl:choose>
        <xsl:when test="$suffix=''">
          <xsl:value-of select="source/@value"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(substring-before(source/@value, '.html'), $suffix, '.html')"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:value-of select="concat(',&#xa;  &quot;', $name, '&quot;: {')"/>
    <xsl:value-of select="concat('&#xa;    &quot;title&quot;: &quot;', title/@value, $description, '&quot;,')"/>
    <xsl:value-of select="concat('&#xa;    &quot;label&quot;: &quot;', $newLevel, '&quot;,')"/>
    <xsl:value-of select="concat('&#xa;    &quot;breadcrumb&quot;: &quot;', $localBreadCrumb, '&quot;')"/>
    <xsl:if test="$suffix='' and page[kind/@value='example']">
      <xsl:text>,&#xa;    &quot;examples&quot;: [</xsl:text>
      <xsl:for-each select="page[kind/@value='example']">
        <xsl:if test="position()!=1">,</xsl:if>
        <xsl:value-of select="concat('&#xa;      {&#xa;        &quot;url&quot;: &quot;', source/@value, '&quot;,&#xa;        &quot;title&quot;: &quot;', title/@value, '&quot;&#xa;      }')"/>
        <xsl:text></xsl:text>
      </xsl:for-each>
      <xsl:text>&#xa;    ]</xsl:text>
    </xsl:if>
    <xsl:text>&#xa;  }</xsl:text>
  </xsl:template>
</xsl:stylesheet>
