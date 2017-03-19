<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir" xmlns="">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
  <xsl:template match="/ImplementationGuide">
    <div style="col-9">
      <table style="border: 0px; font-size: 11px; font-family: verdana; vertical-align: top;" cellpadding="0" border="0" cellspacing="0">
        <tbody>
          <xsl:apply-templates select="page"/>
        </tbody>
      </table>
    </div>
  </xsl:template>
  <xsl:template match="page">
    <xsl:param name="level" select="''"/>
    <xsl:param name="images" as="element()*">
      <img style="background-color: inherit" alt="." class="hierarchy" src="tbl_spacer.png"/>
    </xsl:param>
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
    <tr style="border: 0px; padding:0px; vertical-align: top; background-color: white;">
      <td style="vertical-align: top; text-align : left; background-color: white; padding:0px 4px 0px 4px; white-space: nowrap; background-image: url(tbl_bck0.png)" class="hierarchy">
        <xsl:copy-of select="$images"/>
        <xsl:if test="contains($newLevel, '.')">
          <img style="background-color: inherit" alt="." class="hierarchy" src="tbl_vjoin.png"/>
        </xsl:if>
        <img style="background-color: white; background-color: inherit" alt="." class="hierarchy" src="icon_page.gif"/>
        <a title="{name/@value|title/@value}" href="{source/@value}">
          <xsl:value-of select="concat($newLevel, ' ', title/@value, name/@value)"/>
        </a>
      </td>
    </tr>
    <xsl:apply-templates select="page">
      <xsl:with-param name="level" select="$newLevel"/>
      <xsl:with-param name="images" as="element()+">
        <xsl:copy-of select="$images"/>
        <xsl:if test="contains($newLevel, '.')">
          <img style="background-color: inherit" alt="." class="hierarchy" src="tbl_vline.png"/>
        </xsl:if>
      </xsl:with-param>
    </xsl:apply-templates>
  </xsl:template>
</xsl:stylesheet>
