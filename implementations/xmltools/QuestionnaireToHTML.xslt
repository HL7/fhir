<?xml version="1.0" encoding="UTF-8"?>
<!--
  - (c) 2014 Lloyd McKenzie & Associates Consulting Ltd.  All rights reserved
  -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:f="http://hl7.org/fhir" xmlns:atom="http://www.w3.org/2005/Atom" exclude-result-prefixes="f atom">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
  <xsl:param name="numberSections" select="'false'">
    <!-- If set to true, will auto-generate labels for sections if labels are not present -->
  </xsl:param>
  <xsl:param name="defaultGroupRepetitions" select="2">
    <!-- This is the number of times repeating groups will be included by default if they don't have a declared minimum number of repetitions -->
  </xsl:param>
  <xsl:param name="maxCodings" select="20">
    <!-- This is the maximum number of check boxes or radio buttons that will be permitted for coded elements -->
  </xsl:param>
  <xsl:param name="enableReset" select="'false'">
    <!-- If set to 'true', this will display a reset button on sections to remove data -->
  </xsl:param>
  <xsl:variable name="htmlNamespace" select="'http://www.w3.org/1999/xhtml'"/>
  <xsl:variable name="phrases">
    <!-- These are words that will be embedded within the generated HTML.  Captured as a variable to allow overriding in downstream transforms -->
    <phrase xmlns="" name="form" value="Form"/>
  </xsl:variable>
  <xsl:template match="/">
    <xsl:if test="not(f:Questionnaire)">
      <xsl:message terminate="yes">ERROR: This transform only works on FHIR Questionnaires.  Terminating.</xsl:message>
    </xsl:if>
    <xsl:choose>
      <xsl:when test="f:Questionnaire/f:name or descendant::*[self::f:group or self::f:question]/f:name">
        <xsl:variable name="newQuestionnaire">
          <xsl:apply-templates mode="upgradeQuestionnaire" select="."/>
        </xsl:variable>
        <xsl:apply-templates select="$newQuestionnaire/f:Questionnaire"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:apply-templates select="f:Questionnaire"/>
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
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[starts-with(@url, 'http://hl7.org/fhir/questionnaire-extensions#additionalAnswer')]"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#answerFormat']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#label']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#mayRepeat']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minCardinality']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinality']"/>
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
    <xsl:copy>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*"/>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#label']/f:valueString">
        <f:label>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:label>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minCardinaility']/f:valueInteger">
        <f:minOccurs>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:minOccurs>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinaility']/f:valueInteger">
        <f:maxOccursInteger>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:maxOccursInteger>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinaility']/f:valueCode">
        <f:maxOccursCode>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:maxOccursCode>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#mayRepeat'][f:valueBoolean/@value='true']">
        <f:maxOccursCode value="*"/>
      </xsl:for-each>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:header">
    <f:title>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
    </f:title>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:question">
    <xsl:copy>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*"/>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#answerFormat']/f:valueCode">
        <f:type>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:type>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#label']/f:valueString">
        <f:label>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:label>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minCardinaility']/f:valueInteger">
        <f:minOccurs>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:minOccurs>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinaility']/f:valueInteger">
        <f:maxOccursInteger>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:maxOccursInteger>
      </xsl:for-each>
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinaility']/f:valueCode">
        <f:maxOccursCode>
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </f:maxOccursCode>
      </xsl:for-each>
      <xsl:apply-templates mode="upgradeQuestionnaire" select="node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:question/*[starts-with(local-name(.), 'answer')]">
    <f:answer>
      <xsl:element name="value{substring-after(local-name(.), 'answer')}" namespace="http://hl7.org/fhir">
        <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
      </xsl:element>
    </f:answer>
    <xsl:for-each select="preceding-sibling::f:extension[starts-with(@url,'http://hl7.org/fhir/questionnaire-extensions#additionalAnswer')]|f:extension[starts-with(@url,'http://hl7.org/fhir/questionnaire-extensions#additionalAnswer')]">
      <f:answer>
        <xsl:element name="value{substring-after(@url, 'additionalAnswer')}" namespace="http://hl7.org/fhir">
          <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
        </xsl:element>
      </f:answer>
    </xsl:for-each>
  </xsl:template>
  <xsl:template mode="upgradeQuestionnaire" match="f:choice">
    <f:answer>
      <xsl:element name="valueCoding" namespace="http://hl7.org/fhir">
        <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
      </xsl:element>
    </f:answer>
  </xsl:template>
  <!-- ====================================
     - = Questionnaire to HTML
     - ==================================== -->
  <xsl:template match="f:Questionnaire">
    <html xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.w3.org/1999/xhtml http://www.w3.org/2002/08/xhtml/xhtml1-strict.xsd">
      <head>
        <title>
          <xsl:choose>
            <xsl:when test="f:title/@value">
              <xsl:value-of select="f:title/@value"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="$phrases//phrase[@name='form']/@value"/>
              <xsl:if test="f:identifier/@value[1]">
                <xsl:value-of select="concat(': ', f:identifier/@value[1])"/>
              </xsl:if>
            </xsl:otherwise>
          </xsl:choose>
        </title>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
      </head>
      <body>
        <div>
          <xsl:apply-templates select="f:group"/>
        </div>
        <xsl:call-template name="scripts"/>
      </body>
    </html>
  </xsl:template>
  <xsl:template name="scripts">
    <script type="text/javascript">
      <xsl:comment>
      function addGroup(groupId, button){
        group = document.getElementById(groupId)
        if (group.style.display=="none") {
          group.style.display="inline"
        } else {
          newGroup = group.cloneNode(true)
          button.parentNode.insertBefore(newGroup, button)
          resetGroup(newGroup)
          newGroup.getElementsByTagName("button")[2].display="inline"
        }
      }
      function deleteGroup(button){
        group = button.parentNode
        if (group.id!="undefined") {
          group.style.display="none"
        } else {
          group.parentNode.removeChild(group)
        }
      }
      function resetGroup(button){
        resetDiv(button.parentNode)
      }
      function resetDiv(div) {
        inputs = div.getElementsByTagName("input")
        for(i=0; i &lt; inputs.length; i++) {
          if (inputs[i].default = "undefined") {
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
        format = new RegExp("^([+-]?([1-9]\d*)|0)(\.\d+)?$")
        if (!format.test(input.value)) {
          input.focus()
          alert("Invalid decimal value")
        }
      }
      function checkInteger(input) {
        format = new RegExp("^[+-]?([1-9]\d*)|0$")
        if (!format.test(input.value)) {
          input.focus()
          alert("Invalid integer value")
        }
      }
      function checkDate(input) {
        checkDateValue(input, "^([1-9][0-9]{3}|0[0-9]{3})(-(0[1-9]|1[0-2])(-(0[1-9]|[12][0-9]|3[01]))?)?$", "Invalid date value - must be yyyy-mm-dd")
      }
      function checkDateTime(input) {
        checkDateValue(input, "^([1-9][0-9]{3}|0[0-9]{3})([-/](0[1-9]|1[0-2])([-/](0[1-9]|[12][0-9]|3[01])(\s+(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?)(\s+(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?)?)?)?$", "Invalid date-time value - must be yyyy-mm-dd hh:mm:ss.sss +/-zz:zz")
      }
      function checkInstant(input) {
        checkDateValue(input, "^([1-9][0-9]{3}|0[0-9]{3})[/-](0[1-9]|1[0-2])[-/](0[1-9]|[12][0-9]|3[01])\s+(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)?)\s+(\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00)$", "Invalid date-time value - must be yyyy-mm-dd hh:mm:ss.sss +/-zz:zz")
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
      //</xsl:comment>
    </script>
  </xsl:template>
  <xsl:template match="f:group">
    <xsl:param name="level" select="1"/>
    <xsl:param name="hierarchy"/>
    <xsl:variable name="label">
      <xsl:choose>
        <xsl:when test="f:label">
          <xsl:value-of select="f:label"/>
        </xsl:when>
        <xsl:when test="$numberSections='true'">
          <xsl:number level="multiple"/>
        </xsl:when>
      </xsl:choose>
      <xsl:value-of select="concat(' ', f:title/@value)"/>
    </xsl:variable>
    <xsl:if test="normalize-space($label)!=''">
      <xsl:element name="h{$level}" namespace="{$htmlNamespace}">
        <xsl:value-of select="$label"/>
      </xsl:element>
    </xsl:if>
    <xsl:if test="f:text/@value">
      <p>
        <xsl:value-of select="f:text/@value"/>
      </p>
    </xsl:if>
    <xsl:variable name="repetitions">
      <xsl:choose>
        <xsl:when test="not(f:maxOccursCode/@value or f:maxOccursInteger/@value)">1</xsl:when>
        <xsl:when test="f:minOccurs/@value">
          <xsl:value-of select="f:minOccurs/@value"/>
        </xsl:when>
        <xsl:when test="f:maxOccursCode/@value='*' or f:maxOccursInteger/@value &gt; $defaultGroupRepetitions">
          <xsl:value-of select="$defaultGroupRepetitions"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="f:maxOccursInteger/@value"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:call-template name="groupDiv">
      <xsl:with-param name="level" select="$level"/>
      <xsl:with-param name="hierarchy" select="$hierarchy"/>
      <xsl:with-param name="totalRepetitions" select="$repetitions"/>
    </xsl:call-template>
    <xsl:if test="$repetitions!=1">
      <button type="button" onclick="addGroup('{generate-id()}{$hierarchy}', this)">Add section</button>
    </xsl:if>
  </xsl:template>
  <xsl:template name="groupDiv">
    <xsl:param name="level"/>
    <xsl:param name="hierarchy"/>
    <xsl:param name="totalRepetitions"/>
    <xsl:param name="currentRepetition" select="1"/>
    <xsl:variable name="style">
      <xsl:text>margin:0.5em 2em</xsl:text>
      <xsl:if test="$totalRepetitions!=1">
        <xsl:text>;border-style:solid;border-width:1px;border-color:#A0A0A0</xsl:text>
      </xsl:if>
    </xsl:variable>
    <div style="{$style}">
      <xsl:if test="$currentRepetition=1">
        <xsl:attribute name="id">
          <xsl:value-of select="concat(generate-id(), $hierarchy)"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:if test="$enableReset='true'">
        <button type="button" onclick="resetGroup(this)">Reset</button>
      </xsl:if>
      <button type="button" onclick="deleteGroup(this)">
        <xsl:if test="not((f:minOccurs/@value=0 and $currentRepetition=1) or (not(f:minOccurs/@value) and $currentRepetition&gt;1) or $currentRepetition &gt; f:minOccurs/@value)">
          <xsl:attribute name="style">display:none</xsl:attribute>
        </xsl:if>
        <xsl:text>Remove</xsl:text>
      </button>
      <xsl:apply-templates select="f:group|f:question">
        <xsl:with-param name="level" select="$level + 1"/>
        <xsl:with-param name="hierarchy" select="concat($hierarchy, '.', $currentRepetition)"/>
      </xsl:apply-templates>
    </div>
    <xsl:if test="$currentRepetition &lt; $totalRepetitions">
      <xsl:call-template name="groupDiv">
        <xsl:with-param name="level" select="$level"/>
        <xsl:with-param name="hierarchy" select="$hierarchy"/>
        <xsl:with-param name="totalRepetitions" select="$totalRepetitions"/>
        <xsl:with-param name="currentRepetition" select="$currentRepetition + 1"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
  <xsl:template match="f:question">
    <xsl:param name="level"/>
    <xsl:param name="hierarchy"/>
    <xsl:variable name="id" select="generate-id()"/>
    <p>
      <b>
        <xsl:choose>
          <xsl:when test="f:label">
            <xsl:value-of select="f:label"/>
          </xsl:when>
          <xsl:when test="$numberSections='true'">
            <xsl:number level="multiple"/>
          </xsl:when>
        </xsl:choose>
        <xsl:value-of select="concat(' ', f:text/@value)"/>
      </b>
      <xsl:variable name="answerType" select="f:type/@value"/>
      <xsl:variable name="answerControl">
        <xsl:choose>
          <xsl:when test="$answerType='decimal'">
            <input type="text" onblur="checkDecimal(this)"/>
          </xsl:when>
          <xsl:when test="$answerType='integer'">
            <input type="text" onblur="checkInteger(this)"/>
          </xsl:when>
          <xsl:when test="$answerType='boolean'">
            <input type="checkbox"/>
          </xsl:when>
          <xsl:when test="$answerType='date'">
            <input type="text" onblur="checkDate(this)" maxlength="10"/>
          </xsl:when>
          <xsl:when test="$answerType='dateTime'">
            <input type="text" onblur="checkDateTime(this)" maxlength="26"/>
          </xsl:when>
          <xsl:when test="$answerType='dateInstant'">
            <input type="text" onblur="checkInstant(this)" maxlength="30"/>
          </xsl:when>
          <xsl:when test="$answerType='text'">
            <textarea rows="4" cols="50">&#xA0;</textarea>
          </xsl:when>
          <xsl:when test="$answerType='single-choice' or $answerType='multiple-choice' or $answerType='open-single-choice' or $answerType='open-multiple-choice'">
            <xsl:choose>
              <xsl:when test="not(f:options/f:reference/@value)">
                <xsl:message>
<!--                  <xsl:value-of select="concat('WARNING: A question was defined as requiring a coded answer, but no set of allowed values was declared so unable to expose code choices for question: ', f:text/@value)"/>-->
                </xsl:message>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="valueset">
                  <xsl:apply-templates mode="resolveReference" select="f:options/f:reference"/>
                </xsl:variable>
                <xsl:variable name="valuesetCodings">
                  <xsl:apply-templates mode="valueSetToCodings" select="$valueset"/>
                </xsl:variable>
                <xsl:choose>
                  <xsl:when test="count($valuesetCodings/f:coding)=0">
                    <xsl:message>
                      <xsl:value-of select="concat('WARNING: Unable to resolve value set reference ', f:options/f:reference/@value, ' so unable to expose code choices for question: ', f:text/@value)"/>
                    </xsl:message>
                  </xsl:when>
                  <xsl:when test="count($valuesetCodings/f:coding)&gt;$maxCodings">
                    <xsl:value-of select="concat('WARNING: Question has value set with more than ', $maxCodings, ' options.  No proper interface could be provided.&#x0a;', f:text/@value)"/>
                  </xsl:when>
                  <xsl:when test="ends-with($answerType, 'single-choice')">
                    <br/>
                    <xsl:for-each select="$valuesetCodings/f:coding">
                      <xsl:if test="f:system">
                        <input type="radio" name="{$id}" value="{f:code/@value}"/>
                      </xsl:if>
                      <xsl:choose>
                        <xsl:when test="f:display/@value">
                          <xsl:value-of select="concat(' ', f:display/@value)"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="concat(' ', f:code/@value)"/>
                        </xsl:otherwise>
                      </xsl:choose>
                      <br/>
                    </xsl:for-each>
                  </xsl:when>
                  <xsl:otherwise>
                    <br/>
                    <xsl:for-each select="$valuesetCodings/f:coding">
                      <xsl:if test="f:system">
                        <input type="checkbox" name="{$id}" value="{f:code/@value}"/>
                      </xsl:if>
                      <xsl:choose>
                        <xsl:when test="f:display/@value">
                          <xsl:value-of select="concat(' ', f:display/@value)"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="concat(' ', f:code/@value)"/>
                        </xsl:otherwise>
                      </xsl:choose>
                      <br/>
                    </xsl:for-each>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:when>
          <xsl:otherwise>
            <xsl:choose>
              <xsl:when test="normalize-space($answerType)=''">
                <xsl:message>
<!--                  <xsl:value-of select="concat('WARNING: Answer format was not declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>-->
                </xsl:message>
              </xsl:when>
              <xsl:when test="not($answerType='string')">
                <xsl:message>
<!--                  <xsl:value-of select="concat('WARNING: Unrecognized answer format ', $answerType, ' was declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>-->
                </xsl:message>
              </xsl:when>
            </xsl:choose>
            <input type="text"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:variable>
      <xsl:variable name="adjustedInput">
        <xsl:apply-templates mode="adjustInput" select="$answerControl"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="not(contains($answerType, 'choice')) and f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinality']/@valueInteger">
          <xsl:call-template name="repeatAnswer">
            <xsl:with-param name="totalRepetitions" select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxCardinality']/@valueInteger"/>
            <xsl:with-param name="input" select="$adjustedInput"/>
            <xsl:with-param name="answers" select="f:answer"/>
          </xsl:call-template>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="repeatAnswer">
            <xsl:with-param name="input" select="$adjustedInput"/>
            <xsl:with-param name="answers" select="f:answer"/>
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </p>
    <xsl:apply-templates select="f:group">
      <xsl:with-param name="level" select="$level + 1"/>
      <xsl:with-param name="hierarchy" select="$hierarchy"/>
    </xsl:apply-templates>
  </xsl:template>
  <xsl:template name="repeatAnswer">
    <xsl:param name="repetition" select="1"/>
    <xsl:param name="totalRepetitions" select="1"/>
    <xsl:param name="input"/>
    <xsl:param name="answers"/>
    <xsl:if test="not($totalRepetitions=1)">
      <br/>
    </xsl:if>
    <xsl:apply-templates mode="copyInput" select="$input">
      <xsl:with-param name="position" select="$repetition"/>
      <xsl:with-param name="answer" select="$answers/*[position()=$repetition]/@value"/>
    </xsl:apply-templates>
    <xsl:if test="$repetition &lt; $totalRepetitions">
      <xsl:call-template name="repeatAnswer">
        <xsl:with-param name="repetition" select="$repetition + 1"/>
        <xsl:with-param name="totalRepetitions" select="$totalRepetitions"/>
        <xsl:with-param name="input" select="$input"/>
        <xsl:with-param name="answers" select="$answers"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
  <xsl:template mode="copyInput" match="@*|node()">
    <xsl:param name="position"/>
    <xsl:param name="answer"/>
    <xsl:copy>
      <xsl:apply-templates mode="copyInput" select="@*|node()">
        <xsl:with-param name="position" select="$position"/>
        <xsl:with-param name="answer" select="$answer"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="copyInput" match="html:input|html:textArea">
    <xsl:param name="position"/>
    <xsl:param name="answer"/>
    <xsl:copy>
      <xsl:apply-templates mode="copyInput" select="@*">
        <xsl:with-param name="position" select="$position"/>
        <xsl:with-param name="answer" select="$answer"/>
      </xsl:apply-templates>
      <xsl:if test="normalize-space($answer)!=''">
        <xsl:choose>
          <xsl:when test="self::textArea">
            <xsl:value-of select="$answer"/>
          </xsl:when>
          <xsl:when test="type='text'">
            <xsl:attribute name="value">
              <xsl:value-of select="$answer"/>
            </xsl:attribute>
          </xsl:when>
          <xsl:when test="type='checkbox'">
            <xsl:if test="$answer='true'">
              <xsl:attribute name="checked">true</xsl:attribute>
            </xsl:if>
          </xsl:when>
        </xsl:choose>
      </xsl:if>
      <xsl:apply-templates mode="copyInput" select="@*">
        <xsl:with-param name="position" select="$position"/>
        <xsl:with-param name="answer" select="$answer"/>
      </xsl:apply-templates>
    </xsl:copy>
  </xsl:template>
  <xsl:template mode="resolveReference" match="f:reference">
    <xsl:choose>
      <xsl:when test="starts-with(@value, '#')">
        <xsl:copy-of select="ancestor::f:*[not(parent::f:*)]/f:contained/f:*[@id=substring-after(current()/@value, '#')]"/>
      </xsl:when>
      <xsl:when test="ancestor::atom:feed/atom:entry[atom:id=current()/@value]">
        <xsl:copy-of select="ancestor::atom:feed/atom:entry[atom:id=current()/@value]/atom:content/f:*"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:copy-of select="document(concat(@value, '?_format=application/xml+fhir'))"/>
        <!-- Todo: Is there a way to set HTTP headers in a retrieval? -->
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template mode="valueSetToCodings" match="f:ValueSet">
    <xsl:choose>
      <xsl:when test="f:expansion">
        <xsl:for-each select="f:expansion//f:contains">
          <f:coding>
            <xsl:copy-of select="*"/>
          </f:coding>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="f:define">
        <xsl:for-each select="f:define//f:concept">
          <f:coding>
            <xsl:if test="not(f:abstract/@value='true')">
              <xsl:copy-of select="ancestor::f:define/f:system"/>
            </xsl:if>
            <xsl:copy-of select="f:code|f:display"/>
          </f:coding>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="f:compose[not(f:import or f:exclude or f:include[f:filter])]">
        <xsl:for-each select="f:compose/f:include">
          <f:coding>
            <xsl:copy-of select="*"/>
          </f:coding>
        </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
        <xsl:message>
          <xsl:value-of select="concat('Value set ', f:name/@value, ' is too complex to be expanded by this transform - no codes displayed for question.')"/>
        </xsl:message>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:template mode="adjustInput" match="node()">
    <!-- Default template just copies everything.  Extending transforms may override this to change input controls -->
    <xsl:copy-of select="."/>
  </xsl:template>
</xsl:stylesheet>

