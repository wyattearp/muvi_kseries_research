// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.videoeditor.MEngine;

// Referenced classes of package powermobia.videoeditor.base:
//            ISessionStateListener, MSessionState

public abstract class MSession
{

    public static final int STATUS_INITIALIZING = 5;
    public static final int STATUS_NONE = 0;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_READY = 1;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_STOPPED = 4;
    protected MEngine engine;
    protected long handle;
    private long jniglobalobjectref;
    protected ISessionStateListener listener;
    private MSessionState state;

    public MSession()
    {
        jniglobalobjectref = 0L;
        state = null;
        listener = null;
        engine = null;
        handle = 0L;
    }

    private native Object nativeGetProp(long l, int i);

    private native Object nativeGetState(long l);

    private native int nativeSetProp(long l, int i, Object obj);

    private int onSessionStatus(MSessionState msessionstate)
    {
        if (listener == null)
        {
            return 0;
        } else
        {
            return listener.onSessionStatus(msessionstate);
        }
    }

    public Object getProperty(int i)
    {
        return nativeGetProp(handle, i);
    }

    public Object getState()
    {
        return nativeGetState(handle);
    }

    public int init(MEngine mengine, ISessionStateListener isessionstatelistener)
    {
        engine = mengine;
        listener = isessionstatelistener;
        return 0;
    }

    public int setProperty(int i, Object obj)
    {
        return nativeSetProp(handle, i, obj);
    }

    void setSessionStateListener(ISessionStateListener isessionstatelistener)
    {
        listener = isessionstatelistener;
    }

    public abstract int unInit();
}
