// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import powermobia.utils.MBitmap;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            MultiTouchDetector

public class EditorView extends SurfaceView
    implements android.view.SurfaceHolder.Callback
{
    private class EditorViewDoubleTapListener
        implements android.view.GestureDetector.OnDoubleTapListener
    {

        final EditorView this$0;

        public boolean onDoubleTap(MotionEvent motionevent)
        {
            WorkShopUtils.back2BestFitMode(mWorkShop);
            return false;
        }

        public boolean onDoubleTapEvent(MotionEvent motionevent)
        {
            return false;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            return false;
        }

        private EditorViewDoubleTapListener()
        {
            this$0 = EditorView.this;
            super();
        }

    }

    public static interface ITouchDistribution
    {

        public abstract boolean touchProcess(MotionEvent motionevent);
    }

    private class ZoomGestureListener
        implements MultiTouchDetector.OnMultiTouchListener
    {

        final EditorView this$0;

        public boolean OnMultiTouch(MultiTouchDetector multitouchdetector)
        {
            boolean flag = mIsZoomStarted;
            boolean flag1 = false;
            if (flag)
            {
                com.arcsoft.workshop.ZoomPanController.OnZoomGestureListener onzoomgesturelistener = mOnZoomGestureListener;
                flag1 = false;
                if (onzoomgesturelistener != null)
                {
                    float f = multitouchdetector.getPreviousSpan();
                    float f1 = multitouchdetector.getCurrentSpan();
                    if ((int)f == 0)
                    {
                        f = f1;
                    }
                    int i = mOnZoomGestureListener.OnZoom(f1 / f);
                    flag1 = false;
                    if (i == 0)
                    {
                        flag1 = true;
                    }
                }
            }
            return flag1;
        }

        public boolean OnMultiTouchBegin(MultiTouchDetector multitouchdetector)
        {
            if (mOnZoomGestureListener != null)
            {
                if (mIsPanBegin)
                {
                    mIsPanBegin = false;
                    if (mIsPanStarted)
                    {
                        mOnPanGestureListener.OnPanEnd();
                        mIsPanStarted = false;
                    }
                }
                if (mOnZoomGestureListener.OnZoomBegin() == 0)
                {
                    mIsZoomStarted = true;
                    return true;
                } else
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }

        public void OnMultiTouchEnd(MultiTouchDetector multitouchdetector)
        {
            if (mIsZoomStarted)
            {
                mIsZoomStarted = false;
                if (mOnZoomGestureListener != null)
                {
                    mOnZoomGestureListener.OnZoomEnd();
                }
            }
        }

        private ZoomGestureListener()
        {
            this$0 = EditorView.this;
            super();
        }

    }


    private static final String mTag = "PhotoFix";
    private OnCommandListener mCommandListener;
    private int mDownX;
    private int mDownY;
    private boolean mEnablePanZoom;
    private GestureDetector mGestureDetector;
    private SurfaceHolder mHolder;
    private boolean mIsPanBegin;
    private boolean mIsPanStarted;
    private boolean mIsZoomStarted;
    private com.arcsoft.workshop.ZoomPanController.OnPanGestureListener mOnPanGestureListener;
    private com.arcsoft.workshop.ZoomPanController.OnZoomGestureListener mOnZoomGestureListener;
    private MultiTouchDetector mScaleGestureDetector;
    private Surface mSurface;
    private boolean mSurfaceDestoryed;
    private ITouchDistribution mTouchDistribution;
    private WorkShop mWorkShop;
    private ZoomGestureListener mZoomGestureListener;

    public EditorView(Context context)
    {
        super(context);
        mTouchDistribution = null;
        mEnablePanZoom = true;
        mWorkShop = null;
        mHolder = null;
        mSurface = null;
        mSurfaceDestoryed = false;
        mDownX = 0;
        mDownY = 0;
        mIsZoomStarted = false;
        mIsPanBegin = false;
        mIsPanStarted = false;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mScaleGestureDetector = null;
        mGestureDetector = null;
        mCommandListener = null;
        mZoomGestureListener = null;
        init(context);
    }

    public EditorView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mTouchDistribution = null;
        mEnablePanZoom = true;
        mWorkShop = null;
        mHolder = null;
        mSurface = null;
        mSurfaceDestoryed = false;
        mDownX = 0;
        mDownY = 0;
        mIsZoomStarted = false;
        mIsPanBegin = false;
        mIsPanStarted = false;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mScaleGestureDetector = null;
        mGestureDetector = null;
        mCommandListener = null;
        mZoomGestureListener = null;
        init(context);
    }

    public EditorView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mTouchDistribution = null;
        mEnablePanZoom = true;
        mWorkShop = null;
        mHolder = null;
        mSurface = null;
        mSurfaceDestoryed = false;
        mDownX = 0;
        mDownY = 0;
        mIsZoomStarted = false;
        mIsPanBegin = false;
        mIsPanStarted = false;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mScaleGestureDetector = null;
        mGestureDetector = null;
        mCommandListener = null;
        mZoomGestureListener = null;
        init(context);
    }

    private void init(Context context)
    {
        mWorkShop = (WorkShop)context;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(0);
        mHolder.setFormat(1);
        mSurface = mHolder.getSurface();
        mZoomGestureListener = new ZoomGestureListener();
        mScaleGestureDetector = new MultiTouchDetector(context, mZoomGestureListener);
        mGestureDetector = new GestureDetector(context, new android.view.GestureDetector.OnGestureListener() {

            final EditorView this$0;

            public boolean onDown(MotionEvent motionevent)
            {
                return false;
            }

            public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
            {
                return false;
            }

            public void onLongPress(MotionEvent motionevent)
            {
            }

            public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
            {
                return false;
            }

            public void onShowPress(MotionEvent motionevent)
            {
            }

            public boolean onSingleTapUp(MotionEvent motionevent)
            {
                return false;
            }

            
            {
                this$0 = EditorView.this;
                super();
            }
        });
        mGestureDetector.setOnDoubleTapListener(new EditorViewDoubleTapListener());
    }

    public void doDraw(Bitmap bitmap, Rect rect)
    {
        if (bitmap != null && mHolder != null)
        {
            Canvas canvas = mHolder.lockCanvas(rect);
            if (canvas != null)
            {
                canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
                if (rect == null)
                {
                    canvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
                } else
                {
                    canvas.drawBitmap(bitmap, rect, rect, null);
                }
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void doDraw(MBitmap mbitmap, MRect mrect)
    {
        if (mbitmap != null)
        {
            if (mWorkShop.isFinishing())
            {
                Log.v("WorkShop", "doDraw when isFinishing");
                return;
            }
            if ((mrect != null || mbitmap.getWidth() == mWorkShop.getDisplayW() && mbitmap.getHeight() == mWorkShop.getDisplayH()) && mSurface != null && mSurface.isValid() && !mSurfaceDestoryed && WorkShopUtils.doDraw(mSurface, mbitmap, mrect) != 0)
            {
                if (android.os.Build.VERSION.SDK_INT > 8)
                {
                    WorkShopUtils.releaseANativeWindow();
                }
                if (WorkShopUtils.doDraw(mSurface, mbitmap, mrect) != 0 && android.os.Build.VERSION.SDK_INT > 8)
                {
                    WorkShopUtils.releaseANativeWindow();
                    return;
                }
            }
        }
    }

    public MultiTouchDetector.OnMultiTouchListener getEditorViewMultiTouchListener()
    {
        return mZoomGestureListener;
    }

    public boolean isSurfaceDestory()
    {
        return mSurfaceDestoryed;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (mEnablePanZoom) goto _L2; else goto _L1
_L1:
        boolean flag;
        ITouchDistribution itouchdistribution = mTouchDistribution;
        flag = false;
        if (itouchdistribution != null)
        {
            flag = mTouchDistribution.touchProcess(motionevent);
        }
_L4:
        return flag;
_L2:
        String s;
        s = mWorkShop.getCurrentFilePath();
        flag = false;
        if (s == null) goto _L4; else goto _L3
_L3:
        boolean flag1;
        flag1 = mWorkShop.getSaveStart();
        flag = false;
        if (flag1) goto _L4; else goto _L5
_L5:
        if (motionevent.getAction() == 0)
        {
            resetState();
        }
        if (mIsZoomStarted && motionevent.getPointerCount() < 2)
        {
            mIsZoomStarted = false;
            mScaleGestureDetector.resetState();
        }
        if (mIsZoomStarted || mOnPanGestureListener == null) goto _L7; else goto _L6
_L6:
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 3: default 152
    //                   0 186
    //                   1 341
    //                   2 212
    //                   3 341;
           goto _L8 _L9 _L10 _L11 _L10
_L8:
        break; /* Loop/switch isn't completed */
_L10:
        break MISSING_BLOCK_LABEL_341;
_L7:
        try
        {
            if (mScaleGestureDetector != null)
            {
                mScaleGestureDetector.onTouchEvent(motionevent);
            }
        }
        catch (Exception exception)
        {
            Log.v("PhotoFix", "OnTouch ScaleGestureDetector Exception");
        }
        try
        {
            if (mGestureDetector != null)
            {
                mGestureDetector.onTouchEvent(motionevent);
            }
        }
        catch (Exception exception1)
        {
            Log.v("PhotoFix", "OnTouch GestureDetector Exception");
        }
        return true;
_L9:
        mIsPanBegin = true;
        mDownX = (int)motionevent.getX();
        mDownY = (int)motionevent.getY();
          goto _L7
_L11:
        if (!mIsPanBegin && motionevent.getPointerCount() < 2)
        {
            mIsPanBegin = true;
            mDownX = (int)motionevent.getX();
            mDownY = (int)motionevent.getY();
        } else
        if (mIsPanBegin && motionevent.getPointerCount() < 2)
        {
label0:
            {
                if (!mIsPanStarted)
                {
                    if (mOnPanGestureListener.OnPanBegin(mDownX, mDownY) != 0)
                    {
                        break label0;
                    }
                    mIsPanStarted = true;
                }
                mOnPanGestureListener.OnPan((int)motionevent.getX(), (int)motionevent.getY());
            }
        }
          goto _L7
        mIsPanBegin = false;
        mOnPanGestureListener.OnPanEnd();
          goto _L7
        if (mIsPanBegin)
        {
            mIsPanBegin = false;
            if (mIsPanStarted)
            {
                mOnPanGestureListener.OnPanEnd();
                mIsPanStarted = false;
            }
        }
          goto _L7
    }

    public void registerITouchDistribution(ITouchDistribution itouchdistribution)
    {
        mTouchDistribution = itouchdistribution;
    }

    public void resetState()
    {
        mIsZoomStarted = false;
        mIsPanBegin = false;
        mIsPanStarted = false;
        mScaleGestureDetector.resetState();
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }

    public void setOnPanGestureListener(com.arcsoft.workshop.ZoomPanController.OnPanGestureListener onpangesturelistener)
    {
        mOnPanGestureListener = onpangesturelistener;
    }

    public void setOnZoomGestureListener(com.arcsoft.workshop.ZoomPanController.OnZoomGestureListener onzoomgesturelistener)
    {
        mOnZoomGestureListener = onzoomgesturelistener;
    }

    public void setPanZoomEnabeState(boolean flag)
    {
        mEnablePanZoom = flag;
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        if (j == mWorkShop.getDisplayW() && k == mWorkShop.getDisplayH()) goto _L2; else goto _L1
_L1:
        mWorkShop.reinitEditorEngineWrapper(j, k);
_L4:
        if (mWorkShop.getCurrentFilePath() == null)
        {
            mWorkShop.screenBufferNullProcess();
            mWorkShop.resetBkgBitmap();
            mWorkShop.updateView(null);
        }
        return;
_L2:
        if (mWorkShop.getCurrentFilePath() != null && !mWorkShop.reDraw4Resume())
        {
            mCommandListener.onCommand(5, null, null);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        mSurfaceDestoryed = false;
        mSurface = surfaceholder.getSurface();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        mSurfaceDestoryed = true;
    }




/*
    static boolean access$302(EditorView editorview, boolean flag)
    {
        editorview.mIsPanBegin = flag;
        return flag;
    }

*/



/*
    static boolean access$402(EditorView editorview, boolean flag)
    {
        editorview.mIsPanStarted = flag;
        return flag;
    }

*/




/*
    static boolean access$602(EditorView editorview, boolean flag)
    {
        editorview.mIsZoomStarted = flag;
        return flag;
    }

*/

}
