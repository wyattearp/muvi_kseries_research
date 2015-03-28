// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0 extends Handler
{

    final PlayActivity this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 0: // '\0'
            PlayActivity.access$1600(PlayActivity.this);
            return;

        case 1: // '\001'
            Log.e("FENG", (new StringBuilder()).append("FENG MESSAGE_EDIT_PHOTO_FINISH: mCurrentIndex = ").append(PlayActivity.access$900(PlayActivity.this)).append(", msg.arg1 = ").append(message.arg1).toString());
            return;

        case 2: // '\002'
            PlayActivity.access$2800(PlayActivity.this);
            break;
        }
    }

    ()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
