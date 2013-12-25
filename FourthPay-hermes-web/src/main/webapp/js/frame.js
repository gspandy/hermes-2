$(function () {
    //主导航tab点击切换
    $("#mainnav .tab a").click(function () {
        $("#mainnav .tab a").removeClass("selected");
        $(this).addClass("selected");
    });
    //侧边导航tab点击切换
    $("#side-menu li a").click(function () {
        $("#side-menu li a").removeClass("selected");
        $(this).addClass("selected");
    });
    //加载时高度自动适应
    $(".sidebar,.mainbar .module,.sidebar .module,#mainframe").css("height", $(window).height() - 102);
    //加载时按钮自动适应
    $(".sideBar-trigger").css("top", ($(window).height() - 102) / 2 - 20);
    //点击收起侧边栏
    $(".sideBar-trigger").click(function () {
        $(this).parents("#content").toggleClass("side-folded");
    });
});
//窗口大小更改时高度调整
$(window).resize(function () {
    $(".sidebar,.mainbar .module,.sidebar .module,#mainframe").css("height", $(window).height() - 102);
    //窗口大小更改时按钮自动适应
    $(".sideBar-trigger").css("top", ($(window).height() - 102) / 2 - 20);
});
