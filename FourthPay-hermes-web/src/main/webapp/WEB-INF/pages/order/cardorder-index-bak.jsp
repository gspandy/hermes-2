<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.datepicker_zh_CN.js"></script>
    <style type="text/css">
        .bodyshouye{
            width:1200px;
            margin:0 auto;
            padding:0;
        }
        .orderheader{
            height:20px;
            background-color:#CCCCCC
        }
    </style>
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
            $("#searchForm").submit();
        }

        $(document).ready(function () {
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
                            val = '<a class="link_name" href="/order/cardorder/callback?billid='+aData.billid+'">'+"回调"+'</a>';
                            return val;
                        },
                        "sName"    :"name",
                        "bSortable": false
                    }
                ]
            });

            // 绑定日期类型的点击事件
            $("a[type='dateType']").bind("click", function () {
                $("#state").val("");
                $("#keywordQueryValue").val("");
                $("#corpcode").val("");
                $("#dateType").val($(this).attr("id"));
                $("a[type='dateType']").removeClass("label label-info");
                $(this).addClass("label label-info");
                refreshTime($(this).attr("id"));
                $("#searchForm").submit();
            });

            // 绑定状态的点击事件
            $("a[type='status']").bind("click", function () {
                $("#state").val($(this).attr("id"));
                $("a[type='status']").removeClass("label label-info");
                $(this).addClass("label label-info");
                $("#searchForm").submit();
            });

            // 绑定运营商的点击事件
            $("a[type='cardName']").bind("click", function () {
                $("#corpcode").val($(this).attr("value"));
                $("a[type='cardName']").removeClass("label label-info");
                $(this).addClass("label label-info");
                $("#searchForm").submit();
            });

            // 绑定关键字选择框的点击事件
            $("#keywordQuery").bind("change", function () {
                $("#keywordQueryValue").val("");
            });

            // 光标变成手型
            $("a").css('cursor', 'pointer');

            // 绑定鼠标进入输入框，显示按钮，离开，隐藏按钮
            $("#keywordQueryValue").focusin(function () {
                $("#btnSearch").html("");
                $("#btnSearch").append("<button class=\"btn btn-primary\">查询</button>");
            });

            // 日期
            $("#startTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
                    $("#dateType").val("");
                    $("a[type='dateType']").removeClass("label label-info");
                    $("#searchForm").submit();
                }
            });
            $("#endTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
                    $("#dateType").val("");
                    $("a[type='dateType']").removeClass("label label-info");
                    $("#searchForm").submit();
                }
            });

            // 绑定export的点击事件
            $("#export").bind("click", function () {
                var data = "?startTime=" + $("#startTime").val() + "&endTime=" + $("#endTime").val() + "&keywordQuery=" + $("#keywordQuery").val()
                        +"&state=" + $("#state").val() + "&corpcode=" + $("#corpcode").val() + "&keywordQueryValue=" + $("#keywordQueryValue").val();
                alert(data);
                location.href="/order/cardorder/export" + data;
            });
        });
    </script>
<body>
<div class="bodyshouye">
    <jsp:include page="../base/module/inc/hdader.jsp"/>
    <div class="orderheader">
		<div style="margin-left: 60px">
            <a href="/order/cardorder" class="STYLE1"><span>订单查询</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="/order/dataanalyse" class="STYLE1"><span>数据分析</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="/order/payorder" class="STYLE1"><span>支付订单</span></a>
		</div>
    </div>
    <div style="margin-left:30px;height: 20px">
        <h3>订单查询</h3>
    </div>
    <hr>
    <div id="wrapper">
    <div class="search-form pusht">
        <form id="searchForm" onSubmit="$('#dataTable').refreshData();return false;">
            <input type="hidden" id="dateType" name="dateType"/>
            <input type="hidden" id="state" name="state"/>
            <input type="hidden" id="corpcode" name="corpcode"/>
            <ul>
                <li>
                    <div class="input-prepend"><span class="add-on">起始日期：</span>
                        <input type="text" name="startTime" id="startTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
                <li>
                    <div class="input-prepend"><span class="add-on">截止日期：</span>
                        <input type="text" name="endTime" id="endTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="today" type="dateType" class="label label-info"
                                                style="cursor: hand">今天</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="week" type="dateType">最近一星期</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="month" type="dateType">最近一个月</a></span>
                    </div>
                </li>
            </ul>
            <ul>
                <li>
                    <div class="input-prepend"><span class="add-on">交易状态：</span>

                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="all" type="status" class="label label-info">全部</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="1" type="status">成功</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="2" type="status">失败</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="0" type="status">处理中</a></span>
                    </div>
                </li>
            </ul>
            <ul>
                <li>
                    <div class="input-prepend"><span class="add-on">运营商：</span>

                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_all" type="cardName" class="label label-info">全部</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_1" type="cardName" value="1">神州行卡</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_2" type="cardName" value="2">联通一卡充</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_3" type="cardName" value="3">电信国卡</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_5" type="cardName" value="5">骏网</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_6" type="cardName" value="6">盛大</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_7" type="cardName" value="7">Q币</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_8" type="cardName" value="8">完美</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_9" type="cardName" value="9">征途</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_10" type="cardName" value="10">网易</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_11" type="cardName" value="11">搜狐</a></span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <span class="add-on"><a id="cardName_12" type="cardName" value="12">久游</a></span>
                    </div>
                </li>
            </ul>
            <ul id="ulSearch">
                <li>
                    <div class="input-prepend">
                        <span class="add-on">关键词：</span>
                        <select id="keywordQuery" name="keywordQuery" class="time-select">
                            <option value="1">充值卡号</option>
                            <option value="2">商户订单号</option>
                            <option value="3">殴飞流水号</option>
                        </select>
                        <input type="text" id="keywordQueryValue" name="keywordQueryValue" class="input-60p">
                    </div>
                </li>
                <li id="btnSearch">
                </li>
            </ul>
        </form>
    </div>

    <div class="dataTable-wrapper">
        <table id="dataTable" class="table table-bordered table-striped centered">
            <thead>
            <tr role="row">
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
                <th>回调结果</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    <div>
        <img src="/images/export.png">
        <a id="export" type="export" class="STYLE1"><span>下载查询结果</span></a>
        <span class="STYLE1">（最多支持7天范围内数据）</span>
    </div>
    </div>
</div>
</body>
</html>