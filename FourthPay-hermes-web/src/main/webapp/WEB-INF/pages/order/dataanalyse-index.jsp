<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta  name="description" content="欧飞网充值卡寄售平台" />
    <meta name="keywords" content="欧飞网充值卡寄售平台" />
    <title>订单管理 － 数据分析 - 欧飞网充值卡寄售平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/datatable.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui-1.10.3.datepicker_zh_CN.js"></script>
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
            $("#startTime").val(todayStr);
            $("#endTime").val(todayStr);
        }
        $(document).ready(function () {
            // tab 高亮
            bindTab("order", "order_2");

            //初始化日期中的开始时间和结束时间
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
            $("#startTime").datepicker({
                minDate: -30,
                maxDate: 0,
                showButtonPanel: false,
                onSelect: function (dateText, inst) {
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
                    $("#searchForm").submit();
                },
                beforeShow: function(){
                    setTimeout(function(){
                        $(".ui-datepicker").css("z-index", 9999);
                    }, 10);
                }
            });
            $("#chargeStartTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
                    $("#searchForm").submit();
                },
                beforeShow: function(){
                    setTimeout(function(){
                        $(".ui-datepicker").css("z-index", 9999);
                    }, 10);
                }
            });
            $("#chargeEndTime").datepicker({
                minDate: -30,
                maxDate: 0,
                onSelect: function (dateText, inst) {
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
    <form id="searchForm" onSubmit="$('#dataTable').refreshData();return false;">
        <input type="hidden" id="corpcode" name="corpcode"/>
        <div class="content">
            <div class="accInfo w1200">
                <h3>数据分析</h3>
                <p class="search">
                    <label for="">订单时间：</label>
                    <input type="text" name="startTime" id="startTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    至
                    <input type="text" name="endTime" id="endTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                </p>
                <p class="search">
                    <label for="">交易时间：</label>
                    <input type="text" name="chargeStartTime" id="chargeStartTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                    至
                    <input type="text" name="chargeEndTime" id="chargeEndTime" class="accInput accDate"
                           placeholder="YYYY-MM-DD" readonly/>
                </p>

                <table id="dataTable" class="border_table">
                    <thead>
                        <tr role="row" class="table_title noborder_title">
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
    </form>
</body>
</html>