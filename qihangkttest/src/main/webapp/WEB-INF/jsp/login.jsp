<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/common.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
</head>

<body>
  <nav id="main_nav" class="navbar navbar-default navbar-static-top"> <!-- navbar-static-top去掉导航栏的左右圆角 -->
    <div class="container">                            <!-- 不全屏，想要全屏用container-fluit -->
     <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#qh_navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>            <!-- 三个span是三根根线 -->
      </button>
      <a href="#" class="navbar-brand"><i class="iconfont icon-Windsurfing
        "></i>启航课堂</a>
      </div>
      <div id="qh_navbar" class="collapse navbar-collapse">   <!-- 光ul不能折叠，要放在按钮中 -->
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">首页</a></li>       <!-- active表选中或激活 -->
          <li><a href="#">全部课程</a></li>
          <li><a href="#">问题讨论</a></li>
          <li><a href="#">学习路线</a></li>
          <li><a href="#">资料下载</a></li>
        </ul>
        <div class="navbar-form navbar-left">                        <!-- 做表单与按钮的组合 -->
          <form accept="#" method="get">
           <div class="input-group">
             <input type="text" name="search" class="form-control" placeholder="课程名称..." />
             <span class="input-group-btn">
               <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
             </span>
           </div>
         </form>
       </div>
       <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span style="color:#337ab7;" class="glyphicon glyphicon-user"></span> 登陆</a></li>
        <li><a href="#"><span  style="color:#337ab7;" class="glyphicon glyphicon-log-in"></span> 注册</a></li>
      </ul>
    </div>
  </div>
</nav>

<!-- 登陆，注册选项卡，该区域分三部分，左右为空白，中间为选项卡（标签页） -->
<div id="loginhtml">
  <div class="row">
   <div class="col-md-7 col-sm-6 col-xs-5"></div>   
   <div class="col-md-10 col-sm-12 col-xs-14 login-col">
     <ul class="nav nav-tabs">
      <li  class="col-xs-12 active"><a href="#loginform" data-toggle="tab">登陆账号</a></li>
      <li class="col-xs-12"><a href="#regform" data-toggle="tab">注删账号</a></li>
    </ul>
    <div class="tab-content">
       <!-- 登陆 -->                                             <!-- 实现标签页的内容 -->
       <div id="loginform" class="tab-pane fade in active">
           <form action="${pageContext.request.contextPath}/login.html" method="post">
             <div class="form-group">
               <label>账号:</label>
               <input type="text" name="userInfo" class="form-control" placeholder="邮箱/手机号/用户名">
             </div>
             <div class="form-group">
               <label>密码:</label>
               <input type="password" name="password" class="form-control" >
             </div>
             <div class="checkbox">
               <label><input type="checkbox" name="remeberme">10天内自动登陆</label>
             </div>
             <div class="form-group">
               <input type="submit"  class="form-control btn btn-primary" value="登陆">
             </div>
             <div class="form-group">
                <p><a href="#">找回密码</a>  | 还没有账号？<a href="#">注册账号</a></p>
                <p style="text-align:right;text-decoration: none;">使用第三方账号登录:</p>
                <p style="text-align:right;">
                    <a href="#"><span style="font-size:30px;"class="iconfont icon-weixin"></span></a>
                    <a href="#"><span  style="font-size:30px;" class="iconfont icon-qq"></span></a>
                 </p>
             </div>
           </form>
       </div>
       <!-- 注册 -->
      <div id="regform" class="tab-pane fade">
           <form action="#" method="post">
           <div class="form-group">
               <label>手机:</label>
               <input type="text" name="username" class="form-control" placeholder="常用的手机号">
             </div>
             <div class="form-group">
               <label>用户名:</label>
               <input type="text" name="username" class="form-control" placeholder="邮箱/手机号/用户名">
             </div>
             <div class="form-group">
               <label>密码:</label>
               <input type="password" name="password" class="form-control" >
             </div>
             <div class="form-group">
               <label>手机验证码:</label>
               <div class="row">
                 <div class="col-xs-12">
                     <input type="text" name="phonenumber" class="form-control">
                 </div>
                 <div class="col-xs-12">
                     <input type="button" name="phonenumber" class="form-control btn btn-primary" value="点击获取验证码">
                 </div>
               </div>
               </div>
             <div class="form-group">
               <input type="submit"  class="form-control btn btn-primary" value="注册">
             </div>
       </div>

    </div>
  </div>
  <div class="col-md-7 col-sm-6 col-xs-5"></div>
</div>
</div>


<div class="footer hidden-xs">   <!-- 超小屏时不显示底部 -->
 <div class="footericon"><span class="iconfont icon-Windsurfing"></span></div>
 <p class="cr">Copyright ©2021 qhhangzaixian.All Rights Reserved.</p>
 <p class="cr">启航在线课程 版权所有 | <a href="">工具下载</a> | <a href="#">资料下载</a> | <a href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a></p>
</div>
<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.js"></script> 
<script type="text/JavaScript" src="${pageContext.request.contextPath}/static/js/index.js"></script>

</body>
</html>