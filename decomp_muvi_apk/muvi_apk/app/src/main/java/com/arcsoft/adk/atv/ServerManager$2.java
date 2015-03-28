// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import com.arcsoft.mediaplus.setting.Settings;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            ServerManager

class this._cls0
    implements usChangeListener
{

    final ServerManager this$0;

    public void OnDLNAConnected()
    {
    }

    public void OnDLNADisconnected()
    {
        erverStatusListener aerverstatuslistener[];
        if (Settings.instance().getDefaultDMSUDN() != null)
        {
            Settings.instance().setDefaultDMSUDN(null);
            Settings.instance().setDefaultDMSName(null);
        }
        aerverstatuslistener = ServerManager.access$400(ServerManager.this);
        if (aerverstatuslistener == null) goto _L2; else goto _L1
_L1:
        List list = ServerManager.access$1100(ServerManager.this);
        list;
        JVM INSTR monitorenter ;
        if (ServerManager.access$1100(ServerManager.this).isEmpty()) goto _L4; else goto _L3
_L3:
        Iterator iterator = ServerManager.access$1100(ServerManager.this).iterator();
_L7:
        Desc desc;
        int i;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        desc = (Desc)iterator.next();
        i = aerverstatuslistener.length;
        int j = 0;
_L6:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        aerverstatuslistener[j].onServerRemoved(desc);
        j++;
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L7; else goto _L4
_L4:
        list;
        JVM INSTR monitorexit ;
_L2:
        ServerManager.access$1200(ServerManager.this);
        return;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void OnDLNAInternalError(int i)
    {
    }

    erverStatusListener()
    {
        this$0 = ServerManager.this;
        super();
    }
}
