// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;
import android.view.MotionEvent;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.util.ArrayList;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIRegionBase, Line, MultiTouchDetector

public class UIRegionCrop extends UIRegionBase
{

    public static final int REGION_16V9_STYLE = 4;
    public static final int REGION_3V4_STYLE = 3;
    public static final int REGION_4V3_STYLE = 2;
    public static final int REGION_FREE_STYLE = 1;
    private MBitmap mCropMBitmap;
    private MRect mForeRect;
    private final ArrayList mInnerLineList = new ArrayList();
    private boolean mIsCropMBitmapValid;
    private MRect mOldCropRect;
    private MultiTouchDetector mScaleGestureDetector;
    private final MRect mTextRect = new MRect();
    private final boolean needClearText = false;

    public UIRegionCrop(Context context)
    {
        super(context);
        mCropMBitmap = null;
        mForeRect = null;
        mOldCropRect = null;
        mIsCropMBitmapValid = false;
        mScaleGestureDetector = null;
    }

    private void drawInnerLines(Canvas canvas)
    {
        if (mInnerLineList != null)
        {
            int i = mInnerLineList.size();
            Paint paint = new Paint();
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setColor(0xff888888);
            paint.setStrokeWidth(1.0F);
            paint.setShadowLayer(1.0F, 0.0F, 0.0F, 0xff000000);
            for (int j = 0; j < i; j++)
            {
                Line line = (Line)mInnerLineList.get(j);
                if (line != null)
                {
                    canvas.drawLine(line.mStartX, line.mStartY, line.mEndX, line.mEndY, paint);
                }
            }

        }
    }

    private void drawShadow(Canvas canvas)
    {
        canvas.clipRect(mMaxRect);
        canvas.clipRect(mCropRect, android.graphics.Region.Op.DIFFERENCE);
        canvas.drawColor(0x80808080, android.graphics.PorterDuff.Mode.SRC_OVER);
    }

    private void refreshDisplay()
    {
        if (mOldCropRect != null && mCropMBitmap != null && mIsCropMBitmapValid)
        {
            MPoint mpoint = new MPoint();
            mpoint.set(mForeRect.left, mForeRect.top);
            MRect mrect = new MRect();
            MBitmap mbitmap = mWorkshop.getDisplayMBitmap();
            if (mbitmap != null)
            {
                mrect.set(mOldCropRect.left - 8, mOldCropRect.top - 8, 8 + mOldCropRect.left, 8 + mOldCropRect.top);
                mbitmap.fillColor(mWorkshop.getBackgroundColorForCrop(), mrect, null, 100);
                mrect.set(mOldCropRect.right - 8, mOldCropRect.top - 8, 8 + mOldCropRect.right, 8 + mOldCropRect.top);
                mbitmap.fillColor(mWorkshop.getBackgroundColorForCrop(), mrect, null, 100);
                mrect.set(mOldCropRect.left - 8, mOldCropRect.bottom - 8, 8 + mOldCropRect.left, 8 + mOldCropRect.bottom);
                mbitmap.fillColor(mWorkshop.getBackgroundColorForCrop(), mrect, null, 100);
                mrect.set(mOldCropRect.right - 8, mOldCropRect.bottom - 8, 8 + mOldCropRect.right, 8 + mOldCropRect.bottom);
                mbitmap.fillColor(mWorkshop.getBackgroundColorForCrop(), mrect, null, 100);
                WorkShopUtils.copyBitmap(mWorkshop.getDisplayMBitmap(), mCropMBitmap, mpoint);
                mWorkshop.updateView(null);
                return;
            }
        }
    }

    public void doDraw(Bitmap bitmap)
    {
        Canvas canvas = new Canvas(bitmap);
        drawInnerLines(canvas);
        doCropWireframe(canvas);
        drawCorners(canvas, true);
        drawShadow(canvas);
    }

    public void initMultiTouch(MultiTouchDetector.OnMultiTouchListener onmultitouchlistener)
    {
        mScaleGestureDetector = new MultiTouchDetector(getContext(), onmultitouchlistener);
    }

    protected void onDragging(int i, int j)
    {
        if ((i != 0 || j != 0) && mDragType != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mOldCropRect == null)
        {
            mOldCropRect = new MRect();
        }
        mOldCropRect.set(mCropRect.left, mCropRect.top, mCropRect.right, mCropRect.bottom);
        boolean flag;
        if (mWRatio > 0 && mHRatio > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (5 != mDragType)
        {
            j = (i * mHRatio) / mWRatio;
            if (2 == mDragType || 4 == mDragType)
            {
                j = -j;
            }
        }
        if ((j == 0 || i == 0) && 5 != mDragType) goto _L1; else goto _L3
_L3:
        dragRectangle(i, j, flag, true);
        adjustCropRectangle();
        exchangRectangle();
        restrictRectangle2MAX();
        keepRatio(flag);
        setOriginRect();
        updateCornersAndLines();
        refreshDisplay();
        return;
    }

    protected void onDraggingStart()
    {
        if (!mWorkshop.isZoomPaning())
        {
            mWorkshop.stopAysnTask();
            mDragType = getDragType(mCurX, mCurY);
            if (mDragType != 0)
            {
                mIsDragging = true;
                if (mForeRect == null)
                {
                    mForeRect = new MRect();
                }
                mWorkshop.getEditorEngineWrapper().getImgScreenRect(mForeRect);
                if (mCropMBitmap != null && (mCropMBitmap.getWidth() != mForeRect.width() || mCropMBitmap.getHeight() != mForeRect.height() || mCropMBitmap.getColorSpace() != mWorkshop.getColorSpace()))
                {
                    releaseCropMBitmap();
                }
                if (mCropMBitmap == null)
                {
                    mCropMBitmap = MBitmapFactory.createMBitmapBlank(mForeRect.width(), mForeRect.height(), mWorkshop.getColorSpace());
                }
                if (mWorkshop.getEditorEngineWrapper().getDisplayData(mCropMBitmap) == 0)
                {
                    mIsCropMBitmapValid = true;
                    return;
                }
            }
        }
    }

    protected void onDraggingStop()
    {
        if (mDragType != 0)
        {
            if (5 == mDragType)
            {
                refreshDisplay();
            } else
            {
                mWorkshop.getEditorEngineWrapper().refreshDisplay(null, true);
            }
            mDragType = 0;
        }
        mIsCropMBitmapValid = false;
        mIsDragging = false;
    }

    protected void postOntouch(Object obj)
    {
        try
        {
            if (mScaleGestureDetector != null)
            {
                mScaleGestureDetector.onTouchEvent((MotionEvent)obj);
            }
            return;
        }
        catch (Exception exception)
        {
            Log.v("UIRegionCrop", "OnTouch ScaleGestureDetector Exception");
        }
    }

    protected void preOntouch(Object obj)
    {
        if (mScaleGestureDetector != null && ((MotionEvent)obj).getAction() == 0)
        {
            mScaleGestureDetector.resetState();
        }
    }

    public void releaseCropMBitmap()
    {
        if (mCropMBitmap != null)
        {
            mCropMBitmap.recycle();
        }
        mCropMBitmap = null;
        mIsCropMBitmapValid = false;
    }

    public void resetRegion()
    {
        Pair pair = getKeepRatioHW();
        postResetRegion(((Integer)pair.first).intValue(), ((Integer)pair.second).intValue());
        mIsDragged = false;
    }

    public void setOriginRect()
    {
        mIsDragged = true;
        if (mOrignRect != null)
        {
            mOrignRect.left = mCropRect.left;
            mOrignRect.right = mCropRect.right;
            mOrignRect.top = mCropRect.top;
            mOrignRect.bottom = mCropRect.bottom;
        }
    }

    protected void updateCornersAndLines()
    {
        mInnerLineList.clear();
        int i = mCropRect.right - mCropRect.left;
        int j = mCropRect.bottom - mCropRect.top;
        int k = i / 3;
        int l = j / 3;
        Line line = new Line(k + mCropRect.left, 1 + mCropRect.top, k + mCropRect.left, -1 + mCropRect.bottom);
        mInnerLineList.add(line);
        Line line1 = new Line(mCropRect.right - k, 1 + mCropRect.top, mCropRect.right - k, -1 + mCropRect.bottom);
        mInnerLineList.add(line1);
        Line line2 = new Line(1 + mCropRect.left, l + mCropRect.top, -1 + mCropRect.right, l + mCropRect.top);
        mInnerLineList.add(line2);
        Line line3 = new Line(1 + mCropRect.left, mCropRect.bottom - l, -1 + mCropRect.right, mCropRect.bottom - l);
        mInnerLineList.add(line3);
        super.updateCornersAndLines();
    }
}
