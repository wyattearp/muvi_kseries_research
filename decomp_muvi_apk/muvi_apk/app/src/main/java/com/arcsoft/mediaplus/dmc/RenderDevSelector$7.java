// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class this._cls0
    implements ServiceConnection
{

    final RenderDevSelector this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        RenderDevSelector.access$1202(RenderDevSelector.this, com.arcsoft.mediaplus.service.util.tub.asInterface(ibinder));
        try
        {
            Log.d("Dmc", " RenderDevSelector onServiceConnected");
            RenderDevSelector.access$1200(RenderDevSelector.this).registerCallback(RenderDevSelector.access$1300(RenderDevSelector.this));
            DLNA.instance().getServerManager().registerServerStatusListener(RenderDevSelector.access$1400(RenderDevSelector.this));
            RenderDevSelector.access$1502(RenderDevSelector.this, true);
            RenderDevSelector.access$1600(RenderDevSelector.this);
            if (RenderDevSelector.access$1700(RenderDevSelector.this))
            {
                start();
            }
            return;
        }
        catch (RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        try
        {
            if (RenderDevSelector.access$1200(RenderDevSelector.this) != null)
            {
                RenderDevSelector.access$1200(RenderDevSelector.this).unregisterCallback(RenderDevSelector.access$1300(RenderDevSelector.this));
            }
            if (DLNA.instance().getServerManager() != null)
            {
                DLNA.instance().getServerManager().unregisterServerStatusListener(RenderDevSelector.access$1400(RenderDevSelector.this));
            }
        }
        catch (RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        RenderDevSelector.access$1202(RenderDevSelector.this, null);
        RenderDevSelector.access$1502(RenderDevSelector.this, false);
    }

    gBinder()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
