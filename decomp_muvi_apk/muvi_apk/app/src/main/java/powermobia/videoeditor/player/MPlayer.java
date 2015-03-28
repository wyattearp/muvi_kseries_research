// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.player;

import powermobia.ve.utils.MBitmap;
import powermobia.ve.utils.MBitmapFactory;
import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MDisplayContext;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.base.MSessionStream;

public class MPlayer extends MSession
{

    public static final int PLAYBACKMODE_FF2X = 1;
    public static final int PLAYBACKMODE_FF4X = 2;
    public static final int PLAYBACKMODE_I_FRAME = 5;
    public static final int PLAYBACKMODE_NORMAL = 0;
    public static final int PLAYBACKMODE_SF2X = 3;
    public static final int PLAYBACKMODE_SF4X = 4;
    public static final int PLAYER_LAST_POSITION = -1;
    public static final int PLAYER_SEEK_TYPE_NEXT = 1;
    public static final int PLAYER_SEEK_TYPE_PRE = 0;
    public static final int PROP_PLAYER_BASE = 32768;
    public static final int PROP_PLAYER_RANGE = 32769;
    public static final int PROP_PLAYER_SEEK_DIR = 32770;
    public static final int TRACK_TYPE_AUDIO = 1;
    public static final int TRACK_TYPE_VIDEO;

    public MPlayer()
    {
    }

    private native int nativeAudioRestart(long l);

    private native int nativeCreate(MEngine mengine, MPlayer mplayer);

    private native int nativeDestroy(MPlayer mplayer);

    private native int nativeDisableDisplay(long l, boolean flag);

    private native int nativeDisableTrack(long l, int i, boolean flag);

    private native int nativeDisplayRefresh(long l);

    private native int nativeGetCurFrame(long l, MBitmap mbitmap);

    private native int nativeGetDisplayContext(long l, MDisplayContext mdisplaycontext);

    private native int nativePause(long l);

    private native int nativePlay(long l);

    private native int nativeSeekTo(long l, int i);

    private native int nativeSetDisplayContext(MPlayer mplayer, MDisplayContext mdisplaycontext, Object obj);

    private native int nativeSetMode(long l, int i);

    private native int nativeSetStream(long l, MSessionStream msessionstream, int i, boolean flag);

    private native int nativeSetVolume(long l, int i);

    private native int nativeStop(long l);

    private native int nativeSyncSeekTo(long l, int i);

    public int audioRestart()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeAudioRestart(handle);
        }
    }

    public int disableDisplay(boolean flag)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDisableDisplay(handle, flag);
        }
    }

    public int disableTrack(int i, boolean flag)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDisableTrack(handle, i, flag);
        }
    }

    public int displayRefresh()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDisplayRefresh(handle);
        }
    }

    public MBitmap getCurFrame(int i, int j, int k)
    {
        MBitmap mbitmap;
        if (0L == handle)
        {
            mbitmap = null;
        } else
        {
            mbitmap = MBitmapFactory.createMBitmapBlank(i, j, k);
            if (mbitmap == null)
            {
                return null;
            }
            if (nativeGetCurFrame(handle, mbitmap) != 0)
            {
                mbitmap.recycle();
                return null;
            }
        }
        return mbitmap;
    }

    public MDisplayContext getDisplayContext()
    {
        MDisplayContext mdisplaycontext;
        if (0L == handle)
        {
            mdisplaycontext = null;
        } else
        {
            mdisplaycontext = new MDisplayContext();
            if (nativeGetDisplayContext(handle, mdisplaycontext) != 0)
            {
                return null;
            }
        }
        return mdisplaycontext;
    }

    public int init(MEngine mengine, ISessionStateListener isessionstatelistener)
    {
        super.init(mengine, isessionstatelistener);
        return nativeCreate(mengine, this);
    }

    public int pause()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativePause(handle);
        }
    }

    public int play()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativePlay(handle);
        }
    }

    public int seekTo(int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSeekTo(handle, i);
        }
    }

    public int setDisplayContext(MDisplayContext mdisplaycontext, Object obj)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetDisplayContext(this, mdisplaycontext, obj);
        }
    }

    public int setMode(int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetMode(handle, i);
        }
    }

    public int setStream(MSessionStream msessionstream, int i, boolean flag)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetStream(handle, msessionstream, i, flag);
        }
    }

    public int setVolume(int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetVolume(handle, i);
        }
    }

    public int stop()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeStop(handle);
        }
    }

    public int syncSeekTo(int i)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSyncSeekTo(handle, i);
        }
    }

    public int unInit()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy(this);
        }
    }
}
