<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes"/>
	<xsl:template match="warnings">
    <div>
      <xsl:for-each select="group">
        <h2>
          <xsl:value-of select="@name"/>
        </h2>
        <xsl:apply-templates select="*"/>
      </xsl:for-each>
    </div>
	</xsl:template>
	<xsl:template match="resource">
    <h3>
      <xsl:choose>
        <xsl:when test="@type='Resource'">
          <a href="{@id}.html">
            <xsl:value-of select="concat(@type, ' ', @id)"/>
          </a>
        </xsl:when>
        <xsl:when test="@type='Data Type'">
          <a href="datatypes.html#{@id}">
            <xsl:value-of select="concat(@type, ' ', @id)"/>
          </a>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(@type, ' ', @id)"/>
        </xsl:otherwise>
      </xsl:choose>
    </h3>
    <ul>
      <xsl:apply-templates select="*"/>
    </ul>
	</xsl:template>
	<xsl:template match="message">
    <li>
      <xsl:value-of select="@display"/>
    </li>
	</xsl:template>
</xsl:stylesheet>
