<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns="http://www.w3.org/TR/REC-html40" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:f="http://hl7.org/fhir" xmlns:fn="http://hl7.org/xslt-functions" exclude-result-prefixes="f fn xs">
  <xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>
  <xsl:variable name="profiles" select="//f:Profile"/>
  <xsl:template match="/">
    <html>
      <head>
        <title>Resource spelling/grammar checking</title>
        <meta name="ProgId" content="Word.Document"/>
      </head>
      <body style="mso-ansi-language:EN-US">
        <xsl:for-each select="distinct-values(//f:Profile/f:publisher/@value)">
          <xsl:sort select="."/>
          <div>
            <h1 style="mso-no-proof:yes">
              <xsl:value-of select="if (contains(., '(')) then substring-before(substring-after(., '('), ')') else ."/>
            </h1>
            <xsl:for-each select="$profiles[f:publisher/@value=current()]">
              <xsl:sort select="f:name/@value"/>
              <xsl:apply-templates select="."/>
            </xsl:for-each>
          </div>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>
  <xsl:template match="f:Profile">
    <h2 style="mso-no-proof:yes">
      <xsl:value-of select="f:name/@value"/>
    </h2>
    <table lang="EN-US" style="mso-ansi-language:EN-US">
      <tbody>
        <xsl:for-each select="f:differential/f:element">
          <xsl:for-each select="f:path/@value">
            <tr>
              <td>
                <b style="mso-no-proof:yes">
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
          <xsl:for-each select="f:short/@value[not(contains(., '|'))]">
            <!-- Exclude short names that just enumerate codes -->
            <tr>
              <td>Short name</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:formal/@value">
            <tr>
              <td>Definition</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:comments/@value">
            <tr>
              <td>Comments</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:requirements/@value">
            <tr>
              <td>Requirements</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:synonym/@value">
            <tr>
              <td>Synonym</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:constraint">
            <xsl:for-each select="f:name/@value">
              <tr>
                <td>Constraint Name</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
            <xsl:for-each select="f:human/@value">
              <tr>
                <td>Constraint Text</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
          </xsl:for-each>
          <xsl:for-each select="f:binding">
            <xsl:for-each select="f:name/@value">
              <tr>
                <td>Binding Name</td>
                <td>
                  <span style="mso-no-proof:yes">
                    <xsl:value-of select="."/>
                  </span>
                  <xsl:value-of select="concat(' (', substring(.,1,1), replace(substring(.,2),'(\p{Lu})', concat(' ', '$1')), ')')"/>
                </td>
              </tr>
            </xsl:for-each>
            <xsl:for-each select="f:description/@value">
              <tr>
                <td>Binding Description</td>
                <td>
                  <xsl:value-of select="."/>
                </td>
              </tr>
            </xsl:for-each>
          </xsl:for-each>
        </xsl:for-each>
        <xsl:for-each select="//f:Conformance/f:rest/f:resource[f:type/@value=current()/f:name/@value]/f:searchParam">
          <xsl:for-each select="f:name/@value">
            <tr>
              <td style="mso-no-proof:yes">Search Param Name</td>
              <td>
                <span style="mso-no-proof:yes">
                  <xsl:value-of select="."/>
                </span>
                <xsl:value-of select="concat(' (', substring(.,1,1), replace(substring(.,2),'(\p{Lu})', concat(' ', '$1')), ')')"/>
              </td>
            </tr>
          </xsl:for-each>
          <xsl:for-each select="f:documentation/@value">
            <tr>
              <td style="mso-no-proof:yes">Search Param Description</td>
              <td>
                <xsl:value-of select="."/>
              </td>
            </tr>
          </xsl:for-each>
        </xsl:for-each>
      </tbody>
    </table>
  </xsl:template>
</xsl:stylesheet>
