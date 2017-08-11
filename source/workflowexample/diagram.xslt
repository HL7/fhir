<?xml version="1.0" standalone="no"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text"/>
	<xsl:template match="/">
<xsl:text>@startuml</xsl:text>
!include fhirskin.iuml
<xsl:apply-templates select="/WorkflowExample/process/title"/>
<xsl:text>&#13;&#10;</xsl:text>
<xsl:apply-templates select="/WorkflowExample/actor"/>
<xsl:text>&#13;&#10;</xsl:text>
<xsl:text>&#13;&#10;</xsl:text>
<xsl:apply-templates select="/WorkflowExample/process"/>

@enduml
</xsl:template>



<xsl:template match="/WorkflowExample/process">
<xsl:apply-templates select="./step"/>
</xsl:template>




<xsl:template match="/WorkflowExample/process/title">
title <xsl:value-of select="./@value"/>
</xsl:template>



<xsl:template match="step">
<xsl:apply-templates select="operation"/>
<xsl:apply-templates select="process"/>
<xsl:apply-templates select="pause"/>
<xsl:apply-templates select="alternative"/>
</xsl:template>


<xsl:template match="operation">
<xsl:value-of select="initiator/@value"/> <xsl:if test="dotted/@value='true'">-</xsl:if>-<xsl:text disable-output-escaping="yes">&gt; </xsl:text> <xsl:value-of select="receiver/@value"/> : <xsl:value-of select="name/@value"/>\n<xsl:apply-templates select="instance"/>
<xsl:text>&#13;&#10;</xsl:text>

<!--
<xsl:variable name="areThereInstances" select="boolean(./instance)"/>
<xsl:if test="$areThereInstances='true'">note right
</xsl:if> 
<xsl:apply-templates select="./instance"/><xsl:if test="$areThereInstances='true'">end note
</xsl:if>
-->

</xsl:template>


<xsl:template match="step/process">
group <xsl:value-of select="title/@value"/><xsl:text>&#13;&#10;</xsl:text>
<xsl:apply-templates select="./step"/>end</xsl:template>


<xsl:template match="option/process">
group <xsl:value-of select="title/@value"/><xsl:text>&#13;&#10;</xsl:text>
<xsl:apply-templates select="./step"/>end</xsl:template>


<xsl:template match="step/alternative">
'<xsl:value-of select="name/@value"/> 
<xsl:apply-templates select="option"/>

<xsl:text>&#13;&#10;</xsl:text>
<xsl:apply-templates select="./step"/>end
</xsl:template>

<!--
<xsl:template match="alternative/option">
<xsl:value-of select="description/@value"/>
<xsl:apply-templates select="./step"/>
</xsl:template>
-->


<xsl:template match="alternative/option"> 
<xsl:choose>
<xsl:when test="position() &lt; 2"> 
alt </xsl:when>
<xsl:otherwise> 
else </xsl:otherwise>
</xsl:choose>

<xsl:value-of select="description/@value"/>
<xsl:text>&#13;&#10;</xsl:text>

<xsl:apply-templates select="./step"/>

<!-- was like this before:
<xsl:apply-templates select="operation"/>
<xsl:apply-templates select="process"/>
<xsl:apply-templates select="pause"/>
<xsl:apply-templates select="alternative"/>
-->


</xsl:template>

<xsl:template match="actor">
<xsl:variable name="actorType" select="type/@value"/>
<xsl:if test="$actorType='person'">
actor</xsl:if> <xsl:if test="$actorType='entity'">
participant</xsl:if> 
<xsl:text> "</xsl:text><xsl:value-of select="name/@value"/>" as <xsl:apply-templates select="actorId/@value"/>

</xsl:template>



<xsl:template match="step/pause">
...
</xsl:template>







<xsl:template  match="instance">
<xsl:variable name="iid" select="instanceId/@value"/>

<xsl:text> [[#I</xsl:text><xsl:value-of select="$iid"/> 
<xsl:text> </xsl:text>
<xsl:value-of select="/WorkflowExample/instance[./resourceId/@value=$iid]/name/@value"/> 
<xsl:text>]] </xsl:text><xsl:text>\n</xsl:text>
</xsl:template>


</xsl:stylesheet>

