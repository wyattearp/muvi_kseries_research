// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity

class this._cls0
    implements android.media.eenActivity._cls3
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onPrepared(MediaPlayer mediaplayer)
    {
        Log.e("AEEVideoStreamFullScreenActivity", "onPrepared IN  --------------------------------------- ");
        if (AEEVideoStreamFullScreenActivity.access$800(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$800(AEEVideoStreamFullScreenActivity.this).start();
        }
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
