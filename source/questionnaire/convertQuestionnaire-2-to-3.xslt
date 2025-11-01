<?xml version="1.0" encoding="UTF-8"?>
<!--
  - This transform converts instances conformant with the DSTU 2 version of the Questionnaire resource to be conformant with the DSTU 3 version of the resource.  
  - It should also work to convert interim releases of DSTU 3 questionnaires (e.g., 2.1)
  - This transform is released "as is" and is intended for conversion of example instances.  Use in converting production data is at the user's own risk
  -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://hl7.org/fhir" xpath-default-namespace="http://hl7.org/fhir" exclude-result-prefixes="xs">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:param name="base" select="'http://hl7.org/fhir'">
    <!-- The base URL to use when assigning a URL to the Questionnaire -->
	</xsl:param>
	<xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="Questionnaire">
    <xsl:copy>
      <xsl:apply-templates select="@*|id|meta|implicitRules|language|text|contained|extension|modifierExtension"/>
      <xsl:if test="id/@value and not(url)">
        <url value="{$base}/Questionnaire/{id/@value}"/>
      </xsl:if>
      <xsl:apply-templates select="identifier|version|status|date|publisher|telecom"/>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-category']/valueCodeableConcept">
        <useContext>
          <xsl:apply-templates select="@*|node()"/>
        </useContext>
      </xsl:for-each>
      <xsl:apply-templates select="title|group/title|concept|group/concept"/>
      <xsl:apply-templates select="subjectType"/>
      <xsl:apply-templates select="group/group|group/question|item"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="item">
    <xsl:copy>
      <xsl:apply-templates select="id|extension|modifierExtension|linkId|concept"/>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnare-label']/valueString">
        <prefix>
          <xsl:apply-templates select="@*|node()"/>
        </prefix>
      </xsl:for-each>
      <xsl:apply-templates select="text|type"/>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-enableWhen']">
        <enableWhen>
          <xsl:apply-templates select="@*[not(local-name(.)='url')]|extension[not(@url=('question', 'answered', 'answer'))]"/>
          <xsl:for-each select="extension[@url='question']/valueString">
            <question>
              <xsl:apply-templates select="@*|node()"/>
            </question>
          </xsl:for-each>
          <xsl:for-each select="extension[@url='answered']/valueBoolean">
            <hasAnswer>
              <xsl:apply-templates select="@*|node()"/>
            </hasAnswer>
          </xsl:for-each>
          <xsl:for-each select="extension[@url='answer']">
            <xsl:for-each select="*[starts-with(local-name(.), 'value')]">
              <xsl:variable name="name" select="concat('answer', substring-after(local-name(.), 'value'))"/>
              <xsl:element name="{$name}" namespace="http://hl7.org/fhir">
                <xsl:apply-templates select="@*|node()"/>
              </xsl:element>
            </xsl:for-each>
          </xsl:for-each>
        </enableWhen>
      </xsl:for-each>
      <xsl:apply-templates select="required|repeats"/>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnare-readOnly']/valueBoolean">
        <readOnly>
          <xsl:apply-templates select="@*|node()"/>
        </readOnly>
      </xsl:for-each>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnare-maxLength']/valueInteger">
        <maxLength>
          <xsl:apply-templates select="@*|node()"/>
        </maxLength>
      </xsl:for-each>
      <xsl:apply-templates select="options|option"/>
      <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/questionnare-defaultValue']">
        <xsl:for-each select="*[starts-with(local-name(.), 'value')]">
          <xsl:variable name="name" select="concat('initial', substring-after(local-name(.), 'value'))"/>
          <xsl:element name="{$name}" namespace="http://hl7.org/fhir">
            <xsl:apply-templates select="@*|node()"/>
          </xsl:element>
        </xsl:for-each>
      </xsl:for-each>
      <xsl:apply-templates select="item"/>
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
	<xsl:template match="option[system|code|display]">
    <xsl:copy>
      <xsl:apply-templates select="id"/>
      <valueCoding>
        <xsl:apply-templates select="@*|node()[not(self::id)]"/>
      </valueCoding>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="extension/@url[.='http://hl7.org/fhir/StructureDefinition/questionnaire-questionControl']">
    <xsl:attribute name="url" select="'http://hl7.org/fhir/StructureDefinition/questionnaire-itemControl'"/>
	</xsl:template>
	<xsl:template match="extension[@url='http://hl7.org/fhir/StructureDefinition/sdc-questionnaire-specialGroup']">
    <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-itemControl">
      <valueCodeableConcept>
        <coding>
          <system value="http://hl7.org/fhir/StructureDefinition/questionnaire-item-control"/>
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
              <system value="http://hl7.org/fhir/StructureDefinition/questionnaire-display-category"/>
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
              <system value="http://hl7.org/fhir/StructureDefinition/questionnaire-display-category"/>
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
              <system value="http://hl7.org/fhir/StructureDefinition/questionnaire-display-category"/>
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
	<xsl:template match="extension[ends-with(@url, '/questionnaire-category') or 
	                               ends-with(@url, '/questionnaire-label') or 
	                               ends-with(@url, '/questionnaire-enableWhen') or 
	                               ends-with(@url, '/questionnaire-readOnly') or 
	                               ends-with(@url, '/questionnaire-defaultValue') or 
	                               ends-with(@url, '/questionnaire-maxLength') or
	                               ends-with(@url, '/questionnaire-instruction') or 
	                               ends-with(@url, '/questionnaire-security') or 
	                               ends-with(@url, '/questionnaire-units') or 
	                               ends-with(@url, '/questionnaire-trailing')
	                    ]"/>
	<xsl:template match="required[@value='false']|repeats[@value='false']"/>
</xsl:stylesheet>
