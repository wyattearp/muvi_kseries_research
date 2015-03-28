// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import java.io.IOException;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            DLNAPlayer

public class SecureMediaPlayer extends DLNAPlayer
{

    public static int MEDIA_PLAYER_DTCPIP_PARAM_BASE = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_CLEARSIZE = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_DTCPHOST = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_DTCPPORT = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_DURATION = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_PRIVATE_PATH = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_SEEKTIME = 0;
    public static int MEDIA_PLAYER_DTCPIP_PARAM_SIZE = 0;
    private static final String TAG = "SecureStreamingPlayer";
    private Long mClearSize;
    private String mDtcpIpHost;
    private int mDtcpIpPort;
    private String mPrivatePath;
    private int mSeekTime;
    private String mTitle;

    public SecureMediaPlayer()
    {
        mDtcpIpPort = 0;
        mClearSize = Long.valueOf(0L);
        mSeekTime = 0;
    }

    public boolean isMultiLanguage()
    {
        return super.isMultiLanguage();
    }

    public void prepare()
        throws IOException, IllegalStateException
    {
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_DURATION, mDuration);
        setParamLong(MEDIA_PLAYER_DTCPIP_PARAM_SIZE, mSize.longValue());
        setParamLong(MEDIA_PLAYER_DTCPIP_PARAM_CLEARSIZE, mClearSize.longValue());
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_DTCPPORT, mDtcpIpPort);
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_SEEKTIME, mSeekTime);
        if (mDtcpIpHost != null)
        {
            setParamString(MEDIA_PLAYER_DTCPIP_PARAM_DTCPHOST, mDtcpIpHost);
        }
        if (mPrivatePath != null)
        {
            setParamString(MEDIA_PLAYER_DTCPIP_PARAM_PRIVATE_PATH, mPrivatePath);
        }
        super.prepare();
    }

    public void prepareAsync()
        throws IllegalStateException
    {
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_DURATION, mDuration);
        setParamLong(MEDIA_PLAYER_DTCPIP_PARAM_SIZE, mSize.longValue());
        setParamLong(MEDIA_PLAYER_DTCPIP_PARAM_CLEARSIZE, mClearSize.longValue());
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_DTCPPORT, mDtcpIpPort);
        setParamInt(MEDIA_PLAYER_DTCPIP_PARAM_SEEKTIME, mSeekTime);
        if (mDtcpIpHost != null)
        {
            setParamString(MEDIA_PLAYER_DTCPIP_PARAM_DTCPHOST, mDtcpIpHost);
        }
        if (mPrivatePath != null)
        {
            setParamString(MEDIA_PLAYER_DTCPIP_PARAM_PRIVATE_PATH, mPrivatePath);
        }
        super.prepareAsync();
    }

    public void selectAudioChannel(int i)
    {
        super.selectAudioChannel(i);
    }

    public void setClearSize(Long long1)
    {
        mClearSize = long1;
    }

    public void setContentFormat(String s)
    {
        int i = s.indexOf("DTCP1HOST");
        if (i != -1)
        {
            mDtcpIpHost = s.substring(i, i + s.substring(i).indexOf(";")).substring(10);
        }
        int j = s.indexOf("DTCP1PORT");
        if (j != -1)
        {
            mDtcpIpPort = Integer.valueOf(s.substring(j, j + s.substring(j).indexOf(";")).substring(10)).intValue();
        }
    }

    public void setPrivatePath(String s)
    {
        mPrivatePath = s;
    }

    public void setSeekTime(int i)
    {
        mSeekTime = i;
    }

    public void setTitle(String s)
    {
        mTitle = s;
    }

    public void setTransferMode(String s)
    {
    }

    static 
    {
        MEDIA_PLAYER_DTCPIP_PARAM_BASE = MEDIA_PLAYER_DLNA_EXTENSION_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_DURATION = 1 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_SIZE = 2 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_CLEARSIZE = 3 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_DTCPHOST = 4 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_DTCPPORT = 5 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_PRIVATE_PATH = 6 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
        MEDIA_PLAYER_DTCPIP_PARAM_SEEKTIME = 7 + MEDIA_PLAYER_DTCPIP_PARAM_BASE;
    }
}
