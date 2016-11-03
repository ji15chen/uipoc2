package com.example.dbman.db.core;

import java.io.Serializable;

/**
 * Created by jerry on 2016/10/30.
 */
public class jdbcConfig implements Serializable {
    private String jdbcUrl;
    private String user;
    private String password;

    public jdbcConfig() {
        user="na";
        password ="na";
        jdbcUrl = "jdbc:sqlite://e:/tim.db\\";
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
