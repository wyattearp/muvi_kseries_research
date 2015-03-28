// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            PictureDataSource

public static class mPictureDataSource extends mPictureDataSource
{

    private final PictureDataSource mPictureDataSource;

    public volatile void release()
    {
        super.mPictureDataSource();
    }

    public void slowDown(boolean flag)
    {
        PictureDataSource.access$200(mPictureDataSource, mSession, flag);
    }

    public (PictureDataSource picturedatasource, int i)
    {
        super(picturedatasource, i);
        mPictureDataSource = picturedatasource;
    }
}
