// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements android.view.obalLayoutListener
{

    final MediaPlusActivity this$0;

    public void onGlobalLayout()
    {
        if (MediaPlusActivity.access$1400(MediaPlusActivity.this) != null)
        {
            MediaPlusActivity.access$1400(MediaPlusActivity.this).getViewTreeObserver().removeGlobalOnLayoutListener(this);
            mHandler.sendEmptyMessageDelayed(546, 200L);
        }
    }

    tListener()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
