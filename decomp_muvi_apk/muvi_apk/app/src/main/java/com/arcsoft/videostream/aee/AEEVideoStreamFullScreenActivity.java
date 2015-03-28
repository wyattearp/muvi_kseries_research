// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.mediaplus.MediaPlusApplication;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient, AEEUtilDef

public class AEEVideoStreamFullScreenActivity extends Activity
    implements android.view.SurfaceHolder.Callback
{
    public class ConnectSocketTask extends AsyncTask
    {

        final AEEVideoStreamFullScreenActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
            if (RtspUtils.isAmbar())
            {
                connectSocket();
            }
            return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            if (!RtspUtils.isAmbar())
            {
                break MISSING_BLOCK_LABEL_110;
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                synchronized (mHandler)
                {
                    mHandler.removeMessages(7);
                    mHandler.sendEmptyMessage(7);
                }
                return;
            }
            break MISSING_BLOCK_LABEL_100;
            exception1;
            handler;
            JVM INSTR monitorexit ;
            try
            {
                throw exception1;
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            switchTo(1, 12);
            return;
            switchTo(1, 12);
        }

        public ConnectSocketTask()
        {
            this$0 = AEEVideoStreamFullScreenActivity.this;
            super();
        }
    }

    public class SetParamTask extends AsyncTask
    {

        final AEEVideoStreamFullScreenActivity this$0;

        protected transient Boolean doInBackground(Void avoid[])
        {
            Boolean boolean1;
            boolean1 = Boolean.valueOf(false);
            if (!RtspUtils.isAmbar())
            {
                break MISSING_BLOCK_LABEL_113;
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient == null || !mSocketClient.isConnected())
            {
                break MISSING_BLOCK_LABEL_84;
            }
            mSocketClient.sendCommand(0x10000001, null);
            mSocketClient.startRespondParams(0x10000001);
            mSocketClient.setNextCMD(0x10000024);
            return boolean1;
            try
            {
                switchTo(1, 12);
            }
            catch (IOException ioexception)
            {
                switchTo(1, 12);
                ioexception.printStackTrace();
                return boolean1;
            }
            return boolean1;
            switchTo(2, -1);
            showSurfaceView(0);
            return boolean1;
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected void onPostExecute(Boolean boolean1)
        {
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Boolean)obj);
        }

        public SetParamTask()
        {
            this$0 = AEEVideoStreamFullScreenActivity.this;
            super();
        }
    }


    private static final boolean RTSP_HARDWARE_DECODER = false;
    private static final int SEND_SHOW_STREAMING_DELAY = 1500;
    private static final String TAG = "AEEVideoStreamFullScreenActivity";
    private static final int TIMER_DELAY = 1000;
    protected static final int TYPE_CONNECTED = 2;
    protected static final int TYPE_CONNECTING = 0;
    protected static final int TYPE_CONNECT_FAILED = 1;
    protected static final int TYPE_CONSHOT = 6;
    protected static final int TYPE_FASTSHOT = 5;
    protected static final int TYPE_PHOTO = 4;
    protected static final int TYPE_PLAYBACK = 9;
    protected static final int TYPE_ROLLOVER = 7;
    protected static final int TYPE_VIDEO = 3;
    protected static final int TYPE_VOICE = 8;
    private static final String libPath = "/data/local/tmp/";
    private final int MSG_HIDE_SURFACEVIEW = 12;
    private final int MSG_INIT_DV_INFO = 6;
    private final int MSG_RESUME_PLAYBACK = 5;
    private final int MSG_SEND_COMMAND = 8;
    private final int MSG_SEND_COMMAND_FAILED = 4;
    private final int MSG_SET_PREVIEW_PARAMS = 7;
    private final int MSG_SET_RECING = 9;
    private final int MSG_SHOW_SURFACEVIEW = 1;
    private final int MSG_START_TIMER = 3;
    private final int MSG_STOP_TIMER = 2;
    private final int MSG_SWITCH_EXE_STATUS = 14;
    private final int MSG_SWITCH_STATUS = 0;
    private final int MSG_TOAST_SHORT = 13;
    private final int MSG_UPDATE_DV_INFO = 11;
    private final int MSG_UPDATE_SURFACEVIEW = 10;
    private boolean bPlayerStarted;
    private boolean bStreamConnectDone;
    private boolean isExecuting;
    private boolean isPaused;
    private boolean isRecting;
    private boolean isSocketConnected;
    private boolean isWifiConnected;
    private SecureMediaPlayer mArcMediaPlayer;
    private HashMap mBatteryDrawable;
    private ImageView mCamBattery;
    private TextView mCamNumText;
    private TextView mCamSapceText;
    private ImageView mCamWifi;
    private RelativeLayout mContentLayout;
    private int mContentViewHeight;
    private int mContentViewWidth;
    private int mCurConnectParams;
    private int mCurConnectStatus;
    private int mCurExecuteStatus;
    private int mCurSpendTime;
    private ImageView mExecutBtn;
    private HashMap mExecuteBtnDrawable;
    private HashMap mExecuteBtnString;
    private TextView mFileSize;
    private final Handler mHandler = new Handler() {

        final AEEVideoStreamFullScreenActivity this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 14: default 80
        //                       0 141
        //                       1 159
        //                       2 1144
        //                       3 1212
        //                       4 311
        //                       5 804
        //                       6 1415
        //                       7 814
        //                       8 968
        //                       9 1111
        //                       10 1339
        //                       11 1683
        //                       12 273
        //                       13 123
        //                       14 86;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L1:
            super.handleMessage(message);
_L18:
            return;
_L16:
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity1 = AEEVideoStreamFullScreenActivity.this;
            boolean flag2;
            if (message.arg2 == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            aeevideostreamfullscreenactivity1.switchExeState(flag2, message.arg1);
            continue; /* Loop/switch isn't completed */
_L15:
            Toast.makeText(AEEVideoStreamFullScreenActivity.this, message.arg1, 0).show();
            continue; /* Loop/switch isn't completed */
_L2:
            switchConState(message.arg1, message.arg2);
            continue; /* Loop/switch isn't completed */
_L3:
            if (!isWifiConnected || isPaused) goto _L18; else goto _L17
_L17:
            setStreamConnectDone(true);
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mVideoView == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            IOException ioexception4;
            if (mVideoView.getVisibility() != 0)
            {
                mVideoView.setVisibility(0);
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                if (!bPlayerStarted)
                {
                    showStream();
                    bPlayerStarted = true;
                }
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception4)
            {
                ioexception4.printStackTrace();
            }
            continue; /* Loop/switch isn't completed */
_L14:
            if (mVideoView != null && mVideoView.getVisibility() == 0)
            {
                mVideoView.setVisibility(8);
            }
            continue; /* Loop/switch isn't completed */
_L6:
            String s;
            int i1;
            int j1;
            message.arg2;
            s = (String)message.obj;
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("MSG_SEND_COMMAND_FAILED param = ").append(s).toString());
            i1 = message.arg1;
            j1 = 0;
            i1;
            JVM INSTR tableswitch -19 1: default 460
        //                       -19 662
        //                       -18 797
        //                       -17 698
        //                       -16 790
        //                       -15 460
        //                       -14 783
        //                       -13 776
        //                       -12 769
        //                       -11 460
        //                       -10 460
        //                       -9 762
        //                       -8 755
        //                       -7 748
        //                       -6 460
        //                       -5 460
        //                       -4 741
        //                       -3 734
        //                       -2 460
        //                       -1 460
        //                       0 460
        //                       1 481;
               goto _L19 _L20 _L21 _L22 _L23 _L19 _L24 _L25 _L26 _L19 _L19 _L27 _L28 _L29 _L19 _L19 _L30 _L31 _L19 _L19 _L19 _L32
_L19:
            if (j1 != 0)
            {
                Toast.makeText(AEEVideoStreamFullScreenActivity.this, j1, 0).show();
            }
            continue; /* Loop/switch isn't completed */
_L32:
            j1 = 0x7f0b018e;
            if (s != null && s.contains("16777218"))
            {
                if (isRecting)
                {
                    stopTimer();
                    setHandleRecing(false, mCurExecuteStatus);
                    if (mCurConnectParams == 18)
                    {
                        switchTo(2, -1);
                        showSurfaceView(0);
                    }
                }
                switchTo(1, 19);
                return;
            }
            if (s != null && s.contains("16777220"))
            {
                j1 = 0x7f0b0198;
            }
            if (j1 != 0x7f0b018e && isRecting)
            {
                stopTimer();
                setHandleRecing(false, mCurExecuteStatus);
                if (mCurConnectParams == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
                }
            }
            continue; /* Loop/switch isn't completed */
_L20:
            if (mCurConnectParams == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
            j1 = 0x7f0b019c;
            continue; /* Loop/switch isn't completed */
_L22:
            if (mCurConnectParams == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
            j1 = 0x7f0b0198;
            continue; /* Loop/switch isn't completed */
_L31:
            j1 = 0x7f0b018f;
            continue; /* Loop/switch isn't completed */
_L30:
            j1 = 0x7f0b0190;
            continue; /* Loop/switch isn't completed */
_L29:
            j1 = 0x7f0b0191;
            continue; /* Loop/switch isn't completed */
_L28:
            j1 = 0x7f0b0192;
            continue; /* Loop/switch isn't completed */
_L27:
            j1 = 0x7f0b0193;
            continue; /* Loop/switch isn't completed */
_L26:
            j1 = 0x7f0b0194;
            continue; /* Loop/switch isn't completed */
_L25:
            j1 = 0x7f0b0195;
            continue; /* Loop/switch isn't completed */
_L24:
            j1 = 0x7f0b0196;
            continue; /* Loop/switch isn't completed */
_L23:
            j1 = 0x7f0b0197;
            continue; /* Loop/switch isn't completed */
_L21:
            j1 = 0x7f0b0199;
            if (true) goto _L19; else goto _L7
_L7:
            resumePlayback();
            continue; /* Loop/switch isn't completed */
_L9:
            if (!RtspUtils.isAmbar())
            {
                break MISSING_BLOCK_LABEL_948;
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            IOException ioexception3;
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                mIsSetParams = true;
                if (mSocketClient.sendCommand(0x10000001, null) != 1)
                {
                    mIsSetParams = false;
                }
                mSocketClient.startRespondParams(0x10000001);
                mSocketClient.setNextCMD(0x1000002b);
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                switchTo(1, 12);
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception3)
            {
                switchTo(1, 12);
                ioexception3.printStackTrace();
            }
            continue; /* Loop/switch isn't completed */
            switchTo(2, -1);
            showSurfaceView(0);
            continue; /* Loop/switch isn't completed */
_L10:
            int k;
            int l;
            k = message.arg1;
            l = message.arg2;
            if (k == -1 && l == -1) goto _L18; else goto _L33
_L33:
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient == null || !mSocketClient.isConnected())
            {
                continue; /* Loop/switch isn't completed */
            }
            if (k == -1)
            {
                try
                {
                    mSocketClient.sendCommand(l, null);
                    mSocketClient.startRespondParams(l);
                }
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
                continue; /* Loop/switch isn't completed */
            }
            mSocketClient.sendCommand(k, null);
            mSocketClient.startRespondParams(k);
            mSocketClient.setNextCMD(l);
            continue; /* Loop/switch isn't completed */
_L11:
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity = AEEVideoStreamFullScreenActivity.this;
            boolean flag1;
            if (message.arg1 == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            aeevideostreamfullscreenactivity.setRecting(flag1);
            continue; /* Loop/switch isn't completed */
_L4:
            if (mTimeCountor > 0)
            {
                mCurSpendTime = mTimeCountor;
                mTimeCountor = 0;
            }
            if (mFileSize != null)
            {
                mFileSize.setText(TimeUtils.convertSecToTimeStringWithoutHour(mTimeCountor));
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if (mFileSize != null)
            {
                mFileSize.setText(TimeUtils.convertSecToTimeStringWithoutHour(int i = 1 + 
// JavaClassFileOutputException: get_constant: invalid tag

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
    };
    private RelativeLayout mInfoLayout;
    private final String mIniRes = "MV3Plugin.ini";
    private boolean mIsBacked;
    private boolean mIsFirstStreaming;
    private boolean mIsPreviewClosed;
    private boolean mIsPreviewNotSupport;
    private boolean mIsSetParams;
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final AEEVideoStreamFullScreenActivity this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo == null || networkstateinfo.networkInfo.getType() != 1)
            {
                return;
            }
            isWifiConnected = networkstateinfo.networkInfo.isConnected();
            Exception exception;
            mSocketClient = AEESocketClient.getInstanceClient();
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged isWifiConnected = ").append(isWifiConnected).toString());
            if (!isWifiConnected)
            {
                break MISSING_BLOCK_LABEL_396;
            }
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged bStreamConnectDone = ").append(bStreamConnectDone).append(", mCurConnectParams = ").append(mCurConnectParams).toString());
            if (!bStreamConnectDone)
            {
                break MISSING_BLOCK_LABEL_219;
            }
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("OnConnectivityChanged mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
            if (mCurExecuteStatus == 8)
            {
                switchTo(8, -1);
                return;
            }
            try
            {
                switchTo(2, -1);
                showSurfaceView(0);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                exception.printStackTrace();
                return;
            }
            if (mCurConnectParams == 18 || mCurConnectParams == 20)
            {
                switchTo(1, mCurConnectParams);
                return;
            }
            switchTo(0, -1);
            if (mSocketClient.isConnected())
            {
                synchronized (mHandler)
                {
                    mHandler.removeMessages(7);
                    mHandler.sendEmptyMessage(7);
                }
                return;
            }
            break MISSING_BLOCK_LABEL_330;
            exception1;
            handler;
            JVM INSTR monitorexit ;
            throw exception1;
            if (mSocketTask != null)
            {
                mSocketTask.cancel(true);
                mSocketTask = null;
            }
            mSocketTask = new ConnectSocketTask();
            mSocketTask.execute(new String[0]);
            return;
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                mSocketClient.close();
                mSocketClient = null;
            }
            switchTo(1, 11);
            return;
        }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() ;
    private final AEESocketClient.OnRequestRespondsListener mOnRequestRespondsListener = new AEESocketClient.OnRequestRespondsListener() {

        final AEEVideoStreamFullScreenActivity this$0;

        public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
        {
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
            if (j != 1 || s1 == null) goto _L2; else goto _L1
_L1:
            if (!s1.contains("16777217")) goto _L4; else goto _L3
_L3:
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
            if (mCurExecuteStatus != 6) goto _L6; else goto _L5
_L5:
            setHandleRecing(true, 6);
_L57:
            return;
_L6:
            if (mCurExecuteStatus == 3)
            {
                if (isRecting)
                {
                    stopTimer();
                    setHandleRecing(false, 3);
                    if (mCurConnectParams == 18)
                    {
                        switchTo(2, -1);
                        showSurfaceView(0);
                    }
                }
                switchTo(1, 19);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            sendCommandFailed(j, i, s1);
            return;
_L2:
            int l = -1;
            int i5;
            mSocketClient = AEESocketClient.getInstanceClient();
            i5 = mSocketClient.getNextCMD();
            l = i5;
_L44:
            i;
            JVM INSTR lookupswitch 38: default 572
        //                       -1: 136
        //                       268435457: 1924
        //                       268435458: 2455
        //                       268435459: 2976
        //                       268435460: 3139
        //                       268435461: 3335
        //                       268435462: 3397
        //                       268435463: 3397
        //                       268435464: 3607
        //                       268435465: 3486
        //                       268435466: 3486
        //                       268435474: 631
        //                       268435475: 1888
        //                       268435476: 3546
        //                       268435487: 836
        //                       268435488: 3885
        //                       268435489: 1088
        //                       268435490: 1004
        //                       268435491: 1451
        //                       268435492: 907
        //                       268435493: 2523
        //                       268435494: 3825
        //                       268435495: 3679
        //                       268435497: 3745
        //                       268435498: 3785
        //                       268435499: 2108
        //                       268435500: 2735
        //                       268435501: 3909
        //                       268435502: 3947
        //                       268435503: 3985
        //                       268435504: 4023
        //                       268435505: 4083
        //                       268435506: 4147
        //                       268435507: 2320
        //                       268435509: 1718
        //                       268435510: 1698
        //                       268435511: 1738
        //                       268435512: 1656;
               goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L14 _L15 _L16 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43
_L8:
            continue; /* Loop/switch isn't completed */
_L7:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished default");
            if (j != 0)
            {
                Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
                sendCommandFailed(j, i, s1);
                return;
            }
            continue; /* Loop/switch isn't completed */
            IOException ioexception;
            ioexception;
            ioexception.printStackTrace();
              goto _L44
_L17:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_START_ENCODING");
            if (mCurExecuteStatus != 8 && (!isRecting || mCurConnectParams != 18))
            {
                switchTo(2, -1);
                if (mArcMediaPlayer != null)
                {
                    mArcMediaPlayer.reset();
                    mSocketClient.setIsFirstStartPreview(true);
                    bPlayerStarted = false;
                }
                if (!isPaused)
                {
                    showSurfaceView(0);
                } else
                {
                    setStreamConnectDone(true);
                }
                mIsFirstStreaming = false;
            }
            if (mCurExecuteStatus == 8)
            {
                switchTo(8, -1);
            }
            switch (l)
            {
            default:
                sendHandleCMD(l, -1, 0L);
                return;

            case 268435487: 
                sendHandleCMD(0x1000001f, 0x10000027, 0L);
                return;

            case -1: 
                break;
            }
            continue; /* Loop/switch isn't completed */
_L20:
            if (j == -12)
            {
                mSocketClient.setHasConfig(false);
            } else
            {
                mSocketClient.setHasConfig(true);
            }
            if (l == 0x10000027 || -1 == l)
            {
                sendHandleMSGWithTime(6, 0x10000027, -1, null, 0L);
                return;
            } else
            {
                sendHandleCMD(l, -1, 0L);
                return;
            }
_L25:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_VIDEO_STAMP");
            if (s1 == null || s1.contains("off"))
            {
                if (isRecting)
                {
                    sendHandleCMD(0x10000022, -1, 0L);
                    return;
                } else
                {
                    sendHandleCMD(0x10000021, -1, 0L);
                    return;
                }
            }
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity16 = AEEVideoStreamFullScreenActivity.this;
            int l4;
            if (!mSocketClient.getIsNeedEncoding())
            {
                l4 = 0x10000013;
            } else
            {
                l4 = -1;
            }
            aeevideostreamfullscreenactivity16.sendHandleCMD(l4, 0x10000038, 0L);
            return;
_L23:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAM_TYPE");
            if (s1 == null || s1.contains("rtsp"))
            {
                sendHandleCMD(0x10000023, -1, 0L);
                return;
            }
            mIsFirstStreaming = true;
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity15 = AEEVideoStreamFullScreenActivity.this;
            int k4;
            if (!mSocketClient.getIsNeedEncoding())
            {
                k4 = 0x10000013;
            } else
            {
                k4 = -1;
            }
            aeevideostreamfullscreenactivity15.sendHandleCMD(k4, 0x10000036, 0L);
            return;
_L22:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM");
            if (s1 == null || s1.contains("on"))
            {
                if (!isRecting)
                {
                    sendHandleCMD(0x10000022, -1, 0L);
                    return;
                }
                mIsPreviewNotSupport = false;
                if (!mIsPreviewClosed)
                {
                    if (mSocketClient.getIsFirstStartPreview())
                    {
                        if (l != -1)
                        {
                            sendHandleCMD(0x10000024, -1, 0L);
                        }
                    } else
                    {
                        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity11 = AEEVideoStreamFullScreenActivity.this;
                        int l3;
                        if (mCurConnectParams == 19)
                        {
                            l3 = mCurConnectParams;
                        } else
                        {
                            l3 = -1;
                        }
                        aeevideostreamfullscreenactivity11.switchTo(2, l3);
                        if (!isPaused)
                        {
                            showSurfaceView(0);
                            mIsFirstStreaming = false;
                        } else
                        {
                            setStreamConnectDone(true);
                        }
                    }
                } else
                {
                    switchTo(1, 20);
                }
                if (l != -1)
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity10 = AEEVideoStreamFullScreenActivity.this;
                    int k3;
                    if (l == 0x1000001f)
                    {
                        k3 = 0x10000027;
                    } else
                    {
                        k3 = -1;
                    }
                    aeevideostreamfullscreenactivity10.sendHandleCMD(l, k3, 0L);
                    return;
                }
            } else
            if (isRecting)
            {
                mIsPreviewNotSupport = true;
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity13 = AEEVideoStreamFullScreenActivity.this;
                byte byte2;
                if (mIsPreviewClosed)
                {
                    byte2 = 20;
                } else
                {
                    byte2 = 18;
                }
                aeevideostreamfullscreenactivity13.switchTo(1, byte2);
                if (l != -1)
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity14 = AEEVideoStreamFullScreenActivity.this;
                    int j4;
                    if (l == 0x1000001f)
                    {
                        j4 = 0x10000027;
                    } else
                    {
                        j4 = -1;
                    }
                    aeevideostreamfullscreenactivity14.sendHandleCMD(l, j4, 0L);
                    return;
                }
            } else
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity12 = AEEVideoStreamFullScreenActivity.this;
                int i4;
                if (!mSocketClient.getIsNeedEncoding())
                {
                    i4 = 0x10000013;
                } else
                {
                    i4 = -1;
                }
                aeevideostreamfullscreenactivity12.sendHandleCMD(i4, 0x10000035, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L24:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAMING");
            if (s1 == null || s1.contains("on"))
            {
                if (mSocketClient.getIsNeedEncoding())
                {
                    sendHandleCMD(0x10000012, 0x1000001f, 0L);
                    return;
                }
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity7 = AEEVideoStreamFullScreenActivity.this;
                int i3;
                if (mCurConnectParams == 19)
                {
                    i3 = mCurConnectParams;
                } else
                {
                    i3 = -1;
                }
                aeevideostreamfullscreenactivity7.switchTo(2, i3);
                sendHandleCMD(0x1000001f, 0x10000027, 0L);
                if (!isPaused)
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity8 = AEEVideoStreamFullScreenActivity.this;
                    char c1;
                    if (mIsFirstStreaming)
                    {
                        c1 = '\u05DC';
                    } else
                    {
                        c1 = '\0';
                    }
                    aeevideostreamfullscreenactivity8.showSurfaceView(c1);
                    mIsFirstStreaming = false;
                    return;
                } else
                {
                    setStreamConnectDone(true);
                    return;
                }
            }
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity9 = AEEVideoStreamFullScreenActivity.this;
            int j3;
            if (!mSocketClient.getIsNeedEncoding())
            {
                j3 = 0x10000013;
            } else
            {
                j3 = -1;
            }
            aeevideostreamfullscreenactivity9.sendHandleCMD(j3, 0x10000037, 0L);
            return;
_L43:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_VIDEO_STAMP");
            if (isRecting)
            {
                sendHandleCMD(0x10000022, -1, 0L);
                return;
            } else
            {
                sendHandleCMD(0x10000021, -1, 0L);
                return;
            }
_L41:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAM_TYPE");
            sendHandleCMD(0x10000037, -1, 0L);
            return;
_L40:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_DUAL_STREAM");
            sendHandleCMD(0x10000022, -1, 0L);
            return;
_L42:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAMING");
            if (mSocketClient.getIsNeedEncoding())
            {
                sendHandleCMD(0x10000012, 0x1000001f, 0L);
                return;
            }
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity5 = AEEVideoStreamFullScreenActivity.this;
            int k2;
            if (mCurConnectParams == 19)
            {
                k2 = mCurConnectParams;
            } else
            {
                k2 = -1;
            }
            aeevideostreamfullscreenactivity5.switchTo(2, k2);
            sendHandleCMD(0x1000001f, 0x10000027, 0L);
            if (!isPaused)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity6 = AEEVideoStreamFullScreenActivity.this;
                char c;
                if (mIsFirstStreaming)
                {
                    c = '\u05DC';
                } else
                {
                    c = '\0';
                }
                aeevideostreamfullscreenactivity6.showSurfaceView(c);
                mIsFirstStreaming = false;
                return;
            } else
            {
                setStreamConnectDone(true);
                return;
            }
_L18:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_STOP_ENCODING");
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
                return;
            } else
            {
                sendHandleCMD(l, -1, 0L);
                return;
            }
_L9:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SESSION_START");
            if (j != 0)
            {
                if (mSocketClient.getCurShareId(AEEVideoStreamFullScreenActivity.this) > 0)
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity4 = AEEVideoStreamFullScreenActivity.this;
                    int j2;
                    if (l == 0x1000002b)
                    {
                        j2 = 0x10000033;
                    } else
                    {
                        j2 = -1;
                    }
                    aeevideostreamfullscreenactivity4.sendHandleCMD(l, j2, 0L);
                    return;
                } else
                {
                    switchTo(1, 13);
                    return;
                }
            }
            if (j == 0 && s1 != null && s1.length() > 0)
            {
                try
                {
                    int i2 = Integer.parseInt(s1);
                    AEEUtilDef.setSharedTokenId(AEEVideoStreamFullScreenActivity.this, i2);
                }
                catch (Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }
            switch (l)
            {
            default:
                sendHandleCMD(l, -1, 0L);
                return;

            case 268435499: 
                sendHandleCMD(l, 0x10000033, 0L);
                return;

            case 268435459: 
                sendHandleCMD(l, 0x10000021, 0L);
                return;
            }
_L31:
            byte byte0 = -1;
            j;
            JVM INSTR lookupswitch 2: default 2140
        //                       -4: 2280
        //                       0: 2226;
               goto _L45 _L46 _L47
_L45:
            if (mCurExecuteStatus == -1)
            {
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity3 = AEEVideoStreamFullScreenActivity.this;
                byte byte1;
                int k1;
                if (byte0 == -1)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = byte0;
                }
                if (isRecting)
                {
                    k1 = 1;
                } else
                {
                    k1 = 0;
                }
                aeevideostreamfullscreenactivity3.sendHandleMSGWithTime(14, byte1, k1, null, 0L);
            }
            switch (l)
            {
            default:
                return;

            case 268435507: 
                sendHandleCMD(l, 0x10000025, 0L);
                break;
            }
            return;
_L47:
            if (k > 0)
            {
                if (s1.contains("VIDEO"))
                {
                    byte0 = 3;
                } else
                if (s1.contains("PHOTO"))
                {
                    byte0 = 4;
                } else
                if (s1.contains("VOICE"))
                {
                    byte0 = 8;
                }
            }
              goto _L45
_L46:
            mSocketClient.releaseCurShareId(AEEVideoStreamFullScreenActivity.this);
            sendHandleCMD(0x10000001, 0x1000002b, 0L);
            return;
_L39:
            if (s1 != null && s1.contains("video_flip_rotate_on_"))
            {
                if (!mSocketClient.getIsRolloverOn())
                {
                    mSocketClient.setIsRolloverOn(true);
                }
            } else
            if (s1 != null && s1.contains("video_flip_rotate_off") && mSocketClient.getIsRolloverOn())
            {
                mSocketClient.setIsRolloverOn(false);
            }
            switch (l)
            {
            default:
                sendHandleCMD(l, -1, 0L);
                return;

            case 268435493: 
                sendHandleCMD(l, 0x10000024, 0L);
                return;

            case -1: 
                break;
            }
            continue; /* Loop/switch isn't completed */
_L10:
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.stopRespondParams();
                mSocketClient.setOnRequestRespondsListener(null);
                mSocketClient.releaseCurTokenId();
                mSocketClient.close();
                mSocketClient = null;
                return;
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            return;
_L26:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_APP_STATUS");
            if (s1 != null && s1.contains("record"))
            {
                if (mCurExecuteStatus != 3)
                {
                    AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity2 = AEEVideoStreamFullScreenActivity.this;
                    int j1;
                    if (isRecting)
                    {
                        j1 = 1;
                    } else
                    {
                        j1 = 0;
                    }
                    aeevideostreamfullscreenactivity2.sendHandleMSGWithTime(14, 3, j1, null, 0L);
                }
                mSocketClient.setIsNeedEncoding(false);
                sendHandleCMD(0x1000002c, 0x10000021, 0L);
                return;
            }
            if (s1 != null && s1.contains("idle"))
            {
                mSocketClient.setIsNeedEncoding(true);
                AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity1 = AEEVideoStreamFullScreenActivity.this;
                long l2;
                if (mIsFirstStreaming)
                {
                    l2 = 1500L;
                } else
                {
                    l2 = 0L;
                }
                aeevideostreamfullscreenactivity1.sendHandleCMD(0x10000024, -1, l2);
                return;
            }
            mSocketClient.setIsNeedEncoding(false);
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity = AEEVideoStreamFullScreenActivity.this;
            long l1;
            if (mIsFirstStreaming)
            {
                l1 = 1500L;
            } else
            {
                l1 = 0L;
            }
            aeevideostreamfullscreenactivity.sendHandleCMD(0x10000024, -1, l1);
            return;
_L32:
            int i1;
            String as[];
            if (isRecting)
            {
                i1 = -1;
            } else
            {
                i1 = 0x1000001f;
            }
            if (s1 == null || j != 0) goto _L49; else goto _L48
_L48:
            as = s1.split(":");
            if (as.length != 1) goto _L51; else goto _L50
_L50:
            mTimeCountor = Integer.parseInt(as[0]);
_L54:
            if (!isRecting)
            {
                startTimer();
                setHandleRecing(true, mCurExecuteStatus);
            }
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME mTimeCountor = ").append(mTimeCountor).toString());
_L49:
            isRecting = true;
            sendHandleCMD(l, i1, 0L);
            return;
_L51:
            if (as.length != 2) goto _L53; else goto _L52
_L52:
            mTimeCountor = 60 * Integer.parseInt(as[0]) + Integer.parseInt(as[1]);
              goto _L54
            Exception exception;
            exception;
            exception.printStackTrace();
              goto _L54
_L53:
            if (as.length != 3) goto _L54; else goto _L55
_L55:
            mTimeCountor = 3600 * Integer.parseInt(as[0]) + 60 * Integer.parseInt(as[1]) + Integer.parseInt(as[2]);
              goto _L54
_L11:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_START");
            isExecuting = false;
            switch (j)
            {
            default:
                setHandleRecing(false, 3);
                return;

            case 0: // '\0'
                startTimer();
                setHandleRecing(true, 3);
                if (l > 0)
                {
                    sendHandleCMD(l, -1, 0L);
                    return;
                }
                break;

            case -19: 
            case -17: 
            case 1: // '\001'
                if (isRecting)
                {
                    stopTimer();
                    setHandleRecing(false, 3);
                }
                sendCommandFailed(j, i, s1);
                return;

            case -4: 
                sendHandleCMD(0x10000001, 0x10000003, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L12:
            Log.e("AEEVideoStreamFullScreenActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_STOP");
            isExecuting = false;
            mIsPreviewNotSupport = false;
            if (j == 0)
            {
                stopTimer();
                setHandleRecing(false, mCurExecuteStatus);
                if (mCurExecuteStatus == 8)
                {
                    switchTo(8, -1);
                } else
                if (mIsPreviewClosed)
                {
                    switchTo(1, 20);
                } else
                {
                    showSurfaceView(0);
                    switchTo(2, -1);
                }
            } else
            if (j == -4)
            {
                sendHandleCMD(0x10000001, 0x10000004, 0L);
            } else
            {
                isRecting = true;
                sendCommandFailed(j, i, s1);
            }
            if (l != -1)
            {
                sendHandleCMD(0x10000026, l, 0L);
                return;
            } else
            {
                sendHandleCMD(0x10000026, -1, 0L);
                return;
            }
_L13:
            isExecuting = false;
            if (j == 0)
            {
                startTimer();
                switchTo(8, -1);
                setHandleRecing(false, 8);
                return;
            } else
            {
                isRecting = false;
                sendCommandFailed(j, i, s1);
                return;
            }
_L14:
            isExecuting = false;
            setHandleRecing(false, mCurExecuteStatus);
            if (mIsBacked && mSocketClient != null)
            {
                mSocketClient.setIsExcuting(isExecuting);
            }
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
            }
            sendHandleCMD(0x10000026, -1, 0L);
            return;
_L16:
            isExecuting = false;
            setHandleRecing(false, mCurExecuteStatus);
            if (mCurExecuteStatus != 8)
            {
                updateSurfaceView(0);
            }
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L19:
            isExecuting = false;
            if (j == 0)
            {
                setHandleRecing(true, 6);
                return;
            }
            if (j == 1)
            {
                setHandleRecing(true, 6);
                return;
            } else
            {
                isRecting = false;
                sendCommandFailed(j, i, s1);
                return;
            }
_L15:
            isExecuting = false;
            if (j == 0)
            {
                setHandleRecing(false, 6);
                updateSurfaceView(0);
            } else
            if (j != 1)
            {
                isRecting = true;
                sendCommandFailed(j, i, s1);
            }
            sendHandleCMD(0x10000026, -1, 0L);
            return;
_L28:
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_INFO param = ").append(s1).toString());
            if (j == 0)
            {
                sendHandleMSGWithTime(11, 0x10000027, -1, s1, 0L);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L29:
            if (j == 0)
            {
                sendHandleMSGWithTime(11, 0x10000029, -1, s1, 0L);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L30:
            if (j == 0)
            {
                sendHandleMSGWithTime(11, 0x1000002a, -1, s1, 0L);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L27:
            if (j == 0)
            {
                sendHandleMSGWithTime(11, 0x10000026, -1, s1, 0L);
            }
            if (l == 0x1000002d)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            if (l > 0)
            {
                sendHandleCMD(l, -1, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L21:
            if (j != 0);
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L33:
            if (j == 0)
            {
                mSocketClient.setDVInfo(12, s1);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L34:
            if (j == 0)
            {
                mSocketClient.setDVInfo(14, s1);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L35:
            if (j == 0)
            {
                mSocketClient.setDVInfo(15, s1);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L36:
            if (j == 0)
            {
                mSocketClient.setDVInfo(16, s1);
            } else
            if (j == -15)
            {
                mSocketClient.setDVInfo(16, null);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L37:
            if (j == 0)
            {
                mSocketClient.setDVInfo(17, s1);
            }
            if (mCurConnectParams == 19)
            {
                mCurConnectParams = -1;
                sendHandleMSGWithTime(13, 0x7f0b015d, -1, null, 0L);
            }
            mIsSetParams = false;
            return;
_L38:
            if (j == 0)
            {
                mSocketClient.setDVInfo(13, s1);
            }
            if (l >= 0)
            {
                sendHandleMSGWithTime(6, l, -1, null, 0L);
                return;
            }
            if (true) goto _L57; else goto _L56
_L56:
        }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
    };
    private String mPlayerPluginPath;
    private RelativeLayout mPreviewLayout;
    private int mRealVideoHeight;
    private int mRealVideoWidth;
    private final String mResPath = null;
    private TextView mResolutionText;
    private Resources mResources;
    private BroadcastReceiver mRssiReceiver;
    private int mScreenAvailableLandHeight;
    private int mScreenAvailableLandWidth;
    private int mScreenAvailablePortHeight;
    private int mScreenAvailablePortWidth;
    private int mScreenHeight;
    private int mScreenWidth;
    AEESocketClient mSocketClient;
    private ConnectSocketTask mSocketTask;
    private ImageView mStateBtn;
    private HashMap mStateBtnDrawable;
    private HashMap mStateBtnRecingDrawable;
    private RelativeLayout mStateBtnsLayout;
    private ImageView mStatePlayBackBtn;
    private ImageView mStatusIcon;
    private TextView mStatusText;
    private SurfaceHolder mSurfaceHolder;
    private int mTimeCountor;
    private RelativeLayout mTitleLayout;
    private TextView mTitleText;
    private int mTopBarLandHeight;
    private int mTopBarPortHeight;
    private SurfaceView mVideoView;
    private View mViewGroup;
    private HashMap mWifiDrawable;

    public AEEVideoStreamFullScreenActivity()
    {
        mArcMediaPlayer = null;
        mSurfaceHolder = null;
        mPlayerPluginPath = null;
        mViewGroup = null;
        mCamNumText = null;
        mTitleText = null;
        mCamWifi = null;
        mCamBattery = null;
        mCamSapceText = null;
        mFileSize = null;
        mTimeCountor = 0;
        mCurSpendTime = 0;
        mPreviewLayout = null;
        mContentLayout = null;
        mExecutBtn = null;
        mStateBtn = null;
        mResolutionText = null;
        mStatePlayBackBtn = null;
        mTitleLayout = null;
        mStateBtnsLayout = null;
        mInfoLayout = null;
        mExecuteBtnDrawable = null;
        mStateBtnDrawable = null;
        mStateBtnRecingDrawable = null;
        mExecuteBtnString = null;
        mBatteryDrawable = null;
        mWifiDrawable = null;
        mResources = null;
        mStatusIcon = null;
        mStatusText = null;
        mNetworkTool = null;
        mSocketClient = null;
        mCurConnectParams = -1;
        mCurConnectStatus = -1;
        mCurExecuteStatus = -1;
        isRecting = false;
        isExecuting = false;
        isWifiConnected = false;
        mSocketTask = null;
        isSocketConnected = false;
        bStreamConnectDone = false;
        bPlayerStarted = false;
        mIsFirstStreaming = false;
        mIsSetParams = false;
        mIsPreviewClosed = false;
        mIsPreviewNotSupport = false;
        mIsBacked = false;
        mTopBarPortHeight = 0;
        mTopBarLandHeight = 0;
        mScreenWidth = 0;
        mScreenHeight = 0;
        mContentViewWidth = 0;
        mContentViewHeight = 0;
        mScreenAvailablePortWidth = 0;
        mScreenAvailablePortHeight = 0;
        mScreenAvailableLandWidth = 0;
        mScreenAvailableLandHeight = 0;
        mRealVideoWidth = 432;
        mRealVideoHeight = 240;
        mRssiReceiver = null;
        isPaused = false;
    }

    private boolean connectSocket()
    {
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (!mSocketClient.isConnected())
            {
                mSocketClient.connect();
            }
        }
        catch (IOException ioexception)
        {
            if (mSocketClient != null)
            {
                try
                {
                    mSocketClient.close();
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
                mSocketClient = null;
            }
            switchTo(1, 12);
            return false;
        }
        return true;
    }

    private void getScreenAvailableSize(int i, int j)
    {
        int k;
        int l;
        Log.v("zdf", (new StringBuilder()).append("getScreenAvailableSize, contentWidth = ").append(i).append(", contentHeight = ").append(j).toString());
        getScreenSize();
        getTopBarHeight();
        k = mScreenHeight - j;
        l = mTopBarPortHeight - mTopBarLandHeight;
        Log.v("zdf", (new StringBuilder()).append("getScreenAvailableSize, ctrlAreaHeight = ").append(k).append(", offset = ").append(l).toString());
        mResources.getConfiguration().orientation;
        JVM INSTR tableswitch 1 2: default 136
    //                   1 223
    //                   2 258;
           goto _L1 _L2 _L3
_L1:
        Log.v("zdf", (new StringBuilder()).append("getScreenAvailableSize, mScreenAvailablePortWidth = ").append(mScreenAvailablePortWidth).append(", mScreenAvailablePortHeight = ").append(mScreenAvailablePortHeight).toString());
        Log.v("zdf", (new StringBuilder()).append("getScreenAvailableSize, mScreenAvailableLandWidth = ").append(mScreenAvailableLandWidth).append(", mScreenAvailableLandHeight = ").append(mScreenAvailableLandHeight).toString());
        return;
_L2:
        mScreenAvailablePortWidth = i;
        mScreenAvailablePortHeight = j;
        mScreenAvailableLandWidth = mScreenHeight;
        mScreenAvailableLandHeight = l + (mScreenWidth - k);
        continue; /* Loop/switch isn't completed */
_L3:
        mScreenAvailableLandWidth = i;
        mScreenAvailableLandHeight = j;
        mScreenAvailablePortWidth = mScreenHeight;
        mScreenAvailablePortHeight = mScreenWidth - k - l;
        if (true) goto _L1; else goto _L4
_L4:
    }

    private void getScreenSize()
    {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        mScreenWidth = displaymetrics.widthPixels;
        mScreenHeight = displaymetrics.heightPixels;
        Log.v("zdf", (new StringBuilder()).append("getScreenSize, mScreenWidth = ").append(mScreenWidth).append(", mScreenHeight = ").append(mScreenHeight).toString());
    }

    private void getTopBarHeight()
    {
        mTopBarPortHeight = mResources.getDrawable(0x7f0202b6).getIntrinsicHeight();
        mTopBarLandHeight = mResources.getDrawable(0x7f0202b7).getIntrinsicHeight();
    }

    private void init()
    {
        mViewGroup = LayoutInflater.from(this).inflate(0x7f03003a, null);
        mCamNumText = (TextView)mViewGroup.findViewById(0x7f09012a);
        mTitleText = (TextView)mViewGroup.findViewById(0x7f09011a);
        mCamWifi = (ImageView)mViewGroup.findViewById(0x7f090125);
        mCamBattery = (ImageView)mViewGroup.findViewById(0x7f090126);
        mCamSapceText = (TextView)mViewGroup.findViewById(0x7f090127);
        mFileSize = (TextView)mViewGroup.findViewById(0x7f090129);
        mExecutBtn = (ImageView)mViewGroup.findViewById(0x7f09012b);
        mStateBtn = (ImageView)mViewGroup.findViewById(0x7f090123);
        mResolutionText = (TextView)mViewGroup.findViewById(0x7f090124);
        int i;
        mSocketClient = AEESocketClient.getInstanceClient();
        mResolutionText.setVisibility(0);
        i = mCurExecuteStatus;
        Object obj = null;
        i;
        JVM INSTR tableswitch 3 8: default 232
    //                   3 612
    //                   4 657
    //                   5 685
    //                   6 671
    //                   7 232
    //                   8 750;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6
_L1:
        try
        {
            mResolutionText.setText(((CharSequence) (obj)));
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        mStatePlayBackBtn = (ImageView)mViewGroup.findViewById(0x7f09011b);
        mTitleLayout = (RelativeLayout)mViewGroup.findViewById(0x7f090119);
        mInfoLayout = (RelativeLayout)mViewGroup.findViewById(0x7f090122);
        mPreviewLayout = (RelativeLayout)mViewGroup.findViewById(0x7f09011e);
        mContentLayout = (RelativeLayout)mViewGroup.findViewById(0x7f09011c);
        mStateBtnsLayout = (RelativeLayout)mViewGroup.findViewById(0x7f09011d);
        mPreviewLayout.setOnClickListener(mOnClickListener);
        mVideoView = (SurfaceView)mViewGroup.findViewById(0x7f09011f);
        mVideoView.setVisibility(8);
        mStatusText = (TextView)mViewGroup.findViewById(0x7f090121);
        mStatusText.setVisibility(8);
        mStatusIcon = (ImageView)mViewGroup.findViewById(0x7f090120);
        mStatusIcon.setVisibility(8);
        if (mResources.getConfiguration().orientation == 2)
        {
            if (mTitleLayout != null)
            {
                mTitleLayout.setBackgroundResource(0x7f0202b7);
            }
            if (mInfoLayout != null)
            {
                android.widget.RelativeLayout.LayoutParams layoutparams3 = (android.widget.RelativeLayout.LayoutParams)mInfoLayout.getLayoutParams();
                layoutparams3.width = -1;
                layoutparams3.height = (int)mResources.getDimension(0x7f08010b);
                mInfoLayout.setLayoutParams(layoutparams3);
                mInfoLayout.setBackgroundResource(0x7f020024);
            }
            if (mStateBtnsLayout != null)
            {
                android.widget.RelativeLayout.LayoutParams layoutparams2 = new android.widget.RelativeLayout.LayoutParams(-1, (int)mResources.getDimension(0x7f08011f));
                layoutparams2.addRule(12);
                mStateBtnsLayout.setLayoutParams(layoutparams2);
                mStateBtnsLayout.setBackgroundResource(0x7f020020);
            }
        } else
        {
            if (mTitleLayout != null)
            {
                mTitleLayout.setBackgroundResource(0x7f0202b6);
            }
            if (mInfoLayout != null)
            {
                android.widget.RelativeLayout.LayoutParams layoutparams1 = (android.widget.RelativeLayout.LayoutParams)mInfoLayout.getLayoutParams();
                layoutparams1.width = -1;
                layoutparams1.height = (int)mResources.getDimension(0x7f08010a);
                mInfoLayout.setLayoutParams(layoutparams1);
                mInfoLayout.setBackgroundResource(0x7f020025);
            }
            if (mStateBtnsLayout != null)
            {
                android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)mResources.getDimension(0x7f08011e));
                layoutparams.addRule(12);
                mStateBtnsLayout.setLayoutParams(layoutparams);
                mStateBtnsLayout.setBackgroundResource(0x7f020021);
            }
        }
        setContentView(mViewGroup);
        mExecutBtn.setOnClickListener(mOnClickListener);
        mStatePlayBackBtn.setOnClickListener(mOnClickListener);
        initHolder();
        return;
_L2:
        obj = (new StringBuilder()).append(mSocketClient.getDVInfo(12)).append("      ").append(mSocketClient.getDVInfo(13)).toString();
          goto _L1
_L3:
        obj = mSocketClient.getDVInfo(14);
          goto _L1
_L5:
        obj = mSocketClient.getDVInfo(17);
          goto _L1
_L4:
        obj = mSocketClient.getDVInfo(16);
        if (obj != null)
        {
            break MISSING_BLOCK_LABEL_712;
        }
        obj = mSocketClient.getDVInfo(15);
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("TYPE_FASTSHOT value = ").append(((String) (obj))).toString());
          goto _L1
_L6:
        mResolutionText.setVisibility(8);
        obj = null;
          goto _L1
    }

    private void initDVInfo()
    {
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mCamWifi != null && mWifiDrawable != null)
        {
            int j = Integer.parseInt(mSocketClient.getDVInfo(1));
            mCamWifi.setBackgroundDrawable((Drawable)mWifiDrawable.get(Integer.valueOf(j)));
        }
        if (mCamBattery != null && mBatteryDrawable != null)
        {
            int i = Integer.parseInt(mSocketClient.getDVInfo(2));
            mCamBattery.setBackgroundDrawable((Drawable)mBatteryDrawable.get(Integer.valueOf(i / 25)));
        }
        if (mCamSapceText != null)
        {
            mCamSapceText.setText(mSocketClient.getDVInfo(3));
        }
        mCurExecuteStatus;
        JVM INSTR tableswitch 3 8: default 172
    //                   3 246
    //                   4 270
    //                   5 270
    //                   6 270
    //                   7 172
    //                   8 223;
           goto _L1 _L2 _L3 _L3 _L3 _L1 _L4
_L1:
        String s;
        String s2;
        s = mSocketClient.getDVInfo(0);
        s2 = mSocketClient.getDVInfo(3);
_L5:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s);
        }
        if (mCamSapceText != null)
        {
            mCamSapceText.setText(s2);
            return;
        }
        break MISSING_BLOCK_LABEL_309;
_L4:
        s = mSocketClient.getDVInfo(10);
        s2 = mSocketClient.getDVInfo(3);
          goto _L5
_L2:
        s = mSocketClient.getDVInfo(8);
        s2 = mSocketClient.getDVInfo(6);
          goto _L5
_L3:
        String s1;
        s = mSocketClient.getDVInfo(9);
        s1 = mSocketClient.getDVInfo(7);
        s2 = s1;
          goto _L5
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        return;
        Exception exception;
        exception;
        exception.printStackTrace();
    }

    private void initDefault()
    {
        mResources = getResources();
        releaseDefault();
        if (mExecuteBtnDrawable == null)
        {
            mExecuteBtnDrawable = new HashMap();
            mExecuteBtnDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f02001e));
            mExecuteBtnDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f02001c));
            mExecuteBtnDrawable.put(Integer.valueOf(5), getResources().getDrawable(0x7f02001a));
            mExecuteBtnDrawable.put(Integer.valueOf(6), getResources().getDrawable(0x7f020019));
            mExecuteBtnDrawable.put(Integer.valueOf(8), getResources().getDrawable(0x7f02001b));
        }
        if (mStateBtnDrawable == null)
        {
            mStateBtnDrawable = new HashMap();
            mStateBtnDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020167));
            mStateBtnDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f020165));
            mStateBtnDrawable.put(Integer.valueOf(5), getResources().getDrawable(0x7f020162));
            mStateBtnDrawable.put(Integer.valueOf(6), getResources().getDrawable(0x7f020166));
            mStateBtnDrawable.put(Integer.valueOf(8), getResources().getDrawable(0x7f020163));
        }
        if (mStateBtnRecingDrawable == null)
        {
            mStateBtnRecingDrawable = new HashMap();
            mStateBtnRecingDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020168));
            mStateBtnRecingDrawable.put(Integer.valueOf(8), getResources().getDrawable(0x7f020164));
        }
        if (mExecuteBtnString == null)
        {
            mExecuteBtnString = new HashMap();
            mExecuteBtnString.put(Integer.valueOf(3), mResources.getString(0x7f0b02a8));
            mExecuteBtnString.put(Integer.valueOf(4), mResources.getString(0x7f0b02a9));
            mExecuteBtnString.put(Integer.valueOf(5), mResources.getString(0x7f0b02aa));
            mExecuteBtnString.put(Integer.valueOf(6), mResources.getString(0x7f0b02ab));
            mExecuteBtnString.put(Integer.valueOf(8), mResources.getString(0x7f0b02ae));
            mExecuteBtnString.put(Integer.valueOf(7), mResources.getString(0x7f0b02af));
        }
        if (mBatteryDrawable == null)
        {
            mBatteryDrawable = new HashMap();
            mBatteryDrawable.put(Integer.valueOf(0), getResources().getDrawable(0x7f02002e));
            mBatteryDrawable.put(Integer.valueOf(1), getResources().getDrawable(0x7f020030));
            mBatteryDrawable.put(Integer.valueOf(2), getResources().getDrawable(0x7f020031));
            mBatteryDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020032));
            mBatteryDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f02002f));
            mBatteryDrawable.put(Integer.valueOf(5), getResources().getDrawable(0x7f020033));
        }
        if (mWifiDrawable == null)
        {
            mWifiDrawable = new HashMap();
            mWifiDrawable.put(Integer.valueOf(0), getResources().getDrawable(0x7f020036));
            mWifiDrawable.put(Integer.valueOf(1), getResources().getDrawable(0x7f020037));
            mWifiDrawable.put(Integer.valueOf(2), getResources().getDrawable(0x7f020038));
            mWifiDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020039));
            mWifiDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f02003a));
        }
    }

    private void initHolder()
    {
        mVideoView.getHolder().addCallback(this);
    }

    private int obtainWifiStrength()
    {
        WifiInfo wifiinfo = ((WifiManager)getSystemService("wifi")).getConnectionInfo();
        if (wifiinfo.getBSSID() != null)
        {
            return WifiManager.calculateSignalLevel(wifiinfo.getRssi(), 5);
        } else
        {
            return -1;
        }
    }

    private void onBack()
    {
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        mIsBacked = true;
        mSocketClient.setCurConState(mCurConnectStatus);
        mSocketClient.setCurEXEState(mCurExecuteStatus);
        mSocketClient.setIsExcuting(isRecting);
        mSocketClient.setIsStreamConnected(bStreamConnectDone);
        mSocketClient.setCurConParams(mCurConnectParams);
        mSocketClient.setIsPreviewClosed(mIsPreviewClosed);
        mSocketClient.setIsPreviewNotSupport(mIsPreviewNotSupport);
        if (isRecting)
        {
            mSocketClient.setCurTimer(mTimeCountor);
            synchronized (mHandler)
            {
                mHandler.removeMessages(2);
                mHandler.removeMessages(3);
            }
        }
        mSocketClient.setOnRequestRespondsListener(null);
        finish();
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void refreshTitleBarBg(int i)
    {
        i;
        JVM INSTR tableswitch 1 2: default 24
    //                   1 25
    //                   2 43;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if (mTitleLayout != null)
        {
            mTitleLayout.setBackgroundResource(0x7f0202b6);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mTitleLayout != null)
        {
            mTitleLayout.setBackgroundResource(0x7f0202b7);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    private void registerWIFIReceiver()
    {
        if (mRssiReceiver != null)
        {
            return;
        } else
        {
            mRssiReceiver = new BroadcastReceiver() {

                final AEEVideoStreamFullScreenActivity this$0;

                public void onReceive(Context context, Intent intent)
                {
                    Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("intent = ").append(intent.getAction()).toString());
                    sendHandleMSGWithTime(11, 0x10000029, -1, String.valueOf(obtainWifiStrength()), 0L);
                }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
            };
            registerReceiver(mRssiReceiver, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
            return;
        }
    }

    private void releaseDefault()
    {
        if (mExecuteBtnDrawable != null)
        {
            mExecuteBtnDrawable.clear();
            mExecuteBtnDrawable = null;
        }
        if (mStateBtnDrawable != null)
        {
            mStateBtnDrawable.clear();
            mStateBtnDrawable = null;
        }
        if (mStateBtnRecingDrawable != null)
        {
            mStateBtnRecingDrawable.clear();
            mStateBtnRecingDrawable = null;
        }
        if (mExecuteBtnString != null)
        {
            mExecuteBtnString.clear();
            mExecuteBtnString = null;
        }
        if (mBatteryDrawable != null)
        {
            mBatteryDrawable.clear();
            mBatteryDrawable = null;
        }
        if (mWifiDrawable != null)
        {
            mWifiDrawable.clear();
            mWifiDrawable = null;
        }
    }

    private void releaseUI()
    {
        if (mCamWifi != null)
        {
            mCamWifi = null;
        }
        if (mCamBattery != null)
        {
            mCamBattery = null;
        }
        if (mCamSapceText != null)
        {
            mCamSapceText = null;
        }
        if (mFileSize != null)
        {
            mFileSize = null;
        }
        if (mExecutBtn != null)
        {
            mExecutBtn = null;
        }
        if (mStatePlayBackBtn != null)
        {
            mStatePlayBackBtn = null;
        }
        if (mStatusIcon != null)
        {
            mStatusIcon = null;
        }
        if (mStatusText != null)
        {
            mStatusText = null;
        }
        if (mStateBtn != null)
        {
            mStateBtn = null;
        }
        if (mInfoLayout != null)
        {
            mInfoLayout = null;
        }
        if (mPreviewLayout != null)
        {
            mPreviewLayout = null;
        }
        if (mStateBtnsLayout != null)
        {
            mStateBtnsLayout = null;
        }
        if (mVideoView != null)
        {
            mVideoView.setVisibility(8);
            mVideoView = null;
        }
        if (mSurfaceHolder != null)
        {
            mSurfaceHolder = null;
        }
        if (mViewGroup != null)
        {
            SystemUtils.unbindDrawables(mViewGroup);
            mViewGroup = null;
        }
        System.gc();
    }

    private void sendCommandFailed(int i, int j, String s)
    {
        synchronized (mHandler)
        {
            if (mHandler != null)
            {
                Message message = mHandler.obtainMessage(4);
                message.arg1 = i;
                message.arg2 = j;
                message.obj = s;
                mHandler.removeMessages(4);
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
            message.what = 8;
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

    private void sendHandleMSGWithTime(int i, int j, int k, Object obj, long l)
    {
        Handler handler = mHandler;
        handler;
        JVM INSTR monitorenter ;
        Message message;
        message = mHandler.obtainMessage(i);
        message.what = i;
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        message.arg1 = j;
        if (k == -1)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        message.arg2 = k;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        message.obj = obj;
        mHandler.sendMessageDelayed(message, l);
        handler;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void setHandleRecing(boolean flag, int i)
    {
        Handler handler = mHandler;
        handler;
        JVM INSTR monitorenter ;
        Message message;
        mHandler.removeMessages(9);
        message = mHandler.obtainMessage(9);
        message.what = 9;
        Exception exception;
        int j;
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        message.arg1 = j;
        message.arg2 = i;
        mHandler.sendMessage(message);
        handler;
        JVM INSTR monitorexit ;
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void setRecting(boolean flag)
    {
        int i;
        Object obj;
        if (isRecting != flag)
        {
            isRecting = flag;
        }
        switchExeState(isRecting, mCurExecuteStatus);
        i = 0;
        obj = null;
        int j;
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        j = mCurExecuteStatus;
        i = 0;
        obj = null;
        j;
        JVM INSTR tableswitch 3 8: default 88
    //                   3 140
    //                   4 344
    //                   5 344
    //                   6 246
    //                   7 88
    //                   8 198;
           goto _L1 _L2 _L3 _L3 _L4 _L1 _L5
_L1:
        if (mCamNumText != null && i != 0)
        {
            mCamNumText.setText(String.valueOf(i));
        }
        if (mCamSapceText != null && obj != null)
        {
            mCamSapceText.setText(((CharSequence) (obj)));
        }
        return;
_L2:
        boolean flag3 = isRecting;
        i = 0;
        obj = null;
        if (!flag3) goto _L1; else goto _L6
_L6:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(8));
        mSocketClient.setDVInfo(8, String.valueOf(i));
        Exception exception;
        obj = null;
          goto _L1
_L5:
        boolean flag2 = isRecting;
        i = 0;
        obj = null;
        if (!flag2) goto _L1; else goto _L7
_L7:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(10));
        mSocketClient.setDVInfo(10, String.valueOf(i));
        obj = null;
          goto _L1
_L4:
        int l;
        boolean flag1;
        l = Integer.parseInt(mSocketClient.getDVInfo(7));
        flag1 = isRecting;
        i = 0;
        obj = null;
        if (flag1)
        {
            i = 0;
            obj = null;
            if (flag)
            {
                i = 0;
                obj = null;
                if (l > 0)
                {
                    try
                    {
                        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(9));
                        mSocketClient.setDVInfo(9, String.valueOf(i));
                        obj = String.valueOf(l - 1);
                        mSocketClient.setDVInfo(7, String.valueOf(obj));
                    }
                    // Misplaced declaration of an exception variable
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }
        }
          goto _L1
_L3:
        int k = Integer.parseInt(mSocketClient.getDVInfo(7));
        i = 0;
        obj = null;
        if (flag) goto _L1; else goto _L8
_L8:
        i = 0;
        obj = null;
        if (k <= 0) goto _L1; else goto _L9
_L9:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(9));
        obj = String.valueOf(k - 1);
        mSocketClient.setDVInfo(9, String.valueOf(i));
        mSocketClient.setDVInfo(7, String.valueOf(obj));
          goto _L1
    }

    private void setStreamConnectDone(boolean flag)
    {
        bStreamConnectDone = flag;
    }

    private void setSurfaceViewSize(int i, int j)
    {
        int k;
        int l;
        int i1;
        k = mRealVideoWidth;
        l = 0;
        i1 = 0;
        if (k <= 0) goto _L2; else goto _L1
_L1:
        int j1;
        j1 = mRealVideoHeight;
        l = 0;
        i1 = 0;
        if (j1 <= 0) goto _L2; else goto _L3
_L3:
        if (j * mRealVideoWidth <= i * mRealVideoHeight) goto _L5; else goto _L4
_L4:
        i1 = i;
        l = (i * mRealVideoHeight) / mRealVideoWidth;
_L2:
        Log.v("zdf", (new StringBuilder()).append("setSurfaceViewSize, fitWidth = ").append(i1).append(", fitHeight = ").append(l).toString());
        if (mVideoView != null)
        {
            android.widget.RelativeLayout.LayoutParams layoutparams1 = (android.widget.RelativeLayout.LayoutParams)mVideoView.getLayoutParams();
            layoutparams1.height = l;
            layoutparams1.width = i1;
            mVideoView.setLayoutParams(layoutparams1);
        }
        if (mPreviewLayout != null)
        {
            android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)mPreviewLayout.getLayoutParams();
            layoutparams.height = l;
            layoutparams.width = i1;
            mPreviewLayout.setLayoutParams(layoutparams);
        }
        return;
_L5:
        int k1 = j * mRealVideoWidth;
        int l1 = i * mRealVideoHeight;
        l = 0;
        i1 = 0;
        if (k1 < l1)
        {
            i1 = (j * mRealVideoWidth) / mRealVideoHeight;
            l = j;
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    private void startTimer()
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mHandler.sendEmptyMessage(3);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stopTimer()
    {
        synchronized (mHandler)
        {
            mHandler.removeMessages(2);
            mHandler.removeMessages(3);
            mHandler.sendEmptyMessage(2);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void switchConState(int i, int j)
    {
        i;
        JVM INSTR tableswitch 0 8: default 52
    //                   0 587
    //                   1 53
    //                   2 534
    //                   3 52
    //                   4 52
    //                   5 52
    //                   6 52
    //                   7 52
    //                   8 649;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L1 _L1 _L1 _L5
_L7:
        return;
_L3:
        setStreamConnectDone(false);
        if ((mContentViewWidth == 0 || mContentViewHeight == 0) && mContentLayout != null)
        {
            mContentViewWidth = mContentLayout.getWidth();
            mContentViewHeight = mContentLayout.getHeight();
            getScreenAvailableSize(mContentViewWidth, mContentViewHeight);
            setSurfaceViewSize(mContentViewWidth, mContentViewHeight);
            mContentLayout.invalidate();
        }
        if (mStatusText != null)
        {
            mStatusText.setText(getString(0x7f0b017a));
            mStatusText.setVisibility(0);
        }
        if (mStatusIcon != null)
        {
            mStatusIcon.setVisibility(8);
        }
        if (mCurConnectParams != 19)
        {
            if (mVideoView != null)
            {
                mVideoView.setVisibility(8);
            }
            if (mCurConnectParams != 20)
            {
                if (mArcMediaPlayer != null)
                {
                    mArcMediaPlayer.reset();
                    bPlayerStarted = false;
                }
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    mSocketClient.setIsFirstStartPreview(true);
                }
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            }
        }
        isExecuting = false;
        mIsSetParams = false;
        switch (j)
        {
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        default:
            return;

        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.releaseCurTokenId();
                mSocketClient.setIsNeedEncoding(false);
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            if (isRecting)
            {
                stopTimer();
                setHandleRecing(false, mCurExecuteStatus);
            }
            if (mExecutBtn != null)
            {
                mExecutBtn.setEnabled(false);
            }
            uninitDVInfo();
            initDVInfo();
            return;

        case 18: // '\022'
            if (mStatusText != null)
            {
                mStatusText.setText(getString(0x7f0b0169));
            }
            if (mExecutBtn != null)
            {
                mExecutBtn.setEnabled(true);
                return;
            }
            break;

        case 20: // '\024'
            if (mStatusText != null)
            {
                mStatusText.setText(getString(0x7f0b0167));
            }
            if (mExecutBtn != null)
            {
                mExecutBtn.setEnabled(true);
                return;
            }
            break;

        case 19: // '\023'
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.releaseCurTokenId();
                mSocketClient.setIsNeedEncoding(false);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            if (mStatusText != null)
            {
                mStatusText.setVisibility(8);
            }
            continue; /* Loop/switch isn't completed */
        }
_L1:
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (isPaused) goto _L7; else goto _L6
_L6:
        showDialog(1);
        return;
_L4:
        isSocketConnected = true;
        if (mStatusText != null)
        {
            mStatusText.setVisibility(8);
        }
        if (mStatusIcon != null)
        {
            mStatusIcon.setVisibility(8);
        }
        if (mExecutBtn != null)
        {
            mExecutBtn.setEnabled(true);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (mStatusText != null)
        {
            mStatusText.setText(getString(0x7f0b0166));
            mStatusText.setVisibility(0);
        }
        if (mStatusIcon != null)
        {
            mStatusIcon.setVisibility(8);
        }
        if (mVideoView != null)
        {
            mVideoView.setVisibility(8);
            return;
        }
        if (true) goto _L7; else goto _L5
_L5:
        if (mStatusText != null)
        {
            mStatusText.setVisibility(8);
        }
        if (mStatusIcon != null)
        {
            ImageView imageview = mStatusIcon;
            int k;
            if (!isRecting)
            {
                k = 0x7f020023;
            } else
            {
                k = 0x7f020022;
            }
            imageview.setBackgroundResource(k);
            mStatusIcon.setVisibility(0);
        }
        if (mVideoView != null)
        {
            mVideoView.setVisibility(8);
        }
        if (mExecutBtn != null)
        {
            mExecutBtn.setEnabled(true);
            return;
        }
        if (true) goto _L7; else goto _L8
_L8:
    }

    private void switchExeState(boolean flag, int i)
    {
        Drawable drawable;
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("switchExeState isRecting = ").append(flag).append(" toState = ").append(i).toString());
        boolean flag1;
        if (i == 3 || i == 8)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (mFileSize != null)
        {
            TextView textview1 = mFileSize;
            HashMap hashmap1;
            Drawable drawable1;
            HashMap hashmap2;
            String s;
            HashMap hashmap3;
            TextView textview;
            int k;
            if (flag1)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            textview1.setVisibility(k);
        }
        if (mCamNumText != null)
        {
            textview = mCamNumText;
            int j;
            if (!flag1)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            textview.setVisibility(j);
        }
        if (!flag || mCurExecuteStatus != 3 && mCurExecuteStatus != 8) goto _L2; else goto _L1
_L1:
        hashmap3 = mStateBtnRecingDrawable;
        drawable = null;
        if (hashmap3 != null)
        {
            drawable = (Drawable)mStateBtnRecingDrawable.get(Integer.valueOf(i));
        }
_L8:
        if (drawable != null && mStateBtn != null)
        {
            mStateBtn.setBackgroundDrawable(drawable);
        }
        hashmap1 = mExecuteBtnDrawable;
        drawable1 = null;
        if (hashmap1 != null)
        {
            drawable1 = (Drawable)mExecuteBtnDrawable.get(Integer.valueOf(i));
        }
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("switchExeState curStateView = ").append(drawable1).toString());
        if (drawable1 != null && mExecutBtn != null)
        {
            mExecutBtn.setBackgroundDrawable(drawable1);
            mExecutBtn.setSelected(flag);
        }
        hashmap2 = mExecuteBtnString;
        s = null;
        if (hashmap2 != null)
        {
            s = (String)mExecuteBtnString.get(Integer.valueOf(i));
        }
        if (mTitleText != null && s != null)
        {
            mTitleText.setText(s);
        }
        mCurExecuteStatus = i;
        if (i != 8) goto _L4; else goto _L3
_L3:
        if (mStatusIcon != null)
        {
            if (mStatusIcon.getBackground() == null)
            {
                mStatusIcon.setBackgroundResource(0x7f020035);
            }
            mStatusIcon.setVisibility(0);
        }
        if (mStatusText != null && mStatusText.getVisibility() == 0)
        {
            mStatusText.setVisibility(8);
        }
        if (mVideoView.getVisibility() == 0)
        {
            mVideoView.setVisibility(8);
        }
_L6:
        return;
_L2:
        HashMap hashmap = mStateBtnDrawable;
        drawable = null;
        if (hashmap != null)
        {
            drawable = (Drawable)mStateBtnDrawable.get(Integer.valueOf(i));
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mStatusIcon != null && mStatusIcon.getVisibility() == 0)
        {
            mStatusIcon.setVisibility(8);
        }
        if (mVideoView.getVisibility() == 0 || !bStreamConnectDone) goto _L6; else goto _L5
_L5:
        mVideoView.setVisibility(0);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void uninitDVInfo()
    {
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            mSocketClient.uninitDVInfo();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void unregisterWIFIReceiver()
    {
        if (mRssiReceiver != null)
        {
            unregisterReceiver(mRssiReceiver);
        }
        mRssiReceiver = null;
    }

    private void updateDVInfo(int i, String s)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("updateDVInfo infoType = ").append(i).append(" paramStr = ").append(s).toString());
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (s != null || i != 0x10000026) goto _L2; else goto _L1
_L1:
        if (mCamNumText != null)
        {
            mCamNumText.setText("001");
        }
        if (mCamSapceText != null)
        {
            mCamSapceText.setText("100");
        }
_L9:
        return;
_L2:
        String as[] = s.split(";");
        i;
        JVM INSTR tableswitch 268435494 268435498: default 144
    //                   268435494 145
    //                   268435495 398
    //                   268435496 144
    //                   268435497 443
    //                   268435498 501;
           goto _L3 _L4 _L5 _L3 _L6 _L7
_L3:
        return;
_L4:
        if (as.length <= 5) goto _L9; else goto _L8
_L8:
        String s1;
        String s2;
        String s3;
        String s4;
        String s5;
        String s6;
        s1 = (new StringBuilder()).append(Integer.parseInt(as[0])).append("%").toString();
        int l = Integer.parseInt(as[1]);
        s2 = (new StringBuilder()).append(l / 60).append("H").append(l % 60).toString();
        s3 = as[2];
        s4 = as[3];
        s5 = as[4];
        s6 = as[5];
        mSocketClient.setDVInfo(3, s1);
        mSocketClient.setDVInfo(6, s2);
        mSocketClient.setDVInfo(7, s3);
        mSocketClient.setDVInfo(8, s4);
        mSocketClient.setDVInfo(9, s5);
        mSocketClient.setDVInfo(10, s6);
        mCurExecuteStatus;
        JVM INSTR tableswitch 3 8: default 356
    //                   3 605
    //                   4 572
    //                   5 572
    //                   6 572
    //                   7 356
    //                   8 638;
           goto _L10 _L11 _L12 _L12 _L12 _L10 _L13
_L10:
        if (mCamNumText != null)
        {
            mCamNumText.setText("001");
        }
        if (mCamSapceText == null) goto _L9; else goto _L14
_L14:
        Exception exception;
        mCamSapceText.setText(s1);
        return;
_L5:
        if (as.length != 3) goto _L9; else goto _L15
_L15:
        mSocketClient.setDVInfo(4, as[0]);
        mSocketClient.setDVInfo(11, as[1]);
        mSocketClient.setDVInfo(5, as[2]);
        return;
_L6:
        try
        {
            if (mCamWifi != null && mWifiDrawable != null)
            {
                int k = Integer.parseInt(as[0]);
                mCamWifi.setBackgroundDrawable((Drawable)mWifiDrawable.get(Integer.valueOf(k)));
            }
            mSocketClient.setDVInfo(1, as[0]);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            exception.printStackTrace();
            return;
        }
_L7:
        int j = 100;
        if (as.length > 0)
        {
            j = Integer.parseInt(as[0]);
        }
        if (mBatteryDrawable != null && mCamBattery != null)
        {
            mCamBattery.setBackgroundDrawable((Drawable)mBatteryDrawable.get(Integer.valueOf(j / 25)));
        }
        mSocketClient.setDVInfo(2, as[0]);
        return;
_L12:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s5);
        }
        if (mCamSapceText == null) goto _L9; else goto _L16
_L16:
        mCamSapceText.setText(s3);
        return;
_L11:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s4);
        }
        if (mCamSapceText == null) goto _L9; else goto _L17
_L17:
        mCamSapceText.setText(s2);
        return;
_L13:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s6);
        }
        if (mCamSapceText != null)
        {
            mCamSapceText.setText(s2);
            return;
        }
          goto _L9
    }

    protected void hideSurfaceView()
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(12);
                mHandler.sendEmptyMessage(12);
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

    public void onBackPressed()
    {
        onBack();
        super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        Log.v("zdf", (new StringBuilder()).append("onConfigurationChanged, newConfig.orientation = ").append(configuration.orientation).toString());
        refreshTitleBarBg(configuration.orientation);
        configuration.orientation;
        JVM INSTR tableswitch 1 2: default 64
    //                   1 70
    //                   2 99;
           goto _L1 _L2 _L3
_L1:
        super.onConfigurationChanged(configuration);
        return;
_L2:
        if (mScreenAvailablePortWidth > 0 && mScreenAvailablePortHeight > 0)
        {
            setSurfaceViewSize(mScreenAvailablePortWidth, mScreenAvailablePortHeight);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mScreenAvailableLandWidth > 0 && mScreenAvailableLandHeight > 0)
        {
            setSurfaceViewSize(mScreenAvailableLandWidth, mScreenAvailableLandHeight);
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.e("AEEVideoStreamFullScreenActivity", "onCreate  --------------------------------------- ");
        requestWindowFeature(1);
        getWindow().setFlags(2048, 2048);
        Intent intent = getIntent();
        if (intent != null)
        {
            isRecting = intent.getBooleanExtra("is_cur_executing", false);
            bStreamConnectDone = intent.getBooleanExtra("is_stream_connected", false);
            mIsPreviewClosed = intent.getBooleanExtra("is_preview_closed", false);
            mIsPreviewNotSupport = intent.getBooleanExtra("is_preview_not_support", false);
            mCurConnectParams = intent.getIntExtra("connect_failed_params", -1);
            mCurExecuteStatus = intent.getIntExtra("cur_exe_state", 3);
            mTimeCountor = intent.getIntExtra("get_recting_time", 0);
        }
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("isRecting = ").append(isRecting).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" bStreamConnectDone = ").append(bStreamConnectDone).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mIsPreviewClosed = ").append(mIsPreviewClosed).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mIsPreviewNotSupport = ").append(mIsPreviewNotSupport).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mCurConnectParams = ").append(mCurConnectParams).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append(" mTimeCountor = ").append(mTimeCountor).toString());
        mPlayerPluginPath = MediaPlusApplication.instance().getAssetFilePath(2);
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("onCreate IN  ------- mPlayerPluginPath: ").append(mPlayerPluginPath).toString());
        initDefault();
        init();
        initDVInfo();
        switchExeState(isRecting, mCurExecuteStatus);
        if (isRecting && mFileSize != null && mTimeCountor != 0)
        {
            startTimer();
        } else
        {
            mFileSize.setText("00:00");
        }
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        mNetworkTool = new NetworkTool(this);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
        mSocketClient.setOnRequestRespondsListener(mOnRequestRespondsListener);
    }

    protected Dialog onCreateDialog(int i)
    {
        i;
        JVM INSTR tableswitch 1 1: default 20
    //                   1 26;
           goto _L1 _L2
_L1:
        return super.onCreateDialog(i);
_L2:
        (new android.app.AlertDialog.Builder(this)).setCancelable(false).setMessage(0x7f0b018d).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final AEEVideoStreamFullScreenActivity this$0;

            public void onClick(DialogInterface dialoginterface, int j)
            {
                synchronized (mHandler)
                {
                    mHandler.removeMessages(7);
                    mHandler.sendEmptyMessage(7);
                }
                dialoginterface.dismiss();
                return;
                exception;
                handler;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
        }).show();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onDestroy()
    {
        Log.e("AEEVideoStreamFullScreenActivity", "onDestroy  --------------------------------------- ");
        super.onDestroy();
        bPlayerStarted = false;
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.release();
            mArcMediaPlayer = null;
        }
        if (mNetworkTool != null)
        {
            mNetworkTool.recycle();
            mNetworkTool = null;
        }
        releaseUI();
        releaseDefault();
        System.gc();
    }

    protected void onPause()
    {
        Log.e("AEEVideoStreamFullScreenActivity", "onPause");
        isPaused = true;
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.reset();
        }
        bPlayerStarted = false;
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient.isConnected() && bStreamConnectDone)
            {
                hideSurfaceView();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        unregisterWIFIReceiver();
        SystemUtils.keepScreenOn(this, false);
        super.onPause();
    }

    protected void onResume()
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("onResume isWifiConnected = ").append(isWifiConnected).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("onResume mCurConnectStatus = ").append(mCurConnectStatus).append(" mCurConnectParams = ").append(mCurConnectParams).toString());
        isPaused = false;
        if (!isWifiConnected) goto _L2; else goto _L1
_L1:
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        switchTo(mCurConnectStatus, mCurConnectParams);
        if (!mSocketClient.isConnected()) goto _L2; else goto _L3
_L3:
        if (mCurExecuteStatus != 8) goto _L5; else goto _L4
_L4:
        switchTo(8, -1);
_L7:
        if (isRecting)
        {
            sendHandleCMD(0x1000002c, 0x10000026, 0L);
        }
_L2:
        if (mCurConnectParams == 19)
        {
            switchTo(mCurConnectStatus, mCurConnectParams);
        }
        registerWIFIReceiver();
        super.onResume();
        return;
_L5:
        if (mCurExecuteStatus != 8 && bStreamConnectDone)
        {
            switchTo(2, -1);
            if (mVideoView != null)
            {
                mVideoView.setVisibility(0);
            }
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void resumePlayback()
    {
        Log.e("AEEVideoStreamFullScreenActivity", "resumePlayback()  ");
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.reset();
        }
        showStream();
    }

    protected void showStream()
    {
        if (mArcMediaPlayer == null)
        {
            mArcMediaPlayer = new SecureMediaPlayer();
            mArcMediaPlayer.setConfigFile(mPlayerPluginPath);
        }
        try
        {
            mArcMediaPlayer.reset();
            mArcMediaPlayer.setDataSource(AEESocketClient.getAEEUrl());
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
        catch (IllegalStateException illegalstateexception)
        {
            illegalstateexception.printStackTrace();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        mArcMediaPlayer.setAudioStreamType(3);
        mArcMediaPlayer.setScreenOnWhilePlaying(true);
        mArcMediaPlayer.setDisplay(mSurfaceHolder);
        mArcMediaPlayer.setBenchmark(2);
        mArcMediaPlayer.setOnErrorListener(new android.media.MediaPlayer.OnErrorListener() {

            final AEEVideoStreamFullScreenActivity this$0;

            public boolean onError(MediaPlayer mediaplayer, int i, int j)
            {
                Log.v("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("full showStream onerror what=").append(i).append(";extra=").append(j).toString());
                bPlayerStarted = false;
                switchTo(1, 14);
                Toast.makeText(AEEVideoStreamFullScreenActivity.this, 0x7f0b019f, 1).show();
                return false;
            }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
        });
        mArcMediaPlayer.setOnVideoSizeChangedListener(new android.media.MediaPlayer.OnVideoSizeChangedListener() {

            final AEEVideoStreamFullScreenActivity this$0;

            public void onVideoSizeChanged(MediaPlayer mediaplayer, int i, int j)
            {
                Log.v("zdf", (new StringBuilder()).append("onVideoSizeChanged, width = ").append(i).append(", height = ").append(j).toString());
                mRealVideoWidth = i;
                mRealVideoHeight = j;
                if (mContentLayout != null)
                {
                    mContentViewWidth = mContentLayout.getWidth();
                    mContentViewHeight = mContentLayout.getHeight();
                    getScreenAvailableSize(mContentViewWidth, mContentViewHeight);
                    setSurfaceViewSize(mContentViewWidth, mContentViewHeight);
                    mContentLayout.invalidate();
                }
            }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
        });
        mArcMediaPlayer.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {

            final AEEVideoStreamFullScreenActivity this$0;

            public void onPrepared(MediaPlayer mediaplayer)
            {
                Log.e("AEEVideoStreamFullScreenActivity", "onPrepared IN  --------------------------------------- ");
                if (mArcMediaPlayer != null)
                {
                    mArcMediaPlayer.start();
                }
            }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
        });
        mArcMediaPlayer.setOnInfoListener(new android.media.MediaPlayer.OnInfoListener() {

            final AEEVideoStreamFullScreenActivity this$0;

            public boolean onInfo(MediaPlayer mediaplayer, int i, int j)
            {
                Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("what = ").append(i).append(" extra = ").append(j).toString());
                switch (i)
                {
                default:
                    return false;

                case 900: 
                    break;
                }
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    mSocketClient.setIsFirstStartPreview(false);
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                    return false;
                }
                return false;
            }

            
            {
                this$0 = AEEVideoStreamFullScreenActivity.this;
                super();
            }
        });
        mArcMediaPlayer.prepareAsync();
    }

    protected void showSurfaceView(int i)
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessageDelayed(1, i);
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

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("FENG  surfaceChanged  ---- width: ").append(j).append(" height: ").append(k).append(" bStreamConnectDone = ").append(bStreamConnectDone).toString());
        if (bStreamConnectDone && bPlayerStarted && mArcMediaPlayer != null)
        {
            mArcMediaPlayer.setDisplayRect(0, 0, j, k);
            mArcMediaPlayer.setVolume(1.0F, 1.0F);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        Log.e("AEEVideoStreamFullScreenActivity", "surfaceCreated  ----  ");
        mSurfaceHolder = surfaceholder;
        mSurfaceHolder.setType(0);
        mSurfaceHolder.setFormat(4);
        if (bStreamConnectDone)
        {
            if (bPlayerStarted && mArcMediaPlayer != null)
            {
                mArcMediaPlayer.setDisplay(mSurfaceHolder);
            } else
            {
                showStream();
                bPlayerStarted = true;
            }
            SystemUtils.keepScreenOn(this, true);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        Log.e("AEEVideoStreamFullScreenActivity", "surfaceDestroyed  ----  ");
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.setVolume(0.0F, 0.0F);
            mArcMediaPlayer.setDisplay(null);
        }
        SystemUtils.keepScreenOn(this, false);
    }

    protected void switchTo(int i, int j)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("switchTo type = ").append(i).append(", mCurConStatus = ").append(mCurConnectStatus).toString());
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("switchTo params = ").append(j).toString());
        i;
        JVM INSTR tableswitch 0 8: default 116
    //                   0 117
    //                   1 117
    //                   2 117
    //                   3 116
    //                   4 116
    //                   5 116
    //                   6 116
    //                   7 116
    //                   8 195;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L3
_L1:
        return;
_L2:
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(0);
                Message message1 = mHandler.obtainMessage(0);
                message1.arg1 = i;
                message1.arg2 = j;
                mHandler.sendMessage(message1);
            }
        }
        mCurConnectStatus = i;
        mCurConnectParams = j;
        return;
        exception1;
        handler1;
        JVM INSTR monitorexit ;
        throw exception1;
_L3:
        if (mHandler == null) goto _L1; else goto _L4
_L4:
        synchronized (mHandler)
        {
            mHandler.removeMessages(0);
            Message message = mHandler.obtainMessage(0);
            message.arg1 = i;
            message.arg2 = -1;
            mHandler.sendMessage(message);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void updateSurfaceView(int i)
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(10);
                mHandler.sendEmptyMessageDelayed(10, i);
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



/*
    static boolean access$002(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.bPlayerStarted = flag;
        return flag;
    }

*/



/*
    static int access$102(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mRealVideoWidth = i;
        return i;
    }

*/



/*
    static boolean access$1102(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.isRecting = flag;
        return flag;
    }

*/



/*
    static int access$1202(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mCurConnectParams = i;
        return i;
    }

*/




/*
    static boolean access$1402(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.mIsPreviewClosed = flag;
        return flag;
    }

*/



/*
    static boolean access$1502(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.mIsPreviewNotSupport = flag;
        return flag;
    }

*/



/*
    static boolean access$1602(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.isExecuting = flag;
        return flag;
    }

*/



/*
    static boolean access$1702(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.mIsSetParams = flag;
        return flag;
    }

*/





/*
    static int access$202(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mRealVideoHeight = i;
        return i;
    }

*/






/*
    static boolean access$2402(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.isWifiConnected = flag;
        return flag;
    }

*/







/*
    static int access$2902(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mTimeCountor = i;
        return i;
    }

*/


/*
    static int access$2904(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity)
    {
        int i = 1 + aeevideostreamfullscreenactivity.mTimeCountor;
        aeevideostreamfullscreenactivity.mTimeCountor = i;
        return i;
    }

*/



/*
    static int access$3002(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mCurSpendTime = i;
        return i;
    }

*/








/*
    static ConnectSocketTask access$3602(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, ConnectSocketTask connectsockettask)
    {
        aeevideostreamfullscreenactivity.mSocketTask = connectsockettask;
        return connectsockettask;
    }

*/





/*
    static boolean access$3902(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, boolean flag)
    {
        aeevideostreamfullscreenactivity.mIsFirstStreaming = flag;
        return flag;
    }

*/




/*
    static int access$402(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mContentViewWidth = i;
        return i;
    }

*/




/*
    static int access$502(AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity, int i)
    {
        aeevideostreamfullscreenactivity.mContentViewHeight = i;
        return i;
    }

*/




}
