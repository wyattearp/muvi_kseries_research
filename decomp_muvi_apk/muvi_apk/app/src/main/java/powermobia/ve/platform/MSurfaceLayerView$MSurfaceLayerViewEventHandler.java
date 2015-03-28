// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.platform;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

// Referenced classes of package powermobia.ve.platform:
//            MSurfaceLayerView

private class this._cls0 extends Handler
{

    final MSurfaceLayerView this$0;

    public void handleMessage(Message message)
    {
        if (message == null)
        {
            return;
        }
        message.what;
        JVM INSTR tableswitch 8193 8193: default 28
    //                   8193 34;
           goto _L1 _L2
_L1:
        super.handleMessage(message);
        return;
_L2:
        if (message.arg1 == 0)
        {
            setVisibleSync(true);
        } else
        {
            setVisibleSync(false);
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public (Looper looper)
    {
        this$0 = MSurfaceLayerView.this;
        super(looper);
    }
}
