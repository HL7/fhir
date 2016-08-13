<cfsilent>
<!--- This page uses CFSAVECONTENT to "fill in the blanks" the template
  --- included on the last line of this file. --->

<!--- Initialize Navigation variables so that we can call to relative paths correctly --->
<cfimport prefix="nav" taglib="../customtags/navigation">
<cfinclude template="../config_sitemap.cfm">
<nav:getcurrentpage>
<!--- Import tag library to allow for gray box and other elements --->
<cfimport prefix="layout" taglib="../customtags/layouttags">

<!--- Page-specific content starts here --->
<!------------------------------------------------------------------------>
<!--- START PAGE --------------------------------------------------------->
<!------------------------------------------------------------------------>

<!--- Some UI design ideas --->
<!---http://www.gao.gov/search--->
<!---http://dartmouth.summon.serialssolutions.com/ (results mostly)--->
<!---http://solr.pl/en/2010/10/18/solr-and-autocomplete-part-1/--->
<!---http://www.mattweber.org/2009/05/02/solr-autosuggest-with-termscomponent-and-jquery/--->

<!--- This shows up in the <TITLE> tag --->
<cfset Template.PageTitle = "Search Health Level Seven">

<!--- Additional tags, if any, to add to the <HEAD> tag, in addition to the ones included in the template. --->
<cfsavecontent variable="Template.HTMLHEAD">
	<layout:loadScript script="/css/pagination.css">
	<style>
		span.highlightContext { font-weight: bold; color: #333333; }
		TABLE.searchResultCollection { background-color: #FFFFFF; }
		#searchResultCollection
		{
			style="margin:10px;
			float:left;
		}
		#searchResultCollection .unimportantText
		{
			color: #505050;
			font-size:7pt;
		}
		#searchResultCollection .footnote, #suggestedLinkCollection .footnote
		{
			color: #505050;
			font-size: 8pt;
			line-height: 8pt;
		}	
		#searchResultCollection .searchresulttitle
		{
			font-size: 12pt;
			font-weight: bold;
		}	
		#searchResultCollection .searchresultsnippet
		{
			display: block;
		    margin: 1px 0 0.5em 0;
			color: #6E6E6E;
			font-size: 9pt;
			line-height: 10pt;
		    background-color: #F0F0F0;
		    padding: 8px;
			width: 95%;
			height: 3.9em;
			overflow: hidden;
			text-decoration: none;
		}
		#searchResultCollection .searchresultsnippet:hover
		{
			text-decoration: none;
		}
		#currentRestrictions p
		{
			margin: 0;
			font: bold 9pt sans-serif;
		}
		#currentRestrictions ul
		{
			margin: 0;
			padding: 0;
		}
		#currentRestrictions ul li
		{
			margin: 2px 0;
			padding: 4px 4px 4px 8px;
			display: block;
			list-style-type: none;
			background-color: #F0F0F0;
			overflow: auto;
		}
		#currentRestrictions ul li a.restrictionCloseButton
		{
			display: block;
			float: right;
			padding: 2px;
			font: bold 8pt/9pt sans-serif;
			text-decoration: none;
			border: 1px solid #C0C0C0;
			width: 8pt;
			height: 9pt;
			text-align: center;
		}
		#suggestedLinkCollection
		{
			background-color: #FFF8E7;
			width: 95%;
			padding: 0 8px 1px 8px;
		}
	</style>
</cfsavecontent>

<!--- This shows up on the site's header, next to the HL7 logo. --->
<cfsavecontent variable="Template.Header_PageDescription">
</cfsavecontent>

<!--- This is the actual content for the page. The "meat" of the page goes here --->
<cfsavecontent variable="Template.PageContent">
	<div style="width:100%;">
		<div style="float:left; width: 49%;">
			<h1 style="display:inline;">Search</h1>
		</div>
		<cfif IsDefined("URL.criteria") And Len(Trim(URL.criteria))>
			<cfoutput>
			<div style="float:left; text-align:center; width: 49%;">
				<a href="search_tips.cfm?search_query_string=#URLEncodedFormat(CGI.query_string)#">Search Tips</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="search_contact.cfm?action=feedback&search_query_string=#URLEncodedFormat(CGI.query_string)#">Provide Feedback</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="search_contact.cfm?action=help&search_query_string=#URLEncodedFormat(CGI.query_string)#">Still can't find it?</a>
			</div>
			</cfoutput>
		</cfif>
		<div style="clear:both;"></div>
	</div>

	<cfinclude template="../includes/functions.cfm">
	
	<!---=====================================================================
	|                                                                        |
	|    CCCCC  RRRRRR   IIIIII  TTTTTT  EEEEEE  RRRRRR   IIIIII   AAAA      |
	|   CC      RR   RR    II      TT    EE      RR   RR    II    AA  AA     |
	|   CC      RRRRR      II      TT    EEEE    RRRRR      II    AAAAAA     |
	|   CC      RR  RR     II      TT    EE      RR  RR     II    AA  AA     |
	|    CCCCC  RR   RR  IIIIII    TT    EEEEEE  RR   RR  IIIIII  AA  AA     |
	|                                                                        |
	======================================================================--->
	<cfparam name="URL.criteria" default="">
	<cfparam name="URL.searchFacets" default="">
	<cfparam name="URL.MaxRows" default="10">
	<cfparam name="URL.StartRow" default="1">
	
	<cfif Len(Trim(URL.criteria))>
		<!---=====================================================
		|                                                        |
		|    SSSSS  EEEEEE   AAAA   RRRRRR    CCCCC  HH   HH     |
		|   SS      EE      AA  AA  RR   RR  CC      HH   HH     |
		|    SSSS   EEEE    AAAAAA  RRRRR    CC      HHHHHHH     |
		|       SS  EE      AA  AA  RR  RR   CC      HH   HH     |
		|   SSSSS   EEEEEE  AA  AA  RR   RR   CCCCC  HH   HH     |
		|                                                        |
		======================================================--->
		<cfinvoke component="HL7Search" method="search" returnVariable="searchReturnStruct">
			<cfinvokeargument name="criteria" value="#URL.criteria#">
			<cfinvokeargument name="searchFacets" value="#URL.searchFacets#">
			<cfinvokeargument name="startrow" value="#URL.startrow#">
			<cfinvokeargument name="maxrows" value="#URL.MaxRows#">
			<cfinvokeargument name="contextpassages" value="5">
		</cfinvoke>
		
		<!--- If we didn't get any results, try searching again using the Standard query parser instead of Dismax --->
		<cfif Not searchReturnStruct.query_result.RecordCount>
			<cftry>
				<cfinvoke component="HL7Search" method="search" returnVariable="searchReturnStructStandard">
					<cfinvokeargument name="criteria" value="#URL.criteria#">
					<cfinvokeargument name="searchFacets" value="#URL.searchFacets#">
					<cfinvokeargument name="startrow" value="#URL.startrow#">
					<cfinvokeargument name="maxrows" value="#URL.MaxRows#">
					<cfinvokeargument name="contextpassages" value="5">
					<cfinvokeargument name="dismax" value="false">
				</cfinvoke>
				<cfif searchReturnStructStandard.query_result.RecordCount>
					<cfset searchReturnStruct = searchReturnStructStandard>
				</cfif>
				<cfcatch>
					<cfif Not Request.IsDevelopment>
						<cf_error type="search" fatal="false" display="false" email="true" message="Error trying alternate non-Dismax search" message_detail="#cfcatch.message##cfcatch.detail#">
					<cfelse>
						<cfrethrow>
					</cfif>
				</cfcatch>
			</cftry>
		</cfif>
		
		<cfset qSearchResults = searchReturnStruct.query_result>
		<cfset searchStatus = searchReturnStruct.result_status>
		<cfset qHL7Suggestions = searchReturnStruct.HL7Suggestions>
		<cfset qFacetDescriptionCounts = searchReturnStruct.QUERY_FACETS>
		<cfset search_id = searchReturnStruct.search_id>
	</cfif>
	
	<!---========================================
	|                                           |
	|   FFFFFF   OOOOO   RRRRRR   MM     MM     |
	|   FF      OO   OO  RR   RR  MMM   MMM     |
	|   FFFF    OO   OO  RRRRR    MM MMM MM     |
	|   FF      OO   OO  RR  RR   MM  M  MM     |
	|   FF       OOOOO   RR   RR  MM     MM     |
	|                                           |
	=========================================--->
	<cfset pageSubTitle = "Search">
	<cfset pageSubSubTitle = "">
	<script src="/includes/commonJS.js" type="text/javascript"></script>
	<script language="JavaScript">
		function clearSearch()
			{
			var FormName = document.searchForm;
			resetForm(FormName,true);
			FormName.searchFacets.value = "";
			}
		function reloadSearchCriteria(criteriaToLoad)
			{
			var FormName = document.searchForm;
			
			// First, reset the form to whatever it was when they got there
			resetForm(FormName);
			
			// Now overload the criteria
			FormName.criteria.value = criteriaToLoad;
			
			// Finally, submit the form
			FormName.submit();
			}
		function addFacet(facetToAdd)
			{
			var FormName = document.searchForm;
			
			FormName.searchFacets.value = addToList(FormName.searchFacets.value, facetToAdd);
			
			// Finally, submit the form
			FormName.submit();
			}
		function removeFacet(facetToRemove)
			{
			var FormName = document.searchForm;
			
			if (listFindNoCase(FormName.searchFacets.value,facetToRemove) >= 0)
				{ FormName.searchFacets.value = listDeleteAt(FormName.searchFacets.value, listFindNoCase(FormName.searchFacets.value,facetToRemove)); }
			
			// Finally, submit the form
			FormName.submit();
			}
	</script>
	
	<cfimport prefix="layout" taglib="../customtags/layouttags">
	<cfoutput>
		<form name="searchForm" action="#CGI.SCRIPT_NAME#" method="GET" onsubmit="searchFormSubmitButton.disabled=true;">
			<input type="hidden" name="searchFacets" value="#URL.searchFacets#">
			<table cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td align="left" valign="middle" colspan="3">
						<span style="line-height: 20pt;">What can we help you find?</span>
					</td>
				</tr><tr>
					<td align="left" valign="middle">
						<input type="text" size="60" name="criteria" value="#HTMLEditFormat(URL.criteria)#" style="height: 18pt; font-size: 14pt;" />
					</td>
					<td align="left" valign="middle">&nbsp;</td>
					<td align="left" valign="middle">				
						<input type="button" value="Search!" id="searchFormSubmitButton" onclick="{ this.form.submit(); this.disabled=true; }" style="height:16pt; font-size:14pt; height:31px;" />
					</td>
				</tr>
			</table>
		</form>
	</cfoutput>

	<cfif Len(Trim(URL.criteria))>
		<cfoutput>
			<p>
				Below are results <strong><cfif qSearchResults.RecordCount NEQ 0>#URL.StartRow#<cfelse>0</cfif> - #Min(Val(URL.StartRow + URL.MaxRows - 1),searchStatus.found)#</strong> of about <strong>#NumberFormat(searchStatus.found)#</strong> 
				<cfif Len(URL.criteria)> for &quot;<i>#HTMLEditFormat(URL.criteria)#</i>&quot;</cfif>. 
				(#Evaluate(searchStatus.time/1000)# seconds).
			</p>
		</cfoutput>
	</cfif>

	<!---=========================================================================================================================================================================
	|                                                                                                                                                                            |
	|    CCCCC  UU  UU  RRRRRR   RRRRRR   EEEEEE  NN    NN  TTTTTT       RRRRRR   EEEEEE   SSSSS  TTTTTT  RRRRRR   IIIIII   CCCCC  TTTTTT  IIIIII   OOOOO   NN    NN   SSSSS     |
	|   CC      UU  UU  RR   RR  RR   RR  EE      NNN   NN    TT         RR   RR  EE      SS        TT    RR   RR    II    CC        TT      II    OO   OO  NNN   NN  SS         |
	|   CC      UU  UU  RRRRR    RRRRR    EEEE    NN NN NN    TT         RRRRR    EEEE     SSSS     TT    RRRRR      II    CC        TT      II    OO   OO  NN NN NN   SSSS      |
	|   CC      UU  UU  RR  RR   RR  RR   EE      NN   NNN    TT         RR  RR   EE          SS    TT    RR  RR     II    CC        TT      II    OO   OO  NN   NNN      SS     |
	|    CCCCC   UUUU   RR   RR  RR   RR  EEEEEE  NN    NN    TT         RR   RR  EEEEEE  SSSSS     TT    RR   RR  IIIIII   CCCCC    TT    IIIIII   OOOOO   NN    NN  SSSSS      |
	|                                                                                                                                                                            |
	==========================================================================================================================================================================--->
	<cfif ListLen(URL.searchFacets) GT 0 AND isDefined("qFacetDescriptionCounts") AND qFacetDescriptionCounts.recordCount GT 0>
		<div id="currentRestrictions">
			<p>Your search is currently narrowed to the following areas:</p>
			
			<ul>
				<cfoutput query="qFacetDescriptionCounts">
					<cfif ListFindNoCase(URL.searchFacets,FACET_FIELD)>
						<li>
							<a class="restrictionCloseButton" href="JavaScript:removeFacet('#FACET_FIELD#');">X</a>
							#FACET_GROUP#: #FACET_DESCRIPTION# (<a href="JavaScript:removeFacet('#FACET_FIELD#');"><b>remove filter</b></a>)
						</li>
					</cfif>
				</cfoutput>
			</ul>
		</div>
	</cfif>
				
	<cfif Len(Trim(URL.criteria))>		
	<div style="width: 29.5%; float: left; border-right: 1px solid #C0C0C0; margin-right: 5px; padding-right: 5px;">
		
	<!---===================================================
	|                                                      |
	|   FFFFFF   AAAA    CCCCC  EEEEEE  TTTTTT   SSSSS     |
	|   FF      AA  AA  CC      EE        TT    SS         |
	|   FFFF    AAAAAA  CC      EEEE      TT     SSSS      |
	|   FF      AA  AA  CC      EE        TT        SS     |
	|   FF      AA  AA   CCCCC  EEEEEE    TT    SSSSS      |
	|                                                      |
	====================================================--->
		<cfif qFacetDescriptionCounts.RecordCount>
			<script type="text/javascript">
				function showFacetGroup(groupNum)
				{
					var trs = document.getElementsByTagName("tr");
					for(var i=0;i<trs.length;i++)
					{
						if(trs[i].className.indexOf("facetgroup" + groupNum + "num") > -1)
						{
							trs[i].style.display = "table-row";
						}
						else if(trs[i].className.indexOf("facetgroupmorelink" + groupNum + "num") > -1)
						{
							trs[i].style.display = "none";
						}
					}
				}
			</script>

			<!--- ReQuery the counts without the select items so that we don't include blank groups --->
			<cfquery name="qFacetDescriptionCounts" dbtype="query">
				SELECT *
				FROM qFacetDescriptionCounts
				WHERE FACET_COUNT > 0
					<cfif Len(URL.searchFacets)>
						AND FACET_FIELD NOT IN (<cfqueryparam value="#URL.searchFacets#" cfsqltype="CF_SQL_VARCHAR" list="Yes">)
					</cfif>
			</cfquery>
			
			<cfif qFacetDescriptionCounts.RecordCount>
				<p>Want to get more specific?</p>
			<cfelse>
				<p><em>No additional filters available.</em></p>
			</cfif>
			<table cellspacing="0" cellpadding="0" width="100%">
				<cfoutput query="qFacetDescriptionCounts" group="FACET_GROUP">
					<cfif currentRow GT 1>
						<tr><td>&nbsp;</td></tr>
					</cfif>
					<tr>
						<td align="left" valign="top">
							<b>Filter by #FACET_GROUP#</b>
						</td>
					</tr>
					<cfset groupStartRow = currentRow>
					<cfoutput>						
						<tr class="facetgroup#Val(groupStartRow)#num" style="display: #iif(currentRow - groupStartRow LT 10, DE("table-row"), DE("none"))#;">
							<td align="left" valign="top">
								<div style="margin: 0 0 0 10px;">
									<a href="JavaScript:addFacet('#FACET_FIELD#');">#FACET_DESCRIPTION#</a> (#FACET_COUNT#)<br>
								</div>
							</td>
						</tr>
						<cfif currentRow - groupStartRow IS 10>
							<tr class="facetgroupmorelink#Val(groupStartRow)#num">
								<td align="left" valign="top">
									<div style="margin: 0 0 0 10px;">
										<a href="javascript: showFacetGroup(#Val(groupStartRow)#); void(0);" style="font-weight: bold;">See All...</a>
									</div>
								</td>
							</tr>
						</cfif>
					</cfoutput>
				</cfoutput>
			</table>
		<cfelse>
			<hr>
		</cfif>
	</div>
	<cfelse>
		<br />
		<p>Looking for a specific type of information, like meeting minutes or news releases? You'll have the opportunity to refine your query after you start your search.</p>
	</cfif>				

	<div style="width: 66.5%; float: left; margin-left: -6px; padding-left: 5px; border-left: 1px solid #C0C0C0;">
	<cfif Len(Trim(URL.criteria)) And Not qSearchResults.RecordCount>
		<!---====================================================================================
		|                                                                                       |
		|   NN    NN   OOOOO        RRRRRR   EEEEEE   SSSSS  UU  UU  LL      TTTTTT   SSSSS     |
		|   NNN   NN  OO   OO       RR   RR  EE      SS      UU  UU  LL        TT    SS         |
		|   NN NN NN  OO   OO       RRRRR    EEEE     SSSS   UU  UU  LL        TT     SSSS      |
		|   NN   NNN  OO   OO       RR  RR   EE          SS  UU  UU  LL        TT        SS     |
		|   NN    NN   OOOOO        RR   RR  EEEEEE  SSSSS    UUUU   LLLLLL    TT    SSSSS      |
		|                                                                                       |
		=====================================================================================--->
		<cfoutput>
		<h3>Your search - <strong>#URL.criteria#</strong> - did not match any results.</h3>
		<p>
			Suggestions: 
			<ul>
				<cfif Len(URL.searchFacets)>
					<li>Try removing some search filters.</li>
				</cfif>
				<li>Make sure all words are spelled correctly.</li>
				<li>Try different keywords.</li>
				<li>Try more general keywords.</li>
				<li>Try fewer keywords.</li>
				<li>View the <a href="search_tips.cfm?search_query_string=#URLEncodedFormat(CGI.query_string)#">Search Tips</a> for additional filtering options.</li>
				<li><a href="search_contact.cfm?action=help&search_query_string=#URLEncodedFormat(CGI.query_string)#">Contact us</a> for assistance.</li>
			</ul>
		</p>
		</cfoutput>
	<cfelseif Len(Trim(URL.criteria))>
		<!---================================================================================================
		|                                                                                                   |
		|    SSSSS  UU  UU   GGGGG    GGGGG   EEEEEE   SSSSS  TTTTTT  IIIIII   OOOOO   NN    NN   SSSSS     |
		|   SS      UU  UU  GG       GG       EE      SS        TT      II    OO   OO  NNN   NN  SS         |
		|    SSSS   UU  UU  GG  GGG  GG  GGG  EEEE     SSSS     TT      II    OO   OO  NN NN NN   SSSS      |
		|       SS  UU  UU  GG   GG  GG   GG  EE          SS    TT      II    OO   OO  NN   NNN      SS     |
		|   SSSSS    UUUU    GGGGG    GGGGG   EEEEEE  SSSSS     TT    IIIIII   OOOOO   NN    NN  SSSSS      |
		|                                                                                                   |
		=================================================================================================--->
		<!--- If we have suggestions, output those --->
		<cfif qHL7Suggestions.RecordCount And URL.startRow EQ 1>
			<div width="100%" id="suggestedLinkCollection">
				<h3 style="padding-top:5px;">Suggested Links</h3>
				<cfoutput query="qHL7Suggestions" maxrows="3">
					<cfset searchResultURL = Replace(qHL7Suggestions.URL, "\","/","All")>
					<!--- If it starts with a double slash, replace it with a single slash --->
					<cfif Left(searchResultURL,2) eq "//">
						<cfset searchResultURL = Right(searchResultURL,Len(searchResultURL)-1)>
					</cfif>
					<div style="margin-bottom:15px; margin-left:20px;">
						<a href="viewSearchResult.cfm?search_id=#search_id#&source=suggest&search_result_url=#URLEncodedFormat(searchResultURL)#">
							<div style="float:left; width:20px;">
								<img src="/assets/filetypeimages/#fileIcon(qHL7Suggestions.URL)#" border="0">
							</div>
							<div style="margin-left:20px;">
								<!--- If there is a title, use the title, otherwise the file name --->
								<cfif Len(qHL7Suggestions.Title)>
									#Left(HTMLEditFormat(Title),80)#<cfif Len(Title) GT 80>...</cfif>
								<cfelse>
									#GetFileFromPath(qHL7Suggestions.URL)#
								</cfif>
							</div>
						</a>
						<div style="margin-left:20px;">
							<cfif Not FindNoCase("Connection Failure",qHL7Suggestions.summary)>
								#Left(HTMLEditFormat(qHL7Suggestions.summary),200)#<cfif Len(qHL7Suggestions.summary) GT 200>...</cfif>
							</cfif>
						</div>
					</div>
				</cfoutput>
			</div>
		</cfif>

		<!---============================================================
		|                                                               |
		|   RRRRRR   EEEEEE   SSSSS  UU  UU  LL      TTTTTT   SSSSS     |
		|   RR   RR  EE      SS      UU  UU  LL        TT    SS         |
		|   RRRRR    EEEE     SSSS   UU  UU  LL        TT     SSSS      |
		|   RR  RR   EE          SS  UU  UU  LL        TT        SS     |
		|   RR   RR  EEEEEE  SSSSS    UUUU   LLLLLL    TT    SSSSS      |
		|                                                               |
		=============================================================--->
		<cfif qSearchResults.RecordCount>
			<div width="100%" style="margin: 10px; float: left;" id="searchResultCollection">
				<cfoutput query="qSearchResults" maxRows="#URL.MaxRows#">
					<cfset searchResultURL = Replace(qSearchResults.URL, "\","/","All")>
					<!--- If it starts with a double slash, replace it with a single slash --->
					<cfif Left(searchResultURL,2) eq "//">
						<cfset searchResultURL = Right(searchResultURL,Len(searchResultURL)-1)>
					</cfif>
					<div>
						<div>
							<a class="searchresulttitle" href="viewSearchResult.cfm?search_id=#search_id#&search_result_url=#URLEncodedFormat(searchResultURL)#">
								<div style="float:left; width:20px;">
									<img src="/assets/filetypeimages/#fileIcon(qSearchResults.URL)#" border="0">
								</div>
								<div style="margin-left:20px;">
									<!--- If there is a title, use the title, otherwise the file name --->
									<cfif Len(qSearchResults.Title)>
										#Left(HTMLEditFormat(Title),255)#<cfif Len(Title) GT 255>...</cfif>
									<cfelse>
										#GetFileFromPath(qSearchResults.Key)#
									</cfif>
								</div>
							</a>
						</div>
						<div style="margin-left:20px; padding-bottom:20px;">
							<!--- If we have the search result in context, show it --->
							<div class="searchresultsnippet" href="viewSearchResult.cfm?search_id=#search_id#&search_result_url=#URLEncodedFormat(searchResultURL)#">
								<cfif Len(qSearchResults.context)>
									<!--- We need to unescape the span tags that highlightContext so that they still show --->
									#ReplaceList(HTMLEditFormat(qSearchResults.context), "&lt;span class=&quot;highlightContext&quot;&gt;,&lt;/span class=&quot;highlightContext&quot;&gt;", "<span class=""highlightContext"">,</span>")#
									<!--- If the context is short, also show the summary --->
									<cfif Len(qSearchResults.summary) And Len(RemoveHTML(qSearchResults.context)) LT 500>
										#HTMLEditFormat(Left(qSearchResults.summary,500-Len(RemoveHTML(qSearchResults.context))))#
									</cfif>
								<!--- Otherwise show the summary --->
								<cfelse>
									#HTMLEditFormat(qSearchResults.summary)#
								</cfif>
							</div>
	
							<div class="footnote">
								<cfif Len(searchResultURL) GT 100>...</cfif>#Right(searchResultURL,100)#
								<cfif Len(size)> - #FormatFileSize(size)#</cfif>
							</div>
							
							<span class="unimportantText">
								<em>
									<!--- Try getting the last updated date if it's not an external link --->
									<cfif Not (compareNoCase(Left(qSearchResults.Key,7),"http://") EQ 0 Or compareNoCase(Left(qSearchResults.Key,7),"https://") EQ 0) And FileExists(qSearchResults.Key)>
										<cfset fileDirectory = Reverse(ListRest(Reverse(qSearchResults.Key),"\"))>
										<cfdirectory action="LIST" directory="#fileDirectory#" name="qFileProperties" filter="#GetFileFromPath(qSearchResults.Key)#">
										Last Modified: #DateFormat(qFileProperties.DateLastModified,"mmm d, yyyy")# - #TimeFormat(qFileProperties.DateLastModified,"h:mm:ss tt")# ET<br>
									</cfif>
									<cfif Len(qSearchResults.author)>
										Author: #qSearchResults.author#<br>
									</cfif>
								</em>
							</span>
						</div>
					</div>
				</cfoutput>
			</div>
			
			<!---==========================================================================================
			|                                                                                             |
			|   PPPPPP    AAAA    GGGGG   IIIIII  NN    NN   AAAA   TTTTTT  IIIIII   OOOOO   NN    NN     |
			|   PP   PP  AA  AA  GG         II    NNN   NN  AA  AA    TT      II    OO   OO  NNN   NN     |
			|   PPPPP    AAAAAA  GG  GGG    II    NN NN NN  AAAAAA    TT      II    OO   OO  NN NN NN     |
			|   PP       AA  AA  GG   GG    II    NN   NNN  AA  AA    TT      II    OO   OO  NN   NNN     |
			|   PP       AA  AA   GGGGG   IIIIII  NN    NN  AA  AA    TT    IIIIII   OOOOO   NN    NN     |
			|                                                                                             |
			===========================================================================================--->
			<cfparam name="startRow">
			<cfparam name="maxRows">
			<cfparam name="nextStart" default="#Val(startRow + maxRows)#">
			<cfparam name="queryRecordCount" default="#searchStatus.found#">
			<cfparam name="endRow" default="#Min(Val(startRow+maxRows-1),queryRecordCount)#">
			<cfparam name="numberOfPaginationPages" default="10">
			
			<!--- Generate the basePaginationURL --->
			<cfscript>
				basePaginationURL = "?";
				if (Len(URL.criteria))		{ basePaginationURL = ListAppend(basePaginationURL, "criteria=#URLEncodedFormat(URL.criteria)#", "&?");}
				if (Len(URL.searchFacets))		{ basePaginationURL = ListAppend(basePaginationURL, "searchFacets=#URL.searchFacets#", "&?"); }
			</cfscript>
	
			<br>
			<div id="tnt_pagination" style="text-align: center; white-space: nowrap; margin: auto;">
			<form name="maxRecordsForm" action="" method="GET">
				<cfoutput>
				<CF_EmbedFields>
				<cfif Not StructKeyExists(Form,"startRow")><input type="hidden" name="startRow" value="#startRow#"></cfif>
				<cfif Not StructKeyExists(Form,"maxRows")><input type="hidden" name="maxRows" value="#maxRows#"></cfif>
				
				<cfif startRow eq 1>
					<span class="disabled_tnt_pagination">&lt;&lt;&nbsp; First</span>
					<span class="disabled_tnt_pagination">&lt;&nbsp; Prev</span>
				<cfelse>
					<a href="#ListAppend(basePaginationURL, "startRow=1", "&?")#">&lt;&lt;&nbsp; First</a>
					<a href="#ListAppend(basePaginationURL, "startRow=#Val(startRow-maxRows)#", "&?")#">&lt;&nbsp; Prev</a>
				</cfif>
				
				<cfscript>
					currentPage = Ceiling(startRow/maxRows);
					
					// If they are less than half way through the numberOfPaginationPages, startPagination at 1
					if (currentPage LTE Ceiling(numberOfPaginationPages/2))
						{ startPagination = 1; }
					// Otherwise, startPagination at currentPage minus half the numberOfPaginationPages
					else
						{ startPagination = currentPage - Ceiling(numberOfPaginationPages/2); }
					
					// Figure out the end paging
					recordsRemainingAfterStartPagination = queryRecordCount-((startPagination-1)*maxRows);
					possiblePagesAfterStartPagination = Ceiling(recordsRemainingAfterStartPagination/maxRows);
					
					// If we have less possiblePagesAfterStartPagination than numberOfPaginationPages, use that instead
					endPagination = Min(Val(startPagination+numberOfPaginationPages), Val(startPagination+possiblePagesAfterStartPagination))-1;
				</cfscript>
				
				<!--- Loop from 1 to the number of pages in the pagination and show the links --->
				<cfloop from="#startPagination#" to="#endPagination#" index="pageOffset">
					<cfif pageOffset NEQ currentPage>
						<a href="#ListAppend(basePaginationURL, "startRow=#Val(startPagination+((pageOffset-1)*maxRows))#", "&?")#">#pageOffset#</a>
					<cfelse>
						<span class="active_tnt_link">#pageOffset#</span>
					</cfif>
				</cfloop>
				
				<cfif nextStart LTE queryRecordCount>
					<cfif Val(nextStart + maxRows - 1) GT queryRecordCount>
						<cfset nextRecordCount = queryRecordCount MOD maxRows>
					<cfelse>
						<cfset nextRecordCount = maxRows>
					</cfif>
					<cfset lastRecordStart = queryRecordCount - (queryRecordCount MOD maxRows)+1>
					<a href="#ListAppend(basePaginationURL, "startRow=#nextStart#", "&?")#">Next &nbsp;&gt;</a>
					<a href="#ListAppend(basePaginationURL, "startRow=#lastRecordStart#", "&?")#">Last &nbsp;&gt;&gt;</a>
				<cfelse>
					<span class="disabled_tnt_pagination">Next &nbsp;&gt;</span>
					<span class="disabled_tnt_pagination">Last &nbsp;&gt;&gt;</span>
				</cfif>
				</cfoutput>
			</form>
			</div>
		</cfif>
	</cfif>
	</div>
</cfsavecontent>

<!------------------------------------------------------------------------>
<!--- END PAGE ----------------------------------------------------------->
<!------------------------------------------------------------------------>


</cfsilent>
<!--- Call the template into which all the above code will fit. --->
<cfinclude template="../templates/hl7main_noside_top.cfm">

<cfoutput>#Template.PageContent#</cfoutput>

<cfinclude template="../templates/hl7main_bottom.cfm">