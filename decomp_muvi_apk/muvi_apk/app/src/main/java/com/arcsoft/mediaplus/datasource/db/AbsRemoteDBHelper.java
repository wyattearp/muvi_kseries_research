// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class AbsRemoteDBHelper extends SQLiteOpenHelper
{

    private SQLiteDatabase mDataBase;

    public AbsRemoteDBHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
    {
        super(context, s, cursorfactory, i);
        mDataBase = null;
    }

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

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
    }
}
