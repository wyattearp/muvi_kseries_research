// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.graphics.PointF;
import android.view.MotionEvent;

// Referenced classes of package com.arcsoft.adk.image:
//            MultiTouchGestureDetector

public static interface 
{

    public abstract boolean onEndPinch(MotionEvent motionevent);

    public abstract boolean onPinch(MotionEvent motionevent, MotionEvent motionevent1, PointF pointf, float f);

    public abstract boolean onStartPinch(MotionEvent motionevent);
}
