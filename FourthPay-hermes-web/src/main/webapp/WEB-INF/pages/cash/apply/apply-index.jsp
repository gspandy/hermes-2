<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="欧飞网充值卡寄售平台"/>
    <meta name="keywords" content="欧飞网充值卡寄售平台"/>
    <title>提现管理 － 申请提现 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/error.css"/>
    <script type="text/javascript">
        $(document).ready(function () {
            // tab 高亮
            bindTab("apply", "apply_1");

            // 表单验证
            var $validator = $("#applyForm").validate({
                rules: {
                    applyMoney: {
                        required : true,
                        number : true
                    },
                    cashpwd : {
                        required : true
                    },
                    card : {
                        required : true
                    }
                },
                messages: {
                    applyMoney: {
                        required : "请输入提现金额",
                        number: "请输入数字"
                    },
                    cashpwd : {
                        required : "请输入提现密码"
                    },
                    card : {
                        required : "请选择提现银行卡"
                    }
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    if ( element.is(":radio") )
                        error.appendTo($("#radioErr"));
                    else
                        error.insertAfter(element);
                }
            });

            // 输入完金额后显示费率
            $("#applyMoney").focusout(function () {
                calcCommision();
            });
            // 单选框绑定点击
            $(":radio").bind("click", function () {
                var accountflowid = $(this).val().split("|")[0];
                var bankcode = $(this).val().split("|")[1];
                $("#accountflowid").val(accountflowid);
                $("#bankcode").val(bankcode);

                calcCommision();
            });

            // 初始化的时候显示选中
            if ('${form.accountflowid}') {
                $("#" + "${form.accountflowid}").click();
            }

            //

        });

        // 计算手续费
        function calcCommision() {
            var bankcode = $("#bankcode").val();
            var applyMoney = $("#applyMoney").val();
            // 银行和金额均不为空
            if (bankcode && applyMoney) {
                $.get("/cash/apply/rate/", {"bankcode": bankcode, "applyMoney": applyMoney}, function (data) {
                    $("#commision").html(data + "元");
                    $("#commision").parent().show("slow");
                });
            }
            else {
                $("#commision").html("");
                $("#commision").parent().hide("slow");
            }
        }

    </script>
</head>
<body>
<form id="applyForm" name="applyForm" action="/cash/apply/new" method="POST" autocomplete="off">
    <input type="hidden" name="accountflowid" id="accountflowid">
    <input type="hidden" name="bankcode" id="bankcode">
    <input type="hidden" name="merleftmoney" value="${merleftmoney}">

    <div class="content">
        <div class="accInfo w1200">
            <h2 class="cash">
                申请提现<span>余额：<b class="orange">
                <fmt:formatNumber value="${merleftmoney}" type="currency" pattern="0.00"/></b> 元</span>
                <a href="/cash/bind/new" class="right"><s></s>添加银行卡</a>
            </h2>
            <c:if test="${!empty errMsg}">
                <p class="msg label">${errMsg}</p>
            </c:if>
            <p class="msg label" id="radioErr"></p>
            <p>
                <label for="" class="blabel">选择银行卡：</label>
                <c:choose>
                <c:when test="${!empty binds}">
                    <b class="bankcard">
                        <c:forEach var="i" items="${binds}">
                        <b class="card">
                            <input type="radio" id="${i.accountflowid}" value="${i.accountflowid}|${i.bankcode}" name="card">
                            <%--TODO <i class="bankofchina"></i>--%>
                            <span class="bankName">${i.bankname}</span>
                            <span class="bankNum">尾号<c:out
                                    value="${fn:substring(i.accountno, fn:length(i.accountno)-4 , fn:length(i.accountno))}"/></span>
                            <span class="userName">开户名：${i.accountusername}</span>
                            <%-- TODO <s>费率：10%</s>--%>
                            <span class="gray">限额500起</span>
                        </b>
                        </c:forEach>
                    </b>
                </c:when>
                <c:otherwise>
                    <a href="/cash/bind/new" class="accInput"><s></s>请绑定银行卡</a>
                </c:otherwise>
                </c:choose>
            </p>

            <p><label for="" class="blabel">提现金额：</label>
                <input type="text" id="applyMoney" name="applyMoney" value="${form.applyMoney}" class="accInput" placeholder="输入金额"> 元
            </p>

            <p hidden><label for="" class="blabel">手续费：</label>
                <span id="commision" class="orange"></span>
            </p>

            <p><label for="" class="blabel">提现密码：</label>
                <input type="password" id="cashpwd" name="cashpwd" class="accInput" placeholder="输入提现密码"><a href="/static/join.jsp"> 忘记提现密码？</a>
            </p>

            <p class="label pushT30">
                <button type="submit" class="btn_small">提交</button>
            </p>
        </div>
    </div>
</form>
</body>
</html>