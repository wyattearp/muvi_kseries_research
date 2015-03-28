// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            BaseView, LayoutController

class this._cls0 extends Handler
{

    final BaseView this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 100: // 'd'
            removeMessages(message.what);
            break;
        }
        prefetch(mLayoutController.getVisibleStart(), mLayoutController.getVisibleEnd());
        BaseView.this.preDecode(getDecodeStart(mDirection), getDecodeEnd(mDirection), mDirection);
    }

    public void preDecode()
    {
        sendEmptyMessage(100);
    }

    public (Looper looper)
    {
        this$0 = BaseView.this;
        super(looper);
    }
}
