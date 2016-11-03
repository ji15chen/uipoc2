package com.example.dbman.db.core;

import java.io.Serializable;

/**
 * Created by jerry on 2016/10/30.
 */
public class ConnectionConfig implements Serializable {
    private jdbcConfig local = new jdbcConfig();
    private jdbcConfig remote = new jdbcConfig();

    public jdbcConfig getLocal() {
        return local;
    }

    public void setLocal(jdbcConfig local) {
        this.local = local;
    }

    public jdbcConfig getRemote() {
        return remote;
    }

    public void setRemote(jdbcConfig remote) {
        this.remote = remote;
    }
}
