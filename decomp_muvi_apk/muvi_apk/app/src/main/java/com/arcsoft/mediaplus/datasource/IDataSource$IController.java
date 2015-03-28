// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            IDataSource, Property

public static interface 
{

    public abstract void loadMore(int i);

    public abstract void pause();

    public transient abstract void prefetch(int i, int j, Property aproperty[]);

    public transient abstract void prefetchEx(int ai[], Property aproperty[]);

    public abstract void refresh();

    public abstract void release();

    public abstract void resume();

    public abstract void setEnable(boolean flag);

    public abstract void setResourceType(boolean flag);

    public abstract void sort(Property property, boolean flag);
}
