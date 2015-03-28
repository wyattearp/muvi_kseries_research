// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;

import android.graphics.Bitmap;

// Referenced classes of package powermobia.utils:
//            MBitmap, MStream

public final class MBitmapFactory
{

    public MBitmapFactory()
    {
    }

    public static Bitmap createBitmapFromMBitmap(MBitmap mbitmap, boolean flag)
    {
        Bitmap bitmap = (Bitmap)native_GetBitmapFromMBitmap(mbitmap, flag);
        if (bitmap != null)
        {
            return bitmap;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapBlank(int i, int j, int k)
    {
        MBitmap mbitmap = new MBitmap(0, true, false);
        if (native_BitmapAlloc(mbitmap, k, i, j) == 0)
        {
            return mbitmap;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromBitmap(Bitmap bitmap, boolean flag)
    {
        MBitmap mbitmap = new MBitmap(0, true, flag);
        if (native_BitmapInitEx(mbitmap, bitmap, flag) == 0)
        {
            return mbitmap;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromFile(String s, int i, int j, int k)
    {
        MBitmap mbitmap = new MBitmap(0, true, false);
        if (native_BitmapLoad(mbitmap, 1, i, j, k, s) == 0)
        {
            return mbitmap;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromMBitmap(MBitmap mbitmap)
    {
        MBitmap mbitmap1 = new MBitmap(0, true, false);
        if (native_GetMBitmapFromMBitmap(mbitmap1, mbitmap, false) == 0)
        {
            return mbitmap1;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromMStream(MStream mstream, int i, int j, int k)
    {
        MBitmap mbitmap = new MBitmap(0, true, false);
        if (native_BitmapLoad(mbitmap, 2, i, j, k, mstream) == 0)
        {
            return mbitmap;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromNative(int i, boolean flag)
    {
        if (flag)
        {
            return new MBitmap(i, false, true);
        } else
        {
            return createMBitmapFromMBitmap(new MBitmap(i, false, true));
        }
    }

    public static MBitmap createMBitmapFromRaw(int i, int j, int k, int ai[], byte abyte0[][])
    {
        MBitmap mbitmap = new MBitmap(0, true, true);
        if (native_BitmapInit(mbitmap, i, j, k, ai, abyte0) == 0)
        {
            return mbitmap;
        } else
        {
            return null;
        }
    }

    private static native int native_BitmapAlloc(Object obj, int i, int j, int k);

    private static native int native_BitmapInit(Object obj, int i, int j, int k, int ai[], byte abyte0[][]);

    private static native int native_BitmapInitEx(Object obj, Bitmap bitmap, boolean flag);

    private static native int native_BitmapLoad(Object obj, int i, int j, int k, int l, Object obj1);

    private static native int native_BitmapLoadFast(Object obj, int i, int j, int k, int l, Object obj1);

    private static native Object native_GetBitmapFromMBitmap(Object obj, boolean flag);

    private static native int native_GetMBitmapFromMBitmap(Object obj, Object obj1, boolean flag);
}
