package com.cqupt.mis.rms.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONArray;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

/**
 * 解析json格式的工具类
 * 
 * @author Bern
 * 
 */
public class JSONUtils {
	
	/**
	 * 从json格式转换成一个相应的类
	 * @param jsonStr json字符串
	 * @param cla 要转换的对象类型
	 * @return	相应的对象
	 */
	public static Object json2Obj(String jsonStr, Class<?> cla) {
		Object obj = null;
		try {
			JSONParser parser = new JSONParser(new StringReader(jsonStr));
			JSONValue jsonValue = parser.nextValue();
			if (jsonValue instanceof com.sdicons.json.model.JSONArray) {
				List<Object> list = new ArrayList<Object>();
				JSONArray jsonArray = (JSONArray) jsonValue;
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONValue jsonObj = jsonArray.get(i);
					Object javaObj = JSONMapper.toJava(jsonObj, cla);
					list.add(javaObj);
				}
				obj = list;
			} else if (jsonValue instanceof com.sdicons.json.model.JSONObject) {
				obj = JSONMapper.toJava(jsonValue, cla);
			} else {
				obj = jsonValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return obj;
		}
		return obj;
	}

	/**
	 * 将对象转化成json，并用response输出
	 * 这个方法只能在action方法里面调用一次!
	 * @param o 要进行转化的对象
	 * @param response HttpServletResponse
	 */
	public static void toJSON(Object o, HttpServletResponse response) {
		try {
			String str = JSONMapper.toJSON(o).render(false);
			response.addHeader("Content-Type", "application/json; charset=utf-8");
//			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(str);
		} catch (MapperException e) {
			e.printStackTrace();
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
			return JSONMapper.toJSON(o).render(true);
		} catch (MapperException e) {
			e.printStackTrace();
		}
		return null;
	}

}
