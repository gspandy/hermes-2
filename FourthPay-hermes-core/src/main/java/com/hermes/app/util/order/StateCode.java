package com.hermes.app.util.order;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态交易码，数据库中对应的stat字段
 * @author tujianying/of821
 */
public class StateCode {
    public static final String B_RECEIVED = "1";  // 收卡订单状态:已受理
    public static final String B_SUCC_MATCH = "2"; // 收卡订单状态:金额面值完全匹配
    public static final String B_SUCC_GREATER = "3"; // 收卡订单状态:实际金额大于用户所选面值
    public static final String B_SUCC_LESS = "4"; // 收卡订单状态:实际金额小于用户所选面值
    public static final String B_FAIL_INVALID = "5"; // 收卡订单状态:卡密无效
    public static final String B_STARTGETORDER = "6"; // 收卡订单状态:开始获取订单
    public static final String B_GETORDERSUCC = "7"; // 收卡订单状态:获取订单成功
    public static final String B_GETORDERFAIL = "8"; // 收卡订单状态:获取订单失败
    public static final String B_STARTVALIDCARD = "9"; // 收卡订单状态:开始验卡
    public static final String B_VALIDCARDSUCC = "10"; // 收卡订单状态:验卡成功
    public static final String B_VALIDCARDFAIL = "11"; // 收卡订单状态:验卡失败
    public static final String B_CHARGING = "12"; // 收卡订单状态:充值中
    public static final String B_CHARGEFAIL = "13"; // 收卡订单状态:提交充值发生错误
    public static final String B_SYSTEM_ERROR = "14"; // 收卡订单状态:系统错误
    public static final String B_MANUALOPT = "15"; // 收卡订单状态:需要人工干预
    public static final String B_CARDPASSERROR = "16"; // 收卡订单状态:错误的卡密码
    public static final String B_INVALIDORDER = "17"; // 收卡订单状态:无效订单
    public static final String B_BALANCE_LOW = "18"; // 收卡订单状态:余额不足

    /**
     * 根据界面上传过来的状态与平台内部的状态进行对应
     * @param formState
     * @return
     */
    public static List<String> generalStateList(String formState){
        if(null == formState){
            return null;
        }

        List<String> states = new ArrayList<String>();
        //成功
        if("1".equals(formState)){
            states.add(B_SUCC_MATCH);  //2:收卡订单状态:金额面值完全匹配
            states.add(B_SUCC_GREATER);  //3:收卡订单状态:实际金额大于用户所选面值
            states.add(B_SUCC_LESS);  //4:收卡订单状态:实际金额小于用户所选面值
        }
        //失败
        else if("2".equals(formState)){
            states.add(B_FAIL_INVALID); //5: 收卡订单状态:卡密无效
            states.add(B_GETORDERFAIL); //8:收卡订单状态:获取订单失败
            states.add(B_VALIDCARDFAIL); //11:收卡订单状态:验卡失败
            states.add(B_CHARGEFAIL); //13:收卡订单状态:提交充值发生错误
            states.add(B_SYSTEM_ERROR); //14:收卡订单状态:系统错误
            states.add(B_CARDPASSERROR); //16:收卡订单状态:错误的卡密码
            states.add(B_INVALIDORDER); //17:收卡订单状态:无效订单
            states.add(B_BALANCE_LOW); //18:收卡订单状态:余额不足
        }
        //处理中
        else if("0".equals(formState)){
            states.add(B_RECEIVED); //1: 收卡订单状态:已受理
            states.add(B_STARTGETORDER); //6:收卡订单状态:开始获取订单
            states.add(B_GETORDERSUCC); //7:收卡订单状态:获取订单成功
            states.add(B_STARTVALIDCARD); //9:收卡订单状态:开始验卡
            states.add(B_VALIDCARDSUCC); //10:收卡订单状态:验卡成功
            states.add(B_CHARGING); //12:收卡订单状态:充值中
            states.add(B_MANUALOPT); //15:收卡订单状态:需要人工干预
        }

        return states;
    }
}
