// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            BrowseCallback, MRCPCallback, MSCPCallback, UPCPCallback

class CallbackThreadBridge
{
    class BdBrowseCallback
        implements BrowseCallback
    {

        final CallbackThreadBridge this$0;

        public void OnBrowseRequest(BrowseCallback.DataOnBrowseRequest dataonbrowserequest, BrowseCallback.DataOnBrowseRequestRsp dataonbrowserequestrsp)
        {
            BdOnBrowseRequest bdonbrowserequest = new BdOnBrowseRequest();
            bdonbrowserequest.mDataOnBrowseRequest = dataonbrowserequest;
            bdonbrowserequest.mDataOnBrowseRequestRsp = dataonbrowserequestrsp;
            Message message = new Message();
            message.obj = bdonbrowserequest;
            mHandler.sendMessage(message);
        }

        BdBrowseCallback()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdMRCPCallback
        implements MRCPCallback
    {

        final CallbackThreadBridge this$0;

        public void OnGetCurrentTransportActions(int i, String s, String s1)
        {
            BdOnGetCurrentTransportActions bdongetcurrenttransportactions = new BdOnGetCurrentTransportActions();
            bdongetcurrenttransportactions.mnErrorCode = i;
            bdongetcurrenttransportactions.mstrAllowedActions = s;
            bdongetcurrenttransportactions.mlUpdateId = s1;
            Message message = new Message();
            message.obj = bdongetcurrenttransportactions;
            mHandler.sendMessage(message);
        }

        public void OnGetMeidaInfo(int i, MRCPCallback.DataOnGetMediaInfo dataongetmediainfo, String s)
        {
            BdOnGetMeidaInfo bdongetmeidainfo = new BdOnGetMeidaInfo();
            bdongetmeidainfo.mnErrorCode = i;
            bdongetmeidainfo.mDataOnGetMediaInfo = dataongetmediainfo;
            bdongetmeidainfo.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongetmeidainfo;
            mHandler.sendMessage(message);
        }

        public void OnGetMute(int i, boolean flag, String s)
        {
            BdOnGetMute bdongetmute = new BdOnGetMute();
            bdongetmute.mnErrorCode = i;
            bdongetmute.mbMute = flag;
            bdongetmute.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongetmute;
            mHandler.sendMessage(message);
        }

        public void OnGetPositionInfo(int i, MRCPCallback.DataOnGetPositionInfo dataongetpositioninfo, String s)
        {
            BdOnGetPositionInfo bdongetpositioninfo = new BdOnGetPositionInfo();
            bdongetpositioninfo.mnErrorCode = i;
            bdongetpositioninfo.mDataOnGetPositionInfo = dataongetpositioninfo;
            bdongetpositioninfo.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongetpositioninfo;
            mHandler.sendMessage(message);
        }

        public void OnGetProtocolInfo(int i, MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, String s)
        {
            BdOnGetProtocolInfo bdongetprotocolinfo = new BdOnGetProtocolInfo();
            bdongetprotocolinfo.mnErrorCode = i;
            bdongetprotocolinfo.mDataOnGetProtocolInfo = dataongetprotocolinfo;
            bdongetprotocolinfo.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongetprotocolinfo;
            mHandler.sendMessage(message);
        }

        public void OnGetTransportInfo(int i, MRCPCallback.DataOnGetTransportInfo dataongettransportinfo, String s)
        {
            BdOnGetTransportInfo bdongettransportinfo = new BdOnGetTransportInfo();
            bdongettransportinfo.mnErrorCode = i;
            bdongettransportinfo.mDataOnGetTransportInfo = dataongettransportinfo;
            bdongettransportinfo.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongettransportinfo;
            mHandler.sendMessage(message);
        }

        public void OnGetTransportSettings(int i, MRCPCallback.DataOnGetTransportSettings dataongettransportsettings, String s)
        {
            BdOnGetTransportSettings bdongettransportsettings = new BdOnGetTransportSettings();
            bdongettransportsettings.mnErrorCode = i;
            bdongettransportsettings.mDataOnGetTransportSettings = dataongettransportsettings;
            bdongettransportsettings.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongettransportsettings;
            mHandler.sendMessage(message);
        }

        public void OnGetVolume(int i, int j, String s)
        {
            BdOnGetVolume bdongetvolume = new BdOnGetVolume();
            bdongetvolume.mnErrorCode = i;
            bdongetvolume.muiCurVolume = j;
            bdongetvolume.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdongetvolume;
            mHandler.sendMessage(message);
        }

        public void OnMediaNext(int i, String s)
        {
            BdOnMediaSeek bdonmediaseek = new BdOnMediaSeek();
            bdonmediaseek.mnErrorCode = i;
            bdonmediaseek.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediaseek;
            mHandler.sendMessage(message);
        }

        public void OnMediaPause(int i, String s)
        {
            BdOnMediaPause bdonmediapause = new BdOnMediaPause();
            bdonmediapause.mnErrorCode = i;
            bdonmediapause.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediapause;
            mHandler.sendMessage(message);
        }

        public void OnMediaPlay(int i, String s)
        {
            BdOnMediaPlay bdonmediaplay = new BdOnMediaPlay();
            bdonmediaplay.mnErrorCode = i;
            bdonmediaplay.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediaplay;
            mHandler.sendMessage(message);
        }

        public void OnMediaPrevious(int i, String s)
        {
            BdOnMediaPrevious bdonmediaprevious = new BdOnMediaPrevious();
            bdonmediaprevious.mnErrorCode = i;
            bdonmediaprevious.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediaprevious;
            mHandler.sendMessage(message);
        }

        public void OnMediaSeek(int i, String s)
        {
            BdOnMediaSeek bdonmediaseek = new BdOnMediaSeek();
            bdonmediaseek.mnErrorCode = i;
            bdonmediaseek.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediaseek;
            mHandler.sendMessage(message);
        }

        public void OnMediaStop(int i, String s)
        {
            BdOnMediaStop bdonmediastop = new BdOnMediaStop();
            bdonmediastop.mnErrorCode = i;
            bdonmediastop.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonmediastop;
            mHandler.sendMessage(message);
        }

        public void OnRenderAdded(MRCPCallback.DataOnRenderAdded dataonrenderadded)
        {
            BdOnRenderAdded bdonrenderadded = new BdOnRenderAdded();
            bdonrenderadded.mDataOnRenderAdded = dataonrenderadded;
            Message message = new Message();
            message.obj = bdonrenderadded;
            mHandler.sendMessage(message);
        }

        public void OnRenderInstalled(UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
        {
            BdOnRenderInstalled bdonrenderinstalled = new BdOnRenderInstalled();
            bdonrenderinstalled.mMediaRenderDesc = mediarenderdesc;
            bdonrenderinstalled.mbCm = flag;
            bdonrenderinstalled.mbAvt = flag1;
            bdonrenderinstalled.mbRcl = flag2;
            Message message = new Message();
            message.obj = bdonrenderinstalled;
            mHandler.sendMessage(message);
        }

        public void OnRenderRemoved(MRCPCallback.DataOnRenderRemoved dataonrenderremoved)
        {
            BdOnRenderRemoved bdonrenderremoved = new BdOnRenderRemoved();
            bdonrenderremoved.mDataOnRenderRemoved = dataonrenderremoved;
            Message message = new Message();
            message.obj = bdonrenderremoved;
            mHandler.sendMessage(message);
        }

        public void OnSetAVTransportURI(int i, String s)
        {
            BdOnSetAVTransportURI bdonsetavtransporturi = new BdOnSetAVTransportURI();
            bdonsetavtransporturi.mnErrorCode = i;
            bdonsetavtransporturi.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonsetavtransporturi;
            mHandler.sendMessage(message);
        }

        public void OnSetMute(int i, String s)
        {
            BdOnSetMute bdonsetmute = new BdOnSetMute();
            bdonsetmute.mnErrorCode = i;
            bdonsetmute.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonsetmute;
            mHandler.sendMessage(message);
        }

        public void OnSetVolume(int i, String s)
        {
            BdOnSetVolume bdonsetvolume = new BdOnSetVolume();
            bdonsetvolume.mnErrorCode = i;
            bdonsetvolume.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonsetvolume;
            mHandler.sendMessage(message);
        }

        BdMRCPCallback()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdMSCPCallBack
        implements MSCPCallback
    {

        final CallbackThreadBridge this$0;

        public void OnDestroyObject(int i, String s)
        {
            BdOnDestroyObject bdondestroyobject = new BdOnDestroyObject();
            bdondestroyobject.nErrorCode = i;
            bdondestroyobject.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondestroyobject;
            mHandler.sendMessage(message);
        }

        public void OnDigaBrowseRecordTasks(int i, MSCPCallback.DataOnRecordTasks dataonrecordtasks, String s)
        {
            BdOnDigaBrowseRecordTasks bdondigabrowserecordtasks = new BdOnDigaBrowseRecordTasks();
            bdondigabrowserecordtasks.nErrorCode = i;
            bdondigabrowserecordtasks.data = dataonrecordtasks;
            bdondigabrowserecordtasks.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondigabrowserecordtasks;
            mHandler.sendMessage(message);
        }

        public void OnDigaCreateRecordSchedule(int i, MSCPCallback.DataOnRecordSchedule dataonrecordschedule, String s)
        {
            BdOnDigaCreateRecordSchedule bdondigacreaterecordschedule = new BdOnDigaCreateRecordSchedule();
            bdondigacreaterecordschedule.nErrorCode = i;
            bdondigacreaterecordschedule.data = dataonrecordschedule;
            bdondigacreaterecordschedule.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondigacreaterecordschedule;
            mHandler.sendMessage(message);
        }

        public void OnDigaDeleteRecordSchedule(int i, String s)
        {
            BdOnDigaDeleteRecordSchedule bdondigadeleterecordschedule = new BdOnDigaDeleteRecordSchedule();
            bdondigadeleterecordschedule.nErrorCode = i;
            bdondigadeleterecordschedule.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondigadeleterecordschedule;
            mHandler.sendMessage(message);
        }

        public void OnDigaDisableRecordSchedule(int i, String s)
        {
            BdOnDigaDisableRecordSchedule bdondigadisablerecordschedule = new BdOnDigaDisableRecordSchedule();
            bdondigadisablerecordschedule.nErrorCode = i;
            bdondigadisablerecordschedule.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondigadisablerecordschedule;
            mHandler.sendMessage(message);
        }

        public void OnDigaEnableRecordSchedule(int i, String s)
        {
            BdOnDigaEnableRecordSchedule bdondigaenablerecordschedule = new BdOnDigaEnableRecordSchedule();
            bdondigaenablerecordschedule.nErrorCode = i;
            bdondigaenablerecordschedule.lUpdateId = s;
            Message message = new Message();
            message.obj = bdondigaenablerecordschedule;
            mHandler.sendMessage(message);
        }

        public void OnDigaXP9eGetContainerIds(int i, String s, String s1)
        {
            BdOnDigaXP9eGetContainerIds bdondigaxp9egetcontainerids = new BdOnDigaXP9eGetContainerIds();
            bdondigaxp9egetcontainerids.nErrorCode = i;
            bdondigaxp9egetcontainerids.strContainerIds = s;
            bdondigaxp9egetcontainerids.lUpdateId = s1;
            Message message = new Message();
            message.obj = bdondigaxp9egetcontainerids;
            mHandler.sendMessage(message);
        }

        public void OnDirContentUpdated(MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1)
        {
            BdOnDirContentUpdated bdondircontentupdated = new BdOnDirContentUpdated();
            bdondircontentupdated.mDataOnDirContentUpdated = dataondircontentupdated;
            bdondircontentupdated.mstrDeviceId = s;
            bdondircontentupdated.mstrObjId = s1;
            Message message = new Message();
            message.obj = bdondircontentupdated;
            message.what = dataondircontentupdated.m_nUpdateId;
            Log.e("CallbackThreadBridge", (new StringBuilder()).append("        OnDirMSCP callback msg.what = ").append(dataondircontentupdated.m_nUpdateId).toString());
            mHandler.sendMessage(message);
        }

        public void OnGetSearchCapabilities(int i, String s, String s1)
        {
            BdOnGetSearchCapabilities bdongetsearchcapabilities = new BdOnGetSearchCapabilities();
            bdongetsearchcapabilities.mnErrorCode = i;
            bdongetsearchcapabilities.mstrSearchCaps = s;
            bdongetsearchcapabilities.mlUpdateId = s1;
            Message message = new Message();
            message.obj = bdongetsearchcapabilities;
            mHandler.sendMessage(message);
        }

        public void OnGetSortCapabilities(int i, String s, String s1)
        {
            BdOnGetSortCapabilities bdongetsortcapabilities = new BdOnGetSortCapabilities();
            bdongetsortcapabilities.mnErrorCode = i;
            bdongetsortcapabilities.mstrSortCaps = s;
            bdongetsortcapabilities.mlUpdateId = s1;
            Message message = new Message();
            message.obj = bdongetsortcapabilities;
            mHandler.sendMessage(message);
        }

        public void OnServerAdded(MSCPCallback.DataOnServerAdded dataonserveradded)
        {
            BdOnServerAdded bdonserveradded = new BdOnServerAdded();
            bdonserveradded.mDataOnServerAdded = dataonserveradded;
            Message message = new Message();
            message.obj = bdonserveradded;
            mHandler.sendMessage(message);
        }

        public void OnServerGetProtocolInfo(int i, MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, String s)
        {
            BdOnServerGetProtocolInfo bdonservergetprotocolinfo = new BdOnServerGetProtocolInfo();
            bdonservergetprotocolinfo.mnErrorCode = i;
            bdonservergetprotocolinfo.mDataOnGetProtocolInfo = dataonservergetprotocolinfo;
            bdonservergetprotocolinfo.mlUpdateId = s;
            Message message = new Message();
            message.obj = bdonservergetprotocolinfo;
            mHandler.sendMessage(message);
        }

        public void OnServerRemoved(MSCPCallback.DataOnServerRemoved dataonserverremoved)
        {
            BdOnServerRemoved bdonserverremoved = new BdOnServerRemoved();
            bdonserverremoved.mDataOnServerRemoved = dataonserverremoved;
            Message message = new Message();
            message.obj = bdonserverremoved;
            mHandler.sendMessage(message);
        }

        public void OnXGetDLNAUploadProfiles(int i, String s, String s1)
        {
            BdOnXGetDLNAUploadProfiles bdonxgetdlnauploadprofiles = new BdOnXGetDLNAUploadProfiles();
            bdonxgetdlnauploadprofiles.mnErrorCode = i;
            bdonxgetdlnauploadprofiles.mstrUploadProfiles = s;
            bdonxgetdlnauploadprofiles.mlUpdateId = s1;
            Message message = new Message();
            message.obj = bdonxgetdlnauploadprofiles;
            mHandler.sendMessage(message);
        }

        BdMSCPCallBack()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnBrowseRequest
        implements CallbackData
    {

        BrowseCallback.DataOnBrowseRequest mDataOnBrowseRequest;
        BrowseCallback.DataOnBrowseRequestRsp mDataOnBrowseRequestRsp;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnBrowseRequest;
        }

        BdOnBrowseRequest()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDestroyObject
        implements CallbackData
    {

        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDestroyObject;
        }

        BdOnDestroyObject()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaBrowseRecordTasks
        implements CallbackData
    {

        MSCPCallback.DataOnRecordTasks data;
        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaBrowseRecordTasks;
        }

        BdOnDigaBrowseRecordTasks()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaCreateRecordSchedule
        implements CallbackData
    {

        MSCPCallback.DataOnRecordSchedule data;
        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaCreateRecordSchedule;
        }

        BdOnDigaCreateRecordSchedule()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaDeleteRecordSchedule
        implements CallbackData
    {

        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaDeleteRecordSchedule;
        }

        BdOnDigaDeleteRecordSchedule()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaDisableRecordSchedule
        implements CallbackData
    {

        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaDisableRecordSchedule;
        }

        BdOnDigaDisableRecordSchedule()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaEnableRecordSchedule
        implements CallbackData
    {

        String lUpdateId;
        int nErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaEnableRecordSchedule;
        }

        BdOnDigaEnableRecordSchedule()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDigaXP9eGetContainerIds
        implements CallbackData
    {

        String lUpdateId;
        int nErrorCode;
        String strContainerIds;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDigaXP9eGetContainerIds;
        }

        BdOnDigaXP9eGetContainerIds()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnDirContentUpdated
        implements CallbackData
    {

        MSCPCallback.DataOnDirContentUpdated mDataOnDirContentUpdated;
        String mstrDeviceId;
        String mstrObjId;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnDirContentUpdated;
        }

        BdOnDirContentUpdated()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetCurrentTransportActions
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        String mstrAllowedActions;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetCurrentTransportActions;
        }

        BdOnGetCurrentTransportActions()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetMeidaInfo
        implements CallbackData
    {

        MRCPCallback.DataOnGetMediaInfo mDataOnGetMediaInfo;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetMeidaInfo;
        }

        BdOnGetMeidaInfo()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetMute
        implements CallbackData
    {

        boolean mbMute;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetMute;
        }

        BdOnGetMute()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetPositionInfo
        implements CallbackData
    {

        MRCPCallback.DataOnGetPositionInfo mDataOnGetPositionInfo;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetPositionInfo;
        }

        BdOnGetPositionInfo()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetProtocolInfo
        implements CallbackData
    {

        MRCPCallback.DataOnGetProtocolInfo mDataOnGetProtocolInfo;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetProtocolInfo;
        }

        BdOnGetProtocolInfo()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetSearchCapabilities
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        String mstrSearchCaps;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetSearchCapabilities;
        }

        BdOnGetSearchCapabilities()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetSortCapabilities
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        String mstrSortCaps;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetSortCapabilities;
        }

        BdOnGetSortCapabilities()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetTransportInfo
        implements CallbackData
    {

        MRCPCallback.DataOnGetTransportInfo mDataOnGetTransportInfo;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetTransportInfo;
        }

        BdOnGetTransportInfo()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetTransportSettings
        implements CallbackData
    {

        MRCPCallback.DataOnGetTransportSettings mDataOnGetTransportSettings;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetTransportSettings;
        }

        BdOnGetTransportSettings()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnGetVolume
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        int muiCurVolume;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnGetVolume;
        }

        BdOnGetVolume()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaNext
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaNext;
        }

        BdOnMediaNext()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaPause
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaPause;
        }

        BdOnMediaPause()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaPlay
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaPlay;
        }

        BdOnMediaPlay()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaPrevious
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaPrevious;
        }

        BdOnMediaPrevious()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaSeek
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaSeek;
        }

        BdOnMediaSeek()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnMediaStop
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnMediaStop;
        }

        BdOnMediaStop()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnRenderAdded
        implements CallbackData
    {

        MRCPCallback.DataOnRenderAdded mDataOnRenderAdded;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnRenderAdded;
        }

        BdOnRenderAdded()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnRenderInstalled
        implements CallbackData
    {

        UPnP.MediaRenderDesc mMediaRenderDesc;
        boolean mbAvt;
        boolean mbCm;
        boolean mbRcl;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnRenderInstalled;
        }

        BdOnRenderInstalled()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnRenderRemoved
        implements CallbackData
    {

        MRCPCallback.DataOnRenderRemoved mDataOnRenderRemoved;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnRenderRemoved;
        }

        BdOnRenderRemoved()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnServerAdded
        implements CallbackData
    {

        MSCPCallback.DataOnServerAdded mDataOnServerAdded;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnServerAdded;
        }

        BdOnServerAdded()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnServerGetProtocolInfo
        implements CallbackData
    {

        MSCPCallback.DataOnServerGetProtocolInfo mDataOnGetProtocolInfo;
        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnServerGetProtocolInfo;
        }

        BdOnServerGetProtocolInfo()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnServerRemoved
        implements CallbackData
    {

        MSCPCallback.DataOnServerRemoved mDataOnServerRemoved;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnServerRemoved;
        }

        BdOnServerRemoved()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnSetAVTransportURI
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnSetAVTransportURI;
        }

        BdOnSetAVTransportURI()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnSetMute
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnSetMute;
        }

        BdOnSetMute()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnSetVolume
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnSetVolume;
        }

        BdOnSetVolume()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnUploadResult
        implements CallbackData
    {

        int mlUploadId;
        int mnErrorCode;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnUploadResult;
        }

        BdOnUploadResult()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdOnXGetDLNAUploadProfiles
        implements CallbackData
    {

        String mlUpdateId;
        int mnErrorCode;
        String mstrUploadProfiles;
        final CallbackThreadBridge this$0;

        public CallbackData.CallbackType getType()
        {
            return CallbackData.CallbackType.OnXGetDLNAUploadProfiles;
        }

        BdOnXGetDLNAUploadProfiles()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BdUPCPCallback
        implements UPCPCallback
    {

        final CallbackThreadBridge this$0;

        public void OnUploadResult(int i, int j)
        {
            BdOnUploadResult bdonuploadresult = new BdOnUploadResult();
            bdonuploadresult.mnErrorCode = j;
            bdonuploadresult.mlUploadId = i;
            Message message = new Message();
            message.obj = bdonuploadresult;
            mHandler.sendMessage(message);
        }

        BdUPCPCallback()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }
    }

    class BridgeHandler extends Handler
    {

        final CallbackThreadBridge this$0;

        public void handleMessage(Message message)
        {
            if (message.obj instanceof CallbackData)
            {
                CallbackData callbackdata = (CallbackData)message.obj;
                static class _cls1
                {

                    static final int $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[];

                    static 
                    {
                        $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType = new int[CallbackData.CallbackType.values().length];
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDirContentUpdated.ordinal()] = 1;
                        }
                        catch (NoSuchFieldError nosuchfielderror) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetSortCapabilities.ordinal()] = 2;
                        }
                        catch (NoSuchFieldError nosuchfielderror1) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetSearchCapabilities.ordinal()] = 3;
                        }
                        catch (NoSuchFieldError nosuchfielderror2) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnServerAdded.ordinal()] = 4;
                        }
                        catch (NoSuchFieldError nosuchfielderror3) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnServerRemoved.ordinal()] = 5;
                        }
                        catch (NoSuchFieldError nosuchfielderror4) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnServerGetProtocolInfo.ordinal()] = 6;
                        }
                        catch (NoSuchFieldError nosuchfielderror5) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnXGetDLNAUploadProfiles.ordinal()] = 7;
                        }
                        catch (NoSuchFieldError nosuchfielderror6) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetCurrentTransportActions.ordinal()] = 8;
                        }
                        catch (NoSuchFieldError nosuchfielderror7) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetMeidaInfo.ordinal()] = 9;
                        }
                        catch (NoSuchFieldError nosuchfielderror8) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetMute.ordinal()] = 10;
                        }
                        catch (NoSuchFieldError nosuchfielderror9) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetPositionInfo.ordinal()] = 11;
                        }
                        catch (NoSuchFieldError nosuchfielderror10) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetProtocolInfo.ordinal()] = 12;
                        }
                        catch (NoSuchFieldError nosuchfielderror11) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetTransportInfo.ordinal()] = 13;
                        }
                        catch (NoSuchFieldError nosuchfielderror12) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetTransportSettings.ordinal()] = 14;
                        }
                        catch (NoSuchFieldError nosuchfielderror13) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnGetVolume.ordinal()] = 15;
                        }
                        catch (NoSuchFieldError nosuchfielderror14) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaPause.ordinal()] = 16;
                        }
                        catch (NoSuchFieldError nosuchfielderror15) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaPlay.ordinal()] = 17;
                        }
                        catch (NoSuchFieldError nosuchfielderror16) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaSeek.ordinal()] = 18;
                        }
                        catch (NoSuchFieldError nosuchfielderror17) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaNext.ordinal()] = 19;
                        }
                        catch (NoSuchFieldError nosuchfielderror18) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaPrevious.ordinal()] = 20;
                        }
                        catch (NoSuchFieldError nosuchfielderror19) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnMediaStop.ordinal()] = 21;
                        }
                        catch (NoSuchFieldError nosuchfielderror20) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnRenderAdded.ordinal()] = 22;
                        }
                        catch (NoSuchFieldError nosuchfielderror21) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnRenderInstalled.ordinal()] = 23;
                        }
                        catch (NoSuchFieldError nosuchfielderror22) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnRenderRemoved.ordinal()] = 24;
                        }
                        catch (NoSuchFieldError nosuchfielderror23) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnSetAVTransportURI.ordinal()] = 25;
                        }
                        catch (NoSuchFieldError nosuchfielderror24) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnSetMute.ordinal()] = 26;
                        }
                        catch (NoSuchFieldError nosuchfielderror25) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnSetVolume.ordinal()] = 27;
                        }
                        catch (NoSuchFieldError nosuchfielderror26) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnBrowseRequest.ordinal()] = 28;
                        }
                        catch (NoSuchFieldError nosuchfielderror27) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnUploadResult.ordinal()] = 29;
                        }
                        catch (NoSuchFieldError nosuchfielderror28) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaXP9eGetContainerIds.ordinal()] = 30;
                        }
                        catch (NoSuchFieldError nosuchfielderror29) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaCreateRecordSchedule.ordinal()] = 31;
                        }
                        catch (NoSuchFieldError nosuchfielderror30) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaBrowseRecordTasks.ordinal()] = 32;
                        }
                        catch (NoSuchFieldError nosuchfielderror31) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaDeleteRecordSchedule.ordinal()] = 33;
                        }
                        catch (NoSuchFieldError nosuchfielderror32) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaDisableRecordSchedule.ordinal()] = 34;
                        }
                        catch (NoSuchFieldError nosuchfielderror33) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDigaEnableRecordSchedule.ordinal()] = 35;
                        }
                        catch (NoSuchFieldError nosuchfielderror34) { }
                        try
                        {
                            $SwitchMap$com$arcsoft$adk$atv$CallbackThreadBridge$CallbackData$CallbackType[CallbackData.CallbackType.OnDestroyObject.ordinal()] = 36;
                        }
                        catch (NoSuchFieldError nosuchfielderror35)
                        {
                            return;
                        }
                    }
                }

                BdOnDestroyObject bdondestroyobject;
                switch (_cls1..SwitchMap.com.arcsoft.adk.atv.CallbackThreadBridge.CallbackData.CallbackType[callbackdata.getType().ordinal()])
                {
                default:
                    return;

                case 1: // '\001'
                    BdOnDirContentUpdated bdondircontentupdated = (BdOnDirContentUpdated)callbackdata;
                    m_RedirectServerCallback.OnDirContentUpdated(bdondircontentupdated.mDataOnDirContentUpdated, bdondircontentupdated.mstrDeviceId, bdondircontentupdated.mstrObjId);
                    return;

                case 2: // '\002'
                    BdOnGetSortCapabilities bdongetsortcapabilities = (BdOnGetSortCapabilities)callbackdata;
                    m_RedirectServerCallback.OnGetSortCapabilities(bdongetsortcapabilities.mnErrorCode, bdongetsortcapabilities.mstrSortCaps, bdongetsortcapabilities.mlUpdateId);
                    return;

                case 3: // '\003'
                    BdOnGetSearchCapabilities bdongetsearchcapabilities = (BdOnGetSearchCapabilities)callbackdata;
                    m_RedirectServerCallback.OnGetSearchCapabilities(bdongetsearchcapabilities.mnErrorCode, bdongetsearchcapabilities.mstrSearchCaps, bdongetsearchcapabilities.mlUpdateId);
                    return;

                case 4: // '\004'
                    BdOnServerAdded bdonserveradded = (BdOnServerAdded)callbackdata;
                    m_RedirectServerCallback.OnServerAdded(bdonserveradded.mDataOnServerAdded);
                    return;

                case 5: // '\005'
                    BdOnServerRemoved bdonserverremoved = (BdOnServerRemoved)callbackdata;
                    m_RedirectServerCallback.OnServerRemoved(bdonserverremoved.mDataOnServerRemoved);
                    return;

                case 6: // '\006'
                    BdOnServerGetProtocolInfo bdonservergetprotocolinfo = (BdOnServerGetProtocolInfo)callbackdata;
                    m_RedirectServerCallback.OnServerGetProtocolInfo(bdonservergetprotocolinfo.mnErrorCode, bdonservergetprotocolinfo.mDataOnGetProtocolInfo, bdonservergetprotocolinfo.mlUpdateId);
                    return;

                case 7: // '\007'
                    BdOnXGetDLNAUploadProfiles bdonxgetdlnauploadprofiles = (BdOnXGetDLNAUploadProfiles)callbackdata;
                    m_RedirectServerCallback.OnXGetDLNAUploadProfiles(bdonxgetdlnauploadprofiles.mnErrorCode, bdonxgetdlnauploadprofiles.mstrUploadProfiles, bdonxgetdlnauploadprofiles.mlUpdateId);
                    return;

                case 8: // '\b'
                    BdOnGetCurrentTransportActions bdongetcurrenttransportactions = (BdOnGetCurrentTransportActions)callbackdata;
                    m_RedirectRenderCallback.OnGetCurrentTransportActions(bdongetcurrenttransportactions.mnErrorCode, bdongetcurrenttransportactions.mstrAllowedActions, bdongetcurrenttransportactions.mlUpdateId);
                    return;

                case 9: // '\t'
                    BdOnGetMeidaInfo bdongetmeidainfo = (BdOnGetMeidaInfo)callbackdata;
                    m_RedirectRenderCallback.OnGetMeidaInfo(bdongetmeidainfo.mnErrorCode, bdongetmeidainfo.mDataOnGetMediaInfo, bdongetmeidainfo.mlUpdateId);
                    return;

                case 10: // '\n'
                    BdOnGetMute bdongetmute = (BdOnGetMute)callbackdata;
                    m_RedirectRenderCallback.OnGetMute(bdongetmute.mnErrorCode, bdongetmute.mbMute, bdongetmute.mlUpdateId);
                    return;

                case 11: // '\013'
                    BdOnGetPositionInfo bdongetpositioninfo = (BdOnGetPositionInfo)callbackdata;
                    m_RedirectRenderCallback.OnGetPositionInfo(bdongetpositioninfo.mnErrorCode, bdongetpositioninfo.mDataOnGetPositionInfo, bdongetpositioninfo.mlUpdateId);
                    return;

                case 12: // '\f'
                    BdOnGetProtocolInfo bdongetprotocolinfo = (BdOnGetProtocolInfo)callbackdata;
                    m_RedirectRenderCallback.OnGetProtocolInfo(bdongetprotocolinfo.mnErrorCode, bdongetprotocolinfo.mDataOnGetProtocolInfo, bdongetprotocolinfo.mlUpdateId);
                    return;

                case 13: // '\r'
                    BdOnGetTransportInfo bdongettransportinfo = (BdOnGetTransportInfo)callbackdata;
                    m_RedirectRenderCallback.OnGetTransportInfo(bdongettransportinfo.mnErrorCode, bdongettransportinfo.mDataOnGetTransportInfo, bdongettransportinfo.mlUpdateId);
                    return;

                case 14: // '\016'
                    BdOnGetTransportSettings bdongettransportsettings = (BdOnGetTransportSettings)callbackdata;
                    m_RedirectRenderCallback.OnGetTransportSettings(bdongettransportsettings.mnErrorCode, bdongettransportsettings.mDataOnGetTransportSettings, bdongettransportsettings.mlUpdateId);
                    return;

                case 15: // '\017'
                    BdOnGetVolume bdongetvolume = (BdOnGetVolume)callbackdata;
                    m_RedirectRenderCallback.OnGetVolume(bdongetvolume.mnErrorCode, bdongetvolume.muiCurVolume, bdongetvolume.mlUpdateId);
                    return;

                case 16: // '\020'
                    BdOnMediaPause bdonmediapause = (BdOnMediaPause)callbackdata;
                    m_RedirectRenderCallback.OnMediaPause(bdonmediapause.mnErrorCode, bdonmediapause.mlUpdateId);
                    return;

                case 17: // '\021'
                    BdOnMediaPlay bdonmediaplay = (BdOnMediaPlay)callbackdata;
                    m_RedirectRenderCallback.OnMediaPlay(bdonmediaplay.mnErrorCode, bdonmediaplay.mlUpdateId);
                    return;

                case 18: // '\022'
                    BdOnMediaSeek bdonmediaseek = (BdOnMediaSeek)callbackdata;
                    m_RedirectRenderCallback.OnMediaSeek(bdonmediaseek.mnErrorCode, bdonmediaseek.mlUpdateId);
                    return;

                case 19: // '\023'
                    BdOnMediaNext bdonmedianext = (BdOnMediaNext)callbackdata;
                    m_RedirectRenderCallback.OnMediaNext(bdonmedianext.mnErrorCode, bdonmedianext.mlUpdateId);
                    return;

                case 20: // '\024'
                    BdOnMediaPrevious bdonmediaprevious = (BdOnMediaPrevious)callbackdata;
                    m_RedirectRenderCallback.OnMediaPrevious(bdonmediaprevious.mnErrorCode, bdonmediaprevious.mlUpdateId);
                    return;

                case 21: // '\025'
                    BdOnMediaStop bdonmediastop = (BdOnMediaStop)callbackdata;
                    m_RedirectRenderCallback.OnMediaStop(bdonmediastop.mnErrorCode, bdonmediastop.mlUpdateId);
                    return;

                case 22: // '\026'
                    BdOnRenderAdded bdonrenderadded = (BdOnRenderAdded)callbackdata;
                    m_RedirectRenderCallback.OnRenderAdded(bdonrenderadded.mDataOnRenderAdded);
                    return;

                case 23: // '\027'
                    BdOnRenderInstalled bdonrenderinstalled = (BdOnRenderInstalled)callbackdata;
                    m_RedirectRenderCallback.OnRenderInstalled(bdonrenderinstalled.mMediaRenderDesc, bdonrenderinstalled.mbCm, bdonrenderinstalled.mbAvt, bdonrenderinstalled.mbRcl);
                    return;

                case 24: // '\030'
                    BdOnRenderRemoved bdonrenderremoved = (BdOnRenderRemoved)callbackdata;
                    m_RedirectRenderCallback.OnRenderRemoved(bdonrenderremoved.mDataOnRenderRemoved);
                    return;

                case 25: // '\031'
                    BdOnSetAVTransportURI bdonsetavtransporturi = (BdOnSetAVTransportURI)callbackdata;
                    m_RedirectRenderCallback.OnSetAVTransportURI(bdonsetavtransporturi.mnErrorCode, bdonsetavtransporturi.mlUpdateId);
                    return;

                case 26: // '\032'
                    BdOnSetMute bdonsetmute = (BdOnSetMute)callbackdata;
                    m_RedirectRenderCallback.OnSetMute(bdonsetmute.mnErrorCode, bdonsetmute.mlUpdateId);
                    return;

                case 27: // '\033'
                    BdOnSetVolume bdonsetvolume = (BdOnSetVolume)callbackdata;
                    m_RedirectRenderCallback.OnSetVolume(bdonsetvolume.mnErrorCode, bdonsetvolume.mlUpdateId);
                    return;

                case 28: // '\034'
                    BdOnBrowseRequest bdonbrowserequest = (BdOnBrowseRequest)callbackdata;
                    m_RedirectBrowseCallback.OnBrowseRequest(bdonbrowserequest.mDataOnBrowseRequest, bdonbrowserequest.mDataOnBrowseRequestRsp);
                    return;

                case 29: // '\035'
                    BdOnUploadResult bdonuploadresult = (BdOnUploadResult)callbackdata;
                    m_RedirectUpCallback.OnUploadResult(bdonuploadresult.mlUploadId, bdonuploadresult.mnErrorCode);
                    return;

                case 30: // '\036'
                    BdOnDigaXP9eGetContainerIds bdondigaxp9egetcontainerids = (BdOnDigaXP9eGetContainerIds)callbackdata;
                    m_RedirectServerCallback.OnDigaXP9eGetContainerIds(bdondigaxp9egetcontainerids.nErrorCode, bdondigaxp9egetcontainerids.strContainerIds, bdondigaxp9egetcontainerids.lUpdateId);
                    return;

                case 31: // '\037'
                    BdOnDigaCreateRecordSchedule bdondigacreaterecordschedule = (BdOnDigaCreateRecordSchedule)callbackdata;
                    m_RedirectServerCallback.OnDigaCreateRecordSchedule(bdondigacreaterecordschedule.nErrorCode, bdondigacreaterecordschedule.data, bdondigacreaterecordschedule.lUpdateId);
                    return;

                case 32: // ' '
                    BdOnDigaBrowseRecordTasks bdondigabrowserecordtasks = (BdOnDigaBrowseRecordTasks)callbackdata;
                    m_RedirectServerCallback.OnDigaBrowseRecordTasks(bdondigabrowserecordtasks.nErrorCode, bdondigabrowserecordtasks.data, bdondigabrowserecordtasks.lUpdateId);
                    return;

                case 33: // '!'
                    BdOnDigaDeleteRecordSchedule bdondigadeleterecordschedule = (BdOnDigaDeleteRecordSchedule)callbackdata;
                    m_RedirectServerCallback.OnDigaDeleteRecordSchedule(bdondigadeleterecordschedule.nErrorCode, bdondigadeleterecordschedule.lUpdateId);
                    return;

                case 34: // '"'
                    BdOnDigaDisableRecordSchedule bdondigadisablerecordschedule = (BdOnDigaDisableRecordSchedule)callbackdata;
                    m_RedirectServerCallback.OnDigaDisableRecordSchedule(bdondigadisablerecordschedule.nErrorCode, bdondigadisablerecordschedule.lUpdateId);
                    return;

                case 35: // '#'
                    BdOnDigaEnableRecordSchedule bdondigaenablerecordschedule = (BdOnDigaEnableRecordSchedule)callbackdata;
                    m_RedirectServerCallback.OnDigaEnableRecordSchedule(bdondigaenablerecordschedule.nErrorCode, bdondigaenablerecordschedule.lUpdateId);
                    return;

                case 36: // '$'
                    bdondestroyobject = (BdOnDestroyObject)callbackdata;
                    break;
                }
                m_RedirectServerCallback.OnDestroyObject(bdondestroyobject.nErrorCode, bdondestroyobject.lUpdateId);
                return;
            } else
            {
                super.handleMessage(message);
                return;
            }
        }

        BridgeHandler()
        {
            this$0 = CallbackThreadBridge.this;
            super();
        }

        BridgeHandler(Looper looper)
        {
            this$0 = CallbackThreadBridge.this;
            super(looper);
        }
    }

    static interface CallbackData
    {

        public abstract CallbackType getType();
    }

    public static final class CallbackData.CallbackType extends Enum
    {

        private static final CallbackData.CallbackType $VALUES[];
        public static final CallbackData.CallbackType OnBrowseRequest;
        public static final CallbackData.CallbackType OnDLNA;
        public static final CallbackData.CallbackType OnDestroyObject;
        public static final CallbackData.CallbackType OnDigaBrowseRecordTasks;
        public static final CallbackData.CallbackType OnDigaCreateRecordSchedule;
        public static final CallbackData.CallbackType OnDigaDeleteRecordSchedule;
        public static final CallbackData.CallbackType OnDigaDisableRecordSchedule;
        public static final CallbackData.CallbackType OnDigaEnableRecordSchedule;
        public static final CallbackData.CallbackType OnDigaXP9eGetContainerIds;
        public static final CallbackData.CallbackType OnDirContentUpdated;
        public static final CallbackData.CallbackType OnGetCurrentTransportActions;
        public static final CallbackData.CallbackType OnGetMeidaInfo;
        public static final CallbackData.CallbackType OnGetMute;
        public static final CallbackData.CallbackType OnGetPositionInfo;
        public static final CallbackData.CallbackType OnGetProtocolInfo;
        public static final CallbackData.CallbackType OnGetSearchCapabilities;
        public static final CallbackData.CallbackType OnGetSortCapabilities;
        public static final CallbackData.CallbackType OnGetTransportInfo;
        public static final CallbackData.CallbackType OnGetTransportSettings;
        public static final CallbackData.CallbackType OnGetVolume;
        public static final CallbackData.CallbackType OnMediaNext;
        public static final CallbackData.CallbackType OnMediaPause;
        public static final CallbackData.CallbackType OnMediaPlay;
        public static final CallbackData.CallbackType OnMediaPrevious;
        public static final CallbackData.CallbackType OnMediaSeek;
        public static final CallbackData.CallbackType OnMediaStop;
        public static final CallbackData.CallbackType OnRenderAdded;
        public static final CallbackData.CallbackType OnRenderInstalled;
        public static final CallbackData.CallbackType OnRenderRemoved;
        public static final CallbackData.CallbackType OnServerAdded;
        public static final CallbackData.CallbackType OnServerGetProtocolInfo;
        public static final CallbackData.CallbackType OnServerRemoved;
        public static final CallbackData.CallbackType OnSetAVTransportURI;
        public static final CallbackData.CallbackType OnSetMute;
        public static final CallbackData.CallbackType OnSetVolume;
        public static final CallbackData.CallbackType OnUploadResult;
        public static final CallbackData.CallbackType OnXGetDLNAUploadProfiles;

        public static CallbackData.CallbackType valueOf(String s)
        {
            return (CallbackData.CallbackType)Enum.valueOf(com/arcsoft/adk/atv/CallbackThreadBridge$CallbackData$CallbackType, s);
        }

        public static CallbackData.CallbackType[] values()
        {
            return (CallbackData.CallbackType[])$VALUES.clone();
        }

        static 
        {
            OnDirContentUpdated = new CallbackData.CallbackType("OnDirContentUpdated", 0);
            OnGetSortCapabilities = new CallbackData.CallbackType("OnGetSortCapabilities", 1);
            OnGetSearchCapabilities = new CallbackData.CallbackType("OnGetSearchCapabilities", 2);
            OnServerAdded = new CallbackData.CallbackType("OnServerAdded", 3);
            OnServerRemoved = new CallbackData.CallbackType("OnServerRemoved", 4);
            OnGetCurrentTransportActions = new CallbackData.CallbackType("OnGetCurrentTransportActions", 5);
            OnGetMeidaInfo = new CallbackData.CallbackType("OnGetMeidaInfo", 6);
            OnGetMute = new CallbackData.CallbackType("OnGetMute", 7);
            OnGetPositionInfo = new CallbackData.CallbackType("OnGetPositionInfo", 8);
            OnGetProtocolInfo = new CallbackData.CallbackType("OnGetProtocolInfo", 9);
            OnServerGetProtocolInfo = new CallbackData.CallbackType("OnServerGetProtocolInfo", 10);
            OnXGetDLNAUploadProfiles = new CallbackData.CallbackType("OnXGetDLNAUploadProfiles", 11);
            OnGetTransportInfo = new CallbackData.CallbackType("OnGetTransportInfo", 12);
            OnGetTransportSettings = new CallbackData.CallbackType("OnGetTransportSettings", 13);
            OnGetVolume = new CallbackData.CallbackType("OnGetVolume", 14);
            OnMediaPause = new CallbackData.CallbackType("OnMediaPause", 15);
            OnMediaPlay = new CallbackData.CallbackType("OnMediaPlay", 16);
            OnMediaSeek = new CallbackData.CallbackType("OnMediaSeek", 17);
            OnMediaNext = new CallbackData.CallbackType("OnMediaNext", 18);
            OnMediaPrevious = new CallbackData.CallbackType("OnMediaPrevious", 19);
            OnMediaStop = new CallbackData.CallbackType("OnMediaStop", 20);
            OnRenderAdded = new CallbackData.CallbackType("OnRenderAdded", 21);
            OnRenderInstalled = new CallbackData.CallbackType("OnRenderInstalled", 22);
            OnRenderRemoved = new CallbackData.CallbackType("OnRenderRemoved", 23);
            OnSetAVTransportURI = new CallbackData.CallbackType("OnSetAVTransportURI", 24);
            OnSetMute = new CallbackData.CallbackType("OnSetMute", 25);
            OnSetVolume = new CallbackData.CallbackType("OnSetVolume", 26);
            OnBrowseRequest = new CallbackData.CallbackType("OnBrowseRequest", 27);
            OnUploadResult = new CallbackData.CallbackType("OnUploadResult", 28);
            OnDLNA = new CallbackData.CallbackType("OnDLNA", 29);
            OnDigaXP9eGetContainerIds = new CallbackData.CallbackType("OnDigaXP9eGetContainerIds", 30);
            OnDigaCreateRecordSchedule = new CallbackData.CallbackType("OnDigaCreateRecordSchedule", 31);
            OnDigaBrowseRecordTasks = new CallbackData.CallbackType("OnDigaBrowseRecordTasks", 32);
            OnDigaDeleteRecordSchedule = new CallbackData.CallbackType("OnDigaDeleteRecordSchedule", 33);
            OnDigaDisableRecordSchedule = new CallbackData.CallbackType("OnDigaDisableRecordSchedule", 34);
            OnDigaEnableRecordSchedule = new CallbackData.CallbackType("OnDigaEnableRecordSchedule", 35);
            OnDestroyObject = new CallbackData.CallbackType("OnDestroyObject", 36);
            CallbackData.CallbackType acallbacktype[] = new CallbackData.CallbackType[37];
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

        private CallbackData.CallbackType(String s, int i)
        {
            super(s, i);
        }
    }


    static final String TAG = "CallbackThreadBridge";
    static long timestamp = 0L;
    private BridgeHandler mHandler;
    private BrowseCallback m_RedirectBrowseCallback;
    private MRCPCallback m_RedirectRenderCallback;
    private MSCPCallback m_RedirectServerCallback;
    private UPCPCallback m_RedirectUpCallback;

    public CallbackThreadBridge(Looper looper)
    {
        m_RedirectServerCallback = null;
        m_RedirectRenderCallback = null;
        m_RedirectBrowseCallback = null;
        m_RedirectUpCallback = null;
        mHandler = null;
        mHandler = new BridgeHandler(looper);
    }

    public void RedirectCallbacks(UPnP.UPnPInitParam upnpinitparam)
    {
        m_RedirectServerCallback = upnpinitparam.m_ServerCallback;
        m_RedirectRenderCallback = upnpinitparam.m_RenderCallback;
        m_RedirectBrowseCallback = upnpinitparam.m_BrowseCallback;
        m_RedirectUpCallback = upnpinitparam.m_UpCPCallback;
        upnpinitparam.m_ServerCallback = new BdMSCPCallBack();
        upnpinitparam.m_RenderCallback = new BdMRCPCallback();
        upnpinitparam.m_BrowseCallback = new BdBrowseCallback();
        upnpinitparam.m_UpCPCallback = new BdUPCPCallback();
    }

    public void post(Runnable runnable, long l)
    {
        mHandler.postDelayed(runnable, l);
    }






}
