// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, DownloadFacade, ListViewManager

class this._cls0
    implements ServiceConnection
{

    final MediaPlusActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Log.i("MediaPlusActivity", "DLNAService connected");
        MediaPlusActivity.access$1502(MediaPlusActivity.this, ((com.arcsoft.mediaplus.service.util.er)ibinder).getService());
        mUpDownloadEngine = MediaPlusActivity.access$1500(MediaPlusActivity.this).getUpDownloadEngine();
        MediaPlusActivity.access$1600(MediaPlusActivity.this).setUpDownloadEngine(mUpDownloadEngine);
        MediaPlusActivity.access$500(MediaPlusActivity.this).setUpDownloadContext(mUpDownloadEngine);
        mUpDownloadEngine.controlSpeed(0, 0, 50);
        mUpDownloadEngine.controlSpeed(1, 1, 0);
        mUpDownloadEngine.onStart();
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        Log.i("MediaPlusActivity", "DLNAService disconnected");
        MediaPlusActivity.access$1502(MediaPlusActivity.this, null);
    }

    DownloadEngine()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
