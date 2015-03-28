// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.platform.MPTimer;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusApplication

static final class sChangeListener
    implements com.arcsoft.util.os.tusChangeListener
{

    public void OnScreenStatusTurnOff()
    {
        if (DLNA.instance() != null && DLNA.instance().getUserToken() == 0)
        {
            MPTimer.pause();
        }
        Log.v("mediasee", "========= Screen Off ======");
    }

    public void OnScreenStatusTurnOn()
    {
        MPTimer.resume();
        Log.v("mediasee", "========= Screen On ======");
    }

    sChangeListener()
    {
    }
}
