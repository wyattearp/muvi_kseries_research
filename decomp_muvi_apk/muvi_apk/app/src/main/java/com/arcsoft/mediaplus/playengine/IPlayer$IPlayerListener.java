// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayer

public static interface 
{

    public abstract void onBuffering();

    public abstract void onCompleted();

    public abstract void onError( );

    public abstract void onMute(boolean flag);

    public abstract void onPaused();

    public abstract void onPlayIndexChanged(int i);

    public abstract void onPlayStarted();

    public abstract void onProgressChanged(long l, long l1);

    public abstract void onSeeked();

    public abstract void onStopped();

    public abstract void onVolumeChanged(int i);
}
