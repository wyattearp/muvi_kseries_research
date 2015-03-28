// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            AbsRemoteDBHelper

public class SalixHttpDBHelper extends AbsRemoteDBHelper
{
    public class SalixHttpTable
    {

        public static final String TABLE_NAME = "Media_table";
        final SalixHttpDBHelper this$0;

        public SalixHttpTable()
        {
            this$0 = SalixHttpDBHelper.this;
            super();
        }
    }

    public class SalixHttpTable.Columns
    {

        public static final String ISVIDEO = "is_video";
        public static final String NAME = "name";
        public static final String SIZE = "size";
        public static final String TIME_CODE = "time_code";
        public static final String URL = "url";
        public static final String _ID = "_ID";
        final SalixHttpTable this$1;

        public SalixHttpTable.Columns()
        {
            this$1 = SalixHttpTable.this;
            super();
        }
    }


    private static final int DATABASE_VERSION = 4;
    private static final String DB_NAME = "Salix-http.db";

    public SalixHttpDBHelper(Context context)
    {
        super(context, "Salix-http.db", null, 4);
    }

    public final String getDatabaseName()
    {
        return "Salix-http.db";
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS Media_table (_ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT ,url TEXT ,time_code INTEGER ,size LONG DEFAULT 0 ,is_video INTEGER );");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS Media_table");
        onCreate(sqlitedatabase);
    }
}
