// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.database.sqlite.SQLiteDatabase;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.mediaplus.updownload.dtcp.DTCPDownloadPoolDriver;
import com.arcsoft.util.debug.Log;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements com.arcsoft.mediaplus.updownload.easytransfer.IOnEasyTransferDriverListener
{

    final UpDownloadEngine this$0;

    public int onCancel(List list, String s, long l)
    {
        SQLiteDatabase sqlitedatabase;
        Log.i("UpDownloadEngine", "cancel some easy-transfer");
        if (UpDownloadEngine.this == null)
        {
            Log.e("UpDownloadEngine", "updownload engine had release");
            return -1;
        }
        if (UpDownloadEngine.access$1300(UpDownloadEngine.this) || list == null || list.size() < 1)
        {
            Log.e("UpDownloadEngine", "all task is null");
            return -1;
        }
        sqlitedatabase = UpDownloadEngine.access$1400(UpDownloadEngine.this).getManagerDataBase();
        UpDownloadEngine.access$1500(UpDownloadEngine.this).writeLock().lock();
        sqlitedatabase.beginTransaction();
        int j;
        int i = 0 + UpDownloadEngine.access$1800(UpDownloadEngine.this).cancelTask(s);
        Log.i("UpDownloadEngine", (new StringBuilder()).append("cancel easy-transfer going task =").append(i).toString());
        String as[] = new String[3];
        as[0] = Integer.toString(0);
        as[1] = s;
        as[2] = Integer.toString(1);
        j = i + UpDownloadEngine.access$1400(UpDownloadEngine.this).deleteDownload("type =? AND dms_uuid =? AND state =? ", as);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        UpDownloadEngine.access$1500(UpDownloadEngine.this).writeLock().unlock();
        return j;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public boolean onDownload(List list, String s, long l)
    {
        SQLiteDatabase sqlitedatabase;
        Log.w("UpDownloadEngine", "download some easy-transfer");
        if (UpDownloadEngine.this == null)
        {
            Log.e("UpDownloadEngine", "updownload engine had release");
            return false;
        }
        if (UpDownloadEngine.access$1300(UpDownloadEngine.this) || list == null || list.size() < 1)
        {
            Log.e("UpDownloadEngine", "all task is null");
            return false;
        }
        sqlitedatabase = UpDownloadEngine.access$1400(UpDownloadEngine.this).getManagerDataBase();
        UpDownloadEngine.access$1500(UpDownloadEngine.this).writeLock().lock();
        sqlitedatabase.beginTransaction();
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            com.arcsoft.mediaplus.updownload.easytransfer.TransferItem transferitem = (com.arcsoft.mediaplus.updownload.easytransfer.TransferItem)iterator.next();
            wnloadTask wnloadtask = transferitem.task;
            UpDownloadEngine.access$1600(UpDownloadEngine.this, 0, wnloadtask.uri, wnloadtask.dms_uuid);
            long l1 = UpDownloadEngine.access$1700(UpDownloadEngine.this, wnloadtask);
            Log.i("UpDownloadEngine", (new StringBuilder()).append("insert table id =").append(l1).toString());
            transferitem._id = l1;
        }

        break MISSING_BLOCK_LABEL_206;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        UpDownloadEngine.access$1500(UpDownloadEngine.this).writeLock().unlock();
        if (!isChecking() && isUploadAndDownloadPoolIdle())
        {
            UpDownloadEngine.access$1000(UpDownloadEngine.this);
        }
        return true;
    }

    public boolean onIsUpDownloadIdle()
    {
        if (UpDownloadEngine.this != null)
        {
            return isUploadAndDownloadPoolIdle();
        } else
        {
            return false;
        }
    }

    wnloadTask()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
