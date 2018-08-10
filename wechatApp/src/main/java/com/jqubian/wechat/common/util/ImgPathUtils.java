package com.jqubian.wechat.common.util;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/8/10
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
public class ImgPathUtils {

    private static String separator = System.getProperty("file.separator"); // 文件类型

    public static String getUploadImgBasePath(){
        String basePath = "";
        String os=System.getProperty("os.name");    // 获取系统 linux windows
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/WX/images/";
        } else {
            basePath = "/home/WX/images/";
        }
        basePath = basePath.replace("/",separator);
        return basePath;
    }
}