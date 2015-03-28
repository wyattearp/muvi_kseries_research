// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends <init>
{

    final DMCPlayEngineStatusMachine this$0;

    public  getAfterDoActionStatus( , boolean flag)
    {
         1 = ;
        if ( != .OPEN) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        1 = .OPEN;
_L6:
        return 1;
_L4:
        return .OPEN;
_L2:
        if ( == .STOP)
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

    public boolean isSupportedAction( )
    {
        return  == .OPEN ||  == .STOP;
    }

    private ()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    <init>(<init> <init>1)
    {
        this();
    }
}
