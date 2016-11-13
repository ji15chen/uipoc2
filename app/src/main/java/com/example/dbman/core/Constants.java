package com.example.dbman.core;

import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */

public class Constants {
    public static UUID NULL_UUID = new UUID(0,0);
    public static boolean debug = true;

    private static BaseFunctionDesc functionDescs[] = null;

    static {
        functionDescs = new BaseFunctionDesc[6];

    }
}
