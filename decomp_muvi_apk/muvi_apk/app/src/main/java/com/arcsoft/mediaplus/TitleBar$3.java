// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.widget.SeekBar;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            TitleBar, MediaPlusActivity

class this._cls0
    implements android.widget.eekBarChangeListener
{

    final TitleBar this$0;

    public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
    {
        Log.v("zdf", (new StringBuilder()).append("onProgressChanged, progress = ").append(i).append(", fromUser = ").append(flag).toString());
        TitleBar.access$102(TitleBar.this, flag);
    }

    public void onStartTrackingTouch(SeekBar seekbar)
    {
        Log.v("zdf", "onStartTrackingTouch");
        TitleBar.access$102(TitleBar.this, false);
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        Log.v("zdf", (new StringBuilder()).append("onStopTrackingTouch, progress = ").append(seekbar.getProgress()).toString());
        if (!TitleBar.access$100(TitleBar.this))
        {
            seekbar.setProgress(seekbar.getMax() - seekbar.getProgress());
        }
        MediaPlusActivity mediaplusactivity = (MediaPlusActivity)TitleBar.access$000(TitleBar.this);
        int i;
        if (seekbar.getProgress() == 0)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        mediaplusactivity.switchView(i);
    }

    tivity()
    {
        this$0 = TitleBar.this;
        super();
    }
}
