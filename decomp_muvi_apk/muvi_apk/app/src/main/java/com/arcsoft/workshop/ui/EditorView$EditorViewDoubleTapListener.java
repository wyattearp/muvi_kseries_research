// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.view.MotionEvent;
import com.arcsoft.workshop.utils.WorkShopUtils;

// Referenced classes of package com.arcsoft.workshop.ui:
//            EditorView

private class <init>
    implements android.view.apListener
{

    final EditorView this$0;

    public boolean onDoubleTap(MotionEvent motionevent)
    {
        WorkShopUtils.back2BestFitMode(EditorView.access$700(EditorView.this));
        return false;
    }

    public boolean onDoubleTapEvent(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionevent)
    {
        return false;
    }

    private ()
    {
        this$0 = EditorView.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
