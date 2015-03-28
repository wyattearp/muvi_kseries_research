// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;


// Referenced classes of package powermobia.videoeditor.base:
//            MSession

public class MSessionState
{

    private int currentTime;
    private int duration;
    private int errorCode;
    private MSession session;
    private int status;

    private MSessionState()
    {
        status = 0;
        currentTime = 0;
        duration = 0;
        errorCode = 0;
        session = null;
    }

    public int getCurrentTime()
    {
        return currentTime;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public MSession getSession()
    {
        return session;
    }

    public int getStatus()
    {
        return status;
    }
}
