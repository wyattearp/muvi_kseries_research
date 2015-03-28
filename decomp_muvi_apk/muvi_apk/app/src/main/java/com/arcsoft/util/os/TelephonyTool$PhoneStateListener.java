// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.telephony.PhoneStateListener;

// Referenced classes of package com.arcsoft.util.os:
//            TelephonyTool

private class <init> extends PhoneStateListener
{

    final TelephonyTool this$0;

    public void onCallStateChanged(int i, String s)
    {
        if (TelephonyTool.access$100(TelephonyTool.this) == null)
        {
            return;
        }
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            TelephonyTool.access$100(TelephonyTool.this).onCallStatusIdle();
            return;

        case 2: // '\002'
            TelephonyTool.access$100(TelephonyTool.this).onCallStatusOffHook();
            return;

        case 1: // '\001'
            TelephonyTool.access$100(TelephonyTool.this).onCallStatusRinging();
            return;
        }
    }

    private Listener()
    {
        this$0 = TelephonyTool.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
