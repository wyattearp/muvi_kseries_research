// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import java.io.IOException;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            ArcMediaPlayer

public class WFDMediaPlayer extends ArcMediaPlayer
{

    public static final int AUDIO_BITSPERSAMPLE_16 = 16;
    public static final int AUDIO_BITSPERSAMPLE_8 = 8;
    public static final int AUDIO_CHANNEL_MONO = 1;
    public static final int AUDIO_CHANNEL_STEREO = 2;
    public static final int AUDIO_CODEC_AAC = 0x61616320;
    private static final String Tag = "WFDMediaPlayer";
    public static final int VIDEO_CODEC_H264 = 0x32363420;
    public static final int WFD_PLAYER_WFDSINK_EXTENSION_BASE = 3000;
    public static final int WFD_PLAYER_WFDSINK_PARAM_HDCPPORT = 3004;
    public static final int WFD_PLAYER_WFDSINK_PARAM_IPADDRESS = 3005;
    public static final int WFD_PLAYER_WFDSINK_PARAM_MAXPORT = 3002;
    public static final int WFD_PLAYER_WFDSINK_PARAM_MINPORT = 3001;
    public static final int WFD_PLAYER_WFDSINK_PARAM_RETRYCNT = 3003;
    private static final int mNativeMethodCount = 5;
    private int mHdcpPort;
    private int mMaxPort;
    private int mMinPort;
    private int mRetryCnt;
    private String mSinkIp;

    public WFDMediaPlayer()
    {
        mMinPort = 0;
        mMaxPort = 0;
        mRetryCnt = 0;
        mHdcpPort = 0;
    }

    private native int _getRTPBindPort();

    private native void _setAudioInfo(int i, int j, int k, int l);

    private native void _setParamInt(int i, int j);

    private native void _setParamString(int i, String s);

    private native void _setVideoInfo(int i, int j, int k);

    public int getRTPBindPort()
    {
        return _getRTPBindPort();
    }

    public boolean isMultiLanguage()
    {
        return super.isMultiLanguage();
    }

    public void prepare()
        throws IOException, IllegalStateException
    {
        setParamInt(3001, mMinPort);
        setParamInt(3002, mMaxPort);
        setParamInt(3003, mRetryCnt);
        setParamInt(3004, mHdcpPort);
        if (mSinkIp != null)
        {
            setParamString(3005, mSinkIp);
        }
        super.prepare();
    }

    public void prepareAsync()
        throws IllegalStateException
    {
        setParamInt(3001, mMinPort);
        setParamInt(3002, mMaxPort);
        setParamInt(3003, mRetryCnt);
        setParamInt(3004, mHdcpPort);
        if (mSinkIp != null)
        {
            setParamString(3005, mSinkIp);
        }
        super.prepareAsync();
    }

    public void selectAudioChannel(int i)
    {
        super.selectAudioChannel(i);
    }

    public void setAudioInfo(int i, int j, int k, int l)
    {
        _setAudioInfo(i, j, k, l);
    }

    public void setHdcpPort(int i)
    {
        mHdcpPort = i;
    }

    public void setParamInt(int i, int j)
    {
        _setParamInt(i, j);
    }

    public void setParamString(int i, String s)
    {
        _setParamString(i, s);
    }

    public void setRtpPortParams(int i, int j, int k)
    {
        mMinPort = i;
        mMaxPort = j;
        mRetryCnt = k;
    }

    public void setSinkIp(String s)
    {
        mSinkIp = s;
    }

    public void setVideoInfo(int i, int j, int k)
    {
        _setVideoInfo(i, j, k);
    }
}
