<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>404 Error Page - Ace Admin</title>

		<meta name="description" content="404 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	    <link href="../../style/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
		<link href="../../css/fsnet.css" rel="stylesheet">
		<script type="text/javascript">
			$(window).load(function(){
				 window.top.$("#loading").hide();
			});
		</script>
	</head>

	<body class="no-skin">
		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- #section:settings.box -->
					<!-- /section:settings.box -->
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<!-- #section:pages/error -->
							<div class="error-container">
								<div class="well">
									<h1 class="grey lighter smaller">
										<span class="blue bigger-125">
											<i class="ace-icon fa fa-sitemap"></i>
											404
										</span>
										Page Not Found
									</h1>

									<hr />
									<h3 class="lighter smaller">We looked everywhere but we couldn't find it!</h3>

									<div>
										<form class="form-search">
											<span class="input-icon align-middle">
												<i class="ace-icon fa fa-search"></i>

												<input type="text" class="search-query" placeholder="Give it a search..." />
											</span>
											<button class="btn btn-sm" type="button">Go!</button>
										</form>

										<div class="space"></div>
										<h4 class="smaller">Try one of the following:</h4>

										<ul class="list-unstyled spaced inline bigger-110 margin-15">
											<li>
												<i class="ace-icon fa fa-hand-o-right blue"></i>
												Re-check the url for typos
											</li>

											<li>
												<i class="ace-icon fa fa-hand-o-right blue"></i>
												Read the faq
											</li>

											<li>
												<i class="ace-icon fa fa-hand-o-right blue"></i>
												Tell us about it
											</li>
										</ul>
									</div>

									<hr />
									<div class="space"></div>

									<div class="center">
										<a href="javascript:history.back()" class="btn btn-grey">
											<i class="ace-icon fa fa-arrow-left"></i>
											Go Back
										</a>

										<a href="../.././logout.do" class="btn btn-primary">
											<i class="ace-icon fa fa-tachometer"></i>
											ログイン
										</a>
									</div>
								</div>
							</div>

							<!-- /section:pages/error -->

							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

		</div><!-- /.main-container -->

		<!-- basic scripts -->

	</body>
</html>
