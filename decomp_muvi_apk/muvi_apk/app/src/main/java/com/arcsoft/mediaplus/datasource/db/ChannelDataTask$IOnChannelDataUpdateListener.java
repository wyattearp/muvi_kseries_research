// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataTask

public static interface 
{

    public abstract void OnChannelDataUpdated(String s, Set set);

    public abstract void OnChannelItemRefreshed(String s, int i, long l);

    public abstract void OnDataTransmittedBegin(String s, Set set);

    public abstract void OnDataTransmittedFinish(String s, Set set);

    public abstract void OnDigaActionFinish(int i, int j);
}
