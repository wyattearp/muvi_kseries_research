// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import android.widget.Toast;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity

class this._cls0
    implements android.media.eenActivity._cls1
{

    final AEEVideoStreamFullScreenActivity this$0;

    public boolean onError(MediaPlayer mediaplayer, int i, int j)
    {
        Log.v("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("full showStream onerror what=").append(i).append(";extra=").append(j).toString());
        AEEVideoStreamFullScreenActivity.access$002(AEEVideoStreamFullScreenActivity.this, false);
        switchTo(1, 14);
        Toast.makeText(AEEVideoStreamFullScreenActivity.this, 0x7f0b019f, 1).show();
        return false;
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
