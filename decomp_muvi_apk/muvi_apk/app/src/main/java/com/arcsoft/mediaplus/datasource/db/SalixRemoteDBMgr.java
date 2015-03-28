// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import java.util.HashSet;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixHttpDataTask, SalixHttpDBHelper

public class SalixRemoteDBMgr
{
    public static interface IOnSalixDBUpdaterListener
    {

        public abstract void OnDBDataMounted();

        public abstract void OnDBDataTransmittedBegin();

        public abstract void OnDBDataTransmittedFinish();

        public abstract void OnDBDataUnMounted();

        public abstract void OnDBDataUpdated();
    }


    private static final int MSG_ON_DB_INCREASED = 1;
    private static final int MSG_ON_DB_MOUNTED = 4;
    private static final int MSG_ON_DB_TRANSMITTEDBEGIN = 2;
    private static final int MSG_ON_DB_TRANSMITTEDFINISH = 3;
    private static final int MSG_ON_DB_UNMOUNTED = 5;
    private static final int MSG_ON_DB_UPDATED;
    static SalixRemoteDBMgr sInstance = null;
    Application mApp;
    SalixHttpDBHelper mDataDBHelper;
    Handler mHandler;
    private final HashSet mListeners = new HashSet();
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final SalixRemoteDBMgr this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo == null || networkstateinfo.networkInfo.getType() != 1)
            {
                return;
            } else
            {
                Log.e("FENG", (new StringBuilder()).append("SalixRemoteBDMgr OnConnectivityChanged info.networkInfo.isConnected() = ").append(networkstateinfo.networkInfo.isConnected()).toString());
                return;
            }
        }

            
            {
                this$0 = SalixRemoteDBMgr.this;
                super();
            }
    };
    NetworkTool mNetworkTool;
    SalixHttpDataTask.IOnSalixDataUpdateListener mOnSalixDataUpdateListener;
    SalixHttpDataTask mSalixHttpDataTask;

    protected SalixRemoteDBMgr(Application application)
    {
        mApp = null;
        mSalixHttpDataTask = null;
        mDataDBHelper = null;
        mNetworkTool = null;
        mOnSalixDataUpdateListener = new SalixHttpDataTask.IOnSalixDataUpdateListener() {

            final SalixRemoteDBMgr this$0;

            public void OnDataIncreased(boolean flag, boolean flag1)
            {
            }

            public void OnDataTransmittedBegin()
            {
                mHandler.removeMessages(2);
                mHandler.sendEmptyMessage(2);
            }

            public void OnDataTransmittedFinish()
            {
                mHandler.removeMessages(3);
                mHandler.sendEmptyMessage(3);
            }

            public void OnDataUpdated()
            {
                mHandler.removeMessages(0);
                mHandler.sendEmptyMessage(0);
            }

            
            {
                this$0 = SalixRemoteDBMgr.this;
                super();
            }
        };
        mHandler = new Handler() {

            final SalixRemoteDBMgr this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 5: default 44
            //                           0 191
            //                           1 44
            //                           2 264
            //                           3 337
            //                           4 45
            //                           5 118;
                   goto _L1 _L2 _L1 _L3 _L4 _L5 _L6
_L1:
                return;
_L5:
                HashSet hashset4 = mListeners;
                hashset4;
                JVM INSTR monitorenter ;
                Iterator iterator4 = mListeners.iterator();
_L10:
                if (!iterator4.hasNext()) goto _L8; else goto _L7
_L7:
                IOnSalixDBUpdaterListener ionsalixdbupdaterlistener4 = (IOnSalixDBUpdaterListener)iterator4.next();
                if (ionsalixdbupdaterlistener4 == null) goto _L10; else goto _L9
_L9:
                ionsalixdbupdaterlistener4.OnDBDataMounted();
                  goto _L10
                Exception exception4;
                exception4;
                hashset4;
                JVM INSTR monitorexit ;
                throw exception4;
_L8:
                hashset4;
                JVM INSTR monitorexit ;
                return;
_L6:
                HashSet hashset3 = mListeners;
                hashset3;
                JVM INSTR monitorenter ;
                Iterator iterator3 = mListeners.iterator();
_L14:
                if (!iterator3.hasNext()) goto _L12; else goto _L11
_L11:
                IOnSalixDBUpdaterListener ionsalixdbupdaterlistener3 = (IOnSalixDBUpdaterListener)iterator3.next();
                if (ionsalixdbupdaterlistener3 == null) goto _L14; else goto _L13
_L13:
                ionsalixdbupdaterlistener3.OnDBDataUnMounted();
                  goto _L14
                Exception exception3;
                exception3;
                hashset3;
                JVM INSTR monitorexit ;
                throw exception3;
_L12:
                hashset3;
                JVM INSTR monitorexit ;
                return;
_L2:
                HashSet hashset2 = mListeners;
                hashset2;
                JVM INSTR monitorenter ;
                Iterator iterator2 = mListeners.iterator();
_L18:
                if (!iterator2.hasNext()) goto _L16; else goto _L15
_L15:
                IOnSalixDBUpdaterListener ionsalixdbupdaterlistener2 = (IOnSalixDBUpdaterListener)iterator2.next();
                if (ionsalixdbupdaterlistener2 == null) goto _L18; else goto _L17
_L17:
                ionsalixdbupdaterlistener2.OnDBDataUpdated();
                  goto _L18
                Exception exception2;
                exception2;
                hashset2;
                JVM INSTR monitorexit ;
                throw exception2;
_L16:
                hashset2;
                JVM INSTR monitorexit ;
                return;
_L3:
                HashSet hashset1 = mListeners;
                hashset1;
                JVM INSTR monitorenter ;
                Iterator iterator1 = mListeners.iterator();
_L22:
                if (!iterator1.hasNext()) goto _L20; else goto _L19
_L19:
                IOnSalixDBUpdaterListener ionsalixdbupdaterlistener1 = (IOnSalixDBUpdaterListener)iterator1.next();
                if (ionsalixdbupdaterlistener1 == null) goto _L22; else goto _L21
_L21:
                ionsalixdbupdaterlistener1.OnDBDataTransmittedBegin();
                  goto _L22
                Exception exception1;
                exception1;
                hashset1;
                JVM INSTR monitorexit ;
                throw exception1;
_L20:
                hashset1;
                JVM INSTR monitorexit ;
                return;
_L4:
                HashSet hashset = mListeners;
                hashset;
                JVM INSTR monitorenter ;
                Iterator iterator = mListeners.iterator();
_L26:
                if (!iterator.hasNext()) goto _L24; else goto _L23
_L23:
                IOnSalixDBUpdaterListener ionsalixdbupdaterlistener = (IOnSalixDBUpdaterListener)iterator.next();
                if (ionsalixdbupdaterlistener == null) goto _L26; else goto _L25
_L25:
                ionsalixdbupdaterlistener.OnDBDataTransmittedFinish();
                  goto _L26
                Exception exception;
                exception;
                hashset;
                JVM INSTR monitorexit ;
                throw exception;
_L24:
                hashset;
                JVM INSTR monitorexit ;
            }

            
            {
                this$0 = SalixRemoteDBMgr.this;
                super();
            }
        };
        mApp = application;
    }

    public static void initSingleton(Application application)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new SalixRemoteDBMgr(application);
            sInstance.init();
            return;
        }
    }

    public static SalixRemoteDBMgr instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    public static void uninitSingleton()
    {
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

    public void clearDBData()
    {
        mSalixHttpDataTask.clearDBData();
    }

    protected void init()
    {
        mDataDBHelper = new SalixHttpDBHelper(mApp);
        mSalixHttpDataTask = new SalixHttpDataTask(mApp);
        mSalixHttpDataTask.setOnDataUpdateListener(mOnSalixDataUpdateListener);
        mSalixHttpDataTask.start();
        initNetworkLis();
    }

    void initNetworkLis()
    {
        mNetworkTool = new NetworkTool(mApp);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
    }

    public Cursor queryVideoAndImageCursor(String as[], String s, String as1[], String s1, String s2, String s3, String s4)
    {
        this;
        JVM INSTR monitorenter ;
        Cursor cursor1 = mDataDBHelper.getManagedDatabase().query("Media_table", as, s, as1, s1, s2, s3, s4);
        Cursor cursor = cursor1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public void registerOnDataUpdateListener(IOnSalixDBUpdaterListener ionsalixdbupdaterlistener)
    {
        synchronized (mListeners)
        {
            mListeners.add(ionsalixdbupdaterlistener);
        }
        return;
        exception;
        hashset;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void requestServerData(boolean flag)
    {
        mSalixHttpDataTask.requestServerData(flag);
    }

    protected void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        mApp = null;
        mSalixHttpDataTask.setOnDataUpdateListener(null);
        mSalixHttpDataTask.recycle();
        mDataDBHelper = null;
        if (mNetworkTool != null)
        {
            mNetworkTool.setOnConnectivityChangeListener(null);
            mNetworkTool = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterOnDataUpdateListener(IOnSalixDBUpdaterListener ionsalixdbupdaterlistener)
    {
        synchronized (mListeners)
        {
            mListeners.remove(ionsalixdbupdaterlistener);
        }
        return;
        exception;
        hashset;
        JVM INSTR monitorexit ;
        throw exception;
    }


}
