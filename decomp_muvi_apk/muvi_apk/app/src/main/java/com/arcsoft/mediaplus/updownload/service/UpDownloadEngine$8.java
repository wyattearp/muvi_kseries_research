// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

class this._cls0
    implements com.arcsoft.mediaplus.setting.ChangedListener
{

    final UpDownloadEngine this$0;

    public void OnDefaultDownloadDestinationChanged(String s)
    {
        UpDownloadEngine.access$2202(UpDownloadEngine.this, s);
    }

    public void OnDefaultRenderChanged(String s)
    {
    }

    public void OnDefaultServerChanged(String s)
    {
        Log.i("UpDownloadEngine", (new StringBuilder()).append("OnDefaultServerChanged: ").append(s).toString());
        UpDownloadEngine.access$2102(UpDownloadEngine.this, true);
    }

    public void onBrowseModeChanged(boolean flag)
    {
    }

    public void onSortModeChanged(boolean flag)
    {
    }

    public void onTVStreamingResolutionChange(boolean flag)
    {
    }

    ener()
    {
        this$0 = UpDownloadEngine.this;
        super();
    }
}
