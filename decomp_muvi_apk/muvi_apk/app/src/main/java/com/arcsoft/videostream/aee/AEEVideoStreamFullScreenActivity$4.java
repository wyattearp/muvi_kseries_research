// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.media.MediaPlayer;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient

class this._cls0
    implements android.media.eenActivity._cls4
{

    final AEEVideoStreamFullScreenActivity this$0;

    public boolean onInfo(MediaPlayer mediaplayer, int i, int j)
    {
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("what = ").append(i).append(" extra = ").append(j).toString());
        switch (i)
        {
        default:
            return false;

        case 900: 
            break;
        }
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            mSocketClient.setIsFirstStartPreview(false);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return false;
        }
        return false;
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
