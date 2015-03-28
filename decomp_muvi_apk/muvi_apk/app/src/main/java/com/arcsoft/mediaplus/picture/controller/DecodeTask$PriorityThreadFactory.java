// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.arcsoft.mediaplus.picture.controller:
//            DecodeTask

public class mPriority
    implements ThreadFactory
{

    private final String mName;
    private final AtomicInteger mNumber = new AtomicInteger();
    private final int mPriority;
    final DecodeTask this$0;

    public Thread newThread(Runnable runnable)
    {
        return new Thread(runnable, (new StringBuilder()).append(mName).append('-').append(mNumber.getAndIncrement()).toString()) {

            final DecodeTask.PriorityThreadFactory this$1;

            public void run()
            {
                Process.setThreadPriority(mPriority);
                super.run();
            }

            
            {
                this$1 = DecodeTask.PriorityThreadFactory.this;
                super(runnable, s);
            }
        };
    }


    public _cls1.this._cls1(String s, int i)
    {
        this$0 = DecodeTask.this;
        super();
        mName = s;
        mPriority = i;
    }
}
