// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends o
{

    final DMCPlayEngineStatusMachine this$0;

    public on getAfterDoActionStatus(on on, boolean flag)
    {
label0:
        {
            on on1 = ING;
            if (on == on.STOP)
            {
                if (!flag)
                {
                    break label0;
                }
                on1 = ING;
            }
            return on1;
        }
        return ED;
    }

    public on getDoActionByRequestAction(on on)
    {
        if (on == on.OPEN)
        {
            return on.STOP;
        } else
        {
            return null;
        }
    }

    public on.STOP getStatus()
    {
        return ;
    }

    public boolean isSupportedAction(on on)
    {
        return on == on.OPEN;
    }

    private on()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    o(o o)
    {
        this();
    }
}
