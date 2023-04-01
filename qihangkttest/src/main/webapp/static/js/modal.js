$(function(){
	  //$("#modaltest").modal("show") 
	 
	  /*$("#modaltest").modal({
	  	  backdrop:"static",
	  	  keyboard:true             //让ESC键，关闭模态框，前提是要在div标签里加上 tabindex="-1" false禁用ESC
	  })*/

	/*$("#modalbtn").click(function(){
		//alert("xiong"); //用于测试js代码是否绑定页面的元素
		//$("#modaltest").modal("toggle")   //相当于modal(),,用于手动切换
		//$("#modaltest").modal("show")      // 
		$("#modaltest").modal("hidden")
	});*/


	//四個事件
	   //1.
	   $("#modaltest").on("show.bs.modal",function(){
             alert("showing.........")
	   });
	   //2.
	    $("#modaltest").on("shown.bs.modal",function(){
	   	   alert("shown");
	   });
	     $("#modaltest").on("hide.bs.modal",function(){
	   	     alert("hidinng.....")
	   });
	      $("#modaltest").on("hidden.bs.modal",function(){
	   	     alert("hidden")
	   });
})