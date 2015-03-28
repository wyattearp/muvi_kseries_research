// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videotrim.Utils.UtilFunc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VideoUtils
{

    private static final String TAG = "VideoUtils";

    public VideoUtils()
    {
    }

    public static Bitmap getVideoFrame(String s)
    {
        if (Integer.valueOf(android.os.Build.VERSION.SDK).intValue() >= 10)
        {
            return getVideoFrameAfterSDK10(s);
        } else
        {
            return getVideoFrameBeforeSDK10(s);
        }
    }

    public static Bitmap getVideoFrame2(String s, int i, int j, boolean flag)
    {
        if (!UtilFunc.IsFileExisted(s))
        {
            return null;
        }
        int k;
        if (flag)
        {
            k = 1;
        } else
        {
            k = 3;
        }
        return ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(s, k), i, j, 2);
    }

    public static Bitmap getVideoFrameAfterSDK10(String s)
    {
        MediaMetadataRetriever mediametadataretriever;
        FileInputStream fileinputstream;
        Bitmap bitmap;
        Log.d("VideoUtils", "getVideoFrameAfterSDK10");
        mediametadataretriever = null;
        fileinputstream = null;
        bitmap = null;
        MediaMetadataRetriever mediametadataretriever1 = new MediaMetadataRetriever();
        FileInputStream fileinputstream1;
        bitmap = null;
        fileinputstream1 = null;
        if (mediametadataretriever1 == null)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        boolean flag = (new File(s)).exists();
        bitmap = null;
        fileinputstream1 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_117;
        }
        FileInputStream fileinputstream2 = new FileInputStream(s);
        mediametadataretriever1.setDataSource(fileinputstream2.getFD());
        bitmap = mediametadataretriever1.getFrameAtTime(0L);
        Log.d("VideoUtils", (new StringBuilder()).append("getVideoFrameAfterSDK10, bitmap = ").append(bitmap).toString());
        fileinputstream1 = fileinputstream2;
        if (mediametadataretriever1 != null)
        {
            mediametadataretriever1.release();
        }
        if (fileinputstream1 != null)
        {
            try
            {
                fileinputstream1.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return bitmap;
            }
        }
_L2:
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
_L11:
        outofmemoryerror.printStackTrace();
        if (mediametadataretriever != null)
        {
            mediametadataretriever.release();
        }
        bitmap = null;
        if (fileinputstream == null) goto _L2; else goto _L1
_L1:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
            return null;
        }
        return null;
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L10:
        filenotfoundexception.printStackTrace();
        if (mediametadataretriever != null)
        {
            mediametadataretriever.release();
        }
        if (fileinputstream == null) goto _L2; else goto _L3
_L3:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
            return bitmap;
        }
        return bitmap;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
_L9:
        illegalargumentexception.printStackTrace();
        if (mediametadataretriever != null)
        {
            mediametadataretriever.release();
        }
        if (fileinputstream == null) goto _L2; else goto _L4
_L4:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception4)
        {
            ioexception4.printStackTrace();
            return bitmap;
        }
        return bitmap;
        Exception exception1;
        exception1;
_L8:
        exception1.printStackTrace();
        if (mediametadataretriever != null)
        {
            mediametadataretriever.release();
        }
        if (fileinputstream == null) goto _L2; else goto _L5
_L5:
        try
        {
            fileinputstream.close();
        }
        catch (IOException ioexception5)
        {
            ioexception5.printStackTrace();
            return bitmap;
        }
        return bitmap;
        Exception exception;
        exception;
_L7:
        if (mediametadataretriever != null)
        {
            mediametadataretriever.release();
        }
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
        }
        throw exception;
        exception;
        mediametadataretriever = mediametadataretriever1;
        fileinputstream = null;
        continue; /* Loop/switch isn't completed */
        exception;
        fileinputstream = fileinputstream2;
        mediametadataretriever = mediametadataretriever1;
        if (true) goto _L7; else goto _L6
_L6:
        exception1;
        mediametadataretriever = mediametadataretriever1;
        bitmap = null;
        fileinputstream = null;
          goto _L8
        exception1;
        fileinputstream = fileinputstream2;
        mediametadataretriever = mediametadataretriever1;
          goto _L8
        illegalargumentexception;
        mediametadataretriever = mediametadataretriever1;
        bitmap = null;
        fileinputstream = null;
          goto _L9
        illegalargumentexception;
        fileinputstream = fileinputstream2;
        mediametadataretriever = mediametadataretriever1;
          goto _L9
        filenotfoundexception;
        mediametadataretriever = mediametadataretriever1;
        bitmap = null;
        fileinputstream = null;
          goto _L10
        filenotfoundexception;
        fileinputstream = fileinputstream2;
        mediametadataretriever = mediametadataretriever1;
          goto _L10
        outofmemoryerror;
        mediametadataretriever = mediametadataretriever1;
        fileinputstream = null;
          goto _L11
        outofmemoryerror;
        fileinputstream = fileinputstream2;
        mediametadataretriever = mediametadataretriever1;
          goto _L11
    }

    private static Bitmap getVideoFrameBeforeSDK10(String s)
    {
        Bitmap bitmap;
        Class class1 = Class.forName("android.media.MediaMetadataRetriever");
        Object obj = class1.newInstance();
        class1.getMethod("setDataSource", new Class[] {
            java/lang/String
        }).invoke(obj, new Object[] {
            s
        });
        bitmap = (Bitmap)class1.getMethod("captureFrame", new Class[0]).invoke(obj, new Object[0]);
        class1.getMethod("release", new Class[0]).invoke(obj, new Object[0]);
        return bitmap;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        outofmemoryerror.printStackTrace();
_L2:
        return null;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        classnotfoundexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        InstantiationException instantiationexception;
        instantiationexception;
        instantiationexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        illegalaccessexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        SecurityException securityexception;
        securityexception;
        securityexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        nosuchmethodexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        InvocationTargetException invocationtargetexception;
        invocationtargetexception;
        invocationtargetexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }
}
