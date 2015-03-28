// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.FileUtils;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView

class this._cls0 extends Handler
{

    final ImageDMPPlayView this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 1537 1539: default 36
    //                   1537 36
    //                   1538 36
    //                   1539 37;
           goto _L1 _L1 _L1 _L2
_L1:
        return;
_L2:
        if (message.arg1 == 817 || message.arg1 == 819)
        {
            com.arcsoft.mediaplus.updownload.orCode.showUpDownloadError(mContext, 1, message.arg1, new Object[0]);
            return;
        }
        if (message.obj != null)
        {
            String s;
            if (message.obj instanceof com.arcsoft.mediaplus.updownload.dResult)
            {
                s = ((com.arcsoft.mediaplus.updownload.dResult)message.obj).request.uri;
            } else
            {
                s = ((com.arcsoft.mediaplus.updownload.loadResult)message.obj).request.uri;
            }
            if (message.arg1 == 911)
            {
                android.content.Context context1 = mContext;
                int k = message.arg1;
                Object aobj1[] = new Object[1];
                aobj1[0] = ((com.arcsoft.mediaplus.updownload.dResult)message.obj).filePath;
                com.arcsoft.mediaplus.updownload.orCode.showUpDownloadError(context1, 0, k, aobj1);
                return;
            }
            if (message.arg1 == 1015)
            {
                com.arcsoft.mediaplus.updownload.orCode.showUpDownloadError(mContext, 1, message.arg1, new Object[] {
                    s
                });
                return;
            }
            android.content.Context context = mContext;
            int i;
            int j;
            Object aobj[];
            String s1;
            if (FileUtils.isLocalItem(Uri.parse(s)))
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = message.arg1;
            aobj = new Object[1];
            if (message.obj instanceof com.arcsoft.mediaplus.updownload.dResult)
            {
                s1 = ((com.arcsoft.mediaplus.updownload.dResult)message.obj).request.title;
            } else
            {
                s1 = ((com.arcsoft.mediaplus.updownload.loadResult)message.obj).request.title;
            }
            aobj[0] = s1;
            com.arcsoft.mediaplus.updownload.orCode.showUpDownloadError(context, i, j, aobj);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    UploadRequest()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
