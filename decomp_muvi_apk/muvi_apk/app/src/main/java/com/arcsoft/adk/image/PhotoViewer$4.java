// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.image:
//            PhotoViewer

class this._cls0
    implements android.view.Callback
{

    final PhotoViewer this$0;

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        if (PhotoViewer.access$2900(PhotoViewer.this) == 0 || PhotoViewer.access$3000(PhotoViewer.this) == 0)
        {
            setDisplaySize(j, k);
        }
        if (PhotoViewer.access$700(PhotoViewer.this) != null)
        {
            PhotoViewer.access$700(PhotoViewer.this).resetControlTimer();
        }
        if (!PhotoViewer.access$3200(PhotoViewer.this, j, k))
        {
            Log.e("PhotoViewer", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Surface size not match display orientation.").toString());
            return;
        } else
        {
            Message message = PhotoViewer.access$2000(PhotoViewer.this).obtainMessage(1);
            message.arg1 = j;
            message.arg2 = k;
            PhotoViewer.access$2000(PhotoViewer.this).sendMessageDelayed(message, 10L);
            return;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        Log.e("PhotoViewer", "surfaceCreated:");
        PhotoViewer photoviewer = PhotoViewer.this;
        boolean flag;
        if (!PhotoViewer.access$1200(PhotoViewer.this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        PhotoViewer.access$1702(photoviewer, flag);
        PhotoViewer.access$3302(PhotoViewer.this, true);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        PhotoViewer.access$2200(PhotoViewer.this, PhotoViewer.access$900(PhotoViewer.this), null, SystemUtils.getSDKVersion());
    }

    otoViewerListener()
    {
        this$0 = PhotoViewer.this;
        super();
    }
}
