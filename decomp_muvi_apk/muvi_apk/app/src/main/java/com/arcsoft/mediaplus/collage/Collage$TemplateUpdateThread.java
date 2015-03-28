// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage

private class mPicNum extends Thread
{

    private int mPicNum;
    final Collage this$0;

    public void run()
    {
        Collage.access$302(Collage.this, true);
        int i = Collage.access$800(Collage.this, mPicNum);
        if (Collage.access$900(Collage.this) != null)
        {
            Message message = new Message();
            message.what = 0;
            message.arg1 = i;
            Collage.access$900(Collage.this).sendMessage(message);
        }
    }

    public (int i)
    {
        this$0 = Collage.this;
        super();
        mPicNum = 0;
        mPicNum = i;
    }
}
