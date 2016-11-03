package com.example.dbman.ui.PowerIndicator;

import java.io.Serializable;

/**
 * Created by ChenJi on 2016/10/25.
 */

public class PowerIndicatorModel implements Serializable{
    public String textValue;

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public PowerIndicatorModel(){

        textValue ="test";
    }
}
