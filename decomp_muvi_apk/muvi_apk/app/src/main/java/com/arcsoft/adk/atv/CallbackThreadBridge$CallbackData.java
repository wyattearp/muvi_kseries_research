// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            CallbackThreadBridge

static interface CallbackType
{
    public static final class CallbackType extends Enum
    {

        private static final CallbackType $VALUES[];
        public static final CallbackType OnBrowseRequest;
        public static final CallbackType OnDLNA;
        public static final CallbackType OnDestroyObject;
        public static final CallbackType OnDigaBrowseRecordTasks;
        public static final CallbackType OnDigaCreateRecordSchedule;
        public static final CallbackType OnDigaDeleteRecordSchedule;
        public static final CallbackType OnDigaDisableRecordSchedule;
        public static final CallbackType OnDigaEnableRecordSchedule;
        public static final CallbackType OnDigaXP9eGetContainerIds;
        public static final CallbackType OnDirContentUpdated;
        public static final CallbackType OnGetCurrentTransportActions;
        public static final CallbackType OnGetMeidaInfo;
        public static final CallbackType OnGetMute;
        public static final CallbackType OnGetPositionInfo;
        public static final CallbackType OnGetProtocolInfo;
        public static final CallbackType OnGetSearchCapabilities;
        public static final CallbackType OnGetSortCapabilities;
        public static final CallbackType OnGetTransportInfo;
        public static final CallbackType OnGetTransportSettings;
        public static final CallbackType OnGetVolume;
        public static final CallbackType OnMediaNext;
        public static final CallbackType OnMediaPause;
        public static final CallbackType OnMediaPlay;
        public static final CallbackType OnMediaPrevious;
        public static final CallbackType OnMediaSeek;
        public static final CallbackType OnMediaStop;
        public static final CallbackType OnRenderAdded;
        public static final CallbackType OnRenderInstalled;
        public static final CallbackType OnRenderRemoved;
        public static final CallbackType OnServerAdded;
        public static final CallbackType OnServerGetProtocolInfo;
        public static final CallbackType OnServerRemoved;
        public static final CallbackType OnSetAVTransportURI;
        public static final CallbackType OnSetMute;
        public static final CallbackType OnSetVolume;
        public static final CallbackType OnUploadResult;
        public static final CallbackType OnXGetDLNAUploadProfiles;

        public static CallbackType valueOf(String s)
        {
            return (CallbackType)Enum.valueOf(com/arcsoft/adk/atv/CallbackThreadBridge$CallbackData$CallbackType, s);
        }

        public static CallbackType[] values()
        {
            return (CallbackType[])$VALUES.clone();
        }

        static 
        {
            OnDirContentUpdated = new CallbackType("OnDirContentUpdated", 0);
            OnGetSortCapabilities = new CallbackType("OnGetSortCapabilities", 1);
            OnGetSearchCapabilities = new CallbackType("OnGetSearchCapabilities", 2);
            OnServerAdded = new CallbackType("OnServerAdded", 3);
            OnServerRemoved = new CallbackType("OnServerRemoved", 4);
            OnGetCurrentTransportActions = new CallbackType("OnGetCurrentTransportActions", 5);
            OnGetMeidaInfo = new CallbackType("OnGetMeidaInfo", 6);
            OnGetMute = new CallbackType("OnGetMute", 7);
            OnGetPositionInfo = new CallbackType("OnGetPositionInfo", 8);
            OnGetProtocolInfo = new CallbackType("OnGetProtocolInfo", 9);
            OnServerGetProtocolInfo = new CallbackType("OnServerGetProtocolInfo", 10);
            OnXGetDLNAUploadProfiles = new CallbackType("OnXGetDLNAUploadProfiles", 11);
            OnGetTransportInfo = new CallbackType("OnGetTransportInfo", 12);
            OnGetTransportSettings = new CallbackType("OnGetTransportSettings", 13);
            OnGetVolume = new CallbackType("OnGetVolume", 14);
            OnMediaPause = new CallbackType("OnMediaPause", 15);
            OnMediaPlay = new CallbackType("OnMediaPlay", 16);
            OnMediaSeek = new CallbackType("OnMediaSeek", 17);
            OnMediaNext = new CallbackType("OnMediaNext", 18);
            OnMediaPrevious = new CallbackType("OnMediaPrevious", 19);
            OnMediaStop = new CallbackType("OnMediaStop", 20);
            OnRenderAdded = new CallbackType("OnRenderAdded", 21);
            OnRenderInstalled = new CallbackType("OnRenderInstalled", 22);
            OnRenderRemoved = new CallbackType("OnRenderRemoved", 23);
            OnSetAVTransportURI = new CallbackType("OnSetAVTransportURI", 24);
            OnSetMute = new CallbackType("OnSetMute", 25);
            OnSetVolume = new CallbackType("OnSetVolume", 26);
            OnBrowseRequest = new CallbackType("OnBrowseRequest", 27);
            OnUploadResult = new CallbackType("OnUploadResult", 28);
            OnDLNA = new CallbackType("OnDLNA", 29);
            OnDigaXP9eGetContainerIds = new CallbackType("OnDigaXP9eGetContainerIds", 30);
            OnDigaCreateRecordSchedule = new CallbackType("OnDigaCreateRecordSchedule", 31);
            OnDigaBrowseRecordTasks = new CallbackType("OnDigaBrowseRecordTasks", 32);
            OnDigaDeleteRecordSchedule = new CallbackType("OnDigaDeleteRecordSchedule", 33);
            OnDigaDisableRecordSchedule = new CallbackType("OnDigaDisableRecordSchedule", 34);
            OnDigaEnableRecordSchedule = new CallbackType("OnDigaEnableRecordSchedule", 35);
            OnDestroyObject = new CallbackType("OnDestroyObject", 36);
            CallbackType acallbacktype[] = new CallbackType[37];
            acallbacktype[0] = OnDirContentUpdated;
            acallbacktype[1] = OnGetSortCapabilities;
            acallbacktype[2] = OnGetSearchCapabilities;
            acallbacktype[3] = OnServerAdded;
            acallbacktype[4] = OnServerRemoved;
            acallbacktype[5] = OnGetCurrentTransportActions;
            acallbacktype[6] = OnGetMeidaInfo;
            acallbacktype[7] = OnGetMute;
            acallbacktype[8] = OnGetPositionInfo;
            acallbacktype[9] = OnGetProtocolInfo;
            acallbacktype[10] = OnServerGetProtocolInfo;
            acallbacktype[11] = OnXGetDLNAUploadProfiles;
            acallbacktype[12] = OnGetTransportInfo;
            acallbacktype[13] = OnGetTransportSettings;
            acallbacktype[14] = OnGetVolume;
            acallbacktype[15] = OnMediaPause;
            acallbacktype[16] = OnMediaPlay;
            acallbacktype[17] = OnMediaSeek;
            acallbacktype[18] = OnMediaNext;
            acallbacktype[19] = OnMediaPrevious;
            acallbacktype[20] = OnMediaStop;
            acallbacktype[21] = OnRenderAdded;
            acallbacktype[22] = OnRenderInstalled;
            acallbacktype[23] = OnRenderRemoved;
            acallbacktype[24] = OnSetAVTransportURI;
            acallbacktype[25] = OnSetMute;
            acallbacktype[26] = OnSetVolume;
            acallbacktype[27] = OnBrowseRequest;
            acallbacktype[28] = OnUploadResult;
            acallbacktype[29] = OnDLNA;
            acallbacktype[30] = OnDigaXP9eGetContainerIds;
            acallbacktype[31] = OnDigaCreateRecordSchedule;
            acallbacktype[32] = OnDigaBrowseRecordTasks;
            acallbacktype[33] = OnDigaDeleteRecordSchedule;
            acallbacktype[34] = OnDigaDisableRecordSchedule;
            acallbacktype[35] = OnDigaEnableRecordSchedule;
            acallbacktype[36] = OnDestroyObject;
            $VALUES = acallbacktype;
        }

        private CallbackType(String s, int i)
        {
            super(s, i);
        }
    }


    public abstract CallbackType getType();
}
