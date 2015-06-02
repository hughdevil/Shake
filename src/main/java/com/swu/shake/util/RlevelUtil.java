package com.swu.shake.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Hugh
 *
 */
public final class RlevelUtil {
	private static OrderedProperties prop;
	static {
		//此处用Object.class不行
		InputStream in = RlevelUtil.class.getResourceAsStream("/properties/rlevel.properties");
		prop = new OrderedProperties();
		try {
			if(null !=in){
				prop.load(in);
			}else{
				System.out.println("路径不正确");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过String 获得 rlevel.properties中的具体的等级值int
	 * 
	 * @param levelCode
	 * @return
	 */
	public static int getLevel(String levelCode) {
		return Integer.parseInt(prop.getProperty(levelCode).trim());
	}

	/**
	 * 通过具体的等级值int，获得该等级的描述
	 * 
	 * @param levelCode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getDesc(int levelCode)
			throws UnsupportedEncodingException {
		String value = prop.getProperty(levelCode + "");
		return new String(value.trim().getBytes("ISO-8859-1"), "UTF-8");
	}

	/**
	 * 通过某角色的等级来获得他以下的等级
	 * 
	 * @param unknow
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> getAbleLevel(int unknow)
			throws UnsupportedEncodingException {
		Map<String, String> levels = new TreeMap<String, String>();
		Enumeration<Object> en = prop.keys();
		for (int flag = 0; flag < unknow - 1; flag++) {
			if (en.hasMoreElements()) {
				levels.put((String) en.nextElement(), getDesc(flag + 1));
			}
		}
		return levels;
	}
}