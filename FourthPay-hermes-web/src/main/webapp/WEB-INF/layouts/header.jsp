<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="header">
    <div class="headerCon w1200"> <a href="/login">
        <h1 class="logo">
           <span class="hide">欧飞网最专业的卡寄售平台</span>
        </h1></a>
        <span class="ttel">025-68202240<span class="userTop">欢迎您，尊敬的 <span class="orange">${username}</span><a href="/logout"
                                                                                                            class="cancel">注销</a></span></span>

        <div class="mainnav">
            <ul id="tabs">
                <li id="account"> <!-- 选中的当前状态加class="current" -->
                    <a href="/account"><span>我的账户</span></a><em class="to-top"></em>

                    <div id="accountdiv" class="menunav hide">
                        <a href="/account" id="account_1">账户首页</a>
                        <a href="/account/info" id="account_2">基本资料</a>
                        <a href="/account/security" id="account_3">安全设置</a>
                        <a href="/account/api" id="account_4">API接入</a>
                    </div>
                </li>
                <c:if test="${userflag == 1}">
                <li id="order">
                    <a href="/order/cardorder"><span>订单管理</span></a><em class="to-top"></em>

                    <div id="orderdiv" class="menunav hide">
                        <a href="/order/cardorder" id="order_1">订单查询</a>
                        <a href="/order/dataanalyse" id="order_2" >数据分析</a>
                        <a href="/order/payorder" id="order_3" >支付订单</a>
                    </div>
                </li>
                <li id="apply">
                    <a href="/cash/apply"><span>提现管理</span></a><em class="to-top"></em>

                    <div id="applydiv" class="menunav hide">
                        <a href="/cash/apply" id="apply_1">申请提现</a>
                        <a href="/cash/order" id="apply_2">提现明细</a>
                        <a href="/cash/bind" id="apply_3">提现管理</a>
                    </div>
                </li>
                <li id="rate">
                    <a href="/rate"><span>支付费率</span></a><em class="to-top"></em>
                </li>
                <li id="card">
                    <a href="/card/single"><span>提交卡密</span></a><em class="to-top"></em>
                </li>
                </c:if>
            </ul>
        </div>
        <!-- end mainnav -->
    </div>
</div>