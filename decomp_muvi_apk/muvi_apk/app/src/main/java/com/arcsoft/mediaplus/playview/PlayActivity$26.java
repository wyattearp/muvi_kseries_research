// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements ServiceConnection
{

    final PlayActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Log.i("PlayActivity", "DLNAService connected");
        PlayActivity.access$2902(PlayActivity.this, ((com.arcsoft.mediaplus.service.util.lBinder)ibinder).getService());
        PlayActivity.access$002(PlayActivity.this, PlayActivity.access$2900(PlayActivity.this).getUpDownloadEngine());
        svcReady();
        PlayActivity.access$3000(PlayActivity.this).setUpDownloadEngine(PlayActivity.access$000(PlayActivity.this));
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        Log.i("PlayActivity", "DLNAService disconnected");
        PlayActivity.access$2902(PlayActivity.this, null);
        svcDisable();
    }

    LocalBinder()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
