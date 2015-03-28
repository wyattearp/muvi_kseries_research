// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends 
{

    final DMCPlayEngineStatusMachine this$0;

    public n getAfterDoActionStatus(n n, boolean flag)
    {
        n n1 = n;
        if (n == n.STOP)
        {
            if (flag)
            {
                n1 = G;
            } else
            {
                n1 = G;
            }
        }
        if (n != n.COMPLETE) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        n1 = n.COMPLETE;
_L6:
        return n1;
_L4:
        return n.COMPLETE;
_L2:
        if (n == n.PAUSE)
        {
            if (flag)
            {
                return ;
            } else
            {
                return ;
            }
        }
        if (n == n.SEEK)
        {
            if (flag)
            {
                return ;
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

    public boolean isSupportedAction(n n)
    {
        return n == n.STOP || n == n.PAUSE || n == n.SEEK || n == n.COMPLETE;
    }

    private n()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    ( )
    {
        this();
    }
}
