package com.example.dbman.db.core;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.UuidType;

import java.util.UUID;

/**
 * Created by jerry on 2016/11/6.
 */

public class MyUUID extends UuidType {
    private static final MyUUID singleTon = new MyUUID();

    public static MyUUID getSingleton() {
        return singleTon;
    }
    private MyUUID() {
        super(SqlType.STRING, new Class[]{UUID.class});
    }

    protected MyUUID(SqlType sqlType) {
        super(sqlType);
    }

    protected MyUUID(SqlType sqlType, Class<?>[] classes) {
        super(sqlType, classes);
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object obj) {
        UUID uuid = (UUID)obj;
        return uuid.toString().toUpperCase();
    }
}
