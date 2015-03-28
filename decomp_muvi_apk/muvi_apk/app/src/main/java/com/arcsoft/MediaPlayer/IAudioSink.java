// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.MediaPlayer;


public interface IAudioSink
{

    public static final String TAG = "IAudioSink";

    public abstract void close();

    public abstract void flush();

    public abstract int getPlaybackHeadPosition();

    public abstract int init(int i, int j, int k, int l);

    public abstract void pause();

    public abstract void play();

    public abstract int setVolume(float f, float f1);

    public abstract void stop();

    public abstract int write(byte abyte0[], int i, int j);
}
