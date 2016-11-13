package com.example.dbman.db.genupdate.dao;

import com.j256.ormlite.dao.Dao;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.j256.ormlite.field.types.UuidType;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.UUID;

public interface SysFileInfoDao extends Dao<SysFileInfo,java.util.UUID>{
    public static enum SysFileType{
        FILE_TYPE_ANIMATION,
        FILE_TYPE_IMAGE,
        FILE_TYPE_OTHER,
    }
    public Iterator<SysFileInfo> findSysFile(final UUID UuidType) throws SQLException;
    public SysFileType getFileType(SysFileInfo sfi);
    public InputStream openFileStream(SysFileInfo sfi) throws IOException;
    public InputStream openFileStream(SysFileInfo sfi, boolean bIsThumb) throws IOException;
}
