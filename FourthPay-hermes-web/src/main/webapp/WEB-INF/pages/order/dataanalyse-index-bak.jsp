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

        //页面启动的时候，加载
        function init(){
            var todayStr=translateDate2String(new Date());
            $("#orderStartTime").val(todayStr);
            $("#orderEndTime").val(todayStr);
            $("#searchForm").submit();
        }

        $(document).ready(function () {
            init();
            $('#dataTable').dataTables({
                "sAjaxSource": "${ctx}/order/dataanalyse/list",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    var postData = aoData.concat($('#searchForm').serializeArray());
                    $.post(sSource, postData, function (json) {
                        fnCallback(json.data);
                    }, "json");
                },
                "aoColumns": [
                    {"sName": "cardname", "mDataProp": "cardname"},
                    {"sName": "totalcnt", "mDataProp": "totalcnt"},
                    {"sName": "totalfaceval", "mDataProp": "totalfaceval"},
                    {"sName": "succcnt", "mDataProp": "succcnt"},
                    {"sName": "succfaceval", "mDataProp": "succfaceval"},
                    {"sName": "succaccaddval", "mDataProp": "succaccaddval"}
                ]
            });

            // 日期
            $("#orderStartTime").datepicker({
                minDate: -30,
                maxDate: 0,
                showButtonPanel: false,
                onSelect: function (dateText, inst) {
                    $("#searchForm").submit();
                }
            });
            $("#orderEndTime").datepicker({
                minDate: -30,
                maxDate: 0,
                showButtonPanel: false,
                onSelect: function (dateText, inst) {
                    $("#searchForm").submit();
                }
            });
            $("#chargeStartTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
                    $("#searchForm").submit();
                }
            });
            $("#chargeEndTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
                    $("#searchForm").submit();
                }
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
            <input type="hidden" id="corpcode" name="corpcode"/>
            <ul>
                <li>
                    <div class="input-prepend"><span class="add-on">订单时间：</span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <input type="text" name="orderStartTime" id="orderStartTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
                <li>
                    <div class="input-prepend"><span>-</span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <input type="text" name="orderEndTime" id="orderEndTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
            </ul>
            <ul>
                <li>
                    <div class="input-prepend"><span class="add-on">交易时间：</span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <input type="text" name="chargeStartTime" id="chargeStartTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
                <li>
                    <div class="input-prepend"><span>-</span>
                    </div>
                </li>
                <li>
                    <div class="input-prepend">
                        <input type="text" name="chargeEndTime" id="chargeEndTime" class="time-select"
                               placeholder="YYYY-MM-DD" readonly/>&nbsp;
                    </div>
                </li>
            </ul>
        </form>
    </div>

    <div class="dataTable-wrapper">
        <table id="dataTable" class="table table-bordered table-striped centered">
            <thead>
            <tr role="row">
                <th>运营商</th>
                <th>总交易笔数</th>
                <th>总交易面额（元）</th>
                <th>成功交易笔数</th>
                <th>成功交易面额（元）</th>
                <th>成功交易结算额（元）</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
    </div>
</div>
</body>
</html>