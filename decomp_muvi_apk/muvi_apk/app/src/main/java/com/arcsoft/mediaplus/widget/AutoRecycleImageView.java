// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AutoRecycleImageView extends ImageView
{

    private Bitmap mBitmap;

    public AutoRecycleImageView(Context context)
    {
        super(context);
    }

    public AutoRecycleImageView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public AutoRecycleImageView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    public void destroyDrawingCache()
    {
        super.destroyDrawingCache();
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public boolean hasSetImageBitmap()
    {
        return mBitmap != null;
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        setImageBitmap(bitmap, true);
    }

    public void setImageBitmap(Bitmap bitmap, boolean flag)
    {
        if (mBitmap == null || bitmap != mBitmap)
        {
            super.setImageBitmap(bitmap);
            if (mBitmap != null)
            {
                mBitmap.recycle();
                mBitmap = null;
            }
            if (flag)
            {
                mBitmap = bitmap;
                return;
            }
        }
    }

    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public void setImageResource(int i)
    {
        super.setImageResource(i);
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
    }
}
