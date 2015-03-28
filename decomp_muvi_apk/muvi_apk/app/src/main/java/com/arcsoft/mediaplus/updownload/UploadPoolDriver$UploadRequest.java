// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;


// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UploadPoolDriver

public static class abortflag
{

    private boolean abortflag;
    private boolean cancelflag;
    public String dms_uuid;
    public long fileSize;
    public r listener;
    public String protocolInfo;
    public int state;
    public long tableid;
    public String title;
    public int uploadId;
    public long uploadSize;
    private int uploadresult;
    public String uri;
    public Object userdata;



/*
    static boolean access$002(r r, boolean flag)
    {
        r.cancelflag = flag;
        return flag;
    }

*/



/*
    static boolean access$102(cancelflag cancelflag1, boolean flag)
    {
        cancelflag1.abortflag = flag;
        return flag;
    }

*/



/*
    static int access$702(abortflag abortflag1, int i)
    {
        abortflag1.uploadresult = i;
        return i;
    }

*/

    public uploadresult()
    {
        uploadSize = 0L;
        uploadresult = -1;
        cancelflag = false;
        abortflag = false;
    }
}
