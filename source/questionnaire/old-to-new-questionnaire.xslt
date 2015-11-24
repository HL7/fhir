<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://hl7.org/fhir" xpath-default-namespace="http://hl7.org/fhir" exclude-result-prefixes="xs">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="node()|@*">
    <xsl:copy>
      <xsl:apply-templates select="node()|@*"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="Questionnaire">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()[not(self::group or self::subjectType)]"/>
      <xsl:apply-templates select="group/title|group/concept"/>
      <xsl:apply-templates select="subjectType|group/group|group/question"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="group">
    <xsl:choose>
      <xsl:when test="not(count(question)=1) or title or text">
        <item>
          <xsl:apply-templates select="@*|extension|modifierExtension|linkId|concept"/>
          <xsl:for-each select="title">
            <text>
              <xsl:apply-templates select="@*|linkId|concept"/>
            </text>
          </xsl:for-each>
          <type value="group"/>
          <xsl:apply-templates select="required|repeats"/>
          <xsl:for-each select="text">
            <item>
              <xsl:apply-templates select="."/>
              <type value="display"/>
            </item>
          </xsl:for-each>
          <xsl:call-template name="handleExtensions"/>
          <xsl:apply-templates select="group|question"/>
          <xsl:call-template name="handleTrailing"/>
        </item>
      </xsl:when>
      <xsl:otherwise>
        <xsl:apply-templates select="question"/>
      </xsl:otherwise>
    </xsl:choose>
	</xsl:template>
	<xsl:template match="question">
    <item>
      <xsl:apply-templates select="@*|node()[not(self::group)]"/>
      <xsl:call-template name="handleExtensions"/>
      <xsl:for-each select="group">
        <xsl:choose>
          <xsl:when test="title|text|extension">
            <xsl:apply-templates select="."/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:apply-templates select="question"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:for-each>
      <xsl:call-template name="handleTrailing"/>
    </item>
	</xsl:template>
	<xsl:template match="extension[ends-with(@url, '/questionnaire-instruction') or ends-with(@url, '/questionnaire-security') or ends-with(@url, '/questionnaire-units') or ends-with(@url, 'questionnaire-trailing')]"/>
	<xsl:template match="extension/@url[.='http://hl7.org/fhir/StructureDefinition/questionnaire-questionControl']">
    <xsl:attribute name="url" select="'http://hl7.org/fhir/StructureDefinition/questionnaire-itemControl'"/>
	</xsl:template>
	<xsl:template match="extension[@url='http://hl7.org/fhir/StructureDefinition/sdc-questionnaire-specialGroup']">
    <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-itemControl">
      <valueCodeableConcept>
        <coding>
          <system value="http://hl7.org/fhir/questionnaire-item-control"/>
          <code value="{valueCode/@value}"/>
        </coding>
      </valueCodeableConcept>
    </extension>
	</xsl:template>
	<xsl:template name="handleExtensions">
    <xsl:for-each select="parent::*/extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-instruction']">
      <item>
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-displayCategory">
          <valueCodeableConcept>
            <coding>
              <system value="http://hl7.org/fhir/questionnaire-display-category"/>
              <code value="instructions"/>
            </coding>
          </valueCodeableConcept>
        </extension>
        <xsl:for-each select="valueString">
          <text>
            <xsl:apply-templates select="@*|node()"/>
          </text>
        </xsl:for-each>
        <type value="display"/>
      </item>
    </xsl:for-each>
    <xsl:for-each select="parent::*/extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-security']">
      <item>
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-displayCategory">
          <valueCodeableConcept>
            <coding>
              <system value="http://hl7.org/fhir/questionnaire-display-category"/>
              <code value="security"/>
            </coding>
          </valueCodeableConcept>
        </extension>
        <xsl:for-each select="valueString">
          <text>
            <xsl:apply-templates select="@*|node()"/>
          </text>
        </xsl:for-each>
        <type value="display"/>
      </item>
    </xsl:for-each>
    <xsl:for-each select="parent::*/extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-unit']">
      <item>
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-displayCategory">
          <valueCodeableConcept>
            <coding>
              <system value="http://hl7.org/fhir/questionnaire-display-category"/>
              <code value="units"/>
            </coding>
          </valueCodeableConcept>
        </extension>
        <xsl:for-each select="valueString">
          <text>
            <xsl:apply-templates select="@*|node()"/>
          </text>
        </xsl:for-each>
        <type value="display"/>
      </item>
    </xsl:for-each>
	</xsl:template>
	<xsl:template name="handleTrailing">
    <xsl:for-each select="parent::*/extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-trailing']">
      <item>
        <xsl:for-each select="valueString">
          <text>
            <xsl:apply-templates select="@*|node()"/>
          </text>
        </xsl:for-each>
        <type value="display"/>
      </item>
    </xsl:for-each>
  </xsl:template>
</xsl:stylesheet>
