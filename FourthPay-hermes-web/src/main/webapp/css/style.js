// 首页交互脚本
$(function(){
    // 初始化滚动图片 by jQuery cycle
    $(".slideCon")
    .before("<div id='slideNav'>")
    .cycle({
             pager: '#slideNav'
        });
    //用于indexBanner样式控制 载入窗口时调整banner尺寸
    $(".slides .slideCon a").css("width",$(window).width()); 
})