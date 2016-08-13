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

<!--- This shows up in the <TITLE> tag --->
<cfset Template.PageTitle = "Search Tips Health Level Seven">

<!--- Additional tags, if any, to add to the <HEAD> tag, in addition to the ones included in the template. --->
<cfsavecontent variable="Template.HTMLHEAD">
</cfsavecontent>

<!--- This shows up on the site's header, next to the HL7 logo. --->
<cfsavecontent variable="Template.Header_PageDescription">
</cfsavecontent>

<!--- This is the actual content for the page. The "meat" of the page goes here --->
<cfsavecontent variable="Template.PageContent">
	<h1>Search Tips</h1>
	
	<cfparam name="URL.search_query_string" default="">
	
	<a id="top" name="top"></a>
	<ul style="margin-top:0px;">
		<li><a href="#sect1">Basic search</a></li>
		<li><a href="#sect1a">Filtered search</a></li>
		<li><a href="#sect2">Boolean searching</a></li>
		<li><a href="#sect3">Boolean operator &quot;OR&quot;</a></li>
		<li><a href="#sect4">Boolean operator &quot;AND&quot;</a></li>
		<li><a href="#sect5">Boolean operator &quot;NOT&quot;</a></li>
		<li><a href="#sect6">Boolean operator &quot;+&quot;</a></li>
		<li><a href="#sect7">Boolean operator &quot;-&quot;</a></li>
		<li><a href="#sect8">Grouping</a></li>
		<li><a href="#sect9">Wildcard searches</a></li>
		<li><a href="#sect10">Fuzzy searches</a></li>
		<li><a href="#sect11">Proximity searches</a></li>
		<li><a href="#sect12">Boosting a term</a></li>
		<li><a href="#sect13">Escaping special characters</a></li>
		<li><a href="#sect13a">Additional help</a></li>
	</ul>
	
	<a id="sect1a" name="sect1a"></a>
	<h2>Filtered search</h2>
	<div style="margin-left:25px;">
		<p>When available, filters can be added to your search by clicking the filter category to the left of the search results.</p>
		<p>Filters can be removed by clicking <strong>remove filter</strong> next to the filter which is desired to be removed or by clicking the X to the right of the filter.</p>
		<p><a href="#top">top of page</a></p>
	</div>
	
	<a id="sect1" name="sect1"></a>
	<h2>Basic search</h2>
	<div style="margin-left:25px;">
		<p>To do a basic search, enter some search terms. For example:</p>
		<ul>
			<li>Prague</li>
			<li>Conference</li>
			<li>Interoperability</li>
		</ul>
		<p>This will search for documents containing the words Prague, Conference and Interoperability.</p>
		<p><a href="#top">top of page</a></p>
	</div>
	
	<a id="sect2" name="sect2"></a>
	<h2>Boolean searching</h2>
	<div style="margin-left:25px;">
		<p>Boolean operators allow terms to be combined for more advanced searches. The terms OR, AND, NOT, and +,&nbsp;-&nbsp; are supported.</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect3" name="sect3"></a>
	<h2>Boolean operator &quot;OR&quot;</h2>
	<div style="margin-left:25px;">
		<p>The OR operator links two terms and finds a matching document if either of the terms exist in a document. Note, the symbol || can be used in place or the word OR. To search for documents that contain either &quot;HL7 Standards&quot; or just &quot;ANSI Standards&quot; use the query:</p>
		<p>&quot;HL7 Standards&quot; OR &quot;ANSI Standards&quot;</p>
		<p>or</p>
		<p>&quot;HL7 Standards&quot; || &quot;ANSI Standards&quot;</p>
		<p><a href="#top">top of page</p>
	</div>
		
	<a id="sect4" name="sect4"></a>
	<h2>Boolean operator &quot;AND&quot;</h2>
	<div style="margin-left:25px;">
		<p>The AND operator matches documents where both terms are in the text of the document. The symbol &amp;&amp; can be used in place of the word AND. This is a more restrictive search than an OR search. To search for documents that contain &quot;HL7 Standards&quot; and &quot;ANSI Standards&quot; use the query:</p>
		<p>&quot;HL7 Standards&quot; AND &quot;ANSI Standards&quot;</p>
		<p>or</p>
		<p>&quot;HL7 Standards&quot; &amp;&amp; &quot;ANSI Standards&quot;</p>
		<p>Note: AND is the default search term, so the following search is equivalent to both of the previous examples:</p>
		<p>&quot;HL7 Standards&quot; &quot;ANSI Standards&quot;</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect5" name="sect5"></a>
	<h2>Boolean operator &quot;NOT&quot;</h2>
	<div style="margin-left:25px;">
		<p>The NOT operator excludes documents that contain terms after NOT. The ! symbol can be used in place of the word NOT. To search for documents that contain &quot;HL7 Standards&quot; but not &quot;ANSI Standards&quot; use the query:<br /><br />&quot;HL7 Standards&quot; NOT &quot;ANSI Standards&quot; <br /><br />or <br /><br />&quot;HL7 Standards&quot; ! &quot;ANSI Standards&quot;<br /><br />Note: The NOT operator must be used with multiple terms. For example, the following search will return no results: NOT &quot;ANSI Standards&quot;</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect6" name="sect6"></a>
	<h2>Boolean operator &quot;+&quot;</h2>
	<div style="margin-left:25px;">
		<p>The &quot;+&quot; operator tells the search engine that the search term must appear in a document to be a match. To search for documents that must contain &quot;HL7 Standards&quot; and may contain &quot;ANSI Standards&quot; use the query: <br /><br />+&quot;HL7 Standards&quot; &quot;ANSI Standards&quot;</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect7" name="sect7"></a>
	<h2>Boolean operator &quot;-&quot;</h2>
	<div style="margin-left:25px;">
		<p>The &quot;-&quot; operator excludes documents that contain the term after the &quot;-&quot; symbol. To search for documents that contain &quot;HL7 Standards&quot; but not &quot;ANSI Standards&quot; use the query: <br /><br />&quot;HL7 Standards&quot; - &quot;ANSI Standards&quot;</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect8" name="sect8"></a>
	<h2>Grouping</h2>
	<div style="margin-left:25px;">
		<p>Search terms can be grouped using parentheses for sub queries. For example, to find results about conferences on Interoperability in different locations, try the following:</p>
		<p>(Prague OR London OR Paris) AND Interoperability</p>
		<p>Further explanation of the Boolean operatiors OR and AND is noted in the &quot;Boolean Operators&quot; section above.</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect9" name="sect9"></a>
	<h2>Wildcard searches</h2>
	<div style="margin-left:25px;">
		<p>Wildcard searches permit searches for partial words. The single character wildcard search (?) looks for terms that match that with the single character replaced. For example, to search for "text" or "test" use the search:</p>
		<p>te?t</p>
		<p>Multiple character wildcard searches (*) looks for 0 or more characters. For example, to search for test, tests or tester, use the search:</p>
		<p>test*</p>
		<p>Wildcard searches can also be used in the middle of a term.</p>
		<p>te*t</p>
		<p>Note: * or ? symbols cannot be used as the first character of a search.</p>		
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect10" name="sect10"></a>
	<h2>Fuzzy searches</h2>
	<div style="margin-left:25px;">
		<p>Fuzzy searches return results that match both the exact term searched&nbsp;and&nbsp;results that are close to the term searched.&nbsp;Add a tilde (~) to your search term to execute a fuzzy search.&nbsp;For example:</p>
		<p>certif~</p>
		<p>This search will return results for &quot;certification&quot; and also&nbsp;terms like &quot;certified&quot; and &quot;certify&quot;.</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect12" name="sect12"></a>
	<h2>Boosting a term</h2>
	<div style="margin-left:25px;">
		<p>Boosting a search term gives it more weight in the result list. For example, a search for &quot;HL7 Standards&quot; might yield results about HL7 technology and Standards but not necessarily HL7 Standards. To weight the word &quot;Standards&quot; more heavily use the following searches:</p>
		<p>HL7 Standards^4</p>
		<p>To weight the entire phrase &quot;HL7 Standards&quot; (not just the word &quot;Standards&quot; as shown above) use quotes as follows:</p>
		<p>&quot;HL7 Standards&quot;^4 ANSI</p>
		<p>Any number can be used&nbsp;to boost the term -- the higher the number the higher the boosting. For example, if the query above still returns too many results, consider increasing the boost value as follows:</p>
		<p>&quot;HL7 Standards&quot;^6 ANSI</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect13" name="sect13"></a>
	<h2>Escaping special characters</h2>
	<div style="margin-left:25px;">
		<p>There are a number of special characters that are part of the query syntax. The current list of special characters are + - &amp;&amp; || ! ( ) { } [ ] ^ &quot; ~ * ? : \ To escape these characters use the \ before the character. For example to search for (1+1):2 use the query: \(1\+1\)\:2</p>
		<p><a href="#top">top of page</p>
	</div>
	
	<a id="sect13a" name="sect13a"></a>
	<h2>Additional help</h2>
	<div style="margin-left:25px;">
		<p>If your search isn't returning any results, or isn't finding the results you desire, please try the following:</p>
		<ul>
			<li>Try removing some search filters.</li>
			<li>Make sure all words are spelled correctly.</li>
			<li>Try different keywords.</li>
			<li>Try more general keywords.</li>
			<li>Try fewer keywords.</li>
			<li><a href="search_contact.cfm?action=help&search_query_string=<cfoutput>#URLEncodedFormat(search_query_string)#</cfoutput>">Contact us</a> for assistance.</li>
		</ul>
		<p><a href="#top">top of page</a></p>
	</div>
</cfsavecontent>


<!------------------------------------------------------------------------>
<!--- END PAGE ----------------------------------------------------------->
<!------------------------------------------------------------------------>


</cfsilent>
<!--- Call the template into which all the above code will fit. --->
<cfinclude template="../templates/hl7main.cfm">