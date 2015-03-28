// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.mediaplus.widget.PopupMenuWindow;
import com.arcsoft.util.FileUtils;
import com.arcsoft.videotrim.Utils.AppContext;
import com.arcsoft.videotrim.Utils.MediaFileUtils;
import com.arcsoft.videotrim.Utils.RuntimeConfig;
import com.arcsoft.videotrim.Utils.ThumbManagerList;
import com.arcsoft.videotrim.Utils.UtilFunc;
import java.io.File;
import powermobia.ve.utils.MRect;
import powermobia.videoeditor.base.MDisplayContext;
import powermobia.videoeditor.base.MRange;
import powermobia.videoeditor.base.MSessionStream;
import powermobia.videoeditor.base.MVideoInfo;
import powermobia.videoeditor.clip.MClip;
import powermobia.videoeditor.clip.MMediaSource;

// Referenced classes of package com.arcsoft.videotrim:
//            PlayBackModule, VESurfaceView, TrimUIOper, SaveDialog, 
//            VideoClip

public class videoTrimActivity extends Activity
    implements android.view.SurfaceHolder.Callback, android.view.View.OnClickListener
{
    private class PlaybackEventHandler extends Handler
    {

        final videoTrimActivity this$0;

        public void handleMessage(Message message)
        {
            if (m_Player == null || message == null)
            {
                return;
            }
            message.what;
            JVM INSTR tableswitch 4097 4100: default 48
        //                       4097 54
        //                       4098 107
        //                       4099 138
        //                       4100 93;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            super.handleMessage(message);
            return;
_L2:
            m_Player.OnReady(false);
            m_Player.EnableDisplay(true);
            m_Player.SeekTo(0);
            continue; /* Loop/switch isn't completed */
_L5:
            m_Player.OnPaused();
            continue; /* Loop/switch isn't completed */
_L3:
            setPlayerPlay(false);
            m_Player.SeekTo(0);
            updateTrimUI(0);
            continue; /* Loop/switch isn't completed */
_L4:
            m_Player.OnPlaying();
            int i = m_Player.getCurrentTime();
            updateTrimUI(i);
            if (true) goto _L1; else goto _L6
_L6:
        }

        public PlaybackEventHandler(Looper looper)
        {
            this$0 = videoTrimActivity.this;
            super(looper);
        }
    }


    private static final int MSG_SEEKING_BEGIN = 0;
    private static final int MSG_SEEKING_DELAY_TIME = 500;
    private static final int MSG_SEEKING_END = 1;
    private static final int PLAY_TIME_END_DIS = 50;
    private static final int TRIM_TIME_MIN = 1000;
    private boolean mIsBtnClickListenerProcessing;
    private PopupMenuWindow mMenuWindow;
    private boolean mOldEnabledBtnEnd;
    private boolean mOldEnabledBtnStart;
    private int mSeekPos;
    private Handler mSeekingHandler;
    private LoadingDialog mSeekingWaitDialog;
    private VideoClip mTrimClip;
    private int mTrimLeftValue;
    private int mTrimRightValue;
    private final TrimUIOper.TrimUIOperListener mTrimUIOperListener = new TrimUIOper.TrimUIOperListener() {

        final videoTrimActivity this$0;

        public void notifyCurScaleLevelChanged()
        {
            setBtnSpreadOrPinchEnable();
        }

        public void notifyScaleLevelChanged(int i, int j)
        {
            setBtnSpreadOrPinchEnable();
        }

        public void notifySeekToValue(int i)
        {
            m_TrimTimeText.setText(UtilFunc.TransTimeToString(i, 0));
            if (i >= mTrimLeftValue) goto _L2; else goto _L1
_L1:
            boolean flag;
            boolean flag1;
            int k1 = mTrimRightValue - i;
            flag1 = false;
            flag = false;
            if (k1 >= 1000)
            {
                flag = true;
            }
_L4:
            refreshEnableStartAndEndBtn(flag, flag1);
            AsySeekToThread(i);
            return;
_L2:
            if (i > mTrimRightValue)
            {
                int j1 = i - mTrimLeftValue;
                flag1 = false;
                flag = false;
                if (j1 >= 1000)
                {
                    flag1 = true;
                    flag = false;
                }
            } else
            {
                int j = mTrimLeftValue;
                flag = false;
                if (i != j)
                {
                    int i1 = mTrimRightValue - i;
                    flag = false;
                    if (i1 >= 1000)
                    {
                        flag = true;
                    }
                }
                int k = mTrimRightValue;
                flag1 = false;
                if (i != k)
                {
                    int l = i - mTrimLeftValue;
                    flag1 = false;
                    if (l >= 1000)
                    {
                        flag1 = true;
                    }
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public void notifyValueChanged(boolean flag, int i)
        {
            m_bHasTrimed = false;
            if (flag)
            {
                mTrimLeftValue = i;
            } else
            {
                mTrimRightValue = i;
            }
            refreshEnableStartAndEndBtn(false, false);
            m_TrimTimeText.setText(UtilFunc.TransTimeToString(i, 0));
            AsySeekToThread(i);
        }

        public void onTrimThumbManagerList(ThumbManagerList thumbmanagerlist)
        {
            m_ThumbManagerList = thumbmanagerlist;
        }

            
            {
                this$0 = videoTrimActivity.this;
                super();
            }
    };
    private int mVideoHeight;
    private int mVideoWidth;
    private Button m_BtnEnd;
    private Button m_BtnExit;
    private Button m_BtnMenu;
    private Button m_BtnPause;
    private Button m_BtnPinch;
    private Button m_BtnPlay;
    private Button m_BtnSpread;
    private Button m_BtnStart;
    private MClip m_Clip;
    private MDisplayContext m_DisplayContext;
    private boolean m_IsSeeking;
    private final com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener m_OnMenuClickedListener = new com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener() {

        final videoTrimActivity this$0;

        public void onClicked(int i)
        {
            if (i != 0) goto _L2; else goto _L1
_L1:
            setResult(-1);
            saveWithShareOrNot(false);
_L4:
            if (mMenuWindow != null)
            {
                mMenuWindow.hidePopopMenuWindow();
            }
            return;
_L2:
            if (i == 1)
            {
                setResult(-1);
                saveWithShareOrNot(true);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

            
            {
                this$0 = videoTrimActivity.this;
                super();
            }
    };
    private PlayBackModule m_Player;
    private PlaybackEventHandler m_PlayerEventHandler;
    private SaveDialog m_SaveDialog;
    private String m_SharedTempFileName;
    private MSessionStream m_Stream;
    private SurfaceHolder m_SurfaceHolder;
    private VESurfaceView m_SurfaceView;
    private ThumbManagerList m_ThumbManagerList;
    private final Handler m_TrimSaveHandler = new Handler() {

        final videoTrimActivity this$0;

        public void handleMessage(Message message)
        {
            if (message.what == 0)
            {
                m_SharedTempFileName = (String)message.obj;
                UtilFunc.addVideoFileByFullName(getApplicationContext(), m_SharedTempFileName);
                if (m_bNeedShare)
                {
                    if (UtilFunc.IsFileExisted(m_SharedTempFileName))
                    {
                        UtilFunc.shareVideoByUri(Uri.fromFile(new File(m_SharedTempFileName)), videoTrimActivity.this);
                    }
                } else
                {
                    Toast.makeText(videoTrimActivity.this, 0x7f0b01c5, 0).show();
                }
                m_bHasTrimed = true;
            }
        }

            
            {
                this$0 = videoTrimActivity.this;
                super();
            }
    };
    private TextView m_TrimTimeText;
    private TrimUIOper m_TrimUIOper;
    private int m_VideoDuration;
    private boolean m_bBuildPlayer;
    private boolean m_bHasTrimed;
    private boolean m_bInited;
    private boolean m_bNeedShare;
    private String m_strSourceFile;

    public videoTrimActivity()
    {
        m_SurfaceView = null;
        m_SurfaceHolder = null;
        m_TrimTimeText = null;
        m_Player = null;
        m_DisplayContext = null;
        m_Stream = null;
        m_Clip = null;
        m_TrimUIOper = null;
        m_ThumbManagerList = null;
        mMenuWindow = null;
        m_strSourceFile = null;
        mTrimLeftValue = 0;
        mTrimRightValue = 0;
        m_VideoDuration = 0;
        m_bInited = false;
        m_bBuildPlayer = false;
        mSeekPos = -1;
        m_BtnMenu = null;
        m_BtnPlay = null;
        m_BtnPause = null;
        m_BtnExit = null;
        m_BtnStart = null;
        m_BtnEnd = null;
        m_BtnSpread = null;
        m_BtnPinch = null;
        mOldEnabledBtnStart = false;
        mOldEnabledBtnEnd = false;
        m_SaveDialog = null;
        mTrimClip = null;
        m_bNeedShare = false;
        m_SharedTempFileName = null;
        m_bHasTrimed = false;
        m_IsSeeking = false;
        mSeekingWaitDialog = null;
        mVideoWidth = 0;
        mVideoHeight = 0;
        mIsBtnClickListenerProcessing = false;
        mSeekingHandler = new Handler() {

            final videoTrimActivity this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 1: default 28
            //                           0 29
            //                           1 42;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                removeMessages(0);
                showDeletingWaitDialog();
                return;
_L3:
                removeMessages(0);
                removeMessages(1);
                cancelDeletingWaitDialog();
                if (m_TrimUIOper != null && m_TrimUIOper.getTouchPlayerStatus())
                {
                    setPlayerPlay(true);
                    m_TrimUIOper.setTouchPlayerStatus(false);
                    return;
                }
                if (true) goto _L1; else goto _L4
_L4:
            }

            
            {
                this$0 = videoTrimActivity.this;
                super();
            }
        };
    }

    private void AsySeekToThread(final int value)
    {
        if (m_Player == null)
        {
            return;
        }
        if (mSeekingHandler != null)
        {
            mSeekingHandler.sendEmptyMessageDelayed(0, 500L);
        }
        (new Thread(new Runnable() {

            final videoTrimActivity this$0;
            final int val$value;

            public void run()
            {
                setSeekToStatus(true);
                m_Player.SeekTo(value);
                if (mSeekingHandler != null)
                {
                    mSeekingHandler.sendEmptyMessage(1);
                }
                setSeekToStatus(false);
            }

            
            {
                this$0 = videoTrimActivity.this;
                value = i;
                super();
            }
        })).start();
    }

    private int CreatePlayer(MSessionStream msessionstream, int i)
    {
        if (m_Player != null)
        {
            return 0;
        }
        if (msessionstream == null)
        {
            return 1;
        } else
        {
            InitDisplayContext();
            m_Player = new PlayBackModule();
            int j = m_Player.Init(this, m_PlayerEventHandler, msessionstream, i, m_DisplayContext, m_SurfaceView.getHolder().getSurface());
            m_Player.EnableDisplay(false);
            return j;
        }
    }

    private void DoPlayerPlay()
    {
        if (m_Player == null)
        {
            return;
        } else
        {
            setPlayerRange();
            m_Player.Play();
            return;
        }
    }

    private int InitClip()
    {
        powermobia.videoeditor.MEngine mengine = AppContext.getAppContext().getVEEngine();
        if (mengine == null)
        {
            return 1;
        }
        if (m_Clip == null)
        {
            m_Clip = new MClip();
        }
        MMediaSource mmediasource = new MMediaSource(0, false, m_strSourceFile);
        return m_Clip.init(mengine, mmediasource);
    }

    private void InitDisplayContext()
    {
        if (m_DisplayContext != null)
        {
            m_DisplayContext = null;
        }
        if (m_SurfaceView != null)
        {
            int i = m_SurfaceView.getWidth();
            int j = m_SurfaceView.getHeight();
            if (i > 0 && j > 0)
            {
                MRect mrect = new MRect(0, 0, i, j);
                m_DisplayContext = new MDisplayContext(mrect, mrect, 0, 0, 0, 3);
                return;
            }
        }
    }

    private void InitRes()
    {
        if (m_bInited)
        {
            return;
        }
        setContentView(0x7f03003e);
        if (InitClip() != 0)
        {
            Toast.makeText(this, 0x7f0b01bb, 0).show();
            release();
            finish();
            return;
        }
        m_VideoDuration = getClipDuration();
        if (m_VideoDuration < 1000)
        {
            Toast.makeText(this, 0x7f0b01cd, 0).show();
            release();
            finish();
            return;
        }
        mTrimLeftValue = 0;
        mTrimRightValue = m_VideoDuration;
        m_TrimTimeText = (TextView)findViewById(0x7f09016d);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DS-DIGI.TTF");
        m_TrimTimeText.setTypeface(typeface);
        m_TrimTimeText.setText(UtilFunc.TransTimeToString(0, 0));
        m_BtnMenu = (Button)findViewById(0x7f090161);
        m_BtnExit = (Button)findViewById(0x7f09015f);
        m_BtnPlay = (Button)findViewById(0x7f090170);
        m_BtnPause = (Button)findViewById(0x7f090171);
        m_BtnStart = (Button)findViewById(0x7f09016e);
        m_BtnEnd = (Button)findViewById(0x7f09016f);
        m_BtnSpread = (Button)findViewById(0x7f090172);
        m_BtnPinch = (Button)findViewById(0x7f090173);
        m_BtnPinch.setEnabled(false);
        refreshEnableStartAndEndBtn(false, false);
        m_BtnMenu.setOnClickListener(this);
        m_BtnExit.setOnClickListener(this);
        m_BtnPlay.setOnClickListener(this);
        m_BtnPause.setOnClickListener(this);
        m_BtnStart.setOnClickListener(this);
        m_BtnEnd.setOnClickListener(this);
        m_BtnSpread.setOnClickListener(this);
        m_BtnPinch.setOnClickListener(this);
        m_SurfaceView = (VESurfaceView)findViewById(0x7f09016c);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        MVideoInfo mvideoinfo = (MVideoInfo)m_Clip.getProperty(12291);
        if (mvideoinfo != null)
        {
            mVideoWidth = mvideoinfo.get(3);
            mVideoHeight = mvideoinfo.get(4);
        }
        if (mVideoWidth != 0 && mVideoHeight != 0)
        {
            android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)m_SurfaceView.getLayoutParams();
            layoutparams.height = (displaymetrics.widthPixels * mVideoHeight) / mVideoWidth;
            m_SurfaceView.setLayoutParams(layoutparams);
        }
        m_SurfaceHolder = m_SurfaceView.getHolder();
        m_SurfaceHolder.addCallback(this);
        m_PlayerEventHandler = new PlaybackEventHandler(Looper.myLooper());
        m_bInited = true;
    }

    private int ResetPlayer(MSessionStream msessionstream, int i)
    {
        if (msessionstream != null)
        {
            if (m_Player == null)
            {
                UtilFunc.Log("HW", "SetStream m_StreamForSeek_createplayer");
                return CreatePlayer(msessionstream, i);
            }
            boolean flag = m_Player.IsPlaying();
            UtilFunc.Log("HW", "SetStream m_StreamForSeek");
            if (m_Player.SetStream(msessionstream, i) == 0)
            {
                if (flag)
                {
                    DoPlayerPlay();
                }
                return 0;
            }
        }
        return 1;
    }

    private int buildPlayer(int i)
    {
        this;
        JVM INSTR monitorenter ;
        MSessionStream msessionstream;
        releaseStream();
        m_Stream = createStream();
        msessionstream = m_Stream;
        if (msessionstream != null) goto _L2; else goto _L1
_L1:
        int k = 1;
_L4:
        this;
        JVM INSTR monitorexit ;
        return k;
_L2:
        int j = ResetPlayer(m_Stream, i);
        k = j;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private void changeTrimZoomSize(boolean flag)
    {
        if (m_TrimUIOper == null || m_Player == null)
        {
            return;
        }
        if (flag)
        {
            m_TrimUIOper.changeTrimZoomSize(flag);
            return;
        }
        if (m_TrimUIOper.getCurScaleLevel() == m_TrimUIOper.getMinScaleLevel())
        {
            Toast.makeText(this, "current is the min Scale!", 0).show();
            return;
        } else
        {
            m_TrimUIOper.changeTrimZoomSize(flag);
            return;
        }
    }

    private MSessionStream createStream()
    {
        if (m_Clip == null)
        {
            return null;
        }
        int i = RuntimeConfig.OUTPUT_RESOL_WIDTH;
        int j = RuntimeConfig.OUTPUT_RESOL_HEIGHT;
        if (i == 0 && j == 0)
        {
            MVideoInfo mvideoinfo = (MVideoInfo)m_Clip.getProperty(12291);
            if (mvideoinfo != null)
            {
                i = mvideoinfo.get(3);
                j = mvideoinfo.get(4);
            }
        }
        MRange mrange = new MRange(0, m_VideoDuration);
        return UtilFunc.createStream(2, m_Clip, i, j, mrange, null, m_DisplayContext, false);
    }

    private int getClipDuration()
    {
        MClip mclip = m_Clip;
        int i = 0;
        if (mclip != null)
        {
            MRange mrange = (MRange)m_Clip.getProperty(12292);
            i = 0;
            if (mrange != null)
            {
                i = mrange.get(1);
            }
        }
        return i;
    }

    private void initTrimBar()
    {
        this;
        JVM INSTR monitorenter ;
        if (m_TrimUIOper == null)
        {
            m_TrimUIOper = new TrimUIOper(this, m_Clip);
            m_TrimUIOper.setTrimUIOperListener(mTrimUIOperListener);
            m_TrimUIOper.InitRes();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void refreshEnableStartAndEndBtn(boolean flag, boolean flag1)
    {
        m_BtnStart.setEnabled(flag);
        m_BtnEnd.setEnabled(flag1);
        mOldEnabledBtnStart = flag;
        mOldEnabledBtnEnd = flag1;
    }

    private void release()
    {
        cancelDeletingWaitDialog();
        mSeekingHandler = null;
        if (m_SaveDialog != null)
        {
            m_SaveDialog.destroy();
            m_SaveDialog = null;
        }
        if (m_TrimUIOper != null)
        {
            m_TrimUIOper.destroy();
            m_TrimUIOper = null;
        }
        if (m_Player != null)
        {
            m_Player.UnInit();
            m_Player = null;
        }
        if (m_Stream != null)
        {
            m_Stream.close();
            m_Stream = null;
        }
        if (m_Clip != null)
        {
            m_Clip.unInit();
            m_Clip = null;
        }
        AppContext.DestroyInstatce();
    }

    private void releaseStream()
    {
        if (m_Stream != null)
        {
            m_Stream.close();
            m_Stream = null;
        }
    }

    private void saveWithShareOrNot(boolean flag)
    {
        if (m_bHasTrimed)
        {
            if (flag)
            {
                if (UtilFunc.IsFileExisted(m_SharedTempFileName))
                {
                    UtilFunc.shareVideoByUri(Uri.fromFile(new File(m_SharedTempFileName)), this);
                }
                return;
            } else
            {
                Toast.makeText(this, 0x7f0b01bd, 0).show();
                return;
            }
        } else
        {
            m_bNeedShare = flag;
            startSaveTrim();
            return;
        }
    }

    private void setBtnSpreadOrPinchEnable()
    {
        if (m_TrimUIOper.getCurScaleLevel() == m_TrimUIOper.getMinScaleLevel())
        {
            m_BtnPinch.setEnabled(false);
        } else
        {
            m_BtnPinch.setEnabled(true);
        }
        if (m_TrimUIOper.getCurScaleLevel() == m_TrimUIOper.getMaxScaleLevel())
        {
            m_BtnSpread.setEnabled(false);
            return;
        } else
        {
            m_BtnSpread.setEnabled(true);
            return;
        }
    }

    private void setPlayerRange()
    {
        if (m_Player == null || m_Clip == null)
        {
            return;
        } else
        {
            MRange mrange = new MRange(0, m_VideoDuration);
            m_Player.SetPlayRange(mrange);
            return;
        }
    }

    private void setSeekToStatus(boolean flag)
    {
        m_IsSeeking = flag;
        if (m_TrimUIOper != null)
        {
            m_TrimUIOper.setSeekToStatus(flag);
        }
    }

    private void showMenuWindow()
    {
        if (mMenuWindow == null)
        {
            mMenuWindow = new PopupMenuWindow(this);
            mMenuWindow.setWindowParameter(getResources().getDimensionPixelSize(0x7f08001b), getResources().getDimensionPixelSize(0x7f08001a));
            mMenuWindow.setOnMenuClickedListener(m_OnMenuClickedListener);
        }
        if (!mMenuWindow.isShowing())
        {
            mMenuWindow.showPopupMenuWindow(m_BtnMenu, 0, getResources().getInteger(0x7f0a0021));
        }
    }

    private void startSaveTrim()
    {
        if (m_Player != null)
        {
            setPlayerPlay(false);
        }
        if (m_SaveDialog != null)
        {
            m_SaveDialog.destroy();
            m_SaveDialog = null;
        }
        if (mTrimClip == null)
        {
            mTrimClip = new VideoClip();
        }
        if (mTrimClip != null && mTrimLeftValue >= 0 && mTrimLeftValue < mTrimRightValue)
        {
            mTrimClip.mStrFile = m_strSourceFile;
            mTrimClip.mStartPos = mTrimLeftValue;
            mTrimClip.mDuration = mTrimRightValue - mTrimLeftValue;
        }
        m_SaveDialog = new SaveDialog(this, mTrimClip, m_TrimSaveHandler);
        m_SaveDialog.show();
    }

    private void updateTrimUI(int i)
    {
        if (m_TrimUIOper != null)
        {
            m_TrimUIOper.seekToPos(i);
        }
        if (i >= mTrimLeftValue) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag1;
        int k1 = mTrimRightValue - i;
        flag1 = false;
        flag = false;
        if (k1 >= 1000)
        {
            flag = true;
        }
_L4:
        if (mOldEnabledBtnStart != flag || mOldEnabledBtnEnd != flag1)
        {
            refreshEnableStartAndEndBtn(flag, flag1);
        }
        m_TrimTimeText.setText(UtilFunc.TransTimeToString(i, 0));
        return;
_L2:
        if (i > mTrimRightValue)
        {
            int j1 = i - mTrimLeftValue;
            flag1 = false;
            flag = false;
            if (j1 >= 1000)
            {
                flag1 = true;
                flag = false;
            }
        } else
        {
            int j = mTrimLeftValue;
            flag = false;
            if (i != j)
            {
                int i1 = mTrimRightValue - i;
                flag = false;
                if (i1 >= 1000)
                {
                    flag = true;
                }
            }
            int k = mTrimRightValue;
            flag1 = false;
            if (i != k)
            {
                int l = i - mTrimLeftValue;
                flag1 = false;
                if (l >= 1000)
                {
                    flag1 = true;
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void cancelDeletingWaitDialog()
    {
        if (mSeekingWaitDialog != null)
        {
            mSeekingWaitDialog.dismiss();
            mSeekingWaitDialog = null;
        }
    }

    public int getCurPlayTime()
    {
        if (m_Player != null)
        {
            return m_Player.getCurrentTime();
        } else
        {
            return 0;
        }
    }

    public boolean getPlayerStatus()
    {
        if (m_Player == null)
        {
            return false;
        } else
        {
            return m_Player.IsPlaying();
        }
    }

    public void onBackPressed()
    {
        release();
        super.onBackPressed();
    }

    public void onClick(View view)
    {
        if (!m_IsSeeking) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch (view.getId())
        {
        default:
            return;

        case 2131296607: 
            release();
            finish();
            return;

        case 2131296609: 
            showMenuWindow();
            return;

        case 2131296624: 
            setPlayerPlay(true);
            return;

        case 2131296625: 
            setPlayerPlay(false);
            return;

        case 2131296622: 
            if (m_Player != null && !mIsBtnClickListenerProcessing)
            {
                mIsBtnClickListenerProcessing = true;
                setPlayerPlay(false);
                mTrimLeftValue = m_Player.getCurrentTime();
                if (m_TrimUIOper != null)
                {
                    m_TrimUIOper.scrollToTrimLeftPos();
                }
                refreshEnableStartAndEndBtn(false, false);
                m_bHasTrimed = false;
                mIsBtnClickListenerProcessing = false;
                return;
            }
            break;

        case 2131296623: 
            if (m_Player != null && !mIsBtnClickListenerProcessing)
            {
                mIsBtnClickListenerProcessing = true;
                setPlayerPlay(false);
                mTrimRightValue = m_Player.getCurrentTime();
                if (m_TrimUIOper != null)
                {
                    m_TrimUIOper.scrollToTrimRightPos();
                }
                refreshEnableStartAndEndBtn(false, false);
                m_bHasTrimed = false;
                mIsBtnClickListenerProcessing = false;
                return;
            }
            break;

        case 2131296626: 
            changeTrimZoomSize(true);
            return;

        case 2131296627: 
            changeTrimZoomSize(false);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null)
        {
            m_strSourceFile = intent.getStringExtra("INPUTFILENAME");
        }
        if (UtilFunc.IsFileExisted(m_strSourceFile))
        {
            MediaFileUtils mediafileutils = MediaFileUtils.getMediaFileUtils();
            if (mediafileutils.IsVideoFileType(mediafileutils.GetFileMediaType(m_strSourceFile)))
            {
                InitRes();
                return;
            } else
            {
                Toast.makeText(this, 0x7f0b01bb, 0).show();
                finish();
                return;
            }
        } else
        {
            Toast.makeText(this, 0x7f0b01bc, 0).show();
            finish();
            return;
        }
    }

    protected void onDestroy()
    {
        if (m_ThumbManagerList != null)
        {
            m_ThumbManagerList.RecycleAllBitmap();
            m_ThumbManagerList = null;
        }
        super.onDestroy();
    }

    protected void onPause()
    {
        if (m_Player != null)
        {
            mSeekPos = m_Player.getCurrentTime();
            setPlayerPlay(false);
        }
        super.onPause();
    }

    public void setPlayerPlay(boolean flag)
    {
        if (m_Player == null)
        {
            return;
        }
        if (flag)
        {
            if (!m_Player.IsPlaying())
            {
                m_Player.Play();
            }
            m_BtnPlay.setVisibility(4);
            m_BtnPause.setVisibility(0);
            return;
        }
        if (m_Player.IsPlaying())
        {
            m_Player.Pause();
        }
        m_BtnPlay.setVisibility(0);
        m_BtnPause.setVisibility(4);
    }

    public void showDeletingWaitDialog()
    {
        cancelDeletingWaitDialog();
        if (mSeekingWaitDialog == null)
        {
            mSeekingWaitDialog = new LoadingDialog(this, 0x7f0d0004);
            mSeekingWaitDialog.setText(0x7f0b01cc);
            mSeekingWaitDialog.setCancelable(false);
        }
        try
        {
            mSeekingWaitDialog.show();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        InitDisplayContext();
        if (!m_bBuildPlayer)
        {
            buildPlayer(0);
            initTrimBar();
            m_bBuildPlayer = true;
        }
        if (m_Player == null || surfaceholder == null)
        {
            m_bBuildPlayer = false;
        } else
        {
            boolean flag = m_Player.IsPlaying();
            if (flag)
            {
                m_Player.Pause();
            }
            if (m_Player.SetDisplayContext(m_DisplayContext, surfaceholder.getSurface()) == 0)
            {
                m_Player.RefreshDisplay();
                if (flag)
                {
                    DoPlayerPlay();
                    return;
                }
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        if (m_Player == null)
        {
            return;
        } else
        {
            m_Player.Pause();
            m_Player.SetDisplayContext(m_DisplayContext, null);
            return;
        }
    }

    static 
    {
        int i;
        try
        {
            System.loadLibrary("arcveplatform");
            System.loadLibrary("veadkutils");
            System.loadLibrary("veamcm");
            System.loadLibrary("powermobiautils");
            i = android.os.Build.VERSION.SDK_INT;
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
            return;
        }
        if (i >= 10) goto _L2; else goto _L1
_L1:
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_2_2.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
_L3:
        System.load("/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
        System.loadLibrary("powermobiamoviestory");
        return;
_L2:
        if (i >= 14)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_2_3.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
          goto _L3
        if (i >= 16)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_4_0.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
          goto _L3
        if (i >= 17)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_4_1.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
          goto _L3
        if (i >= 19)
        {
            break MISSING_BLOCK_LABEL_213;
        }
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_4_2.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
          goto _L3
        FileUtils.CopyFile((new StringBuilder()).append("/data/data/com.MUVI.MediaPlus/lib/").append("libpowermobiaplatform_4_4.so").toString(), "/data/data/com.MUVI.MediaPlus/libpowermobiaplatform.so");
          goto _L3
    }





/*
    static int access$1002(videoTrimActivity videotrimactivity, int i)
    {
        videotrimactivity.mTrimLeftValue = i;
        return i;
    }

*/



/*
    static int access$1102(videoTrimActivity videotrimactivity, int i)
    {
        videotrimactivity.mTrimRightValue = i;
        return i;
    }

*/






/*
    static ThumbManagerList access$1602(videoTrimActivity videotrimactivity, ThumbManagerList thumbmanagerlist)
    {
        videotrimactivity.m_ThumbManagerList = thumbmanagerlist;
        return thumbmanagerlist;
    }

*/



/*
    static String access$202(videoTrimActivity videotrimactivity, String s)
    {
        videotrimactivity.m_SharedTempFileName = s;
        return s;
    }

*/



/*
    static boolean access$402(videoTrimActivity videotrimactivity, boolean flag)
    {
        videotrimactivity.m_bHasTrimed = flag;
        return flag;
    }

*/





}
