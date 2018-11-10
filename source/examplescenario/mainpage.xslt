<?xml version="1.0" standalone="no"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
			<head>
				<title>
					<xsl:value-of select="/ExampleScenario/title/@value"/>
				</title>
<!--				<meta http-equiv="refresh" content="5"/>  -->
				<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
				<meta name="author" content="http://hl7.org/fhir"/>

				<link rel="stylesheet" href="fhir.css"/>
				<link rel="Prev" href="http://hl7.org/fhir/account.html"/>

				<!-- Bootstrap core CSS -->
				<link rel="stylesheet" href="./dist/css/bootstrap.css"/>
				<link rel="stylesheet" href="./assets/css/bootstrap-fhir.css"/>

				<!-- Project extras -->
				<link rel="stylesheet" href="./assets/css/project.css"/>
				<link rel="stylesheet" href="./assets/css/pygments-manni.css"/>
				<link rel="stylesheet" href="./assets/css/jquery-ui.css"/>

				<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
				<!-- [if lt IE 9]>
  <script src="./assets/js/html5shiv.js"></script>
  <script src="./assets/js/respond.min.js"></script>
  <![endif] -->

				<!-- Favicons -->
				<link sizes="144x144" rel="apple-touch-icon-precomposed" href="./assets/ico/apple-touch-icon-144-precomposed.png"/>
				<link sizes="114x114" rel="apple-touch-icon-precomposed" href="./assets/ico/apple-touch-icon-114-precomposed.png"/>
				<link sizes="72x72" rel="apple-touch-icon-precomposed" href="./assets/ico/apple-touch-icon-72-precomposed.png"/>
				<link rel="apple-touch-icon-precomposed" href="./assets/ico/apple-touch-icon-57-precomposed.png"/>
				<link rel="shortcut icon" href="./assets/ico/favicon.png"/>

			</head>

			<body>
				<div id="segment-header" class="segment">
					<!-- segment-header -->
         <div class="container"><a id="logo" no-external="true" href="http://hl7.org/fhir"><img alt="logo fhir" src="assets/images/fhir-logo-www.png"></img></a><div id="hl7-status"><b>Mobile Medication Administration</b></div>
            <div id="hl7-nav"><a id="hl7-logo" no-external="true" href="http://www.hl7.org"><img height="50" alt="visit the hl7 website" width="42" src="assets/images/hl7-logo.png"></img></a></div>
            <div id="hl7-search"><a id="hl7-search-lnk" no-external="true" href="http://hl7.org/fhir/search.cfm"><img alt="Search FHIR" src="assets/images/search.png"></img></a></div>
         </div>
					<div class="container">
						<!-- container -->
					</div>
				</div>
				<!-- /segment-header -->

				<div id="segment-navbar" class="segment">
					<!-- segment-navbar -->
					<div id="stripe"> </div>
					<div class="container">
						<!-- container -->
						<!-- HEADER CONTENT -->

						<nav class="navbar navbar-inverse">
							<div class="container">
								<button data-target=".navbar-inverse-collapse" data-toggle="collapse" type="button" class="navbar-toggle">
									<span class="icon-bar"> </span>
									<span class="icon-bar"> </span>
									<span class="icon-bar"> </span>
								</button>
								<a href="index.html" class="navbar-brand hidden">FHIR</a>
								<div class="nav-collapse collapse navbar-inverse-collapse">
									<ul class="nav navbar-nav">
										<li>
											<a href="./index.html">Home</a>
										</li>
										<li>
											<a href="./modules.html">Getting Started</a>
										</li>
										<li>
											<a href="./documentation.html">Documentation</a>
										</li>
										<li>
											<a href="./resourcelist.html">Resources</a>
										</li>
										<li>
											<a href="./profilelist.html">Profiles</a>
										</li>
										<li>
											<a href="./extensibility-registry.html">Extensions</a>
										</li>
										<li>
											<a href="./operationslist.html">Operations</a>
										</li>
										<li>
											<a href="./terminologies-systems.html">Terminologies</a>
										</li>
									</ul>
								</div>
								<!-- /.nav-collapse -->
							</div>
							<!-- /.container -->
						</nav>
						<!-- /.navbar -->

						<!-- /HEADER CONTENT -->				
					</div>
					<!-- /container -->
				</div>
				<!-- /segment-navbar -->


				<div id="segment-breadcrumb" class="segment">
					<!-- segment-breadcrumb -->
					<div class="container">
						<!-- container -->	
						<ul class="breadcrumb">
							<li>
								<a href="???medications-module.html???">
									<img src="medication.png"/> ???Medication???</a>
							</li>
							<li>
								<b>???Workflows???</b>
							</li>
							<li>
								<b>
									<xsl:value-of select="/ExampleScenario/process/title/@value"/>
								</b>
							</li>
							<!-- account.html / resource / Account -->

						</ul>	
					</div>
					<!-- /container -->
				</div>
				<!-- /segment-breadcrumb -->

				<div id="segment-content" class="segment">
					<!-- segment-content -->
					<div class="container">
						<!-- container -->
						<div class="row">
							<div class="inner-wrapper">
								<!-- CONTENT CONTENT -->

								<div class="col-12">

									<ul class="nav nav-tabs">
										<li class="active">
											<a href="#">Overview</a>
										</li>
										<li>
											<a href="example-details.html">Detailed Walkthrough</a>
										</li>
										<li>
											<a href="example-instances.html">Instances</a>
										</li>
									</ul>

									<h1 class="self-link-parent">
										<span class="sectioncount">???8.12???<a name="8.12"> </a>
										</span>
										<xsl:value-of select="/ExampleScenario/process/title/@value"/>
										<a href="account.html#8.12" title="link to here" class="self-link">
											<img src="assets/images/link.svg" width="20" class="self-link" height="20"/>
										</a>
									</h1>

									<table class="cols">
										<tr>
											<td>Publisher: (link?) <xsl:value-of select="/ExampleScenario/publisher/@value"/>
											</td>
											<td>
												<a href="versions.html#maturity">Status</a>: <xsl:value-of select="/ExampleScenario/status/@value"/>
											</td>
											<td>
												<a href="versions.html#maturity">Experimental</a>: <xsl:value-of select="/ExampleScenario/experimental/@value"/>
											</td>
											<td>
												<a href="versions.html#maturity">Copyright</a>: <xsl:value-of select="/ExampleScenario/copyright/@value"/>
											</td>
										</tr>

									</table>
									<table  class="cols">
										<tr>
											<td>
												<a href="versions.html#maturity">Purpose</a>: <xsl:value-of select="/ExampleScenario/purpose/@value"/>
											</td>
										</tr>
									</table>

									<br/>
									<h2>Description</h2>
									<p>
										<xsl:value-of select="/ExampleScenario/description/@value"/>
									</p>
									<h4>Pre-Conditions</h4>
									<p>
										<xsl:value-of select="/ExampleScenario/process/preConditions/@value"/>
									</p>

									<h4>Post Conditions</h4>
									<p>
										<xsl:value-of select="/ExampleScenario/process/postConditions/@value"/>
									</p>
									<br/>


									<h2>Workflow Sequence</h2>

									<img alt="Interaction diagram showing flow" src="diagram.png" usemap="#diagram_map"/>

<xsl:value-of select="unparsed-text('./diagram.cmapx')" disable-output-escaping="yes"/>



								</div>
							</div>
							<!-- /inner-wrapper -->
						</div>
						<!-- /row -->



						<br/>
						<br/>	


						<br/>
						<br/>
						<br/>















					</div>
					<!-- /container -->



				</div>
				<!-- /segment-content -->


				<div id="segment-footer" class="segment">
					<!-- segment-footer -->
					<div class="container">
						<!-- container -->
						<div class="inner-wrapper">
							<p>
          &#169; 2015+ myself. Based on FHIR version (3.1.0-12171).  IG generated on Mon, Jun 26, 2017 20:38+0200.
								<br/>
								<span style="color: #FFFF77">
          Links: <a style="color: #81BEF7" href="history.html">Version History</a> |
									<a style="color: #81BEF7" href="toc.html">Table of Contents</a> |
									<a style="color: #81BEF7" rel="license" href="http://hl7.org/fhir/stu3/license.html">
										<img style="border-style: none;" alt="CC0" src="cc0.png"/>
									</a> |
									<a style="color: #81BEF7" target="_blank" href="http://hl7.org/fhir-issues">Propose a change <img alt="external" style="text-align: baseline" src="external.png"/>
									</a>
								</span>
							</p>
						</div>
						<!-- /inner-wrapper -->
					</div>
					<!-- /container -->
				</div>
				<!-- /segment-footer -->


				<!-- disqus thread -->
				<!-- disqus -->
				<!-- end disqus -->        

				<div id="segment-post-footer" class="segment hidden">
					<!-- segment-post-footer -->
					<div class="container">
						<!-- container -->
					</div>
					<!-- /container -->
				</div>
				<!-- /segment-post-footer -->

				<!-- JS and analytics only. -->
				<!-- Bootstrap core JavaScript
================================================== -->
				<!-- Placed at the end of the document so the pages load faster -->
				<script src="./assets/js/jquery.js"> </script>
				<!-- note keep space here, otherwise it will be transformed to empty tag -> fails -->
				<script src="./dist/js/bootstrap.min.js"> </script>
				<script src="./assets/js/respond.min.js"> </script>

				<script src="./assets/js/fhir.js"> </script>

				<!-- Analytics Below
================================================== -->




				<script src="./assets/js/jquery-1.11.1.js"> </script>
				<script src="./assets/js/jquery-ui.min.js"> </script>
				<script>
try {
  var currentTabIndex = sessionStorage.getItem('fhir-resource-tab-index');
}
catch(exception){ 
}

if (!currentTabIndex)
  currentTabIndex = '0';

$( '#tabs' ).tabs({
         active: currentTabIndex,
         activate: function( event, ui ) {
             var active = $('.selector').tabs('option', 'active');
             currentTabIndex = ui.newTab.index();
             document.activeElement.blur();
             try {
               sessionStorage.setItem('fhir-resource-tab-index', currentTabIndex);
             }
             catch(exception){ 
             }
         }
     });
				</script>

			</body>
		</html>

	</xsl:template>




</xsl:stylesheet>
