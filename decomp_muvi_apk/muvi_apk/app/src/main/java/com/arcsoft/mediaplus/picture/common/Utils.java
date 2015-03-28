// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.StatFs;
import android.text.TextUtils;
import com.arcsoft.util.debug.Log;
import java.io.Closeable;
import java.io.File;
import java.io.InterruptedIOException;
import java.util.Random;

public class Utils
{

    private static final String DEBUG_TAG = "GalleryDebug";
    private static final long INITIALCRC = -1L;
    private static final boolean IS_DEBUG_BUILD = false;
    private static final String MASK_STRING = "********************************";
    private static final long POLY64REV = 0x95ac9329ac4bc9b5L;
    private static final String TAG = "Utils";
    private static long sCrcTable[];

    public Utils()
    {
    }

    public static void assertTrue(boolean flag)
    {
        if (!flag)
        {
            throw new AssertionError();
        } else
        {
            return;
        }
    }

    public static transient void assertTrue(boolean flag, String s, Object aobj[])
    {
        if (!flag)
        {
            if (aobj.length != 0)
            {
                s = String.format(s, aobj);
            }
            throw new AssertionError(s);
        } else
        {
            return;
        }
    }

    public static int ceilLog2(float f)
    {
        int i = 0;
        do
        {
            if (i >= 31 || (float)(1 << i) >= f)
            {
                return i;
            }
            i++;
        } while (true);
    }

    public static Object checkNotNull(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return obj;
        }
    }

    public static float clamp(float f, float f1, float f2)
    {
        if (f > f2)
        {
            return f2;
        }
        if (f < f1)
        {
            return f1;
        } else
        {
            return f;
        }
    }

    public static int clamp(int i, int j, int k)
    {
        if (i > k)
        {
            return k;
        }
        if (i < j)
        {
            return j;
        } else
        {
            return i;
        }
    }

    public static long clamp(long l, long l1, long l2)
    {
        if (l > l2)
        {
            return l2;
        }
        if (l < l1)
        {
            return l1;
        } else
        {
            return l;
        }
    }

    public static void closeSilently(Cursor cursor)
    {
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_10;
        }
        cursor.close();
        return;
        Throwable throwable;
        throwable;
        Log.w("Utils", "fail to close", throwable);
        return;
    }

    public static void closeSilently(ParcelFileDescriptor parcelfiledescriptor)
    {
        if (parcelfiledescriptor == null)
        {
            break MISSING_BLOCK_LABEL_8;
        }
        parcelfiledescriptor.close();
        return;
        Throwable throwable;
        throwable;
        Log.w("Utils", "fail to close", throwable);
        return;
    }

    public static void closeSilently(Closeable closeable)
    {
        if (closeable == null)
        {
            return;
        }
        try
        {
            closeable.close();
            return;
        }
        catch (Throwable throwable)
        {
            Log.w("Utils", "close fail", throwable);
        }
    }

    public static int compare(long l, long l1)
    {
        if (l < l1)
        {
            return -1;
        }
        return l != l1 ? 1 : 0;
    }

    public static String[] copyOf(String as[], int i)
    {
        String as1[] = new String[i];
        System.arraycopy(as, 0, as1, 0, Math.min(as.length, i));
        return as1;
    }

    public static final long crc64Long(String s)
    {
        if (s == null || s.length() == 0)
        {
            return 0L;
        } else
        {
            return crc64Long(getBytes(s));
        }
    }

    public static final long crc64Long(byte abyte0[])
    {
        long l = -1L;
        int i = 0;
        for (int j = abyte0.length; i < j; i++)
        {
            l = sCrcTable[0xff & ((int)l ^ abyte0[i])] ^ l >> 8;
        }

        return l;
    }

    public static transient void debug(String s, Object aobj[])
    {
        if (aobj.length == 0)
        {
            Log.d("GalleryDebug", s);
            return;
        } else
        {
            Log.d("GalleryDebug", String.format(s, aobj));
            return;
        }
    }

    public static PendingIntent deserializePendingIntent(byte abyte0[])
    {
        Parcel parcel = null;
        if (abyte0 == null) goto _L2; else goto _L1
_L1:
        PendingIntent pendingintent1;
        parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, abyte0.length);
        pendingintent1 = PendingIntent.readPendingIntentOrNullFromParcel(parcel);
        PendingIntent pendingintent;
        pendingintent = pendingintent1;
        if (parcel != null)
        {
            parcel.recycle();
        }
_L4:
        return pendingintent;
_L2:
        pendingintent = null;
        if (true) goto _L4; else goto _L3
_L3:
        null.recycle();
        return null;
        Exception exception1;
        exception1;
        throw new IllegalArgumentException("error parsing PendingIntent");
        Exception exception;
        exception;
        if (parcel != null)
        {
            parcel.recycle();
        }
        throw exception;
    }

    public static float distance(float f, float f1, float f2, float f3)
    {
        float f4 = f - f2;
        float f5 = f1 - f3;
        return (float)Math.hypot(f4, f5);
    }

    public static String ensureNotNull(String s)
    {
        if (s == null)
        {
            s = "";
        }
        return s;
    }

    public static boolean equals(Object obj, Object obj1)
    {
        while (obj != obj1 && (obj == null || !obj.equals(obj1))) 
        {
            return false;
        }
        return true;
    }

    public static String escapeXml(String s)
    {
        StringBuilder stringbuilder;
        int i;
        int j;
        stringbuilder = new StringBuilder();
        i = 0;
        j = s.length();
_L8:
        char c;
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 5: default 80
    //                   34: 113
    //                   38: 133
    //                   39: 123
    //                   60: 93
    //                   62: 103;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L3:
        break MISSING_BLOCK_LABEL_133;
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        stringbuilder.append(c);
_L9:
        i++;
        if (true) goto _L8; else goto _L7
_L7:
        stringbuilder.append("&lt;");
          goto _L9
_L6:
        stringbuilder.append("&gt;");
          goto _L9
_L2:
        stringbuilder.append("&quot;");
          goto _L9
_L4:
        stringbuilder.append("&#039;");
          goto _L9
        stringbuilder.append("&amp;");
          goto _L9
        return stringbuilder.toString();
    }

    public static int floorLog2(float f)
    {
        int i = 0;
        do
        {
            if (i >= 31 || (float)(1 << i) > f)
            {
                return i - 1;
            }
            i++;
        } while (true);
    }

    public static byte[] getBytes(String s)
    {
        byte abyte0[] = new byte[2 * s.length()];
        char ac[] = s.toCharArray();
        int i = ac.length;
        int j = 0;
        int k = 0;
        for (; j < i; j++)
        {
            char c = ac[j];
            int l = k + 1;
            abyte0[k] = (byte)(c & 0xff);
            k = l + 1;
            abyte0[l] = (byte)(c >> 8);
        }

        return abyte0;
    }

    public static String getUserAgent(Context context)
    {
        PackageInfo packageinfo;
        Object aobj[];
        try
        {
            packageinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            throw new IllegalStateException("getPackageInfo failed");
        }
        aobj = new Object[9];
        aobj[0] = packageinfo.packageName;
        aobj[1] = packageinfo.versionName;
        aobj[2] = Build.BRAND;
        aobj[3] = Build.DEVICE;
        aobj[4] = Build.MODEL;
        aobj[5] = Build.ID;
        aobj[6] = android.os.Build.VERSION.SDK;
        aobj[7] = android.os.Build.VERSION.RELEASE;
        aobj[8] = android.os.Build.VERSION.INCREMENTAL;
        return String.format("%s/%s; %s/%s/%s/%s; %s/%s/%s", aobj);
    }

    public static boolean handleInterrruptedException(Throwable throwable)
    {
        if ((throwable instanceof InterruptedIOException) || (throwable instanceof InterruptedException))
        {
            Thread.currentThread().interrupt();
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean hasSpaceForSize(long l)
    {
        if ("mounted".equals(Environment.getExternalStorageState()))
        {
            String s = Environment.getExternalStorageDirectory().getPath();
            long l1;
            int i;
            try
            {
                StatFs statfs = new StatFs(s);
                l1 = statfs.getAvailableBlocks();
                i = statfs.getBlockSize();
            }
            catch (Exception exception)
            {
                Log.i("Utils", "Fail to access external storage", exception);
                return false;
            }
            if (l1 * (long)i > l)
            {
                return true;
            }
        }
        return false;
    }

    public static float interpolateAngle(float f, float f1, float f2)
    {
        float f3 = f1 - f;
        if (f3 < 0.0F)
        {
            f3 += 360F;
        }
        if (f3 > 180F)
        {
            f3 -= 360F;
        }
        float f4 = f + f3 * f2;
        if (f4 < 0.0F)
        {
            f4 += 360F;
        }
        return f4;
    }

    public static float interpolateScale(float f, float f1, float f2)
    {
        return f + f2 * (f1 - f);
    }

    public static boolean isNullOrEmpty(String s)
    {
        return TextUtils.isEmpty(s);
    }

    public static boolean isOpaque(int i)
    {
        return i >>> 24 == 255;
    }

    public static boolean isPowerOf2(int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException();
        }
        return (i & -i) == i;
    }

    public static String maskDebugInfo(Object obj)
    {
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            s = obj.toString();
            int i = Math.min(s.length(), "********************************".length());
            if (!IS_DEBUG_BUILD)
            {
                return "********************************".substring(0, i);
            }
        }
        return s;
    }

    public static int nextPowerOf2(int i)
    {
        if (i <= 0 || i > 0x40000000)
        {
            throw new IllegalArgumentException();
        } else
        {
            int j = i - 1;
            int k = j | j >> 16;
            int l = k | k >> 8;
            int i1 = l | l >> 4;
            int j1 = i1 | i1 >> 2;
            return 1 + (j1 | j1 >> 1);
        }
    }

    public static float parseFloatSafely(String s, float f)
    {
        if (s == null)
        {
            return f;
        }
        float f1;
        try
        {
            f1 = Float.parseFloat(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return f;
        }
        return f1;
    }

    public static int parseIntSafely(String s, int i)
    {
        if (s == null)
        {
            return i;
        }
        int j;
        try
        {
            j = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return i;
        }
        return j;
    }

    public static int prevPowerOf2(int i)
    {
        if (i <= 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            return Integer.highestOneBit(i);
        }
    }

    public static byte[] serializePendingIntent(PendingIntent pendingintent)
    {
        Parcel parcel = null;
        byte abyte0[];
        parcel = Parcel.obtain();
        PendingIntent.writePendingIntentOrNullToParcel(pendingintent, parcel);
        abyte0 = parcel.marshall();
        if (parcel != null)
        {
            parcel.recycle();
        }
        return abyte0;
        Exception exception;
        exception;
        if (parcel != null)
        {
            parcel.recycle();
        }
        throw exception;
    }

    public static void shuffle(int ai[], Random random)
    {
        int i = ai.length;
        while (i > 0) 
        {
            int j = random.nextInt(i);
            if (j != i - 1)
            {
                int k = ai[i - 1];
                ai[i - 1] = ai[j];
                ai[j] = k;
            }
            i--;
        }
    }

    public static void swap(int ai[], int i, int j)
    {
        int k = ai[i];
        ai[i] = ai[j];
        ai[j] = k;
    }

    public static void swap(Object aobj[], int i, int j)
    {
        Object obj = aobj[i];
        aobj[i] = aobj[j];
        aobj[j] = obj;
    }

    public static void waitWithoutInterrupt(Object obj)
    {
        try
        {
            obj.wait();
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            Log.w("Utils", (new StringBuilder()).append("unexpected interrupt: ").append(obj).toString());
        }
    }

    static 
    {
        sCrcTable = new long[256];
        boolean flag;
        if (Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug"))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        IS_DEBUG_BUILD = flag;
        for (int i = 0; i < 256; i++)
        {
            long l = i;
            int j = 0;
            while (j < 8) 
            {
                long l1;
                if ((1 & (int)l) != 0)
                {
                    l1 = 0x95ac9329ac4bc9b5L;
                } else
                {
                    l1 = 0L;
                }
                l = l1 ^ l >> 1;
                j++;
            }
            sCrcTable[i] = l;
        }

    }
}
