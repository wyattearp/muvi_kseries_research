// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;

import android.graphics.Bitmap;

// Referenced classes of package powermobia.utils:
//            MRect, MPoint, MStream

public final class MBitmap
{

    public static final int RESIZE_BILINEAR = 2;
    public static final int RESIZE_NEAREST_NEIGHBOUR = 1;
    private Bitmap mAndroidBitmap;
    private int mBitmap_ref;
    private int mNativeBitmap;
    private boolean mNeedFree;
    private boolean mRecycled;
    private boolean mShared;

    private MBitmap()
    {
        mNeedFree = false;
        mRecycled = false;
        mShared = false;
        mNeedFree = false;
        mNativeBitmap = 0;
    }

    public MBitmap(int i, boolean flag, boolean flag1)
    {
        mNeedFree = false;
        mRecycled = false;
        mShared = false;
        mNativeBitmap = i;
        mNeedFree = flag;
        mShared = flag1;
    }

    private void checkRecycled(String s)
    {
        if (mRecycled)
        {
            throw new IllegalStateException(s);
        } else
        {
            return;
        }
    }

    private int free()
    {
        if (mNativeBitmap != 0 && mNeedFree)
        {
            native_BitmapFree(this);
            mNativeBitmap = 0;
        }
        return 0;
    }

    private native Object native_BitmapColorConvert(int i, int j);

    private native Object native_BitmapCrop(int i, Object obj);

    private native Object native_BitmapCropRotFlipResample(int i, Object obj, int j, int k, int l, int i1);

    private native int native_BitmapFillColor(int i, int j, Object obj, int k, int l);

    private native Object native_BitmapFlip(int i, int j);

    private native int native_BitmapFree(Object obj);

    private native int native_BitmapMerge(int i, int j, Object obj, int k, Object obj1, int l);

    private native Object native_BitmapResample(int i, int j, int k, int l);

    private native Object native_BitmapRotate(int i, int j);

    private native int native_BitmapSave(int i, int j, int k, Object obj, int l);

    private native int native_GetBitmapColorSpace(int i);

    private native int native_GetBitmapHeight(int i);

    private native int[] native_GetBitmapRowBytes(int i);

    private native int native_GetBitmapWidth(int i);

    public MBitmap colorConvert(int i)
    {
        return (MBitmap)native_BitmapColorConvert(mNativeBitmap, i);
    }

    public MBitmap crop(MRect mrect)
    {
        return (MBitmap)native_BitmapCrop(mNativeBitmap, mrect);
    }

    public MBitmap cropRotFlipResample(MRect mrect, int i, int j, int k, int l)
    {
        return (MBitmap)native_BitmapCropRotFlipResample(mNativeBitmap, mrect, i, j, k, l);
    }

    public int fillColor(int i, MRect mrect, MBitmap mbitmap, int j)
    {
        int k = 0;
        if (mbitmap != null)
        {
            k = mbitmap.ni();
        }
        return native_BitmapFillColor(mNativeBitmap, i, mrect, k, j);
    }

    protected void finalize()
        throws Throwable
    {
        Exception exception;
        try
        {
            free();
        }
        catch (Exception exception1)
        {
            super.finalize();
            return;
        }
        finally
        {
            super.finalize();
        }
        super.finalize();
        return;
        throw exception;
    }

    public MBitmap flip(int i)
    {
        return (MBitmap)native_BitmapFlip(mNativeBitmap, i);
    }

    public final int getColorSpace()
    {
        return native_GetBitmapColorSpace(mNativeBitmap);
    }

    public final int getHeight()
    {
        return native_GetBitmapHeight(mNativeBitmap);
    }

    public final int[] getRowBytes()
    {
        return native_GetBitmapRowBytes(mNativeBitmap);
    }

    public final int getWidth()
    {
        return native_GetBitmapWidth(mNativeBitmap);
    }

    public final boolean isRecycled()
    {
        return mRecycled;
    }

    public int merge(MBitmap mbitmap, MPoint mpoint, MBitmap mbitmap1, MPoint mpoint1, int i)
    {
        int j = 0;
        if (mbitmap != null)
        {
            j = mbitmap.ni();
        }
        int k = 0;
        if (mbitmap1 != null)
        {
            k = mbitmap1.ni();
        }
        return native_BitmapMerge(mNativeBitmap, j, mpoint, k, mpoint1, i);
    }

    final int ni()
    {
        return mNativeBitmap;
    }

    public void recycle()
    {
        if (!mRecycled)
        {
            free();
            mRecycled = true;
        }
    }

    public MBitmap resample(int i, int j, int k)
    {
        return (MBitmap)native_BitmapResample(mNativeBitmap, i, j, k);
    }

    public MBitmap rotate(int i)
    {
        return (MBitmap)native_BitmapRotate(mNativeBitmap, i);
    }

    public int save(int i, String s, int j)
    {
        return native_BitmapSave(mNativeBitmap, 1, i, s, j);
    }

    public int save(int i, MStream mstream, int j)
    {
        return native_BitmapSave(mNativeBitmap, 2, i, mstream, j);
    }
}
