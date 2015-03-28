// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;


// Referenced classes of package com.arcsoft.videotrim.Utils:
//            SystemEventManager

static class 
{

    static final int $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[];

    static 
    {
        $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent = new int[.values().length];
        try
        {
            $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[.MOUNTED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[.UNMOUNTED.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[.EJECT.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[.SCANNER_STARTED.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$videotrim$Utils$Constants$SDCardEvent[.SCANNER_FINISHED.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4)
        {
            return;
        }
    }
}
