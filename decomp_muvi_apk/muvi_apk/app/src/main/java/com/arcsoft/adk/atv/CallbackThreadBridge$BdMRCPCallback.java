// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Message;

// Referenced classes of package com.arcsoft.adk.atv:
//            MRCPCallback, CallbackThreadBridge

class this._cls0
    implements MRCPCallback
{

    final CallbackThreadBridge this$0;

    public void OnGetCurrentTransportActions(int i, String s, String s1)
    {
        ransportActions ransportactions = new ransportActions(CallbackThreadBridge.this);
        ransportactions.mnErrorCode = i;
        ransportactions.mstrAllowedActions = s;
        ransportactions.mlUpdateId = s1;
        Message message = new Message();
        message.obj = ransportactions;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetMeidaInfo(int i, endMessage endmessage, String s)
    {
        o o = new o(CallbackThreadBridge.this);
        o.mnErrorCode = i;
        o.mDataOnGetMediaInfo = endmessage;
        o.mlUpdateId = s;
        Message message = new Message();
        message.obj = o;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetMute(int i, boolean flag, String s)
    {
        endMessage endmessage = new it>(CallbackThreadBridge.this);
        endmessage.rrorCode = i;
        endmessage.ute = flag;
        endmessage.pdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetPositionInfo(int i, endMessage endmessage, String s)
    {
        Info info = new Info(CallbackThreadBridge.this);
        info.mnErrorCode = i;
        info.mDataOnGetPositionInfo = endmessage;
        info.mlUpdateId = s;
        Message message = new Message();
        message.obj = info;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetProtocolInfo(int i, endMessage endmessage, String s)
    {
        Info info = new Info(CallbackThreadBridge.this);
        info.mnErrorCode = i;
        info.mDataOnGetProtocolInfo = endmessage;
        info.mlUpdateId = s;
        Message message = new Message();
        message.obj = info;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetTransportInfo(int i, endMessage endmessage, String s)
    {
        tInfo tinfo = new tInfo(CallbackThreadBridge.this);
        tinfo.mnErrorCode = i;
        tinfo.mDataOnGetTransportInfo = endmessage;
        tinfo.mlUpdateId = s;
        Message message = new Message();
        message.obj = tinfo;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetTransportSettings(int i, ngs ngs, String s)
    {
        tSettings tsettings = new tSettings(CallbackThreadBridge.this);
        tsettings.mnErrorCode = i;
        tsettings.mDataOnGetTransportSettings = ngs;
        tsettings.mlUpdateId = s;
        Message message = new Message();
        message.obj = tsettings;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnGetVolume(int i, int j, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.uiCurVolume = j;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaNext(int i, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaPause(int i, String s)
    {
        endMessage endmessage = new <init>(CallbackThreadBridge.this);
        endmessage.mnErrorCode = i;
        endmessage.mlUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaPlay(int i, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaPrevious(int i, String s)
    {
        us us = new us(CallbackThreadBridge.this);
        us.mnErrorCode = i;
        us.mlUpdateId = s;
        Message message = new Message();
        message.obj = us;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaSeek(int i, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnMediaStop(int i, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnRenderAdded(endMessage endmessage)
    {
          = new (CallbackThreadBridge.this);
        .mDataOnRenderAdded = endmessage;
        Message message = new Message();
        message.obj = ;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnRenderInstalled(endMessage endmessage, boolean flag, boolean flag1, boolean flag2)
    {
        lled lled = new lled(CallbackThreadBridge.this);
        lled.mMediaRenderDesc = endmessage;
        lled.mbCm = flag;
        lled.mbAvt = flag1;
        lled.mbRcl = flag2;
        Message message = new Message();
        message.obj = lled;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnRenderRemoved(endMessage endmessage)
    {
        ed ed = new ed(CallbackThreadBridge.this);
        ed.mDataOnRenderRemoved = endmessage;
        Message message = new Message();
        message.obj = ed;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnSetAVTransportURI(int i, String s)
    {
        ortURI orturi = new ortURI(CallbackThreadBridge.this);
        orturi.mnErrorCode = i;
        orturi.mlUpdateId = s;
        Message message = new Message();
        message.obj = orturi;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnSetMute(int i, String s)
    {
        endMessage endmessage = new it>(CallbackThreadBridge.this);
        endmessage.rrorCode = i;
        endmessage.pdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    public void OnSetVolume(int i, String s)
    {
        endMessage endmessage = new init>(CallbackThreadBridge.this);
        endmessage.nErrorCode = i;
        endmessage.lUpdateId = s;
        Message message = new Message();
        message.obj = endmessage;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    ngs()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }
}
