// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.rtsp;


public class RtspUtils
{

    private static final String ambarStrUrl = "rtsp://192.168.42.1/live";
    private static String strUrl = "rtsp://192.168.42.1/live";

    public RtspUtils()
    {
    }

    public static void changeRtspUrl(String s)
    {
        strUrl = s;
    }

    public static String getRtspUrl()
    {
        return strUrl;
    }

    public static boolean isAmbar()
    {
        return strUrl.equals("rtsp://192.168.42.1/live");
    }

}
