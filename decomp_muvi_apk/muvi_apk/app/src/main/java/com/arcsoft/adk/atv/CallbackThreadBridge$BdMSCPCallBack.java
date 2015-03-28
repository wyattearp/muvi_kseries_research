// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            MSCPCallback, CallbackThreadBridge

class this._cls0
    implements MSCPCallback
{

    final CallbackThreadBridge this$0;

    public void OnDestroyObject(int i, String s)
    {
        ct ct = new ct(CallbackThreadBridge.this);
        ct.nErrorCode = i;
        ct.lUpdateId = s;
        Message message = new Message();
        message.obj = ct;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaBrowseRecordTasks(int i, endMessage endmessage, String s)
    {
        ecordTasks ecordtasks = new ecordTasks(CallbackThreadBridge.this);
        ecordtasks.nErrorCode = i;
        ecordtasks.data = endmessage;
        ecordtasks.lUpdateId = s;
        Message message = new Message();
        message.obj = ecordtasks;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaCreateRecordSchedule(int i, endMessage endmessage, String s)
    {
        ecordSchedule ecordschedule = new ecordSchedule(CallbackThreadBridge.this);
        ecordschedule.nErrorCode = i;
        ecordschedule.data = endmessage;
        ecordschedule.lUpdateId = s;
        Message message = new Message();
        message.obj = ecordschedule;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaDeleteRecordSchedule(int i, String s)
    {
        ecordSchedule ecordschedule = new ecordSchedule(CallbackThreadBridge.this);
        ecordschedule.nErrorCode = i;
        ecordschedule.lUpdateId = s;
        Message message = new Message();
        message.obj = ecordschedule;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaDisableRecordSchedule(int i, String s)
    {
        RecordSchedule recordschedule = new RecordSchedule(CallbackThreadBridge.this);
        recordschedule.nErrorCode = i;
        recordschedule.lUpdateId = s;
        Message message = new Message();
        message.obj = recordschedule;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaEnableRecordSchedule(int i, String s)
    {
        ecordSchedule ecordschedule = new ecordSchedule(CallbackThreadBridge.this);
        ecordschedule.nErrorCode = i;
        ecordschedule.lUpdateId = s;
        Message message = new Message();
        message.obj = ecordschedule;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDigaXP9eGetContainerIds(int i, String s, String s1)
    {
        ContainerIds containerids = new ContainerIds(CallbackThreadBridge.this);
        containerids.nErrorCode = i;
        containerids.strContainerIds = s;
        containerids.lUpdateId = s1;
        Message message = new Message();
        message.obj = containerids;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnDirContentUpdated( , String s, String s1)
    {
        pdated pdated = new pdated(CallbackThreadBridge.this);
        pdated.mDataOnDirContentUpdated = ;
        pdated.mstrDeviceId = s;
        pdated.mstrObjId = s1;
        Message message = new Message();
        message.obj = pdated;
        message.what = .m_nUpdateId;
        Log.e("CallbackThreadBridge", (new StringBuilder()).append("        OnDirMSCP callback msg.what = ").append(.m_nUpdateId).toString());
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetSearchCapabilities(int i, String s, String s1)
    {
        pabilities pabilities = new pabilities(CallbackThreadBridge.this);
        pabilities.mnErrorCode = i;
        pabilities.mstrSearchCaps = s;
        pabilities.mlUpdateId = s1;
        Message message = new Message();
        message.obj = pabilities;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetSortCapabilities(int i, String s, String s1)
    {
        bilities bilities = new bilities(CallbackThreadBridge.this);
        bilities.mnErrorCode = i;
        bilities.mstrSortCaps = s;
        bilities.mlUpdateId = s1;
        Message message = new Message();
        message.obj = bilities;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnServerAdded(endMessage endmessage)
    {
          = new (CallbackThreadBridge.this);
        .mDataOnServerAdded = endmessage;
        Message message = new Message();
        message.obj = ;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnServerGetProtocolInfo(int i, Info info, String s)
    {
        otocolInfo otocolinfo = new otocolInfo(CallbackThreadBridge.this);
        otocolinfo.mnErrorCode = i;
        otocolinfo.mDataOnGetProtocolInfo = info;
        otocolinfo.mlUpdateId = s;
        Message message = new Message();
        message.obj = otocolinfo;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnServerRemoved(endMessage endmessage)
    {
        ed ed = new ed(CallbackThreadBridge.this);
        ed.mDataOnServerRemoved = endmessage;
        Message message = new Message();
        message.obj = ed;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnXGetDLNAUploadProfiles(int i, String s, String s1)
    {
        oadProfiles oadprofiles = new oadProfiles(CallbackThreadBridge.this);
        oadprofiles.mnErrorCode = i;
        oadprofiles.mstrUploadProfiles = s;
        oadprofiles.mlUpdateId = s1;
        Message message = new Message();
        message.obj = oadprofiles;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    Info()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }
}
