<?xml version="1.0" encoding="UTF-8"?>
<!--
  - (c) 2014 Lloyd McKenzie & Associates Consulting Ltd.  All rights reserved
  -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xmlns:html="http://www.w3.org/1999/xhtml" xmlns:f="http://hl7.org/fhir" xmlns:atom="http://www.w3.org/2005/Atom" xmlns:saxon="http://saxon.sf.net/" xmlns:msxsl="urn:schemas-microsoft-com:xslt" xmlns:common="http://exslt.org/common" exclude-result-prefixes="f atom saxon msxsl common html">
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
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minOccurs']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxOccurs']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-sdc#additionalGroupText']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-sdc#additionalQuestionText']"/>
  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#enableWhen']"/>

  <xsl:template mode="upgradeQuestionnaire" match="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#label']/f:valueString|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#hidden']/f:valueBoolean|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#defaultValue']/f:valueString|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#defaultAsFixed']/f:valueBoolean|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#allowedResource']/f:valueCode|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#mimeType']/f:valueCode|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-sdc#endpoint']/f:valueUri|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-sdc#specialGroup']/valueCode|
                                                   f:extension[@url='http://hl7.org/fhir/questionnaire-sdc#optionalDisplay']/valueBoolean|
                                                   f:extension[@url='http://www.healthintersections.com.au/fhir/Profile/metadata#reference']/valueUri">
    <xsl:variable name="name" select="concat('_', substring-after(parent::f:extension/@url, '#'))"/>
    <xsl:element name="{$name}" namespace="http://hl7.org/fhir">
      <xsl:apply-templates mode="upgradeQuestionnaire" select="@*|node()"/>
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
      <xsl:when test="f:text!='' or f:group or count(f:question)&gt;1 or f:repeats/@value='true'">
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
        <xsl:when test="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minOccurs']">
          <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#minOccurs']/f:valueInteger">
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
      <xsl:when test="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxOccurs']/f:valueCode or
                    (not(f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxOccurs']/f:valueInteger) and f:repeats/@value='true')">
        <f:_maxOccursCode value="*"/>
      </xsl:when>
      <xsl:otherwise>
        <f:_maxOccursInteger>
          <xsl:choose>
            <xsl:when test="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxOccurs']/f:valueInteger">
              <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/questionnaire-extensions#maxOccurs']/f:valueInteger">
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
    <xsl:value-of select="$pass3"/>
  </xsl:template>
  <!-- ====================================
     - = Questionnaire to HTML
     - ==================================== -->
  <xsl:template match="f:Questionnaire">
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
              <xsl:text>function validateQuestionnaire() {&#x0a;</xsl:text>
              <xsl:value-of select="concat('  return validate', f:group/f:linkId/@safeValue, '(document.getElementById(&quot;div-cnt&quot;));&#x0a;')"/>
              <xsl:text>}&#x0a;&#x0a;</xsl:text>
              <xsl:text>function populateQuestionnaire() {&#x0a;</xsl:text>
              <xsl:text>  return null;&#x0a;</xsl:text>
              <xsl:text>}&#x0a;&#x0a;</xsl:text>
              <xsl:text>function parseQuestionnaire() {&#x0a;</xsl:text>
              <xsl:text>  return null;&#x0a;</xsl:text>
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
  <xsl:template mode="script" match="f:group">
    <xsl:value-of select="concat('function validate', generate-id(), '(node) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
    <xsl:value-of select="concat('function populate', generate-id(), '(node, answers) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
    <xsl:value-of select="concat('function parse', generate-id(), '(node) {&#x0a;')"/>
    <xsl:text>}&#x0a;&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="validateScript" match="f:group">
    <xsl:value-of select="concat('&#x0a;function validate', f:linkId/@safeValue, '(node) {&#x0a;')"/>
    <xsl:value-of select="concat('  var groupNodes = findDiv(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
    <xsl:text disable-output-escaping="yes">  if (groupNodes.length &lt; </xsl:text>
    <xsl:value-of select="concat(f:_minOccurs/@value, ')&#x0a;')"/>
    <xsl:text>    return false&#x0a;  else&#x0a;    return </xsl:text>
    <xsl:for-each select="f:group|f:question">
      <xsl:if test="position()!=1">
        <xsl:text disable-output-escaping="yes">&#x0a;         &amp;&amp; </xsl:text>
      </xsl:if>
      <xsl:value-of select="concat('validate', f:linkId/@safeValue, '(findDiv(node, &quot;', f:linkId/@value, '&quot;))')"/>
    </xsl:for-each>
    <xsl:text>;&#x0a;}&#x0a;&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="validateScript" match="f:question">
    <xsl:value-of select="concat('&#x0a;function validate', f:linkId/@safeValue, '(node) {&#x0a;')"/>
    <xsl:value-of select="concat('  var answerNodes = findAnswers(node, &quot;', f:linkId/@value, '&quot;);&#x0a;')"/>
    <xsl:text disable-output-escaping="yes">  if (answerNodes.length &lt; </xsl:text>
    <xsl:value-of select="concat(f:_minOccurs/@value, ')&#x0a;')"/>
    <xsl:text>    return false&#x0a;  else&#x0a;    return </xsl:text>
    <xsl:for-each select="f:group">
      <xsl:if test="position()!=1">
        <xsl:text disable-output-escaping="yes">&#x0a;         &amp;&amp; </xsl:text>
      </xsl:if>
      <xsl:value-of select="concat('validate', f:linkId/@safeValue, '(findDiv(node, &quot;', f:linkId/@value, '&quot;))')"/>
    </xsl:for-each>
    <xsl:text>;&#x0a;}&#x0a;&#x0a;</xsl:text>
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
function findDiv(node, linkId) {
  return $(node).children("div").filter(function() {
    return $(this).children("span").text() == linkId;
  });
}

function findAnswers(node, linkId) {
  var answerCell = $(node).children("table").children("tr").filter(function() {return $(this).firstChild.firstChild.text() === linkId;}).cell(1);
  if (answerCell.length == 0) {
    alert("Unable to find question " + linkId);
    return null;
  }
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
      "status": "draft"
      // It would be nice to populate the reference to the Questionnaire, but we'd need to know the id      
    }
  } else {
    populateQuestionnaire();
  }
}

function saveDraft() {
  questionnaireAnswers.status = "draft"
  saveQuestionnaire();
}

function saveFinal() {
  if (validateQuestionnaire()) {
    if (questionnaireAnswers.status == "completed")
      questionnaireAnswers.status = "amended"
     else
      questionnaireAnswers.status = "completed";
    saveQuestionnaire();
  }
}

function saveQuestionnaire() {
  parseQuestionnaire();
  
}

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
                if (codes[i].extension[j].url == "http:\/\/hl7.org\/fhir\/Profile\/tools-extensions#definition")
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
          if (codes[i].extension[j].url == "http:\/\/hl7.org\/fhir\/Profile\/tools-extensions#definition")
            definition = codes[i].extension[j].valueString;
        }
      }
      source = source + "&lt;li id=\""+codes[i].system+"||"+codes[i].code+"\" title=\""+definition+"\">"+codes[i].display+"&lt;/li>";
    }
  }
  document.getElementById("fhir-code-ul").innerHTML = source;  
  new combo('fhir-code-input','#9c9','#fff'); 
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
            document.getElementById("fhir-tag-ul").innerHTML = "&lt;li>"+processNarrative(oo.text.div)+"&lt;/li>";  
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

function deleteRow(control) {
  var row = control.parentNode.parentNode;
  if (confirm("Remove this entry?")) {
    row.parentNode.deleteRow(row.rowIndex);
    checkMaxCodeRows()
  }
}

function setRowId(row) {
  row.id = getRowId(row.children[2].innerHTML, row.children[3].innerHTML, row.children[1].innerHTML);
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
  currentRow.insertCell(0).innerHTML = removeButton;
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
      .combo {position:relative;width:310px;text-align:left;padding:0;margin:0;}
      .combo * {padding:0;margin:0;}
      .combo label {display:block;float:left;width:92px;text-align:right;}
      .combo input {width:300px; height:1.5em;z-index:50;}
      .combo ul {padding:1px;border:2px solid #ccc;width: 300px;background-color:#fff;position:absolute;right:3px;top:1.8em;display:none;z-index:51;}
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
        var row = table.insertRow(0);
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
      function checkTime(input) {
        checkDateValue(input, "^([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\.[0-9]+)$", "Invalid time value - must be hh:mm:ss.sss")
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
      </xsl:comment>
    </script>
  </xsl:template>
  <xsl:template match="f:group">
    <xsl:param name="path"/>
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
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/Profile/questionnaire-extensions#flyover']/stringValue/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
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
    <xsl:if test="$repetitions!=1">
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
  </xsl:template>
  <xsl:template name="groupDiv">
    <xsl:param name="path"/>
    <xsl:param name="level"/>
    <xsl:param name="hierarchy"/>
    <xsl:param name="totalRepetitions"/>
    <xsl:param name="currentRepetition" select="1"/>
    <xsl:variable name="style">
      <xsl:text>display:block;margin:0.5em 2em;</xsl:text>
      <xsl:if test="$totalRepetitions!=1">
        <xsl:text>border-style:solid;border-width:1px;border-color:#A0A0A0;</xsl:text>
      </xsl:if>
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
      <xsl:if test="$totalRepetitions &gt; 1">
        <xsl:choose>
          <xsl:when test="$iconPath=''">
            <button type="button" onclick="deleteGroup(this)">
              <xsl:if test="not((f:_minOccurs/@value=0 and $currentRepetition=1) or (not(f:_minOccurs/@value) and $currentRepetition&gt;1) or $currentRepetition &gt; f:_minOccurs/@value)">
                <xsl:attribute name="style">display:none</xsl:attribute>
              </xsl:if>
              <xsl:text>Remove</xsl:text>
            </button>
          </xsl:when>
          <xsl:otherwise>
            <input type="image" onclick="deleteGroup(this)" src="{$iconPath}/html-form-delete.png" alt="Remove" style="width:10px;height:10px;"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:if>
      <xsl:apply-templates select="f:group|f:question">
        <xsl:with-param name="path" select="$newPath"/>
        <xsl:with-param name="level" select="$level + 1"/>
        <xsl:with-param name="hierarchy" select="concat($hierarchy, '.', $currentRepetition)"/>
      </xsl:apply-templates>
    </div>
    <xsl:if test="$currentRepetition &lt; $totalRepetitions">
      <xsl:call-template name="groupDiv">
        <xsl:with-param name="path" select="$path"/>
        <xsl:with-param name="level" select="$level"/>
        <xsl:with-param name="hierarchy" select="$hierarchy"/>
        <xsl:with-param name="totalRepetitions" select="$totalRepetitions"/>
        <xsl:with-param name="currentRepetition" select="$currentRepetition + 1"/>
      </xsl:call-template>
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
        <xsl:value-of select="concat(' ', f:text/@value)"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="f:required/@value='true'">
          <b>
            <xsl:value-of select="$label"/>
          </b>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="$label"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
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
        <xsl:when test="$answerType='time'">
          <input type="text" onblur="time(this)" maxlength="26"/>
        </xsl:when>
        <xsl:when test="$answerType='instant'">
          <input type="text" onblur="checkInstant(this)" maxlength="30"/>
        </xsl:when>
        <xsl:when test="$answerType='text'">
          <textarea rows="4" cols="50">&#xA0;</textarea>
        </xsl:when>
        <xsl:when test="$answerType='choice' or $answerType='open-choice'">
          <xsl:choose>
            <xsl:when test="not(f:options/f:reference/@value)">
              <xsl:message>
                <xsl:value-of select="concat('WARNING: A question was defined as requiring a coded answer, but no set of allowed values was declared so unable to expose code choices for question: ', f:linkId/@value, ' &quot;', f:text/@value, '&quot;')"/>
              </xsl:message>
            </xsl:when>
            <xsl:otherwise>
              <xsl:variable name="valueset">
                <xsl:apply-templates mode="resolveReference" select="f:options/f:reference"/>
              </xsl:variable>
              <xsl:variable name="valuesetCodings">
                <xsl:choose>
                  <xsl:when test="$useMicrosoft='true'">
                    <xsl:apply-templates mode="valueSetToCodings" select="msxsl:node-set($valueset)"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:apply-templates mode="valueSetToCodings" select="common:node-set($valueset)"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:variable name="codingCount">
                <xsl:choose>
                  <xsl:when test="$useMicrosoft='true'">
                    <xsl:value-of select="count(msxsl:node-set($valuesetCodings)/f:coding)"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="count(common:node-set($valuesetCodings)/f:coding)"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
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
              <xsl:choose>
                <xsl:when test="$codingCount=0 and $operationOutcome!='too-costly'">                
                  <xsl:message>
                    <xsl:value-of select="concat('WARNING: Unable to resolve value set reference ', f:options/f:reference/@value, ' so unable to expose code choices for question: ', f:text/@value)"/>
                  </xsl:message>
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
                  <button onclick="javascript:initCodeLookup('{$encodedValuesetURL}', this.previousElementSibling, {$extensible}, {$max})">Edit</button>
                </xsl:when>
                <xsl:when test="$codingCount&gt;$maxListboxCodings">
                  <!-- Can't Lookup -->
                  <xsl:value-of select="concat('WARNING: Question has value set with more than ', $maxListboxCodings, ' options.  No proper interface could be provided.&#x0a;', f:text/@value)"/>
                </xsl:when>
                <xsl:when test="$codingCount&gt;$maxCheckboxCodings">
                  <!-- List box -->
                  <select name="{$path}">
                    <xsl:if test="f:_maxOccursInteger/@value!=1 or f:_maxOccursCode/@value">
                      <xsl:attribute name="multiple">multiple</xsl:attribute>
                    </xsl:if>
                    <xsl:choose>
                      <xsl:when test="$useMicrosoft='true'">
                        <xsl:for-each select="msxsl:node-set($valuesetCodings)/f:coding">
                          <xsl:call-template name="doListBoxItem"/>
                        </xsl:for-each>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:for-each select="common:node-set($valuesetCodings)/f:coding">
                          <xsl:call-template name="doListBoxItem"/>
                        </xsl:for-each>
                      </xsl:otherwise>
                    </xsl:choose>
                  </select>
                </xsl:when>
                <xsl:when test="f:_maxOccursInteger/@value=1">
                  <!-- Radio buttons -->
                  <xsl:choose>
                    <xsl:when test="$useMicrosoft='true'">
                      <xsl:for-each select="msxsl:node-set($valuesetCodings)/f:coding">
                        <xsl:call-template name="doRadioBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:for-each select="common:node-set($valuesetCodings)/f:coding">
                        <xsl:call-template name="doRadioBoxItem">
                          <xsl:with-param name="path" select="$path"/>
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
                      <xsl:for-each select="msxsl:node-set($valuesetCodings)/f:coding">
                        <xsl:call-template name="doCheckBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:for-each select="common:node-set($valuesetCodings)/f:coding">
                        <xsl:call-template name="doCheckBoxItem">
                          <xsl:with-param name="path" select="$path"/>
                        </xsl:call-template>
                      </xsl:for-each>
                    </xsl:otherwise>
                  </xsl:choose>
                  <xsl:if test="$answerType='open-choice'">
                    <xsl:text>Other:</xsl:text>
                    <input type="text"/>
                  </xsl:if>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:when>
        <xsl:when test="$answerType='reference'">
          <xsl:message>WARNING: Reference is not yet a supported type - treating as string</xsl:message>
          <input tabindex="text"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:choose>
            <xsl:when test="normalize-space($answerType)=''">
              <xsl:message>
                <xsl:value-of select="concat('WARNING: Answer format was not declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>
              </xsl:message>
            </xsl:when>
            <xsl:when test="not($answerType='string')">
              <xsl:message>
                <xsl:value-of select="concat('WARNING: Unrecognized answer format ', $answerType, ' was declared for question: ', f:text/@value, '&#x0a;Treating answer as string.')"/>
              </xsl:message>
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
      <td style="vertical-align:top;">
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/Profile/questionnaire-extensions#flyover']/stringValue/@value">
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
      </td>
    </tr>
  </xsl:template>
  <xsl:template name="doListBoxItem">
    <xsl:variable name="name">
      <xsl:choose>
        <xsl:when test="f:display/@value">
          <xsl:value-of select="concat(' ', f:display/@value)"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(' ', f:code/@value)"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <option value="{f:code/@value}">
      <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/Profile/tools-extensions#definition']/f:valueString/@value">
        <xsl:attribute name="title">
          <xsl:value-of select="."/>
        </xsl:attribute>
      </xsl:for-each>
      <xsl:value-of select="$name"/>
    </option>
  </xsl:template>
  <xsl:template name="doRadioBoxItem">
    <xsl:param name="path"/>
    <xsl:if test="f:system">
      <input type="radio" name="{$path}" value="{f:code/@value}">
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/Profile/tools-extensions#definition']/f:valueString/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
      </input>
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
  </xsl:template>
  <xsl:template name="doCheckBoxItem">
    <xsl:param name="path"/>
    <xsl:if test="f:system">
      <input type="checkbox" name="{$path}" value="{f:code/@value}">
        <xsl:for-each select="f:extension[@url='http://hl7.org/fhir/Profile/tools-extensions#definition']/f:valueString/@value">
          <xsl:attribute name="title">
            <xsl:value-of select="."/>
          </xsl:attribute>
        </xsl:for-each>
      </input>
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
        <xsl:copy-of select="ancestor::f:*[not(parent::f:*)]/f:contained/f:*[@id=substring-after(current()/@value, '#')]"/>
      </xsl:when>
      <xsl:when test="ancestor::atom:feed/atom:entry[atom:id=current()/@value]">
        <xsl:copy-of select="ancestor::atom:feed/atom:entry[atom:id=current()/@value]/atom:content/f:*"/>
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

