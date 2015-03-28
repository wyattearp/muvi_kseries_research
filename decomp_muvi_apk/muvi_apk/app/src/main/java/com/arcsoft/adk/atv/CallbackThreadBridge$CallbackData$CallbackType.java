// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            CallbackThreadBridge

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES OnBrowseRequest;
    public static final .VALUES OnDLNA;
    public static final .VALUES OnDestroyObject;
    public static final .VALUES OnDigaBrowseRecordTasks;
    public static final .VALUES OnDigaCreateRecordSchedule;
    public static final .VALUES OnDigaDeleteRecordSchedule;
    public static final .VALUES OnDigaDisableRecordSchedule;
    public static final .VALUES OnDigaEnableRecordSchedule;
    public static final .VALUES OnDigaXP9eGetContainerIds;
    public static final .VALUES OnDirContentUpdated;
    public static final .VALUES OnGetCurrentTransportActions;
    public static final .VALUES OnGetMeidaInfo;
    public static final .VALUES OnGetMute;
    public static final .VALUES OnGetPositionInfo;
    public static final .VALUES OnGetProtocolInfo;
    public static final .VALUES OnGetSearchCapabilities;
    public static final .VALUES OnGetSortCapabilities;
    public static final .VALUES OnGetTransportInfo;
    public static final .VALUES OnGetTransportSettings;
    public static final .VALUES OnGetVolume;
    public static final .VALUES OnMediaNext;
    public static final .VALUES OnMediaPause;
    public static final .VALUES OnMediaPlay;
    public static final .VALUES OnMediaPrevious;
    public static final .VALUES OnMediaSeek;
    public static final .VALUES OnMediaStop;
    public static final .VALUES OnRenderAdded;
    public static final .VALUES OnRenderInstalled;
    public static final .VALUES OnRenderRemoved;
    public static final .VALUES OnServerAdded;
    public static final .VALUES OnServerGetProtocolInfo;
    public static final .VALUES OnServerRemoved;
    public static final .VALUES OnSetAVTransportURI;
    public static final .VALUES OnSetMute;
    public static final .VALUES OnSetVolume;
    public static final .VALUES OnUploadResult;
    public static final .VALUES OnXGetDLNAUploadProfiles;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/adk/atv/CallbackThreadBridge$CallbackData$CallbackType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        OnDirContentUpdated = new <init>("OnDirContentUpdated", 0);
        OnGetSortCapabilities = new <init>("OnGetSortCapabilities", 1);
        OnGetSearchCapabilities = new <init>("OnGetSearchCapabilities", 2);
        OnServerAdded = new <init>("OnServerAdded", 3);
        OnServerRemoved = new <init>("OnServerRemoved", 4);
        OnGetCurrentTransportActions = new <init>("OnGetCurrentTransportActions", 5);
        OnGetMeidaInfo = new <init>("OnGetMeidaInfo", 6);
        OnGetMute = new <init>("OnGetMute", 7);
        OnGetPositionInfo = new <init>("OnGetPositionInfo", 8);
        OnGetProtocolInfo = new <init>("OnGetProtocolInfo", 9);
        OnServerGetProtocolInfo = new <init>("OnServerGetProtocolInfo", 10);
        OnXGetDLNAUploadProfiles = new <init>("OnXGetDLNAUploadProfiles", 11);
        OnGetTransportInfo = new <init>("OnGetTransportInfo", 12);
        OnGetTransportSettings = new <init>("OnGetTransportSettings", 13);
        OnGetVolume = new <init>("OnGetVolume", 14);
        OnMediaPause = new <init>("OnMediaPause", 15);
        OnMediaPlay = new <init>("OnMediaPlay", 16);
        OnMediaSeek = new <init>("OnMediaSeek", 17);
        OnMediaNext = new <init>("OnMediaNext", 18);
        OnMediaPrevious = new <init>("OnMediaPrevious", 19);
        OnMediaStop = new <init>("OnMediaStop", 20);
        OnRenderAdded = new <init>("OnRenderAdded", 21);
        OnRenderInstalled = new <init>("OnRenderInstalled", 22);
        OnRenderRemoved = new <init>("OnRenderRemoved", 23);
        OnSetAVTransportURI = new <init>("OnSetAVTransportURI", 24);
        OnSetMute = new <init>("OnSetMute", 25);
        OnSetVolume = new <init>("OnSetVolume", 26);
        OnBrowseRequest = new <init>("OnBrowseRequest", 27);
        OnUploadResult = new <init>("OnUploadResult", 28);
        OnDLNA = new <init>("OnDLNA", 29);
        OnDigaXP9eGetContainerIds = new <init>("OnDigaXP9eGetContainerIds", 30);
        OnDigaCreateRecordSchedule = new <init>("OnDigaCreateRecordSchedule", 31);
        OnDigaBrowseRecordTasks = new <init>("OnDigaBrowseRecordTasks", 32);
        OnDigaDeleteRecordSchedule = new <init>("OnDigaDeleteRecordSchedule", 33);
        OnDigaDisableRecordSchedule = new <init>("OnDigaDisableRecordSchedule", 34);
        OnDigaEnableRecordSchedule = new <init>("OnDigaEnableRecordSchedule", 35);
        OnDestroyObject = new <init>("OnDestroyObject", 36);
        e_3B_.clone aclone[] = new <init>[37];
        aclone[0] = OnDirContentUpdated;
        aclone[1] = OnGetSortCapabilities;
        aclone[2] = OnGetSearchCapabilities;
        aclone[3] = OnServerAdded;
        aclone[4] = OnServerRemoved;
        aclone[5] = OnGetCurrentTransportActions;
        aclone[6] = OnGetMeidaInfo;
        aclone[7] = OnGetMute;
        aclone[8] = OnGetPositionInfo;
        aclone[9] = OnGetProtocolInfo;
        aclone[10] = OnServerGetProtocolInfo;
        aclone[11] = OnXGetDLNAUploadProfiles;
        aclone[12] = OnGetTransportInfo;
        aclone[13] = OnGetTransportSettings;
        aclone[14] = OnGetVolume;
        aclone[15] = OnMediaPause;
        aclone[16] = OnMediaPlay;
        aclone[17] = OnMediaSeek;
        aclone[18] = OnMediaNext;
        aclone[19] = OnMediaPrevious;
        aclone[20] = OnMediaStop;
        aclone[21] = OnRenderAdded;
        aclone[22] = OnRenderInstalled;
        aclone[23] = OnRenderRemoved;
        aclone[24] = OnSetAVTransportURI;
        aclone[25] = OnSetMute;
        aclone[26] = OnSetVolume;
        aclone[27] = OnBrowseRequest;
        aclone[28] = OnUploadResult;
        aclone[29] = OnDLNA;
        aclone[30] = OnDigaXP9eGetContainerIds;
        aclone[31] = OnDigaCreateRecordSchedule;
        aclone[32] = OnDigaBrowseRecordTasks;
        aclone[33] = OnDigaDeleteRecordSchedule;
        aclone[34] = OnDigaDisableRecordSchedule;
        aclone[35] = OnDigaEnableRecordSchedule;
        aclone[36] = OnDestroyObject;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
