// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;


public class MimeTypeUtils
{

    public static final String EXTENSION_MAP_MIMETYPE[][] = {
        {
            "gif", "image/gif"
        }, {
            "jpg", "image/jpeg"
        }, {
            "jpeg", "image/jpeg"
        }, {
            "jpe", "image/jpeg"
        }, {
            "jp2", "image/jp2"
        }, {
            "thm", "image/jpeg"
        }, {
            "png", "image/png"
        }, {
            "bmp", "image/bmp"
        }, {
            "tiff", "image/tiff"
        }, {
            "wbmp", "image/vnd.wap.wbmp"
        }, {
            "rgb", "image/x-rgb"
        }, {
            "svg", "image/svg+xml"
        }, {
            "dib", "image/bmp"
        }, {
            "jfif", "image/pjpeg"
        }, {
            "tif", "image/tiff"
        }, {
            "mid", "audio/mid"
        }, {
            "midi", "audio/midi"
        }, {
            "au", "audio/basic"
        }, {
            "snd", "audio/basic"
        }, {
            "m3u", "audio/x-mpegurl"
        }, {
            "rmi", "audio/mid"
        }, {
            "mp3", "audio/mpeg"
        }, {
            "m4a", "audio/mp4"
        }, {
            "wma", "audio/x-ms-wma"
        }, {
            "wav", "audio/x-wav"
        }, {
            "pcm", "audio/l16"
        }, {
            "adts", "audio/vnd.dlna.adts"
        }, {
            "avi", "video/mpeg"
        }, {
            "divx", "video/avi"
        }, {
            "xvid", "video/avi"
        }, {
            "mp4", "video/mp4"
        }, {
            "m4v", "video/mp4"
        }, {
            "mpg", "video/mpeg"
        }, {
            "mpeg", "video/mpeg"
        }, {
            "mpe", "video/mpeg"
        }, {
            "wmv", "video/x-ms-wmv"
        }, {
            "asf", "video/x-ms-asf"
        }, {
            "3gp", "video/3gpp"
        }, {
            "3gpp", "video/3gpp"
        }, {
            "3g2", "video/3gpp2"
        }, {
            "3gpp2", "video/3gpp2"
        }, {
            "ts", "video/vnd.dlna.mpeg-tts"
        }, {
            "m2ts", "video/vnd.dlna.mpeg-tts"
        }, {
            "tts", "video/vnd.dlna.mpeg-tts"
        }, {
            "rmvb", "video/vnd.rn-realvideo"
        }, {
            "mov", "video/quicktime"
        }, {
            "viv", "video/vnd.vivo"
        }, {
            "vivo", "video/vnd.vivo"
        }, {
            "movie", "video/x-sgi-movie"
        }
    };
    public static final String EXTENSION_UNKNOWN = "";
    public static final String MIMETYPE_MAP_EXTENSION[][] = {
        {
            "image/gif", "gif"
        }, {
            "image/jpeg", "jpg"
        }, {
            "image/png", "png"
        }, {
            "image/bmp", "bmp"
        }, {
            "image/tiff", "tiff"
        }, {
            "image/pjpeg", "jfif"
        }, {
            "image/vnd.wap.wbmp", "wbmp"
        }, {
            "image/x-rgb", "rgb"
        }, {
            "image/svg+xml", "svg"
        }, {
            "audio/mpeg", "mp3"
        }, {
            "audio/mp4", "m4a"
        }, {
            "audio/3gpp", "3gp"
        }, {
            "audio/x-ms-wma", "wma"
        }, {
            "audio/x-wav", "wav"
        }, {
            "audio/l16", "pcm"
        }, {
            "audio/vnd.dlna.adts", "adts"
        }, {
            "audio/basic", "snd"
        }, {
            "audio/mid", "mid"
        }, {
            "audio/midi", "mid"
        }, {
            "audio/x-mid", "mid"
        }, {
            "audio/x-midi", "mid"
        }, {
            "audio/x-mpeg", "mp2"
        }, {
            "video/avi", "divx"
        }, {
            "video/mp4", "mp4"
        }, {
            "video/mpeg", "mpeg"
        }, {
            "video/x-ms-wmv", "wmv"
        }, {
            "video/x-ms-asf", "asf"
        }, {
            "video/3gpp", "3gp"
        }, {
            "video/3gpp2", "3g2"
        }, {
            "video/vnd.dlna.mpeg-tts", "tts"
        }, {
            "video/vnd.rn-realvideo", "rmvb"
        }, {
            "video/quicktime", "mov"
        }, {
            "video/vnd.vivo", "vivo"
        }, {
            "video/x-msvideo", "avi"
        }, {
            "video/x-sgi-movie", "movie"
        }, {
            "application/ogg", "ogg"
        }
    };

    public MimeTypeUtils()
    {
    }

    public static String getExtensionMapMimeType(String s)
    {
        if (s != null)
        {
            String as[][] = MIMETYPE_MAP_EXTENSION;
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String as1[] = as[j];
                if (s.equalsIgnoreCase(as1[0]))
                {
                    return as1[1];
                }
                j++;
            }
        }
        return null;
    }

    public static String getMimeTypeMapExtension(String s)
    {
        if (s != null)
        {
            String as[][] = EXTENSION_MAP_MIMETYPE;
            int i = as.length;
            int j = 0;
            while (j < i) 
            {
                String as1[] = as[j];
                if (s.equalsIgnoreCase(as1[0]))
                {
                    return as1[1];
                }
                j++;
            }
        }
        return null;
    }

}
