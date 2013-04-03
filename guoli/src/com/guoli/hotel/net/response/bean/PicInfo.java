/**
 * Project Name:Guoli
 * File Name:PicInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-4-2下午5:09:27
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.net.response.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:PicInfo <br/>
 * @Description:    酒店图片对象
 * Date:     2013-4-2 下午5:09:27 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PicInfo implements Parcelable {

    /**酒店图片名称*/
    @SerializedName("picname")
    private String name;
    
    /**酒店图片文件名*/
    @SerializedName("filename")
    private String picName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(picName);
    }
    
    public static final Parcelable.Creator<PicInfo> CREATOR = new Parcelable.Creator<PicInfo>() {
        public PicInfo createFromParcel(Parcel in) {
            PicInfo info = new PicInfo();
            info.name = in.readString();
            info.picName = in.readString();
            return info;
        }

        public PicInfo[] newArray(int size) {
            return new PicInfo[size];
        }
    };
    
}

