// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            SalixHttpUpdater

public static class mbIsVideo
{

    public int mCreateTime;
    public String mFileName;
    public long mSize;
    public String mUrl;
    public boolean mbIsVideo;

    public String toString()
    {
        return (new StringBuilder()).append(mFileName).append(" ").append(mUrl).append(" ").append(mCreateTime).append(" ").append(mSize).append(" ").append(mbIsVideo).toString();
    }

    public I()
    {
        mFileName = "";
        mUrl = "";
        mCreateTime = 0;
        mSize = 0L;
        mbIsVideo = false;
    }
}
