// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.net.NetworkInfo;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, RenderManager, ServerManager, CallbackThreadBridge

class this._cls1
    implements Runnable
{

    final is._cls0 this$1;

    public void run()
    {
        DLNA.access$600(_fld0, false);
    }

    irst()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/adk/atv/DLNA$5

/* anonymous class */
    class DLNA._cls5
        implements com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener
    {

        private boolean bFirst;
        final DLNA this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo != null && networkstateinfo.networkInfo.getType() == 1)
            {
                if (bFirst)
                {
                    bFirst = false;
                    return;
                }
                Log.d("UPNP", networkstateinfo.networkInfo.toString());
                android.net.NetworkInfo.State state = networkstateinfo.networkInfo.getState();
                if (state == android.net.NetworkInfo.State.CONNECTED)
                {
                    Log.d("UPNP", "DLNA NetworkInfo.State.CONNECTED  uninitUpnp");
                    DLNA.access$100(DLNA.this);
                    DLNA.access$200(DLNA.this).reset();
                    DLNA.access$300(DLNA.this).reset();
                    Log.d("UPNP", "DLNA NetworkInfo.State.CONNECTED  initUpnp");
                    DLNA.access$400(DLNA.this);
                    if (DLNA.access$500(DLNA.this) != null)
                    {
                        DLNA.access$500(DLNA.this).post(new DLNA._cls5._cls1(), 0L);
                        return;
                    } else
                    {
                        DLNA.access$600(DLNA.this, true);
                        return;
                    }
                }
                if (state == android.net.NetworkInfo.State.DISCONNECTED)
                {
                    if (DLNA.access$500(DLNA.this) != null)
                    {
                        DLNA.access$500(DLNA.this).post(new DLNA._cls5._cls2(), 0L);
                    } else
                    {
                        DLNA.access$600(DLNA.this, false);
                    }
                    Log.d("UPNP", "DLNA NetworkInfo.State.DISCONNECTED uninitUpnp");
                    DLNA.access$100(DLNA.this);
                    return;
                }
            }
        }

            
            {
                this$0 = DLNA.this;
                super();
                bFirst = true;
            }

        // Unreferenced inner class com/arcsoft/adk/atv/DLNA$5$1

/* anonymous class */
        class DLNA._cls5._cls1
            implements Runnable
        {

            final DLNA._cls5 this$1;

            public void run()
            {
                DLNA.access$600(this$0, true);
            }

                    
                    {
                        this$1 = DLNA._cls5.this;
                        super();
                    }
        }

    }

}
