/**
 * Project Name:Guoli
 * File Name:AreaInfo.java
 * Package Name:com.guoli.hotel.net.response.bean
 * Date:2013-3-6下午8:21:33
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:AreaInfo <br/>
 * @Description:    行政区域/商圈基类
 * Date:     2013-3-6 下午8:21:33 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AreaInfo implements Parcelable {

    
    @SerializedName("name")
    protected String name;

    @SerializedName("code")
    protected String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{name=" + name + ", code=" + code + "}";
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(code);
    }

    public static final Parcelable.Creator<AreaInfo> CREATOR = new Creator<AreaInfo>() {
        @Override
        public AreaInfo[] newArray(int size) {
            return new AreaInfo[size];
        }

        @Override
        public AreaInfo createFromParcel(Parcel source) {
            AreaInfo info = new AreaInfo();
            info.name = source.readString();
            info.code = source.readString();
            return info;
        }
    };
}

