// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

public static interface Y
{

    public abstract boolean doAction(Y y, Object obj);

    public abstract void onDoActionError(Y y, Object obj);

    public abstract void onDoActionResultError(Y y, Object obj);
}
