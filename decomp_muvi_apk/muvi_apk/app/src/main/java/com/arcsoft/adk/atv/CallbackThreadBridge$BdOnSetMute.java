// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            CallbackThreadBridge

class this._cls0
    implements 
{

    String mlUpdateId;
    int mnErrorCode;
    final CallbackThreadBridge this$0;

    public .CallbackType getType()
    {
        return .CallbackType.OnSetMute;
    }

    .CallbackType()
    {
        this$0 = CallbackThreadBridge.this;
        super();
    }
}
