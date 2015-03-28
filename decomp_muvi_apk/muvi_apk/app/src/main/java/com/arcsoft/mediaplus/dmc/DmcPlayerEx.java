// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.mediaplus.playengine.DMCPlayEngine;
import com.arcsoft.mediaplus.playengine.DMCPlayer;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayingDataProvider

public class DmcPlayerEx extends DMCPlayer
{

    protected DmcPlayingDataProvider mDataProvider;

    public DmcPlayerEx()
    {
        mDataProvider = null;
        TAG = "Dmc";
    }

    public boolean play(int i, long l)
    {
        return super.play(i, l);
    }

    public boolean play(final int _index, final long _startpos, final com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT _effect)
    {
        final com.arcsoft.adk.atv.UPnP.MediaRenderDesc renderdesc;
        if (mDataProvider != null && _index >= 0)
        {
            if ((renderdesc = DLNA.instance().getRenderManager().getRenderDesc(mEngine.getRenderUDN())) != null)
            {
                mCurAction = null;
                mMsgHandler.removeMessages(0);
                mDelayPlayHandler.removeMessages(0);
                Message message = mDelayPlayHandler.obtainMessage();
                message.what = 0;
                message.obj = new Runnable() {

                    final DmcPlayerEx this$0;
                    final com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT val$_effect;
                    final int val$_index;
                    final long val$_startpos;
                    final com.arcsoft.adk.atv.UPnP.MediaRenderDesc val$renderdesc;

                    public void run()
                    {
                        Log.d(
// JavaClassFileOutputException: get_constant: invalid tag

            
            {
                this$0 = DmcPlayerEx.this;
                _index = i;
                _startpos = l;
                _effect = effect;
                renderdesc = mediarenderdesc;
                super();
            }
                };
                checkAndStop(_effect);
                mDelayPlayHandler.sendMessageDelayed(message, 500L);
                return false;
            }
        }
        return false;
    }

    public boolean playnext()
    {
        int i;
        if (mDataProvider != null)
        {
            if (-1 != (i = mDataProvider.next()))
            {
                return play(i, 0L);
            }
        }
        return false;
    }

    public boolean playprev()
    {
        int i;
        if (mDataProvider != null)
        {
            if (-1 != (i = mDataProvider.prev()))
            {
                return play(i, 0L);
            }
        }
        return false;
    }

    protected void registPlaylistChangeListener()
    {
        if (mDataProvider != null)
        {
            mDataProvider.setOnPlaylistChangeListener(mPlaylistChangeListener);
        }
    }

    public void setActivate(boolean flag)
    {
label0:
        {
            if (flag && mIsEngineLose)
            {
                mIsEngineLose = false;
                mEngine.gainListening(mEngineListener);
                if (DLNA.instance().getRenderManager().isRenderOnline(mEngine.getRenderUDN()))
                {
                    break label0;
                }
                mEngineListener.OnError(com.arcsoft.mediaplus.playengine.IPlayEngine.PlayEngineError.ERROR_RENDER_DISCONNECTED);
            }
            return;
        }
        play(mDataProvider.getCurrentIndex(), 0L, com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_FADE);
    }

    public void setDataProvider(DmcPlayingDataProvider dmcplayingdataprovider)
    {
        mDataProvider = dmcplayingdataprovider;
        stop(com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_NONE);
        registPlaylistChangeListener();
    }

    protected void unRegistPlaylistChangeListener()
    {
        if (mDataProvider != null)
        {
            mDataProvider.setOnPlaylistChangeListener(null);
        }
    }







/*
    static com.arcsoft.mediaplus.playengine.DMCPlayer.PlayerAction access$502(DmcPlayerEx dmcplayerex, com.arcsoft.mediaplus.playengine.DMCPlayer.PlayerAction playeraction)
    {
        dmcplayerex.mCurAction = playeraction;
        return playeraction;
    }

*/


/*
    static com.arcsoft.mediaplus.playengine.DMCPlayer.PlayerAction access$602(DmcPlayerEx dmcplayerex, com.arcsoft.mediaplus.playengine.DMCPlayer.PlayerAction playeraction)
    {
        dmcplayerex.mCurAction = playeraction;
        return playeraction;
    }

*/



}
