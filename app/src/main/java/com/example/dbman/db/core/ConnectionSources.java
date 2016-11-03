package com.example.dbman.db.core;

import com.alibaba.fastjson.JSON;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by jerry on 2016/10/30.
 */
public class ConnectionSources {
    private static ConnectionSources connectionSources = null;
    public static  ConnectionSources getInstance(){
        if (connectionSources == null){
            connectionSources = new ConnectionSources();
        }
        return connectionSources;
    }

    protected void finalize(){
        try {
            if (localConn != null) {
                localConn.close();
            }
            if (remoteConn != null) {
                remoteConn.close();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private ConnectionConfig connectionConfig;
    private JdbcPooledConnectionSource localConn = null;
    private JdbcPooledConnectionSource remoteConn = null;

    public ConnectionSources(){
        try {
            connectionConfig = JSON.parseObject(new FileInputStream("db.json"), ConnectionConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            connectionConfig = new ConnectionConfig();
        }

        try {
            localConn = new JdbcPooledConnectionSource(connectionConfig.getLocal().getJdbcUrl());
            localConn.setMaxConnectionAgeMillis(5 * 60 * 1000);
            remoteConn = new JdbcPooledConnectionSource(connectionConfig.getLocal().getJdbcUrl());
            remoteConn.setMaxConnectionAgeMillis(5 * 60 * 1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JdbcPooledConnectionSource getLocalConn() {
        return localConn;
    }

    public void setLocalConn(JdbcPooledConnectionSource localConn) {
        this.localConn = localConn;
    }

    public JdbcPooledConnectionSource getRemoteConn() {
        return remoteConn;
    }

    public void setRemoteConn(JdbcPooledConnectionSource remoteConn) {
        this.remoteConn = remoteConn;
    }
}
