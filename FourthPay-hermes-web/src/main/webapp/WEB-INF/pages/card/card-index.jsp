<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="欧飞网充值卡寄售平台"/>
    <meta name="keywords" content="欧飞网充值卡寄售平台"/>
    <title>提交卡密 - 欧飞网充值卡寄售平台</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/datatable.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/error.css"/>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.dataTables.init.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.artDialog.js?skin=twitter"></script>
    <script type="text/javascript">
        function callback(val){
            $.post("/order/cardorder/callback?billid="+ val, function(json) {
                art.dialog({
                    title: '回调结果',
                    content: "result:" + json.data.result +";msg:" +  json.data.info,
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
            bindTab("card", "");

            // 表单验证
            var $validator = $("#cardForm").validate({
                rules: {
                    cardno: {
                        required : true,
                        number : true
                    },
                    cardpass : {
                        required : true,
                        number : true
                    },
                    captcha :{
                        required : true
                    }
                },
                messages: {
                    cardno: {
                        required : "请输入充值卡卡号",
                        number: "请输入数字"
                    },
                    cardpass : {
                        required : "请输入充值卡密码",
                        number: "请输入数字"
                    },
                    captcha :{
                        required : "请输入验证码"
                    }
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    if ( element.is(":checkbox") )
                        error.appendTo(element.parent().next());
                    else if ( element.attr("id") === "captcha")
                        error.insertAfter($("#cimg"));
                    else
                        error.insertAfter(element);
                }
            });


            // 初始化最近交易记录
            $('#dataTable').dataTables({
                "aaSorting": [
                    [ 6, "desc" ]
                ],
                "sAjaxSource": "${ctx}/card/order/list",
                "fnServerData": function (sSource, aoData, fnCallback) {
                    var postData = aoData;
                    $.post(sSource, postData, function (json) {
                        fnCallback(json.data);
                    }, "json");
                },
                "aoColumns": [
                    {"sName": "cardno", "mDataProp": "cardno"},
                    {"sName": "corpname", "mDataProp": "corpname"},
                    {"sName": "faceval", "mDataProp": "faceval"},
                    {"sName": "realval", "mDataProp": "realval"},
                    {"sName": "accaddval", "mDataProp": "accaddval"},
                    {"sName": "retresult", "mDataProp": "retresultmsg"},
                    {"sName": "ordertime", "mDataProp": "ordertime"},
                    {
                        "mDataProp": function (aData, type, val) {
                            //val = '<a class="link_name" href="/order/cardorder/callback?billid=' + aData.billid + '">' + "回调" + '</a>';
                            val = '<a class="link_name" href="#" onclick="callback(\''+aData.billid+'\')">'+"回调"+'</a>';
                            return val;
                        },
                        "bSortable": false
                    }
                ]
            });

            var $categories = $("#categories > li");
            // 绑定侧边栏点击事件
            $categories.on("click", function () {
                var $this = $(this);
                $.get("/card/category/" + $this.attr("data-id"), function (categories) {
                    // 支持的提卡面值（用于友情提示显示）
                    var supportValue = "";
                    var $cardName = $this.attr("data-name");
                    $("#facevalues").html("").append("<label for=\"\">充值卡面值：</label>");
                    $.each(categories, function (i, item) {
                        $("#facevalues").append("<span style='cursor:pointer' " +
                                "onclick=chooseValue('" + item.cardcode + "','" + item.faceval + "',this)>" + item.faceval + "元</span>");
                        supportValue += item.faceval+"元、";
                    });
                    // 友情提醒
                    $("#remind1").html("2."+$cardName+"支持的充值卡面额为"+
                            supportValue.substring(0, supportValue.length-1)+"，请勿使用其他面额进行支付。");
                    $("#remind2").html("3.输入正确的序列号和密码的"+$cardName+"都可以支付。");

                    // 侧边栏选中样式
                    $categories.removeClass("current");
                    $this.addClass("current");

                    // 面值重置
                    $("#cardcode").val("");
                    $("#faceval").html("");

                    var $facevalues = $("#facevalues > span");
                    // 默认选中第一个面值
                    if ($facevalues.length > 0) {
                        $("#errMsg").html("");
//                        $facevalues[0].click();
                    }
                    // 没有面值就报错
                    else {
                        $("#faceval").html("");
                        $("#errMsg").html("没有获取到面值信息，暂时不能提交");
                    }
                });
            });
            // 默认选中第一个：中国移动
            $categories[0].click();

            // 绑定提交事件
            $("#btnPost").on("click", function () {
                // 如果验证通过
                if($("#cardForm").valid()){
                    $.post("/card/postCard", $('#cardForm').serializeArray(), function (json) {
                        // 成功
                        if (json.data.result === "2001") {
                            // 显示成功信息
                            art.dialog({
                                icon: 'succeed',
                                title: '卡密提交结果',
                                content: json.data.info,
                                ok: true,
                                cancelVal: '关闭',
                                cancel: true,
                                lock: true
                            });
                            // 刷新表格
                            $('#dataTable').refreshData();
                        }
                        else {
                            // 刷新验证码
                            refreshmvp();
                            // 显示失败信息
                            art.dialog({
                                icon: 'error',
                                title: '卡密提交结果',
                                content: json.data.info,
                                ok: true,
                                cancelVal: '关闭',
                                cancel: true,
                                lock: true
                            });
                        }
                    });
                }
            });

            // 绑定刷新事件
            $("#refresh").on("click", function () {
                $('#dataTable').refreshData();
            });

            // 表单重置
            $("#reset").on("click", function () {
                $validator.resetForm();
            });
        });

        // 选中面值
        function chooseValue(cardcode, faceval, obj) {
            // 选中的卡赋值
            $("#cardcode").val(cardcode);
            // 选中样式
            $("#facevalues > span").removeClass("selected");
            $(obj).addClass("selected");

            $("#faceval").html("<label>选择面值：</label><span>" + faceval + "元</span>");
        }

        // 验证码
        var VerifyCodeTimes=1;
        function refreshmvp(){
            $("#cimg").attr("src", "/captcha/image?"+(VerifyCodeTimes++));
        }
    </script>
</head>
<body>
<form id="cardForm" name="cardForm" autocomplete="off">
    <input type="hidden" name="cardcode" id="cardcode">
    <div class="content">
        <div class="accInfo submitInfo w1200 clearfix">
            <div class="submitLeft">
                <ul id="categories">
                    <li data-id="1" data-name="移动充值卡" class="current">
                        <a href="javascript:void(0)"><s></s>移动充值卡</a>
                    </li>
                    <li data-id="2" data-name="联通充值卡">
                        <a href="javascript:void(0)"><s class="recharge01"></s>联通充值卡</a>
                    </li>
                    <li data-id="3" data-name="电信充值卡">
                        <a href="javascript:void(0)"><s class="recharge02"></s>电信充值卡</a>
                    </li>
                    <li data-id="7" data-name="QQ充值卡">
                        <a href="javascript:void(0)"><s class="recharge03"></s>QQ充值卡</a>
                    </li>
                    <li data-id="5" data-name="骏网一卡通">
                        <a href="javascript:void(0)"><s class="recharge04"></s>骏网一卡通</a>
                    </li>
                    <li data-id="6" data-name="盛大一卡通">
                        <a href="javascript:void(0)"><s class="recharge05"></s>盛大一卡通</a>
                    </li>
                    <li data-id="8" data-name="完美一卡通">
                        <a href="javascript:void(0)"><s class="recharge06"></s>完美一卡通</a>
                    </li>
                    <li data-id="9" data-name="征途一卡通">
                        <a href="javascript:void(0)"><s class="recharge07"></s>征途一卡通</a>
                    </li>
                    <li data-id="10" data-name="网易一卡通">
                        <a href="javascript:void(0)"><s class="recharge08"></s>网易一卡通</a>
                    </li>
                    <li data-id="11" data-name="搜狐一卡通">
                        <a href="javascript:void(0)"><s class="recharge09"></s>搜狐一卡通</a>
                    </li>
                    <li data-id="12" data-name="久游一卡通">
                        <a href="javascript:void(0)"><s class="recharge10"></s>久游一卡通</a>
                    </li>
                    <li data-id="13" data-name="天宏一卡通">
                        <a href="javascript:void(0)"><s class="recharge11"></s>天宏一卡通</a>
                    </li>
                    <li data-id="15" data-name="光宇一卡通">
                        <a href="javascript:void(0)"><s class="recharge12"></s>光宇一卡通</a>
                    </li>
                    <li data-id="4" data-name="欧飞一卡通">
                        <a href="javascript:void(0)"><s class="recharge13"></s>欧飞一卡通</a>
                    </li>
                    <li data-id="23" data-name="财付通一卡通">
                        <a href="javascript:void(0)"><s class="recharge14"></s>财付通一卡通</a>
                    </li>
                    <li data-id="14" data-name="天下一卡通">
                        <a href="javascript:void(0)"><s class="recharge15"></s>天下一卡通</a>
                    </li>
                    <li data-id="16" data-name="纵游一卡通">
                        <a href="javascript:void(0)"><s class="recharge16"></s>纵游一卡通</a>
                    </li>
                </ul>
            </div>
            <div class="submitRight">
                <h2>友情提醒</h2>

                <p>1.请确认您选择的“充值卡面额”和实际的充值卡面额一致，否则会导致交易失败和余额丢失。</p>

                <p id="remind1"></p>

                <p id="remind2"></p>

                <div class="subm">
                    <a href="/card/single" class="current">单张提交</a>
                    <a href="/card/batch" >批量提交</a></div>

                <div class="submCon01 submCon">
                    <div id="messageBox" class="msg label" style="display:none">输入有误，请先更正。</div>
                    <p id="errMsg" class="msg label"></p>

                    <p id="facevalues" class="choose">
                    </p>

                    <p id="faceval"></p>

                    <p>
                        <label for="">充值卡序列号：</label>
                        <input type="text" name="cardno" id="cardno" placeholder="请输入充值卡卡号" class="accInput">
                    </p>

                    <p>
                        <label for="">充值卡密码：</label>
                        <input type="text" name="cardpass" id="cardpass" placeholder="请输入充值卡密码" class="accInput">
                    </p>
                    <p>
                        <label for="">验证码：</label>
                        <input type="text" class="accInput" style="width: 163px" name="captcha" id="captcha" placeholder="验证码"
                               maxlength="10"/>&nbsp;
                        <img id="cimg" src="/captcha/image" onclick="refreshmvp();"
                             style="width:85px; height: 40px; border: 1px solid; cursor: hand; clear: both;"
                             title="看不清？点击更换另一个。" />
                    </p>
                    <p class="label pushT30">
                        <button id="btnPost" onclick="return false" class="btn_small">确认提交</button>
                        <button id="reset" type="reset" class="btn_small gray_small">重置</button>
                    </p>
                </div>
                <h2 class="borderT">最近订单查询
                    <span>提示：提交完成之后，点击刷新按钮，实时查看入账情况</span>
                    <a id="refresh" href="javascript:void(0)" class="re"></a>
                </h2>
                <table class="border_table" id="dataTable">
                    <thead class="table_title">
                    <tr>
                        <th>充值卡号</th>
                        <th>运营商</th>
                        <th>声明面值</th>
                        <th>结算面值</th>
                        <th>实际支付额</th>
                        <th>支付状态</th>
                        <th>交易时间</th>
                        <th>回调结果</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>