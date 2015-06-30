<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes"/>
	<xsl:template match="warnings">
    <table border="1">
      <tbody>
        <tr>
          <th/>
          <th>Warnings</th>
          <th>Information</th>
          <th>Original FMM</th>
        </tr>
        <xsl:for-each select="group">
          <tr>
            <td>
              <b>
                <a href="#{generate-id()}">
                  <xsl:value-of select="@name"/>
                </a>
              </b>
            </td>
            <td>
              <xsl:value-of select="count(descendant::message[@level='WARNING'])"/>
            </td>
            <td>
              <xsl:value-of select="count(descendant::message[@level='INFORMATION'])"/>
            </td>
            <td/>
          </tr>
          <xsl:for-each select="resource">
            <tr>
              <td>
                <a href="#{generate-id()}">
                  <xsl:value-of select="@id"/>
                </a>
              </td>
              <td>
                <xsl:value-of select="count(descendant::message[@level='WARNING'])"/>
              </td>
              <td>
                <xsl:value-of select="count(descendant::message[@level='INFORMATION'])"/>
              </td>
              <td style="color:red">
                <b>
                  <xsl:value-of select="if (@basefmm) then @basefmm else if (@fmm &gt; 0) then @fmm else ''"/>
                </b>
              </td>
            </tr>
          </xsl:for-each>
        </xsl:for-each>
      </tbody>
    </table>
    <div>
      <xsl:for-each select="group">
        <a name="{generate-id()}"/>
        <h2>
          <xsl:value-of select="@name"/>
        </h2>
        <xsl:apply-templates select="*"/>
      </xsl:for-each>
    </div>
	</xsl:template>
	<xsl:template match="resource">
    <a name="{generate-id()}"/>
    <h3>
      <xsl:choose>
        <xsl:when test="@type='Resource'">
          <a href="{@id}.html">
            <xsl:value-of select="concat(@type, ' ', @id)"/>
          </a>
        </xsl:when>
        <xsl:when test="@type='Data Type'">
          <a href="datatypes.html#{@id}">
            <xsl:value-of select="concat(@type, ' ', @id)"/>
          </a>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="concat(@type, ' ', @id)"/>
        </xsl:otherwise>
      </xsl:choose>
    </h3>
    <ul>
      <xsl:apply-templates select="*"/>
    </ul>
	</xsl:template>
	<xsl:template match="message">
    <li>
      <xsl:value-of select="@display"/>
    </li>
	</xsl:template>
</xsl:stylesheet>
