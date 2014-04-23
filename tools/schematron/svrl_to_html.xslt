<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:svrl="http://purl.oclc.org/dsdl/svrl" version="2.0" exclude-result-prefixes="svrl">
  <xsl:output method="html" indent="yes"/>
  <xsl:template match="svrl:schematron-output">
    <html>
      <head>
        <title>Schematron Validation issues</title>
      </head>
      <body>
        <xsl:apply-templates select="svrl:failed-assert"/>
      </body>
    </html>
  </xsl:template>
  <xsl:template match="svrl:text">
    <h2 align="center">
      <xsl:apply-templates/>
    </h2>
  </xsl:template>
  <xsl:template match="svrl:failed-assert">
    <p>
      <xsl:choose>
        <xsl:when test="lower-case(@flag) = 'error' or not(@flag)">
          <font color="red">ERROR: </font>
        </xsl:when>
        <xsl:when test="lower-case(@flag) = 'warning'">
          <font color="blue">WARNING: </font>
        </xsl:when>
        <xsl:when test="lower-case(@flag) = 'info'">
          <font color="blue">INFO: </font>
        </xsl:when>
      </xsl:choose>
      <font color="black">
        <xsl:value-of select="svrl:text"/>
      </font>
      <xsl:text>&#x0a;</xsl:text>
      <font color="gray">
        <xsl:value-of select="concat('&#x0a;&#09;- occurred at xPath: ', @location)"/>
      </font>
    </p>
  </xsl:template>
</xsl:stylesheet>
