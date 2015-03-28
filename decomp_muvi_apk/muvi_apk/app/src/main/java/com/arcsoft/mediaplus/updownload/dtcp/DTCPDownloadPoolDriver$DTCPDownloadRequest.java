// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.dtcp;

import com.arcsoft.adk.atv.dtcp.DtcpSink;

// Referenced classes of package com.arcsoft.mediaplus.updownload.dtcp:
//            DTCPDownloadPoolDriver

public static class parentdir extends com.arcsoft.mediaplus.updownload.uest
{

    public String mAkeIPAddr;
    public long mAkePort;
    private DtcpSink mDtcpSink;



/*
    static DtcpSink access$1902( , DtcpSink dtcpsink)
    {
        .mDtcpSink = dtcpsink;
        return dtcpsink;
    }

*/

    public mDtcpSink(com.arcsoft.mediaplus.updownload.uest uest)
    {
        mAkeIPAddr = null;
        mAkePort = 0L;
        mDtcpSink = null;
        tableid = uest.tableid;
        dms_uuid = uest.dms_uuid;
        item_uuid = uest.item_uuid;
        upnp_class = uest.upnp_class;
        title = uest.title;
        uri = uest.uri;
        fileSize = uest.fileSize;
        listener = uest.listener;
        protocolInfo = uest.protocolInfo;
        parentdir = uest.parentdir;
    }
}
