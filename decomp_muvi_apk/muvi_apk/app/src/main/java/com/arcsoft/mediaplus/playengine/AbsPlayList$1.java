// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            AbsPlayList

static class peatMode
{

    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[];

    static 
    {
        $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode = new int[peatMode.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[peatMode.RepeatAll.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[peatMode.RepeatOne.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$AbsPlayList$RepeatMode[peatMode.NoRepeat.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}
