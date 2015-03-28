// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.autocut;

import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.clip.MClip;

public class MAutoCut extends MSession
{

    private static final int PROP_BASE = 28672;
    public static final int PROP_MAX_DURATION = 28674;
    public static final int PROP_MAX_DURATION_PERCENT = 28673;

    public MAutoCut()
    {
    }

    private native int nativeCancel(long l);

    private native int nativeCreate(MEngine mengine);

    private native int nativeDestroy();

    private native MClip nativeGetClip(long l, int i);

    private native int nativeGetClipCount(long l);

    private native int nativeInsertVideo(long l, String s, int i, int j);

    private native int nativePause(long l);

    private native int nativeResume(long l);

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

    public MClip getClip(int i)
    {
        if (0L == handle)
        {
            return null;
        } else
        {
            return nativeGetClip(handle, i);
        }
    }

    public int getClipCount()
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeGetClipCount(handle);
        }
    }

    public int init(MEngine mengine, ISessionStateListener isessionstatelistener)
    {
        super.init(mengine, isessionstatelistener);
        return nativeCreate(mengine);
    }

    public int insertVideo(String s, int i, int j)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeInsertVideo(handle, s, i, j);
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
