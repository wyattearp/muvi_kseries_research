// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.producer;

import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.base.ISessionStateListener;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.base.MSessionStream;

public class MProducer extends MSession
{

    private static final int PROP_BASE = 24576;
    public static final int PROP_PARAM = 24577;

    public MProducer()
    {
    }

    private native int nativeCancel(long l);

    private native int nativeCreate(MEngine mengine, MProducer mproducer);

    private native int nativeDestroy(MProducer mproducer);

    private native int nativePause(long l);

    private native int nativeResume(long l);

    private native int nativeSetStream(long l, MSessionStream msessionstream);

    private native int nativeStart(long l);

    private native int nativeStop(long l);

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

    public int setStream(MSessionStream msessionstream)
    {
        if (0L == handle)
        {
            return 0x80006;
        } else
        {
            return nativeSetStream(handle, msessionstream);
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
