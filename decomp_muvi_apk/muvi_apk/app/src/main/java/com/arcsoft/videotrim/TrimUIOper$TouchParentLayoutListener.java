// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.view.MotionEvent;
import android.view.View;
import com.arcsoft.videotrim.Utils.UtilFunc;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimUIOper

private class <init>
    implements android.view.Listener
{

    int TouchX;
    boolean isControlIndicator;
    boolean isMoving;
    boolean isZoomMode;
    final TrimUIOper this$0;
    int touchDownPos;
    float touchDownSpace;
    long touchDownTime;
    int touchMovePos;
    float touchUpSpace;
    long touchUpTime;

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (!TrimUIOper.access$200(TrimUIOper.this)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int i;
        TouchX = (int)motionevent.getX();
        i = 0xff & motionevent.getAction();
        if (i != 0) goto _L4; else goto _L3
_L3:
        touchDownTime = System.currentTimeMillis();
        isMoving = false;
        touchDownPos = TouchX;
        touchMovePos = TouchX;
        if (TouchX <= TrimUIOper.access$300(TrimUIOper.this) + TrimUIOper.access$400(TrimUIOper.this) || TouchX >= TrimUIOper.access$500(TrimUIOper.this)) goto _L6; else goto _L5
_L5:
        isControlIndicator = true;
        TrimUIOper.access$602(TrimUIOper.this, TrimUIOper.access$700(TrimUIOper.this));
        TrimUIOper.access$800(TrimUIOper.this, false);
_L7:
        return true;
_L6:
        isControlIndicator = false;
        return false;
_L4:
        if (i == 5)
        {
            isZoomMode = true;
            touchDownSpace = TrimUIOper.access$900(TrimUIOper.this, motionevent);
        } else
        {
label0:
            {
                if (i != 6)
                {
                    break label0;
                }
                touchUpSpace = TrimUIOper.access$900(TrimUIOper.this, motionevent);
            }
        }
          goto _L7
        if (i != 2) goto _L9; else goto _L8
_L8:
        isMoving = true;
        if (!isControlIndicator) goto _L1; else goto _L10
_L10:
        if (isZoomMode)
        {
            return true;
        }
        int l = TouchX - touchMovePos;
        TrimUIOper.access$1000(TrimUIOper.this, l, isMoving);
        touchMovePos = TouchX;
          goto _L7
_L9:
        if (i != 1) goto _L7; else goto _L11
_L11:
        touchUpTime = System.currentTimeMillis();
        isMoving = false;
        if (!isControlIndicator) goto _L1; else goto _L12
_L12:
        if (!isZoomMode) goto _L14; else goto _L13
_L13:
        isZoomMode = false;
        if (touchDownSpace - touchUpSpace <= 100F) goto _L16; else goto _L15
_L15:
        UtilFunc.Log("TrimUIOper", "TouchParentLayoutListener --- > ZOOM: detachTrim()");
        TrimUIOper.access$1100(TrimUIOper.this);
_L17:
        return true;
_L16:
        if (touchUpSpace - touchDownSpace > 100F)
        {
            UtilFunc.Log("TrimUIOper", "TouchParentLayoutListener --- > ZOOM: attachTrim()");
            TrimUIOper.access$1200(TrimUIOper.this);
        }
        if (true) goto _L17; else goto _L14
_L14:
        if (touchDownPos - TouchX > -30 && touchDownPos - TouchX < 30 && (float)Math.abs(touchUpTime - touchDownTime) < 500F)
        {
            UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchParentLayoutListener --- > onClick, moveToCenterByPos: ").append(TouchX).toString());
            int k = TouchX - TrimUIOper.access$300(TrimUIOper.this) - TrimUIOper.access$400(TrimUIOper.this);
            TrimUIOper.access$1300(TrimUIOper.this, TouchX);
            TrimUIOper.access$1400(TrimUIOper.this, k);
            return true;
        }
        int j = TouchX - touchMovePos;
        UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchParentLayoutListener --- > ACTION_UP, TouchParentLayoutMoving: ").append(TouchX).toString());
        TrimUIOper.access$1000(TrimUIOper.this, j, isMoving);
          goto _L7
    }

    private ()
    {
        this$0 = TrimUIOper.this;
        super();
        TouchX = 0;
        isControlIndicator = false;
        touchDownPos = 0;
        touchMovePos = 0;
        isZoomMode = false;
        touchDownSpace = 0.0F;
        touchUpSpace = 0.0F;
        isMoving = false;
    }

    isMoving(isMoving ismoving)
    {
        this();
    }
}
