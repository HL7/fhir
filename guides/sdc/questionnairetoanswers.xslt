<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns="http://hl7.org/fhir" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:f="http://hl7.org/fhir" exclude-result-prefixes="xs f">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
    <xsl:apply-templates select="//f:Questionnaire"/>
	</xsl:template>
	<xsl:template match="f:Questionnaire">
    <QuestionnaireAnswers>
      <questionnaire>
        <reference value="Questionnaire/{f:id/@value}"/>
      </questionnaire>
      <status value="in progress"/>
      <subject>
        <reference value="???"/>
      </subject>
      <authored value="???"/>
      <xsl:apply-templates select="f:group|f:question"/>
    </QuestionnaireAnswers>
	</xsl:template>
	<xsl:template match="f:group">
    <xsl:copy>
      <xsl:variable name="notes" as="xs:string*">
        <xsl:if test="f:repeats/@value='true'">Repeats</xsl:if>
        <xsl:if test="f:required/@value='true'">Required</xsl:if>
      </xsl:variable>
      <xsl:if test="count($notes)&gt;0">
        <xsl:comment select="string-join($notes, ',')"/>
      </xsl:if>
      <xsl:copy-of select="f:linkId|f:title|f:text"/>
      <xsl:apply-templates select="f:group|f:question"/>
    </xsl:copy>
	</xsl:template>
	<xsl:template match="f:question">
    <xsl:copy>
      <xsl:variable name="notes" as="xs:string*">
        <xsl:if test="f:repeats/@value='true'">Repeats</xsl:if>
        <xsl:if test="f:required/@value='true'">Required</xsl:if>
      </xsl:variable>
      <xsl:if test="count($notes)&gt;0">
        <xsl:comment select="string-join($notes, ',')"/>
      </xsl:if>
      <xsl:copy-of select="f:linkId|f:text"/>
      <xsl:choose>
        <xsl:when test="f:type/@value='boolean'">
          <valueBoolean value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='decimal'">
          <valueDecimal value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='integer'">
          <valueInteger value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='date'">
          <valueDate value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='datetime'">
          <valueDateTime value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='instant'">
          <valueInstant value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='time'">
          <valueTime value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='string'">
          <valueString value="??"/>
        </xsl:when>
        <xsl:when test="f:type/@value='Attachment'">
          <valueAttachment/>
        </xsl:when>
        <xsl:when test="f:type/@value=('choice', 'open-choice')">
          <valueCoding>
            <system value=""/>
            <code value=""/>
            <display value=""/>
          </valueCoding>
        </xsl:when>
        <xsl:when test="f:type/@value='reference'">
          <valueReference/>
        </xsl:when>
      </xsl:choose>
      <xsl:apply-templates select="f:group|f:question"/>
    </xsl:copy>
	</xsl:template>
</xsl:stylesheet>
