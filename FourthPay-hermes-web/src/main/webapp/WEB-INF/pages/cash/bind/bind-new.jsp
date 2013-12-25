<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="description" content="欧飞网充值卡寄售平台"/>
    <meta name="keywords" content="欧飞网充值卡寄售平台"/>
    <title>提现管理 － 申请提现 － 添加银行卡 - 欧飞网充值卡寄售平台</title>
    <script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/error.css"/>
    <script>
        $(document).ready(function () {
            // tab 高亮
            bindTab("apply", "apply_3");

            // 表单验证
            var $validator = $("#bankBindForm").validate({
                rules: {
                    banks: {
                        required : true
                    },
                    accountusername : {
                        required : true
                    },
                    accountno : {
                        required : true
                    },
                    accountnoagain : {
                        required : true,
                        equalTo: "#accountno"
                    }
                },
                messages: {
                    banks: {
                        required : "请选择银行类型"
                    },
                    accountusername : {
                        required : "请输入开户名"
                    },
                    accountno : {
                        required : "请输入银行卡号"
                    },
                    accountnoagain : {
                        required : "请再次输入银行卡号",
                        equalTo: "两次卡号输入不一致"
                    }
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    if ( element.is(":checkbox") )
                        error.appendTo(element.parent().next());
                    else
                        error.insertAfter(element);
                }
            });

            // 初始化下拉框
            $.get("/cash/bind/banks", function (banks) {
                $("#banks").append("<option value=''>选择银行</option>");
                $.each(banks, function (i, val) {
                    $("#banks").append("<option value=" + val.BANKCODE + ">" + val.BANKNAME + "</option>");
                });
            });

            // 绑定提交事件
            $("#J-submit").click(function () {
                if($("#bankBindForm").valid()){
                    $.post("/cash/bind/add", $('#bankBindForm').serializeArray(), function (data) {
                        if (data === "success") {
                            window.location.href = "/cash/bind/success";
                        }
                        else if (data === "fail") {
                            $("#errMsg").html("");
                            $("#errMsg").html("绑定银行卡失败");
                            $("#errMsg").show();
                        }
                        else {
                            $("#errMsg").html("");
                            $("#errMsg").html(data);
                            $("#errMsg").show();
                        }
                    });
                }
            });

            $("#banks").change(function () {
                var bankcode = $("#banks").val();
                if (bankcode != "") {
                    $("#bankcode").val($("#banks").val());
                    $("#bankname").val($("#banks option:selected").text());
                }
                else {
                    $("#bankcode").val("");
                    $("#bankname").val("");
                }
            });
        });
    </script>
</head>
<body>
<form name="bankBindForm" id="bankBindForm">
<div class="content">
    <div class="accInfo w1200">
        <h3>绑定银行卡</h3>
        <p id="errMsg" class="msg label" style="display: none"></p>
        <p><label for="">银行类型：</label>
            <select id="banks" name="banks"></select>
            <input type="hidden" id="bankcode" name="bankcode">
            <input type="hidden" id="bankname" name="bankname">
        </p>
        <p><label for="">开户名：</label>
            <input type="text" name="accountusername" class="accInput">
        </p>

        <p><label for="">银行卡号：</label>
            <input type="text" id="accountno" name="accountno" class="accInput">
        </p>

        <p><label for="">再输一次：</label>
            <input type="text" name="accountnoagain" class="accInput">
        </p>

        <p><label for="">备注：</label>
            <input type="text" name="remarkinfo" class="accInput">
        </p>

        <p class="label pushT30">
            <input type="button" id="J-submit" class="btn_small" value="提交" />
        </p>
    </div>
</div>
</body>
</html>