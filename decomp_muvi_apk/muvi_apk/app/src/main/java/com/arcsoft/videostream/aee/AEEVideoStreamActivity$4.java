// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import android.widget.Toast;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity

class this._cls0
    implements android.media.er
{

    final AEEVideoStreamActivity this$0;

    public boolean onError(MediaPlayer mediaplayer, int i, int j)
    {
        Log.v("AEEVideoStreamActivity", (new StringBuilder()).append("showStream onerror what=").append(i).append(";extra=").append(j).toString());
        AEEVideoStreamActivity.access$502(AEEVideoStreamActivity.this, false);
        switchTo(1, 14);
        Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b019f, 1).show();
        return false;
    }

    ()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
