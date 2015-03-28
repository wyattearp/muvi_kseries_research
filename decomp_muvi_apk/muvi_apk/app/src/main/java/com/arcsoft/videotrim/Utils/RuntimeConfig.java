// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;


public class RuntimeConfig
{

    public static int AUDIOFORMAT = 0;
    public static final boolean DEBUG = true;
    public static final long DEFAULT_OUTPUT_BITRATE_720P = 0x600000L;
    public static final long DEFAULT_OUTPUT_BITRATE_DONE = 0x1a5e00L;
    public static final long DEFAULT_OUTPUT_BITRATE_QCIF = 0x3e800L;
    public static final long DEFAULT_OUTPUT_BITRATE_QVGA = 0x5dc00L;
    public static final long DEFAULT_OUTPUT_BITRATE_VGA = 0x177000L;
    public static final long DEFAULT_OUTPUT_FRAME_RATE = 30000L;
    public static long FILESIZE_LIMIT = 0L;
    public static int FILE_FORMAT = 0;
    public static boolean IFRAME_SOLUTION = false;
    public static int OUTPUT_RESOL_HEIGHT = 0;
    public static int OUTPUT_RESOL_WIDTH = 0;
    public static final String STR_FONT_TTF = "fonts/DS-DIGI.TTF";
    public static final int TRIM_DECODE_THUMBNAIL_HEIGHT = 240;
    public static final int TRIM_DECODE_THUMBNAIL_WIDTH = 320;
    public static int VIDEOFORMAT = 4;

    public RuntimeConfig()
    {
    }

    static 
    {
        FILESIZE_LIMIT = 0L;
        FILE_FORMAT = 1;
        AUDIOFORMAT = 1;
        OUTPUT_RESOL_WIDTH = 0;
        OUTPUT_RESOL_HEIGHT = 0;
        IFRAME_SOLUTION = false;
    }
}
