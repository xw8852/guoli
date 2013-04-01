package com.guoli.hotel.utils;

import java.io.File;

import com.guoli.hotel.GuoliApplication;

public class ImageUtil {
    /**
     * 获取小图图片地址
     * @param picPath
     * @param picName
     * @return
     */
    public static final String getThumbnailImageUrl(String picPath,String picName){
        return GuoliApplication.PIC_PATH_PRE+File.separator+picPath+"ico/"+picName;
    }
    /**
     * 获取大图图片地址
     * @param picPath
     * @param picName
     * @return
     */
    public static final String getImageUrl(String picPath,String picName){
        return GuoliApplication.PIC_PATH_PRE+File.separator+picPath+"large/"+picName;
    }
}
