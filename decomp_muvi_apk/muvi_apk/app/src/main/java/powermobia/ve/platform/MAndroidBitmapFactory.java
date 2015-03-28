// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.platform;

import android.graphics.Bitmap;
import powermobia.ve.utils.MBitmap;

public final class MAndroidBitmapFactory
{

    public MAndroidBitmapFactory()
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

    private static native int native_BitmapInitEx(Object obj, Bitmap bitmap, boolean flag);

    private static native Object native_GetBitmapFromMBitmap(Object obj, boolean flag);

    private static native int native_TransformMBitmapIntoBitmap(Object obj, Object obj1);

    public static int transformMBitmapIntoBitmap(MBitmap mbitmap, Bitmap bitmap)
    {
        if (mbitmap == null || bitmap == null)
        {
            return 2;
        } else
        {
            return native_TransformMBitmapIntoBitmap(mbitmap, bitmap);
        }
    }
}
