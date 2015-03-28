// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.StatFs;
import android.webkit.MimeTypeMap;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import powermobia.ve.platform.MAndroidBitmapFactory;
import powermobia.ve.utils.MBitmap;
import powermobia.ve.utils.MBitmapFactory;
import powermobia.ve.utils.MColorSpace;
import powermobia.videoeditor.base.MDisplayContext;
import powermobia.videoeditor.base.MRange;
import powermobia.videoeditor.base.MSession;
import powermobia.videoeditor.base.MSessionStream;
import powermobia.videoeditor.base.MVideoInfo;
import powermobia.videoeditor.clip.MClip;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            RuntimeConfig

public class UtilFunc
{

    private static final String TAG = "UtilFunc";

    public UtilFunc()
    {
    }

    public static int CompressBitmapToJpg(Bitmap bitmap, String s)
    {
        if (bitmap == null || s == null || s.length() <= 0)
        {
            return 2;
        }
        FileOutputStream fileoutputstream;
        int i;
        try
        {
            fileoutputstream = new FileOutputStream(s);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
            return 1;
        }
        if (bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 80, fileoutputstream))
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (fileoutputstream != null)
        {
            try
            {
                fileoutputstream.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        }
        return i;
    }

    public static int CreateClipThumbnailManager(MClip mclip, int i, int j, boolean flag)
    {
        if (mclip == null || i <= 0 || j <= 0)
        {
            return 2;
        }
        boolean flag1;
        MVideoInfo mvideoinfo;
        Rect rect;
        Rect rect1;
        int k;
        int l;
        if (2 == ((Integer)mclip.getProperty(12289)).intValue())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        mvideoinfo = (MVideoInfo)mclip.getProperty(12291);
        rect = new Rect(0, 0, mvideoinfo.get(3), mvideoinfo.get(4));
        rect1 = GetFitoutRect(rect, new Rect(0, 0, i, j));
        if (flag1)
        {
            k = Math.min(rect.width(), rect1.width());
        } else
        {
            k = mvideoinfo.get(3);
        }
        if (flag1)
        {
            l = Math.min(rect.height(), rect1.height());
        } else
        {
            l = mvideoinfo.get(4);
        }
        if (k % 4 != 0)
        {
            k = 4 * ((k + 3) / 4);
        }
        Log("UtilFunc", (new StringBuilder()).append("CreateClipThumbnailManager: streamWidth = ").append(k).append(" streamHeight = ").append(l).toString());
        if (flag)
        {
            return mclip.createThumbnailManager(k, j, 0x10002, flag);
        } else
        {
            return mclip.createThumbnailManager(k, l, 0x10002);
        }
    }

    public static boolean CreateMultilevelDirectory(String s)
    {
        if (s == null || s.length() <= 0)
        {
            return false;
        }
        File file = new File(s);
        if (file.exists())
        {
            return true;
        }
        boolean flag;
        try
        {
            flag = file.mkdirs();
        }
        catch (SecurityException securityexception)
        {
            securityexception.printStackTrace();
            return false;
        }
        return flag;
    }

    public static void DeleteFileByName(String s)
    {
        Log("Utils.DeleteFileByName", (new StringBuilder()).append("strPath--->").append(s).toString());
        File file;
        if (s != null && s.length() > 0)
        {
            if ((file = new File(s)).exists())
            {
                file.delete();
                return;
            }
        }
    }

    public static Rect GetFitInRect(Rect rect, Rect rect1)
    {
        Rect rect2 = new Rect(0, 0, rect1.width(), rect1.height());
        int i;
        int j;
        if (rect.width() * rect1.height() > rect1.width() * rect.height())
        {
            rect2.bottom = (rect.height() * rect1.width()) / rect.width();
        } else
        {
            rect2.right = (rect.width() * rect1.height()) / rect.height();
        }
        i = rect1.width() - rect2.width();
        j = rect1.height() - rect2.height();
        rect2.left = rect2.left + i / 2;
        rect2.right = rect2.right + i / 2;
        rect2.top = rect2.top + j / 2;
        rect2.bottom = rect2.bottom + j / 2;
        if (rect2.width() < 2)
        {
            if (rect2.left > 2)
            {
                rect2.left = -1 + rect2.left;
                rect2.right = 1 + rect2.right;
            } else
            {
                rect2.left = 0;
                rect2.right = 2;
            }
        }
        if (rect2.height() < 2)
        {
            if (rect2.top > 2)
            {
                rect2.top = -1 + rect2.top;
                rect2.bottom = 1 + rect2.bottom;
            } else
            {
                rect2.top = 0;
                rect2.bottom = 2;
            }
        }
        if (rect2.width() % 2 != 0)
        {
            rect2.right = 1 + rect2.right;
        }
        if (rect2.height() % 2 != 0)
        {
            rect2.bottom = 1 + rect2.bottom;
        }
        return rect2;
    }

    public static Rect GetFitoutRect(Rect rect, Rect rect1)
    {
        Rect rect2 = new Rect(0, 0, rect1.width(), rect1.height());
        int i;
        int j;
        if (rect.width() * rect1.height() > rect1.width() * rect.height())
        {
            rect2.right = (rect.width() * rect1.height()) / rect.height();
        } else
        {
            rect2.bottom = (rect.height() * rect1.width()) / rect.width();
        }
        i = rect1.width() - rect2.width();
        j = rect1.height() - rect2.height();
        rect2.left = rect2.left + i / 2;
        rect2.right = rect2.right + i / 2;
        rect2.top = rect2.top + j / 2;
        rect2.bottom = rect2.bottom + j / 2;
        if (rect2.width() < 2)
        {
            if (rect2.left > 2)
            {
                rect2.left = -1 + rect2.left;
                rect2.right = 1 + rect2.right;
            } else
            {
                rect2.left = 0;
                rect2.right = 2;
            }
        }
        if (rect2.height() < 2)
        {
            if (rect2.top > 2)
            {
                rect2.top = -1 + rect2.top;
                rect2.bottom = 1 + rect2.bottom;
            } else
            {
                rect2.top = 0;
                rect2.bottom = 2;
            }
        }
        if (rect2.width() % 2 != 0)
        {
            rect2.right = 1 + rect2.right;
        }
        if (rect2.height() % 2 != 0)
        {
            rect2.bottom = 1 + rect2.bottom;
        }
        return rect2;
    }

    public static long GetFreeSpace(String s)
    {
        if (s == null || s.length() <= 0)
        {
            return 0L;
        } else
        {
            StatFs statfs = new StatFs(s);
            return (long)statfs.getBlockSize() * (long)statfs.getAvailableBlocks();
        }
    }

    private static String GetMimeTypeByMediaFileName(String s)
    {
        String s1;
        MimeTypeMap mimetypemap;
        if (s != null)
        {
            if ((s1 = s.substring(1 + s.lastIndexOf("."))) != null && (mimetypemap = MimeTypeMap.getSingleton()) != null)
            {
                return mimetypemap.getMimeTypeFromExtension(s1.toLowerCase());
            }
        }
        return null;
    }

    public static long GetOutputBitrate(int i, int j)
    {
        if (i <= 0 || j <= 0)
        {
            return 0L;
        }
        long l = i * j;
        if (l <= (25344L + 0x12c00L) / 2L)
        {
            return 0x3e800L;
        }
        if (l <= (0x12c00L + 0x4b000L) / 2L)
        {
            return 0x5dc00L;
        }
        if (l <= (0x4b000L + 0x54600L) / 2L)
        {
            return 0x177000L;
        }
        return l > (0x54600L + 0xe1000L) / 2L ? 0x600000L : 0x1a5e00L;
    }

    public static boolean IsFileExisted(String s)
    {
        File file;
        if (s != null && s.length() > 0)
        {
            if ((file = new File(s)).isFile() && file.exists())
            {
                return true;
            }
        }
        return false;
    }

    public static void Log(String s, String s1)
    {
        com.arcsoft.util.debug.Log.i(s, s1);
    }

    public static boolean RenameFile(String s, String s1)
    {
        if (s != null && s.length() > 0 && s1 != null && s1.length() > 0)
        {
            File file = new File(s);
            File file1 = new File(s1);
            if (file.isFile() && file.renameTo(file1))
            {
                return true;
            }
        }
        return false;
    }

    public static String TransTimeToString(int i, int j)
    {
        int k = i + 500;
        if (k < j)
        {
            k = j;
        }
        int l = k / 1000;
        int i1 = l / 3600;
        int j1 = l % 3600;
        int k1 = j1 / 60;
        int l1 = j1 % 60;
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(i1);
        aobj[1] = Integer.valueOf(k1);
        aobj[2] = Integer.valueOf(l1);
        return String.format("%02d:%02d:%02d", aobj);
    }

    public static Uri addVideoFileByFullName(Context context, String s)
    {
        ContentValues contentvalues;
        File file = new File(s);
        if (file == null || !file.exists())
        {
            break MISSING_BLOCK_LABEL_403;
        }
        String s1 = file.getName();
        int i = s1.lastIndexOf(".");
        if (i < 0)
        {
            return null;
        }
        String s2 = s1.substring(0, i);
        contentvalues = new ContentValues(8);
        contentvalues.put("title", s2);
        contentvalues.put("_display_name", file.getName());
        contentvalues.put("_data", file.getPath());
        contentvalues.put("_size", Long.valueOf(file.length()));
        long l = System.currentTimeMillis();
        contentvalues.put("date_added", Long.valueOf(l));
        contentvalues.put("date_modified", Long.valueOf(l / 1000L));
        MediaMetadataRetriever mediametadataretriever;
        String s4;
        mediametadataretriever = new MediaMetadataRetriever();
        mediametadataretriever.setDataSource(s);
        s4 = mediametadataretriever.extractMetadata(9);
        if (s4 == null)
        {
            break MISSING_BLOCK_LABEL_189;
        }
        contentvalues.put("duration", Integer.valueOf(Integer.parseInt(s4)));
        String s5;
        String s6;
        s5 = mediametadataretriever.extractMetadata(20);
        s6 = mediametadataretriever.extractMetadata(19);
        if (s5 == null)
        {
            break MISSING_BLOCK_LABEL_233;
        }
        if (Integer.parseInt(s5) <= 0 || s6 == null)
        {
            break MISSING_BLOCK_LABEL_233;
        }
        if (Integer.parseInt(s6) > 0)
        {
            break MISSING_BLOCK_LABEL_285;
        }
        Bitmap bitmap = mediametadataretriever.getFrameAtTime(0L);
        int j;
        int k;
        j = 0;
        k = 0;
        if (bitmap == null)
        {
            break MISSING_BLOCK_LABEL_271;
        }
        k = bitmap.getWidth();
        j = bitmap.getHeight();
        bitmap.recycle();
        s5 = String.valueOf(k);
        s6 = String.valueOf(j);
        if (s5 == null || s6 == null)
        {
            break MISSING_BLOCK_LABEL_329;
        }
        contentvalues.put("resolution", (new StringBuilder()).append(s5).append("x").append(s6).toString());
        String s3;
        Uri uri;
        if (mediametadataretriever != null)
        {
            try
            {
                mediametadataretriever.release();
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        s3 = GetMimeTypeByMediaFileName(s);
        if (s3 == null)
        {
            s3 = String.format("video/mp4", new Object[0]);
        }
        contentvalues.put("mime_type", s3);
        try
        {
            uri = context.getContentResolver().insert(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentvalues);
        }
        catch (Exception exception1)
        {
            return null;
        }
        return uri;
        return null;
    }

    public static MSessionStream createStream(int i, MSession msession, int j, int k, MRange mrange, MClip mclip, MDisplayContext mdisplaycontext, boolean flag)
    {
        MSessionStream msessionstream;
        if (msession == null || mrange == null || mdisplaycontext == null)
        {
            msessionstream = null;
        } else
        {
            msessionstream = new MSessionStream();
            Log("HW", (new StringBuilder()).append("CreateStream SW stream.open(sourceType=").append(i).append(", decoderType=").append(2).append(")").toString());
            int l = msessionstream.open(i, msession, j, k, mrange, mclip, mdisplaycontext, 2);
            Log("HW", (new StringBuilder()).append("CreateStream SW stream.open() iRes=").append(l).append(",------->").toString());
            if (l != 0)
            {
                return null;
            }
        }
        return msessionstream;
    }

    public static Bitmap decodeBitmapByRect(Bitmap bitmap, Rect rect, Rect rect1)
    {
        int i;
        int j;
        new Rect();
        Rect rect2 = GetFitInRect(rect, rect1);
        i = rect2.right - rect2.left;
        j = rect2.bottom - rect2.top;
        if (bitmap == null)
        {
            return null;
        }
        Bitmap bitmap1;
        bitmap1 = Bitmap.createBitmap(Math.round(i), Math.round(j), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint(2);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, Math.round(i), Math.round(j)), paint);
        bitmap.recycle();
        return bitmap1;
        OutOfMemoryError outofmemoryerror;
        outofmemoryerror;
        outofmemoryerror.printStackTrace();
_L2:
        return null;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        illegalargumentexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int getClipThumbnail(MClip mclip, MBitmap mbitmap, int i, boolean flag, boolean flag1)
    {
        Log("UtilFunc", "getClipThumbnail in...");
        if (mclip == null || mbitmap == null)
        {
            return 2;
        }
        int j;
        if (flag1)
        {
            if (RuntimeConfig.IFRAME_SOLUTION)
            {
                Log("HW", "getKeyframe SW1");
                j = mclip.getKeyframe(mbitmap, i, true, 1);
            } else
            {
                Log("HW", "getKeyframe SW1");
                j = mclip.getKeyframe(mbitmap, i, true, 2);
            }
        } else
        {
            Log("HW", "getthubmanil");
            j = mclip.getThumbnail(mbitmap, i, true);
        }
        Log("UtilFunc", (new StringBuilder()).append("getClipThumbnail out...iRes: ").append(j).toString());
        return j;
    }

    public static Object getClipThumbnail(MClip mclip, int i, int j, int k, boolean flag, boolean flag1)
    {
        Log("UtilFunc", (new StringBuilder()).append("getClipThumbnail in...clip: ").append(mclip).toString());
        MBitmap mbitmap;
        if (mclip == null)
        {
            mbitmap = null;
        } else
        {
            if (CreateClipThumbnailManager(mclip, 320, 240, true) != 0)
            {
                return null;
            }
            mbitmap = MBitmapFactory.createMBitmapBlank(320, 240, MColorSpace.MPAF_RGB16_R5G6B5);
            if (mbitmap == null)
            {
                return null;
            }
            if (getClipThumbnail(mclip, mbitmap, i, flag, true) != 0)
            {
                if (!mbitmap.isRecycled())
                {
                    mbitmap.recycle();
                }
                mclip.destroyThumbnailManager();
                return null;
            }
            mclip.destroyThumbnailManager();
            if (!flag1)
            {
                Bitmap bitmap1;
                try
                {
                    Bitmap bitmap = MAndroidBitmapFactory.createBitmapFromMBitmap(mbitmap, false);
                    if (!mbitmap.isRecycled())
                    {
                        mbitmap.recycle();
                    }
                    bitmap1 = decodeBitmapByRect(bitmap, new Rect(0, 0, 320, 240), new Rect(0, 0, j, k));
                }
                catch (OutOfMemoryError outofmemoryerror)
                {
                    outofmemoryerror.printStackTrace();
                    return null;
                }
                catch (IllegalArgumentException illegalargumentexception)
                {
                    illegalargumentexception.printStackTrace();
                    return null;
                }
                return bitmap1;
            }
        }
        return mbitmap;
    }

    public static int getVideoTimeByFileName(String s)
    {
        if (!IsFileExisted(s))
        {
            return -1;
        }
        int i;
        try
        {
            MediaMetadataRetriever mediametadataretriever = new MediaMetadataRetriever();
            mediametadataretriever.setDataSource(s);
            i = Integer.parseInt(mediametadataretriever.extractMetadata(9));
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static void shareVideoByUri(Uri uri, Activity activity)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.setType("video/*");
        activity.startActivity(Intent.createChooser(intent, activity.getString(0x7f0b011d)));
    }
}
