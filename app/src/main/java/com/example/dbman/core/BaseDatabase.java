package com.example.dbman.core;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.example.dbman.db.core.ConnectionSources;
import com.example.dbman.db.core.Database;
import com.example.dbman.db.genupdate.daoimpl.CheckMntcDetailDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.CheckMntcInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.CpntTypesDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.DepartmentDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.DepotInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.DeptExentDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ECFactoryDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.EqmtCpntInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.EqmtInOutDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.EquipLogDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.EquipTypeDetailDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ExentDataDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ExtendTypeDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.FactoryInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.FaultInforDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.PersonInOutDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.PersonInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.RoleKeyDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.RoleMenuDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.RoomAdminUserDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ScanStoreDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ScanStoreDetailDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.ScanStoreEquipTypeDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.StoreDetailDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.StoreExtendChangeDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysFileInfoDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysKeyDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysLogDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysMenuDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysParameterDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysRoleDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysUserDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.SysdiagramsDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.TmpHmdDataDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.UserCardDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.UserDataPermissionDaoImpl;
import com.example.dbman.db.genupdate.daoimpl.WorkersDaoImpl;
import com.example.dbman.db.genupdate.schema.CheckMntcDetail;
import com.example.dbman.db.genupdate.schema.CheckMntcInfo;
import com.example.dbman.db.genupdate.schema.CpntTypes;
import com.example.dbman.db.genupdate.schema.Department;
import com.example.dbman.db.genupdate.schema.DepotInfo;
import com.example.dbman.db.genupdate.schema.DeptExent;
import com.example.dbman.db.genupdate.schema.ECFactory;
import com.example.dbman.db.genupdate.schema.EqmtCpntInfo;
import com.example.dbman.db.genupdate.schema.EqmtInOut;
import com.example.dbman.db.genupdate.schema.EquipLog;
import com.example.dbman.db.genupdate.schema.EquipType;
import com.example.dbman.db.genupdate.schema.EquipTypeDetail;
import com.example.dbman.db.genupdate.schema.ExentData;
import com.example.dbman.db.genupdate.schema.ExtendType;
import com.example.dbman.db.genupdate.schema.FactoryInfo;
import com.example.dbman.db.genupdate.schema.FaultInfor;
import com.example.dbman.db.genupdate.schema.PersonInOut;
import com.example.dbman.db.genupdate.schema.PersonInfo;
import com.example.dbman.db.genupdate.schema.RoleKey;
import com.example.dbman.db.genupdate.schema.RoleMenu;
import com.example.dbman.db.genupdate.schema.RoomAdminUser;
import com.example.dbman.db.genupdate.schema.ScanStore;
import com.example.dbman.db.genupdate.schema.ScanStoreDetail;
import com.example.dbman.db.genupdate.schema.ScanStoreEquipType;
import com.example.dbman.db.genupdate.schema.StoreDetail;
import com.example.dbman.db.genupdate.schema.StoreExtendChange;
import com.example.dbman.db.genupdate.schema.SysFileInfo;
import com.example.dbman.db.genupdate.schema.SysKey;
import com.example.dbman.db.genupdate.schema.SysLog;
import com.example.dbman.db.genupdate.schema.SysMenu;
import com.example.dbman.db.genupdate.schema.SysParameter;
import com.example.dbman.db.genupdate.schema.SysRole;
import com.example.dbman.db.genupdate.schema.SysUser;
import com.example.dbman.db.genupdate.schema.Sysdiagrams;
import com.example.dbman.db.genupdate.schema.TmpHmdData;
import com.example.dbman.db.genupdate.schema.UserCard;
import com.example.dbman.db.genupdate.schema.UserDataPermission;
import com.example.dbman.db.genupdate.schema.Workers;
import com.example.dbman.ui.R;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 * Created by ChenJi on 2016/10/26.
 */

public class BaseDatabase {
    private static final String DB_NAME="dbman.db";
    private static BaseDatabase baseDatabase = new BaseDatabase();
    private static String DATABASE_PATH;
    private static String DATABASE_URL;
    private static String DATABASE_DIR;
    private ConnectionSource connectionSource;
//    public class ClassDesc{
//        private String tableName;
//        private Class schemeClass;
//        private Class interfaceClass;
//        private Class daoClass;
//
//
//        public String getTableName() {
//            return tableName;
//        }
//
//        public void setTableName(String tableName) {
//            this.tableName = tableName;
//        }
//
//        public Class getSchemeClass() {
//            return schemeClass;
//        }
//
//        public void setSchemeClass(Class schemeClass) {
//            this.schemeClass = schemeClass;
//        }
//
//        public Class getInterfaceClass() {
//            return interfaceClass;
//        }
//
//        public void setInterfaceClass(Class interfaceClass) {
//            this.interfaceClass = interfaceClass;
//        }
//
//        public Class getDaoClass() {
//            return daoClass;
//        }
//
//        public void setDaoClass(Class daoClass) {
//            this.daoClass = daoClass;
//        }
//    }
    public enum TableNames {
    CheckMntcDetail,
    CheckMntcInfo,
    CpntTypes,
    Department,
    DepotInfo,
    DeptExent,
    ECFactory,
    EqmtCpntInfo,
    EqmtInOut,
    EquipLog,
    EquipType,
    EquipTypeDetail,
    ExentData,
    ExtendType,
    FactoryInfo,
    FaultInfor,
    PersonInfo,
    PersonInOut,
    RoleKey,
    RoleMenu,
    RoomAdminUser,
    ScanStore,
    ScanStoreDetail,
    ScanStoreEquipType,
    StoreDetail,
    StoreExtendChange,
    Sysdiagrams,
    SysFileInfo,
    SysKey,
    SysLog,
    SysMenu,
    SysParameter,
    SysRole,
    SysUser,
    TmpHmdData,
    UserCard,
    UserDataPermission,
    Workers
    };

    private static final Class schemaClasses [] ={
            CheckMntcDetail.class,
            CheckMntcInfo.class,
            CpntTypes.class,
            Department.class,
            DepotInfo.class,
            DeptExent.class,
            ECFactory.class,
            EqmtCpntInfo.class,
            EqmtInOut.class,
            EquipLog.class,
            EquipType.class,
            EquipTypeDetail.class,
            ExentData.class,
            ExtendType.class,
            FactoryInfo.class,
            FaultInfor.class,
            PersonInfo.class,
            PersonInOut.class,
            RoleKey.class,
            RoleMenu.class,
            RoomAdminUser.class,
            ScanStore.class,
            ScanStoreDetail.class,
            ScanStoreEquipType.class,
            StoreDetail.class,
            StoreExtendChange.class,
            Sysdiagrams.class,
            SysFileInfo.class,
            SysKey.class,
            SysLog.class,
            SysMenu.class,
            SysParameter.class,
            SysRole.class,
            SysUser.class,
            TmpHmdData.class,
            UserCard.class,
            UserDataPermission.class,
            Workers.class
    };

    private static final Class [] daoImplClasses = {
            CheckMntcDetailDaoImpl.class,
            CheckMntcInfoDaoImpl.class,
            CpntTypesDaoImpl.class,
            DepartmentDaoImpl.class,
            DepotInfoDaoImpl.class,
            DeptExentDaoImpl.class,
            ECFactoryDaoImpl.class,
            EqmtCpntInfoDaoImpl.class,
            EqmtInOutDaoImpl.class,
            EquipLogDaoImpl.class,
            EquipTypeDaoImpl.class,
            EquipTypeDetailDaoImpl.class,
            ExentDataDaoImpl.class,
            ExtendTypeDaoImpl.class,
            FactoryInfoDaoImpl.class,
            FaultInforDaoImpl.class,
            PersonInfoDaoImpl.class,
            PersonInOutDaoImpl.class,
            RoleKeyDaoImpl.class,
            RoleMenuDaoImpl.class,
            RoomAdminUserDaoImpl.class,
            ScanStoreDaoImpl.class,
            ScanStoreDetailDaoImpl.class,
            ScanStoreEquipTypeDaoImpl.class,
            StoreDetailDaoImpl.class,
            StoreExtendChangeDaoImpl.class,
            SysdiagramsDaoImpl.class,
            SysFileInfoDaoImpl.class,
            SysKeyDaoImpl.class,
            SysLogDaoImpl.class,
            SysMenuDaoImpl.class,
            SysParameterDaoImpl.class,
            SysRoleDaoImpl.class,
            SysUserDaoImpl.class,
            TmpHmdDataDaoImpl.class,
            UserCardDaoImpl.class,
            UserDataPermissionDaoImpl.class,
            WorkersDaoImpl.class
    };

    private HashMap<String,BaseDaoImpl> daoHashMap = new HashMap<String, BaseDaoImpl>();

    private void initDao(ConnectionSource connectionSources){
        for (Class clszz: daoImplClasses){
            try {
                Constructor constructor = clszz.getConstructor(ConnectionSource.class);
                {
                    String name = clszz.getSimpleName();
                    int inx = name.indexOf("DaoImpl");
                    if (inx >= 0){
                        name = name.substring(0,inx);
                    }
                    BaseDaoImpl impl = (BaseDaoImpl) constructor.newInstance(connectionSources);
                    daoHashMap.put(name, impl);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static BaseDatabase getInstance() {
        return baseDatabase;
    }

    private SQLiteDatabase db;

    private void createDBIfNotExist(){
             File dir = new File(DATABASE_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }//如果该目录不存在，创建该目录

            File filepath = new File(DATABASE_PATH);
            if (!filepath.exists()) {//如果文件不存在
                try {
                    InputStream inputStream = BaseApplication.getApp().getResources().openRawResource(R.raw.dbman);//将raw中的test.db放入输入流中
                    FileOutputStream fileOutputStream = new FileOutputStream(DATABASE_PATH);//将新的文件放入输出流中
                    byte[] buff = new byte[8192];
                    int len = 0;
                    while ((len = inputStream.read(buff)) > 0) {
                        fileOutputStream.write(buff, 0, len);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                } catch (Exception e) {
                    Log.i("info","无法复制");
                    e.printStackTrace();
                }
            }//写入文件结束
            Log.i("filepath"," "+filepath);
    }

    public boolean init(Application app){
        try {
            DATABASE_DIR = "/data/data/" + app.getPackageName();
            DATABASE_PATH = DATABASE_DIR + "/" + DB_NAME;
            DATABASE_URL = "jdbc:sqlite:/"+ DATABASE_PATH;
            try {
                DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
                createDBIfNotExist();
                connectionSource = new JdbcConnectionSource(DATABASE_URL);
                initDao(connectionSource);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            LogUtils.e("ui database error",e);
            return false;
        }
        return true;
    }




}
