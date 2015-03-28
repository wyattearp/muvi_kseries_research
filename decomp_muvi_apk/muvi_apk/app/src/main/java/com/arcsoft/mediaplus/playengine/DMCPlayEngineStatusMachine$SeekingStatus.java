// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends tatusInfo
{

    final DMCPlayEngineStatusMachine this$0;

    public n getCauseAction()
    {
        return n.SEEK;
    }

    public n getDestinationStatus(n n, boolean flag)
    {
        if (DMCPlayEngineStatusMachine.access$1500(DMCPlayEngineStatusMachine.this) == )
        {
            return ;
        }
        if (DMCPlayEngineStatusMachine.access$1500(DMCPlayEngineStatusMachine.this) == this._fld0)
        {
            return this._fld0;
        }
        if (DMCPlayEngineStatusMachine.access$1500(DMCPlayEngineStatusMachine.this) == this._fld0)
        {
            return this._fld0;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Imposible Previous Status when seek end : ").append(DMCPlayEngineStatusMachine.access$1500(DMCPlayEngineStatusMachine.this)).toString());
        }
    }

    public this._cls0 getStatus()
    {
        return ;
    }

    private n()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    tatusInfo(tatusInfo tatusinfo)
    {
        this();
    }
}
