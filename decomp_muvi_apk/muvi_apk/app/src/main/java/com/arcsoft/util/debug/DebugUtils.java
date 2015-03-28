// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.debug;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.arcsoft.util.debug:
//            Log

public class DebugUtils
{

    public static final boolean IS_OPEN_ALLOC_DEBUG = true;
    public static final int TRACE_CURRENT = 4;
    private static HashMap mObjectDebug = new HashMap();

    public DebugUtils()
    {
    }

    public static void addObjectAllocDebug(Object obj)
    {
        if (obj != null)
        {
            StackTraceElement stacktraceelement = Thread.currentThread().getStackTrace()[3];
            mObjectDebug.put(obj, (new StringBuilder()).append(stacktraceelement.getClassName()).append(":").append(stacktraceelement.getMethodName()).append(":").append(stacktraceelement.getLineNumber()).toString());
        }
    }

    public static void addObjectAllocDebug(String s, Object obj)
    {
        if (obj != null)
        {
            mObjectDebug.put(obj, s);
        }
    }

    public static String currentTraceInfo()
    {
        return getTraceInfo(4);
    }

    public static void drawSizeAtBitmap(Bitmap bitmap)
    {
        if (bitmap == null)
        {
            return;
        } else
        {
            Paint paint = new Paint();
            paint.setTextSize(20F);
            paint.setColor(0xff0000ff);
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            paint.setShadowLayer(1.0F, 0.0F, 0.0F, -1);
            Canvas canvas = new Canvas(bitmap);
            android.graphics.Paint.FontMetrics fontmetrics = paint.getFontMetrics();
            float f = (1 + bitmap.getWidth()) / 2;
            float f1 = ((float)bitmap.getHeight() - (fontmetrics.descent + fontmetrics.ascent)) / 2.0F;
            canvas.drawText((new StringBuilder()).append(bitmap.getWidth()).append("x").append(bitmap.getHeight()).toString(), f, f1, paint);
            return;
        }
    }

    public static void drawTextAtBitmap(Bitmap bitmap, String s)
    {
        if (bitmap == null)
        {
            return;
        } else
        {
            Paint paint = new Paint();
            paint.setTextSize(20F);
            paint.setColor(0xff0000ff);
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            paint.setShadowLayer(1.0F, 0.0F, 0.0F, -1);
            Canvas canvas = new Canvas(bitmap);
            android.graphics.Paint.FontMetrics fontmetrics = paint.getFontMetrics();
            canvas.drawText(s, (1 + bitmap.getWidth()) / 2, ((float)bitmap.getHeight() - (fontmetrics.descent + fontmetrics.ascent)) / 2.0F, paint);
            return;
        }
    }

    public static int getLineNumber()
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        if (astacktraceelement == null || astacktraceelement.length < 4 || astacktraceelement[1] == null)
        {
            return -1;
        } else
        {
            return astacktraceelement[3].getLineNumber();
        }
    }

    public static String getMethodName()
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        if (astacktraceelement == null || astacktraceelement.length < 4 || astacktraceelement[1] == null)
        {
            return null;
        } else
        {
            return astacktraceelement[3].getMethodName();
        }
    }

    public static String getTraceInfo(int i)
    {
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        if (astacktraceelement == null || astacktraceelement.length < i + 1 || astacktraceelement[i] == null)
        {
            return null;
        } else
        {
            return (new StringBuilder()).append("[Line:").append(astacktraceelement[i].getLineNumber()).append(", Method:").append(astacktraceelement[i].getMethodName()).append("]").toString();
        }
    }

    public static void logObjectDebugInfo()
    {
        Log.e("Debug", "Check Object debug info");
        String s;
        for (Iterator iterator = mObjectDebug.keySet().iterator(); iterator.hasNext(); Log.e("Debug", (new StringBuilder()).append("Object has not recycle : tag = ").append(s).toString()))
        {
            Object obj = iterator.next();
            s = (String)mObjectDebug.get(obj);
        }

        mObjectDebug.clear();
    }

    public static void removeObjectDebug(Object obj)
    {
        if (obj != null)
        {
            mObjectDebug.remove(obj);
        }
    }

}
