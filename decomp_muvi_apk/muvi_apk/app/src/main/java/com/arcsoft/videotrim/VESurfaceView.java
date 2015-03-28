// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import powermobia.ve.utils.MRect;

public class VESurfaceView extends SurfaceView
{

    private boolean m_bSkip;
    private Bitmap m_frameBitmap;
    private SurfaceHolder m_surfaceHolder;

    public VESurfaceView(Context context)
    {
        super(context);
        m_surfaceHolder = null;
        m_frameBitmap = null;
        m_bSkip = false;
        init();
    }

    public VESurfaceView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        m_surfaceHolder = null;
        m_frameBitmap = null;
        m_bSkip = false;
        init();
    }

    public VESurfaceView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        m_surfaceHolder = null;
        m_frameBitmap = null;
        m_bSkip = false;
        init();
    }

    private void init()
    {
        m_surfaceHolder = getHolder();
        m_surfaceHolder.setType(0);
        m_surfaceHolder.setFormat(4);
    }

    public Object DisplayInitialize(Object obj, MRect mrect)
    {
        if (m_frameBitmap != null)
        {
            m_frameBitmap.recycle();
            m_frameBitmap = null;
        }
        try
        {
            m_frameBitmap = Bitmap.createBitmap(mrect.right - mrect.left, mrect.bottom - mrect.top, android.graphics.Bitmap.Config.RGB_565);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            outofmemoryerror.printStackTrace();
            return this;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
            return this;
        }
        return this;
    }

    public Bitmap DisplayLock(Object obj)
    {
        return m_frameBitmap;
    }

    public int DisplayUninitialize(Object obj)
    {
        if (m_frameBitmap != null)
        {
            m_frameBitmap.recycle();
            m_frameBitmap = null;
        }
        return 0;
    }

    public int DisplayUnlock(Object obj)
    {
        if (!m_bSkip && m_surfaceHolder != null)
        {
            Canvas canvas = m_surfaceHolder.lockCanvas();
            if (canvas != null)
            {
                canvas.drawBitmap(m_frameBitmap, 0.0F, 0.0F, null);
                m_surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
        return 0;
    }

    public int DisplayUpdate(Object obj, MRect mrect)
    {
        return 0;
    }

    public void SetSkipFlag(boolean flag)
    {
        m_bSkip = flag;
    }

    public void fillBlack()
    {
        if (!m_bSkip && m_surfaceHolder != null)
        {
            Canvas canvas = m_surfaceHolder.lockCanvas();
            if (canvas != null)
            {
                canvas.drawRGB(0, 0, 0);
                m_surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = getMeasuredWidth();
        int l = getMeasuredHeight();
        if (k % 32 > 0 || l % 2 != 0)
        {
            if (k % 32 > 0)
            {
                k = 32 * (k / 32);
            }
            if (l % 2 != 0)
            {
                l--;
            }
            setMeasuredDimension(k, l);
        }
    }
}
