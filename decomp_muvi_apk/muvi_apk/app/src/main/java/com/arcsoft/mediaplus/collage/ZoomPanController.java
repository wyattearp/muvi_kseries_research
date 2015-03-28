// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.content.Context;
import android.graphics.Point;
import java.util.ArrayList;
import powermobia.utils.MBitmap;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage

public final class ZoomPanController
{
    public static interface OnPanGestureListener
    {

        public abstract int OnPan(int i, int j);

        public abstract int OnPanBegin(int i, int j);

        public abstract int OnPanEnd();
    }

    public static interface OnRotateGestureListener
    {

        public abstract int OnRotate(int i);

        public abstract int OnRotateBegin();

        public abstract int OnRotateEnd();
    }

    public static interface OnZoomGestureListener
    {

        public abstract int OnZoom(float f);

        public abstract int OnZoomBegin();

        public abstract int OnZoomEnd();
    }

    private class ZoomPanRotateListener
        implements OnZoomGestureListener, OnPanGestureListener, OnRotateGestureListener
    {

        final ZoomPanController this$0;

        private int doPan(int i, int j)
        {
            int k = i - mPtPanPrePos.x;
            int l = j - mPtPanPrePos.y;
            int i1 = -k;
            int j1 = -l;
            if (i1 != 0 || j1 != 0)
            {
                ArrayList arraylist = mCollage.getDisRects2Img();
                ArrayList arraylist1 = mCollage.getFitoutImgRects();
                if (arraylist.size() <= 0 || arraylist1.size() <= 0 || mCurrPanRectIndex >= arraylist.size() || mCurrPanRectIndex >= arraylist1.size())
                {
                    return 5;
                }
                MRect mrect = (MRect)arraylist.get(mCurrPanRectIndex);
                MRect mrect1 = (MRect)arraylist1.get(mCurrPanRectIndex);
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
            mPtPanPrePos.x = i;
            mPtPanPrePos.y = j;
            mCollage.drawPart(mCurrPanRectIndex);
            return 0;
        }

        public int OnPan(int i, int j)
        {
            if (mIsPaning && mCurrPanRectIndex != -1)
            {
                doPan(i, j);
            }
            return 0;
        }

        public int OnPanBegin(int i, int j)
        {
            mPtPanPrePos.x = i;
            mPtPanPrePos.y = j;
            mIsPaning = true;
            mCurrPanRectIndex = mCollage.getCurrRectIndex(i, j);
            return 0;
        }

        public int OnPanEnd()
        {
            if (mIsPaning)
            {
                mIsPaning = false;
            }
            mCurrPanRectIndex = -1;
            return 0;
        }

        public int OnRotate(int i)
        {
            int l;
label0:
            {
                Point point = mCollage.getZoomFirstPointerPos();
                if (point.x != -1 && point.y != -1)
                {
                    mCurrRectIndex = mCollage.getCurrRectIndex(point.x, point.y);
                    if (mCurrRectIndex != -1)
                    {
                        int j = -i;
                        int k = ((Integer)mCollage.getRotateAngles().get(mCurrRectIndex)).intValue();
                        l = (j + k) % 360;
                        if (l != k)
                        {
                            break label0;
                        }
                    }
                }
                return 0;
            }
            mCollage.getRotateAngles().remove(mCurrRectIndex);
            mCollage.getRotateAngles().add(mCurrRectIndex, new Integer(l));
            doRotate(l);
            return 0;
        }

        public int OnRotateBegin()
        {
            mIsRotating = true;
            return 0;
        }

        public int OnRotateEnd()
        {
            if (mIsRotating)
            {
                mIsRotating = true;
            }
            mCurrRectIndex = -1;
            return 0;
        }

        public int OnZoom(float f)
        {
            Point point = mCollage.getZoomFirstPointerPos();
            if (point.x != -1 && point.y != -1)
            {
                mCurrRectIndex = mCollage.getCurrRectIndex(point.x, point.y);
                if (mCurrRectIndex != -1)
                {
                    int i = ((Integer)mCollage.getZoomScales().get(mCurrRectIndex)).intValue();
                    if (f < 1.0F && i == 1000 || f > 1.0F && 2000 == i)
                    {
                        return 0;
                    }
                    int j = (int)(f * (float)i);
                    if (j < 1000)
                    {
                        MRect mrect = mCollage.getOriFitoutRect(mCurrRectIndex);
                        MRect mrect1 = (MRect)mCollage.getFitoutImgRects().get(mCurrRectIndex);
                        j = 1000;
                        if ((1.0F * (float)mrect.width()) / (float)mrect1.width() <= (1.0F * (float)mrect.height()) / (float)mrect1.height());
                    }
                    if (j > 2000)
                    {
                        j = 2000;
                    }
                    mCollage.getZoomScales().remove(mCurrRectIndex);
                    mCollage.getZoomScales().add(mCurrRectIndex, new Integer(j));
                    doZoom(((float)j - (float)i) / 1000F, j);
                }
            }
            return 0;
        }

        public int OnZoomBegin()
        {
            mIsZooming = true;
            return 0;
        }

        public int OnZoomEnd()
        {
            if (mIsZooming)
            {
                mIsZooming = false;
            }
            mCurrRectIndex = -1;
            return 0;
        }

        private ZoomPanRotateListener()
        {
            this$0 = ZoomPanController.this;
            super();
        }

    }


    private Collage mCollage;
    private int mCurrPanRectIndex;
    private int mCurrRectIndex;
    private boolean mIsPaning;
    private boolean mIsRotating;
    private boolean mIsZooming;
    private MPoint mPtPanPrePos;
    private ZoomPanRotateListener mZoomPanRotateListener;

    public ZoomPanController(Context context)
    {
        mCollage = null;
        mPtPanPrePos = new MPoint();
        mIsPaning = false;
        mIsRotating = false;
        mIsZooming = false;
        mCurrRectIndex = -1;
        mCurrPanRectIndex = -1;
        mCollage = (Collage)context;
    }

    public OnPanGestureListener createOnPanGestureListener()
    {
        if (mZoomPanRotateListener == null)
        {
            mZoomPanRotateListener = new ZoomPanRotateListener();
        }
        return mZoomPanRotateListener;
    }

    public OnRotateGestureListener createOnRotateGestureListener()
    {
        if (mZoomPanRotateListener == null)
        {
            mZoomPanRotateListener = new ZoomPanRotateListener();
        }
        return mZoomPanRotateListener;
    }

    public OnZoomGestureListener createOnZoomGestureListener()
    {
        if (mZoomPanRotateListener == null)
        {
            mZoomPanRotateListener = new ZoomPanRotateListener();
        }
        return mZoomPanRotateListener;
    }

    public int doRotate(int i)
    {
        return 0;
    }

    public int doZoom(float f, int i)
    {
        MBitmap mbitmap = (MBitmap)mCollage.getOriBitmapList().get(mCurrRectIndex);
        int j = mbitmap.getWidth();
        int k = mbitmap.getHeight();
        float f1 = (1.0F * (float)i) / 1000F;
        ((Integer)mCollage.getRotateAngles().get(mCurrRectIndex)).intValue();
        MBitmap mbitmap1 = mbitmap.resample((int)(f1 * (float)j), (int)(f1 * (float)k), 1);
        if (mbitmap1 == null)
        {
            return -1;
        }
        ((MBitmap)mCollage.getBitmapList().get(mCurrRectIndex)).recycle();
        mCollage.getBitmapList().remove(mCurrRectIndex);
        mCollage.getBitmapList().add(mCurrRectIndex, mbitmap1);
        MRect mrect = (MRect)mCollage.getDisRects2Img().get(mCurrRectIndex);
        int l = (int)(f * (float)j) / 2;
        int i1 = (int)(f * (float)k) / 2;
        MRect mrect1 = (MRect)mCollage.getFitoutImgRects().get(mCurrRectIndex);
        mrect1.set(0, 0, mbitmap1.getWidth(), mbitmap1.getHeight());
        if (i1 + mrect.top < mrect1.top)
        {
            i1 = mrect1.top - mrect.top;
        }
        if (i1 + mrect.bottom > mrect1.bottom)
        {
            i1 = mrect1.bottom - mrect.bottom;
        }
        if (l + mrect.left < mrect1.left)
        {
            l = mrect1.left - mrect.left;
        }
        if (l + mrect.right > mrect1.right)
        {
            l = mrect1.right - mrect.right;
        }
        Collage.translateRect(mrect, l, i1);
        mCollage.drawPart(mCurrRectIndex);
        return 0;
    }

    public boolean isZoomPaning()
    {
        return mIsPaning || mIsZooming;
    }

    public void resetZoomPaningState()
    {
        mIsZooming = false;
        mIsPaning = false;
    }




/*
    static boolean access$202(ZoomPanController zoompancontroller, boolean flag)
    {
        zoompancontroller.mIsPaning = flag;
        return flag;
    }

*/



/*
    static int access$302(ZoomPanController zoompancontroller, int i)
    {
        zoompancontroller.mCurrPanRectIndex = i;
        return i;
    }

*/




/*
    static boolean access$502(ZoomPanController zoompancontroller, boolean flag)
    {
        zoompancontroller.mIsZooming = flag;
        return flag;
    }

*/



/*
    static int access$602(ZoomPanController zoompancontroller, int i)
    {
        zoompancontroller.mCurrRectIndex = i;
        return i;
    }

*/



/*
    static boolean access$702(ZoomPanController zoompancontroller, boolean flag)
    {
        zoompancontroller.mIsRotating = flag;
        return flag;
    }

*/
}
