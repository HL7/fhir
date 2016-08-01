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
		  "base": "{{[id]}}.html",
			"template-base": "template-instance-base.html",
			"template-format": "template-instance-format.html"
		},
		"ImplementationGuide": {
			"template-base": "",
			"template-format": ""
		},
		"StructureDefinition": {
			"template-base": "template-profile.html",
			"template-defns": "template-definitions.html",
			"template-mappings": "template-mappings.html",
			"template-examples": "template-examples.html",
			"template-profile-xml": "template-profile-xml.html",
			"template-profile-json": "template-profile-json.html",
			"base": "{{[id]}}.html",
			"defns": "{{[id]}}-definitions.html",
			"profile-xml": "{{[id]}}.profile.xml.html",
			"profile-json": "{{[id]}}.profile.json.html"
		},
		"ValueSet": {
		  "base": "valueset-{{[id]}}.html",
			"template-base": "template-valueset.html"
		}
	},
	"canonicalBase": "</xsl:text>
    <xsl:value-of select="url/@value"/>
    <xsl:text>",
	"extraTemplates": ["mappings", "examples", "profile-xml", "profile-json"],
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
      <xsl:value-of select="concat('&#xa;    &quot;', sourceReference/reference/@value, '&quot;:{&#xa;      &quot;base&quot;: &quot;')"/>
      <xsl:choose>
        <xsl:when test="$type='ValueSet'">valueset-</xsl:when>
        <xsl:when test="$type='StructureDefinition'">
          <xsl:if test="exists(/ImplementationGuide/page[kind/@value='Resource' and source/@value=concat('extension-', $id, '.html')])">extension-</xsl:if>
        </xsl:when>
      </xsl:choose>
      <xsl:text>{{[id]}}.html"&#xa;    }</xsl:text>
	  </xsl:for-each>
	  <xsl:text>
	}
}</xsl:text>
  </xsl:template>
</xsl:stylesheet>
