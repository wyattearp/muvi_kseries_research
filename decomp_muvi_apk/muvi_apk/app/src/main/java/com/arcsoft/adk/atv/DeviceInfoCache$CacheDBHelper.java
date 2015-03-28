// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Referenced classes of package com.arcsoft.adk.atv:
//            DeviceInfoCache

private final class mDataBase extends SQLiteOpenHelper
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

    public ry(Context context)
    {
        this$0 = DeviceInfoCache.this;
        super(context, "device_meta.db", null, 1);
        mDataBase = null;
    }
}
