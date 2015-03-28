// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.platform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public final class MSurfaceLayerView extends ImageView
{
    private class MSurfaceLayerViewEventHandler extends Handler
    {

        final MSurfaceLayerView this$0;

        public void handleMessage(Message message)
        {
            if (message == null)
            {
                return;
            }
            message.what;
            JVM INSTR tableswitch 8193 8193: default 28
        //                       8193 34;
               goto _L1 _L2
_L1:
            super.handleMessage(message);
            return;
_L2:
            if (message.arg1 == 0)
            {
                setVisibleSync(true);
            } else
            {
                setVisibleSync(false);
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public MSurfaceLayerViewEventHandler(Looper looper)
        {
            this$0 = MSurfaceLayerView.this;
            super(looper);
        }
    }


    private static final int MSG_SURFACELAYERVIEW_BASE = 8192;
    public static final int MSG_SURFACELAYERVIEW_SET_VISIBILITY = 8193;
    private static final int SCALE_RATIO_OF_REGION = 10000;
    protected int mAlpha;
    protected Bitmap mContentBitmap;
    protected Rect mContentRegion;
    private int mFrameRate;
    private boolean mNeedRefresh;
    private Object mObjRefreshDisplaySync;
    private final Object mObjTimerSync;
    private MSurfaceLayerViewEventHandler m_EventHandler;
    private final Object m_ObjSync;
    private Timer m_Timer;
    private TimerTask m_TimerTask;

    public MSurfaceLayerView(Context context)
    {
        super(context);
        mContentBitmap = null;
        mAlpha = 255;
        mFrameRate = 15;
        mContentRegion = new Rect();
        m_ObjSync = new Object();
        mObjRefreshDisplaySync = new Object();
        m_EventHandler = new MSurfaceLayerViewEventHandler(Looper.myLooper());
        mNeedRefresh = false;
        m_Timer = null;
        m_TimerTask = null;
        mObjTimerSync = new Object();
    }

    public MSurfaceLayerView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContentBitmap = null;
        mAlpha = 255;
        mFrameRate = 15;
        mContentRegion = new Rect();
        m_ObjSync = new Object();
        mObjRefreshDisplaySync = new Object();
        m_EventHandler = new MSurfaceLayerViewEventHandler(Looper.myLooper());
        mNeedRefresh = false;
        m_Timer = null;
        m_TimerTask = null;
        mObjTimerSync = new Object();
    }

    public MSurfaceLayerView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContentBitmap = null;
        mAlpha = 255;
        mFrameRate = 15;
        mContentRegion = new Rect();
        m_ObjSync = new Object();
        mObjRefreshDisplaySync = new Object();
        m_EventHandler = new MSurfaceLayerViewEventHandler(Looper.myLooper());
        mNeedRefresh = false;
        m_Timer = null;
        m_TimerTask = null;
        mObjTimerSync = new Object();
    }

    private void StartRefreshDisplayTimer()
    {
        m_Timer = new Timer();
        m_TimerTask = new TimerTask() {

            final MSurfaceLayerView this$0;

            public void run()
            {
                synchronized (mObjTimerSync)
                {
                    if (mNeedRefresh)
                    {
                        postInvalidate();
                        mNeedRefresh = false;
                    }
                }
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                this$0 = MSurfaceLayerView.this;
                super();
            }
        };
        m_Timer.scheduleAtFixedRate(m_TimerTask, 0L, 1000 / mFrameRate);
    }

    private void StopRefreshDisplayTimer()
    {
        synchronized (mObjTimerSync)
        {
            if (m_TimerTask != null)
            {
                m_TimerTask.cancel();
                m_TimerTask = null;
                m_Timer.cancel();
                m_Timer = null;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected boolean isVisible()
    {
        return getVisibility() == 0;
    }

    protected void onDraw(Canvas canvas)
    {
        if (canvas == null)
        {
            return;
        }
        synchronized (m_ObjSync)
        {
            if (mContentBitmap != null && !mContentBitmap.isRecycled() && !mContentRegion.isEmpty())
            {
                break MISSING_BLOCK_LABEL_47;
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Paint paint = new Paint();
        if (mContentRegion.width() != mContentBitmap.getWidth() || mContentRegion.height() != mContentBitmap.getHeight())
        {
            Log.i("PLATFORM_LOG", (new StringBuilder("!!! bitmap resampled when draw ")).append(mContentRegion.width()).append(" ").append(mContentBitmap.getWidth()).append(" ").append(mContentRegion.height()).append(" ").append(mContentBitmap.getHeight()).toString());
        }
        paint.setAlpha(mAlpha);
        canvas.drawBitmap(mContentBitmap, null, mContentRegion, paint);
        obj;
        JVM INSTR monitorexit ;
    }

    protected void prepare()
    {
    }

    protected void refreshDisplay()
    {
        mNeedRefresh = true;
    }

    protected void release()
    {
        synchronized (m_ObjSync)
        {
            setContentBitmap(null);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setContentBitmap(Bitmap bitmap)
    {
        Object obj = m_ObjSync;
        obj;
        JVM INSTR monitorenter ;
        if (mContentBitmap != null && !mContentBitmap.isRecycled())
        {
            mContentBitmap.recycle();
            mContentBitmap = null;
        }
        mContentBitmap = bitmap;
        if (mContentBitmap == null)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        mNeedRefresh = true;
        StartRefreshDisplayTimer();
_L2:
        return;
        mNeedRefresh = false;
        StopRefreshDisplayTimer();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setContentBitmapRegion(int i, int j, int k, int l)
    {
        int i1 = getWidth();
        int j1 = getHeight();
        synchronized (m_ObjSync)
        {
            mContentRegion.left = (i1 * i) / 10000;
            mContentRegion.top = (j1 * j) / 10000;
            mContentRegion.right = (i1 * k) / 10000;
            mContentRegion.bottom = (j1 * l) / 10000;
        }
        Log.i("PLATFORM_LOG", (new StringBuilder("setContentBitmapRegion mContentRegion: ")).append(mContentRegion.left).append(",").append(mContentRegion.top).append(",").append(mContentRegion.right).append(",").append(mContentRegion.bottom).toString());
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setFrameRate(int i)
    {
        if (i <= 0)
        {
            return;
        }
        synchronized (mObjRefreshDisplaySync)
        {
            mFrameRate = i;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setTransparency(int i)
    {
        synchronized (m_ObjSync)
        {
            mAlpha = i;
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setVisible(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 4;
        }
        if (m_EventHandler != null)
        {
            m_EventHandler.removeMessages(8193);
            Message message = m_EventHandler.obtainMessage(8193, i, 0);
            m_EventHandler.sendMessage(message);
        }
    }

    protected void setVisibleSync(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 4;
        }
        synchronized (m_ObjSync)
        {
            setVisibility(i);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }



}
