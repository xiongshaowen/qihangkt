$(function(){
	$("#cllbtn").click(function(){
		$("#clp1").collapse({
			parent:$("#pg")  //或"#pg"
		}).collapse('toggle');
	});
//事件，正显示，显示，关闭折叠，关闭了
   $("#clp3").on("show.bs.collapse",function(){
   	  alert("showing.....");
   });
   $("#clp3").on("shown.bs.collapse",function(){
   	  alert("shown");
   });
   $("#clp3").on("hide.bs.collapse",function(){
   	  alert("hidding.....");
   });
   $("#clp3").on("hidden.bs.collapse",function(){
   	  alert("hidden");
   });

})