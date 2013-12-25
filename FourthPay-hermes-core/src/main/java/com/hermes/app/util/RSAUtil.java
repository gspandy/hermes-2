package com.hermes.app.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

	private static BigInteger private_d = new BigInteger(
			"3206586642942415709865087389521403230384599658161226562177807849299468150139");
	private static BigInteger n = new BigInteger(
			"7318321375709168120463791861978437703461807315898125152257493378072925281977");

	/**
	 * �õ���Կ
	 * 
	 * @param key
	 * @throws Exception
	 */
	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	/**
	 * �õ�˽Կ
	 * 
	 * @param key
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes;
		keyBytes = (new BASE64Decoder()).decodeBuffer(key);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		return privateKey;
	}

	/**
	 * 
	 * @return
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String s = (new BASE64Encoder()).encode(keyBytes);
		return s;
	}

	/***************************
	 * 
	 * @Description: ���� 
	 * @author yujialin
	 * @date Feb 11, 2011 5:03:27 PM 
	 *
	 * @param str
	 * @return
	 */
	public static String getDecryptString(String str) {

		byte ptext[] = HexUtil.toByteArray(str);
		BigInteger encry_c = new BigInteger(ptext);

		BigInteger private_m = encry_c.modPow(private_d, n);
		byte[] mt = private_m.toByteArray();
		StringBuffer buffer = new StringBuffer();
		for (int i = mt.length - 1; i > -1; i--) {
			buffer.append((char) mt[i]);
		}
		return buffer.substring(0, buffer.length()-10).toString();
	}
}
