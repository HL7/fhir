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
<cfset Template.PageTitle = "Search Feedback Health Level Seven">

<!--- Additional tags, if any, to add to the <HEAD> tag, in addition to the ones included in the template. --->
<cfsavecontent variable="Template.HTMLHEAD">
</cfsavecontent>

<!--- This shows up on the site's header, next to the HL7 logo. --->
<cfsavecontent variable="Template.Header_PageDescription">
</cfsavecontent>

<!--- This is the actual content for the page. The "meat" of the page goes here --->
<cfsavecontent variable="Template.PageContent">
	<cfparam name="URL.action" default="feedback">
	<cfparam name="URL.search_query_string" default="">

	<!--- Initialize the captcha object --->
	<cfset captchaObj = CreateObject("component","customtags.com.hl7.captcha").Init()/>
	
	<!---=====================================================================
	|                                                                        |
	|   FFFFFF  EEEEEE  EEEEEE  DDDDD    BBBBBB    AAAA    CCCCC  KK  KK     |
	|   FF      EE      EE      DD   DD  BB   BB  AA  AA  CC      KK KK      |
	|   FFFF    EEEE    EEEE    DD   DD  BBBBB    AAAAAA  CC      KKKK       |
	|   FF      EE      EE      DD   DD  BB   BB  AA  AA  CC      KK KK      |
	|   FF      EEEEEE  EEEEEE  DDDDD    BBBBBB   AA  AA   CCCCC  KK  KK     |
	|                                                                        |
	======================================================================--->
	<cfif LCase(URL.action) EQ "feedback">
		<h1>Provide Feedback</h1>
		<p>
			Your feedback is very important to us. Comments submitted here will be used to
			refine our search engine to provide better results.
		</p><p>
			Please click the "Provide Feedback" link above the Search box, enter your comments, 
			and then click the "Submit" button.
		</p>
		<cfoutput>
		<cfform action="?action=post&search_query_string=#URLEncodedFormat(URL.search_query_string)#" method="POST">
			<input type="hidden" name="contact_type" value="#URL.action#">
			<textarea name="contact_text" rows="6" cols="75"></textarea><br><br>
			
			<strong>Name (optional):</strong><br>
			<input type="text" name="contact_name" size="35"><br><br>
			
			<strong>Email (optional):</strong><br>
			<cfinput type="text" name="contact_email" size="35" message="Please enter a valid email address" validate="email"><br><br>
			
			<!--- Show them the Image Challenge if they are not a logged in user --->
			<cfif Not IsDefined("client.id") Or Not Len(client.id)>
				<strong>Image Challenge*:</strong><br>
				#captchaObj.render()#<br>
			</cfif>
			
			<input type="submit" value="Submit">
		</cfform>
		</cfoutput>
	
	<!---=====================================
	|                                        |
	|   HH   HH  EEEEEE  LL      PPPPPP      |
	|   HH   HH  EE      LL      PP   PP     |
	|   HHHHHHH  EEEE    LL      PPPPP       |
	|   HH   HH  EE      LL      PP          |
	|   HH   HH  EEEEEE  LLLLLL  PP          |
	|                                        |
	======================================--->
	<cfelseif LCase(URL.action) EQ "help">
		<h1>Search Assistance</h1>
		<p>
			If you're unable to find information on the website, please describe what you
			are looking for in the box below, then enter your contact information so that
			we can get back to you.
		</p>
		<cfoutput>
		<cfform action="?action=post&search_query_string=#URLEncodedFormat(URL.search_query_string)#" method="POST">
			<input type="hidden" name="contact_type" value="#URL.action#">
			
			<strong>Search Criteria:</strong><br>
			#URL.search_query_string#<br><br>
			
			<strong>Description of what you were looking for*:</strong><br>
			<cftextarea name="contact_text" rows="3" cols="50" required="Yes" message="Please describe what you were looking for"></cftextarea><br><br>
			
			<strong>Name*:</strong><br>
			<cfinput type="text" name="contact_name" size="35" required="Yes" message="Please enter your name"><br><br>
			
			<strong>Email*:</strong><br>
			<cfinput type="text" name="contact_email" size="35" required="Yes" message="Please enter a valid email address" validate="email"><br><br>
			
			<!--- Show them the Image Challenge if they are not a logged in user --->
			<cfif Not IsDefined("client.id") Or Not Len(client.id)>
				<strong>Image Challenge*:</strong><br>
				#captchaObj.render()#<br>
			</cfif>
			
			<input type="submit" value="Submit">
		</cfform>
		</cfoutput>
	
	<!---=====================================
	|                                        |
	|   PPPPPP    OOOOO    SSSSS  TTTTTT     |
	|   PP   PP  OO   OO  SS        TT       |
	|   PPPPP    OO   OO   SSSS     TT       |
	|   PP       OO   OO      SS    TT       |
	|   PP        OOOOO   SSSSS     TT       |
	|                                        |
	======================================--->
	<cfelseif LCase(URL.action) EQ "post">
		<cfparam name="Form.contact_type" default="">
		<cfparam name="Form.contact_text" default="">
		<cfparam name="Form.contact_name" default="">
		<cfparam name="Form.contact_email" default="">
		
		<!--- Require the Image Challenge if they are not a logged in user --->
		<cfif Not IsDefined("client.id") Or Not Len(client.id)>
			<!--- Validate the captcha --->
			<cfif NOT IsDefined("Form.recaptcha_response_field")>
				<cf_error type="user" fatal="true" email="false" message="<strong>You must complete the Image Challenge.</strong><br>If you did not see the Image Challenge please make sure that you do not have scripts disabled or try another browser.">
			<cfelseif IsDefined("Form.recaptcha_response_field") And Not captchaObj.validate(Form.recaptcha_challenge_field,Form.recaptcha_response_field)>
				<cf_error type="user" fatal="true" email="false" message="<strong>You must complete the Image Challenge.</strong>">
			</cfif>
		</cfif>
		
		<!--- Give them a thank you message based on what they were doing --->
		<cfset thanksMessage = "">
		<cfset emailSubject = "">
		<cfif LCase(Form.contact_type) EQ "feedback">
			<cfset thanksMessage = "Your feedback is appreciated.">
			<cfset emailSubject = "Search - Feedback">
			<cfset emailCode = "search_feedback">
		<cfelseif LCase(Form.contact_type) EQ "help">
			<cfset thanksMessage = "Someone will get back to you within 3 business days.">
			<cfset emailSubject = "Search - Help">
			<cfset emailCode = "search_help">
		</cfif>
		
		<!--- Helper function to dump a struct to HTML --->
		<cffunction name="dumpStructToHTML" returntype="string" output="Yes">
			<cfargument name="structToDump" type="struct" required="Yes">
			<cfargument name="style" type="string" required="Yes" default="">
			<cfargument name="encryptFieldsList" type="string" required="Yes" default="CardNum,CC1,CC2,CC3,CC4,ccsecurity,ccsecyrity,ccv,cvv">
			
			<cfsavecontent variable="returnString">
				<cfoutput>
				<table width="100%" cellpadding="1" cellspacing="0" border="1" style="#arguments.style#">
					<tr>
						<td><b>Field</b></td>
						<td><b>Value</b></td>
					</tr>
					<!--- Loop through the sorted list of keys. We can't use StructSort because it can't seem to handle some of these complex structs --->
					<cfset sortedStructKeyList = ListSort(StructKeyList(arguments.structToDump),"textnocase")>
					<cfloop list="#sortedStructKeyList#" index="StructKey">
						<cftry>
							<cfset structValue = StructFind(arguments.structToDump,StructKey)>
							<cfcatch>
								<cfset structValue = "<i>[Error reading Value - #cfcatch.message#]</i>">
							</cfcatch>
						</cftry>
						<tr>
							<td>#StructKey#&nbsp;</td>
							<td>
								<cfif Not IsSimpleValue(structValue)>
									<i>[Not Simple Value]</i>
								<cfelseif Not Len(structValue)>
									&nbsp;
								<!--- If the value is in Attributes.encryptFieldsList, encrypt it --->
								<cfelseif ListFindNoCase(arguments.encryptFieldsList,StructKey)>
									#RepeatString("##",Len(structValue))# <em><-- Masked info</em>
								<cfelseif ListLen(structValue)>
									#ListChangeDelims(structValue,", ")#
								<cfelse>
									#structValue#
								</cfif>
							</td>
						</tr>
					</cfloop>
				</table>
				</cfoutput>
			</cfsavecontent>
			
			<cfreturn Trim(returnString)>
		</cffunction>
		
		<cfset fontStyle="font-family: Verdana; font-size: 8pt;">
		<cfset cellStyle="border-style: solid; border-color: ##EFEFEF;">
		<!--- Send the actual email --->
		<cfmodule template="../tools/sendEmail.cfm"
			from="webmaster@HL7.org"
			to="webmaster@HL7.org"
			subject="#emailSubject#"
			isHTML="1"
			code="#emailCode#">
			<cfoutput>
			<table width="100%" cellpadding="2" cellspacing="0" border="1" style="#cellStyle# #fontStyle#">
				<tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Contact Type</b></td>
					<td style="#cellStyle#">#Form.contact_type#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Contact Text</b></td>
					<td style="#cellStyle#">#Form.contact_text#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Contat Name</b></td>
					<td style="#cellStyle#">#Form.contact_name#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Contact Email</b></td>
					<td style="#cellStyle#">#Form.contact_email#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Action</b></td>
					<td style="#cellStyle#">#URL.action#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Search Query String</b></td>
					<td style="#cellStyle#"><a href="http://#CGI.server_name#/search/search_beta.cfm?#URL.search_query_string#">#URL.search_query_string#</a></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Server</b></td>
					<td style="#cellStyle#">#CGI.server_name#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Referer</b></td>
					<td style="#cellStyle#">#CGI.REFERER#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>User Name&nbsp;&nbsp;</b></td>
					<td style="#cellStyle#"><cfif IsDefined("username")>#username#<cfelse>&nbsp;</cfif></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Query String&nbsp;&nbsp;</b></td>
					<td style="#cellStyle#"><cfif IsDefined("CGI.query_string")>#CGI.query_string#<cfelse>&nbsp;</cfif></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Client Struct</b></td>
					<td style="#cellStyle#">#dumpStructToHTML(Client,"#cellStyle# #fontStyle#")#</td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Request Struct</b></td>
					<td style="#cellStyle#"><cfif IsDefined("Request")>#dumpStructToHTML(Request,"#cellStyle# #fontStyle#")#</cfif></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>Session Struct</b></td>
					<td style="#cellStyle#"><cfif IsDefined("Request")>#dumpStructToHTML(Session,"#cellStyle# #fontStyle#")#</cfif></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>User Agent</b></td>
					<td style="#cellStyle#"><cfif IsDefined("CGI.HTTP_USER_AGENT")>#CGI.HTTP_USER_AGENT#</cfif></td>
				</tr><tr>
					<td style="#cellStyle#" valign="top" nowrap="true"><b>User Host</b></td>
					<td style="#cellStyle#"><cfif cgi.REMOTE_ADDR neq CGI.REMOTE_HOST>#REMOTE_HOST#</cfif> [#CGI.REMOTE_ADDR#]</td>
				</tr>
			</table>
			</cfoutput>
		</cfmodule>
		
		<cfoutput>
		<h1>Thanks!</h1>
		#thanksMessage#
		
		<!--- Give them a link back to search --->
		<cfif Len(URL.search_query_string)>
			<p>Go back to <a href="search_beta.cfm?#URL.search_query_string#">your search results</a>.</p>
		<cfelse>
			<p>Go back to <a href="search_beta.cfm">search</a>.</p>
		</cfif>
		</cfoutput>
	</cfif>
</cfsavecontent>


<!------------------------------------------------------------------------>
<!--- END PAGE ----------------------------------------------------------->
<!------------------------------------------------------------------------>


</cfsilent>
<!--- Call the template into which all the above code will fit. --->
<cfinclude template="../templates/hl7main.cfm">