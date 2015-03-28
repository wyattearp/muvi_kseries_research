// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            RenderManager

class this._cls0
    implements usChangeListener
{

    final RenderManager this$0;

    public void OnDLNAConnected()
    {
    }

    public void OnDLNADisconnected()
    {
        enderStatusListener aenderstatuslistener[] = RenderManager.access$500(RenderManager.this);
        if (aenderstatuslistener != null)
        {
            for (Iterator iterator = RenderManager.access$900(RenderManager.this).iterator(); iterator.hasNext();)
            {
                Desc desc = (Desc)iterator.next();
                int i = aenderstatuslistener.length;
                int j = 0;
                while (j < i) 
                {
                    aenderstatuslistener[j].onRenderRemoved(desc);
                    j++;
                }
            }

        }
        RenderManager.access$1000(RenderManager.this);
    }

    public void OnDLNAInternalError(int i)
    {
    }

    enderStatusListener()
    {
        this$0 = RenderManager.this;
        super();
    }
}
