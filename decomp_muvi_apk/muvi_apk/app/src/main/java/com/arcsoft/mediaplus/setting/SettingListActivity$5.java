// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingListActivity

class ack.Stub extends com.arcsoft.mediaplus.service.util.tub
{

    final SettingListActivity this$0;

    public void onDmrOffline(String s)
        throws RemoteException
    {
        Log.w(TAG, (new StringBuilder()).append("onDmrOffline(): ").append(s).toString());
    }

    public void onDmrOnline(String s)
        throws RemoteException
    {
        Log.w(TAG, (new StringBuilder()).append("onDmrOnline(): ").append(s).toString());
    }

    public void onDmsOffline(String s)
        throws RemoteException
    {
        Log.w(TAG, (new StringBuilder()).append("onDmsOffline(): ").append(s).toString());
        Message message = Message.obtain();
        message.what = 773;
        message.obj = s;
        SettingListActivity.access$500(SettingListActivity.this).sendMessage(message);
    }

    public void onDmsOnline(String s)
        throws RemoteException
    {
        Log.w(TAG, (new StringBuilder()).append("onDmsOnline(): ").append(s).toString());
        Message message = Message.obtain();
        message.what = 774;
        message.obj = s;
        SettingListActivity.access$500(SettingListActivity.this).sendMessage(message);
    }

    ack.Stub()
    {
        this$0 = SettingListActivity.this;
        super();
    }
}
