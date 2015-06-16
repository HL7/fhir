<?xml version="1.0" encoding="UTF-8"?>
<!--
  - Released under the terms of the FHIR license
  - Initial development by Lloyd McKenzie, Gevity Consulting Ltd.
  -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:f="http://hl7.org/fhir" xmlns:saxon="http://saxon.sf.net/" xmlns:msxsl="urn:schemas-microsoft-com:xslt" xmlns:common="http://exslt.org/common" exclude-result-prefixes="f saxon msxsl common html">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="useMicrosoft" select="'false'">
    <!-- If set to true, will use microsoft XML conventions -->
  </xsl:param>
  <xsl:param name="numberSections" select="'false'">
    <!-- If set to true, will auto-generate labels for sections if labels are not present -->
  </xsl:param>
  <xsl:param name="defaultAnswerRepetitions" select="1">
    <!-- This is the number of times repeating answers will be included by default if they don't have a declared minimum number of repetitions -->
  </xsl:param>
  <xsl:param name="defaultGroupRepetitions" select="1">
    <!-- This is the number of times repeating groups will be included by default if they don't have a declared minimum number of repetitions -->
  </xsl:param>
  <xsl:param name="maxCheckboxCodings" select="6">
    <!-- This is the maximum number of check boxes or radio buttons that will be permitted for coded elements before going to list box -->
  </xsl:param>
  <xsl:param name="maxListboxCodings" select="20">
    <!-- This is the maximum number of list box entries that will be permitted for coded elements before going to dialog box -->
  </xsl:param>
  <xsl:param name="enableReset" select="'false'">
    <!-- If set to 'true', this will display a reset button on sections to remove data -->
  </xsl:param>
  <xsl:param name="expansionServer" select="'http://fhir-dev.healthintersections.com.au/open'">
    <!-- The base URI of the server to use for value set expansions -->
  </xsl:param>
  <xsl:param name="iconPath" select="'http://fhir-dev.healthintersections.com.au'">
    <!-- The path at which the html-form-add.png and html-form-delete.png icons can be found.  If not present, text will be used -->
  </xsl:param>
  <xsl:param name="jQueryPath" select="'http://fhir-dev.healthintersections.com.au/js'">
    <!-- The path at which the various jQuery plugins can be found.  If not present, large code lookups cannot be performed -->
  </xsl:param>
  <xsl:param name="saveOnly" select="'false'">
    <!-- If true, the "draft" save will be disabled -->
  </xsl:param>
  <xsl:param name="suppressWarnings" select="'false'">
    <!-- If true, warning messages will not be displayed -->
  </xsl:param>
  <xsl:param name="instructionStyle" select="'font-style:italic'"/>
  <xsl:param name="securityStyle" select="'color:red'"/>
  <xsl:variable name="htmlNamespace" select="'http://www.w3.org/1999/xhtml'"/>
  <xsl:variable name="phrases">
    <!-- These are words that will be embedded within the generated HTML.  Captured as a variable to allow overriding in downstream transforms -->
    <phrase xmlns="" name="form" value="Form"/>
  </xsl:variable>
  <xsl:template match="/">
    <xsl:if test="not(f:Questionnaire)">
      <xsl:message terminate="yes">ERROR: This transform only works on FHIR Questionnaires.  Terminating.</xsl:message>
    </xsl:if>
    <xsl:variable name="newQuestionnaire">
      <xsl:apply-templates mode="upgradeQuestionnaire" select="."/>
    </xsl:variable>
<!--    <xsl:choose>
      <xsl:when test="$useMicrosoft='true'">
        <xsl:copy-of select="msxsl:node-set($newQuestionnaire)/f:Questionnaire"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:copy-of select="common:node-set($newQuestionnaire)/f:Questionnaire"/>
      </xsl:otherwise>
    </xsl:choose>-->
    <xsl:choose>
      <xsl:when test="$useMicrosoft='true'">
        <xsl:apply-templates select="msxsl:node-set($newQuestionnaire)/f:Questionnaire"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:apply-templates select="common:node-set($newQuestionnaire)/f:Questionnaire"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <!-- ===============================================
     - = Convert old-style questionnaire to new style
     - =============================================== -->
  <!-- Note: This conversion doesn't pay attention to order of elements, only names and nesting levels -->
  <xsl:template mode="upgradeQuestionnaire" match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-minOccurs']"/> <!-- done -->
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxOccurs']"/> <!-- done -->
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-enableWhen']">
    <xsl:element name="_enableWhen" namespace="http://hl7.org/fhir">
      <xsl:for-each select="f:extension[@url='question']/valueString">
        <xsl:element name="question" namespace="http://hl7.org/fhir">
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </xsl:element>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='answer']/*">
        <xsl:element name="answer{substring-after(local-name(.), 'value')}" namespace="http://hl7.org/fhir">
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </xsl:element>
      </xsl:for-each>
    </xsl:element>
  </xsl:template>
  <!-- label, hidden, instruction, security, tooltip, style, markup -->
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-label']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-hidden']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-defaultAsFixed']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-allowedResource']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-instruction']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-security']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-help']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-tooltip']| 
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-trailing']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-units']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxLength']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-sdc-endpoint']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/minLength']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/regex']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/entryFormat']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/maxDecimalPlaces']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/maxSize']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/mimeType']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/style']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/markup']">
    <xsl:variable name="baseName">
      <xsl:for-each select="substring-after(@url, 'StructureDefinition/')">
        <xsl:choose>
          <xsl:when test="starts-with(., 'questionnaire-sdc-')">
            <xsl:value-of select="substring-after(., 'questionnaire-sdc-')"/>
          </xsl:when>
          <xsl:when test="starts-with(., 'questionnaire-')">
            <xsl:value-of select="substring-after(., 'questionnaire-')"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:value-of select="."/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:for-each>
    </xsl:variable>
    <xsl:element name="_{$baseName}" namespace="http://hl7.org/fhir">
      <xsl:for-each select="*">
        <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
      </xsl:for-each>
    </xsl:element>  
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-defaultValue']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/minValue']|
                                                   f:extension[@url='http://hl7.org/fhir/StructureDefinition/maxValue']">
    <xsl:variable name="name" select="concat('_', substring-after(@url, 'StructureDefinition/'), substring-after(local-name(*), 'value'))"/>
    <xsl:element name="{$name}" namespace="http://hl7.org/fhir">
      <xsl:for-each select="*">
        <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
      </xsl:for-each>
    </xsl:element>  
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:Questionnaire/f:name">
    <xsl:variable name="title">
      <xsl:choose>
        <xsl:when test="f:text/@value">
          <xsl:value-of select="f:text/@value"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="f:code/f:display/@value[1]"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:if test="normalize-space($title)!=''">
      <f:title value="{normalize-space($title)}"/>
    </xsl:if>
    <f:code>
      <xsl:copy-of select="@*|node()"/>
    </f:code>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:group">
    <xsl:choose>
      <xsl:when test="f:text/@value!='' or f:title/@value!='' or f:group or count(f:question)&gt;1 or count(f:extension[@url='http://www.healthintersections.com.au/fhir/StructureDefinition/metadata-type'])!=0 or f:repeats/@value='true'">
        <xsl:copy>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*"/>
          <xsl:call-template name="upgradeCardinality"/>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="node()"/>
        </xsl:copy>
      </xsl:when>
      <xsl:otherwise>
        <xsl:apply-templates mode="upgradeQuestionnaire" select="f:question">
          <xsl:with-param name="additionalPrefix" select="concat(f:linkId/@value, '[1]')"/>
        </xsl:apply-templates>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="upgradeCardinality">
    <f:_minOccurs>
      <xsl:choose>
        <xsl:when test="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-minOccurs']">
          <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-minOccurs']/f:valueInteger">
            <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
          </xsl:for-each>
        </xsl:when>
        <xsl:when test="f:required/@value='true'">
          <xsl:attribute name="value">1</xsl:attribute>
        </xsl:when>
        <xsl:otherwise>
          <xsl:attribute name="value">0</xsl:attribute>
        </xsl:otherwise>
      </xsl:choose>
    </f:_minOccurs>
    <xsl:choose>
      <xsl:when test="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxOccurs']/f:valueCode or
                    (not(f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxOccurs']/f:valueInteger) and f:repeats/@value='true')">
        <f:_maxOccursCode value="*"/>
      </xsl:when>
      <xsl:otherwise>
        <f:_maxOccursInteger>
          <xsl:choose>
            <xsl:when test="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxOccurs']/f:valueInteger">
              <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/questionnaire-maxOccurs']/f:valueInteger">
                <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
              </xsl:for-each>
            </xsl:when>
            <xsl:otherwise>
              <xsl:attribute name="value">1</xsl:attribute>
            </xsl:otherwise>
          </xsl:choose>
        </f:_maxOccursInteger>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:header">
    <f:title>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
    </f:title>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:question">
    <xsl:param name="additionalPrefix"/>
    <xsl:copy>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*"/>
      <xsl:call-template name="upgradeCardinality"/>
      <xsl:if test="f:type[@value='choice' or @value='open-choice']">
        <xsl:choose>
          <xsl:when test="not(f:options/f:reference/@value)">
            <xsl:if test="not($suppressWarnings='true')">
              <xsl:message>
                <xsl:value-of select="concat('WARNING: A question was defined as requiring a coded answer, but no set of allowed values was declared so unable to expose code choices for question: ', f:linkId/@value, ' &quot;', f:text/@value, '&quot;')"/>
              </xsl:message>
            </xsl:if>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="valueset">
              <xsl:apply-templates mode="resolveReference" select="f:options/f:reference"/>
            </xsl:variable>
            <xsl:choose>
              <xsl:when test="$useMicrosoft='true'">
                <xsl:apply-templates mode="valueSetToCodings" select="msxsl:node-set($valueset)"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:apply-templates mode="valueSetToCodings" select="common:node-set($valueset)"/>
              </xsl:otherwise>
            </xsl:choose>
            <xsl:variable name="operationOutcome">
              <xsl:choose>
                <xsl:when test="$useMicrosoft='true'">
                  <xsl:value-of select="msxsl:node-set($valueset)/f:OperationOutcome/f:issue[1]/f:type/f:code/@value"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:value-of select="common:node-set($valueset)/f:OperationOutcome/f:issue[1]/f:type/f:code/@value"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:if test="$operationOutcome!=''">
              <operationOutcome value="{$operationOutcome}"/>
            </xsl:if>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:if>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="node()">
        <xsl:with-param name="additionalPrefix" select="$additionalPrefix"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:linkId">
    <xsl:param name="additionalPrefix"/>
    <xsl:variable name="functionSafeId">
      <xsl:call-template name="makeFunctionSafeId">
        <xsl:with-param name="value" select="@value"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:copy>
      <xsl:attribute name="value">
        <xsl:value-of select="concat($additionalPrefix, @value)"/>
      </xsl:attribute>
      <xsl:attribute name="safeValue">
        <xsl:value-of select="$functionSafeId"/>
      </xsl:attribute>
    </xsl:copy>
  </xsl:template>
  <xsl:template name="makeFunctionSafeId">
    <xsl:param name="value"/>
    <xsl:variable name="pass1">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$value"/>
        <xsl:with-param name="pattern" select="'.'"/>
        <xsl:with-param name="replacement" select="'_'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:variable name="pass2">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$pass1"/>
        <xsl:with-param name="pattern" select="'['"/>
        <xsl:with-param name="replacement" select="'_'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:variable name="pass3">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$pass2"/>
        <xsl:with-param name="pattern" select="']'"/>
        <xsl:with-param name="replacement" select="''"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:variable name="pass4">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$pass3"/>
        <xsl:with-param name="pattern" select="'/'"/>
        <xsl:with-param name="replacement" select="'_'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:value-of select="$pass4"/>
  </xsl:template>
  <!-- ====================================
     - = Questionnaire to HTML
     - ==================================== -->
  <xsl:template match="f:Questionnaire">
    <xsl:for-each select="descendant::f:modifierExtension[not(@url='http://hl7.org/fhir/StructureDefinition/styleSensitive')]">
      <xsl:message terminate="yes">
        <xsl:value-of select="concat('Error: Unrecognized modifier extension.  No rendering performed: ', @url)"/>
      </xsl:message>
    </xsl:for-each>
    <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html></xsl:text>
    <html>
      <head>
        <!-- Insertion points allow "extra" stuff to be introduced by post-processing through string substitution -->
        <xsl:comment>header insertion point</xsl:comment>
        <xsl:if test="$jQueryPath!=''">
          <script type="text/javascript">
            <xsl:comment>
              <xsl:text>&#x0a;</xsl:text>
              <xsl:text>var questionnaireAnswers = null;&#x0a;</xsl:text>
              <xsl:text>var questionnaireAnswersId = null;&#x0a;</xsl:text>
              <xsl:text>var questionnaireAnswersVersion = null;&#x0a;</xsl:text>
              <xsl:text>var questionnaireAnswersEndpoint = null;&#x0a;</xsl:text>
              <xsl:apply-templates mode="validateScript" select="//f:group|//f:question"/>
              <xsl:apply-templates mode="serializeScript" select="//f:group|//f:question"/>
              <xsl:text>function validateQuestionnaire() {&#x0a;</xsl:text>
              <xsl:value-of select="concat('  return validate', f:group/f:linkId/@safeValue, '(document.getElementById(&quot;div-cnt&quot;));&#x0a;')"/>
              <xsl:text>}&#x0a;&#x0a;</xsl:text>
              <xsl:text>function populateQuestionnaire() {&#x0a;</xsl:text>
              <xsl:text>  return null;&#x0a;</xsl:text>
              <xsl:text>}&#x0a;&#x0a;</xsl:text>
              <xsl:text>function serializeQuestionnaire() {&#x0a;</xsl:text>
              <xsl:value-of select="concat('  var rootGroup = serialize', f:group/f:linkId/@safeValue, '(document.getElementById(&quot;div-cnt&quot;));&#x0a;')"/>
              <xsl:text>  if (rootGroup.length == 0)&#x0a;</xsl:text>
              <xsl:text>    delete answers.group&#x0a;</xsl:text>
              <xsl:text>  else&#x0a;</xsl:text>
              <xsl:text>    answers.group = rootGroup;&#x0a;</xsl:text>
              <xsl:text>  return answers;&#x0a;</xsl:text>
              <xsl:text>}&#x0a;&#x0a;</xsl:text>
            </xsl:comment>
          </script>
        </xsl:if>
        <xsl:call-template name="scripts"/>
        <title>
          <xsl:choose>
            <xsl:when test="f:title/@value">
              <xsl:value-of select="f:title/@value"/>
            </xsl:when>
            <xsl:when test="$useMicrosoft='true'">
              <xsl:value-of select="msxsl:node-set($phrases)//phrase[@name='form']/@value"/>
              <xsl:if test="f:identifier/@value[1]">
                <xsl:value-of select="concat(': ', f:identifier/@value[1])"/>
              </xsl:if>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="common:node-set($phrases)//phrase[@name='form']/@value"/>
              <xsl:if test="f:identifier/@value[1]">
                <xsl:value-of select="concat(': ', f:identifier/@value[1])"/>
              </xsl:if>
            </xsl:otherwise>
          </xsl:choose>
        </title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
      </head>
      <body onload="loadAnswers()">
        <xsl:comment>body top insertion point</xsl:comment>
        <div id="div-cnt">
          <xsl:apply-templates select="f:group">
            <xsl:with-param name="path" select="'Path:'"/>
          </xsl:apply-templates>
        </div>
        <table>
          <tbody>
            <tr>
              <xsl:if test="not($saveOnly='true')">
                <td>
                  <button onclick="saveDraft()">Save as Draft</button>
                </td>
              </xsl:if>
              <td>
                <button onclick="saveFinal()">Save as Complete</button>
              </td>
            </tr>
          </tbody>
        </table>
        <xsl:comment>body bottom insertion point</xsl:comment>
      </body>
    </html>
  </xsl:template>
<!--  <xsl:template mode="script" match="f:group">
<xsl:message>Got here</xsl:message>
    <xsl:value-of select="concat('function validate', generate-id(), '(node) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
    <xsl:value-of select="concat('function populate', generate-id(), '(node, answers) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
    <xsl:value-of select="concat('function serialize', generate-id(), '(node) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
  </xsl:template>-->
  <xsl:template mode="enableWhen" match="f:questionnaire">
    <xsl:value-of select="concat('&#x0a;function change_', f:linkId/@safeValue, '() {&#x0a;')"/>
    <xsl:text>&#x0a;    }&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="serializeScript" match="f:group">
    <xsl:value-of select="concat('&#x0a;function serialize', f:linkId/@safeValue, '(node, groups) {&#x0a;')"/>
    <xsl:value-of select="concat('  var groupNodes = findDiv(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
    <xsl:text disable-output-escaping="yes">  for (var i=0; i&lt;groupNodes.length;i++) {&#x0a;</xsl:text>
    <xsl:value-of select="concat('    var group = {&#x0a;      &quot;linkId&quot;:&quot;', f:linkId/@value, '&quot;')"/>
    <xsl:for-each select="f:title/@value">
      <xsl:text>,&#x0a;      "title":"</xsl:text>
      <xsl:call-template name="escapeText">
        <xsl:with-param name="text" select="."/>
      </xsl:call-template>
      <xsl:text>"</xsl:text>
    </xsl:for-each>
    <xsl:for-each select="f:text/@value">
      <xsl:text>,&#x0a;      "text":"</xsl:text>
      <xsl:call-template name="escapeText">
        <xsl:with-param name="text" select="."/>
      </xsl:call-template>
      <xsl:text>"</xsl:text>
    </xsl:for-each>
    <xsl:text>&#x0a;    }&#x0a;</xsl:text>
    <xsl:choose>
      <xsl:when test="f:group">
        <xsl:text>    var childGroups = [];</xsl:text>
        <xsl:for-each select="f:group">
          <xsl:value-of select="concat('    serialize', f:linkId/@safeValue, '(groupNodes[i], childGroups);&#x0a;')"/>
        </xsl:for-each>
        <xsl:text>    if (childGroups.length != 0) {&#x0a;</xsl:text>
        <xsl:text>      group.group = childGroups;&#x0a;</xsl:text>
        <xsl:text>      groups.push(group);&#x0a;</xsl:text>
        <xsl:text>    }&#x0a;</xsl:text>
      </xsl:when>
      <xsl:when test="f:question">
        <xsl:text>    var childQuestions = [];</xsl:text>
        <xsl:for-each select="f:question">
          <xsl:value-of select="concat('    serialize', f:linkId/@safeValue, '(groupNodes[i], childQuestions);&#x0a;')"/>
        </xsl:for-each>
        <xsl:text>    if (childQuestions.length != 0){&#x0a;</xsl:text>
        <xsl:text>      group.question = childQuestions;&#x0a;</xsl:text>
        <xsl:text>      groups.push(group);&#x0a;</xsl:text>
        <xsl:text>    }&#x0a;</xsl:text>
      </xsl:when>
    </xsl:choose>
    <xsl:text>  }&#x0a;&#x0a;}&#x0a;&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="serializeScript" match="f:question">
    <xsl:value-of select="concat('&#x0a;function serialize', f:linkId/@safeValue, '(node, answers) {&#x0a;')"/>
    <xsl:value-of select="concat('  var question = {&#x0a;      &quot;linkId&quot;:&quot;', f:linkId/@value, '&quot;')"/>
    <xsl:for-each select="f:text/@value">
      <xsl:text>,&#x0a;      "text":"</xsl:text>
      <xsl:call-template name="escapeText">
        <xsl:with-param name="text" select="."/>
      </xsl:call-template>
      <xsl:text>"</xsl:text>
    </xsl:for-each>
    <xsl:text>&#x0a;    }&#x0a;</xsl:text>
    <xsl:value-of select="concat('  var answerNodes = findAnswers(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
    <xsl:text>  if (answerNodes.length != 0) {&#x0a;</xsl:text>
    <xsl:text disable-output-escaping="yes">    for (int i=0; i &lt; answerNodes.length; i++) {</xsl:text>
    <xsl:variable name="answerType">
      <xsl:choose>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
        <xsl:when test="f:type/@value='string'">answerString</xsl:when>
      </xsl:choose>
    </xsl:variable>
    <xsl:text>    }&#x0a;  }&#x0a;</xsl:text>
    <xsl:if test="f:group">
      <xsl:text>    var childGroups = [];</xsl:text>
      <xsl:for-each select="f:group">
        <xsl:value-of select="concat('    serialize', f:linkId/@safeValue, '(groupNodes[i], childGroups);&#x0a;')"/>
      </xsl:for-each>
      <xsl:text>    if (childGroups.length != 0) {&#x0a;</xsl:text>
      <xsl:text>      group.group = childGroups;&#x0a;</xsl:text>
      <xsl:text>      groups.push(group);&#x0a;</xsl:text>
      <xsl:text>    }&#x0a;</xsl:text>
    </xsl:if>
<!--    <xsl:choose>
      <xsl:when test="f:_minOccurs/@value!=0">
        <xsl:text disable-output-escaping="yes">  if (answerNodes == null || answerNodes.length &lt; </xsl:text>
        <xsl:value-of select="concat(f:_minOccurs/@value, ')&#x0a;')"/>
        <xsl:text>    return false&#x0a;  else {&#x0a;    </xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>  if (answerNodes != null) {&#x0a;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:for-each select="f:group">
      <xsl:if test="position()!=1">
        <xsl:text disable-output-escaping="yes">&#x0a;         &amp;&amp; </xsl:text>
      </xsl:if>
      <xsl:value-of select="concat('serialize', f:linkId/@safeValue, '(findDiv(node, &quot;', f:linkId/@value, '&quot;))')"/>
    </xsl:for-each>-->
    <xsl:text>&#x0a;}&#x0a;&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="validateScript" match="f:group">
    <xsl:value-of select="concat('&#x0a;function validate', f:linkId/@safeValue, '(node) {&#x0a;')"/>
    <xsl:value-of select="concat('  var groupNodes = findDiv(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
    <xsl:choose>
      <xsl:when test="f:_minOccurs/@value!=0">
        <xsl:text disable-output-escaping="yes">  if (groupNodes.length &lt; </xsl:text>
        <xsl:value-of select="concat(f:_minOccurs/@value, ') {&#x0a;')"/>
        <xsl:value-of select="concat('    setAddFocus(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
        <xsl:text>    node.focus()&#x0a;    alert('Must have at least </xsl:text>
        <xsl:value-of select="concat(f:_minOccurs/@value, ' occurrences of: ')"/>
        <xsl:choose>
          <xsl:when test="f:title/@value">
            <xsl:call-template name="escapeText">
              <xsl:with-param name="text" select="f:title/@value"/>
            </xsl:call-template>
          </xsl:when>
          <xsl:when test="f:text/@value">
            <xsl:call-template name="escapeText">
              <xsl:with-param name="text" select="f:text/@value"/>
            </xsl:call-template>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>group</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>');&#x0a;    return false&#x0a;  } else {&#x0a;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>  if (groupNodes != null) {&#x0a;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:text>    var valid = true;&#x0a;</xsl:text>
    <xsl:text disable-output-escaping="yes">    for (var i=0; i&lt;groupNodes.length &amp;&amp; valid; i++) {&#x0a;</xsl:text>
    <xsl:text>      var divNode = groupNodes[i];&#x0a;      valid =</xsl:text>
    <xsl:for-each select="f:group|f:question">
      <xsl:if test="position()!=1">
        <xsl:text disable-output-escaping="yes">&#x0a;         &amp;&amp;</xsl:text>
      </xsl:if>
      <xsl:value-of select="concat(' validate', f:linkId/@safeValue, '(divNode, &quot;', f:linkId/@value, '&quot;)')"/>
    </xsl:for-each>
    <xsl:text>;&#x0a;    }&#x0a;    return valid;&#x0a;  }&#x0a;}&#x0a;&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="validateScript" match="f:question">
    <xsl:value-of select="concat('&#x0a;function validate', f:linkId/@safeValue, '(node) {&#x0a;')"/>
    <xsl:choose>
      <xsl:when test="f:coding">
        <xsl:text>  var codings = [{</xsl:text>
        <xsl:for-each select="f:coding">
          <xsl:if test="position()!=1">
            <xsl:text>,&#x0a;                 {</xsl:text>
          </xsl:if>
          <xsl:text>"system": "</xsl:text>
          <xsl:value-of select="f:system/@value"/>
          <xsl:text>", "code": "</xsl:text>
          <xsl:value-of select="f:code/@value"/>
          <xsl:text>", "display": "</xsl:text>
          <xsl:value-of select="f:display/@value"/>
          <xsl:text>"}</xsl:text>
        </xsl:for-each>
        <xsl:value-of select="concat('];&#x0a;  var answerNodes = findAnswers(node, &quot;', f:linkId/@value, '&quot;,codings);&#x0a;')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="concat('  var answerNodes = findAnswers(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:choose>
      <xsl:when test="f:_minOccurs/@value!=0">
        <xsl:text disable-output-escaping="yes">  if (answerNodes == null || answerNodes.length &lt; </xsl:text>
        <xsl:value-of select="concat(f:_minOccurs/@value, ') {&#x0a;')"/>
<xsl:text>showError();&#x0a;</xsl:text>
        <xsl:text>    node.focus()&#x0a;    alert('Must have at least </xsl:text>
        <xsl:value-of select="concat(f:_minOccurs/@value, ' occurrences of ')"/>
        <xsl:choose>
          <xsl:when test="f:title/@value">
            <xsl:call-template name="escapeText">
              <xsl:with-param name="text" select="f:title/@value"/>
            </xsl:call-template>
          </xsl:when>
          <xsl:when test="f:text/@value">
            <xsl:call-template name="escapeText">
              <xsl:with-param name="text" select="f:text/@value"/>
            </xsl:call-template>
          </xsl:when>
          <xsl:otherwise>
            <xsl:text>group</xsl:text>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:text>');&#x0a;    return false&#x0a;  } else {&#x0a;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>  if (answerNodes != null) {&#x0a;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:choose>
      <xsl:when test="f:group">
        <xsl:value-of select="concat('    var groupNodes = findDiv(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
        <xsl:text>    return</xsl:text>
        <xsl:for-each select="f:group|f:question">
          <xsl:if test="position()!=1">
            <xsl:text disable-output-escaping="yes">&#x0a;         &amp;&amp;</xsl:text>
          </xsl:if>
          <xsl:value-of select="concat(' validate', f:linkId/@safeValue, '(node, &quot;', f:linkId/@value, '&quot;)')"/>
        </xsl:for-each>
        <xsl:text>;&#x0a;  }&#x0a;}&#x0a;&#x0a;</xsl:text>
      </xsl:when>
      <xsl:otherwise>
        <xsl:text>    return true;&#x0a;  }&#x0a;}&#x0a;</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="scripts">
    <xsl:if test="$jQueryPath!=''">
      <script type="text/javascript" src="{$jQueryPath}/json2.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/statuspage.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery-1.6.2.min.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery-ui-1.8.16.custom.min.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery-1.6.2.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery.ui.core.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery.ui.widget.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery.ui.mouse.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery.ui.resizable.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jquery.ui.draggable.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jtip.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/jcookie.js">&#xA0;</script>
      <script type="text/javascript" src="{$jQueryPath}/hl7connect.js">&#xA0;</script>
      <script type="text/javascript">
        <xsl:comment>
          <xsl:text>&#x0a;</xsl:text>
          <xsl:value-of select="concat('var expansionUri = &quot;', $expansionServer, '&quot;&#x0a;')"/>
          <xsl:choose>
            <xsl:when test="$iconPath!=''">
              <xsl:text disable-output-escaping="yes">var removeGroupButton = "&lt;input type=\"image\" alt=\"Remove\" onclick=\"deleteRow(this)\" style=\"width:10px;height:10px\" src=\"</xsl:text>
              <xsl:value-of select="concat($iconPath, '/html-form-delete.png\&quot;/&gt;&quot;;')"/>
              <xsl:text disable-output-escaping="yes">var removeInputButton = "&lt;input type=\"image\" alt=\"Remove\" onclick=\"deleteInput(this)\" style=\"width:10px;height:10px\" src=\"</xsl:text>
              <xsl:value-of select="concat($iconPath, '/html-form-delete.png\&quot;/&gt;&quot;;')"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:text disable-output-escaping="yes">var removeGroupButton = "&lt;button onclick=\"deleteRow(this)\"&gt;X&lt;/button&gt;";</xsl:text>
              <xsl:text disable-output-escaping="yes">var removeInputButton = "&lt;button onclick=\"deleteInput(this)\"&gt;X&lt;/button&gt;";</xsl:text>
            </xsl:otherwise>
          </xsl:choose>
          <xsl:text>&#x0a;</xsl:text>
        </xsl:comment>
      </script>
      <script type="text/javascript">
<xsl:comment>
  <xsl:text>&#x0a;var removeButton = '</xsl:text>
  <xsl:choose>
    <xsl:when test="$iconPath=''">
      <xsl:text disable-output-escaping="yes">&lt;button type="button" onclick="deleteRow(this)"&gt;X&lt;/button&gt;'&#x0a;</xsl:text>
    </xsl:when>
    <xsl:otherwise>
      <xsl:text disable-output-escaping="yes">&lt;input type="image" onclick="deleteRow(this)" src="</xsl:text>
      <xsl:value-of select="$iconPath"/>
      <xsl:text disable-output-escaping="yes">/html-form-delete.png" alt="Remove" style="width:10px;height:10px;"/&gt;'&#x0a;</xsl:text>
    </xsl:otherwise>
  </xsl:choose>
function findDiv(node, linkId) {
  return $(node).children("div").filter(function() {
    return $(this).children("span:first-child").text() == linkId
        &amp;&amp; this.style.display != "none";
  });
}

function setAddFocus(node, linkId) {
  $(node).children("div").filter(function() {
    return $(this).children("span:first-child").text() == linkId
  }).last().next(":input").focus();
}

function answerCell(node, linkId) {
  var answerRows = $(node).children("table").children("tbody").children("tr").filter(function() {
    return $(this).children("td:first-child").children("span:first-child").text() === linkId;
  });
  if (answerRows.length == 0) {
    alert("Unable to find question row: " + linkId);
    return null;
  }
  if (answerRows.length != 1) {
    alert("Multiple question rows for: " + linkId);
    return null;
  }
  
  return inputCell = answerRows[0].cells[1];
}

function answerType(inputCell) {
  if ($(inputCell).children("input:radio").length > 0)
    return "radio"
    
  else if ($(inputCell).children("input:checkbox").length > 0)
    return "checkbox"
    
  else if ($(inputCell).children("select").length > 0) {
    var options = $(inputCell).children("select").children("option");
    if (options.count!=3
     || $(options[0]).val() != ''
     || $(options[1]).val() != 'true'
     || $(options[2]).val() != 'false'
     || $(options[0]).text() != ''
     || $(options[1]).text() != 'Yes'
     || $(options[2]).text() != 'No')
      return "select"
    else
      return "boolean"
  
  } else if ($(inputCell).children("table").length > 0)
    if ($(inputCell).children("table").children("colgroup").children("col:last-child",[span='2']).length > 0)
      return "coding"
 
    else if ($(inputCell).children("table").children("colgroup").children("col").length == 3)
      return "reference"
    
    else if ($(inputCell).children("input:text").length > 0)
      return "repeating-text"
  
    else if ($(inputCell).children("textarea").length > 0)
      return "repeating-textarea"
    
    else alert("Unknown repeating type")
  
  else if ($(inputCell).children("input:text").length > 0)
    return "text"

  else if ($(inputCell).children("textarea").length > 0)
    return "textarea"
    
  else if ($(inputCell).children("*:input").length == 0)
    return "none"
    
  else alert("Unknown type");
}

function findAnswers(node, linkId, lookupArray, isNumber) {
  var inputCell = answerCell(node, linkId);

  if (inputCell == null) return null;
  
  var inputType = answerType(inputCell);
  
  var answerCount = 0;
  var answers = [];
  
  switch (inputType) {
    case "radio":
    case "checkbox":
      $(inputCell).children(":checked").each(function() {
        var value = $(this).val();
        if (lookupArray) {
          var coding = $.grep(lookupArray, function(aCoding){ return aCoding.code == value; });
          if (coding)
            answers.push(coding);
          
        } else
          answers.push(value);
      });
      break;
      
    case "boolean":
      if ($(inputCell).children("input:checkbox", ":checked").length!=0)
        answers.push('true')
      else
        answers.push('false');
      break;
    
    case "select":
      $(inputCell).children("select").children("option:selected").each(function() {
        var value = $(this).val();
        if (value != "") {
          if (lookupArray) {
            var coding = $.grep(lookupArray, function(aCoding){ return aCoding.display == value; });
            if (coding)
              answers.push(coding);
            
          } else
            answers.push(value);
        }
      });
      break;

    case "coding":
      $(inputCell).children("table").children("tbody").children("tr").each(function() {
        var coding = {
          "system": this.cells[2].textContent,
          "code": this.cells[3].textContent,
          "display": this.cells[1].textContent
        }
        answers.push(coding);
      });
      break;
      
    case "reference":
      $(inputCell).children("table").children("tbody").children("tr").each(function() {
        var reference = {
          "reference": this.cells[2].textContent,
          "display": this.cells[1].textContent
        }
        answers.push(reference);
      });
      break;
      
    case "repeating-text":
    case "repeating-textarea":
      $(inputCell).children("table").children("tbody").children("tr").each(function() {
        var value = $(this).children("td:last-child").children("input:visible").val();
        if (value != "") {
          if (isNumber)
            answers.push(value.toNumber())
          else
            answers.push(value);
        }
      });
      break;
      
    case "text":
    case "textarea":
      $(inputCell).children("input:visible").each(function() {
        var value = $(this).val();
        if (value != "") {
          if (isNumber)
            answers.push(value.toNumber())
          else
            answers.push(value);
        }
      });
      break;
      
    case "none":
  }
  
  return answers;
}

function loadAnswers() {
  if (questionnaireAnswers == null) {
    questionnaireAnswers =
    {
      "resourceType": "QuestionnaireAnswers",
      "text": {
        "status": "generated",
        "div": null
      },
      // "questionnaire": TODO,
      "status": "draft",
      // "subject": TODO,
      // "author": TODO,
      "authored": "2001-01-01T00:00:00",
      // "encounter": TODO,
      "group": null
    }
  } else {
    populateQuestionnaire();
  }
}

function currentTime() {
  var now = new Date();
  return now.getYear + "-" + padTime(now.getMonth) + "-" + padTime(now.getDay) + "T" + padTime(now.getHours) + ':' + padTime(now.getMinutes) + ':' + padTime(now.getSeconds);
}

function padTime(num) {
  if (num &lt; 10)
    return "0" + num
  else
    return num;
}

function saveDraft() {
  serializeQuestionnaire();
  questionnaireAnswers.status = "draft"
  questionnaireAnswers.authored = currentTime();
  saveQuestionnaire(questionnaireAnswers);
}

function saveFinal() {
  if (validateQuestionnaire()) {
    serializeQuestionnaire();
    if (questionnaireAnswers.status == "completed")
      questionnaireAnswers.status = "amended"
     else
      questionnaireAnswers.status = "completed";
    questionnaireAnswers.authored = currentTime();
    saveQuestionnaire();
  }
}

function saveQuestionnaire() {
  // Todo: save stuff
}

function showError() {
  alert('Error');
}
</xsl:comment>
      </script>
      <script type="text/javascript">
        <xsl:comment>
var fhirValuesetUrl = null;
var fhirCodeMax = null;
var fhirCodeExtensible = null;
var fhirCodeSourceTable = null;

var selectedSystem = "";
var selectedCode = "";
var selectedDisplay = "";
var selectedDefinition = "";

var selectionTable;
var codes;
var addButton;
var doneButton;
var codeSelection;


function combo(id,h,l) 
{
  var self = this; 
  self.h = h; 
  self.l = l; 
  self.inp = document.getElementById(id); 
  self.hasfocus = false; 
  self.sel = -1; 
  self.ul = self.inp.nextSibling; 
  while (self.ul.nodeType == 3) 
    self.ul = self.ul.nextSibling; 
  self.ul.onmouseover = function() {
    self.ul.className = '';
  }; 
  self.ul.onmouseout = function() {
    self.ul.className = 'focused'; 
    if (!self.hasfocus) 
      self.ul.style.display = 'none';
  }; 
  self.list = self.ul.getElementsByTagName('li'); 
  selectedSystem = ""
  selectedCode = ""
  selectedDisplay = ""
  selectedDefinition = ""
  for (var i=self.list.length - 1; i >= 0; i=i-1) {
    self.list[i].onclick = function() {
      if (this.id != null) {
        self.inp.value = this.firstChild.data;  
        for (var i = 0; i &lt; codes.length; i++) {
          if (codes[i].system+"||"+codes[i].code == this.id) {
            selectedSystem = codes[i].system;
            selectedCode = codes[i].code;
            selectedDisplay = codes[i].display;
            currentCodeDefinition = ""
            if (codes[i].extension != null) {
              for (var j = 0; j &lt; codes[i].extension.length; j++) {
                if (codes[i].extension[j].url == "http:\/\/hl7.org\/fhir\/StructureDefinition\/valueset-definition")
                  selectedDefinition = codes[i].extension[j].valueString;
              }
            }
            checkMaxCodeRows()
          }
        }        
        self.rset(self);
      }
    }
  } 
  self.inp.onfocus = function() {
    self.ul.style.display = 'block'; 
    self.ul.className = 'focused'; 
    self.hasfocus = true; 
    self.sel = -1;
  }; 
  self.inp.onblur = function() {
    if (self.ul.className=='focused') {
      self.rset(self);
    } 
    self.ul.className = ''; 
    self.hasfocus = false;
  }; 
  self.inp.onkeyup = function(e) {
    var k = (e)? e.keyCode : event.keyCode; 
    if (k == 40 || k == 13) {
      if (self.sel == self.list.length-1) {
        self.list[self.sel].style.backgroundColor = self.l; 
        self.sel = -1;
      } 
      if (self.sel > -1)
        self.list[self.sel].style.backgroundColor = self.l; 
      self.inp.value = self.list[++self.sel].firstChild.data; 
      self.list[self.sel].style.backgroundColor = self.h;
    } 
    else if (k == 38 &amp;&amp; self.sel > 0) {
      self.list[self.sel].style.backgroundColor = self.l;
      self.sel = self.sel -1
      self.inp.value = self.list[self.sel].firstChild.data; 
      self.list[self.sel].style.backgroundColor = self.h;
    }
    return false;
  };
} 
  
combo.prototype.rset = function(self) {
  self.ul.style.display = 'none'; 
  self.sel = -1; 
  for (var i=self.list.length - 1; i >= 0; i=i-1) {
    self.list[i].style.backgroundColor = self.l;
  }  
  return false;
};

function codeBuildForm()
{
  var d = document.getElementById("fhir-code-form");
  if (d == null)
  {
    d = document.createElement("div");
    d.id = "fhir-code-form";
  }

  d.innerHTML = "&lt;span id=\"fhir-code-label\">Code:&lt;/span>&lt;table>&lt;tbody>&lt;tr>&lt;td class=\"combo\">&lt;input id=\"fhir-code-input\" type=\"text\" name=\"codeChoice\" title=\"Type text to filter list\" size=\"100%\" onInput=\"reload(this)\"/>"+
                "&lt;ul id=\"fhir-code-ul\" style=\"display:none;\" class=\"\">&lt;/ul>&lt;/td>&lt;td>&lt;button id=\"fhir-code-select\" onClick='addRow()' style=\"display:none;\">Add&lt;/button>&lt;/td>&lt;/tr>&lt;tr>&lt;td>"+
                "&lt;button id=\"fhir-code-commit\" onClick='commitCodes()' style=\"display:none;\">Apply&lt;/button>&lt;/td>&lt;td>"+
                "&lt;button onClick='closeCodeSelect()'>Cancel&lt;/button>&lt;/td>&lt;/tr>&lt;/tbody>&lt;/table>&lt;div id=\"fhir-code-selections\"/>";
  d.className = "fhir-code-form";
	document.getElementById("div-cnt").appendChild(d);
  $('.fhir-code-form').css('left', fhirCodeSourceTable.offsetLeft+fhirCodeSourceTable.offsetWidth);
  $('.fhir-code-form').css('top', fhirCodeSourceTable.offsetTop+fhirCodeSourceTable.offsetHeight);
}

function initCodeLookup(valueset, sourceTable, extensible, max) {
  fhirValuesetUrl = valueset;
  fhirCodeMax = max;
  fhirCodeExtensible = extensible;
  fhirCodeSourceTable = sourceTable

  codeBuildForm();

  var inputLabel = document.getElementById("fhir-code-label");
  var targetDiv = document.getElementById("fhir-code-selections");
  codeSelection = document.getElementById("fhir-code-input");
  addButton = document.getElementById("fhir-code-select");
  doneButton = document.getElementById("fhir-code-commit");

  if (max==1) {
    targetDiv.style.display="none";
    addButton.style.display = "none";
    if (extensible)
      inputLabel.innerHTML="Select code/Enter value:"
    else
      inputLabel.innerHTML="Select code:";

  } else {
    targetDiv.style.display="inherit";
    addButton.style.display = "inherit";
    if (extensible)
      inputLabel.innerHTML="Select code(s)/Enter value(s):"
    else
      inputLabel.innerHTML="Select code(s):";
  }
  if (targetDiv.table)
    targetDiv.removeChild(targetDiv.table);

  selectionTable = sourceTable.cloneNode(true);
  targetDiv.appendChild(selectionTable);

  for (var i = 0; i &lt; selectionTable.rows.length; i++) {
    setRowId(selectionTable.rows[i])
  }

  checkMaxCodeRows()

  load();
}

function reload(edit) {
  getCodes(fhirValuesetUrl, edit.value);
  checkMaxCodeRows()
}

function load() {
  new combo('fhir-code-input','#9c9','#fff');
  getCodes(fhirValuesetUrl, "");
}

function processExpansion(data)
{
  var source = "";
//  codes = data.entry[0].content.expansion.contains;
  codes = data.expansion.contains;
  if (codes != null) {
    for (var i = 0; i &lt; codes.length; i++) {
      var definition = ""
      if (codes[i].extension != null) {
        for (var j = 0; j &lt; codes[i].extension.length; j++) {
          if (codes[i].extension[j].url == "http:\/\/hl7.org\/fhir\/StructureDefinition\/valueset-definition")
            definition = codes[i].extension[j].valueString;
        }
      }
      source = source + "&lt;li id=\""+codes[i].system+"||"+codes[i].code+"\" title=\""+definition+"\">"+codes[i].display+"&lt;/li>";
    }
  }
  document.getElementById("fhir-code-ul").innerHTML = source;  
  new combo('fhir-code-input','#9c9','#fff'); 
}

function processNarrative(narr) {
  var s = narr.indexOf("&gt;");
  var e = narr.lastIndexOf("&lt;");
  return narr.substring(s+1, e-1);
}

function getCodes(id, text)
{
    try
    {
      var uri = expansionUri+"/ValueSet/$expand?_format=json&amp;identifier="+id+"&amp;filter="+text;
      $.ajax({
        url: uri,
        cache: false,
        dataType: "json",
        success: function(data){
          processExpansion(data);
        },
        error: function(jqXHR, textStatus, errorThrown){
          try {
            var oo = jQuery.parseJSON(jqXHR.responseText);
            document.getElementById("fhir-code-ul").innerHTML = "&lt;li>"+processNarrative(oo.text.div)+"&lt;/li>";  
          } catch (err) {
            alert("System Error: "+textStatus+" (:"+err+")");
          }
        }
      });
    }
    catch (err)
    {
      alert("System Error: "+err);
    }
}

function deleteRow(control) {
  var row = control.parentNode.parentNode;
  if (confirm("Remove this entry?")) {
    row.parentNode.deleteRow(row.rowIndex);
    checkMaxCodeRows()
  }
}

function setRowId(row) {
  row.id = getRowId(row.cells[2].textContent, row.cells[3].textContent, row.cells[1].textContent);
}

function getRowId(system, code, display) {
  return system.trim()+"||"+code.trim()+"||"+display.trim().toUpperCase();
}

function addRow() {
  var selectionText = codeSelection.value;
  var addCode = (selectionText == selectedDisplay);
  if (addCode)
    var newRowId = getRowId(selectedSystem, selectedCode, selectedDisplay)
  else if (fhirCodeExtensible)
    var newRowId = getRowId("", "", selectionText)
  else
    return; // No code selected an not extensible binding
    
  var currentRow = document.getElementById(newRowId);

  if (currentRow != null) {
    alert("Already exists");
    return;
  }

  currentRow = selectionTable.insertRow(-1);
  currentRow.insertCell(0).innerHTML = removeGroupButton;
  // Todo: make the URL a variable
  var aCell = currentRow.insertCell(1);
  aCell.innerHTML = addCode ? selectedDisplay : selectionText;
  aCell.title = addCode ? selectedDefinition : "";
  if (addCode) {
    aCell = currentRow.insertCell(2);
    aCell.innerHTML = selectedSystem;
    aCell.style.fontSize="0px";
    aCell = currentRow.insertCell(3);
    aCell.innerHTML = selectedCode;
    aCell.style.fontSize="0px";
  } else {
    currentRow.insertCell(2).style.fontSize = "0px"
    currentRow.insertCell(3).style.fontSize = "0px"
  }
  setRowId(currentRow);
  checkMaxCodeRows();
}

function checkMaxCodeRows() {
  if ((fhirCodeMax == null || selectionTable.rows.length &lt; fhirCodeMax)
  &amp;&amp; fhirCodeMax != 1
  &amp;&amp; codeSelection.value != "" 
  &amp;&amp; (codeSelection.value == selectedDisplay || fhirCodeExtensible))
    addButton.style.display = "inherit"
  else
    addButton.style.display = "none";

  if (fhirCodeMax == 1) {
    if (codeSelection.value != ""
    &amp;&amp; (codeSelection.value == selectedDisplay || fhirCodeExtensible))
      doneButton.style.display = "inherit"
    else
      doneButton.style.display = "none";
  } else
      doneButton.style.display = "inherit";
}

function commitCodes() {
  if (fhirCodeMax == 1) {
    for (var i=selectionTable.rows.length - 1; i >= 0; i=i-1) {
      selectionTable.deleteRow(i);
    }
    addRow();
  }

  var dataTable = selectionTable.cloneNode(true);
  for (var i = 0; i &lt; dataTable.rows.length; i++) {
    dataTable.rows[i].removeAttribute("id");
  }
  var parent = fhirCodeSourceTable.parentNode;
  parent.removeChild(fhirCodeSourceTable);
  parent.insertBefore(dataTable, parent.children[0]);
  closeCodeSelect();
}

function closeCodeSelect() {
	document.getElementById("div-cnt").removeChild(document.getElementById("fhir-code-form"));
}
        </xsl:comment>
      </script>
      <script type="text/javascript">
        <xsl:comment>
var fhirReferenceMax = null;
var fhirReferenceSourceTable = null;
var fhirReferenceCount = 0;

var referenceTable;
var doneButton1;
var doneButton2;

function referenceBuildForm()
{
  var d = document.getElementById("fhir-reference-form");
  if (d == null)
  {
    d = document.createElement("div");
    d.id = "fhir-reference-form";
  }

  d.innerHTML = "&lt;div id=\"fhir-reference-div\" style=\"overflow:hidden\">&lt;span id=\"fhir-reference-label\">Resources:&lt;/span>&lt;div style=\"overflow:auto\">" +
    "&lt;table id=\"fhir-reference-table\" style=\"border-style:solid;border-width:thin;\">&lt;col span=\"2\"/>" +
    "&lt;col style=\"width:0px;visibility:collapse;\"/>&lt;tbody> &lt;/tbody>&lt;/table>&lt;/div>&lt;table>&lt;tbody>" +
    "&lt;tr>&lt;td>&lt;button onClick='cancelReference()'>Cancel&lt;/button>&lt;/td>&lt;td>" +
    "&lt;button id=\"fhir-reference-commit\" onClick='commitReferences()'>Apply&lt;/button>&lt;/td>&lt;/tr>&lt;/tbody>&lt;/table>&lt;/div>";
  d.className = "fhir-code-form";
	document.getElementById("div-cnt").appendChild(d);
  $('.fhir-code-form').css('left', fhirReferenceSourceTable.offsetLeft+fhirReferenceSourceTable.offsetWidth);
  $('.fhir-code-form').css('top', fhirReferenceSourceTable.offsetTop+fhirReferenceSourceTable.offsetHeight);
}

function initReferenceLookup(query, label, sourceTable, max) {
  fhirReferenceQuery = query;
  fhirReferenceMax = max;
  fhirReferenceSourceTable = sourceTable

  referenceBuildForm();

  var inputLabel = document.getElementById("fhir-reference-label");
  referenceTable = document.getElementById("fhir-reference-table");
  doneButton = document.getElementById("fhir-reference-commit");

  if (max==1) {
    inputLabel.innerHTML=label + " (select one):"
    doneButton.style.display = "none";
    referenceControl = "&lt;button title=\"Choose this resource\" onclick=\"chooseReference(this.parentNode.parentNode)\">Select&lt;/button>"
  } else {
    inputLabel.innerHTML=label + " (select one or more): "
    doneButton.style.display = "inherit";
    referenceControl = "&lt;input type=\"checkbox\" title=\"Include this resource\" onchange=\"toggleReference(this)\"/>";
  }

  getReferences(query, referenceControl);
}

function toggleReference(control) {
  var row = control.parentNode.parentNode;
  if (control.checked) {
    if (fhirReferenceCount >= fhirReferenceMax) {
      control.checked = false;
      alert("Only " + fhirReferenceMax + " entries may be selected");
    } else {
      fhirReferenceCount++;
    }
  } else {
    fhirReferenceCount = fhirReferenceCount - 1;
  }
}

function displaySummaries(data, referenceControl)
{
  var source = "";
  var entries = data.entry;

  for (var i=referenceTable.rows.length - 1; i >= 0; i=i-1) {
    referenceTable.deleteRow(i);
  }

  var div = document.createElement("t");
  if (entries != null) {
    for (var i = 0; i &lt; entries.length; i++) {
      row = referenceTable.insertRow(-1)
      var aCell = row.insertCell(0);
      aCell.innerHTML = referenceControl
      if (fhirReferenceMax != 1) {
        $.grep(fhirReferenceSourceTable.rows, function(aRow){
          if (aRow.cells[2].textContent == entries[i].id) {
            $(aCell).children("input:checkbox")[0].checked = true;
            fhirReferenceCount++;
          }
        });
      }
      aCell = row.insertCell(1);
      div.innerHTML = entries[i].summary;
      aCell.innerHTML = $(div).text();
      aCell = row.insertCell(2);
      aCell.innerHTML = entries[i].id;
      aCell.style.fontSize="0px";
    }
  }
  // Do I need to remove the created element somehow?
}

function getReferences(query, referenceControl)
{
    try
    {
      var uri = expansionUri+query;
      $.ajax({
        url: uri,
        cache: false,
        dataType: "json",
        success: function(data){
          displaySummaries(data, referenceControl);
        },
        error: function(jqXHR, textStatus, errorThrown){
          try {
            var oo = jQuery.parseJSON(jqXHR.responseText);
            referenceTable.insertRow(-1).insertCell(0).innerHTML = processNarrative(oo.text.div);  
          } catch (err) {
            alert("System Error: "+textStatus);
          }
        }
      });
    }
    catch (err)
    {
      alert("System Error: "+err);
    }
}

function clearSourceTable() {
  for (var i=fhirReferenceSourceTable.rows.length - 1; i >= 0; i=i-1) {
    fhirReferenceSourceTable.deleteRow(i);
  }  
}

function cancelReference() {
  closeReferenceSelect();
}

function chooseReference(row) {
  clearSourceTable()
  var sourceRow = fhirReferenceSourceTable.insertRow(0);
  var aCell = sourceRow.insertCell(0);
  aCell.innerHTML = removeInputButton;
  var aCell = sourceRow.insertCell(1);
  aCell.innerHTML = row.cells[1].innerHTML;
  var aCell = sourceRow.insertCell(2);
  aCell.innerHTML = row.cells[2].innerHTML;
  aCell.style.fontSize="0px";

  closeReferenceSelect();
}

function commitReferences() {
  clearSourceTable()

  var rowNum = 0;
  for (var i = 0; i &lt; referenceTable.rows.length; i++) {
    if ($(referenceTable.rows[i].cells[0]).children("input:checkbox")[0].checked) {
      var sourceRow = fhirReferenceSourceTable.insertRow(rowNum);
      var aCell = sourceRow.insertCell(0);
      aCell.innerHTML = removeInputButton;
      var aCell = sourceRow.insertCell(1);
      aCell.innerHTML = referenceTable.rows[i].cells[1].innerHTML;
      var aCell = sourceRow.insertCell(2);
      aCell.innerHTML = referenceTable.rows[i].cells[2].innerHTML;
      aCell.style.fontSize="0px";
      rowNum++;
    }
  }

  closeReferenceSelect();
}

function closeReferenceSelect() {
	document.getElementById("div-cnt").removeChild(document.getElementById("fhir-reference-form"));
}
        </xsl:comment>
      </script>
      <style type="">
      div.fhir-code-form {
       background-color: Lemonchiffon; 
       border-bottom: 1px solid #778899;
       border-right: 1px solid #778899;
       padding: 10px 10px 0px 10px;
       margin: 0px 0px 0px 0px;
       position: fixed;
       width: 520px;
       top: 0;
       left: 0;
       opacity: 1.0;
       z-order: 50;
      }
      div.fhir-code-form,div.fhir-reference-form {
       background-color: Lemonchiffon; 
       border-bottom: 1px solid #778899;
       border-right: 1px solid #778899;
       padding: 10px 10px 0px 10px;
       margin: 0px 0px 0px 0px;
       position: fixed;
       width: 520px;
       top: 0;
       left: 0;
       opacity: 1.0;
       z-order: 50;
      }
      .combo {position:relative;width:310px;text-align:left;padding:0;margin:0;}
      .combo * {padding:0;margin:0;}
      .combo label {display:block;float:left;width:92px;text-align:right;}
      .combo input {width:300px; height:1.5em;z-index:50;}
      .combo ul {padding:1px;border:2px solid #ccc;width: 600px;background-color:#fff;position:absolute;left:3px;top:1.8em;display:none;z-index:51;}
      .combo li {display:block; width: 100%;}
      </style>
    </xsl:if>
    <script type="text/javascript">
      <xsl:comment>
      //
      // Scripts for managing groups
      //
      function addGroup(groupId, button){
        group = document.getElementById(groupId)
        if (group.style.display=="none") {
          group.style.display="block"
        } else {
          newGroup = group.cloneNode(true)
          button.parentNode.insertBefore(newGroup, button)
          resetGroup(newGroup)
          //newGroup.getElementsByTagName("button")[2].style.display="inline"
        }
      }
      function deleteGroup(button){
        group = button.parentNode;
        if (group.id!="undefined") {
          group.style.display="none"
        } else {
          group.parentNode.removeChild(group)
        }
      }
      function addInput(button){
        var baseInput = button.nextElementSibling
        var rootPath = baseInput.nextElementSibling
        var occurrence = rootPath.nextElementSibling
        var max = occurrence.nextElementSibling
        var table = button.previousElementSibling
        // Todo: Check maximum (need to do this on deleteInput too)
        
        occurrence.innerHTML = parseInt(occurrence.innerHTML) + 1;
        var newInput = baseInput.cloneNode();
        newInput.setAttribute('name', rootPath.innerHTML + "[" + occurrence.innerHTML + "]");
        newInput.style.display = "inline";
        var row = table.insertRow(table.rows.length);
        row.insertCell(0).innerHTML = removeInputButton;
        row.insertCell(1).appendChild(newInput);
      }
      function deleteInput(button){
        var row = button.parentNode.parentNode;
        if (confirm("Remove this entry?")) {
          row.parentNode.deleteRow(row.rowIndex);
        }
      }
      function resetGroup(button){
        resetDiv(button.parentNode)
      }
      function resetDiv(div) {
        inputs = div.getElementsByTagName("input")
        for(i=0; i &lt; inputs.length; i++) {
          if (inputs[i].default == "undefined") {
            inputs[i].value = ""
          } else {
            inputs[i].value = inputs[i].default
          }
        }
        textAreas = div.getElementsByTagName("textArea")
        for(i=0; i &lt; textAreas.length; i++) {
          textAreas[i].value = ""
        }
        divs = div.getElementsByTagName("div")
        for(i=0; i &lt; divs.length; i++) {
          resetDiv(divs[i])
        }
      }
      function checkDecimal(input) {
        if (input.value != '') {
          format = new RegExp("^([+-]?([1-9]\d*)|0)(\.\d+)?$")
          if (!format.test(input.value)) {
            input.focus()
            alert("Invalid decimal value")
          }
        }
      }
      function checkInteger(input) {
        if (input.value != '') {
          format = new RegExp("^[+-]?([1-9]\d*)|0$")
          if (!format.test(input.value)) {
            input.focus()
            alert("Invalid integer value")
          }
        }
      }
      function checkDate(input) {
        if (input.value != '') {
          checkDateValue(input.value, "^([1-9][0-9]{3}|0[0-9]{3})(-(0[1-9]|1[0-2])(-(0[1-9]|[12][0-9]|3[01]))?)?$", "Invalid date value - must be yyyy-mm-dd")
        }
      }
      function checkDateTime(input) {
        if (input.value != '') {
          checkDateValue(input.value, "^([1-9][0-9]{3}|0[0-9]{3})([-/](0[1-9]|1[0-2])([-/](0[1-9]|[12][0-9]|3[01])(\s+(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?)(\s+(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?$", "Invalid date-time value - must be yyyy-mm-dd hh:mm:ss.sss +/-zz:zz")
        }
      }
      function checkTime(input) {
        if (input.value != '') {
          checkDateValue(input.value, "^([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)$", "Invalid time value - must be hh:mm:ss.sss")
        }
      }
      function checkInstant(input) {
        if (input.value != '') {
          checkDateValue(input.value, "^([1-9][0-9]{3}|0[0-9]{3})[/-](0[1-9]|1[0-2])[-/](0[1-9]|[12][0-9]|3[01])\s+(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?)\s+(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)$", "Invalid date-time value - must be yyyy-mm-dd hh:mm:ss.sss +/-zz:zz")
        }
      }
      function checkDateValue(input, formatStr, message) {
        format = new RegExp(formatStr)
        if (input.value=='') {
          // do nothing

        } else if (!format.test(input.value)) {
          input.focus()
          alert(message)
          
        } else if (input.value.length > 7) {
          datePortion = input.value.substring(1,8)
          year = datePortion.split(/[/-]/)[0]
          month = datePortion.split(/[/-]/)[1]
          day = datePortion.split(/[/-]/)[2]
          date = new Date(year, month-1, day)
          if ((date.getMonth()+1!=month) || (date.getDate()!=day) || (date.getFullYear()!=year)) {
            input.focus()
            alert("Invalid date.")
          }
        }
      }
      </xsl:comment>
    </script>
  </xsl:template>
  <xsl:template match="f:group">
    <xsl:param name="path"/>
    <xsl:param name="level" select="1"/>
    <xsl:param name="hierarchy"/>
    <xsl:if test="not(f:_hidden/@value='true')">
      <xsl:variable name="leadingText">
        <xsl:variable name="prefix">
          <xsl:choose>
            <xsl:when test="f:_label">
              <!-- This empty message makes the software run in Saxon.  Which is terribly scary -->
              <xsl:message/>
              <xsl:value-of select="f:_label"/>
            </xsl:when>
            <xsl:when test="$numberSections='true'">
              <xsl:number level="multiple"/>
            </xsl:when>
          </xsl:choose>
        </xsl:variable>
        <xsl:if test="normalize-space($prefix)!='' or f:title/@value">
          <xsl:element name="h{$level}" namespace="{$htmlNamespace}">
            <xsl:for-each select="_tooltip/@value">
              <xsl:attribute name="title">
                <xsl:value-of select="."/>
              </xsl:attribute>
            </xsl:for-each>
            <xsl:value-of select="$prefix"/>
            <xsl:apply-templates mode="styledText" select="f:title"/>
            <xsl:for-each select="f:_question">
              <xsl:variable name="helptext">
                <xsl:call-template name="escapeText">
                  <xsl:with-param name="text" select="@value"/>
                </xsl:call-template>
              </xsl:variable>
              <button style="margin:0em 0em;" type="button" onclick="alert('{$helptext}')"><b>(?)</b></button>
            </xsl:for-each>
          </xsl:element>
        </xsl:if>
        <xsl:for-each select="f:text">
          <p>
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>
        <xsl:for-each select="f:_instruction">
          <p style="{$instructionStyle}">
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>
        <xsl:for-each select="f:_security">
          <p style="{$securityStyle}">
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>
      </xsl:variable>
      <xsl:copy-of select="$leadingText"/>
      <xsl:if test="normalize-space($leadingText)=''">
        <br/>
      </xsl:if>
    </xsl:if>
    <xsl:variable name="repetitions">
      <xsl:choose>
        <xsl:when test="f:_minOccurs[@value&gt;1]">
          <xsl:value-of select="f:_minOccurs/@value"/>
        </xsl:when>
        <xsl:when test="f:_maxOccursCode/@value='*' or f:_maxOccursInteger/@value &gt; $defaultGroupRepetitions">
          <xsl:value-of select="$defaultGroupRepetitions"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="f:_maxOccursInteger/@value"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:call-template name="groupDiv">
      <xsl:with-param name="path" select="$path"/>
      <xsl:with-param name="level" select="$level"/>
      <xsl:with-param name="hierarchy" select="$hierarchy"/>
      <xsl:with-param name="totalRepetitions" select="$repetitions"/>
    </xsl:call-template>
    <xsl:if test="$repetitions!=1 and not(f:_hidden/@value='true')">
      <xsl:choose>
        <xsl:when test="$iconPath=''">
          <button style="margin:0em 2em;" type="button" onclick="addGroup('{generate-id()}{$hierarchy}', this)">Add section</button>
        </xsl:when>
        <xsl:otherwise>
          <input style="margin:0em 2em;width:10px;height:10px;" type="image" onclick="addGroup('{generate-id()}{$hierarchy}', this)" src="{$iconPath}/html-form-add.png" alt="Add section"/>
        </xsl:otherwise>
      </xsl:choose>
      <span style="display:none">
        <xsl:value-of select="f:linkId/@value"/>
<!--        <xsl:value-of select="concat($path, f:linkId/@value)"/>-->
      </span>
      <span style="display:none">
        <xsl:value-of select="$repetitions"/>
      </span>
    </xsl:if>
    <xsl:for-each select="f:_trailing">
      <p>
        <xsl:apply-templates mode="styledText" select="."/>
      </p>
    </xsl:for-each>
  </xsl:template>
  <xsl:template name="groupDiv">
    <xsl:param name="path"/>
    <xsl:param name="level"/>
    <xsl:param name="hierarchy"/>
    <xsl:param name="totalRepetitions"/>
    <xsl:param name="currentRepetition" select="1"/>
    <xsl:variable name="style">
      <xsl:choose>
        <xsl:when test="f:_hidden/@value='true'">display:none</xsl:when>
        <xsl:otherwise>
          <xsl:text>display:block;margin:0.5em 2em;border-style:solid;border-width:1px;border-color:#A0A0A0;</xsl:text>
          <xsl:if test="$totalRepetitions!=1">
            <xsl:text>border-style:solid;border-width:1px;border-color:#A0A0A0;</xsl:text>
          </xsl:if>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="newPath" select="concat($path, f:linkId/@value, '[', $currentRepetition, ']')"/>
    <div style="{$style}">
      <xsl:if test="$currentRepetition=1">
        <xsl:attribute name="id">
          <xsl:value-of select="concat(generate-id(), $hierarchy)"/>
        </xsl:attribute>
      </xsl:if>
      <span style="display:none">
        <xsl:value-of select="f:linkId/@value"/>
<!--        <xsl:value-of select="$newPath"/>-->
      </span>
      <xsl:if test="$enableReset='true'">
        <button type="button" onclick="resetGroup(this)">Reset</button>
      </xsl:if>
<!--      <xsl:if test="$totalRepetitions &gt; 1">-->
        <xsl:choose>
          <xsl:when test="$iconPath=''">
            <button type="button" onclick="deleteGroup(this)">X</button>
          </xsl:when>
          <xsl:otherwise>
            <input type="image" onclick="deleteGroup(this)" src="{$iconPath}/html-form-delete.png" alt="Remove" style="width:10px;height:10px;"/>
          </xsl:otherwise>
        </xsl:choose>
<!--      </xsl:if>-->
      <xsl:apply-templates select="f:group|f:question">
        <xsl:with-param name="path" select="$newPath"/>
        <xsl:with-param name="level" select="$level + 1"/>
        <xsl:with-param name="hierarchy" select="concat($hierarchy, '.', $currentRepetition)"/>
      </xsl:apply-templates>
    </div>
    <xsl:if test="not(f:_hidden/@value='true')">
      <xsl:choose>
        <xsl:when test="$iconPath=''">
          <button type="button" onclick="addGroup('{generate-id()}{$hierarchy}', this)">Add</button>
        </xsl:when>
        <xsl:otherwise>
          <input style="margin:0em 2em;width:10px;height:10px;" type="image" onclick="addGroup('{generate-id()}{$hierarchy}', this)" src="{$iconPath}/html-form-add.png" alt="Add section"/>
        </xsl:otherwise>
      </xsl:choose>
      <span style="display:none">
        <xsl:value-of select="f:linkId/@value"/>
      </span>
      <span style="display:none">
        <xsl:value-of select="$currentRepetition"/>
      </span>
      <xsl:if test="$currentRepetition &lt; $totalRepetitions">
        <xsl:call-template name="groupDiv">
          <xsl:with-param name="path" select="$path"/>
          <xsl:with-param name="level" select="$level"/>
          <xsl:with-param name="hierarchy" select="$hierarchy"/>
          <xsl:with-param name="totalRepetitions" select="$totalRepetitions"/>
          <xsl:with-param name="currentRepetition" select="$currentRepetition + 1"/>
        </xsl:call-template>
      </xsl:if>
    </xsl:if>
  </xsl:template>
  <xsl:template name="questionRow">
    <xsl:param name="path"/>
    <xsl:variable name="id" select="generate-id()"/>
    <xsl:variable name="formattedLabel">
      <xsl:variable name="label">
        <xsl:choose>
          <xsl:when test="f:label">
            <xsl:value-of select="f:label"/>
          </xsl:when>
          <xsl:when test="$numberSections='true'">
            <xsl:number level="multiple"/>
          </xsl:when>
        </xsl:choose>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="f:required/@value='true'">
          <b>
            <xsl:value-of select="concat($label, ' ')"/>
            <xsl:apply-templates mode="styledText" select="f:text"/>
          </b>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat($label, ' ')"/>
          <xsl:apply-templates mode="styledText" select="f:text"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="answerType" select="f:type/@value"/>
    <xsl:variable name="unit">
      <xsl:for-each select="f:_units/@value">
        <xsl:value-of select="concat('&#xA0;', .)"/>
      </xsl:for-each>
    </xsl:variable>
    <xsl:variable name="answerControl">
      <xsl:choose>
        <xsl:when test="$answerType='decimal'">
          <input type="text" onblur="checkDecimal(this)">
            <xsl:call-template name="checkTextInput"/>
          </input>
          <xsl:copy-of select="$unit"/>
        </xsl:when>
        <xsl:when test="$answerType='integer'">
          <input type="text" onblur="checkInteger(this)">
            <xsl:call-template name="checkTextInput"/>
          </input>
          <xsl:copy-of select="$unit"/>
        </xsl:when>
        <xsl:when test="$answerType='boolean'">
          <select name="{$path}">
            <xsl:call-template name="checkTextInput"/>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </xsl:when>
        <xsl:when test="$answerType='date'">
          <input type="text" onblur="checkDate(this)" maxlength="10">
            <xsl:call-template name="checkTextInput"/>
          </input>
        </xsl:when>
        <xsl:when test="$answerType='dateTime'">
          <input type="text" onblur="checkDateTime(this)" maxlength="26">
            <xsl:call-template name="checkTextInput"/>
          </input>
        </xsl:when>
        <xsl:when test="$answerType='time'">
          <input type="text" onblur="time(this)" maxlength="26">
            <xsl:call-template name="checkTextInput"/>
          </input>
        </xsl:when>
        <xsl:when test="$answerType='instant'">
          <input type="text" onblur="checkInstant(this)" maxlength="30">
            <xsl:call-template name="checkTextInput"/>
          </input>
        </xsl:when>
        <xsl:when test="$answerType='text'">
          <textarea rows="4" cols="50">
            <xsl:call-template name="checkTextInput"/>
            <xsl:text>&#xA0;</xsl:text>
          </textarea>
        </xsl:when>
        <xsl:when test="$answerType='choice' or $answerType='open-choice'">
          <xsl:choose>
            <xsl:when test="not(f:options/f:reference/@value)">
              <xsl:if test="not($suppressWarnings='true')">
                <xsl:message>
                  <xsl:value-of select="concat('WARNING: A question was defined as requiring a coded answer, but no set of allowed values was declared so unable to expose code choices for question: ', f:linkId/@value, ' &quot;', f:text/@value, '&quot;')"/>
                </xsl:message>
              </xsl:if>
            </xsl:when>
            <xsl:otherwise>
              <xsl:variable name="valueset">
                <xsl:apply-templates mode="resolveReference" select="f:options/f:reference"/>
              </xsl:variable>
              <xsl:variable name="valuesetCodings" select="f:coding"/>
              <xsl:variable name="codingCount">
                <xsl:choose>
                  <xsl:when test="$useMicrosoft='true'">
                    <xsl:value-of select="count(msxsl:node-set($valuesetCodings))"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="count(common:node-set($valuesetCodings))"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:variable name="operationOutcome" select="f:operationOutcome/@value"/>
              <xsl:choose>
                <xsl:when test="$codingCount=0 and $operationOutcome!='too-costly'">                
                  <xsl:if test="not($suppressWarnings='true')">
                    <xsl:message>
                      <xsl:value-of select="concat('WARNING: Unable to resolve value set reference ', f:options/f:reference/@value, ' so unable to expose code choices for question: ', f:text/@value)"/>
                    </xsl:message>
                  </xsl:if>
                </xsl:when>
                <xsl:when test="($codingCount&gt;$maxListboxCodings or $operationOutcome='too-costly') and $expansionServer!='' and $jQueryPath!=''">
                  <!-- Lookup -->
                  <table style="table-layout:fixed;width=100%;">
                    <col style="width:20px;"/>
                    <col style="width:auto;"/>
                    <col span="2" style="width:0px;visibility:collapse;"/>
                  </table>
                  <xsl:variable name="encodedValuesetURL">
                    <xsl:call-template name="encodeURL">
                      <xsl:with-param name="url" select="f:options/f:reference/@value"/>
                    </xsl:call-template>
                  </xsl:variable>
                  <xsl:variable name="extensible">
                    <xsl:choose>
                      <xsl:when test="$answerType='choice'">false</xsl:when>
                      <xsl:otherwise>true</xsl:otherwise>
                    </xsl:choose>
                  </xsl:variable>
                  <xsl:variable name="max">
                    <xsl:choose>
                      <xsl:when test="f:_maxOccursInteger/@value">
                        <xsl:value-of select="f:_maxOccursInteger/@value"/>
                      </xsl:when>
                      <xsl:when test="f:_maxOccursCode">
                        <xsl:value-of select="$defaultAnswerRepetitions"/>
                      </xsl:when>
                      <xsl:otherwise>1</xsl:otherwise>
                    </xsl:choose>
                  </xsl:variable>
                  <xsl:variable name="checkInput">
                    <xsl:if test="//f:_enableWhen[f:_question/@value=current()/f:linkId/@value]">
<xsl:message>Got here1</xsl:message>
                      <xsl:text>true</xsl:text>
                    </xsl:if>
                  </xsl:variable>
                  <xsl:call-template name="checkTextInput"/>
                  <button onclick="javascript:initCodeLookup('{$encodedValuesetURL}', this.previousElementSibling, {$extensible}, {$max}, {$checkInput})">Edit</button>
                </xsl:when>
                <xsl:when test="$codingCount&gt;$maxListboxCodings">
                  <!-- Can't Lookup -->
                  <xsl:value-of select="concat('WARNING: Question has value set with more than ', $maxListboxCodings, ' options.  No proper interface could be provided.&#x0a;', f:text/@value)"/>
                </xsl:when>
                <xsl:when test="$codingCount&gt;$maxCheckboxCodings">
                  <!-- List box -->
                  <select name="{$path}">
                    <xsl:call-template name="checkTextInput"/>
                    <xsl:if test="f:_maxOccursInteger/@value!=1 or f:_maxOccursCode/@value">
                      <xsl:attribute name="multiple">multiple</xsl:attribute>
                    </xsl:if>
                    <xsl:choose>
                      <xsl:when test="$useMicrosoft='true'">
                        <xsl:for-each select="msxsl:node-set($valuesetCodings)">
                          <xsl:call-template name="doListBoxItem"/>
                        </xsl:for-each>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:for-each select="common:node-set($valuesetCodings)">
                          <xsl:call-template name="doListBoxItem"/>
                        </xsl:for-each>
                      </xsl:otherwise>
                    </xsl:choose>
                  </select>
                  <xsl:copy-of select="$unit"/>
                </xsl:when>
                <xsl:when test="f:_maxOccursInteger/@value=1">
                  <!-- Radio buttons -->
                  <xsl:choose>
                    <xsl:when test="$useMicrosoft='true'">
                      <xsl:for-each select="msxsl:node-set($valuesetCodings)">
                        <xsl:call-template name="doRadioBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                          <xsl:with-param name="unit" select="$unit"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:for-each select="common:node-set($valuesetCodings)">
                        <xsl:call-template name="doRadioBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                          <xsl:with-param name="unit" select="$unit"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:otherwise>
                  </xsl:choose>
                  <xsl:if test="$answerType='open-choice'">
                    <xsl:text>Other:</xsl:text>
                    <input type="text"/>
                  </xsl:if>
                </xsl:when>
                <xsl:otherwise>
                  <!-- Check boxes -->
                  <xsl:choose>
                    <xsl:when test="$useMicrosoft='true'">
                      <xsl:for-each select="msxsl:node-set($valuesetCodings)">
                        <xsl:call-template name="doCheckBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                          <xsl:with-param name="unit" select="$unit"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:for-each select="common:node-set($valuesetCodings)">
                        <xsl:call-template name="doCheckBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                          <xsl:with-param name="unit" select="$unit"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:otherwise>
                  </xsl:choose>
                  <xsl:if test="$answerType='open-choice'">
                    <xsl:text>Other:</xsl:text>
                    <input type="text"/>
                    <xsl:copy-of select="$unit"/>
                  </xsl:if>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:when test="$answerType='reference'">
          <xsl:choose>
            <xsl:when test="f:extension[@url='http://www.healthintersections.com.au/fhir/StructureDefinition/metadata-reference']/f:valueString/@value">
              <table style="table-layout:fixed;width=100%">
                <col style="width:20px"/>
                <col style="width:auto"/>
                <col style="width:0px;visibility:collapse;"/>
                <tbody/>
              </table>
              <xsl:variable name="max">
                <xsl:choose>
                  <xsl:when test="f:_maxOccursInteger/@value">
                    <xsl:value-of select="f:_maxOccursInteger/@value"/>
                  </xsl:when>
                  <xsl:when test="f:_maxOccursCode">
                    <xsl:value-of select="$defaultAnswerRepetitions"/>
                  </xsl:when>
                  <xsl:otherwise>1</xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:variable name="checkInput">
                <xsl:if test="//f:_enableWhen[f:_question/@value=current()/f:linkId/@value]">
<xsl:message>Got here2</xsl:message>
                  <xsl:text>true</xsl:text>
                </xsl:if>
              </xsl:variable>
              <button onclick="javascript:initReferenceLookup('{f:extension[@url='http://www.healthintersections.com.au/fhir/StructureDefinition/metadata-reference']/f:valueString/@value}', '{f:text/@value}', this.previousElementSibling, {$max}, {$checkInput})">Edit</button>
              <input name="Path:{f:linkId/@value}" tabindex="text"/><!-- remove? -->
            </xsl:when>
            <xsl:otherwise>
              <xsl:if test="not($suppressWarnings='true')">
                <xsl:message>WARNING: No look-up extension provided for resource reference - treating as string</xsl:message>
              </xsl:if>
              <input tabindex="text"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:otherwise>
          <xsl:choose>
            <xsl:when test="normalize-space($answerType)=''">
              <xsl:if test="not($suppressWarnings='true')">
                <xsl:message>
                  <xsl:value-of select="concat('WARNING: Answer format was not declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>
                </xsl:message>
              </xsl:if>
            </xsl:when>
            <xsl:when test="not($answerType='string')">
              <xsl:if test="not($suppressWarnings='true')">
                <xsl:message>
                  <xsl:value-of select="concat('WARNING: Unrecognized answer format ', $answerType, ' was declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>
                </xsl:message>
              </xsl:if>
            </xsl:when>
          </xsl:choose>
          <input type="text"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="adjustedInput">
      <xsl:choose>
        <xsl:when test="$useMicrosoft='true'">
          <xsl:apply-templates mode="adjustInput" select="msxsl:node-set($answerControl)"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:apply-templates mode="adjustInput" select="common:node-set($answerControl)"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <tr>
      <xsl:if test="f:_hidden/@value='true'">
        <xsl:attribute name="style">display:none</xsl:attribute>
      </xsl:if>
      <td style="vertical-align:top;">
        <xsl:for-each select="_tooltip/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
        <xsl:for-each select="f:linkId/@value">
          <span style="display:none;">
            <xsl:value-of select="."/>
          </span>
        </xsl:for-each>
        <xsl:copy-of select="$formattedLabel"/>
        <xsl:for-each select="f:_question">
          <xsl:variable name="helptext">
            <xsl:call-template name="escapeText">
              <xsl:with-param name="text" select="@value"/>
            </xsl:call-template>
          </xsl:variable>
          <button style="margin:0em 0em;" type="button" onclick="alert('{$helptext}')"><b>(?)</b></button>
        </xsl:for-each>
        <xsl:for-each select="f:_instruction">
          <p style="{$instructionStyle}">
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>
        <xsl:for-each select="f:_security">
          <p style="{$securityStyle}">
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>
      </td>
      <td>
        <xsl:variable name="newPath" select="concat($path, f:linkId/@value)"/>
        <xsl:choose>
          <xsl:when test="not(contains($answerType, 'choice')) and (f:_maxOccursInteger/@value!=1 or f:_maxOccursCode/@value)">
            <xsl:variable name="repetitions">
              <xsl:choose>
                <xsl:when test="f:_maxOccursInteger/@value">
                  <xsl:value-of select="f:_maxOccursInteger/@value"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:value-of select="$defaultAnswerRepetitions"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <table>
              <tbody>
                <xsl:call-template name="repeatAnswer">
                  <xsl:with-param name="path" select="$newPath"/>
                  <xsl:with-param name="totalRepetitions" select="$repetitions"/>
                  <xsl:with-param name="input" select="$adjustedInput"/>
                </xsl:call-template>
              </tbody>
            </table>
            <xsl:choose>
              <xsl:when test="$iconPath=''">
                <button style="margin:0em 2em;" type="button" onclick="addInput(this)">Add Response</button>
              </xsl:when>
              <xsl:otherwise>
                <input style="margin:0em 2em;width:10px;height:10px;" type="image" onclick="addInput(this)" src="{$iconPath}/html-form-add.png" alt="Add response"/>
              </xsl:otherwise>
            </xsl:choose>
            <xsl:choose>
              <xsl:when test="$useMicrosoft='true'">
                <xsl:for-each select="msxsl:node-set($adjustedInput)/*">
                  <xsl:copy>
                    <xsl:copy-of select="@*"/>
                    <xsl:attribute name="style">
                      <xsl:value-of select="concat('display:none;', @style)"/>
                    </xsl:attribute>
                    <xsl:copy-of select="node()"/>
                  </xsl:copy>
                </xsl:for-each>
              </xsl:when>
              <xsl:otherwise>
                <xsl:for-each select="common:node-set($adjustedInput)/*">
                  <xsl:copy>
                    <xsl:copy-of select="@*"/>
                    <xsl:attribute name="style">
                      <xsl:value-of select="concat('display:none;', @style)"/>
                    </xsl:attribute>
                    <xsl:copy-of select="node()"/>
                  </xsl:copy>
                </xsl:for-each>
              </xsl:otherwise>
            </xsl:choose>
            <span style="display:none">
              <xsl:value-of select="$newPath"/>
            </span>
            <span style="display:none">
              <xsl:value-of select="$repetitions"/>
            </span>
            <span style="display:none">
              <xsl:choose>
                <xsl:when test="f:_maxOccursInteger/@value">
                  <xsl:value-of select="f:_maxOccursInteger/@value"/>
                </xsl:when>
                <xsl:otherwise>*</xsl:otherwise>
              </xsl:choose>
            </span>
          </xsl:when>
          <xsl:when test="$useMicrosoft='true'">
            <xsl:apply-templates mode="copyInput" select="msxsl:node-set($adjustedInput)">
              <xsl:with-param name="path" select="$newPath"/>
              <xsl:with-param name="position" select="1"/>
            </xsl:apply-templates>
          </xsl:when>
          <xsl:otherwise>
            <xsl:apply-templates mode="copyInput" select="common:node-set($adjustedInput)">
              <xsl:with-param name="path" select="$newPath"/>
              <xsl:with-param name="position" select="1"/>
            </xsl:apply-templates>
          </xsl:otherwise>
        </xsl:choose>
        <xsl:for-each select="f:_trailing">
          <p>
            <xsl:apply-templates mode="styledText" select="."/>
          </p>
        </xsl:for-each>        
      </td>
    </tr>
  </xsl:template>
  <xsl:template name="doListBoxItem">
    <option value="{f:code/@value}">
      <xsl:for-each select="f:_style/@value">
        <xsl:attribute name="style">
          <xsl:value-of select="."/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/valueset-definition']/f:valueString/@value">
        <xsl:attribute name="title">
          <xsl:value-of select="."/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:choose>
        <xsl:when test="f:_markup/@value">
          <xsl:value-of select="f:_markup/@value" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="f:display/@value">
          <xsl:value-of select="concat(' ', f:display/@value)"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(' ', f:code/@value)"/>
        </xsl:otherwise>
      </xsl:choose>
    </option>
  </xsl:template>
  <xsl:template name="doRadioBoxItem">
    <xsl:param name="path"/>
    <xsl:param name="unit"/>
<!--    <xsl:if test="f:system">-->
      <input type="radio" name="{$path}" value="{f:code/@value}">
        <xsl:call-template name="checkTextAnswerInput"/>
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/valueset-definition']/f:valueString/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
      </input>
      <xsl:copy-of select="$unit"/>
<!--    </xsl:if>-->
    <span>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/style']/f:valueString/@value">
        <xsl:attribute name="style">
          <xsl:value-of select="."/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:choose>
        <xsl:when test="f:extension[@url='http://hl7.org/fhir/StructureDefinition/markup']/f:valueString/@value">
          <xsl:value-of select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/markup']/f:valueString/@value" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="f:display/@value">
          <xsl:value-of select="concat(' ', f:display/@value)"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(' ', f:code/@value)"/>
        </xsl:otherwise>
      </xsl:choose>
    </span>
    <br/>
  </xsl:template>
  <xsl:template name="doCheckBoxItem">
    <xsl:param name="path"/>
    <xsl:param name="unit"/>
    <xsl:if test="f:system">
      <input type="checkbox" name="{$path}" value="{f:code/@value}">
        <xsl:call-template name="checkTextAnswerInput"/>
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/StructureDefinition/valueset-definition']/f:valueString/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
      </input>
      <xsl:copy-of select="$unit"/>
    </xsl:if>
    <span>
      <xsl:for-each select="f:_style/@value">
        <xsl:attribute name="style">
          <xsl:value-of select="."/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:choose>
        <xsl:when test="f:_markup/@value">
          <xsl:value-of select="f:_markup/@value" disable-output-escaping="yes"/>
        </xsl:when>
        <xsl:when test="f:display/@value">
          <xsl:value-of select="concat(' ', f:display/@value)"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(' ', f:code/@value)"/>
        </xsl:otherwise>
      </xsl:choose>
    </span>
    <br/>
  </xsl:template>
  <xsl:template name="checkTextInput">
    <xsl:if test="//f:_enableWhen[f:question/@value=current()/f:linkId/@value]">
      <xsl:attribute name="onChange">
        <xsl:value-of select="concat('change_', f:linkId/@safeValue)"/>
      </xsl:attribute>
    </xsl:if>
  </xsl:template>
  <xsl:template name="checkTextAnswerInput">
    <xsl:if test="//f:_enableWhen[f:question/@value=current()/f:linkId/@value]">
      <xsl:attribute name="onChange">
        <xsl:value-of select="concat('change_', f:linkId/@safeValue)"/>
      </xsl:attribute>
    </xsl:if>
  </xsl:template>
  <xsl:template match="f:question">
    <xsl:param name="path"/>
    <xsl:param name="level"/>
    <xsl:param name="hierarchy"/>
    <xsl:choose>
      <xsl:when test="preceding-sibling::*[1][self::f:question]">
        <!-- We're already inside a table, so the table will contain this question -->
      </xsl:when>
      <xsl:when test="following-sibling::*[1][self::f:question]">
        <table>
          <tbody>
            <xsl:call-template name="questionRow">
              <xsl:with-param name="path" select="$path"/>
            </xsl:call-template>
            <xsl:variable name="precedingGroups" select="count(preceding-sibling::f:group)"/>
            <xsl:for-each select="following-sibling::f:question[count(preceding-sibling::f:group)=$precedingGroups]">
              <xsl:call-template name="questionRow">
                <xsl:with-param name="path" select="$path"/>
              </xsl:call-template>
            </xsl:for-each>
          </tbody>
        </table>
      </xsl:when>
      <xsl:otherwise>
        <table>
          <tbody>
            <xsl:call-template name="questionRow">
              <xsl:with-param name="path" select="$path"/>
            </xsl:call-template>
          </tbody>
        </table>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:apply-templates select="f:group">
      <xsl:with-param name="path" select="concat($path, f:linkId/@value, '[1]')"/>
      <xsl:with-param name="level" select="$level + 1"/>
      <xsl:with-param name="hierarchy" select="$hierarchy"/>
    </xsl:apply-templates>
  </xsl:template>
  <xsl:template name="repeatAnswer">
    <xsl:param name="path"/>
    <xsl:param name="repetition" select="1"/>
    <xsl:param name="totalRepetitions" select="1"/>
    <xsl:param name="input"/>
    <tr>
      <td>
        <xsl:choose>
          <xsl:when test="$iconPath=''">
            <button type="button" onclick="deleteInput(this)">X</button>
          </xsl:when>
          <xsl:otherwise>
            <input type="image" onclick="deleteInput(this)" src="{$iconPath}/html-form-delete.png" alt="Remove" style="width:10px;height:10px;"/>
          </xsl:otherwise>
        </xsl:choose>
      </td>
      <td>
        <xsl:choose>
          <xsl:when test="$useMicrosoft='true'">
            <xsl:apply-templates mode="copyInput" select="msxsl:node-set($input)">
              <xsl:with-param name="path" select="$path"/>
              <xsl:with-param name="position" select="$repetition"/>
            </xsl:apply-templates>
          </xsl:when>
          <xsl:otherwise>
            <xsl:apply-templates mode="copyInput" select="common:node-set($input)">
              <xsl:with-param name="path" select="$path"/>
              <xsl:with-param name="position" select="$repetition"/>
            </xsl:apply-templates>
          </xsl:otherwise>
        </xsl:choose>
      </td>
    </tr>
    <xsl:if test="$repetition &lt; $totalRepetitions">
      <xsl:call-template name="repeatAnswer">
        <xsl:with-param name="path" select="$path"/>
        <xsl:with-param name="repetition" select="$repetition + 1"/>
        <xsl:with-param name="totalRepetitions" select="$totalRepetitions"/>
        <xsl:with-param name="input" select="$input"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
  <xsl:template mode="copyInput" match="@*|node()">
    <xsl:param name="path"/>
    <xsl:param name="position"/>
    <xsl:copy>
      <xsl:apply-templates mode="copyInput" select="@*|node()">
        <xsl:with-param name="path" select="$path"/>
        <xsl:with-param name="position" select="$position"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="copyInput" match="html:input|html:textArea">
    <xsl:param name="path"/>
    <xsl:param name="position"/>
    <xsl:copy>
      <xsl:attribute name="name">
        <xsl:value-of select="concat($path, '[', $position, ']')"/>
      </xsl:attribute>
      <xsl:apply-templates mode="copyInput" select="@*|node()">
        <xsl:with-param name="path" select="$path"/>
        <xsl:with-param name="position" select="$position"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="resolveReference" match="f:reference">
    <xsl:choose>
      <xsl:when test="starts-with(@value, '#')">
        <xsl:copy-of select="ancestor::f:*/f:contained/f:*[f:id/@value=substring-after(current()/@value, '#')]"/>
      </xsl:when>
      <xsl:when test="ancestor::f:bundle/f:entry/f:resource/*/f:id=current()/@value">
        <xsl:copy-of select="ancestor::f:bundle/f:entry/f:resource/*[f:id=current()/@value]"/>
      </xsl:when>
      <xsl:when test="$expansionServer!=''">
        <xsl:variable name="encodedValuesetURL">
          <xsl:call-template name="encodeURL">
            <xsl:with-param name="url" select="@value"/>
          </xsl:call-template>
        </xsl:variable>
        <xsl:variable name="url">
          <xsl:choose>
            <xsl:when test="$useMicrosoft='true'">
              <xsl:value-of select="concat($expansionServer, '/ValueSet/$expand?_format=xml&amp;_nohttperr=1&amp;identifier=', $encodedValuesetURL)"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="concat($expansionServer, '/ValueSet/$expand?_format=xml&amp;identifier=', $encodedValuesetURL)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:copy-of select="document($url, /)"/>
      </xsl:when>
      <xsl:otherwise>
        
<!-- Need to suppress this until HL7 starts returning proper resources
        <xsl:copy-of select="document(concat(@value, '?_format=application/xml+fhir'), /)"/>
-->
        <!-- Todo: Is there a way to set HTTP headers in a retrieval? -->
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template name="encodeURL">
    <xsl:param name="url"/>
    <xsl:variable name="pass1">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$url"/>
        <xsl:with-param name="pattern" select="':'"/>
        <xsl:with-param name="replacement" select="'%3A'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:variable name="pass2">
      <xsl:call-template name="replace">
        <xsl:with-param name="input" select="$pass1"/>
        <xsl:with-param name="pattern" select="'/'"/>
        <xsl:with-param name="replacement" select="'%2F'"/>
      </xsl:call-template>
    </xsl:variable>
    <xsl:value-of select="$pass2"/>
  </xsl:template>
  <xsl:template name="escapeText">
    <xsl:param name="text"/>
    <xsl:call-template name="replace">
      <xsl:with-param name="input" select="$text"/>
      <xsl:with-param name="pattern">'</xsl:with-param>
      <xsl:with-param name="replacement">\'</xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template name="replace">
    <xsl:param name="input"/>
    <xsl:param name="pattern"/>
    <xsl:param name="replacement"/>
    <xsl:choose>
      <xsl:when test="contains($input, $pattern)">
        <xsl:variable name="remainder">
          <xsl:call-template name="replace">
            <xsl:with-param name="input" select="substring-after($input, $pattern)"/>
            <xsl:with-param name="pattern" select="$pattern"/>
            <xsl:with-param name="replacement" select="$replacement"/>
          </xsl:call-template>
        </xsl:variable>
        <xsl:value-of select="concat(substring-before($input, $pattern), $replacement, $remainder)"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$input"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template mode="valueSetToCodings" match="f:ValueSet">
    <xsl:choose>
      <xsl:when test="f:expansion">
        <xsl:for-each select="f:expansion//f:contains">
          <xsl:if test="not(f:abstract/@value='true')">
            <f:coding>
              <xsl:copy-of select="f:system|f:version|f:code|f:display|f:extension"/>
            </f:coding>
          </xsl:if>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="not(f:define or f:compose) or f:compose[f:import or f:exclude or f:include[descendant::f:filter]]">
        <xsl:if test="not($suppressWarnings='true')">
          <xsl:message>
            <xsl:value-of select="concat('WARNING: Value set ', f:name/@value, ' is too complex to be expanded by this transform - no codes displayed for question.')"/>
          </xsl:message>
        </xsl:if>
      </xsl:when>
      <xsl:otherwise>
        <xsl:for-each select="f:define//f:concept">
          <xsl:if test="not(f:abstract/@value='true')">
            <f:coding>
              <xsl:copy-of select="f:extension"/>
              <xsl:copy-of select="parent::f:define/f:system|parent::f:define/f:version"/>
              <xsl:copy-of select="f:code|f:display|f:definition"/>
            </f:coding>
          </xsl:if>
        </xsl:for-each>
        <xsl:for-each select="f:compose/f:include/f:concept">
          <f:coding>
            <xsl:copy-of select="f:extension"/>
            <xsl:copy-of select="parent::f:include/f:system|parent::f:include/f:version"/>
            <xsl:copy-of select="f:code|f:display"/>
          </f:coding>
        </xsl:for-each>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template mode="adjustInput" match="node()">
    <!-- Default template just copies everything.  Extending transforms may override this to change input controls -->
    <xsl:copy-of select="."/>
  </xsl:template>
  <xsl:template mode="styledText" match="*">
    <xsl:choose>
      <xsl:when test="f:_style and f:_markup">
        <span style="{f:_style/@value}">
          <xsl:value-of select="f:_markup/@value" disable-output-escaping="yes"/>
        </span>
      </xsl:when>
      <xsl:when test="f:_markup">
        <xsl:value-of select="f:_markup/@value" disable-output-escaping="yes"/>
      </xsl:when>
      <xsl:when test="f:_style">
        <span style="{f:_style/@value}">
          <xsl:value-of select="@value"/>
        </span>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="@value"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
</xsl:stylesheet>

