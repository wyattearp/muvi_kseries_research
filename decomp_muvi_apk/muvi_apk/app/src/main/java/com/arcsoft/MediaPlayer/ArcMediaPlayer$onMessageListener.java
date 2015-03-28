// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.media.MediaPlayer;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            ArcMediaPlayer

public static interface 
{

    public static final int MESSAGE_INFO_ACODEC_DECODE_ERROR = 12293;
    public static final int MESSAGE_INFO_ACODEC_UNSUPPORTAUDIO = 20492;
    public static final int MESSAGE_INFO_AUDIOOUTPUT_FAIL = 28723;
    public static final int MESSAGE_INFO_AVCODEC_UNSUPPORT = 32773;
    public static final int MESSAGE_INFO_DISPLAY_FAIL = 28673;
    public static final int MESSAGE_INFO_HDCP_AKE_FAILED = 32778;
    public static final int MESSAGE_INFO_HDCP_AKE_SUCC = 32777;
    public static final int MESSAGE_INFO_NOAUDIO_UPSUPPORTVIDEO = 32771;
    public static final int MESSAGE_INFO_NOVIDEO_UPSUPPORTAUDIO = 32772;
    public static final int MESSAGE_INFO_RTP_PORT_READY = 32779;
    public static final int MESSAGE_INFO_SPLITTER_AUDIO_END = 12290;
    public static final int MESSAGE_INFO_SPLITTER_HTTP_NETWORK = 18;
    public static final int MESSAGE_INFO_SPLITTER_NOAUDIO = 32770;
    public static final int MESSAGE_INFO_SPLITTER_NOVIDEO = 32769;
    public static final int MESSAGE_INFO_SPLITTER_UNSUPPORT_FORMAT = 16390;
    public static final int MESSAGE_INFO_SPLITTER_VIDEO_END = 12289;
    public static final int MESSAGE_INFO_UNKNOWN = 65535;
    public static final int MESSAGE_INFO_VCODEC_DECODE_ERROR = 12297;
    public static final int MESSAGE_INFO_VCODEC_UNSUPPORTVIDEO = 20491;
    public static final int MESSAGE_LEVEL_ERROR = 259;
    public static final int MESSAGE_LEVEL_NONE = 257;
    public static final int MESSAGE_LEVEL_USERDEFINED = 260;
    public static final int MESSAGE_LEVEL_WARNING = 258;
    public static final int MV2_MESSAGE_INFO_SPLITTER_SEEK_UNAVAILABLE = 32774;
    public static final int MV2_MESSAGE_INFO_VCODEC_HW2SW = 32775;

    public abstract boolean onMessage(MediaPlayer mediaplayer, int i, int j);
}
