// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.tool.DynamicDataStateMachine;

// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

private class <init> extends Handler
    implements ver
{

    private static final int DOSTEP_IDLE_INTERVAL = 100;
    private static final int DOSTEP_INTERVAL = 10;
    private static final int MSG_DOSTEP = 1;
    private static final int MSG_EDITITEM = 2;
    final ThumbEngine this$0;

    public void close()
    {
        stop();
    }

    public void editItem(int i)
    {
        Message message = Message.obtain();
        message.what = 2;
        message.arg1 = i;
        sendMessage(message);
    }

    public void handleMessage(Message message)
    {
        if (message.what != 1) goto _L2; else goto _L1
_L1:
        if (ThumbEngine.access$1300(ThumbEngine.this).isResume()) goto _L4; else goto _L3
_L3:
        return;
_L4:
        if (doStep() == 0x80002)
        {
            sendEmptyMessageDelayed(1, 100L);
            return;
        } else
        {
            sendEmptyMessageDelayed(1, 10L);
            return;
        }
_L2:
        if (message.what == 2)
        {
            int i = message.arg1;
            ThumbEngine.access$2000(ThumbEngine.this, ThumbEngine.access$1600(ThumbEngine.this), i);
            if (i >= ThumbEngine.access$1400(ThumbEngine.this) && i <= ThumbEngine.access$1500(ThumbEngine.this))
            {
                prefetItem(ThumbEngine.access$1400(ThumbEngine.this), ThumbEngine.access$1500(ThumbEngine.this));
                return;
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public void open()
    {
    }

    public void pause()
    {
        removeMessages(1);
    }

    public void prefetItem(int i, int j)
    {
        ThumbEngine.access$1900(ThumbEngine.this, ThumbEngine.access$1600(ThumbEngine.this), i, j);
    }

    public void resume()
    {
        removeMessages(1);
        sendEmptyMessageDelayed(1, 10L);
    }

    public void start()
    {
        sendEmptyMessageDelayed(1, 10L);
    }

    public void stop()
    {
        removeMessages(1);
    }

    private ver()
    {
        this$0 = ThumbEngine.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
