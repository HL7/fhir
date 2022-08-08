<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
    xmlns:mif2="urn:hl7-org:v3/mif2"
    exclude-result-prefixes="#all"
    version="2.0">
    <xd:doc scope="stylesheet">
        <xd:desc>
            <xd:p><xd:b>Created on:</xd:b> Jan 16, 2017</xd:p>
            <xd:p><xd:b>Author:</xd:b> ahenket</xd:p>
            <xd:p></xd:p>
        </xd:desc>
    </xd:doc>
    
    <xsl:output indent="yes"/>
    
    <xsl:variable name="lang" select="'nl'"/>
    
    <xsl:variable name="dir" select="string-join(tokenize(document-uri(.),'/')[position() != last()],'/')"/>
    <xsl:variable name="nl" select="doc(concat($dir,'/source_nl.xml'))/*" as="element()*"/>
    
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="mif2:conceptDomain/mif2:annotations/mif2:documentation/mif2:definition/mif2:text">
        <xsl:variable name="snm" select="ancestor::mif2:conceptDomain/@name"/>
        
        <xsl:copy-of select="." copy-namespaces="no"/>
        <xsl:copy-of select="$nl//mif2:conceptDomain[@name = $snm]/mif2:annotations/mif2:documentation/mif2:definition/mif2:text[@lang = $lang]" copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="mif2:codeSystem/mif2:annotations/mif2:documentation/mif2:description/mif2:text">
        <xsl:variable name="sid" select="ancestor::mif2:codeSystem/@codeSystemId"/>
        <xsl:variable name="snm" select="ancestor::mif2:codeSystem/@name"/>
        
        <xsl:copy-of select="." copy-namespaces="no"/>
        <xsl:copy-of select="$nl//mif2:codeSystem[@codeSystemId = $sid][@name = $snm]/mif2:annotations/mif2:documentation/mif2:description/mif2:text[@lang = $lang]" copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="mif2:codeSystem/mif2:releasedVersion/mif2:concept/mif2:annotations/mif2:documentation/mif2:definition/mif2:text">
        <xsl:variable name="sid" select="ancestor::mif2:codeSystem/@codeSystemId"/>
        <xsl:variable name="snm" select="ancestor::mif2:codeSystem/@name"/>
        <xsl:variable name="code" select="ancestor::mif2:concept[1]/mif2:code/@code"/>
        
        <xsl:copy-of select="." copy-namespaces="no"/>
        <xsl:copy-of select="$nl//mif2:codeSystem[@codeSystemId = $sid][@name = $snm]/mif2:releasedVersion/mif2:concept[mif2:code/@code = $code]/mif2:annotations/mif2:documentation/mif2:definition/mif2:text[@lang = $lang]" copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="mif2:codeSystem/mif2:releasedVersion/mif2:concept/mif2:printName">
        <xsl:variable name="sid" select="ancestor::mif2:codeSystem/@codeSystemId"/>
        <xsl:variable name="snm" select="ancestor::mif2:codeSystem/@name"/>
        <xsl:variable name="code" select="ancestor::mif2:concept[1]/mif2:code/@code"/>
        
        <xsl:copy-of select="." copy-namespaces="no"/>
        <xsl:copy-of select="$nl//mif2:codeSystem[@codeSystemId = $sid][@name = $snm]/mif2:releasedVersion/mif2:concept[mif2:code/@code = $code]/mif2:printName[@language = $lang]" copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="mif2:valueSet/mif2:annotations/mif2:documentation/mif2:description/mif2:text">
        <xsl:variable name="sid" select="ancestor::mif2:valueSet/@id"/>
        <xsl:variable name="snm" select="ancestor::mif2:valueSet/@name"/>
        
        <xsl:copy-of select="." copy-namespaces="no"/>
        <xsl:copy-of select="$nl//mif2:valueSet[@id = $sid][@name = $snm]/mif2:annotations/mif2:documentation/mif2:description/mif2:text[@lang = $lang]" copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="text() | processing-instruction() | comment()">
        <xsl:copy copy-namespaces="no"/>
    </xsl:template>
    
    <xsl:template match="*">
        <xsl:copy copy-namespaces="no">
            <xsl:copy-of select="@*" copy-namespaces="no"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
    
</xsl:stylesheet>