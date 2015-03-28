// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.platform;

import android.media.AudioTrack;

public class MPAudioTrack
{

    private static final String TAG = "MPAudioTrack";
    private byte mAudioBuffer[];
    private AudioTrack mAudioTrack;

    private MPAudioTrack()
    {
        mAudioTrack = null;
        mAudioBuffer = null;
    }

    public int Create(int i, int j, int k, int l)
    {
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
        mAudioTrack = new AudioTrack(3, i, byte0, byte1, l, 1);
        if (mAudioTrack == null)
        {
            return -1;
        }
        mAudioBuffer = new byte[l];
        if (mAudioBuffer == null)
        {
            mAudioTrack.release();
            mAudioTrack = null;
            return -1;
        } else
        {
            return 0;
        }
    }

    public void Destroy()
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.release();
            mAudioTrack = null;
        }
        mAudioBuffer = null;
    }

    public void Flush()
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.flush();
        }
    }

    public byte[] GetDataBuffer()
    {
        return mAudioBuffer;
    }

    public int GetPosition()
    {
        AudioTrack audiotrack = mAudioTrack;
        int i = 0;
        if (audiotrack != null)
        {
            i = mAudioTrack.getPlaybackHeadPosition();
        }
        return i;
    }

    public void Pause()
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.pause();
        }
    }

    public int SetVolume(float f, float f1)
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.setStereoVolume(f, f1);
        }
        return 0;
    }

    public void Start()
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.play();
        }
    }

    public void Stop()
    {
        if (mAudioTrack != null)
        {
            mAudioTrack.stop();
        }
    }

    public int Write(int i)
    {
        AudioTrack audiotrack = mAudioTrack;
        int j = 0;
        if (audiotrack != null)
        {
            j = mAudioTrack.write(mAudioBuffer, 0, i);
        }
        return j;
    }
}
