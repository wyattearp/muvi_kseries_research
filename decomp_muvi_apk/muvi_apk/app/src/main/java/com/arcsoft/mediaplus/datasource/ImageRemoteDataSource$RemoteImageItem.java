// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            ImageItem, ImageRemoteDataSource

public static class largepath extends ImageItem
{

    String largepath;
    String thumbnailurl;
    String thumbpath;

    public String toString()
    {
        return (new StringBuilder()).append("Image : ").append(title).append(" - ").append(datestring).toString();
    }

    public ()
    {
        thumbnailurl = null;
        thumbpath = null;
        largepath = null;
    }
}
