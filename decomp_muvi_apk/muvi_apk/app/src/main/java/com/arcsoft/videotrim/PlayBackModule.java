// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.content.Context;
import android.os.Handler;
import android.view.Surface;
import com.arcsoft.videotrim.Utils.AppContext;
import com.arcsoft.videotrim.Utils.RuntimeConfig;
import com.arcsoft.videotrim.Utils.UtilFunc;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MDisplayContext;
import powermobia.videoeditor.base.MRange;
import powermobia.videoeditor.base.MSessionState;
import powermobia.videoeditor.base.MSessionStream;
import powermobia.videoeditor.base.MVideoInfo;
import powermobia.videoeditor.player.MPlayer;
import powermobia.videoeditor.player.MPlayerState;

public class PlayBackModule
    implements ISessionStateListener
{
    public static final class Direction extends Enum
    {

        private static final Direction $VALUES[];
        public static final Direction NEXT_KEYFRAME;
        public static final Direction PREV_KEYFRAME;

        public static Direction valueOf(String s)
        {
            return (Direction)Enum.valueOf(com/arcsoft/videotrim/PlayBackModule$Direction, s);
        }

        public static Direction[] values()
        {
            return (Direction[])$VALUES.clone();
        }

        static 
        {
            PREV_KEYFRAME = new Direction("PREV_KEYFRAME", 0);
            NEXT_KEYFRAME = new Direction("NEXT_KEYFRAME", 1);
            Direction adirection[] = new Direction[2];
            adirection[0] = PREV_KEYFRAME;
            adirection[1] = NEXT_KEYFRAME;
            $VALUES = adirection;
        }

        private Direction(String s, int i)
        {
            super(s, i);
        }
    }


    public static final int DEFAULT_SEEK_POSITION = -1;
    private static final int MIN_PLAYER_CB_INTERVAL = 100;
    private static final int MSG_PLAYER_BASE = 4096;
    private static final int MSG_PLAYER_LAST = 4100;
    public static final int MSG_PLAYER_PAUSED = 4100;
    public static final int MSG_PLAYER_READY = 4097;
    public static final int MSG_PLAYER_RUNNING = 4099;
    public static final int MSG_PLAYER_STOPPED = 4098;
    private Context m_Context;
    private Handler m_Handler;
    private int m_iOldPlayerStatus;
    private int m_iOldPlayerTime;
    private MPlayer m_player;

    public PlayBackModule()
    {
        m_Context = null;
        m_Handler = null;
        m_player = null;
        m_iOldPlayerStatus = 0;
        m_iOldPlayerTime = 0;
    }

    public int EnableDisplay(boolean flag)
    {
        if (m_player != null)
        {
            MPlayer mplayer = m_player;
            boolean flag1;
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (mplayer.disableDisplay(flag1) == 0)
            {
                return 0;
            }
        }
        return 1;
    }

    public int Init(Context context, Handler handler, MSessionStream msessionstream, int i, MDisplayContext mdisplaycontext, Surface surface)
    {
        byte byte0 = 1;
        if (context == null || handler == null || msessionstream == null || mdisplaycontext == null)
        {
            byte0 = 2;
        } else
        {
            m_Context = context;
            m_Handler = handler;
            AppContext appcontext = AppContext.getAppContext();
            m_player = new MPlayer();
            if (m_player.init(appcontext.getVEEngine(), this) == 0)
            {
                if (SetDisplayContext(mdisplaycontext, surface) != 0)
                {
                    m_player.unInit();
                    m_player = null;
                    return byte0;
                }
                if (m_player.setStream(msessionstream, i, RuntimeConfig.IFRAME_SOLUTION) != 0)
                {
                    m_player.unInit();
                    m_player = null;
                    return byte0;
                } else
                {
                    return 0;
                }
            }
        }
        return byte0;
    }

    public boolean IsPlaying()
    {
        MPlayerState mplayerstate;
        if (m_player != null)
        {
            if ((mplayerstate = (MPlayerState)m_player.getState()) != null && mplayerstate.get(0) == 2)
            {
                return true;
            }
        }
        return false;
    }

    public int OnPaused()
    {
        return m_player != null ? 0 : 1;
    }

    public int OnPlaying()
    {
        return m_player != null ? 0 : 1;
    }

    public int OnReady(boolean flag)
    {
        while (m_player == null || m_player.disableTrack(0, flag) != 0) 
        {
            return 1;
        }
        return 0;
    }

    public int OnStopped(boolean flag)
    {
        MRange mrange;
        if (m_player != null)
        {
            if (!flag || m_player.seekTo((mrange = (MRange)m_player.getProperty(32769)).get(0)) == 0)
            {
                return 0;
            }
        }
        return 1;
    }

    public int Pause()
    {
        if (m_player != null)
        {
            if (!IsPlaying())
            {
                return 0;
            }
            if (m_player.pause() == 0)
            {
                return 0;
            }
        }
        return 1;
    }

    public int Play()
    {
        if (m_player != null)
        {
            if (IsPlaying())
            {
                return 0;
            }
            if (m_player.play() == 0)
            {
                return 0;
            }
        }
        return 1;
    }

    public int RefreshDisplay()
    {
        while (m_player == null || m_player.displayRefresh() != 0) 
        {
            return 1;
        }
        return 0;
    }

    public int SeekTo(int i)
    {
        if (m_player == null)
        {
            return 1;
        }
        m_Handler.removeMessages(4099);
        m_Handler.removeMessages(4100);
        if (m_player.seekTo(i) != 0)
        {
            UtilFunc.Log("ASYNC_SEEK", "Async seek error!");
            return 1;
        } else
        {
            return 0;
        }
    }

    public int SetDisplayContext(MDisplayContext mdisplaycontext, Surface surface)
    {
        if (m_player == null)
        {
            return 5;
        }
        if (surface != null && !surface.isValid())
        {
            surface = null;
        }
        return m_player.setDisplayContext(mdisplaycontext, surface) == 0 ? 0 : 1;
    }

    public int SetHandler(Handler handler)
    {
        if (handler == null)
        {
            return 2;
        }
        if (handler != null)
        {
            for (int i = 4096; i <= 4100; i++)
            {
                handler.removeMessages(i);
            }

        }
        m_Handler = handler;
        return 0;
    }

    public int SetPlayRange(int i, int j)
    {
        return SetPlayRange(new MRange(i, j));
    }

    public int SetPlayRange(MRange mrange)
    {
        if (m_player == null || mrange == null)
        {
            return 1;
        } else
        {
            return m_player.setProperty(32769, mrange);
        }
    }

    public int SetStream(MSessionStream msessionstream, int i)
    {
        byte byte0 = 1;
        if (msessionstream == null)
        {
            byte0 = 2;
        } else
        if (m_player != null)
        {
            m_Handler.removeMessages(4099);
            m_Handler.removeMessages(4100);
            if (m_player.setStream(msessionstream, i, RuntimeConfig.IFRAME_SOLUTION) == 0)
            {
                return 0;
            }
        }
        return byte0;
    }

    public int Stop()
    {
        while (m_player == null || m_player.stop() != 0) 
        {
            return 1;
        }
        return 0;
    }

    public void UnInit()
    {
        if (m_player != null)
        {
            m_player.unInit();
            m_player = null;
        }
        if (m_Handler != null)
        {
            for (int i = 4096; i <= 4100; i++)
            {
                m_Handler.removeMessages(i);
            }

            m_Handler = null;
        }
        m_Context = null;
        m_iOldPlayerStatus = 0;
        m_iOldPlayerTime = 0;
    }

    public int getCurrentTime()
    {
        MPlayerState mplayerstate;
        if (m_player != null)
        {
            if ((mplayerstate = (MPlayerState)m_player.getState()) != null)
            {
                return mplayerstate.get(1);
            }
        }
        return -1;
    }

    public int getDuration()
    {
        MPlayerState mplayerstate;
        MVideoInfo mvideoinfo;
        if (m_player != null)
        {
            if ((mplayerstate = (MPlayerState)m_player.getState()) != null && (mvideoinfo = mplayerstate.getVideoInfo()) != null)
            {
                return mvideoinfo.get(5);
            }
        }
        return -1;
    }

    public int onSessionStatus(MSessionState msessionstate)
    {
        if (msessionstate.getErrorCode() == 0) goto _L2; else goto _L1
_L1:
        int i = 0x801f7;
_L4:
        return i;
_L2:
        Handler handler;
        handler = m_Handler;
        i = 0;
        if (handler == null) goto _L4; else goto _L3
_L3:
        msessionstate.getStatus();
        JVM INSTR tableswitch 1 4: default 56
    //                   1 59
    //                   2 184
    //                   3 106
    //                   4 154;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        return 0x80005;
_L6:
        m_iOldPlayerTime = 0;
        m_iOldPlayerStatus = 0;
        android.os.Message message3 = m_Handler.obtainMessage(4097, msessionstate.getCurrentTime(), 0);
        m_Handler.sendMessage(message3);
_L11:
        m_iOldPlayerStatus = msessionstate.getStatus();
        return 0;
_L8:
        m_Handler.removeMessages(4099);
        if (m_iOldPlayerStatus != 3)
        {
            android.os.Message message2 = m_Handler.obtainMessage(4100, msessionstate.getCurrentTime(), 0);
            m_Handler.sendMessage(message2);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        android.os.Message message1 = m_Handler.obtainMessage(4098, msessionstate.getCurrentTime(), 0);
        m_Handler.sendMessage(message1);
        continue; /* Loop/switch isn't completed */
_L7:
        int j = msessionstate.getCurrentTime();
        int k;
        if (m_iOldPlayerTime >= j)
        {
            k = m_iOldPlayerTime - j;
        } else
        {
            k = j - m_iOldPlayerTime;
        }
        if (m_iOldPlayerStatus != msessionstate.getStatus() || k >= 100)
        {
            android.os.Message message = m_Handler.obtainMessage(4099, j, 0);
            m_Handler.removeMessages(4099);
            m_Handler.sendMessage(message);
            m_iOldPlayerTime = j;
        }
        if (true) goto _L11; else goto _L10
_L10:
    }

    public int setSeekDirection(Direction direction)
    {
        if (m_player == null)
        {
            return 5;
        }
        int i;
        Integer integer;
        if (direction == Direction.PREV_KEYFRAME)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        integer = Integer.valueOf(i);
        return m_player.setProperty(32770, integer);
    }

    public int syncSeekTo(int i)
    {
        if (m_player == null)
        {
            return 1;
        }
        m_Handler.removeMessages(4099);
        m_Handler.removeMessages(4100);
        if (m_player.syncSeekTo(i) != 0)
        {
            UtilFunc.Log("SYNC_SEEK", "Async seek error!");
            return 1;
        } else
        {
            return 0;
        }
    }
}
