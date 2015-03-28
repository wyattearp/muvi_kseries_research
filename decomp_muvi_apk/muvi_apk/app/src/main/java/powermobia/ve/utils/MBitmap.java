// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


// Referenced classes of package powermobia.ve.utils:
//            MRect, MPoint, MStream

public final class MBitmap
{

    public static final int RESIZE_BILINEAR = 2;
    public static final int RESIZE_NEAREST_NEIGHBOUR = 1;
    private static int mNativeAMCM = 0;
    private static int mNativeAMCM_ref = 0;
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
        if (mNativeAMCM == 0)
        {
            mNativeAMCM = native_CreateAMCM(65535);
        }
        mNativeAMCM_ref = 1 + mNativeAMCM_ref;
    }

    public MBitmap(int i, boolean flag, boolean flag1)
    {
        mNeedFree = false;
        mRecycled = false;
        mShared = false;
        mNativeBitmap = i;
        if (mNativeAMCM == 0)
        {
            mNativeAMCM = native_CreateAMCM(65535);
        }
        mNativeAMCM_ref = 1 + mNativeAMCM_ref;
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
        mNativeAMCM_ref = -1 + mNativeAMCM_ref;
        if (mNativeAMCM != 0 && mNativeAMCM_ref == 0)
        {
            native_DestryAMCM(mNativeAMCM);
            mNativeAMCM = 0;
        }
        return 0;
    }

    private native int native_BitmapColorConvert(int i, int j, int k);

    private native int native_BitmapCrop(int i, int j, int k, Object obj);

    private native int native_BitmapCropRotFlipResample(int i, int j, int k, Object obj, Object obj1, int l, int i1);

    private native int native_BitmapFillColor(int i, int j, int k, Object obj, int l, int i1);

    private native int native_BitmapFlip(int i, int j, int k, int l);

    private native int native_BitmapFree(Object obj);

    private native int native_BitmapMerge(int i, int j, int k, Object obj, int l, Object obj1, int i1);

    private native int native_BitmapResample(int i, int j, int k, int l);

    private native int native_BitmapRotate(int i, int j, int k, int l);

    private native int native_BitmapSave(int i, int j, int k, int l, Object obj, int i1);

    private native int native_CreateAMCM(int i);

    private native int native_DestryAMCM(int i);

    private native int native_GetBitmapColorSpace(int i);

    private native int native_GetBitmapHeight(int i);

    private native int[] native_GetBitmapRowBytes(int i);

    private native int native_GetBitmapWidth(int i);

    public int colorConvert(MBitmap mbitmap)
    {
        int i = 0;
        if (mbitmap != null)
        {
            i = mbitmap.ni();
        }
        return native_BitmapColorConvert(mNativeAMCM, mNativeBitmap, i);
    }

    public int crop(MBitmap mbitmap, MRect mrect)
    {
        int i = 0;
        if (mbitmap != null)
        {
            i = mbitmap.ni();
        }
        return native_BitmapCrop(mNativeAMCM, mNativeBitmap, i, mrect);
    }

    public int cropRotFlipResample(MBitmap mbitmap, MRect mrect, MRect mrect1, int i, int j)
    {
        int k = 0;
        if (mbitmap != null)
        {
            k = mbitmap.ni();
        }
        return native_BitmapCropRotFlipResample(mNativeAMCM, mNativeBitmap, k, mrect, mrect1, i, j);
    }

    public int fillColor(int i, MRect mrect, MBitmap mbitmap, int j)
    {
        int k = 0;
        if (mbitmap != null)
        {
            k = mbitmap.ni();
        }
        return native_BitmapFillColor(mNativeAMCM, mNativeBitmap, i, mrect, k, j);
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

    public int flip(MBitmap mbitmap, int i)
    {
        int j = 0;
        if (mbitmap != null)
        {
            j = mbitmap.ni();
        }
        return native_BitmapFlip(mNativeAMCM, mNativeBitmap, j, i);
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
        return native_BitmapMerge(mNativeAMCM, mNativeBitmap, j, mpoint, k, mpoint1, i);
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

    public int resample(MBitmap mbitmap, int i)
    {
        int j = 0;
        if (mbitmap != null)
        {
            j = mbitmap.ni();
        }
        return native_BitmapResample(mNativeAMCM, mNativeBitmap, j, i);
    }

    public int rotate(MBitmap mbitmap, int i)
    {
        int j = 0;
        if (mbitmap != null)
        {
            j = mbitmap.ni();
        }
        return native_BitmapRotate(mNativeAMCM, mNativeBitmap, j, i);
    }

    public int save(int i, String s, int j)
    {
        return native_BitmapSave(mNativeAMCM, mNativeBitmap, 1, i, s, j);
    }

    public int save(int i, MStream mstream, int j)
    {
        return native_BitmapSave(mNativeAMCM, mNativeBitmap, 2, i, mstream, j);
    }

}
