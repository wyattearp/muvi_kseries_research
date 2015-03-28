// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


// Referenced classes of package powermobia.ve.utils:
//            MBitmap

public final class MBitmapFactory
{

    public MBitmapFactory()
    {
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

    public static MBitmap createMBitmapFromMBitmap(MBitmap mbitmap, boolean flag)
    {
        if (flag)
        {
            return mbitmap;
        }
        MBitmap mbitmap1 = new MBitmap(0, true, false);
        if (native_GetMBitmapFromMBitmap(mbitmap1, mbitmap, false) == 0)
        {
            return mbitmap1;
        } else
        {
            return null;
        }
    }

    public static MBitmap createMBitmapFromNative(int i)
    {
        return new MBitmap(i, false, true);
    }

    private static native int native_BitmapAlloc(Object obj, int i, int j, int k);

    private static native int native_GetMBitmapFromMBitmap(Object obj, Object obj1, boolean flag);
}
