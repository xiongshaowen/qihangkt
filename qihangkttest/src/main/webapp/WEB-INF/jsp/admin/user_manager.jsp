<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}static/fonticonfont.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/user_manager.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/datetimepicker/css/bootstrap-datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrapSelect/css/bootstrap-select.min.css">
	
</head>
<body>
	<body>
	<!-- 用户管理 -->
	<div class="container-fluid">
		<div class="row">
			<div class="search-title_bar">搜索</div>
		</div>
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/userSearch.html" id="search-form" class="form-inline" method="post">
				<div  class="form-group has-feedback">    <!-- has-feedback"作相对与绝对定位用的，主要是为了在日期输入框中加（字体）图标 -->
					<labe>报名时间：</labe>
					<input class="form-control input-sm form_datetime" type="text" name="regCourseStartTime">
					<span class="iconfont icon-rili form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<labe>至</labe>
					<input class="form-control input-sm form_datetime" type="text" name="regCourseEndTime">
					<span class="iconfont icon-rili form-control-feedback"></span>
				</div>
				&nbsp;<!-- 空格 -->
				<div class="form-group">
					<labe>报名课程：</labe>
					<input class="form-control input-sm" type="text" name="regCourse" placeholder="课程名">
				</div>
				<div class="form-group">
					<labe>学员信息：</labe>
					<input class="form-control input-sm" type="text" name="userInfo" placeholder="用户名/手机号">
				</div>
			</form>
		</div>
		<div id="search_btn" class="row text-right">
			<button id="searchBtn" type="butoon" class="btn btn-default">查询</button>
			<button type="butoon" class="btn btn-default" data-toggle="modal" data-target="#addUserModal">添加</button>
			<button id="batchDelUsers" type="butoon" class="btn btn-default">删除</button>
			<button type="butoon" class="btn btn-default">导入</button>
			<button type="butoon" class="btn btn-default">导出</button>
		</div>
		<div id="content_table" class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 30px;"><input type="checkbox" class="chkall" onclick="chkall();"></td>
						<td>用户名</td>
						<td>手机号</td>
						<td>邮箱地址</td>
						<td>角色</td>
						<td>用户状态</td>
						<td style="width: 100px;">操作</td>
					</tr>
					<c:forEach items="${userDatsByPager.list}" var="user">
					<tr>
						<td><input  value="${user.id}" type="checkbox" name="chkuser"  class="chkone" onclick="chkone();"></td>
						<td>${user.username}</td>
						<td>${user.phone}</td>
						<td>${user.email}</td>
						<td>
						<c:forEach items="${user.roles}" var="role">
						   ${role.name}&nbsp;
						</c:forEach>
						</td>
						<td>${user.enable}</td>
						<td><a href="${pageContext.request.contextPath}/admin/editUser.html?id=${user.id}"  data-toggle="modal" data-target="#editUserModal">编辑</a> <a href="${pageContext.request.contextPath}/admin/delUser.html?id=${user.id}" onclick="return delSure()">删除</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="pager" class="row">
			<p class="pull-left">总共有<span>${userDatsByPager.total}</span>条记录，当前页<span> ${userDatsByPager.pageNum} / ${userDatsByPager.pages} </span>页</p>
			<div class="btn-group pull-right">
				<a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/admin/user_manager.html?pageNum=1&pageSize=10">首 页</a>
				<a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/admin/user_manager.html?pageNum=${userDatsByPager.prePage}&pageSize=10">上一页</a>
				<a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/admin/user_manager.html?pageNum=${userDatsByPager.nextPage}&pageSize=10">下一页</a>
				<a type="button" class="btn btn-default" href="${pageContext.request.contextPath}/admin/user_manager.html?pageNum=${userDatsByPager.pages}&pageSize=10">尾 页</a>
			</div>
		</div>
	</div>
     
     <!-- 模态框，用于添加，删除时的弹出框————添加 -->
     <div class="modal fade" id="addUserModal" tabindex="-1">
     	<div class="modal-dialog">
     		<div class="modal-content">
     			<div class="modal-header">
     				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
     				<h4 class="modal-title" id="myModalLabel">添加用户</h4>
     			</div>
     			<div class="modal-body">
     				<form  id="addUserForm" action="${pageContext.request.contextPath}/admin/addUser.html" method="post">
     					<div class="form-group">
     						<label>用户名:</label>
     						<input type="text" name="username" class="form-control" placeholder="用户名">
     					</div>
     					<div class="form-group">
     						<label>密码:</label>
     						<input type="password" name="password" class="form-control">
     					</div>
     					<div class="form-group">
							<label>关联的角色：</label> 
							<select name="roleIds" class="selectpicker form-control" multiple>  <!-- roleIds是一个数组或集合 -->
								<c:forEach items="${allRoles}" var="role">
									<option value="${role.id}">${role.name}</option>
								</c:forEach>
							</select>
						</div>
     					<div class="form-group">
     						<label>手机号:</label>
     						<input type="text" name="phone" class="form-control" placeholder="手机号">
     					</div>
     					<div class="form-group">
     						<label>邮箱:</label>
     						<input type="text" name="email" class="form-control" placeholder="邮箱">
     					</div>
     				</form>
     			</div>
     			<div class="modal-footer">
     				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
     				<button id="addUserBtn" addtype="button" class="btn btn-primary">添加</button>  <!-- 利用 js来提交表单，不直接手form提交 -->
     			</div>
     		</div>
     	</div>
     </div>

   <!-- Modal 编辑 -->
	<div class="modal fade" data-backdrop="false" id="editUserModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 这里的内容是动态改变的 -->
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-1.12.4.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script> 
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/static/js/user_manager.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/i18n/defaults-zh_CN.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
</body>
</html>