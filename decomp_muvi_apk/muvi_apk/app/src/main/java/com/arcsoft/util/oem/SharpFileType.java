// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.oem;

import java.util.HashMap;

public class SharpFileType
{

    public static final int FILE_TYPE_3GPP = 24;
    public static final int FILE_TYPE_3GPP2 = 25;
    public static final int FILE_TYPE_ADTS = 6;
    public static final int FILE_TYPE_ASF = 27;
    public static final int FILE_TYPE_AVI = 28;
    public static final int FILE_TYPE_BMP = 57;
    public static final int FILE_TYPE_DIVX = 29;
    public static final int FILE_TYPE_GIF = 55;
    public static final int FILE_TYPE_JP2 = 54;
    public static final int FILE_TYPE_JPE = 53;
    public static final int FILE_TYPE_JPEG = 52;
    public static final int FILE_TYPE_JPG = 51;
    public static final int FILE_TYPE_M2TS = 32;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_MOV = 34;
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_MPEG = 23;
    public static final int FILE_TYPE_MPG = 22;
    public static final int FILE_TYPE_NOT_SUPPORT = 0;
    public static final int FILE_TYPE_PCM = 5;
    public static final int FILE_TYPE_PNG = 56;
    public static final int FILE_TYPE_THM = 58;
    public static final int FILE_TYPE_TIFF = 59;
    public static final int FILE_TYPE_TS = 31;
    public static final int FILE_TYPE_TTS = 33;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_WMA = 4;
    public static final int FILE_TYPE_WMV = 26;
    public static final int FILE_TYPE_XVID = 30;
    private static final int FIRST_AUDIO_FILE_TYPE = 1;
    private static final int FIRST_IMAGE_FILE_TYPE = 52;
    private static final int FIRST_VIDEO_FILE_TYPE = 21;
    private static final int LAST_AUDIO_FILE_TYPE = 6;
    private static final int LAST_IMAGE_FILE_TYPE = 59;
    private static final int LAST_VIDEO_FILE_TYPE = 34;
    private static HashMap sFileTypeMap;

    public SharpFileType()
    {
    }

    public static int getFileType(String s)
    {
        int i;
        Integer integer;
        if (s != null)
        {
            if ((i = s.lastIndexOf(".")) >= 0 && (integer = (Integer)sFileTypeMap.get(s.substring(i + 1).toLowerCase())) != null)
            {
                return integer.intValue();
            }
        }
        return 0;
    }

    static 
    {
        sFileTypeMap = new HashMap();
        sFileTypeMap.put("mp3", Integer.valueOf(1));
        sFileTypeMap.put("m4a", Integer.valueOf(2));
        sFileTypeMap.put("wav", Integer.valueOf(3));
        sFileTypeMap.put("wma", Integer.valueOf(4));
        sFileTypeMap.put("pcm", Integer.valueOf(5));
        sFileTypeMap.put("adts", Integer.valueOf(6));
        sFileTypeMap.put("mp4", Integer.valueOf(21));
        sFileTypeMap.put("mpg", Integer.valueOf(22));
        sFileTypeMap.put("mpeg", Integer.valueOf(23));
        sFileTypeMap.put("3gp", Integer.valueOf(24));
        sFileTypeMap.put("3gpp", Integer.valueOf(24));
        sFileTypeMap.put("3g2", Integer.valueOf(25));
        sFileTypeMap.put("3gpp2", Integer.valueOf(25));
        sFileTypeMap.put("wmv", Integer.valueOf(26));
        sFileTypeMap.put("asf", Integer.valueOf(27));
        sFileTypeMap.put("avi", Integer.valueOf(28));
        sFileTypeMap.put("divx", Integer.valueOf(29));
        sFileTypeMap.put("xvid", Integer.valueOf(30));
        sFileTypeMap.put("ts", Integer.valueOf(31));
        sFileTypeMap.put("m2ts", Integer.valueOf(32));
        sFileTypeMap.put("tts", Integer.valueOf(33));
        sFileTypeMap.put("mov", Integer.valueOf(34));
        sFileTypeMap.put("jpg", Integer.valueOf(51));
        sFileTypeMap.put("jpeg", Integer.valueOf(52));
        sFileTypeMap.put("jpe", Integer.valueOf(53));
        sFileTypeMap.put("jp2", Integer.valueOf(54));
        sFileTypeMap.put("gif", Integer.valueOf(55));
        sFileTypeMap.put("png", Integer.valueOf(56));
        sFileTypeMap.put("bmp", Integer.valueOf(57));
        sFileTypeMap.put("thm", Integer.valueOf(58));
        sFileTypeMap.put("tiff", Integer.valueOf(59));
    }
}
