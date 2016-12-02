package com.example.dbman.ui.StoreDetail.dbmodel;

import java.util.HashMap;

/**
 * Created by jerry on 2016/12/3.
 */

public interface IDataColumn {
    int size();
    String [] getDataRefIDs();
    String getColumnName(String dataRefID);
}
