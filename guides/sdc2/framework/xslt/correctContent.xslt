<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:f="http://hl7.org/fhir" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xsi xs f">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="@xsi:schemaLocation|f:extension[@url=('http://hl7.org/fhir/tools-profile-spreadsheet', 'http://hl7.org/fhir/tools-ig-publish-dependencies')]"/>
  <xsl:template match="h1|h2|h3|h4|h5">
    <xsl:variable name="hierarchy" as="xs:string">
      <xsl:apply-templates mode="findHierarchy" select="."/>
    </xsl:variable>
    <xsl:variable name="link">
      <xsl:choose>
        <xsl:when test="preceding-sibling::*[1][self::a[@name]]">
          <xsl:value-of select="preceding-sibling::*[1]/@name"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$hierarchy"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:if test="not(preceding-sibling::*[1][self::a[@name]])">
      <a name="{$hierarchy}">&#x20;</a>
    </xsl:if>
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      <span class="sectioncount">
        <xsl:value-of select="concat('{{site.data.pages[page.path].label}}.', $hierarchy)"/>
      </span>
      <xsl:text> </xsl:text>
      <xsl:apply-templates select="node()"/>
      <xsl:text> </xsl:text>
      <a title="link to here" class="self-link" href="sdc.html#toc">
        <img src="target.png"/>
      </a>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="findHierarchy" match="h1|h2|h3|h4|h5" as="xs:string">
    <xsl:param name="hierarchyString" as="xs:string" select="''"/>
    <xsl:param name="prevLevelCount" as="xs:double" select="0"/>
    <xsl:param name="prevLevel" as="xs:double" select="floor(number(substring(local-name(.), 2)))"/>
    <xsl:variable name="level" as="xs:double" select="floor(number(substring(local-name(.), 2)))"/>
    <xsl:variable name="levelCount" as="xs:double" select="if ($prevLevel = $level) then $prevLevelCount + 1 else $prevLevelCount"/>
    <xsl:variable as="xs:string" name="newHierarchyString" select="concat($levelCount, if ($hierarchyString!='') then concat('.', $hierarchyString) else '')"/>
    <xsl:choose>
      <xsl:when test="count(preceding::*[self::h1 or self::h2 or self::h3 or self::h4 or self::h5])!=0">
        <xsl:choose>
          <xsl:when test="$level&lt;$prevLevel">
            <xsl:apply-templates mode="findHierarchy" select="preceding::*[self::h1 or self::h2 or self::h3 or self::h4 or self::h5][1]">
              <xsl:with-param name="hierarchyString" as="xs:string" select="$newHierarchyString"/>
              <xsl:with-param name="prevLevelCount" as="xs:double" select="1"/>
              <xsl:with-param name="prevLevel" as="xs:double" select="$level"/>
            </xsl:apply-templates>
          </xsl:when>
          <xsl:otherwise>
            <xsl:apply-templates mode="findHierarchy" select="preceding::*[self::h1 or self::h2 or self::h3 or self::h4 or self::h5][1]">
              <xsl:with-param name="hierarchyString" as="xs:string" select="$hierarchyString"/>
              <xsl:with-param name="prevLevelCount" as="xs:double" select="$levelCount"/>
              <xsl:with-param name="prevLevel" as="xs:double" select="$prevLevel"/>
            </xsl:apply-templates>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$newHierarchyString"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>  
</xsl:stylesheet>
