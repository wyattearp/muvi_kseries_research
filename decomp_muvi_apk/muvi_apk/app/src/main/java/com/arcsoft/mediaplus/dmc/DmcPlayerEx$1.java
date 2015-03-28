// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.mediaplus.playengine.DMCPlayEngine;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcPlayerEx, DmcPlayingDataProvider

class 
    implements Runnable
{

    final DmcPlayerEx this$0;
    final com.arcsoft.mediaplus.playengine..EFFECT val$_effect;
    final int val$_index;
    final long val$_startpos;
    final com.arcsoft.adk.atv.erDesc val$renderdesc;

    public void run()
    {
        Log.d(DmcPlayerEx.access$000(), (new StringBuilder()).append("Play index ").append(val$_index).toString());
        long l = mDataProvider.getId(val$_index);
        if (mDataProvider.getUri(val$_index) == null)
        {
            Log.e(DmcPlayerEx.access$100(), "Uri is null!");
            if (DmcPlayerEx.access$200(DmcPlayerEx.this) != null)
            {
                DmcPlayerEx.access$300(DmcPlayerEx.this).onError(com.arcsoft.mediaplus.playengine.Error.ERROR_PLAYLIST_URI_NULL);
            }
            return;
        }
        if (DLNA.instance().getRenderManager().isSharpDMR(DmcPlayerEx.access$400(DmcPlayerEx.this).getRenderUDN()))
        {
            DmcPlayerEx.access$502(DmcPlayerEx.this, new com.arcsoft.mediaplus.playengine.pPlayAction(DmcPlayerEx.this, l, val$_startpos, val$_effect));
        } else
        {
            DmcPlayerEx.access$602(DmcPlayerEx.this, new com.arcsoft.mediaplus.playengine.Action(DmcPlayerEx.this, l, val$_startpos, val$_effect));
        }
        if (mDataProvider != null)
        {
            mDataProvider.setCurrentIndex(val$_index, true);
        }
        DmcPlayerEx.access$700(DmcPlayerEx.this).buildAction();
        mDataProvider.getPlayURLAsync(val$_index, val$renderdesc, new Long(l), DmcPlayerEx.access$800(DmcPlayerEx.this));
        DmcPlayerEx.access$900(DmcPlayerEx.this, val$_effect);
    }

    ()
    {
        this$0 = final_dmcplayerex;
        val$_index = i;
        val$_startpos = l;
        val$_effect = effect;
        val$renderdesc = com.arcsoft.adk.atv.erDesc.this;
        super();
    }
}
