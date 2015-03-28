// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            PhotoViewer

public static interface 
{

    public abstract boolean isPreparingFilePath(int i);

    public abstract boolean isSupportZoom(int i);

    public abstract boolean isVideo(int i);

    public abstract void onClick();

    public abstract void onCurrentIndexChanged(int i);

    public abstract void onModeChanged( );

    public abstract void onPrefetch(int i, int j);

    public abstract void onPrefetchEx(int ai[]);

    public abstract void onSliding(boolean flag);

    public abstract void resetControlTimer();
}
