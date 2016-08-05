<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:f="http://hl7.org/fhir" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" exclude-result-prefixes="xsi">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
	<xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="@xsi:schemaLocation|f:extension[@url='http://hl7.org/fhir/tools-profile-spreadsheet']"/>
	<xsl:template match="fakematch">
    <!-- This is here to avoid a warning when processing non-namespaced files -->
	</xsl:template>
</xsl:stylesheet>
