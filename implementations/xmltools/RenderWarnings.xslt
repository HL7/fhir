<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:variable name="marker" as="xs:string" select="'===================================================================================================='"/>
	<xsl:template match="warnings">
    <xsl:for-each select="group">
      <xsl:value-of select="fn:concat('&#x0a;', substring($marker, 1, string-length(@name) + 4), '&#x0a;')"/>
      <xsl:value-of select="concat('= ', @name, ' =&#x0a;')"/>
      <xsl:value-of select="fn:concat(substring($marker, 1, string-length(@name) + 4), '&#x0a;')"/>
      <xsl:apply-templates select="*"/>
    </xsl:for-each>
    <xsl:value-of select="fn:concat('&#x0a;Errors: ', count(//message[@level='ERROR']), ' Warnings: ', count(//message[@level='WARNING']), ' Hints: ', count(//message[@level='INFORMATION']), '&#x0a;')"/>
    <xsl:text>&#x0a;===Resources with FMM forced to 0===&#x0a;</xsl:text>
    <xsl:for-each select="group[resource[(@basefmm or @fmm&gt;0) and message[@level!='Information']]]">
      <xsl:value-of select="concat(@name, ':&#x0a;  ')"/>
      <xsl:for-each select="resource[@basefmm or @fmm&gt;0]">
        <xsl:if test="position()!=1">, </xsl:if>
        <xsl:value-of select="concat(@id, '(', if (@basefmm) then @basefmm else @fmm, ')')"/>
      </xsl:for-each>
      <xsl:text>&#x0a;</xsl:text>
    </xsl:for-each>
	</xsl:template>
	<xsl:template match="resource">
	  <xsl:value-of select="concat(@type, ' ', @id, ':&#x0a;')"/>
	  <xsl:apply-templates select="*"/>
	  <xsl:text>&#x0a;</xsl:text>
	</xsl:template>
	<xsl:template match="message">
    <xsl:value-of select="concat('  ', @display, '&#x0a;')"/>
	</xsl:template>
</xsl:stylesheet>
