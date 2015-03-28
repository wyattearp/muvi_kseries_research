// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.util.ArrayList;
import powermobia.utils.MRect;

public class UIRegionBase extends View
    implements android.view.View.OnTouchListener
{

    protected static final float CORNER_FOCUS_INFALTESIZE = 2.5F;
    protected static final int CORNER_RECT_HALFSIZE = 5;
    protected static final int DEFAULT_PRECISION = 20;
    protected static final int DRAG_MOVE = 5;
    protected static final int DRAG_RESIZE_LB = 4;
    protected static final int DRAG_RESIZE_RB = 3;
    protected static final int DRAG_RESIZE_TL = 1;
    protected static final int DRAG_RESIZE_TR = 2;
    protected static final int DRAG_TYPE_NONE;
    protected boolean isDrawPrompt;
    protected OnCommandListener mCommandListener;
    protected ArrayList mCornerRectList;
    protected Rect mCropRect;
    protected int mCurX;
    protected int mCurY;
    protected int mDragType;
    protected int mHRatio;
    protected boolean mIsDragged;
    protected boolean mIsDragging;
    protected Rect mMaxRect;
    protected int mMinSize;
    protected Rect mOrignRect;
    protected int mWRatio;
    protected WorkShop mWorkshop;

    public UIRegionBase(Context context)
    {
        super(context);
        mMaxRect = new Rect();
        mCropRect = new Rect();
        mCommandListener = null;
        isDrawPrompt = true;
        mCornerRectList = new ArrayList();
        mWRatio = 0;
        mHRatio = 0;
        mIsDragging = false;
        mIsDragged = false;
        mDragType = 0;
        mCurX = 0;
        mCurY = 0;
        mMinSize = 50;
        mOrignRect = new Rect();
        mWorkshop = (WorkShop)context;
        setOnTouchListener(this);
    }

    protected void adjustCropRectangle()
    {
        if (!WorkShopUtils.isRectInsideRect(mCropRect, mMaxRect))
        {
            if (mCropRect.left < mMaxRect.left)
            {
                mCropRect.left = mMaxRect.left;
            }
            if (mCropRect.right > mMaxRect.right)
            {
                mCropRect.right = mMaxRect.right;
            }
            if (mCropRect.top < mMaxRect.top)
            {
                mCropRect.top = mMaxRect.top;
            }
            if (mCropRect.bottom > mMaxRect.bottom)
            {
                mCropRect.bottom = mMaxRect.bottom;
            }
        }
    }

    protected void calcRect(int i, int j, Rect rect)
    {
        if (rect != null)
        {
            rect.left = i - 5;
            rect.top = j - 5;
            rect.right = i + 5;
            rect.bottom = j + 5;
        }
    }

    protected void doCropWireframe(Canvas canvas)
    {
        if (mCropRect != null)
        {
            Paint paint = new Paint();
            paint.setColor(0xff000000);
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setStrokeWidth(2.0F);
            canvas.drawRect(new Rect(1 + mCropRect.left, 1 + mCropRect.top, -1 + mCropRect.right, -1 + mCropRect.bottom), paint);
        }
    }

    public void doDraw(Bitmap bitmap)
    {
    }

    protected void dragRectangle(int i, int j, boolean flag, boolean flag1)
    {
        mDragType;
        JVM INSTR tableswitch 1 5: default 40
    //                   1 41
    //                   2 214
    //                   3 560
    //                   4 387
    //                   5 733;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        Rect rect16 = mCropRect;
        rect16.left = i + rect16.left;
        Rect rect17 = mCropRect;
        rect17.top = j + rect17.top;
        if (flag1 && (mCropRect.width() < 50 || mCropRect.height() < 50))
        {
            Rect rect18 = mCropRect;
            rect18.left = rect18.left - i;
            Rect rect19 = mCropRect;
            rect19.top = rect19.top - j;
            return;
        }
        if (!flag)
        {
            if (mCropRect.width() < mMinSize)
            {
                mCropRect.left = mCropRect.right - mMinSize;
            }
            if (mCropRect.height() < mMinSize)
            {
                mCropRect.top = mCropRect.bottom - mMinSize;
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        Rect rect12 = mCropRect;
        rect12.right = i + rect12.right;
        Rect rect13 = mCropRect;
        rect13.top = j + rect13.top;
        if (flag1 && (mCropRect.width() < 50 || mCropRect.height() < 50))
        {
            Rect rect14 = mCropRect;
            rect14.right = rect14.right - i;
            Rect rect15 = mCropRect;
            rect15.top = rect15.top - j;
            return;
        }
        if (!flag)
        {
            if (mCropRect.width() < mMinSize)
            {
                mCropRect.right = mCropRect.left + mMinSize;
            }
            if (mCropRect.height() < mMinSize)
            {
                mCropRect.top = mCropRect.bottom - mMinSize;
                return;
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
        Rect rect8 = mCropRect;
        rect8.left = i + rect8.left;
        Rect rect9 = mCropRect;
        rect9.bottom = j + rect9.bottom;
        if (flag1 && (mCropRect.width() < 50 || mCropRect.height() < 50))
        {
            Rect rect10 = mCropRect;
            rect10.left = rect10.left - i;
            Rect rect11 = mCropRect;
            rect11.bottom = rect11.bottom - j;
            return;
        }
        if (!flag)
        {
            if (mCropRect.width() < mMinSize)
            {
                mCropRect.left = mCropRect.right - mMinSize;
            }
            if (mCropRect.height() < mMinSize)
            {
                mCropRect.bottom = mCropRect.top + mMinSize;
                return;
            }
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        Rect rect4 = mCropRect;
        rect4.right = i + rect4.right;
        Rect rect5 = mCropRect;
        rect5.bottom = j + rect5.bottom;
        if (flag1 && (mCropRect.width() < 50 || mCropRect.height() < 50))
        {
            Rect rect6 = mCropRect;
            rect6.right = rect6.right - i;
            Rect rect7 = mCropRect;
            rect7.bottom = rect7.bottom - j;
            return;
        }
        if (!flag)
        {
            if (mCropRect.width() < mMinSize)
            {
                mCropRect.right = mCropRect.left + mMinSize;
            }
            if (mCropRect.height() < mMinSize)
            {
                mCropRect.bottom = mCropRect.top + mMinSize;
                return;
            }
        }
        if (true) goto _L1; else goto _L6
_L6:
        if (mMaxRect != null)
        {
            if (i + mCropRect.left < mMaxRect.left)
            {
                i = mMaxRect.left - mCropRect.left;
            }
            if (i + mCropRect.right > mMaxRect.right)
            {
                i = mMaxRect.right - mCropRect.right;
            }
            if (j + mCropRect.top < mMaxRect.top)
            {
                j = mMaxRect.top - mCropRect.top;
            }
            if (j + mCropRect.bottom > mMaxRect.bottom)
            {
                j = mMaxRect.bottom - mCropRect.bottom;
            }
        }
        Rect rect = mCropRect;
        rect.left = i + rect.left;
        Rect rect1 = mCropRect;
        rect1.top = j + rect1.top;
        Rect rect2 = mCropRect;
        rect2.right = i + rect2.right;
        Rect rect3 = mCropRect;
        rect3.bottom = j + rect3.bottom;
        return;
    }

    protected void drawACorner(int i, Paint paint, Canvas canvas, Rect rect, RectF rectf, boolean flag, boolean flag1)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        RectF rectf2;
        paint.setColor(0xff0000ff);
        rectf2 = new RectF((float)rect.left - 2.5F, (float)rect.top - 2.5F, 2.5F + (float)rect.right, 2.5F + (float)rect.bottom);
        if (!flag1) goto _L4; else goto _L3
_L3:
        canvas.drawRect(rectf2, paint);
_L6:
        return;
_L4:
        RectF rectf3 = new RectF(rectf2);
        if (willDrawCorner(rectf3, i))
        {
            canvas.drawRect(rectf3, paint);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        paint.setColor(-1);
        if (flag1)
        {
            canvas.drawRect(rect, paint);
            return;
        }
        RectF rectf1 = new RectF(rect.left, rect.top, rect.right, rect.bottom);
        if (willDrawCorner(rectf1, i))
        {
            canvas.drawRect(rectf1, paint);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void drawACorner(int i, Paint paint, RectF rectf, Canvas canvas, int j)
    {
        Rect rect = (Rect)mCornerRectList.get(j);
        boolean flag;
        if (i == mDragType)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        drawACorner(i, paint, canvas, rect, rectf, flag, true);
    }

    protected void drawCorners(Canvas canvas, boolean flag)
    {
        RectF arectf[] = new RectF[4];
        for (int i = -1 + arectf.length; i >= 0; i--)
        {
            arectf[i] = new RectF();
        }

        if (mCornerRectList != null && mCornerRectList.size() > 0)
        {
            Paint paint = new Paint();
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setAlpha(128);
            drawACorner(1, paint, arectf[0], canvas, 0);
            drawACorner(2, paint, arectf[1], canvas, 1);
            drawACorner(3, paint, arectf[2], canvas, 2);
            drawACorner(4, paint, arectf[3], canvas, 3);
            paint.setColor(0xff000000);
            paint.setStyle(android.graphics.Paint.Style.STROKE);
            paint.setStrokeWidth(1.0F);
            drawCornersWireframe(canvas, paint, mDragType, arectf, flag);
        }
    }

    protected void drawCornersWireframe(Canvas canvas, Paint paint, int i, RectF arectf[], boolean flag)
    {
        int j = mCornerRectList.size();
        while (j > 0) 
        {
            Rect rect = (Rect)mCornerRectList.get(j - 1);
            if (i == j)
            {
                if (flag)
                {
                    canvas.drawRect((float)rect.left - 2.5F, (float)rect.top - 2.5F, 2.5F + (float)rect.right, 2.5F + (float)rect.bottom, paint);
                } else
                {
                    RectF rectf = arectf[j - 1];
                    if (rectf.right >= (float)mMaxRect.left && rectf.bottom >= (float)mMaxRect.top && rectf.left <= (float)mMaxRect.right && rectf.top <= (float)mMaxRect.bottom)
                    {
                        canvas.drawRect((float)rect.left - 2.5F, (float)rect.top - 2.5F, 2.5F + (float)rect.right, 2.5F + (float)rect.bottom, paint);
                    }
                }
            } else
            if (flag)
            {
                canvas.drawRect(rect, paint);
            } else
            if (rect.right >= mMaxRect.left && rect.bottom >= mMaxRect.top && rect.left <= mMaxRect.right && rect.top <= mMaxRect.bottom)
            {
                canvas.drawRect(rect, paint);
            }
            j--;
        }
    }

    protected void exchangRectangle()
    {
        if (mCropRect.left > mCropRect.right)
        {
            int j = mCropRect.right;
            mCropRect.right = mCropRect.left;
            mCropRect.left = j;
        }
        if (mCropRect.top > mCropRect.bottom)
        {
            int i = mCropRect.bottom;
            mCropRect.bottom = mCropRect.top;
            mCropRect.top = i;
        }
    }

    protected int getDragType(int i, int j)
    {
        int k = 0;
        Rect rect = new Rect(mCropRect);
        Point apoint[] = new Point[5];
        apoint[0] = new Point(rect.left, rect.top);
        apoint[1] = new Point(rect.right, rect.top);
        apoint[3] = new Point(rect.left, rect.bottom);
        apoint[2] = new Point(rect.right, rect.bottom);
        apoint[4] = new Point((rect.right + rect.left) / 2, (rect.bottom + rect.top) / 2);
        int ai[] = new int[5];
        int l = (int)Math.sqrt(1250);
        for (int i1 = 0; i1 < 5; i1++)
        {
            int k1 = i - apoint[i1].x;
            int l1 = j - apoint[i1].y;
            ai[i1] = (int)Math.sqrt(k1 * k1 + l1 * l1);
            if (ai[i1] < l)
            {
                l = ai[i1];
                k = i1 + 1;
            }
        }

        if (k != 0 && Math.abs(l - ai[4]) < 5)
        {
            k = 5;
        }
        if (k == 0)
        {
            if (WorkShopUtils.isPointInRect(rect, i, j))
            {
                k = 5;
            } else
            {
                Rect arect[] = new Rect[4];
                arect[0] = new Rect(rect.left, -40 + rect.top, rect.right, rect.bottom);
                arect[1] = new Rect(-40 + rect.left, rect.top, rect.right, rect.bottom);
                arect[2] = new Rect(rect.left, rect.top, 40 + rect.right, rect.bottom);
                arect[3] = new Rect(rect.left, rect.top, rect.right, 40 + rect.bottom);
                int j1 = 0;
                while (j1 < 4) 
                {
                    if (WorkShopUtils.isPointInRect(arect[j1], i, j))
                    {
                        return 5;
                    }
                    j1++;
                }
            }
        }
        return k;
    }

    protected Pair getKeepRatioHW()
    {
        if (mWRatio != 0 && mHRatio != 0) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = mMaxRect.width();
        j = mMaxRect.height();
_L4:
        return new Pair(Integer.valueOf(i), Integer.valueOf(j));
_L2:
        if (mWRatio > mHRatio)
        {
            i = (mMaxRect.right - mMaxRect.left) / 2;
            j = (i * mHRatio) / mWRatio;
            if (j > mMaxRect.height())
            {
                j = (mMaxRect.bottom - mMaxRect.top) / 2;
                i = (j * mWRatio) / mHRatio;
            }
        } else
        {
            j = (mMaxRect.bottom - mMaxRect.top) / 2;
            i = (j * mWRatio) / mHRatio;
            if (i > mMaxRect.width())
            {
                i = (mMaxRect.right - mMaxRect.left) / 2;
                j = (i * mHRatio) / mWRatio;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Rect getOriginRect()
    {
        return mOrignRect;
    }

    public void getRegion(MRect mrect)
    {
        if (mrect != null)
        {
            mrect.set(mCropRect.left, mCropRect.top, mCropRect.right, mCropRect.bottom);
        }
    }

    public boolean isEverDragged()
    {
        return mIsDragged;
    }

    protected void keepRatio(boolean flag)
    {
        if (!flag) goto _L2; else goto _L1
_L1:
        int j;
        int i = mCropRect.width();
        mCropRect.height();
        j = (i * mHRatio) / mWRatio;
        mDragType;
        JVM INSTR tableswitch 1 4: default 68
    //                   1 69
    //                   2 69
    //                   3 188
    //                   4 188;
           goto _L2 _L3 _L3 _L4 _L4
_L2:
        return;
_L3:
        mCropRect.top = mCropRect.bottom - j;
        if (mCropRect.top < mMaxRect.top)
        {
            mCropRect.top = mMaxRect.top;
            int l = ((mCropRect.bottom - mCropRect.top) * mWRatio) / mHRatio;
            if (1 == mDragType)
            {
                mCropRect.left = mCropRect.right - l;
                return;
            } else
            {
                mCropRect.right = l + mCropRect.left;
                return;
            }
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        mCropRect.bottom = j + mCropRect.top;
        if (mCropRect.bottom > mMaxRect.bottom)
        {
            mCropRect.bottom = mMaxRect.bottom;
            int k = ((mCropRect.bottom - mCropRect.top) * mWRatio) / mHRatio;
            if (4 == mDragType)
            {
                mCropRect.left = mCropRect.right - k;
                return;
            } else
            {
                mCropRect.right = k + mCropRect.left;
                return;
            }
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected void onDragging(int i, int j)
    {
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
                return;
            }
        }
    }

    protected void onDraggingStop()
    {
        mDragType = 0;
        mWorkshop.getEditorEngineWrapper().refreshDisplay(null, true);
        mIsDragging = false;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        Log.e("UiRegion", (new StringBuilder()).append("UiRegion event1 = ").append(motionevent.getAction()).toString());
        Log.e("UiRegion", (new StringBuilder()).append("UiRegion event2 = ").append(0xff & motionevent.getAction()).toString());
        preOntouch(motionevent);
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 108
    //                   0 123
    //                   1 252
    //                   2 165
    //                   3 252;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        boolean flag;
        onDraggingStop();
        flag = false;
_L6:
        postOntouch(motionevent);
        return flag;
_L2:
        mCurX = (int)motionevent.getX();
        mCurY = (int)motionevent.getY();
        onDraggingStart();
        int j1 = mDragType;
        flag = false;
        if (j1 != 0)
        {
            flag = true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int i = motionevent.getPointerCount();
        flag = false;
        if (i <= 1)
        {
            boolean flag1 = mIsDragging;
            flag = false;
            if (flag1)
            {
                int j = (int)motionevent.getX();
                int k = (int)motionevent.getY();
                int l = j - mCurX;
                int i1 = k - mCurY;
                mCurX = j;
                mCurY = k;
                onDragging(l, i1);
                flag = true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        onDraggingStop();
        flag = true;
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void postOntouch(Object obj)
    {
    }

    protected void postResetRegion(int i, int j)
    {
        mCropRect.left = (mMaxRect.right + mMaxRect.left) / 2 - i / 2;
        mCropRect.top = (mMaxRect.bottom + mMaxRect.top) / 2 - j / 2;
        mCropRect.right = i + mCropRect.left;
        mCropRect.bottom = j + mCropRect.top;
        updateCornersAndLines();
    }

    protected void preOntouch(Object obj)
    {
    }

    public void resetRegion()
    {
    }

    protected void restrictRectangle2MAX()
    {
        if (!WorkShopUtils.isRectInsideRect(mCropRect, mMaxRect))
        {
            int i = mCropRect.width();
            int j = mCropRect.height();
            if (mCropRect.left > mMaxRect.right)
            {
                mCropRect.left = mMaxRect.right;
                mCropRect.right = i + mCropRect.left;
            }
            if (mCropRect.right < mMaxRect.left)
            {
                mCropRect.right = mMaxRect.left;
                mCropRect.left = mCropRect.right - i;
            }
            if (mCropRect.top > mMaxRect.bottom)
            {
                mCropRect.top = mMaxRect.bottom;
                mCropRect.bottom = j + mCropRect.top;
            }
            if (mCropRect.bottom < mMaxRect.top)
            {
                mCropRect.bottom = mMaxRect.top;
                mCropRect.top = mCropRect.bottom - j;
            }
        }
    }

    public void setAspect(int i, int j)
    {
        if (i < 0 || j < 0)
        {
            i = 0;
            j = 0;
        }
        mWRatio = i;
        mHRatio = j;
    }

    public void setDrawPrompt(boolean flag)
    {
        isDrawPrompt = flag;
    }

    public void setMaxRegion(MRect mrect)
    {
        if (mrect != null)
        {
            mMaxRect.set(mrect.left, mrect.top, mrect.right, mrect.bottom);
        }
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }

    public void setRegion(MRect mrect)
    {
        if (mrect != null)
        {
            mCropRect.set(mrect.left, mrect.top, mrect.right, mrect.bottom);
            updateCornersAndLines();
        }
    }

    public void setVisibility(int i)
    {
        setVisibility(i, true);
    }

    public void setVisibility(int i, boolean flag)
    {
        if (getVisibility() != i)
        {
            super.setVisibility(i);
            if (flag)
            {
                mWorkshop.getEditorEngineWrapper().refreshDisplay(null, true);
                return;
            }
        }
    }

    protected void updateCornersAndLines()
    {
        mCornerRectList.clear();
        Rect rect = new Rect();
        mCornerRectList.add(rect);
        Rect rect1 = new Rect();
        mCornerRectList.add(rect1);
        Rect rect2 = new Rect();
        mCornerRectList.add(rect2);
        Rect rect3 = new Rect();
        mCornerRectList.add(rect3);
        calcRect(mCropRect.left, mCropRect.top, (Rect)mCornerRectList.get(0));
        calcRect(mCropRect.right, mCropRect.top, (Rect)mCornerRectList.get(1));
        calcRect(mCropRect.right, mCropRect.bottom, (Rect)mCornerRectList.get(2));
        calcRect(mCropRect.left, mCropRect.bottom, (Rect)mCornerRectList.get(3));
    }

    protected boolean willDrawCorner(RectF rectf, int i)
    {
        boolean flag = true;
        i;
        JVM INSTR tableswitch 1 4: default 32
    //                   1 36
    //                   2 70
    //                   3 138
    //                   4 104;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        flag = false;
_L7:
        return flag;
_L2:
        if (rectf.bottom <= (float)mMaxRect.top || rectf.right <= (float)mMaxRect.left)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (rectf.bottom <= (float)mMaxRect.top || rectf.left >= (float)mMaxRect.right)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (rectf.top >= (float)mMaxRect.bottom || rectf.right <= (float)mMaxRect.left)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (rectf.top >= (float)mMaxRect.bottom || rectf.left >= (float)mMaxRect.right)
        {
            return false;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }
}
