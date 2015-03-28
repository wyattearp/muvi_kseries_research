// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

class DeviceInfoCache
{
    private final class CacheDBHelper extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME = "device_meta.db";
        private static final int DATABASE_VERSION = 1;
        private SQLiteDatabase mDataBase;
        final DeviceInfoCache this$0;

        public void close()
        {
            this;
            JVM INSTR monitorenter ;
            if (mDataBase != null)
            {
                mDataBase.close();
                mDataBase = null;
            }
            super.close();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public SQLiteDatabase getManagedDatabase()
        {
            this;
            JVM INSTR monitorenter ;
            SQLiteDatabase sqlitedatabase;
            if (mDataBase == null)
            {
                mDataBase = super.getWritableDatabase();
            }
            sqlitedatabase = mDataBase;
            this;
            JVM INSTR monitorexit ;
            return sqlitedatabase;
            Exception exception;
            exception;
            throw exception;
        }

        public final SQLiteDatabase getReadableDatabase()
        {
            this;
            JVM INSTR monitorenter ;
            throw new UnsupportedOperationException("Use getManagedDatabase() instead");
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final SQLiteDatabase getWritableDatabase()
        {
            this;
            JVM INSTR monitorenter ;
            throw new UnsupportedOperationException("Use getManagedDatabase() instead");
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onCreate(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS ServerTable (_id INTEGER PRIMARY KEY ,udn TEXT UNIQUE ,name TEXT ,icon_data TEXT ,icon_url TEXT);");
        }

        public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
        }

        public CacheDBHelper(Context context)
        {
            this$0 = DeviceInfoCache.this;
            super(context, "device_meta.db", null, 1);
            mDataBase = null;
        }
    }

    static class RenderCacheInfo
    {

        byte icondata[];
        String iconurl;
        String name;
        String udn;

        RenderCacheInfo()
        {
        }
    }

    static class ServerCacheInfo
    {

        byte icondata[];
        String iconurl;
        String name;
        String udn;

        ServerCacheInfo()
        {
        }
    }

    private final class ServerTable
    {

        static final String TABLE_NAME = "ServerTable";
        final DeviceInfoCache this$0;

        private ServerTable()
        {
            this$0 = DeviceInfoCache.this;
            super();
        }
    }

    final class ServerTable.Columns
        implements BaseColumns
    {

        public static final String ICONDATA = "icon_data";
        public static final String ICON_URL = "icon_url";
        public static final String NAME = "name";
        public static final String UDN = "udn";
        final ServerTable this$1;

        ServerTable.Columns()
        {
            this$1 = ServerTable.this;
            super();
        }
    }


    private CacheDBHelper mCacheDBHelper;

    DeviceInfoCache(Context context)
    {
        mCacheDBHelper = null;
        mCacheDBHelper = new CacheDBHelper(context);
    }

    protected void finalize()
        throws Throwable
    {
        release();
        super.finalize();
    }

    ServerCacheInfo loadServerCacheInfo(String s)
    {
        String as[];
        String as1[];
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        as = (new String[] {
            "name", "icon_url", "icon_data"
        });
        as1 = (new String[] {
            s
        });
        cursor = null;
        sqlitedatabase = mCacheDBHelper.getManagedDatabase();
        boolean flag;
        cursor = sqlitedatabase.query("ServerTable", as, "udn=?", as1, null, null, null);
        flag = cursor.moveToFirst();
        ServerCacheInfo servercacheinfo;
        servercacheinfo = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_132;
        }
        ServerCacheInfo servercacheinfo1 = new ServerCacheInfo();
        servercacheinfo1.name = cursor.getString(0);
        servercacheinfo1.iconurl = cursor.getString(1);
        servercacheinfo1.icondata = cursor.getBlob(2);
        servercacheinfo1.udn = s;
        servercacheinfo = servercacheinfo1;
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return servercacheinfo;
        Exception exception1;
        exception1;
_L5:
        servercacheinfo = null;
        if (cursor == null) goto _L2; else goto _L1
_L1:
        cursor.close();
        return null;
        Exception exception;
        exception;
_L4:
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
        exception;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception2;
        exception2;
          goto _L5
    }

    ServerCacheInfo queryRenderCacheInfo(String s)
    {
        return null;
    }

    void release()
    {
        if (mCacheDBHelper != null)
        {
            mCacheDBHelper.close();
            mCacheDBHelper = null;
        }
    }

    void saveRenderCacheInfo(ServerCacheInfo servercacheinfo)
    {
    }

    boolean saveServerCacheInfo(ServerCacheInfo servercacheinfo)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        ContentValues contentvalues;
        SQLiteDatabase sqlitedatabase;
        int i;
        contentvalues = new ContentValues();
        contentvalues.clear();
        contentvalues.put("udn", servercacheinfo.udn);
        contentvalues.put("name", servercacheinfo.name);
        contentvalues.put("icon_data", servercacheinfo.icondata);
        contentvalues.put("icon_url", servercacheinfo.iconurl);
        String as[] = new String[1];
        as[0] = servercacheinfo.udn;
        sqlitedatabase = mCacheDBHelper.getManagedDatabase();
        i = sqlitedatabase.update("ServerTable", contentvalues, "udn=?", as);
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        long l = sqlitedatabase.insert("ServerTable", null, contentvalues);
        if (l != -1L)
        {
            i = 1;
        }
        if (i == 0)
        {
            flag = false;
        }
        return flag;
        Exception exception;
        exception;
        throw exception;
    }
}
