// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends Info
{

    final DMCPlayEngineStatusMachine this$0;

    public Info getCauseAction()
    {
        return Y;
    }

    public Y getDestinationStatus(Y y, boolean flag)
    {
        if (flag)
        {
            return Y;
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
            throw new IllegalStateException((new StringBuilder()).append("Imposible Previous Status when startplay : ").append(DMCPlayEngineStatusMachine.access$1500(DMCPlayEngineStatusMachine.this)).toString());
        }
    }

    public this._cls0 getStatus()
    {
        return ;
    }

    private Info()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    Info(Info info)
    {
        this();
    }
}
