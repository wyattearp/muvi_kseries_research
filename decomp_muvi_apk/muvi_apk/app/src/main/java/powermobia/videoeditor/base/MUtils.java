// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;

import powermobia.ve.utils.MBitmap;
import powermobia.ve.utils.MBitmapFactory;
import powermobia.videoeditor.MEngine;
import powermobia.videoeditor.clip.MMediaSource;

// Referenced classes of package powermobia.videoeditor.base:
//            MBubbleTextSource, MVideoInfo

public class MUtils
{

    public static final int CHECK_ALLOW_UNSEEKABLE = 0x20000;
    public static final int CHECK_AUDIO = 1;
    public static final int CHECK_BASE = 0;
    public static final int CHECK_NO_AUDIO_TRACK = 2;
    public static final int CHECK_VIDEO = 0x10000;
    public static final int UNSUPPORTED_ACODEC = 4;
    public static final int UNSUPPORTED_FILE = 1;
    public static final int UNSUPPORTED_IMAGECODEC = 5;
    public static final int UNSUPPORTED_MPEG4_HEADER = 8;
    public static final int UNSUPPORTED_NOAUDIO = 7;
    public static final int UNSUPPORTED_NONE = 0;
    public static final int UNSUPPORTED_NOVIDEO = 6;
    public static final int UNSUPPORTED_RESOLUTION = 2;
    public static final int UNSUPPORTED_VCODEC = 3;
    public static final int UNSUPPORT_H264_HEADER = 10;
    public static final int UNSUPPORT_UNSEEKABLE = 9;

    public MUtils()
    {
    }

    public static int ReleaseAllHWDecoder(MEngine mengine)
    {
        return nativeReleaseAllHWDecoder(mengine);
    }

    public static int SetEnableHWDecoderPool(MEngine mengine, boolean flag)
    {
        return nativeSetEnableHWDecoderPool(mengine, flag);
    }

    public static int generateSVGFile(String s, String s1, String s2, String s3, int i, int j)
    {
        return nativeGenerateSVGFile(s, s1, s2, s3, i, j);
    }

    public static MBitmap getSVGThumbnail(MEngine mengine, MBubbleTextSource mbubbletextsource, int i, int j, int k, int l, int i1)
    {
        MBitmap mbitmap = MBitmapFactory.createMBitmapBlank(j, k, i);
        if (mbitmap == null)
        {
            mbitmap = null;
        } else
        if (nativeGetSVGThumbnail(mengine, mbitmap, mbubbletextsource, l, i1) != 0)
        {
            mbitmap.recycle();
            return null;
        }
        return mbitmap;
    }

    public static int getThemeCover(MEngine mengine, String s, MMediaSource ammediasource[], int i, int j, String s1)
    {
        return nativeGetThemeCover(mengine, s, ammediasource, i, j, s1);
    }

    public static MVideoInfo getVideoInfo(MEngine mengine, String s)
    {
        return nativeGetVideoInfo(mengine, s);
    }

    public static int isFileEditable(MEngine mengine, String s, int i)
    {
        return nativeFileEditable(mengine, s, i);
    }

    private static native int nativeFileEditable(MEngine mengine, String s, int i);

    private static native int nativeGenerateSVGFile(String s, String s1, String s2, String s3, int i, int j);

    private static native int nativeGetSVGThumbnail(MEngine mengine, MBitmap mbitmap, MBubbleTextSource mbubbletextsource, int i, int j);

    private static native int nativeGetThemeCover(MEngine mengine, String s, MMediaSource ammediasource[], int i, int j, String s1);

    private static native MVideoInfo nativeGetVideoInfo(MEngine mengine, String s);

    private static native int nativeReleaseAllHWDecoder(MEngine mengine);

    private static native int nativeSetEnableHWDecoderPool(MEngine mengine, boolean flag);
}
