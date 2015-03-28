// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer

static class PLAYSTATUS
{

    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[];
    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[];

    static 
    {
        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError = new int[PlayEngineError.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_RENDER_DISCONNECTED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_SERVER_DISCONNECTED.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_UNKNOWN.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_OPENFILE.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_PLAY.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_PAUSE.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_SEEK.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_STOP.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_GETVOLUME.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_GETMUTE.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PlayEngineError[PlayEngineError.ERROR_UNSUPPORT.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS = new int[PLAYSTATUS.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_IDLE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_OPENING.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_OPENED.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_STARTINGPLAY.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_PLAYING.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_BUFFERING.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_PAUSING.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_PAUSED.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_STOPPING.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_STOPPED.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayEngine$PLAYSTATUS[PLAYSTATUS.STATUS_SEEKING.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror21)
        {
            return;
        }
    }
}
