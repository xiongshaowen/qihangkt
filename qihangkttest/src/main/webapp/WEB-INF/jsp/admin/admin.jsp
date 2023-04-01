<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>admin</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/admin.css">
</head>

<body>
    <!-- 顶部 -->
    <div id="admin_top" class="container-fluid">
     <div class="row"><!-- 放在一行中 -->
      <div class="navbar navbar-inverse navbar-static-top">  <!-- 下面把一行三等分，中间的空白，已定制bootstrap栅格为24格，每等分8格 -->
         <div class="navbar-header col-md-8">
            <span class="navbar-brand"><i class="iconfont icon-Windsurfing"></i>启航课堂后台管理页</span>
        </div>
        <div class="col-md-8 col-md-offset-8 login-info text-right">     <!-- offset－8向右偏移8格，等于是不用写出中间8格的格的div -->
            <i class="iconfont icon-character"></i> admin 
            <i class="iconfont icon-rili"></i> 2022-01-01
            <a href="#" class="pull-right"><i class="iconfont icon-dianyuan"></i></a>
        </div>

    </div>
</div>  
</div>

<!-- 侧边栏 -->
<div id="sidle_bar">
    <div class="sidlebar_title">
       <p>
           <span>导航模块 / </span>
           <span>Nav Module</span>
       </p>  

   </div>
   <!-- 显示侧边栏内容区域__折叠菜单 -->
   <div class="sidlebar_content navbar-fixed-bottom">    <!-- 该navbar固定在底部，即高为全屏高，随网页高的改变页改变 -->
      <a href="#collapse_system" data-toggle="collapse"><i class="iconfont icon-jiahao1"></i>系统设置</a>
      <ul id="collapse_system" class="collapse collapse_all">   <!-- in collapse_all默认展开折叠的东西 -->
          <li><a href="" data-iframesrc="${pageContext.request.contextPath}/admin/user_manager.html?pageNum=1&pageSize=10"><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href="" data-iframesrc="${pageContext.request.contextPath}/admin/role_manager.html"><i class="iconfont icon-vertical_line"></i>角色管理</a>
          <li><a href="" data-iframesrc="${pageContext.request.contextPath}/admin/resources_manager.html"><i class="iconfont icon-vertical_line"></i>資源管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
      </ul>
      <a href="#collapse_video" data-toggle="collapse"><i class="iconfont icon-jiahao1"></i>视频设置</a>
      <ul id="collapse_video" class="collapse collapse_all">   <!-- in collapse_all默认展开折叠的东西 -->
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
      </ul>

      <a href="#collapse_course" data-toggle="collapse"><i class="iconfont icon-jiahao1"></i>课程管理</a>
      <ul id="collapse_course" class="collapse collapse_all">   <!-- in collapse_all默认展开折叠的东西 -->
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
      </ul>

      <a href="#collapse_res" data-toggle="collapse"><i class="iconfont icon-jiahao1"></i>资料管理</a>
      <ul id="collapse_res" class="collapse collapse_all">   <!-- in collapse_all默认展开折叠的东西 -->
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
          <li><a href=""><i class="iconfont icon-vertical_line"></i>用户管理</a>
      </ul>
   </div>
</div>

<!-- 路径导航 -->
<div id="path_nav">
   <ol class="breadcrumb">
       <li><a href="">home</a></li>
       <li><a href="">library</a></li>
       <li class="active">Date</li>
   </ol>
</div>

<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script> 
<script type="text/JavaScript" src="${pageContext.request.contextPath}/static/js/admin.js"></script>

</body>
</html>
<!-- iframe 内联框架 navbar-fixed-bottom是框架组件，覆盖上面所有元素,使之原页面不能用，所要要在样式表中设z-index:-1 -->
<iframe id="iframe-content" src="${pageContext.request.contextPath}/static/html/admin-welcome.html" class="navbar-fixed-bottom" frameborder="no" scrolling="auto" width="100%" height="100%"></iframe>