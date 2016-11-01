package com.example.dbman.ui.PowerIndicator.bean;

/**
 * Created by likai on 11/18/16.
 */

public class T1EntryBean {
    public String title;
    public String body;

    public T1EntryBean(String title, String body){
        setTitle(title);
        setBody(body);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
