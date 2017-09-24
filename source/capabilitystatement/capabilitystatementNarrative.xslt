<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xpath-default-namespace="http://hl7.org/fhir" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns="http://www.w3.org/1999/xhtml" xmlns:xhtml="http://www.w3.org/1999/xhtml" xmlns:fn="http://hl7.org/fhir/xslt-functions" exclude-result-prefixes="xs xhtml fn">
  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
<!--  <xsl:variable name="fhirpath" select="'[%fhir-path%]'"/>  TODO: Put this back once validation doesn't choke on it -->
  <xsl:variable name="fhirpath" select="'{{site.data.fhir.path}}'"/>
<!--  <xsl:variable name="fhirpath" select="'../'"/>-->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="/*[self::CapabilityStatement|self::Conformance]">
    <xsl:copy>
      <xsl:copy-of select="@*|id"/>
      <text xmlns="http://hl7.org/fhir">
        <status xmlns="http://hl7.org/fhir" value="generated"/>
        <div xmlns="http://www.w3.org/1999/xhtml">
          <xsl:for-each select="name">
            <h2>
              <xsl:value-of select="@value"/>
            </h2>
          </xsl:for-each>
          <p>
            <xsl:text>(</xsl:text>
            <xsl:choose>
              <xsl:when test="kind/@value='instance'">Implementation Instance Capability Statement</xsl:when>
              <xsl:when test="kind/@value='capability'">Software Capability Capability Statement</xsl:when>
              <xsl:when test="kind/@value='requirements'">Requirements Definition</xsl:when>
              <xsl:otherwise>**Unknown Capability Statement Kind**</xsl:otherwise>
            </xsl:choose>
            <xsl:text>)</xsl:text>
          </p>
          <p>
            <xsl:variable name="content" as="xs:string+">
              <xsl:value-of select="url/@value"/>
              <xsl:for-each select="version">
                <xsl:value-of select="concat('Version: ', @value)"/>
              </xsl:for-each>
              <xsl:value-of select="concat('Published: ', date/@value)"/>
              <xsl:for-each select="status[@value=('draft', 'retired')]">
                <xsl:value-of select="concat('(', @value, ')')"/>
              </xsl:for-each>
              <xsl:if test="experimental/@value='true'">
                <xsl:text> - experimental</xsl:text>
              </xsl:if>
            </xsl:variable>
            <xsl:value-of select="normalize-space(string-join($content, ' '))"/>
          </p>
          <xsl:variable name="firstTelecom" as="xs:string?">
            <xsl:choose>
              <xsl:when test="telecom[system/@value='url']">
                <xsl:value-of select="telecom[system/@value='url'][1]/value/@value"/>
              </xsl:when>
              <xsl:when test="telecom[system/@value='email']">
                <xsl:value-of select="concat('mailto:', telecom[system/@value='email'][1]/value/@value)"/>
              </xsl:when>
            </xsl:choose>
          </xsl:variable>
          <p>
            <xsl:text>Published by: </xsl:text>
            <b>
              <xsl:choose>
                <xsl:when test="$firstTelecom">
                  <a href="{$firstTelecom}">
                    <xsl:value-of select="publisher/@value"/>
                  </a>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:value-of select="publisher/@value"/>
                </xsl:otherwise>
              </xsl:choose>
            </b>
            <xsl:variable name="telecoms" as="xs:string*">
              <xsl:for-each select="telecom[not(starts-with($firstTelecom, value/@value))]">
                <xsl:if test="system/@value">
                  <xsl:value-of select="concat(system/@value, ': ')"/>
                </xsl:if>
                <xsl:value-of select="value/@value"/>
              </xsl:for-each>
            </xsl:variable>
            <xsl:value-of select="string-join($telecoms, ' ')"/>
          </p>
          <xsl:copy-of select="fn:handleMarkdownLines(description/@value)"/>
          <xsl:for-each select="requirements">
            <p>
              <b>Requirements:</b>
            </p>
            <xsl:copy-of select="fn:handleMarkdownLines(@value)"/>
          </xsl:for-each>
          <xsl:for-each select="copyright">
            <p>
              <b>Copyright:</b>
            </p>
            <xsl:copy-of select="fn:handleMarkdownLines(@value)"/>
          </xsl:for-each>
          <xsl:for-each select="software">
            <p>
              <xsl:variable name="parts" as="xs:string+">
                <xsl:value-of select="concat('Applies to software: ', name/@value)"/>
                <xsl:for-each select="version/@value">
                  <xsl:value-of select="concat('version: ', .)"/>
                </xsl:for-each>
                <xsl:value-of select="date/@value"/>
              </xsl:variable>
              <xsl:value-of select="string-join($parts, ' ')"/>
            </p>
          </xsl:for-each>
          <xsl:for-each select="implementation">
            <p>
              <xsl:value-of select="concat('Implementation: ', url/@value)"/>
            </p>
            <xsl:copy-of select="fn:handleMarkdownLines(description/@value)"/>
          </xsl:for-each>
          <xsl:if test="fhirVersion/@value|acceptUnknown/@value|format/@value|profile/@value">
            <h2>General</h2>
            <div class="table-wrapper">
              <table>
              <tbody>
                <xsl:for-each select="fhirVersion/@value">
                  <tr>
                    <th>FHIR Version:</th>
                    <td>$ver$</td>
                  </tr>
                </xsl:for-each>
                <xsl:for-each select="acceptnknown/@value">
                  <tr>
                    <th>Accepts elements from future versions:</th>
                    <td>
                      <xsl:value-of select="."/>
                    </td>
                  </tr>
                </xsl:for-each>
                <xsl:if test="format/@value">
                  <tr>
                    <th>Supported formats:</th>
                    <td>
                      <xsl:value-of select="string-join(format/@value, ', ')"/>
                    </td>
                  </tr>
                </xsl:if>
                <xsl:if test="profile/@value">
                  <tr>
                    <th>Supported profiles:</th>
                    <td>
                      <xsl:for-each select="profile/@value">
                        <p>
                          <a href="{.}.html">
                            <xsl:value-of select="."/>
                          </a>
                        </p>
                      </xsl:for-each>
                    </td>
                  </tr>
                </xsl:if>
              </tbody>
            </table>
            </div>
          </xsl:if>
          <xsl:for-each select="rest">
            <h2>
              <xsl:value-of select="concat('REST ', @mode, ' behavior')"/>
            </h2>
            <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
            <xsl:for-each select="security/description/@value">
              <p>
                <b>Security:</b>
              </p>
              <xsl:copy-of select="fn:handleMarkdownLines(.)"/>
            </xsl:for-each>
            <xsl:if test="resource">
              <h3>Resource summary</h3>
              <div class="table-wrapper">              
                <table class="grid">
                <thead>
                  <tr>
                    <th>Resource</th>
                    <th>Search</th>
                    <th>Read</th>
                    <th>Read Version</th>
                    <th>Instance History</th>
                    <th>Resource History</th>
                    <th>Create</th>
                    <th>Update</th>
                    <th>Delete</th>
                  </tr>
                </thead>
                <tbody>
                  <xsl:for-each select="resource">
                    <tr>
                      <th>
                        <xsl:value-of select="type/@value"/>
                        <xsl:for-each select="profile/reference/@value">
                          <xsl:text> (</xsl:text>
                            <a href="{.}.html">Profile</a>
                          <xsl:text>)</xsl:text>
                        </xsl:for-each>
                      </th>
                      <td>
                        <xsl:for-each select="interaction[code/@value='search-type']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='read']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='vread']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                        <xsl:if test="readHistory/@value=false()">(current only)</xsl:if>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='history-instance']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='history-type']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='create']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                        <xsl:if test="conditionalCreate/@value='true'">(conditional supported)</xsl:if>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='update']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                        <xsl:if test="updateCreate/@value='false'">(existing only)</xsl:if>
                        <xsl:if test="conditionalUpdate/@value='true'">(conditional supported)</xsl:if>
                      </td>
                      <td>
                        <xsl:for-each select="interaction[code/@value='delete']">
                          <xsl:call-template name="doCapabilityStatement"/>
                        </xsl:for-each>
                        <xsl:if test="conditionalDelete/@value='true'">(conditional supported)</xsl:if>
                      </td>
                    </tr>
                  </xsl:for-each>
                </tbody>
              </table>
              </div>
            </xsl:if>
            <xsl:if test="operation">
              <p>
                <b>Operations:</b>
              </p>
              <ul>
                <xsl:for-each select="operation">
                  <li>
                    <a href="{lower-case(definition/reference/@value)}">
                      <xsl:value-of select="name/@value"/>
                    </a>
                    <xsl:for-each select="definition/display/@value">
                      <xsl:value-of select="concat(' - ', .)"/>
                    </xsl:for-each>
                    <xsl:for-each select="extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value">
                      <i>
                        <xsl:value-of select="concat(' ', .)"/>
                      </i>
                    </xsl:for-each>
                  </li>
                </xsl:for-each>
              </ul>
            </xsl:if>
            <xsl:if test="interaction">
              <xsl:variable name="doCapabilityStatement" as="xs:boolean" select="exists(interaction/extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value)"/>
              <h3>General interactions</h3>
              <div class="table-wrapper">
                <table class="list">
                <thead>
                  <tr>
                    <th>Name</th>
                    <xsl:if test="$doCapabilityStatement">
                      <th>Capability Statement</th>
                    </xsl:if>
                    <th>Description</th>
                  </tr>
                </thead>
                <tbody>
                  <xsl:apply-templates select="interaction">
                    <xsl:with-param name="doCapabilityStatement" select="$doCapabilityStatement"/>
                  </xsl:apply-templates>
                </tbody>
              </table>
              </div>
            </xsl:if>
            <xsl:for-each select="resource">
              <br/>
              <br/>
              <h3>
                <a href="{$fhirpath}{lower-case(type/@value)}.html">
                  <xsl:value-of select="type/@value"/>
                </a>              
              </h3>
              <xsl:for-each select="profile/@value">
                <p>
                  <xsl:text>Profile: </xsl:text>
                  <a href="{.}.html">
                    <xsl:value-of select="."/>
                  </a>
                </p>
              </xsl:for-each>
              <xsl:copy-of select="fn:handleMarkdownLines(description/@value)">
                <!-- This doesn't exist yet -->
              </xsl:copy-of>
              <h4>Interactions</h4>
              <div class="table-wrapper">
                <table class="list">
                <xsl:variable name="doCapabilityStatement" as="xs:boolean" select="exists(interaction/extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value)"/>
                <thead>
                  <tr>
                    <th>Name</th>
                    <xsl:if test="$doCapabilityStatement">
                      <th>Conformance</th>
                    </xsl:if>
                    <th>Description</th>
                  </tr>
                </thead>
                <tbody>
                  <xsl:apply-templates select="interaction[documentation]">
                    <xsl:with-param name="doCapabilityStatement" select="$doCapabilityStatement"/>
                  </xsl:apply-templates>
                </tbody>
              </table>
              </div>
              <xsl:if test="searchInclude or searchParam">
                <h4>Search</h4>
                <xsl:if test="searchInclude">
                  <p>
                    <xsl:text>Supported Includes: </xsl:text>
                    <xsl:value-of select="string-join(searchInclude/@value, ' ')"/>
                  </p>
                </xsl:if>
                <xsl:if test="searchParam">
                  <xsl:call-template name="doParams"/>
                </xsl:if>
              </xsl:if>
            </xsl:for-each>
          </xsl:for-each>
          <xsl:for-each select="messaging">
            <br/>
            <br/>
            <h2>Messaging</h2>
            <xsl:if test="endpoint">
              <p>
                <b>End point(s): </b>
              </p>
              <div class="table-wrapper">
                <table>
                <tbody>
                  <tr>
                    <th>Address</th>
                    <th>Protocol(s)</th>
                  </tr>
                  <xsl:for-each select="endpoint">
                    <tr>
                      <td>
                        <xsl:value-of select="address/@value"/>
                      </td>
                      <td>
                        <xsl:for-each select="protocol">
                          <xsl:if test="position()!=1">, </xsl:if>
                          <xsl:value-of select="if (display) then display/@value else code/@value"/>
                        </xsl:for-each>
                      </td>
                    </tr>
                  </xsl:for-each>
                </tbody>
              </table>
              </div>
            </xsl:if>
            <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
            <div class="table-wrapper">
              <table class="grid">
              <thead>
                <tr>
                  <th>Event</th>
                  <th>Category</th>
                  <th>Mode</th>
                  <th>Focus</th>
                  <th>Request</th>
                  <th>Response</th>
                  <th>Notes</th>
                </tr>
              </thead>
              <tbody>
                <xsl:for-each select="event">
                  <tr>
                    <td>
                      <xsl:value-of select="code/@value"/>
                    </td>
                    <td>
                      <xsl:value-of select="category/@value"/>
                    </td>
                    <td>
                      <xsl:value-of select="mode/@value"/>
                    </td>
                    <td>
                      <xsl:value-of select="focus/@value"/>
                    </td>
                    <td>
                      <xsl:for-each select="request/refereince/@value">
                        <a href="{.}.html">
                          <xsl:value-of select="."/>
                        </a>
                      </xsl:for-each>
                    </td>
                    <td>
                      <xsl:for-each select="response/reference/@value">
                        <a href="{.}.html">
                          <xsl:value-of select="."/>
                        </a>
                      </xsl:for-each>
                    </td>
                    <td>
                      <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
                    </td>
                  </tr>
                </xsl:for-each>
              </tbody>
            </table>
            </div>
          </xsl:for-each>
          <xsl:if test="document">
            <br/>
            <br/>
            <h2>Documents</h2>
            <xsl:for-each select="rest/documentMailbox/@value">
              <xsl:value-of select="concat('Mailbox: ', .)"/>
            </xsl:for-each>
            <div class="table-wrapper">
              <table class="grid">
              <thead>
                <tr>
                  <th>Mode</th>
                  <th>Profile</th>
                  <th>Notes</th>
                </tr>
              </thead>
              <tbody>
                <xsl:for-each select="document">
                  <tr>
                    <td>
                      <xsl:value-of select="mode/@value"/>
                    </td>
                    <td>
                      <xsl:for-each select="profile/@value">
                        <a href="{.}.html">
                          <xsl:value-of select="."/>
                        </a>
                      </xsl:for-each>
                    </td>
                    <td>
                      <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
                    </td>
                  </tr>
                </xsl:for-each>
              </tbody>
            </table>
            </div>
          </xsl:if>
        </div>
      </text>
      <xsl:copy-of select="node()[not(self::text or self::id)]"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template name="doParams" as="element(xhtml:div)">
    <xsl:variable name="doCapabilityStatement" as="xs:boolean" select="exists(*[self::searchParam or self::parameter]/extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value)"/>
    <div class="table-wrapper">
      <table class="list">
      <thead>
        <tr>
          <th>Parameter</th>
          <xsl:if test="$doCapabilityStatement">
            <th>Conformance</th>
          </xsl:if>
          <th>Type</th>
          <th>Definition &amp; Chaining</th>
        </tr>
      </thead>
      <tbody>
        <xsl:for-each select="searchParam|parameter">
          <tr>
            <th>
              <xsl:choose>
                <xsl:when test="definition/@value">
<!-- Search parameter URLs don't actually resolve right now
                 <a href="{definition/@value}">-->
                    <xsl:value-of select="name/@value"/>
<!--                  </a>-->
                </xsl:when>
                <xsl:otherwise>
                  <xsl:value-of select="@name"/>
                </xsl:otherwise>
              </xsl:choose>
            </th>
            <xsl:if test="$doCapabilityStatement">
              <td>
                <xsl:value-of select="extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value"/>
              </td>
            </xsl:if>
            <td>
              <xsl:value-of select="type/@value"/>
              <xsl:if test="target/@value">
                <xsl:value-of select="concat(' (', string-join(target/@value, ', '), ')')"/>
              </xsl:if>
            </td>
            <td>
              <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
              <xsl:if test="chain/@value">
                <xsl:value-of select="concat('Chaining: ', string-join(chain/@value, ', '))"/>
              </xsl:if>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
    </table>
    </div>
  </xsl:template>
  <xsl:template match="interaction">
    <xsl:param name="doCapabilityStatement" as="xs:boolean" required="yes"/>
    <tr>
      <th>
        <a name="{ancestor::resource/type/@value}-{code/@value}">
          <!-- To get around browser issue -->
          <xsl:text>&#xA0;</xsl:text>
        </a>
        <span>
          <xsl:value-of select="code/@value"/>
        </span>
      </th>
      <xsl:if test="$doCapabilityStatement">
        <td>
          <xsl:value-of select="extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value"/>
        </td>
      </xsl:if>
      <td>
        <xsl:if test="code/@value='transaction'">
          <p>
            <xsl:value-of select="concat('Modes: ', string-join(transactionMode/@value, ', '))"/>
          </p>
        </xsl:if>
        <xsl:copy-of select="fn:handleMarkdownLines(documentation/@value)"/>
      </td>
    </tr>
  </xsl:template>
  <xsl:template name="doCapabilityStatement" as="node()">
    <xsl:variable name="documentation" as="xs:string">
      <xsl:if test="normalize-space(documentation/@value)!=''">
        <xsl:copy-of select="string-join(fn:markDownToString(fn:handleMarkdown(documentation/@value)), '')"/>
      </xsl:if>
    </xsl:variable>
    <xsl:variable name="value" as="xs:string">
      <xsl:choose>
        <xsl:when test="extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']">
          <xsl:value-of select="extension[@url='http://hl7.org/fhir/StructureDefinition/conformance-expectation']/valueCode/@value"/>
        </xsl:when>
        <xsl:otherwise>Yes</xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:choose>
      <xsl:when test="normalize-space($documentation)=''">
        <xsl:value-of select="$value"/>
      </xsl:when>
      <xsl:otherwise>
        <a title="{$documentation}" href="#{ancestor::resource/type/@value}-{code/@value}">
          <xsl:value-of select="$value"/>
        </a>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <xsl:function name="fn:handleMarkdownLines" as="item()*">
    <xsl:param name="string" as="xs:string?"/>
    <xsl:variable name="lines" as="element(xhtml:lines)">
      <lines>
        <xsl:for-each select="tokenize($string, '&lt;br/&gt;')">
          <xsl:choose>
            <xsl:when test="starts-with(normalize-space(.), '*')">
              <bulletLine>
                <xsl:value-of select="substring-after(., '*')"/>
              </bulletLine>
            </xsl:when>
            <xsl:when test="starts-with(normalize-space(.), '#')">
              <numberLine>
                <xsl:value-of select="substring-after(., '#')"/>
              </numberLine>
            </xsl:when>
            <!-- Could support indent, but not going to bother for now -->
            <xsl:otherwise>
              <line>
                <xsl:copy-of select="."/>
              </line>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:for-each>
      </lines>
    </xsl:variable>
    <xsl:apply-templates mode="processLines" select="$lines/*[1]"/>
  </xsl:function>
  <xsl:function name="fn:markDownToString" as="xs:string+">
    <xsl:param name="markdown" as="node()+"/>
    <xsl:apply-templates mode="markDownToString" select="$markdown"/>
  </xsl:function>
  <xsl:template mode="markDownToString" match="text()">
    <xsl:value-of select="."/>
  </xsl:template>
  <xsl:template mode="markDownToString" match="*">
    <xsl:apply-templates mode="markDownToString" select="node()"/>
  </xsl:template>
  <xsl:template mode="markDownToString" match="xhtml:p">
    <xsl:apply-templates mode="markDownToString" select="node()"/>
    <xsl:text>&#x0a;</xsl:text>
  </xsl:template>
  <xsl:template mode="processLines" match="*" as="item()*">
    <xsl:variable name="position" select="position()"/>
    <xsl:variable name="lines" as="element(xhtml:lines)+">
      <xsl:variable name="sameLines" as="element()*">
        <xsl:apply-templates mode="sameLines" select="following-sibling::*[1]">
          <xsl:with-param name="name" select="local-name(.)"/>
        </xsl:apply-templates>
      </xsl:variable>
      <lines>
        <xsl:copy-of select="."/>
        <xsl:copy-of select="$sameLines"/>
      </lines>
      <xsl:apply-templates mode="processLines" select="following-sibling::*[$position + count($sameLines)+1]"/>
    </xsl:variable>
    <xsl:for-each select="$lines">
      <xsl:choose>
        <xsl:when test="xhtml:bulletLine">
          <ul>
            <xsl:for-each select="xhtml:bulletLine">
              <li>
                <xsl:copy-of select="fn:handleMarkdown(.)"/>
              </li>
            </xsl:for-each>
          </ul>
        </xsl:when>
        <xsl:when test="xhtml:numberLine">
          <ol>
            <xsl:for-each select="xhtml:numberLine">
              <li>
                <xsl:copy-of select="fn:handleMarkdown(.)"/>
              </li>
            </xsl:for-each>
          </ol>
        </xsl:when>
        <xsl:otherwise>
          <xsl:for-each select="*">
            <p>
              <xsl:copy-of select="fn:handleMarkdown(.)"/>
            </p>
          </xsl:for-each>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:for-each>
  </xsl:template>
  <xsl:template mode="sameLines" match="*" as="element()+">
    <xsl:param name="name" as="xs:string"/>
    <xsl:if test="local-name(.)=$name">
      <xsl:copy-of select="."/>
      <xsl:apply-templates mode="sameLines" select="following-sibling::*[1]">
        <xsl:with-param name="name" select="$name"/>
      </xsl:apply-templates>
    </xsl:if>
  </xsl:template>
  <xsl:variable name="BOLD" select="''''''''"/>
  <xsl:variable name="ITALIC" select="''''''"/>
  <xsl:variable name="H5" select="'====='"/>
  <xsl:variable name="H4" select="'===='"/>
  <xsl:variable name="H3" select="'==='"/>
  <xsl:variable name="H2" select="'=='"/>
  <xsl:variable name="H1" select="'='"/>
  <xsl:variable name="WEIRD" select="'zQQzzQQz'"/>
  <xsl:function name="fn:handleMarkdown" as="item()*">
    <xsl:param name="string" as="xs:string?"/>
    <xsl:choose>
      <xsl:when test="not($string)"/>
      <xsl:when test="fn:splitable($string, $BOLD)">
        <xsl:copy-of select="fn:split($string, $BOLD, 'b')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $ITALIC)">
        <xsl:copy-of select="fn:split($string, $ITALIC, 'i')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $H5)">
        <xsl:copy-of select="fn:split($string, $H5, 'h7')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $H4)">
        <xsl:copy-of select="fn:split($string, $H4, 'h6')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $H3)">
        <xsl:copy-of select="fn:split($string, $H3, 'h5')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $H2)">
        <xsl:copy-of select="fn:split($string, $H2, 'h4')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, $H1)">
        <xsl:copy-of select="fn:split($string, $H1, 'h3')"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, '[[Image:', ']]')">
        <xsl:variable name="parts" select="fn:separate($string, '[[Image:', ']]')"/>
        <xsl:copy-of select="fn:handleMarkdown($parts[1])"/>
        <xsl:variable name="imageParts" as="xs:string+" select="tokenize($parts[2], '\|')"/>
        <xsl:variable name="src" as="xs:string" select="$imageParts[1]"/>
        <xsl:variable name="alt" as="xs:string?" select="$imageParts[position()&gt;1][last()]"/>
        <img alt="{$alt}" src="{$src}"/>
        <xsl:copy-of select="fn:handleMarkdown($parts[3])"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, '[[', ']]')">
        <xsl:variable name="parts" as="xs:string+" select="fn:separate($string, '[[', ']]')"/>
        <xsl:copy-of select="fn:handleMarkdown($parts[1])"/>
        <xsl:variable name="linkParts" as="xs:string+" select="tokenize($parts[2], '\|')"/>
        <xsl:variable name="linkBase" as="xs:string" select="$linkParts[1]"/>
        <xsl:variable name="linkExt" as="xs:string?" select="if (ends-with($linkBase, '.html') or contains($linkBase, '#')) then '' else '.html'"/>
        <xsl:variable name="name" as="xs:string?" select="$linkParts[2]"/>
        <a href="{if(contains($linkBase, ':') or ends-with($linkBase, '.html') or $name) then '' else $fhirpath}{lower-case($linkBase)}{$linkExt}">
          <xsl:value-of select="if ($name) then $name else $linkBase"/>
        </a>
        <xsl:copy-of select="fn:handleMarkdown($parts[3])"/>
      </xsl:when>
      <xsl:when test="fn:splitable($string, '[', ']')">
        <xsl:variable name="parts" select="fn:separate($string, '[', ']')"/>
        <xsl:copy-of select="fn:handleMarkdown($parts[1])"/>
        <xsl:variable name="linkParts" as="xs:string+" select="tokenize($parts[2], ' ')"/>
        <xsl:variable name="linkBase" as="xs:string" select="$linkParts[1]"/>
        <xsl:variable name="name" as="xs:string?" select="string-join($linkParts[position()&gt;1], ' ')"/>
        <a href="{$linkBase}">
          <xsl:value-of select="if ($name) then $name else $linkBase"/>
        </a>
        <xsl:copy-of select="fn:handleMarkdown($parts[3])"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$string"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>
  <xsl:function name="fn:splitable" as="xs:boolean">
    <xsl:param name="string" as="xs:string"/>
    <xsl:param name="split" as="xs:string"/>
    <xsl:value-of select="fn:splitable($string, $split, $split)"/>
  </xsl:function>
  <xsl:function name="fn:splitable" as="xs:boolean">
    <xsl:param name="string" as="xs:string"/>
    <xsl:param name="start" as="xs:string"/>
    <xsl:param name="end" as="xs:string"/>
    <xsl:value-of select="matches($string, fn:regex($start, $end))"/>
  </xsl:function>
  <xsl:function name="fn:regex" as="xs:string">
    <xsl:param name="start" as="xs:string"/>
    <xsl:param name="end" as="xs:string"/>
    <xsl:value-of select="concat('(.*[^\\])?', replace($start, '([\[\]\\])', '\\$1'), '(.*[^\\])', replace($end, '([\[\]\\])', '\\$1'), '(.*)')"/>
  </xsl:function>
  <xsl:function name="fn:separate" as="xs:string+">
    <xsl:param name="string" as="xs:string"/>
    <xsl:param name="start" as="xs:string"/>
    <xsl:param name="end" as="xs:string"/>
    <xsl:variable name="fixedString" as="xs:string" select="replace($string, fn:regex($start, $end), concat('$1', $WEIRD, '$3'))"/>
    <xsl:variable name="before" as="xs:string" select="substring-before($fixedString, $WEIRD)"/>
    <xsl:variable name="after" as="xs:string" select="substring-after($fixedString, $WEIRD)"/>
    <xsl:variable name="middle" as="xs:string" select="substring-before(substring-after($string, concat($before, $start)), concat($end, $after))"/>
    <xsl:copy-of select="$before"/>
    <xsl:copy-of select="$middle"/>
    <xsl:copy-of select="$after"/>
  </xsl:function>
  <xsl:function name="fn:split" as="item()+">
    <xsl:param name="string" as="xs:string"/>
    <xsl:param name="split" as="xs:string"/>
    <xsl:param name="element" as="xs:string"/>
    <xsl:variable name="parts" as="xs:string+" select="fn:separate($string, $split, $split)"/>
    <xsl:copy-of select="fn:handleMarkdown($parts[1])"/>
    <xsl:element name="{$element}" namespace="http://www.w3.org/1999/xhtml">
      <xsl:copy-of select="fn:handleMarkdown($parts[2])"/>
    </xsl:element>
    <xsl:copy-of select="fn:handleMarkdown($parts[3])"/>
  </xsl:function>
</xsl:stylesheet>
