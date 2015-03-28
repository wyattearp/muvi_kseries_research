// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;


// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            ChannelDataSource

class this._cls0
    implements com.arcsoft.mediaplus.setting.hangedListener
{

    final ChannelDataSource this$0;

    public void OnDefaultDownloadDestinationChanged(String s)
    {
    }

    public void OnDefaultRenderChanged(String s)
    {
    }

    public void OnDefaultServerChanged(String s)
    {
    }

    public void onBrowseModeChanged(boolean flag)
    {
    }

    public void onSortModeChanged(boolean flag)
    {
    }

    public void onTVStreamingResolutionChange(boolean flag)
    {
        ChannelDataSource.access$002(ChannelDataSource.this, flag);
    }

    gedListener()
    {
        this$0 = ChannelDataSource.this;
        super();
    }
}
