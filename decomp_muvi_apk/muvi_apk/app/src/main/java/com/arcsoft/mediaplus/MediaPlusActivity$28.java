// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements ServiceConnection
{

    final MediaPlusActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        MediaPlusActivity.access$2302(MediaPlusActivity.this, com.arcsoft.mediaplus.service.util.ub.asInterface(ibinder));
        MediaPlusActivity.access$4202(MediaPlusActivity.this, true);
        Log.e("FENG", (new StringBuilder()).append("FENG onServiceConnected mBinder = ").append(MediaPlusActivity.access$2300(MediaPlusActivity.this)).toString());
        if (MediaPlusActivity.access$2300(MediaPlusActivity.this) == null) goto _L2; else goto _L1
_L1:
        String as[] = MediaPlusActivity.access$2300(MediaPlusActivity.this).getOnlineDmsUDN();
        if (as == null) goto _L2; else goto _L3
_L3:
        if (as.length <= 0) goto _L2; else goto _L4
_L4:
        int i = as.length;
        int j = 0;
_L9:
        if (j >= i) goto _L2; else goto _L5
_L5:
        String s = as[j];
        if (!MediaPlusActivity.access$2300(MediaPlusActivity.this).getDmsFriendlyName(s).equalsIgnoreCase("DMS for DV")) goto _L7; else goto _L6
_L6:
        MediaPlusActivity.access$2102(MediaPlusActivity.this, s);
        MediaPlusActivity.access$2400(MediaPlusActivity.this, MediaPlusActivity.access$2100(MediaPlusActivity.this));
_L2:
        return;
_L7:
        j++;
        if (true) goto _L9; else goto _L8
_L8:
        RemoteException remoteexception;
        remoteexception;
        remoteexception.printStackTrace();
        return;
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        MediaPlusActivity.access$2302(MediaPlusActivity.this, null);
        MediaPlusActivity.access$4202(MediaPlusActivity.this, false);
    }

    tingBinder()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
