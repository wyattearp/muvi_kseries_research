// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;


// Referenced classes of package com.arcsoft.mediaplus.setting:
//            Settings

public static interface 
{

    public abstract void OnDefaultDownloadDestinationChanged(String s);

    public abstract void OnDefaultRenderChanged(String s);

    public abstract void OnDefaultServerChanged(String s);

    public abstract void onBrowseModeChanged(boolean flag);

    public abstract void onSortModeChanged(boolean flag);

    public abstract void onTVStreamingResolutionChange(boolean flag);
}
