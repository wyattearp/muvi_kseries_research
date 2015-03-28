// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Message;

// Referenced classes of package com.arcsoft.adk.atv:
//            BrowseCallback, CallbackThreadBridge

class this._cls0
    implements BrowseCallback
{

    final CallbackThreadBridge this$0;

    public void OnBrowseRequest(this._cls0 _pcls0, this._cls0 _pcls0_1)
    {
          = new (CallbackThreadBridge.this);
        .mDataOnBrowseRequest = _pcls0;
        .mDataOnBrowseRequestRsp = _pcls0_1;
        Message message = new Message();
        message.obj = ;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).dMessage(message);
    }

    ()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }
}
