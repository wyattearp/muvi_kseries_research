// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.producer;

import powermobia.videoeditor.base.MVideoInfo;

public class MProducerState
{

    private int currentTime;
    private MVideoInfo dstVideoInfo;
    private MVideoInfo srcVideoInfo;
    private int state;

    private MProducerState()
    {
        srcVideoInfo = null;
        dstVideoInfo = null;
        state = 0;
        currentTime = 0;
    }

    public int getCurrentTime()
    {
        return currentTime;
    }

    public MVideoInfo getDstVideoInfo()
    {
        return dstVideoInfo;
    }

    public MVideoInfo getSrcVideoInfo()
    {
        return srcVideoInfo;
    }

    public int getState()
    {
        return state;
    }
}
