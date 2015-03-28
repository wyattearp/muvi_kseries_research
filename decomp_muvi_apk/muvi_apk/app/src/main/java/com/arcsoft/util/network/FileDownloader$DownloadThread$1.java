// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import java.io.File;

// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

class val.tmpfile
    implements Runnable
{

    final val.tmpfile this$1;
    final File val$oldfile;
    final File val$tmpfile;

    public void run()
    {
        val$oldfile.delete();
        val$tmpfile.delete();
    }

    ()
    {
        this$1 = final_;
        val$oldfile = file;
        val$tmpfile = File.this;
        super();
    }
}
