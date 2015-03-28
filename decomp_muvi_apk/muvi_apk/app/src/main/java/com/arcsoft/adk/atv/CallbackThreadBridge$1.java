// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            CallbackThreadBridge

static class llbackData.CallbackType
{

    static final int $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[];

    static 
    {
        $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType = new int[llbackData.CallbackType.values().length];
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDirContentUpdated.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetSortCapabilities.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetSearchCapabilities.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnServerAdded.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnServerRemoved.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnServerGetProtocolInfo.ordinal()] = 6;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnXGetDLNAUploadProfiles.ordinal()] = 7;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetCurrentTransportActions.ordinal()] = 8;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetMeidaInfo.ordinal()] = 9;
        }
        catch (NoSuchFieldError nosuchfielderror8) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetMute.ordinal()] = 10;
        }
        catch (NoSuchFieldError nosuchfielderror9) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetPositionInfo.ordinal()] = 11;
        }
        catch (NoSuchFieldError nosuchfielderror10) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetProtocolInfo.ordinal()] = 12;
        }
        catch (NoSuchFieldError nosuchfielderror11) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetTransportInfo.ordinal()] = 13;
        }
        catch (NoSuchFieldError nosuchfielderror12) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetTransportSettings.ordinal()] = 14;
        }
        catch (NoSuchFieldError nosuchfielderror13) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnGetVolume.ordinal()] = 15;
        }
        catch (NoSuchFieldError nosuchfielderror14) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaPause.ordinal()] = 16;
        }
        catch (NoSuchFieldError nosuchfielderror15) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaPlay.ordinal()] = 17;
        }
        catch (NoSuchFieldError nosuchfielderror16) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaSeek.ordinal()] = 18;
        }
        catch (NoSuchFieldError nosuchfielderror17) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaNext.ordinal()] = 19;
        }
        catch (NoSuchFieldError nosuchfielderror18) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaPrevious.ordinal()] = 20;
        }
        catch (NoSuchFieldError nosuchfielderror19) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnMediaStop.ordinal()] = 21;
        }
        catch (NoSuchFieldError nosuchfielderror20) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnRenderAdded.ordinal()] = 22;
        }
        catch (NoSuchFieldError nosuchfielderror21) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnRenderInstalled.ordinal()] = 23;
        }
        catch (NoSuchFieldError nosuchfielderror22) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnRenderRemoved.ordinal()] = 24;
        }
        catch (NoSuchFieldError nosuchfielderror23) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnSetAVTransportURI.ordinal()] = 25;
        }
        catch (NoSuchFieldError nosuchfielderror24) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnSetMute.ordinal()] = 26;
        }
        catch (NoSuchFieldError nosuchfielderror25) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnSetVolume.ordinal()] = 27;
        }
        catch (NoSuchFieldError nosuchfielderror26) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnBrowseRequest.ordinal()] = 28;
        }
        catch (NoSuchFieldError nosuchfielderror27) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnUploadResult.ordinal()] = 29;
        }
        catch (NoSuchFieldError nosuchfielderror28) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaXP9eGetContainerIds.ordinal()] = 30;
        }
        catch (NoSuchFieldError nosuchfielderror29) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaCreateRecordSchedule.ordinal()] = 31;
        }
        catch (NoSuchFieldError nosuchfielderror30) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaBrowseRecordTasks.ordinal()] = 32;
        }
        catch (NoSuchFieldError nosuchfielderror31) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaDeleteRecordSchedule.ordinal()] = 33;
        }
        catch (NoSuchFieldError nosuchfielderror32) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaDisableRecordSchedule.ordinal()] = 34;
        }
        catch (NoSuchFieldError nosuchfielderror33) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDigaEnableRecordSchedule.ordinal()] = 35;
        }
        catch (NoSuchFieldError nosuchfielderror34) { }
        try
        {
            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[llbackData.CallbackType.OnDestroyObject.ordinal()] = 36;
        }
        catch (NoSuchFieldError nosuchfielderror35)
        {
            return;
        }
    }
}
