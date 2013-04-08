/**
 * Project Name:Guoli
 * File Name:HelpInfo.java
 * Package Name:com.guoli.hotel.bean
 * Date:2013-4-8下午3:40:20
 * Copyright (c) 2013
 * Company:苏州海客科技有限公司
 *
*/

package com.guoli.hotel.bean;

import com.google.gson.annotations.SerializedName;

/**
 * ClassName:HelpInfo <br/>
 * @Description:
 * Date:     2013-4-8 下午3:40:20 <br/>
 * @author   maple
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HelpInfo {
    
    /**问题*/
    @SerializedName("quest")
    private String quest;
    
    /**答案*/
    @SerializedName("answer")
    private String answer;

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

