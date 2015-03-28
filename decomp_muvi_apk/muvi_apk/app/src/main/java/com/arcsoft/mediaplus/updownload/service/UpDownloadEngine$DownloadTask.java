// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;


// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

public static class priority
{

    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NONE;
    public String dms_uuid;
    public long fileSize;
    public String item_uuid;
    public long mediaClass;
    public long mediaId;
    public int priority;
    public String protocolInfo;
    public long status;
    public String title;
    public String uri;
    public boolean videoOrImage;

    protected Object clone()
    {
        priority priority1 = new <init>();
        priority1.dms_uuid = dms_uuid;
        priority1.fileSize = fileSize;
        priority1.item_uuid = item_uuid;
        priority1.mediaClass = mediaClass;
        priority1.mediaId = mediaId;
        priority1.protocolInfo = protocolInfo;
        priority1.status = status;
        priority1.title = title;
        priority1.uri = uri;
        priority1.status = status;
        priority1.videoOrImage = videoOrImage;
        priority1.priority = priority;
        return priority1;
    }

    public ()
    {
        videoOrImage = false;
        priority = 0;
    }
}
