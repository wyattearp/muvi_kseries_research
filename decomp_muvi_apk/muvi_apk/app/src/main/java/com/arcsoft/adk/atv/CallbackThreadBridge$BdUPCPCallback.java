// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.Message;

// Referenced classes of package com.arcsoft.adk.atv:
//            UPCPCallback, CallbackThreadBridge

class this._cls0
    implements UPCPCallback
{

    final CallbackThreadBridge this$0;

    public void OnUploadResult(int i, int j)
    {
        t t = new t(CallbackThreadBridge.this);
        t.mnErrorCode = j;
        t.mlUploadId = i;
        Message message = new Message();
        message.obj = t;
        CallbackThreadBridge.access$400(CallbackThreadBridge.this).endMessage(message);
    }

    t()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }
}
