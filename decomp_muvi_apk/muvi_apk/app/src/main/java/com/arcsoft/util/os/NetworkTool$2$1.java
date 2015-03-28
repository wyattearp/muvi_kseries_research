// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

// Referenced classes of package com.arcsoft.util.os:
//            NetworkTool

class this._cls1
    implements Runnable
{

    final aSettingChanged this$1;

    public void run()
    {
        if (NetworkTool.access$200(_fld0) != null)
        {
            NetworkTool.access$200(_fld0).OnBackgroundDataSettingChanged();
        }
    }

    nit>()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/util/os/NetworkTool$2

/* anonymous class */
    class NetworkTool._cls2 extends BroadcastReceiver
    {

        final NetworkTool this$0;

        public void onReceive(Context context, Intent intent)
        {
            if (NetworkTool.access$000(NetworkTool.this) == null)
            {
                NetworkTool.access$002(NetworkTool.this, new Handler());
            }
            NetworkTool.access$000(NetworkTool.this).post(new NetworkTool._cls2._cls1());
        }

            
            {
                this$0 = NetworkTool.this;
                super();
            }
    }

}
