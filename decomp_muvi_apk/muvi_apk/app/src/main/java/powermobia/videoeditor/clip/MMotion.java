// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;

import powermobia.ve.utils.MRect;

public class MMotion
{

    private MRect end;
    private MRect start;

    public MMotion()
    {
        start = null;
        end = null;
    }

    public MMotion(MMotion mmotion)
    {
        start = null;
        end = null;
        if (mmotion != null)
        {
            start = mmotion.start;
            end = mmotion.end;
        }
    }

    public MRect getEnd()
    {
        return end;
    }

    public MRect getStart()
    {
        return start;
    }

    public void setEnd(MRect mrect)
    {
        end = mrect;
    }

    public void setStart(MRect mrect)
    {
        start = mrect;
    }
}
