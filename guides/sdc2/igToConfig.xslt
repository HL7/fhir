<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
	<xsl:output method="text" encoding="UTF-8"/>
  <xsl:template match="/ImplementationGuide">
    <xsl:text>{
	"tool": "jekyll",
	"paths": {
		"resources": "resources",
		"pages": "pages",
		"temp": "temp",
		"output": "output",
		"qa": "qa",
		"specification": "http://hl7-fhir.github.io/"
	},
	"defaults": {
		"Any": {
			"template-format": "instance-template-format.html",
			"template-base": "instance-template-base.html"
		}
	},
	"canonicalBase": "</xsl:text>
    <xsl:value-of select="url/@value"/>
    <xsl:text>",
	"source": "</xsl:text>
	  <xsl:value-of select="id/@value"/>
	  <xsl:text>.xml",
  "pre-process": {
    "folder": "content",
    "transform": "correct_content.xslt"
  },
  "spreadsheets": [</xsl:text>
    <xsl:for-each select="package/extension[@url='http://hl7.org/fhir/tools-profile-spreadsheet']/valueUri/@value">
      <xsl:if test="position()!=1">,</xsl:if>
      <xsl:value-of select="concat('&#xa;    &quot;', ., '&quot;')"/>
    </xsl:for-each>
    <xsl:text>
	],
	"resources": {</xsl:text>
	  <xsl:for-each select="package/resource">
      <xsl:variable name="type" select="substring-before(sourceReference/reference/@value, '/')"/>
      <xsl:variable name="id" select="substring-after(sourceReference/reference/@value, '/')"/>
      <xsl:if test="position()!=1 and position()!=last()">,</xsl:if>
      <xsl:value-of select="concat('&#xa;    &quot;', sourceReference/reference/@value, '&quot;:{&#xa;      &quot;base&quot;: &quot;', $id, '&quot;')"/>
      <xsl:if test="$type='StructureDefinition' and not(example/@value='true')">
        <xsl:value-of select="concat(',&#xa;      &quot;defns&quot;: &quot;', $id, '-definitions.html&quot;')"/>
      </xsl:if>
      <xsl:text>
    }</xsl:text>
	  </xsl:for-each>
	  <xsl:text>
	}
}</xsl:text>
  </xsl:template>
</xsl:stylesheet>
