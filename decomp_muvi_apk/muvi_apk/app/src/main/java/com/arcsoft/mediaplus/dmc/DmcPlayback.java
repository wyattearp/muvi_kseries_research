// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.datasource.DMCDataSource;
import com.arcsoft.mediaplus.datasource.DataSourceFactory;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.playview.DataSourcePlayList;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            IDmcPlaylistUpdater, DmcPlayingDataProvider, DmcCacheManager, RenderDevSelector, 
//            DmcPlayerEx, DmcPlayingListAdapter

public class DmcPlayback extends Activity
    implements IDmcPlaylistUpdater, com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener
{
    private class PlaybackHandler extends Handler
    {

        private long mBufferBasedTimer;
        private long mCurrentProgress;
        private long mCurrentSysTime;
        private int mIndex;
        private boolean mIsTimerRunning;
        private long mTimerBasedTime;
        final DmcPlayback this$0;

        public void handleMessage(Message message)
        {
            mCurrentSysTime = System.currentTimeMillis();
            message.what;
            JVM INSTR lookupswitch 6: default 68
        //                       3: 69
        //                       4: 403
        //                       9: 422
        //                       10: 307
        //                       11: 348
        //                       22: 430;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L4:
            break MISSING_BLOCK_LABEL_422;
_L1:
            break; /* Loop/switch isn't completed */
_L7:
            break MISSING_BLOCK_LABEL_430;
_L8:
            return;
_L2:
            if (message.arg1 == 2)
            {
                mIndex = message.arg2;
                mCurrentProgress = 0L;
                mTimerBasedTime = mCurrentSysTime;
                refreshTotal(5000L);
                mCurrentMaxOfSeekbar = 5000;
                mSeekBar.setMax(mCurrentMaxOfSeekbar);
                mSeekBar.setEnabled(false);
                mPlayBtn.setEnabled(false);
                refreshCurrent(0L);
                mIsTimerRunning = false;
                sendEmptyMessageDelayed(3, 50L);
                return;
            }
            if (mCurrentProgress >= 5000L)
            {
                removeMessages(3);
                refreshTotal(0L);
                refreshCurrent(0L);
                mIsTimerRunning = false;
                if (mListener != null)
                {
                    mListener.onCompleted();
                    return;
                }
            } else
            {
                mIsTimerRunning = true;
                mCurrentProgress = mCurrentSysTime - mTimerBasedTime;
                Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying progress: ").append(mCurrentProgress).toString());
                refreshCurrent(mCurrentProgress);
                sendEmptyMessageDelayed(3, 50L);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if (mIsTimerRunning)
            {
                Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying pause: ").append(mCurrentProgress).toString());
                removeMessages(3);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            if (mIsTimerRunning)
            {
                Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying resume: ").append(mCurrentProgress).toString());
                mTimerBasedTime = mCurrentSysTime - mCurrentProgress;
                sendEmptyMessage(3);
                return;
            }
            if (true) goto _L8; else goto _L3
_L3:
            refreshPlayingItem(message.arg1, (DmcUtils.COVER_TYPE)message.obj);
            return;
            finish();
            return;
            if (-1L == mBufferBasedTimer)
            {
                mBufferBasedTimer = mCurrentSysTime;
                sendEmptyMessageDelayed(22, 50L);
                Log.d("Dmc", "BufferTimer init");
                return;
            }
            if (mCurrentSysTime - mBufferBasedTimer >= 40000L)
            {
                removeMessages(22);
                mBufferBasedTimer = -1L;
                showBufferTimeoutDlg();
                Log.d("Dmc", "BufferTimer timeout");
                return;
            } else
            {
                Log.d("Dmc", (new StringBuilder()).append("BufferTimer progress = ").append(mCurrentSysTime - mBufferBasedTimer).toString());
                sendEmptyMessageDelayed(22, 50L);
                return;
            }
        }

        public boolean isTimingIndex(int i)
        {
            return mIndex == i && mIsTimerRunning;
        }

        public void resetBufferTimer()
        {
            mBufferBasedTimer = -1L;
        }

        private PlaybackHandler()
        {
            this$0 = DmcPlayback.this;
            super();
            mCurrentProgress = 0L;
            mIsTimerRunning = false;
            mTimerBasedTime = 0L;
            mCurrentSysTime = 0L;
            mIndex = 0;
            mBufferBasedTimer = -1L;
        }

    }


    private final boolean PAUSE_BY_HOME_LOCK = false;
    private final String TAG = "Dmc";
    private ImageView mBackBtn;
    private AlertDialog mBufferTimeoutDlg;
    private DmcCacheManager mCacheManager;
    protected int mCurrentMaxOfSeekbar;
    private TextView mCurrentTime;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

        final DmcPlayback this$0;

        public void onCountChanged(int i, int j)
        {
            Log.v("zdf", (new StringBuilder()).append("**** [DmcPlayback] onCountChanged: oldCount ").append(j).append(" newCount ").append(i).toString());
            if (mPlaylistAdpter != null)
            {
                mPlaylistAdpter.notifyDataSetChanged();
            }
        }

        public void onDataChanged(int i, Property property)
        {
        }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
    };
    private DmcPlayingDataProvider mDataProvider;
    protected IDataSource mDataSource;
    private TextView mDeviceDisp;
    private String mDeviceName;
    private String mDeviceUdn;
    private final com.arcsoft.adk.atv.DLNA.IDlnaFileServer mFileServerListener = new com.arcsoft.adk.atv.DLNA.IDlnaFileServer() {

        final DmcPlayback this$0;

        public void closeFileServerFailed()
        {
        }

        public void closeFileServerSucess()
        {
        }

        public void enableFileServerSuccess()
        {
            toastXXX(20);
        }

        public void enalbleFileServerFailed()
        {
            toastXXX(21);
        }

        public void fileServerDismiss()
        {
            toastXXX(19);
        }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
    };
    private com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter mFilter;
    protected PlaybackHandler mHandler;
    protected android.widget.AdapterView.OnItemClickListener mItemClickListener;
    protected com.arcsoft.mediaplus.playengine.IPlayer.IPlayerListener mListener;
    private ImageButton mNextBtn;
    protected android.view.View.OnClickListener mOnClickListener;
    private final android.widget.AbsListView.OnScrollListener mOnScrollListener = new android.widget.AbsListView.OnScrollListener() {

        final DmcPlayback this$0;

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
        }

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
            if (i == 0)
            {
                if (abslistview == null);
            }
        }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
    };
    protected boolean mPausedByManualOper;
    private ImageButton mPlayBtn;
    private DmcPlayerEx mPlayer;
    private TextView mPlayingContent;
    private TextView mPlayingDevice;
    private int mPlayingIndex;
    private ListView mPlayingList;
    private ImageView mPlayingThumbnail;
    private DmcPlayingListAdapter mPlaylistAdpter;
    private ImageButton mPrevBtn;
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;
    protected android.widget.SeekBar.OnSeekBarChangeListener mSeekBarChangeListener;
    private ImageView mSwitcherBtn;
    private long mTotalPlayTime;
    private TextView mTotalTime;
    private LinearLayout mTvPlayingView;
    private DmcUtils.TV_STATUS mTvStatus;

    public DmcPlayback()
    {
        mSwitcherBtn = null;
        mTvPlayingView = null;
        mPlayingList = null;
        mBackBtn = null;
        mPlayingThumbnail = null;
        mPlayingContent = null;
        mPlayingDevice = null;
        mDeviceDisp = null;
        mProgressBar = null;
        mPlaylistAdpter = null;
        mCacheManager = null;
        mCurrentTime = null;
        mTotalTime = null;
        mPrevBtn = null;
        mNextBtn = null;
        mPlayBtn = null;
        mSeekBar = null;
        mTvStatus = DmcUtils.TV_STATUS.TV_PLAY_STARTUS_PLAYING;
        mDataSource = null;
        mDeviceName = null;
        mDeviceUdn = null;
        mPlayer = null;
        mDataProvider = null;
        mFilter = null;
        mPlayingIndex = 0;
        mCurrentMaxOfSeekbar = 0;
        mPausedByManualOper = false;
        mTotalPlayTime = 0L;
        mHandler = null;
        mListener = new com.arcsoft.mediaplus.playengine.IPlayer.IPlayerListener() {

            final DmcPlayback this$0;

            public void onBuffering()
            {
                Log.d("Dmc", "onBuffering");
                startBufferingTimer();
                refreshPlayBtn(true);
                refreshLoadingDlg(true);
            }

            public void onCompleted()
            {
                Log.d("Dmc", "onCompleted");
                resetController();
                if (mDataProvider != null && !mDataProvider.isPlayOver() && mPlayer != null)
                {
                    refreshPlayBtn(false);
                    mPlayer.playnext();
                } else
                {
                    stopPlayer();
                    mPlayingIndex = 0;
                    refreshPrevNextBtn(true);
                    refreshCover(0, true);
                    refreshPlayBtn(false, true);
                    refreshPlayingContent(false);
                    if (mDataProvider != null)
                    {
                        mDataProvider.resetPlayingInfo();
                        return;
                    }
                }
            }

            public void onError(com.arcsoft.mediaplus.playengine.IPlayer.PlayerError playererror)
            {
                Log.d("Dmc", (new StringBuilder()).append("onError\uFF1A code = ").append(playererror.name()).toString());
                static class _cls10
                {

                    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[];

                    static 
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError = new int[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_RENDER_DISCONNECTED.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_UNSUPPORT.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_OPENFILE.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_PLAY.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_PLAYLIST_URI_NULL.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror4)
                        {
                            return;
                        }
                    }
                }

                switch (_cls10..SwitchMap.com.arcsoft.mediaplus.playengine.IPlayer.PlayerError[playererror.ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    if (mHandler != null)
                    {
                        mHandler.sendEmptyMessage(9);
                    }
                    toastXXX(14);
                    return;

                case 2: // '\002'
                    playNextAuto();
                    toastXXX(15);
                    return;

                case 3: // '\003'
                    playNextAuto();
                    toastXXX(17);
                    return;

                case 4: // '\004'
                    playNextAuto();
                    toastXXX(16);
                    // fall through

                case 5: // '\005'
                    playNextAuto();
                    toastXXX(18);
                    return;
                }
            }

            public void onMute(boolean flag)
            {
            }

            public void onPaused()
            {
                mPausedByManualOper = true;
                refreshPlayBtn(false);
                pauseImageDispTimer();
                Log.d("Dmc", "onPaused");
            }

            public void onPlayIndexChanged(int i)
            {
                Log.d("Dmc", (new StringBuilder()).append("onPlayIndexChanged index = ").append(i).toString());
                mPlayingIndex = i;
                stopImageDispTimer();
                refreshPrevNextBtn(false);
            }

            public void onPlayStarted()
            {
                Log.d("Dmc", "onPlayStarted");
                hideBufferTimeoutDlg();
                refreshPlayingContent(true);
                refreshCover(mPlayingIndex, false);
                refreshPlayBtn(false);
                refreshLoadingDlg(false);
                startImageDispTimer();
                mPausedByManualOper = false;
            }

            public void onProgressChanged(long l, long l1)
            {
                Log.d("Dmc", (new StringBuilder()).append("onProgressChanged\uFF1Aduration = ").append(l).append(" position = ").append(l1).toString());
                if (0L != l && 0L != l1)
                {
                    mSeekBar.setEnabled(true);
                    if ((long)mCurrentMaxOfSeekbar != l)
                    {
                        mCurrentMaxOfSeekbar = (int)l;
                        mSeekBar.setMax(mCurrentMaxOfSeekbar);
                    }
                    refreshCurrent(l1);
                    refreshTotal(l);
                }
            }

            public void onSeeked()
            {
                Log.d("Dmc", "onSeeked");
            }

            public void onStopped()
            {
                stopImageDispTimer();
                stopBufferingTimer();
                Log.d("Dmc", "onStopped");
                refreshLoadingDlg(false);
            }

            public void onVolumeChanged(int i)
            {
                Log.d("Dmc", (new StringBuilder()).append("onVolumeChanged: volume = ").append(i).toString());
            }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
        };
        mBufferTimeoutDlg = null;
        mSeekBarChangeListener = new android.widget.SeekBar.OnSeekBarChangeListener() {

            final DmcPlayback this$0;

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                if (mPlayer != null)
                {
                    mPlayer.seekTo(i);
                }
                refreshCurrent(i);
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
                if (mPlayer != null)
                {
                    mPlayer.startSeek();
                }
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                if (mPlayer != null)
                {
                    mPlayer.endSeek();
                }
            }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
        };
        mItemClickListener = new android.widget.AdapterView.OnItemClickListener() {

            final DmcPlayback this$0;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                playFromIndex(i);
            }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
        };
        mOnClickListener = new android.view.View.OnClickListener() {

            final DmcPlayback this$0;

            public void onClick(View view)
            {
                view.getId();
                JVM INSTR tableswitch 2131296345 2131296352: default 52
            //                           2131296345 224
            //                           2131296346 97
            //                           2131296347 253
            //                           2131296348 89
            //                           2131296349 52
            //                           2131296350 52
            //                           2131296351 52
            //                           2131296352 53;
                   goto _L1 _L2 _L3 _L4 _L5 _L1 _L1 _L1 _L6
_L1:
                return;
_L6:
                DmcPlayback dmcplayback = DmcPlayback.this;
                boolean flag;
                if (mTvStatus != DmcUtils.TV_STATUS.TV_PLAY_STARTUS_PLAYING)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                dmcplayback.ctlTv(flag);
                return;
_L5:
                finish();
                return;
_L3:
                if (mPlayer.getStatus() == com.arcsoft.mediaplus.playengine.IPlayer.Status.STATUS_STOPPED || mPlayer.getStatus() == com.arcsoft.mediaplus.playengine.IPlayer.Status.STATUS_IDLE || mPlayer.getStatus() == com.arcsoft.mediaplus.playengine.IPlayer.Status.STATUS_STOPPING)
                {
                    mPlayer.play(mDataProvider.getCurrentIndex(), 0L, com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_FADE);
                    return;
                }
                if (mPlayer.getStatus() == com.arcsoft.mediaplus.playengine.IPlayer.Status.STATUS_PAUSED)
                {
                    mPlayer.resume();
                    return;
                }
                if (mPlayer.canPause())
                {
                    mPlayer.pause();
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L2:
                if (mPlayer != null)
                {
                    resetController();
                    mPlayer.playprev();
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L4:
                if (mPlayer != null)
                {
                    resetController();
                    mPlayer.playnext();
                    return;
                }
                if (true) goto _L1; else goto _L7
_L7:
            }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
        };
    }

    private void hideBufferTimeoutDlg()
    {
        if (mBufferTimeoutDlg != null && mBufferTimeoutDlg.isShowing())
        {
            mBufferTimeoutDlg.dismiss();
        }
        stopBufferingTimer();
    }

    private void refreshLoadingDlg(boolean flag)
    {
        if (mProgressBar != null)
        {
            ProgressBar progressbar = mProgressBar;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 4;
            }
            progressbar.setVisibility(i);
        }
    }

    private void refreshPlayingContent(boolean flag)
    {
        if (mDataProvider != null && mPlayingContent != null)
        {
            TextView textview = mPlayingContent;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 4;
            }
            textview.setVisibility(i);
            if (flag)
            {
                mPlayingContent.setText(mDataProvider.getMediaName(mPlayingIndex));
            }
        }
        refreshPlayingDeviceName(flag);
    }

    private void refreshPlayingDeviceName(boolean flag)
    {
        if (mPlayingDevice != null)
        {
            TextView textview = mPlayingDevice;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 4;
            }
            textview.setVisibility(i);
        }
        if (flag)
        {
            mPlayingDevice.setText((new StringBuilder()).append(getResources().getString(0x7f0b01d1)).append(" ").append(mDeviceName).toString());
        }
    }

    private void release()
    {
        if (mPlaylistAdpter != null)
        {
            mPlaylistAdpter = null;
        }
        if (mDataProvider != null)
        {
            mDataProvider.destroy();
            mDataProvider = null;
        }
        if (mCacheManager != null)
        {
            mCacheManager.destroy();
            mCacheManager = null;
        }
        stopImageDispTimer();
        stopBufferingTimer();
        if (mDataSource != null)
        {
            releaseDataSource(mDataSource);
            mDataSource = null;
        }
        if (mHandler != null)
        {
            mHandler = null;
        }
        Settings.instance().unregisterSettingChangeListener(this);
        DLNA.instance().unRegisterDlnaFileServerListener();
        releaseUI();
    }

    private void releaseUI()
    {
        if (mSwitcherBtn != null)
        {
            mSwitcherBtn = null;
        }
        if (mTvPlayingView != null)
        {
            mTvPlayingView = null;
        }
        if (mPlayingList != null)
        {
            mPlayingList = null;
        }
        if (mBackBtn != null)
        {
            mBackBtn = null;
        }
        if (mPlayingThumbnail != null)
        {
            mPlayingThumbnail = null;
        }
        if (mPlayingContent != null)
        {
            mPlayingContent = null;
        }
        if (mPlayingDevice != null)
        {
            mPlayingDevice = null;
        }
        if (mDeviceName != null)
        {
            mDeviceName = null;
        }
        if (mCurrentTime != null)
        {
            mCurrentTime = null;
        }
        if (mTotalTime != null)
        {
            mTotalTime = null;
        }
        if (mPrevBtn != null)
        {
            mPrevBtn = null;
        }
        if (mNextBtn != null)
        {
            mNextBtn = null;
        }
        if (mPlayBtn != null)
        {
            mPlayBtn = null;
        }
        if (mSeekBar != null)
        {
            mSeekBar = null;
        }
        if (mProgressBar != null)
        {
            mProgressBar = null;
        }
        if (mBufferTimeoutDlg != null)
        {
            mBufferTimeoutDlg.dismiss();
            mBufferTimeoutDlg = null;
        }
    }

    private void resetController()
    {
        stopImageDispTimer();
        stopBufferingTimer();
        if (mSeekBar != null)
        {
            mCurrentMaxOfSeekbar = 0;
            mSeekBar.setMax(mCurrentMaxOfSeekbar);
            mSeekBar.setEnabled(false);
        }
        refreshCurrent(0L);
        refreshTotal(0L);
    }

    private void showBufferTimeoutDlg()
    {
        if (mBufferTimeoutDlg == null)
        {
            mBufferTimeoutDlg = (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b01e8).setPositiveButton(getString(0x7f0b001f), new android.content.DialogInterface.OnClickListener() {

                final DmcPlayback this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    stopPlayer();
                    playFromIndex(mPlayingIndex);
                    resetController();
                }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
            }).setNegativeButton(getString(0x7f0b01ea), new android.content.DialogInterface.OnClickListener() {

                final DmcPlayback this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    if (mHandler != null)
                    {
                        mHandler.sendEmptyMessage(9);
                    }
                }

            
            {
                this$0 = DmcPlayback.this;
                super();
            }
            }).create();
        }
        mBufferTimeoutDlg.show();
    }

    private void startBufferingTimer()
    {
        if (mHandler != null)
        {
            mHandler.sendEmptyMessage(22);
        }
    }

    private void startImageDispTimer()
    {
        if (mDataProvider != null && mHandler != null && mPlayingIndex >= 0 && mPlayingIndex < mDataProvider.getCount())
        {
            mHandler.removeMessages(3);
            if (mHandler.isTimingIndex(mPlayingIndex))
            {
                resumeImageDispTimer();
                Log.d("Dmc", "startImageDispTimer resume ");
                return;
            }
            if (!mDataProvider.isVideoFile(mPlayingIndex))
            {
                Message message = new Message();
                message.what = 3;
                message.arg1 = 2;
                message.arg2 = mPlayingIndex;
                mHandler.sendMessage(message);
                Log.d("Dmc", "startImageDispTimer init ");
                return;
            }
        }
    }

    private void stopBufferingTimer()
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(22);
            mHandler.resetBufferTimer();
        }
    }

    public void OnDefaultDownloadDestinationChanged(String s)
    {
    }

    public void OnDefaultRenderChanged(String s)
    {
    }

    public void OnDefaultServerChanged(String s)
    {
        if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
        {
            finish();
        }
    }

    protected void createPlayer()
    {
        if (mPlayer == null)
        {
            mPlayer = new DmcPlayerEx();
        }
        mPlayer.init();
        mPlayer.setRender(mDeviceUdn);
        mPlayer.setDataProvider(mDataProvider);
        mPlayer.setPlayerListener(mListener);
        playFromIndex(mPlayingIndex);
    }

    protected void ctlTv(boolean flag)
    {
        byte byte0 = 8;
        DmcUtils.TV_STATUS tv_status;
        if (mTvPlayingView != null)
        {
            LinearLayout linearlayout = mTvPlayingView;
            ListView listview;
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = byte0;
            }
            linearlayout.setVisibility(i);
        }
        if (mPlayingList != null)
        {
            listview = mPlayingList;
            if (!flag)
            {
                byte0 = 0;
            }
            listview.setVisibility(byte0);
        }
        if (flag)
        {
            tv_status = DmcUtils.TV_STATUS.TV_PLAY_STARTUS_PLAYING;
        } else
        {
            tv_status = DmcUtils.TV_STATUS.TV_PLAY_STARTUS_PLAYLIST;
        }
        mTvStatus = tv_status;
        refreshSwitchBtn();
    }

    protected void destoryPlayList(DataSourcePlayList datasourceplaylist)
    {
        DMCDataSource dmcdatasource;
        if (datasourceplaylist != null)
        {
            if ((dmcdatasource = (DMCDataSource)datasourceplaylist.getDataSource()) != null)
            {
                dmcdatasource.unInit();
                return;
            }
        }
    }

    protected void destroyPlayer()
    {
        stopPlayer();
        if (mPlayer != null)
        {
            mPlayer.uninit();
            mPlayer = null;
        }
    }

    protected IDataSource getDataSource()
        throws IllegalStateException
    {
        Intent intent = getIntent();
        if (intent != null)
        {
            mFilter = (com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter)intent.getParcelableExtra("datasource_filter");
        }
        if (mFilter == null)
        {
            throw new IllegalStateException("Only Support Use filter yet");
        } else
        {
            return DataSourceFactory.instance().getDataSource(mFilter);
        }
    }

    protected void loadUIRes()
    {
        mSwitcherBtn = (ImageView)findViewById(0x7f090060);
        mPlayingList = (ListView)findViewById(0x7f09004f);
        mTvPlayingView = (LinearLayout)findViewById(0x7f090050);
        mPlayingContent = (TextView)findViewById(0x7f090053);
        mPlayingDevice = (TextView)findViewById(0x7f090054);
        mBackBtn = (ImageView)findViewById(0x7f09005c);
        mCurrentTime = (TextView)findViewById(0x7f090056);
        mTotalTime = (TextView)findViewById(0x7f090058);
        mSeekBar = (SeekBar)findViewById(0x7f090057);
        mPlayingThumbnail = (ImageView)findViewById(0x7f090051);
        mPrevBtn = (ImageButton)findViewById(0x7f090059);
        mNextBtn = (ImageButton)findViewById(0x7f09005b);
        mPlayBtn = (ImageButton)findViewById(0x7f09005a);
        mDeviceDisp = (TextView)findViewById(0x7f09005e);
        mProgressBar = (ProgressBar)findViewById(0x7f090052);
        mPlaylistAdpter = new DmcPlayingListAdapter(this, mCacheManager, mDataProvider);
        if (mPlayingList != null)
        {
            mPlayingList.setAdapter(mPlaylistAdpter);
            mPlayingList.setOnItemClickListener(mItemClickListener);
            mPlayingList.setOnScrollListener(mOnScrollListener);
        }
        if (mSwitcherBtn != null)
        {
            mSwitcherBtn.setOnClickListener(mOnClickListener);
        }
        if (mBackBtn != null)
        {
            mBackBtn.setOnClickListener(mOnClickListener);
        }
        refreshPlayingContent(false);
        if (mSeekBar != null)
        {
            mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
        }
        if (mPlayBtn != null)
        {
            mPlayBtn.setOnClickListener(mOnClickListener);
            refreshPlayBtn(true);
        }
        if (mPrevBtn != null)
        {
            mPrevBtn.setOnClickListener(mOnClickListener);
        }
        if (mNextBtn != null)
        {
            mNextBtn.setOnClickListener(mOnClickListener);
        }
        if (mDeviceDisp != null)
        {
            mDeviceDisp.setText(mDeviceName);
        }
        refreshPlayingDeviceName(false);
        refreshCover(mPlayingIndex, true);
    }

    public void onBrowseModeChanged(boolean flag)
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000f);
        mDataSource = getDataSource();
        if (mDataSource != null)
        {
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSource.getController();
            if (icontroller != null)
            {
                icontroller.loadMore(-1);
                icontroller.release();
            }
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
        }
        DmcPlayingDataProvider dmcplayingdataprovider;
        Intent intent;
        if (RenderDevSelector.CURRENT_PROVIDER_TYPE == DmcUtils.PROVIDER_TYPE.TYPE_FROM_MULTI_VIEW)
        {
            dmcplayingdataprovider = new DmcPlayingDataProvider(ListViewManager.getSelectedItemListEx(), mDataSource, this);
        } else
        {
            dmcplayingdataprovider = new DmcPlayingDataProvider(mDataSource, this);
        }
        mDataProvider = dmcplayingdataprovider;
        mCacheManager = new DmcCacheManager(this, mDataProvider, mDataSource);
        mCacheManager.setPlaylistUpdater(this);
        Settings.instance().registerSettingChangeListener(this);
        intent = getIntent();
        if (intent != null)
        {
            mDeviceName = intent.getStringExtra("default_render_name");
            mDeviceUdn = intent.getStringExtra("default_render_udn");
            mPlayingIndex = intent.getIntExtra("play_from_index", 0);
        }
        mHandler = new PlaybackHandler();
        loadUIRes();
        resetController();
        createPlayer();
        DLNA.instance().registerDlnaFileServerListener(mFileServerListener);
        if (!DLNA.instance().canDmcWork())
        {
            toastXXX(19);
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        destroyPlayer();
        release();
    }

    protected void onPause()
    {
        super.onPause();
        if (mPlayer == null);
    }

    protected void onResume()
    {
        super.onResume();
        if (mPlayer == null);
    }

    public void onSortModeChanged(boolean flag)
    {
    }

    public void onTVStreamingResolutionChange(boolean flag)
    {
    }

    protected void pauseImageDispTimer()
    {
        if (mHandler != null)
        {
            Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying pause playing index ").append(mPlayingIndex).toString());
            if (mDataProvider != null && !mDataProvider.isVideoFile(mPlayingIndex))
            {
                mHandler.sendEmptyMessage(10);
            }
        }
    }

    protected void playFromIndex(int i)
    {
        if (mDataProvider != null && i < mDataProvider.getCount())
        {
            mDataProvider.setCurrentIndex(i, false);
            mPlayingIndex = i;
            mPlayer.play(i, 0L);
            resetController();
            refreshPrevNextBtn(false);
        }
    }

    protected void playNextAuto()
    {
        if (mPlayer != null)
        {
            resetController();
            mPlayer.playnext();
        }
    }

    protected void refreshCover(int i, boolean flag)
    {
        if (mPlayingThumbnail != null && mCacheManager != null)
        {
            ImageView imageview = mPlayingThumbnail;
            DmcCacheManager dmccachemanager = mCacheManager;
            if (flag)
            {
                i = -1;
            }
            imageview.setImageBitmap(dmccachemanager.getTvCover(i));
        }
    }

    protected void refreshCurrent(long l)
    {
        if (mCurrentTime != null)
        {
            mCurrentTime.setText(TimeUtils.convertSecToTimeString(l / 1000L, false));
        }
        if (mSeekBar != null)
        {
            mSeekBar.setProgress((int)l);
        }
        Log.d("Dmc", (new StringBuilder()).append("SeekBar msec = ").append(l).append(" max = ").append(mSeekBar.getMax()).toString());
    }

    protected void refreshPlayBtn(boolean flag)
    {
        refreshPlayBtn(flag, false);
    }

    protected void refreshPlayBtn(boolean flag, boolean flag1)
    {
        int i = 0x7f020063;
        if (mPlayBtn == null)
        {
            return;
        }
        Log.d("Dmc", (new StringBuilder()).append("refreshPlayBtn isWaiting = ").append(flag).toString());
        if (mDataProvider != null && mDataProvider.isVideoFile(mPlayingIndex) && !flag)
        {
            ImageButton imagebutton = mPlayBtn;
            Resources resources = getResources();
            if (mPlayer.canPause())
            {
                i = 0x7f020062;
            }
            imagebutton.setBackgroundDrawable(resources.getDrawable(i));
            mPlayBtn.setEnabled(true);
            Log.d("Dmc", (new StringBuilder()).append("refreshPlayBtn canPause = ").append(mPlayer.canPause()).toString());
            return;
        }
        if (flag1)
        {
            mPlayBtn.setBackgroundDrawable(getResources().getDrawable(i));
            mPlayBtn.setEnabled(true);
            Log.d("Dmc", "refreshPlayBtn setEnabled  false ");
            return;
        } else
        {
            mPlayBtn.setBackgroundDrawable(getResources().getDrawable(i));
            mPlayBtn.setEnabled(false);
            Log.d("Dmc", "refreshPlayBtn setEnabled  false ");
            return;
        }
    }

    public void refreshPlayingItem(int i, DmcUtils.COVER_TYPE cover_type)
    {
        if (mPlayingList != null && mPlaylistAdpter != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        int k;
        if (cover_type != DmcUtils.COVER_TYPE.TYPE_THUMBNAIL || mPlayingList.getVisibility() != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        j = mPlayingList.getFirstVisiblePosition();
        k = mPlayingList.getLastVisiblePosition();
        if (i < j || i > k) goto _L1; else goto _L3
_L3:
        View view = mPlayingList.getChildAt(i - j);
        mPlaylistAdpter.refreshCover(view, i);
        Log.d("Dmc", (new StringBuilder()).append("notifyListDecodedIndex :index = ").append(i).append(" begin = ").append(j).append(" end = ").append(k).toString());
        return;
        if (cover_type != DmcUtils.COVER_TYPE.TYPE_TV_COVER || i != mPlayingIndex) goto _L1; else goto _L4
_L4:
        refreshCover(i, false);
        return;
    }

    protected void refreshPrevNextBtn(boolean flag)
    {
        boolean flag1 = true;
        if (mPrevBtn == null || mNextBtn == null || mDataProvider == null || mDataProvider.getCount() <= 0)
        {
            return;
        }
        ImageButton imagebutton = mPrevBtn;
        boolean flag2;
        ImageButton imagebutton1;
        if (!flag && mDataProvider.hasPrev())
        {
            flag2 = flag1;
        } else
        {
            flag2 = false;
        }
        imagebutton.setEnabled(flag2);
        imagebutton1 = mNextBtn;
        if (flag || !mDataProvider.hasNext())
        {
            flag1 = false;
        }
        imagebutton1.setEnabled(flag1);
    }

    protected void refreshSwitchBtn()
    {
label0:
        {
            if (mSwitcherBtn != null)
            {
                if (mTvStatus != DmcUtils.TV_STATUS.TV_PLAY_STARTUS_PLAYING)
                {
                    break label0;
                }
                mSwitcherBtn.setBackgroundDrawable(getResources().getDrawable(0x7f020067));
            }
            return;
        }
        mSwitcherBtn.setBackgroundDrawable(getResources().getDrawable(0x7f020066));
    }

    protected void refreshTotal(long l)
    {
        if (l != mTotalPlayTime)
        {
            mTotalPlayTime = l;
            mTotalTime.setText(TimeUtils.convertSecToTimeString((999L + mTotalPlayTime) / 1000L, false));
        }
    }

    protected void releaseDataSource(IDataSource idatasource)
    {
        DataSourceFactory.instance().releaseDataSource(idatasource);
    }

    protected void resumeImageDispTimer()
    {
        if (mHandler != null)
        {
            Log.d("Dmc", (new StringBuilder()).append("ImageDisplaying resume playing index ").append(mPlayingIndex).toString());
            if (mDataProvider != null && !mDataProvider.isVideoFile(mPlayingIndex))
            {
                mHandler.sendEmptyMessage(11);
            }
        }
    }

    protected void stopImageDispTimer()
    {
        if (mHandler != null)
        {
            mHandler.removeMessages(3);
        }
    }

    protected void stopPlayer()
    {
        if (mPlayer != null && mPlayer.getStatus() != com.arcsoft.mediaplus.playengine.IPlayer.Status.STATUS_IDLE)
        {
            mPlayer.stop(com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_STOP_PLAY_FADE);
        }
    }

    protected void toastXXX(int i)
    {
        String s;
        boolean flag;
        s = null;
        flag = false;
        i;
        JVM INSTR tableswitch 12 22: default 64
    //                   12 124
    //                   13 137
    //                   14 173
    //                   15 209
    //                   16 235
    //                   17 222
    //                   18 248
    //                   19 261
    //                   20 274
    //                   21 287
    //                   22 300;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
        if (s != null)
        {
            if (flag && mDataProvider != null && mDataProvider.hasNext())
            {
                s = (new StringBuilder()).append(s).append(getString(0x7f0b01e2)).toString();
            }
            Toast.makeText(this, s, 0).show();
        }
        return;
_L2:
        s = getString(0x7f0b01d0);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L3:
        String s2 = getString(0x7f0b0018);
        Object aobj1[] = new Object[1];
        aobj1[0] = mDeviceName;
        s = String.format(s2, aobj1);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L4:
        String s1 = getString(0x7f0b0017);
        Object aobj[] = new Object[1];
        aobj[0] = mDeviceName;
        s = String.format(s1, aobj);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L5:
        s = getString(0x7f0b01e6);
        flag = true;
        continue; /* Loop/switch isn't completed */
_L7:
        s = getString(0x7f0b01e0);
        flag = true;
        continue; /* Loop/switch isn't completed */
_L6:
        s = getString(0x7f0b01df);
        flag = true;
        continue; /* Loop/switch isn't completed */
_L8:
        s = getString(0x7f0b01e1);
        flag = true;
        continue; /* Loop/switch isn't completed */
_L9:
        s = getString(0x7f0b01e3);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L10:
        s = getString(0x7f0b01e4);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L11:
        s = getString(0x7f0b01e5);
        flag = false;
        continue; /* Loop/switch isn't completed */
_L12:
        s = getString(0x7f0b01e7);
        flag = false;
        if (true) goto _L1; else goto _L13
_L13:
    }

    public void updateListItem(int i, DmcUtils.COVER_TYPE cover_type)
    {
        if (mHandler != null)
        {
            Message message = new Message();
            message.what = 4;
            message.arg1 = i;
            message.obj = cover_type;
            mHandler.sendMessage(message);
        }
    }










/*
    static int access$202(DmcPlayback dmcplayback, int i)
    {
        dmcplayback.mPlayingIndex = i;
        return i;
    }

*/







}
