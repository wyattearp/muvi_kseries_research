// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.player;

import powermobia.videoeditor.base.MVideoInfo;

public class MPlayerState
{

    public static final int CURRENT_TIME = 1;
    public static final int PLAYERBACK_MODE = 2;
    public static final int STATUS = 0;
    public static final int VOLUME = 3;
    private int currentTime;
    private boolean muted;
    private int playbackMode;
    private int status;
    private MVideoInfo videoInfo;
    private int volume;

    private MPlayerState()
    {
    }

    public int get(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 0: // '\0'
            return status;

        case 1: // '\001'
            return currentTime;

        case 2: // '\002'
            return playbackMode;

        case 3: // '\003'
            return volume;
        }
    }

    public boolean getMuteFlag()
    {
        return muted;
    }

    public MVideoInfo getVideoInfo()
    {
        return videoInfo;
    }
}
