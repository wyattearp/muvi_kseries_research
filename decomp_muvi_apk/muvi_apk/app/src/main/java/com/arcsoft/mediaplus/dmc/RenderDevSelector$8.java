// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class gCallback.Stub extends com.arcsoft.mediaplus.service.util..Stub
{

    final RenderDevSelector this$0;

    public void onDmrOffline(String s)
        throws RemoteException
    {
        Log.d("Dmc", (new StringBuilder()).append(" RenderDevSelector onDmrOffline : strUDN = ").append(s).toString());
        Message message = Message.obtain();
        message.what = 8;
        message.obj = s;
        RenderDevSelector.access$1800(RenderDevSelector.this).sendMessage(message);
    }

    public void onDmrOnline(String s)
        throws RemoteException
    {
        Log.d("Dmc", (new StringBuilder()).append(" RenderDevSelector onDmrOnline : strUDN = ").append(s).toString());
        Message message = Message.obtain();
        message.what = 7;
        message.obj = s;
        RenderDevSelector.access$1800(RenderDevSelector.this).sendMessage(message);
    }

    public void onDmsOffline(String s)
        throws RemoteException
    {
    }

    public void onDmsOnline(String s)
        throws RemoteException
    {
    }

    gCallback.Stub()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
