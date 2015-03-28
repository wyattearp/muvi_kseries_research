// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.datasource.db.SalixHttpUpdater;
import com.arcsoft.mediaplus.datasource.db.SalixRemoteDBMgr;
import com.arcsoft.mediaplus.listview.IItemListView;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.ui.ListViewGL;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.setting.SettingsActivity;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.FileDownloader;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.os.BatteryTool;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.util.os.StorageTool;
import com.arcsoft.util.tool.CachePathManager;
import com.arcsoft.util.tool.FileDelete;
import com.arcsoft.util.tool.ToastMgr;
import com.arcsoft.videostream.aee.AEESocketClient;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusBaseActivity, ListViewManager, ViewManager, MediaPlusApplication, 
//            DownloadFacade

public class MediaPlusActivity extends MediaPlusBaseActivity
{
    public class ConnectSocketTask extends AsyncTask
    {

        final MediaPlusActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            connectSocket();
            return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
        }

        public ConnectSocketTask()
        {
            this$0 = MediaPlusActivity.this;
            super();
        }
    }

    private class RequestFileThread extends Thread
    {

        private boolean stop;
        final MediaPlusActivity this$0;

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            int j;
            i = 0;
            j = 0;
_L9:
            if (!stop)
            {
                break MISSING_BLOCK_LABEL_18;
            }
            this;
            JVM INSTR monitorexit ;
            j;
            return;
            HttpURLConnection httpurlconnection;
            InputStream inputstream;
            Log.e("MediaPlusActivity", "FENG RequestFileThread *******************");
            httpurlconnection = (HttpURLConnection)(new URL("http://10.10.1.1/MOUNT.chipsipcmd")).openConnection();
            httpurlconnection.setConnectTimeout(5000);
            httpurlconnection.setReadTimeout(5000);
            httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.connect();
            inputstream = httpurlconnection.getInputStream();
            if (inputstream == null) goto _L2; else goto _L1
_L1:
            StringBuilder stringbuilder;
            InputStreamReader inputstreamreader;
            stringbuilder = new StringBuilder();
            inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
_L5:
            int l = inputstreamreader.read();
            if (l == -1) goto _L4; else goto _L3
_L3:
            stringbuilder.append((char)l);
              goto _L5
            MalformedURLException malformedurlexception;
            malformedurlexception;
            int k = j;
_L14:
            malformedurlexception.printStackTrace();
            if (i != 12) goto _L7; else goto _L6
_L6:
            switchToHandle(1);
            this;
            JVM INSTR monitorexit ;
            return;
_L11:
            this;
            JVM INSTR monitorexit ;
            Exception exception;
            throw exception;
_L4:
            inputstreamreader.close();
            inputstream.close();
            httpurlconnection.disconnect();
            Log.e("MediaPlusActivity", (new StringBuilder()).append("FENG sb.toString() = ").append(stringbuilder.toString()).toString());
            SalixHttpUpdater.setIsSDA(true);
            if (!stringbuilder.toString().contains("/var/tmp/usb/sdb1"))
            {
                break MISSING_BLOCK_LABEL_277;
            }
            SalixHttpUpdater.setIsSDA(false);
            if (mHandler != null)
            {
                mHandler.removeMessages(775);
                mHandler.sendEmptyMessage(775);
            }
            this;
            JVM INSTR monitorexit ;
            j;
            return;
            if (!stringbuilder.toString().contains("/var/tmp/usb/sda1")) goto _L2; else goto _L8
_L8:
            if (mHandler != null)
            {
                mHandler.removeMessages(775);
                mHandler.sendEmptyMessage(775);
            }
            this;
            JVM INSTR monitorexit ;
            j;
            return;
_L2:
            k = j + 1;
            if (j != 20)
            {
                break MISSING_BLOCK_LABEL_354;
            }
            switchToHandle(1);
            this;
            JVM INSTR monitorexit ;
            return;
            Thread.sleep(500L);
_L10:
            j = k;
              goto _L9
_L7:
            i++;
            Log.e("MediaPlusActivity", "FENG RequestFileThread **** MalformedURLException");
              goto _L10
_L13:
            IOException ioexception;
            ioexception.printStackTrace();
            if (i != 12)
            {
                break MISSING_BLOCK_LABEL_431;
            }
            if (mHandler != null)
            {
                mHandler.removeMessages(775);
                mHandler.sendEmptyMessage(775);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            i++;
            Log.e("MediaPlusActivity", "FENG RequestFileThread **** IOException");
              goto _L10
_L12:
            InterruptedException interruptedexception;
            interruptedexception.printStackTrace();
            if (i != 12)
            {
                break MISSING_BLOCK_LABEL_467;
            }
            switchToHandle(1);
            this;
            JVM INSTR monitorexit ;
            return;
            i++;
            Log.e("MediaPlusActivity", "FENG RequestFileThread **** InterruptedException");
              goto _L10
            exception;
            j;
              goto _L11
            interruptedexception;
              goto _L12
            ioexception;
              goto _L13
            malformedurlexception;
              goto _L14
            exception;
              goto _L11
            ioexception;
            k = j;
              goto _L13
            interruptedexception;
            k = j;
              goto _L12
        }

        public void stopRequest()
        {
            stop = true;
        }

        private RequestFileThread()
        {
            this$0 = MediaPlusActivity.this;
            super();
            stop = false;
        }

    }


    public static final int CONTEXT_MENU_DOWNLOAD = 306;
    public static final int CONTEXT_MENU_DOWNLOADING = 307;
    public static final int CONTEXT_MENU_INFO = 303;
    public static final int CONTEXT_MENU_PUSH = 301;
    public static final int CONTEXT_MENU_UPLOAD = 304;
    public static final int CONTEXT_MENU_UPLOADING = 305;
    public static final int CONTEXT_MENU_VIEW = 302;
    public static final int DELETE_PROGRESS_DIALOG_ID = 12294;
    public static final int DTCP_DOWNLOAD_CONFIRM = 12293;
    private static final String DTCP_DOWNLOAD_INDEX = "dtcp_download_index";
    static final String LAST_DLNA_SERVER = "last_dlna_server";
    public static final int MSG_CONNECT_SOCKET = 523;
    public static final int MSG_DELETE_FILES = 774;
    private static final int MSG_DELETING_CANCEL = 4097;
    private static final int MSG_DELETING_LOCAL_REFRESH = 4098;
    private static final int MSG_DELETING_REMOTE_REFRESH = 4096;
    public static final int MSG_FILE_DELETED = 778;
    public static final int MSG_FILE_DELETE_START = 777;
    public static final int MSG_REFRESH_CONTROL_BAR = 776;
    public static final int MSG_REFRESH_SELECTOR_TITLE = 772;
    public static final int MSG_REQUEST_SALIX_DATA = 524;
    public static final int MSG_REQUEST_SALIX_FILES = 775;
    public static final int MSG_SEND_COMMAND_FAILED = 550;
    public static final int MSG_SEND_RTSP_CMD = 549;
    public static final int MSG_SET_CANCEL_ALL_BTN_ENABLED = 552;
    public static final int MSG_SET_EXECUTING = 551;
    public static final int MSG_SHOW_HIDE_VIEWS = 526;
    public static final int MSG_SHOW_LOADING_DIALOG = 771;
    public static final int MSG_SHOW_MAIN_LAYOUT = 546;
    public static final int MSG_SHOW_SDCARD_VIEW = 545;
    public static final int MSG_SWITCH_CONTENT = 773;
    public static final int MSG_SWITCH_TO_STATE = 525;
    public static final int MSG_SWITCH_VIEW = 769;
    public static final int MSG_TOAST_MEDIA_SCANNER = 770;
    public static final int MSG_UPDATE_REFRESH_VIEW_LAYOUT = 547;
    public static final int OPTIONS_MENU_CLEARALL = 202;
    public static final int OPTIONS_MENU_MANAGER = 206;
    public static final int OPTIONS_MENU_SETTING = 201;
    public static final int REQUESTCODE_PLAY = 0x800001;
    public static final int START_ACTIVITY_REQUEST_CODE_COLLAGE = 0x800003;
    public static final int START_ACTIVITY_REQUEST_CODE_SETTING = 0x800002;
    public static final int START_ACTIVITY_REQUEST_CODE_SHARE = 0x800004;
    private static final String TAG = "MediaPlusActivity";
    public static final int TYPE_CHANNEL = 4;
    protected static final int TYPE_CONNECTED = 2;
    protected static final int TYPE_CONNECTING = 0;
    protected static final int TYPE_CONNECT_FAILED = 1;
    public static final int TYPE_ENCODEDFOLDER = 8;
    public static final int TYPE_LOCAL = 1;
    public static final int TYPE_REMOTE = 2;
    public static final int UPDOWNLOAD_BATTERYLOW_DIALOG_ID = 12292;
    public static final int UPDOWNLOAD_CANCELSINGLE_DIALOG_ID = 12290;
    public static final int UPDOWNLOAD_CLEARALL_DIALOG_ID = 12291;
    public static final int UPDOWNLOAD_MODE = 255;
    public static boolean mIsSharing = false;
    private final int REQUEST_EXCETION_COUNT_MAX = 12;
    private final int REQUEST_SOCKET_TIME = 5000;
    private final int REQUEST_TIME_MAX = 10000;
    private final int REQUEST_TIME_SPACE = 500;
    private RelativeLayout ViewLayout;
    protected android.view.View.OnClickListener contentListener;
    private boolean didBack;
    private boolean isExcuted;
    private final android.view.View.OnClickListener listListener = new android.view.View.OnClickListener() {

        final MediaPlusActivity this$0;

        public void onClick(View view)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(769);
                Message message = mHandler.obtainMessage(769);
                message.obj = view.getTag();
                mHandler.sendMessageDelayed(message, 50L);
            }
            return;
            exception;
            handler;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.BatteryTool.IOnBatteryChangeListener mBatteryChangeListener = new com.arcsoft.util.os.BatteryTool.IOnBatteryChangeListener() {

        final MediaPlusActivity this$0;

        public void OnBatteryLevelChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

        public void OnBatteryPlugedChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

        public void OnBatteryStatusChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private BatteryTool mBatteryTool;
    private boolean mBindServiceSuccessful;
    private IDlnaSettingBinder mBinder;
    private BroadcastReceiver mClearCacheReceiver;
    private ConnectSocketTask mConnectTask;
    public ServiceConnection mConnection;
    protected Context mContext;
    protected int mCurrentType;
    private final com.arcsoft.util.tool.FileDelete.onDeleteListener mDeleteListener = new com.arcsoft.util.tool.FileDelete.onDeleteListener() ;
    private Object mDeletingObj;
    protected Stack mDirectorieStack;
    private DownloadFacade mDownloadFacade;
    private FileDelete mFileDelete;
    protected Stack mGroupIDStack;
    protected Handler mHandler;
    private LayoutInflater mInflater;
    private boolean mIsDMSDeleteFailed;
    private boolean mIsPhotoViewerStart;
    private boolean mIsShowToast;
    private boolean mIsWifiConnected;
    private ListViewManager mListViewManager;
    private final com.arcsoft.util.os.StorageTool.IOnMediaScanListener mMediaScanListener = new com.arcsoft.util.os.StorageTool.IOnMediaScanListener() {

        final MediaPlusActivity this$0;

        public void onMediaScanFinished()
        {
            mHandler.removeMessages(770);
        }

        public void onMediaScanStarted()
        {
            mHandler.sendEmptyMessage(770);
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final MediaPlusActivity this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            boolean flag;
            flag = true;
            Log.e("MediaPlusActivity", (new StringBuilder()).append("MediaPlusActivity OnConnectivityChanged info.networkInfo.isConnected() = ").append(networkstateinfo.networkInfo.isConnected()).toString());
            if (networkstateinfo.networkInfo != null && networkstateinfo.networkInfo.getType() == flag) goto _L2; else goto _L1
_L1:
            return;
_L2:
            if (!networkstateinfo.networkInfo.isConnected())
            {
                break; /* Loop/switch isn't completed */
            }
            mIsWifiConnected = flag;
            AEESocketClient aeesocketclient1 = AEESocketClient.getInstanceClient();
            AEESocketClient aeesocketclient = aeesocketclient1;
_L4:
            if (RtspUtils.isAmbar() && aeesocketclient != null && aeesocketclient.isConnected() && aeesocketclient.getCurTokenId() > 0 && !aeesocketclient.getIsNeedEncoding() && !didBack)
            {
                initORequestRespondsListener();
                isExcuted = flag;
                MediaPlusActivity mediaplusactivity1 = MediaPlusActivity.this;
                IOException ioexception;
                if (!isExcuted)
                {
                    flag = false;
                }
                mediaplusactivity1.sengHandleMSGWithTime(551, flag, -1, null, 0);
                sendHandleCMD(0x10000013, -1, 0L);
                return;
            }
            if (true) goto _L1; else goto _L3
            ioexception;
            ioexception.printStackTrace();
            aeesocketclient = null;
              goto _L4
_L3:
            mIsWifiConnected = false;
            if (isRemote() && mListViewManager != null)
            {
                String s = getResources().getString(0x7f0b005c);
                Log.i("zdf", (new StringBuilder()).append("######## [MediaPlusActivity] OnConnectivityChanged(false), before sendMsgToUpdateTextStatus, strText = ").append(s).toString());
                ((ListViewGL)mListViewManager.getCurrentListView()).sendMsgToUpdateTextStatus(s, flag, 0);
            }
            if (mListViewManager != null && mListViewManager.getDownloadCount() > 0 && mUpDownloadEngine != null)
            {
                mUpDownloadEngine.cancelAllTask();
            }
            if (isRemote())
            {
                dismissLoadingDialog();
            }
            isExcuted = false;
            MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
            if (!isExcuted)
            {
                flag = false;
            }
            mediaplusactivity.sengHandleMSGWithTime(551, flag, -1, null, 0);
            return;
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private RelativeLayout mNoSdCardLayout;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final MediaPlusActivity this$0;

        public void onClick(View view)
        {
            view.getId();
            JVM INSTR tableswitch 2131296422 2131296422: default 24
        //                       2131296422 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            switchTo(0);
            if (mIsWifiConnected)
            {
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    if (!mSocketClient.isConnected())
                    {
                        switchTo(1);
                        return;
                    }
                }
                catch (IOException ioexception)
                {
                    switchTo(1);
                    ioexception.printStackTrace();
                    return;
                }
            } else
            {
                switchTo(1);
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private boolean mOnDeleteCanceled;
    private int mOnDeleteFileNum;
    private int mOnDeleteRecvCount;
    private int mOnDeleteSendCount;
    private Handler mOnDeletingHandler;
    private final com.arcsoft.videostream.aee.AEESocketClient.OnRequestRespondsListener mOnRequestRespondsListener = new com.arcsoft.videostream.aee.AEESocketClient.OnRequestRespondsListener() {

        final MediaPlusActivity this$0;

        public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
        {
            int l;
            l = 1;
            Log.e("MediaPlusActivity", (new StringBuilder()).append("testP onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
            i;
            JVM INSTR lookupswitch 2: default 92
        //                       -1: 178
        //                       268435475: 133;
               goto _L1 _L2 _L3
_L1:
            if (j == 0) goto _L5; else goto _L4
_L4:
            Log.e("testP", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
            sendCommandFailed(j, i, s1);
_L7:
            return;
_L3:
            isExcuted = false;
            MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
            if (!isExcuted)
            {
                l = 0;
            }
            mediaplusactivity.sengHandleMSGWithTime(551, l, -1, null, 0);
            return;
_L2:
            if (j != l || s1 == null) goto _L7; else goto _L6
_L6:
            sendCommandFailed(j, i, s1);
            return;
_L5:
            isExcuted = false;
            MediaPlusActivity mediaplusactivity1 = MediaPlusActivity.this;
            if (!isExcuted)
            {
                l = 0;
            }
            mediaplusactivity1.sengHandleMSGWithTime(551, l, -1, null, 0);
            return;
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private final ViewGroup mPanelView = null;
    private View mProgress;
    private ImageView mRefreshBtn;
    private RequestFileThread mRequestThread;
    private int mResultCode;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() ;
    private final com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener mSettingChangedListener = new com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener() {

        final MediaPlusActivity this$0;

        public void OnDefaultDownloadDestinationChanged(String s)
        {
        }

        public void OnDefaultRenderChanged(String s)
        {
        }

        public void OnDefaultServerChanged(String s)
        {
            whetherRemoveAllDownloadTask(s);
            if (isRemote())
            {
                showLoadingDialog();
                RemoteDBMgr.instance().setContentParam(false);
                if (mStrCurUDN != null && !mStrCurUDN.equals(s))
                {
                    if (isDigaActivity() && isRemote() && mCurrentType == 4)
                    {
                        RemoteDBMgr.instance().setCurrentServer(s, "AV", true);
                    } else
                    {
                        RemoteDBMgr.instance().setCurrentServer(s, null, true);
                    }
                }
            }
            onDMSAdded(s);
        }

        public void onBrowseModeChanged(boolean flag)
        {
            if (isRemote())
            {
                RemoteDBMgr.instance().setBrowseByFolder(flag);
            }
        }

        public void onSortModeChanged(boolean flag)
        {
        }

        public void onTVStreamingResolutionChange(boolean flag)
        {
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private AEESocketClient mSocketClient;
    private final com.arcsoft.util.os.StorageTool.IOnStorageStatusChangeListener mStorageListener = new com.arcsoft.util.os.StorageTool.IOnStorageStatusChangeListener() {

        final MediaPlusActivity this$0;

        public void onStorageChecking()
        {
        }

        public void onStorageMounted()
        {
        }

        public void onStorageUnmounted()
        {
            Toast.makeText(mContext, 0x7f0b0056, 1).show();
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    protected StorageTool mStorageTool;
    private String mStrAutoUDN;
    private String mStrCurUDN;
    private final ViewGroup mTitleView = null;
    private com.arcsoft.adk.atv.DLNA.UserToken mToken;
    protected UpDownloadEngine mUpDownloadEngine;
    private final com.arcsoft.util.tool.FileDelete.IRelatedDeleteList mUpDownloadEngineDeleteLis = new com.arcsoft.util.tool.FileDelete.IRelatedDeleteList() {

        final MediaPlusActivity this$0;

        public void delete(com.arcsoft.util.tool.FileDelete.DeleteData deletedata)
        {
            if (mUpDownloadEngine != null && deletedata != null)
            {
                mUpDownloadEngine.deleteDownloadedRecode(deletedata.filePath);
            }
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private DLNAService mUpDownloadServiceBinder;
    private final ServiceConnection mUpdownloadConnection = new ServiceConnection() {

        final MediaPlusActivity this$0;

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.i("MediaPlusActivity", "DLNAService connected");
            mUpDownloadServiceBinder = ((com.arcsoft.mediaplus.service.util.DLNAService.LocalBinder)ibinder).getService();
            mUpDownloadEngine = mUpDownloadServiceBinder.getUpDownloadEngine();
            mDownloadFacade.setUpDownloadEngine(mUpDownloadEngine);
            mListViewManager.setUpDownloadContext(mUpDownloadEngine);
            mUpDownloadEngine.controlSpeed(0, 0, 50);
            mUpDownloadEngine.controlSpeed(1, 1, 0);
            mUpDownloadEngine.onStart();
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.i("MediaPlusActivity", "DLNAService disconnected");
            mUpDownloadServiceBinder = null;
        }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
    };
    private ViewManager mViewManager;
    private Button m_BtnCancelDelete;
    private LoadingDialog m_DeleteLocalWaitDialog;
    private AlertDialog m_DeleteRemoteWaitDialog;
    private TextView m_DeletingProgressStatusTxt;

    public MediaPlusActivity()
    {
        mBatteryTool = null;
        mStorageTool = null;
        mCurrentType = -1;
        mDirectorieStack = new Stack();
        mGroupIDStack = new Stack();
        mToken = null;
        ViewLayout = null;
        mNoSdCardLayout = null;
        mInflater = null;
        mViewManager = null;
        mListViewManager = null;
        mProgress = null;
        mFileDelete = null;
        mDownloadFacade = null;
        mRequestThread = null;
        mNetworkTool = null;
        mSocketClient = null;
        mRefreshBtn = null;
        mIsWifiConnected = false;
        mIsShowToast = false;
        mResultCode = -1;
        mConnectTask = null;
        mStrAutoUDN = null;
        isExcuted = false;
        didBack = false;
        mOnDeleteFileNum = 0;
        mOnDeleteRecvCount = 0;
        mOnDeleteSendCount = -1;
        mDeletingObj = new Object();
        mOnDeleteCanceled = false;
        mIsDMSDeleteFailed = false;
        mStrCurUDN = null;
        contentListener = new android.view.View.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(View view)
            {
                Integer integer = (Integer)view.getTag();
                switchDevicesUninit(integer.intValue());
                synchronized (mHandler)
                {
                    mHandler.removeMessages(773);
                    Message message = mHandler.obtainMessage(773);
                    message.obj = integer;
                    mHandler.sendMessageDelayed(message, 50L);
                }
                return;
                exception;
                handler;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        };
        mClearCacheReceiver = null;
        mUpDownloadServiceBinder = null;
        mUpDownloadEngine = null;
        mHandler = new Handler() {

            final MediaPlusActivity this$0;

            public void handleMessage(Message message)
            {
                super.handleMessage(message);
                message.what;
                JVM INSTR lookupswitch 20: default 180
            //                           523: 957
            //                           524: 1033
            //                           525: 1041
            //                           526: 215
            //                           545: 1057
            //                           546: 1088
            //                           549: 1100
            //                           550: 1341
            //                           551: 1466
            //                           552: 393
            //                           769: 181
            //                           770: 285
            //                           771: 180
            //                           772: 313
            //                           773: 236
            //                           774: 424
            //                           775: 562
            //                           776: 570
            //                           777: 595
            //                           778: 711;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L1 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
                return;
_L12:
                if (mViewManager != null)
                {
                    mViewManager.switchToView(ViewManager.getViewStatus(), ((Integer)message.obj).intValue());
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L5:
                if (mListViewManager != null)
                {
                    mListViewManager.showHideViews();
                    return;
                }
                if (true) goto _L1; else goto _L15
_L15:
                Integer integer = (Integer)message.obj;
                Log.e("FENG", (new StringBuilder()).append("FENG MSG_SWITCH_CONTENT: contentType ").append(integer).toString());
                switchDevicesInit(integer.intValue(), 2);
                return;
_L13:
                Toast.makeText(mContext, 0x7f0b0055, 1).show();
                sendEmptyMessageDelayed(770, 3000L);
                return;
_L14:
                if (mViewManager != null)
                {
                    mViewManager.refreshSelectorTitle(((Integer)message.obj).intValue());
                    if (((Integer)message.obj).intValue() > 0)
                    {
                        mViewManager.setControlBarEnable(true);
                        return;
                    }
                    if (((Integer)message.obj).intValue() == 0)
                    {
                        mViewManager.setControlBarEnable(false);
                        return;
                    }
                }
                continue; /* Loop/switch isn't completed */
_L11:
                if (mViewManager != null)
                {
                    mViewManager.setCancelAllBtnEnabled(((Boolean)message.obj).booleanValue());
                    return;
                }
                if (true) goto _L1; else goto _L16
_L16:
                MediaItem amediaitem[] = mListViewManager.getSelectedItemList();
                if (mFileDelete != null && amediaitem != null)
                {
                    String as[] = getObjectIDsBySelectedIndexs(mListViewManager.getSelectedKeyList());
                    if (isRemote())
                    {
                        for (int i2 = 0; i2 < amediaitem.length; i2++)
                        {
                            if (mUpDownloadEngine != null && mUpDownloadEngine.isFileDownloadingOrWaiting(as[i2]))
                            {
                                ToastMgr.showToast(MediaPlusActivity.this, 0x7f0b0181, 0);
                                return;
                            }
                        }

                        mFileDelete.onDelete(as);
                        return;
                    } else
                    {
                        mFileDelete.onDelete(amediaitem);
                        return;
                    }
                }
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
_L17:
                SalixRemoteDBMgr.instance().requestServerData(true);
                return;
_L18:
                if (mViewManager != null)
                {
                    mViewManager.refreshControlBar(message.arg1);
                    return;
                }
                if (true) goto _L1; else goto _L19
_L19:
                char c;
                if (isRemote())
                {
                    showDeletingRemoteWaitDialog();
                    c = '\u1000';
                } else
                {
                    showDeletingLocalWaitDialog();
                    c = '\u1002';
                }
                if (mOnDeletingHandler != null && mOnDeleteFileNum >= 1)
                {
                    mOnDeletingHandler.sendEmptyMessageDelayed(c, 50L);
                }
                if (ViewManager.getViewStatus() == 2)
                {
                    if (!OEMConfig.OPENGL_OPTIMIZE)
                    {
                        ((GridView)mListViewManager.getCurrentListView()).setClickable(false);
                    }
                    mViewManager.setAllBtnEnable(false);
                    return;
                }
                if (true)
                {
                    continue; /* Loop/switch isn't completed */
                }
_L20:
                Log.v("zdf", (new StringBuilder()).append("MSG_FILE_DELETED, msg.arg1 = ").append(message.arg1).append(", msg.arg2 = ").append(message.arg2).append(", mIsDMSDeleteFailed = ").append(mIsDMSDeleteFailed).toString());
                if (ViewManager.getViewStatus() == 2)
                {
                    if (!OEMConfig.OPENGL_OPTIMIZE)
                    {
                        GridView gridview = (GridView)mListViewManager.getCurrentListView();
                        if (!gridview.isClickable())
                        {
                            gridview.setClickable(true);
                        }
                    }
                    if (mViewManager != null)
                    {
                        mViewManager.setAllBtnEnable(true);
                    }
                }
                if (isRemote())
                {
                    dmsDeleteRemoteWaitDialog();
                } else
                {
                    dmsDeleteLocalWaitDialog();
                }
                if (mListViewManager != null)
                {
                    mListViewManager.onFileDeleted();
                }
                if (mIsDMSDeleteFailed || message.arg1 < message.arg2 && !mOnDeleteCanceled)
                {
                    ToastMgr.showToast(mContext, 0x7f0b016d, 1);
                }
                mOnDeleteRecvCount = 0;
                mOnDeleteSendCount = 0;
                mOnDeleteFileNum = 0;
                mIsDMSDeleteFailed = false;
                return;
_L2:
                if (mIsWifiConnected)
                {
                    if (mConnectTask != null)
                    {
                        mConnectTask.cancel(true);
                        mConnectTask = null;
                    }
                    mConnectTask = new ConnectSocketTask();
                    mConnectTask.execute(new String[0]);
                    return;
                }
                if (true) goto _L1; else goto _L3
_L3:
                requestSalixFile();
                return;
_L4:
                int l1 = message.arg1;
                switchTo(l1);
                return;
_L6:
                MediaPlusActivity mediaplusactivity3 = MediaPlusActivity.this;
                boolean flag1;
                if (message.arg1 == 1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                mediaplusactivity3.showNoSdcard(flag1);
                return;
_L7:
                ViewLayout.setVisibility(0);
                return;
_L8:
                int k;
                int l;
                k = message.arg1;
                l = message.arg2;
                if (k == -1 && l == -1) goto _L1; else goto _L21
_L21:
                AEESocketClient aeesocketclient = AEESocketClient.getInstanceClient();
                if (aeesocketclient == null)
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (!aeesocketclient.isConnected())
                {
                    continue; /* Loop/switch isn't completed */
                }
                if (k == -1)
                {
                    k = l;
                    l = -1;
                }
                int i1;
                aeesocketclient.startRespondParams(k);
                i1 = aeesocketclient.sendCommand(k, null);
                aeesocketclient.setNextCMD(l);
                i1;
                JVM INSTR tableswitch 2 4: default 1492
            //                           2 1208
            //                           3 1231
            //                           4 1231;
                   goto _L22 _L23 _L24 _L24
_L23:
                try
                {
                    sendHandleCMD(k, l, 200L);
                    return;
                }
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                }
                return;
_L24:
                if (!isExcuted) goto _L1; else goto _L25
_L25:
                MediaPlusActivity mediaplusactivity1;
                isExcuted = false;
                mediaplusactivity1 = MediaPlusActivity.this;
                String s;
                int i;
                int j;
                int j1;
                MediaPlusActivity mediaplusactivity2;
                int k1;
                if (isExcuted)
                {
                    j1 = 1;
                } else
                {
                    j1 = 0;
                }
                mediaplusactivity1.sengHandleMSGWithTime(551, j1, -1, null, 0);
                return;
                if (!isExcuted) goto _L1; else goto _L26
_L26:
                isExcuted = false;
                mediaplusactivity2 = MediaPlusActivity.this;
                if (isExcuted)
                {
                    k1 = 1;
                } else
                {
                    k1 = 0;
                }
                mediaplusactivity2.sengHandleMSGWithTime(551, k1, -1, null, 0);
                return;
_L9:
                message.arg2;
                s = (String)message.obj;
                i = message.arg1;
                j = 0;
                i;
                JVM INSTR tableswitch 1 1: default 1384
            //                           1 1403;
                   goto _L27 _L28
_L27:
                if (j != 0)
                {
                    Toast.makeText(MediaPlusActivity.this, j, 0).show();
                    return;
                }
                  goto _L29
_L28:
                j = 0x7f0b018e;
                if (s != null && s.contains("16777218"))
                {
                    j = 0x7f0b018d;
                } else
                if (s != null && s.contains("16777220"))
                {
                    j = 0x7f0b0198;
                }
                mResultCode = j;
                if (true) goto _L27; else goto _L29
_L29:
                if (true) goto _L1; else goto _L30
_L30:
_L10:
                MediaPlusActivity mediaplusactivity = MediaPlusActivity.this;
                boolean flag;
                if (message.arg1 == 1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                mediaplusactivity.setExecuting(flag);
                return;
_L22:
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        };
        m_DeleteLocalWaitDialog = null;
        m_DeleteRemoteWaitDialog = null;
        m_BtnCancelDelete = null;
        m_DeletingProgressStatusTxt = null;
        mOnDeletingHandler = new Handler() {

            final MediaPlusActivity this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 4096 4098: default 32
            //                           4096 33
            //                           4097 136
            //                           4098 177;
                   goto _L1 _L2 _L3 _L4
_L1:
                return;
_L2:
                removeMessages(4096);
                if (m_DeleteRemoteWaitDialog != null && !mOnDeleteCanceled)
                {
                    String s3 = getString(0x7f0b0120);
                    String s4 = (new StringBuilder()).append(s3).append("(").append(mOnDeleteRecvCount).append("/").append(mOnDeleteFileNum).append(")").toString();
                    m_DeletingProgressStatusTxt.setText(s4);
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L3:
                removeMessages(4096);
                if (m_DeleteRemoteWaitDialog != null)
                {
                    String s2 = getString(0x7f0b0182);
                    m_DeletingProgressStatusTxt.setText(s2);
                    return;
                }
                if (true) goto _L1; else goto _L4
_L4:
                removeMessages(4098);
                if (m_DeleteLocalWaitDialog != null)
                {
                    String s = getString(0x7f0b0120);
                    String s1 = (new StringBuilder()).append(s).append("(").append(mOnDeleteRecvCount).append("/").append(mOnDeleteFileNum).append(")").toString();
                    m_DeleteLocalWaitDialog.setText(s1);
                    return;
                }
                if (true) goto _L1; else goto _L5
_L5:
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        };
        mIsPhotoViewerStart = false;
        mBinder = null;
        mBindServiceSuccessful = false;
        mConnection = new ServiceConnection() {

            final MediaPlusActivity this$0;

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                mBinder = com.arcsoft.mediaplus.service.util.IDlnaSettingBinder.Stub.asInterface(ibinder);
                mBindServiceSuccessful = true;
                Log.e("FENG", (new StringBuilder()).append("FENG onServiceConnected mBinder = ").append(mBinder).toString());
                if (mBinder == null) goto _L2; else goto _L1
_L1:
                String as[] = mBinder.getOnlineDmsUDN();
                if (as == null) goto _L2; else goto _L3
_L3:
                if (as.length <= 0) goto _L2; else goto _L4
_L4:
                int i = as.length;
                int j = 0;
_L9:
                if (j >= i) goto _L2; else goto _L5
_L5:
                String s = as[j];
                if (!mBinder.getDmsFriendlyName(s).equalsIgnoreCase("DMS for DV")) goto _L7; else goto _L6
_L6:
                mStrAutoUDN = s;
                setDefaultDMS(mStrAutoUDN);
_L2:
                return;
_L7:
                j++;
                if (true) goto _L9; else goto _L8
_L8:
                RemoteException remoteexception;
                remoteexception;
                remoteexception.printStackTrace();
                return;
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                mBinder = null;
                mBindServiceSuccessful = false;
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        };
    }

    private String GetDmsName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
        if (s != null)
        {
            if ((mediaserverdesc = DLNA.instance().getServerManager().getServerDesc(s)) != null)
            {
                return mediaserverdesc.m_strFriendlyName;
            }
        }
        return null;
    }

    private void bindDLNAService()
    {
        try
        {
            bindService(new Intent("MUVI.MediaPlus.DLNA.SERVICE"), mConnection, 1);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    private void cancelFileDeleting()
    {
        mOnDeleteCanceled = true;
        if (mOnDeletingHandler != null)
        {
            mOnDeletingHandler.sendEmptyMessage(4097);
        }
    }

    private void checkBattery(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo, int i)
    {
        if (batteryinfo.pluged == 0 && batteryinfo.maxlevel != 0 && (100 * batteryinfo.level) / batteryinfo.maxlevel < i && mUpDownloadEngine != null && mUpDownloadEngine.getAllTaskCount() > 0)
        {
            removeDialog(12292);
            showLoadingDialog();
        }
    }

    private boolean checkEnvironment(Context context)
    {
        long l = FileUtils.getSDCardAvailableSize();
        if (l < 0L)
        {
            Toast.makeText(mContext, 0x7f0b0056, 1).show();
            return true;
        }
        if (l < 10240L)
        {
            Toast.makeText(mContext, 0x7f0b0066, 1).show();
            return true;
        }
        if (l < 0x32000L)
        {
            Toast.makeText(mContext, 0x7f0b0065, 1).show();
        }
        if (FileUtils.getAndroidDataAvailableSize() < 5120L)
        {
            Toast.makeText(mContext, 0x7f0b0064, 0).show();
            return true;
        }
        if (isRemote() && NetworkUtil.getLocalIpViaWiFi(this) == null)
        {
            Toast.makeText(mContext, 0x7f0b015e, 0).show();
            return true;
        }
        long l1 = FileUtils.getMemoryAvailableSize(context);
        if (l1 > 0L && l1 < 5120L)
        {
            Toast.makeText(mContext, 0x7f0b0063, 0).show();
        }
        return false;
    }

    private boolean connectSocket()
    {
        Log.e("FENG", "FENG  connectSocket  ---------- IN");
        try
        {
            Log.e("testP", (new StringBuilder()).append("MediaPlusActivity connectSocket mSocketClient = ").append(mSocketClient).toString());
            mSocketClient = AEESocketClient.getInstanceClient();
            Log.e("MediaPlusActivity", (new StringBuilder()).append("connectSocket  -----isConnected = ").append(mSocketClient.isConnected()).toString());
            if (!mSocketClient.isConnected())
            {
                mSocketClient.connect();
            }
        }
        catch (IOException ioexception)
        {
            return false;
        }
        return true;
    }

    private void dmsDeleteLocalWaitDialog()
    {
        if (m_DeleteLocalWaitDialog != null)
        {
            m_DeleteLocalWaitDialog.dismiss();
            m_DeleteLocalWaitDialog = null;
        }
    }

    private void dmsDeleteRemoteWaitDialog()
    {
        if (mOnDeleteFileNum == 1)
        {
            dmsDeleteLocalWaitDialog();
        } else
        if (m_DeleteRemoteWaitDialog != null)
        {
            m_DeleteRemoteWaitDialog.dismiss();
            m_DeletingProgressStatusTxt = null;
            m_DeleteRemoteWaitDialog = null;
            return;
        }
    }

    private String[] getObjectIDsBySelectedIndexs(Integer ainteger[])
    {
        if (ainteger == null)
        {
            return null;
        }
        if (mListViewManager != null)
        {
            mListViewManager.clearIDsItems();
        }
        ArrayList arraylist = new ArrayList();
        int i = ainteger.length;
        for (int j = 0; j < i; j++)
        {
            Integer integer = ainteger[j];
            String s = mListViewManager.getCurrentListView().getDataSource().getRemoteItemUUID(integer.intValue());
            if (mListViewManager != null)
            {
                mListViewManager.putIDSItem(s, integer);
            }
            arraylist.add(s);
        }

        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    private void initORequestRespondsListener()
    {
        try
        {
            AEESocketClient.getInstanceClient().setOnRequestRespondsListener(mOnRequestRespondsListener);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void initSystemEvent()
    {
        mStorageTool = new StorageTool(this);
    }

    private void initUI()
    {
        mInflater = LayoutInflater.from(this);
        ViewLayout = (RelativeLayout)mInflater.inflate(0x7f030021, null);
        mNoSdCardLayout = (RelativeLayout)ViewLayout.findViewById(0x7f0900a8);
        mViewManager = new ViewManager(this, ViewLayout);
        mListViewManager = new ListViewManager(this, ViewLayout);
        mViewManager.setListViewManager(mListViewManager);
        if (mFileDelete == null)
        {
            mFileDelete = new FileDelete(this, mDeleteListener);
            mFileDelete.setRelatedDeleteLis(mUpDownloadEngineDeleteLis);
        }
        mProgress = ViewLayout.findViewById(0x7f0900a7);
        mProgress.setVisibility(8);
        mRefreshBtn = (ImageView)ViewLayout.findViewById(0x7f0900a6);
        mRefreshBtn.setVisibility(8);
        mRefreshBtn.setOnClickListener(mOnClickListener);
    }

    private void initUpdownloadService()
    {
        Intent intent = new Intent();
        intent.setAction("ArcSoft.DLNA.UPDWONLOAD.SERVICE");
        intent.setClass(this, com/arcsoft/mediaplus/service/util/DLNAService);
        bindService(intent, mUpdownloadConnection, 1);
    }

    private void onDMSAdded(String s)
    {
        Log.v("zdf", (new StringBuilder()).append("[MediaPlusActivity] onDMSAdded, strUDN = ").append(s).toString());
        if (Settings.instance().getDefaultDMSUDN() == null && s != null)
        {
            String s1 = GetDmsName(s);
            if (s1 != null && s1.equalsIgnoreCase("DMS for DV"))
            {
                setDefaultDMS(s);
            }
            mStrAutoUDN = s;
        }
    }

    private void onDeletedEnd(int i, int j)
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(778);
            Message message = mHandler.obtainMessage(778);
            message.arg1 = i;
            message.arg2 = j;
            mHandler.sendMessageDelayed(message, 5L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void onDeletingOnce(String s, int i)
    {
        if (i == 0 && mListViewManager != null)
        {
            IDataSource idatasource = mListViewManager.getCurrentListView().getDataSource();
            String s1 = ListViewManager.getObjId(s);
            Log.e("MediaPlusActivity", (new StringBuilder()).append("testP onDeletingOnce str_objId = ").append(s1).toString());
            Integer integer = mListViewManager.getIndex(s1);
            Log.e("MediaPlusActivity", (new StringBuilder()).append("testP onDeletingOnce sleectedIndex = ").append(integer).toString());
            MediaItem mediaitem = mListViewManager.getSelectedItem(integer);
            boolean flag = false;
            if (idatasource != null)
            {
                flag = false;
                if (mediaitem != null)
                {
                    flag = idatasource.delete(mediaitem);
                }
            }
            if (flag)
            {
                int j = RemoteDBMgr.instance().delteRemoteItem(s1);
                Log.e("MediaPlusActivity", (new StringBuilder()).append("testP OnDestroyObject del suc = ").append(j).toString());
                ListViewManager.removeSelectedItem(integer);
                mListViewManager.removeIDsItem(s1);
                ListViewManager.removeUpdateItem(s);
            }
        }
    }

    private void registerClearCacheReceiver()
    {
        if (mClearCacheReceiver != null)
        {
            return;
        } else
        {
            mClearCacheReceiver = new BroadcastReceiver() {

                final MediaPlusActivity this$0;

                public void onReceive(Context context, Intent intent)
                {
                    if (MediaPlusActivity.this != null && !isFinishing())
                    {
                        finish();
                    }
                }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("com.MUVI.MediaPlus.clear.cache");
            registerReceiver(mClearCacheReceiver, intentfilter);
            return;
        }
    }

    private void registerSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.setOnStorageStatusChangeListener(mStorageListener);
            mStorageTool.setOnMediaScanListener(mMediaScanListener);
        }
    }

    private void releaseUI()
    {
        mContext = null;
        if (mInflater != null)
        {
            mInflater = null;
        }
        if (mProgress != null)
        {
            mProgress = null;
        }
        if (mFileDelete != null)
        {
            mFileDelete = null;
        }
        if (mViewManager != null)
        {
            mViewManager.release();
            mViewManager = null;
        }
        dmsDeleteLocalWaitDialog();
        dmsDeleteRemoteWaitDialog();
        if (mNoSdCardLayout != null)
        {
            mNoSdCardLayout = null;
        }
        if (ViewLayout != null)
        {
            SystemUtils.unbindDrawables(ViewLayout);
            ViewLayout = null;
        }
    }

    private void sendCommandFailed(int i, int j, String s)
    {
        synchronized (mHandler)
        {
            if (mHandler != null)
            {
                Message message = mHandler.obtainMessage(550);
                message.arg1 = i;
                message.arg2 = j;
                message.obj = s;
                mHandler.removeMessages(550);
                mHandler.sendMessage(message);
            }
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void sendHandleCMD(int i, int j, long l)
    {
        synchronized (mHandler)
        {
            Message message = new Message();
            message.what = 549;
            message.arg1 = i;
            message.arg2 = j;
            mHandler.sendMessageDelayed(message, l);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void sengHandleMSGWithTime(int i, int j, int k, Object obj, int l)
    {
        synchronized (mHandler)
        {
            Message message = mHandler.obtainMessage(i);
            message.what = i;
            message.arg1 = j;
            message.arg2 = k;
            message.obj = obj;
            mHandler.removeMessages(i);
            mHandler.sendMessageDelayed(message, l);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void setDefaultDMS(String s)
    {
        mStrCurUDN = s;
        Settings.instance().setDefaultDMSUDN(s);
        String s1 = GetDmsName(s);
        if (s1 == null)
        {
            s1 = getString(0x7f0b001a);
        } else
        {
            String.format(getResources().getString(0x7f0b0185), new Object[] {
                s1
            });
        }
        Settings.instance().setDefaultDMSName(s1);
        Log.d("FENG", (new StringBuilder()).append("FENG Settings.instance().getDefaultDMSName(): ").append(Settings.instance().getDefaultDMSName()).toString());
        Log.d("FENG", (new StringBuilder()).append("FENG Settings.instance().getDefaultDMSUDN(): ").append(Settings.instance().getDefaultDMSUDN()).toString());
        RemoteDBMgr.instance().setCurrentServer(s, null, true);
    }

    private void setExecuting(boolean flag)
    {
        if (mViewManager != null)
        {
            mViewManager.setControlBarExecuting(flag);
        }
    }

    private void showDeletingLocalWaitDialog()
    {
        if (m_DeleteLocalWaitDialog == null)
        {
            m_DeleteLocalWaitDialog = new LoadingDialog(this, 0x7f0d0004);
            m_DeleteLocalWaitDialog.setText(0x7f0b0120);
        }
        try
        {
            m_DeleteLocalWaitDialog.setCancelable(false);
            m_DeleteLocalWaitDialog.show();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    private void showDeletingRemoteWaitDialog()
    {
        if (mOnDeleteFileNum == 1)
        {
            showDeletingLocalWaitDialog();
        } else
        {
            View view = LayoutInflater.from(this).inflate(0x7f03000e, null);
            if (view != null)
            {
                m_DeletingProgressStatusTxt = (TextView)view.findViewById(0x7f09004b);
                m_BtnCancelDelete = (Button)view.findViewById(0x7f09004c);
                m_BtnCancelDelete.setOnClickListener(new android.view.View.OnClickListener() {

                    final MediaPlusActivity this$0;

                    public void onClick(View view1)
                    {
                        cancelFileDeleting();
                    }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
                });
                m_DeleteRemoteWaitDialog = (new android.app.AlertDialog.Builder(this)).create();
                m_DeleteRemoteWaitDialog.setCancelable(false);
                m_DeleteRemoteWaitDialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {

                    final MediaPlusActivity this$0;

                    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
                    {
                        if (i == 4)
                        {
                            cancelFileDeleting();
                            return true;
                        } else
                        {
                            return false;
                        }
                    }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
                });
                mOnDeleteCanceled = false;
                m_DeleteRemoteWaitDialog.show();
                m_DeleteRemoteWaitDialog.getWindow().setContentView(view, new android.widget.RelativeLayout.LayoutParams(-2, -2));
                return;
            }
        }
    }

    private void showList()
    {
        String s;
        RemoteDBMgr.instance().setContentParam(false);
        if (isDigaActivity())
        {
            break MISSING_BLOCK_LABEL_51;
        }
        s = Settings.instance().getDefaultDMSUDN();
        mSocketClient = AEESocketClient.getInstanceClient();
        RemoteDBMgr.instance().setCurrentServer(s, null, mSocketClient.getIsNeedFreshFiles());
        mSocketClient.setIsNeedFreshFiles(false);
        return;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        return;
    }

    private void switchToHandle(int i)
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(525);
            Message message = mHandler.obtainMessage(525);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }
    }

    private void unbindDLNAService()
    {
        try
        {
            if (mConnection != null && mBinder != null && mBindServiceSuccessful)
            {
                unbindService(mConnection);
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    private void uninitMessage()
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(255);
            mHandler.removeMessages(769);
            mHandler.removeMessages(770);
            mHandler.removeMessages(771);
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    private void uninitSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.recycle();
            mStorageTool = null;
        }
    }

    private void uninitUpdownloadService()
    {
        if (mUpDownloadServiceBinder != null)
        {
            mUpDownloadEngine.controlSpeed(0, 1, 20);
            mUpDownloadEngine.controlSpeed(1, 1, 100);
            if (!Settings.instance().getUpDownloadStatusOnBackground())
            {
                mUpDownloadEngine.onStop();
            }
            unbindService(mUpdownloadConnection);
        }
        mUpDownloadEngine = null;
        mUpDownloadServiceBinder = null;
    }

    private void unregisterClearCacheReceiver()
    {
        if (mClearCacheReceiver != null)
        {
            unregisterReceiver(mClearCacheReceiver);
        }
        mClearCacheReceiver = null;
    }

    private void unregisterSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.setOnStorageStatusChangeListener(null);
            mStorageTool.setOnMediaScanListener(null);
        }
    }

    private void whetherRemoveAllDownloadTask(String s)
    {
        String s1 = getLastServerSharePerference();
        if (s != null && !s.equals("") && !s1.equals("") && !s1.equals(s) && mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
        writeLastServerToSharePerference(s);
    }

    protected void SDCardMount()
    {
        onResume();
    }

    protected void SDCardUnmount()
    {
        super.SDCardUnmount();
        onPause();
        sendMsgToShowNoSdcard(false);
    }

    protected void checkSetting()
    {
        if ("com.MUVI.start.mediaplus.online".equalsIgnoreCase(getIntent().getAction()))
        {
            if (Settings.instance().getDefaultDMSUDN() == null || isDigaActivity() && !DLNA.instance().getServerManager().isDigaDMS(Settings.instance().getDefaultDMSUDN()))
            {
                Toast.makeText(this, 0x7f0b005c, 1).show();
            } else
            if (RemoteDBMgr.instance().isReceivingData())
            {
                mHandler.sendEmptyMessage(771);
                return;
            }
        }
    }

    public void clearMarkView()
    {
        if (mListViewManager != null)
        {
            mListViewManager.clearSelectItems();
            if (ViewManager.getViewStatus() == 2)
            {
                refreshSelectorTitle(ListViewManager.getSelectedItemsNum());
            }
        }
    }

    protected void deleteFiles()
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(774);
            mHandler.sendEmptyMessage(774);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void deleteOldCacheIfNeeded()
    {
        try
        {
            FileUtils.deleteOldDBIfNeeded(this);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void dismissLoadingDialog()
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(771);
                safelyDismissDialog(12289);
            }
            return;
        } else
        {
            return;
        }
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void doItemSelect(int i, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc, long l)
    {
        if (remoteitemdesc != null || !isRemote()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        RemoteDBMgr remotedbmgr;
        String s;
        boolean flag;
        if (remoteitemdesc == null || remoteitemdesc.m_lProperty != 2L)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        Log.e("testP", "testP doItemSelect 1");
        remotedbmgr = RemoteDBMgr.instance();
        s = remoteitemdesc.m_strObjId;
        if (remoteitemdesc.m_PresentItem != null && remoteitemdesc.m_PresentItem.m_strPxnGroupId != null)
        {
            int k = remoteitemdesc.m_PresentItem.m_strPxnGroupId.length();
            flag = false;
            if (k != 0)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        flag = true;
        if (!remotedbmgr.setCurrentFolder(s, flag)) goto _L1; else goto _L3
_L3:
        mDirectorieStack.push(remoteitemdesc.m_strObjId);
        Stack stack = mGroupIDStack;
        String s1;
        if (remoteitemdesc.m_PresentItem == null)
        {
            s1 = null;
        } else
        {
            s1 = remoteitemdesc.m_PresentItem.m_strPxnGroupId;
        }
        stack.push(s1);
        return;
        Log.e("testP", "testP doItemSelect 2");
        int j = mListViewManager.getPlayDataSourceIndex(i);
        if (isRemote())
        {
            FileDownloader.uninitSingleton();
            FileDownloader.initSingleton(MediaPlusApplication.instance(), 1);
        }
        if (!mIsPhotoViewerStart)
        {
            mIsPhotoViewerStart = true;
            startView(j, false, l);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected boolean doMenuBack()
    {
        boolean flag = true;
        if (mDirectorieStack.size() <= 0 || !Settings.instance().getOnlineContentsListviewMode())
        {
            flag = false;
        } else
        {
            int i = mDirectorieStack.size();
            String s = null;
            String s1 = null;
            if (i > 0)
            {
                mDirectorieStack.pop();
                mGroupIDStack.pop();
                RemoteDBMgr remotedbmgr;
                try
                {
                    s = (String)mDirectorieStack.lastElement();
                    s1 = (String)mGroupIDStack.lastElement();
                }
                catch (NoSuchElementException nosuchelementexception)
                {
                    s = "0";
                    s1 = null;
                }
            }
            if (s != null)
            {
                remotedbmgr = RemoteDBMgr.instance();
                boolean flag1;
                if (s1 == null || s1.length() == 0)
                {
                    flag1 = flag;
                } else
                {
                    flag1 = false;
                }
                remotedbmgr.setCurrentFolder(s, flag1);
                return flag;
            }
        }
        return flag;
    }

    public void exitFileList()
    {
        if (mListViewManager != null && mListViewManager.getDownloadCount() > 0)
        {
            (new android.app.AlertDialog.Builder(mContext)).setTitle(0x7f0b0123).setMessage(0x7f0b0124).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

                final MediaPlusActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if (mUpDownloadEngine != null)
                    {
                        mUpDownloadEngine.cancelAllTask();
                    }
                    dialoginterface.dismiss();
                    setResult(mResultCode);
                    finish();
                }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

                final MediaPlusActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
            }).show();
            return;
        } else
        {
            setResult(mResultCode);
            finish();
            return;
        }
    }

    public int getCurrentViewType()
    {
        return mListViewManager.getCurrentType();
    }

    public DownloadFacade getDownloadFacade()
    {
        return mDownloadFacade;
    }

    String getLastServerSharePerference()
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getPackageName(), 3);
        if (sharedpreferences == null)
        {
            return "";
        } else
        {
            return sharedpreferences.getString("last_dlna_server", "");
        }
    }

    public boolean hasDefaultDMS()
    {
        return Settings.instance().getDefaultDMSUDN() != null;
    }

    void initNetworkLis()
    {
        mNetworkTool = new NetworkTool(this);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
    }

    public boolean isDigaActivity()
    {
        return false;
    }

    public boolean isRemote()
    {
        if (mListViewManager != null)
        {
            return mListViewManager.isRemote();
        } else
        {
            return false;
        }
    }

    public boolean isWifiConnected()
    {
        return mIsWifiConnected;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        Log.i("MediaPlusActivity", (new StringBuilder()).append("onActivityResult : requestCode = ").append(i).toString());
        Log.i("MediaPlusActivity", (new StringBuilder()).append("onActivityResult : resultCode = ").append(j).toString());
        if (FileUtils.hasStorage(false)) goto _L2; else goto _L1
_L1:
        Toast.makeText(this, 0x7f0b0056, 1).show();
        setResult(mResultCode);
        finish();
_L8:
        return;
_L2:
        i;
        JVM INSTR tableswitch 8388609 8388612: default 116
    //                   8388609 124
    //                   8388610 238
    //                   8388611 261
    //                   8388612 280;
           goto _L3 _L4 _L5 _L6 _L7
_L3:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_280;
_L9:
        super.onActivityResult(i, j, intent);
        return;
_L4:
        mIsPhotoViewerStart = false;
        if (isRemote())
        {
            FileDownloader.uninitSingleton();
            FileDownloader.initSingleton(MediaPlusApplication.instance(), 5);
        }
        int k;
        if (intent != null)
        {
            k = intent.getIntExtra("Param.PushTo.CurIndex", 0);
        } else
        {
            k = 0;
            if (intent == null)
            {
                boolean flag = isRemote();
                k = 0;
                if (flag)
                {
                    mListViewManager.refreshTextView();
                    k = 0;
                }
            }
        }
        Log.i("MediaPlusActivity", (new StringBuilder()).append("REQUESTCODE_PLAY: index = ").append(k).toString());
        if (mListViewManager != null)
        {
            mListViewManager.setItemVisibleInScreen(k);
            return;
        }
          goto _L8
_L5:
        if (isRemote() && !RemoteDBMgr.instance().isReceivingData())
        {
            dismissLoadingDialog();
        }
          goto _L9
_L6:
        if (!isRemote() && j == -1)
        {
            clearMarkView();
        }
          goto _L9
        mIsSharing = false;
          goto _L9
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getWindow().addFlags(0x20000);
    }

    public void onBackPressed()
    {
        didBack = true;
        ViewManager.getViewStatus();
        JVM INSTR tableswitch 0 4: default 44
    //                   0 52
    //                   1 52
    //                   2 64
    //                   3 51
    //                   4 94;
           goto _L1 _L2 _L2 _L3 _L4 _L5
_L1:
        if (!isExcuted)
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        return;
_L2:
        if (isExcuted) goto _L4; else goto _L6
_L6:
        exitFileList();
        return;
_L3:
        MediaPlusActivity mediaplusactivity = (MediaPlusActivity)mContext;
        boolean flag = isRemote();
        int i = 0;
        if (!flag)
        {
            i = 1;
        }
        mediaplusactivity.switchView(i);
        return;
_L5:
        ((MediaPlusActivity)mContext).switchView(0);
        return;
        setResult(mResultCode);
        super.onBackPressed();
        return;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        Log.e("FENG", "FENG MediaPlusActivity onConfigurationChanged ");
        if (ViewLayout != null)
        {
            ViewLayout.setVisibility(4);
        }
        ViewTreeObserver viewtreeobserver = ViewLayout.getViewTreeObserver();
        if (viewtreeobserver != null)
        {
            viewtreeobserver.addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                final MediaPlusActivity this$0;

                public void onGlobalLayout()
                {
                    if (ViewLayout != null)
                    {
                        ViewLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mHandler.sendEmptyMessageDelayed(546, 200L);
                    }
                }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
            });
        }
        mListViewManager.changeGridLayoutParams();
        if (mViewManager != null)
        {
            mViewManager.onConfigurationChanged();
        }
        if (mIsSharing)
        {
            mListViewManager.onSharingConfigurationChanged();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        deleteOldCacheIfNeeded();
        mDownloadFacade = new DownloadFacade(mContext);
        mStrAutoUDN = null;
        initUI();
        setContentView(ViewLayout);
        mContext = this;
        mToken = DLNA.instance().lockUserToken();
        if (CachePathManager.instance().isClearing())
        {
            Toast.makeText(this, 0x7f0b0067, 1).show();
            finish();
            return;
        }
        didBack = false;
        initNetworkLis();
        checkSetting();
        initSystemEvent();
        initUpdownloadService();
        registerClearCacheReceiver();
        mBatteryTool = new BatteryTool(mContext);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
        Settings.instance().registerSettingChangeListener(mSettingChangedListener);
        mViewManager.refreshTitleAndControlbar(-1, ViewManager.getViewStatus());
        synchronized (mHandler)
        {
            mHandler.removeMessages(773);
            Message message = mHandler.obtainMessage(773);
            message.obj = Integer.valueOf(mListViewManager.getCurrentType());
            mHandler.sendMessageDelayed(message, 0L);
        }
        bindDLNAService();
        mStrCurUDN = Settings.instance().getDefaultDMSUDN();
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected Dialog onCreateDialog(int i, final Bundle args)
    {
        i;
        JVM INSTR tableswitch 12289 12293: default 36
    //                   12289 38
    //                   12290 57
    //                   12291 117
    //                   12292 173
    //                   12293 229;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return null;
_L2:
        if (isRemote())
        {
            return new LoadingDialog(this, 0x7f0d0004);
        }
          goto _L1
_L3:
        return (new android.app.AlertDialog.Builder(mContext)).setTitle(0x7f0b00a4).setMessage(0x7f0b00a8).setCancelable(true).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;
            final Bundle val$args;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                if (args.getInt("state") == 0)
                {
                    ListViewManager listviewmanager = mListViewManager;
                    boolean flag;
                    if (!mListViewManager.isRemote())
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    listviewmanager.cancelUpdownload(Boolean.valueOf(flag), args.getInt("position"));
                }
            }

            
            {
                this$0 = MediaPlusActivity.this;
                args = bundle;
                super();
            }
        }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).create();
_L4:
        return (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b00a5).setMessage(0x7f0b00a9).setCancelable(true).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                mListViewManager.cancelAllUpdownload();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).create();
_L5:
        return (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b00a6).setMessage(0x7f0b00af).setCancelable(false).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                mListViewManager.cancelAllUpdownload();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).create();
_L6:
        return (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b00b0).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;
            final Bundle val$args;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                int k = args.getInt("dtcp_download_index");
                mListViewManager.addUpdownload(Boolean.valueOf(false), k);
            }

            
            {
                this$0 = MediaPlusActivity.this;
                args = bundle;
                super();
            }
        }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

            final MediaPlusActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface.cancel();
            }

            
            {
                this$0 = MediaPlusActivity.this;
                super();
            }
        }).create();
    }

    protected void onDestroy()
    {
        Log.e("FENG", "FENG onDestroy() ---------------- IN");
        mIsSharing = false;
        if (mConnectTask != null)
        {
            mConnectTask.cancel(true);
            mConnectTask = null;
        }
        if (mNetworkTool != null)
        {
            mNetworkTool.setOnConnectivityChangeListener(null);
            mNetworkTool = null;
        }
        if (mRequestThread != null)
        {
            mRequestThread.stopRequest();
            mRequestThread = null;
        }
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
        Settings.instance().unregisterSettingChangeListener(mSettingChangedListener);
        RemoteDBMgr.instance().setServerStatusListener(null);
        RemoteDBMgr.instance().cancelServerDataRequest();
        RemoteDBMgr.instance().setCurrentServer(null, null, true);
        dismissLoadingDialog();
        mListViewManager.destroy();
        uninitMessage();
        uninitUpdownloadService();
        uninitSystemEvent();
        unregisterClearCacheReceiver();
        unbindDLNAService();
        if (mToken != null)
        {
            DLNA.instance().releaseUserToken(mToken);
            mToken = null;
        }
        releaseUI();
        ViewManager.setViewStatus(0);
        super.onDestroy();
        System.gc();
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        int j;
        flag = true;
        j = 0xff & mCurrentType;
        i;
        JVM INSTR tableswitch 1 4: default 44
    //                   1 53
    //                   2 79
    //                   3 44
    //                   4 105;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        flag = super.onKeyUp(i, keyevent);
_L6:
        return flag;
_L2:
        if (j == flag)
        {
            switchView(4);
            return flag;
        }
        if (j != 4) goto _L6; else goto _L5
_L5:
        switchView(2);
        return flag;
_L3:
        if (j == 2)
        {
            switchView(4);
            return flag;
        }
        if (j != 4) goto _L6; else goto _L7
_L7:
        switchView(flag);
        return flag;
_L4:
        if (doMenuBack())
        {
            return flag;
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    protected void onPause()
    {
        super.onPause();
        mListViewManager.pause();
        if (mBatteryTool != null)
        {
            mBatteryTool.setOnBatteryChangeListener(null);
        }
        unregisterSystemEvent();
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.controlSpeed(0, 1, 20);
            mUpDownloadEngine.controlSpeed(1, 1, 100);
        }
        if (mToken != null)
        {
            Log.w("DLNA Service", "Release Token in MediaSee activity");
            DLNA.instance().releaseUserToken(mToken);
            mToken = null;
        }
    }

    protected void onResume()
    {
        super.onResume();
        Log.w("MediaPlusActivity", "onResume()");
        if (!isSDCardAvailable())
        {
            showNoSdcard(true);
            if (mViewManager != null)
            {
                mViewManager.showControlBar(4);
            }
            return;
        }
        showNoSdcard(false);
        if (mViewManager != null)
        {
            mViewManager.showControlBar(0);
        }
        mToken = DLNA.instance().lockUserToken();
        if (isRemote() && NetworkUtil.getLocalIpViaWiFi(this) == null)
        {
            mListViewManager.setUpDownloadContext(mUpDownloadEngine);
            mListViewManager.resume();
            return;
        }
        if (mBatteryTool != null)
        {
            mBatteryTool.setOnBatteryChangeListener(mBatteryChangeListener);
        }
        if (isRemote())
        {
            if (RemoteDBMgr.instance().isReceivingData())
            {
                mHandler.sendEmptyMessage(771);
            } else
            {
                dismissLoadingDialog();
            }
        }
        mListViewManager.setUpDownloadContext(mUpDownloadEngine);
        mListViewManager.resume();
        registerSystemEvent();
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.controlSpeed(0, 0, 50);
            mUpDownloadEngine.controlSpeed(1, 1, 0);
        }
        mDownloadFacade.setContext(this);
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return motionevent.getAction() == 0;
    }

    protected void prepareIntent(Intent intent, boolean flag)
    {
        com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter;
        String s;
        datasourcefilter = mListViewManager.getPlayDataSourceFilter();
        s = "";
        datasourcefilter.mediatype;
        JVM INSTR lookupswitch 4: default 60
    //                   1: 105
    //                   2: 97
    //                   4: 113
    //                   64: 97;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L4:
        break MISSING_BLOCK_LABEL_113;
_L5:
        intent.setType(s);
        intent.putExtra("datasource_filter", datasourcefilter);
        int i;
        if (mListViewManager == null)
        {
            i = 1;
        } else
        {
            i = mListViewManager.getCurrentType();
        }
        intent.putExtra("Param.isLocalContent", i);
        return;
_L3:
        s = "image/*";
          goto _L5
_L2:
        s = "audio/*";
          goto _L5
        s = "video/*";
          goto _L5
    }

    public void refreshControlBar(int i)
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(776);
            Message message = mHandler.obtainMessage(776);
            message.arg1 = i;
            mHandler.sendMessage(message);
        }
    }

    public void refreshMarkView()
    {
        int i = ListViewManager.getSelectedItemsNum();
        int j = mListViewManager.getTotalMediaCount();
        mListViewManager.clearSelectItems();
        if (i != j)
        {
            mListViewManager.selectAll();
        }
        mListViewManager.refreshCurrentView(false);
    }

    public void refreshRemoteView()
    {
        String s = Settings.instance().getDefaultDMSUDN();
        if (s != null)
        {
            RemoteDBMgr.instance().setCurrentServer(s, null, true);
        }
    }

    public void refreshSelectorTitle(int i)
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(772);
            Message message = mHandler.obtainMessage(772);
            message.obj = Integer.valueOf(i);
            mHandler.sendMessageDelayed(message, 50L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void requestSalixFile()
    {
        Log.e("FENG", "FENG requestSalixFile IN ------------------");
        if (mRequestThread != null)
        {
            mRequestThread.stopRequest();
            mRequestThread = null;
        }
        if (!mIsWifiConnected)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient != null && mSocketClient.isConnected())
        {
            switchToHandle(0);
            mRequestThread = new RequestFileThread();
            mRequestThread.start();
        }
        return;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        return;
    }

    public void safelyDismissDialog(int i)
    {
        try
        {
            dismissDialog(i);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }

    void sendMsgToShowNoSdcard(boolean flag)
    {
        if (mHandler == null)
        {
            return;
        }
        Message message = new Message();
        message.what = 545;
        if (flag)
        {
            message.arg1 = 1;
        } else
        {
            message.arg1 = 0;
        }
        mHandler.sendMessage(message);
    }

    public void setCancelAllBtnEnabled(boolean flag)
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(552);
            Message message = mHandler.obtainMessage(552);
            message.obj = Boolean.valueOf(flag);
            mHandler.sendMessageDelayed(message, 0L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setProgressVisible(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        if (mProgress != null && mProgress.getVisibility() != i)
        {
            mProgress.setVisibility(i);
        }
    }

    public void showHideViews()
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(526);
            Message message = mHandler.obtainMessage(526);
            mHandler.sendMessage(message);
        }
    }

    public void showLoadingDialog()
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(771);
                Message message = mHandler.obtainMessage(771);
                mHandler.sendMessage(message);
            }
            return;
        } else
        {
            return;
        }
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void showNoSdcard(boolean flag)
    {
        if (flag)
        {
            mNoSdCardLayout.setVisibility(0);
            return;
        } else
        {
            mNoSdCardLayout.setVisibility(8);
            return;
        }
    }

    protected void startSettingActivity()
    {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingsActivity);
        mContext.startActivity(intent);
    }

    protected void startView(int i, boolean flag, long l)
    {
        if (flag)
        {
            String s1 = Settings.instance().getDefaultDMRUDN();
            if (s1 == null)
            {
                Toast.makeText(mContext, 0x7f0b005b, 0).show();
                return;
            }
            if (!DLNA.instance().getRenderManager().isRenderOnline(s1))
            {
                Toast.makeText(mContext, 0x7f0b005a, 0).show();
                return;
            }
        }
        String s;
        Intent intent;
        if (flag)
        {
            s = "MUVI.mediaplus.intent.action.PUSHTO";
        } else
        {
            s = "MUVI.mediaplus.intent.action.VIEW";
        }
        intent = new Intent(s);
        intent.putExtra("Param.PushTo.CurIndex", i);
        intent.putExtra("Param.PushTo.CurPosition", l);
        prepareIntent(intent, flag);
        startActivityForResult(intent, 0x800001);
        Log.e("FENG", (new StringBuilder()).append("FENG startView Exit: index = ").append(i).append(", startPos = ").append(l).append(", pushto = ").append(flag).toString());
    }

    public void switchContentView(int i)
    {
        switchDevicesUninit(i);
        synchronized (mHandler)
        {
            mHandler.removeMessages(773);
            Message message = mHandler.obtainMessage(773);
            message.obj = Integer.valueOf(i);
            mHandler.sendMessageDelayed(message, 0L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void switchDevicesInit(int i, int j)
    {
        Log.d("FENG", (new StringBuilder()).append("FENG switchDevicesInit ************* contentType = ").append(i).toString());
        if (i != 0) goto _L2; else goto _L1
_L1:
        if (NetworkUtil.getLocalIpViaWiFi(this) != null || mIsShowToast) goto _L4; else goto _L3
_L3:
        Toast.makeText(mContext, 0x7f0b015e, 0).show();
        mIsShowToast = true;
_L6:
        if (i == 0)
        {
            showList();
        }
        mListViewManager.setCurrentListView(i, true);
        return;
_L4:
        if ((Settings.instance().getDefaultDMSUDN() == null || isDigaActivity() && !DLNA.instance().getServerManager().isDigaDMS(Settings.instance().getDefaultDMSUDN())) && !mIsShowToast)
        {
            Toast.makeText(this, 0x7f0b005c, 1).show();
            mIsShowToast = true;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        switchTo(2);
        if (mListViewManager != null)
        {
            Log.i("zdf", "######## [MediaPlusActivity] switchDevicesInit, before sendMsgToUpdateTextStatus false");
            ((ListViewGL)mListViewManager.getCurrentListView()).sendMsgToUpdateTextStatus("", false, 0);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void switchDevicesUninit(int i)
    {
        if (isRemote())
        {
            RemoteDBMgr.instance().cancelServerDataRequest();
        }
        mListViewManager.releaseLists(false);
        mDirectorieStack.clear();
        mGroupIDStack.clear();
    }

    protected void switchTo(int i)
    {
        Log.e("MediaPlusActivity", (new StringBuilder()).append("switch to type = ").append(i).toString());
        i;
        JVM INSTR tableswitch 0 2: default 52
    //                   0 53
    //                   1 61
    //                   2 69;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if (isRemote())
        {
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (isRemote())
        {
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (isRemote())
        {
            return;
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    protected void switchView(int i)
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(769);
            Message message = mHandler.obtainMessage(769);
            message.obj = Integer.valueOf(i);
            mHandler.sendMessageDelayed(message, 0L);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void writeLastServerToSharePerference(String s)
    {
        SharedPreferences sharedpreferences = getSharedPreferences(getPackageName(), 3);
        if (sharedpreferences == null)
        {
            return;
        } else
        {
            sharedpreferences.edit().putString("last_dlna_server", s).commit();
            return;
        }
    }




/*
    static boolean access$002(MediaPlusActivity mediaplusactivity, boolean flag)
    {
        mediaplusactivity.mIsDMSDeleteFailed = flag;
        return flag;
    }

*/




/*
    static int access$102(MediaPlusActivity mediaplusactivity, int i)
    {
        mediaplusactivity.mOnDeleteFileNum = i;
        return i;
    }

*/



/*
    static boolean access$1102(MediaPlusActivity mediaplusactivity, boolean flag)
    {
        mediaplusactivity.isExcuted = flag;
        return flag;
    }

*/






/*
    static DLNAService access$1502(MediaPlusActivity mediaplusactivity, DLNAService dlnaservice)
    {
        mediaplusactivity.mUpDownloadServiceBinder = dlnaservice;
        return dlnaservice;
    }

*/








/*
    static int access$202(MediaPlusActivity mediaplusactivity, int i)
    {
        mediaplusactivity.mOnDeleteRecvCount = i;
        return i;
    }

*/


/*
    static int access$208(MediaPlusActivity mediaplusactivity)
    {
        int i = mediaplusactivity.mOnDeleteRecvCount;
        mediaplusactivity.mOnDeleteRecvCount = i + 1;
        return i;
    }

*/



/*
    static String access$2102(MediaPlusActivity mediaplusactivity, String s)
    {
        mediaplusactivity.mStrAutoUDN = s;
        return s;
    }

*/




/*
    static IDlnaSettingBinder access$2302(MediaPlusActivity mediaplusactivity, IDlnaSettingBinder idlnasettingbinder)
    {
        mediaplusactivity.mBinder = idlnasettingbinder;
        return idlnasettingbinder;
    }

*/














/*
    static ConnectSocketTask access$3402(MediaPlusActivity mediaplusactivity, ConnectSocketTask connectsockettask)
    {
        mediaplusactivity.mConnectTask = connectsockettask;
        return connectsockettask;
    }

*/



/*
    static int access$3502(MediaPlusActivity mediaplusactivity, int i)
    {
        mediaplusactivity.mResultCode = i;
        return i;
    }

*/








/*
    static int access$402(MediaPlusActivity mediaplusactivity, int i)
    {
        mediaplusactivity.mOnDeleteSendCount = i;
        return i;
    }

*/


/*
    static boolean access$4202(MediaPlusActivity mediaplusactivity, boolean flag)
    {
        mediaplusactivity.mBindServiceSuccessful = flag;
        return flag;
    }

*/



/*
    static AEESocketClient access$4302(MediaPlusActivity mediaplusactivity, AEESocketClient aeesocketclient)
    {
        mediaplusactivity.mSocketClient = aeesocketclient;
        return aeesocketclient;
    }

*/








/*
    static boolean access$802(MediaPlusActivity mediaplusactivity, boolean flag)
    {
        mediaplusactivity.mIsWifiConnected = flag;
        return flag;
    }

*/

}
