<?xml version="1.0" encoding="UTF-8"?>
<!--
  - Converts a FHIR IG stored as XML into a JSON file that drives the operation of the IG Publisher tool,
  -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:f="http://hl7.org/fhir">
  <xsl:param name="spec"/>
  <xsl:param name="version"/>
  <xsl:param name="fhirVersion" select="/f:ImplementationGuide/f:fhirVersion/@value"/>
  <xsl:param name="snomedRelease" select="'UV'"/>
	<xsl:output method="text" encoding="UTF-8"/>
  <xsl:template match="/f:ImplementationGuide">
    <xsl:variable name="snomedReleaseNumber">
      <xsl:choose>
        <xsl:when test="$snomedRelease='AU'">32506021000036107</xsl:when>
        <xsl:when test="$snomedRelease='CA'">20611000087101</xsl:when>
        <xsl:when test="$snomedRelease='DK'">554471000005108</xsl:when>
        <xsl:when test="$snomedRelease='ES'">449081005</xsl:when>
        <xsl:when test="$snomedRelease='NL'">11000146104</xsl:when>
        <xsl:when test="$snomedRelease='SE'">45991000052106</xsl:when>
        <xsl:when test="$snomedRelease='UK'">999000041000000102</xsl:when>
        <xsl:when test="$snomedRelease='US'">731000124108</xsl:when>
        <xsl:when test="$snomedRelease='UV'">900000000000207008</xsl:when>
        <xsl:otherwise>
          <xsl:message terminate="yes">
            <xsl:value-of select="concat('ERROR: Unsupported snomedRelease: ', $snomedRelease)"/>
          </xsl:message>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:text>{
	"tool": "jekyll",
  "logging": ["html"],
	"version": "</xsl:text>
	<xsl:value-of select="$fhirVersion"/>
	<xsl:text>",
	</xsl:text>
	<xsl:if test="$version!=''">
    <xsl:value-of select="concat('&quot;fixed-business-version&quot;: &quot;', $version, '&quot;,&#xa;  ')"/>
	</xsl:if>
	<xsl:text>"html-template": "template-page.html",&#xa;  </xsl:text>
	<xsl:text>"paths": {
		"resources": ["resources","../src/examples", "../src/resources", "../src/vocabulary"],
		"pages": ["../src/images", "pages"],
		"temp": "temp",
		"output": "../website",
		"txCache": "txcache",
    "history" : "history.html",
		"qa": "qa",
		"specification": "</xsl:text>
		<xsl:value-of select="$spec"/>
		<xsl:text>"
	},
	"pre-process": [
	  {"folder": "../framework/assets",
	   "relativePath": "assets"},
	  {"folder": "../framework/includes",
	   "relativePath": "_includes"},
	  {"folder": "../src/includes",
	   "relativePath": "_includes"},
	  {"folder": "../src/data",
	   "relativePath": "_data"},
	  {"folder": "../src/pagecontent",
	   "relativePath": "_includes",
	   "transform": "../framework/xslt/processPages.xslt"}
	],
  "extraTemplates": [
    {
      "name": "mappings",
      "description": "Mappings"
    },
    {
      "name": "examples",
      "description": "Examples"
    },
    {
      "name": "profile-xml",
      "description": "Profile XML"
    },
    {
      "name": "profile-json",
      "description": "Profile JSON"
    },
    {
      "name": "profile-ttl",
      "description": "Profile Turtle"
    }
  ],
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
			"template-profile-ttl": "../framework/templates/template-profile-ttl.html",
			"base": "{{[id]}}.html",
			"defns": "{{[id]}}-definitions.html",
			"mappings": "{{[id]}}-mappings.html",
			"examples": "{{[id]}}-examples.html",
			"profile-xml": "{{[id]}}.profile.xml.html",
			"profile-json": "{{[id]}}.profile.json.html",
			"profile-ttl": "{{[id]}}.profile.ttl.html"
		},
		"ValueSet": {
			"template-base": "../framework/templates/template-valueset.html",
			"template-format": "../framework/templates/template-valueset-format.html",
		  "base": "valueset-{{[id]}}.html",
		  "format": "valueset-{{[id]}}.{{[fmt]}}.html"
		}
	},
  "sct-edition" : "http://snomed.info/sct/</xsl:text>
  <xsl:value-of select="$snomedReleaseNumber"/>
  <xsl:text>",
  "no-inactive-codes" : "true",
	"canonicalBase": "</xsl:text>
  <xsl:value-of select="substring-before(f:url/@value, '/ImplementationGuide')"/>
  <xsl:text>",&#xa;	</xsl:text>
  <xsl:for-each select="f:dependency[f:type/@value='reference']/f:uri/@value">
    <xsl:variable name="code">
      <xsl:call-template name="findLast">
        <xsl:with-param name="string" select="."/>
        <xsl:with-param name="split" select="'/'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:value-of select="concat('&quot;dependencyList&quot;: [&#xa;    {&#xa;      &quot;name&quot; : &quot;', $code, '&quot;,&#xa;      &quot;location&quot; : &quot;', ., 
      '&quot;,&#xa;      &quot;source&quot; : &quot;../../', $code, '2/website&quot;&#xa;    }&#xa;  ],&#xa;  ')"/>
  </xsl:for-each>
	<xsl:text>"source": "</xsl:text>
  <xsl:value-of select="f:id/@value"/>
  <xsl:text>.xml",
  "spreadsheets": [</xsl:text>
    <xsl:for-each select="f:package/f:extension[@url='http://hl7.org/fhir/tools-profile-spreadsheet']/f:valueUri/@value">
      <xsl:if test="position()!=1">,</xsl:if>
      <xsl:value-of select="concat('&#xa;    &quot;', ., '&quot;')"/>
    </xsl:for-each>
    <xsl:text>
	],
	"resources": {</xsl:text>
	  <xsl:for-each select="f:package/f:resource">
      <xsl:variable name="type" select="substring-before(f:sourceReference/f:reference/@value, '/')"/>
      <xsl:variable name="id" select="substring-after(f:sourceReference/f:reference/@value, '/')"/>
      <xsl:if test="position()!=1">,</xsl:if>
      <xsl:value-of select="concat('&#xa;    &quot;', f:sourceReference/f:reference/@value, '&quot;:{&#xa;')"/>
      <xsl:if test="f:example/@value='true'">
        <xsl:choose>
          <xsl:when test="$type='ValueSet'">
            <xsl:text>		"template-base": "../framework/templates/template-instance-base.html",&#xa;</xsl:text>
            <xsl:text>		"template-format": "../framework/templates/template-instance-format.html",&#xa;</xsl:text>
            <xsl:text>      "base": "{{[id]}}.html",&#xa;</xsl:text>
            <xsl:text>	    "format": "{{[id]}}.{{[fmt]}}.html"</xsl:text>
          </xsl:when>
          <xsl:when test="$type='StructureDefinition'">
            <xsl:text>		"template-base": "../framework/templates/template-instance-base.html",&#xa;</xsl:text>
            <xsl:text>		"template-format": "../framework/templates/template-instance-format.html",&#xa;</xsl:text>
            <xsl:text>      "template-defns": "",&#xa;</xsl:text>
            <xsl:text>      "template-mappings": "",&#xa;</xsl:text>
            <xsl:text>      "template-examples": "",&#xa;</xsl:text>
            <xsl:text>      "template-profile-xml": "",&#xa;</xsl:text>
            <xsl:text>      "template-profile-json": "",&#xa;</xsl:text>
            <xsl:text>      "template-profile-ttl": "",&#xa;</xsl:text>
            <xsl:text>      "base": "{{[id]}}.html",&#xa;</xsl:text>
            <xsl:text>	    "format": "{{[id]}}.{{[fmt]}}.html"</xsl:text>
          </xsl:when>
        </xsl:choose>
      </xsl:if>
      <xsl:if test="not(f:example/@value='true') and (ancestor::f:ImplementationGuide//f:page[f:source/@value=concat('extension-', $id, '.html')] or starts-with($id, 'ext-')) and $type='StructureDefinition'">
        <xsl:text>      "template-base": "../framework/templates/template-ext.html",&#xa;</xsl:text>
        <xsl:text>      "template-defns": "../framework/templates/template-ext-definitions.html",&#xa;</xsl:text>
        <xsl:text>      "template-mappings": "../framework/templates/template-ext-mappings.html",&#xa;</xsl:text>
        <xsl:text>      "template-examples": "",&#xa;</xsl:text>
        <xsl:text>      "template-profile-xml": "../framework/templates/template-ext-xml.html",&#xa;</xsl:text>
        <xsl:text>      "template-profile-json": "../framework/templates/template-ext-json.html",&#xa;</xsl:text>
        <xsl:text>      "template-profile-ttl": "../framework/templates/template-ext-ttl.html",&#xa;</xsl:text>
        <xsl:text>      "template-format": "",&#xa;</xsl:text>
        <xsl:text>      "base": "extension-{{[id]}}.html",&#xa;</xsl:text>
        <xsl:text>      "defns": "extension-{{[id]}}-definitions.html",&#xa;</xsl:text>
        <xsl:text>      "mappings": "extension-{{[id]}}-mappings.html",&#xa;</xsl:text>
        <xsl:text>      "examples": "extension-{{[id]}}-examples.html",&#xa;</xsl:text>
        <xsl:text>      "profile-xml": "extension-{{[id]}}.profile.xml.html",&#xa;</xsl:text>
        <xsl:text>      "profile-json": "extension-{{[id]}}.profile.json.html",&#xa;</xsl:text>
        <xsl:text>      "profile-ttl": "extension-{{[id]}}.profile.ttl.html"&#xa;</xsl:text>
      </xsl:if>
      <xsl:text>    }</xsl:text>
	  </xsl:for-each>
	  <xsl:text>
	}
}</xsl:text>
  </xsl:template>
  <xsl:template name="findLast">
    <xsl:param name="string"/>
    <xsl:param name="split"/>
    <xsl:choose>
      <xsl:when test="contains($string, $split)">
        <xsl:call-template name="findLast">
          <xsl:with-param name="string" select="substring-after($string, $split)"/>
          <xsl:with-param name="split" select="$split"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$string"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
</xsl:stylesheet>