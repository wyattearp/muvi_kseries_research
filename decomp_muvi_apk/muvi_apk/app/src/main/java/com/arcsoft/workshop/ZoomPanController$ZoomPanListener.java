// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import powermobia.utils.MPoint;

// Referenced classes of package com.arcsoft.workshop:
//            ZoomPanController, EditorEngineWrapper, OnImageEventListener, WorkShop

private class <init>
    implements tener, ener
{

    final ZoomPanController this$0;

    private int doPan(int i, int j)
    {
        powermobia.photoeditor.er er = new powermobia.photoeditor.er();
        int k = ZoomPanController.access$300(ZoomPanController.this).getState(er);
        int l = i - ZoomPanController.access$100(ZoomPanController.this).x;
        int i1 = j - ZoomPanController.access$100(ZoomPanController.this).y;
        if (l != 0 || i1 != 0)
        {
            if (ZoomPanController.access$400(ZoomPanController.this) != null)
            {
                ZoomPanController.access$400(ZoomPanController.this).onChange(2, null, null);
            }
            k = ZoomPanController.access$300(ZoomPanController.this).pan(l, i1);
            if (ZoomPanController.access$400(ZoomPanController.this) != null)
            {
                ZoomPanController.access$400(ZoomPanController.this).onChange(3, null, null);
            }
        }
        ZoomPanController.access$100(ZoomPanController.this).x = i;
        ZoomPanController.access$100(ZoomPanController.this).y = j;
        return k;
    }

    public int OnPan(int i, int j)
    {
        if (ZoomPanController.access$200(ZoomPanController.this))
        {
            doPan(i, j);
        }
        return 0;
    }

    public int OnPanBegin(int i, int j)
    {
        ZoomPanController.access$100(ZoomPanController.this).x = i;
        ZoomPanController.access$100(ZoomPanController.this).y = j;
        ZoomPanController.access$202(ZoomPanController.this, true);
        return 0;
    }

    public int OnPanEnd()
    {
        if (ZoomPanController.access$200(ZoomPanController.this))
        {
            ZoomPanController.access$500(ZoomPanController.this).startAysnTask();
            ZoomPanController.access$202(ZoomPanController.this, false);
        }
        return 0;
    }

    public int OnZoom(float f)
    {
        if (ZoomPanController.access$600(ZoomPanController.this) && ZoomPanController.access$300(ZoomPanController.this) != null)
        {
            powermobia.photoeditor.is._cls0 _lcls0 = new powermobia.photoeditor.is._cls0();
            if (ZoomPanController.access$300(ZoomPanController.this).getState(_lcls0) == 0)
            {
                int i = (int)(0.5D + (double)(f * (float)_lcls0._fld0));
                doZoom(null, null, i);
            }
        }
        return 0;
    }

    public int OnZoomBegin()
    {
        ZoomPanController.access$602(ZoomPanController.this, true);
        return 0;
    }

    public int OnZoomEnd()
    {
        if (ZoomPanController.access$600(ZoomPanController.this))
        {
            ZoomPanController.access$602(ZoomPanController.this, false);
            ZoomPanController.access$500(ZoomPanController.this).startAysnTask();
        }
        return 0;
    }

    private ener()
    {
        this$0 = ZoomPanController.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
