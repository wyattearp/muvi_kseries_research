// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.ImageUtils;
import com.arcsoft.util.VideoUtils;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

public class LocalFrameDecoder
    implements com.arcsoft.mediaplus.picture.data.LocalCacheProvider.IThumbnailDecoder
{

    public static final int ALBUM_THUMB_HEIGHT = 180;
    public static final int ALBUM_THUMB_WIDTH = 180;
    private static final int MICRO_THUMB_SIZE = 96;
    private static final String TAG = "LocalFrameDecoder";
    public static final int THUMB_DATA_SIZE = 8192;
    public static final int THUMB_HEIGHT = 128;
    public static final int THUMB_WIDTH = 128;

    public LocalFrameDecoder()
    {
    }

    public static Bitmap createThumbnail(MediaItem mediaitem, int i, int j, Context context)
    {
        if (mediaitem == null || i <= 0 || j <= 0)
        {
            return null;
        }
        String s = mediaitem.strDecodePath;
        if (s == null)
        {
            if (mediaitem.uri != null && !mediaitem.uri.toString().startsWith("file://"))
            {
                if (s == null)
                {
                    return null;
                }
            } else
            if (mediaitem.uri == null)
            {
                s = null;
            } else
            {
                s = mediaitem.uri.toString();
            }
        }
        if (s == null)
        {
            return null;
        }
        Log.i("LocalFrameDecoder", (new StringBuilder()).append("createThumbnail path : ").append(s).toString());
        if (s.startsWith("file://"))
        {
            s = s.substring(7);
        }
        Bitmap bitmap = getThumbFromExif(s);
        if (bitmap == null)
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(s, options);
            int k;
            if (options.outWidth > options.outHeight)
            {
                k = options.outWidth;
            } else
            {
                k = options.outHeight;
            }
            options.inSampleSize = k / 128;
            if (options.inSampleSize < 1)
            {
                options.inSampleSize = 1;
            }
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
            bitmap = BitmapFactory.decodeFile(s, options);
            int _tmp = options.outWidth;
            int _tmp1 = options.outHeight;
            if (bitmap == null)
            {
                return null;
            }
        }
        return redressBitmap(bitmap, i, j);
    }

    public static Bitmap decodeImage(MediaItem mediaitem, Context context, int i, int j, boolean flag)
    {
        if (mediaitem == null)
        {
            return null;
        }
        if (mediaitem.videoOrImage)
        {
            Bitmap bitmap;
            if (mediaitem.strDecodePath != null)
            {
                bitmap = BitmapFactory.decodeFile(mediaitem.strDecodePath);
            } else
            {
                if (mediaitem.uri == null)
                {
                    return null;
                }
                boolean flag1 = mediaitem.uri.toString().startsWith("file://");
                bitmap = null;
                if (flag1)
                {
                    bitmap = VideoUtils.getVideoFrame(mediaitem.uri.toString().substring(7));
                }
            }
            if (flag)
            {
                return redressBitmap(bitmap, i, j);
            } else
            {
                return bitmap;
            }
        } else
        {
            return createThumbnail(mediaitem, i, j, context);
        }
    }

    static String getDownloadedFilePath(MediaItem mediaitem)
    {
        String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, mediaitem.title, mediaitem.uri.toString(), MyUPnPUtils.getItemResource(RemoteDBMgr.instance().getCurrentServer(), RemoteDBMgr.instance().getRemoteItemUUID(mediaitem._id)));
        if (!DownloadFacade.isFileExist(s))
        {
            s = null;
        }
        return s;
    }

    private static Bitmap getThumbFromExif(String s)
    {
        Bitmap bitmap = null;
        ExifInterface exifinterface;
        boolean flag;
        byte abyte0[];
        int i;
        int j;
        Bitmap bitmap1;
        try
        {
            exifinterface = new ExifInterface(s);
        }
        catch (IOException ioexception)
        {
            return bitmap;
        }
        bitmap = null;
        if (exifinterface == null) goto _L2; else goto _L1
_L1:
        flag = exifinterface.hasThumbnail();
        bitmap = null;
        if (!flag) goto _L2; else goto _L3
_L3:
        abyte0 = exifinterface.getThumbnail();
        bitmap = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
        i = exifinterface.getAttributeInt("Orientation", -1);
        j = 0;
        if (i == -1) goto _L5; else goto _L4
_L4:
        j = 0;
        i;
        JVM INSTR tableswitch 3 8: default 108
    //                   3 140
    //                   4 108
    //                   5 108
    //                   6 133
    //                   7 108
    //                   8 148;
           goto _L5 _L6 _L5 _L5 _L7 _L5 _L8
_L5:
        if (bitmap == null || (float)j == 0.0F) goto _L2; else goto _L9
_L9:
        bitmap1 = ImageUtils.rotate(bitmap, j);
        bitmap = bitmap1;
_L2:
        return bitmap;
_L7:
        j = 90;
        continue; /* Loop/switch isn't completed */
_L6:
        j = 180;
        continue; /* Loop/switch isn't completed */
_L8:
        j = 270;
        if (true) goto _L5; else goto _L10
_L10:
    }

    private static Bitmap getThumbFromMediaProvider(long l, int i, int j, Context context, String s)
    {
        Bitmap bitmap = null;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.outWidth = i;
        options.outHeight = j;
        options.inDither = false;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
        byte byte0 = 1;
        if (i <= 96 && j <= 96)
        {
            byte0 = 3;
        }
        Bitmap bitmap1;
        try
        {
            bitmap = android.provider.MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), l, byte0, options);
            bitmap1 = rotateBitmap(bitmap, s);
        }
        catch (OutOfMemoryError outofmemoryerror)
        {
            return bitmap;
        }
        return bitmap1;
    }

    private static Bitmap redressBitmap(Bitmap bitmap, int i, int j)
    {
        if (bitmap == null)
        {
            return null;
        }
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        int i1 = k / 2;
        int j1 = l / 2;
        int k1;
        int l1;
        int i2;
        int j2;
        OutOfMemoryError outofmemoryerror;
        Bitmap bitmap1;
        Canvas canvas;
        Paint paint;
        Rect rect;
        Rect rect1;
        if (i * l < j * k)
        {
            i2 = (i * l) / j;
            j2 = Math.max(0, Math.min(i1 - i2 / 2, k - i2));
            l1 = 0;
            k1 = l;
        } else
        {
            k1 = (j * k) / i;
            l1 = Math.max(0, Math.min(j1 - k1 / 2, l - k1));
            i2 = k;
            j2 = 0;
        }
        Log.d("LocalFrameDecoder", (new StringBuilder()).append("redressBitmap thumbnailWidth=").append(i).append(";thumbnailHeight=").append(j).append(";bmpWidth=").append(k).append(";bmpHeight=").append(l).append("cropWidth=").append(i2).append(";cropHeight=").append(k1).toString());
        bitmap1 = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap1);
        paint = new Paint();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawColor(0);
        rect = new Rect(j2, l1, j2 + i2, l1 + k1);
        rect1 = new Rect(0, 0, i, j);
        canvas.drawBitmap(bitmap, rect, rect1, paint);
        bitmap.recycle();
        return bitmap1;
        outofmemoryerror;
        outofmemoryerror.printStackTrace();
        Log.e("LocalFrameDecoder", "===out of memory===");
_L2:
        return null;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, String s)
    {
        int i = ImageUtils.getExifOrientationToDegrees(s);
        if (bitmap != null && (float)i != 0.0F)
        {
            bitmap = ImageUtils.rotate(bitmap, i);
        }
        return bitmap;
    }

    public Bitmap decodeImage(MediaItem mediaitem, Context context)
    {
        return decodeImage(mediaitem, context, 128, 128, true);
    }

    public void registerDecodeOkListener()
    {
    }

    public void unRegisterDecodeOkListener()
    {
    }
}
