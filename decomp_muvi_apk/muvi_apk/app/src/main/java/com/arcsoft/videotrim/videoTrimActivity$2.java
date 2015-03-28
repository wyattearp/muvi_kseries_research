// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.arcsoft.videotrim.Utils.UtilFunc;
import java.io.File;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity

class this._cls0 extends Handler
{

    final videoTrimActivity this$0;

    public void handleMessage(Message message)
    {
        if (message.what == 0)
        {
            videoTrimActivity.access$202(videoTrimActivity.this, (String)message.obj);
            UtilFunc.addVideoFileByFullName(getApplicationContext(), videoTrimActivity.access$200(videoTrimActivity.this));
            if (videoTrimActivity.access$300(videoTrimActivity.this))
            {
                if (UtilFunc.IsFileExisted(videoTrimActivity.access$200(videoTrimActivity.this)))
                {
                    UtilFunc.shareVideoByUri(Uri.fromFile(new File(videoTrimActivity.access$200(videoTrimActivity.this))), videoTrimActivity.this);
                }
            } else
            {
                Toast.makeText(videoTrimActivity.this, 0x7f0b01c5, 0).show();
            }
            videoTrimActivity.access$402(videoTrimActivity.this, true);
        }
    }

    ()
    {
        this$0 = videoTrimActivity.this;
        super();
    }
}
