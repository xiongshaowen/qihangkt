$(function(){
	$(".form_datetime").datetimepicker({
		format: 'yyyy-mm-dd hh:ii:ss',
		autoclose: true,
		todayBtn: true,
		language: 'zh_CN'
		});
	//添加用户提交请求
	$("#addUserBtn").on("click",function(){
		//真正开发中，我们要做两件事，一个是前端的数据校验有效性，一个是提交表单数据
		  //提交表单数据
		  $("#addUserForm").submit();
	});
	//编辑用户，中清除模态框数据
	$("#editUserModal").on("hidden.bs.modal",function(){
		$(this).removeData("bs.modal");
	});
	//编辑用户，中重新渲染下拉选择列表插件
	$("#editUserModal").on("shown.bs.modal",function(){
		$("#editUserModal .selectpicker").selectpicker();
	})
	//批量删除，ajax请求
	$("#batchDelUsers").on("click",function(){
		//alert("batchDel")
		//1.获取到勾选了复选框的dom对象
		var checkboxes = $(".chkone:checked"); 
		//alert(checkboxes.length)
		if(checkboxes.length == 0){
			alert("必须要选一个要删除的记录？");
		}else{
			//2.先获取到勾选了的复选框中的value(user的id值）
			var userIds = new Array();
			checkboxes.each(function(){
				userIds.push(this.value);  //<td><input  value="${user.id}" type="checkbox" name="chkuser"...
			});
			alert(userIds);
			//3.通过ajax,向服务端提交批量删除用户的请求，userIds传到服务端
			var datas = JSON.stringify(userIds);
			var flag = delSure();        //下面定义的一个方法（返回布尔值）提醒是否真的删除?
			if(flag){
				$.ajax({ 
					url: "/qihangkttest/admin/batchDelUsers.html", 
					type: 'POST',
					data:{
						   uid:datas,
					},
					success: function(rs){
			           //删除成功后的回调函数
						if(rs == "success"){
							$(location).attr("href","/qihangkttest/admin/user_manager.html");
						}
			      }});
			}
			
		}
		
	});
	//糢糊查询提交请求
	$("#searchBtn").on("click",function(){
		$("#search-form").submit();
	});
	
});
function chkall(){
	$(".chkone").prop("checked",$(".chkall").prop("checked"));
}

function chkone(){
	var flag = true;
	$(".chkone").each(function(index,el){
		  if($(el).prop("checked")==false){
             flag=false;
	   }
    });
    if(flag){
    	$(".chkall").prop("checked",true);
    	return;

    }else{
    	$(".chkall").prop("checked",false);
    }

}

//编辑用户提交
function editUserFormSubmit(){
	//alert("editUser");
	$("#editUserForm").submit();
}


//问真要删除记录吗。
function delSure(){
	if(confirm("你确定要删除这条记录吗？")){
		return true;
	}else{
		return false;
	}
	
	
}
