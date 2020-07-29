package com.nuite.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * erp对接时使用的加密、解密算法
 */
public class AESUtils {
    private final static String AES_PASSWORD = "1234567890123456";

    public static String decrypt(String data) {
        SecretKey secretKey = new SecretKeySpec(AES_PASSWORD.getBytes(), "AES");
        byte[] enCodeFormat = secretKey.getEncoded();
        AES aes = SecureUtil.aes(enCodeFormat);
        return aes.decryptStr(data, CharsetUtil.CHARSET_UTF_8);
    }
}
