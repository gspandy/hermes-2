package com.hermes.app.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @类功能说明：AES加解密类
 * @公司名称：江苏欧飞
 * @作者：fengjiao.Chen
 * @创建时间：2012-8-30 下午16:40:38
 * @版本：V1.0
 */
public class AES
{

    /**
     * 函数功能: 加密
     *
     * @author fengjiao.Chen 2012-8-30
     * @param sSrc 数据源
     * @param sKey 密钥
     * @return String 返回加密后的数据
     * @throws Exception
     */
    public static String Encrypt(String sSrc, String sKey) throws Exception
    {
        if (sKey == null)
        {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16)
        {
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 函数功能: 加密
     *
     * @author fengjiao.Chen 2012-8-30
     * @param sSrc 数据源
     * @param sKey 密钥
     * @return byte[] 返回加密后的数据
     * @throws Exception
     */
    public static byte[] Encryptbyte(String sSrc, String sKey) throws Exception
    {
        if (sKey == null)
        {
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16)
        {
            return null;
        }

        while (sSrc.length() % 16 != 0)
        {
            char c = 0;
            sSrc += c;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // Cipher cipher =
        // Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        // IvParameterSpec iv = new
        // IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        Cipher cipher = Cipher.getInstance("AES"); // 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return encrypted;
    }

    /**
     * 函数功能: 解密
     *
     * @author fengjiao.Chen 2012-8-30
     * @param sSrc 数据源
     * @param sKey 密钥
     * @return String 返回解密后的数据
     * @throws Exception
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception
    {
        try
        {
            // 判断Key是否正确
            if (sKey == null)
            {
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16)
            {
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            try
            {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 函数功能: 十六进制串转为字节数组
     *
     * @author fengjiao.Chen 2012-8-30
     * @param strhex 十六进制串
     * @return byte[] 返回字节数组
     * @throws Exception
     */
    public static byte[] hex2byte(String strhex)
    {
        if (strhex == null)
        {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1)
        {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++)
        {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    /**
     * 函数功能: 字节数组转为十六进制串
     *
     * @author fengjiao.Chen 2012-8-30
     * @param byte[] 字节数组
     * @return String 返回十六进制串
     * @throws Exception
     */
    public static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
            {
                hs = hs + "0" + stmp;
            }
            else
            {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 函数功能: 字节数组加密为字节数组
     *
     * @author fengjiao.Chen 2012-8-30
     * @param byte[] 字节数组
     * @param byte[] 字节数组 key
     * @return byte[]
     * @throws Exception
     */
    private static final byte[] fill(byte[] bytes, byte[] key)
    {
        if (bytes == null)
        {
            return null;
        }
        else
        {
            int bytesLength = bytes.length;
            int factor = key.length;

            if (bytesLength % factor == 0)
            {
                return bytes;
            }
            else
            {
                int newBytesLength = (bytesLength / factor + 1) * factor;
                byte[] newBytes = new byte[newBytesLength];

                for (int i = 0; i < bytesLength; i++)
                {
                    newBytes[i] = bytes[i];
                }

                for (int k = bytesLength; k < newBytesLength; k++)
                {
                    newBytes[k] = 0;
                }

                return newBytes;
            }
        }
    }

    /**
     * 函数功能: 过滤字节数组空格
     *
     * @author fengjiao.Chen 2012-8-30
     * @param byte[] 字节数组
     * @return byte[]
     * @throws Exception
     */
    private static final byte[] trim(byte[] bytes)
    {
        if (bytes == null)
        {
            return null;
        }
        else
        {
            int bytesLength = bytes.length;

            int i = bytesLength - 1;

            for (; i >= 0; i--)
            {
                if (bytes[i] != 0)
                {
                    break;
                }
            }

            byte[] newBytes = new byte[i + 1];

            for (int k = 0; k <= i; k++)
            {
                newBytes[k] = bytes[k];
            }

            return newBytes;
        }
    }

    /**
     * 函数功能: 字节数组解密为字节数组
     *
     * @author fengjiao.Chen 2012-8-30
     * @param byte[] 字节数组 sSrc
     * @param String 字节数组 sKey
     * @return byte[]
     * @throws Exception
     */
    public static byte[] Decryptbyte(byte[] sSrc, String sKey) throws Exception
    {
        try
        {
            // 判断Key是否正确
            if (sKey == null)
            {
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16)
            {
                return null;
            }
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding"); // 创建密码器
            // SecureRandom sr = new SecureRandom();
            // Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            try
            {
                byte[] original = cipher.doFinal(sSrc);

                return original;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}

