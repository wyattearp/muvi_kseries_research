// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

// Referenced classes of package com.arcsoft.adk.atv:
//            Recycleble, MetadataImage

public static class mOpts
    implements Recycleble
{

    Bitmap mBitmap;
    android.graphics.er.mOpts mOpts;

    public void Recycle()
    {
        if (mOpts != null)
        {
            mOpts = null;
        }
        if (mBitmap != null)
        {
            mBitmap.recycle();
            mBitmap = null;
        }
    }

    public int getDimensionHeight()
    {
        android.graphics.er er = mOpts;
        int i = 0;
        if (er != null)
        {
            i = mOpts.mOpts;
        }
        return i;
    }

    public int getDimensionWidth()
    {
        android.graphics.er er = mOpts;
        int i = 0;
        if (er != null)
        {
            i = mOpts.mOpts;
        }
        return i;
    }

    public (String s)
    {
        mOpts = null;
        mBitmap = null;
        mOpts = new android.graphics.er.mOpts();
        mOpts.unds = true;
        mBitmap = BitmapFactory.decodeFile(s, mOpts);
    }
}
