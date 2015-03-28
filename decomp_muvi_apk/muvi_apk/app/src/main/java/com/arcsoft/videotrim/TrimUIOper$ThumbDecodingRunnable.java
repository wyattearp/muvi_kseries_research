// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.videotrim.Utils.UtilFunc;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimUIOper

private class <init>
    implements Runnable
{

    final TrimUIOper this$0;

    public void run()
    {
        Object obj = TrimUIOper.access$3200(TrimUIOper.this);
        obj;
        JVM INSTR monitorenter ;
_L5:
        if (!TrimUIOper.access$3000(TrimUIOper.this)) goto _L2; else goto _L1
_L1:
        int i = TrimUIOper.access$3300(TrimUIOper.this);
        if (i >= TrimUIOper.access$2300(TrimUIOper.this) || i < 0)
        {
            break MISSING_BLOCK_LABEL_145;
        }
        Bitmap bitmap = (Bitmap)UtilFunc.getClipThumbnail(TrimUIOper.access$3400(TrimUIOper.this), i, TrimUIOper.access$2100(TrimUIOper.this), TrimUIOper.access$3500(TrimUIOper.this), false, false);
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        Message message = new Message();
        message.what = 1;
        message.arg1 = i;
        message.obj = bitmap;
        TrimUIOper.access$3600(TrimUIOper.this, i, bitmap);
        mhandler.sendMessage(message);
        try
        {
            Thread.sleep(100L);
        }
        catch (InterruptedException interruptedexception) { }
        continue; /* Loop/switch isn't completed */
        int j = 0;
_L3:
        if (j >= 10)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag;
        Thread.sleep(100L);
        flag = TrimUIOper.access$3000(TrimUIOper.this);
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        j++;
        if (true) goto _L3; else goto _L2
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception1;
        interruptedexception1;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private ()
    {
        this$0 = TrimUIOper.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
