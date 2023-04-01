//折叠功能的两个事件：展开，隐藏。这里我们实现，展开后，显示‘-’号，隐藏（收缩）后，显示‘+’号。
$(function(){
	$('.collapse_all').on('hidden.bs.collapse',function(){
        var a = $(this).prev();   //获取到上一个兄弟节点的dom对象（因为字体图标（+-）为i标签中，它又位于折叠菜单ul对象的兄弟）
		var i = a.children();
		i.removeClass('icon-hengxian');
		i.addClass('icon-jiahao1');
	});
	$('.collapse_all').on('shown.bs.collapse',function(){
		var a = $(this).prev();   //获取到上一个兄弟节点的dom对象（因为字体图标（+-）为i标签中，它又位于折叠菜单ul对象的兄弟）
		var i = a.children();
		i.removeClass('icon-jiahao1');
		i.addClass('icon-hengxian');
	});
	//使所有的菜单(a超链接－有单击事件）失去点击事件，因为没有设置该功能，我们点击每一个折叠菜单项都会刷新一次页面，因为有a href=""
	$('.collapse_all > li > a').click(function(el){
		el.preventDefault();
		var current = $(this);  //缓存点击的对象
		$(".collapse_all > li > a").removeClass("navactive");  //当点击某个<a>时，去除原先的样式
		current.addClass("navactive");          //给当前对象<a>加上样式，让人知道当前是它
	    $("#iframe-content").attr('src',current.data("iframesrc"));  //attr读取html页面的<body>内容
	     var text = current.text();   //获取标签里的内容
	     var mnav = current.parent().parent().prev().text();
	     $("#path_nav > .breadcrumb > li:last-child").html(text);  //写内容到标签内如：<a>写入的内容</>
         $("#path_nav > .breadcrumb > li:last-child").prev().html(mnav)
	});
});