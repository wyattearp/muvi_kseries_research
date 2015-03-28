// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient, AEEVideoStreamActivity

public class AEEConnectActivity extends Activity
{
    public class ConnectSocketTask extends AsyncTask
    {

        final AEEConnectActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            Log.e("AEEConnectActivity", (new StringBuilder()).append("ConnectSocketTask RtspUtils.isAmbar() = ").append(RtspUtils.isAmbar()).toString());
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
                break MISSING_BLOCK_LABEL_105;
            }
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                bConnectDone = true;
                if (mHandler != null)
                {
                    mHandler.removeMessages(1);
                    mHandler.sendEmptyMessage(1);
                    return;
                }
                break MISSING_BLOCK_LABEL_105;
            }
            try
            {
                switchTo(2);
                return;
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            switchTo(2);
        }

        public ConnectSocketTask()
        {
            this$0 = AEEConnectActivity.this;
            super();
        }
    }


    private static final int SEND_MSG_DELAY = 500;
    private static final String TAG = "AEEConnectActivity";
    private static final int TYPE_CONNECTING = 1;
    private static final int TYPE_CONNECT_FAILED = 2;
    private static final int TYPE_CONNECT_INIT = 3;
    private final int MSG_SEND_COMMAND = 8;
    private final int MSG_SEND_COMMAND_FAILED = 4;
    private final int MSG_SWITCH_ACTIITY = 1;
    private final int MSG_SWITCH_STATUS = 0;
    private boolean bConnectDone;
    boolean isWifiConnected;
    private Drawable mBtnConnectFailed;
    private Drawable mBtnConnecting;
    private RelativeLayout mCheckLayout;
    private ImageView mConnectBtn;
    private TextView mConnectFailText;
    private int mCurConStatus;
    private final Handler mHandler = new Handler() {

        final AEEConnectActivity this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 8: default 56
        //                       0 62
        //                       1 76
        //                       2 56
        //                       3 56
        //                       4 86
        //                       5 56
        //                       6 56
        //                       7 56
        //                       8 159;
               goto _L1 _L2 _L3 _L1 _L1 _L4 _L1 _L1 _L1 _L5
_L1:
            super.handleMessage(message);
_L11:
            return;
_L2:
            switchState(message.arg1);
              goto _L1
_L3:
            switchActivity();
              goto _L1
_L4:
            int k;
            int l;
            k = message.arg1;
            l = 0;
            k;
            JVM INSTR lookupswitch 2: default 124
        //                       -17: 152
        //                       1: 145;
               goto _L6 _L7 _L8
_L6:
            break; /* Loop/switch isn't completed */
_L7:
            break MISSING_BLOCK_LABEL_152;
_L9:
            if (l != 0)
            {
                Toast.makeText(AEEConnectActivity.this, l, 0).show();
            }
              goto _L1
_L8:
            l = 0x7f0b018e;
              goto _L9
            l = 0x7f0b0198;
              goto _L9
_L5:
            int i;
            int j;
            i = message.arg1;
            j = message.arg2;
            if (i == -1 && j == -1) goto _L11; else goto _L10
_L10:
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected())
            {
label0:
                {
                    if (i != -1)
                    {
                        break label0;
                    }
                    try
                    {
                        mSocketClient.sendCommand(j, null);
                        mSocketClient.startRespondParams(j);
                    }
                    catch (IOException ioexception)
                    {
                        ioexception.printStackTrace();
                    }
                }
            }
              goto _L1
            mSocketClient.sendCommand(i, null);
            mSocketClient.startRespondParams(i);
            mSocketClient.setNextCMD(j);
              goto _L1
        }

            
            {
                this$0 = AEEConnectActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        final AEEConnectActivity this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo != null && networkstateinfo.networkInfo.getType() == 1)
            {
                Log.e("AEEConnectActivity", (new StringBuilder()).append("OnConnectivityChanged  ---------- info.networkInfo.isConnected() = ").append(networkstateinfo.networkInfo.isConnected()).toString());
                isWifiConnected = networkstateinfo.networkInfo.isConnected();
                if (!isWifiConnected)
                {
                    tryConnectSocket();
                    return;
                }
            }
        }

            
            {
                this$0 = AEEConnectActivity.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private CheckBox mNoCheck;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final AEEConnectActivity this$0;

        public void onClick(View view)
        {
            boolean flag = true;
            view.getId();
            JVM INSTR tableswitch 2131296637 2131296642: default 44
        //                       2131296637 45
        //                       2131296638 62
        //                       2131296639 44
        //                       2131296640 44
        //                       2131296641 99
        //                       2131296642 148;
               goto _L1 _L2 _L3 _L1 _L1 _L4 _L5
_L1:
            return;
_L2:
            startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            return;
_L3:
            if (mCurConStatus != flag)
            {
                if (needTryConnect)
                {
                    tryConnectSocket();
                    return;
                } else
                {
                    switchActivity();
                    return;
                }
            }
              goto _L1
_L4:
            needTryConnect = mYesCheck.isChecked();
            CheckBox checkbox = mNoCheck;
            if (needTryConnect)
            {
                flag = false;
            }
            checkbox.setChecked(flag);
            return;
_L5:
            AEEConnectActivity aeeconnectactivity = AEEConnectActivity.this;
            if (mNoCheck.isChecked())
            {
                flag = false;
            }
            aeeconnectactivity.needTryConnect = flag;
            mYesCheck.setChecked(needTryConnect);
            return;
        }

            
            {
                this$0 = AEEConnectActivity.this;
                super();
            }
    };
    private Resources mResources;
    private AEESocketClient mSocketClient;
    private ConnectSocketTask mSocketTask;
    private View mViewGroup;
    private Drawable mWifiConnectFailed;
    private Drawable mWifiConnecting;
    private ImageView mWifiView;
    private CheckBox mYesCheck;
    private boolean needTryConnect;

    public AEEConnectActivity()
    {
        bConnectDone = false;
        mNetworkTool = null;
        mSocketClient = null;
        mResources = null;
        mViewGroup = null;
        mWifiView = null;
        mConnectBtn = null;
        mConnectFailText = null;
        mYesCheck = null;
        mNoCheck = null;
        needTryConnect = true;
        mCheckLayout = null;
        mBtnConnectFailed = null;
        mBtnConnecting = null;
        mWifiConnectFailed = null;
        mWifiConnecting = null;
        isWifiConnected = false;
        mSocketTask = null;
        mCurConStatus = 3;
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
            switchTo(2);
            return false;
        }
        return true;
    }

    private void init()
    {
        mResources = getResources();
        mViewGroup = LayoutInflater.from(this).inflate(0x7f030042, null);
        setContentView(mViewGroup);
        mWifiView = (ImageView)findViewById(0x7f09017d);
        mWifiView.setOnClickListener(mOnClickListener);
        mConnectBtn = (ImageView)findViewById(0x7f09017e);
        mConnectBtn.setOnClickListener(mOnClickListener);
        mYesCheck = (CheckBox)findViewById(0x7f090181);
        mYesCheck.setOnClickListener(mOnClickListener);
        mNoCheck = (CheckBox)findViewById(0x7f090182);
        mNoCheck.setOnClickListener(mOnClickListener);
        mConnectFailText = (TextView)findViewById(0x7f09017f);
        mCheckLayout = (RelativeLayout)findViewById(0x7f090180);
    }

    private void initDefault()
    {
        releaseDefault();
        if (mWifiConnecting == null)
        {
            mWifiConnecting = (BitmapDrawable)getResources().getDrawable(0x7f020009);
        }
        if (mWifiConnectFailed == null)
        {
            mWifiConnectFailed = (BitmapDrawable)getResources().getDrawable(0x7f02000a);
        }
        if (mBtnConnectFailed == null)
        {
            mBtnConnectFailed = getResources().getDrawable(0x7f020008);
        }
        if (mBtnConnecting == null)
        {
            mBtnConnecting = getResources().getDrawable(0x7f020006);
        }
    }

    private void releaseDefault()
    {
        if (mWifiConnecting != null)
        {
            mWifiConnecting = null;
        }
        if (mWifiConnectFailed != null)
        {
            mWifiConnectFailed = null;
        }
        if (mBtnConnectFailed != null)
        {
            mBtnConnectFailed = null;
        }
        if (mBtnConnecting != null)
        {
            mBtnConnecting = null;
        }
    }

    private void releaseUI()
    {
        if (mViewGroup != null)
        {
            SystemUtils.unbindDrawables(mViewGroup);
            mViewGroup = null;
        }
        System.gc();
    }

    private void sendCommandFailed(int i)
    {
        synchronized (mHandler)
        {
            if (mHandler != null)
            {
                Message message = mHandler.obtainMessage(4);
                message.arg1 = i;
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

    private void switchActivity()
    {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), com/arcsoft/videostream/aee/AEEVideoStreamActivity);
        startActivity(intent);
        finish();
    }

    private void switchState(int i)
    {
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            if (mConnectBtn != null)
            {
                mConnectBtn.setBackgroundDrawable(mBtnConnecting);
                mConnectBtn.setClickable(false);
            }
            if (mWifiView != null)
            {
                mWifiView.setBackgroundDrawable(mWifiConnecting);
            }
            if (mCheckLayout != null)
            {
                mCheckLayout.setVisibility(8);
            }
            if (mConnectFailText != null)
            {
                mConnectFailText.setVisibility(8);
            }
            bConnectDone = false;
            return;

        case 2: // '\002'
            break;
        }
        if (mConnectBtn != null)
        {
            mConnectBtn.setBackgroundDrawable(mBtnConnectFailed);
            mConnectBtn.setClickable(true);
        }
        if (mWifiView != null)
        {
            mWifiView.setBackgroundDrawable(mWifiConnectFailed);
        }
        if (mCheckLayout != null)
        {
            mCheckLayout.setVisibility(0);
        }
        if (mConnectFailText != null)
        {
            mConnectFailText.setVisibility(0);
        }
        bConnectDone = false;
    }

    private void tryConnectSocket()
    {
        Log.e("AEEConnectActivity", (new StringBuilder()).append("tryConnectSocket isWifiConnected = ").append(isWifiConnected).toString());
        if (isWifiConnected)
        {
            switchTo(1);
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                if (mSocketClient != null && mSocketClient.isConnected())
                {
                    mSocketClient.close();
                    mSocketClient = null;
                }
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            if (mSocketTask != null)
            {
                mSocketTask.cancel(true);
                mSocketTask = null;
            }
            mSocketTask = new ConnectSocketTask();
            mSocketTask.execute(new String[0]);
            return;
        }
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            if (mSocketClient != null && mSocketClient.isConnected())
            {
                mSocketClient.close();
                mSocketClient = null;
            }
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        switchTo(2);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.e("AEEConnectActivity", "onCreate  --------------------------------------- ");
        requestWindowFeature(1);
        getWindow().setFlags(2048, 2048);
        initDefault();
        init();
        mNetworkTool = new NetworkTool(this);
        mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
    }

    protected void onDestroy()
    {
        Log.e("AEEConnectActivity", "onDestroy  --------------------------------------- ");
        super.onDestroy();
        if (mNetworkTool != null)
        {
            mNetworkTool.recycle();
            mNetworkTool = null;
        }
        releaseUI();
        releaseDefault();
        System.gc();
    }

    protected void switchTo(int i)
    {
        Log.e("AEEConnectActivity", (new StringBuilder()).append("switchTo type = ").append(i).append(", mCurConStatus = ").append(mCurConStatus).toString());
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            if (mHandler != null)
            {
                synchronized (mHandler)
                {
                    mHandler.removeMessages(0);
                    Message message1 = mHandler.obtainMessage(0);
                    message1.arg1 = i;
                    mHandler.sendMessage(message1);
                }
            }
            mCurConStatus = 1;
            return;

        case 2: // '\002'
            break;
        }
        break MISSING_BLOCK_LABEL_132;
        exception1;
        handler1;
        JVM INSTR monitorexit ;
        throw exception1;
        if (mHandler != null)
        {
            synchronized (mHandler)
            {
                mHandler.removeMessages(0);
                Message message = mHandler.obtainMessage(0);
                message.arg1 = i;
                mHandler.sendMessage(message);
            }
        }
        mCurConStatus = 2;
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
    }





/*
    static boolean access$102(AEEConnectActivity aeeconnectactivity, boolean flag)
    {
        aeeconnectactivity.needTryConnect = flag;
        return flag;
    }

*/








/*
    static AEESocketClient access$702(AEEConnectActivity aeeconnectactivity, AEESocketClient aeesocketclient)
    {
        aeeconnectactivity.mSocketClient = aeesocketclient;
        return aeesocketclient;
    }

*/



/*
    static boolean access$902(AEEConnectActivity aeeconnectactivity, boolean flag)
    {
        aeeconnectactivity.bConnectDone = flag;
        return flag;
    }

*/
}
