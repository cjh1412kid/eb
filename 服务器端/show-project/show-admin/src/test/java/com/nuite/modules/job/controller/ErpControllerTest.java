package com.nuite.modules.job.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.nuite.modules.sys.shiro.ShiroUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ErpControllerTest {
    @Test
    public void aesEncryptTest() throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        String content = "1";

        SecretKeySpec key = new SecretKeySpec("1234567890123456".getBytes(), "AES");
        AES aes = SecureUtil.aes(key.getEncoded());
        // 解密为字符串
        String encryptHex = aes.encryptHex(content, CharsetUtil.UTF_8);

//        Cipher cipher = Cipher.getInstance("AES/ECB/pkcs5padding");// 创建密码器
//
//        byte[] byteContent = content.getBytes("utf-8");
//
//        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
//
//        byte[] result = cipher.doFinal(byteContent);// 加密
//
//        String encryptHex = HexUtil.encodeHexStr(result);
        System.out.println(encryptHex);

//        // 构建
//        AES aes = SecureUtil.aes(enCodeFormat);
//        // 加密为16进制表示
//        String encryptHex = Base64.encode(aes.encrypt(content));
//        System.out.println(encryptHex);
    }

    @Test
    public void aesTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        SimpleHash object = new SimpleHash(ShiroUtils.hashAlgorithmName, "123");
        System.out.println(object);
        System.out.println(DigestUtils.sha256Hex("123"));
    }
}