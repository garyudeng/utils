package org.gary.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.gary.utils.safe.CipherUtil;


/**
 * 配置文件config.properties所有配置
 * 
 * @author gary
 */
public class PropertiesContent {
	public static Map<String, String> config = new HashMap<String, String>();
	public static Properties properties=new Properties();
	static {
		ResourceBundle rb = ResourceBundle.getBundle("config");
		Enumeration<String> cfgs = rb.getKeys();
		while (cfgs.hasMoreElements()) {
			String key = cfgs.nextElement();
			String val=rb.getString(key);
			if (key.contains("mail.password")==true) {
				try {
					config.put(key, CipherUtil.decryptData(val));
				} catch (Exception e) {
					System.out.println("对参数解密异常" + e);
				}
			}else{
				config.put(key,val);
			}
		}
		 properties.putAll(config);
	}
}
