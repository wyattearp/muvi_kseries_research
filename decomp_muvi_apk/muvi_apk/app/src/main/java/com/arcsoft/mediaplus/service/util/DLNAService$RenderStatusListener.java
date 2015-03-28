// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;


// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            DLNAService

private class <init>
    implements com.arcsoft.adk.atv.er
{

    final DLNAService this$0;

    public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.o o, int i)
    {
    }

    public void onRenderAdded(com.arcsoft.adk.atv.StatusListener statuslistener)
    {
        if (statuslistener != null)
        {
            DLNAService.access$2300(DLNAService.this, statuslistener._fld0);
        }
    }

    public void onRenderInstalled(com.arcsoft.adk.atv.StatusListener statuslistener, boolean flag, boolean flag1, boolean flag2)
    {
    }

    public void onRenderRemoved(com.arcsoft.adk.atv.StatusListener statuslistener)
    {
        if (statuslistener != null)
        {
            DLNAService.access$2400(DLNAService.this, statuslistener._fld0);
        }
    }

    private I()
    {
        this$0 = DLNAService.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
