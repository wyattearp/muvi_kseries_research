// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            AbsPlayList

public static interface 
{

    public abstract void onPlayIndexChanged(int i);

    public abstract void onPlayIndexReady(int i);

    public abstract void onPlaylistUpdated(int i, int j, boolean flag);
}
