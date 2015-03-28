// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public class MediaFile
{
    public static class MediaFileType
    {

        public String maintype;
        public String subType;

        public MediaFileType()
        {
            maintype = "unknown";
            subType = "unknown";
        }
    }


    public static final String FILE_MAIN_TYPE_AUDIO = "audio";
    public static final String FILE_MAIN_TYPE_IMAGE = "image";
    public static final String FILE_MAIN_TYPE_UNKNOWN = "unknown";
    public static final String FILE_MAIN_TYPE_VIDEO = "video";
    static final String FILE_SUB_TYPE_UNKNOWN = "unknown";

    public MediaFile()
    {
    }

    public static MediaFileType GetFileTypeFromProtocol(String s)
    {
        MediaFileType mediafiletype = new MediaFileType();
        int i;
        if (s != null)
        {
            if ((i = s.indexOf("http-get:*:")) >= 0)
            {
                int j = i + "http-get:*:".length();
                ParseMimeType(s.substring(j, s.indexOf(':', j)), mediafiletype);
                return mediafiletype;
            }
        }
        return mediafiletype;
    }

    public static MediaFileType ParseMimeType(String s)
    {
        MediaFileType mediafiletype = new MediaFileType();
        ParseMimeType(s, mediafiletype);
        return mediafiletype;
    }

    private static void ParseMimeType(String s, MediaFileType mediafiletype)
    {
        if (mediafiletype != null)
        {
            mediafiletype.maintype = "unknown";
            mediafiletype.subType = "unknown";
            if (s != null)
            {
                int i = s.indexOf('/');
                if (i >= 0)
                {
                    mediafiletype.maintype = s.substring(0, i);
                    mediafiletype.subType = s.substring(i + 1);
                    return;
                }
            }
        }
    }
}
