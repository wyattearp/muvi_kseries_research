// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.EmptyCursor;
import java.io.File;

// Referenced classes of package com.arcsoft.mediaplus.updownload.db:
//            DownloadedFileTable

public class DownloadedFileDBMgr
{

    static final int DATABASE_VERSION = 1;
    private static final String TAG = "DownloadedFileDBMgr";
    private static DownloadedFileDBMgr sInstance = null;
    private Application mApp;
    private int mCount;
    boolean mIsDBReady;
    private String mName;
    private int mNewVersion;
    private SQLiteDatabase mSqlite;

    protected DownloadedFileDBMgr(Application application)
    {
        mApp = null;
        mIsDBReady = false;
        mCount = -1;
        mNewVersion = 0;
        mName = null;
        mApp = application;
    }

    private void createDB()
    {
        setDBReady(isSDCardAvailable());
        if (isReady()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        String s = OEMConfig.DOWNLOADED_DB_PATH;
        File file = new File(s);
        if (!file.exists())
        {
            file.mkdirs();
        }
        if (mSqlite != null)
        {
            mSqlite.close();
            mSqlite = null;
        }
        mName = getDBName(mApp);
        try
        {
            mSqlite = SQLiteDatabase.openOrCreateDatabase((new StringBuilder()).append(s).append(mName).toString(), null);
        }
        catch (SQLiteException sqliteexception)
        {
            Log.e("DownloadedFileDBMgr", (new StringBuilder()).append("Couldn't open ").append(getDBName(mApp)).append(" for writing (will try read-only):").toString(), sqliteexception);
            throw sqliteexception;
        }
        i = mSqlite.getVersion();
        mNewVersion = 1;
        if (i == mNewVersion) goto _L1; else goto _L3
_L3:
        if (mSqlite.isReadOnly())
        {
            throw new SQLiteException((new StringBuilder()).append("Can't upgrade read-only database from version ").append(mSqlite.getVersion()).append(" to ").append(mNewVersion).append(": ").append(mName).toString());
        }
        mSqlite.beginTransaction();
        if (i != 0) goto _L5; else goto _L4
_L4:
        onCreate(mSqlite);
_L6:
        mSqlite.setVersion(mNewVersion);
        mSqlite.setTransactionSuccessful();
        mSqlite.endTransaction();
        return;
_L5:
        if (i <= mNewVersion)
        {
            break MISSING_BLOCK_LABEL_313;
        }
        onDowngrade(mSqlite, i, mNewVersion);
          goto _L6
        Exception exception;
        exception;
        mSqlite.endTransaction();
        throw exception;
        onUpgrade(mSqlite, i, mNewVersion);
          goto _L6
    }

    public static String getDBName(Application application)
    {
        return "DownloadedFileTable.db";
    }

    private static void getTraceInfo()
    {
        int i = 8;
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        if (astacktraceelement != null)
        {
            if (astacktraceelement.length < i)
            {
                i = astacktraceelement.length;
            }
            int j = 0;
            while (j < i) 
            {
                Log.v("DownloadedFileDBMgr", (new StringBuilder()).append("[Class:").append(astacktraceelement[j].getClassName()).append(", Method:").append(astacktraceelement[j].getMethodName()).append(", Line:").append(astacktraceelement[j].getLineNumber()).append("]").toString());
                j++;
            }
        }
    }

    public static void initSingleton(Application application)
    {
        getTraceInfo();
        if (sInstance != null)
        {
            Log.w("DownloadedFileDBMgr", "Already initialized.");
            return;
        } else
        {
            sInstance = new DownloadedFileDBMgr(application);
            sInstance.init();
            return;
        }
    }

    public static DownloadedFileDBMgr instance()
    {
        getTraceInfo();
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    public static boolean isSDCardAvailable()
    {
        for (String s = Environment.getExternalStorageState(); !"mounted".equals(s) && !"mounted_ro".equals(s) || FileUtils.getAndroidDataAvailableSize() < 10240L;)
        {
            return false;
        }

        return true;
    }

    private boolean isValidFile(String s)
    {
        File file;
        if (s != null && s.length() > 0)
        {
            if ((file = new File(s)) != null && file.exists())
            {
                return true;
            }
        }
        return false;
    }

    private void onCreate(SQLiteDatabase sqlitedatabase)
    {
        sqlitedatabase.execSQL("CREATE TABLE IF NOT EXISTS DownloadedFileTable (_id INTEGER PRIMARY KEY AUTOINCREMENT ,dms_uuid TEXT ,item_uuid TEXT ,file_size LONG ,file_path TEXT ,file_title TEXT ,uri TEXT );");
    }

    private void onDowngrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        Log.e("MovDBMgr", (new StringBuilder()).append("onCreate db = ").append(sqlitedatabase).append(" oldVersion = ").append(i).append(" newVersion = ").append(j).toString());
        throw new SQLiteException((new StringBuilder()).append("Can't downgrade database from version ").append(i).append(" to ").append(j).toString());
    }

    private void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
    {
        sqlitedatabase.execSQL("DROP TABLE IF EXISTS DownloadedFileTable");
        onCreate(sqlitedatabase);
    }

    public static void uninitSingleton()
    {
        getTraceInfo();
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.uninit();
            sInstance = null;
            return;
        }
    }

    public int addDownloadedFileToTable(String s, String s1, String s2, String s3, String s4, long l)
    {
        SQLiteDatabase sqlitedatabase = mSqlite;
        if (sqlitedatabase == null)
        {
            return -1;
        } else
        {
            deleteItemFromTableByFilePath(s3);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("dms_uuid", s);
            contentvalues.put("item_uuid", s1);
            contentvalues.put("uri", s2);
            contentvalues.put("file_path", s3);
            contentvalues.put("file_title", s4);
            contentvalues.put("file_size", Long.valueOf(l));
            sqlitedatabase.insert("DownloadedFileTable", "_id", contentvalues);
            mCount = refreshCount();
            return getDownloadedFileId(-1 + mCount);
        }
    }

    public int deleteItemFromTableByFilePath(String s)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = mSqlite;
        if (sqlitedatabase == null || s == null)
        {
            return -1;
        }
        as = (new String[] {
            s
        });
        int i = sqlitedatabase.delete("DownloadedFileTable", "file_path=?", as);
        mCount = refreshCount();
        return i;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        mCount = refreshCount();
        return 0;
        Exception exception;
        exception;
        mCount = refreshCount();
        throw exception;
    }

    public int deleteItemFromTableById(long l)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = mSqlite;
        as = new String[1];
        as[0] = String.valueOf(l);
        int i = sqlitedatabase.delete("DownloadedFileTable", "_id=?", as);
        mCount = refreshCount();
        return i;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        mCount = refreshCount();
        return 0;
        Exception exception;
        exception;
        mCount = refreshCount();
        throw exception;
    }

    public void deleteMovItemFromTableByIndex(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = mSqlite;
        int j = getDownloadedFileId(i);
        as = new String[1];
        as[0] = String.valueOf(j);
        sqlitedatabase.delete("DownloadedFileTable", "_id=?", as);
        mCount = refreshCount();
        return;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        mCount = refreshCount();
        return;
        Exception exception;
        exception;
        mCount = refreshCount();
        throw exception;
    }

    public int getCount()
    {
        if (mSqlite == null)
        {
            return 0;
        }
        if (mCount != -1)
        {
            return mCount;
        } else
        {
            mCount = refreshCount();
            return mCount;
        }
    }

    public int getDownloadedFileId(int i)
    {
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = mSqlite;
        if (sqlitedatabase == null)
        {
            return 0;
        }
        Cursor cursor = sqlitedatabase.query("DownloadedFileTable", null, null, null, null, null, null);
        int j;
        j = 0;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        j = 0;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        int i1 = cursor.getCount();
        j = 0;
        if (i >= i1)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        boolean flag1 = cursor.moveToFirst();
        j = 0;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        int j1;
        cursor.moveToPosition(i);
        j1 = cursor.getInt(cursor.getColumnIndex("_id"));
        j = j1;
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return j;
        Exception exception4;
        exception4;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception4;
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        j = 0;
        if (true)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        j = 0;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        int k = null.getCount();
        j = 0;
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        boolean flag = null.moveToFirst();
        j = 0;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        int l;
        null.moveToPosition(i);
        l = null.getInt(null.getColumnIndex("_id"));
        j = l;
        if (false)
        {
            null.close();
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception3;
        exception3;
        if (false)
        {
            null.close();
        }
        throw exception3;
        Exception exception;
        exception;
        if (true || i < 0)
        {
            break MISSING_BLOCK_LABEL_303;
        }
        if (i < null.getCount() && null.moveToFirst())
        {
            null.moveToPosition(i);
            null.getInt(null.getColumnIndex("_id"));
        }
        if (false)
        {
            null.close();
        }
        throw exception;
        Exception exception1;
        exception1;
        if (false)
        {
            null.close();
        }
        throw exception1;
    }

    public String getDownloadedFilePath(int i)
    {
        SQLiteDatabase sqlitedatabase;
        String as[];
        sqlitedatabase = mSqlite;
        if (sqlitedatabase == null)
        {
            return null;
        }
        int j = getDownloadedFileId(i);
        as = new String[1];
        as[0] = String.valueOf(j);
        Cursor cursor = sqlitedatabase.query("DownloadedFileTable", null, "_id=?", as, null, null, null);
        String s;
        s = null;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        boolean flag1 = cursor.moveToFirst();
        s = null;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        String s2;
        cursor.moveToPosition(i);
        s2 = cursor.getString(cursor.getColumnIndex("file_path"));
        s = s2;
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return s;
        Exception exception4;
        exception4;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception4;
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        s = null;
        if (true)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        boolean flag = null.moveToFirst();
        s = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_197;
        }
        String s1;
        null.moveToPosition(i);
        s1 = null.getString(null.getColumnIndex("file_path"));
        s = s1;
        if (false)
        {
            null.close();
        }
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception3;
        exception3;
        if (false)
        {
            null.close();
        }
        throw exception3;
        Exception exception;
        exception;
        if (true)
        {
            break MISSING_BLOCK_LABEL_264;
        }
        if (null.moveToFirst())
        {
            null.moveToPosition(i);
            null.getString(null.getColumnIndex("file_path"));
        }
        if (false)
        {
            null.close();
        }
        throw exception;
        Exception exception1;
        exception1;
        if (false)
        {
            null.close();
        }
        throw exception1;
    }

    public SQLiteDatabase getSqlite()
    {
        return mSqlite;
    }

    public void init()
    {
        createDB();
        refreshDB();
    }

    public boolean isReady()
    {
        return mIsDBReady;
    }

    public Cursor query(String as[], String s, String as1[], String s1)
    {
        Object obj;
        SQLiteDatabase sqlitedatabase;
        obj = null;
        sqlitedatabase = getSqlite();
        if (sqlitedatabase == null)
        {
            return null;
        }
        obj = sqlitedatabase.query("DownloadedFileTable", as, s, as1, null, null, s1);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        obj = EmptyCursor.EMPTY_CURSOR;
        ((Cursor) (obj)).moveToFirst();
_L2:
        return ((Cursor) (obj));
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        ((Cursor) (obj)).moveToFirst();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        ((Cursor) (obj)).moveToFirst();
        throw exception;
    }

    public int refreshCount()
    {
        SQLiteDatabase sqlitedatabase = mSqlite;
        Cursor cursor = sqlitedatabase.query("DownloadedFileTable", null, null, null, null, null, null);
        int i;
        i = 0;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        boolean flag1 = cursor.moveToFirst();
        i = 0;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        int k = cursor.getCount();
        i = k;
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return i;
        Exception exception4;
        exception4;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception4;
        Exception exception2;
        exception2;
        exception2.printStackTrace();
        i = 0;
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag = null.moveToFirst();
        i = 0;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j = null.getCount();
        i = j;
        if (true) goto _L2; else goto _L1
_L1:
        null.close();
        return i;
        Exception exception3;
        exception3;
        if (false)
        {
            null.close();
        }
        throw exception3;
        Exception exception;
        exception;
        if (true)
        {
            break MISSING_BLOCK_LABEL_181;
        }
        if (null.moveToFirst())
        {
            null.getCount();
        }
        if (false)
        {
            null.close();
        }
        throw exception;
        Exception exception1;
        exception1;
        if (false)
        {
            null.close();
        }
        throw exception1;
    }

    public void refreshDB()
    {
        Cursor cursor = query(DownloadedFileTable.PROJECTION_ARRAY, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                String s = cursor.getString(cursor.getColumnIndex("file_path"));
                if (!isValidFile(s))
                {
                    deleteItemFromTableByFilePath(s);
                }
            } while (cursor.moveToNext());
        }
    }

    public void releaseInstance()
    {
        uninit();
    }

    protected void setDBReady(boolean flag)
    {
        mIsDBReady = flag;
    }

    public void uninit()
    {
        if (mSqlite != null)
        {
            mSqlite.close();
            mSqlite = null;
        }
    }

}
