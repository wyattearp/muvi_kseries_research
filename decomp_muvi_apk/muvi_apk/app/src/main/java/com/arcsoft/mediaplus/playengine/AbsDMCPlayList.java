// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            AbsPlayList

public abstract class AbsDMCPlayList extends AbsPlayList
{

    public AbsDMCPlayList()
    {
    }

    protected abstract void getPlayURLAsync(int i, com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, Object obj, com.arcsoft.mediaplus.datasource.DMCDataSource.IOnGetPlayURLListener iongetplayurllistener);
}
