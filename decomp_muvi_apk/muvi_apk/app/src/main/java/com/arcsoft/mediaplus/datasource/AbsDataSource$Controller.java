// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsDataSource, Property

protected static class mSession
    implements mSession
{

    private boolean isReleased;
    protected final AbsDataSource mDataSource;
    protected final int mSession;

    public final void loadMore(int i)
    {
        AbsDataSource.access$800(mDataSource, mSession, i);
    }

    public final void pause()
    {
        AbsDataSource.access$100(mDataSource, mSession);
    }

    public final transient void prefetch(int i, int j, Property aproperty[])
    {
        AbsDataSource.access$400(mDataSource, mSession, i, j, aproperty);
    }

    public final transient void prefetchEx(int ai[], Property aproperty[])
    {
        AbsDataSource.access$500(mDataSource, mSession, ai, aproperty);
    }

    public final void refresh()
    {
        AbsDataSource.access$600(mDataSource, mSession);
    }

    public void release()
    {
        if (!isReleased)
        {
            isReleased = true;
            AbsDataSource.access$000(mDataSource, mSession);
        }
    }

    public final void resume()
    {
        AbsDataSource.access$200(mDataSource, mSession);
    }

    public final void setEnable(boolean flag)
    {
        AbsDataSource.access$300(mDataSource, mSession, flag);
        if (!flag)
        {
            release();
        }
    }

    public final void setResourceType(boolean flag)
    {
        AbsDataSource.access$900(mDataSource, mSession, flag);
    }

    public final void sort(Property property, boolean flag)
    {
        AbsDataSource.access$700(mDataSource, mSession, property, flag);
    }

    public (AbsDataSource absdatasource, int i)
    {
        isReleased = false;
        mDataSource = absdatasource;
        mSession = i;
    }
}
