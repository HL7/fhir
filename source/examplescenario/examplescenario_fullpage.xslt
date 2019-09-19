<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:fhir="http://hl7.org/fhir" version="1.0">
	<xsl:output method="html" />
	<xsl:param name="pref" select="pref" />
	<xsl:template match="/">
		<head>
			<meta content="text/html;charset=utf-8" http-equiv="Content Type" />
			<title><xsl:value-of select="$pref"/></title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0" />
			<meta name="author" content="http://hl7.org/fhir" />
			<link href="fhir.css" rel="stylesheet" />
			<!-- Bootstrap core CSS -->
			<link href="assets/css/bootstrap.css" rel="stylesheet" />
			<link href="assets/css/bootstrap-fhir.css" rel="stylesheet" />
			<!-- Project extras -->
			<link href="assets/css/project.css" rel="stylesheet" />
			<link href="assets/css/pygments-manni.css" rel="stylesheet" />
			<link href="assets/css/jquery-ui.css" rel="stylesheet" />
			<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
			<!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
			<!-- Favicons -->
			<link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png" />
			<link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png" />
			<link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png" />
			<link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png" />
			<link rel="shortcut icon" href="assets/ico/favicon.png" />
			<script src="./assets/js/jquery.js"/>
			<script src="./assets/js/jquery-ui.min.js">
			</script>
			<script src="./assets/js/bootstrap.min.js">
			</script>
			<script type="text/javascript">//<![CDATA[
$(window).load(function(){
$("[data-tab]").on('click', function() {
    var tab = $(this).attr('data-tab'),
        target = $(this).attr('href');
    $('ul.nav a[href="' + tab + '"]').tab('show');
    $('html, body').animate({
        scrollTop: $(target).offset().top
    }, 10);    
});
});//]]></script>
		</head>
		<body>
			<div id="segment-header" class="segment">
				<!-- segment-header -->
				<div class="container">
					<!-- container -->
					<a id="logo" no-external="true" href="http://hl7.org/fhir">
							<img src="assets/images/fhir-logo.png" />					</a>
					<div id="hl7-status">
					</div>
					<div id="hl7-nav">
						<a id="hl7-logo" no-external="true" href="http://www.hl7.org">
							<img height="50" alt="visit the hl7 website" width="42" src="assets/images/hl7-logo.png" />
						</a>
					</div>
					<div id="hl7-search">
						<a id="hl7-search-lnk" no-external="true" href="http://hl7.org/fhir/search.cfm">
						</a>
					</div>
				</div>
				<div class="container">
					<!-- container -->
				</div>
			</div>
			<!-- /segment-header -->
			<div id="segment-navbar" class="segment">
				<!-- segment-navbar -->
				<div id="stripe">
				</div>
				<div class="container">
					<!-- container -->
					<!-- HEADER CONTENT -->
					<nav class="navbar navbar-inverse">
						<div class="container">
							<button data-target=".navbar-inverse-collapse" class="navbar-toggle" data-toggle="collapse" type="button">
								<span class="icon-bar" />
								<span class="icon-bar" />
								<span class="icon-bar" />
							</button>
							<a class="navbar-brand hidden" href="{{site.data.fhir.path}}index.html">FHIR</a>
							<div class="nav-collapse collapse navbar-inverse-collapse">
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

							<div xmlns="http://www.w3.org/1999/xhtml" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://hl7.org/fhir ../../../../schema/fhir-single.xsd">




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
															<a data-toggle="tab" href="#sequence">Sequence</a>
														</li>
														<li>
															<a data-toggle="tab" href="#details">Details</a>
														</li>
														<li>
															<a data-toggle="tab" href="#resources">Resources</a>
														</li>
													</ul>
													<div class="tab-content">
														<div id="sequence" class="tab-pane fade in active">
															<h3>
																<a name="description" />
																Description
															</h3>
															<p>
																<xsl:value-of select="/fhir:ExampleScenario/fhir:description/@value" />
															</p>
															<h4>
																<a name="preconditions" />
																Pre-Conditions
															</h4>
															<p>
																<xsl:value-of select="/fhir:ExampleScenario/fhir:process/fhir:preConditions/@value" />
															</p>
															<h4>
																<a name="postconditions" />
																Post Conditions
															</h4>
															<p>
																<xsl:value-of select="/fhir:ExampleScenario/fhir:process/fhir:postConditions/@value" />
															</p>
															<p />
<!--
															<xsl:value-of select="unparsed-text('./output/images/current-diagram.svg')" disable-output-escaping="yes" />
-->
															<xsl:copy-of select="document('./output/images/current-diagram.svg')"/>


														</div>



														<div id="details" class="tab-pane fade">
															<h2>
																<a name="details" />
																Details
															</h2>
															<div>
																<table class="cols">
																	<tr>
																		<td>
																			Publisher: (link?)
																			<xsl:value-of select="/fhir:ExampleScenario/fhir:publisher/@value" />
																		</td>
																		<td>
																			<a href="versions.html#maturity">Status</a>
																			:
																			<xsl:value-of select="/fhir:ExampleScenario/fhir:status/@value" />
																		</td>
																		<td>
																			<a href="versions.html#maturity">Experimental</a>
																			:
																			<xsl:value-of select="/fhir:ExampleScenario/fhir:experimental/@value" />
																		</td>
																		<td>
																			<a href="versions.html#maturity">Copyright</a>
																			:
																			<xsl:value-of select="/fhir:ExampleScenario/fhir:copyright/@value" />
																		</td>
																	</tr>
																</table>
																<table class="cols">
																	<tr>
																		<td>
																			<a href="versions.html#maturity">Purpose</a>
																			:
																			<xsl:value-of select="/fhir:ExampleScenario/fhir:purpose/@value" />
																		</td>
																	</tr>
																</table>
																<p />
																<p />
															</div>
															<div>
																<h3>
																	<a name="Actors" />
																	Actors
																</h3>
																<table class="grid">
																	<tbody>
																		<tr>
																			<th>Name</th>
																			<th>Type</th>
																			<th>Description</th>
																		</tr>
																		<xsl:apply-templates select="/fhir:ExampleScenario/fhir:actor" />
																	</tbody>
																</table>
																<p />
																<p />
															</div>
															<div>
																<h3>
																	<a name="flow" />
																	Process Flow -
																	<xsl:value-of select="/fhir:ExampleScenario/fhir:process/fhir:description/@value" />
																</h3>
																<xsl:apply-templates select="/fhir:ExampleScenario/fhir:process" />
															</div>
														</div>
														<div id="resources" class="tab-pane fade">
															<h3>Resources</h3>
															<h2>Resources</h2>
															<xsl:for-each-group select="/fhir:ExampleScenario/fhir:instance/fhir:resourceType" group-by="@value">
																<xsl:apply-templates select="../fhir:resourceType" />
															</xsl:for-each-group>
														</div>
													</div>
													<!-- /row -->
													<!--									
								</div>
-->
												</div>
												<!-- /container -->
											</div>
										</div>
									</div>
								</div>
							</div>




						</div>  <!-- /inner-wrapper -->
					</div>  <!-- /row -->
				</div>  <!-- /container -->
			</div>  <!-- /segment-content -->

			<script type="text/javascript" src="jquery.js"> </script>
			<script type="text/javascript" src="assets/js/jquery-ui.min.js"> </script>
			<script type="text/javascript">
				try {
				var currentTabIndex = sessionStorage.getItem('fhir-resource-tab-index');
				} catch(exception) {
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
				} catch(exception) {
				}
				}
				});
			</script>
			<div id="segment-footer" class="segment">  <!-- segment-footer -->
				<div class="container">  <!-- container -->
					<div class="inner-wrapper">
						<p>
							&#169; Based on FHIR.
							<br/>
							<span style="color: #FFFF77">
								Links: <a style="color: #81BEF7" href="history.html">Version History</a> |
								<a style="color: #81BEF7" href="toc.html">Table of Contents</a> |
								<a style="color: #81BEF7" rel="license" href="{{site.data.fhir.path}}license.html"><img style="border-style: none;" alt="CC0" src="cc0.png"/></a> |
							</span>
						</p>
					</div>  <!-- /inner-wrapper -->
				</div>  <!-- /container -->
			</div>  <!-- /segment-footer -->

			<div id="segment-post-footer" class="segment hidden">  <!-- segment-post-footer -->
				<div class="container">  <!-- container -->
				</div>  <!-- /container -->
			</div>  <!-- /segment-post-footer -->

			<!-- JS and analytics only. -->
			<!-- Bootstrap core JavaScript
  ================================================== -->
			<!-- Placed at the end of the document so the pages load faster -->
			<script type="text/javascript" src="assets/js/jquery.js"> </script>     <!-- note keep space here, otherwise it will be transformed to empty tag -> fails -->
			<script type="text/javascript" src="assets/js/bootstrap.min.js"> </script>
			<script type="text/javascript" src="assets/js/respond.min.js"> </script>
			<script type="text/javascript" src="assets/js/fhir.js"> </script>

			<!-- Analytics Below
  ================================================== -->
		</body>	  

	</xsl:template>

	<xsl:template match="fhir:actor">
		<tr>
			<td>
				<b>
					<xsl:value-of select="fhir:name/@value" />
				</b>
			</td>
			<td>
				<xsl:value-of select="fhir:type/@value" />
			</td>
			<td>
				<xsl:value-of select="fhir:description/@value" />
			</td>
		</tr>
	</xsl:template>


	<xsl:template match="/fhir:ExampleScenario/fhir:process">
		<!--		<h3><xsl:value-of select="title/@value"/></h3> <br/>  -->
		<div class="container">
					<!-- Áreas -->
					<div>
						<div>Main Flow</div>
						<!-- /Área -->
  <table class="cols">
    <tbody>
      <tr>
        <th>Step</th>
        <th>Description</th>
        <th>Operation</th>
        <th>Request</th>
        <th>Response</th>
        <th>z</th>
        <th>x</th>
        <th>x</th>
        <th>x</th>
      </tr>

									<xsl:apply-templates select="fhir:step" />
					
    </tbody>
  </table>

					</div>
				</div>
	</xsl:template>


	<xsl:template match="fhir:process">
		<xsl:value-of select="fhir:title/@value" />
			<xsl:apply-templates select="./fhir:step" />
	</xsl:template>


	<xsl:template match="fhir:step">
		<!-- Each Step -->
      <tr>
				<a href="#{position()}" />
				<xsl:apply-templates select="./fhir:operation" />
	  </tr>
	</xsl:template>

	<xsl:template match="fhir:operation">
		<td>
		<a name="p2">
			<xsl:value-of select="fhir:number/@value" />
		<xsl:value-of select="fhir:name/@value" />
		</a>
		</td>
		<td>
		<xsl:value-of select="fhir:description/@value" />
		</td>
		<td>
		RQ:<xsl:apply-templates select="./fhir:request" />
		
		</td>
		<td>
		RP:<xsl:apply-templates select="./fhir:response" />
		</td>
	</xsl:template>

	
	
	
	
	<xsl:template match="alternative">
		<a name="p2">
			Alternative:
			<xsl:value-of select="fhir:number/@value" />
		</a>
		<xsl:value-of select="fhir:name/@value" />
		<xsl:value-of select="fhir:description/@value" />
		<!-- IF STEP IS ALTERNATIVE -->
				<xsl:apply-templates select="./fhir:option" />
				<!--
				<div class="accordion-group">
						<div class="accordion-heading ponto">
							<a class="accordion-toggle" data-toggle="collapse" href="{position()}">Option1 #1-1-1</a>
						</div>
						<div class="accordion-body" id="{position()}">
						<xsl:apply-templates select="./option"/>
						</div>
					</div>
					-->
	</xsl:template>
	<xsl:template match="option">
		<xsl:variable name="id" select="position()" />
		<xsl:variable name="optionname" select="./fhir:description/@value" />
				<a class="accordion-toggle" data-parent="{$id}" data-toggle="collapse" href="#{$id}">
					<xsl:value-of select="$id" />
					-
					<xsl:value-of select="./fhir:description/@value" />
				</a>
			<!-- Serviços -->
				<xsl:apply-templates select="./*" />
			<!-- /Serviços -->
	</xsl:template>
	<xsl:template match="pause">(pause)</xsl:template>

	<xsl:template match="fhir:request">
		<b>Request</b>
		(
		<xsl:value-of select="../fhir:receiver/@value" />
		-
		<xsl:value-of select="../fhir:initiator/@value" />
		):
		<xsl:apply-templates select="./fhir:resourceId" />
		<br/>
	</xsl:template>

	<xsl:template match="fhir:response">
		<b>Response</b>
		(
		<xsl:value-of select="../fhir:receiver/@value" />
		-
		<xsl:value-of select="../fhir:initiator/@value" />
		):
		<xsl:apply-templates select="./fhir:resourceId" />
		<br/>
	</xsl:template>
	<xsl:template match="fhir:resourceId">
		<xsl:variable name="iid" select="./@value" />
		<a href="example-instances.html#{/fhir:ExampleScenario/fhir:instance[fhir:resourceId/@value=$iid]/fhir:resourceId/@value}">
			<xsl:value-of select="/fhir:ExampleScenario/fhir:instance[fhir:resourceId/@value=$iid]/fhir:name/@value" />
		</a>
	</xsl:template>

	<xsl:template match="fhir:resourceType">
		<p />
		<xsl:variable name="thisResourceType" select="./@value" />
		<h3>
			<xsl:value-of select="$thisResourceType" />
		</h3>
		<table class="grid">
			<tbody>
				<tr>
					<th>Artifact</th>
					<th>Version</th>
					<th>Description</th>
					<th>Created by step</th>
					<th>Creating actor</th>
				</tr>
				<xsl:apply-templates select="../../fhir:instance[fhir:resourceType/@value=$thisResourceType]" />
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="fhir:instance">
	
		<xsl:variable name="thisResourceId" select="./fhir:resourceId/@value" />
		<xsl:variable name="versions" select="count(./fhir:version)" />

		<tr >
			<td rowspan="{$versions+1}">
				<a name="{resourceId/@value}" href="../fhir:examples/{resourceId/@value}">
					<b>
						<xsl:value-of select="fhir:name/@value" />
					</b>
				</a>
			</td>

			<td>
(<xsl:value-of select="$versions"/>)
			</td>
			<td>
				<b><xsl:value-of select="fhir:description/@value" /></b>
			</td>
			<td  colspan="3">
						
			</td>		
		</tr>		
	        <xsl:apply-templates select="./fhir:version" />		
		

	</xsl:template>
	

	<xsl:template match="fhir:version">

		<tr>
			<td>
		<xsl:value-of select="fhir:versionId/@value" />
			</td>
			<td>
		<xsl:value-of select="fhir:description/@value" />
			</td>
		</tr>

	</xsl:template>
			

	
</xsl:stylesheet>
