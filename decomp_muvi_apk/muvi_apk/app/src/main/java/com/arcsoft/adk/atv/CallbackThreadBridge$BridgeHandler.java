// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.adk.atv:
//            CallbackThreadBridge, MSCPCallback, MRCPCallback, BrowseCallback, 
//            UPCPCallback

class this._cls0 extends Handler
{

    final CallbackThreadBridge this$0;

    public void handleMessage(Message message)
    {
        if (message.obj instanceof ect)
        {
            sp sp = (ect)message.obj;
            ect ect;
            switch (om.arcsoft.adk.atv.CallbackThreadBridge.CallbackData.CallbackType[sp.etType().ordinal()])
            {
            default:
                return;

            case 1: // '\001'
                Updated updated = (Updated)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDirContentUpdated(updated.mDataOnDirContentUpdated, updated.mstrDeviceId, updated.mstrObjId);
                return;

            case 2: // '\002'
                abilities abilities = (abilities)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnGetSortCapabilities(abilities.mnErrorCode, abilities.mstrSortCaps, abilities.mlUpdateId);
                return;

            case 3: // '\003'
                apabilities apabilities = (apabilities)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnGetSearchCapabilities(apabilities.mnErrorCode, apabilities.mstrSearchCaps, apabilities.mlUpdateId);
                return;

            case 4: // '\004'
                d d1 = (d)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnServerAdded(d1.mDataOnServerAdded);
                return;

            case 5: // '\005'
                ved ved1 = (ved)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnServerRemoved(ved1.mDataOnServerRemoved);
                return;

            case 6: // '\006'
                rotocolInfo rotocolinfo = (rotocolInfo)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnServerGetProtocolInfo(rotocolinfo.mnErrorCode, rotocolinfo.mDataOnGetProtocolInfo, rotocolinfo.mlUpdateId);
                return;

            case 7: // '\007'
                loadProfiles loadprofiles = (loadProfiles)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnXGetDLNAUploadProfiles(loadprofiles.mnErrorCode, loadprofiles.mstrUploadProfiles, loadprofiles.mlUpdateId);
                return;

            case 8: // '\b'
                TransportActions transportactions = (TransportActions)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetCurrentTransportActions(transportactions.mnErrorCode, transportactions.mstrAllowedActions, transportactions.mlUpdateId);
                return;

            case 9: // '\t'
                fo fo1 = (fo)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetMeidaInfo(fo1.mnErrorCode, fo1.mDataOnGetMediaInfo, fo1.mlUpdateId);
                return;

            case 10: // '\n'
                fo fo = (fo.mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetMute(fo.ErrorCode, fo.Mute, fo.UpdateId);
                return;

            case 11: // '\013'
                nInfo ninfo = (nInfo)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetPositionInfo(ninfo.mnErrorCode, ninfo.mDataOnGetPositionInfo, ninfo.mlUpdateId);
                return;

            case 12: // '\f'
                lInfo linfo = (lInfo)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetProtocolInfo(linfo.mnErrorCode, linfo.mDataOnGetProtocolInfo, linfo.mlUpdateId);
                return;

            case 13: // '\r'
                rtInfo rtinfo = (rtInfo)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetTransportInfo(rtinfo.mnErrorCode, rtinfo.mDataOnGetTransportInfo, rtinfo.mlUpdateId);
                return;

            case 14: // '\016'
                rtSettings rtsettings = (rtSettings)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetTransportSettings(rtsettings.mnErrorCode, rtsettings.mDataOnGetTransportSettings, rtsettings.mlUpdateId);
                return;

            case 15: // '\017'
                ings ings = (rtSettings.mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnGetVolume(ings.mnErrorCode, ings.muiCurVolume, ings.mlUpdateId);
                return;

            case 16: // '\020'
                 3 = ()sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaPause(3.mnErrorCode, 3.mlUpdateId);
                return;

            case 17: // '\021'
                 2 = (.mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaPlay(2.mnErrorCode, 2.mlUpdateId);
                return;

            case 18: // '\022'
                 1 = (mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaSeek(1.mnErrorCode, 1.mlUpdateId);
                return;

            case 19: // '\023'
                  = (mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaNext(.mnErrorCode, .mlUpdateId);
                return;

            case 20: // '\024'
                ous ous1 = (ous)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaPrevious(ous1.mnErrorCode, ous1.mlUpdateId);
                return;

            case 21: // '\025'
                ous ous = (ous.mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnMediaStop(ous.mnErrorCode, ous.mlUpdateId);
                return;

            case 22: // '\026'
                d d = (d)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnRenderAdded(d.mDataOnRenderAdded);
                return;

            case 23: // '\027'
                alled alled = (alled)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnRenderInstalled(alled.mMediaRenderDesc, alled.mbCm, alled.mbAvt, alled.mbRcl);
                return;

            case 24: // '\030'
                ved ved = (ved)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnRenderRemoved(ved.mDataOnRenderRemoved);
                return;

            case 25: // '\031'
                portURI porturi2 = (portURI)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnSetAVTransportURI(porturi2.mnErrorCode, porturi2.mlUpdateId);
                return;

            case 26: // '\032'
                portURI porturi1 = (portURI.mlUpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnSetMute(porturi1.ErrorCode, porturi1.UpdateId);
                return;

            case 27: // '\033'
                portURI porturi = (UpdateId)sp;
                CallbackThreadBridge.access$100(CallbackThreadBridge.this).OnSetVolume(porturi.mnErrorCode, porturi.mlUpdateId);
                return;

            case 28: // '\034'
                est est = (est)sp;
                CallbackThreadBridge.access$200(CallbackThreadBridge.this).OnBrowseRequest(est.mDataOnBrowseRequest, est.mDataOnBrowseRequestRsp);
                return;

            case 29: // '\035'
                lt lt = (lt)sp;
                CallbackThreadBridge.access$300(CallbackThreadBridge.this).OnUploadResult(lt.mlUploadId, lt.mnErrorCode);
                return;

            case 30: // '\036'
                tContainerIds tcontainerids = (tContainerIds)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaXP9eGetContainerIds(tcontainerids.nErrorCode, tcontainerids.strContainerIds, tcontainerids.lUpdateId);
                return;

            case 31: // '\037'
                RecordSchedule recordschedule2 = (RecordSchedule)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaCreateRecordSchedule(recordschedule2.nErrorCode, recordschedule2.data, recordschedule2.lUpdateId);
                return;

            case 32: // ' '
                RecordTasks recordtasks = (RecordTasks)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaBrowseRecordTasks(recordtasks.nErrorCode, recordtasks.data, recordtasks.lUpdateId);
                return;

            case 33: // '!'
                RecordSchedule recordschedule1 = (RecordSchedule)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaDeleteRecordSchedule(recordschedule1.nErrorCode, recordschedule1.lUpdateId);
                return;

            case 34: // '"'
                eRecordSchedule erecordschedule = (eRecordSchedule)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaDisableRecordSchedule(erecordschedule.nErrorCode, erecordschedule.lUpdateId);
                return;

            case 35: // '#'
                RecordSchedule recordschedule = (RecordSchedule)sp;
                CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDigaEnableRecordSchedule(recordschedule.nErrorCode, recordschedule.lUpdateId);
                return;

            case 36: // '$'
                ect = (ect)sp;
                break;
            }
            CallbackThreadBridge.access$000(CallbackThreadBridge.this).OnDestroyObject(ect.nErrorCode, ect.lUpdateId);
            return;
        } else
        {
            super.handleMessage(message);
            return;
        }
    }

    ect()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }

    this._cls0(Looper looper)
    {
        this$0 = CallbackThreadBridge.this;
        super(looper);
    }
}
