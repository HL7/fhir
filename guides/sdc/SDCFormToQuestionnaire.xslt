<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xpath-default-namespace="http://hl7.org/fhir" xmlns="http://hl7.org/fhir" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:sdc="http://nlm.nih.gov/sdc/form" xmlns:mfi13="http://www.iso.org/19763/13/2013" xmlns:mdr3="http://www.iso.org/11179/3/2013" xmlns:fn="http://hl7.org/xslt/functions" exclude-result-prefixes="sdc mfi13 mdr3 xs fn">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
  <!-- This transform does not yet handle:
       - language translations (not sure we'll encounter those in the US)
       - style (no examples where this is actually used) -->
  <xsl:template match="/">
    <xsl:for-each select="distinct-values(//mfi13:style[not(contains(., ':'))])">
      <xsl:message select="concat('Style appears to be a CSS reference rather than style definition: ', .)"/>
    </xsl:for-each>
    <feed xmlns="http://www.w3.org/2005/Atom" xsi:schemaLocation="http://www.w3.org/2005/Atom ../../schema/fhir-atom.xsd"> 
      <title>Questionnaires</title>
      <author>
        <name>HL7</name>
      </author>
      <id>urn:uuid:this-should-be-a-guid</id>
      <updated>2014-05-15T17:25:15Z</updated>
      <xsl:for-each select="descendant::sdc:form_package">
        <xsl:variable name="questionnaire" as="element(Questionnaire)">
          <xsl:apply-templates select="."/>
        </xsl:variable>
        <xsl:for-each select="$questionnaire">
          <entry>
            <title>Some Questionnaire</title>
            <id>
              <!-- We use the business identifier for the resource id because we have nothing better -->
              <xsl:value-of select="concat(identifier/system/@value, '#', identifier/value/@value)"/>
            </id>
            <updated>2014-05-15T17:25:15Z</updated>
            <content type="text/xml">
              <xsl:copy-of select="."/>
            </content>
          </entry>
        </xsl:for-each>
      </xsl:for-each>
    </feed>
  </xsl:template>
  <xsl:template match="sdc:form_package">
    <Questionnaire xmlns="http://hl7.org/fhir">
      <!-- No endpoint in the examples so far.  Not sure we really need it -->
      <xsl:for-each select="sdc:form_design/sdc:classifier/mdr3:designation[mdr3:sign!='']">
        <!-- Don't care whether they're preferred or not, nor what the context is.  If it's a category, we expose it -->
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#category">
          <valueCodeableConcept>
            <coding>
              <xsl:for-each select="mdr3:namespace">
                <!-- No system specified in the examples, but we can hope that some might populate it.  Would need this to be a URL though. -->
                <system value="{normalize-space(.)}"/>
              </xsl:for-each>
              <code value="{mdr3:sign}"/>
            </coding>
          </valueCodeableConcept>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:administrative_package/sdc:form_language/mfi13:textual_language">
        <language value="{substring(., 1, 2)}">
          <!-- We grab the first 2 characters because we do IETF language codes, not ISO639-3 codes, and IETF requires 2 character where they exist.  
              I'm going to assume the only relevant languages we'd encounter would be the 2-character variety -->
        </language>
      </xsl:for-each>
      <text>
        <status value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml">Todo</div>
      </text>
<!--      <xsl:apply-templates select="descendant::sdc:list_field"/>-->
      <xsl:choose>
        <xsl:when test="starts-with(sdc:form_design/@form_design_identifier, 'http://cap.org')">
          <xsl:variable name="idParts" as="xs:string+" select="tokenize(substring-after(sdc:form_design/@form_design_identifier, 'http://cap.org/eCC/SDC/'), '/')"/>
          <identifier>
            <system value="http://www.cap.org/eCC/SDC"/>
            <value value="{string-join($idParts[position()!=last()], '/')}"/>
          </identifier>
          <version value="{$idParts[last()]}"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:for-each select="sdc:form_design/@form_design_identifier">
            <identifier>
              <system value="{substring-before(., '#')}"/>
              <value value="{substring-after(., '#')}"/>
            </identifier>
          </xsl:for-each>
        </xsl:otherwise>
      </xsl:choose>
      <!-- What do we populate "version" with? -->
      <xsl:if test="not(sdc:administrative_package/sdc:registration[sdc:state/mdr3:administrative_status!=''])">
        <status value="published"/>
        <xsl:for-each select="sdc:administrative_package/sdc:registration/sdc:creation_date">
          <date value="{.}"/>
        </xsl:for-each>
      </xsl:if>
      <xsl:for-each select="sdc:administrative_package/sdc:registration[sdc:state/mdr3:administrative_status!='']">
        <xsl:variable name="state" as="xs:string">
          <xsl:for-each select="sdc:state/mdr3:administrative_status">
            <!-- Don't seem to have any standard vocabulary for this in the SDC spec?  Must have standard vocab for FHIR -->
            <xsl:choose>
              <xsl:when test=".='DRAFT NEW'">draft</xsl:when>
              <xsl:when test=".=('Final', 'RELEASED')">published</xsl:when>
              <xsl:otherwise>
                <xsl:text>Unknown</xsl:text>
                <xsl:message terminate="yes" select="concat('Unrecognized state: ', .)"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:for-each>
        </xsl:variable>
        <status value="{$state}"/>
        <xsl:for-each select="sdc:state/mdr3:effective_date">
          <xsl:choose>
            <xsl:when test="matches(., '\d{4}-\d{2}-\d{2}.*')">
              <date value="{substring(., 1, 10)}"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:message select="concat('Date ', ., ' is not in a valid format and has been ignored.')"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:for-each>
      </xsl:for-each>
      <xsl:for-each select="sdc:administrative_package/sdc:registration/sdc:stewardship_record/sdc:organization/sdc:name">
        <publisher value="{normalize-space(.)}"/>
      </xsl:for-each>
      <group>
        <xsl:call-template name="checkGuards"/>
        <xsl:for-each select="sdc:form_design">
          <xsl:for-each select="sdc:form_design/sdc:designation/sdc:definition[@acceptability='preferred' or not(@acceptability)]/mdr3:text[1]">
            <!-- We only take the first one, if there are multiples.  Need to agree on coded contexts to make it worth it to have multiples. -->
            <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#description">
              <valueString value="{normalize-space(.)}"/>
            </extension>
          </xsl:for-each>
          <xsl:for-each select="sdc:form_design/sdc:security_and_privacy[normalize-space(.)!='']">
            <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#security">
              <valueString value="{normalize-space(.)}"/>
            </extension>
          </xsl:for-each>
          <linkId value="root"/>
          <xsl:for-each select="sdc:designation[@acceptability='preferred' or not(@acceptability)]/sdc:sign">
            <!-- Not bothering with additional titles because we haven't landed on mandating a useful context yet.  
                The example that exists with multiple titles doesn't have a good reason for existing -->
            <xsl:if test="position()=1">
              <title value="{normalize-space(.)}"/>
            </xsl:if>
          </xsl:for-each>
          <xsl:apply-templates select="sdc:header|sdc:section|sdc:footer"/>
        </xsl:for-each>
      </group>
    </Questionnaire>
  </xsl:template>
  <xsl:template match="sdc:list_field" as="element(contained)+">
    <xsl:variable name="url" as="xs:string" select="parent::sdc:question/sdc:question_identifier"/>
    <xsl:variable name="id" as="xs:string" select="generate-id()"/>
    <contained>
      <ValueSet id="{$id}-vs">
        <status value="active"/>
        <define>
          <system value="{$url}-cs"/>
          <xsl:for-each select="sdc:list_item">
            <concept>
              <code value="{mfi13:value}"/>
              <xsl:for-each select="mfi13:item_prompt/mfi13:label">
                <display value="{normalize-space(.)}">
                  <xsl:call-template name="doStyle"/>
                </display>
              </xsl:for-each>
              <xsl:for-each select="mfi13:value_meaning/mfi13:label">
                <definition value="{normalize-space(.)}">
                  <xsl:call-template name="doStyle"/>
                </definition>
              </xsl:for-each>
            </concept>
          </xsl:for-each>
        </define>
      </ValueSet>
    </contained>
    <xsl:if test="sdc:list_item[sdc:value_meaning_terminology_code]">
      <contained>
        <ConceptMap id="{$id}-cm">
          <!-- Note: The mappings are all expressed as having a code of "none", so not really clear on the value of having a mapping . . . -->
          <status value="active"/>
          <sourceResource>
            <reference value="#{$id}-vs"/>
          </sourceResource>
          <targetResource>
            <reference value="#2.16.840.1.113883.3.26.1-vs"/>
          </targetResource>
          <xsl:for-each select="sdc:list_item">
            <element>
              <codeSystem value="{$url}-cs"/>
              <code value="{mfi13:value}"/>
              <map>
                <xsl:choose>
                  <xsl:when test="sdc:value_meaning_terminology_code='None'">
                    <equivalence value="unmatched"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:message terminate="yes" select="concat('We actually got a real mapping - need to add support for: ', sdc:value_meaning_terminology_code)"/>
                  </xsl:otherwise>
                </xsl:choose>
              </map>
            </element>
          </xsl:for-each>
        </ConceptMap>
      </contained>
    </xsl:if>
  </xsl:template>
  <xsl:template name="doCardinality" as="element()*">
    <xsl:if test="mfi13:cardinality/mfi13:minimum!='0'">
      <required value="true"/>
    </xsl:if>
    <xsl:if test="mfi13:cardinality/mfi13:maximum!='1' or */mfi13:multiselect='true'">
      <repeats value="true"/>
    </xsl:if>
  </xsl:template>
  <xsl:template name="doCardinalityExtensions" as="element(extension)*">
    <xsl:for-each select="mfi13:cardinality/mfi13:minimum[not(.=('0', '1'))]">
      <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#minOccurs">
        <valueInteger value="{.}"/>
      </extension>
    </xsl:for-each>
    <xsl:for-each select="mfi13:cardinality/mfi13:maximum[not(.=('1', 'unbounded'))]">
      <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#maxOccurs">
        <valueInteger value="{.}"/>
      </extension>
    </xsl:for-each>
  </xsl:template>
  <xsl:template match="sdc:header|sdc:section|sdc:footer|sdc:contained_section" as="element(group)">
    <group>
      <xsl:call-template name="checkGuards"/>
      <xsl:if test="self::sdc:header or self::sdc:footer">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-sdc#specialGroup">
          <valueCode value="{local-name(.)}"/>
        </extension>
      </xsl:if>
      <xsl:for-each select="sdc:section_number/mfi13:label">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-sdc#label">
          <valueString value="{.}">
            <xsl:call-template name="doStyle"/>
          </valueString>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:additional_instruction/mfi13:label">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#instruction">
          <valueString value="{normalize-space(.)}">
            <xsl:call-template name="doStyle"/>
          </valueString>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:additional_text/mfi13:label">
        <xsl:message>No support for generic 'additional_text'</xsl:message>
      </xsl:for-each>
      <xsl:call-template name="doCardinalityExtensions"/>
      <xsl:for-each select="sdc:section_identifier">
        <xsl:choose>
          <xsl:when test="contains(., '#')">
            <linkId value="{substring-after(., '#')}"/>
          </xsl:when>
          <xsl:otherwise>
            <linkId value="{.}"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:for-each>
      <xsl:for-each select="sdc:section_title/mfi13:label">
        <title value="{normalize-space(.)}">
          <xsl:call-template name="doStyle"/>
        </title>
      </xsl:for-each>
      <xsl:if test="sdc:section_instruction/mfi13:label[.!='']">
        <text value="{string-join(sdc:section_instruction/mfi13:label[.!=''], '&#x0a;')}">
          <xsl:choose>
            <xsl:when test="count(sdc:section_instruction/mfi13:label)=1">
              <xsl:for-each select="sdc:section_instruction/mfi13:label">
                <xsl:call-template name="doStyle"/>
              </xsl:for-each>
            </xsl:when>
            <xsl:when test="sdc:section_instruction/mfi13:style">
              <xsl:message select="'Multiple styles for section_instruction.  Need to convert to HTML - not yet supported'"/>
            </xsl:when>
          </xsl:choose>
        </text>
      </xsl:if>
      <xsl:call-template name="doCardinality"/>
      <xsl:apply-templates select="sdc:question|sdc:contained_section"/>
    </group>
  </xsl:template>
  <xsl:template match="sdc:question" as="element(question)">
    <xsl:variable name="questionId" as="xs:string" select="if (contains(sdc:question_identifier, '#')) then substring-after(sdc:question_identifier, '#') else sdc:question_identifier/text()"/>
    <xsl:variable name="type" as="xs:string">
      <xsl:choose>
        <!-- Todo: do we want something explicit for file, duration and/or time? -->
        <xsl:when test="(sdc:list_field or sdc:lookup_field) and count(sdc:list_field/sdc:list_item[sdc:fill_in='true'])=1">open-choice</xsl:when>
        <xsl:when test="sdc:list_field or sdc:lookup_field">choice</xsl:when>
        <xsl:when test="*/sdc:datatype='string' or not(*/sdc:datatype)">string</xsl:when>
        <xsl:when test="*/sdc:datatype='integer'">integer</xsl:when>
        <xsl:when test="*/sdc:datatype='decimal'">decimal</xsl:when>
        <xsl:when test="*/sdc:datatype='string_date'">date</xsl:when>
        <xsl:when test="*/sdc:datatype='international_dateTime'">dateTime</xsl:when>
        <xsl:when test="*/sdc:datatype='string_time'">string</xsl:when>
        <xsl:when test="*/sdc:datatype='duration'">string</xsl:when>
        <xsl:when test="*/sdc:datatype='file'">string</xsl:when>
        <xsl:otherwise>string</xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <question>
      <xsl:call-template name="doCardinalityExtensions"/>
      <xsl:for-each select="sdc:question_number/mfi13:label">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-sdc#label">
          <valueString value="{.}">
            <xsl:call-template name="doStyle"/>
          </valueString>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:additional_instruction/mfi13:label|sdc:question_instruction/mfi13:label">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#instruction">
          <xsl:call-template name="doStyle"/>
          <valueString value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:text_after_question/mfi13:label">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#trailing">
          <valueString value="{normalize-space(.)}">
            <xsl:call-template name="doStyle"/>
          </valueString>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="sdc:*/mfi13:unit_of_measure[normalize-space(.)!='']">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#units">
          <valueString value="{normalize-space(.)}"/>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/mfi13:default_value">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#default">
          <xsl:choose>
            <xsl:when test="$type='choice'">
              <xsl:for-each select="parent::*/sdc:list_item[sdc:list_item_identifier=current()]">
                <xsl:call-template name="doCoding">
                  <xsl:with-param name="questionId" select="$questionId"/>
                </xsl:call-template>
              </xsl:for-each>
            </xsl:when>
            <xsl:otherwise>
              <xsl:element name="value{upper-case(substring($type,1,1))}{substring($type,2)}" namespace="http://hl7.org/fhir">
                <xsl:attribute name="value" select="."/>
              </xsl:element>
            </xsl:otherwise>
          </xsl:choose>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:default_element">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#default">
          <xsl:for-each select="following-sibling::sdc:list_item[sdc:list_item_identifier=current()/sdc:list_item_identifier]">
            <xsl:call-template name="doCoding">
              <xsl:with-param name="questionId" select="$questionId"/>
            </xsl:call-template>
          </xsl:for-each>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:string/sdc:minimum_characters">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#minLength">
          <valueInteger value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:choose>
        <xsl:when test="*/sdc:datatype/sdc:string/sdc:maximum_characters">
          <xsl:for-each select="*/sdc:datatype/sdc:string/sdc:maximum_characters">
            <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#maxLength">
              <valueInteger value="{.}"/>
            </extension>
          </xsl:for-each>
        </xsl:when>
        <xsl:otherwise>
          <xsl:for-each select="*/mfi13:maximum_character_quantity">
            <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#maxLength">
              <valueInteger value="{.}"/>
            </extension>
          </xsl:for-each>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:for-each select="*/sdc:datatype/sdc:string/sdc:reg_ex">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#regex">
          <valueString value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:string/sdc:pattern|*/sdc:format">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#entryFormat">
          <valueString value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:*/sdc:minimum_value">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#minValue">
          <xsl:choose>
            <xsl:when test="parent::sdc:integer">
              <valueInteger value="{.}"/>
            </xsl:when>
            <xsl:when test="parent::sdc:decimal">
              <valueDecimal value="{.}"/>
            </xsl:when>
          </xsl:choose>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:*/sdc:maximum_value">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#maxValue">
          <xsl:choose>
            <xsl:when test="parent::sdc:integer">
              <valueInteger value="{.}"/>
            </xsl:when>
            <xsl:when test="parent::sdc:decimal">
              <valueDecimal value="{.}"/>
            </xsl:when>
          </xsl:choose>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:decimal/sdc:fractionDigits">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#maxDecimalPlaces">
          <valueInteger value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:for-each select="ancestor::sdc:form_package/sdc:mapping_package/sdc:mdr_mapping/sdc:question_element_data_element_association[mfi13:question_element_identifier=current()/sdc:question_identifier]">
        <!-- May want to move this to a mapping resource -->
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#deReference">
          <valueResource>
            <reference value="{fn:escapeSpaces(mfi13:data_element_scoped_identifier|mfi13:data_element_identifier)}"/>
          </valueResource>
        </extension>
        <!-- For now, not including the dex_mapping_specification stuff.  Seems like that should live in CDE, not Questionnaire -->
      </xsl:for-each>
      <xsl:for-each select="*/sdc:datatype/sdc:decimal/sdc:fractionDigits">
        <extension url="http://hl7.org/fhir/StructureDefinition/element-extensions#minOccurs">
          <valueInteger value="{.}"/>
        </extension>
      </xsl:for-each>
      <xsl:call-template name="checkGuards"/>
      <xsl:if test="*/sdc:default_element/sdc:read_only='true' or */mfi13:default_value_read_only='true'">
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#defaultAsFixed">
          <valueBoolean value="true"/>
        </extension>
      </xsl:if>
      <linkId value="{$questionId}"/>
      <xsl:for-each select="sdc:question_prompt/mfi13:label[.!='']">
        <text value="{normalize-space(.)}">
          <xsl:call-template name="doStyle"/>
        </text>
      </xsl:for-each>
      <xsl:if test="not(sdc:question_prompt/mfi13:label[.!=''])">
        <text value="HIDDEN QUESTION"/>
      </xsl:if>
      <type value="{$type}"/>
      <xsl:call-template name="doCardinality"/>
      <xsl:for-each select="sdc:list_field">
        <options>
          <reference value="#{generate-id()}-vs"/>
        </options>
      </xsl:for-each>
      <xsl:for-each select="sdc:lookup_field">
        <options>
          <reference value="{sdc:end_point}"/>
        </options>
      </xsl:for-each>
      <xsl:for-each select="sdc:list_field/sdc:list_item[mfi13:fill_in='true']">
        <xsl:variable name="answerId" select="if (contains(sdc:list_item_identifier, '#')) then substring-after(sdc:list_item_identifier, '#') else sdc:list_item_identifier"/>
        <group>
          <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen">
            <xsl:variable name="baseQuestionId" as="xs:string" select="ancestor::sdc:question[1]/sdc:question_identifier"/>
            <xsl:variable name="questionId" as="xs:string" select="if (contains($baseQuestionId, '#')) then substring-after($baseQuestionId, '#') else $baseQuestionId"/>
            <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen.question">
              <valueString value="{$questionId}"/>
            </extension>
            <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen.value">
              <xsl:call-template name="doCoding">
                <xsl:with-param name="questionId" select="$questionId"/>
              </xsl:call-template>
            </extension>
          </extension>
          <linkId value="{$answerId}-fillin-grp"/>
          <required value="true"/>
          <repeats value="false"/>
          <question>
            <linkId value="{$answerId}-fillin-answer"/>
            <xsl:for-each select="mfi13:item_prompt/mfi13:label">
              <text value="{.}">
                <xsl:call-template name="doStyle"/>
              </text>
            </xsl:for-each>
            <required value="true"/>
            <repeats value="false"/>
          </question>
        </group>
      </xsl:for-each>
      <xsl:apply-templates select="sdc:list_field/sdc:list_item/sdc:guard/sdc:guarded_element/sdc:section"/>
      <xsl:for-each select="sdc:list_field/sdc:list_item/sdc:guard/sdc:guarded_element[sdc:question]">
        <group>
          <xsl:call-template name="checkGuards"/>
          <xsl:for-each select="parent::sdc:guard/parent::sdc:list_item/sdc:list_item_identifier">
            <linkId value="{if (contains(., '#')) then substring-after(., '#') else .}"/>
          </xsl:for-each>
          <required value="false"/>
          <repeats value="false"/>
          <xsl:apply-templates select="sdc:question"/>
        </group>
      </xsl:for-each>
    </question>
  </xsl:template>
  <xsl:template name="doCoding">
    <xsl:param name="questionId" as="xs:string"/>
    <valueCoding>
      <system value="{$questionId}-cs"/>
      <xsl:for-each select="mfi13:value[.!='']">
        <code value="{.}"/>
      </xsl:for-each>
      <xsl:if test="normalize-space(mfi13:value)=''">
        <xsl:for-each select="mfi13:item_prompt/mfi13:label">
          <display value="{.}"/>
        </xsl:for-each>
      </xsl:if>
    </valueCoding>
  </xsl:template>
  <xsl:template name="doStyle">
    <xsl:if test="parent::*/mfi13:style[contains(., ':')]">
      <extension url="http://hl7.org/fhir/StructureDefinition/rendering-extensions#style">
        <valueString value="{string-join(parent::*/mfi13:style/text(), ';')}"/>
      </extension>
    </xsl:if>
  </xsl:template>
  <xsl:template name="checkGuards">
    <xsl:for-each select="ancestor::sdc:form_design//sdc:guarded_element_identifier[.=(current()/sdc:section_identifier, current()/sdc:question_identifier)]">
      <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen">
        <xsl:variable name="questionId" as="xs:string" select="ancestor::sdc:question[1]/sdc:question_identifier"/>
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen.question">
          <valueString value="{substring-after($questionId, '#')}"/>
        </extension>
        <extension url="http://hl7.org/fhir/StructureDefinition/questionnaire-extensions#displayWhen.value">
          <xsl:for-each select="parent::sdc:guard/parent::sdc:list_item">
            <valueCoding>
              <system value="{$questionId}-cs"/>
              <code value="{mfi13:value}"/>
            </valueCoding>
          </xsl:for-each>
        </extension>
      </extension>
    </xsl:for-each>
  </xsl:template>
  <xsl:function name="fn:escapeSpaces" as="xs:string">
    <xsl:param name="baseString" as="xs:string"/>
    <xsl:variable name="string" select="normalize-space($baseString)"/>
    <xsl:choose>
      <xsl:when test="contains($string, ' ')">
        <xsl:value-of select="concat(substring-before($string, ' '), '%20', fn:escapeSpaces(substring-after($string, ' ')))"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$string"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>
</xsl:stylesheet>