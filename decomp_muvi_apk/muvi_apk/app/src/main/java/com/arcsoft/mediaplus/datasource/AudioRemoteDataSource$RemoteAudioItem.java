// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AudioItem, AudioRemoteDataSource

private class <init> extends AudioItem
{

    String albumFile;
    String albumUrl;
    String datestring;
    final AudioRemoteDataSource this$0;

    public String toString()
    {
        return (new StringBuilder()).append("Audio").append(title).append(" - ").append(duration).append("s").toString();
    }

    private ()
    {
        this$0 = AudioRemoteDataSource.this;
        super();
        albumUrl = null;
        albumFile = null;
        datestring = null;
    }

    datestring(datestring datestring1)
    {
        this();
    }
}
