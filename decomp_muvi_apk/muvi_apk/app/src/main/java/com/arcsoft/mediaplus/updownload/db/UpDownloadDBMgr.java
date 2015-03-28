// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.db;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.db:
//            UpDownloadDBHelper

public class UpDownloadDBMgr
{
    public static interface IOnDBDataListener
    {

        public abstract void OnDBDataMounted(String s);

        public abstract void OnDBDataUnMounted(String s);

        public abstract void OnDBDataUpdated(String s);
    }


    private static final String TAG = "UpDownloadDBMgr";
    private static UpDownloadDBMgr sInstance = null;
    private Application mApp;
    private Application mApplication;
    private UpDownloadDBHelper mDBHelper;
    private final ArrayList mListeners = new ArrayList();
    private ReadWriteLock mLock;
    private Cursor mQueryCursor;
    private Cursor mQueryDownloadCursor;
    private Cursor mQueryUploadCursor;

    private UpDownloadDBMgr(Application application)
    {
        mApp = null;
        mDBHelper = null;
        mApplication = null;
        mLock = null;
        mQueryCursor = null;
        mQueryDownloadCursor = null;
        mQueryUploadCursor = null;
        mApplication = application;
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
                Log.v("UpDownloadDBMgr", (new StringBuilder()).append("[Class:").append(astacktraceelement[j].getClassName()).append(", Method:").append(astacktraceelement[j].getMethodName()).append(", Line:").append(astacktraceelement[j].getLineNumber()).append("]").toString());
                j++;
            }
        }
    }

    private void init()
    {
        mDBHelper = new UpDownloadDBHelper(mApplication);
    }

    public static void initSingleton(Application application, Looper looper, ReadWriteLock readwritelock)
    {
        getTraceInfo();
        if (sInstance != null)
        {
            Log.w("UpDownloadDBMgr", "Already initialized.");
            return;
        } else
        {
            sInstance = new UpDownloadDBMgr(application);
            sInstance.init();
            sInstance.mApp = application;
            sInstance.mLock = readwritelock;
            return;
        }
    }

    public static UpDownloadDBMgr instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    public static UpDownloadDBMgr newInstance(Application application, Looper looper, ReadWriteLock readwritelock)
    {
        getTraceInfo();
        UpDownloadDBMgr updownloaddbmgr = new UpDownloadDBMgr(application);
        updownloaddbmgr.init();
        updownloaddbmgr.mApp = application;
        updownloaddbmgr.mLock = readwritelock;
        return updownloaddbmgr;
    }

    private void releaseDownloadCursor()
    {
        if (mQueryDownloadCursor != null)
        {
            mQueryDownloadCursor.close();
            mQueryDownloadCursor = null;
        }
    }

    private void releaseQueryCursor()
    {
        if (mQueryCursor != null)
        {
            mQueryCursor.close();
            mQueryCursor = null;
        }
        releaseDownloadCursor();
        releaseUploadCursor();
    }

    private void releaseUploadCursor()
    {
        if (mQueryUploadCursor != null)
        {
            mQueryUploadCursor.close();
            mQueryUploadCursor = null;
        }
    }

    private void uninit()
    {
        if (mDBHelper != null)
        {
            mDBHelper.close();
        }
        mDBHelper = null;
        releaseQueryCursor();
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

    public int delete(String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        int j = sqlitedatabase.delete("UpDownloadTable", s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int deleteDownload(String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        int i = deleteDownloadNotSync(s, as);
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int deleteDownloadNotSync(String s, String as[])
    {
        releaseQueryCursor();
        UpDownloadDBHelper updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase = null;
        if (updownloaddbhelper != null)
        {
            sqlitedatabase = mDBHelper.getManagerDatabase();
        }
        int i = 0;
        if (sqlitedatabase != null)
        {
            i = sqlitedatabase.delete("UpDownloadTable", s, as);
        }
        return i;
    }

    public int deleteEasyTransfer(String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        int j = sqlitedatabase.delete("EasyTransferTable", s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int deleteUpload(String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        int j = sqlitedatabase.delete("UpDownloadTable", s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getAllCount()
    {
        this;
        JVM INSTR monitorenter ;
        if (mQueryCursor == null) goto _L2; else goto _L1
_L1:
        int k = mQueryCursor.getCount();
        int j = k;
_L4:
        this;
        JVM INSTR monitorexit ;
        return j;
_L2:
        Cursor cursor = query(new String[] {
            "_id"
        }, null, null, null);
        int i;
        i = 0;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        i = cursor.getCount();
        cursor.close();
        j = i;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public int getAllDownloadingCount()
    {
        this;
        JVM INSTR monitorenter ;
        if (mQueryCursor == null) goto _L2; else goto _L1
_L1:
        int k = mQueryCursor.getCount();
        int j = k;
_L4:
        this;
        JVM INSTR monitorexit ;
        return j;
_L2:
        Cursor cursor = query(new String[] {
            "_id", "state"
        }, "state = 1 or state = 5 or state = 2", null, null);
        int i;
        i = 0;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        i = cursor.getCount();
        cursor.close();
        j = i;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public SQLiteDatabase getManagerDataBase()
    {
        return mDBHelper.getManagerDatabase();
    }

    public long insertDownload(ContentValues contentvalues)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        long l = -1L;
        contentvalues.put("type", Integer.valueOf(0));
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        l = sqlitedatabase.insert("UpDownloadTable", null, contentvalues);
        if (l >= 0L)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        throw new SQLException("Failed to Add DOWNLOAD!!!");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Log.v("UpDownloadDBMgr", (new StringBuilder()).append("Added DOWNLOAD rowId = ").append(l).toString());
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    public long insertEasyTransfer(ContentValues contentvalues)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        long l;
        l = -1L;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        l = sqlitedatabase.insert("EasyTransferTable", null, contentvalues);
        if (l >= 0L)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        throw new SQLException("Failed to Add DOWNLOAD!!!");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Log.v("UpDownloadDBMgr", (new StringBuilder()).append("Added DOWNLOAD rowId = ").append(l).toString());
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    public long insertUpload(ContentValues contentvalues)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        long l = -1L;
        contentvalues.put("type", Integer.valueOf(1));
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        l = sqlitedatabase.insert("UpDownloadTable", null, contentvalues);
        if (l >= 0L)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        throw new SQLException("Failed to Add UPLOAD!!!");
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Log.v("UpDownloadDBMgr", (new StringBuilder()).append("Added UPLOAD rowId = ").append(l).toString());
        this;
        JVM INSTR monitorexit ;
        return l;
    }

    public Cursor query(String as[], String s, String as1[], String s1)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        Cursor cursor;
        cursor = null;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        cursor = sqlitedatabase.query("UpDownloadTable", as, s, as1, null, null, s1);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        Log.v("UpDownloadDBMgr", "Failed to Query DOWNLOAD!!!");
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryAll(String as[])
    {
        this;
        JVM INSTR monitorenter ;
        if (mQueryCursor != null) goto _L2; else goto _L1
_L1:
        Cursor cursor;
        cursor = query(as, null, null, null);
        mQueryCursor = cursor;
_L4:
        this;
        JVM INSTR monitorexit ;
        return cursor;
_L2:
        cursor = mQueryCursor;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryAllDownload()
    {
        this;
        JVM INSTR monitorenter ;
        if (mQueryDownloadCursor != null) goto _L2; else goto _L1
_L1:
        Cursor cursor;
        cursor = queryDownload(new String[] {
            "_id", "uri", "state"
        }, null, null, null);
        mQueryDownloadCursor = cursor;
_L4:
        this;
        JVM INSTR monitorexit ;
        return cursor;
_L2:
        cursor = mQueryDownloadCursor;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryAllUpload()
    {
        this;
        JVM INSTR monitorenter ;
        if (mQueryUploadCursor != null) goto _L2; else goto _L1
_L1:
        Cursor cursor;
        cursor = queryUpload(new String[] {
            "_id", "uri", "state"
        }, null, null, null);
        mQueryUploadCursor = cursor;
_L4:
        this;
        JVM INSTR monitorexit ;
        return cursor;
_L2:
        cursor = mQueryUploadCursor;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryDownload(String as[], String s, String as1[], String s1)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        Cursor cursor;
        cursor = null;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        cursor = sqlitedatabase.query("DownloadView", as, s, as1, null, null, s1);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        Log.v("UpDownloadDBMgr", "Failed to Query DOWNLOAD!!!");
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryEasyTransfer(String as[], String s, String as1[], String s1)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        Cursor cursor;
        cursor = null;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        cursor = sqlitedatabase.query("EasyTransferTable", as, s, as1, null, null, s1);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        Log.v("UpDownloadDBMgr", "Failed to Query EASY TRANSFER!!!");
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryUpload(String as[], String s, String as1[], String s1)
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        Cursor cursor;
        cursor = null;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        cursor = sqlitedatabase.query("UploadView", as, s, as1, null, null, s1);
        if (cursor != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        Log.v("UpDownloadDBMgr", "Failed to Query UPLOAD!!!");
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception;
        exception;
        throw exception;
    }

    public void registerOnDataUpdateListener(IOnDBDataListener iondbdatalistener)
    {
label0:
        {
            synchronized (mListeners)
            {
                if (!mListeners.contains(iondbdatalistener))
                {
                    break label0;
                }
            }
            return;
        }
        mListeners.add(iondbdatalistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void releaseInstance()
    {
        uninit();
    }

    public void unregisterOnDataUpdateListener(IOnDBDataListener iondbdatalistener)
    {
        synchronized (mListeners)
        {
            mListeners.remove(iondbdatalistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int update(ContentValues contentvalues, String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        int j = sqlitedatabase.update("UpDownloadTable", contentvalues, s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int updateDownload(ContentValues contentvalues, String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        int j = sqlitedatabase.update("UpDownloadTable", contentvalues, s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int updateEasyTransfer(ContentValues contentvalues, String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        int j = sqlitedatabase.update("EasyTransferTable", contentvalues, s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int updateUpload(ContentValues contentvalues, String s, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        UpDownloadDBHelper updownloaddbhelper;
        releaseQueryCursor();
        updownloaddbhelper = mDBHelper;
        SQLiteDatabase sqlitedatabase;
        sqlitedatabase = null;
        if (updownloaddbhelper == null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        sqlitedatabase = mDBHelper.getManagerDatabase();
        int i;
        i = 0;
        if (sqlitedatabase == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        int j = sqlitedatabase.update("UpDownloadTable", contentvalues, s, as);
        i = j;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

}
