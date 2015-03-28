// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.ListView;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingListActivity, DigitalMediaAdapter

class this._cls0
    implements ServiceConnection
{

    final SettingListActivity this$0;

    public void onServiceConnected(ComponentName componentname, IBinder ibinder)
    {
        Log.e(TAG, "onServiceConnected()");
        SettingListActivity.access$602(SettingListActivity.this, com.arcsoft.mediaplus.service.util.b.asInterface(ibinder));
        try
        {
            SettingListActivity.access$600(SettingListActivity.this).registerCallback(SettingListActivity.access$700(SettingListActivity.this));
            DLNA.instance().getServerManager().registerServerStatusListener(SettingListActivity.access$800(SettingListActivity.this));
            SettingListActivity.access$302(SettingListActivity.this, new DigitalMediaAdapter(SettingListActivity.this, SettingListActivity.access$000(SettingListActivity.this), SettingListActivity.access$600(SettingListActivity.this)));
            SettingListActivity.access$300(SettingListActivity.this).setStatusChangedListener(SettingListActivity.this);
            SettingListActivity.access$900(SettingListActivity.this).setAdapter(SettingListActivity.access$300(SettingListActivity.this));
            setItemChecked(SettingListActivity.access$300(SettingListActivity.this).getActiveIndex());
        }
        catch (RemoteException remoteexception) { }
        SettingListActivity.access$1002(SettingListActivity.this, true);
    }

    public void onServiceDisconnected(ComponentName componentname)
    {
        Log.e(TAG, "onServiceDisconnected()");
        try
        {
            if (SettingListActivity.access$600(SettingListActivity.this) != null)
            {
                SettingListActivity.access$600(SettingListActivity.this).unregisterCallback(SettingListActivity.access$700(SettingListActivity.this));
            }
            if (DLNA.instance().getServerManager() != null)
            {
                DLNA.instance().getServerManager().unregisterServerStatusListener(SettingListActivity.access$800(SettingListActivity.this));
            }
        }
        catch (RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        SettingListActivity.access$602(SettingListActivity.this, null);
        SettingListActivity.access$1002(SettingListActivity.this, false);
        SettingListActivity.access$900(SettingListActivity.this).postInvalidate();
    }

    r()
    {
        this$0 = SettingListActivity.this;
        super();
    }
}
