// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDBMgr

public static interface 
{

    public abstract void OnDBDataMounted(String s);

    public abstract void OnDBDataTransmittedBegin(String s);

    public abstract void OnDBDataTransmittedFinish(String s);

    public abstract void OnDBDataUnMounted(String s);

    public abstract void OnDBDataUpdated(String s,  );
}
