// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropSizeWindow

class this._cls0
    implements android.view.r
{

    final UICropSizeWindow this$0;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (UICropSizeWindow.access$000(UICropSizeWindow.this, (int)motionevent.getRawX(), (int)motionevent.getRawY()))
        {
            return true;
        }
        if (mBaseView != null)
        {
            ((CheckBox)mBaseView).setChecked(false);
        }
        return false;
    }

    ()
    {
        this$0 = UICropSizeWindow.this;
        super();
    }
}
