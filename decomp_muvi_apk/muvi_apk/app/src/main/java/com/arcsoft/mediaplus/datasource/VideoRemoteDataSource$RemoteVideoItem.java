// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            VideoItem, VideoRemoteDataSource

public static class channelNr extends VideoItem
{

    String albumFile;
    String albumUrl;
    String channelName;
    String channelNr;
    int copyCount;
    long resumePoint;

    public String toString()
    {
        return (new StringBuilder()).append("Audio").append(title).append(" - ").append(duration).append("s").toString();
    }

    public ()
    {
        albumUrl = null;
        albumFile = null;
        resumePoint = 0L;
        copyCount = 0;
        channelName = null;
        channelNr = null;
    }
}
