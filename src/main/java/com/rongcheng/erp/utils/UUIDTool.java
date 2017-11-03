package com.rongcheng.erp.utils;

import java.util.UUID;

/**
 * UUID工具包
 * @author 赵滨
 */
public class UUIDTool {

    /**
     * 创建UUID字符串，32个数字，4个间隔符，共36位
     * @return
     * @author 赵滨
     */
    public static String createUUIDString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
