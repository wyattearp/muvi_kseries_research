// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.util.os.atteryChangeListener
{

    final PlayActivity this$0;

    public void OnBatteryLevelChanged(com.arcsoft.util.os.eryInfo eryinfo)
    {
        PlayActivity.access$3100(PlayActivity.this, eryinfo, 15);
    }

    public void OnBatteryPlugedChanged(com.arcsoft.util.os.eryInfo eryinfo)
    {
        PlayActivity.access$3100(PlayActivity.this, eryinfo, 15);
    }

    public void OnBatteryStatusChanged(com.arcsoft.util.os.eryInfo eryinfo)
    {
        PlayActivity.access$3100(PlayActivity.this, eryinfo, 15);
    }

    eListener()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
