// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.graphics.Point;
import java.util.ArrayList;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            ZoomPanController, Collage

private class <init>
    implements <init>, <init>, r
{

    final ZoomPanController this$0;

    private int doPan(int i, int j)
    {
        int k = i - ZoomPanController.access$100(ZoomPanController.this).x;
        int l = j - ZoomPanController.access$100(ZoomPanController.this).y;
        int i1 = -k;
        int j1 = -l;
        if (i1 != 0 || j1 != 0)
        {
            ArrayList arraylist = ZoomPanController.access$400(ZoomPanController.this).getDisRects2Img();
            ArrayList arraylist1 = ZoomPanController.access$400(ZoomPanController.this).getFitoutImgRects();
            if (arraylist.size() <= 0 || arraylist1.size() <= 0 || ZoomPanController.access$300(ZoomPanController.this) >= arraylist.size() || ZoomPanController.access$300(ZoomPanController.this) >= arraylist1.size())
            {
                return 5;
            }
            MRect mrect = (MRect)arraylist.get(ZoomPanController.access$300(ZoomPanController.this));
            MRect mrect1 = (MRect)arraylist1.get(ZoomPanController.access$300(ZoomPanController.this));
            if (mrect1 != null)
            {
                if (i1 + mrect.left < mrect1.left)
                {
                    i1 = mrect1.left - mrect.left;
                }
                if (i1 + mrect.right > mrect1.right)
                {
                    i1 = mrect1.right - mrect.right;
                }
                if (j1 + mrect.top < mrect1.top)
                {
                    j1 = mrect1.top - mrect.top;
                }
                if (j1 + mrect.bottom > mrect1.bottom)
                {
                    j1 = mrect1.bottom - mrect.bottom;
                }
            }
            mrect.left = i1 + mrect.left;
            mrect.top = j1 + mrect.top;
            mrect.right = i1 + mrect.right;
            mrect.bottom = j1 + mrect.bottom;
        }
        ZoomPanController.access$100(ZoomPanController.this).x = i;
        ZoomPanController.access$100(ZoomPanController.this).y = j;
        ZoomPanController.access$400(ZoomPanController.this).drawPart(ZoomPanController.access$300(ZoomPanController.this));
        return 0;
    }

    public int OnPan(int i, int j)
    {
        if (ZoomPanController.access$200(ZoomPanController.this) && ZoomPanController.access$300(ZoomPanController.this) != -1)
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
        ZoomPanController.access$302(ZoomPanController.this, ZoomPanController.access$400(ZoomPanController.this).getCurrRectIndex(i, j));
        return 0;
    }

    public int OnPanEnd()
    {
        if (ZoomPanController.access$200(ZoomPanController.this))
        {
            ZoomPanController.access$202(ZoomPanController.this, false);
        }
        ZoomPanController.access$302(ZoomPanController.this, -1);
        return 0;
    }

    public int OnRotate(int i)
    {
        int l;
label0:
        {
            Point point = ZoomPanController.access$400(ZoomPanController.this).getZoomFirstPointerPos();
            if (point.x != -1 && point.y != -1)
            {
                ZoomPanController.access$602(ZoomPanController.this, ZoomPanController.access$400(ZoomPanController.this).getCurrRectIndex(point.x, point.y));
                if (ZoomPanController.access$600(ZoomPanController.this) != -1)
                {
                    int j = -i;
                    int k = ((Integer)ZoomPanController.access$400(ZoomPanController.this).getRotateAngles().get(ZoomPanController.access$600(ZoomPanController.this))).intValue();
                    l = (j + k) % 360;
                    if (l != k)
                    {
                        break label0;
                    }
                }
            }
            return 0;
        }
        ZoomPanController.access$400(ZoomPanController.this).getRotateAngles().remove(ZoomPanController.access$600(ZoomPanController.this));
        ZoomPanController.access$400(ZoomPanController.this).getRotateAngles().add(ZoomPanController.access$600(ZoomPanController.this), new Integer(l));
        doRotate(l);
        return 0;
    }

    public int OnRotateBegin()
    {
        ZoomPanController.access$702(ZoomPanController.this, true);
        return 0;
    }

    public int OnRotateEnd()
    {
        if (ZoomPanController.access$700(ZoomPanController.this))
        {
            ZoomPanController.access$702(ZoomPanController.this, true);
        }
        ZoomPanController.access$602(ZoomPanController.this, -1);
        return 0;
    }

    public int OnZoom(float f)
    {
        Point point = ZoomPanController.access$400(ZoomPanController.this).getZoomFirstPointerPos();
        if (point.x != -1 && point.y != -1)
        {
            ZoomPanController.access$602(ZoomPanController.this, ZoomPanController.access$400(ZoomPanController.this).getCurrRectIndex(point.x, point.y));
            if (ZoomPanController.access$600(ZoomPanController.this) != -1)
            {
                int i = ((Integer)ZoomPanController.access$400(ZoomPanController.this).getZoomScales().get(ZoomPanController.access$600(ZoomPanController.this))).intValue();
                if (f < 1.0F && i == 1000 || f > 1.0F && 2000 == i)
                {
                    return 0;
                }
                int j = (int)(f * (float)i);
                if (j < 1000)
                {
                    MRect mrect = ZoomPanController.access$400(ZoomPanController.this).getOriFitoutRect(ZoomPanController.access$600(ZoomPanController.this));
                    MRect mrect1 = (MRect)ZoomPanController.access$400(ZoomPanController.this).getFitoutImgRects().get(ZoomPanController.access$600(ZoomPanController.this));
                    j = 1000;
                    if ((1.0F * (float)mrect.width()) / (float)mrect1.width() <= (1.0F * (float)mrect.height()) / (float)mrect1.height());
                }
                if (j > 2000)
                {
                    j = 2000;
                }
                ZoomPanController.access$400(ZoomPanController.this).getZoomScales().remove(ZoomPanController.access$600(ZoomPanController.this));
                ZoomPanController.access$400(ZoomPanController.this).getZoomScales().add(ZoomPanController.access$600(ZoomPanController.this), new Integer(j));
                doZoom(((float)j - (float)i) / 1000F, j);
            }
        }
        return 0;
    }

    public int OnZoomBegin()
    {
        ZoomPanController.access$502(ZoomPanController.this, true);
        return 0;
    }

    public int OnZoomEnd()
    {
        if (ZoomPanController.access$500(ZoomPanController.this))
        {
            ZoomPanController.access$502(ZoomPanController.this, false);
        }
        ZoomPanController.access$602(ZoomPanController.this, -1);
        return 0;
    }

    private r()
    {
        this$0 = ZoomPanController.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
