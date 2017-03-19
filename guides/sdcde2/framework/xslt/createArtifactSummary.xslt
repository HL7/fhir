<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" xpath-default-namespace="http://hl7.org/fhir">
  <xsl:output method="xml" encoding="utf-8" indent="yes" omit-xml-declaration="yes"/>
  <xsl:param name="columns" select="3"/>
  <xsl:template match="ImplementationGuide">
    <div>
      <table width="100%">
        <xsl:variable name="guide" select="." as="element()"/>
        <xsl:variable name="rows" select="if (count(package) mod 3 = 0) then count(package) idiv 3 else count(package) idiv 3 + 1"/>
        <xsl:for-each select="for $row in (0 to (count(package) idiv 3) + 1) return $row">
          <xsl:variable name="row" select="current()"/>
          <xsl:if test="$guide/package[$row * 3 + 1]">
            <tr>
              <xsl:for-each select="for $col in (0 to 2) return $col">
                <xsl:variable name="col" select="current()"/>
                <td column-width="30%">
                  <xsl:for-each select="$guide/package[$row + $col*$rows + 1]">
                    <a href="#{translate(name/@value, ' ', '_')}">
                      <xsl:value-of select="concat(count(preceding-sibling::package) + 1, '. ', name/@value, '&#160;&#160;&#160;&#9654;')"/>
                    </a>
                  </xsl:for-each>
                </td>
              </xsl:for-each>
            </tr>
            <tr colspan="3">
              <td>&#160;</td>
            </tr>
          </xsl:if>
        </xsl:for-each>
      </table>
      <p>This page provides a list of all of the profiles, extensions and value sets defined as part of the implementation guide.</p>
      <xsl:for-each select="package">
        <h2>
          <span class="mysectioncount">
            <a name="{translate(name/@value, ' ', '_')}">
              <xsl:value-of select="position()"/>.&#160;&#160;<xsl:value-of select="name/@value"/>
            </a>
          </span>
        </h2>
        <xsl:apply-templates select="."/>
      </xsl:for-each>
    </div>
  </xsl:template>
  <xsl:template match="package">
    <p>
      <xsl:value-of select="description/@value"/>
    </p>
    <p>
      <table>
        <tbody>
          <xsl:for-each select="resource">
            <tr>
              <td style="column-width:30%">
                <xsl:choose>
                  <xsl:when test="sourceReference">
                    <xsl:variable name="type" select="substring-before(sourceReference/reference/@value, '/')"/>
                    <xsl:variable name="id" select="substring-after(sourceReference/reference/@value, '/')"/>
                    <a href="{if ($type='ValueSet') then 'valueset-' else if (starts-with($id, 'ext')) then 'extension-' else ''}{$id}.html">
                      &#160;&#160;&#x2022;&#160;&#160;<xsl:value-of select="name/@value"/>
                    </a>
                  </xsl:when>
                  <xsl:otherwise>
                    <a href="{if(starts-with(sourceUri/@value, 'ext-')) then 'extension-' else ''}{substring-before(sourceUri/@value, '.xml')}.html">
                      &#160;&#160;&#x2022;&#160;&#160;<xsl:value-of select="name/@value"/>
                    </a>
                  </xsl:otherwise>
                </xsl:choose>
              </td>
              <td>
                <xsl:value-of select="description/@value" disable-output-escaping="yes"/>
              </td>
            </tr>
          </xsl:for-each>
        </tbody>
      </table>
    </p>
  </xsl:template>
  <xsl:template match="package" mode="first">
    <tr>
      <xsl:apply-templates select=".|following-sibling::package[position() &lt; $columns]" mode="next"/>
      <xsl:if test="count(following-sibling::package) &lt; ($columns - 1)">
        <xsl:call-template name="emptycell">
          <xsl:with-param name="cells" select="$columns - 1 - count(following-sibling::package)"/>
        </xsl:call-template>
      </xsl:if>
    </tr>
    <tr colspan="3">
      <td>&#160;</td>
    </tr>
  </xsl:template>
  <xsl:template match="package" mode="next">
    <td style="column-width:30%">
      <a href="{translate(concat('#',name/@value), ' ', '_')}">
        <xsl:number/>. <xsl:value-of select="name/@value"/> &#160;&#160;&#160;&#9654;</a>
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
