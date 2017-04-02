<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:html="http://www.w3.org/1999/xhtml" xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://hl7.org/fhir">
  <xsl:output method="xml" encoding="utf-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="columns" select="3"/>
  <xsl:template match="f:ImplementationGuide">
    <div>
      <div class="itoc">
        <p>Artifact Packages</p>
        <xsl:for-each select="f:package">
          <p class="link">-
            <a href="#{position()}">
              <xsl:value-of select="f:name/@value"/>
            </a>
          </p>
        </xsl:for-each>
      </div>
      <p>This page provides a list of the FHIR artifacts defined as part of this implementation guide.</p>
      <xsl:for-each select="f:package">
        <h2 class="self-link-parent">
          <a name="{position()}">
            <xsl:value-of select="' '"/>
          </a>
          <xsl:value-of select="concat(f:name/@value, ' ')"/> 
          <a href="#{position()}" title="link to here" class="self-link">
            <img src="assets/images/link.svg" width="20" class="self-link" height="20"/>
          </a>
        </h2>
        <xsl:apply-templates select="."/>
      </xsl:for-each>
    </div>
  </xsl:template>
  <xsl:template match="f:package">
    <p>
      <xsl:value-of select="f:description/@value"/>
    </p>
    <p>
      <table>
        <tbody>
          <xsl:for-each select="f:resource">
            <tr>
              <td style="column-width:30%">
                <xsl:choose>
                  <xsl:when test="f:sourceReference">
                    <xsl:variable name="type" select="substring-before(f:sourceReference/f:reference/@value, '/')"/>
                    <xsl:variable name="id" select="substring-after(f:sourceReference/f:reference/@value, '/')"/>
                    <xsl:variable name="href">
                      <xsl:choose>
                        <xsl:when test="$type='ValueSet' and not(f:example/@value='true' or f:purpose/@value='example')">
                          <xsl:value-of select="concat('valueset-', $id, '.html')"/>
                        </xsl:when>
                        <xsl:when test="starts-with($id, 'ext')">
                          <xsl:value-of select="concat('extension-', $id, '.html')"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="concat($id, '.html')"/>
                        </xsl:otherwise>
                      </xsl:choose>
                    </xsl:variable>
                    <a href="{$href}">
                      &#160;&#160;&#x2022;&#160;&#160;<xsl:value-of select="f:name/@value"/>
                    </a>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:variable name="href">
                      <xsl:choose>
                        <xsl:when test="starts-with(f:sourceUri/@value, 'ext-')">
                          <xsl:value-of select="concat('extension-', substring-before(sourceUri/@value, '.xml'), '.html')"/>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:value-of select="concat(substring-before(sourceUri/@value, '.xml'), '.html')"/>
                        </xsl:otherwise>
                      </xsl:choose>
                    </xsl:variable>
                    <a href="{$href}">
                      &#160;&#160;&#x2022;&#160;&#160;<xsl:value-of select="f:name/@value"/>
                    </a>
                  </xsl:otherwise>
                </xsl:choose>
              </td>
              <td>
                <xsl:value-of select="f:description/@value" disable-output-escaping="yes"/>
              </td>
            </tr>
          </xsl:for-each>
        </tbody>
      </table>
    </p>
  </xsl:template>
  <xsl:template match="f:package" mode="first">
    <tr>
      <xsl:apply-templates select=".|following-sibling::f:package[position() &lt; $columns]" mode="next"/>
      <xsl:if test="count(following-sibling::f:package) &lt; ($columns - 1)">
        <xsl:call-template name="emptycell">
          <xsl:with-param name="cells" select="$columns - 1 - count(following-sibling::f:package)"/>
        </xsl:call-template>
      </xsl:if>
    </tr>
    <tr colspan="3">
      <td>&#160;</td>
    </tr>
  </xsl:template>
  <xsl:template match="f:package" mode="next">
    <td style="column-width:30%">
      <a href="{translate(concat('#',name/@value), ' ', '_')}">
        <xsl:number/>. <xsl:value-of select="f:name/@value"/> &#160;&#160;&#160;&#9654;</a>
    </td>
  </xsl:template>
  <xsl:template name="emptycell">
    <xsl:param name="cells"/>
    <td/>
    <xsl:if test="$cells &gt; 1">
      <xsl:call-template name="emptycell">
        <xsl:with-param name="cells" select="$cells - 1"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>
</xsl:stylesheet>
