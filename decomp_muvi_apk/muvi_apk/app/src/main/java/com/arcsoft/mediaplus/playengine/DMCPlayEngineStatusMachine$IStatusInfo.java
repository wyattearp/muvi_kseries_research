// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

private static interface ion
{

    public abstract ion getAfterDoActionStatus(ion ion, boolean flag);

    public abstract ion getDoActionByRequestAction(ion ion);

    public abstract ion getStatus();

    public abstract boolean isSupportedAction(ion ion);
}
