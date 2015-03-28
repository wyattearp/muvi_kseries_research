// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.os.Handler;
import android.os.Message;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.arcsoft.mediaplus.setting.DigitalMediaAdapter;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class this._cls0 extends Handler
{

    final RenderDevSelector this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 1 8: default 52
    //                   1 53
    //                   2 52
    //                   3 52
    //                   4 52
    //                   5 87
    //                   6 108
    //                   7 154
    //                   8 182;
           goto _L1 _L2 _L1 _L1 _L1 _L3 _L4 _L5 _L6
_L1:
        return;
_L2:
        if (RenderDevSelector.access$700(RenderDevSelector.this) != null && RenderDevSelector.access$700(RenderDevSelector.this).isShowing())
        {
            RenderDevSelector.access$700(RenderDevSelector.this).dismiss();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (RenderDevSelector.access$800(RenderDevSelector.this) != null)
        {
            RenderDevSelector.access$800(RenderDevSelector.this).notifyDataSetChanged();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (RenderDevSelector.access$900(RenderDevSelector.this) != null && !RenderDevSelector.access$1000(RenderDevSelector.this))
        {
            Toast.makeText(RenderDevSelector.access$900(RenderDevSelector.this), 0x7f0b0058, 0).show();
            RenderDevSelector.access$1002(RenderDevSelector.this, true);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (RenderDevSelector.access$800(RenderDevSelector.this) != null)
        {
            RenderDevSelector.access$800(RenderDevSelector.this).dmrOnLine((String)message.obj);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (RenderDevSelector.access$800(RenderDevSelector.this) != null)
        {
            RenderDevSelector.access$800(RenderDevSelector.this).dmrOffLine((String)message.obj);
            return;
        }
        if (true) goto _L1; else goto _L7
_L7:
    }

    ter()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
