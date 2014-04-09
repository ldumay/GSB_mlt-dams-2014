<?php
	$repInclude = './include/';
	// Début de Page
  	require($repInclude . "_entete.inc.html");
	?>

	<body>

		<!-- Header -->
		<header class="header navbar navbar-fixed-top" role="banner">
			<?php require($repInclude . "top.navbar.inc.php"); ?>
		</header> <!-- /.header -->

		<div id="container">
			<div id="sidebar" class="sidebar-fixed">
				<div id="sidebar-content">
					<?php require($repInclude.'_sommaire.inc.php'); ?>
				</div>
				<div id="divider" class="resizeable"></div>
			</div>
			<!-- /Sidebar -->

			<div id="content">
				<div class="container">
					<!--=== Page Header ===-->
					<div class="page-header">
						<div class="page-title">
							<h3>Dashboard</h3>
							<span>Good morning, John!</span>
						</div>
					</div>
					<!-- /Page Header -->

					<!--=== Page Content ===-->
					<div class="row">
						<!--=== Example Box ===-->
						<div class="col-md-12">
							<div class="widget box">
								<div class="widget-header">
									<h4><i class="icon-reorder"></i> Example Box</h4>
								</div>
								<div class="widget-content">
									<p>Lorem Ipsum.</p>
								</div>
							</div>
						</div> <!-- /.col-md-12 -->
						<!-- /Example Box -->
					</div> <!-- /.row -->
					<!-- /Page Content -->

				</div>
				<!-- /.container -->

			</div>
		</div>

	</body>
</html>