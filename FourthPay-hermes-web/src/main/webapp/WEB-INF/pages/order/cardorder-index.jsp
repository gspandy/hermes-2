<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta  name="description" content="欧飞网充值卡寄售平台" />
    <meta name="keywords" content="欧飞网充值卡寄售平台" />
    <title>订单管理 － 订单查询 - 欧飞网充值卡寄售平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/datatable.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.datepicker_zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.artDialog.js?skin=twitter"></script>
    <script type="text/javascript">
        function translateDate2String(myDate){
            var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
            var month = myDate.getMonth() + 1;       //获取当前月份(0-11,0代表1月)
            var day = myDate.getDate();        //获取当前日(1-31)

            if(month < 10){
                month = "0" + month;
            }
            if(day < 10){
                day = "0" + day;
            }
            return year + "-" + month + "-" + day;
        }

        function subtractTime(startDate,endDate){
            // 给日期类对象添加日期差方法，返回日期与diff参数日期的时间差，单位为天
            Date.prototype.diff = function(date){
                return (this.getTime() - date.getTime())/(24 * 60 * 60 * 1000);
            }
            // 构造两个日期，分别是系统时间和2013/04/08 12:43:45
            var end = new Date(endDate);
            var start = new Date(startDate);
            // 调用日期差方法，求得参数日期与系统时间相差的天数
            var diff = end.diff(start);

            return diff;
        }

        function refreshTime(dateType){
            var todayStr=translateDate2String(new Date());
            $("#endTime").val(todayStr);

            if ("today" == dateType){
                $("#startTime").val(todayStr);
            }
            else if("week" == dateType){
                var beforeweekDate = new Date();
                beforeweekDate.setDate(beforeweekDate.getDate() - 7);
                var beforWeekStr = translateDate2String(beforeweekDate);
                $("#startTime").val(beforWeekStr);
            }
            else if("month" == dateType){
                var beforeMonthDate = new Date();
                beforeMonthDate.setMonth(beforeMonthDate.getMonth() - 1);
                var beforeMonthStr = translateDate2String(beforeMonthDate);
                $("#startTime").val(beforeMonthStr);
            }
        }

        //页面启动的时候，加载
        function init(){
            var todayStr=translateDate2String(new Date());
            $("#startTime").val(todayStr);
            $("#endTime").val(todayStr);
        }

        function callback(val){
            $.post("/order/cardorder/callback?billid="+ val, function(json) {
                art.dialog({
                    title: '回调结果',
                    content: json.data.info,
                    width: 350,
                    ok: true,
                    cancelVal: '关闭',
                    cancel: true,
                    lock: true
                });
            }, "json");
        }

        function postcardagain(val){
            $.post("/card/postCardAgain?billid="+ val, function(json) {
                art.dialog({
                    title: '再次提交结果',
                    content: json.data.info,
                    width: 350,
                    ok: true,
                    cancelVal: '关闭',
                    cancel: true,
                    lock: true
                });
            }, "json");
        }

        $(document).ready(function () {
            // tab 高亮
            bindTab("order", "order_1");

            //初始化日期中的开始时间和结束时间
            init();

            $('#dataTable').dataTables({
                "aaSorting": [
                    [ 9, "desc" ]
                ],
                "sAjaxSource": "${ctx}/order/cardorder/list",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    var postData = aoData.concat($('#searchForm').serializeArray());
                    $.post(sSource, postData, function (json) {
                        fnCallback(json.data);
                    }, "json");
                },
                "aoColumns": [
                    {"sName": "merorderno", "mDataProp": "merorderno"},
                    {"sName": "billid", "mDataProp": "billid"},
                    {"sName": "cardno", "mDataProp": "cardno"},
                    {"sName": "faceval", "mDataProp": "faceval"},
                    {"sName": "realval", "mDataProp": "realval"},
                    {"sName": "mercommission", "mDataProp": "mercommission"},
                    {"sName": "merleftmoney", "mDataProp": "merleftmoney"},
                    {"sName": "cardname", "mDataProp": "cardname"},
                    {"sName": "retresult", "mDataProp": "retresultmsg"},
                    {"sName": "ordertime", "mDataProp": "ordertime"},
                    {
                        "mDataProp": function ( aData, type, val ) {
                            if(aData.retresult == "2012" || aData.retresult == "2013" || aData.retresult == "2019"){
                                //val = '<a class="link_name" href="/card/postCardAgain?billid='+aData.billid+'">'+"提交"+'</a>';
                                val = '<a class="link_name" href="#" onclick="postcardagain(\''+aData.billid+'\')">'+"提交"+'</a>';
                                return val;
                            }else{
                                val = '<a class="gray" href="#">'+"提交"+'</a>';
                                return val;
                            }
                        },
                        "sName"    :"name",
                        "bSortable": false
                    },
                    {
                        "mDataProp": function ( aData, type, val ) {
                            //val = '<a class="link_name" href="/order/cardorder/callback?billid='+aData.billid+'">'+"回调"+'</a>';
                            val = '<a class="link_name" href="#" onclick="callback(\''+aData.billid+'\')">'+"回调"+'</a>';

                            return val;
                        },
                        "sName"    :"name",
                        "bSortable": false
                    }
                ]
            });

            // 日期
            $("#startTime").datepicker({
                minDate: -30,
                maxDate: 0,
                showButtonPanel: false,
                onSelect: function (dateText, inst) {
                    $("#dateType").val("");
                    $("a[type='dateType']").removeClass("current");
                    $("#searchForm").submit();
                },
                beforeShow: function(){
                    setTimeout(function(){
                        $(".ui-datepicker").css("z-index", 9999);
                    }, 10);
                }
            });
            $("#endTime").datepicker({
                minDate: -30,
                maxDate: 0,
                showButtonPanel: false,
                onSelect: function (dateText, inst) {
                    $("#dateType").val("");
                    $("a[type='dateType']").removeClass("current");
                    $("#searchForm").submit();
                },
                beforeShow: function(){
                    setTimeout(function(){
                        $(".ui-datepicker").css("z-index", 9999);
                    }, 10);
                }
            });

            // 绑定日期类型的点击事件
            $("a[type='dateType']").bind("click", function () {
                $("#state").val("");
                $("#keywordQueryValue").val("");
                $("#corpcode").val("");
                $("#dateType").val($(this).attr("id"));
                $("a[type='dateType']").removeClass("current");
                $(this).addClass("current");
                refreshTime($(this).attr("id"));
                $("#searchForm").submit();
            });

            // 光标变成手型
            $("a").css('cursor', 'pointer');

            // 绑定状态的点击事件
            $("a[type='status']").bind("click", function () {
                $("#state").val($(this).attr("id"));
                $("a[type='status']").removeClass("current");
                $(this).addClass("current");
                $("#searchForm").submit();
            });

            // 绑定运营商的点击事件
            $("a[type='cardName']").bind("click", function () {
                $("#corpcode").val($(this).attr("value"));
                $("a[type='cardName']").removeClass("current");
                $(this).addClass("current");
                $("#searchForm").submit();
            });

            // 绑定关键字选择框的点击事件
            $("#keywordQuery").bind("change", function () {
                $("#keywordQueryValue").val("");
                $("#searchForm").submit();
            });

            // 绑定鼠标进入输入框，显示按钮，离开，隐藏按钮
            $("#keywordQueryValue").focusin(function () {
                $("#keywordQueryValue").nextAll().remove();
                $("#keywordQueryValue").after("<button class=\"btn_s\">搜索</button>");
            });

            // 绑定export的点击事件
            $("#export").bind("click", function () {
                var day = subtractTime($("#startTime").val(), $("#endTime").val());
                if( day > 7){
                    alert("时间天数大于7天，请重新选择！");
                }else{
                    var data = "?startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&keywordQuery=" + $("#keywordQuery").val()
                            +"&state=" + $("#state").val() + "&corpcode=" + $("#corpcode").val() + "&keywordQueryValue=" + $("#keywordQueryValue").val();
                    location.href="/order/cardorder/export" + data;
                }
            });
        });
    </script>
</head>
<body>
    <form id="searchForm" onsubmit="$('#dataTable').refreshData();return false;">
        <input type="hidden" id="dateType" name="dateType"/>
        <input type="hidden" id="state" name="state"/>
        <input type="hidden" id="corpcode" name="corpcode"/>
        <div class="content">
            <div class="accInfo w1200">
                <h3>订单查询</h3>
                <p class="search">
                    <label for="">起止时间：</label>
                    <input type="text" name="startTime" id="startTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    至
                    <input type="text" name="endTime" id="endTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    <b><a id="today" type="dateType" class="current">今天</a></b>
                    <b><a id="week" type="dateType">最近一星期</a></b>
                    <b><a id="month" type="dateType">最近一个月</a></b>
                </p>
                <p class="search">
                    <label for="">交易状态：</label>
                    <b><a id="all" type="status" class="current">全部</a></b>
                    <b><a id="1" type="status">成功</a></b>
                    <b><a id="2" type="status">失败</a></b>
                    <b><a id="0" type="status">处理中</a></b>
                </p>
                <p class="search">
                    <label for="">运营商：</label>
                    <b><a id="cardName_all" type="cardName" class="current">全部</a></b>
                    <b><a id="cardName_1" type="cardName" value="1">神州行卡</a></b>
                    <b><a id="cardName_2" type="cardName" value="2">联通一卡充</a></b>
                    <b><a id="cardName_3" type="cardName" value="3">电信国卡</a></b>
                    <b><a id="cardName_5" type="cardName" value="5">骏网</a></b>
                    <b><a id="cardName_6" type="cardName" value="6">盛大</a></b>
                    <b><a id="cardName_7" type="cardName" value="7">Q币</a></b>
                    <b><a id="cardName_8" type="cardName" value="8">完美</a></b>
                    <b><a id="cardName_9" type="cardName" value="9">征途</a></b>
                    <b><a id="cardName_10" type="cardName" value="10">网易</a></b>
                    <b><a id="cardName_11" type="cardName" value="11">搜狐</a></b>
                    <b><a id="cardName_12" type="cardName" value="12">久游</a></b>
                </p>
                <p class="search">
                    <label for="">关键词：</label>
                    <select name="keywordQuery" id="keywordQuery">
                        <option value="1">充值卡号</option>
                        <option value="2">商户订单号</option>
                        <option value="3">殴飞流水号</option>
                    </select>
                    <input type="text" id="keywordQueryValue" name="keywordQueryValue"
		    		class="accInput">
                </p>

                <table id="dataTable" class="border_table">
                    <thead>
                        <tr role="row" class="table_title noborder_title">
                            <th>商户订单号</th>
                            <th>殴飞流水号</th>
                            <th>充值卡号</th>
                            <th>申明面值</th>
                            <th>结算面值</th>
                            <th>手续费</th>
                            <th>账户余额</th>
                            <th>运营商</th>
                            <th>支付状态</th>
                            <th>用户交易时间</th>
                            <th>再次提交</th>
                            <th>回调结果</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <p class="download"><a id="export" type="export"><s></s>下载查询结果</a>（最多支持7天范围内数据）</p>
            </div>
        </div>
    </form>
</body>
</html>