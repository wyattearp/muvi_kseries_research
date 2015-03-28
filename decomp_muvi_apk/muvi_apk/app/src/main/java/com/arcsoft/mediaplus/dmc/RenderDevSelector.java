// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.mediaplus.service.util.IDlnaSettingCallback;
import com.arcsoft.mediaplus.setting.DigitalMediaAdapter;
import com.arcsoft.mediaplus.setting.DigitalMediaItem;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcUtils, DmcPlayback, RenderAdapter

public class RenderDevSelector
    implements com.arcsoft.mediaplus.setting.DigitalMediaAdapter.IDMPStatusChanged, android.widget.PopupWindow.OnDismissListener
{
    public static interface IRenderSelectorDismiss
    {

        public abstract void cancelRenderSelector();
    }


    public static boolean CURRENT_PROVIDER_FROM_LOCAL = true;
    public static DmcUtils.PROVIDER_TYPE CURRENT_PROVIDER_TYPE;
    private final String TAG = "Dmc";
    private boolean mAlwaysPlay2Render;
    private boolean mBindServiceSuccessful;
    private IDlnaSettingBinder mBinder;
    private LinearLayout mBottom;
    private final IDlnaSettingCallback mCallback = new com.arcsoft.mediaplus.service.util.IDlnaSettingCallback.Stub() {

        final RenderDevSelector this$0;

        public void onDmrOffline(String s)
            throws RemoteException
        {
            Log.d("Dmc", (new StringBuilder()).append(" RenderDevSelector onDmrOffline : strUDN = ").append(s).toString());
            Message message = Message.obtain();
            message.what = 8;
            message.obj = s;
            mHandler.sendMessage(message);
        }

        public void onDmrOnline(String s)
            throws RemoteException
        {
            Log.d("Dmc", (new StringBuilder()).append(" RenderDevSelector onDmrOnline : strUDN = ").append(s).toString());
            Message message = Message.obtain();
            message.what = 7;
            message.obj = s;
            mHandler.sendMessage(message);
        }

        public void onDmsOffline(String s)
            throws RemoteException
        {
        }

        public void onDmsOnline(String s)
            throws RemoteException
        {
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private CheckBox mCheckBox;
    private final android.widget.CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new android.widget.CompoundButton.OnCheckedChangeListener() {

        final RenderDevSelector this$0;

        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            Log.d("Dmc", (new StringBuilder()).append("onCheckedChanged  isChecked = ").append(flag).toString());
            mAlwaysPlay2Render = flag;
            if (mAlwaysPlay2Render)
            {
                Log.d("Dmc", (new StringBuilder()).append("RenderDev  mUseTempRender = ").append(mUseTempRender).toString());
                mUseTempRender = false;
            }
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private final ServiceConnection mConnection = new ServiceConnection() {

        final RenderDevSelector this$0;

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            mBinder = com.arcsoft.mediaplus.service.util.IDlnaSettingBinder.Stub.asInterface(ibinder);
            try
            {
                Log.d("Dmc", " RenderDevSelector onServiceConnected");
                mBinder.registerCallback(mCallback);
                DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
                mBindServiceSuccessful = true;
                bindListView();
                if (needShowIfSvcNotReady)
                {
                    start();
                }
                return;
            }
            catch (RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
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
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private Activity mContext;
    private int mCurrentIndex;
    private com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter mDataSourceFilter;
    private AlertDialog mDefaultOffLineDlg;
    private IRenderSelectorDismiss mDismissListener;
    private DigitalMediaAdapter mDmrAdapter;
    private final Handler mHandler = new Handler() {

        final RenderDevSelector this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 8: default 52
        //                       1 53
        //                       2 52
        //                       3 52
        //                       4 52
        //                       5 87
        //                       6 108
        //                       7 154
        //                       8 182;
               goto _L1 _L2 _L1 _L1 _L1 _L3 _L4 _L5 _L6
_L1:
            return;
_L2:
            if (mSelectorWindow != null && mSelectorWindow.isShowing())
            {
                mSelectorWindow.dismiss();
                return;
            }
            continue; /* Loop/switch isn't completed */
_L3:
            if (mDmrAdapter != null)
            {
                mDmrAdapter.notifyDataSetChanged();
                return;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if (mContext != null && !mToastNoItem)
            {
                Toast.makeText(mContext, 0x7f0b0058, 0).show();
                mToastNoItem = true;
                return;
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if (mDmrAdapter != null)
            {
                mDmrAdapter.dmrOnLine((String)message.obj);
                return;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            if (mDmrAdapter != null)
            {
                mDmrAdapter.dmrOffLine((String)message.obj);
                return;
            }
            if (true) goto _L1; else goto _L7
_L7:
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private int mHeight;
    private boolean mInitAReady;
    private boolean mIsSelected;
    private final android.widget.AdapterView.OnItemClickListener mListItemClickListener = new android.widget.AdapterView.OnItemClickListener() {

        final RenderDevSelector this$0;

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            mIsSelected = true;
            hide();
            startPlaybackIntent(i);
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private ListView mListView;
    protected View mMainView;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final RenderDevSelector this$0;

        public void onClick(View view)
        {
            if (mCheckBox != null)
            {
                RenderDevSelector renderdevselector = RenderDevSelector.this;
                boolean flag;
                if (!mCheckBox.isChecked())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                renderdevselector.updateCheckBox(flag);
            }
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private PopupWindow mSelectorWindow;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final RenderDevSelector this$0;

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
            if (mediaserverdesc != null)
            {
                if (mediaserverdesc.m_DeviceIcon != null);
            }
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
    };
    private boolean mToastNoItem;
    private boolean mUseTempRender;
    private int mWidth;
    private boolean needShowIfSvcNotReady;

    public RenderDevSelector(Activity activity)
    {
        mSelectorWindow = null;
        mListView = null;
        mCheckBox = null;
        mBottom = null;
        mContext = null;
        mMainView = null;
        mDmrAdapter = null;
        mToastNoItem = false;
        mBinder = null;
        mBindServiceSuccessful = false;
        mDataSourceFilter = null;
        mAlwaysPlay2Render = false;
        mUseTempRender = false;
        needShowIfSvcNotReady = false;
        mCurrentIndex = 0;
        mHeight = 376;
        mWidth = 411;
        mDismissListener = null;
        mIsSelected = false;
        mDefaultOffLineDlg = null;
        mInitAReady = false;
        mContext = activity;
        init();
    }

    private void bindDLNAService()
    {
        try
        {
            Intent intent = new Intent("MUVI.MediaPlus.DLNA.SERVICE");
            mContext.bindService(intent, mConnection, 1);
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    private void bindListView()
    {
        creatAdapter();
        mListView.setAdapter(mDmrAdapter);
        mInitAReady = true;
    }

    private void hide()
    {
        if (mSelectorWindow != null && mSelectorWindow.isShowing())
        {
            mSelectorWindow.dismiss();
        }
    }

    private void init()
    {
        if (mContext != null)
        {
            int ai[] = mContext.getResources().getIntArray(0x7f060004);
            if (ai != null)
            {
                mWidth = ai[0];
                mHeight = ai[1];
            }
            mMainView = mContext.getLayoutInflater().inflate(0x7f03002d, null);
            if (mMainView != null)
            {
                mCheckBox = (CheckBox)mMainView.findViewById(0x7f0900dc);
                mBottom = (LinearLayout)mMainView.findViewById(0x7f0900db);
                if (mBottom != null)
                {
                    mBottom.setOnClickListener(mOnClickListener);
                }
                if (mCheckBox != null)
                {
                    mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
                }
                mListView = (ListView)mMainView.findViewById(0x7f0900da);
                mListView.setOnItemClickListener(mListItemClickListener);
                mSelectorWindow = new PopupWindow(mMainView, mWidth, mHeight);
                mSelectorWindow.setBackgroundDrawable(new BitmapDrawable());
                mSelectorWindow.setOutsideTouchable(true);
                mSelectorWindow.setTouchable(true);
                mSelectorWindow.setFocusable(true);
                mSelectorWindow.setOnDismissListener(this);
                mToastNoItem = false;
                bindDLNAService();
                return;
            }
        }
    }

    private boolean isDefaultDmrOnLine(String s)
    {
        return DLNA.instance().getRenderManager().isRenderOnline(s);
    }

    private void selectTempRender()
    {
        if (mContext == null)
        {
            return;
        }
        if (mDefaultOffLineDlg == null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            String s = mContext.getString(0x7f0b0018);
            Object aobj[] = new Object[1];
            aobj[0] = DmcUtils.getDefaultRenderName(mContext);
            String s1 = stringbuilder.append(String.format(s, aobj)).append(mContext.getString(0x7f0b01e9)).toString();
            mDefaultOffLineDlg = (new android.app.AlertDialog.Builder(mContext)).setMessage(s1).setPositiveButton(mContext.getString(0x7f0b001f), new android.content.DialogInterface.OnClickListener() {

                final RenderDevSelector this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    mUseTempRender = true;
                    mCheckBox.setChecked(false);
                    show();
                }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
            }).setNegativeButton(mContext.getString(0x7f0b0020), new android.content.DialogInterface.OnClickListener() {

                final RenderDevSelector this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                }

            
            {
                this$0 = RenderDevSelector.this;
                super();
            }
            }).create();
        }
        mDefaultOffLineDlg.show();
    }

    private void show()
    {
        mIsSelected = false;
        mToastNoItem = false;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int i = (displaymetrics.heightPixels - mHeight) / 2;
        int j = (displaymetrics.widthPixels - mWidth) / 2;
        if (mSelectorWindow != null)
        {
            mSelectorWindow.showAtLocation(new View(mContext), 51, j, i);
        }
    }

    private void startPlaybackByDefault()
    {
        String s = DmcUtils.getDefaultRenderUDN(mContext);
        if (!isDefaultDmrOnLine(s))
        {
            selectTempRender();
            return;
        } else
        {
            Intent intent = new Intent(mContext, com/arcsoft/mediaplus/dmc/DmcPlayback);
            intent.putExtra("datasource_filter", mDataSourceFilter);
            intent.putExtra("default_render_name", DmcUtils.getDefaultRenderName(mContext));
            intent.putExtra("default_render_udn", s);
            intent.putExtra("alway_play_to", true);
            intent.putExtra("play_from_index", mCurrentIndex);
            mContext.startActivity(intent);
            return;
        }
    }

    private void startPlaybackIntent(int i)
    {
        if (mContext == null) goto _L2; else goto _L1
_L1:
        Intent intent;
        intent = new Intent(mContext, com/arcsoft/mediaplus/dmc/DmcPlayback);
        intent.putExtra("datasource_filter", mDataSourceFilter);
        if (mDmrAdapter == null) goto _L4; else goto _L3
_L3:
        DigitalMediaItem digitalmediaitem;
        digitalmediaitem = (DigitalMediaItem)mDmrAdapter.getItem(i);
        if (digitalmediaitem != null)
        {
            intent.putExtra("default_render_name", digitalmediaitem.name);
            intent.putExtra("default_render_udn", digitalmediaitem.udn);
        }
        if (!mAlwaysPlay2Render || mUseTempRender) goto _L6; else goto _L5
_L5:
        DmcUtils.saveDefaultRenderInfo(mContext, digitalmediaitem);
_L4:
        if (CURRENT_PROVIDER_TYPE == DmcUtils.PROVIDER_TYPE.TYPE_FROM_LARGE_VIEW)
        {
            intent.putExtra("play_from_index", mCurrentIndex);
        }
        mContext.startActivity(intent);
        mHandler.sendEmptyMessageDelayed(1, 200L);
_L2:
        return;
_L6:
        if (!mAlwaysPlay2Render)
        {
            DmcUtils.resetDefaultRender(mContext);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    private void unbindDLNAService()
    {
        try
        {
            if (mConnection != null && mBinder != null && mBindServiceSuccessful)
            {
                mContext.unbindService(mConnection);
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
        }
    }

    private void updateCheckBox(boolean flag)
    {
        if (mCheckBox != null)
        {
            mCheckBox.setChecked(flag);
        }
    }

    protected void creatAdapter()
    {
        if (mDmrAdapter == null)
        {
            mDmrAdapter = new RenderAdapter(mContext, 1, mBinder);
            mDmrAdapter.setStatusChangedListener(this);
        }
    }

    public void destroy()
    {
        unbindDLNAService();
        if (mSelectorWindow != null)
        {
            if (mSelectorWindow.isShowing())
            {
                mSelectorWindow.dismiss();
            }
            mSelectorWindow.setOnDismissListener(null);
            mSelectorWindow = null;
        }
        if (mDismissListener != null)
        {
            mDismissListener = null;
        }
        if (mDefaultOffLineDlg != null)
        {
            mDefaultOffLineDlg.dismiss();
            mDefaultOffLineDlg = null;
        }
    }

    public void notifyChanged()
    {
        Message message = Message.obtain();
        message.what = 5;
        mHandler.sendMessage(message);
        Log.d("Dmc", " RenderDevSelector notifyChanged");
    }

    public void notifyDismiss()
    {
        mHandler.removeMessages(6);
        Message message = Message.obtain();
        message.what = 6;
        mHandler.sendMessage(message);
    }

    public void onConfigurationChanged()
    {
        if (mSelectorWindow != null && mSelectorWindow.isShowing())
        {
            mSelectorWindow.dismiss();
            show();
        }
    }

    public void onDismiss()
    {
        Log.d("Dmc", (new StringBuilder()).append("renderSelectorDismiss :  selected = ").append(mIsSelected).toString());
        if (!mIsSelected && mDismissListener != null)
        {
            mDismissListener.cancelRenderSelector();
        }
    }

    public void postInvalidate(int i)
    {
        if (mListView != null)
        {
            View view = mListView.getChildAt(i);
            if (view != null)
            {
                view.postInvalidate();
                Log.d("Dmc", (new StringBuilder()).append(" RenderDevSelector postInvalidate index : ").append(i).toString());
            }
        }
    }

    public void setCurrentIndex(int i)
    {
        mCurrentIndex = i;
    }

    public void setDataSourceFilter(com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter)
    {
        mDataSourceFilter = datasourcefilter;
    }

    public void setItemChecked(int i)
    {
    }

    public void setSelectorDisListener(IRenderSelectorDismiss irenderselectordismiss)
    {
        mDismissListener = irenderselectordismiss;
    }

    public void start()
    {
        mAlwaysPlay2Render = DmcUtils.alwaysPlay2DefaultRender(mContext);
        updateCheckBox(mAlwaysPlay2Render);
        if (mAlwaysPlay2Render)
        {
            startPlaybackByDefault();
            return;
        }
        if (mInitAReady)
        {
            show();
            return;
        } else
        {
            needShowIfSvcNotReady = true;
            return;
        }
    }

    static 
    {
        CURRENT_PROVIDER_TYPE = DmcUtils.PROVIDER_TYPE.TYPE_FROM_LARGE_VIEW;
    }



/*
    static boolean access$002(RenderDevSelector renderdevselector, boolean flag)
    {
        renderdevselector.mAlwaysPlay2Render = flag;
        return flag;
    }

*/




/*
    static boolean access$1002(RenderDevSelector renderdevselector, boolean flag)
    {
        renderdevselector.mToastNoItem = flag;
        return flag;
    }

*/


/*
    static boolean access$102(RenderDevSelector renderdevselector, boolean flag)
    {
        renderdevselector.mUseTempRender = flag;
        return flag;
    }

*/




/*
    static IDlnaSettingBinder access$1202(RenderDevSelector renderdevselector, IDlnaSettingBinder idlnasettingbinder)
    {
        renderdevselector.mBinder = idlnasettingbinder;
        return idlnasettingbinder;
    }

*/




/*
    static boolean access$1502(RenderDevSelector renderdevselector, boolean flag)
    {
        renderdevselector.mBindServiceSuccessful = flag;
        return flag;
    }

*/







/*
    static boolean access$402(RenderDevSelector renderdevselector, boolean flag)
    {
        renderdevselector.mIsSelected = flag;
        return flag;
    }

*/





}
