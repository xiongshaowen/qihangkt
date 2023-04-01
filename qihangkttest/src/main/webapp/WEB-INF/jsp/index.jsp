<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>启航课堂首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/lib/html5shiv/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/static/lib/respond/respond.min.js"></script>
    <![endif]-->
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
</head>

<body>
	<nav id="main_nav" class="navbar navbar-default navbar-static-top">
		<!-- navbar-static-top去掉导航栏的左右圆角 -->
		<div class="container">
			<!-- 不全屏，想要全屏用container-fluit -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#qh_navbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
					<!-- 三个span是三根根线 -->
				</button>
				<a href="#" class="navbar-brand"><i
					class="iconfont icon-Windsurfing
            "></i>启航课堂</a>
			</div>
			<div id="qh_navbar" class="collapse navbar-collapse">
				<!-- 光ul不能折叠，要放在按钮中 -->
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<!-- active表选中或激活 -->
					<li><a href="#">全部课程</a></li>
					<li><a href="#">问题讨论</a></li>
					<li><a href="#">学习路线</a></li>
					<li><a href="#">资料下载</a></li>
				</ul>
				<div class="navbar-form navbar-left">
					<!-- 做表单与按钮的组合 -->
					<form accept="#" method="get">
						<div class="input-group">
							<input type="text" name="search" class="form-control"
								placeholder="课程名称..." /> <span class="input-group-btn">
								<button type="button" class="btn btn-primary">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</form>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/login.html"><span style="color: #337ab7;" class="glyphicon glyphicon-user"></span> 登陆</a></li>
					<li><a href="#"><span style="color: #337ab7;"
							class="glyphicon glyphicon-log-in"></span> 注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 轮播图片 -->
	<div id="carousel-qhkt" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-qhkt" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-qhkt" data-slide-to="1"></li>
			<li data-target="#carousel-qhkt" data-slide-to="2"></li>
		</ol>

		<!-- 显示图片 -->
		<div class="carousel-inner" role="listbox">

			<div class="item active"
				data-lg-img="${pageContext.request.contextPath}/static/image/slide_01_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/image/slide_01_768x410.jpg">
			</div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath}/static/image/slide_02_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/image/slide_02_768x410.jpg">
			</div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath}/static/image/slide_03_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/image/slide_03_768x410.jpg">
			</div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath}/static/image/xiong.png"
				data-xs-img="${pageContext.request.contextPath}/static/image/xiong.png">
			</div>
		</div>

		<!-- 控制轮换 -->
		<a class="left carousel-control" href="#carousel-qhkt" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel-qhkt" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!-- 课程列表缩略图 -->
	<div id="courseList" class="container">
		<div class="page-header">
			<h3 class="text-center">
				最新发布课程<a href="#">全部课程<i class="iconfont icon-dayu"></i></a>
			</h3>
			<!--  iconfont用了阿里的字体图标大于'>' -->
		</div>
		<div class="row">
			<div class="col-sm-12 col-md-6">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/static/image/jvm.jpg"
						alt="课程">
					<div class="caption">
						<h3>
							<a href="#">java系列技术之JVM调优</a>
						</h3>
						<h3 class="course_price">
							￥50.00 <a class="course_others" href="#">其它课程</a>
						</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/static/image/jvm.jpg"
						alt="课程">
					<div class="caption">
						<h3>
							<a href="#">java系列技术之JVM调优</a>
						</h3>
						<h3 class="course_price">
							￥50.00 <a class="course_others" href="#">其它课程</a>
						</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/static/image/jvm.jpg"
						alt="课程">
					<div class="caption">
						<h3>
							<a href="#">java系列技术之JVM调优</a>
						</h3>
						<h3 class="course_price">
							￥50.00 <a class="course_others" href="#">其它课程</a>
						</h3>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="thumbnail">
					<img src="${pageContext.request.contextPath}/static/image/jvm.jpg"
						alt="课程">
					<div class="caption">
						<h3>
							<a href="#">java系列技术之JVM调优</a>
						</h3>
						<h3 class="course_price">
							￥50.00 <a class="course_others" href="#">其它课程</a>
						</h3>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="footer hidden-xs">
		<!-- 超小屏时不显示底部 -->
		<div class="footericon">
			<span class="iconfont icon-Windsurfing"></span>
		</div>
		<p class="cr">Copyright ©2021 qhhangzaixian.All Rights Reserved.</p>
		<p class="cr">
			启航在线课程 版权所有 | <a href="">工具下载</a> | <a href="#">资料下载</a> | <a
				href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a>
		</p>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery-1.12.4.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/JavaScript"
		src="${pageContext.request.contextPath}/static/js/index.js"></script>

</body>
</html>