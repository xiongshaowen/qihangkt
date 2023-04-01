$(function(){
	$("#dbtn").mouseenter(function(){
		$(this).dropdown('toggle');

	}).mouseleave(function(){
		$(this).dropdown("toggle")
	});
	$("#dmm").mouseenter(function(){
		$("#dbtn").dropdown('toggle');
	}).mouseleave(function(){
		$("#dbtn").dropdown('toggle')
	})
})