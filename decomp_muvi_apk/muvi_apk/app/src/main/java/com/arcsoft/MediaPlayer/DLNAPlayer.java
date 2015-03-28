// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.util.Log;
import java.sql.Time;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            ArcMediaPlayer

public class DLNAPlayer extends ArcMediaPlayer
{

    public static int MEDIA_PLAYER_DLNA_EXTENSION_BASE = 0;
    public static int MEDIA_PLAYER_DLNA_PARAM_SEEKFLAG = 0;
    private static final String TAG = "DLNAPlayer";
    private static final int mNativeMethodCount = 3;
    protected boolean mAvailableConnectionStalling;
    protected int mDuration;
    protected Long mSize;
    protected boolean mSupportByteSeek;
    protected boolean mSupportTimeSeek;

    public DLNAPlayer()
    {
        mSupportTimeSeek = false;
        mSupportByteSeek = true;
        mAvailableConnectionStalling = true;
        mSize = Long.valueOf(0L);
        mDuration = 0;
    }

    protected static int TimeStringToSecond(String s)
    {
        if (s == null)
        {
            return 0;
        }
        if (s.contains("."))
        {
            s = s.substring(0, s.indexOf('.', 0));
        }
        Time time = Time.valueOf(s);
        return 3600 * time.getHours() + 60 * time.getMinutes() + time.getSeconds();
    }

    private native void _setParamInt(int i, int j);

    private native void _setParamLong(int i, long l);

    private native void _setParamString(int i, String s);

    public void setDuration(String s)
    {
        mDuration = TimeStringToSecond(s);
    }

    protected void setParamInt(int i, int j)
    {
        _setParamInt(i, j);
    }

    protected void setParamLong(int i, long l)
    {
        _setParamLong(i, l);
    }

    protected void setParamString(int i, String s)
    {
        _setParamString(i, s);
    }

    public void setSeekFlag(boolean flag, boolean flag1, boolean flag2)
    {
        mSupportTimeSeek = flag1;
        mSupportByteSeek = flag;
        mAvailableConnectionStalling = flag2;
        byte byte0;
        int i;
        int j;
        boolean flag3;
        byte byte1;
        int k;
        if (mSupportTimeSeek)
        {
            byte0 = 2;
        } else
        {
            byte0 = 0;
        }
        if (mSupportByteSeek)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = byte0 + i;
        flag3 = mAvailableConnectionStalling;
        byte1 = 0;
        if (flag3)
        {
            byte1 = 4;
        }
        k = j + byte1;
        setParamInt(MEDIA_PLAYER_DLNA_PARAM_SEEKFLAG, k);
    }

    public void setSize(Long long1)
    {
        mSize = long1;
    }

    static 
    {
        MEDIA_PLAYER_DLNA_PARAM_SEEKFLAG = 1;
        MEDIA_PLAYER_DLNA_EXTENSION_BASE = 1000;
        Log.v("DLNAPlayer", "welcome to DLNAPlayer");
    }
}
