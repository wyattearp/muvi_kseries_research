// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask

public static interface 
{

    public abstract void OnDataIncreased(String s, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4);

    public abstract void OnDataTransmittedBegin(String s);

    public abstract void OnDataTransmittedFinish(String s, int i);

    public abstract void OnDataUpdated(String s);
}
