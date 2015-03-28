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
        on on1 = on;
        if (on != on.STOP) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        on1 = NG;
_L6:
        return on1;
_L4:
        return NG;
_L2:
        if (on == on.PLAY)
        {
            if (flag)
            {
                return NGPLAY;
            } else
            {
                return ;
            }
        }
        if (on == on.SEEK)
        {
            if (flag)
            {
                return G;
            } else
            {
                return ;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public  getStatus()
    {
        return ;
    }

    public boolean isSupportedAction(on on)
    {
        return on == on.STOP || on == on.PLAY || on == on.SEEK;
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
