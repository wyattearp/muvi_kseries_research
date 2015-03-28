// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferStateMachine

static class ate
{

    static final int $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[];
    static final int $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[];

    static 
    {
        $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action = new int[tion.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[tion.BROWSE.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[tion.BUILD.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[tion.COMPLETE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[tion.CANCEL.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$Action[tion.DELETE.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State = new int[ate.values().length];
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.BROWSING.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.BROWSED.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.BUILDING.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.BUILT.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.COMPLETING.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.COMPLETED.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.CANCELLED.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.DELETED.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            $SwitchMap$com$arcsoft$mediaplus$updownload$easytransfer$EasyTransferStateMachine$State[ate.FAULT.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror13)
        {
            return;
        }
    }
}
