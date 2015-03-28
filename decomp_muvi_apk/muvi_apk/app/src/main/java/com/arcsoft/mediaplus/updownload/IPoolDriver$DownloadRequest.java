// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;


// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            IPoolDriver

public static class videoOrImage
{

    public int abortflag;
    public int cancelflag;
    public String dms_uuid;
    public long downloadSize;
    public long fileSize;
    public String filepath;
    public String item_uuid;
    public r listener;
    public long media_id;
    public String parentdir;
    public String protocolInfo;
    public long tableid;
    public String title;
    public int upnp_class;
    public String uri;
    public Object userdata;
    public boolean videoOrImage;

    public r()
    {
        downloadSize = 0L;
        cancelflag = 0;
        abortflag = 0;
        protocolInfo = null;
        videoOrImage = false;
    }
}
