// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

static class atus
{

    static final int $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[];

    static 
    {
        $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status = new int[atus.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.FAULT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.STOPPING.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.STOPED.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.CANCELING.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.CANCELED.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.OPENING.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.OPENED.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.STARTINGPLAY.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.PLAYING.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.PAUSING.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.PAUSED.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$playengine$DMCPlayEngineStatusMachine$Status[atus.SEEKING.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror11)
        {
            return;
        }
    }
}
