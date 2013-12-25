<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta  name="description" content="欧飞网充值卡寄售平台" />
    <meta name="keywords" content="欧飞网充值卡寄售平台" />
    <title>注册 - 欧飞网充值卡寄售平台</title>
    <script src="http://pic.ofcard.com/jslib/jquery/jquery-1.8.1.min.js" type="text/javascript"></script>
    <script src="/js/jquery.validate.min.js" type="text/javascript"></script>
    <script language="javascript" src="/js/provincesdata.js" charset="UTF-8"></script>
    <script language="javascript" src="/js/jquery.provincesCityCounty.js" charset="UTF-8"></script>
    <script src="/js/jquery.blockUI.js" type="text/javascript"></script>
    <script src="/js/jquery.form.js" type="text/javascript"></script>
    <script>
        //商户类型选择
        function ChkRealityType(val){
            $("#label1").removeClass();
            $("#label2").removeClass();
            if(val==1){
                document.getElementById("company").style.display='';
                document.getElementById("idcard_1").style.display='';
                document.getElementById("idcard_2").style.display='none';
                $("#label1").addClass("Vrabom Visio");
                $("#label2").addClass("Vrabom");
                $("#merchantType").val("company");
            }else{
                document.getElementById("company").style.display='none';
                document.getElementById("idcard_1").style.display='none';
                document.getElementById("idcard_2").style.display='';
                $("#label1").addClass("Vrabom");
                $("#label2").addClass("Vrabom Visio");
                $("#merchantType").val("personal");
            }
        }

        $(document).ready(function() {
            $("#label1").addClass("Vrabom Visio");

            $("#area").ProvinceCity("province","city","country");
            $("#country").attr("validate","{required:true,messages:{required:'请选择所属区域'}}");
            $("#province").addClass("VSelect");
            $("#city").addClass("VSelect pushZ1");
            $("#country").addClass("VSelect");

            // 校验form表单中的参数是否验证
            $("#thisFrm").validate({
                rules: {
                    userName: {
                        required: true,
                        rangelength:[4,20],
                        isNickName:true
                    },
                    userPassword: {
                        required: true,
                        rangelength: [6,20]
                    },
                    confirmPassword: {
                        required: true,
                        rangelength: [6,20],
                        equalTo: '#userPassword'
                    },
                    companyName: {
                        required: '#realitytype1:checked',
                        maxlength: 20
                    },
                    contactName: {
                        required:true,
                        maxlength: 10
                    },
                    companyLicenseNumber: {
                        required:'#realitytype1:checked',
                        maxlength: 20
                    },
                    identityCard: {
                        required:'#realitytype2:checked',
                        isIdCardNo: true
                    },
                    cellPhoneNumber: {
                        required:true,
                        isMobile:true
                    },
                    email: {
                        required:true,
                        email:true
                    },
                    captcha: {
                        required:true
                    }
                },
                messages: {
                    userName: {
                        required: "请输入登录账户",
                        rangelength: "必须是4到20位字符!",
                        isNickName: "该用户名已被使用"
                    },
                    userPassword:{
                        required: "请设置登录密码",
                        rangelength: "必须是6至20位字符"
                    },
                    confirmPassword: {
                        required: "请再次输入登录密码",
                        rangelength: "必须是6至20位字符",
                        equalTo: "两次输入的密码不一致"
                    },
                    companyName: {
                        required: "请输入公司名称",
                        maxlength: "公司名称不能超过20个字符"
                    },
                    contactName: {
                        required: "请输入联系人",
                        maxlength: "联系人名称不能超过10个字符"
                    },
                    companyLicenseNumber: {
                        required: "请输入执照号码",
                        maxlength: "执照号码不能超过20个字符"
                    },
                    identityCard: {
                        required: "请输入身份证号",
                        isIdCardNo: "请正确输入您的身份证号码"
                    },
                    cellPhoneNumber: {
                        required: "请输入手机号码",
                        isMobile: "手机号格式不正确"
                    },
                    email: {
                        required: "用于收货和找回密码,请认真输入",
                        email: "邮箱格式不正确"
                    },
                    captcha: {
                        required: "请输入验证码"
                    }
                }
            });

            var options = {
                url: '/register',
                type:'post',
                beforeSubmit:function(){
                    $('#loadingBox').html("注册中，请等待。。。");
                    if($("#thisFrm").valid()){
                        $.blockUI({message: $('#loadingBox'),overlayCSS: { backgroundColor: '#2f4f4f' }});
                        return true;
                    }else{
                        return false;
                    }
                },
                dataType:'json',
                success:function(data) {
                    if(data.result=='success'){
                        //
                        setTimeout(function(){
                            $('#loadingBox').html("注册成功！");
                            setTimeout(function(){
                                window.location.href='/login';
                            }, 3000);
                        },3000)
                    }else{
                setTimeout(function(){
                    var emsg=data.errMsg;
                    $('#loadingBox').html(emsg);
                    $('#errMsg').html(emsg);

                    setTimeout(unblock, 2000);
                    refreshmvp();
                },3000)
            }
        }//edn success
            };
            $('#thisFrm').ajaxForm(options);

            $(".ShowXieyi").toggle(function() {$(".xieyiArea").show();},function(){$(".xieyiArea").hide();});
        })
        var VerifyCodeTimes=1;
        function refreshmvp(){
            $("#cimg").attr("src", "/captcha/image?"+(VerifyCodeTimes++));
        }

        jQuery.validator.addMethod("isNickName",function(value){
            return valNickName(value);
        },"该用户名已被使用");

        //验证用户名是否存在
        function valNickName(value){
            var ret = $.ajax({
                url : "/register/checkusername",
                async : false,
                cache : false,
                type : 'post',
                data : "username="+value
            }).responseText;

            var result = eval("(" + ret + ")");
            return !result.data;
        }
        jQuery.validator.addMethod("isMobile", function(value) {
            var length = value.length;
            return (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(147)|(18[0-9]{1}))+\d{8})$/.test(value));
        }, "手机号格式不正确");

        //验证身份证
        function isIdCardNo(num) {
            var qiyeChk = document.getElementById("realitytype1") ;
            if(qiyeChk.checked){
                return true  ;
            }else {
                num = num.toUpperCase();
                //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
                if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
                    return false;
                }
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                //下面分别分析出生日期和校验位
                var len, re;
                len = num.length;
                if (len == 15) {
                    re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
                    var arrSplit = num.match(re);

                    //检查生日日期是否正确
                    var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
                    var bGoodDay;
                    bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                    if (!bGoodDay){
                        return false;
                    } else {
                        //将15位身份证转成18位
                        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                        var nTemp = 0, i;
                        num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
                        for(i = 0; i < 17; i ++) {
                            nTemp += num.substr(i, 1) * arrInt[i];
                        }
                        num += arrCh[nTemp % 11];
                        return num;
                    }
                }
                if (len == 18) {
                    re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
                    var arrSplit = num.match(re);

                    //检查生日日期是否正确
                    var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
                    var bGoodDay;
                    bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
                    if (!bGoodDay) {
                        return false;
                    } else {
                        //检验18位身份证的校验码是否正确。
                        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                        var valnum;
                        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
                        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
                        var nTemp = 0, i;
                        for(i = 0; i < 17; i ++) {
                            nTemp += num.substr(i, 1) * arrInt[i];
                        }
                        valnum = arrCh[nTemp % 11];
                        if (valnum != num.substr(17, 1)) {
                            return false;
                        }
                        return num;
                    }
                }
                return false;
            }
        }

        jQuery.validator.addMethod("isIdCardNo", function(value) {
            return isIdCardNo(value);
        }, "请正确输入您的身份证号码");

        function unblock(){
            $.unblockUI({message: null});
        }
    </script>
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <link type="text/css" rel="stylesheet" href="/css/error.css">
    <style>
    </style>
</head>
<body>
<div class="content w1200">
    <div class="pagesarea">
        <form action="/register" name="thisFrm" id="thisFrm" method="post" autocomplete="off">
            <input type="hidden" name="merchantType" id="merchantType" value="company">
            <ul class="form s1000">
                <li class="info1"><h4>账户信息</h4></li>
                <li>
                    <label for="userName">账户名：</label>
                    <input id="userName" class="middle" type="text" name="userName">
                    <span class="gray">字母、中文、数字组成,4到20位字符!</span>
                </li>
                <li>
                    <label for="userPassword">登录密码：</label>
                    <input type="password" name="userPassword" id="userPassword" class="middle">
	                <span class="gray">数字、字母、特殊符组合,6到20位字符!</span>
                </li>
                <li>
                    <label for="confirmPassword">确认密码：</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" class="middle">
	                <span class="gray">再输入一次密码!</span>
                </li>

                <li class="info1"><h4>基本信息</h4></li>
                <li><label>商户类型：</label>
                    <ul class="transverse">
                        <li><input type="radio" name="realitytype" id="realitytype1" value="1"
                                   onclick="ChkRealityType(this.value)" checked="checked" class="Visiput">
                            <label id="label1" for="realitytype1" class="Vrabom">企业用户</label></li>
                        <li><input type="radio" name="realitytype" id="realitytype2" value="2"
                                   onclick="ChkRealityType(this.value)" class="Visiput">
                            <label id="label2" for="realitytype2" class="Vrabom">个人用户</label></li>
                    </ul>
                </li>
                <li class="showInput">
                    <ul class="common info">
                        <li id="company">
                            <label for="companyName">公司名称：</label>
                            <input type="text" id="companyName" name="companyName" class="middle">
                        </li>
                        <li>
                            <label for="contactName">联系人：</label>
                            <input type="text" id="contactName" name="contactName" class="middle">
                        </li>
                        <li id="idcard_1">
                            <label for="companyLicenseNumber">执照号码：</label>
                            <input type="text" id="companyLicenseNumber" name="companyLicenseNumber" class="middle">
                        </li>
                        <li id="idcard_2" style="display: none;">
                            <label for="identityCard">身份证：</label>
                            <input type="text" id="identityCard" name="identityCard" class="middle">
                        </li>
                    </ul>
                </li>
                <li id="area"><label>所在区域：</label></li>
                <li class="info1"><h4>联系方式</h4></li>
                <li>
                    <label for="cellPhoneNumber">手机号码：</label>
                    <input type="text" id="cellPhoneNumber" name="cellPhoneNumber" class="middle">
                </li>
                <li>
                    <label for="email">E-Mail：</label>
                    <input type="text" id="email" name="email" class="middle">
                </li>
                <li><!-- input输入正确加class＝"correct",否则加"error" -->
                    <label for="captcha">验证码：</label>
                    <input type="text" class="shorter" name="captcha" id="captcha" maxlength="10"/>&nbsp;
                    <img id="cimg" src="/captcha/image" onclick="refreshmvp();"
                         style="width:85px; height: 35px; border: 1px solid; cursor: hand; clear: both;"
                         title="看不清？点击更换另一个。" />
                </li>
                <li class="label">
                    <p id="loadingBox" hidden="hidden"></p>
                    <p id="errMsg" style="color: red;"></p>
                </li>
                <li class="btnarea"><button type="submit" name="Submit" class="free">同意以下协议并注册</button></li>
                <li class="label"><a href="javascript:void(0)" class="ShowXieyi">《第四方支付用户注册协议》</a></li>
            </ul>
        </form>
    </div>
</div> <!-- end regCon -->
</div>
<div class="xieyiArea tcontent hide">
    <div class="block xieyi">
        <h2 class="tcenter">第四方支付用户注册协议</h2>
        <h4>1、 服务条款的确认和接纳</h4>
        <p>	欧飞第四方支付网站（以下简称“第四方支付”）提供的各项基于互联网的服务的所有权和运作权归江苏欧飞电子商务有限公司所有。本站提供的服务将完全按照其发布的服务条款和操作规则严格执行。服务使用人（以下称“用户”）在本站发生访问、注册、登陆、查询、提现等网络行为时均默示同意本站全部服务条款，用户必须确认：本协议条款是处理双方权利义务的约定，除非违反国家强制性法律，否则始终有效。使用第四方支付及其提供的服务，您将受本服务协议和所有有关的政策、条件和准则的约束。如果您不同意本使用条件中的任何一条，您可以不注册第四方支付。</p>

        <h4>2、服务内容与条款</h4>
        <div class="subP">
            <h6>2.1支付服务</h6>
            <p>1、向用户提供接口文档和技术支持；但必须同第四方支付经过下一轮的合作谈判和协议签署；</p>
            <p>2、为用户提供各种形式的款项代收服务以及网上交易信息查询和管理功能；</p>
            <p>3、用户必须拥有合法的网站，具备开发能力的技术力量；如果用户网站不合法，则第四方支付可以随时关闭服务，并且不予结算；</p>
            <h6>2.2提现服务</h6>
            <p>1、用户可于任何工作日的工作时间（9:00-17:00），将账户内T-3日前的“冻结资金”申请提现；第四方支付会将扣除手续费后的金额结算到用户的银行账户或欧飞账户内；</p>
            <p>2、但若因用户原因发生退款失败（如提供银行账户状态异常等），第四方支付不负任何责任，也不做冲正，且不退还已产生的提现手续费。</p>
            <h6>2.3用户隐私和安全</h6>
            <p>尊重用户个人隐私是本站的一项基本政策。除了用户授权或者国家机关要求外，第四方支付绝对不会公开、编辑或透露其注册资料及保存在本站中的非公开内容，更不会向任何第三方出售、提供用户资料及相关信息。</p>
            <p>用户一旦注册成功，成为第四方支付的合法用户，将得到一个密码和用户名。您可随时根据指示改变您的密码。用户需谨慎合理的保存、使用用户名和密码。用户若发现任何非法使用用户帐号或存在安全漏洞的情况，请立即通知本站和向公安机关报案。</p>
        </div>
        <h4>3、所有权声明及责任限制</h4>
        <div class="subP">
            <h6>3.1网络服务内容的所有权</h6>
            <p>	本站定义的网络服务内容包括：文字、软件、声音、图片、录象、图表、广告中的全部内容；电子邮件的全部内容；本站为用户提供的其它信息。所有这些内容受版权、商标、标签和其它财产所有权法律的保护。所以，用户只能在本站和广告商授权下才能使用这些内容，而不能擅自复制、再造这些内容、或创造与内容有关的派生产品。本站所有的文章版权归原文作者和本站共同所有，任何人需要转载本站的文章，必须征得原文作者和本站授权。</p>
            <h6>3.2责任限制</h6>
            <p>	如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等本站会尽可能合理地协助处理善后事宜，并尽最大努力使客户免受损失。</p>
        </div>
        <h4>4、法律管辖和适用</h4>
        <p>	本协议的订立、执行和解释及争议的解决均应适用中国法律。</p>
        <p>	如发生本站服务条款与中国法律相抵触时，则这些条款将完全按法律规定重新解释，而其它有效条款继续有效。</p>
        <p>	如双方就本协议内容或其执行发生任何争议，双方应尽力友好协商解决；协商不成时，任何一方均可向千米网服务器所在地人民法院提起诉讼。</p>
    </div>
</div><!-- xieyiArea end -->
</body>
</html>