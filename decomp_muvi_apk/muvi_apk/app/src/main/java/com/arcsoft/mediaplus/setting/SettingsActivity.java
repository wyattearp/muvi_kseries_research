// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.MediaPlusBaseActivity;
import com.arcsoft.mediaplus.dmc.DmcUtils;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.ChangeRtspDlg;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            Settings, SettingListActivity, About

public class SettingsActivity extends MediaPlusBaseActivity
{
    class DeleteTask extends AsyncTask
    {

        final SettingsActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            if (as == null || as.length == 0)
            {
                return null;
            } else
            {
                FileUtils.closeCacheDB(SettingsActivity.this);
                FileUtils.deleteFolder(as[0]);
                return null;
            }
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            dismissDeletingDlg();
            updateFolderSize();
            toastClearCacheOk();
            FileUtils.recreateCacheMgr(SettingsActivity.this);
        }

        DeleteTask()
        {
            this$0 = SettingsActivity.this;
            super();
        }
    }

    class GetFolderSizeTask extends AsyncTask
    {

        final SettingsActivity this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            if (as == null || as.length == 0)
            {
                return null;
            } else
            {
                mCacheSize = FileUtils.getFolderSizeFormated(as[0]);
                return FileUtils.formatSize(mCacheSize);
            }
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            if (mCacheSizeView == null)
            {
                return;
            } else
            {
                updateCacheSize(s);
                return;
            }
        }

        GetFolderSizeTask()
        {
            this$0 = SettingsActivity.this;
            super();
        }
    }


    private static final int MSG_UPDATE_DEVICE_NAME = 1;
    private static final int MSG_UPDATE_SIZE = 0;
    public static final long ONE_MB = 0x100000L;
    static final boolean SUPPORT_DMR = false;
    private static final String TAG = "SettingsActivity";
    public static final int TYPE_DMR = 1;
    public static final int TYPE_DMS;
    private RelativeLayout mAboutBtn;
    private ImageView mBackBtn;
    private long mCacheSize;
    private TextView mCacheSizeView;
    private RelativeLayout mChangeRtspUrl;
    private TextView mClearCacheBtn;
    private TextView mClearDefaultRenderDev;
    private RelativeLayout mContentAccessBtn;
    private TextView mContentAccessVal;
    private String mContentAccessarray[];
    private int mCurContentAccess;
    private DeleteTask mDeleteTask;
    private LoadingDialog mDeletingDlg;
    private GetFolderSizeTask mGetFolderSizeTask;
    Handler mHandler;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final SettingsActivity this$0;

        public void onClick(View view)
        {
            switch (view.getId())
            {
            case 2131296511: 
            default:
                return;

            case 2131296504: 
                Intent intent2 = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingListActivity);
                intent2.putExtra("settingtype", 0);
                startActivityForResult(intent2, 0);
                return;

            case 2131296494: 
                Intent intent1 = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingListActivity);
                intent1.putExtra("settingtype", 1);
                startActivityForResult(intent1, 1);
                return;

            case 2131296509: 
                startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                return;

            case 2131296510: 
                Intent intent = new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/About);
                startActivity(intent);
                return;

            case 2131296503: 
                dealWithClearCacheClicked();
                return;

            case 2131296507: 
                updateSupportLivingView();
                updateSupportLivingViewItem();
                toastSupportLivingViewOk();
                return;

            case 2131296262: 
                finish();
                return;

            case 2131296506: 
                (new ChangeRtspDlg(SettingsActivity.this)).showDialog();
                return;

            case 2131296499: 
                DmcUtils.resetDefaultRender(SettingsActivity.this);
                return;

            case 2131296495: 
                showChoiceCmdView(0x7f0900ef, 0x7f0b006a, mCurContentAccess);
                return;
            }
        }

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
    };
    private PopupWindow mPopupWindow;
    private LinearLayout mResetDMCView;
    private RelativeLayout mSelectDeviceBtn;
    private TextView mSelectDeviceVal;
    private RelativeLayout mSelectRenderBtn;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final SettingsActivity this$0;

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
            if (mHandler != null)
            {
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessage(1);
            }
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            Log.e("SettingsActivity", "onServerMetaChanged");
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            if (mHandler != null)
            {
                mHandler.removeMessages(1);
                mHandler.sendEmptyMessage(1);
            }
        }

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
    };
    private RelativeLayout mSettingCMDBtn;
    private RelativeLayout mSupportLivingViewBtn;
    private TextView mSupportLivingViewSwitcher;
    private RelativeLayout mWiFiBtn;

    public SettingsActivity()
    {
        mSelectDeviceBtn = null;
        mSelectDeviceVal = null;
        mContentAccessBtn = null;
        mContentAccessVal = null;
        mSelectRenderBtn = null;
        mWiFiBtn = null;
        mAboutBtn = null;
        mClearCacheBtn = null;
        mClearDefaultRenderDev = null;
        mCacheSizeView = null;
        mSettingCMDBtn = null;
        mSupportLivingViewBtn = null;
        mResetDMCView = null;
        mSupportLivingViewSwitcher = null;
        mChangeRtspUrl = null;
        mBackBtn = null;
        mDeletingDlg = null;
        mDeleteTask = null;
        mGetFolderSizeTask = null;
        mCacheSize = 0L;
        mPopupWindow = null;
        mContentAccessarray = null;
        mCurContentAccess = -1;
        mHandler = new Handler() {

            final SettingsActivity this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 1: default 28
            //                           0 29
            //                           1 99;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                if (mCacheSizeView != null)
                {
                    mCacheSizeView.setText((String)message.obj);
                }
                if (mClearCacheBtn != null)
                {
                    TextView textview = mClearCacheBtn;
                    boolean flag;
                    if (0L != mCacheSize)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    textview.setEnabled(flag);
                    return;
                }
                continue; /* Loop/switch isn't completed */
_L3:
                if (mSelectDeviceVal != null)
                {
                    mSelectDeviceVal.setText(Settings.instance().getDefaultDMSName());
                    return;
                }
                if (true) goto _L1; else goto _L4
_L4:
            }

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
        };
    }

    private void showChoiceCmdView(final int curViewId, int i, int j)
    {
        String as[] = null;
        curViewId;
        JVM INSTR tableswitch 2131296495 2131296495: default 24
    //                   2131296495 196;
           goto _L1 _L2
_L1:
        View view = LayoutInflater.from(this).inflate(0x7f030032, null);
        ((TextView)view.findViewById(0x7f0900dd)).setText(getResources().getString(i));
        ((Button)view.findViewById(0x7f0900ed)).setOnClickListener(new android.view.View.OnClickListener() {

            final SettingsActivity this$0;

            public void onClick(View view1)
            {
                if (mPopupWindow != null)
                {
                    mPopupWindow.dismiss();
                }
            }

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
        });
        ListView listview = (ListView)view.findViewById(0x7f0900ec);
        ArrayAdapter arrayadapter = new ArrayAdapter(this, 0x7f030034, 0x7f090100, as);
        listview.setChoiceMode(1);
        listview.setAdapter(arrayadapter);
        listview.setItemChecked(j, true);
        if (as != null && as.length > 3)
        {
            android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)listview.getLayoutParams();
            layoutparams.height = getResources().getInteger(0x7f0a0011);
            listview.setLayoutParams(layoutparams);
            listview.setSelection(j);
        }
        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final SettingsActivity this$0;
            final int val$curViewId;

            public void onItemClick(AdapterView adapterview, View view1, int k, long l)
            {
                curViewId;
                JVM INSTR tableswitch 2131296495 2131296495: default 24
            //                           2131296495 45;
                   goto _L1 _L2
_L1:
                if (mPopupWindow != null)
                {
                    mPopupWindow.dismiss();
                }
                return;
_L2:
                mCurContentAccess = k;
                mContentAccessVal.setText(mContentAccessarray[k]);
                Settings.instance().setDefaultContentAccess(mCurContentAccess);
                if (true) goto _L1; else goto _L3
_L3:
            }

            
            {
                this$0 = SettingsActivity.this;
                curViewId = i;
                super();
            }
        });
        showPopWindow(view);
        return;
_L2:
        as = mContentAccessarray;
        if (true) goto _L1; else goto _L3
_L3:
    }

    private void showPopWindow(View view)
    {
        if (mPopupWindow == null)
        {
            mPopupWindow = new PopupWindow(view, -2, -2, true);
        }
        mPopupWindow.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener() {

            final SettingsActivity this$0;

            public void onDismiss()
            {
                mPopupWindow = null;
            }

            
            {
                this$0 = SettingsActivity.this;
                super();
            }
        });
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(0x7f0d0008);
        View view1 = getWindow().getDecorView();
        mPopupWindow.showAtLocation(view1, 129, 0, 0);
    }

    protected void SDCardEject()
    {
        super.SDCardEject();
        updateFolderSize();
    }

    void dealWithClearCacheClicked()
    {
        if (mCacheSize > 0x100000L)
        {
            showDeletingDlg();
            if (mDeleteTask != null)
            {
                mDeleteTask.cancel(true);
                mDeleteTask = null;
            }
            mDeleteTask = new DeleteTask();
            DeleteTask deletetask = mDeleteTask;
            String as[] = new String[1];
            as[0] = OEMConfig.CACHE_BASE_PATH;
            deletetask.execute(as);
            return;
        } else
        {
            FileUtils.closeCacheDB(this);
            FileUtils.deleteFolder(OEMConfig.CACHE_BASE_PATH);
            toastClearCacheOk();
            updateFolderSize();
            FileUtils.recreateCacheMgr(this);
            return;
        }
    }

    void dismissDeletingDlg()
    {
        if (mDeletingDlg == null)
        {
            return;
        } else
        {
            mDeletingDlg.dismiss();
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == -1)
        {
            if (1 == i)
            {
                String s1 = intent.getStringExtra("current_dmr");
                Log.v("zdf", (new StringBuilder()).append("current_dmr: ").append(s1).toString());
            } else
            if (i == 0)
            {
                String s = intent.getStringExtra("current_dms");
                Log.v("zdf", (new StringBuilder()).append("current_dms: ").append(s).toString());
                mSelectDeviceVal.setVisibility(0);
                mSelectDeviceVal.setText(s);
                String.format(getResources().getString(0x7f0b0185), new Object[] {
                    s
                });
                return;
            }
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030033);
        mSelectDeviceBtn = (RelativeLayout)findViewById(0x7f0900f8);
        mSelectDeviceVal = (TextView)findViewById(0x7f0900f9);
        mSelectRenderBtn = (RelativeLayout)findViewById(0x7f0900ee);
        mContentAccessBtn = (RelativeLayout)findViewById(0x7f0900ef);
        mContentAccessVal = (TextView)findViewById(0x7f0900f0);
        mSupportLivingViewBtn = (RelativeLayout)findViewById(0x7f0900fb);
        mSupportLivingViewSwitcher = (TextView)findViewById(0x7f0900fc);
        mChangeRtspUrl = (RelativeLayout)findViewById(0x7f0900fa);
        mClearDefaultRenderDev = (TextView)findViewById(0x7f0900f3);
        mResetDMCView = (LinearLayout)findViewById(0x7f0900f1);
        mWiFiBtn = (RelativeLayout)findViewById(0x7f0900fd);
        mAboutBtn = (RelativeLayout)findViewById(0x7f0900fe);
        mBackBtn = (ImageView)findViewById(0x7f090006);
        findViewById(0x7f0900f4).setVisibility(0);
        mCacheSizeView = (TextView)findViewById(0x7f0900f6);
        mClearCacheBtn = (TextView)findViewById(0x7f0900f7);
        mDeletingDlg = new LoadingDialog(this, 0x7f0d0004);
        mDeletingDlg.setCancelable(false);
        mDeletingDlg.setText(0x7f0b0071);
        if (mSelectDeviceBtn != null)
        {
            RelativeLayout relativelayout6 = mSelectDeviceBtn;
            RelativeLayout relativelayout;
            RelativeLayout relativelayout1;
            RelativeLayout relativelayout2;
            RelativeLayout relativelayout3;
            RelativeLayout relativelayout4;
            RelativeLayout relativelayout5;
            int l;
            RelativeLayout relativelayout7;
            android.view.View.OnClickListener onclicklistener3;
            if (false)
            {
                l = 0;
            } else
            {
                l = 8;
            }
            relativelayout6.setVisibility(l);
            relativelayout7 = mSelectDeviceBtn;
            if (false)
            {
                onclicklistener3 = mOnClickListener;
            } else
            {
                onclicklistener3 = null;
            }
            relativelayout7.setOnClickListener(onclicklistener3);
        }
        if (mSelectDeviceVal != null)
        {
            mSelectDeviceVal.setVisibility(0);
            mSelectDeviceVal.setText(Settings.instance().getDefaultDMSName());
        }
        if (mSelectRenderBtn != null)
        {
            relativelayout4 = mSelectRenderBtn;
            int k;
            android.view.View.OnClickListener onclicklistener2;
            if (false)
            {
                k = 0;
            } else
            {
                k = 8;
            }
            relativelayout4.setVisibility(k);
            relativelayout5 = mSelectRenderBtn;
            if (false)
            {
                onclicklistener2 = mOnClickListener;
            } else
            {
                onclicklistener2 = null;
            }
            relativelayout5.setOnClickListener(onclicklistener2);
        }
        if (mContentAccessBtn != null)
        {
            relativelayout2 = mContentAccessBtn;
            int j;
            android.view.View.OnClickListener onclicklistener1;
            if (true)
            {
                j = 0;
            } else
            {
                j = 8;
            }
            relativelayout2.setVisibility(j);
            relativelayout3 = mContentAccessBtn;
            if (true)
            {
                onclicklistener1 = mOnClickListener;
            } else
            {
                onclicklistener1 = null;
            }
            relativelayout3.setOnClickListener(onclicklistener1);
        }
        if (mContentAccessVal != null)
        {
            mContentAccessarray = getResources().getStringArray(0x7f06001f);
            mCurContentAccess = Settings.instance().getDefaultContentAccess();
            mContentAccessVal.setText(mContentAccessarray[mCurContentAccess]);
        }
        if (mSupportLivingViewBtn != null)
        {
            relativelayout = mSupportLivingViewBtn;
            int i = 0;
            android.view.View.OnClickListener onclicklistener;
            if (true)
            {
                i = 8;
            }
            relativelayout.setVisibility(i);
            relativelayout1 = mSupportLivingViewBtn;
            if (false)
            {
                onclicklistener = mOnClickListener;
            } else
            {
                onclicklistener = null;
            }
            relativelayout1.setOnClickListener(onclicklistener);
            updateSupportLivingViewItem();
        }
        if (DLNA.instance().getServerManager() != null)
        {
            DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
        }
        if (mChangeRtspUrl != null)
        {
            updateChangeRtspUrl();
        }
        if (mClearCacheBtn != null)
        {
            mClearCacheBtn.setOnClickListener(mOnClickListener);
        }
        if (mResetDMCView != null)
        {
            mResetDMCView.setVisibility(8);
        }
        if (mClearDefaultRenderDev == null);
        if (mWiFiBtn != null)
        {
            mWiFiBtn.setOnClickListener(mOnClickListener);
        }
        if (mAboutBtn != null)
        {
            mAboutBtn.setVisibility(8);
            mAboutBtn.setOnClickListener(null);
        }
        if (mBackBtn != null)
        {
            mBackBtn.setOnClickListener(mOnClickListener);
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if (DLNA.instance().getServerManager() != null)
        {
            DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
        }
        Settings.instance().setDefaultContentAccess(mCurContentAccess);
        mSelectDeviceBtn = null;
        mSelectRenderBtn = null;
        mWiFiBtn = null;
        mAboutBtn = null;
        mClearCacheBtn = null;
        mBackBtn = null;
        mDeletingDlg = null;
        mCacheSizeView = null;
        if (mGetFolderSizeTask != null)
        {
            mGetFolderSizeTask.cancel(true);
            mGetFolderSizeTask = null;
        }
        if (mDeleteTask != null)
        {
            mDeleteTask.cancel(true);
            mDeleteTask = null;
        }
    }

    protected void onResume()
    {
        super.onResume();
        updateFolderSize();
        if (mHandler != null)
        {
            mHandler.removeMessages(1);
            mHandler.sendEmptyMessage(1);
        }
    }

    void showDeletingDlg()
    {
        if (mDeletingDlg == null)
        {
            return;
        } else
        {
            mDeletingDlg.show();
            return;
        }
    }

    void toastClearCacheOk()
    {
        Toast.makeText(this, getString(0x7f0b018b), 0).show();
    }

    void toastSupportLivingViewOk()
    {
        Toast.makeText(this, getString(0x7f0b019e), 0).show();
    }

    void updateCacheSize(String s)
    {
        if (s == null || mHandler == null)
        {
            return;
        } else
        {
            Message message = new Message();
            message.what = 0;
            message.obj = s;
            mHandler.sendMessage(message);
            return;
        }
    }

    void updateChangeRtspUrl()
    {
        if (false)
        {
            if (getSharedPreferences(getPackageName(), 0).getBoolean("key_support_living_view", true))
            {
                mChangeRtspUrl.setVisibility(0);
                mChangeRtspUrl.setOnClickListener(mOnClickListener);
                return;
            } else
            {
                mChangeRtspUrl.setVisibility(8);
                mChangeRtspUrl.setOnClickListener(null);
                return;
            }
        } else
        {
            mChangeRtspUrl.setVisibility(8);
            mChangeRtspUrl.setOnClickListener(null);
            return;
        }
    }

    void updateFolderSize()
    {
        if (mGetFolderSizeTask != null)
        {
            mGetFolderSizeTask.cancel(true);
            mGetFolderSizeTask = null;
        }
        mGetFolderSizeTask = new GetFolderSizeTask();
        GetFolderSizeTask getfoldersizetask = mGetFolderSizeTask;
        String as[] = new String[1];
        as[0] = OEMConfig.CACHE_BASE_PATH;
        getfoldersizetask.execute(as);
    }

    void updateSupportLivingView()
    {
        boolean flag = true;
        SharedPreferences sharedpreferences = getSharedPreferences(getPackageName(), 0);
        boolean flag1 = sharedpreferences.getBoolean("key_support_living_view", flag);
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        if (flag1)
        {
            flag = false;
        }
        editor.putBoolean("key_support_living_view", flag);
        editor.commit();
    }

    void updateSupportLivingViewItem()
    {
        boolean flag = getSharedPreferences(getPackageName(), 0).getBoolean("key_support_living_view", true);
        Log.e("FENG", (new StringBuilder()).append("FENG updateSupportLivingViewItem() supportLiving: ").append(flag).toString());
        int i = (int)getResources().getDimension(0x7f0800e7);
        Log.e("SettingsActivity", (new StringBuilder()).append("updateSupportLivingViewItem padding = ").append(i).toString());
        if (flag)
        {
            if (mSupportLivingViewSwitcher != null)
            {
                mSupportLivingViewSwitcher.setBackgroundResource(0x7f020288);
                mSupportLivingViewSwitcher.setText(0x7f0b01a0);
                mSupportLivingViewSwitcher.setGravity(19);
                mSupportLivingViewSwitcher.setPadding(i + 2, 0, 0, 0);
            }
        } else
        if (mSupportLivingViewSwitcher != null)
        {
            mSupportLivingViewSwitcher.setBackgroundResource(0x7f020287);
            mSupportLivingViewSwitcher.setText(0x7f0b01a1);
            mSupportLivingViewSwitcher.setGravity(21);
            mSupportLivingViewSwitcher.setPadding(0, 0, i, 0);
            return;
        }
    }



/*
    static int access$002(SettingsActivity settingsactivity, int i)
    {
        settingsactivity.mCurContentAccess = i;
        return i;
    }

*/




/*
    static PopupWindow access$202(SettingsActivity settingsactivity, PopupWindow popupwindow)
    {
        settingsactivity.mPopupWindow = popupwindow;
        return popupwindow;
    }

*/





/*
    static long access$502(SettingsActivity settingsactivity, long l)
    {
        settingsactivity.mCacheSize = l;
        return l;
    }

*/



}
