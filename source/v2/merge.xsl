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
            <xd:p><xd:b>Purpose:</xd:b> Takes official input file and merges that with a given language file. This is useful when the base file is updated and needs resyncing</xd:p>
            <xd:p></xd:p>
            <xd:p>Call: java -jar ../../tools/java/imports/Saxon-HE-9.5.1-5.jar -s:source.xml -xsl:merge.xsl -o:source_nl_merge.xml langfile=source_nl.xml</xd:p>
        </xd:desc>
    </xd:doc>
    
    <xsl:output indent="yes"/>
    <xsl:param name="langfile" required="yes"/>
    <xsl:variable name="nl" as="element(f:tables)" select="doc($langfile)/f:tables"/>
    
    <xsl:template match="/">
        <xsl:apply-templates/>
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
            <xsl:copy-of select="$nl/f:table[@id=$tid]/f:desc"/>
            <xsl:apply-templates select="node()">
                <xsl:with-param name="tid" select="$tid"/>
            </xsl:apply-templates>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="f:version">
        <xsl:param name="tid"/>
        <xsl:variable name="version" select="@version"/>
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:copy-of select="$nl/f:table[@id=$tid]/f:version[@version=$version]/f:desc"/>
            <xsl:apply-templates select="node()">
                <xsl:with-param name="tid" select="$tid"/>
                <xsl:with-param name="version" select="$version"/>
            </xsl:apply-templates>
        </xsl:copy>
    </xsl:template>
    
    <xsl:template match="f:item">
        <xsl:param name="tid"/>
        <xsl:param name="version"/>
        <xsl:variable name="code" select="@code"/>
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:copy-of select="$nl/f:table[@id=$tid]/f:version[@version=$version]/f:item[@code=$code]/f:desc"/>
            <xsl:apply-templates select="node()"/>
        </xsl:copy>
    </xsl:template>
    
    <!-- Simply copy everything that's not matched -->
    <xsl:template match="@*|node()" priority="-2">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>