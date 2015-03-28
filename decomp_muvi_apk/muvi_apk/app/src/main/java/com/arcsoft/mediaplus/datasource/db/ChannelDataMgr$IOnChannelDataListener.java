// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

public static interface 
{

    public abstract void OnChannelDataMounted(String s);

    public abstract void OnChannelDataTransmittedBegin(String s, Set set);

    public abstract void OnChannelDataTransmittedFinish(String s, Set set);

    public abstract void OnChannelDataUnMounted(String s);

    public abstract void OnChannelDataUpdated(String s, Set set);

    public abstract void OnChannelItemRefresh(String s, int i, long l);

    public abstract void OnDigaActionFinish(int i, int j);
}
