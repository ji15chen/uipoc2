package com.example.dbman.core;

/**
 * Created by jerry on 2016/11/4.
 */

public class BaseDaoClass {
        private String tableName;
        private Class schemeClass;
        private Class interfaceClass;
        private Class daoClass;


        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public Class getSchemeClass() {
            return schemeClass;
        }

        public void setSchemeClass(Class schemeClass) {
            this.schemeClass = schemeClass;
        }

        public Class getInterfaceClass() {
            return interfaceClass;
        }

        public void setInterfaceClass(Class interfaceClass) {
            this.interfaceClass = interfaceClass;
        }

        public Class getDaoClass() {
            return daoClass;
        }

        public void setDaoClass(Class daoClass) {
            this.daoClass = daoClass;
        }
}
