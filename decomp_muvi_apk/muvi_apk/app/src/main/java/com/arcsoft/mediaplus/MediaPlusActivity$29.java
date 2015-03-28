// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements android.view.ivity._cls29
{

    final MediaPlusActivity this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296422 2131296422: default 24
    //                   2131296422 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        switchTo(0);
        if (MediaPlusActivity.access$800(MediaPlusActivity.this))
        {
            try
            {
                MediaPlusActivity.access$4302(MediaPlusActivity.this, AEESocketClient.getInstanceClient());
                if (!MediaPlusActivity.access$4300(MediaPlusActivity.this).isConnected())
                {
                    switchTo(1);
                    return;
                }
            }
            catch (IOException ioexception)
            {
                switchTo(1);
                ioexception.printStackTrace();
                return;
            }
        } else
        {
            switchTo(1);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    ()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
