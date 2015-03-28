// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

public abstract class IArcMediaPlayer extends MediaPlayer
{

    public IArcMediaPlayer()
    {
    }

    public abstract Bitmap captureFrame(int i);

    public abstract int getAspectRatio();

    public abstract int getMode();

    public abstract double getRate();

    public abstract boolean isHardware();

    public abstract void seekToSyncFrame(int i);

    public abstract void set3DDisplayMode(boolean flag);

    public abstract void set3DPupilDist(long l);

    public abstract void setHardwareMode(boolean flag);

    public abstract void setMode(int i, double d);
}
