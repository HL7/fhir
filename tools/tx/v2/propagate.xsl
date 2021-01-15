<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
    xmlns:f="http://hl7.org/fhir/dev"
    exclude-result-prefixes="xs xd f"
    version="2.0">
    <xd:doc scope="stylesheet">
        <xd:desc>
            <xd:p><xd:b>Created on:</xd:b> Oct 6, 2015</xd:p>
            <xd:p><xd:b>Author:</xd:b> ahenket</xd:p>
            <xd:p><xd:b>Purpose:</xd:b> Takes language input file and propagates translations done in one version of a table to the other versions. For versions only when desc and optionally comments match. For items only when code+desc match.</xd:p>
            <xd:p></xd:p>
            <xd:p>Call: java -jar ../../tools/java/imports/Saxon-HE-9.5.1-5.jar -s:source_nl.xml -xsl:propagate.xsl -o:source_nl_prop.xml</xd:p>
            <xd:p>Check missing item translations:</xd:p>
            <xd:p>//*:table[not(@id=('0070','0033','0133','0141','0147','0153','0203','0258','0281','0317','0339','0348','0349','0350','0351'))]/*:version[not(contains(@version,' '))][*:item[not(*:desc)]][last()]/*:item[not(*:desc)]</xd:p>
        </xd:desc>
    </xd:doc>
    
    <xsl:output indent="yes"/>
    
    <xsl:variable name="nl" as="element(f:tables)" select="/f:tables"/>
    
    <xsl:template match="/">
        <xsl:apply-templates/>
        <xsl:variable name="total" select="count(//*:table)"/>
        <xsl:variable name="fullyTranslated" select="count(//*:table[not(*:version[not(contains(@version,' '))]/*:item[not(*:desc)])])"/>
        <xsl:variable name="partiallyTranslated" select="count(//*:table[*:version[not(contains(@version,' '))]/*:item[*:desc]][*:version[not(contains(@version,' '))]/*:item[not(*:desc)]])"/>
        <xsl:variable name="missingTitles" select="count(//*:table[*:version[not(contains(@version,' '))][not(*:desc)]][*:version[not(contains(@version,' '))]/*:item[*:desc]])"/>
        
        <xsl:message>Tables: <xsl:value-of select="$total"/></xsl:message>
        <xsl:message>   Fully translated tables         : <xsl:value-of select="$fullyTranslated"/></xsl:message>
        <xsl:message>   Partially translated tables     : <xsl:value-of select="$partiallyTranslated"/></xsl:message>
        <xsl:message>   Untranslated tables             : <xsl:value-of select="$total - $fullyTranslated - $partiallyTranslated"/></xsl:message>
        <xsl:message>   Translated tables missing title : <xsl:value-of select="$missingTitles"/></xsl:message>
        <xsl:message>   Percentage done                 : <xsl:value-of select="round(($fullyTranslated div $total) * 100)"/>%</xsl:message>
    </xsl:template>
    
    <xsl:template match="f:tables">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="f:table">
        <xsl:variable name="tid" select="@id"/>
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="node()"/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="f:version">
        <xsl:variable name="desc" select="lower-case(@desc)"/>
        <xsl:variable name="comment" select="lower-case(@comment)"/>
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="comment()"/>
            <xsl:copy-of select="f:desc"/>
            <xsl:if test="not(f:desc)">
                <xsl:choose>
                    <xsl:when test=".[@comments]">
                        <xsl:copy-of select="(ancestor::f:table/f:version[lower-case(@desc)=$desc][lower-case(@comments)=$comment]/f:desc)[last()]"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:copy-of select="(ancestor::f:table/f:version[lower-case(@desc)=$desc]/f:desc)[last()]"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:if>
            <xsl:apply-templates select="* except f:desc"/>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="f:item">
        <xsl:param name="version"/>
        <xsl:variable name="code" select="lower-case(@code)"/>
        <xsl:variable name="desc" select="lower-case(@desc)"/>
        <xsl:variable name="comment" select="@comment"/>
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates select="comment()"/>
            <xsl:choose>
                <xsl:when test="f:desc">
                    <xsl:copy-of select="f:desc"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:choose>
                        <xsl:when test="@comments">
                            <xsl:copy-of select="(ancestor::f:table/f:version/f:item[lower-case(@code) = $code][lower-case(@desc) = $desc][@comments]/f:desc)[last()]"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:variable name="t" select="(ancestor::f:table/f:version/f:item[lower-case(@code) = $code][lower-case(@desc) = $desc]/f:desc)[last()]"/>
                            <xsl:if test="$t">
                                <desc xmlns="http://hl7.org/fhir/dev">
                                    <xsl:copy-of select="$t/(@* except @comments)"/>
                                    <xsl:copy-of select="$t/*"/>
                                </desc>
                            </xsl:if>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates select="* except f:desc"/>
        </xsl:copy>
    </xsl:template>
    
    <!-- Simply copy everything that's not matched -->
    <xsl:template match="@*|node()" priority="-2">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>