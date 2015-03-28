// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.mediaplus.service.util.IDlnaSettingCallback;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            DigitalMediaAdapter, DigitalMediaItem

public class SettingListActivity extends Activity
    implements DigitalMediaAdapter.IDMPStatusChanged
{

    private static final int MSG_DISMISS_DIALOG = 770;
    private static final int MSG_DMR_OFFLINE = 771;
    private static final int MSG_DMR_ONLINE = 772;
    private static final int MSG_DMS_OFFLINE = 773;
    private static final int MSG_DMS_ONLINE = 774;
    private static final int MSG_NOTIFY_CHANGE = 769;
    protected String TAG;
    private DigitalMediaAdapter mAdapter;
    private boolean mBindServiceSuccessful;
    private IDlnaSettingBinder mBinder;
    private final IDlnaSettingCallback mCallback = new com.arcsoft.mediaplus.service.util.IDlnaSettingCallback.Stub() {

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
            mHandler.sendMessage(message);
        }

        public void onDmsOnline(String s)
            throws RemoteException
        {
            Log.w(TAG, (new StringBuilder()).append("onDmsOnline(): ").append(s).toString());
            Message message = Message.obtain();
            message.what = 774;
            message.obj = s;
            mHandler.sendMessage(message);
        }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
    };
    public ServiceConnection mConnection;
    private int mCurType;
    private final Handler mHandler = new Handler() {

        final SettingListActivity this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 769 774: default 48
        //                       769 49
        //                       770 60
        //                       771 48
        //                       772 48
        //                       773 115
        //                       774 143;
               goto _L1 _L2 _L3 _L1 _L1 _L4 _L5
_L1:
            return;
_L2:
            ((DigitalMediaAdapter)message.obj).notifyDataSetChanged();
            return;
_L3:
            if (!mToastNoItem)
            {
                int i;
                if (((DigitalMediaAdapter)message.obj).getType() == 1)
                {
                    i = 0x7f0b0058;
                } else
                {
                    i = 0x7f0b0057;
                }
                Toast.makeText(SettingListActivity.this, i, 0).show();
                mToastNoItem = true;
                return;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if (mAdapter != null)
            {
                mAdapter.dmsOffLine((String)message.obj);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if (mAdapter != null)
            {
                mAdapter.dmsOnLine((String)message.obj);
                return;
            }
            if (true) goto _L1; else goto _L6
_L6:
        }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
    };
    private ListView mListView;
    private TextView mNavigationTitle;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final SettingListActivity this$0;

        public void onClick(View view)
        {
            switch (view.getId())
            {
            default:
                return;

            case 2131296487: 
                finish();
                break;
            }
        }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
    };
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final SettingListActivity this$0;

        public void OnDestroyObject(String s, int i)
        {
        }

        public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordTasks dataonrecordtasks, int i)
        {
        }

        public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordSchedule dataonrecordschedule, int i)
        {
        }

        public void OnDigaDeleteRecordSchedule(String s, int i)
        {
        }

        public void OnDigaDisableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaEnableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
        {
        }

        public void onGetSearchCapabilities(String s, String s1, int i)
        {
        }

        public void onGetSortCapabilities(String s, String s1, int i)
        {
        }

        public void onServerAdded(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            Log.e(TAG, "settingList onServerMetaChanged");
            if (mediaserverdesc != null)
            {
                if (mediaserverdesc.m_DeviceIcon != null);
            }
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            Log.e(TAG, "mServerStatusListener onServerRemoved");
        }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
    };
    private boolean mToastNoItem;

    public SettingListActivity()
    {
        mListView = null;
        mAdapter = null;
        mNavigationTitle = null;
        mCurType = -1;
        mToastNoItem = false;
        mBinder = null;
        mBindServiceSuccessful = false;
        TAG = "SettingListActivity";
        mConnection = new ServiceConnection() {

            final SettingListActivity this$0;

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.e(TAG, "onServiceConnected()");
                mBinder = com.arcsoft.mediaplus.service.util.IDlnaSettingBinder.Stub.asInterface(ibinder);
                try
                {
                    mBinder.registerCallback(mCallback);
                    DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
                    mAdapter = new DigitalMediaAdapter(SettingListActivity.this, mCurType, mBinder);
                    mAdapter.setStatusChangedListener(SettingListActivity.this);
                    mListView.setAdapter(mAdapter);
                    setItemChecked(mAdapter.getActiveIndex());
                }
                catch (RemoteException remoteexception) { }
                mBindServiceSuccessful = true;
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.e(TAG, "onServiceDisconnected()");
                try
                {
                    if (mBinder != null)
                    {
                        mBinder.unregisterCallback(mCallback);
                    }
                    if (DLNA.instance().getServerManager() != null)
                    {
                        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
                    }
                }
                catch (RemoteException remoteexception)
                {
                    remoteexception.printStackTrace();
                }
                mBinder = null;
                mBindServiceSuccessful = false;
                mListView.postInvalidate();
            }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
        };
    }

    private void bindDLNAService()
    {
        try
        {
            bindService(new Intent("MUVI.MediaPlus.DLNA.SERVICE"), mConnection, 1);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    private void onDMRItemSelected()
    {
        int i = mListView.getCheckedItemPosition();
        DigitalMediaItem digitalmediaitem = (DigitalMediaItem)mAdapter.getItem(i);
        if (digitalmediaitem == null)
        {
            return;
        }
        mAdapter.setActiveIndex(i);
        String s = digitalmediaitem.udn;
        Intent intent;
        if (s != null && mBindServiceSuccessful && mBinder != null)
        {
            try
            {
                mBinder.setActiveDmr(s);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        intent = new Intent();
        intent.putExtra("current_dmr", digitalmediaitem.name);
        setResult(-1, intent);
        finish();
    }

    private void onDMSItemSelected()
    {
        int i = mListView.getCheckedItemPosition();
        DigitalMediaItem digitalmediaitem = (DigitalMediaItem)mAdapter.getItem(i);
        if (digitalmediaitem == null)
        {
            return;
        }
        mAdapter.setActiveIndex(i);
        String s = digitalmediaitem.udn;
        Intent intent;
        if (s != null && mBindServiceSuccessful && mBinder != null)
        {
            try
            {
                mBinder.setActiveDms(s);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        intent = new Intent();
        intent.putExtra("current_dms", digitalmediaitem.name);
        setResult(-1, intent);
        finish();
    }

    private void unbindDLNAService()
    {
        try
        {
            if (mConnection != null && mBinder != null && mBindServiceSuccessful)
            {
                unbindService(mConnection);
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    public void notifyChanged()
    {
        Message message = Message.obtain();
        message.what = 769;
        message.obj = mAdapter;
        mHandler.sendMessage(message);
        Log.d(TAG, " SettingListActivity notifyChanged");
    }

    public void notifyDismiss()
    {
        mHandler.removeMessages(770);
        Message message = Message.obtain();
        message.what = 770;
        message.obj = mAdapter;
        mHandler.sendMessage(message);
        Log.d(TAG, " SettingListActivity notifyDismiss");
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030030);
        mNavigationTitle = (TextView)findViewById(0x7f090007);
        mListView = (ListView)findViewById(0x7f0900e8);
        mCurType = getIntent().getIntExtra("settingtype", 0);
        if (mNavigationTitle != null)
        {
            TextView textview = mNavigationTitle;
            int i;
            if (mCurType == 1)
            {
                i = 0x7f0b0077;
            } else
            {
                i = 0x7f0b0068;
            }
            textview.setText(i);
        }
        bindDLNAService();
        mListView.setChoiceMode(1);
        mListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final SettingListActivity this$0;

            public void onItemClick(AdapterView adapterview, View view, int j, long l)
            {
                if (mCurType == 1)
                {
                    onDMRItemSelected();
                    return;
                } else
                {
                    onDMSItemSelected();
                    return;
                }
            }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
        });
        findViewById(0x7f0900e9).setOnClickListener(new android.view.View.OnClickListener() {

            final SettingListActivity this$0;

            public void onClick(View view)
            {
                mAdapter.reSearch();
                mToastNoItem = false;
            }

            
            {
                this$0 = SettingListActivity.this;
                super();
            }
        });
        findViewById(0x7f0900e7).setOnClickListener(mOnClickListener);
    }

    protected void onDestroy()
    {
        unbindDLNAService();
        mNavigationTitle = null;
        try
        {
            if (mBinder != null)
            {
                mBinder.unregisterCallback(mCallback);
                mBinder = null;
                mBindServiceSuccessful = false;
            }
        }
        catch (RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
        }
        super.onDestroy();
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public void postInvalidate(int i)
    {
        if (mListView != null)
        {
            View view = mListView.getChildAt(i);
            if (view != null)
            {
                view.postInvalidate();
                Log.d(TAG, (new StringBuilder()).append(" SettingListActivity postInvalidate index : ").append(i).toString());
            }
        }
    }

    public void setItemChecked(int i)
    {
        if (mListView != null)
        {
            int j = 0;
            while (j < mListView.getCount()) 
            {
                ListView listview = mListView;
                boolean flag;
                if (j == i)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                listview.setItemChecked(j, flag);
                j++;
            }
        }
    }




/*
    static boolean access$1002(SettingListActivity settinglistactivity, boolean flag)
    {
        settinglistactivity.mBindServiceSuccessful = flag;
        return flag;
    }

*/




/*
    static DigitalMediaAdapter access$302(SettingListActivity settinglistactivity, DigitalMediaAdapter digitalmediaadapter)
    {
        settinglistactivity.mAdapter = digitalmediaadapter;
        return digitalmediaadapter;
    }

*/



/*
    static boolean access$402(SettingListActivity settinglistactivity, boolean flag)
    {
        settinglistactivity.mToastNoItem = flag;
        return flag;
    }

*/




/*
    static IDlnaSettingBinder access$602(SettingListActivity settinglistactivity, IDlnaSettingBinder idlnasettingbinder)
    {
        settinglistactivity.mBinder = idlnasettingbinder;
        return idlnasettingbinder;
    }

*/



}
