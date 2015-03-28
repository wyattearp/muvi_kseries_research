// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayEngine

public static interface 
{

    public abstract void OnBuffering(int i);

    public abstract void OnComplete();

    public abstract void OnError( );

    public abstract void OnMute(boolean flag);

    public abstract void OnOpened();

    public abstract void OnOpening();

    public abstract void OnPaused();

    public abstract void OnPausing();

    public abstract void OnProgressChanged(long l, long l1);

    public abstract void OnSeeked(long l);

    public abstract void OnSeeking();

    public abstract void OnStartedPlay();

    public abstract void OnStartingPlay();

    public abstract void OnStopped();

    public abstract void OnStopping();

    public abstract void OnVolumeChanged(int i);
}
