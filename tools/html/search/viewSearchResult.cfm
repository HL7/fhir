<cfparam name="URL.search_id" default="">
<cfparam name="URL.search_result_url" default="">

<!--- If we don't have a search_id or a search_result_url, redirect to the search page --->
<cfif Not Len(URL.search_id) Or Not Len(URL.search_result_url)>
	<cflocation url="index.cfm" addtoken="No">
</cfif>

<!--- First, log that they are viewing the search result --->
<cftry>
	<cfquery name="iSearchResult" datasource="HL7PageLoads">
		IF EXISTS
			(
			SELECT 1
			FROM Search
			WHERE search_id = <cfqueryparam value="#URL.search_id#" cfsqltype="CF_SQL_INTEGER" NULL="#(Not Len(URL.search_id))#">
			)
		INSERT INTO Search_Result (search_id, search_result_url)
		VALUES
			(
			<cfqueryparam value="#URL.search_id#" cfsqltype="CF_SQL_INTEGER" NULL="#(Not Len(URL.search_id))#">,
			<cfqueryparam value="#Left(URL.search_result_url,900)#" cfsqltype="CF_SQL_VARCHAR">
			)
	</cfquery>
	<cfcatch>
		<cf_error fatal="false" display="false" email="true" message="Error logging search result">
	</cfcatch>
</cftry>

<!--- Now, redirect them to the content that they requested --->
<cflocation url="#URL.search_result_url#" addtoken="No">
