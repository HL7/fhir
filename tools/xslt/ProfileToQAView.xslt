<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns="http://www.w3.org/TR/REC-html40" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xpath-default-namespace="http://hl7.org/fhir" exclude-result-prefixes="xs">
  <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
  <xsl:variable name="resources" as="element()+">
    <xsl:for-each select="/Bundle/entry/fullUrl/@value">
      <xsl:if test="not(contains(., '/daf-') or contains(., '/cqif-') or contains(., '/cqm-') or contains(., '/qicore-') or contains(., '/sdc-') or contains(., '/uslab-'))">
        <xsl:copy-of select="ancestor::entry/resource/*"/>
      </xsl:if>
    </xsl:for-each>
  </xsl:variable>
  <xsl:template match="/">
    <html>
      <head>
        <title>Resource spelling/grammar checking</title>
        <meta name="ProgId" content="Word.Document"/>
      </head>
      <body style="mso-ansi-language:EN-US">
        <xsl:for-each select="distinct-values(/Bundle/entry/resource/*/publisher/@value)">
          <xsl:sort select="."/>
          <div>
            <h1 style="mso-no-prooyes">
              <xsl:value-of select="if (contains(., '(')) then substring-before(substring-after(., '('), ')') else ."/>
            </h1>
            <xsl:for-each select="$resources[publisher/@value=current()][not(self::SearchParameter)]">
              <xsl:sort select="name/@value"/>
              <xsl:apply-templates select="."/>
            </xsl:for-each>
            <xsl:if test="$resources[self::SearchParameter]">
              <h2 style="mso-no-prooyes">Search Parameters</h2>
              <table lang="EN-US" style="mso-ansi-language:EN-US">
                <tbody>
                  <xsl:for-each select="$resources[publisher/@value=current()][self::SearchParameter]">
                    <xsl:sort select="name/@value"/>
                    <xsl:apply-templates select="."/>
                  </xsl:for-each>
                </tbody>
              </table>
            </xsl:if>
          </div>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>
  <xsl:template match="StructureDefinition">
    <h2 style="mso-no-prooyes">
      <xsl:value-of select="url/@value"/>
    </h2>
    <xsl:variable name="isExtension" as="xs:boolean" select="constrainedType/@value='Extension'"/>
    <table lang="EN-US" style="mso-ansi-language:EN-US">
      <tbody>
        <xsl:if test="$isExtension">
          <xsl:for-each select="name/@value">
            <tr>
              <td>
                <b style="mso-no-prooyes">Name</b>
              </td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
        </xsl:if>
        <xsl:for-each select="differential/element">
          <xsl:for-each select="path/@value">
            <tr>
              <td>
                <b style="mso-no-prooyes">
                  <xsl:value-of select="."/>
                </b>
              </td>
              <td>
                <!-- Last node in path, split into words -->
                <xsl:variable name="name" select="if (contains(., '.')) then tokenize(., '.')[last()] else ."/>
                <xsl:value-of select="concat(substring($name,1,1), replace(substring($name,2),'(\p{Lu})', concat(' ', '$1')))"/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:if test="$isExtension">
            <xsl:for-each select="name/@value">
              <tr>
                <td>Name</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
            <xsl:for-each select="display/@value">
              <tr>
                <td>Label</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
          </xsl:if>
          <xsl:for-each select="short/@value[not(contains(., '|'))]">
            <!-- Exclude short names that just enumerate codes -->
            <tr>
              <td>Short name</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="definition/@value">
            <tr>
              <td>Definition</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="comments/@value">
            <tr>
              <td>Comments</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="requirements/@value">
            <tr>
              <td>Requirements</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="alias/@value">
            <tr>
              <td>Synonym</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="meaningWhenMissing/@value">
            <tr>
              <td>Meaning when missing</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="constraint">
            <xsl:for-each select="requirements/@value">
              <tr>
                <td>Constraint Requirements</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
            <xsl:for-each select="human/@value">
              <tr>
                <td>Constraint Text</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
          </xsl:for-each>
          <xsl:for-each select="slicing/description">
            <tr>
              <td>Slicing description</td>
              <td>
                <span style="mso-no-prooyes">
                  <xsl:value-of select="."/>
                </span>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="binding/description/@value">
            <tr>
              <td>Binding Description</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
        </xsl:for-each>
      </tbody>
    </table>
  </xsl:template>
  <xsl:template match="SearchParameter">
    <tr>
      <td>
        <xsl:value-of select="url/@value"/>
      </td>
      <td>
        <b>
          <xsl:for-each select="name/@value">
            <span style="mso-no-prooyes">
              <xsl:value-of select="."/>
            </span>
            <xsl:value-of select="concat(' (', substring(.,1,1), replace(substring(.,2),'(\p{Lu})', concat(' ', '$1')), '): ')"/>
          </xsl:for-each>
        </b>
        <xsl:for-each select="description/@value">
          <xsl:value-of select="."/>
        </xsl:for-each>
      </td>
    </tr>
  </xsl:template>
  <xsl:template match="OperationDefinition">
    <h2 style="mso-no-prooyes">
      <xsl:value-of select="concat('Operation: ', name/@value)"/>
    </h2>
    <table lang="EN-US" style="mso-ansi-language:EN-US">
      <tbody>
        <xsl:for-each select="name/@value">
          <tr>
            <td style="mso-no-prooyes">Name</td>
            <td>
              <span style="mso-no-prooyes">
                <xsl:value-of select="."/>
              </span>
              <xsl:value-of select="concat(' (', substring(.,1,1), replace(substring(.,2),'(\p{Lu})', concat(' ', '$1')), ')')"/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="description/@value">
          <tr>
            <td style="mso-no-prooyes">Description</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="requirements/@value">
          <tr>
            <td style="mso-no-prooyes">Requirements</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="notes/@value">
          <tr>
            <td style="mso-no-prooyes">Notes</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="parameter">
          <tr>
            <td style="mso-no-prooyes">
              <xsl:value-of select="concat('Parameter ', name/@value)"/>
            </td>
            <td>
              <xsl:value-of select="documentation/@value"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
    </table>
  </xsl:template>
  <xsl:template match="ValueSet">
    <h2 style="mso-no-prooyes">
      <xsl:value-of select="concat('ValueSet: ', name/@value)"/>
    </h2>
    <table lang="EN-US" style="mso-ansi-language:EN-US">
      <tbody>
        <xsl:for-each select="name/@value">
          <tr>
            <td style="mso-no-prooyes">Name</td>
            <td>
              <span style="mso-no-prooyes">
                <xsl:value-of select="."/>
              </span>
              <xsl:value-of select="concat(' (', substring(.,1,1), replace(substring(.,2),'(\p{Lu})', concat(' ', '$1')), ')')"/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="description/@value">
          <tr>
            <td style="mso-no-prooyes">Description</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="requirements/@value">
          <tr>
            <td style="mso-no-prooyes">Requirements</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="copyright/@value">
          <tr>
            <td style="mso-no-prooyes">Copyright</td>
            <td>
              <xsl:value-of select="."/>
            </td>
          </tr>
        </xsl:for-each>
        <xsl:for-each select="codeSystem/descendant::concept">
          <tr>
            <td style="mso-no-prooyes">
              <xsl:if test="position()=1">Content</xsl:if>
            </td>
            <td>
              <b>
                <xsl:value-of select="concat(display/@value, ': ')"/>
              </b>
              <xsl:value-of select="definition/@value"/>
            </td>
          </tr>
        </xsl:for-each>
      </tbody>
    </table>
  </xsl:template>
  <xsl:template match="Conformance"/>
  <xsl:template match="*">
    <xsl:message terminate="yes" select="concat('Unexpected resource: ', local-name(.))"/>
  </xsl:template>
</xsl:stylesheet>
