package com.example.dbman.ui.StoreDetail;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class StoreDetailModel implements Serializable{
    public String textValue;

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }


    public StoreDetailModel(){

        textValue ="test";
    }
}
