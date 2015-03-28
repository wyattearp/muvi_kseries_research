// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            CanvasTexture

public class StringTexture extends CanvasTexture
{

    private final android.graphics.Paint.FontMetricsInt mMetrics;
    private final TextPaint mPaint;
    private final String mText;

    private StringTexture(String s, TextPaint textpaint, android.graphics.Paint.FontMetricsInt fontmetricsint, int i, int j)
    {
        super(i, j);
        mText = s;
        mPaint = textpaint;
        mMetrics = fontmetricsint;
    }

    public static TextPaint getDefaultPaint(float f, int i, android.graphics.Paint.Align align, boolean flag)
    {
        TextPaint textpaint = new TextPaint();
        textpaint.setTextSize(f);
        textpaint.setAntiAlias(true);
        textpaint.setColor(i);
        textpaint.setTextAlign(align);
        if (flag)
        {
            textpaint.setShadowLayer(2.0F, 0.0F, 0.0F, 0xff000000);
        }
        return textpaint;
    }

    public static StringTexture newInstance(String s, float f, int i, float f1, boolean flag, android.graphics.Paint.Align align, boolean flag1)
    {
        TextPaint textpaint = getDefaultPaint(f, i, align, flag1);
        if (flag)
        {
            textpaint.setTypeface(Typeface.defaultFromStyle(1));
        }
        return newInstance(TextUtils.ellipsize(s, textpaint, f1, android.text.TextUtils.TruncateAt.END).toString(), textpaint);
    }

    public static StringTexture newInstance(String s, float f, int i, android.graphics.Paint.Align align, boolean flag)
    {
        return newInstance(s, getDefaultPaint(f, i, align, flag));
    }

    private static StringTexture newInstance(String s, TextPaint textpaint)
    {
        android.graphics.Paint.FontMetricsInt fontmetricsint = textpaint.getFontMetricsInt();
        int i = (int)Math.ceil(textpaint.measureText(s));
        int j = fontmetricsint.bottom - fontmetricsint.top;
        if (i <= 0)
        {
            i = 1;
        }
        if (j <= 0)
        {
            j = 1;
        }
        return new StringTexture(s, textpaint, fontmetricsint, i, j);
    }

    protected void onDraw(Canvas canvas, Bitmap bitmap)
    {
        canvas.translate(0.0F, -mMetrics.ascent);
        canvas.drawText(mText, 0.0F, 0.0F, mPaint);
    }
}
