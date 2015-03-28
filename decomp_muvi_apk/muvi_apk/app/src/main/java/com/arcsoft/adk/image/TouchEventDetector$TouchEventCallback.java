// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.view.MotionEvent;

// Referenced classes of package com.arcsoft.adk.image:
//            TouchEventDetector

public static interface A
{

    public abstract boolean OnMultiTouchBegin(TouchEventDetector toucheventdetector);

    public abstract void OnMultiTouchEnd(TouchEventDetector toucheventdetector);

    public abstract boolean OnMultiTouchNext(TouchEventDetector toucheventdetector);

    public abstract void onDoubleTap(int i, int j);

    public abstract boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1);

    public abstract void onSingleKeyDown(int i, int j);

    public abstract void onSingleKeyMove(int i, int j);

    public abstract void onSingleKeyUp(int i, int j);
}
