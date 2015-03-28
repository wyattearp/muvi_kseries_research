// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixRemoteDBMgr

public static interface 
{

    public abstract void OnDBDataMounted();

    public abstract void OnDBDataTransmittedBegin();

    public abstract void OnDBDataTransmittedFinish();

    public abstract void OnDBDataUnMounted();

    public abstract void OnDBDataUpdated();
}
