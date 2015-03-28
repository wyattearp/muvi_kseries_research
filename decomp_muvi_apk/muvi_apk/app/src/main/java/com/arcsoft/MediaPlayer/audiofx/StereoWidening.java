// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer.audiofx;

import com.arcsoft.MediaPlayer.ArcMediaPlayer;

// Referenced classes of package com.arcsoft.MediaPlayer.audiofx:
//            AudioEffect

public class StereoWidening extends AudioEffect
{

    public static final int MODE_AUTO = 0;
    public static final int MODE_HEADPHONE = 1;
    public static final int MODE_SPEAKER = 2;
    public static final int PARAM_ENABLE = 8193;
    public static final int PARAM_MODE = 8194;

    public StereoWidening(ArcMediaPlayer arcmediaplayer)
    {
        super(arcmediaplayer);
    }

    public boolean isSWEnabled()
    {
        int ai[] = new int[1];
        checkStatus(getAudioEffectParam(8193, null, ai));
        return ai[0] != 0;
    }

    public void setEnabled(boolean flag)
    {
        int i = 1;
        int ai[] = new int[i];
        if (!flag)
        {
            i = 0;
        }
        ai[0] = i;
        checkStatus(setAudioEffectParam(8193, ai));
    }

    public void setStereoWideningMode(int i)
    {
        int ai[] = {
            i
        };
        if (i < 0 || 2 < i)
        {
            checkStatus(-4);
            return;
        } else
        {
            checkStatus(setAudioEffectParam(8194, ai));
            return;
        }
    }
}
