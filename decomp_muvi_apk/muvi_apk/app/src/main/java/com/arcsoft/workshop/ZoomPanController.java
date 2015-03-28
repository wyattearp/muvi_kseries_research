// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;

import android.content.Context;
import powermobia.utils.MPoint;

// Referenced classes of package com.arcsoft.workshop:
//            WorkShop, EditorEngineWrapper, OnImageEventListener

public final class ZoomPanController
{
    public static interface OnPanGestureListener
    {

        public abstract int OnPan(int i, int j);

        public abstract int OnPanBegin(int i, int j);

        public abstract int OnPanEnd();
    }

    public static interface OnZoomGestureListener
    {

        public abstract int OnZoom(float f);

        public abstract int OnZoomBegin();

        public abstract int OnZoomEnd();
    }

    private class ZoomPanListener
        implements OnZoomGestureListener, OnPanGestureListener
    {

        final ZoomPanController this$0;

        private int doPan(int i, int j)
        {
            powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
            int k = mEditorEngineWrapper.getState(state);
            int l = i - mPtPanPrePos.x;
            int i1 = j - mPtPanPrePos.y;
            if (l != 0 || i1 != 0)
            {
                if (mImageEventListener != null)
                {
                    mImageEventListener.onChange(2, null, null);
                }
                k = mEditorEngineWrapper.pan(l, i1);
                if (mImageEventListener != null)
                {
                    mImageEventListener.onChange(3, null, null);
                }
            }
            mPtPanPrePos.x = i;
            mPtPanPrePos.y = j;
            return k;
        }

        public int OnPan(int i, int j)
        {
            if (mIsPaning)
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
            return 0;
        }

        public int OnPanEnd()
        {
            if (mIsPaning)
            {
                mWorkShop.startAysnTask();
                mIsPaning = false;
            }
            return 0;
        }

        public int OnZoom(float f)
        {
            if (mIsZooming && mEditorEngineWrapper != null)
            {
                powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
                if (mEditorEngineWrapper.getState(state) == 0)
                {
                    int i = (int)(0.5D + (double)(f * (float)state.iZoom));
                    doZoom(null, null, i);
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
                mWorkShop.startAysnTask();
            }
            return 0;
        }

        private ZoomPanListener()
        {
            this$0 = ZoomPanController.this;
            super();
        }

    }


    private static final int mZoomLevelNum = 17;
    private EditorEngineWrapper mEditorEngineWrapper;
    private OnImageEventListener mImageEventListener;
    private boolean mIsPaning;
    private boolean mIsZooming;
    private MPoint mPtPanPrePos;
    private WorkShop mWorkShop;
    private int mZoomLevelArray[];
    private ZoomPanListener mZoomPanListener;

    public ZoomPanController(Context context)
    {
        mPtPanPrePos = new MPoint();
        mIsPaning = false;
        mIsZooming = false;
        mZoomLevelArray = null;
        mEditorEngineWrapper = null;
        mImageEventListener = null;
        mWorkShop = null;
        mWorkShop = (WorkShop)context;
        initZoomLevels();
    }

    private void initZoomLevels()
    {
        mZoomLevelArray = new int[17];
        int i;
        int j;
        i = 0;
        j = 0;
_L2:
        if (j >= 17)
        {
            break; /* Loop/switch isn't completed */
        }
        mZoomLevelArray[j] = i;
        j++;
        i += 125;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        exception.printStackTrace();
_L1:
    }

    public OnPanGestureListener createOnPanGestureListener()
    {
        if (mZoomPanListener == null)
        {
            mZoomPanListener = new ZoomPanListener();
        }
        return mZoomPanListener;
    }

    public OnZoomGestureListener createOnZoomGestureListener()
    {
        if (mZoomPanListener == null)
        {
            mZoomPanListener = new ZoomPanListener();
        }
        return mZoomPanListener;
    }

    public int doZoom(Integer integer, Integer integer1, int i)
    {
        powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
        int j = mEditorEngineWrapper.getState(state);
        if (i != state.iZoom)
        {
            Integer integer2 = new Integer(0);
            int k = Math.min(Math.max(i, ((Integer)mEditorEngineWrapper.getProp(0xa6d007, integer2)).intValue() / 2), mZoomLevelArray[16]);
            if (mImageEventListener != null)
            {
                mImageEventListener.onChange(2, null, null);
            }
            if (k != state.iZoom)
            {
                if (integer != null && integer1 != null)
                {
                    MPoint mpoint = new MPoint(integer.intValue(), integer1.intValue());
                    j = mEditorEngineWrapper.zoom(k, mpoint);
                } else
                {
                    j = mEditorEngineWrapper.zoom(k, null);
                }
                if (mImageEventListener != null)
                {
                    mImageEventListener.onChange(3, null, null);
                }
            }
        }
        return j;
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

    public void setEditorEngineWrapper(EditorEngineWrapper editorenginewrapper)
    {
        mEditorEngineWrapper = editorenginewrapper;
    }

    public void setImageEventListener(OnImageEventListener onimageeventlistener)
    {
        mImageEventListener = onimageeventlistener;
    }




/*
    static boolean access$202(ZoomPanController zoompancontroller, boolean flag)
    {
        zoompancontroller.mIsPaning = flag;
        return flag;
    }

*/






/*
    static boolean access$602(ZoomPanController zoompancontroller, boolean flag)
    {
        zoompancontroller.mIsZooming = flag;
        return flag;
    }

*/
}
