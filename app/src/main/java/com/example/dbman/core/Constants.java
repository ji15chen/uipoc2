package com.example.dbman.core;

import java.util.UUID;

/**
 * Created by jerry on 2016/11/4.
 */

public class Constants {
    public static final UUID NULL_UUID = new UUID(0,0);
    public static boolean debug = true;
    public static final UUID STORE_CHECKINOUT_REASON_UUID = UUID.fromString("00000000-0000-0000-0000-000000000013");
    public static final String DB_ARRAY_SPLITTER = "\\$#\\$#";

    private static BaseFunctionDesc functionDescs[] = null;
    public static boolean test_card = true;
    static {
        functionDescs = new BaseFunctionDesc[6];

    }
}
