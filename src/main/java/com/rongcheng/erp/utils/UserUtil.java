package com.rongcheng.erp.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class UserUtil {
	/**
	 * 对密码进行md5加密
	 * @param msg 未加密的密码
	 * @return ret 加密后的面
	 */
	public static String md5(String msg){ 
    	String ret="";    	
    	try {
    		//先将字符串变成字节,因为MD5是针对字节加密的
			byte[] input = msg.getBytes();
    		//利用md5对msg处理
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(input);
			//为避免乱码,使用base64,将字节转换成字符串
			ret = Base64.encodeBase64String(output);
			//去除base64中的/+=特殊字符
			ret = Base64.encodeBase64String(ret.getBytes());//赵滨 修改(二次加密)
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ret.toLowerCase();	
    }
	/**
	 * 对list集合去除重复
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list){ 
		for(int i = 0;i < list.size() - 1;i++){ 
		    for(int j = list.size() -1;j>i;j--){ 
		      if(list.get(j).equals(list.get(i))){ 
		        list.remove(j); 
		      } 
		    } 
	    }  
		  return list;
	} 
	/**
	 * 对list集合去除重复
	 * @param list
	 * @return
	 */
	public static List<BigInteger> distinct(List<BigInteger> list){ 
		for(int i = 0;i < list.size() - 1;i++){ 
		    for(int j = list.size() -1;j>i;j--){ 
		      if(list.get(j).equals(list.get(i))){ 
		        list.remove(j); 
		      } 
		    } 
	    }  
		  return list;
	}	
}