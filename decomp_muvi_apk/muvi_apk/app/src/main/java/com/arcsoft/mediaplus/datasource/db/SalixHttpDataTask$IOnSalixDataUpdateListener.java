// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixHttpDataTask

public static interface 
{

    public abstract void OnDataIncreased(boolean flag, boolean flag1);

    public abstract void OnDataTransmittedBegin();

    public abstract void OnDataTransmittedFinish();

    public abstract void OnDataUpdated();
}
