<?xml version="1.0" encoding="UTF-8"?>
<!--
  - This process turns XHTML into simple HTML, ensures anchors defining names have end tags and creates section labels and links on all sections
  -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xsi xs">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="@xsi:schemaLocation"/>
  <xsl:template priority="10" match="/div">
    <div>
      <xsl:apply-templates select="@*|node()"/>
    </div>
  </xsl:template>
  <xsl:template priority="5" match="*">
    <xsl:element name="{local-name(.)}">
      <xsl:apply-templates select="@*|node()"/>
    </xsl:element>
  </xsl:template>
  <xsl:template match="a[@name]">
    <a>
      <xsl:apply-templates select="@*|node()"/>
      <xsl:text>&#x20;</xsl:text>
    </a>
  </xsl:template>
  <xsl:template priority="10" match="h1|h2|h3|h4|h5">
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
    <xsl:element name="{local-name(.)}">
      <xsl:apply-templates select="@*"/>
      <xsl:attribute name="class" select="'self-link-parent'"/>
      <span class="sectioncount">
        <xsl:value-of select="concat('{{site.data.pages[page.path].label}}.0.', $hierarchy)"/>
      </span>
      <xsl:text> </xsl:text>
      <xsl:apply-templates select="node()"/>
      <xsl:text> </xsl:text>
      <a title="link to here" class="self-link">
        <xsl:attribute name="href" select="concat('{{page.path}}#', $link)"/>
        <img src="assets/images/link.svg" class="self-link" width="20" height="20"/>
      </a>
    </xsl:element>
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
