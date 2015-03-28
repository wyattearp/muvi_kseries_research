// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Bitmap;
import android.graphics.Canvas;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            UploadedTexture

abstract class CanvasTexture extends UploadedTexture
{

    protected Canvas mCanvas;
    private final android.graphics.Bitmap.Config mConfig;

    public CanvasTexture(int i, int j)
    {
        mConfig = android.graphics.Bitmap.Config.ARGB_8888;
        setSize(i, j);
        setOpaque(false);
    }

    protected abstract void onDraw(Canvas canvas, Bitmap bitmap);

    protected void onFreeBitmap(Bitmap bitmap)
    {
        if (!inFinalizer())
        {
            bitmap.recycle();
        }
    }

    protected Bitmap onGetBitmap()
    {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);
        mCanvas = new Canvas(bitmap);
        onDraw(mCanvas, bitmap);
        return bitmap;
    }
}
