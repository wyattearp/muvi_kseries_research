// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private class <init> extends fo
{

    final DMCPlayEngineStatusMachine this$0;

    public ion getAfterDoActionStatus(ion ion, boolean flag)
    {
        ion ion1 = ;
        if (ion != ion.STOP) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        ion1 = ING;
_L6:
        return ion1;
_L4:
        return ;
_L2:
        if (ion == ion.PLAY)
        {
            if (flag)
            {
                return INGPLAY;
            } else
            {
                return ;
            }
        }
        if (ion == ion.SEEK)
        {
            if (flag)
            {
                return NG;
            } else
            {
                return D;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public D getStatus()
    {
        return D;
    }

    public boolean isSupportedAction(ion ion)
    {
        return ion == ion.STOP || ion == ion.PLAY || ion == ion.SEEK;
    }

    private ion()
    {
        this$0 = DMCPlayEngineStatusMachine.this;
        super(DMCPlayEngineStatusMachine.this, null);
    }

    fo(fo fo)
    {
        this();
    }
}
