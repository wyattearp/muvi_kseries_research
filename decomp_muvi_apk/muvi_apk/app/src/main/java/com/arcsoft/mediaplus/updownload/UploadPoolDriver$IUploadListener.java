// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;


// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UploadPoolDriver

public static interface 
{

    public abstract void onUploadFinished( );

    public abstract void onUploadProgress( , long l);

    public abstract void onUploadStarted( , long l);
}
