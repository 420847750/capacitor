package cn.fulong.tra.util;

import java.util.UUID;

/**
 * @author Administrator
 * 生成32位随机id
 * 
 */
public class GetUUID32 {
	public static String getUUID32(){
	    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	    return uuid;
 	}
}
