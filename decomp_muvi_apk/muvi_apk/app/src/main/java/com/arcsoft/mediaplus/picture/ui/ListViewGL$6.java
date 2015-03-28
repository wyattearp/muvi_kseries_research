// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.ToastMgr;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

class this._cls0 extends Handler
{

    final ListViewGL this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 1539 1543: default 44
    //                   1539 53
    //                   1540 44
    //                   1541 392
    //                   1542 44
    //                   1543 435;
           goto _L1 _L2 _L1 _L3 _L1 _L4
_L1:
        ListViewGL.this.handleMessage(message);
_L6:
        return;
_L2:
        Log.d("FENG", (new StringBuilder()).append("FENG local MSG_UPDOWNLOAD_FINISH IN msg.arg1 = ").append(message.arg1).toString());
        if (message.arg1 != 817 && message.arg1 != 819)
        {
            continue; /* Loop/switch isn't completed */
        }
        com.arcsoft.mediaplus.updownload.ls.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[0]);
        continue; /* Loop/switch isn't completed */
        if (message.obj == null) goto _L6; else goto _L5
_L5:
        if (message.arg1 == 911)
        {
            Context context1 = mContext;
            int l = message.arg1;
            Object aobj1[] = new Object[1];
            aobj1[0] = ((com.arcsoft.mediaplus.updownload.ownloadResult)message.obj).filePath;
            com.arcsoft.mediaplus.updownload.ls.ErrorCode.showUpDownloadError(context1, 0, l, aobj1);
        } else
        if (message.arg1 == 1015)
        {
            String s2 = Uri.decode(((com.arcsoft.mediaplus.updownload.ver.UploadResult)message.obj).request.uri);
            com.arcsoft.mediaplus.updownload.ls.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[] {
                s2
            });
        } else
        {
            Context context = mContext;
            int j;
            int k;
            Object aobj[];
            String s1;
            if (message.obj instanceof com.arcsoft.mediaplus.updownload.ver.UploadResult)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            k = message.arg1;
            aobj = new Object[1];
            if (message.obj instanceof com.arcsoft.mediaplus.updownload.ownloadResult)
            {
                s1 = ((com.arcsoft.mediaplus.updownload.ownloadResult)message.obj).request.title;
            } else
            {
                s1 = ((com.arcsoft.mediaplus.updownload.ver.UploadResult)message.obj).request.title;
            }
            aobj[0] = s1;
            com.arcsoft.mediaplus.updownload.ls.ErrorCode.showUpDownloadError(context, j, k, aobj);
        }
        if (ListViewGL.access$200(ListViewGL.this) != null && ListViewGL.access$200(ListViewGL.this).getAllTaskCount() == 0)
        {
            ToastMgr.showToast(mContext, mContext.getString(0x7f0b00ae), 0);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        ListViewGL listviewgl = ListViewGL.this;
        String s = (String)message.obj;
        int i = message.arg1;
        boolean flag = false;
        if (i != 0)
        {
            flag = true;
        }
        listviewgl.updateTextStatus(s, flag);
        continue; /* Loop/switch isn't completed */
_L4:
        if (message.obj != null && (message.obj instanceof String))
        {
            Toast.makeText(mContext, (String)message.obj, 1).show();
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    ownloadRequest()
    {
        this$0 = ListViewGL.this;
        super();
    }
}
