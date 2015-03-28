// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

public class ImageUtils
{

    public ImageUtils()
    {
    }

    public static Bitmap getAppointedSizeBitmap(Bitmap bitmap, int i, int j)
    {
        if (bitmap == null || bitmap.isRecycled())
        {
            return null;
        }
        if (i < 1 || j < 1)
        {
            bitmap.recycle();
            return null;
        }
        Bitmap bitmap1;
        try
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.outWidth = i;
            options.outHeight = j;
            bitmap1 = Bitmap.createScaledBitmap(bitmap, i, j, true);
            bitmap.recycle();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return null;
        }
        return bitmap1;
    }

    public static int getExifOrientationToDegrees(String s)
    {
        ExifInterface exifinterface = new ExifInterface(s);
        ExifInterface exifinterface1 = exifinterface;
_L8:
        Log.d("FENG", (new StringBuilder()).append("FENG getExifOrientationToDegrees exif = ").append(exifinterface1).toString());
        if (exifinterface1 == null) goto _L2; else goto _L1
_L1:
        int i;
        i = exifinterface1.getAttributeInt("Orientation", -1);
        Log.d("FENG", (new StringBuilder()).append("FENG getExifOrientationToDegrees orientation = ").append(i).toString());
        if (i == -1) goto _L2; else goto _L3
_L3:
        i;
        JVM INSTR tableswitch 3 8: default 120
    //                   3 125
    //                   4 120
    //                   5 120
    //                   6 122
    //                   7 120
    //                   8 129;
           goto _L2 _L4 _L2 _L2 _L5 _L2 _L6
_L2:
        return 0;
_L5:
        return 90;
_L4:
        return 180;
_L6:
        return 270;
        IOException ioexception;
        ioexception;
        exifinterface1 = null;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static float getFitInRect(Rect rect, Rect rect1, Rect rect2, boolean flag)
    {
        if (rect == null || rect1 == null || rect2 == null)
        {
            return 0.0F;
        }
        if (rect.width() <= 0 || rect.height() <= 0)
        {
            rect2.set(0, 0, 0, 0);
            return 0.0F;
        }
        int i = rect.width();
        int j = rect.height();
        int k = rect1.width();
        int l = rect1.height();
        if (k > i || l > j || flag)
        {
            int i1 = (0x186a0 * i) / k;
            int j1 = (0x186a0 * j) / l;
            if (i1 <= j1)
            {
                rect2.left = rect.left;
                rect2.right = rect.right;
                rect2.top = rect.top + (j - (l * i1) / 0x186a0 >> 1);
                int l1 = (50000 + l * i1) / 0x186a0;
                rect2.bottom = l1 + rect2.top;
                if (l1 == 0)
                {
                    rect2.top = -1 + rect2.top;
                }
                return 100000F / (float)i1;
            }
            rect2.top = rect.top;
            rect2.bottom = rect.bottom;
            rect2.left = rect.left + (i - (k * j1) / 0x186a0 >> 1);
            int k1 = (50000 + k * j1) / 0x186a0;
            rect2.right = k1 + rect2.left;
            if (k1 == 0)
            {
                rect2.left = -1 + rect2.left;
            }
            return 100000F / (float)j1;
        } else
        {
            rect2.left = rect.left + (i - k >> 1);
            rect2.right = k + rect2.left;
            rect2.top = rect.top + (j - l >> 1);
            rect2.bottom = l + rect2.top;
            return 1.0F;
        }
    }

    public static float getFitOutRect(Rect rect, Rect rect1, Rect rect2, boolean flag)
    {
        if (rect == null || rect1 == null || rect2 == null)
        {
            return 0.0F;
        }
        if (rect.width() <= 0 || rect.height() <= 0)
        {
            rect2.set(0, 0, 0, 0);
            return 0.0F;
        }
        int i = rect.width();
        int j = rect.height();
        int k = rect1.width();
        int l = rect1.height();
        if ((k <= i || l <= j) && !flag)
        {
            if (k <= i && l <= j)
            {
                rect2.top = 0;
                rect2.left = 0;
                rect2.right = rect1.width();
                rect2.bottom = rect1.height();
                return 1.0F;
            }
            if (k <= i)
            {
                rect2.left = 0;
                rect2.right = k;
                rect2.top = l - j >> 1;
                rect2.bottom = j + rect2.top;
                return 1.0F;
            }
            if (l <= j)
            {
                rect2.top = 0;
                rect2.bottom = l;
                rect2.left = k - i >> 1;
                rect2.right = i + rect2.left;
                return 1.0F;
            }
        }
        int i1 = (0x186a0 * k) / i;
        int j1 = (0x186a0 * l) / j;
        boolean flag1 = false;
        if (i1 < j1)
        {
            flag1 = true;
        }
        if (flag1)
        {
            rect2.left = 0;
            rect2.right = k;
            int l1 = (j * i1) / 0x186a0;
            rect2.top = (rect1.top + rect1.bottom) - l1 >> 1;
            rect2.bottom = l1 + rect2.top;
            return (float)i1 / 100000F;
        } else
        {
            rect2.top = 0;
            rect2.bottom = l;
            int k1 = (i * j1) / 0x186a0;
            rect2.left = (rect1.left + rect1.right) - k1 >> 1;
            rect2.right = k1 + rect2.left;
            return (float)j1 / 100000F;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int i)
    {
        if (i == 0 || bitmap == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(i, (float)bitmap.getWidth() / 2.0F, (float)bitmap.getHeight() / 2.0F);
        Bitmap bitmap1;
        try
        {
            int j = bitmap.getWidth();
            int k = bitmap.getHeight();
            bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, j, k, matrix, true);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            return bitmap;
        }
        if (bitmap == bitmap1)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        bitmap.recycle();
        bitmap = bitmap1;
        return bitmap;
    }
}
