<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/ImplementationGuide">
    <xsl:apply-templates select="page"/>
	</xsl:template>
	<xsl:template match="page">
    <xsl:if test="kind/@value='page'">
      <xsl:result-document method="text" encoding="UTF-8" href="{source/@value}">
        <xsl:text>---&#xa;---&#xa;{% include template-page.html %}</xsl:text>
      </xsl:result-document>
    </xsl:if>
    <xsl:apply-templates select="page"/>
	</xsl:template>
</xsl:stylesheet>
