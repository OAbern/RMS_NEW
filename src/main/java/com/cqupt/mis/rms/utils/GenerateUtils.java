package com.cqupt.mis.rms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 常用工具类
 * 产生系统唯一的ID
 * @author Bern
 *
 */
public class GenerateUtils {
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong = "yyyyMMddHHmmss";
    
    /**
    *返回系统自动产生的ID
    *@return String
    */
    public static String getID(){
    	Random rad=new Random();
    	Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date) + rad.nextInt(1000);
    }
    
    /**
     *返回系统自动为新增用户产生的ID
     *@return String
     */
    public static String generateUserID(){
    	//暂时用123代替
    	String userID = "t" + new Random().nextInt(100000);
    	return userID;
    }
    
    /**
     *生成旁证材料的文件名
     *@return String
     */
    public static String generateFileName(String fileName) {   
        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");   
        String formatDate = format.format(new Date());   
           
        int random = new Random().nextInt(10000);   
           
        int position = fileName.lastIndexOf(".");   
        String extension = fileName.substring(position);   
           
        return "p"+formatDate + random + extension;   
    }
    
    /**
     *生成保存旁证材料的文件夹路径
     *@return String
     */
    public static String generateSavePath(){
    	String savePath = "upload";
    	return savePath;
    }
    
    /**
     *生成录入科研信息时的状态
     *@return String
     */
    public static int getInputStatus(){
    	//1代表该信息已录入，但未被审批
    	int status = 1;
    	return status;
    }
    
}
