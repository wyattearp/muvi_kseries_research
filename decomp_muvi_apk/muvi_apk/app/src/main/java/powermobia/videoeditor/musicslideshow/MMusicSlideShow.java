// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.musicslideshow;

import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.storyboard.MStoryboard;

public class MMusicSlideShow extends MSession
{

    private static final int PROP_BASE = 20480;
    public static final int PROP_RESOLUTION = 20482;
    public static final int PROP_SEQUENCE = 20481;

    public MMusicSlideShow()
    {
    }

    private native int nativeCancel(long l);

    private native int nativeCreate(MEngine mengine);

    private native int nativeDestroy();

    private native int nativeInsertImage(long l, String s);

    private native int nativeInsertMusic(long l, String s, int i, int j);

    private native int nativePause(long l);

    private native int nativeResume(long l);

    private native int nativeSaveToStoryboard(long l, MStoryboard mstoryboard);

    private native int nativeSetTemplate(long l, String s);

    private native int nativeStart(long l);

    public int cancel()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeCancel(handle);
        }
    }

    public int init(MEngine mengine, ISessionStateListener isessionstatelistener)
    {
        super.init(mengine, isessionstatelistener);
        return nativeCreate(mengine);
    }

    public int insertImage(String s)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeInsertImage(handle, s);
        }
    }

    public int insertMusic(String s, int i, int j)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeInsertMusic(handle, s, i, j);
        }
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

    public int resume()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeResume(handle);
        }
    }

    public int saveToStoryboard(MStoryboard mstoryboard)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSaveToStoryboard(handle, mstoryboard);
        }
    }

    public int setTemplate(String s)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetTemplate(handle, s);
        }
    }

    public int start()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeStart(handle);
        }
    }

    public int unInit()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeDestroy();
        }
    }
}
