// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MediaFileUtils
{
    static class MediaFileType
    {

        private final int m_iFileType;
        private final String m_strMimeType;

        public int GetFileType()
        {
            return m_iFileType;
        }

        public String GetMimeType()
        {
            return m_strMimeType;
        }


        MediaFileType(int i, String s)
        {
            m_iFileType = i;
            m_strMimeType = s;
        }
    }


    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_AAC = 8;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_ASF = 29;
    public static final int FILE_TYPE_AVI = 28;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_IMY = 13;
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_K3G = 27;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_MID = 11;
    public static final int FILE_TYPE_MOV = 30;
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_SKM = 26;
    public static final int FILE_TYPE_SMF = 12;
    public static final int FILE_TYPE_UNKNOWN = 0;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_WBMP = 35;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_WMV = 25;
    private static final int FIRST_AUDIO_FILE_TYPE = 1;
    private static final int FIRST_IMAGE_FILE_TYPE = 31;
    private static final int FIRST_MIDI_FILE_TYPE = 11;
    private static final int FIRST_VIDEO_FILE_TYPE = 21;
    private static final int LAST_AUDIO_FILE_TYPE = 8;
    private static final int LAST_IMAGE_FILE_TYPE = 35;
    private static final int LAST_MIDI_FILE_TYPE = 13;
    private static final int LAST_VIDEO_FILE_TYPE = 30;
    private static MediaFileUtils m_MediaFileUtils = null;
    private HashMap s_FileTypeMap;
    public String s_strFileExtFilter;

    private MediaFileUtils()
    {
        s_FileTypeMap = null;
        s_FileTypeMap = new HashMap();
        InitMyType();
    }

    private void InitMyType()
    {
        addFileType("MP3", 1, "audio/mpeg");
        addFileType("M4A", 2, "audio/mp4");
        addFileType("WAV", 3, "audio/x-wav");
        addFileType("AMR", 4, "audio/amr");
        addFileType("AWB", 5, "audio/amr-wb");
        addFileType("WMA", 6, "audio/x-ms-wma");
        addFileType("OGG", 7, "application/ogg");
        addFileType("OGA", 7, "application/ogg");
        addFileType("AAC", 8, "audio/aac");
        addFileType("MID", 11, "audio/midi");
        addFileType("MIDI", 11, "audio/midi");
        addFileType("XMF", 11, "audio/midi");
        addFileType("RTTTL", 11, "audio/midi");
        addFileType("SMF", 12, "audio/sp-midi");
        addFileType("IMY", 13, "audio/imelody");
        addFileType("RTX", 11, "audio/midi");
        addFileType("OTA", 11, "audio/midi");
        addFileType("MP4", 21, "video/mp4");
        addFileType("M4V", 22, "video/mp4");
        addFileType("3GP", 23, "video/3gpp");
        addFileType("3GPP", 23, "video/3gpp");
        addFileType("3G2", 24, "video/3gpp2");
        addFileType("3GPP2", 24, "video/3gpp2");
        addFileType("WMV", 25, "video/x-ms-wmv");
        addFileType("SKM", 26, "video/skm");
        addFileType("K3G", 27, "video/k3g");
        addFileType("AVI", 28, "video/avi");
        addFileType("ASF", 29, "video/asf");
        addFileType("MOV", 30, "video/quicktime");
        addFileType("JPG", 31, "image/jpeg");
        addFileType("JPEG", 31, "image/jpeg");
        addFileType("GIF", 32, "image/gif");
        addFileType("PNG", 33, "image/png");
        addFileType("BMP", 34, "image/x-ms-bmp");
        addFileType("WBMP", 35, "image/vnd.wap.wbmp");
        StringBuilder stringbuilder = new StringBuilder();
        for (Iterator iterator = s_FileTypeMap.keySet().iterator(); iterator.hasNext(); stringbuilder.append((String)iterator.next()))
        {
            if (stringbuilder.length() > 0)
            {
                stringbuilder.append(',');
            }
        }

        s_strFileExtFilter = stringbuilder.toString();
    }

    private void addFileType(String s, int i, String s1)
    {
        s_FileTypeMap.put(s, new MediaFileType(i, s1));
    }

    public static MediaFileUtils getMediaFileUtils()
    {
        if (m_MediaFileUtils == null)
        {
            m_MediaFileUtils = new MediaFileUtils();
        }
        return m_MediaFileUtils;
    }

    public int GetFileMediaType(String s)
    {
        int i = s.lastIndexOf(".");
        MediaFileType mediafiletype;
        if (i >= 0)
        {
            if ((mediafiletype = (MediaFileType)s_FileTypeMap.get(s.substring(i + 1).toUpperCase())) != null)
            {
                return mediafiletype.m_iFileType;
            }
        }
        return 0;
    }

    public boolean IsAudioFileType(int i)
    {
        return i >= 1 && i <= 8 || i >= 11 && i <= 13;
    }

    public boolean IsImageFileType(int i)
    {
        return i >= 31 && i <= 35;
    }

    public boolean IsVideoFileType(int i)
    {
        return i >= 21 && i <= 30;
    }

}
