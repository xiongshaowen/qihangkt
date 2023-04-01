$(function(){
   changeBgImg();
   $(window).on("resize",changeBgImg);   //resize监听视口大小，大小被改变就会触发函数
})
function changeBgImg(){
	var winWidth = $(window).width();
	//alert("能拿到屏幕宽度吗?"+winWidth);
	$("#carousel-qhkt>.carousel-inner>.item").each(function(index,el){    //循环
		var div =$(el);
		//alert(div.class);   //如果显示几个弹出框，说明拿到了div(显示图片)的集合
		//console.log(div);   //网页控制台中显示组件信息
		var bgimg = div.data((winWidth <= 768)?"xs-img":"lg-img");
		//alert(bgimg)
		if(winWidth <= 768){
			div.html('<img src="'+bgimg+'" alt="carousel">');
		}else{
			div.html("");
			div.css('background',"url('"+bgimg+"') no-repeat");
		}
		});
}