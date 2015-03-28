// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.opengl;

import android.graphics.Bitmap;
import com.arcsoft.mediaplus.picture.common.Utils;

// Referenced classes of package com.arcsoft.mediaplus.picture.opengl:
//            UploadedTexture

public class BitmapTexture extends UploadedTexture
{

    protected Bitmap mContentBitmap;

    public BitmapTexture(Bitmap bitmap)
    {
        this(bitmap, false);
    }

    public BitmapTexture(Bitmap bitmap, boolean flag)
    {
        super(flag);
        boolean flag1;
        if (bitmap != null && !bitmap.isRecycled())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        Utils.assertTrue(flag1);
        mContentBitmap = bitmap;
    }

    public Bitmap getBitmap()
    {
        return mContentBitmap;
    }

    protected void onFreeBitmap(Bitmap bitmap)
    {
    }

    protected Bitmap onGetBitmap()
    {
        return mContentBitmap;
    }
}
