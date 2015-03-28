// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.widget.SeekBar;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback, DmcPlayingDataProvider, DmcPlayerEx

class this._cls0
    implements com.arcsoft.mediaplus.playengine.rListener
{

    final DmcPlayback this$0;

    public void onBuffering()
    {
        Log.d("Dmc", "onBuffering");
        DmcPlayback.access$1100(DmcPlayback.this);
        refreshPlayBtn(true);
        DmcPlayback.access$500(DmcPlayback.this, true);
    }

    public void onCompleted()
    {
        Log.d("Dmc", "onCompleted");
        DmcPlayback.access$800(DmcPlayback.this);
        if (DmcPlayback.access$900(DmcPlayback.this) != null && !DmcPlayback.access$900(DmcPlayback.this).isPlayOver() && DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            refreshPlayBtn(false);
            DmcPlayback.access$1000(DmcPlayback.this).playnext();
        } else
        {
            stopPlayer();
            DmcPlayback.access$202(DmcPlayback.this, 0);
            refreshPrevNextBtn(true);
            refreshCover(0, true);
            refreshPlayBtn(false, true);
            DmcPlayback.access$400(DmcPlayback.this, false);
            if (DmcPlayback.access$900(DmcPlayback.this) != null)
            {
                DmcPlayback.access$900(DmcPlayback.this).resetPlayingInfo();
                return;
            }
        }
    }

    public void onError(com.arcsoft.mediaplus.playengine.Error error)
    {
        Log.d("Dmc", (new StringBuilder()).append("onError\uFF1A code = ").append(error.name()).toString());
        switch (..SwitchMap.com.arcsoft.mediaplus.playengine.IPlayer.PlayerError[error.ordinal()])
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
        DmcPlayback.access$202(DmcPlayback.this, i);
        stopImageDispTimer();
        refreshPrevNextBtn(false);
    }

    public void onPlayStarted()
    {
        Log.d("Dmc", "onPlayStarted");
        DmcPlayback.access$300(DmcPlayback.this);
        DmcPlayback.access$400(DmcPlayback.this, true);
        refreshCover(DmcPlayback.access$200(DmcPlayback.this), false);
        refreshPlayBtn(false);
        DmcPlayback.access$500(DmcPlayback.this, false);
        DmcPlayback.access$600(DmcPlayback.this);
        mPausedByManualOper = false;
    }

    public void onProgressChanged(long l, long l1)
    {
        Log.d("Dmc", (new StringBuilder()).append("onProgressChanged\uFF1Aduration = ").append(l).append(" position = ").append(l1).toString());
        if (0L != l && 0L != l1)
        {
            DmcPlayback.access$1200(DmcPlayback.this).setEnabled(true);
            if ((long)mCurrentMaxOfSeekbar != l)
            {
                mCurrentMaxOfSeekbar = (int)l;
                DmcPlayback.access$1200(DmcPlayback.this).setMax(mCurrentMaxOfSeekbar);
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
        DmcPlayback.access$700(DmcPlayback.this);
        Log.d("Dmc", "onStopped");
        DmcPlayback.access$500(DmcPlayback.this, false);
    }

    public void onVolumeChanged(int i)
    {
        Log.d("Dmc", (new StringBuilder()).append("onVolumeChanged: volume = ").append(i).toString());
    }

    .PlayerError()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
