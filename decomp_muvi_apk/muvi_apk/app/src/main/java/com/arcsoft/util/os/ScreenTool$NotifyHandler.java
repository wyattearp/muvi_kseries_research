// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.util.os:
//            ScreenTool

private class <init> extends Handler
{

    final ScreenTool this$0;

    public void handleMessage(Message message)
    {
        sChangeListener aschangelistener[] = ScreenTool.access$300(ScreenTool.this);
        if (aschangelistener != null && aschangelistener.length >= 1) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            int k = aschangelistener.length;
            int l = 0;
            while (l < k) 
            {
                aschangelistener[l].OnScreenStatusTurnOff();
                l++;
            }
            break;

        case 2: // '\002'
            i = aschangelistener.length;
            j = 0;
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L1; else goto _L3
_L3:
        while (j < i) 
        {
            aschangelistener[j].OnScreenStatusTurnOn();
            j++;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    private sChangeListener()
    {
        this$0 = ScreenTool.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
