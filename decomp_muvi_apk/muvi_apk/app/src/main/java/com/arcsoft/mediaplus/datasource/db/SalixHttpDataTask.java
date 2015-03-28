// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.arcsoft.Recyclable;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixHttpDBHelper, SalixHttpUpdater

public class SalixHttpDataTask extends Thread
    implements Recyclable
{
    public static interface IOnSalixDataUpdateListener
    {

        public abstract void OnDataIncreased(boolean flag, boolean flag1);

        public abstract void OnDataTransmittedBegin();

        public abstract void OnDataTransmittedFinish();

        public abstract void OnDataUpdated();
    }


    private static final int STATUS_DOING = 1;
    private static final int STATUS_NONE = 0;
    private static final int STATUS_QUIT = 2;
    private static final String TAG = "SalixHttpDataTask";
    private boolean mClearData;
    private SalixHttpDBHelper mDBHelper;
    private IOnSalixDataUpdateListener mListener;
    private final Object mMutex = new Object();
    private int mStatus;

    public SalixHttpDataTask(Context context)
    {
        mStatus = 0;
        mDBHelper = null;
        mClearData = false;
        mListener = null;
        mDBHelper = new SalixHttpDBHelper(context);
    }

    private boolean checkAndClearDBData(SQLiteDatabase sqlitedatabase)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mClearData;
        boolean flag1 = false;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        if (mDBHelper != null)
        {
            sqlitedatabase = mDBHelper.getManagedDatabase();
        }
        flag1 = false;
        if (sqlitedatabase == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mClearData = false;
        sqlitedatabase.beginTransaction();
        sqlitedatabase.delete("Media_table", null, null);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataUpdated();
            }
        }
        break MISSING_BLOCK_LABEL_115;
        exception1;
        ionsalixdataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        flag1 = true;
        if (true) goto _L1; else goto _L3
_L3:
    }

    private boolean hasData()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = mDBHelper.getManagedDatabase();
        cursor = null;
        int i;
        cursor = sqlitedatabase.rawQuery("select count(*) from MediaTable", null);
        cursor.moveToFirst();
        i = cursor.getInt(0);
        boolean flag;
        flag = false;
        if (i > 0)
        {
            flag = true;
        }
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return flag;
        Exception exception1;
        exception1;
        flag = false;
        if (cursor == null) goto _L2; else goto _L1
_L1:
        cursor.close();
        return false;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    public void cancelTask()
    {
        this;
        JVM INSTR monitorenter ;
        pauseTask();
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish();
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        ionsalixdataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean clearDBData()
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = mDBHelper.getManagedDatabase();
        boolean flag = false;
        if (sqlitedatabase != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        mClearData = false;
        sqlitedatabase.beginTransaction();
        sqlitedatabase.delete("Media_table", null, null);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataUpdated();
            }
        }
        break MISSING_BLOCK_LABEL_90;
        exception1;
        ionsalixdataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        flag = true;
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void insertTable(SalixHttpUpdater.HttpFileEntity httpfileentity)
    {
        this;
        JVM INSTR monitorenter ;
        if (httpfileentity != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        SQLiteDatabase sqlitedatabase;
        ContentValues contentvalues;
        sqlitedatabase = mDBHelper.getManagedDatabase();
        contentvalues = new ContentValues();
        Exception exception;
        int i;
        long l;
        if (httpfileentity.mbIsVideo)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("is_video", Integer.valueOf(i));
        contentvalues.put("url", httpfileentity.mUrl);
        contentvalues.put("size", Long.valueOf(httpfileentity.mSize));
        contentvalues.put("time_code", Integer.valueOf(httpfileentity.mCreateTime));
        contentvalues.put("name", httpfileentity.mFileName);
        l = sqlitedatabase.insert("Media_table", null, contentvalues);
        Log.d("test", (new StringBuilder()).append("insert result  = ").append(l).toString());
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }

    public void pauseTask()
    {
        this;
        JVM INSTR monitorenter ;
        mStatus = 0;
        synchronized (mMutex) { }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void recycle()
    {
        if (mDBHelper != null)
        {
            mDBHelper.close();
        }
        mDBHelper = null;
    }

    public void requestServerData(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        boolean flag1 = hasData();
        if (!flag1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        cancelTask();
        mClearData = flag | mClearData;
        checkAndClearDBData(null);
        resumeTask();
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void resumeTask()
    {
        this;
        JVM INSTR monitorenter ;
        mStatus = 1;
        synchronized (mMutex)
        {
            mMutex.notify();
        }
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedBegin();
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception2;
        ionsalixdataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public void run()
    {
        List list;
        new ArrayList();
        list = null;
_L4:
        if (mStatus == 2)
        {
            break MISSING_BLOCK_LABEL_177;
        }
        Object obj = mMutex;
        obj;
        JVM INSTR monitorenter ;
        SalixHttpDBHelper salixhttpdbhelper;
        if (mStatus != 1)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        salixhttpdbhelper = mDBHelper;
        if (salixhttpdbhelper != null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        mMutex.wait();
_L1:
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        obj;
        JVM INSTR monitorexit ;
        checkAndClearDBData(mDBHelper.getManagedDatabase());
        List list1 = SalixHttpUpdater.updateContent();
        list = list1;
_L2:
        Exception exception1;
        if (list == null || list.size() == 0)
        {
            cancelTask();
        } else
        {
            for (Iterator iterator = list.iterator(); iterator.hasNext(); insertTable((SalixHttpUpdater.HttpFileEntity)iterator.next())) { }
            mListener.OnDataUpdated();
        }
        mStatus = 0;
        continue; /* Loop/switch isn't completed */
        exception1;
        exception1.printStackTrace();
          goto _L2
        return;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setOnDataUpdateListener(IOnSalixDataUpdateListener ionsalixdataupdatelistener)
    {
        mListener = ionsalixdataupdatelistener;
    }
}
