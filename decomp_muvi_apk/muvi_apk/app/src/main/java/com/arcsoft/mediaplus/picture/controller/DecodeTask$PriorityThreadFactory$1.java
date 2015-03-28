// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.os.Process;

// Referenced classes of package com.arcsoft.mediaplus.picture.controller:
//            DecodeTask

class this._cls1 extends Thread
{

    final this._cls1 this$1;

    public void run()
    {
        Process.setThreadPriority(cess._mth000(this._cls1.this));
        super.run();
    }

    Q(Runnable runnable, String s)
    {
        this$1 = this._cls1.this;
        super(runnable, s);
    }
}
