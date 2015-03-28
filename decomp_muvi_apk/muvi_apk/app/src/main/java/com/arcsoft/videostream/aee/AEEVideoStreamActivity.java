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
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.MediaPlusApplication;
import com.arcsoft.mediaplus.setting.AEESettingCMDListActivity;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient, AEEVideoStreamFullScreenActivity, AEEUtilDef

public class AEEVideoStreamActivity extends Activity
    implements android.view.SurfaceHolder.Callback
{
    public class ConnectSocketTask extends AsyncTask
    {

        final AEEVideoStreamActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
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
                break MISSING_BLOCK_LABEL_121;
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected() && mPauseType == -1)
            {
                synchronized (mHandler)
                {
                    mHandler.removeMessages(7);
                    mHandler.sendEmptyMessage(7);
                }
                return;
            }
            break MISSING_BLOCK_LABEL_111;
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
            this$0 = AEEVideoStreamActivity.this;
            super();
        }
    }

    public class SetParamTask extends AsyncTask
    {

        final AEEVideoStreamActivity this$0;

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
            mSocketClient.sendCommandSuc(0x10000001, null);
            mSocketClient.startRespondParams(0x10000001);
            mSocketClient.setNextCMD(0x1000002b);
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
            this$0 = AEEVideoStreamActivity.this;
            super();
        }
    }


    public static final int CONNECTED_TYPE_IS_EXCUTED = 22;
    public static final int CONNECTED_TYPE_IS_EXCUTING = 21;
    public static final int CONNECTED_TYPE_POWER_ON = 17;
    public static final int DLG_ID_DEVICE_OPERATED = 1;
    public static final int FAIL_TYPE_DEVICE_OPERATED = 19;
    public static final int FAIL_TYPE_POWER_OFF = 16;
    public static final int FAIL_TYPE_PREVIEW_CLOSED = 20;
    public static final int FAIL_TYPE_PREVIEW_FAILED = 14;
    public static final int FAIL_TYPE_PREVIEW_NOTSURPORT = 18;
    public static final int FAIL_TYPE_SESSION_FAILED = 13;
    public static final int FAIL_TYPE_SOCKET_FAILED = 12;
    public static final int FAIL_TYPE_WIFI_DISCONNECT = 11;
    private static final boolean RTSP_HARDWARE_DECODER = false;
    private static final int SEND_SHOW_STREAMING_DELAY = 1500;
    private static final String TAG = "AEEVideoStreamActivity";
    private static final int TIMER_DELAY = 1000;
    public static final int TYPE_CONNECTED = 2;
    public static final int TYPE_CONNECTING = 0;
    public static final int TYPE_CONNECT_FAILED = 1;
    protected static final int TYPE_CONSHOT = 6;
    protected static final int TYPE_FASTSHOT = 5;
    protected static final int TYPE_PHOTO = 4;
    protected static final int TYPE_PLAYBACK = 9;
    protected static final int TYPE_ROLLOVER = 7;
    protected static final int TYPE_VIDEO = 3;
    protected static final int TYPE_VOICE = 8;
    public static final int VAL_PLAYER_BUFFER = 702;
    public static final int VAL_PLAYER_CONNECT = 701;
    public static final int VAL_PLAYER_DISPLAY = 900;
    private final int MSG_HIDE_SURFACEVIEW = 6;
    private final int MSG_INIT_DV_INFO = 11;
    private final int MSG_RESET_SURFACEVIEW = 15;
    private final int MSG_RESUME_PLAYBACK = 5;
    private final int MSG_SEND_COMMAND = 8;
    private final int MSG_SEND_COMMAND_FAILED = 4;
    private final int MSG_SET_PREVIEW_PARAMS = 7;
    private final int MSG_SET_STATE_BTN_ENABLE = 18;
    private final int MSG_SHOW_SURFACEVIEW = 1;
    private final int MSG_START_TIMER = 3;
    private final int MSG_STOP_TIMER = 2;
    private final int MSG_SWITCH_EXE_SELECTED = 16;
    private final int MSG_SWITCH_EXE_STATUS = 10;
    private final int MSG_SWITCH_STATUS = 0;
    private final int MSG_TOAST_SHORT = 17;
    private final int MSG_UPDATE_DV_INFO = 12;
    private final int MSG_UPDATE_EXE_SELECTED = 9;
    private final int MSG_UPDATE_STATE_SELECTED = 14;
    private final int MSG_UPDATE_SURFACEVIEW = 13;
    private boolean bPlayerStarted;
    private boolean bStreamConnectDone;
    private boolean isExecuting;
    private boolean isPaused;
    private boolean isPlaying;
    private boolean isRecting;
    private boolean isWifiConnected;
    private SecureMediaPlayer mArcMediaPlayer;
    private HashMap mBatteryDrawable;
    private View mBlockView;
    private ImageView mCamBattery;
    private ImageView mCamFocusT;
    private ImageView mCamFocusW;
    private String mCamInfo;
    private TextView mCamInfoText;
    private String mCamManfacturer;
    private String mCamModel;
    private TextView mCamModelText;
    private TextView mCamNumText;
    private TextView mCamSapceText;
    private String mCamVersion;
    private ImageView mCamWifi;
    private int mCurConnectParams;
    private int mCurConnectStatus;
    private int mCurDVMode;
    private int mCurExecuteStatus;
    private int mCurSpendTime;
    private final boolean mDidExecute = false;
    private ImageView mExecutBtn;
    private HashMap mExecuteBtnDrawable;
    private HashMap mExecuteBtnSelectedDrawable;
    private TextView mFileSize;
    private ImageView mFullScreenBtn;
    private final Handler mHandler = new Handler() {

        final AEEVideoStreamActivity this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 18: default 96
        //                       0 120
        //                       1 138
        //                       2 1391
        //                       3 1459
        //                       4 374
        //                       5 828
        //                       6 260
        //                       7 838
        //                       8 992
        //                       9 1266
        //                       10 1553
        //                       11 1567
        //                       12 1896
        //                       13 1917
        //                       14 1994
        //                       15 298
        //                       16 1358
        //                       17 102
        //                       18 2026;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
            super.handleMessage(message);
_L22:
            return;
_L19:
            Toast.makeText(AEEVideoStreamActivity.this, message.arg1, 0).show();
            continue; /* Loop/switch isn't completed */
_L2:
            switchConState(message.arg1, message.arg2);
            continue; /* Loop/switch isn't completed */
_L3:
            Log.e("AEEVideoStreamActivity", "MSG_SHOW_SURFACEVIEW");
            if (!isWifiConnected || isPaused) goto _L22; else goto _L21
_L21:
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
_L8:
            if (mVideoView != null && mVideoView.getVisibility() == 0)
            {
                mVideoView.setVisibility(8);
            }
            continue; /* Loop/switch isn't completed */
_L17:
            if (mVideoView != null && mVideoView.getVisibility() == 0)
            {
                mVideoView.setVisibility(8);
            }
            if (mArcMediaPlayer != null)
            {
                mArcMediaPlayer.reset();
            }
            bPlayerStarted = false;
            isPlaying = false;
            continue; /* Loop/switch isn't completed */
_L6:
            String s1;
            int j1;
            int k1;
            message.arg2;
            s1 = (String)message.obj;
            j1 = message.arg1;
            k1 = 0;
            j1;
            JVM INSTR tableswitch -19 1: default 496
        //                       -19 686
        //                       -18 821
        //                       -17 722
        //                       -16 814
        //                       -15 496
        //                       -14 807
        //                       -13 800
        //                       -12 793
        //                       -11 496
        //                       -10 496
        //                       -9 786
        //                       -8 779
        //                       -7 772
        //                       -6 496
        //                       -5 496
        //                       -4 765
        //                       -3 758
        //                       -2 496
        //                       -1 496
        //                       0 496
        //                       1 517;
               goto _L23 _L24 _L25 _L26 _L27 _L23 _L28 _L29 _L30 _L23 _L23 _L31 _L32 _L33 _L23 _L23 _L34 _L35 _L23 _L23 _L23 _L36
_L23:
            if (k1 != 0)
            {
                Toast.makeText(AEEVideoStreamActivity.this, k1, 0).show();
            }
            continue; /* Loop/switch isn't completed */
_L36:
            k1 = 0x7f0b018e;
            if (s1 != null && s1.contains("16777218"))
            {
                if (isRecting)
                {
                    stopTimer();
                    setHandleUpdateSelectedExe(false, true);
                    if (mCurConnectParams == 18)
                    {
                        switchTo(2, -1);
                        showSurfaceView(0);
                    }
                }
                switchTo(1, 19);
                return;
            }
            if (s1 != null && s1.contains("16777220"))
            {
                k1 = 0x7f0b0198;
            }
            if (k1 != 0x7f0b018e && isRecting)
            {
                stopTimer();
                setHandleUpdateSelectedExe(false, true);
                if (mCurConnectParams == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
                }
            }
            continue; /* Loop/switch isn't completed */
_L24:
            if (mCurConnectParams == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
            k1 = 0x7f0b019c;
            continue; /* Loop/switch isn't completed */
_L26:
            if (mCurConnectParams == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
            k1 = 0x7f0b0198;
            continue; /* Loop/switch isn't completed */
_L35:
            k1 = 0x7f0b018f;
            continue; /* Loop/switch isn't completed */
_L34:
            k1 = 0x7f0b0190;
            continue; /* Loop/switch isn't completed */
_L33:
            k1 = 0x7f0b0191;
            continue; /* Loop/switch isn't completed */
_L32:
            k1 = 0x7f0b0192;
            continue; /* Loop/switch isn't completed */
_L31:
            k1 = 0x7f0b0193;
            continue; /* Loop/switch isn't completed */
_L30:
            k1 = 0x7f0b0194;
            continue; /* Loop/switch isn't completed */
_L29:
            k1 = 0x7f0b0195;
            continue; /* Loop/switch isn't completed */
_L28:
            k1 = 0x7f0b0196;
            continue; /* Loop/switch isn't completed */
_L27:
            k1 = 0x7f0b0197;
            continue; /* Loop/switch isn't completed */
_L25:
            k1 = 0x7f0b0199;
            if (true) goto _L23; else goto _L7
_L7:
            resumePlayback();
            continue; /* Loop/switch isn't completed */
_L9:
            if (!RtspUtils.isAmbar())
            {
                break MISSING_BLOCK_LABEL_972;
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
            if (k == -1 && l == -1) goto _L22; else goto _L37
_L37:
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient == null || !mSocketClient.isConnected()) goto _L39; else goto _L38
_L38:
            if (k == -1)
            {
                k = l;
                l = -1;
            }
            int i1;
            i1 = mSocketClient.sendCommand(k, null);
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("MSG_SEND_COMMAND val = ").append(i1).append(" msgId = ").append(k).toString());
            i1;
            JVM INSTR tableswitch 2 4: default 1140
        //                       2 1177
        //                       3 1194
        //                       4 1194;
               goto _L40 _L41 _L42 _L42
_L40:
            try
            {
                mSocketClient.startRespondParams(k);
                mSocketClient.setNextCMD(l);
            }
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            continue; /* Loop/switch isn't completed */
_L41:
            sendHandleCMD(k, l, 200L);
            continue; /* Loop/switch isn't completed */
_L42:
            if (isExecuting)
            {
                isExecuting = false;
                sendHandleMSGWithTime(18, 22, -1, null, 0L);
            }
            continue; /* Loop/switch isn't completed */
_L39:
            if (isExecuting)
            {
                isExecuting = false;
                sendHandleMSGWithTime(18, 22, -1, null, 0L);
            }
            continue; /* Loop/switch isn't completed */
_L11:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("MSG_UPDATE_EXE_SELECTED msg.arg1 = ").append(message.arg1).append(" msg.arg2 = ").append(message.arg2).toString());
            AEEVideoStreamActivity aeevideostreamactivity2 = AEEVideoStreamActivity.this;
            boolean flag3;
            boolean flag4;
            if (message.arg1 == 1)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            if (message.arg2 == 1)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            aeevideostreamactivity2.updateExeSelectedState(flag3, flag4);
            continue; /* Loop/switch isn't completed */
_L18:
            AEEVideoStreamActivity aeevideostreamactivity1 = AEEVideoStreamActivity.this;
            boolean flag2;
            if (message.arg1 == 1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            aeevideostreamactivity1.switchExeState(flag2);
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
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
    };
    int mHeight;
    private boolean mIsFirstStreaming;
    private boolean mIsPreviewClosed;
    private boolean mIsPreviewNotSupport;
    private boolean mIsSetParams;
    private boolean mIsTwicePressed;
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final AEEVideoStreamActivity this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo != null && networkstateinfo.networkInfo.getType() == 1)
            {
label0:
                {
                    Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("OnConnectivityChanged  ---------- info.networkInfo.isConnected() = ").append(networkstateinfo.networkInfo.isConnected()).toString());
                    isWifiConnected = networkstateinfo.networkInfo.isConnected();
                    AEEVideoStreamActivity aeevideostreamactivity = AEEVideoStreamActivity.this;
                    boolean flag = isWifiConnected;
                    int i = 0;
                    byte byte0;
                    if (!flag)
                    {
                        i = 1;
                    }
                    if (isWifiConnected)
                    {
                        byte0 = -1;
                    } else
                    {
                        byte0 = 11;
                    }
                    aeevideostreamactivity.switchTo(i, byte0);
                    try
                    {
                        mSocketClient = AEESocketClient.getInstanceClient();
                        if (!isWifiConnected)
                        {
                            continue; /* Loop/switch isn't completed */
                        }
                        if (mPauseType != 0x10000066)
                        {
                            if (mSocketClient == null || !mSocketClient.isConnected())
                            {
                                break MISSING_BLOCK_LABEL_281;
                            }
                            if (mPauseType == -1)
                            {
                                if (mSocketClient.getCurTokenId() > 0)
                                {
                                    mIsSetParams = true;
                                    sendHandleCMD(0x1000002b, 0x10000033, 0L);
                                    return;
                                }
                                break label0;
                            }
                        }
                    }
                    catch (IOException ioexception)
                    {
                        ioexception.printStackTrace();
                        return;
                    }
                }
            }
_L2:
            return;
            synchronized (mHandler)
            {
                mHandler.removeMessages(7);
                mHandler.sendEmptyMessage(7);
            }
            return;
            exception;
            handler;
            JVM INSTR monitorexit ;
            throw exception;
            if (mSocketTask != null)
            {
                mSocketTask.cancel(true);
                mSocketTask = null;
            }
            mSocketTask = new ConnectSocketTask();
            mSocketTask.execute(new String[0]);
            return;
            if (mSocketClient == null || !mSocketClient.isConnected()) goto _L2; else goto _L1
_L1:
            mSocketClient.close();
            mSocketClient = null;
            return;
        }

            
            {
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final AEEVideoStreamActivity this$0;

        public void onClick(View view)
        {
            mIsTwicePressed = false;
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onClick v = ").append(view).toString());
            view.getId();
            JVM INSTR tableswitch 2131296563 2131296590: default 164
        //                       2131296563 165
        //                       2131296564 164
        //                       2131296565 772
        //                       2131296566 395
        //                       2131296567 1062
        //                       2131296568 1170
        //                       2131296569 1116
        //                       2131296570 1284
        //                       2131296571 164
        //                       2131296572 1509
        //                       2131296573 164
        //                       2131296574 164
        //                       2131296575 1593
        //                       2131296576 164
        //                       2131296577 164
        //                       2131296578 1677
        //                       2131296579 164
        //                       2131296580 164
        //                       2131296581 1761
        //                       2131296582 164
        //                       2131296583 164
        //                       2131296584 1846
        //                       2131296585 164
        //                       2131296586 164
        //                       2131296587 1965
        //                       2131296588 164
        //                       2131296589 164
        //                       2131296590 2050;
               goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L9 _L1 _L1 _L10 _L1 _L1 _L11 _L1 _L1 _L12 _L1 _L1 _L13 _L1 _L1 _L14 _L1 _L1 _L15
_L1:
            return;
_L2:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mOnClickListener video_view mCurConnectStatus = ").append(mCurConnectStatus).append(", isRecting = ").append(isRecting).toString());
            if (mCurConnectStatus == 2 || mCurConnectStatus == 1 && (mCurConnectParams == 20 || mCurConnectParams == 18))
            {
                boolean flag;
                AEEVideoStreamActivity aeevideostreamactivity;
                boolean flag1;
                if (mVideoView != null && mVideoView.getVisibility() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                aeevideostreamactivity = AEEVideoStreamActivity.this;
                if (flag)
                {
                    flag1 = true;
                } else
                if (mCurConnectParams == 18)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                aeevideostreamactivity.mIsPreviewClosed = flag1;
                if (mIsPreviewClosed)
                {
                    switchTo(1, 20);
                    return;
                }
                if (mIsPreviewNotSupport)
                {
                    switchTo(1, 18);
                    return;
                } else
                {
                    showSurfaceView(0);
                    switchTo(2, -1);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L4:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate isExecuting = ").append(isExecuting).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate mIsSetParams = ").append(mIsSetParams).toString());
            if (isExecuting || mCurConnectStatus == 0 || mCurConnectParams == 19 || mIsSetParams)
            {
                continue; /* Loop/switch isn't completed */
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (!mSocketClient.isConnected())
            {
                break MISSING_BLOCK_LABEL_643;
            }
            mSocketClient.setIsNeedFreshFiles(true);
_L16:
            isExecuting = true;
            IOException ioexception3;
            switch (mCurExecuteStatus)
            {
            case 7: // '\007'
            default:
                return;

            case 3: // '\003'
                if (isRecting)
                {
                    sendHandleCMD(0x10000004, -1, 0L);
                    return;
                } else
                {
                    sendHandleCMD(0x10000003, 0x10000021, 0L);
                    return;
                }

            case 4: // '\004'
                sendHandleCMD(0x10000006, -1, 0L);
                return;

            case 5: // '\005'
                sendHandleCMD(0x10000007, -1, 0L);
                return;

            case 6: // '\006'
                if (isRecting)
                {
                    sendHandleCMD(0x10000008, -1, 0L);
                    return;
                } else
                {
                    sendHandleCMD(0x10000014, -1, 0L);
                    return;
                }

            case 8: // '\b'
                break;
            }
            break MISSING_BLOCK_LABEL_738;
            try
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b015e, 0).show();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
              goto _L16
            if (isRecting)
            {
                sendHandleCMD(0x10000004, -1, 0L);
                return;
            } else
            {
                sendHandleCMD(0x10000005, -1, 0L);
                return;
            }
_L3:
            if (mPauseType == -1 && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                mPauseType = 0x10000066;
                Intent intent2;
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                }
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
                mSocketClient.setOnRequestRespondsListener(null);
                intent2 = new Intent();
                intent2.setClass(getApplicationContext(), com/arcsoft/videostream/aee/AEEVideoStreamFullScreenActivity);
                intent2.putExtra("is_cur_executing", isRecting);
                intent2.putExtra("is_stream_connected", bStreamConnectDone);
                intent2.putExtra("connect_failed_params", mCurConnectParams);
                intent2.putExtra("cur_exe_state", mCurExecuteStatus);
                intent2.putExtra("is_preview_closed", mIsPreviewClosed);
                intent2.putExtra("is_preview_not_support", mIsPreviewNotSupport);
                if (isRecting && mFileSize != null)
                {
                    intent2.putExtra("get_recting_time", mTimeCountor);
                    synchronized (mHandler)
                    {
                        mHandler.removeMessages(2);
                        mHandler.removeMessages(3);
                    }
                }
                startActivityForResult(intent2, 0x10000066);
                return;
            }
            continue; /* Loop/switch isn't completed */
            exception;
            handler;
            JVM INSTR monitorexit ;
            throw exception;
_L5:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                sendHandleCMD(0x1000000a, -1, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L7:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                sendHandleCMD(0x10000009, -1, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            if (mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (mCurConnectStatus == 2)
                {
                    mPowOffBtn.setImageResource(0x7f02013a);
                    sendHandleCMD(0x10000011, -1, 0L);
                    return;
                }
                if (mCurConnectStatus == 1 && mCurConnectParams == 16)
                {
                    mPowOffBtn.setImageResource(0x7f02013c);
                    sendHandleCMD(0x10000016, -1, 0L);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L8:
            if (isExecuting || mPauseType != -1 || mCurConnectStatus == 0 || mCurConnectParams == 19 || mIsSetParams)
            {
                continue; /* Loop/switch isn't completed */
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (!mSocketClient.isConnected()) goto _L18; else goto _L17
_L17:
            if (isRecting)
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            }
              goto _L19
            IOException ioexception1;
            ioexception1;
_L20:
            ioexception1.printStackTrace();
            return;
_L19:
            if (!mSocketClient.hasConfig())
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0163, 0).show();
                return;
            }
            Intent intent1;
            mPauseType = 0x10000065;
            mSocketClient.setOnRequestRespondsListener(null);
            mSocketClient.setCurEXEState(mCurExecuteStatus);
            intent1 = new Intent();
            try
            {
                intent1.setClass(getApplicationContext(), com/arcsoft/mediaplus/setting/AEESettingCMDListActivity);
                startActivityForResult(intent1, 0x10000065);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception1) { }
            if (true) goto _L20; else goto _L18
_L18:
            Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b015f, 0).show();
            return;
_L9:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    isExecuting = true;
                    switchDVMode(3);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L10:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    isExecuting = true;
                    switchDVMode(4);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L11:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    isExecuting = true;
                    switchDVMode(5);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L12:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    isExecuting = true;
                    switchDVMode(6);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L13:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                }
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    if (mSocketClient.isConnected())
                    {
                        isExecuting = true;
                        sendHandleCMD(0x10000033, -1, 0L);
                        return;
                    }
                }
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                    return;
                }
            }
            if (true) goto _L1; else goto _L14
_L14:
            if (!isExecuting && mCurConnectStatus != 0 && mCurConnectParams != 19 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    isExecuting = true;
                    switchDVMode(8);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
_L15:
            if (!isExecuting && mPauseType == -1 && !mIsSetParams)
            {
                if (isRecting)
                {
                    Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                    return;
                } else
                {
                    mPauseType = 0x10000067;
                    mSocketClient.setOnRequestRespondsListener(null);
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/MediaPlusActivity);
                    startActivityForResult(intent, 0x10000067);
                    return;
                }
            }
            if (true) goto _L1; else goto _L21
_L21:
        }

            
            {
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
    };
    private final AEESocketClient.OnRequestRespondsListener mOnRequestRespondsListener = new AEESocketClient.OnRequestRespondsListener() {

        final AEEVideoStreamActivity this$0;

        public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
        {
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onRequestRespondsFinished respond = ").append(s).append(" num = ").append(j).append(" param = ").append(s1).append(" curCmdType = ").append(i).append(" paramSize = ").append(k).toString());
            if (j != 1 || s1 == null) goto _L2; else goto _L1
_L1:
            if (!s1.contains("16777217")) goto _L4; else goto _L3
_L3:
            if (mCurExecuteStatus != 6) goto _L6; else goto _L5
_L5:
            setHandleUpdateSelectedExe(true, true);
_L64:
            return;
_L6:
            if (mCurExecuteStatus == 3)
            {
                stopTimer();
                setHandleUpdateSelectedExe(false, true);
                if (mCurConnectParams == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
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
            int l4;
            mSocketClient = AEESocketClient.getInstanceClient();
            l4 = mSocketClient.getNextCMD();
            l = l4;
_L51:
            i;
            JVM INSTR lookupswitch 45: default 584
        //                       -1: 104
        //                       268435457: 2106
        //                       268435458: 2288
        //                       268435459: 2817
        //                       268435460: 2984
        //                       268435461: 3176
        //                       268435462: 3237
        //                       268435463: 3237
        //                       268435464: 3794
        //                       268435465: 3274
        //                       268435466: 3274
        //                       268435467: 4341
        //                       268435468: 4442
        //                       268435469: 3533
        //                       268435470: 3609
        //                       268435473: 3856
        //                       268435474: 643
        //                       268435475: 2042
        //                       268435476: 3685
        //                       268435477: 4534
        //                       268435478: 3867
        //                       268435487: 942
        //                       268435488: 4873
        //                       268435489: 1193
        //                       268435490: 1109
        //                       268435491: 1605
        //                       268435492: 1012
        //                       268435493: 2364
        //                       268435494: 4801
        //                       268435495: 4641
        //                       268435496: 4719
        //                       268435498: 4760
        //                       268435499: 3886
        //                       268435500: 2551
        //                       268435501: 4899
        //                       268435502: 4939
        //                       268435503: 4979
        //                       268435504: 5019
        //                       268435505: 5081
        //                       268435506: 5144
        //                       268435507: 3299
        //                       268435509: 1872
        //                       268435510: 1852
        //                       268435511: 1892
        //                       268435512: 1810;
               goto _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L14 _L15 _L16 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44 _L45 _L46 _L47 _L48 _L49 _L50
_L8:
            continue; /* Loop/switch isn't completed */
_L7:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished default");
            if (j != 0)
            {
                Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
                sendCommandFailed(j, i, s1);
                return;
            }
            continue; /* Loop/switch isn't completed */
            IOException ioexception;
            ioexception;
            ioexception.printStackTrace();
              goto _L51
_L22:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onRequestRespondsFinished PARAM_AEE_CMD_START_ENCODING nextCmd = ").append(l).toString());
            if (mCurDVMode != 8 && (!isRecting || mCurConnectParams != 18))
            {
                if (mIsPreviewClosed)
                {
                    switchTo(1, 20);
                } else
                {
                    AEEVideoStreamActivity aeevideostreamactivity16 = AEEVideoStreamActivity.this;
                    int k4;
                    if (mCurConnectParams == 19)
                    {
                        k4 = mCurConnectParams;
                    } else
                    {
                        k4 = -1;
                    }
                    aeevideostreamactivity16.switchTo(2, k4);
                    if (mArcMediaPlayer != null)
                    {
                        mArcMediaPlayer.reset();
                        mSocketClient.setIsFirstStartPreview(true);
                        bPlayerStarted = false;
                        isPlaying = false;
                    }
                    if (!isPaused)
                    {
                        AEEVideoStreamActivity aeevideostreamactivity17 = AEEVideoStreamActivity.this;
                        char c2;
                        if (mIsFirstStreaming)
                        {
                            c2 = '\u05DC';
                        } else
                        {
                            c2 = '\0';
                        }
                        aeevideostreamactivity17.showSurfaceView(c2);
                    } else
                    {
                        setStreamConnectDone(true);
                    }
                    mIsFirstStreaming = false;
                }
            }
            isExecuting = false;
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
_L27:
            if (j == -12)
            {
                mSocketClient.setHasConfig(false);
            } else
            {
                mSocketClient.setHasConfig(true);
            }
            if (l == 0x10000027 || -1 == l)
            {
                sendHandleMSG(11, 0x10000027, -1, null);
                return;
            } else
            {
                sendHandleCMD(l, -1, 0L);
                return;
            }
_L32:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_VIDEO_STAMP");
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
            AEEVideoStreamActivity aeevideostreamactivity15 = AEEVideoStreamActivity.this;
            int j4;
            if (!mSocketClient.getIsNeedEncoding())
            {
                j4 = 0x10000013;
            } else
            {
                j4 = -1;
            }
            aeevideostreamactivity15.sendHandleCMD(j4, 0x10000038, 0L);
            return;
_L30:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAM_TYPE");
            if (s1 == null || s1.contains("rtsp"))
            {
                sendHandleCMD(0x10000023, -1, 0L);
                return;
            }
            mIsFirstStreaming = true;
            AEEVideoStreamActivity aeevideostreamactivity14 = AEEVideoStreamActivity.this;
            int i4;
            if (!mSocketClient.getIsNeedEncoding())
            {
                i4 = 0x10000013;
            } else
            {
                i4 = -1;
            }
            aeevideostreamactivity14.sendHandleCMD(i4, 0x10000036, 0L);
            return;
_L29:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM mSocketClient.getIsFirstStartPreview() = ").append(mSocketClient.getIsFirstStartPreview()).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM isRecting = ").append(isRecting).toString());
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
                        sendHandleCMD(0x10000024, -1, 0L);
                        return;
                    }
                    AEEVideoStreamActivity aeevideostreamactivity10 = AEEVideoStreamActivity.this;
                    AEEVideoStreamActivity aeevideostreamactivity9;
                    int j3;
                    if (mCurConnectParams == 19)
                    {
                        j3 = mCurConnectParams;
                    } else
                    {
                        j3 = -1;
                    }
                    aeevideostreamactivity10.switchTo(2, j3);
                    if (!isPaused)
                    {
                        showSurfaceView(0);
                        mIsFirstStreaming = false;
                    } else
                    {
                        setStreamConnectDone(true);
                    }
                } else
                {
                    switchTo(1, 20);
                }
                if (l != -1)
                {
                    aeevideostreamactivity9 = AEEVideoStreamActivity.this;
                    int i3;
                    if (l == 0x1000001f)
                    {
                        i3 = 0x10000027;
                    } else
                    {
                        i3 = -1;
                    }
                    aeevideostreamactivity9.sendHandleCMD(l, i3, 0L);
                    return;
                }
            } else
            if (isRecting)
            {
                mIsPreviewNotSupport = true;
                AEEVideoStreamActivity aeevideostreamactivity12 = AEEVideoStreamActivity.this;
                byte byte2;
                if (mIsPreviewClosed)
                {
                    byte2 = 20;
                } else
                {
                    byte2 = 18;
                }
                aeevideostreamactivity12.switchTo(1, byte2);
                if (l != -1)
                {
                    AEEVideoStreamActivity aeevideostreamactivity13 = AEEVideoStreamActivity.this;
                    int l3;
                    if (l == 0x1000001f)
                    {
                        l3 = 0x10000027;
                    } else
                    {
                        l3 = -1;
                    }
                    aeevideostreamactivity13.sendHandleCMD(l, l3, 0L);
                    return;
                }
            } else
            {
                AEEVideoStreamActivity aeevideostreamactivity11 = AEEVideoStreamActivity.this;
                int k3;
                if (!mSocketClient.getIsNeedEncoding())
                {
                    k3 = 0x10000013;
                } else
                {
                    k3 = -1;
                }
                aeevideostreamactivity11.sendHandleCMD(k3, 0x10000035, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L31:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_GET_DV_SETTINGS_STREAMING");
            if (s1 == null || s1.contains("on"))
            {
                if (mSocketClient.getIsNeedEncoding())
                {
                    sendHandleCMD(0x10000012, 0x1000001f, 0L);
                    return;
                }
                AEEVideoStreamActivity aeevideostreamactivity6 = AEEVideoStreamActivity.this;
                int j2;
                if (mCurConnectParams == 19)
                {
                    j2 = mCurConnectParams;
                } else
                {
                    j2 = -1;
                }
                aeevideostreamactivity6.switchTo(2, j2);
                sendHandleCMD(0x1000001f, 0x10000027, 0L);
                if (!isPaused)
                {
                    AEEVideoStreamActivity aeevideostreamactivity7 = AEEVideoStreamActivity.this;
                    char c1;
                    if (mIsFirstStreaming)
                    {
                        c1 = '\u05DC';
                    } else
                    {
                        c1 = '\0';
                    }
                    aeevideostreamactivity7.showSurfaceView(c1);
                    mIsFirstStreaming = false;
                    return;
                } else
                {
                    setStreamConnectDone(true);
                    return;
                }
            }
            AEEVideoStreamActivity aeevideostreamactivity8 = AEEVideoStreamActivity.this;
            int k2;
            if (!mSocketClient.getIsNeedEncoding())
            {
                k2 = 0x10000013;
            } else
            {
                k2 = -1;
            }
            aeevideostreamactivity8.sendHandleCMD(k2, 0x10000037, 0L);
            return;
_L50:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_VIDEO_STAMP");
            if (isRecting)
            {
                sendHandleCMD(0x10000022, -1, 0L);
                return;
            } else
            {
                sendHandleCMD(0x10000021, -1, 0L);
                return;
            }
_L48:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAM_TYPE");
            sendHandleCMD(0x10000023, -1, 0L);
            return;
_L47:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_DUAL_STREAM");
            sendHandleCMD(0x10000022, -1, 0L);
            return;
_L49:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_SET_DV_SETTINGS_STREAMING");
            if (mSocketClient.getIsNeedEncoding())
            {
                sendHandleCMD(0x10000012, 0x1000001f, 0L);
                return;
            }
            AEEVideoStreamActivity aeevideostreamactivity4 = AEEVideoStreamActivity.this;
            int i2;
            if (mCurConnectParams == 19)
            {
                i2 = mCurConnectParams;
            } else
            {
                i2 = -1;
            }
            aeevideostreamactivity4.switchTo(2, i2);
            sendHandleCMD(0x1000001f, 0x10000027, 0L);
            if (!isPaused)
            {
                AEEVideoStreamActivity aeevideostreamactivity5 = AEEVideoStreamActivity.this;
                char c;
                if (mIsFirstStreaming)
                {
                    c = '\u05DC';
                } else
                {
                    c = '\0';
                }
                aeevideostreamactivity5.showSurfaceView(c);
                mIsFirstStreaming = false;
                return;
            } else
            {
                setStreamConnectDone(true);
                return;
            }
_L23:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_STOP_ENCODING");
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
                return;
            }
            if (l == 0x1000000d || l == 0x1000000e)
            {
                sendHandleCMD(l, -1, 500L);
                return;
            } else
            {
                sendHandleCMD(l, -1, 0L);
                return;
            }
_L9:
            if (j != 0)
            {
                if (mSocketClient.getCurShareId(AEEVideoStreamActivity.this) > 0)
                {
                    AEEVideoStreamActivity aeevideostreamactivity3 = AEEVideoStreamActivity.this;
                    int k1;
                    if (l == 0x1000002b)
                    {
                        k1 = 0x10000033;
                    } else
                    {
                        k1 = -1;
                    }
                    aeevideostreamactivity3.sendHandleCMD(l, k1, 0L);
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
                    int j1 = Integer.parseInt(s1);
                    AEEUtilDef.setSharedTokenId(AEEVideoStreamActivity.this, j1);
                }
                catch (Exception exception1)
                {
                    exception1.printStackTrace();
                }
            }
            if (l != -1)
            {
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
            }
            continue; /* Loop/switch isn't completed */
_L10:
            Log.e("AEEVideoStreamActivity", "PARAM_AEE_CMD_SESSION_STOP");
            try
            {
                mSocketClient.stopRespondParams();
                mSocketClient.setOnRequestRespondsListener(null);
                mSocketClient.releaseCurTokenId();
                mSocketClient.close();
                mSocketClient.realseClient();
                mSocketClient = null;
                return;
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            return;
_L33:
            if (s1 != null && s1.contains("record"))
            {
                if (mCurDVMode != 3)
                {
                    mCurDVMode = 3;
                    sendHandleMSG(10, 3, -1, null);
                }
                mSocketClient.setIsNeedEncoding(false);
                sendHandleCMD(0x1000002c, 0x10000021, 0L);
                return;
            }
            if (s1 != null && s1.contains("idle"))
            {
                mSocketClient.setIsNeedEncoding(true);
                AEEVideoStreamActivity aeevideostreamactivity2 = AEEVideoStreamActivity.this;
                long l2;
                if (mIsFirstStreaming)
                {
                    l2 = 1500L;
                } else
                {
                    l2 = 0L;
                }
                aeevideostreamactivity2.sendHandleCMD(0x10000024, -1, l2);
                return;
            }
            mSocketClient.setIsNeedEncoding(false);
            AEEVideoStreamActivity aeevideostreamactivity1 = AEEVideoStreamActivity.this;
            long l1;
            if (mIsFirstStreaming)
            {
                l1 = 1500L;
            } else
            {
                l1 = 0L;
            }
            aeevideostreamactivity1.sendHandleCMD(0x10000024, -1, l1);
            return;
_L39:
            int i1;
            String as[];
            if (isRecting)
            {
                i1 = -1;
            } else
            {
                i1 = 0x1000001f;
            }
            if (s1 == null || j != 0) goto _L53; else goto _L52
_L52:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME param = ").append(s1).toString());
            as = s1.split(":");
            if (as.length != 1) goto _L55; else goto _L54
_L54:
            mTimeCountor = Integer.parseInt(as[0]);
_L58:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_RECORD_TIME mTimeCountor = ").append(mTimeCountor).toString());
_L53:
            if (!isRecting)
            {
                startTimer();
                sendHandleMSGWithTime(16, 1, -1, null, 0L);
            }
            isRecting = true;
            sendHandleCMD(l, i1, 0L);
            return;
_L55:
            if (as.length != 2) goto _L57; else goto _L56
_L56:
            mTimeCountor = 60 * Integer.parseInt(as[0]) + Integer.parseInt(as[1]);
              goto _L58
            Exception exception;
            exception;
            exception.printStackTrace();
              goto _L53
_L57:
            if (as.length != 3) goto _L58; else goto _L59
_L59:
            mTimeCountor = 3600 * Integer.parseInt(as[0]) + 60 * Integer.parseInt(as[1]) + Integer.parseInt(as[2]);
              goto _L58
_L11:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_START");
            isExecuting = false;
            switch (j)
            {
            default:
                setHandleUpdateSelectedExe(false, true);
                return;

            case 0: // '\0'
                startTimer();
                setHandleUpdateSelectedExe(true, true);
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
                    setHandleUpdateSelectedExe(false, true);
                }
                sendCommandFailed(j, i, s1);
                return;

            case -4: 
                sendHandleCMD(0x10000001, 0x10000003, 0L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L12:
            Log.e("AEEVideoStreamActivity", "onRequestRespondsFinished PARAM_AEE_CMD_RECORD_STOP");
            mIsPreviewNotSupport = false;
            if (j == 0 || isPaused)
            {
                stopTimer();
                setHandleUpdateSelectedExe(false, true);
                if (mCurExecuteStatus == 8)
                {
                    switchTo(8, 0);
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
                switchTo(8, 1);
                setHandleUpdateSelectedExe(true, true);
                return;
            } else
            {
                isRecting = false;
                sendCommandFailed(j, i, s1);
                return;
            }
_L14:
            setHandleUpdateSelectedExe(false, true);
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
            }
            sendHandleCMD(0x10000026, -1, 0L);
            return;
_L16:
            isExecuting = false;
            if (j != 0)
            {
                sendCommandFailed(j, i, s1);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L46:
            if (s1 != null && s1.contains("video_flip_rotate_on_"))
            {
                if (isExecuting)
                {
                    sendHandleCMD(0x10000013, 0x1000000e, 0L);
                } else
                {
                    if (!mSocketClient.getIsRolloverOn())
                    {
                        mSocketClient.setIsRolloverOn(true);
                    }
                    sendHandleMSG(14, 1, 7, null);
                }
            } else
            if (s1 != null && s1.contains("video_flip_rotate_off"))
            {
                if (isExecuting)
                {
                    sendHandleCMD(0x10000013, 0x1000000d, 0L);
                } else
                {
                    if (mSocketClient.getIsRolloverOn())
                    {
                        mSocketClient.setIsRolloverOn(false);
                    }
                    sendHandleMSG(14, 0, 7, null);
                }
            } else
            {
                sendCommandFailed(j, i, s1);
                isExecuting = false;
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
_L19:
            if (j == 0)
            {
                mSocketClient.setIsRolloverOn(true);
                sendHandleMSG(14, 1, 7, null);
            } else
            {
                sendCommandFailed(j, i, s1);
            }
            if (mSocketClient.getIsNeedEncoding())
            {
                sendHandleCMD(0x10000012, -1, 0L);
            }
            isExecuting = false;
            return;
_L20:
            if (j == 0)
            {
                mSocketClient.setIsRolloverOn(false);
                sendHandleMSG(14, 0, 7, null);
            } else
            {
                sendCommandFailed(j, i, s1);
            }
            if (mSocketClient.getIsNeedEncoding())
            {
                sendHandleCMD(0x10000012, -1, 0L);
            }
            isExecuting = false;
            return;
_L24:
            isExecuting = false;
            switch (j)
            {
            default:
                isRecting = false;
                sendCommandFailed(j, i, s1);
                return;

            case 0: // '\0'
                setHandleUpdateSelectedExe(true, true);
                return;

            case 1: // '\001'
                break;
            }
            if (s1 != null && s1.contains("16777220"))
            {
                isRecting = false;
                sendCommandFailed(j, i, s1);
                return;
            } else
            {
                setHandleUpdateSelectedExe(true, true);
                return;
            }
_L15:
            if (j == 0)
            {
                setHandleUpdateSelectedExe(false, true);
                updateSurfaceView(0);
            } else
            if (j != 1)
            {
                isRecting = true;
                sendCommandFailed(j, i, s1);
            }
            sendHandleCMD(0x10000026, -1, 0L);
            return;
_L21:
            switchTo(1, 16);
            return;
_L26:
            showSurfaceView(0);
            switchTo(2, 17);
            return;
_L38:
            byte byte0 = -1;
            j;
            JVM INSTR lookupswitch 2: default 3916
        //                       -4: 4157
        //                       0: 4100;
               goto _L60 _L61 _L62
_L60:
            if (mCurDVMode == -1)
            {
                mCurDVMode = byte0;
                AEEVideoStreamActivity aeevideostreamactivity = AEEVideoStreamActivity.this;
                byte byte1;
                if (byte0 == -1)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = byte0;
                }
                aeevideostreamactivity.sendHandleMSG(10, byte1, -1, null);
            }
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_MODE curState = ").append(byte0).append(" mCurDVMode = ").append(mCurDVMode).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_MODE nextCMD = ").append(l).toString());
            switch (l)
            {
            default:
                return;

            case 268435467: 
                if (mCurDVMode != 3)
                {
                    sendHandleCMD(l, -1, 0L);
                    return;
                } else
                {
                    mCurDVMode = 3;
                    sendHandleMSG(10, 3, -1, null);
                    return;
                }

            case 268435468: 
                if (byte0 != 8 && mCurDVMode != 8)
                {
                    sendHandleCMD(0x10000013, l, 0L);
                    return;
                } else
                {
                    mCurDVMode = 8;
                    sendHandleMSG(10, 8, -1, null);
                    return;
                }

            case 268435477: 
                if (byte0 != 4 && mCurDVMode != 4)
                {
                    sendHandleCMD(l, -1, 0L);
                    return;
                } else
                {
                    mCurDVMode = 4;
                    sendHandleMSG(10, mSwitchDVMode, -1, null);
                    return;
                }

            case 268435507: 
                sendHandleCMD(l, 0x10000025, 0L);
                return;
            }
_L62:
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
              goto _L60
_L61:
            mSocketClient.releaseCurShareId(AEEVideoStreamActivity.this);
            sendHandleCMD(0x10000001, 0x1000002b, 0L);
            return;
_L17:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_DV_MODE mSwitchDVMode = ").append(mSwitchDVMode).toString());
            if (j == 0)
            {
                mCurDVMode = 3;
                sendHandleMSG(10, 3, -1, null);
                return;
            }
            if (mCurDVMode == 3)
            {
                sendHandleCMD(0x10000013, -1, 0L);
            }
            sendCommandFailed(j, i, s1);
            mSwitchDVMode = -1;
            return;
_L18:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_VOICE_MODE mSwitchDVMode = ").append(mSwitchDVMode).toString());
            if (j == 0)
            {
                mCurDVMode = 8;
                sendHandleMSG(10, 8, -1, null);
                return;
            } else
            {
                sendHandleCMD(0x10000012, -1, 0L);
                sendCommandFailed(j, i, s1);
                mSwitchDVMode = -1;
                return;
            }
_L25:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_PHOTO_MODE mSwitchDVMode = ").append(mSwitchDVMode).toString());
            if (j == 0)
            {
                mCurDVMode = 4;
                sendHandleMSG(10, mSwitchDVMode, -1, null);
                return;
            }
            if (mCurDVMode == 3)
            {
                sendHandleCMD(0x10000013, -1, 0L);
            }
            sendCommandFailed(j, i, s1);
            mSwitchDVMode = -1;
            return;
_L35:
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("PARAM_AEE_CMD_GET_DV_SETTINGS_DV_INFO param = ").append(s1).append(" nextCmd = ").append(l).toString());
            if (j == 0)
            {
                sendHandleMSG(12, 0x10000027, -1, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L36:
            if (j == 0)
            {
                sendHandleMSG(12, 0x10000028, -1, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L37:
            if (j == 0)
            {
                sendHandleMSG(12, 0x1000002a, -1, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L34:
            if (j == 0)
            {
                sendHandleMSG(12, 0x10000026, -1, s1);
            }
            if (l == 0x1000002d)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
            } else
            if (l > 0)
            {
                sendHandleCMD(l, -1, 0L);
            }
            isExecuting = false;
            return;
_L28:
            if (j != 0);
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L40:
            if (j == 0)
            {
                mSocketClient.setDVInfo(12, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L41:
            if (j == 0)
            {
                mSocketClient.setDVInfo(14, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L42:
            if (j == 0)
            {
                mSocketClient.setDVInfo(15, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L43:
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
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            continue; /* Loop/switch isn't completed */
_L44:
            if (j == 0)
            {
                mSocketClient.setDVInfo(17, s1);
            }
            if (mCurConnectParams == 19)
            {
                mCurConnectParams = -1;
                sendHandleMSG(17, 0x7f0b015d, -1, null);
            }
            mIsSetParams = false;
            return;
_L45:
            if (j == 0)
            {
                mSocketClient.setDVInfo(13, s1);
            }
            if (l >= 0)
            {
                sendHandleMSG(11, l, -1, Integer.valueOf(0));
                return;
            }
            if (true) goto _L64; else goto _L63
_L63:
        }

            
            {
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
    };
    private int mPauseType;
    private ImageView mPowOffBtn;
    private BroadcastReceiver mRssiReceiver;
    private ImageView mSettingBtn;
    AEESocketClient mSocketClient;
    private ConnectSocketTask mSocketTask;
    private RelativeLayout mStateBtnsBar;
    private ImageView mStateContShotBtn;
    private ImageView mStateFastShotBtn;
    private ImageView mStatePhotoBtn;
    private ImageView mStatePlayBackBtn;
    private RelativeLayout mStatePlayBackBtnLayout;
    private ImageView mStateRolloverBtn;
    private RelativeLayout mStateRolloverBtnLayout;
    private ImageView mStateVideoBtn;
    private ImageView mStateVoiceBtn;
    private ImageView mStatusIcon;
    private TextView mStatusText;
    private SurfaceHolder mSurfaceHolder;
    private int mSwitchDVMode;
    private int mTimeCountor;
    private SurfaceView mVideoView;
    private View mViewGroup;
    int mWidth;
    private HashMap mWifiDrawable;

    public AEEVideoStreamActivity()
    {
        mArcMediaPlayer = null;
        mSurfaceHolder = null;
        mViewGroup = null;
        mCamNumText = null;
        mCamWifi = null;
        mCamBattery = null;
        mCamSapceText = null;
        mFileSize = null;
        mCamModelText = null;
        mCamInfoText = null;
        mTimeCountor = 0;
        mCurSpendTime = 0;
        mCamManfacturer = null;
        mCamModel = null;
        mCamVersion = null;
        mCamInfo = null;
        mFullScreenBtn = null;
        mPowOffBtn = null;
        mExecutBtn = null;
        mSettingBtn = null;
        mCamFocusT = null;
        mCamFocusW = null;
        mStateVideoBtn = null;
        mStatePhotoBtn = null;
        mStateFastShotBtn = null;
        mStateContShotBtn = null;
        mStateRolloverBtn = null;
        mStateVoiceBtn = null;
        mStatePlayBackBtn = null;
        mBlockView = null;
        mStateBtnsBar = null;
        mStateRolloverBtnLayout = null;
        mStatePlayBackBtnLayout = null;
        mExecuteBtnDrawable = null;
        mExecuteBtnSelectedDrawable = null;
        mBatteryDrawable = null;
        mWifiDrawable = null;
        mStatusIcon = null;
        mStatusText = null;
        mNetworkTool = null;
        mSocketClient = null;
        mCurConnectStatus = -1;
        mCurConnectParams = -1;
        mCurExecuteStatus = -1;
        isPlaying = false;
        mPauseType = -1;
        isRecting = false;
        isExecuting = false;
        isWifiConnected = false;
        mSocketTask = null;
        bStreamConnectDone = false;
        bPlayerStarted = false;
        mIsTwicePressed = false;
        mIsFirstStreaming = false;
        mIsSetParams = false;
        mIsPreviewClosed = false;
        mIsPreviewNotSupport = false;
        mSwitchDVMode = -1;
        mCurDVMode = -1;
        mRssiReceiver = null;
        mWidth = 0;
        mHeight = 0;
        isPaused = false;
    }

    private void cancelSelectAllStateBtn()
    {
        if (mStateVideoBtn != null)
        {
            mStateVideoBtn.setSelected(false);
        }
        if (mStatePhotoBtn != null)
        {
            mStatePhotoBtn.setSelected(false);
        }
        if (mStateFastShotBtn != null)
        {
            mStateFastShotBtn.setSelected(false);
        }
        if (mStateContShotBtn != null)
        {
            mStateContShotBtn.setSelected(false);
        }
        if (mStateVoiceBtn != null)
        {
            mStateVoiceBtn.setSelected(false);
        }
        if (mStateRolloverBtn != null)
        {
            mStateRolloverBtn.setSelected(false);
        }
        if (mStatePlayBackBtn != null)
        {
            mStatePlayBackBtn.setSelected(false);
        }
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

    private void init()
    {
        mViewGroup = LayoutInflater.from(this).inflate(0x7f03003b, null);
        setContentView(mViewGroup);
        mCamNumText = (TextView)mViewGroup.findViewById(0x7f09012a);
        mCamWifi = (ImageView)mViewGroup.findViewById(0x7f090125);
        mCamBattery = (ImageView)mViewGroup.findViewById(0x7f090126);
        mCamSapceText = (TextView)mViewGroup.findViewById(0x7f090127);
        mFileSize = (TextView)mViewGroup.findViewById(0x7f090129);
        mCamModelText = (TextView)mViewGroup.findViewById(0x7f090134);
        mCamInfoText = (TextView)mViewGroup.findViewById(0x7f09013b);
        initDVInfo();
        mFullScreenBtn = (ImageView)mViewGroup.findViewById(0x7f090135);
        mFullScreenBtn.setOnClickListener(mOnClickListener);
        mPowOffBtn = (ImageView)mViewGroup.findViewById(0x7f090138);
        mPowOffBtn.setOnClickListener(mOnClickListener);
        mPowOffBtn.setVisibility(8);
        mExecutBtn = (ImageView)mViewGroup.findViewById(0x7f090136);
        mExecutBtn.setOnClickListener(mOnClickListener);
        mSettingBtn = (ImageView)mViewGroup.findViewById(0x7f09013a);
        mSettingBtn.setOnClickListener(mOnClickListener);
        mCamFocusT = (ImageView)mViewGroup.findViewById(0x7f090137);
        mCamFocusT.setOnClickListener(mOnClickListener);
        mCamFocusW = (ImageView)mViewGroup.findViewById(0x7f090139);
        mCamFocusW.setOnClickListener(mOnClickListener);
        mStateVideoBtn = (ImageView)mViewGroup.findViewById(0x7f09013c);
        mStateVideoBtn.setOnClickListener(mOnClickListener);
        mStatePhotoBtn = (ImageView)mViewGroup.findViewById(0x7f09013f);
        mStatePhotoBtn.setOnClickListener(mOnClickListener);
        mStateFastShotBtn = (ImageView)mViewGroup.findViewById(0x7f090142);
        mStateFastShotBtn.setOnClickListener(mOnClickListener);
        mStateContShotBtn = (ImageView)mViewGroup.findViewById(0x7f090145);
        mStateContShotBtn.setEnabled(false);
        mStateRolloverBtn = (ImageView)mViewGroup.findViewById(0x7f090148);
        mStateRolloverBtn.setOnClickListener(mOnClickListener);
        mStateVoiceBtn = (ImageView)mViewGroup.findViewById(0x7f09014b);
        mStateVoiceBtn.setOnClickListener(mOnClickListener);
        mStatePlayBackBtn = (ImageView)mViewGroup.findViewById(0x7f09014e);
        mStatePlayBackBtn.setOnClickListener(mOnClickListener);
        mStateRolloverBtnLayout = (RelativeLayout)mViewGroup.findViewById(0x7f090147);
        mStatePlayBackBtnLayout = (RelativeLayout)mViewGroup.findViewById(0x7f09014d);
        android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)mStatePlayBackBtnLayout.getLayoutParams();
        layoutparams.addRule(5, 0x7f090128);
        layoutparams.addRule(3, 0x7f090128);
        mStatePlayBackBtnLayout.setLayoutParams(layoutparams);
        mStatePlayBackBtnLayout.setVisibility(0);
        mStateRolloverBtnLayout.setVisibility(8);
        mStateBtnsBar = (RelativeLayout)mViewGroup.findViewById(0x7f09011d);
        mVideoView = (SurfaceView)mViewGroup.findViewById(0x7f09011f);
        mVideoView.setVisibility(8);
        mBlockView = mViewGroup.findViewById(0x7f090133);
        mBlockView.setOnClickListener(mOnClickListener);
        mStatusText = (TextView)mViewGroup.findViewById(0x7f090121);
        mStatusText.setVisibility(8);
        mStatusIcon = (ImageView)mViewGroup.findViewById(0x7f090120);
        mStatusIcon.setVisibility(8);
        initHolder();
    }

    private void initDVInfo()
    {
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mCamNumText != null)
            {
                int k = Integer.parseInt(mSocketClient.getDVInfo(0));
                mCamNumText.setText(String.valueOf(k));
            }
            if (mCamWifi != null && mWifiDrawable != null)
            {
                int j = Integer.parseInt(mSocketClient.getDVInfo(1));
                mCamWifi.setBackgroundDrawable((Drawable)mWifiDrawable.get(Integer.valueOf(j)));
            }
            if (mCamBattery != null)
            {
                int i = Integer.parseInt(mSocketClient.getDVInfo(2));
                mCamBattery.setBackgroundDrawable((Drawable)mBatteryDrawable.get(Integer.valueOf(i / 25)));
            }
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("space = ").append(mSocketClient.getDVInfo(3)).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mCamSapceText = ").append(mCamSapceText).toString());
            if (mCamSapceText != null)
            {
                mCamSapceText.setText(mSocketClient.getDVInfo(3));
            }
            String s = mSocketClient.getDVInfo(4);
            String s1 = mSocketClient.getDVInfo(11);
            String s2 = mSocketClient.getDVInfo(5);
            String s3 = (new StringBuilder()).append(s).append(" ").append(s1).append(" ").append(s2).toString();
            if (mCamModelText != null)
            {
                mCamModelText.setText(s1);
            }
            if (mCamInfoText != null)
            {
                mCamInfoText.setText(s3);
            }
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void initDefault()
    {
        releaseDefault();
        if (mExecuteBtnDrawable == null)
        {
            mExecuteBtnDrawable = new HashMap();
            mExecuteBtnDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020017));
            mExecuteBtnDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f020014));
            mExecuteBtnDrawable.put(Integer.valueOf(5), getResources().getDrawable(0x7f02000d));
            mExecuteBtnDrawable.put(Integer.valueOf(6), getResources().getDrawable(0x7f02000b));
            mExecuteBtnDrawable.put(Integer.valueOf(8), getResources().getDrawable(0x7f020012));
        }
        if (mExecuteBtnSelectedDrawable == null)
        {
            mExecuteBtnSelectedDrawable = new HashMap();
            mExecuteBtnSelectedDrawable.put(Integer.valueOf(3), getResources().getDrawable(0x7f020018));
            mExecuteBtnSelectedDrawable.put(Integer.valueOf(4), getResources().getDrawable(0x7f020015));
            mExecuteBtnSelectedDrawable.put(Integer.valueOf(5), getResources().getDrawable(0x7f02000e));
            mExecuteBtnSelectedDrawable.put(Integer.valueOf(6), getResources().getDrawable(0x7f02000c));
            mExecuteBtnSelectedDrawable.put(Integer.valueOf(8), getResources().getDrawable(0x7f020013));
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
        try
        {
            AEESocketClient.getInstanceClient().initDVInfo();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private void initHolder()
    {
        mVideoView.getHolder().addCallback(this);
    }

    private void initORequestRespondsListener()
    {
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            mSocketClient.setOnRequestRespondsListener(mOnRequestRespondsListener);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    static void loadLibrarys()
    {
        int i;
        i = SystemUtils.getSDKVersion();
        Log.e("FENG", (new StringBuilder()).append("FENG  loadLibrarys  ----- lSDKVersion: ").append(i).toString());
        System.loadLibrary("mv3_platform");
        System.loadLibrary("mv3_common");
        System.loadLibrary("mv3_asmespliter");
        System.loadLibrary("mv3_h264dec");
        System.loadLibrary("mv3_mpplat");
        System.loadLibrary("mv3_playerbase");
        if (i < 14)
        {
            try
            {
                System.loadLibrary("mv3_jni_2_3");
                return;
            }
            catch (UnsatisfiedLinkError unsatisfiedlinkerror)
            {
                unsatisfiedlinkerror.printStackTrace();
            }
            break MISSING_BLOCK_LABEL_98;
        }
        if (i >= 19)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        System.loadLibrary("mv3_jni");
        return;
        return;
        System.loadLibrary("mv3_jni_4_4");
        return;
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

    private void registerWIFIReceiver()
    {
        if (mRssiReceiver != null)
        {
            return;
        } else
        {
            mRssiReceiver = new BroadcastReceiver() {

                final AEEVideoStreamActivity this$0;

                public void onReceive(Context context, Intent intent)
                {
                    Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("intent = ").append(intent.getAction()).toString());
                    sendHandleMSGWithTime(12, 0x10000029, -1, String.valueOf(obtainWifiStrength()), 0L);
                }

            
            {
                this$0 = AEEVideoStreamActivity.this;
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
        if (mExecuteBtnSelectedDrawable != null)
        {
            mExecuteBtnSelectedDrawable.clear();
            mExecuteBtnSelectedDrawable = null;
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
        if (mCamNumText != null)
        {
            mCamNumText = null;
        }
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
        if (mCamModelText != null)
        {
            mCamModelText = null;
        }
        if (mCamInfoText != null)
        {
            mCamInfoText = null;
        }
        if (mFullScreenBtn != null)
        {
            mFullScreenBtn = null;
        }
        if (mPowOffBtn != null)
        {
            mPowOffBtn = null;
        }
        if (mExecutBtn != null)
        {
            mExecutBtn = null;
        }
        if (mSettingBtn != null)
        {
            mSettingBtn = null;
        }
        if (mCamFocusT != null)
        {
            mCamFocusT = null;
        }
        if (mCamFocusW != null)
        {
            mCamFocusW = null;
        }
        if (mStateVideoBtn != null)
        {
            mStateVideoBtn = null;
        }
        if (mStatePhotoBtn != null)
        {
            mStatePhotoBtn = null;
        }
        if (mStateFastShotBtn != null)
        {
            mStateFastShotBtn = null;
        }
        if (mStateContShotBtn != null)
        {
            mStateContShotBtn = null;
        }
        if (mStateRolloverBtn != null)
        {
            mStateRolloverBtn = null;
        }
        if (mStateVoiceBtn != null)
        {
            mStateVoiceBtn = null;
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
        if (i != -1 || j != -1)
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
        } else
        {
            return;
        }
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void sendHandleMSG(int i, int j, int k, Object obj)
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
        mHandler.sendMessage(message);
        handler;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
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
        mHandler.removeMessages(i);
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

    private void setHandleUpdateSelectedExe(boolean flag, boolean flag1)
    {
        int i;
        i = 1;
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("setHandleUpdateSelectedExe isSelected = ").append(flag).append(" needUpdateIndo = ").append(flag1).toString());
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
            j = i;
        } else
        {
            j = 0;
        }
        message.arg1 = j;
        if (!flag1)
        {
            i = 0;
        }
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

    private void setStateBtnEnable(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
    {
        if (mFullScreenBtn != null && mFullScreenBtn.isEnabled() != flag1)
        {
            mFullScreenBtn.setEnabled(flag1);
        }
        if (mPowOffBtn != null && mPowOffBtn.isEnabled() != flag2)
        {
            mPowOffBtn.setEnabled(flag2);
        }
        boolean flag5;
        boolean flag6;
        if (mExecutBtn != null && mExecutBtn.isEnabled() != flag)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        if (flag5)
        {
            mExecutBtn.setEnabled(flag);
            if (mSettingBtn != null)
            {
                mSettingBtn.setEnabled(flag);
            }
            if (mCamFocusT != null)
            {
                mCamFocusT.setEnabled(flag);
            }
            if (mCamFocusW != null)
            {
                mCamFocusW.setEnabled(flag);
            }
        }
        if (mStateVideoBtn != null && mStateVideoBtn.isEnabled() != flag3)
        {
            flag6 = true;
        } else
        {
            flag6 = false;
        }
        if (flag6)
        {
            mStateVideoBtn.setEnabled(flag3);
            if (mStatePhotoBtn != null)
            {
                mStatePhotoBtn.setEnabled(flag3);
            }
            if (mStateFastShotBtn != null)
            {
                mStateFastShotBtn.setEnabled(flag3);
            }
            if (mStateRolloverBtn != null)
            {
                mStateRolloverBtn.setEnabled(flag3);
            }
            if (mStateVoiceBtn != null)
            {
                mStateVoiceBtn.setEnabled(flag3);
            }
        }
        if (mStatePlayBackBtn != null && mStatePlayBackBtn.isEnabled() != flag4)
        {
            mStatePlayBackBtn.setEnabled(flag4);
        }
    }

    private void setStateBtnVisible(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        if (mStateBtnsBar != null && mStateBtnsBar.getVisibility() != i)
        {
            mStateBtnsBar.setVisibility(i);
        }
    }

    private void setStreamConnectDone(boolean flag)
    {
        if (bStreamConnectDone != flag)
        {
            bStreamConnectDone = flag;
        }
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
        mCurConnectParams = j;
        switchStateBtnEnable(i);
        i;
        JVM INSTR tableswitch 0 8: default 60
    //                   0 604
    //                   1 61
    //                   2 529
    //                   3 60
    //                   4 60
    //                   5 60
    //                   6 60
    //                   7 60
    //                   8 666;
           goto _L1 _L2 _L3 _L4 _L1 _L1 _L1 _L1 _L1 _L5
_L7:
        return;
_L3:
        if (mStatusText != null)
        {
            mStatusText.setText(0x7f0b017a);
            mStatusText.setVisibility(0);
        }
        if (mStatusIcon != null && mStatusIcon.getVisibility() == 0)
        {
            mStatusIcon.setVisibility(8);
        }
        if (mCurConnectParams != 19)
        {
            setStreamConnectDone(false);
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
                    isPlaying = false;
                }
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                    mSocketClient.setIsFirstStartPreview(true);
                }
                catch (IOException ioexception3)
                {
                    ioexception3.printStackTrace();
                }
            }
        }
        isExecuting = false;
        mIsSetParams = false;
        switchStateBtnEnable(j);
        switch (j)
        {
        case 15: // '\017'
        case 17: // '\021'
        default:
            return;

        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            IOException ioexception1;
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.releaseCurTokenId();
                mSocketClient.setIsNeedEncoding(false);
            }
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            if (isRecting)
            {
                stopTimer();
                setHandleUpdateSelectedExe(false, false);
            }
            mCurDVMode = -1;
            uninitDVInfo();
            initDVInfo();
            cancelSelectAllStateBtn();
            return;

        case 16: // '\020'
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.setIsNeedEncoding(false);
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            if (mStatusText != null)
            {
                mStatusText.setText(0x7f0b0165);
            }
            if (mPowOffBtn != null)
            {
                mPowOffBtn.setImageResource(0x7f02013b);
            }
            if (isRecting)
            {
                stopTimer();
                setHandleUpdateSelectedExe(false, false);
                return;
            }
            break;

        case 18: // '\022'
            if (mStatusText != null)
            {
                mStatusText.setText(0x7f0b0169);
                return;
            }
            break;

        case 20: // '\024'
            if (mStatusText != null)
            {
                mStatusText.setText(0x7f0b0167);
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
            if (!isPaused)
            {
                showDialog(1);
            }
            continue; /* Loop/switch isn't completed */
        }
_L1:
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!isRecting) goto _L7; else goto _L6
_L6:
        stopTimer();
        setHandleUpdateSelectedExe(false, false);
        return;
_L4:
        if (mStatusText != null)
        {
            mStatusText.setVisibility(8);
        }
        if (mStatusIcon != null)
        {
            mStatusIcon.setVisibility(8);
        }
        switch (j)
        {
        default:
            return;

        case 17: // '\021'
            break;
        }
        if (mPowOffBtn != null)
        {
            mPowOffBtn.setImageResource(0x7f020139);
        }
        setStateBtnVisible(true);
        return;
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
        continue; /* Loop/switch isn't completed */
_L5:
        if (mStatusText != null && mStatusText.getVisibility() == 0)
        {
            mStatusText.setVisibility(8);
        }
        if (mStatusIcon != null)
        {
            ImageView imageview = mStatusIcon;
            int k;
            if (j == 0)
            {
                k = 0x7f020035;
            } else
            {
                k = 0x7f020034;
            }
            imageview.setBackgroundResource(k);
            mStatusIcon.setVisibility(0);
        }
        if (mVideoView != null && mStatusText.getVisibility() == 0)
        {
            mVideoView.setVisibility(8);
            return;
        }
        if (true) goto _L7; else goto _L8
_L8:
    }

    private void switchDVMode(int i)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("****switchDVMode mode=").append(i).append(";mCurExecuteStatus=").append(mCurExecuteStatus).toString());
        if (mCurExecuteStatus == i)
        {
            isExecuting = false;
            return;
        }
        mSwitchDVMode = i;
        switch (i)
        {
        case 7: // '\007'
        default:
            return;

        case 3: // '\003'
            sendHandleCMD(0x1000002b, 0x1000000b, 0L);
            return;

        case 8: // '\b'
            sendHandleCMD(0x1000002b, 0x1000000c, 0L);
            return;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            sendHandleCMD(0x1000002b, 0x10000015, 0L);
            return;
        }
    }

    private void switchExeState(int i)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("******switchExeState toState = ").append(i).append(" mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
        if (mCurExecuteStatus == i)
        {
            isExecuting = false;
            updateStateBtnSelcted(true, mCurExecuteStatus);
            Log.e("AEEVideoStreamActivity", "======switchExeState out==000=======");
            return;
        }
        if (mCurExecuteStatus != -1)
        {
            updateStateBtnSelcted(false, mCurExecuteStatus);
        }
        updateStateBtnSelcted(true, i);
        HashMap hashmap;
        Drawable drawable;
        if (i == 8)
        {
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
                Log.e("AEEVideoStreamActivity", "set mVideoView.setVisibility(SurfaceView.GONE");
                mVideoView.setVisibility(8);
            }
            if (mStatusText != null && mStatusText.getVisibility() == 0)
            {
                mStatusText.setVisibility(8);
            }
        } else
        if (mCurExecuteStatus == 8)
        {
            switchConState(2, -1);
            showSurfaceView(0);
            if (mStatusIcon != null && mStatusIcon.getVisibility() == 0)
            {
                mStatusIcon.setVisibility(8);
            }
            sendHandleCMD(0x10000012, -1, 0L);
        }
        mCurExecuteStatus = i;
        if (mExecutBtn != null)
        {
            mExecutBtn.setVisibility(0);
        }
        if (mSettingBtn != null)
        {
            mSettingBtn.setVisibility(0);
        }
        hashmap = mExecuteBtnDrawable;
        drawable = null;
        if (hashmap != null)
        {
            drawable = (Drawable)mExecuteBtnDrawable.get(Integer.valueOf(i));
        }
        if (drawable != null && mExecutBtn != null)
        {
            mExecutBtn.setBackgroundDrawable(drawable);
        }
        if (mFileSize != null)
        {
            if (mCurDVMode == 4 && mFileSize.getVisibility() == 0)
            {
                mFileSize.setVisibility(8);
            } else
            if (mCurDVMode != 4 && mFileSize.getVisibility() != 0)
            {
                mFileSize.setVisibility(0);
            }
        }
        updateDVSpaceinfo();
        isExecuting = false;
        Log.e("AEEVideoStreamActivity", "======switchExeState out=========");
    }

    private void switchExeState(boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        Drawable drawable;
        HashMap hashmap1 = mExecuteBtnSelectedDrawable;
        drawable = null;
        if (hashmap1 != null)
        {
            drawable = (Drawable)mExecuteBtnSelectedDrawable.get(Integer.valueOf(mCurExecuteStatus));
        }
_L4:
        if (mExecutBtn != null && drawable != null)
        {
            mExecutBtn.setBackgroundDrawable(drawable);
        }
        return;
_L2:
        HashMap hashmap = mExecuteBtnDrawable;
        drawable = null;
        if (hashmap != null)
        {
            drawable = (Drawable)mExecuteBtnDrawable.get(Integer.valueOf(mCurExecuteStatus));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void switchStateBtnEnable(int i)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("switchStateBtnEnable type = ").append(i).toString());
        switch (i)
        {
        case 1: // '\001'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        case 10: // '\n'
        case 15: // '\017'
        case 17: // '\021'
        default:
            return;

        case 16: // '\020'
            setStateBtnVisible(false);
            setStateBtnEnable(false, false, true, false, true);
            return;

        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
            setStateBtnVisible(true);
            setStateBtnEnable(false, false, false, false, true);
            return;

        case 18: // '\022'
        case 20: // '\024'
            setStateBtnEnable(true, true, true, true, true);
            return;

        case 19: // '\023'
            setStateBtnVisible(true);
            setStateBtnEnable(true, false, true, true, true);
            return;

        case 2: // '\002'
            setStateBtnEnable(true, true, true, true, true);
            return;

        case 0: // '\0'
            setStateBtnEnable(false, false, false, false, false);
            return;

        case 8: // '\b'
            setStateBtnEnable(true, true, true, true, true);
            return;

        case 22: // '\026'
            setStateBtnEnable(true, true, true, true, true);
            return;

        case 21: // '\025'
            setStateBtnEnable(false, false, false, false, false);
            return;
        }
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
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("updateDVInfo infoType = ").append(i).append(" paramStr = ").append(s).toString());
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if (s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String as[] = s.split(";");
        i;
        JVM INSTR tableswitch 268435494 268435498: default 104
    //                   268435494 105
    //                   268435495 414
    //                   268435496 566
    //                   268435497 603
    //                   268435498 671;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        return;
_L4:
        if (as.length <= 5) goto _L1; else goto _L9
_L9:
        String s1;
        String s2;
        s1 = "%0";
        s2 = "0H0";
        if (as[0].length() > 0)
        {
            s1 = (new StringBuilder()).append(Integer.parseInt(as[0])).append("%").toString();
            mSocketClient.setDVInfo(3, s1);
        }
        if (as[1].length() > 0)
        {
            int l = Integer.parseInt(as[1]);
            s2 = (new StringBuilder()).append(l / 60).append("H").append(l % 60).toString();
            mSocketClient.setDVInfo(6, s2);
        }
_L17:
        String s3;
        String s4;
        String s5;
        String s6;
        s3 = as[2];
        s4 = as[3];
        s5 = as[4];
        s6 = as[5];
        mSocketClient.setDVInfo(7, s3);
        mSocketClient.setDVInfo(8, s4);
        mSocketClient.setDVInfo(9, s5);
        mSocketClient.setDVInfo(10, s6);
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mCurDVMode = ").append(mCurDVMode).toString());
        mCurDVMode;
        JVM INSTR tableswitch 3 8: default 372
    //                   3 785
    //                   4 752
    //                   5 372
    //                   6 372
    //                   7 372
    //                   8 818;
           goto _L10 _L11 _L12 _L10 _L10 _L10 _L13
_L10:
        if (mCamNumText != null)
        {
            mCamNumText.setText("001");
        }
        if (mCamSapceText == null) goto _L1; else goto _L14
_L14:
        Exception exception;
        mCamSapceText.setText(s1);
        return;
_L5:
        if (as.length != 3) goto _L1; else goto _L15
_L15:
        mCamManfacturer = as[0];
        mCamVersion = as[2];
        mCamModel = as[1];
        mCamInfo = (new StringBuilder()).append(mCamManfacturer).append(" ").append(mCamModel).append(" ").append(mCamVersion).toString();
        if (mCamModelText != null)
        {
            mCamModelText.setText(mCamModel);
        }
        if (mCamInfoText != null)
        {
            mCamInfoText.setText(mCamInfo);
        }
        mSocketClient.setDVInfo(4, as[0]);
        mSocketClient.setDVInfo(11, as[1]);
        mSocketClient.setDVInfo(5, as[2]);
        return;
_L6:
        if (as.length <= 0 || as[0] == null || as[0].length() <= 0) goto _L1; else goto _L16
_L16:
        mSocketClient.setDVInfo(18, as[0]);
        return;
_L7:
        try
        {
            if (mCamWifi != null && mWifiDrawable != null && as[0].length() > 0)
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
_L8:
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
        Exception exception1;
        exception1;
        exception1.printStackTrace();
          goto _L17
_L12:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s5);
        }
        if (mCamSapceText == null) goto _L1; else goto _L18
_L18:
        mCamSapceText.setText(s3);
        return;
_L11:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s4);
        }
        if (mCamSapceText == null) goto _L1; else goto _L19
_L19:
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
          goto _L1
    }

    private void updateDVSpaceinfo()
    {
        mCurDVMode;
        JVM INSTR tableswitch 3 8: default 44
    //                   3 116
    //                   4 139
    //                   5 44
    //                   6 44
    //                   7 44
    //                   8 93;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4
_L1:
        String s;
        String s1;
        s = mSocketClient.getDVInfo(0);
        s1 = mSocketClient.getDVInfo(3);
_L6:
        if (mCamNumText != null)
        {
            mCamNumText.setText(s);
        }
        if (mCamSapceText != null)
        {
            mCamSapceText.setText(s1);
        }
        return;
_L4:
        s = mSocketClient.getDVInfo(10);
        s1 = mSocketClient.getDVInfo(6);
        continue; /* Loop/switch isn't completed */
_L2:
        s = mSocketClient.getDVInfo(8);
        s1 = mSocketClient.getDVInfo(6);
        continue; /* Loop/switch isn't completed */
_L3:
        s = mSocketClient.getDVInfo(9);
        s1 = mSocketClient.getDVInfo(7);
        if (true) goto _L6; else goto _L5
_L5:
    }

    private void updateExeSelectedState(boolean flag, boolean flag1)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("updateExeSelectedState isRecting = ").append(isRecting).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("updateExeSelectedState isSelected = ").append(flag).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("updateExeSelectedState mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
        if ((mCurExecuteStatus == 3 || mCurExecuteStatus == 8 || mCurExecuteStatus == 6) && isRecting != flag)
        {
            isRecting = flag;
            switchExeState(isRecting);
        }
        if (flag1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        Object obj;
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
        JVM INSTR tableswitch 3 8: default 200
    //                   3 256
    //                   4 469
    //                   5 469
    //                   6 372
    //                   7 200
    //                   8 319;
           goto _L3 _L4 _L5 _L5 _L6 _L3 _L7
_L3:
        if (mCamNumText != null && i != 0)
        {
            mCamNumText.setText(String.valueOf(i));
        }
        if (mCamSapceText == null || obj == null) goto _L1; else goto _L8
_L8:
        mCamSapceText.setText(((CharSequence) (obj)));
        return;
_L4:
        boolean flag4 = isRecting;
        i = 0;
        obj = null;
        if (!flag4) goto _L3; else goto _L9
_L9:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(8));
        mSocketClient.setDVInfo(8, String.valueOf(i));
        Exception exception;
        obj = null;
          goto _L3
_L7:
        boolean flag3 = isRecting;
        i = 0;
        obj = null;
        if (!flag3) goto _L3; else goto _L10
_L10:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(10));
        mSocketClient.setDVInfo(10, String.valueOf(i));
        obj = null;
          goto _L3
_L6:
        int l;
        boolean flag2;
        l = Integer.parseInt(mSocketClient.getDVInfo(7));
        flag2 = isRecting;
        i = 0;
        obj = null;
        if (flag2)
        {
            i = 0;
            obj = null;
            if (flag)
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
          goto _L3
_L5:
        int k = Integer.parseInt(mSocketClient.getDVInfo(7));
        i = 0;
        obj = null;
        if (flag) goto _L3; else goto _L11
_L11:
        i = 0;
        obj = null;
        if (k <= 0) goto _L3; else goto _L12
_L12:
        i = 1 + Integer.parseInt(mSocketClient.getDVInfo(9));
        obj = String.valueOf(k - 1);
        mSocketClient.setDVInfo(9, String.valueOf(i));
        mSocketClient.setDVInfo(7, String.valueOf(obj));
          goto _L3
    }

    private void updateStateBtnSelcted(boolean flag, int i)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("updateStateBtnSelcted isSelected = ").append(flag).append(" type = ").append(i).toString());
        i;
        JVM INSTR tableswitch 3 9: default 80
    //                   3 81
    //                   4 97
    //                   5 113
    //                   6 129
    //                   7 161
    //                   8 145
    //                   9 177;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return;
_L2:
        if (mStateVideoBtn != null)
        {
            mStateVideoBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mStatePhotoBtn != null)
        {
            mStatePhotoBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mStateFastShotBtn != null)
        {
            mStateFastShotBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (mStateContShotBtn != null)
        {
            mStateContShotBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (mStateVoiceBtn != null)
        {
            mStateVoiceBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (mStateRolloverBtn != null)
        {
            mStateRolloverBtn.setSelected(flag);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (mStatePlayBackBtn != null)
        {
            mStatePlayBackBtn.setSelected(flag);
            return;
        }
        if (true) goto _L1; else goto _L9
_L9:
    }

    protected void hideSurfaceView()
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(6);
                mHandler.sendEmptyMessage(6);
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

    protected void onActivityResult(int i, int j, Intent intent)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onActivityResultrequestCode = ").append(i).append(" resultCode= ").append(j).toString());
        initORequestRespondsListener();
        switch (i)
        {
        default:
            return;

        case 268435557: 
        case 268435559: 
            if (j == 0x7f0b018d)
            {
                switchTo(1, 19);
                return;
            }
            if (mSocketClient.getCurTokenId() > 0)
            {
                try
                {
                    mSocketClient = AEESocketClient.getInstanceClient();
                }
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
                if (mCurDVMode != 8 && mSocketClient.getIsNeedEncoding())
                {
                    isExecuting = true;
                    sendHandleMSGWithTime(18, 0, -1, null, 0L);
                    sendHandleCMD(0x10000012, 0x10000026, 0L);
                    return;
                } else
                {
                    sendHandleCMD(0x10000026, -1, 0L);
                    return;
                }
            }
            synchronized (mHandler)
            {
                mHandler.removeMessages(7);
                mHandler.sendEmptyMessage(7);
            }
            return;

        case 268435558: 
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            mCurConnectStatus = mSocketClient.getCurConState();
            mCurExecuteStatus = mSocketClient.getCurEXEState();
            isRecting = mSocketClient.getIsExcuting();
            bStreamConnectDone = mSocketClient.getIsStreamConnected();
            mIsPreviewClosed = mSocketClient.getIsPreviewClosed();
            mIsPreviewNotSupport = mSocketClient.getIsPreviewNotSupport();
            mCurConnectParams = mSocketClient.getCurConParams();
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mCurConnectStatus = ").append(mCurConnectStatus).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mCurExecuteStatus = ").append(mCurExecuteStatus).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("isRecting = ").append(isRecting).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("bStreamConnectDone = ").append(bStreamConnectDone).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mIsPreviewClosed = ").append(mIsPreviewClosed).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mIsPreviewNotSupport = ").append(mIsPreviewNotSupport).toString());
            Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mCurConnectParams = ").append(mCurConnectParams).toString());
            if (mCurExecuteStatus == 6 || mCurExecuteStatus == 3 || mCurExecuteStatus == 8)
            {
                if (isRecting)
                {
                    mTimeCountor = mSocketClient.getCurTimer();
                    startTimer();
                    sendHandleMSGWithTime(16, 1, -1, null, 0L);
                } else
                {
                    stopTimer();
                    sendHandleMSGWithTime(16, 0, -1, null, 0L);
                }
            } else
            {
                isExecuting = isRecting;
                isRecting = false;
            }
            updateDVSpaceinfo();
            switchTo(mCurConnectStatus, mCurConnectParams);
            return;
        }
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void onBackPressed()
    {
        if (!mIsTwicePressed)
        {
            mIsTwicePressed = true;
            Toast.makeText(this, 0x7f0b005f, 1).show();
            return;
        } else
        {
            super.onBackPressed();
            return;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.e("AEEVideoStreamActivity", "onCreate  --------------------------------------- ");
        requestWindowFeature(1);
        getWindow().setFlags(2048, 2048);
        String s = MediaPlusApplication.instance().getAssetFilePath(2);
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onCreate IN  ------- pluginPath: ").append(s).toString());
        mArcMediaPlayer = new SecureMediaPlayer();
        mArcMediaPlayer.setConfigFile(s);
        mArcMediaPlayer.setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {

            final AEEVideoStreamActivity this$0;

            public void onPrepared(MediaPlayer mediaplayer)
            {
                Log.e("AEEVideoStreamActivity", "onPrepared IN  --------------------------------------- ");
                if (mArcMediaPlayer != null)
                {
                    mArcMediaPlayer.start();
                    mArcMediaPlayer.setDisplay(null);
                    mArcMediaPlayer.setDisplay(mSurfaceHolder);
                }
            }

            
            {
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
        });
        mArcMediaPlayer.setOnInfoListener(new android.media.MediaPlayer.OnInfoListener() {

            final AEEVideoStreamActivity this$0;

            public boolean onInfo(MediaPlayer mediaplayer, int i, int j)
            {
                Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("what = ").append(i).append(" extra = ").append(j).toString());
                switch (i)
                {
                default:
                    return false;

                case 900: 
                    isPlaying = true;
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
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
        });
        initDefault();
        init();
        mNetworkTool = new NetworkTool(this);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            mSocketClient.setOnRequestRespondsListener(mOnRequestRespondsListener);
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
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

            final AEEVideoStreamActivity this$0;

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
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
        }).show();
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onDestroy()
    {
        Log.e("AEEVideoStreamActivity", "onDestroy  --------------------------------------- ");
        String s;
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                mSocketClient.sendCommand(0x10000002, null);
                mSocketClient.startRespondParams(0x10000002);
                if (isRecting && mCurExecuteStatus != 6)
                {
                    stopTimer();
                }
            }
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        s = Settings.instance().getDefaultDMSName();
        if (s != null && s.length() > 0 && !s.equalsIgnoreCase("ActionCam") && !s.equalsIgnoreCase("IRONX") && !s.equalsIgnoreCase("ActionCam DMS") && !s.equalsIgnoreCase("ArcSoft DMS for AEE") && !s.equalsIgnoreCase("DMS for DV") && !s.equalsIgnoreCase("ArcSoft DMS for DXG") && !s.equalsIgnoreCase("ArcSoft DMS for Salix") && !s.equalsIgnoreCase("Salix ActionCam DMS") && !s.equalsIgnoreCase("ArcSoft DMS") && !s.startsWith("ArcSoft DMS"))
        {
            Settings.instance().setDefaultDMSUDN(null);
            Settings.instance().setDefaultDMSName(null);
        }
        super.onDestroy();
        bPlayerStarted = false;
        isPlaying = false;
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.release();
            mArcMediaPlayer = null;
        }
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null)
            {
                mSocketClient.setIsFirstStartPreview(true);
                mSocketClient.stopRespondParams();
                mSocketClient.setOnRequestRespondsListener(null);
                mSocketClient.close();
                mSocketClient.realseClient();
                mSocketClient = null;
            }
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        catch (Exception exception1)
        {
            exception1.printStackTrace();
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
        Log.e("AEEVideoStreamActivity", "onPause");
        isPaused = true;
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.reset();
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                mSocketClient.setIsFirstStartPreview(true);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        bPlayerStarted = false;
        isPlaying = false;
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
        if (mPauseType != 0x10000066 && mPauseType != -1)
        {
            setStreamConnectDone(false);
        }
        unregisterWIFIReceiver();
        SystemUtils.keepScreenOn(this, false);
        super.onPause();
    }

    protected void onResume()
    {
        Log.e("AEEVideoStreamActivity", "onResume");
        isPaused = false;
        mPauseType = -1;
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
        if (!mSocketClient.isConnected() || mCurExecuteStatus != 8) goto _L4; else goto _L3
_L3:
        switchTo(8, -1);
_L6:
        if (mSocketClient.isConnected() && isRecting)
        {
            sendHandleCMD(0x1000002c, 0x10000026, 0L);
        }
_L2:
        registerWIFIReceiver();
        super.onResume();
        return;
_L4:
        if (mSocketClient.isConnected() && mCurExecuteStatus != 8 && bStreamConnectDone)
        {
            switchTo(2, -1);
            showSurfaceView(0);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void resetSurfaceView()
    {
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(15);
                mHandler.sendEmptyMessage(15);
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

    public void resumePlayback()
    {
        Log.e("AEEVideoStreamActivity", "resumePlayback()  ");
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
            return;
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
        mArcMediaPlayer.setDisplayRect(0, 0, mWidth, mHeight);
        mArcMediaPlayer.setVolume(1.0F, 1.0F);
        mArcMediaPlayer.setBenchmark(2);
        mArcMediaPlayer.setOnErrorListener(new android.media.MediaPlayer.OnErrorListener() {

            final AEEVideoStreamActivity this$0;

            public boolean onError(MediaPlayer mediaplayer, int i, int j)
            {
                Log.v("AEEVideoStreamActivity", (new StringBuilder()).append("showStream onerror what=").append(i).append(";extra=").append(j).toString());
                bPlayerStarted = false;
                switchTo(1, 14);
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b019f, 1).show();
                return false;
            }

            
            {
                this$0 = AEEVideoStreamActivity.this;
                super();
            }
        });
        mArcMediaPlayer.prepareAsync();
    }

    public void showSurfaceView(int i)
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
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("FENG  surfaceChanged  ---- width: ").append(j).append(" height: ").append(k).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("surfaceChanged bStreamConnectDone = ").append(bStreamConnectDone).append(" bPlayerStarted = ").append(bPlayerStarted).toString());
        mWidth = j;
        mHeight = k;
        if (bStreamConnectDone)
        {
            if (bPlayerStarted && mArcMediaPlayer != null)
            {
                mArcMediaPlayer.setDisplay(mSurfaceHolder);
                mArcMediaPlayer.setDisplayRect(0, 0, mWidth, mHeight);
                mArcMediaPlayer.setVolume(1.0F, 1.0F);
            } else
            {
                showStream();
                bPlayerStarted = true;
            }
            SystemUtils.keepScreenOn(this, true);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        Log.e("AEEVideoStreamActivity", "surfaceCreated  ----  ");
        mSurfaceHolder = surfaceholder;
        mSurfaceHolder.setType(0);
        mSurfaceHolder.setFormat(4);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        Log.e("AEEVideoStreamActivity", "surfaceDestroyed  ----  ");
        if (mArcMediaPlayer != null)
        {
            mArcMediaPlayer.setVolume(0.0F, 0.0F);
            mArcMediaPlayer.setDisplay(null);
        }
        SystemUtils.keepScreenOn(this, false);
    }

    public void switchTo(int i, int j)
    {
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("switchTo type = ").append(i).append(", params = ").append(j).append(" mCurConStatus = ").append(mCurConnectStatus).toString());
        i;
        JVM INSTR tableswitch 0 8: default 100
    //                   0 101
    //                   1 101
    //                   2 101
    //                   3 100
    //                   4 100
    //                   5 100
    //                   6 100
    //                   7 100
    //                   8 174;
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
                mHandler.removeMessages(13);
                mHandler.sendEmptyMessageDelayed(13, i);
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

    static 
    {
        loadLibrarys();
    }





/*
    static int access$1002(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mCurConnectParams = i;
        return i;
    }

*/




/*
    static boolean access$1202(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.mIsPreviewClosed = flag;
        return flag;
    }

*/



/*
    static boolean access$1302(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.mIsPreviewNotSupport = flag;
        return flag;
    }

*/




/*
    static boolean access$1502(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.isExecuting = flag;
        return flag;
    }

*/



/*
    static boolean access$1602(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.mIsSetParams = flag;
        return flag;
    }

*/




/*
    static int access$1802(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mPauseType = i;
        return i;
    }

*/




/*
    static boolean access$202(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.isPlaying = flag;
        return flag;
    }

*/



/*
    static int access$2102(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mTimeCountor = i;
        return i;
    }

*/


/*
    static int access$2104(AEEVideoStreamActivity aeevideostreamactivity)
    {
        int i = 1 + aeevideostreamactivity.mTimeCountor;
        aeevideostreamactivity.mTimeCountor = i;
        return i;
    }

*/






/*
    static boolean access$2502(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.isWifiConnected = flag;
        return flag;
    }

*/









/*
    static int access$3202(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mCurSpendTime = i;
        return i;
    }

*/







/*
    static ConnectSocketTask access$3702(AEEVideoStreamActivity aeevideostreamactivity, ConnectSocketTask connectsockettask)
    {
        aeevideostreamactivity.mSocketTask = connectsockettask;
        return connectsockettask;
    }

*/






/*
    static int access$4002(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mCurDVMode = i;
        return i;
    }

*/



/*
    static boolean access$4102(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.mIsFirstStreaming = flag;
        return flag;
    }

*/





/*
    static int access$4402(AEEVideoStreamActivity aeevideostreamactivity, int i)
    {
        aeevideostreamactivity.mSwitchDVMode = i;
        return i;
    }

*/



/*
    static boolean access$502(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.bPlayerStarted = flag;
        return flag;
    }

*/



/*
    static boolean access$702(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.mIsTwicePressed = flag;
        return flag;
    }

*/




/*
    static boolean access$902(AEEVideoStreamActivity aeevideostreamactivity, boolean flag)
    {
        aeevideostreamactivity.isRecting = flag;
        return flag;
    }

*/
}
