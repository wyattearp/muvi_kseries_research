// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropBar, UISaveDialog

private class this._cls0 extends HandlerThread
{

    private final Handler mUIHandle = new Handler() {

        final UICropBar.CropHandlerThread this$1;

        public void handleMessage(Message message)
        {
            if (message.what == 0)
            {
                UICropBar.access$200(this$0).dismiss();
                UICropBar.access$400(this$0).switchToEdit();
            }
        }

            
            {
                this$1 = UICropBar.CropHandlerThread.this;
                super();
            }
    };
    final UICropBar this$0;

    public void run()
    {
        UICropBar.access$500(UICropBar.this).setBgState(1);
        new MRect();
        MRect mrect = UICropBar.access$100(UICropBar.this).ropRect();
        MRect mrect1 = new MRect();
        UICropBar.access$300(UICropBar.this).onCommand(18, mrect1, null);
        UICropBar.access$300(UICropBar.this).onCommand(0, Integer.valueOf(1), null);
        if (mrect1.width() == mrect.width() && mrect1.height() == mrect.height())
        {
            WorkShopUtils.back2BestFitModeForEdit(UICropBar.access$500(UICropBar.this));
            mUIHandle.sendEmptyMessage(0);
            return;
        } else
        {
            UICropBar.access$300(UICropBar.this).onCommand(17, new Rect(mrect.left, mrect.top, mrect.right, mrect.bottom), null);
            mUIHandle.sendEmptyMessage(0);
            return;
        }
    }

    public _cls1.this._cls1(String s)
    {
        this$0 = UICropBar.this;
        super(s);
    }
}
