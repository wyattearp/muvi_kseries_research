// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.clip;


public class MFade
{

    public static final int DURATION = 0;
    public static final int END_PERCENT = 2;
    public static final int START_PERCENT = 1;
    private int duration;
    private int endPercent;
    private int startPercent;

    public MFade()
    {
        duration = 0;
        startPercent = 0;
        endPercent = 0;
    }

    public MFade(int i, int j, int k)
    {
        duration = 0;
        startPercent = 0;
        endPercent = 0;
        duration = i;
        startPercent = j;
        endPercent = k;
    }

    public MFade(MFade mfade)
    {
        duration = 0;
        startPercent = 0;
        endPercent = 0;
        duration = mfade.duration;
        startPercent = mfade.startPercent;
        endPercent = mfade.endPercent;
    }

    public int get(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 0: // '\0'
            return duration;

        case 1: // '\001'
            return startPercent;

        case 2: // '\002'
            return endPercent;
        }
    }

    public void set(int i, int j)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            duration = j;
            return;

        case 1: // '\001'
            startPercent = j;
            return;

        case 2: // '\002'
            endPercent = j;
            break;
        }
    }
}
