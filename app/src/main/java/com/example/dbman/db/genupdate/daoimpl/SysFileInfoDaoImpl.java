package com.example.dbman.db.genupdate.daoimpl;
import com.example.dbman.core.Utils;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.j256.ormlite.support.ConnectionSource;
import com.example.dbman.db.genupdate.dao.SysFileInfoDao;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
public class SysFileInfoDaoImpl extends BaseDaoImpl<SysFileInfo,java.util.UUID> implements SysFileInfoDao{
public SysFileInfoDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, SysFileInfo.class );
		}

	@Override
	public List<SysFileInfo> findSysFile(UUID uuid) throws SQLException{
		return queryForEq("ObjectID", uuid);
	}

	@Override
	public SysFileType getFileType(SysFileInfo sfi) {
		String ext = sfi.getExtension();
		String mimeType = Utils.getMIMEType(ext);

		if (mimeType.contains("image")){
			return SysFileType.FILE_TYPE_IMAGE;
		}

		if (mimeType.contains("video")){
			return SysFileType.FILE_TYPE_VIDEO;
		}
		if (mimeType.contains("audio")){
			return SysFileType.FILE_TYPE_AUDIO;
		}

		return SysFileType.FILE_TYPE_OTHER;
	}

	@Override
	public InputStream openFileStream(SysFileInfo sfi) throws IOException {
		return null;
	}
	@Override
	public InputStream openFileStream(SysFileInfo sfi, boolean bIsThumb) throws IOException{
		return null;
	}

}
