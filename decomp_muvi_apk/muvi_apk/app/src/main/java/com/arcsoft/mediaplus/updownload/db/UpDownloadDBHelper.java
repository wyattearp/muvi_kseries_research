// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class UpDownloadDBHelper extends SQLiteOpenHelper
{

    static final String DATABASE_NAME = "updownload.db";
    static final int DATABASE_VERSION = 1;
    public static final int TYPE_DOWNLOAD = 0;
    public static final int TYPE_UPLOAD = 1;
    private SQLiteDatabase mDataBase;

    public UpDownloadDBHelper(Context context)
    {
        super(context, "updownload.db", null, 1);
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

    public SQLiteDatabase getManagerDatabase()
    {
        if (mDataBase == null)
        {
            mDataBase = getWritableDatabase();
        }
        return mDataBase;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS UpDownloadTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, volume_id TEXT, type INTEAGER DEFAULT 0, dms_uuid TEXT, item_uuid TEXT, UPNP_CLASS INTEGER DEFAULT 0 ,MEDIA_ID INTEGER, file_size TEXT, current_size TEXT, date_added INTEGER DEFAULT 0, state INTEGER DEFAULT 0, file_path TEXT, title TEXT, uri TEXT, protocol_info TEXT ,mediaType INTEAGER DEFAULT 0, upload_id INTEGER DEFAULT 0 );");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS DownloadView AS SELECT UpDownloadTable._id,uri AS uri,file_path AS file_path,title AS title,file_size AS file_size,current_size AS current_size,date_added AS date_added,state AS state,dms_uuid AS dms_uuid,item_uuid AS item_uuid,UPNP_CLASS AS UPNP_CLASS,mediaType AS mediaType,MEDIA_ID AS MEDIA_ID FROM UpDownloadTable WHERE type = 0;");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS UploadView AS SELECT UpDownloadTable._id,upload_id AS upload_id,uri AS uri,title AS title,file_size AS file_size,current_size AS current_size,date_added AS date_added,state AS state,dms_uuid AS dms_uuid,UPNP_CLASS AS UPNP_CLASS,MEDIA_ID AS MEDIA_ID,protocol_info AS protocol_info FROM UpDownloadTable WHERE type = 1;");
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS EasyTransferTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, dms_name TEXT, dms_uuid TEXT, server_state INTEGER DEFAULT 1 ,start_hour INTEGER, start_minute INTEGER, record_day INTEGER DEFAULT 0, retry_count INTEGER DEFAULT 5, allow_move INTEGER DEFAULT 0, enable_plugin INTEGER DEFAULT 0 );");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS UpDownloadTable");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS DownloadView");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS UploadView");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS EasyTransferTable");
        onCreate(sqlitedatabase);
    }
}
