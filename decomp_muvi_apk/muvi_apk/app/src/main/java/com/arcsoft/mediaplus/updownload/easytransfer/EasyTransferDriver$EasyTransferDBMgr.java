// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.datasource.db.DataTask;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.datasource.db.RemoteDataDBHelper;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferDriver

private class init extends RemoteDBMgr
{

    private final String PROJECTIONS[] = {
        "DATE", "_ID", "TITLE", "URL", "SIZE"
    };
    private String mServerudn;
    private long mTableId;
    private int readyTransfered;
    final EasyTransferDriver this$0;

    private int getCopyCount(String s, String s1)
    {
        return getRemoteItemCopyCount(s, s1);
    }

    private int getEasyTransferFlag(String s, String s1)
    {
        return 1;
    }

    private String getProtocolInfo(Uri uri, long l)
    {
        com.arcsoft.adk.atv.rDriver.EasyTransferDBMgr easytransferdbmgr;
label0:
        {
            ArrayList arraylist = getRemoteItemResourceDesc(l);
            boolean flag;
            String s;
            if (!FileUtils.isLocalItem(uri) && DLNA.instance().getServerManager().isDigaDMS(RemoteDBMgr.instance().getCurrentServer()))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            s = null;
            if (arraylist != null)
            {
                int i = arraylist.size();
                s = null;
                if (i > 0)
                {
                    easytransferdbmgr = (com.arcsoft.adk.atv.tServer)arraylist.get(0);
                    if (!flag || easytransferdbmgr.ContentProtocolInfo == null || easytransferdbmgr.ContentProtocolInfo.length() == 0)
                    {
                        break label0;
                    }
                    s = easytransferdbmgr.ContentProtocolInfo;
                }
            }
            return s;
        }
        return easytransferdbmgr.olInfo;
    }

    protected Cursor queryVideo()
    {
        this;
        JVM INSTR monitorenter ;
        Cursor cursor = super.queryVideo(PROJECTIONS, "DATE IS NOT NULL", null, null, null, null, null);
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean ready()
    {
        this;
        JVM INSTR monitorenter ;
        int i = readyTransfered;
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean start(String s, long l)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        Log.w("EasyTransferDriver", (new StringBuilder()).append("start id:").append(l).append(", server:").append(s).toString());
        if (EasyTransferDriver.access$1100(EasyTransferDriver.this)) goto _L2; else goto _L1
_L1:
        Log.w("EasyTransferDriver", "wifi is offline, so as not to easy-transfer.");
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        if (EasyTransferDriver.access$900(EasyTransferDriver.this) || !EasyTransferDriver.access$1000(EasyTransferDriver.this))
        {
            break MISSING_BLOCK_LABEL_103;
        }
        Log.w("EasyTransferDriver", "destination sdcard unmounted, so as not to easy-transfer.");
        flag = false;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        if (DLNA.instance().getServerManager().isServerOnline(s))
        {
            break MISSING_BLOCK_LABEL_130;
        }
        Log.w("EasyTransferDriver", "server is offline, so as not to easy-transfer.");
        flag = false;
        continue; /* Loop/switch isn't completed */
        boolean flag1;
        int i;
        String s1;
        if (DLNA.instance().getServerManager().isDigaDMS(s))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mEncodedFolder = flag1;
        flag = false;
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = s.length();
        flag = false;
        if (i <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        readyTransfered = 1;
        mServerudn = s;
        mTableId = l;
        s1 = getCurrentServer();
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        if (s1.equals(s))
        {
            requestServerData(true);
            break MISSING_BLOCK_LABEL_223;
        }
        setCurrentServer(s, null, true);
        flag = true;
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void startDBData(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (mDataDBHelper != null) goto _L2; else goto _L1
_L1:
        String s1 = mServerUDN;
        if (s1 != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        mDataDBHelper = new RemoteDataDBHelper(mApplication, mServerUDN, "easytransfer");
        mDataTask.setDatabaseHelper(mDataDBHelper, null, flag);
        mNotifyTimer.geFlag(mServerUDN, true);
        mDataTask.requestServerData(flag, mEncodedFolder);
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public void stop(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Log.w("EasyTransferDriver", (new StringBuilder()).append("DBMgr stop() =").append(s).toString());
        if (s != null) goto _L2; else goto _L1
_L1:
        readyTransfered = 0;
        setCurrentServer(null, null, true);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (s == null) goto _L4; else goto _L3
_L3:
        if (!s.equals(mServerudn)) goto _L4; else goto _L5
_L5:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        readyTransfered = 0;
        setCurrentServer(null, null, true);
          goto _L4
        Exception exception;
        exception;
        throw exception;
        readyTransfered = 2;
          goto _L4
    }

    protected void stopDBData()
    {
        this;
        JVM INSTR monitorenter ;
        RemoteDataDBHelper remotedatadbhelper;
        cancelServerDataRequest();
        remotedatadbhelper = mDataDBHelper;
        if (remotedatadbhelper != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mDataDBHelper.close();
        mDataDBHelper = null;
        mNotifyTimer.mNotifyTimer();
        mNotifyTimer.geFlag(mServerUDN, false);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    protected void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        mServerudn = null;
        mTableId = -1L;
        super.uninit();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }






    protected (Application application, Looper looper)
    {
        this$0 = EasyTransferDriver.this;
        super(application, looper);
        readyTransfered = 0;
        mServerudn = null;
        mTableId = -1L;
        mEncodedFolder = true;
        init();
    }
}
