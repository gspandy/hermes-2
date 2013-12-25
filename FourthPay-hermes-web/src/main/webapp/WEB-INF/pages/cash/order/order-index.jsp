<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta  name="description" content="欧飞网充值卡寄售平台" />
	<meta name="keywords" content="欧飞网充值卡寄售平台" />
	<title>提现管理 － 申请提现 - 添加银行卡 - 欧飞网充值卡寄售平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/datatable.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.datepicker_zh_CN.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // tab 高亮
            bindTab("apply", "apply_2");

            $('#dataTable').dataTables({
                "aaSorting": [
                    [ 1, "desc" ]
                ],
                "sAjaxSource": "${ctx}/cash/order/list",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    var postData = aoData.concat($('#searchForm').serializeArray());
                    $.post(sSource, postData, function (json) {
                        fnCallback(json.data);
                        $("#applyTimeStart").val(json.start);
                        $("#applyTimeEnd").val(json.end);
                    }, "json");
                },
                "aoColumns": [
                    {"sName": "cashorderno", "mDataProp": "cashorderno"},
                    {"sName": "applytime", "mDataProp": "formatApplytime"},
                    {"sName": "applymoney", "mDataProp": "applymoney"},
                    {"sName": "commision", "mDataProp": "commision"},
                    {"sName": "merleftmoney", "mDataProp": "merleftmoney"},
                    {"sName": "merleftmoneyafter", "mDataProp": "merleftmoneyafter"},
                    {
                        "mDataProp": function (aData, type, val) {
                            if (aData.applytype == 1) {
                                val = "提现";
                            } else if (aData.applytype == 2) {
                                val = "转信用点";
                            } else {
                                val = "";// 异常类型
                            }
                            return val;
                        },
                        "sName": "applytype"
                    },
                    {"sName": "bankname", "mDataProp": "bankname"},
                    {"sName": "accountusername", "mDataProp": "accountusername"},
                    {"sName": "accountno", "mDataProp": "accountno"},
                    {
                        "mDataProp": function (aData, type, val) {
                            if (aData.datastat == '1'
                                    || (aData.datastat == 2 && (aData.dealstat == 0 || aData.dealstat == 1))) {
                                val = "正常";
                            } else if (aData.datastat == 2 && aData.dealstat == 2) {
                                val = "成功";
                            } else if (aData.datastat == 3 || (aData.datastat == 2 && aData.dealstat == 3)) {
                                val = "失败";
                            } else {
                                val = "";// 异常状态
                            }
                            return val;
                        },
                        "sName": "stat"
                    },
                    {"sName": "remarkinfo", "mDataProp": "remarkinfo"}
                ]
            });


            // 绑定日期类型的点击事件
            $("a[type='dateType']").bind("click", function () {
                $("#cashOrderNo").val("");
                $("#dateType").val($(this).attr("id"));
                $("a[type='dateType']").removeClass("current");
                $(this).addClass("current");
                $("#searchForm").submit();
            });

            // 绑定状态的点击事件
            $("a[type='stat']").bind("click", function () {
                $("#cashOrderNo").val("");
                $("#stat").val($(this).attr("id"));
                $("a[type='stat']").removeClass("current");
                $(this).addClass("current");
                $("#searchForm").submit();
            });
            // 光标变成手型
            $("a").css('cursor', 'pointer');

            // 绑定鼠标进入输入框，显示按钮，离开，隐藏按钮
            $("#cashOrderNo").focusin(function () {
                $("#cashOrderNo").nextAll().remove();
                $("#cashOrderNo").after("<button class=\"btn_s\">搜索</button>");
            });

            // 日期
            $("#applyTimeStart").datepicker({
                minDate: -30,
                maxDate: 0,
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
            $("#applyTimeEnd").datepicker({
                minDate: -30,
                maxDate: 0,
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
        });
    </script>
</head>
<body>
    <form id="searchForm" onsubmit="$('#dataTable').refreshData();return false;">
        <input type="hidden" id="dateType" name="dateType"/>
        <input type="hidden" id="stat" name="stat"/>
        <div class="content">
            <div class="accInfo w1200">
                <h3>提现明细</h3>
                <p class="search">
                    <label for="">起止时间：</label>
                    <input type="text" name="applyTimeStart" id="applyTimeStart" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    至
                    <input type="text" name="applyTimeEnd" id="applyTimeEnd" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    <b><a id="today" type="dateType" class="current">今天</a></b>
                    <b><a id="week" type="dateType">最近一星期</a></b>
                    <b><a id="month" type="dateType">最近一个月</a></b>
                </p>
                <p class="search">
                    <label for="">交易状态：</label>
                    <b><a id="all" type="stat" class="current">全部</a></b>
                    <b><a id="1" type="stat">成功</a></b>
                    <b><a id="2" type="stat">失败</a></b>
                    <b><a id="0" type="stat">处理中</a></b>
                </p>
                <p class="search">
                    <label for="">提现单号：</label>
                    <input type="text" id="cashOrderNo" name="cashOrderNo"
                           placeholder="T13102618055037" class="accInput">
                    <%--<button class="btn_s">确定</button>--%>
                </p>
                <table id="dataTable" class="border_table">
                    <thead>
                    <tr role="row" class="table_title noborder_title">
                        <th>提现单号</th>
                        <th>申请时间</th>
                        <th>提现金额</th>
                        <th>手续费</th>
                        <th>提现前金额</th>
                        <th>提现后金额</th>
                        <th>提现类型</th>
                        <th>银行名称</th>
                        <th>真实姓名</th>
                        <th>银行账号</th>
                        <th>状态</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <%--<p class="download"><a href=""><s></s>下载查询结果</a>（最多支持7天范围内数据）</p>--%>
            </div>
        </div>
    </form>
</body>
</html>