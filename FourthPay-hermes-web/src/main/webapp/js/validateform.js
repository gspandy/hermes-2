var validateRegExp = {
    decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
    intege: "^-?[1-9]\\d*$", //整数
    intege1: "^[1-9]\\d*$", //正整数
    intege2: "^-[1-9]\\d*$", //负整数
    num: "^([+-]?)\\d*\\.?\\d+$", //数字
    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
    color: "^[a-fA-F0-9]{6}$", //颜色
    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
    letter: "^[A-Za-z]+$", //字母
    letter_l: "^[a-z]+$", //小写字母
    letter_u: "^[A-Z]+$", //大写字母
    mobile: "^0?(13|15|18|14)[0-9]{9}$", //手机
    notempty: "^\\S+$", //非空
    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
    fullNumber: "^[0-9]+$", //数字
    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
    zipcode: "^\\d{6}$", //邮编
    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};

//验证规则
var validateRules = {
    strLength: function(str) {
        var len = 0;
        for (var i = 0; i < str.length; i++) {
            var c = str.charCodeAt(i);
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                len++;
            } else {
                len += 2;
            }
        }
        return len;
    },
    isNull: function(str) {
        return (str == "" || typeof str != "string");
    },
    betweenLength: function(str, min, max) {
        var _len = validateRules.strLength(str);
        return (_len >= min && _len <= max);
    },
    isUid: function(str) {
        return new RegExp(validateRegExp.username).test(str);
    },
    isZipcode: function(str) {
        return new RegExp(validateRegExp.zipcode).test(str);
    },
    fullNumberName: function(str) {
        return new RegExp(validateRegExp.fullNumber).test(str);
    },
    isPwd: function(str) {
        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
    },
    /*规则：6-20，数字、字母和符号。
    数字：6-10弱，11及后为中    可通过验证，but提示：您的密码过于简单，有被盗风险，建议您修改
    字母：6-10弱，11及后为中      可通过验证，but提示：您的密码过于简单，有被盗风险，建议您修改
    字符：6-10弱，11及后为中    可通过验证，but提示：您的密码过于简单，有被盗风险，建议您修改
    数字、字母或字符两者组合：6- 10中 ，11及后为强
    数字、字母和字符三者组合：6及后为强*/
    pwdLevel: function(value) {
        var pattern_1 = /^.*([\W_])+.*$/i;
        var pattern_2 = /^.*([a-zA-Z])+.*$/i;
        var pattern_3 = /^.*([0-9])+.*$/i;
        var level = 0;
        if (value.length > 10) {
            level++;
        }
        if (pattern_1.test(value)) {
            level++;
        }
        if (pattern_2.test(value)) {
            level++;
        }
        if (pattern_3.test(value)) {
            level++;
        }
        if (level > 3) {
            level = 3;
        }
        return level;
    },
    isPwdRepeat: function(str1, str2) {
        return (str1 == str2);
    },
    isEmail: function(str) {
        return new RegExp(validateRegExp.email).test(str);
    },
    isTel: function(str) {
        return new RegExp(validateRegExp.tel).test(str);
    },
    isMobile: function(str) {
        return new RegExp(validateRegExp.mobile).test(str);
    },
    checkType: function(element) {
        return (element.attr("type") == "checkbox" || element.attr("type") == "radio" || element.attr("rel") == "select");
    },
    isRealName: function(str) {
        return new RegExp(validateRegExp.realname).test(str);
    },
    isCompanyname: function(str) {
        return new RegExp(validateRegExp.companyname).test(str);
    },
    isCompanyaddr: function(str) {
        return new RegExp(validateRegExp.companyaddr).test(str);
    },
    isCompanysite: function(str) {
        return new RegExp(validateRegExp.companysite).test(str);
    }
};

var validateUI = {
    showOk: function(options, msg) {
        var _this = $(options.elem + 'Tip'),
            _elem = $(options.elem);
        _this.removeClass('alertTipError onfocus').addClass('alertTip').show();
        _this.find('span').html(msg ? msg : options.onCorrect);
        _elem.removeClass('error').attr('sta', '2');
        return true;
    },
    showErr: function(options, msg) {
        var _this = $(options.elem + 'Tip'),
            _elem = $(options.elem);
            _this.removeClass('alertTip onfocus').addClass('alertTipError').show();
            _this.find('span').html(msg);
            _elem.addClass('error').attr('sta', '1');
        return false;
    },
    showFocus: function(options) {
        var _this = $(options.elem + 'Tip');
        _this.removeClass('alertTipError alertTip').show().addClass('onfocus');
        _this.find('span').html(options.onFocus);
    },
    hideTip: function(options) {
        $(options.elem + 'Tip').hide();
        $(options.elem).attr('sta', '2');
    }
}

var validateChkFun = {
    init: function(fun) {
        fun.call(this);
    },
    defaultChk: function(options) {
        /*
            //用于普通input合法性验证
            elem: '#phone',
            onFocus: '提示信息',
            isNull: '为空提示信息',
            onCorrect: '正确提示信息',
            regExp: validateRegExp.mobile,//验证正则
            onlyNotNullChk: false, //默认false,输入性验证，即不为空的状态下再验证数据的合法性
            onError: {
                badFormat: "出错提示信息"
            }
         */
        var _this = $(options.elem);

        _this.bind('focus', function() {
            validateUI.showFocus(options);
        })
        .bind('blur', function() {
            validateUI.hideTip(options);
        });
    }
};