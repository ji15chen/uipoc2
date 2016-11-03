package db.genupdate.daoimpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.BaseDaoImpl;
import java.sql.SQLException;
import com.j256.ormlite.support.ConnectionSource;
import db.genupdate.dao.M_PointDao;
import db.genupdate.schema.M_Point;
public class M_PointDaoImpl extends BaseDaoImpl<M_Point,java.util.UUID>{
public M_PointDaoImpl (ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, M_Point.class );
		}
}
