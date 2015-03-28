// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.widget.SeekBar;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayback, DmcPlayerEx

class this._cls0
    implements android.widget.BarChangeListener
{

    final DmcPlayback this$0;

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        if (DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            DmcPlayback.access$1000(DmcPlayback.this).seekTo(i);
        }
        refreshCurrent(i);
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
        if (DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            DmcPlayback.access$1000(DmcPlayback.this).startSeek();
        }
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        if (DmcPlayback.access$1000(DmcPlayback.this) != null)
        {
            DmcPlayback.access$1000(DmcPlayback.this).endSeek();
        }
    }

    stener()
    {
        this$0 = DmcPlayback.this;
        super();
    }
}
