// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity, DataSourcePlayList, VolumeController

public abstract class PlayView
    implements RootPlayView.IAnimationComponent
{
    public static interface IPlayViewContorller
    {

        public abstract void requestQuit(PlayView playview);
    }

    public class PlaySession
    {

        int index;
        boolean playing;
        long position;
        final PlayView this$0;

        PlaySession(int i, long l)
        {
            this$0 = PlayView.this;
            super();
            playing = true;
            index = i;
            position = l;
        }
    }


    public static final int INTERVAL_HIDE_CONTROLVIEW = 5000;
    public static final int MSG_HIDE_CONTROLVIEW = 1280;
    public static final int MSG_SHOW_CONTROLVIEW = 1281;
    static final int MSG_UPDOWNLOAD_FINISH = 1539;
    static final int MSG_UPDOWNLOAD_PROGRESS = 1538;
    static final int MSG_UPDOWNLOAD_START = 1537;
    private static final String TAG = "PlayView";
    final int DMR_MAX_VOLUME_VALUE = 100;
    final int DMR_STEP_VOLUME = 3;
    final int VOLUME_CONTROL_HIDE_DELAY = 3000;
    protected Context mContext;
    protected Handler mHandler;
    protected android.view.View.OnTouchListener mOnTouchListener;
    protected ViewGroup mParent;
    protected DataSourcePlayList mPlayList;
    private IPlayViewContorller mPlayViewContorller;
    protected com.arcsoft.util.tool.PlayerMandatoryManager.IOnPlayerMandatoryManagerCallback mPlayerMandatoryManagerCallback;
    protected View mRootView;
    protected VolumeController mVolumeController;
    protected ViewGroup mVolumeView;

    public PlayView(Context context, ViewGroup viewgroup)
    {
        mVolumeView = null;
        mVolumeController = null;
        mPlayerMandatoryManagerCallback = null;
        mOnTouchListener = new android.view.View.OnTouchListener() {

            final PlayView this$0;

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return PlayView.this.onTouch(view, motionevent);
            }

            
            {
                this$0 = PlayView.this;
                super();
            }
        };
        mHandler = new Handler(Looper.myLooper()) {

            final PlayView this$0;

            public void handleMessage(Message message)
            {
                switch (message.what)
                {
                default:
                    return;

                case 1280: 
                    hideControlView();
                    ((PlayActivity)mContext).hideControlBar();
                    return;

                case 1281: 
                    showControlView();
                    break;
                }
                ((PlayActivity)mContext).showControlBar();
            }

            
            {
                this$0 = PlayView.this;
                super(looper);
            }
        };
        if (viewgroup == null)
        {
            throw new NullPointerException("Parent needed!");
        } else
        {
            mContext = context;
            mParent = viewgroup;
            return;
        }
    }

    private void loadView()
    {
        View view = mRootView;
        int i = 0;
        if (view != null)
        {
            i = mRootView.getVisibility();
            onDestoryRootView(mRootView);
            mParent.removeView(mRootView);
        }
        mRootView = LayoutInflater.from(mContext).inflate(getLayoutID(), null);
        if (mRootView == null)
        {
            throw new UnknownError((new StringBuilder()).append("Can not initialize PlayView : ").append(getClass().getSimpleName()).toString());
        } else
        {
            mParent.addView(mRootView);
            android.view.ViewGroup.LayoutParams layoutparams = mRootView.getLayoutParams();
            layoutparams.height = -1;
            layoutparams.width = -1;
            mRootView.setLayoutParams(layoutparams);
            mRootView.setVisibility(i);
            mRootView.setOnTouchListener(mOnTouchListener);
            setupViewEvents();
            resetHideControlTimer(true);
            refreshThumbnail();
            return;
        }
    }

    protected abstract void addUpdownload(boolean flag);

    protected abstract void cancelAllUpdownload();

    protected abstract void cancelUpdownload(boolean flag);

    public int getAudioChannelNum()
    {
        return 0;
    }

    public int getAudioTrackNum()
    {
        return 0;
    }

    public int getCurrentAudioChannelIndex()
    {
        return 0;
    }

    public int getCurrentAudioTrackIndex()
    {
        return 0;
    }

    public int getCurrentIndexByPV()
    {
        return -1;
    }

    protected abstract int getLayoutID();

    public final View getRootView()
    {
        return mRootView;
    }

    protected abstract int getUpdownloadState(boolean flag);

    protected void hideControlBarImmediatly()
    {
        mHandler.removeMessages(1280);
        mHandler.removeMessages(1281);
        mHandler.sendEmptyMessage(1280);
    }

    protected void hideControlView()
    {
        getControlView().setVisibility(4);
        ((PlayActivity)mContext).findViewById(0x7f0900af).setVisibility(8);
        ((PlayActivity)mContext).findViewById(0x7f0900ad).setVisibility(8);
    }

    public final void init()
    {
        loadView();
        onInit();
    }

    protected boolean isDMSContainDtcp(Uri uri, int i)
    {
        ArrayList arraylist;
        boolean flag;
        arraylist = mPlayList.getDataSource().getRemoteItemResourceDesc(i);
        flag = false;
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        int j;
        j = arraylist.size();
        flag = false;
        if (j <= 0) goto _L2; else goto _L3
_L3:
        int k = 0;
_L8:
        int l;
        l = arraylist.size();
        flag = false;
        if (k >= l) goto _L2; else goto _L4
_L4:
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)arraylist.get(k);
        String s;
        if (false && presentitem_resource.m_strPxnVgaContentProtocolInfo != null && presentitem_resource.m_strPxnVgaContentProtocolInfo.length() != 0)
        {
            s = presentitem_resource.m_strPxnVgaContentProtocolInfo;
        } else
        {
            s = presentitem_resource.m_strProtocolInfo;
        }
        if (!s.contains("DTCP1HOST") || !s.contains("DTCP1PORT")) goto _L6; else goto _L5
_L5:
        flag = true;
_L2:
        return flag;
_L6:
        k++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public boolean isNeedConfirm()
    {
        return false;
    }

    protected boolean isSlideView()
    {
        return false;
    }

    abstract void onActivityPause();

    abstract void onActivityResume();

    abstract void onActivityStart();

    abstract void onActivityStop();

    protected abstract void onDestoryRootView(View view);

    protected abstract void onInit();

    public abstract boolean onKeyDown(int i, KeyEvent keyevent);

    public abstract boolean onKeyUp(int i, KeyEvent keyevent);

    protected abstract void onSetPlayList(DataSourcePlayList datasourceplaylist);

    abstract void onStart();

    abstract void onStarted(long l);

    abstract void onStop();

    abstract void onStopCanceled();

    abstract PlaySession onStopped(boolean flag);

    protected boolean onTouch(View view, MotionEvent motionevent)
    {
        return false;
    }

    protected abstract void onUninit();

    void onWindowFocusChanged(boolean flag)
    {
    }

    public abstract void prepareOptionMenu(Menu menu);

    abstract void refreshThumbnail();

    public final void requestQuit()
    {
        if (mPlayViewContorller != null)
        {
            mPlayViewContorller.requestQuit(this);
        }
    }

    protected void resetHideControlTimer(boolean flag)
    {
        mHandler.removeMessages(1280);
        mHandler.removeMessages(1281);
        mHandler.sendEmptyMessage(1281);
        if (flag && !((PlayActivity)mContext).isDialogShown() && !((PlayActivity)mContext).isInfoViewShown())
        {
            mHandler.sendEmptyMessageDelayed(1280, 5000L);
        }
    }

    protected void resetHideControlTimerEx()
    {
    }

    protected abstract boolean samePortLandLayout();

    public void selectAudioChannel(int i)
    {
    }

    protected void setBtnEnable(View view, boolean flag)
    {
        if (view != null)
        {
            view.setEnabled(flag);
            if (!flag)
            {
                view.setPressed(false);
                return;
            }
        }
    }

    public final void setConfigurationChanged(Configuration configuration)
    {
        if (samePortLandLayout())
        {
            return;
        } else
        {
            loadView();
            return;
        }
    }

    public void setCurrentAudioTrackIndex(int i)
    {
    }

    public final void setPlayList(DataSourcePlayList datasourceplaylist)
    {
        mPlayList = datasourceplaylist;
        onSetPlayList(datasourceplaylist);
    }

    public final void setPlayviewContorller(IPlayViewContorller iplayviewcontorller)
    {
        mPlayViewContorller = iplayviewcontorller;
    }

    protected abstract void setUpDownloadEngine(UpDownloadEngine updownloadengine);

    public final void setVisibility(int i)
    {
        getRootView().setVisibility(i);
    }

    boolean setVolume(ViewGroup viewgroup, boolean flag)
    {
        return false;
    }

    protected abstract void setupViewEvents();

    protected void showControlView()
    {
        Log.d("PlayView", "showControlView invoked");
        getControlView().setVisibility(0);
        if (((PlayActivity)mContext).isDMPView() && !((PlayActivity)mContext).isRealtimePlayView() && !isSlideView())
        {
            ((PlayActivity)mContext).findViewById(0x7f0900af).setVisibility(8);
        }
        if (((PlayActivity)mContext).getMediaType() == 4 && ((PlayActivity)mContext).isDMPView())
        {
            ((PlayActivity)mContext).findViewById(0x7f0900ad).setVisibility(8);
        }
    }

    protected void stopHideControlTimer()
    {
        mHandler.removeMessages(1280);
    }

    public void svcDisable()
    {
    }

    public void svcReady()
    {
    }

    public final void uninit()
    {
        if (mRootView != null)
        {
            onDestoryRootView(mRootView);
            mParent.removeView(mRootView);
        }
        onUninit();
    }

    abstract void updateBtnsEnableStatus(boolean flag);
}
