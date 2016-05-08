package com.cqupt.mis.rms.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * 解析json格式的工具类
 * 
 * @author Bern
 * 
 */
public class JSONUtils {

	/**
	 * 将对象转化成json，并用response输出
	 * 这个方法只能在action方法里面调用一次!
	 * @param o 要进行转化的对象
	 * @param response HttpServletResponse
	 */
	public static void toJSON(Object o, HttpServletResponse response) {
		try {
			String str = JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);	//关掉循环引用检测
			response.addHeader("Content-Type", "application/json; charset=utf-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 将对象转成json字符串
	 * @param o 要进行转换的对象
	 * @return json字符串
	 */
	public static String toJSONString(Object o) {
		try {
			return JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect);	//关掉循环引用检测
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将json字符串转换为java对象
	 * <p>原生态，未添加任何的操作，只是简单的封装
	 * @param text 要转换的json文本
	 * @return 转换后的对象
	 */
	public static JSONObject parseObject(String text) {
		return JSON.parseObject(text);
	}

	/**
	 * 转换为int数组
	 * @param array @see JSONArray
	 * @return 如果类型转换异常，则返回null
	 */
	public static int[] toArray(JSONArray array) {
		if(array==null || array.size()==0)
			return null;

		int[] result = new int[array.size()];
		for(int i=0; i<array.size(); i++) {
			try {
				Integer num = (Integer) array.get(i);
				result[i] = num;
			}catch (ClassCastException e) {
				e.printStackTrace();
				return null;
			}
		}
		return  result;
	}

}
