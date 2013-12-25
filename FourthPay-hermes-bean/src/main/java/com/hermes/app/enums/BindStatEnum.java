package com.hermes.app.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 银行卡状态枚举
 *
 * @author of644
 */
public enum BindStatEnum {
    处理中("1"), 审核通过("2"), 审核不通过("3"), 冻结("4"), 解冻("5");

    private String value;

    private static final Map<String, BindStatEnum> map =
            new HashMap<String, BindStatEnum>();

    static {
        for (BindStatEnum element : BindStatEnum.values()) {
            map.put(element.value, element);
        }
    }

    private BindStatEnum(String value) {
        this.value = value;
    }

    public static BindStatEnum fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        throw new NoSuchElementException(name + "not found");
    }
}
