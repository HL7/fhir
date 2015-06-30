<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:variable name="allmessages" as="element(message)*">
    <xsl:apply-templates mode="fix" select="/warnings/message"/>
	</xsl:variable>
	<xsl:variable name="messages" as="element(message)*">
    <xsl:for-each select="distinct-values($allmessages/@display)">
      <xsl:copy-of select="$allmessages[@display=current()][1]"/>
    </xsl:for-each>
	</xsl:variable>
	<xsl:variable name="groups" as="element(group)+">
    <xsl:apply-templates mode="fix" select="/warnings/groups/group"/>
	</xsl:variable>
  <xsl:template mode="fix" match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates mode="fix" select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="fix" match="@*[normalize-space(.)='' or .='-1']"/>
  <xsl:template mode="fix" match="group">
    <xsl:copy>
      <xsl:variable name="order" select="count(parent::groups/preceding-sibling::groups)+1"/>
      <xsl:attribute name="order" select="$order"/>
      <xsl:attribute name="type" select="if ($order=1) then 'Resource' else if ($order=2) then 'Data Type' else 'Profile'"/>
      <xsl:apply-templates mode="fix" select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="fix" match="message">
    <xsl:variable name="message" as="element(message)">
      <message id="{position()}">
        <xsl:apply-templates mode="fix" select="@*"/>
        <xsl:attribute name="text" select="text()"/>
        <xsl:choose>
          <xsl:when test="starts-with(@location, 'ValueSet[')">
            <xsl:attribute name="valueset" select="substring-before(substring-after(@location, '['), ']')"/>
          </xsl:when>
          <xsl:when test="@location='rdf:w5.base' and contains(., ':')">
            <xsl:attribute name="resource" select="substring-before(., ':')"/>
            <xsl:attribute name="location" select="substring-before(., ':')"/>
            <xsl:attribute name="text" select="normalize-space(substring-after(., ':'))"/>
          </xsl:when>
          <xsl:when test="@location='rdf:w5.base'">
            <xsl:attribute name="location" select="''"/>
          </xsl:when>
          <xsl:when test="starts-with(@location, 'http:')">
            <xsl:attribute name="resource" select="tokenize(@location, '/')[last()]"/>
          </xsl:when>
          <xsl:when test="starts-with(@location, 'Binding @')">
            <xsl:variable name="name" select="normalize-space(substring-after(@location, '@'))"/>
            <xsl:attribute name="resource" select="if (contains($name, '.')) then substring-before($name, '.') else $name"/>
            <xsl:attribute name="location" select="$name"/>
          </xsl:when>
          <xsl:when test="@location='turtle'">
            <xsl:attribute name="location" select="''"/>
          </xsl:when>
          <xsl:when test="contains(@location, '.')">
            <xsl:attribute name="resource" select="substring-before(@location, '.')"/>
          </xsl:when>
          <xsl:when test="contains(@location, '/')">
            <xsl:attribute name="resource" select="substring-before(@location, '/')"/>
          </xsl:when>
          <xsl:when test="@location!=''">
            <xsl:attribute name="resource" select="@location"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:message select="."/>
            <xsl:message terminate="yes" select="'Unable to categorize error'"/>
          </xsl:otherwise>
        </xsl:choose>
      </message>
    </xsl:variable>
    <xsl:for-each select="$message">
      <xsl:copy>
        <xsl:copy-of select="@*"/>
        <xsl:attribute name="display" select="concat(@level, ': ', if (not(@location='')) then concat(@location, ': ') else '', @text)"/>
      </xsl:copy>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="/">
    <warnings>
      <xsl:variable name="matchedMessages" as="element(group)*">
        <xsl:for-each select="distinct-values($groups/@name)">
          <xsl:sort select="."/>
          <xsl:variable name="resources" as="element(resource)*">
            <xsl:for-each select="$groups[@name=current()]/resource">
              <xsl:sort select="parent::group/@order"/>
              <xsl:sort select="@id"/>
              <xsl:variable name="messages" as="element(message)*" select="$messages[@resource=current()/@id or @valueset=current()/valueset/@name]"/>
              <xsl:if test="$messages">
                <xsl:copy>
                  <xsl:copy-of select="parent::group/@type|@id|@fmm|@basefmm"/>
                  <xsl:for-each select="$messages">
                    <xsl:sort select="@display"/>
                    <xsl:copy-of select="."/>
                  </xsl:for-each>
                </xsl:copy>
              </xsl:if>
            </xsl:for-each>
          </xsl:variable>
          <xsl:if test="$resources">
            <group name="{.}">
              <xsl:copy-of select="$resources"/>
            </group>
          </xsl:if>
        </xsl:for-each>
      </xsl:variable>
      <xsl:copy-of select="$matchedMessages"/>
      <xsl:variable name="unmatchedMessages" as="element(message)*" select="$messages[not(@id = $matchedMessages//message/@id)]"/>
      <xsl:if test="$unmatchedMessages">
        <group name="Unowned">
          <xsl:for-each select="$unmatchedMessages">
            <xsl:sort select="@display"/>
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </group>
      </xsl:if>
    </warnings>
  </xsl:template>
</xsl:stylesheet>
