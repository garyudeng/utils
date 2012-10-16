package org.gary.utils.safe;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/** MD5加密 */
public class KeyedDigestMD5 {
	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(buffer);
			return md5.digest(key);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return null;
	}
    /**
     * 加密
     * @param strSrc需要加密的字符串
     * @param key 密钥
     * @return
     */
	public static String getKeyedDigest(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));
			
			StringBuffer result = new StringBuffer();
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			for (int i = 0; i < temp.length; i++) {
				result.append(Integer.toHexString(
						(0x000000ff & temp[i]) | 0xffffff00).substring(6));
			}
			return result.toString();

		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static void main(String[] args) {
		String mi;
		String s = "loyin";
		// 第二个参数请填空字符串
		mi = KeyedDigestMD5.getKeyedDigest(s, "loyin");
		System.out.println("mi:bbf035214a2c4d7c598c618d239831d2\n" + mi);
	}
}
