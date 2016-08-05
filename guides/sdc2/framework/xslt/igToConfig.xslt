<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xpath-default-namespace="http://hl7.org/fhir">
  <xsl:param name="spec"/>
	<xsl:output method="text" encoding="UTF-8"/>
  <xsl:template match="/ImplementationGuide">
    <xsl:text>{
	"tool": "jekyll",
	"paths": {
		"resources": "resources",
		"pages": "pages",
		"temp": "temp",
		"output": "../website",
		"txCache": "../txcache",
    "history" : "history.html",
		"qa": "qa",
		"specification": "</xsl:text>
		<xsl:value-of select="$spec"/>
		<xsl:text>"
	},
	"defaults": {
		"Any": {
			"template-base": "../framework/templates/template-instance-base.html",
			"template-format": "../framework/templates/template-instance-format.html",
		  "base": "{{[id]}}.html",
		  "format": "{{[id]}}.{{[fmt]}}.html"
		},
		"ImplementationGuide": {
			"template-base": "",
			"template-format": ""
		},
		"StructureDefinition": {
			"template-base": "../framework/templates/template-profile.html",
			"template-defns": "../framework/templates/template-profile-definitions.html",
			"template-mappings": "../framework/templates/template-profile-mappings.html",
			"template-examples": "../framework/templates/template-profile-examples.html",
			"template-profile-xml": "../framework/templates/template-profile-xml.html",
			"template-profile-json": "../framework/templates/template-profile-json.html",
			"base": "{{[id]}}.html",
			"defns": "{{[id]}}-definitions.html",
			"mappings": "{{[id]}}-mappings.html",
			"examples": "{{[id]}}-examples.html",
			"profile-xml": "{{[id]}}.profile.xml.html",
			"profile-json": "{{[id]}}.profile.json.html"
		},
		"ValueSet": {
			"template-base": "../framework/templates/template-valueset.html",
			"template-format": "../framework/templates/template-valueset-format.html",
		  "base": "valueset-{{[id]}}.html",
		  "format": "valueset-{{[id]}}.{{[fmt]}}.html"
		}
	},
	"canonicalBase": "</xsl:text>
    <xsl:value-of select="url/@value"/>
    <xsl:text>",
	"extraTemplates": ["mappings", "examples", "profile-xml", "profile-json"],
	"source": "</xsl:text>
	  <xsl:value-of select="id/@value"/>
	  <xsl:text>.xml",
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
      <xsl:if test="position()!=1">,</xsl:if>
      <xsl:value-of select="concat('&#xa;    &quot;', sourceReference/reference/@value, '&quot;:{&#xa;')"/>
      <xsl:if test="example/@value='true'">
        <xsl:choose>
          <xsl:when test="$type='ValueSet'">
            <xsl:text>      "base": "{{[id]}}.html"&#xa;</xsl:text>
          </xsl:when>
          <xsl:when test="$type='StructureDefinition'">
            <xsl:text>      "template-defns": "",&#xa;</xsl:text>
            <xsl:text>      "template-mappings": "",&#xa;</xsl:text>
            <xsl:text>      "template-examples": "",&#xa;</xsl:text>
            <xsl:text>      "template-profile-xml": "",&#xa;</xsl:text>
            <xsl:text>      "template-profile-json": ""&#xa;</xsl:text>
          </xsl:when>
        </xsl:choose>
      </xsl:if>
      <xsl:if test="not(example/@value='true') and exists(ancestor::ImplementationGuide//page[source/@value=concat('extension-', $id, '.html')]) and $type='StructureDefinition'">
        <xsl:text>      "template-base": "../framework/templates/template-ext.html",&#xa;</xsl:text>
        <xsl:text>      "template-defns": "../framework/templates/template-ext-definitions.html",&#xa;</xsl:text>
        <xsl:text>      "template-mappings": "../framework/templates/template-ext-mappings.html",&#xa;</xsl:text>
        <xsl:text>      "template-examples": "",&#xa;</xsl:text>
        <xsl:text>      "template-profile-xml": "../framework/templates/template-ext-xml.html",&#xa;</xsl:text>
        <xsl:text>      "template-profile-json": "../framework/templates/template-ext-json.html",&#xa;</xsl:text>
        <xsl:text>      "base": "extension-{{[id]}}.html",&#xa;</xsl:text>
        <xsl:text>      "defns": "extension-{{[id]}}-definitions.html",&#xa;</xsl:text>
        <xsl:text>      "mappings": "extension-{{[id]}}-mappings.html",&#xa;</xsl:text>
        <xsl:text>      "examples": "extension-{{[id]}}-examples.html",&#xa;</xsl:text>
        <xsl:text>      "profile-xml": "extension-{{[id]}}.profile.xml.html",&#xa;</xsl:text>
        <xsl:text>      "profile-json": "extension-{{[id]}}.profile.json.html"&#xa;</xsl:text>
      </xsl:if>
      <xsl:text>    }</xsl:text>
	  </xsl:for-each>
	  <xsl:text>
	}
}</xsl:text>
  </xsl:template>
</xsl:stylesheet>
