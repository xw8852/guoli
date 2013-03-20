/**
 * Project Name:Guoli
 * File Name:UserInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-3-20下午3:23:06
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:UserInfo <br/>
 * @Description:    用户信息
 * Date:     2013-3-20 下午3:23:06 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UserInfo {
    
    /**姓名*/
//    @SerializedName("")
//    private String name;
    
    /**昵称*/
    @SerializedName("nickname")
    private String nickname;
    
    /**性别*/
    @SerializedName("sex")
    private String gender;
    
    /**生日*/
    @SerializedName("birthday")
    private String birthday;
    
    /**地址*/
    @SerializedName("address")
    private String address;
    
    /**邮编*/
    @SerializedName("postcode")
    private String postcode;
    
    /**手机*/
//    @SerializedName("")
//    private String phone;
    
    /**登录用户UUID*/
    @SerializedName("uid")
    private String uid;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    
    @Override
    public String toString() {
        return "{name=" + ", nickname=" + nickname + ", uid=" + uid
                + ", gender=" + gender + ", birthday=" + birthday + ", address=" + address
                + ", postcode=" + postcode + ", phone="  + "}";
    }
}

