<?xml version="1.0" standalone="no"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
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
		<script src="./assets/js/vis-network.min.js"> </script>
		<link href="./assets/css/vis-network.min.css" rel="stylesheet" type="text/css" />

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
										<li>
											<a href="example-mainpage.html">Overview</a>
										</li>
										<li >
											<a href="example-details.html">Detailed Walkthrough</a>
										</li>
										<li class="active">
											<a>Instances</a>
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



									<h2>Instance relationships</h2>
									<div id="mynetwork"/>

									<script type="text/javascript">
<xsl:text>
										// create an array with nodes
										var nodes = new vis.DataSet([
										{id: 'mr1', label: 'Initial Prescription'},
										{id: 'mr1i1', label: 'Administration request - Morning'},
										{id: 'mr1i2', label: 'Administration request - Lunch'},
										{id: 'ma1', label: 'Administration report - Morning'},
										{id: 'ma2', label: 'Administration report - Lunch'},
										{id: 'ma2.1', label: 'Initial administration report'},
										{id: 'ma2.2', label: 'Final administration report'},
										]);

										// create an array with edges
										var edges = new vis.DataSet([
										{from: 'mr1i1', to: 'mr1', label: 'basedOn'},
										{from: 'mr1i2', to: 'mr1', label: 'basedOn'},
										{from: 'ma2.1', to: 'ma2', label: 'versionOf'},
										{from: 'ma2.2', to: 'ma2', label: 'versionOf'},
										{from: 'ma1', to: 'mr1i1', label: 'request'},
										{from: 'ma2', to: 'mr1i2', label: 'request'},
										]);

										// create a network
										var container = document.getElementById('mynetwork');
										var data = {
										nodes: nodes,
										edges: edges
										};
										var options = {height:'200px', layout:{hierarchical: {direction:'LR'} } };
										var network = new vis.Network(container, data, options);

</xsl:text>
									</script>

									<div>



										<h2>Resources</h2>
<xsl:for-each-group select="ExampleScenario/instance/resourceType" group-by="@value">
<xsl:apply-templates select="../resourceType"/>
</xsl:for-each-group>
					


									</div>

								</div>
							</div>
							<!-- /inner-wrapper -->
						</div>
						<!-- /row -->
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
								(R)(c) HL7.org 2011+. FHIR Release 4 Candidate (v3.1.0-11982) generated on Sun, May 7, 2017 00:21+0200. <a href="qa.html">QA Page</a>
								<br/>
								<span style="color: #FFFF77">
									Links: <a style="color: #81BEF7" href="http://hl7.org/fhir/search.cfm">Search <img src="external.png" style="text-align: baseline"/>
									</a> | 
									<a style="color: #81BEF7" href="history.html">Version History</a> | 
									<a style="color: #81BEF7" href="toc.html">Table of Contents</a> | 
									<a style="color: #81BEF7" href="credits.html">Credits</a> | 
									<a style="color: #81BEF7" href="http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2FSTU3%2Faccount.html&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2Faccount.html">Compare to DSTU3 <img src="external.png" style="text-align: baseline"/>
									</a> |                
									<a rel="license" style="color: #81BEF7" href="license.html">
										<img src="cc0.png" alt="CC0" style="border-style: none;"/>
									</a> | 
									<a style="color: #81BEF7" href="http://hl7.org/fhir-issues" target="_blank">Propose a change <img src="external.png" style="text-align: baseline"/>
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




				<script src="./assets/js/jquery.js"> </script>
				<script src="./assets/js/jquery-ui.min.js"> </script>
				<script>
<xsl:text>
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
</xsl:text>
				</script>

				
			</body>
		</html>



	</xsl:template>


	<xsl:template  match="resourceType">
<br/>
 	<xsl:variable name="thisResourceType" select="./@value"/>
		<h3>
			<xsl:value-of select="$thisResourceType"/>
		</h3>

		<table class="grid">
			<tbody>
				<tr>
					<th>Artifact</th>
					<th>Description</th>
					<th>Version</th>
					<th>Created by step</th>
					<th>Creating actor</th>
				</tr>
				<xsl:apply-templates select="../../instance[resourceType/@value=$thisResourceType]"/>

			</tbody>
		</table>												

	</xsl:template>


	<xsl:template  match="instance">
		<tr>
		
<!--		
&lt;a name=&quot;
<xsl:value-of select="resourceId/@value"/>
&quot;&gt;
&lt;/a&gt;
-->


			<td>
 <a name="{resourceId/@value}"></a><b><xsl:value-of select="name/@value"/></b>
			</td>
			<td><xsl:value-of select="description/@value"/></td>
			<td style="background-color:whitesmoke" colspan="3"/>
		</tr>
		
	</xsl:template>



</xsl:stylesheet>
