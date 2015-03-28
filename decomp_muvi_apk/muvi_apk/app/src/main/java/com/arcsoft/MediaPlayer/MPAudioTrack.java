// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.media.AudioTrack;
import android.util.Log;

// Referenced classes of package com.arcsoft.MediaPlayer:
//            IAudioSink

public class MPAudioTrack
{

    private static final String TAG = "MPAudioTrack";
    private byte mAudioBuffer[];
    private IAudioSink mAudioSink;
    private AudioTrack mAudioTrack;
    private int m_nBitsPerSample;
    private int m_nBufferSize;
    private int m_nChannelNumber;
    private int m_nSampleRateInHz;

    private MPAudioTrack()
    {
        mAudioTrack = null;
        mAudioBuffer = null;
        mAudioSink = null;
        m_nSampleRateInHz = 0;
        m_nChannelNumber = 0;
        m_nBitsPerSample = 0;
        m_nBufferSize = 0;
    }

    private int ReStart()
    {
        Stop();
        if (mAudioTrack != null)
        {
            mAudioTrack.release();
            mAudioTrack = null;
        }
        if (mAudioSink != null)
        {
            mAudioSink.close();
        }
        if (initAudioOutput(m_nSampleRateInHz, m_nChannelNumber, m_nBitsPerSample, m_nBufferSize) == 0)
        {
            Start();
            return 0;
        } else
        {
            return -1;
        }
    }

    private int initAudioBuffer(int i)
    {
        if (mAudioBuffer != null && mAudioBuffer.length != i)
        {
            mAudioBuffer = null;
        }
        if (mAudioBuffer == null)
        {
            mAudioBuffer = new byte[i];
        }
        if (mAudioBuffer == null)
        {
            Log.e("MPAudioTrack", "AudioTrack, memory alloc failed");
            return -1;
        } else
        {
            m_nBufferSize = i;
            return 0;
        }
    }

    public int Create(int i, int j, int k, int l)
    {
        int i1 = initAudioOutput(i, j, k, l);
        if (i1 == 0)
        {
            i1 = initAudioBuffer(l);
        }
        if (i1 != 0)
        {
            Destroy();
        }
        return i1;
    }

    public void Destroy()
    {
        if (mAudioSink != null)
        {
            mAudioSink.close();
            mAudioSink = null;
        }
        if (mAudioTrack != null)
        {
            mAudioTrack.release();
            mAudioTrack = null;
        }
        mAudioBuffer = null;
    }

    public void Flush()
    {
        if (mAudioSink != null)
        {
            mAudioSink.flush();
        } else
        if (mAudioTrack != null)
        {
            mAudioTrack.flush();
            return;
        }
    }

    public byte[] GetDataBuffer()
    {
        return mAudioBuffer;
    }

    public int GetPosition()
    {
        int i;
        if (mAudioSink != null)
        {
            i = mAudioSink.getPlaybackHeadPosition();
        } else
        {
            AudioTrack audiotrack = mAudioTrack;
            i = 0;
            if (audiotrack != null)
            {
                return mAudioTrack.getPlaybackHeadPosition();
            }
        }
        return i;
    }

    public void Pause()
    {
        if (mAudioSink != null)
        {
            mAudioSink.pause();
        } else
        if (mAudioTrack != null)
        {
            mAudioTrack.pause();
            return;
        }
    }

    public int SetVolume(float f, float f1)
    {
        if (mAudioSink == null) goto _L2; else goto _L1
_L1:
        mAudioSink.setVolume(f, f1);
_L4:
        return 0;
_L2:
        if (mAudioTrack != null)
        {
            mAudioTrack.setStereoVolume(f, f1);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void Start()
    {
        if (mAudioSink != null)
        {
            mAudioSink.play();
        } else
        if (mAudioTrack != null)
        {
            mAudioTrack.play();
            return;
        }
    }

    public void Stop()
    {
        if (mAudioSink == null) goto _L2; else goto _L1
_L1:
        mAudioSink.stop();
_L4:
        Flush();
        return;
_L2:
        if (mAudioTrack != null)
        {
            mAudioTrack.stop();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int Write(int i)
    {
        int j = -1;
        if (mAudioSink != null)
        {
            j = mAudioSink.write(mAudioBuffer, 0, i);
        } else
        if (mAudioTrack != null)
        {
            j = mAudioTrack.write(mAudioBuffer, 0, i);
        }
        if (j < 0)
        {
            Log.e("MPAudioTrack", (new StringBuilder("some error happened in AudioTrack, error code = ")).append(j).append(" restart AudioTrack ").toString());
            ReStart();
            if (mAudioSink != null)
            {
                j = mAudioSink.write(mAudioBuffer, 0, i);
            } else
            if (mAudioTrack != null)
            {
                j = mAudioTrack.write(mAudioBuffer, 0, i);
            }
            Log.e("MPAudioTrack", (new StringBuilder("After AudioTrack restart, pos = ")).append(j).toString());
        }
        return j;
    }

    public int initAudioOutput(int i, int j, int k, int l)
    {
        Log.i("MPAudioTrack", (new StringBuilder("MPAudioTrack, nSampleRateInHz = ")).append(i).append(", nChannelNumber =").append(j).append("\uFF0C nBitsPerSample = ").append(k).toString());
        byte byte0;
        byte byte1;
        if (1 == j)
        {
            byte0 = 4;
        } else
        if (2 == j)
        {
            byte0 = 12;
        } else
        {
            return -1;
        }
        if (8 == k)
        {
            byte1 = 3;
        } else
        if (16 == k)
        {
            byte1 = 2;
        } else
        {
            return -1;
        }
        if (mAudioTrack != null)
        {
            mAudioTrack.release();
            mAudioTrack = null;
        }
        if (mAudioSink != null)
        {
            mAudioSink.close();
        }
        if (mAudioSink != null)
        {
            if (mAudioSink.init(i, j, k, l) != 0)
            {
                return -1;
            }
        } else
        {
            mAudioTrack = new AudioTrack(3, i, byte0, byte1, l, 1);
            if (mAudioTrack == null)
            {
                return -1;
            }
        }
        m_nSampleRateInHz = i;
        m_nChannelNumber = j;
        m_nBitsPerSample = k;
        return 0;
    }
}
