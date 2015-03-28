// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity

class this._cls0
    implements android.media.tener
{

    final AEEVideoStreamActivity this$0;

    public void onPrepared(MediaPlayer mediaplayer)
    {
        Log.e("AEEVideoStreamActivity", "onPrepared IN  --------------------------------------- ");
        if (AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this).start();
            AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this).setDisplay(null);
            AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this).setDisplay(AEEVideoStreamActivity.access$100(AEEVideoStreamActivity.this));
        }
    }

    ()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
