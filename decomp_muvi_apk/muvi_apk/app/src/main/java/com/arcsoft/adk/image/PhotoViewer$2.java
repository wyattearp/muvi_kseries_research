// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import com.arcsoft.util.SystemUtils;

// Referenced classes of package com.arcsoft.adk.image:
//            PhotoViewer

class this._cls0 extends Handler
{

    final PhotoViewer this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 34
    //                   1 89;
           goto _L1 _L2 _L3
_L1:
        super.handleMessage(message);
        return;
_L2:
        if (PhotoViewer.access$1900(PhotoViewer.this, PhotoViewer.access$900(PhotoViewer.this)) == 0x80002)
        {
            PhotoViewer.access$2000(PhotoViewer.this).sendEmptyMessageDelayed(0, 50L);
        } else
        {
            PhotoViewer.access$2000(PhotoViewer.this).sendEmptyMessageDelayed(0, 3L);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        int i = message.arg1;
        int j = message.arg2;
        int k = PhotoViewer.access$2100(PhotoViewer.this);
        PhotoViewer.access$2200(PhotoViewer.this, PhotoViewer.access$900(PhotoViewer.this), getHolder().getSurface(), SystemUtils.getSDKVersion());
        if (!PhotoViewer.access$700(PhotoViewer.this).isVideo(getCurrentIndex()))
        {
            PhotoViewer.access$2302(PhotoViewer.this, true);
        }
        PhotoViewer.access$2400(PhotoViewer.this, PhotoViewer.access$900(PhotoViewer.this), k, i, j);
        if (true) goto _L1; else goto _L4
_L4:
    }

    otoViewerListener()
    {
        this$0 = PhotoViewer.this;
        super();
    }
}
