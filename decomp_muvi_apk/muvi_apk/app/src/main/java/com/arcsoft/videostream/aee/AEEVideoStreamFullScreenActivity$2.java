// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import android.widget.RelativeLayout;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity

class this._cls0
    implements android.media.ner
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onVideoSizeChanged(MediaPlayer mediaplayer, int i, int j)
    {
        Log.v("zdf", (new StringBuilder()).append("onVideoSizeChanged, width = ").append(i).append(", height = ").append(j).toString());
        AEEVideoStreamFullScreenActivity.access$102(AEEVideoStreamFullScreenActivity.this, i);
        AEEVideoStreamFullScreenActivity.access$202(AEEVideoStreamFullScreenActivity.this, j);
        if (AEEVideoStreamFullScreenActivity.access$300(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$402(AEEVideoStreamFullScreenActivity.this, AEEVideoStreamFullScreenActivity.access$300(AEEVideoStreamFullScreenActivity.this).getWidth());
            AEEVideoStreamFullScreenActivity.access$502(AEEVideoStreamFullScreenActivity.this, AEEVideoStreamFullScreenActivity.access$300(AEEVideoStreamFullScreenActivity.this).getHeight());
            AEEVideoStreamFullScreenActivity.access$600(AEEVideoStreamFullScreenActivity.this, AEEVideoStreamFullScreenActivity.access$400(AEEVideoStreamFullScreenActivity.this), AEEVideoStreamFullScreenActivity.access$500(AEEVideoStreamFullScreenActivity.this));
            AEEVideoStreamFullScreenActivity.access$700(AEEVideoStreamFullScreenActivity.this, AEEVideoStreamFullScreenActivity.access$400(AEEVideoStreamFullScreenActivity.this), AEEVideoStreamFullScreenActivity.access$500(AEEVideoStreamFullScreenActivity.this));
            AEEVideoStreamFullScreenActivity.access$300(AEEVideoStreamFullScreenActivity.this).invalidate();
        }
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
