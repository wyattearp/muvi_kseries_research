// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            AbsRemoteDBHelper

public class RemoteDataDBHelper extends AbsRemoteDBHelper
{

    private static final String DATABASE_PREFIX = "server-";
    private static final int DATABASE_VERSION = 4;
    private String mServerUDN;

    public RemoteDataDBHelper(Context context, String s)
    {
        super(context, (new StringBuilder()).append("server-").append(s).append(".db").toString(), null, 4);
        mServerUDN = null;
        mServerUDN = s;
    }

    public RemoteDataDBHelper(Context context, String s, String s1)
    {
        super(context, (new StringBuilder()).append(s1).append(".db").toString(), null, 4);
        mServerUDN = null;
        mServerUDN = s;
    }

    public final String getDatabaseName()
    {
        return (new StringBuilder()).append("server-").append(mServerUDN).toString();
    }

    public String getServerUDN()
    {
        return mServerUDN;
    }

    public void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS MediaTable (_ID INTEGER PRIMARY KEY AUTOINCREMENT ,PARENT_ID TEXT ,ITEM_UUID TEXT ,UPNP_CLASS INTEGER DEFAULT 0 ,TITLE TEXT ,DATE TEXT ,ARTIST TEXT ,ALBUM TEXT ,GENRE TEXT ,CREATOR TEXT ,PROPERTY INTEGER DEFAULT 0 ,ALBUM_URL TEXT ,CHANNEL_NAME TEXT ,GROUP_ID TEXT ,GROUP_TOPFLAG TEXT ,GROUP_TITLE TEXT ,GROUP_MEMBERNUMBER TEXT );");
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS ResourceTable (_ID INTEGER PRIMARY KEY AUTOINCREMENT ,MEDIA_ID INTEGER ,SIZE INTEGER DEFAULT 0 ,PROTOCOL_INFO TEXT ,DURATION INTEGER DEFAULT 0 ,RESOLUTION TEXT ,PROTECTION TEXT ,BITRATE INTEGER DEFAULT -1 ,BITSPERSAMPLE INTEGER DEFAULT -1 ,SAMPLFREQUENCY INTEGER DEFAULT -1 ,NOAUDIOCHANNEL INTEGER DEFAULT -1 ,COLORDEPTH INTEGER DEFAULT -1 ,RES_URL TEXT ,LOCAL_FLAG INTEAGER DEFAULT 0 ,CHAPTERLIST_URL TEXT ,PXNCOPYCOUNT INTEGER DEFAULT 0 ,VGABITRATE INTEGER DEFAULT -1 ,RES_VGAURL TEXT ,VGAPROTOCOL_INFO TEXT ,RESUME_POINT INTEGER DEFAULT -1 ,CLEARTEXT_SIZE INTEGER DEFAULT 0 ,VGACLEARTEXT_SIZE INTEGER DEFAULT 0 );");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS AudioDBView AS SELECT MediaTable._ID,TITLE AS TITLE,DATE AS DATE,ARTIST AS ARTIST,ALBUM AS ALBUM,GENRE AS GENRE,ALBUM_URL AS ALBUM_URL,RES_URL AS URL,DURATION AS DURATION,SIZE AS SIZE,PROPERTY AS PROPERTY FROM MediaTable,ResourceTable WHERE MediaTable._ID = MEDIA_ID AND LOCAL_FLAG =1  AND UPNP_CLASS = 2;");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS ImageDBView AS SELECT MediaTable._ID,TITLE AS TITLE,DATE AS DATE,RES_URL AS URL,ALBUM_URL AS THUMBNAIL_URL,SIZE AS SIZE,PROPERTY AS PROPERTY,GROUP_ID AS GROUP_ID,RES_VGAURL AS VGAURL FROM MediaTable,ResourceTable WHERE MediaTable._ID = MEDIA_ID AND LOCAL_FLAG =1  AND UPNP_CLASS = 3;");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS VideoDBView AS SELECT MediaTable._ID,TITLE AS TITLE,DATE AS DATE,RES_URL AS URL,ALBUM_URL AS ALBUM_URL,DURATION AS DURATION,SIZE AS SIZE,PROPERTY AS PROPERTY,RES_VGAURL AS VGAURL,RESUME_POINT AS RESUME_POINT,PXNCOPYCOUNT AS COPY_COUNT,CHANNEL_NAME AS CHANNEL_NAME,GROUP_ID AS GROUP_ID FROM MediaTable,ResourceTable WHERE MediaTable._ID = MEDIA_ID AND LOCAL_FLAG =1  AND UPNP_CLASS = 1;");
        sqlitedatabase.execSQL("CREATE VIEW IF NOT EXISTS FolderDBView AS SELECT _ID,TITLE AS TITLE,GROUP_ID AS GROUP_ID FROM MediaTable WHERE PROPERTY = 2 order by TITLE asc;");
    }

    public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS MediaTable");
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS ResourceTable");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS AudioDBView");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS ImageDBView");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS VideoDBView");
        sqlitedatabase.execSQL("DROP VIEW IF EXISTS FolderDBView");
        onCreate(sqlitedatabase);
    }
}
