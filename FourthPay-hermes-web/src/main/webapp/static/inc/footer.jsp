<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<script>
    // 最新公告
    $.get( "/account/announce/list", function( json ) {
        if( json.result == "success") {
            var $title = $("#titles");
            $.each(json.data, function(i, item) {
                $title.append("<a>"+item.title+"</a>");
            });
        }
        else {
            $("#titles").append("暂无公告信息");
        }
    });
</script>
<div class="footer">
    <div class="footerLinks w1200">
        <dl class="coBank">
            <dt>合作银行</dt>
            <dd>
                <a href="http://www.ccb.com/" class="ccb" target="_blank"><span>中国建设银行</span></a>
                <a href="http://www.cmbchina.com/" class="cmb" target="_blank"><span>招商银行</span></a>
                <a href="http://www.bankcomm.com/" class="comm" target="_blank"><span>交通银行</span></a>
                <a href="http://www.boc.cn/ebanking/" class="boc" target="_blank"><span>中国银行</span></a>
                <a href="http://www.abchina.com/" class="abchina" target="_blank"><span>中国农业银行</span></a>
                <a href="http://www.cebbank.com/Site/ceb/cn" class="ceb" target="_blank"><span>中国光大银行</span></a>
            </dd>
        </dl>
        <dl class="coBusiness">
            <dt>商户合作</dt>
            <dd>
                <a href="https://www.alipay.com/" class="alipay" target="_blank"><span>支付宝</span></a>
                <a href="https://www.tenpay.com/" class="tenpay" target="_blank"><span>财付通</span></a>
                <a href="http://www.yeepay.com/" class="yeepay" target="_blank"><span>易宝支付</span></a>
                <a href="https://www.99bill.com/" class="bill" target="_blank"><span>快钱</span></a>
            </dd>
        </dl>
        <dl class="notice">
            <dt>公告</dt>
            <dd id="titles">
                <%--<a>关于11月5日广发还款、提现、代付等付款延迟的关于11月5日广发还款、提现、代付等付款延迟的</a>--%>
                <%--<a>提现，支付，付款等问题的讨论</a>--%>
            </dd>
        </dl>
    </div>
    <div class="footerCon w1200">
        <p>
            <a href="http://weibo.com/ofpay" target="_blank">官方微博</a>
            <a href="http://www.ofpay.com/career/?current=about" target="_blank">招聘信息</a>
            <a href="http://www.ofpay.com/contact/" target="_blank">联系我们</a>
        </p>
        <p class="copyright">苏ICP备B1-20110001-5 Copyright &copy; 2003-2013 江苏欧飞电子商务有限公司 all rights reserved.</p>
        <p>
            <a href="http://www.cyberpolice.cn/wfjb/" class="eImg eImg01" target="_blank"></a>
            <a href="http://nanjing.bcpcn.com/" class="eImg eImg02" target="_blank"></a>
            <a href="http://www.njgs.gov.cn/" class="eImg eImg03" target="_blank"></a>
        </p>
    </div>
</div>