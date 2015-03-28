// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;

// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine

class this._cls0 extends Handler
{

    final DMCPlayEngine this$0;

    public void handleMessage(Message message)
    {
        boolean flag;
        long l;
        RenderManager rendermanager;
        flag = true;
        l = 0L;
        rendermanager = DLNA.instance().getRenderManager();
        if (message.what != 8) goto _L2; else goto _L1
_L1:
        DMCPlayEngine.access$2900(DMCPlayEngine.this, yncMsgType.MSG_GETVOLUME, true);
        rendermanager.getVolumeAsync(DMCPlayEngine.access$200(DMCPlayEngine.this), com.arcsoft.adk.atv.lumeChannel.MASTER);
        l = 3000L;
_L4:
        if (flag)
        {
            sendEmptyMessageDelayed(message.what, l);
        }
        return;
_L2:
        if (message.what == 1)
        {
            DMCPlayEngine.access$2900(DMCPlayEngine.this, yncMsgType.MSG_GETACTION, true);
            rendermanager.getTransportInfoAsync(DMCPlayEngine.access$200(DMCPlayEngine.this));
            l = 1000L;
        } else
        if (message.what == 16)
        {
            DMCPlayEngine.access$2900(DMCPlayEngine.this, yncMsgType.MSG_GETALLOWACTIONS, true);
            rendermanager.getCurrentTransportActionsAsync(DMCPlayEngine.access$200(DMCPlayEngine.this));
            l = 300L;
        } else
        if (message.what != 2)
        {
            if (message.what == 4)
            {
                if (getStatus() == STATUS.STATUS_PLAYING)
                {
                    DMCPlayEngine.access$2900(DMCPlayEngine.this, yncMsgType.MSG_GETPOSITION, true);
                    rendermanager.getPositionInfoAsync(DMCPlayEngine.access$200(DMCPlayEngine.this));
                }
                l = 1000L;
            } else
            if (message.what == -1 || message.what == -8191)
            {
                ((Runnable)message.obj).run();
                flag = false;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    STATUS()
    {
        this$0 = DMCPlayEngine.this;
        super();
    }
}
