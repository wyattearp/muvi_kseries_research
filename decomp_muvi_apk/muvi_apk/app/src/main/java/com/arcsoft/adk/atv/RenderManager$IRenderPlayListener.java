// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            RenderManager

public static interface tings
{

    public abstract void onGetCurrentTransportActions(int i, String s, String s1);

    public abstract void onGetMeidaInfo(int i, tings tings, String s);

    public abstract void onGetMute(int i, boolean flag, String s);

    public abstract void onGetPositionInfo(int i,  , String s);

    public abstract void onGetTransportInfo(int i, o o, String s);

    public abstract void onGetTransportSettings(int i, tings tings, String s);

    public abstract void onGetVolume(int i, int j, String s);

    public abstract void onMediaPause(int i, String s);

    public abstract void onMediaPlay(int i, String s);

    public abstract void onMediaSeek(int i, String s);

    public abstract void onMediaStop(int i, String s);

    public abstract void onOpenMedia(int i, String s);

    public abstract void onSetMute(int i, String s);

    public abstract void onSetVolume(int i, String s);
}
