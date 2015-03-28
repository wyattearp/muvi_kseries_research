// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.DownloadPoolDriver;
import com.arcsoft.mediaplus.updownload.DownloadTaskItem;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.mediaplus.updownload.UpDownloadUtils;
import com.arcsoft.mediaplus.updownload.UploadPoolDriver;
import com.arcsoft.mediaplus.updownload.UploadTaskItem;
import com.arcsoft.mediaplus.updownload.db.DownloadedFileDBMgr;
import com.arcsoft.mediaplus.updownload.db.DownloadedFileTable;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.mediaplus.updownload.db.UpDownloadTable;
import com.arcsoft.mediaplus.updownload.dtcp.DTCPDownloadPoolDriver;
import com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;
import com.arcsoft.util.MimeTypeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.util.tool.ToastMgr;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            DownloadQueue

public class UpDownloadEngine
    implements IEasyTransferEngine
{
    public static class DownloadTask
    {

        public static final int PRIORITY_HIGH = 1;
        public static final int PRIORITY_NONE;
        public String dms_uuid;
        public long fileSize;
        public String item_uuid;
        public long mediaClass;
        public long mediaId;
        public int priority;
        public String protocolInfo;
        public long status;
        public String title;
        public String uri;
        public boolean videoOrImage;

        protected Object clone()
        {
            DownloadTask downloadtask = new DownloadTask();
            downloadtask.dms_uuid = dms_uuid;
            downloadtask.fileSize = fileSize;
            downloadtask.item_uuid = item_uuid;
            downloadtask.mediaClass = mediaClass;
            downloadtask.mediaId = mediaId;
            downloadtask.protocolInfo = protocolInfo;
            downloadtask.status = status;
            downloadtask.title = title;
            downloadtask.uri = uri;
            downloadtask.status = status;
            downloadtask.videoOrImage = videoOrImage;
            downloadtask.priority = priority;
            return downloadtask;
        }

        public DownloadTask()
        {
            videoOrImage = false;
            priority = 0;
        }
    }

    public static interface IOnUpDownloadListener
    {

        public abstract void onProgress(String s, String s1, long l, long l1);

        public abstract void onUpDownloadFinish(String s, String s1, Object obj, int i);

        public abstract void onUpDownloadStart(String s, String s1);
    }

    private class ServiceHandler extends Handler
    {

        final UpDownloadEngine this$0;

        public void handleMessage(Message message)
        {
            if (message.what != 0) goto _L2; else goto _L1
_L1:
            ((Runnable)message.obj).run();
_L4:
            return;
_L2:
            if (message.what != 1)
            {
                break MISSING_BLOCK_LABEL_155;
            }
            if (!isRelease())
            {
                String s = Settings.instance().getDefaultDMSUDN();
                mDownloadPoolDriver.abortTask(s);
                mDtcpDownloadPoolDriver.abortTask(s);
                mUploadPoolDriver.abortTask(s);
            }
            ArrayList arraylist2 = mListeners;
            arraylist2;
            JVM INSTR monitorenter ;
            for (Iterator iterator2 = mListeners.iterator(); iterator2.hasNext(); ((IOnUpDownloadListener)iterator2.next()).onUpDownloadFinish(null, null, null, 819)) { }
            break MISSING_BLOCK_LABEL_151;
            Exception exception2;
            exception2;
            arraylist2;
            JVM INSTR monitorexit ;
            throw exception2;
            arraylist2;
            JVM INSTR monitorexit ;
            return;
            if (message.what == 2)
            {
                startServiceThread();
                return;
            }
            if (message.what != 3)
            {
                continue; /* Loop/switch isn't completed */
            }
            stopServiceThread();
            cancel_internal(false, -1, true);
            ArrayList arraylist1 = mListeners;
            arraylist1;
            JVM INSTR monitorenter ;
            for (Iterator iterator1 = mListeners.iterator(); iterator1.hasNext(); ((IOnUpDownloadListener)iterator1.next()).onUpDownloadFinish(null, null, null, 817)) { }
            break MISSING_BLOCK_LABEL_263;
            Exception exception1;
            exception1;
            arraylist1;
            JVM INSTR monitorexit ;
            throw exception1;
            arraylist1;
            JVM INSTR monitorexit ;
            return;
            if (message.what != 4) goto _L4; else goto _L3
_L3:
            stopServiceThread();
            abortAllTask();
            cancel_internal(true, 1, false);
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadFinish(null, null, null, 819)) { }
            break MISSING_BLOCK_LABEL_367;
            Exception exception;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
        }

        void release()
        {
            removeMessages(0);
            removeMessages(1);
            removeMessages(2);
            removeMessages(3);
            removeMessages(4);
        }

        private ServiceHandler()
        {
            this$0 = UpDownloadEngine.this;
            super();
        }

    }

    private class ServiceThread extends Thread
    {

        AtomicBoolean bExit;
        final UpDownloadEngine this$0;

        public void run()
        {
            Log.i("UpDownloadEngine", "ServiceThread run");
            if (!bExit.get()) goto _L2; else goto _L1
_L1:
            mCheckFlag = 0;
            return;
_L2:
            checkDatabaseDms();
            if (!bExit.get())
            {
                if ((1 & mCheckFlag) > 0)
                {
                    checkSdcardVolume();
                    mCheckFlag = -2 & mCheckFlag;
                }
                if (!bExit.get())
                {
                    if ((2 & mCheckFlag) > 0)
                    {
                        checkDatabaseState();
                        mCheckFlag = -3 & mCheckFlag;
                    }
                    if (!bExit.get())
                    {
                        if ((4 & mCheckFlag) > 0)
                        {
                            startFirstUpDownloadTask();
                            mCheckFlag = -5 & mCheckFlag;
                        }
                        bExit.set(true);
                    }
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        private ServiceThread()
        {
            this$0 = UpDownloadEngine.this;
            super();
            bExit = new AtomicBoolean(false);
        }

    }

    private static class TruncateString
    {

        static String truncateUTF8(String s, int i)
        {
            byte abyte0[];
            String s1;
            try
            {
                abyte0 = s.getBytes("UTF-8");
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                unsupportedencodingexception.printStackTrace();
                Log.e("UpDownloadEngine", "truncate title not support utf-8");
                return null;
            }
            if (i < abyte0.length) goto _L2; else goto _L1
_L1:
            Log.i("UpDownloadEngine", "profile title not need truncate");
            s1 = new String(abyte0);
_L4:
            return s1;
_L2:
            Log.i("UpDownloadEngine", "profile title too long, need truncate");
            int j = -1 + abyte0.length;
            do
            {
                s1 = null;
                if (j < 0)
                {
                    continue;
                }
                if (abyte0[j] >= -64 && abyte0[j] <= -4 && j <= i)
                {
                    String s2 = new String(abyte0, 0, j);
                    Log.i("UpDownloadEngine", (new StringBuilder()).append("profile title truncate string =").append(s2).toString());
                    return s2;
                }
                j--;
            } while (true);
            if (true) goto _L4; else goto _L3
_L3:
        }

        private TruncateString()
        {
        }
    }

    private static class UpDownloadToken
    {

        private UpDownloadEngine mEngine;
        private int mUpDownloadToken;

        public void onStart()
        {
            mUpDownloadToken = 1 + mUpDownloadToken;
            mEngine.mEasyTransferDriver.start();
            if (mUpDownloadToken == 1)
            {
                mEngine.resumeAllTask();
            }
        }

        public void onStop()
        {
            mUpDownloadToken = -1 + mUpDownloadToken;
            if (mUpDownloadToken == 0)
            {
                mEngine.abortAllTask();
                mEngine.mNotification = null;
            }
            if (mUpDownloadToken < 0)
            {
                mUpDownloadToken = 0;
            }
        }

        public UpDownloadToken(UpDownloadEngine updownloadengine)
        {
            mEngine = null;
            mUpDownloadToken = 0;
            mEngine = updownloadengine;
        }
    }

    public static class UploadTask
    {

        public String dms_uuid;
        public boolean is3D;
        public long mediaClass;
        public long mediaId;
        public String protocolInfo;
        public String title;
        public String uri;

        public UploadTask()
        {
            is3D = false;
        }
    }


    private static final int CHECK_DATABASE_STATE = 2;
    private static final int CHECK_SDCARD_VOLUME = 1;
    private static final int CHECK_START_TASK = 4;
    public static final String DOWNLOAD_SCHEME_CONTENTS = "Contents";
    public static final String DOWNLOAD_SCHEME_MEDIA_PLUS = "Media+";
    public static final String DOWNLOAD_SDCARD_PATH;
    private static final boolean ENABLE_DOWNLOAD_QUEUE = false;
    public static final String IMAGE_INTERNAL_SCHEME = "Pictures";
    public static final String IMAGE_SDCARD_SCHEME = "Media+/Contents";
    private static final int MAX_RUNNING_TASK = 3;
    private static final int MSG_CLEAN = 3;
    private static final int MSG_RUNNABLE = 0;
    private static final int MSG_START = 2;
    private static final int MSG_STOP = 1;
    private static final int MSG_UNMOUNTED = 4;
    public static final String MUSIC_INTERNAL_SCHEME = "Music";
    public static final String MUSIC_SDCARD_SCHEME = "Music";
    protected static final String NOTIFY_VP_DOWNLOADED = "files_download_finished";
    private static final String TAG = "UpDownloadEngine";
    public static final String VIDEO_INTERNAL_SCHEME = "Video";
    public static final String VIDEO_SDCARD_SCHEME = "Media+/Contents";
    public String DOWNLOAD_INTERNAL_PATH;
    private final int NOTIFY_ID = 0;
    private Application mApp;
    private int mCheckFlag;
    private Context mContext;
    protected long mCount;
    private int mCurTaskType;
    private int mCurrentDownloadProgress;
    private ReadWriteLock mCursorLock;
    private String mDownloadDestination;
    private final com.arcsoft.mediaplus.updownload.IPoolDriver.IDownloadListener mDownloadListener = new com.arcsoft.mediaplus.updownload.IPoolDriver.IDownloadListener() {

        final UpDownloadEngine this$0;

        public void onDownloadFinished(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult downloadresult)
        {
            ArrayList arraylist;
            Log.i("UpDownloadEngine", (new StringBuilder()).append("onDownloadFinished id=").append(downloadresult.tableid).append(" ,result=").append(downloadresult.errorcode).toString());
            updateDownloadFinished(downloadresult);
            Exception exception;
            Iterator iterator;
            if (downloadresult.errorcode != 816 && downloadresult.errorcode != 818 && downloadresult.errorcode != 817 && downloadresult.errorcode != 819)
            {
                if (downloadresult.errorcode == 911)
                {
                    Intent intent = new Intent("files_download_finished");
                    mContext.sendBroadcast(intent);
                    endNotification(0, 0);
                } else
                {
                    endNotification(0, 1);
                }
            } else
            {
                endNotification(0, 2);
            }
            arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadFinish(downloadresult.request.dms_uuid, downloadresult.request.uri, downloadresult, downloadresult.errorcode)) { }
            break MISSING_BLOCK_LABEL_230;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
            mEasyTransferDriver.onDownloadFinished(downloadresult);
            if (downloadresult.errorcode != 819 && downloadresult.errorcode != 817)
            {
                startFirstUpDownloadTask();
            }
            return;
        }

        public void onDownloadProgress(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest, long l)
        {
            mCount = System.currentTimeMillis() - tick;
            if (mCount <= 1400L)
            {
                break MISSING_BLOCK_LABEL_190;
            }
            Log.d("UpDownloadEngine", (new StringBuilder()).append("tick = ").append(mCount).toString());
            mCurrentDownloadProgress = (int)(100F * ((float)downloadrequest.downloadSize / (float)downloadrequest.fileSize));
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onProgress(downloadrequest.dms_uuid, downloadrequest.uri, downloadrequest.downloadSize, downloadrequest.fileSize)) { }
            break MISSING_BLOCK_LABEL_162;
            Exception exception;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
            updateNotification(downloadrequest.downloadSize, downloadrequest.fileSize);
            tick = System.currentTimeMillis();
        }

        public void onDownloadStarted(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest, long l)
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("onDownloadStarted id=").append(l).toString());
            mCurrentDownloadProgress = 0;
            updateDownloadStarted(downloadrequest);
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadStart(downloadrequest.dms_uuid, downloadrequest.uri)) { }
            break MISSING_BLOCK_LABEL_110;
            Exception exception;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
            setUpNotification(0, downloadrequest.title, downloadrequest.downloadSize, downloadrequest.fileSize);
            mEasyTransferDriver.onDownloadStarted(downloadrequest, l);
            tick = System.currentTimeMillis();
            return;
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private Hashtable mDownloadMap;
    private DownloadPoolDriver mDownloadPoolDriver;
    DownloadQueue mDownloadQueue;
    private DTCPDownloadPoolDriver mDtcpDownloadPoolDriver;
    private EasyTransferDriver mEasyTransferDriver;
    com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver.IOnEasyTransferDriverListener mEasyTransferDriverListener;
    private ServiceHandler mHandler;
    private boolean mIsDmsConnection;
    private boolean mIsWifiConnection;
    private final ArrayList mListeners = new ArrayList();
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final UpDownloadEngine this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo == null || networkstateinfo.networkInfo.getType() != 1)
            {
                return;
            }
            if (networkstateinfo.networkInfo.isConnected())
            {
                Log.i("UpDownloadEngine", "OnConnectivityChanged connect");
                mIsWifiConnection = true;
                mHandler.removeMessages(2);
                mHandler.sendEmptyMessage(2);
                return;
            } else
            {
                Log.i("UpDownloadEngine", "OnConnectivityChanged disconnect");
                mIsWifiConnection = false;
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessage(1);
                return;
            }
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    private final com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr.IOnDBDataListener mOnDBDataListener = new com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr.IOnDBDataListener() {

        final UpDownloadEngine this$0;

        public void OnDBDataMounted(String s)
        {
        }

        public void OnDBDataUnMounted(String s)
        {
        }

        public void OnDBDataUpdated(String s)
        {
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final UpDownloadEngine this$0;

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
            String s = Settings.instance().getDefaultDMSUDN();
            if (mediaserverdesc.m_strUuid != null && mediaserverdesc.m_strUuid.equals(s))
            {
                Log.i("UpDownloadEngine", (new StringBuilder()).append("onServerAdded: ").append(mediaserverdesc.m_strUuid).toString());
                mIsDmsConnection = true;
                mHandler.removeMessages(2);
                mHandler.sendEmptyMessage(2);
            }
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            String s = Settings.instance().getDefaultDMSUDN();
            if (mediaserverdesc.m_strUuid != null && mediaserverdesc.m_strUuid.equals(s))
            {
                Log.i("UpDownloadEngine", (new StringBuilder()).append("onServerRemoved:").append(mediaserverdesc.m_strUuid).toString());
                mIsDmsConnection = false;
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessage(1);
            }
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private ServiceThread mServiceThread;
    private final com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener mSettingChangedListener = new com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener() {

        final UpDownloadEngine this$0;

        public void OnDefaultDownloadDestinationChanged(String s)
        {
            mDownloadDestination = s;
        }

        public void OnDefaultRenderChanged(String s)
        {
        }

        public void OnDefaultServerChanged(String s)
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("OnDefaultServerChanged: ").append(s).toString());
            mIsDmsConnection = true;
        }

        public void onBrowseModeChanged(boolean flag)
        {
        }

        public void onSortModeChanged(boolean flag)
        {
        }

        public void onTVStreamingResolutionChange(boolean flag)
        {
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private com.arcsoft.adk.atv.DLNA.UserToken mTimerToken;
    private UpDownloadToken mToken;
    private UpDownloadDBMgr mUpDownloadDBMgr;
    private final com.arcsoft.mediaplus.updownload.UploadPoolDriver.IUploadListener mUploadListener = new com.arcsoft.mediaplus.updownload.UploadPoolDriver.IUploadListener() {

        final UpDownloadEngine this$0;

        public void onUploadFinished(com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult uploadresult)
        {
            ArrayList arraylist;
            Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadFinished id=").append(uploadresult.tableid).append(" ,result=").append(uploadresult.errorcode).toString());
            updateUploadFinished(uploadresult);
            if (uploadresult.errorcode != 819 && uploadresult.errorcode != 817)
            {
                startFirstUpDownloadTask();
                Exception exception;
                Iterator iterator;
                if (uploadresult.errorcode == 1015)
                {
                    endNotification(1, 0);
                } else
                {
                    endNotification(1, 1);
                }
            } else
            {
                endNotification(1, 2);
            }
            arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadFinish(uploadresult.request.dms_uuid, uploadresult.request.uri, uploadresult, uploadresult.errorcode)) { }
            break MISSING_BLOCK_LABEL_195;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
        }

        public void onUploadProgress(com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadRequest uploadrequest, long l)
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadProgress id=").append(l).toString());
            mCount = System.currentTimeMillis() - tick;
            if (mCount <= 500L)
            {
                break MISSING_BLOCK_LABEL_161;
            }
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onProgress(uploadrequest.dms_uuid, uploadrequest.uri, uploadrequest.uploadSize, uploadrequest.fileSize)) { }
            break MISSING_BLOCK_LABEL_133;
            Exception exception;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
            updateNotification(uploadrequest.uploadSize, uploadrequest.fileSize);
            tick = System.currentTimeMillis();
        }

        public void onUploadStarted(com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadRequest uploadrequest, long l)
        {
            Log.i("UpDownloadEngine", (new StringBuilder()).append("onUploadStarted id=").append(l).toString());
            updateUploadStarted(uploadrequest);
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadStart(uploadrequest.dms_uuid, uploadrequest.uri)) { }
            break MISSING_BLOCK_LABEL_101;
            Exception exception;
            exception;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception;
            arraylist;
            JVM INSTR monitorexit ;
            setUpNotification(1, uploadrequest.title, uploadrequest.uploadSize, uploadrequest.fileSize);
            return;
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    private UploadPoolDriver mUploadPoolDriver;
    private final BroadcastReceiver sdcardListener = new BroadcastReceiver() {

        final UpDownloadEngine this$0;

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if (s.equals("android.intent.action.MEDIA_MOUNTED"))
            {
                Log.i("UpDownloadEngine", (new StringBuilder()).append("SD card mounted =").append(s).toString());
                mHandler.removeMessages(2);
                mHandler.sendEmptyMessage(2);
            } else
            if (s.equals("android.intent.action.MEDIA_UNMOUNTED") || s.equals("android.intent.action.MEDIA_BAD_REMOVAL") || s.equals("android.intent.action.MEDIA_EJECT"))
            {
                Log.i("UpDownloadEngine", (new StringBuilder()).append("SD card unmounted =").append(s).toString());
                mHandler.removeMessages(4);
                mHandler.sendEmptyMessage(4);
                return;
            }
        }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
    };
    protected long tick;

    public UpDownloadEngine()
    {
        DOWNLOAD_INTERNAL_PATH = null;
        mUpDownloadDBMgr = null;
        mDownloadPoolDriver = null;
        mUploadPoolDriver = null;
        mDtcpDownloadPoolDriver = null;
        mEasyTransferDriver = null;
        mHandler = new ServiceHandler();
        mServiceThread = null;
        mApp = null;
        mContext = null;
        mNetworkTool = null;
        mIsWifiConnection = true;
        mIsDmsConnection = true;
        mDownloadDestination = "Internal flash memory";
        mCursorLock = null;
        mToken = null;
        mTimerToken = null;
        mDownloadMap = null;
        mDownloadQueue = null;
        mCount = 0L;
        tick = 0L;
        mCurrentDownloadProgress = 0;
        mNotificationManager = null;
        mNotification = null;
        mEasyTransferDriverListener = new com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver.IOnEasyTransferDriverListener() {

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
                if (isRelease() || list == null || list.size() < 1)
                {
                    Log.e("UpDownloadEngine", "all task is null");
                    return -1;
                }
                sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
                mCursorLock.writeLock().lock();
                sqlitedatabase.beginTransaction();
                int j;
                int i = 0 + mDtcpDownloadPoolDriver.cancelTask(s);
                Log.i("UpDownloadEngine", (new StringBuilder()).append("cancel easy-transfer going task =").append(i).toString());
                String as[] = new String[3];
                as[0] = Integer.toString(0);
                as[1] = s;
                as[2] = Integer.toString(1);
                j = i + mUpDownloadDBMgr.deleteDownload("type =? AND dms_uuid =? AND state =? ", as);
                sqlitedatabase.setTransactionSuccessful();
                sqlitedatabase.endTransaction();
                mCursorLock.writeLock().unlock();
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
                if (isRelease() || list == null || list.size() < 1)
                {
                    Log.e("UpDownloadEngine", "all task is null");
                    return false;
                }
                sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
                mCursorLock.writeLock().lock();
                sqlitedatabase.beginTransaction();
                for (Iterator iterator = list.iterator(); iterator.hasNext();)
                {
                    com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver.TransferItem transferitem = (com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver.TransferItem)iterator.next();
                    DownloadTask downloadtask = transferitem.task;
                    deleteUpDownload(0, downloadtask.uri, downloadtask.dms_uuid);
                    long l1 = insertDownloadTask(downloadtask);
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
                mCursorLock.writeLock().unlock();
                if (!isChecking() && isUploadAndDownloadPoolIdle())
                {
                    startFirstUpDownloadTask();
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

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
        };
        mCheckFlag = 7;
        mCurTaskType = 0;
    }

    private boolean cancel_internal(boolean flag, int i, boolean flag1)
    {
        Log.i("UpDownloadEngine", "cancel_internal");
        if (isRelease())
        {
            return false;
        }
        if (!flag1) goto _L2; else goto _L1
_L1:
        deleteUpDownload(0, 0L, true);
        deleteUpDownload(1, 0L, true);
        mDownloadPoolDriver.cancelAllTask(flag);
        mDtcpDownloadPoolDriver.cancelAllTask(flag);
        mUploadPoolDriver.cancelAllTask(flag);
        mEasyTransferDriver.onCancelAllTask(flag);
_L4:
        return true;
_L2:
        if (i == 1)
        {
            deleteUpDownload(1, 0L, true);
            mUploadPoolDriver.cancelAllTask(flag);
        } else
        if (i == 0)
        {
            deleteUpDownload(0, 0L, true);
            mDownloadPoolDriver.cancelAllTask(flag);
            mDtcpDownloadPoolDriver.cancelAllTask(flag);
            mEasyTransferDriver.onCancelAllTask(flag);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void checkDatabaseDms()
    {
        Log.i("UpDownloadEngine", "checkDatabaseDms");
        mEasyTransferDriver.checkDatabase();
    }

    private void checkDatabaseState()
    {
        Log.i("UpDownloadEngine", "checkDatabaseState");
        updateDatabaseState();
    }

    private void checkSdcardVolume()
    {
        Log.i("UpDownloadEngine", "checkSdcardVolume");
        deleteUpDownloadVolumeId(getCurrentVolumeId());
    }

    private void clearNotDownloadedItems()
    {
        if (mDownloadMap == null)
        {
            return;
        }
        Hashtable hashtable = mDownloadMap;
        hashtable;
        JVM INSTR monitorenter ;
        Iterator iterator = mDownloadMap.entrySet().iterator();
        if (iterator != null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        hashtable;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashtable;
        JVM INSTR monitorexit ;
        throw exception;
        ArrayList arraylist;
        mDownloadMap.size();
        arraylist = new ArrayList();
_L2:
        java.util.Map.Entry entry;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_137;
            }
            entry = (java.util.Map.Entry)iterator.next();
        } while (entry == null);
        DownloadTask downloadtask = (DownloadTask)entry.getValue();
        if (downloadtask == null) goto _L2; else goto _L1
_L1:
        if (3L == downloadtask.status || 4L == downloadtask.status) goto _L2; else goto _L3
_L3:
        arraylist.add(downloadtask.item_uuid);
          goto _L2
        String s;
        for (Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); mDownloadMap.remove(s))
        {
            s = (String)iterator1.next();
        }

        arraylist.clear();
        hashtable;
        JVM INSTR monitorexit ;
    }

    private boolean deleteUpDownload(int i, long l, boolean flag)
    {
        String s;
        String as[];
        int j;
        if (flag)
        {
            s = null;
        } else
        {
            s = "_id=?";
        }
        as = null;
        if (!flag)
        {
            as = new String[1];
            as[0] = Long.toString(l);
        }
        mCursorLock.writeLock().lock();
        if (i == 0)
        {
            j = mUpDownloadDBMgr.deleteDownload(s, as);
        } else
        {
            j = 0;
            if (i == 1)
            {
                j = mUpDownloadDBMgr.deleteUpload(s, as);
            }
        }
        mCursorLock.writeLock().unlock();
        return j > 0;
    }

    private boolean deleteUpDownload(int i, String s, String s1)
    {
        return deleteUpDownload(i, s, s1, true);
    }

    private boolean deleteUpDownload(int i, String s, String s1, boolean flag)
    {
        String as[] = {
            s, s1
        };
        if (flag)
        {
            mCursorLock.writeLock().lock();
        }
        int j;
        if (i == 0)
        {
            j = mUpDownloadDBMgr.deleteDownloadNotSync("uri=? AND dms_uuid=?", as);
        } else
        {
            j = 0;
            if (i == 1)
            {
                j = mUpDownloadDBMgr.deleteUpload("uri=? AND dms_uuid=?", as);
            }
        }
        if (flag)
        {
            mCursorLock.writeLock().unlock();
        }
        return j > 0;
    }

    private void deleteUpDownloadVolumeId(String s)
    {
    }

    private void endNotification(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Log.d("UpDownloadEngine", "endNotification");
        if (mNotification == null) goto _L2; else goto _L1
_L1:
        NotificationManager notificationmanager = mNotificationManager;
        if (notificationmanager != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        Notification notification = mNotification;
        notification;
        JVM INSTR monitorenter ;
        PendingIntent pendingintent;
        mNotification.flags = 16;
        mNotification.contentView = null;
        Intent intent = new Intent();
        pendingintent = PendingIntent.getActivity(mContext, 0, intent, 0x8000000);
        Object obj;
        Object obj1;
        obj = null;
        obj1 = null;
        j;
        JVM INSTR tableswitch 0 2: default 116
    //                   0 188
    //                   1 262
    //                   2 296;
           goto _L4 _L5 _L6 _L7
_L4:
        mNotification.tickerText = ((CharSequence) (obj1));
        mNotification.setLatestEventInfo(mContext, ((CharSequence) (obj1)), ((CharSequence) (obj)), pendingintent);
        mNotification.when = System.currentTimeMillis();
        mNotificationManager.notify(0, mNotification);
        mNotification = null;
        notification;
        JVM INSTR monitorexit ;
          goto _L2
        Exception exception1;
        exception1;
        notification;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L5:
        if (i != 0) goto _L9; else goto _L8
_L8:
        obj = mContext.getString(0x7f0b00d9);
_L12:
        obj1 = obj;
        if (mNotification.icon != 0x7f020260) goto _L11; else goto _L10
_L10:
        mNotification.icon = 0x7f02025f;
          goto _L4
_L9:
        obj = mContext.getString(0x7f0b00de);
          goto _L12
_L11:
        mNotification.icon = 0x7f020261;
          goto _L4
_L6:
        if (i != 0) goto _L14; else goto _L13
_L13:
        obj = mContext.getString(0x7f0b00da);
          goto _L15
_L14:
        obj = mContext.getString(0x7f0b00df);
          goto _L15
_L7:
        if (i != 0) goto _L17; else goto _L16
_L16:
        obj = mContext.getString(0x7f0b00db);
          goto _L18
_L17:
        String s = mContext.getString(0x7f0b00e0);
        obj = s;
          goto _L18
_L15:
        obj1 = obj;
          goto _L4
_L18:
        obj1 = obj;
          goto _L4
    }

    private String getCurrentVolumeId()
    {
        Log.i("UpDownloadEngine", "getVolumeId");
        String s = Environment.getExternalStorageDirectory().getPath();
        Log.v("UpDownloadEngine", (new StringBuilder()).append(s).append(" volume ID: ").append(0).toString());
        return Integer.toHexString(0);
    }

    private void initDownloadStatusMap()
    {
        Log.e("FENG", "FENG UpDownloadEngine initDownloadStatusMap() IN");
        mDownloadMap = new Hashtable();
        mDownloadQueue = new DownloadQueue();
        readFromWatingDownloadDB();
        readFromAlreadyDownloadDB();
    }

    private long insertDownloadTask(DownloadTask downloadtask)
    {
        long l;
        DownloadQueue downloadqueue;
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("type", Long.toString(0L));
        contentvalues.put("dms_uuid", downloadtask.dms_uuid);
        contentvalues.put("item_uuid", downloadtask.item_uuid);
        contentvalues.put("UPNP_CLASS", Long.valueOf(downloadtask.mediaClass));
        contentvalues.put("MEDIA_ID", Long.valueOf(downloadtask.mediaId));
        contentvalues.put("title", downloadtask.title);
        contentvalues.put("uri", downloadtask.uri);
        contentvalues.put("current_size", Integer.valueOf(0));
        contentvalues.put("file_size", Long.valueOf(downloadtask.fileSize));
        contentvalues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        int i;
        int j;
        DownloadTask downloadtask1;
        if (downloadtask.priority == 0)
        {
            i = 1;
        } else
        {
            i = 5;
        }
        contentvalues.put("state", Integer.valueOf(i));
        contentvalues.put("protocol_info", downloadtask.protocolInfo);
        if (downloadtask.videoOrImage)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        contentvalues.put("mediaType", Integer.valueOf(j));
        if (downloadtask.priority == 0)
        {
            l = mUpDownloadDBMgr.insertDownload(contentvalues);
        } else
        {
            String as[] = UpDownloadTable.PROJECTION_ARRAY;
            String as1[] = new String[4];
            as1[0] = downloadtask.dms_uuid;
            as1[1] = downloadtask.uri;
            as1[2] = Integer.toString(3);
            as1[3] = Integer.toString(2);
            Cursor cursor = mUpDownloadDBMgr.query(as, "dms_uuid=? AND uri=? AND state!=? AND state!=?", as1, null);
            if (cursor != null && cursor.getCount() > 0)
            {
                l = mUpDownloadDBMgr.updateDownload(contentvalues, "dms_uuid=? AND uri=? AND state!=? AND state!=?", as1);
            } else
            {
                l = mUpDownloadDBMgr.insertDownload(contentvalues);
            }
        }
        if (-1L == l) goto _L2; else goto _L1
_L1:
        downloadqueue = mDownloadQueue;
        downloadqueue;
        JVM INSTR monitorenter ;
        if (downloadtask.priority != 0) goto _L4; else goto _L3
_L3:
        mDownloadQueue.enQueue(downloadtask);
_L6:
        if (mDownloadMap == null)
        {
            break MISSING_BLOCK_LABEL_441;
        }
        if (downloadtask.item_uuid != null)
        {
            downloadtask1 = getDownloadTaskByUuid(downloadtask.item_uuid);
            if (downloadtask1 == null)
            {
                break MISSING_BLOCK_LABEL_428;
            }
            downloadtask1.status = 1L;
            downloadtask1.uri = downloadtask.uri;
        }
_L2:
        return l;
_L4:
        if (1 != downloadtask.priority) goto _L6; else goto _L5
_L5:
        mDownloadQueue.insertToQueue(0, downloadtask);
          goto _L6
        Exception exception;
        exception;
        downloadqueue;
        JVM INSTR monitorexit ;
        throw exception;
        downloadtask.status = 1L;
        insertTaskToDownloadStatus(downloadtask);
        return l;
        Log.e("test", "DownloadMap is null");
        return l;
    }

    private void insertToDownloadedFileTable(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
    {
        if (downloadrequest == null)
        {
            return;
        } else
        {
            DownloadedFileDBMgr.instance().addDownloadedFileToTable(downloadrequest.dms_uuid, downloadrequest.item_uuid, downloadrequest.uri, downloadrequest.filepath, downloadrequest.title, downloadrequest.fileSize);
            return;
        }
    }

    private long insertUploadTask(UploadTask uploadtask, String s, long l)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("volume_id", getCurrentVolumeId());
        contentvalues.put("type", Long.toString(1L));
        contentvalues.put("dms_uuid", uploadtask.dms_uuid);
        contentvalues.put("UPNP_CLASS", Long.valueOf(uploadtask.mediaClass));
        contentvalues.put("MEDIA_ID", Long.valueOf(uploadtask.mediaId));
        contentvalues.put("title", uploadtask.title);
        contentvalues.put("uri", uploadtask.uri);
        contentvalues.put("protocol_info", uploadtask.protocolInfo);
        contentvalues.put("current_size", Integer.valueOf(0));
        contentvalues.put("file_size", Long.valueOf(l));
        contentvalues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000L));
        contentvalues.put("state", Integer.valueOf(1));
        return mUpDownloadDBMgr.insertUpload(contentvalues);
    }

    private boolean isDtcpFile(String s)
    {
        while (s == null || s.equals("") || !s.contains("DTCP1HOST") || !s.contains("DTCP1PORT")) 
        {
            return false;
        }
        return true;
    }

    private boolean isRelease()
    {
        boolean flag;
        if (mUpDownloadDBMgr == null || mUploadPoolDriver == null || mDownloadPoolDriver == null || mDtcpDownloadPoolDriver == null || mEasyTransferDriver == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Log.i("UpDownloadEngine", (new StringBuilder()).append("Engine release =").append(flag).toString());
        return flag;
    }

    private void lockTimerToken()
    {
        if (mTimerToken == null)
        {
            Log.w("UpDownloadEngine", "lock Token in UpDownloadEngine");
            mTimerToken = DLNA.instance().lockUserToken();
        }
    }

    private int queryUpDownloadId(int i, String s, String s1, boolean flag)
    {
        return queryUpDownloadId(i, s, flag);
    }

    private int queryUpDownloadId(int i, String s, boolean flag)
    {
        String as[] = {
            "_id"
        };
        String as1[] = {
            s
        };
        int j = -1;
        if (flag)
        {
            mCursorLock.readLock().lock();
        }
        Cursor cursor;
        boolean flag1;
        if (i == 0)
        {
            cursor = mUpDownloadDBMgr.queryDownload(as, "uri=?", as1, null);
        } else
        {
            cursor = null;
            if (i == 1)
            {
                cursor = mUpDownloadDBMgr.queryUpload(as, "uri=?", as1, null);
            }
        }
        flag1 = false;
        if (cursor != null)
        {
            flag1 = cursor.moveToFirst();
            if (flag1)
            {
                j = cursor.getInt(cursor.getColumnIndex("_id"));
            }
            cursor.close();
        }
        if (flag)
        {
            mCursorLock.readLock().unlock();
        }
        if (flag1)
        {
            return j;
        } else
        {
            return -1;
        }
    }

    private String queryUpDownloadMediaItemUuid(int i, long l)
    {
        String as[] = {
            "item_uuid"
        };
        String as1[] = new String[1];
        as1[0] = String.valueOf(l);
        mCursorLock.readLock().lock();
        Cursor cursor;
        boolean flag;
        String s;
        if (i == 0)
        {
            cursor = mUpDownloadDBMgr.queryDownload(as, "_id=?", as1, null);
        } else
        {
            cursor = null;
            if (i == 1)
            {
                cursor = mUpDownloadDBMgr.queryUpload(as, "_id=?", as1, null);
            }
        }
        flag = false;
        s = null;
        if (cursor != null)
        {
            flag = cursor.moveToFirst();
            s = null;
            if (flag)
            {
                s = cursor.getString(cursor.getColumnIndex("item_uuid"));
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

    private int queryUpDownloadState(int i, String s, String s1, boolean flag)
    {
        String as[] = {
            "state"
        };
        String as1[] = {
            s, s1
        };
        int j = -1;
        if (flag)
        {
            mCursorLock.readLock().lock();
        }
        Cursor cursor;
        boolean flag1;
        if (i == 0)
        {
            cursor = mUpDownloadDBMgr.queryDownload(as, "uri=? AND dms_uuid=?", as1, null);
        } else
        {
            cursor = null;
            if (i == 1)
            {
                cursor = mUpDownloadDBMgr.queryUpload(as, "uri=? AND dms_uuid=?", as1, null);
            }
        }
        flag1 = false;
        if (cursor != null)
        {
            flag1 = cursor.moveToFirst();
            if (flag1)
            {
                j = cursor.getInt(cursor.getColumnIndex("state"));
            }
            cursor.close();
        }
        if (flag)
        {
            mCursorLock.readLock().unlock();
        }
        if (flag1)
        {
            return j;
        } else
        {
            return 0;
        }
    }

    private void readFromAlreadyDownloadDB()
    {
        Cursor cursor = DownloadedFileDBMgr.instance().query(DownloadedFileTable.PROJECTION_ARRAY, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                DownloadTask downloadtask = new DownloadTask();
                downloadtask.title = cursor.getString(cursor.getColumnIndex("file_title"));
                downloadtask.mediaId = cursor.getLong(cursor.getColumnIndex("_id"));
                downloadtask.dms_uuid = cursor.getString(cursor.getColumnIndex("dms_uuid"));
                downloadtask.item_uuid = cursor.getString(cursor.getColumnIndex("item_uuid"));
                downloadtask.status = 3L;
                insertTaskToDownloadStatus(downloadtask);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    private void readFromWatingDownloadDB()
    {
        String as[] = UpDownloadTable.PROJECTION_ARRAY;
        String as1[] = new String[2];
        as1[0] = Integer.toString(1);
        as1[1] = Integer.toString(0);
        Cursor cursor = UpDownloadDBMgr.instance().query(as, "state=? AND type=?", as1, "date_added desc");
        if (cursor == null || !cursor.moveToFirst())
        {
            return;
        }
        Log.d("test", (new StringBuilder()).append("waiting download cursor count = ").append(cursor.getCount()).toString());
        do
        {
            DownloadTask downloadtask = new DownloadTask();
            downloadtask.dms_uuid = cursor.getString(cursor.getColumnIndex("dms_uuid"));
            downloadtask.item_uuid = cursor.getString(cursor.getColumnIndex("item_uuid"));
            downloadtask.title = cursor.getString(cursor.getColumnIndex("title"));
            downloadtask.uri = cursor.getString(cursor.getColumnIndex("uri"));
            downloadtask.fileSize = Long.parseLong(cursor.getString(cursor.getColumnIndex("file_size")));
            downloadtask.protocolInfo = cursor.getString(cursor.getColumnIndex("protocol_info"));
            insertTaskToDownloadStatus(downloadtask);
            mDownloadQueue.enQueue(downloadtask);
        } while (cursor.moveToNext());
        cursor.close();
    }

    private void releaseTimerToken()
    {
        if (mTimerToken != null)
        {
            Log.w("UpDownloadEngine", "Release Token in UpDownloadEngine");
            DLNA.instance().releaseUserToken(mTimerToken);
            mTimerToken = null;
        }
    }

    private void scanSingleFile(String s)
    {
        if (s == null)
        {
            return;
        }
        boolean flag = Environment.getExternalStorageDirectory().getPath().startsWith("/mnt");
        File file;
        Context context;
        String as[];
        if (!mDownloadDestination.equalsIgnoreCase("SD card"))
        {
            flag = false;
        }
        if (flag)
        {
            s = (new StringBuilder()).append("/mnt").append(s).toString();
        }
        file = new File(s);
        context = mContext;
        as = new String[1];
        as[0] = file.toString();
        MediaScannerConnection.scanFile(context, as, null, new android.media.MediaScannerConnection.OnScanCompletedListener() {

            final UpDownloadEngine this$0;

            public void onScanCompleted(String s1, Uri uri)
            {
                Log.i("test", (new StringBuilder()).append("Scanned ").append(s1).append(":").toString());
                Log.i("test", (new StringBuilder()).append("-> uri=").append(uri).toString());
                ContentResolver contentresolver = mContext.getContentResolver();
                long l = 0L;
                long l1 = 0L;
                Cursor cursor = contentresolver.query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst())
                {
                    l = cursor.getLong(cursor.getColumnIndex("date_modified"));
                    l1 = cursor.getLong(cursor.getColumnIndex("date_added"));
                    cursor.close();
                }
                ContentValues contentvalues = new ContentValues();
                if (l > 0L && String.valueOf(l).length() > 10)
                {
                    contentvalues.put("date_modified", Long.valueOf(l / 1000L));
                }
                if (l1 > 0L && String.valueOf(l1).length() > 13)
                {
                    contentvalues.put("date_added", Long.valueOf(l1 / 1000L));
                }
                if (contentvalues.size() > 0)
                {
                    contentresolver.update(uri, contentvalues, null, null);
                }
            }

            
            {
                this$0 = UpDownloadEngine.this;
                super();
            }
        });
    }

    private void setDatabaseListener(boolean flag)
    {
        if (flag)
        {
            mUpDownloadDBMgr.registerOnDataUpdateListener(mOnDBDataListener);
            return;
        } else
        {
            mUpDownloadDBMgr.unregisterOnDataUpdateListener(mOnDBDataListener);
            return;
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
            mApp.getApplicationContext().registerReceiver(sdcardListener, intentfilter);
            return;
        } else
        {
            mApp.getApplicationContext().unregisterReceiver(sdcardListener);
            return;
        }
    }

    private void setUpNotification(int i, String s, long l, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        Log.d("UpDownloadEngine", "setUpNotification");
        if (mNotification == null)
        {
            mNotification = new Notification();
        }
        Notification notification = mNotification;
        notification;
        JVM INSTR monitorenter ;
        int j;
        String s1;
        String s2;
        String s3;
        int k;
        if (i == 0)
        {
            j = 0x7f020260;
        } else
        {
            j = 0x7f020262;
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        s1 = mContext.getString(0x7f0b00d7);
_L5:
        long l2 = System.currentTimeMillis();
        mNotification.icon = j;
        mNotification.tickerText = s1;
        mNotification.when = l2;
        s2 = DateUtils.formatDateTime(mContext, l2, 129);
        s3 = (new StringBuilder()).append(l).append("/").append(l1).toString();
        if (l1 > 0L) goto _L4; else goto _L3
_L3:
        k = 0;
_L6:
        mNotification.flags = 2;
        RemoteViews remoteviews = new RemoteViews(mContext.getPackageName(), 0x7f030028);
        remoteviews.setImageViewResource(0x7f0900d2, j);
        remoteviews.setTextViewText(0x7f09006b, s);
        remoteviews.setTextViewText(0x7f0900d3, s3);
        remoteviews.setTextViewText(0x7f0900d4, (new StringBuilder()).append(k).append("%").toString());
        remoteviews.setTextViewText(0x7f0900d5, s2);
        remoteviews.setProgressBar(0x7f09000a, 100, k, false);
        mNotification.contentView = remoteviews;
        Intent intent = new Intent();
        PendingIntent pendingintent = PendingIntent.getActivity(mContext, 0, intent, 0x8000000);
        mNotification.contentIntent = pendingintent;
        mNotificationManager.notify(0, mNotification);
        notification;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        s1 = mContext.getString(0x7f0b00dc);
          goto _L5
_L4:
        k = (int)((100L * l) / l1);
          goto _L6
        Exception exception1;
        exception1;
        notification;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
          goto _L5
    }

    private void setValidFileTitle(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
    {
        if (downloadrequest != null)
        {
            String as[] = DownloadedFileTable.PROJECTION_ARRAY;
            String as1[] = new String[1];
            as1[0] = downloadrequest.item_uuid;
            Cursor cursor = DownloadedFileDBMgr.instance().query(as, "item_uuid=?", as1, null);
            if (cursor != null && cursor.moveToFirst())
            {
                String s5 = cursor.getString(cursor.getColumnIndex("file_title"));
                if (s5 != null)
                {
                    downloadrequest.title = s5;
                    return;
                }
            }
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = MyUPnPUtils.getItemResource(downloadrequest.dms_uuid, downloadrequest.item_uuid);
            if (presentitem_resource != null)
            {
                if (DLNA.instance().getServerManager().isDigaDMS(downloadrequest.dms_uuid) && presentitem_resource.m_strPxnVgaContentProtocolInfo != null && presentitem_resource.m_strPxnVgaContentProtocolInfo.length() != 0)
                {
                    presentitem_resource.m_strProtocolInfo = presentitem_resource.m_strPxnVgaContentProtocolInfo;
                }
                if (downloadrequest.videoOrImage && downloadrequest.upnp_class != -1 && downloadrequest.upnp_class != 1)
                {
                    downloadrequest.upnp_class = 1;
                }
                String s = MimeTypeUtils.getExtensionMapMimeType(UpDownloadUtils.getProtocolMimeType(downloadrequest.upnp_class, presentitem_resource.m_strProtocolInfo));
                if (s != null)
                {
                    String s1 = downloadrequest.title;
                    String s2 = downloadrequest.parentdir;
                    String s3 = (new StringBuilder()).append(s1).append(".").append(s).toString();
                    File file = new File((new StringBuilder()).append(s2).append(s3).toString());
                    int i = 1;
                    if (file.exists())
                    {
                        do
                        {
                            String s4 = (new StringBuilder()).append(s1).append("_").append(i).append(".").append(s).toString();
                            if (!(new File((new StringBuilder()).append(s2).append(s4).toString())).exists())
                            {
                                downloadrequest.title = (new StringBuilder()).append(s1).append("_").append(i).toString();
                                return;
                            }
                            i++;
                        } while (true);
                    }
                }
            }
        }
    }

    private boolean startFirstUpDownloadTask()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = startFirstUpDownloadTaskCursor();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean startFirstUpDownloadTaskCursor()
    {
        this;
        JVM INSTR monitorenter ;
        Log.i("UpDownloadEngine", "startFirstUpDownloadTask");
        boolean flag = true;
        String as[];
        Cursor cursor;
        as = UpDownloadTable.PROJECTION_ARRAY;
        String as1[] = new String[1];
        as1[0] = Integer.toString(5);
        cursor = mUpDownloadDBMgr.query(as, "state=?", as1, "date_added DESC");
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        if (cursor.getCount() != 0)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        String as2[] = new String[1];
        as2[0] = Integer.toString(1);
        cursor = mUpDownloadDBMgr.query(as, "state=?", as2, "date_added ASC");
        if (cursor != null) goto _L2; else goto _L1
_L1:
        Log.i("zdf", "startFirstUpDownloadTaskCursor, null == cursor, fail!");
        boolean flag1 = false;
_L9:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        int i = cursor.getCount();
        if (i <= 0) goto _L4; else goto _L3
_L3:
        int j;
        cursor.moveToFirst();
        j = cursor.getInt(cursor.getColumnIndex("type"));
        if (j != 0) goto _L6; else goto _L5
_L5:
        com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest;
        long l = cursor.getLong(cursor.getColumnIndex("date_added"));
        int k = cursor.getInt(cursor.getColumnIndex("state"));
        Log.e("zdf", (new StringBuilder()).append("######## [UpDownloadEngine] startFirstUpDownloadTaskCursor, add_time = ").append(l).append(", status = ").append(k).toString());
        downloadrequest = new com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest();
        downloadrequest.media_id = cursor.getInt(cursor.getColumnIndex("MEDIA_ID"));
        downloadrequest.tableid = cursor.getInt(cursor.getColumnIndex("_id"));
        downloadrequest.dms_uuid = cursor.getString(cursor.getColumnIndex("dms_uuid"));
        downloadrequest.item_uuid = cursor.getString(cursor.getColumnIndex("item_uuid"));
        downloadrequest.upnp_class = cursor.getInt(cursor.getColumnIndex("UPNP_CLASS"));
        downloadrequest.title = cursor.getString(cursor.getColumnIndex("title"));
        downloadrequest.uri = cursor.getString(cursor.getColumnIndex("uri"));
        downloadrequest.fileSize = Long.parseLong(cursor.getString(cursor.getColumnIndex("file_size")));
        downloadrequest.listener = mDownloadListener;
        downloadrequest.protocolInfo = cursor.getString(cursor.getColumnIndex("protocol_info"));
        Exception exception;
        boolean flag2;
        DownloadTask downloadtask;
        int i1;
        File file;
        if (cursor.getInt(cursor.getColumnIndex("mediaType")) == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        downloadrequest.videoOrImage = flag2;
        cursor.getInt(cursor.getColumnIndex("UPNP_CLASS"));
        downloadrequest.parentdir = OEMConfig.DOWNLOAD_PATH;
        (new File(downloadrequest.parentdir)).mkdirs();
        setValidFileTitle(downloadrequest);
        if (!isDtcpFile(downloadrequest.protocolInfo)) goto _L8; else goto _L7
_L7:
        mDtcpDownloadPoolDriver.download(downloadrequest);
_L10:
        downloadtask = getDownloadTaskByUuid(downloadrequest.item_uuid);
        if (downloadtask == null)
        {
            break MISSING_BLOCK_LABEL_601;
        }
        downloadtask.title = downloadrequest.title;
        downloadtask.status = 2L;
        mCurTaskType = 0;
_L4:
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_622;
        }
        Log.i("UpDownloadEngine", "startFirstUpDownloadTask() task empty");
        flag = false;
        cursor.close();
        flag1 = flag;
          goto _L9
_L8:
        mDownloadPoolDriver.download(downloadrequest);
          goto _L10
        exception;
        throw exception;
_L6:
        if (j != 1) goto _L4; else goto _L11
_L11:
label0:
        {
            i1 = cursor.getColumnIndex("uri");
            String s = Uri.parse(cursor.getString(i1)).getPath();
            file = new File(s);
            if (file.exists() && file.isFile())
            {
                break label0;
            }
            Log.e("UpDownloadEngine", (new StringBuilder()).append("upload file path fail =").append(s).toString());
        }
          goto _L4
        com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadRequest uploadrequest = new com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadRequest();
        uploadrequest.uri = cursor.getString(i1);
        uploadrequest.tableid = cursor.getInt(cursor.getColumnIndex("_id"));
        uploadrequest.dms_uuid = cursor.getString(cursor.getColumnIndex("dms_uuid"));
        uploadrequest.title = cursor.getString(cursor.getColumnIndex("title"));
        uploadrequest.protocolInfo = cursor.getString(cursor.getColumnIndex("protocol_info"));
        uploadrequest.fileSize = file.length();
        uploadrequest.listener = mUploadListener;
        mUploadPoolDriver.upload(uploadrequest);
        mCurTaskType = 1;
        lockTimerToken();
          goto _L4
    }

    private boolean startFirstUpDownloadTaskQueue()
    {
        this;
        JVM INSTR monitorenter ;
        DownloadQueue downloadqueue = mDownloadQueue;
        if (downloadqueue != null) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L3:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        DownloadTask downloadtask;
        synchronized (mDownloadQueue)
        {
            downloadtask = (DownloadTask)mDownloadQueue.deQueue();
        }
        com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest;
        downloadrequest = taskToRequest(downloadtask);
        if (!isDtcpFile(downloadrequest.protocolInfo))
        {
            break MISSING_BLOCK_LABEL_130;
        }
        mDtcpDownloadPoolDriver.download(downloadrequest);
_L4:
        getDownloadTaskByUuid(downloadrequest.item_uuid);
        if (downloadtask == null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        downloadtask.status = 2L;
        int i;
        mCurTaskType = 0;
        i = mDownloadQueue.size();
        Exception exception;
        if (i == 0)
        {
            flag = false;
        } else
        {
            flag = true;
        }
          goto _L3
        exception1;
        downloadqueue1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        mDownloadPoolDriver.download(downloadrequest);
          goto _L4
    }

    private void startServiceThread()
    {
        Log.i("UpDownloadEngine", "startServiceThread");
        if (mServiceThread != null && mServiceThread.isAlive())
        {
            Log.e("UpDownloadEngine", "startServiceThread alive");
        } else
        if (mIsWifiConnection)
        {
            mCheckFlag = 7;
            if (mCheckFlag > 0)
            {
                mServiceThread = new ServiceThread();
                mServiceThread.start();
                return;
            }
        }
    }

    private void stopServiceThread()
    {
        Log.i("UpDownloadEngine", "stopServiceThread");
        mCheckFlag = 0;
        if (mServiceThread != null)
        {
            mServiceThread.bExit.set(true);
        }
        mServiceThread = null;
    }

    private com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest taskToRequest(DownloadTask downloadtask)
    {
        com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest = new com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest();
        downloadrequest.media_id = downloadtask.mediaId;
        downloadrequest.dms_uuid = downloadtask.dms_uuid;
        downloadrequest.item_uuid = downloadtask.item_uuid;
        downloadrequest.title = downloadtask.title;
        downloadrequest.uri = downloadtask.uri;
        downloadrequest.fileSize = downloadtask.fileSize;
        downloadrequest.listener = mDownloadListener;
        downloadrequest.videoOrImage = downloadtask.videoOrImage;
        downloadrequest.protocolInfo = downloadtask.protocolInfo;
        int _tmp = (int)downloadtask.mediaClass;
        downloadrequest.parentdir = OEMConfig.DOWNLOAD_PATH;
        (new File(downloadrequest.parentdir)).mkdirs();
        (new File(downloadrequest.parentdir)).mkdirs();
        return downloadrequest;
    }

    private void updateDatabaseState()
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("state", Integer.valueOf(1));
        contentvalues.put("upload_id", "");
        String as[] = new String[1];
        as[0] = Long.toString(2L);
        mCursorLock.writeLock().lock();
        mUpDownloadDBMgr.update(contentvalues, "state=?", as);
        mCursorLock.writeLock().unlock();
    }

    private void updateDownloadFinished(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult downloadresult)
    {
        if (downloadresult.errorcode != 816 && downloadresult.errorcode != 817)
        {
            if (downloadresult.errorcode == 819)
            {
                String s = Long.toString(downloadresult.tableid);
                ContentValues contentvalues = new ContentValues();
                contentvalues.put("state", Integer.valueOf(1));
                String as[] = {
                    s
                };
                mCursorLock.writeLock().lock();
                mUpDownloadDBMgr.updateDownload(contentvalues, "_id=?", as);
                mCursorLock.writeLock().unlock();
                return;
            }
            long l;
            if (downloadresult.errorcode == 911)
            {
                l = 3L;
            } else
            {
                l = 4L;
            }
            updateUpDownloadState(0, downloadresult.tableid, l);
            if (downloadresult.errorcode == 911)
            {
                insertToDownloadedFileTable(downloadresult.request);
                scanSingleFile(downloadresult.filePath);
                return;
            }
        }
    }

    private void updateDownloadStarted(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
    {
        String s = Long.toString(downloadrequest.tableid);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("state", Integer.valueOf(2));
        String as[] = {
            s
        };
        mCursorLock.writeLock().lock();
        mUpDownloadDBMgr.updateDownload(contentvalues, "_id=?", as);
        mCursorLock.writeLock().unlock();
    }

    private void updateNotification(long l, long l1)
    {
        Log.d("UpDownloadEngine", (new StringBuilder()).append("updateNotification, mNotification = ").append(mNotification).toString());
        if (mNotification == null)
        {
            return;
        }
        String s = (new StringBuilder()).append(l).append("/").append(l1).toString();
        int i;
        RemoteViews remoteviews;
        if (l1 <= 0L)
        {
            i = 0;
        } else
        {
            i = (int)((100L * l) / l1);
        }
        remoteviews = mNotification.contentView;
        remoteviews.setTextViewText(0x7f0900d3, s);
        remoteviews.setTextViewText(0x7f0900d4, (new StringBuilder()).append(i).append("%").toString());
        remoteviews.setProgressBar(0x7f09000a, 100, i, false);
        mNotificationManager.notify(0, mNotification);
    }

    private boolean updateUpDownloadState(int i, long l, long l1)
    {
        String as[] = new String[1];
        as[0] = Long.toString(l);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("state", Long.valueOf(l1));
        mCursorLock.writeLock().lock();
        int j;
        if (i == 0)
        {
            j = mUpDownloadDBMgr.updateDownload(contentvalues, "_id=?", as);
        } else
        {
            j = 0;
            if (i == 1)
            {
                j = mUpDownloadDBMgr.updateUpload(contentvalues, "_id=?", as);
            }
        }
        mCursorLock.writeLock().unlock();
        if (mDownloadMap != null)
        {
            String s = queryUpDownloadMediaItemUuid(i, l);
            if (s != null)
            {
                DownloadTask downloadtask = getDownloadTaskByUuid(s);
                if (downloadtask != null)
                {
                    downloadtask.status = l1;
                    downloadtask.priority = 0;
                }
            } else
            {
                Log.e("test", (new StringBuilder()).append("UpDownloadEngine.java updateUpDonwloadState tableid = ").append(l).append(" the tableid queried is null").toString());
            }
        }
        return j > 0;
    }

    private void updateUploadFinished(com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult uploadresult)
    {
        if (uploadresult.errorcode == 816 || uploadresult.errorcode == 817)
        {
            return;
        }
        if (uploadresult.errorcode == 819)
        {
            String s = Long.toString(uploadresult.tableid);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("state", Integer.valueOf(1));
            contentvalues.put("upload_id", "");
            String as[] = {
                s
            };
            mCursorLock.writeLock().lock();
            mUpDownloadDBMgr.updateUpload(contentvalues, "_id=?", as);
            mCursorLock.writeLock().unlock();
            return;
        }
        long l;
        if (uploadresult.errorcode == 1015)
        {
            l = 3L;
        } else
        {
            l = 4L;
        }
        updateUpDownloadState(1, uploadresult.tableid, l);
        releaseTimerToken();
    }

    private void updateUploadStarted(com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadRequest uploadrequest)
    {
        String s = Long.toString(uploadrequest.tableid);
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("state", Integer.valueOf(2));
        contentvalues.put("upload_id", Long.toString(uploadrequest.uploadId));
        String as[] = {
            s
        };
        mCursorLock.writeLock().lock();
        mUpDownloadDBMgr.updateUpload(contentvalues, "_id=?", as);
        mCursorLock.writeLock().unlock();
    }

    public boolean abortAllTask()
    {
        Log.i("UpDownloadEngine", "abortAllTask");
        if (isRelease())
        {
            return false;
        } else
        {
            stopServiceThread();
            mDownloadPoolDriver.abortAllTask();
            mDtcpDownloadPoolDriver.abortAllTask();
            mUploadPoolDriver.abortAllTask();
            endNotification(mCurTaskType, 2);
            return true;
        }
    }

    public boolean cancelAllTask()
    {
        Log.i("UpDownloadEngine", "cancelAllTask");
        clearNotDownloadedItems();
        readFromAlreadyDownloadDB();
        stopServiceThread();
        cancel_internal(true, -1, true);
        if (mDownloadQueue != null)
        {
            mDownloadQueue.clear();
        }
        if (isRelease() || isDownloadPoolIdle())
        {
            return false;
        }
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        for (Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((IOnUpDownloadListener)iterator.next()).onUpDownloadFinish(null, null, null, 817)) { }
        break MISSING_BLOCK_LABEL_116;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        endNotification(mCurTaskType, 2);
        return true;
    }

    public boolean cancelDownloadTask(DownloadTask downloadtask, boolean flag)
    {
        boolean flag1;
        if (downloadtask == null || downloadtask.uri == null)
        {
            flag1 = false;
        } else
        {
            flag1 = cancelTask(0, downloadtask.dms_uuid, downloadtask.title, Uri.parse(downloadtask.uri), flag);
            if (mDownloadMap != null)
            {
                mDownloadMap.remove(downloadtask.item_uuid);
                return flag1;
            }
        }
        return flag1;
    }

    public void cancelDownloadTasks(ArrayList arraylist)
    {
        boolean flag;
        SQLiteDatabase sqlitedatabase;
        System.currentTimeMillis();
        flag = false;
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        mCursorLock.writeLock().lock();
        sqlitedatabase.beginTransaction();
        Iterator iterator = arraylist.iterator();
_L1:
        DownloadTask downloadtask;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_83;
            }
            downloadtask = (DownloadTask)iterator.next();
        } while (downloadtask == null);
        flag |= cancelDownloadTask(downloadtask, false);
          goto _L1
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        mCursorLock.writeLock().unlock();
        readFromAlreadyDownloadDB();
        Log.v("zdf", (new StringBuilder()).append("[UpDownloadEngine] cancelDownloadTasks, isOngoing = ").append(flag).toString());
        if (flag)
        {
            endNotification(0, 2);
        }
        return;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public boolean cancelEasyTransfer(long l)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.cancelEasyTransfer(l);
        } else
        {
            return false;
        }
    }

    public boolean cancelEasyTransfer(String s)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.cancelEasyTransfer(s);
        } else
        {
            return false;
        }
    }

    public void cancelTask(int i, String s, Uri uri)
    {
        cancelTask(i, s, uri, true);
    }

    public void cancelTask(int i, String s, Uri uri, boolean flag)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("cancelTask =").append(uri.toString()).toString());
        if (!isRelease() && s != null && uri != null) goto _L2; else goto _L1
_L1:
        Log.e("UpDownloadEngine", "server uuid or uri is null");
_L4:
        return;
_L2:
        if (isChecking())
        {
            Log.e("UpDownloadEngine", "checking, please wait!");
            return;
        }
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k = queryUpDownloadId(0, uri.toString(), s, flag);
        if (k > -1)
        {
            int l = queryUpDownloadState(0, uri.toString(), s, flag);
            deleteUpDownload(0, k, false);
            boolean flag1 = mDownloadPoolDriver.cancelTask(k);
            if (!flag1)
            {
                flag1 = mDtcpDownloadPoolDriver.cancelTask(k);
            }
            if (flag1)
            {
                endNotification(0, 2);
            }
            mEasyTransferDriver.onCancelTask(s, uri, l, flag1);
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (i != 1) goto _L4; else goto _L3
_L3:
        int j = queryUpDownloadId(1, uri.toString(), s, true);
        if (j > -1)
        {
            deleteUpDownload(1, j, false);
            if (mUploadPoolDriver.cancelTask(j))
            {
                endNotification(1, 2);
                return;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public boolean cancelTask(int i, String s, String s1, Uri uri, boolean flag)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("cancelTask =").append(uri.toString()).toString());
        if (isRelease() || s == null || uri == null)
        {
            Log.e("UpDownloadEngine", "server uuid or uri is null");
        } else
        {
            if (isChecking())
            {
                Log.e("UpDownloadEngine", "checking, please wait!");
                return false;
            }
            if (i == 0 && deleteUpDownload(i, uri.toString(), s, flag))
            {
                boolean flag1 = mDownloadPoolDriver.cancelTask(uri, flag);
                if (!flag1)
                {
                    flag1 = mDtcpDownloadPoolDriver.cancelTask(uri, flag);
                }
                Log.v("zdf", (new StringBuilder()).append("[UpDownloadEngine] cancelTask, isOngoing = ").append(flag1).toString());
                return flag1;
            }
        }
        return false;
    }

    public void controlSpeed(int i, int j, int k)
    {
        if (isRelease())
        {
            return;
        }
        if (i == 0)
        {
            mDownloadPoolDriver.controlSpeed(j, k);
            mDtcpDownloadPoolDriver.controlSpeed(j, k);
            return;
        } else
        {
            mUploadPoolDriver.controlSpeed(j, k);
            return;
        }
    }

    public boolean deleteDownloadRecode(String s)
    {
        if (mDownloadMap == null || s == null)
        {
            return false;
        }
        Hashtable hashtable = mDownloadMap;
        hashtable;
        JVM INSTR monitorenter ;
        if (mDownloadMap.remove(s) != null)
        {
            return true;
        }
        break MISSING_BLOCK_LABEL_40;
        Exception exception;
        exception;
        hashtable;
        JVM INSTR monitorexit ;
        throw exception;
        hashtable;
        JVM INSTR monitorexit ;
        return false;
    }

    public boolean deleteDownloadedRecode(String s)
    {
        if (s == null || s.length() <= 0)
        {
            return false;
        }
        String as[] = DownloadedFileTable.PROJECTION_ARRAY;
        String as1[] = {
            s
        };
        Cursor cursor = DownloadedFileDBMgr.instance().query(as, "file_path=?", as1, null);
        String s1 = null;
        if (cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            s1 = null;
            if (flag)
            {
                s1 = cursor.getString(cursor.getColumnIndex("item_uuid"));
                DownloadedFileDBMgr.instance().deleteItemFromTableByFilePath(s);
            }
        }
        return deleteDownloadRecode(s1);
    }

    public boolean deleteEasyTransfer(long l)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.deleteEasyTransfer(l);
        } else
        {
            return false;
        }
    }

    public boolean deleteEasyTransfer(String s)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.deleteEasyTransfer(s);
        } else
        {
            return false;
        }
    }

    public int downloadTask(ArrayList arraylist)
    {
        int i;
        i = 0;
        Log.i("UpDownloadEngine", "download batch Task");
        if (!isRelease() && arraylist != null && arraylist.size() >= 1) goto _L2; else goto _L1
_L1:
        Log.e("UpDownloadEngine", "all task is null");
_L6:
        return i;
_L2:
        SQLiteDatabase sqlitedatabase;
        if (mDownloadPoolDriver.isTaskCanceling())
        {
            Log.e("UpDownloadEngine", "tasks are canceling!");
            ToastMgr.showToast(mContext, 0x7f0b0182, 0);
            return -1;
        }
        i = 0;
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        mCursorLock.writeLock().lock();
        sqlitedatabase.beginTransaction();
        Iterator iterator = arraylist.iterator();
_L4:
        DownloadTask downloadtask;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_302;
            }
            downloadtask = (DownloadTask)iterator.next();
        } while (downloadtask == null);
        if (!DownloadFacade.isMediaStoreSupported(downloadtask))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (isTaskExist(downloadtask.item_uuid, false) && !isFileDownloadFailed(downloadtask.item_uuid) && (downloadtask.priority == 0 || isFileDownloading(downloadtask.item_uuid)) && downloadtask.uri == null)
        {
            Log.e("UpDownloadEngine", "uri is null!");
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_219;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
        int j = queryUpDownloadId(0, downloadtask.uri, downloadtask.dms_uuid, false);
        if (j <= -1)
        {
            break MISSING_BLOCK_LABEL_283;
        }
        Log.e("UpDownloadEngine", (new StringBuilder()).append("record has downloaded before, delete =").append(downloadtask.uri).toString());
        deleteUpDownload(0, j, false);
        if (insertDownloadTask(downloadtask) > -1L)
        {
            i++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        mCursorLock.writeLock().unlock();
        if (!isChecking() && isUploadAndDownloadPoolIdle())
        {
            startFirstUpDownloadTask();
            return i;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean downloadTask(DownloadTask downloadtask)
    {
        SQLiteDatabase sqlitedatabase;
        Log.i("UpDownloadEngine", "downloadTask");
        if (isRelease() || downloadtask == null)
        {
            Log.e("UpDownloadEngine", "task is null");
            return false;
        }
        if (downloadtask.uri == null)
        {
            Log.e("UpDownloadEngine", "uri is null!");
            return false;
        }
        if (mDownloadPoolDriver != null && mDownloadPoolDriver.isTaskCanceling())
        {
            Log.e("UpDownloadEngine", "task is canceling!");
            ToastMgr.showToast(mContext, 0x7f0b0182, 0);
            return false;
        }
        mCursorLock.writeLock().lock();
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        sqlitedatabase.beginTransaction();
        int i = queryUpDownloadId(0, downloadtask.uri, downloadtask.dms_uuid, false);
        if (i <= -1)
        {
            break MISSING_BLOCK_LABEL_177;
        }
        Log.e("UpDownloadEngine", (new StringBuilder()).append("upload record has downloaded before, delete =").append(downloadtask.uri).toString());
        deleteUpDownload(0, i, false);
        long l;
        l = insertDownloadTask(downloadtask);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        mCursorLock.writeLock().unlock();
        Log.i("UpDownloadEngine", (new StringBuilder()).append("downloadTask id=").append(l).toString());
        if (l != -1L && !isChecking() && isUploadAndDownloadPoolIdle())
        {
            startFirstUpDownloadTask();
        }
        return true;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public boolean executeEasyTransfer(long l)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.executeEasyTransfer(l);
        } else
        {
            return false;
        }
    }

    public boolean executeEasyTransfer(String s)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.executeEasyTransfer(s);
        } else
        {
            return false;
        }
    }

    public int getAllDowloadingTaskCount()
    {
        Log.i("UpDownloadEngine", "getAllDowloadingTaskCount");
        if (isRelease())
        {
            return 0;
        }
        if (isChecking())
        {
            Log.e("UpDownloadEngine", "checking, please wait!");
            return 0;
        } else
        {
            int i = mUpDownloadDBMgr.getAllDownloadingCount();
            Log.i("UpDownloadEngine", (new StringBuilder()).append("getAllDowloadingTaskCount = ").append(i).toString());
            return i;
        }
    }

    public int getAllTaskCount()
    {
        Log.i("UpDownloadEngine", "getAllTaskCount");
        if (isRelease())
        {
            return 0;
        }
        if (isChecking())
        {
            Log.e("UpDownloadEngine", "checking, please wait!");
            return 0;
        } else
        {
            int i = mUpDownloadDBMgr.getAllCount();
            Log.i("UpDownloadEngine", (new StringBuilder()).append("getAllTaskCount = ").append(i).toString());
            return i;
        }
    }

    public int getCount()
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getCount();
        } else
        {
            return 0;
        }
    }

    public int getCurrentDownloadProgress()
    {
        return mCurrentDownloadProgress;
    }

    public Hashtable getDownloadStates()
    {
        return mDownloadMap;
    }

    public long getDownloadStatus(String s)
    {
        DownloadTask downloadtask = getDownloadTaskByUuid(s);
        if (downloadtask == null)
        {
            return 0L;
        } else
        {
            return downloadtask.status;
        }
    }

    DownloadTask getDownloadTaskByUuid(String s)
    {
        if (getDownloadStates() == null)
        {
            return null;
        } else
        {
            return (DownloadTask)getDownloadStates().get(s);
        }
    }

    public com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Result getEasyTransfer(long l)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getEasyTransfer(l);
        } else
        {
            return null;
        }
    }

    public com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Result getEasyTransfer(String s)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getEasyTransfer(s);
        } else
        {
            return null;
        }
    }

    public int getServerState(long l)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getServerState(l);
        } else
        {
            return 0;
        }
    }

    public int getServerState(String s)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getServerState(s);
        } else
        {
            return 0;
        }
    }

    public long getTableid(int i)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.getTableid(i);
        } else
        {
            return -1L;
        }
    }

    public AbsTaskItem getTaskItem(int i, Uri uri)
    {
        if (isRelease())
        {
            return null;
        }
        if (uri == null)
        {
            Log.e("UpDownloadEngine", "uri is null");
            return null;
        }
        if (isChecking())
        {
            Log.e("UpDownloadEngine", "checking, please wait!");
            return null;
        }
        mCursorLock.readLock().lock();
        if (i != 0) goto _L2; else goto _L1
_L1:
        Cursor cursor = mUpDownloadDBMgr.queryAllDownload();
_L4:
        if (cursor == null)
        {
            mCursorLock.readLock().unlock();
            return null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        cursor = null;
        if (i == 1)
        {
            cursor = mUpDownloadDBMgr.queryAllUpload();
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!cursor.moveToFirst())
        {
            mCursorLock.readLock().unlock();
            return null;
        }
        String s = uri.toString();
        do
        {
            boolean flag;
            if (cursor.getString(cursor.getColumnIndex("uri")).compareTo(s) == 0)
            {
                flag = true;
            } else
            {
                if (cursor.moveToNext())
                {
                    continue;
                }
                flag = false;
            }
            if (!flag)
            {
                mCursorLock.readLock().unlock();
                return null;
            }
            break;
        } while (true);
        int j = cursor.getInt(cursor.getColumnIndex("_id"));
        int k = cursor.getInt(cursor.getColumnIndex("state"));
        mCursorLock.readLock().unlock();
        Object obj;
        boolean flag1;
        if (k == 1)
        {
            if (i == 0)
            {
                obj = new DownloadTaskItem();
            } else
            {
                obj = new UploadTaskItem();
            }
            obj.state = 1;
            flag1 = true;
        } else
        {
            if (i == 0)
            {
                obj = new DownloadTaskItem();
                flag1 = mDownloadPoolDriver.getTask(j, ((AbsTaskItem) (obj)));
                if (!flag1)
                {
                    flag1 = mDtcpDownloadPoolDriver.getTask(j, ((AbsTaskItem) (obj)));
                }
            } else
            {
                flag1 = false;
                obj = null;
                if (i == 1)
                {
                    obj = new UploadTaskItem();
                    flag1 = mUploadPoolDriver.getTask(j, ((AbsTaskItem) (obj)));
                }
            }
            obj.state = 2;
        }
        if (!flag1)
        {
            obj = null;
        }
        return ((AbsTaskItem) (obj));
    }

    public AbsTaskItem getTaskItemByPos(int i)
    {
        Log.i("UpDownloadEngine", "getTaskItemByPos");
        Object obj;
        if (isRelease())
        {
            obj = null;
        } else
        {
            if (i < 0)
            {
                Log.e("UpDownloadEngine", "error position!");
                return null;
            }
            if (isChecking())
            {
                Log.e("UpDownloadEngine", "checking, please wait!");
                return null;
            }
            mCursorLock.readLock().lock();
            String as[] = {
                "type", "_id", "state", "uri", "title", "file_size", "dms_uuid"
            };
            Cursor cursor = mUpDownloadDBMgr.queryAll(as);
            if (cursor == null)
            {
                mCursorLock.readLock().unlock();
                return null;
            }
            int j = cursor.getCount();
            obj = null;
            if (j > 0)
            {
                obj = null;
                if (i < j)
                {
                    boolean flag = cursor.moveToPosition(i);
                    obj = null;
                    if (flag)
                    {
                        int k = cursor.getInt(cursor.getColumnIndex("type"));
                        if (k == 0)
                        {
                            obj = new DownloadTaskItem();
                        } else
                        {
                            obj = new UploadTaskItem();
                        }
                        obj.itemtype = k;
                        obj.dms_uuid = cursor.getString(cursor.getColumnIndex("dms_uuid"));
                        obj.id = cursor.getInt(cursor.getColumnIndex("_id"));
                        obj.state = cursor.getInt(cursor.getColumnIndex("state"));
                        obj.totalbytes = cursor.getInt(cursor.getColumnIndex("file_size"));
                        if (k == 0)
                        {
                            int j1 = cursor.getColumnIndex("title");
                            ((DownloadTaskItem)obj).title = cursor.getString(j1);
                            int k1 = cursor.getColumnIndex("uri");
                            ((DownloadTaskItem)obj).uri = cursor.getString(k1);
                        } else
                        {
                            int l = cursor.getColumnIndex("title");
                            ((UploadTaskItem)obj).title = cursor.getString(l);
                            int i1 = cursor.getColumnIndex("uri");
                            ((UploadTaskItem)obj).uri = cursor.getString(i1);
                        }
                    }
                }
            }
            cursor.close();
            mCursorLock.readLock().unlock();
            if (obj == null || ((AbsTaskItem) (obj)).state == 2 && (((AbsTaskItem) (obj)).itemtype != 0 || !mDownloadPoolDriver.getTask(((AbsTaskItem) (obj)).id, ((AbsTaskItem) (obj))) && !mDtcpDownloadPoolDriver.getTask(((AbsTaskItem) (obj)).id, ((AbsTaskItem) (obj)))) && (((AbsTaskItem) (obj)).itemtype != 1 || !mUploadPoolDriver.getTask(((AbsTaskItem) (obj)).id, ((AbsTaskItem) (obj)))))
            {
                return null;
            }
        }
        return ((AbsTaskItem) (obj));
    }

    void insertTaskToDownloadStatus(DownloadTask downloadtask)
    {
        if (downloadtask == null || downloadtask.item_uuid == null || mDownloadMap == null)
        {
            return;
        } else
        {
            mDownloadMap.put(downloadtask.item_uuid, downloadtask);
            return;
        }
    }

    public boolean isChecking()
    {
        return mServiceThread != null && mServiceThread.isAlive();
    }

    public boolean isDownloadPoolIdle()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isRelease();
        boolean flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        boolean flag2 = mDownloadPoolDriver.isThreadPoolActive();
        flag1 = false;
        if (!flag2)
        {
            flag1 = true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isFileDownloadFailed(String s)
    {
        return 4L == getDownloadStatus(s);
    }

    public boolean isFileDownloadWaiting(String s)
    {
        long l = getDownloadStatus(s);
        return 1L == l || 5L == l;
    }

    public boolean isFileDownloaded(String s)
    {
        return 3L == getDownloadStatus(s);
    }

    public boolean isFileDownloadedOrFailed(String s)
    {
        long l = getDownloadStatus(s);
        return 3L == l || 4L == l;
    }

    public boolean isFileDownloading(String s)
    {
        return 2L == getDownloadStatus(s);
    }

    public boolean isFileDownloadingOrWaiting(String s)
    {
        long l = getDownloadStatus(s);
        return 2L == l || 1L == l || 5L == l;
    }

    public boolean isTaskExist(String s, boolean flag)
    {
        if (getDownloadStates() == null)
        {
            return false;
        } else
        {
            return getDownloadStates().containsKey(s);
        }
    }

    public boolean isUploadAndDownloadPoolIdle()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isRelease();
        boolean flag1 = false;
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        boolean flag2 = mDownloadPoolDriver.isThreadPoolActive();
        flag1 = false;
        if (flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag3 = mUploadPoolDriver.isThreadPoolActive();
        flag1 = false;
        if (flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag4 = mDtcpDownloadPoolDriver.isThreadPoolActive();
        flag1 = false;
        if (!flag4)
        {
            flag1 = true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void onCreate(Application application, Context context)
    {
        boolean flag = true;
        Log.i("UpDownloadEngine", "onCreate");
        mApp = application;
        mContext = context;
        mToken = new UpDownloadToken(this);
        mCursorLock = new ReentrantReadWriteLock(false);
        UpDownloadDBMgr.initSingleton(application, null, mCursorLock);
        mUpDownloadDBMgr = UpDownloadDBMgr.instance();
        initDownloadStatusMap();
        setSDcardListener(flag);
        setDatabaseListener(flag);
        mUploadPoolDriver = new UploadPoolDriver();
        mUploadPoolDriver.init(application.getApplicationContext(), mUpDownloadDBMgr);
        mDownloadPoolDriver = new DownloadPoolDriver();
        mDownloadPoolDriver.init(application.getApplicationContext(), mUpDownloadDBMgr);
        mDtcpDownloadPoolDriver = new DTCPDownloadPoolDriver();
        mDtcpDownloadPoolDriver.init(application.getApplicationContext(), mUpDownloadDBMgr);
        mEasyTransferDriver = new EasyTransferDriver();
        mEasyTransferDriver.init(application, mUpDownloadDBMgr, mEasyTransferDriverListener, mCursorLock);
        String s = Settings.instance().getDefaultDMSUDN();
        Log.i("UpDownloadEngine", (new StringBuilder()).append("server udn=").append(s).toString());
        boolean flag1;
        if (s != null)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        mIsDmsConnection = flag1;
        if (NetworkUtil.getLocalIpViaWiFi(mContext) == null)
        {
            flag = false;
        }
        mIsWifiConnection = flag;
        Log.i("UpDownloadEngine", (new StringBuilder()).append("Is wifi connection=").append(mIsWifiConnection).append(", is dms connection=").append(mIsDmsConnection).toString());
        mDownloadDestination = Settings.instance().getDefaultDownloadDestination();
        mNetworkTool = new NetworkTool(context);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
        Settings.instance().registerSettingChangeListener(mSettingChangedListener);
        mNotificationManager = (NotificationManager)mContext.getSystemService("notification");
        mNotificationManager.cancel(0);
    }

    public void onDestroy()
    {
        Log.i("UpDownloadEngine", "onDestroy");
        mHandler.release();
        mHandler = null;
        mNetworkTool.setOnConnectivityChangeListener(null);
        mNetworkTool = null;
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
        Settings.instance().unregisterSettingChangeListener(mSettingChangedListener);
        mListeners.clear();
        stopServiceThread();
        setSDcardListener(false);
        setDatabaseListener(false);
        mUploadPoolDriver.uninit();
        mUploadPoolDriver = null;
        mDownloadPoolDriver.uninit();
        mDownloadPoolDriver = null;
        mDtcpDownloadPoolDriver.uninit();
        mDtcpDownloadPoolDriver = null;
        mEasyTransferDriver.uninit();
        mEasyTransferDriver = null;
        mUpDownloadDBMgr.releaseInstance();
        mUpDownloadDBMgr = null;
        endNotification(mCurTaskType, 2);
        mNotification = null;
        mNotificationManager = null;
        mCursorLock = null;
        mToken = null;
        if (mDownloadMap != null)
        {
            mDownloadMap.clear();
            mDownloadMap = null;
        }
        releaseTimerToken();
    }

    public void onStart()
    {
        Log.i("UpDownloadEngine", "onStart");
        mToken.onStart();
    }

    public void onStop()
    {
        Log.i("UpDownloadEngine", "onStop");
        mToken.onStop();
    }

    public void pauseTask(String s, Uri uri)
    {
    }

    public boolean registerEasyTransfer(com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Request request)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.registerEasyTransfer(request);
        } else
        {
            return false;
        }
    }

    public void registerListener(com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.IOnEasyTransferEngineListener ioneasytransferenginelistener)
    {
        if (mEasyTransferDriver != null)
        {
            mEasyTransferDriver.registerListener(ioneasytransferenginelistener);
        }
    }

    public void registerUpDownloadListener(IOnUpDownloadListener ionupdownloadlistener)
    {
        if (ionupdownloadlistener == null)
        {
            return;
        }
        synchronized (mListeners)
        {
            if (!mListeners.contains(ionupdownloadlistener))
            {
                break MISSING_BLOCK_LABEL_31;
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        mListeners.add(ionupdownloadlistener);
        arraylist;
        JVM INSTR monitorexit ;
    }

    public boolean resumeAllTask()
    {
        Log.i("UpDownloadEngine", "resumeAllTask");
        if (isRelease())
        {
            return false;
        }
        if (isChecking())
        {
            Log.e("UpDownloadEngine", "checking, please wait!");
            return false;
        }
        if (isUploadAndDownloadPoolIdle())
        {
            startServiceThread();
        }
        return true;
    }

    public void resumeTask(String s, Uri uri)
    {
    }

    public void retryTask(int i, String s, Uri uri)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("retryTask =").append(uri.toString()).toString());
        if (isRelease() || s == null || uri == null)
        {
            Log.e("UpDownloadEngine", "server uuid or uri is null");
        } else
        {
            if (isChecking())
            {
                Log.e("UpDownloadEngine", "checking, please wait!");
                return;
            }
            long l = queryUpDownloadId(i, uri.toString(), s, true);
            if (l > -1L)
            {
                updateUpDownloadState(i, l, 1L);
                if (!isChecking() && isUploadAndDownloadPoolIdle())
                {
                    startFirstUpDownloadTask();
                    return;
                }
            }
        }
    }

    public void start()
    {
        if (this != null)
        {
            onStart();
        }
    }

    public void stop()
    {
        if (mEasyTransferDriver != null)
        {
            mEasyTransferDriver.stop();
        }
    }

    public void unregisterListener(com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.IOnEasyTransferEngineListener ioneasytransferenginelistener)
    {
        if (mEasyTransferDriver != null)
        {
            mEasyTransferDriver.unregisterListener(ioneasytransferenginelistener);
        }
    }

    public void unregisterUpDownloadListener(IOnUpDownloadListener ionupdownloadlistener)
    {
        synchronized (mListeners)
        {
            mListeners.remove(ionupdownloadlistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean updateEasyTransfer(long l, com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Request request)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.updateEasyTransfer(l, request);
        } else
        {
            return false;
        }
    }

    public boolean updateEasyTransfer(String s, com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Request request)
    {
        if (mEasyTransferDriver != null)
        {
            return mEasyTransferDriver.updateEasyTransfer(s, request);
        } else
        {
            return false;
        }
    }

    public boolean uploadTask(UploadTask uploadtask)
    {
        String s;
        File file;
        SQLiteDatabase sqlitedatabase;
        Log.i("UpDownloadEngine", "uploadTask");
        if (isRelease() || uploadtask == null)
        {
            Log.e("UpDownloadEngine", "task is null");
            return false;
        }
        if (uploadtask.dms_uuid == null)
        {
            Log.e("UpDownloadEngine", "uuid is null");
            return false;
        }
        if (uploadtask.uri == null)
        {
            Log.e("UpDownloadEngine", "uri is null!");
            return false;
        }
        s = Uri.parse(uploadtask.uri).getPath();
        file = new File(s);
        if (!file.exists() || !file.isFile())
        {
            Log.e("UpDownloadEngine", (new StringBuilder()).append("upload file path fail =").append(s).toString());
            return false;
        }
        mCursorLock.writeLock().lock();
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        sqlitedatabase.beginTransaction();
        int i = queryUpDownloadId(1, uploadtask.uri, uploadtask.dms_uuid, false);
        if (i <= -1)
        {
            break MISSING_BLOCK_LABEL_224;
        }
        Log.e("UpDownloadEngine", (new StringBuilder()).append("upload record has uploaded before, delete =").append(uploadtask.uri).toString());
        deleteUpDownload(1, i, false);
        long l;
        l = insertUploadTask(uploadtask, s, file.length());
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        mCursorLock.writeLock().unlock();
        Log.i("UpDownloadEngine", (new StringBuilder()).append("uploadTask id=").append(l).toString());
        if (l != -1L && !isChecking() && isUploadAndDownloadPoolIdle())
        {
            startFirstUpDownloadTask();
        }
        return true;
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
    }

    public boolean uploadTask(ArrayList arraylist)
    {
        boolean flag;
        flag = true;
        Log.i("UpDownloadEngine", "uploadTasks");
        if (!isRelease() && arraylist != null && arraylist.size() >= flag) goto _L2; else goto _L1
_L1:
        Log.e("UpDownloadEngine", "all task is null");
        flag = false;
_L7:
        return flag;
_L2:
        SQLiteDatabase sqlitedatabase;
        mCursorLock.writeLock().lock();
        sqlitedatabase = mUpDownloadDBMgr.getManagerDataBase();
        sqlitedatabase.beginTransaction();
        Iterator iterator = arraylist.iterator();
_L5:
        UploadTask uploadtask;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_328;
        }
        uploadtask = (UploadTask)iterator.next();
        if (uploadtask.dms_uuid != null) goto _L4; else goto _L3
_L3:
        Log.e("UpDownloadEngine", "uuid is null");
          goto _L5
        Exception exception;
        exception;
        sqlitedatabase.endTransaction();
        throw exception;
_L4:
label0:
        {
            if (uploadtask.uri != null)
            {
                break label0;
            }
            Log.e("UpDownloadEngine", "uri is null!");
        }
          goto _L5
        String s;
        File file;
label1:
        {
            s = Uri.parse(uploadtask.uri).getPath();
            file = new File(s);
            if (file.exists() && file.isFile())
            {
                break label1;
            }
            Log.e("UpDownloadEngine", (new StringBuilder()).append("upload file path fail =").append(s).toString());
        }
          goto _L5
        int i = queryUpDownloadId(1, uploadtask.uri, uploadtask.dms_uuid, false);
        if (i <= -1)
        {
            break MISSING_BLOCK_LABEL_283;
        }
        Log.e("UpDownloadEngine", (new StringBuilder()).append("upload record has uploaded before, delete =").append(uploadtask.uri).toString());
        deleteUpDownload(1, i, false);
        long l = insertUploadTask(uploadtask, s, file.length());
        Log.i("UpDownloadEngine", (new StringBuilder()).append("uploadTask id=").append(l).toString());
          goto _L5
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        mCursorLock.writeLock().unlock();
        if (!isChecking() && isUploadAndDownloadPoolIdle())
        {
            startFirstUpDownloadTask();
            return flag;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    static 
    {
        DOWNLOAD_SDCARD_PATH = OEMConfig.DOWNLOAD_PATH;
    }



/*
    static int access$102(UpDownloadEngine updownloadengine, int i)
    {
        updownloadengine.mCurrentDownloadProgress = i;
        return i;
    }

*/












/*
    static boolean access$2002(UpDownloadEngine updownloadengine, boolean flag)
    {
        updownloadengine.mIsWifiConnection = flag;
        return flag;
    }

*/


/*
    static boolean access$2102(UpDownloadEngine updownloadengine, boolean flag)
    {
        updownloadengine.mIsDmsConnection = flag;
        return flag;
    }

*/


/*
    static String access$2202(UpDownloadEngine updownloadengine, String s)
    {
        updownloadengine.mDownloadDestination = s;
        return s;
    }

*/




/*
    static int access$2402(UpDownloadEngine updownloadengine, int i)
    {
        updownloadengine.mCheckFlag = i;
        return i;
    }

*/










/*
    static Notification access$3302(UpDownloadEngine updownloadengine, Notification notification)
    {
        updownloadengine.mNotification = notification;
        return notification;
    }

*/






}
