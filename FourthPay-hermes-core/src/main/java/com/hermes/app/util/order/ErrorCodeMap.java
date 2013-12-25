package com.hermes.app.util.order;

import java.util.HashMap;

/*******************************************************************************
 * 返回商户信息类
 * 
 * @author yushaofang
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class ErrorCodeMap extends HashMap {
	public static ErrorCodeMap errorMap = new ErrorCodeMap();

	// 支付成功
	public static int CARDPAY_SUCCESS = 2000;
	// 数据接收成功
	public static int DATA_RECEIVE_SUCCESS = 2001;
	// 不支持该类卡
	public static int NOT_SUPPORT_CARD = 2002;

	// 签名验证失败
	public static int SIGN_VALID_FAIL = 2003;

	// 订单内容重复
	public static int ORDER_CONTENT_DUPLICATE = 2004;

	// 该卡密已经有被使用的记录
	public static int CARDPASS_BEEN_USED = 2005;

	// 订单号已经存在
	public static int ORDERNO_DUPLICATE = 2006;

	// 数据非法
	public static int DATA_INVALID = 2007;

	// 非法用户
	public static int USERCODE_INVALID = 2008;

	// 暂时停止收该类卡
	public static int CARD_RECEIVE_STOP = 2009;

	// 卡密无效
	public static int CARD_NOPASS_INVALID = 2010;

	// 面值错误
	public static int CARD_FACEVAL_ERROR = 2011;

	// 网络出错，请稍候再试
	public static int NETWORK_TEMP_ERROR = 2012;

	// 系统繁忙
	public static int SYSTEM_BUSY = 2013;

	// 不存在该笔订单
	public static int NOTEXIST_ORDERNO = 2014;

	// 未知请求
	public static int UNKNOW_MODE = 2015;

	// 密码错误
	public static int CARDPASS_ERROR = 2016;

	// 支付失败，匹配订单失败
	public static int MATCHORDER_TIMEOUT = 2017;

	// 余额不足
	public static int BALANCE_LOW = 2018;

	// 地区运营商维护
	public static int CARDAREA_MAINTENANCE = 2019;

	// 提交次数过多
	public static int EXCEED_MAXTRYCOUNT = 2020;
	
	 // 失败，面值小于10元卡
    public static int CARD_SMALLVALUE = 2021;
    
    // 提交过于频繁
    public static int CARD_SUBMITBUSY = 2022;

	// OTHER_ERRORS
	public static int OTHER_ERRORS = 2999;

	static {
		errorMap.put(new Integer(CARDPAY_SUCCESS), "支付成功");
		errorMap.put(new Integer(DATA_RECEIVE_SUCCESS), "数据接收成功正在验证卡密");
		errorMap.put(new Integer(NOT_SUPPORT_CARD), "不支持该类卡或该面值");
		errorMap.put(new Integer(ORDER_CONTENT_DUPLICATE), "订单内容重复");
		errorMap.put(new Integer(CARDPASS_BEEN_USED), "该卡密已经有被使用的记录");
		errorMap.put(new Integer(ORDERNO_DUPLICATE), "订单号重复");
		errorMap.put(new Integer(DATA_INVALID), "数据非法");
		errorMap.put(new Integer(USERCODE_INVALID), "非法用户");
		errorMap.put(new Integer(SIGN_VALID_FAIL), "签名失败");
		errorMap.put(new Integer(CARD_RECEIVE_STOP), "暂停收该类卡或该面值");
		errorMap.put(new Integer(CARD_NOPASS_INVALID), "充值卡无效");
		errorMap.put(new Integer(CARD_FACEVAL_ERROR), "实际面值{0}元");
		errorMap.put(new Integer(NETWORK_TEMP_ERROR), "处理失败卡密未使用");
		errorMap.put(new Integer(SYSTEM_BUSY), "系统繁忙");
		errorMap.put(new Integer(UNKNOW_MODE), "未知请求");
		errorMap.put(new Integer(NOTEXIST_ORDERNO), "不存在该笔订单");
		errorMap.put(new Integer(CARDPASS_ERROR), "密码错误");
		errorMap.put(new Integer(MATCHORDER_TIMEOUT), "匹配订单失败卡密未处理");
		errorMap.put(new Integer(BALANCE_LOW), "卡中余额不足");
		errorMap.put(new Integer(CARDAREA_MAINTENANCE), "运营商维护");
		errorMap.put(new Integer(EXCEED_MAXTRYCOUNT), "提交次数过多");
		errorMap.put(new Integer(CARD_SMALLVALUE), "失败，面值小于10元卡");
		errorMap.put(new Integer(CARD_SUBMITBUSY), "提交过于频繁");
		errorMap.put(new Integer(OTHER_ERRORS), "其他错误");
	}

	/***************************************************************************
	 * 返回错误信息
	 * 
	 * @param errCode
	 * @return
	 */
	public static String getErrorMsg(Integer errCode) {
		if (errorMap.containsKey(errCode)) {
			return (String) errorMap.get(errCode);
		} else {
			return "未知错误！";
		}
	}
	
	/***************************************************************************
     * 返回错误信息
     * 2013.7.22 zlj 修改2011的错误码
     * @param errCode
     * @return
     */
    public static String getErrorMsgs(Integer errCode,Float retvalue) {
        if (errorMap.containsKey(errCode)) {
            if(CARD_FACEVAL_ERROR == errCode)
            {
                errorMap.remove(CARD_FACEVAL_ERROR);
                errorMap.put(new Integer(CARD_FACEVAL_ERROR), "实际面值"+retvalue+"元");
            }
            return (String) errorMap.get(errCode);
        } else {
            return "未知错误！";
        }
    }

}
