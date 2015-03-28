// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.graphics.Rect;
import com.arcsoft.mediaplus.picture.common.Utils;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            OverScroller

public class LayoutController
{
    public static interface ILayoutControllerReadyListener
    {

        public abstract void ready();
    }


    protected int mContentLength;
    protected int mCount;
    protected int mHeight;
    protected int mItemSpaceX;
    protected int mItemSpaceY;
    ILayoutControllerReadyListener mLayoutControllerReadyListener;
    protected int mOnePageHeight;
    protected int mOverflingDistanceX;
    protected int mOverflingDistanceY;
    protected int mPaddingLeft;
    protected int mPaddingTop;
    protected int mScrollPosition;
    protected OverScroller mScroller;
    protected int mUnitCount;
    protected int mUnitHeight;
    protected int mUnitWidth;
    protected int mVisibleEnd;
    protected int mVisibleStart;
    protected int mWidth;

    public LayoutController(Context context)
    {
        mContentLength = 0;
        mOverflingDistanceX = 0;
        mOverflingDistanceY = 0;
        mPaddingLeft = 0;
        mPaddingTop = 0;
        mItemSpaceX = 0;
        mItemSpaceY = 0;
        mOnePageHeight = 0;
        mLayoutControllerReadyListener = null;
        mScroller = new OverScroller(context);
    }

    private void setVisibleRange(int i, int j)
    {
        if (i == mVisibleStart && j == mVisibleEnd)
        {
            return;
        }
        if (i < j)
        {
            mVisibleStart = i;
            mVisibleEnd = j;
            return;
        } else
        {
            mVisibleEnd = 0;
            mVisibleStart = 0;
            return;
        }
    }

    private void updateVisibleSlotRange()
    {
        int i = mScrollPosition;
        int j = mUnitHeight + mItemSpaceY;
        if (j == 0)
        {
            return;
        } else
        {
            int k = Math.max(0, (((i - mPaddingTop) + mItemSpaceY) / j) * mUnitCount);
            int l = (-1 + (j + (i + mHeight))) / j;
            setVisibleRange(k, Math.min(mCount, l * mUnitCount));
            return;
        }
    }

    public boolean computeScrollOffset()
    {
        return mScroller.computeScrollOffset();
    }

    public void fling(int i, int j, int k)
    {
        int l = getPosition();
        mScroller.fling(0, l, 0, i, 0, 0, j, k, mOverflingDistanceX, mOverflingDistanceY);
    }

    public void forceFinished()
    {
        mScroller.forceFinished(true);
        mScroller.startScroll(mScroller.getCurrX(), mScroller.getCurrY(), 0, 0, 0);
    }

    public int getBottomPosition()
    {
        return mContentLength - mOnePageHeight;
    }

    public int getContentLength()
    {
        return mContentLength;
    }

    public int getCountOnePage()
    {
        int i = mUnitHeight + mItemSpaceY;
        return (mHeight / i) * mUnitCount;
    }

    public int getIndexByPosition(int i, int j)
    {
        int k = (j + mScrollPosition) - mPaddingTop;
        int l = i - mPaddingLeft;
        int i1 = k / (mUnitHeight + mItemSpaceY);
        return l / (mUnitWidth + mItemSpaceX) + i1 * mUnitCount;
    }

    public int getItemCount()
    {
        return mCount;
    }

    public int getItemHeight()
    {
        return mUnitHeight;
    }

    public int getItemWidth()
    {
        return mUnitWidth;
    }

    public int getPosition()
    {
        return mScroller.getCurrY();
    }

    public int getScrollLimit()
    {
        int i = mContentLength - mHeight;
        if (i <= 0)
        {
            i = 0;
        }
        return i;
    }

    public Rect getSlotRect(int i)
    {
        int j = i / mUnitCount;
        int k = i - j * mUnitCount;
        int l = mPaddingLeft + k * (mUnitWidth + mItemSpaceX);
        int i1 = (mPaddingTop + j * (mUnitHeight + mItemSpaceY)) - mScrollPosition;
        return new Rect(l, i1, l + mUnitWidth, i1 + mUnitHeight);
    }

    public int getUnitCount()
    {
        return mUnitCount;
    }

    public int getVisibleEnd()
    {
        return mVisibleEnd;
    }

    public int getVisibleStart()
    {
        return mVisibleStart;
    }

    public boolean isFinished()
    {
        return mScroller.isFinished();
    }

    public boolean isReady()
    {
        return mUnitCount > 0;
    }

    public void setLayoutControllerReady(ILayoutControllerReadyListener ilayoutcontrollerreadylistener)
    {
        mLayoutControllerReadyListener = ilayoutcontrollerreadylistener;
    }

    public void setLayoutSize(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        mWidth = i;
        mHeight = j;
        mCount = i1;
        mUnitWidth = k;
        mUnitHeight = l;
        mUnitCount = j1;
        int l1 = (-1 + (mCount + mUnitCount)) / mUnitCount;
        mContentLength = k1 + (mPaddingTop + l1 * (mUnitHeight + mItemSpaceY));
        updateVisibleSlotRange();
        mLayoutControllerReadyListener.ready();
    }

    public void setOnePageHeight(int i)
    {
        mOnePageHeight = i;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        mPaddingLeft = i;
        mPaddingTop = j;
        mItemSpaceX = k;
        mItemSpaceY = l;
    }

    public int setPosition(int i)
    {
        if (i <= 0)
        {
            i = 0;
        }
        int j = getBottomPosition();
        if (j >= 0 && i > j)
        {
            i = j;
        }
        mScrollPosition = i;
        mScroller.startScroll(mScroller.getCurrX(), i, 0, 0, 0);
        return i;
    }

    public void setScrollPosition(int i, boolean flag)
    {
        if (!flag && mScrollPosition == i)
        {
            return;
        } else
        {
            mScrollPosition = i;
            updateVisibleSlotRange();
            return;
        }
    }

    public int startScroll(int i, int j, int k)
    {
        int l = mScroller.getCurrY();
        int i1 = mScroller.getFinalY();
        int j1 = Utils.clamp(i1 + i, j, k);
        if (j1 != l)
        {
            mScroller.startScroll(0, l, 0, j1 - l, 0);
        }
        return (i1 + i) - j1;
    }
}
