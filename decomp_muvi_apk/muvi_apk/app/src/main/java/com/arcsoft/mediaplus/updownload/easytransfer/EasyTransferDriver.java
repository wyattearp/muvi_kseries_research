// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.datasource.db.DataTask;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.datasource.db.RemoteDataDBHelper;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.db.EasyTransferTable;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.util.tool.DateConvert;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            IEasyTransferEngine, EasyTransferTimer, EasyTransferStateMachine, EasyTransferList

public class EasyTransferDriver
    implements IEasyTransferEngine
{
    private class DriverHandler extends Handler
    {

        final EasyTransferDriver this$0;

        public void handleMessage(Message message)
        {
            if (!mFinished) goto _L2; else goto _L1
_L1:
            return;
_L2:
            switch (message.what)
            {
            default:
                return;

            case 2: // '\002'
                if (!mIsMounted)
                {
                    if (checkDestinationToSDcard())
                    {
                        stop();
                        return;
                    }
                } else
                {
                    start();
                    return;
                }
                break;

            case 3: // '\003'
                if (!mIsWifiConnection)
                {
                    stop();
                    return;
                } else
                {
                    start();
                    return;
                }

            case 4: // '\004'
                String s = (String)message.obj;
                cancelEasyTransfer(s);
                return;

            case 0: // '\0'
            case 1: // '\001'
                break;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public void release()
        {
            removeMessages(0);
            removeMessages(1);
            removeMessages(2);
            removeMessages(3);
            removeMessages(4);
        }

        private DriverHandler()
        {
            this$0 = EasyTransferDriver.this;
            super();
        }

    }

    private class EasyTransferDBMgr extends RemoteDBMgr
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
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
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
                        presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)arraylist.get(0);
                        if (!flag || presentitem_resource.m_strPxnVgaContentProtocolInfo == null || presentitem_resource.m_strPxnVgaContentProtocolInfo.length() == 0)
                        {
                            break label0;
                        }
                        s = presentitem_resource.m_strPxnVgaContentProtocolInfo;
                    }
                }
                return s;
            }
            return presentitem_resource.m_strProtocolInfo;
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
            if (mIsWifiConnection) goto _L2; else goto _L1
_L1:
            Log.w("EasyTransferDriver", "wifi is offline, so as not to easy-transfer.");
_L4:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            if (mIsMounted || !checkDestinationToSDcard())
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
            mNotifyTimer.setServerChangeFlag(mServerUDN, true);
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
            mNotifyTimer.clearFlags();
            mNotifyTimer.setServerChangeFlag(mServerUDN, false);
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






        protected EasyTransferDBMgr(Application application, Looper looper)
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

    public static interface IOnEasyTransferDriverListener
    {

        public abstract int onCancel(List list, String s, long l);

        public abstract boolean onDownload(List list, String s, long l);

        public abstract boolean onIsUpDownloadIdle();
    }

    private static class StateMachineData
    {

        boolean retried;
        String serverudn;
        long tableid;

        StateMachineData(String s, long l, boolean flag)
        {
            serverudn = s;
            tableid = l;
            retried = flag;
        }
    }

    public static class TransferItem
    {

        public long _id;
        public com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask task;

        public TransferItem()
        {
        }
    }

    private class TransferQueue
        implements Recyclable
    {

        private final Comparator mCmpId;
        private final Comparator mCmpUdn;
        LinkedList mWaitQueue;
        final EasyTransferDriver this$0;

        public void add(String s, long l, int i, boolean flag)
        {
            Log.w("TransferQueue", (new StringBuilder()).append("add() id=").append(l).toString());
            LinkedList linkedlist = mWaitQueue;
            linkedlist;
            JVM INSTR monitorenter ;
            TransferServerNode transferservernode;
            transferservernode = new TransferServerNode();
            getServerInfo(transferservernode, l);
            transferservernode.serverudn = s;
            transferservernode.tableid = l;
            transferservernode.serverstate = i;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_96;
            }
            mWaitQueue.addFirst(transferservernode);
_L2:
            linkedlist;
            JVM INSTR monitorexit ;
            return;
            mWaitQueue.add(transferservernode);
            if (true) goto _L2; else goto _L1
_L1:
            Exception exception;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public TransferServerNode find(String s, long l)
        {
label0:
            {
                TransferServerNode transferservernode = new TransferServerNode();
                transferservernode.serverudn = s;
                transferservernode.tableid = l;
                Comparator comparator;
                TransferServerNode transferservernode1;
                if (s != null)
                {
                    comparator = mCmpUdn;
                } else
                {
                    comparator = mCmpId;
                }
                synchronized (mWaitQueue)
                {
                    Iterator iterator = mWaitQueue.iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break label0;
                        }
                        transferservernode1 = (TransferServerNode)iterator.next();
                    } while (comparator.compare(transferservernode, transferservernode1) != 0);
                    Log.w("TransferQueue", (new StringBuilder()).append("find() =").append(l).toString());
                }
                return transferservernode1;
            }
            Log.w("TransferQueue", (new StringBuilder()).append("find() fail =").append(l).toString());
            linkedlist;
            JVM INSTR monitorexit ;
            return null;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public TransferServerNode first()
        {
label0:
            {
                TransferServerNode transferservernode;
                synchronized (mWaitQueue)
                {
                    Iterator iterator = mWaitQueue.iterator();
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    transferservernode = (TransferServerNode)iterator.next();
                    Log.w("TransferQueue", (new StringBuilder()).append("first(), tableid =").append(transferservernode.tableid).toString());
                }
                return transferservernode;
            }
            Log.w("TransferQueue", "first() fail.");
            linkedlist;
            JVM INSTR monitorexit ;
            return null;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public int getCount()
        {
            int i;
            synchronized (mWaitQueue)
            {
                i = mWaitQueue.size();
                Log.w("TransferQueue", (new StringBuilder()).append("getCount() =").append(i).toString());
            }
            return i;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void recycle()
        {
            Log.w("TransferQueue", "recycle()");
            synchronized (mWaitQueue)
            {
                mWaitQueue.clear();
            }
            return;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public TransferServerNode remove(String s, long l)
        {
            TransferServerNode transferservernode1;
            LinkedList linkedlist;
            Log.w("TransferQueue", (new StringBuilder()).append("remove() id=").append(l).toString());
            TransferServerNode transferservernode = new TransferServerNode();
            transferservernode.serverudn = s;
            transferservernode.tableid = l;
            Comparator comparator;
            Iterator iterator;
            TransferServerNode transferservernode2;
            if (s != null)
            {
                comparator = mCmpUdn;
            } else
            {
                comparator = mCmpId;
            }
            transferservernode1 = null;
            linkedlist = mWaitQueue;
            linkedlist;
            JVM INSTR monitorenter ;
            iterator = mWaitQueue.iterator();
_L1:
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_141;
                }
                transferservernode2 = (TransferServerNode)iterator.next();
            } while (comparator.compare(transferservernode, transferservernode2) != 0);
            iterator.remove();
            transferservernode1 = transferservernode2;
              goto _L1
            linkedlist;
            JVM INSTR monitorexit ;
            return transferservernode1;
            Exception exception;
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void update(String s, long l)
        {
            Log.w("TransferQueue", (new StringBuilder()).append("update() id=").append(l).toString());
            TransferServerNode transferservernode = find(s, l);
            if (transferservernode != null)
            {
                synchronized (mWaitQueue)
                {
                    getServerInfo(transferservernode, l);
                }
                return;
            } else
            {
                return;
            }
            exception;
            linkedlist;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private TransferQueue()
        {
            this$0 = EasyTransferDriver.this;
            super();
            mWaitQueue = new LinkedList();
            mCmpUdn = new _cls1();
            mCmpId = new _cls2();
        }

    }

    private class TransferServerNode
    {

        boolean addtodownloadqueue;
        int current;
        boolean enableAllow;
        boolean enablePlugIn;
        int recordDay;
        String servername;
        int serverstate;
        String serverudn;
        int succuss;
        long tableid;
        final EasyTransferDriver this$0;
        int total;

        private TransferServerNode()
        {
            this$0 = EasyTransferDriver.this;
            super();
            recordDay = 0;
            enableAllow = false;
            enablePlugIn = false;
            total = 0;
            current = 0;
            succuss = 0;
            addtodownloadqueue = false;
        }

    }


    private final int MSG_MOUNTED = 2;
    private final int MSG_NEXT = 1;
    private final int MSG_SERVER_REMOVED = 4;
    private final int MSG_TIMER = 0;
    private final int MSG_WIFI = 3;
    private final int NOTIFY_ID = 1;
    private final String TAG = "EasyTransferDriver";
    Application mApp;
    ReadWriteLock mCursorLock;
    private final com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener mDBListener = new com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener() {

        final EasyTransferDriver this$0;
        boolean transmittedBegined;

        public void OnDBDataMounted(String s)
        {
        }

        public void OnDBDataTransmittedBegin(String s)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("OnDBDataTransmittedBegin() =").append(s).toString());
            if (s == null)
            {
                return;
            }
            if (mTransferQueue != null)
            {
                TransferServerNode transferservernode = mTransferQueue.first();
                if (transferservernode != null && s.equalsIgnoreCase(transferservernode.serverudn))
                {
                    for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IEasyTransferEngine.IOnEasyTransferEngineListener)iterator.next()).onEasyTransferStart(s, transferservernode.tableid, transferservernode.serverstate)) { }
                } else
                if (mRemoteDBMgr != null)
                {
                    if (s.equalsIgnoreCase(mRemoteDBMgr.mServerudn))
                    {
                        finishAction(EasyTransferStateMachine.Action.BROWSE, false, s, mRemoteDBMgr.mTableId);
                        doAction(EasyTransferStateMachine.Action.BROWSE, s, mRemoteDBMgr.mTableId, false);
                        return;
                    } else
                    {
                        finishAction(EasyTransferStateMachine.Action.BROWSE, false, s, -1L);
                        doAction(EasyTransferStateMachine.Action.BROWSE, s, -1L, false);
                        return;
                    }
                }
            } else
            if (mRemoteDBMgr != null)
            {
                finishAction(EasyTransferStateMachine.Action.BROWSE, false, s, -1L);
                return;
            }
            transmittedBegined = true;
        }

        public void OnDBDataTransmittedFinish(String s)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("OnDBDataTransmittedFinish() =").append(s).toString());
            if (!transmittedBegined)
            {
                return;
            }
            if (mTransferQueue == null) goto _L2; else goto _L1
_L1:
            TransferServerNode transferservernode = mTransferQueue.first();
            if (transferservernode == null || !s.equalsIgnoreCase(transferservernode.serverudn)) goto _L4; else goto _L3
_L3:
            finishAction(EasyTransferStateMachine.Action.BROWSE, true, transferservernode.serverudn, transferservernode.tableid);
            doAction(EasyTransferStateMachine.Action.BUILD, transferservernode.serverudn, transferservernode.tableid, false);
_L6:
            transmittedBegined = false;
            return;
_L4:
            if (mRemoteDBMgr != null)
            {
                finishAction(EasyTransferStateMachine.Action.BROWSE, false, null, -1L);
                if (s.equalsIgnoreCase(mRemoteDBMgr.mServerudn))
                {
                    doAction(EasyTransferStateMachine.Action.BROWSE, null, mRemoteDBMgr.mTableId, false);
                } else
                {
                    doAction(EasyTransferStateMachine.Action.BROWSE, s, -1L, false);
                }
            }
            continue; /* Loop/switch isn't completed */
_L2:
            if (mRemoteDBMgr != null)
            {
                finishAction(EasyTransferStateMachine.Action.BROWSE, false, null, -1L);
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public void OnDBDataUnMounted(String s)
        {
        }

        public void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
        {
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
                transmittedBegined = false;
            }
    };
    boolean mFinished;
    private final DriverHandler mHandler = new DriverHandler();
    boolean mHasStart;
    IOnEasyTransferDriverListener mIdleListener;
    private boolean mIsMounted;
    private boolean mIsWifiConnection;
    EasyTransferList mList;
    private final EasyTransferList.IOnTransferListListener mListListener = new EasyTransferList.IOnTransferListListener() {

        final EasyTransferDriver this$0;

        private int getDaysBetween(Calendar calendar, Calendar calendar1)
        {
            if (calendar.get(1) != calendar1.get(1))
            {
                return -1;
            } else
            {
                return calendar.get(6) - calendar1.get(6);
            }
        }

        private int queryUpDownloadState(String s, String s1)
        {
            String as[] = {
                "state"
            };
            String as1[] = {
                s, s1
            };
            int i = -1;
            mCursorLock.readLock().lock();
            Cursor cursor = mUpDownloadDBMgr.queryDownload(as, "uri=? AND dms_uuid=?", as1, null);
            boolean flag = false;
            if (cursor != null)
            {
                flag = cursor.moveToFirst();
                if (flag)
                {
                    i = cursor.getInt(cursor.getColumnIndex("state"));
                }
                cursor.close();
            }
            mCursorLock.readLock().unlock();
            if (flag)
            {
                return i;
            } else
            {
                return 0;
            }
        }

        public boolean onBuildList(List list, EasyTransferList.Options options, String s, long l)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("onBuildList() tableid =").append(l).toString());
            if (mFinished || EasyTransferDriver.this == null)
            {
                options.cancelled = true;
                finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
                return false;
            }
            synchronized (EasyTransferDriver.this)
            {
                if (mIsMounted || !checkDestinationToSDcard())
                {
                    break MISSING_BLOCK_LABEL_133;
                }
                Log.w("EasyTransferDriver", "destination sdcard unmounted, break building.");
                options.cancelled = true;
                finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
            }
            return false;
            exception;
            easytransferdriver;
            JVM INSTR monitorexit ;
            throw exception;
            TransferServerNode transferservernode;
            mApp.getApplicationContext();
            transferservernode = mTransferQueue.first();
            if (transferservernode == null)
            {
                break MISSING_BLOCK_LABEL_172;
            }
            if (transferservernode.tableid == l)
            {
                break MISSING_BLOCK_LABEL_221;
            }
            Log.w("EasyTransferDriver", (new StringBuilder()).append("Can not find buiding easy-transfer on queue:").append(s).toString());
            options.cancelled = true;
            finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
            easytransferdriver;
            JVM INSTR monitorexit ;
            return false;
            Cursor cursor;
            Log.w("EasyTransferDriver", (new StringBuilder()).append("recordDay =").append(transferservernode.recordDay).toString());
            cursor = mRemoteDBMgr.queryVideo();
            easytransferdriver;
            JVM INSTR monitorexit ;
            long l1;
            Calendar calendar;
            Calendar calendar1;
            if (cursor == null)
            {
                options.cancelled = true;
                finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
                return false;
            }
            l1 = System.currentTimeMillis();
            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar1 = Calendar.getInstance();
_L4:
            if (!cursor.moveToNext() || options.cancelled) goto _L2; else goto _L1
_L1:
            int i;
            String s1;
            i = -1;
            s1 = cursor.getString(0);
            if (s1 == null || s1.length() < 1) goto _L4; else goto _L3
_L3:
            if (transferservernode.recordDay != 0)
            {
                calendar1.setTime(DateConvert.stringToDate(s1));
                i = getDaysBetween(calendar, calendar1);
                Log.w("EasyTransferDriver", (new StringBuilder()).append("between days =").append(i).toString());
            }
            if (transferservernode.recordDay == 0) goto _L6; else goto _L5
_L5:
            if (i < 0) goto _L4; else goto _L7
_L7:
            if (i > transferservernode.recordDay) goto _L4; else goto _L6
_L6:
            ParseException parseexception;
            TransferItem transferitem;
            com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask;
            transferitem = new TransferItem();
            transferitem.task = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
            downloadtask = transferitem.task;
            downloadtask.dms_uuid = s;
            downloadtask.mediaClass = 1L;
            downloadtask.mediaId = cursor.getLong(1);
            downloadtask.title = cursor.getString(2);
            downloadtask.uri = cursor.getString(3);
            downloadtask.fileSize = cursor.getInt(4);
            if (downloadtask.dms_uuid != null)
            {
                break MISSING_BLOCK_LABEL_589;
            }
            Log.e("EasyTransferDriver", "uuid is null");
              goto _L4
label0:
            {
                if (downloadtask.uri != null)
                {
                    break label0;
                }
                Log.e("EasyTransferDriver", "uri is null!");
            }
              goto _L4
            if (!mFinished && EasyTransferDriver.this != null) goto _L9; else goto _L8
_L8:
            options.cancelled = true;
_L2:
            Log.i("EasyTransferDriver", (new StringBuilder()).append("building list count:").append(list.size()).append(", time:").append(System.currentTimeMillis() - l1).toString());
            cursor.close();
            if (options.cancelled || list.size() < 1)
            {
                options.cancelled = true;
                finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
            }
            EasyTransferDriver easytransferdriver1;
            Exception exception1;
            int j;
            int k;
            return !options.cancelled;
_L9:
            easytransferdriver1 = EasyTransferDriver.this;
            easytransferdriver1;
            JVM INSTR monitorenter ;
            if (transferservernode.serverstate != 5)
            {
                break MISSING_BLOCK_LABEL_788;
            }
            k = queryUpDownloadState(downloadtask.uri, downloadtask.dms_uuid);
            if (k == 0 || k == 4)
            {
                break MISSING_BLOCK_LABEL_788;
            }
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L4
            exception1;
            easytransferdriver1;
            JVM INSTR monitorexit ;
            try
            {
                throw exception1;
            }
            // Misplaced declaration of an exception variable
            catch (ParseException parseexception)
            {
                Log.e("EasyTransferDriver", (new StringBuilder()).append("can not parse date =").append(s1).toString());
                parseexception.printStackTrace();
                options.cancelled = true;
            }
              goto _L4
            if (mIsMounted || !checkDestinationToSDcard()) goto _L11; else goto _L10
_L10:
            Log.w("EasyTransferDriver", "destination sdcard unmounted, break building.");
            options.cancelled = true;
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L2
_L11:
            transferservernode = mTransferQueue.first();
            if (transferservernode == null) goto _L13; else goto _L12
_L12:
            if (transferservernode.tableid == l) goto _L14; else goto _L13
_L13:
            Log.w("EasyTransferDriver", (new StringBuilder()).append("Has removed easy-transfer on queue:").append(s).toString());
            options.cancelled = true;
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L2
_L14:
            downloadtask.protocolInfo = mRemoteDBMgr.getProtocolInfo(Uri.parse(downloadtask.uri), downloadtask.mediaId);
            downloadtask.item_uuid = mRemoteDBMgr.getRemoteItemUUID(downloadtask.mediaId);
            if (downloadtask.item_uuid != null) goto _L16; else goto _L15
_L15:
            Log.e("EasyTransferDriver", "item_uuid is null");
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L4
_L16:
            if (mRemoteDBMgr.getEasyTransferFlag(downloadtask.dms_uuid, downloadtask.item_uuid) != 0) goto _L18; else goto _L17
_L17:
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L4
_L18:
            j = mRemoteDBMgr.getCopyCount(downloadtask.dms_uuid, downloadtask.item_uuid);
            if (j <= 0) goto _L20; else goto _L19
_L19:
            if (j != 1) goto _L22; else goto _L21
_L21:
            if (transferservernode.enableAllow) goto _L22; else goto _L20
_L20:
            easytransferdriver1;
            JVM INSTR monitorexit ;
              goto _L4
_L22:
            easytransferdriver1;
            JVM INSTR monitorexit ;
            list.add(transferitem);
              goto _L4
        }

        public void onDestroyList(List list, String s, long l)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("onDestroyList(), tableid =").append(l).toString());
            if (EasyTransferDriver.this == null)
            {
                return;
            }
            synchronized (EasyTransferDriver.this)
            {
                if (mIdleListener != null)
                {
                    mIdleListener.onCancel(list, s, l);
                }
                finishAction(EasyTransferStateMachine.Action.CANCEL, true, s, l);
                doAction(EasyTransferStateMachine.Action.BROWSE, s, l, false);
            }
            return;
            exception;
            easytransferdriver;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean onTransferList(List list, EasyTransferList.Options options, String s, long l)
        {
            Log.w("EasyTransferDriver", (new StringBuilder()).append("onTransferList(), tableid = ").append(l).toString());
            if (EasyTransferDriver.this == null)
            {
                options.cancelled = true;
                finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
                return false;
            }
            EasyTransferDriver easytransferdriver = EasyTransferDriver.this;
            easytransferdriver;
            JVM INSTR monitorenter ;
            TransferServerNode transferservernode;
            mApp.getApplicationContext();
            transferservernode = mTransferQueue.first();
            if (transferservernode == null)
            {
                break MISSING_BLOCK_LABEL_103;
            }
            if (transferservernode.tableid == l)
            {
                break MISSING_BLOCK_LABEL_160;
            }
            Log.w("EasyTransferDriver", (new StringBuilder()).append("Can not find buiding easy-transfer on queue:").append(s).toString());
            options.cancelled = true;
            finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
            easytransferdriver;
            JVM INSTR monitorexit ;
            return false;
            Exception exception;
            exception;
            easytransferdriver;
            JVM INSTR monitorexit ;
            throw exception;
            if (mIsMounted || !checkDestinationToSDcard())
            {
                break MISSING_BLOCK_LABEL_212;
            }
            Log.w("EasyTransferDriver", "destination sdcard unmounted, break building.");
            options.cancelled = true;
            finishAction(EasyTransferStateMachine.Action.BUILD, false, s, l);
            easytransferdriver;
            JVM INSTR monitorexit ;
            return false;
            boolean flag;
            finishAction(EasyTransferStateMachine.Action.BUILD, true, s, l);
            doAction(EasyTransferStateMachine.Action.COMPLETE, s, l, false);
            transferservernode.total = list.size();
            flag = options.cancelled;
            boolean flag1;
            flag1 = false;
            if (flag)
            {
                break MISSING_BLOCK_LABEL_341;
            }
            IOnEasyTransferDriverListener ioneasytransferdriverlistener = mIdleListener;
            flag1 = false;
            if (ioneasytransferdriverlistener == null)
            {
                break MISSING_BLOCK_LABEL_341;
            }
            flag1 = mIdleListener.onDownload(list, s, l);
            if (flag1)
            {
                break MISSING_BLOCK_LABEL_341;
            }
            Log.w("EasyTransferDriver", (new StringBuilder()).append("Can not download tasks:").append(list.size()).toString());
            options.cancelled = true;
            TransferServerNode transferservernode1 = mTransferQueue.first();
            if (transferservernode1 == null)
            {
                break MISSING_BLOCK_LABEL_369;
            }
            if (transferservernode1.tableid == l)
            {
                break MISSING_BLOCK_LABEL_374;
            }
            options.cancelled = true;
            if (options.cancelled)
            {
                break MISSING_BLOCK_LABEL_400;
            }
            transferservernode1.addtodownloadqueue = flag1;
_L1:
            easytransferdriver;
            JVM INSTR monitorexit ;
            return !options.cancelled;
            finishAction(EasyTransferStateMachine.Action.COMPLETE, false, s, l);
              goto _L1
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
    };
    ArrayList mListeners;
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final EasyTransferDriver this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            while (networkstateinfo.networkInfo == null || networkstateinfo.networkInfo.getType() != 1 || mFinished || mHandler == null) 
            {
                return;
            }
            if (networkstateinfo.networkInfo.isConnected())
            {
                Log.i("EasyTransferDriver", "OnConnectivityChanged connect");
                mIsWifiConnection = true;
                mHandler.removeMessages(3);
                mHandler.sendEmptyMessage(3);
                return;
            } else
            {
                Log.i("EasyTransferDriver", "OnConnectivityChanged disconnect");
                mIsWifiConnection = false;
                mHandler.removeMessages(3);
                mHandler.sendEmptyMessage(3);
                return;
            }
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    EasyTransferStateMachine.IOnActionListener mOnActionListener;
    EasyTransferDBMgr mRemoteDBMgr;
    private final BroadcastReceiver mSDcardListener = new BroadcastReceiver() {

        final EasyTransferDriver this$0;

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if (!mFinished && mHandler != null)
            {
                if (s.equals("android.intent.action.MEDIA_MOUNTED"))
                {
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("SD card mounted =").append(s).toString());
                    mIsMounted = true;
                    mHandler.removeMessages(2);
                    mHandler.sendEmptyMessage(2);
                    return;
                }
                if (s.equals("android.intent.action.MEDIA_UNMOUNTED") || s.equals("android.intent.action.MEDIA_BAD_REMOVAL") || s.equals("android.intent.action.MEDIA_EJECT"))
                {
                    Log.i("EasyTransferDriver", (new StringBuilder()).append("SD card unmounted =").append(s).toString());
                    mIsMounted = false;
                    mHandler.removeMessages(2);
                    mHandler.sendEmptyMessage(2);
                    return;
                }
            }
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
    };
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final EasyTransferDriver this$0;

        public void OnDestroyObject(String s, int i)
        {
        }

        public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordTasks dataonrecordtasks, int i)
        {
        }

        public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordSchedule dataonrecordschedule, int i)
        {
        }

        public void OnDigaDeleteRecordSchedule(String s, int i)
        {
        }

        public void OnDigaDisableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaEnableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
        {
        }

        public void onGetSearchCapabilities(String s, String s1, int i)
        {
        }

        public void onGetSortCapabilities(String s, String s1, int i)
        {
        }

        public void onServerAdded(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            while (mFinished || mHandler == null || mediaserverdesc.m_strUuid == null || mTransferQueue.find(mediaserverdesc.m_strUuid, -1L) == null) 
            {
                return;
            }
            Log.i("EasyTransferDriver", (new StringBuilder()).append("onServerRemoved:").append(mediaserverdesc.m_strUuid).toString());
            mHandler.removeMessages(4);
            Message message = mHandler.obtainMessage(4, mediaserverdesc.m_strUuid);
            mHandler.sendMessage(message);
        }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
    };
    private EasyTransferStateMachine mStateMachine;
    EasyTransferTimer mTimer;
    EasyTransferTimer.IOnEasyTransferTimerListener mTimerListener;
    TransferQueue mTransferQueue;
    UpDownloadDBMgr mUpDownloadDBMgr;

    public EasyTransferDriver()
    {
        mFinished = false;
        mHasStart = false;
        mApp = null;
        mUpDownloadDBMgr = null;
        mCursorLock = null;
        mTimer = null;
        mRemoteDBMgr = null;
        mList = null;
        mTransferQueue = null;
        mListeners = new ArrayList();
        mIdleListener = null;
        mNetworkTool = null;
        mIsWifiConnection = true;
        mIsMounted = true;
        mStateMachine = null;
        mOnActionListener = new EasyTransferStateMachine.IOnActionListener() {

            final EasyTransferDriver this$0;

            public boolean onDoAction(EasyTransferStateMachine.Action action, Object obj)
            {
                StateMachineData statemachinedata;
                int i;
                boolean flag;
                if (mFinished || EasyTransferDriver.this == null)
                {
                    return false;
                }
                statemachinedata = (StateMachineData)obj;
                static class _cls10
                {

                    static final int $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[];

                    static 
                    {
                        $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action = new int[EasyTransferStateMachine.Action.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[EasyTransferStateMachine.Action.BROWSE.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[EasyTransferStateMachine.Action.BUILD.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[EasyTransferStateMachine.Action.COMPLETE.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[EasyTransferStateMachine.Action.CANCEL.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[EasyTransferStateMachine.Action.DELETE.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror4)
                        {
                            return;
                        }
                    }
                }

                i = _cls10..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()];
                flag = false;
                i;
                JVM INSTR tableswitch 1 5: default 72
            //                           1 75
            //                           2 87
            //                           3 110
            //                           4 116
            //                           5 223;
                   goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
                return flag;
_L2:
                flag = nextEasyTransfer();
                continue; /* Loop/switch isn't completed */
_L3:
                flag = mList.build(statemachinedata.serverudn, statemachinedata.tableid);
                continue; /* Loop/switch isn't completed */
_L4:
                flag = true;
                continue; /* Loop/switch isn't completed */
_L5:
                EasyTransferTimer easytransfertimer1 = mTimer;
                flag = false;
                if (easytransfertimer1 != null)
                {
                    String s1 = statemachinedata.serverudn;
                    flag = false;
                    if (s1 != null)
                    {
                        mTransferQueue.remove(null, statemachinedata.tableid);
                        mTimer.cancel(statemachinedata.serverudn, statemachinedata.tableid);
                        stopDBTransmit(statemachinedata.serverudn, statemachinedata.tableid, true);
                        mList.cancel(statemachinedata.serverudn, statemachinedata.tableid);
                        flag = true;
                    }
                }
                continue; /* Loop/switch isn't completed */
_L6:
                EasyTransferTimer easytransfertimer = mTimer;
                flag = false;
                if (easytransfertimer != null)
                {
                    String s = statemachinedata.serverudn;
                    flag = false;
                    if (s != null)
                    {
                        mTransferQueue.remove(null, statemachinedata.tableid);
                        mTimer.delete(statemachinedata.serverudn, statemachinedata.tableid);
                        stopDBTransmit(statemachinedata.serverudn, statemachinedata.tableid, true);
                        mList.cancel(statemachinedata.serverudn, statemachinedata.tableid);
                        flag = true;
                    }
                }
                if (true) goto _L1; else goto _L7
_L7:
            }

            public void onDoActionResult(EasyTransferStateMachine.Action action, EasyTransferStateMachine.State state, EasyTransferStateMachine.State state1, Object obj)
            {
                if (!mFinished && EasyTransferDriver.this != null) goto _L2; else goto _L1
_L1:
                return;
_L2:
                StateMachineData statemachinedata = (StateMachineData)obj;
                switch (_cls10..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()])
                {
                default:
                    return;

                case 2: // '\002'
                case 3: // '\003'
                    break;

                case 1: // '\001'
                    notifyEasyTransferFinish(statemachinedata.serverudn, statemachinedata.tableid);
                    return;

                case 4: // '\004'
                    if (state == EasyTransferStateMachine.State.COMPLETED || state == EasyTransferStateMachine.State.FAULT)
                    {
                        finishAction(EasyTransferStateMachine.Action.CANCEL, true, statemachinedata.serverudn, statemachinedata.tableid);
                        return;
                    }
                    if (state1 == EasyTransferStateMachine.State.CANCELLING)
                    {
                        updateState(statemachinedata.tableid, 4L);
                        return;
                    }
                    break;

                case 5: // '\005'
                    if (state == EasyTransferStateMachine.State.COMPLETED || state == EasyTransferStateMachine.State.FAULT)
                    {
                        finishAction(EasyTransferStateMachine.Action.DELETE, true, statemachinedata.serverudn, statemachinedata.tableid);
                        return;
                    }
                    continue; /* Loop/switch isn't completed */
                }
                if (true) goto _L1; else goto _L3
_L3:
                if (state1 != EasyTransferStateMachine.State.DELETING) goto _L1; else goto _L4
_L4:
            }

            public void onFinishActionResult(EasyTransferStateMachine.Action action, EasyTransferStateMachine.State state, EasyTransferStateMachine.State state1, Object obj)
            {
                if (!mFinished && EasyTransferDriver.this != null) goto _L2; else goto _L1
_L1:
                return;
_L2:
                StateMachineData statemachinedata = (StateMachineData)obj;
                switch (_cls10..SwitchMap.com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferStateMachine.Action[action.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    if (state1 != EasyTransferStateMachine.State.FAULT)
                    {
                        stopDBTransmit(statemachinedata.serverudn, statemachinedata.tableid, false);
                        return;
                    } else
                    {
                        stopDBTransmit(statemachinedata.serverudn, statemachinedata.tableid, true);
                        mTransferQueue.remove(statemachinedata.serverudn, statemachinedata.tableid);
                        updateState(statemachinedata.tableid, 1L);
                        TransferServerNode transferservernode4 = mTransferQueue.find(null, statemachinedata.tableid);
                        endNotification(mApp.getApplicationContext(), transferservernode4);
                        return;
                    }

                case 2: // '\002'
                    if (state1 == EasyTransferStateMachine.State.FAULT)
                    {
                        mTransferQueue.remove(statemachinedata.serverudn, statemachinedata.tableid);
                        mList.clear();
                        updateState(statemachinedata.tableid, 1L);
                        TransferServerNode transferservernode3 = mTransferQueue.find(null, statemachinedata.tableid);
                        endNotification(mApp.getApplicationContext(), transferservernode3);
                        return;
                    }
                    break;

                case 3: // '\003'
                    updateState(statemachinedata.tableid, 1L);
                    TransferServerNode transferservernode2 = mTransferQueue.find(null, statemachinedata.tableid);
                    endNotification(mApp.getApplicationContext(), transferservernode2);
                    if (state1 == EasyTransferStateMachine.State.COMPLETED)
                    {
                        updateState(statemachinedata.tableid, 1L);
                        mTransferQueue.remove(statemachinedata.serverudn, statemachinedata.tableid);
                        mList.clear();
                        if (transferservernode2 != null && transferservernode2.total != transferservernode2.succuss && transferservernode2.serverstate != 3)
                        {
                            Log.i("EasyTransferDriver", (new StringBuilder()).append("retry timer =").append(statemachinedata.tableid).toString());
                            mTimer.retryTimer(statemachinedata.serverudn, statemachinedata.tableid);
                            return;
                        }
                    }
                    break;

                case 4: // '\004'
                    mTransferQueue.remove(statemachinedata.serverudn, statemachinedata.tableid);
                    mList.clear();
                    updateState(statemachinedata.tableid, 1L);
                    TransferServerNode transferservernode1 = mTransferQueue.find(null, statemachinedata.tableid);
                    endNotification(mApp.getApplicationContext(), transferservernode1);
                    return;

                case 5: // '\005'
                    mTransferQueue.remove(statemachinedata.serverudn, statemachinedata.tableid);
                    mList.clear();
                    DeleteDB(statemachinedata.tableid);
                    TransferServerNode transferservernode = mTransferQueue.find(null, statemachinedata.tableid);
                    endNotification(mApp.getApplicationContext(), transferservernode);
                    return;
                }
                if (true) goto _L1; else goto _L3
_L3:
            }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
        };
        mTimerListener = new EasyTransferTimer.IOnEasyTransferTimerListener() {

            final EasyTransferDriver this$0;

            public void onTimer(final List list, int i, String s, long l)
            {
                Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() id:").append(l).append(", state:").append(i).toString());
                if ((i == 2 || i == 3) && mTimer != null)
                {
                    mTimer.startTimer();
                }
                if (mHandler == null || list.size() < 1)
                {
                    return;
                } else
                {
                    mHandler.post(i. new Runnable() {

                        final _cls4 this$1;
                        final List val$list;
                        final int val$serverState;

                        public void run()
                        {
                            if (mFinished || _fld0 == null)
                            {
                                return;
                            }
                            EasyTransferDriver easytransferdriver = _fld0;
                            easytransferdriver;
                            JVM INSTR monitorenter ;
                            TransferServerNode transferservernode;
                            Iterator iterator;
                            Log.i("EasyTransferDriver", (new StringBuilder()).append("before queue size =").append(mTransferQueue.getCount()).toString());
                            transferservernode = mTransferQueue.first();
                            iterator = list.iterator();
_L9:
                            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
                            String s;
                            long l;
                            EasyTransferTimer.Timer timer = (EasyTransferTimer.Timer)iterator.next();
                            s = timer.serverudn;
                            l = timer.index;
                            boolean flag = true;
                            TransferServerNode transferservernode1 = mTransferQueue.find(s, l);
                            if (transferservernode1 == null) goto _L4; else goto _L3
_L3:
                            Log.w("EasyTransferDriver", (new StringBuilder()).append("onTimer() find state:").append(transferservernode1.serverstate).toString());
                            if (transferservernode1.serverstate != 2 && transferservernode1.serverstate != 3) goto _L6; else goto _L5
_L5:
                            int i;
                            Log.i("EasyTransferDriver", (new StringBuilder()).append("auto/manual easy-transfer had waited, ignore :").append(l).toString());
                            i = serverState;
                            flag = false;
                            if (i != 5) goto _L4; else goto _L7
_L7:
                            Log.w("EasyTransferDriver", (new StringBuilder()).append("why retry timer comes on?! ").append(l).toString());
_L4:
                            if (!flag)
                            {
                                continue; /* Loop/switch isn't completed */
                            }
                            mTransferQueue.add(s, l, serverState, false);
                            continue; /* Loop/switch isn't completed */
                            Exception exception;
                            exception;
                            easytransferdriver;
                            JVM INSTR monitorexit ;
                            throw exception;
_L6:
                            if (transferservernode1.serverstate != 5)
                            {
                                continue; /* Loop/switch isn't completed */
                            }
                            if (serverState != 2 && transferservernode1.serverstate != 3)
                            {
                                break MISSING_BLOCK_LABEL_398;
                            }
                            if (transferservernode == null)
                            {
                                continue; /* Loop/switch isn't completed */
                            }
                            if (transferservernode.tableid != l)
                            {
                                Log.i("EasyTransferDriver", (new StringBuilder()).append("retry waited, remove :").append(l).toString());
                                mTransferQueue.remove(s, l);
                            }
                            continue; /* Loop/switch isn't completed */
                            Log.w("EasyTransferDriver", (new StringBuilder()).append("why two retry timer exist?! ").append(l).toString());
                            flag = false;
                            if (true) goto _L4; else goto _L2
_L2:
                            Log.i("EasyTransferDriver", (new StringBuilder()).append("after queue size =").append(mTransferQueue.getCount()).toString());
                            if (mIdleListener != null)
                            {
                                Log.i("EasyTransferDriver", (new StringBuilder()).append("is idle =").append(mIdleListener.onIsUpDownloadIdle()).toString());
                            }
                            if (mIdleListener == null || !mIdleListener.onIsUpDownloadIdle() || transferservernode != null)
                            {
                                break MISSING_BLOCK_LABEL_573;
                            }
                            doAction(EasyTransferStateMachine.Action.BROWSE, null, -1L, false);
                            easytransferdriver;
                            JVM INSTR monitorexit ;
                            return;
                            if (true) goto _L9; else goto _L8
_L8:
                        }

            
            {
                this$1 = final__pcls4;
                list = list1;
                serverState = I.this;
                super();
            }
                    });
                    return;
                }
            }

            
            {
                this$0 = EasyTransferDriver.this;
                super();
            }
        };
        mNotificationManager = null;
        mNotification = null;
    }

    private boolean DeleteDB(long l)
    {
        String as[] = new String[1];
        as[0] = Long.toString(l);
        return del_impl("_id=?", as);
    }

    private boolean DeleteDB(String s)
    {
        return del_impl("dms_uuid=?", new String[] {
            s
        });
    }

    private void addToTimer()
    {
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(EasyTransferTable.PROJECTION_ARRAY, null, null, null);
        if (cursor != null)
        {
            EasyTransferTimer.Request request;
            for (; cursor.moveToNext(); mTimer.register(request))
            {
                request = new EasyTransferTimer.Request();
                request.index = cursor.getInt(cursor.getColumnIndex("_id"));
                request.serverudn = cursor.getString(cursor.getColumnIndex("dms_uuid"));
                request.startHour = cursor.getInt(cursor.getColumnIndex("start_hour"));
                request.startMinute = cursor.getInt(cursor.getColumnIndex("start_minute"));
                request.retryCount = 5;
                request.retryPeriod = 0x1d4c0;
            }

            cursor.close();
        }
        mCursorLock.readLock().unlock();
    }

    private boolean checkDestinationToSDcard()
    {
        return Settings.instance().getDefaultDownloadDestination().equalsIgnoreCase(mApp.getResources().getStringArray(0x7f060007)[1]);
    }

    private boolean del_impl(String s, String as[])
    {
        mCursorLock.writeLock().lock();
        int i = mUpDownloadDBMgr.deleteEasyTransfer(s, as);
        mCursorLock.writeLock().unlock();
        return i > 0;
    }

    private void doAction(EasyTransferStateMachine.Action action, String s, long l, boolean flag)
    {
        if (mStateMachine != null)
        {
            StateMachineData statemachinedata = new StateMachineData(s, l, flag);
            mStateMachine.doAction(action, statemachinedata);
        }
    }

    private void endNotification(Context context, TransferServerNode transferservernode)
    {
        Log.d("EasyTransferDriver", "endNotification");
        if (mNotification == null || mNotificationManager == null)
        {
            return;
        }
        if (context != null && transferservernode != null)
        {
            mNotification.flags = 16;
            mNotification.contentView = null;
            PendingIntent pendingintent = PendingIntent.getActivity(context, 0, new Intent(), 0x8000000);
            String s = transferservernode.servername;
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(transferservernode.succuss);
            aobj[1] = Integer.valueOf(transferservernode.total - transferservernode.succuss);
            String s1 = context.getString(0x7f0b00ec, aobj);
            mNotification.icon = 0x7f02025f;
            mNotification.tickerText = s;
            mNotification.setLatestEventInfo(context, s, s1, pendingintent);
            mNotification.when = System.currentTimeMillis();
            mNotificationManager.notify(1, mNotification);
        } else
        {
            mNotificationManager.cancel(1);
        }
        mNotification = null;
    }

    private void finishAction(EasyTransferStateMachine.Action action, boolean flag, String s, long l)
    {
        if (mStateMachine != null)
        {
            StateMachineData statemachinedata = new StateMachineData(s, l, false);
            mStateMachine.finishAction(action, flag, statemachinedata);
        }
    }

    private int getDBCount()
    {
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(null, null, null, null);
        int i = 0;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            i = 0;
            if (flag)
            {
                i = cursor.getCount();
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        return i;
    }

    private IEasyTransferEngine.Result getEasyTransfer_impl(String s, long l)
    {
        String as[] = new String[2];
        as[0] = Long.toString(l);
        as[1] = s;
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(EasyTransferTable.PROJECTION_ARRAY, "_id=? AND dms_uuid=?", as, null);
        IEasyTransferEngine.Result result = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            result = null;
            if (flag)
            {
                result = new IEasyTransferEngine.Result();
                result.request = new IEasyTransferEngine.Request();
                int i = cursor.getColumnIndex("dms_name");
                result.request.servername = cursor.getString(i);
                result.request.serverudn = s;
                int j = cursor.getColumnIndex("record_day");
                result.request.recordDay = cursor.getInt(j);
                int k = cursor.getColumnIndex("start_hour");
                result.request.startHour = cursor.getInt(k);
                int i1 = cursor.getColumnIndex("start_minute");
                result.request.startMinute = cursor.getInt(i1);
                result.serverState = cursor.getInt(cursor.getColumnIndex("server_state"));
                int j1 = cursor.getColumnIndex("allow_move");
                IEasyTransferEngine.Request request = result.request;
                boolean flag1;
                int k1;
                IEasyTransferEngine.Request request1;
                int l1;
                boolean flag2;
                if (cursor.getInt(j1) == 0)
                {
                    flag1 = false;
                } else
                {
                    flag1 = true;
                }
                request.enableAllow = flag1;
                k1 = cursor.getColumnIndex("enable_plugin");
                request1 = result.request;
                l1 = cursor.getInt(k1);
                flag2 = false;
                if (l1 != 0)
                {
                    flag2 = true;
                }
                request1.enablePlugIn = flag2;
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        return result;
    }

    private boolean getServerInfo(TransferServerNode transferservernode, long l)
    {
        boolean flag = true;
        String as[] = new String[4];
        as[0] = "dms_name";
        as[flag] = "record_day";
        as[2] = "allow_move";
        as[3] = "enable_plugin";
        String as1[] = new String[flag];
        as1[0] = Long.toString(l);
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, "_id=?", as1, null);
        boolean flag1 = false;
        if (cursor != null)
        {
            flag1 = cursor.moveToFirst();
            if (flag1)
            {
                transferservernode.servername = cursor.getString(cursor.getColumnIndex("dms_name"));
                transferservernode.recordDay = cursor.getInt(cursor.getColumnIndex("record_day"));
                boolean flag2;
                if (cursor.getInt(cursor.getColumnIndex("allow_move")) == flag)
                {
                    flag2 = flag;
                } else
                {
                    flag2 = false;
                }
                transferservernode.enableAllow = flag2;
                if (cursor.getInt(cursor.getColumnIndex("enable_plugin")) != flag)
                {
                    flag = false;
                }
                transferservernode.enablePlugIn = flag;
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        return flag1;
    }

    private String getServerName(long l)
    {
        if (mUpDownloadDBMgr != null)
        {
            return queryDBServer(l);
        } else
        {
            return null;
        }
    }

    private String getServerudn(long l)
    {
        if (mUpDownloadDBMgr != null)
        {
            return queryDBudn(l);
        } else
        {
            return null;
        }
    }

    private long getTableid_impl(int i)
    {
        String as[] = {
            "_id"
        };
        long l = -1L;
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, null, null, null);
        if (cursor != null)
        {
            cursor.moveToPosition(i);
            l = cursor.getInt(0);
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        return l;
    }

    private long getTableid_impl(String s)
    {
        if (mUpDownloadDBMgr != null)
        {
            return queryDBid(s);
        } else
        {
            return -1L;
        }
    }

    private void initTable()
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("server_state", Integer.valueOf(1));
        mCursorLock.writeLock().lock();
        mUpDownloadDBMgr.updateEasyTransfer(contentvalues, null, null);
        mCursorLock.writeLock().unlock();
    }

    private long insertDB(IEasyTransferEngine.Request request)
    {
        int i = 1;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("dms_name", request.servername);
        contentvalues.put("dms_uuid", request.serverudn);
        contentvalues.put("server_state", Integer.valueOf(i));
        contentvalues.put("start_hour", Integer.valueOf(request.startHour));
        contentvalues.put("start_minute", Integer.valueOf(request.startMinute));
        contentvalues.put("record_day", Integer.valueOf(request.recordDay));
        int j;
        long l;
        if (request.enableAllow)
        {
            j = i;
        } else
        {
            j = 0;
        }
        contentvalues.put("allow_move", Integer.valueOf(j));
        if (!request.enablePlugIn)
        {
            i = 0;
        }
        contentvalues.put("enable_plugin", Integer.valueOf(i));
        mCursorLock.writeLock().lock();
        l = mUpDownloadDBMgr.insertEasyTransfer(contentvalues);
        mCursorLock.writeLock().unlock();
        return l;
    }

    private boolean isEasyTransferRunning()
    {
        return mTransferQueue.getCount() > 0;
    }

    private boolean nextEasyTransfer()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mFinished;
        boolean flag1 = false;
        if (flag) goto _L2; else goto _L1
_L1:
        DriverHandler driverhandler = mHandler;
        flag1 = false;
        if (driverhandler != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L3:
        TransferServerNode transferservernode = mTransferQueue.first();
        flag1 = false;
        if (transferservernode == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        final String serverudn = transferservernode.serverudn;
        final long tableid = transferservernode.tableid;
        updateState(transferservernode.tableid, transferservernode.serverstate);
        mHandler.post(new Runnable() {

            final EasyTransferDriver this$0;
            final String val$serverudn;
            final long val$tableid;

            public void run()
            {
                if (mFinished || EasyTransferDriver.this == null)
                {
                    return;
                }
                synchronized (EasyTransferDriver.this)
                {
                    if (!mRemoteDBMgr.start(serverudn, tableid))
                    {
                        finishAction(EasyTransferStateMachine.Action.BROWSE, false, serverudn, tableid);
                        doAction(EasyTransferStateMachine.Action.BROWSE, serverudn, tableid, false);
                    }
                }
                return;
                exception1;
                easytransferdriver;
                JVM INSTR monitorexit ;
                throw exception1;
            }

            
            {
                this$0 = EasyTransferDriver.this;
                serverudn = s;
                tableid = l;
                super();
            }
        });
        flag1 = true;
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private void notifyEasyTransferFinish(String s, long l)
    {
        for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IEasyTransferEngine.IOnEasyTransferEngineListener)iterator.next()).onEasyTransferFinish(s, l)) { }
    }

    private String queryDBServer(long l)
    {
        String as[] = {
            "dms_name"
        };
        String as1[] = new String[1];
        as1[0] = Long.toString(l);
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, "_id=?", as1, null);
        boolean flag = false;
        String s = null;
        if (cursor != null)
        {
            flag = cursor.moveToFirst();
            s = null;
            if (flag)
            {
                s = cursor.getString(cursor.getColumnIndex("dms_name"));
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        if (flag)
        {
            return s;
        } else
        {
            return null;
        }
    }

    private int queryDBState(long l)
    {
        String as[] = {
            "server_state"
        };
        String as1[] = new String[1];
        as1[0] = Long.toString(l);
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, "_id=?", as1, null);
        int i = 0;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            i = 0;
            if (flag)
            {
                i = cursor.getInt(cursor.getColumnIndex("server_state"));
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        Log.i("EasyTransferDriver", (new StringBuilder()).append("get state =").append(i).toString());
        return i;
    }

    private long queryDBid(String s)
    {
        String as[] = {
            "_id"
        };
        String as1[] = {
            s
        };
        long l = -1L;
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, "dms_uuid=?", as1, null);
        boolean flag = false;
        if (cursor != null)
        {
            flag = cursor.moveToFirst();
            if (flag)
            {
                l = cursor.getInt(cursor.getColumnIndex("_id"));
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        if (flag)
        {
            return l;
        } else
        {
            return -1L;
        }
    }

    private String queryDBudn(long l)
    {
        String as[] = {
            "dms_uuid"
        };
        String as1[] = new String[1];
        as1[0] = Long.toString(l);
        mCursorLock.readLock().lock();
        Cursor cursor = mUpDownloadDBMgr.queryEasyTransfer(as, "_id=?", as1, null);
        boolean flag = false;
        String s = null;
        if (cursor != null)
        {
            flag = cursor.moveToFirst();
            s = null;
            if (flag)
            {
                s = cursor.getString(cursor.getColumnIndex("dms_uuid"));
            }
            cursor.close();
        }
        mCursorLock.readLock().unlock();
        if (flag)
        {
            return s;
        } else
        {
            return null;
        }
    }

    private void setSDcardListener(boolean flag)
    {
        if (flag)
        {
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
            intentfilter.addAction("android.intent.action.MEDIA_EJECT");
            intentfilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentfilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
            intentfilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
            intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentfilter.addDataScheme("file");
            mApp.getApplicationContext().registerReceiver(mSDcardListener, intentfilter);
            return;
        } else
        {
            mApp.getApplicationContext().unregisterReceiver(mSDcardListener);
            return;
        }
    }

    private void setUpNotification(Context context, TransferServerNode transferservernode)
    {
        Log.d("EasyTransferDriver", "setUpNotification");
        if (mNotification == null)
        {
            mNotification = new Notification();
        }
        String s = context.getString(0x7f0b00e9);
        long l = System.currentTimeMillis();
        mNotification.icon = 0x7f020260;
        mNotification.tickerText = s;
        mNotification.when = l;
        mNotification.flags = 2;
        String s1 = transferservernode.servername;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(transferservernode.total);
        String s2 = context.getString(0x7f0b00eb, aobj);
        PendingIntent pendingintent = PendingIntent.getActivity(context, 0, new Intent(), 0x8000000);
        mNotification.setLatestEventInfo(context, s1, s2, pendingintent);
        mNotification.contentIntent = pendingintent;
        mNotificationManager.notify(1, mNotification);
    }

    private boolean stopDBTransmit(final String udn, long l, final boolean forced)
    {
        this;
        JVM INSTR monitorenter ;
        if (mFinished) goto _L2; else goto _L1
_L1:
        DriverHandler driverhandler = mHandler;
        if (driverhandler != null) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        mHandler.post(new Runnable() {

            final EasyTransferDriver this$0;
            final boolean val$forced;
            final String val$udn;

            public void run()
            {
                if (mFinished || EasyTransferDriver.this == null)
                {
                    return;
                }
                synchronized (EasyTransferDriver.this)
                {
                    mRemoteDBMgr.stop(udn, forced);
                }
                return;
                exception1;
                easytransferdriver;
                JVM INSTR monitorexit ;
                throw exception1;
            }

            
            {
                this$0 = EasyTransferDriver.this;
                udn = s;
                forced = flag;
                super();
            }
        });
        flag = true;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private boolean updateDB(long l, IEasyTransferEngine.Request request)
    {
        String as[] = new String[1];
        as[0] = Long.toString(l);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("server_state", Integer.valueOf(1));
        contentvalues.put("start_hour", Integer.valueOf(request.startHour));
        contentvalues.put("start_minute", Integer.valueOf(request.startMinute));
        contentvalues.put("record_day", Integer.valueOf(request.recordDay));
        int i;
        int j;
        if (request.enableAllow)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        contentvalues.put("allow_move", Integer.valueOf(i));
        if (request.enablePlugIn)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        contentvalues.put("enable_plugin", Integer.valueOf(j));
        mCursorLock.writeLock().lock();
        mUpDownloadDBMgr.updateEasyTransfer(contentvalues, "_id=?", as);
        mCursorLock.writeLock().unlock();
        return 0 > 0;
    }

    private void updateNotification(Context context, TransferServerNode transferservernode)
    {
        Log.d("EasyTransferDriver", "updateNotification");
        if (mNotification == null || mNotificationManager == null || transferservernode == null)
        {
            return;
        } else
        {
            String s = transferservernode.servername;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(transferservernode.total);
            String s1 = context.getString(0x7f0b00eb, aobj);
            PendingIntent pendingintent = PendingIntent.getActivity(context, 0, new Intent(), 0x8000000);
            mNotification.setLatestEventInfo(context, s, s1, pendingintent);
            mNotificationManager.notify(1, mNotification);
            return;
        }
    }

    private boolean updateState(long l, long l1)
    {
        String as[] = new String[1];
        as[0] = Long.toString(l);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("server_state", Long.valueOf(l1));
        mCursorLock.writeLock().lock();
        int i = mUpDownloadDBMgr.updateEasyTransfer(contentvalues, "_id=?", as);
        mCursorLock.writeLock().unlock();
        return i > 0;
    }

    public boolean cancelEasyTransfer(long l)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("cancelEasyTransfer() id=").append(l).toString());
        String s = getServerudn(l);
        EasyTransferTimer easytransfertimer = mTimer;
        boolean flag = false;
        if (easytransfertimer != null)
        {
            flag = false;
            if (s != null)
            {
                doAction(EasyTransferStateMachine.Action.CANCEL, s, l, false);
                flag = true;
            }
        }
        return flag;
    }

    public boolean cancelEasyTransfer(String s)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("cancelEasyTransfer() server=").append(s).toString());
        long l = getTableid_impl(s);
        EasyTransferTimer easytransfertimer = mTimer;
        boolean flag = false;
        if (easytransfertimer != null)
        {
            int i = l != -1L;
            flag = false;
            if (i > 0)
            {
                doAction(EasyTransferStateMachine.Action.CANCEL, s, l, false);
                flag = true;
            }
        }
        return flag;
    }

    public void checkDatabase()
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase;
        String as[] = {
            "dms_uuid"
        };
        mCursorLock.readLock().lock();
        cursor = mUpDownloadDBMgr.queryEasyTransfer(as, null, null, null);
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_174;
        }
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        sqlitedatabase.beginTransaction();
        int i;
        for (; cursor.moveToNext(); Log.i("EasyTransferDriver", (new StringBuilder()).append("delete count =").append(i).toString()))
        {
            String s = cursor.getString(cursor.getColumnIndex("dms_uuid"));
            String as1[] = new String[3];
            as1[0] = s;
            as1[1] = Integer.toString(1);
            as1[2] = Integer.toString(2);
            i = mUpDownloadDBMgr.deleteDownload("dms_uuid =? AND (state =? OR state =? ) ", as1);
        }

        break MISSING_BLOCK_LABEL_160;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        cursor.close();
        mCursorLock.readLock().unlock();
        return;
    }

    public boolean deleteEasyTransfer(long l)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("deleteEasyTransfer() id=").append(l).toString());
        String s = getServerudn(l);
        EasyTransferTimer easytransfertimer = mTimer;
        boolean flag = false;
        if (easytransfertimer != null)
        {
            flag = false;
            if (s != null)
            {
                doAction(EasyTransferStateMachine.Action.DELETE, s, l, false);
                flag = true;
            }
        }
        return flag;
    }

    public boolean deleteEasyTransfer(String s)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("deleteEasyTransfer() server=").append(s).toString());
        long l = getTableid_impl(s);
        EasyTransferTimer easytransfertimer = mTimer;
        boolean flag = false;
        if (easytransfertimer != null)
        {
            int i = l != -1L;
            flag = false;
            if (i > 0)
            {
                doAction(EasyTransferStateMachine.Action.DELETE, s, l, false);
                flag = true;
            }
        }
        return flag;
    }

    public boolean executeEasyTransfer(long l)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("executeEasyTransfer() id=").append(l).toString());
        String s = getServerudn(l);
        if (mTimer != null && s != null)
        {
            if (mTransferQueue.getCount() > 0)
            {
                Log.e("EasyTransferDriver", "some server is waiting easy-transfer! can't not execute.");
            } else
            if (mTimer.execute(s, l))
            {
                mTransferQueue.remove(s, l);
                return true;
            }
        }
        return false;
    }

    public boolean executeEasyTransfer(String s)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("executeEasyTransfer() server=").append(s).toString());
        long l = getTableid_impl(s);
        if (mTimer != null && l > -1L)
        {
            if (mTransferQueue.getCount() > 0)
            {
                Log.e("EasyTransferDriver", "some server is waiting easy-transfer! can't not execute");
            } else
            if (mTimer.execute(s, l))
            {
                mTransferQueue.remove(s, l);
                return true;
            }
        }
        return false;
    }

    protected void finalize()
        throws Throwable
    {
        if (!mFinished)
        {
            uninit();
        }
    }

    public int getCount()
    {
        UpDownloadDBMgr updownloaddbmgr = mUpDownloadDBMgr;
        int i = 0;
        if (updownloaddbmgr != null)
        {
            i = getDBCount();
        }
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getCount() =").append(i).toString());
        return i;
    }

    public IEasyTransferEngine.Result getEasyTransfer(long l)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getEasyTransfer() id=").append(l).toString());
        String s = getServerudn(l);
        if (s != null)
        {
            return getEasyTransfer_impl(s, l);
        } else
        {
            return null;
        }
    }

    public IEasyTransferEngine.Result getEasyTransfer(String s)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getEasyTransfer() server=").append(s).toString());
        long l = getTableid_impl(s);
        if (l > -1L)
        {
            return getEasyTransfer_impl(s, l);
        } else
        {
            return null;
        }
    }

    public int getServerState(long l)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getServerState() id=").append(l).toString());
        return queryDBState(l);
    }

    public int getServerState(String s)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getServerState() server=").append(s).toString());
        long l = getTableid_impl(s);
        if (l > -1L)
        {
            return queryDBState(l);
        } else
        {
            return 0;
        }
    }

    public long getTableid(int i)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("getTableid() =").append(i).toString());
        if (mUpDownloadDBMgr != null)
        {
            return getTableid_impl(i);
        } else
        {
            return -1L;
        }
    }

    public boolean init(Application application, UpDownloadDBMgr updownloaddbmgr, IOnEasyTransferDriverListener ioneasytransferdriverlistener, ReadWriteLock readwritelock)
    {
        Log.i("EasyTransferDriver", "init()");
        mApp = application;
        mUpDownloadDBMgr = updownloaddbmgr;
        mIdleListener = ioneasytransferdriverlistener;
        mCursorLock = readwritelock;
        mTimer = new EasyTransferTimer();
        mTimer.init(application);
        mTimer.registerTimerListener(mTimerListener);
        mList = new EasyTransferList();
        mList.init(mListListener);
        mRemoteDBMgr = new EasyTransferDBMgr(application, Looper.myLooper());
        mRemoteDBMgr.registerOnDataUpdateListener(mDBListener);
        mTransferQueue = new TransferQueue();
        mNotificationManager = (NotificationManager)application.getApplicationContext().getSystemService("notification");
        mNotificationManager.cancel(1);
        setSDcardListener(true);
        mNetworkTool = new NetworkTool(mApp.getApplicationContext());
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
        mStateMachine = new EasyTransferStateMachine(mOnActionListener);
        mFinished = false;
        return true;
    }

    public void onCancelAllTask(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = mFinished;
        if (!flag1) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        TransferServerNode transferservernode;
        Log.i("EasyTransferDriver", "cancel all task");
        transferservernode = mTransferQueue.first();
        if (transferservernode == null)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        if (transferservernode.addtodownloadqueue)
        {
            Log.d("EasyTransferDriver", (new StringBuilder()).append("cancel count =").append(transferservernode.total - transferservernode.current).toString());
            cancelEasyTransfer(transferservernode.tableid);
        }
        endNotification(mApp.getApplicationContext(), transferservernode);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void onCancelTask(String s, Uri uri, int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (s != null && uri != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        TransferServerNode transferservernode;
        if (mFinished || queryDBid(s) <= 0L)
        {
            continue; /* Loop/switch isn't completed */
        }
        Log.i("EasyTransferDriver", (new StringBuilder()).append("cancel task =").append(uri.toString()).append(", is going =").append(flag).toString());
        transferservernode = mTransferQueue.first();
        if (transferservernode == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!transferservernode.serverudn.equalsIgnoreCase(s) || flag || i != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        Log.i("EasyTransferDriver", "cancel one server task");
        transferservernode.current = 1 + transferservernode.current;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean onDownloadFinished(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult downloadresult)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        Log.i("EasyTransferDriver", (new StringBuilder()).append("onDownloadFinished id=").append(downloadresult.tableid).append(" ,result=").append(downloadresult.errorcode).toString());
        flag = mFinished;
        boolean flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        TransferServerNode transferservernode = mTransferQueue.first();
        if (transferservernode == null) goto _L4; else goto _L3
_L3:
        if (!transferservernode.serverudn.equalsIgnoreCase(downloadresult.request.dms_uuid)) goto _L4; else goto _L5
_L5:
        int i;
        Log.i("EasyTransferDriver", (new StringBuilder()).append("onDownloadFinished total=").append(transferservernode.total).append(" ,current=").append(transferservernode.current).toString());
        if (downloadresult.errorcode == 819 || downloadresult.errorcode == 817)
        {
            break MISSING_BLOCK_LABEL_328;
        }
        i = downloadresult.errorcode;
        boolean flag3;
        flag3 = false;
        if (i != 911)
        {
            break MISSING_BLOCK_LABEL_201;
        }
        transferservernode.succuss = 1 + transferservernode.succuss;
        updateNotification(mApp.getApplicationContext(), transferservernode);
_L11:
        if (transferservernode.total != transferservernode.current && !flag3) goto _L7; else goto _L6
_L6:
        finishAction(EasyTransferStateMachine.Action.COMPLETE, true, transferservernode.serverudn, transferservernode.tableid);
        doAction(EasyTransferStateMachine.Action.BROWSE, transferservernode.serverudn, transferservernode.tableid, true);
          goto _L7
_L4:
        flag1 = false;
        if (transferservernode == null) goto _L1; else goto _L8
_L8:
        IOnEasyTransferDriverListener ioneasytransferdriverlistener = mIdleListener;
        flag1 = false;
        if (ioneasytransferdriverlistener == null) goto _L1; else goto _L9
_L9:
        boolean flag2 = mIdleListener.onIsUpDownloadIdle();
        flag1 = false;
        if (!flag2) goto _L1; else goto _L10
_L10:
        doAction(EasyTransferStateMachine.Action.BROWSE, null, -1L, false);
        flag1 = true;
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L7:
        flag1 = true;
          goto _L1
        flag3 = true;
          goto _L11
    }

    public boolean onDownloadStarted(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest, long l)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        Log.i("EasyTransferDriver", (new StringBuilder()).append("onDownloadStarted id=").append(l).toString());
        flag = mFinished;
        boolean flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        TransferServerNode transferservernode = mTransferQueue.first();
        flag1 = false;
        if (transferservernode == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag2 = transferservernode.serverudn.equalsIgnoreCase(downloadrequest.dms_uuid);
        flag1 = false;
        if (!flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        transferservernode.current = 1 + transferservernode.current;
        if (mNotification != null) goto _L4; else goto _L3
_L3:
        setUpNotification(mApp.getApplicationContext(), transferservernode);
_L6:
        for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IEasyTransferEngine.IOnEasyTransferEngineListener)iterator.next()).onEasyTransfering(transferservernode.serverudn, transferservernode.tableid, transferservernode.total, transferservernode.current)) { }
        break MISSING_BLOCK_LABEL_198;
        Exception exception;
        exception;
        throw exception;
_L4:
        updateNotification(mApp.getApplicationContext(), transferservernode);
        if (true) goto _L6; else goto _L5
_L5:
        flag1 = true;
        if (true) goto _L1; else goto _L7
_L7:
    }

    public boolean registerEasyTransfer(IEasyTransferEngine.Request request)
    {
        Log.i("EasyTransferDriver", "registerEasyTransfer()");
        long l = insertDB(request);
        if (mTimer != null && l > -1L)
        {
            long l1 = queryDBid(request.serverudn);
            EasyTransferTimer.Request request1 = new EasyTransferTimer.Request();
            request1.index = l1;
            request1.serverudn = request.serverudn;
            request1.startHour = request.startHour;
            request1.startMinute = request.startMinute;
            request1.retryCount = 5;
            request1.retryPeriod = 0x1d4c0;
            return mTimer.register(request1);
        } else
        {
            return false;
        }
    }

    public void registerListener(IEasyTransferEngine.IOnEasyTransferEngineListener ioneasytransferenginelistener)
    {
        Log.i("EasyTransferDriver", "registerListener()");
        if (ioneasytransferenginelistener == null)
        {
            return;
        }
        synchronized (mListeners)
        {
            if (!mListeners.contains(ioneasytransferenginelistener))
            {
                break MISSING_BLOCK_LABEL_42;
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        mListeners.add(ioneasytransferenginelistener);
        arraylist;
        JVM INSTR monitorexit ;
    }

    public void start()
    {
        Log.i("EasyTransferDriver", "start()");
        if (!mFinished && !mHasStart)
        {
            initTable();
            addToTimer();
        }
        if (!mHasStart)
        {
            mTimer.start();
            mHasStart = true;
        }
    }

    public void stop()
    {
        Log.i("EasyTransferDriver", "stop()");
        if (mHasStart)
        {
            mTimer.stop();
            mTransferQueue.recycle();
            mRemoteDBMgr.stop(null, true);
            mList.cancel();
            initTable();
            mHasStart = false;
        }
    }

    public void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        Log.i("EasyTransferDriver", "uninit()");
        mFinished = true;
        mHandler.release();
        setSDcardListener(false);
        mNetworkTool.setOnConnectivityChangeListener(null);
        mNetworkTool = null;
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
        mListeners.clear();
        mStateMachine.reset();
        mStateMachine = null;
        mList.release();
        mList = null;
        mRemoteDBMgr.uninit();
        mRemoteDBMgr = null;
        mTimer.uninit();
        mTimer.unregisterTimerListener();
        mTimer = null;
        mTransferQueue.recycle();
        mTransferQueue = null;
        endNotification(mApp.getApplicationContext(), null);
        mNotification = null;
        mNotificationManager = null;
        mUpDownloadDBMgr = null;
        mIdleListener = null;
        mHasStart = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterListener(IEasyTransferEngine.IOnEasyTransferEngineListener ioneasytransferenginelistener)
    {
        Log.i("EasyTransferDriver", "unregisterListener()");
        synchronized (mListeners)
        {
            mListeners.remove(ioneasytransferenginelistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean updateEasyTransfer(long l, IEasyTransferEngine.Request request)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("updateEasyTransfer() id=").append(l).toString());
        String s = getServerudn(l);
        if (mTimer != null && s != null)
        {
            IEasyTransferEngine.Result result = getEasyTransfer(l);
            updateDB(l, request);
            if (result.request.startHour != request.startHour || result.request.startMinute != request.startMinute)
            {
                mTimer.update(s, l, request.startHour, request.startMinute);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean updateEasyTransfer(String s, IEasyTransferEngine.Request request)
    {
        Log.i("EasyTransferDriver", (new StringBuilder()).append("updateEasyTransfer() server=").append(s).toString());
        long l = getTableid_impl(s);
        if (mTimer != null && l > -1L)
        {
            IEasyTransferEngine.Result result = getEasyTransfer(s);
            updateDB(l, request);
            if (result.request.startHour != request.startHour || result.request.startMinute != request.startMinute)
            {
                mTimer.update(s, l, request.startHour, request.startMinute);
            }
            return true;
        } else
        {
            return false;
        }
    }





/*
    static boolean access$1102(EasyTransferDriver easytransferdriver, boolean flag)
    {
        easytransferdriver.mIsWifiConnection = flag;
        return flag;
    }

*/












/*
    static boolean access$902(EasyTransferDriver easytransferdriver, boolean flag)
    {
        easytransferdriver.mIsMounted = flag;
        return flag;
    }

*/

    // Unreferenced inner class com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferDriver$TransferQueue$1

/* anonymous class */
    class TransferQueue._cls1
        implements Comparator
    {

        final TransferQueue this$1;

        public int compare(TransferServerNode transferservernode, TransferServerNode transferservernode1)
        {
            return !transferservernode.serverudn.equalsIgnoreCase(transferservernode1.serverudn) ? -1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TransferServerNode)obj, (TransferServerNode)obj1);
        }

            
            {
                this$1 = TransferQueue.this;
                super();
            }
    }


    // Unreferenced inner class com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferDriver$TransferQueue$2

/* anonymous class */
    class TransferQueue._cls2
        implements Comparator
    {

        final TransferQueue this$1;

        public int compare(TransferServerNode transferservernode, TransferServerNode transferservernode1)
        {
            return transferservernode.tableid != transferservernode1.tableid ? -1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TransferServerNode)obj, (TransferServerNode)obj1);
        }

            
            {
                this$1 = TransferQueue.this;
                super();
            }
    }

}
