// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;


// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

public static class bUrgent
{

    private boolean bUrgent;
    private boolean cancelflag;
    public y compressor;
    public String dms_uuid;
    private long downloadSize;
    private long fileSize;
    private String filepath;
    public int index;
    public String item_uuid;
    public r listener;
    public String parentdir;
    public int requestcode;
    private long taskid;
    public int upnp_class;
    public String url;
    public Object userdata;



/*
    static boolean access$2002(r r, boolean flag)
    {
        r.bUrgent = flag;
        return flag;
    }

*/



/*
    static String access$2802(bUrgent burgent, String s)
    {
        burgent.filepath = s;
        return s;
    }

*/



/*
    static long access$402(filepath filepath1, long l)
    {
        filepath1.fileSize = l;
        return l;
    }

*/



/*
    static long access$514(fileSize filesize, long l)
    {
        long l1 = l + filesize.downloadSize;
        filesize.downloadSize = l1;
        return l1;
    }

*/



/*
    static boolean access$602(downloadSize downloadsize, boolean flag)
    {
        downloadsize.cancelflag = flag;
        return flag;
    }

*/



/*
    static long access$902(cancelflag cancelflag1, long l)
    {
        cancelflag1.taskid = l;
        return l;
    }

*/

    public taskid()
    {
        index = -1;
        cancelflag = false;
        bUrgent = false;
    }
}
