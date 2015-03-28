// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer.audiofx;

import com.arcsoft.MediaPlayer.ArcMediaPlayer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AudioEffect
{

    public static final int AUDIO_EFFECT_ID = 0;
    public static final int EQUALIZER_ID = 1;
    public static final int EQUALIZER_PARAM_BASE = 4096;
    public static final int STATUS_ERRORALREADY_EXISTS = -2;
    public static final int STATUS_ERROR_ = -1;
    public static final int STATUS_ERROR_BAD_VALUE = -4;
    public static final int STATUS_ERROR_INVALID_OPERATION = -5;
    public static final int STATUS_ERROR_NOTINIT = -3;
    public static final int STATUS_SUCCESS = 0;
    public static final int STEREOWIDENING_ID = 2;
    public static final int STEREOWIDENING_PARAM_BASE = 8192;
    private ArcMediaPlayer mPlayer;

    public AudioEffect(ArcMediaPlayer arcmediaplayer)
    {
        mPlayer = arcmediaplayer;
    }

    private int byteArrayToInt(byte abyte0[], int i)
    {
        ByteBuffer bytebuffer = ByteBuffer.wrap(abyte0);
        bytebuffer.order(ByteOrder.nativeOrder());
        return bytebuffer.getInt(i);
    }

    private byte[] intToByteArray(int i)
    {
        ByteBuffer bytebuffer = ByteBuffer.allocate(4);
        bytebuffer.order(ByteOrder.nativeOrder());
        bytebuffer.putInt(i);
        return bytebuffer.array();
    }

    protected void checkStatus(int i)
    {
        switch (i)
        {
        case -3: 
        case -2: 
        case -1: 
        default:
            throw new UnsupportedOperationException("set/get parameter error");

        case -4: 
            throw new IllegalArgumentException(" bad parameter value");

        case -5: 
            throw new UnsupportedOperationException("invalid parameter operation");

        case 0: // '\0'
            return;
        }
    }

    protected int getAudioEffectParam(int i, int ai[], byte abyte0[])
    {
        while (mPlayer == null || abyte0 == null) 
        {
            return -5;
        }
        return mPlayer.getAudioEffectParam(i, ai, abyte0);
    }

    protected int getAudioEffectParam(int i, int ai[], int ai1[])
    {
        int j;
        if (mPlayer == null)
        {
            j = -5;
        } else
        {
            if (ai1 == null || ai1.length <= 0)
            {
                return -4;
            }
            byte abyte0[] = new byte[ai1.length << 2];
            j = mPlayer.getAudioEffectParam(i, ai, abyte0);
            int k = 0;
            while (k < ai1.length) 
            {
                ai1[k] = byteArrayToInt(abyte0, k << 2);
                k++;
            }
        }
        return j;
    }

    protected int setAudioEffectParam(int i, int ai[])
    {
        while (mPlayer == null || ai == null || ai.length <= 0) 
        {
            return -5;
        }
        return mPlayer.setAudioEffectParam(i, ai);
    }
}
