// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;


// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback

static class PlayerError
{

    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[];

    static 
    {
        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError = new int[com.arcsoft.mediaplus.playengine.rror.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.rror.ERROR_RENDER_DISCONNECTED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.rror.ERROR_UNSUPPORT.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.rror.ERROR_OPENFILE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.rror.ERROR_PLAY.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.rror.ERROR_PLAYLIST_URI_NULL.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4)
        {
            return;
        }
    }
}
