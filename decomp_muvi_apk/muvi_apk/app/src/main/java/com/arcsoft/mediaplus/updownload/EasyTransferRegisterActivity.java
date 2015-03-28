// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.setting.EasyTransferSettingActivity;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            EasyTransferRegisterListView

public class EasyTransferRegisterActivity extends Activity
{

    public static final String EXTRA_COMMAND_ALLOW = "allow";
    public static final String EXTRA_COMMAND_EDIT = "easytransfer";
    public static final String EXTRA_COMMAND_HOUR = "hour";
    public static final String EXTRA_COMMAND_MINUTE = "minute";
    public static final String EXTRA_COMMAND_PLUGIN = "plugin";
    public static final String EXTRA_COMMAND_RECORDDAY = "recordday";
    public static final String EXTRA_COMMAND_SERVERNAME = "server_name";
    public static final String EXTRA_COMMAND_SERVERUDN = "server_udn";
    public static final String EXTRA_EDIT = "edit";
    public static final String EXTRA_REGISTER = "register";
    public static final int REQUEST_EASYTRANSFER_SET = 1;
    private final int DIALOG_ID_EASYTRANSFER_CANCEL = 4;
    private final int DIALOG_ID_EASYTRANSFER_CONFIRM_CANCEL = 5;
    private final int DIALOG_ID_EASYTRANSFER_CONFIRM_DELETE = 3;
    private final int DIALOG_ID_EASYTRANSFER_CONFIRM_EXECUTE = 2;
    private final int DIALOG_ID_EASYTRANSFER_DESTINATION = 0;
    private final int DIALOG_ID_EASYTRANSFER_EXECUTE_DELETE = 1;
    private final String TAG = "EasyTransferActivity";
    private Button mBtnRegister;
    private Context mContext;
    private IEasyTransferEngine mEasyTransferEngine;
    Handler mHandler;
    private EasyTransferRegisterListView.IListItemClickListener mItemClickListener;
    com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.IOnEasyTransferEngineListener mLisener;
    private EasyTransferRegisterListView mListView;
    private RelativeLayout mNoRegisterView;
    private android.view.View.OnClickListener mRegisterNewOnClickListener;
    private DLNAService mUpDownloadServiceBinder;
    private ServiceConnection mUpdownloadConnection;

    public EasyTransferRegisterActivity()
    {
        mContext = null;
        mListView = null;
        mBtnRegister = null;
        mNoRegisterView = null;
        mHandler = new Handler(new android.os.Handler.Callback() {

            final EasyTransferRegisterActivity this$0;

            public boolean handleMessage(Message message)
            {
                if (mListView != null)
                {
                    mListView.invalidateViews();
                }
                return true;
            }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
        });
        mRegisterNewOnClickListener = new android.view.View.OnClickListener() {

            final EasyTransferRegisterActivity this$0;

            public void onClick(View view)
            {
                if (mEasyTransferEngine == null || Settings.instance().getDefaultDMSUDN() == null)
                {
                    return;
                } else
                {
                    Intent intent = new Intent(EasyTransferRegisterActivity.this, com/arcsoft/mediaplus/setting/EasyTransferSettingActivity);
                    intent.putExtra("easytransfer", "register");
                    intent.putExtra("server_udn", Settings.instance().getDefaultDMSUDN());
                    intent.putExtra("server_name", Settings.instance().getDefaultDMSName());
                    ((Activity)mContext).startActivityForResult(intent, 1);
                    return;
                }
            }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
        };
        mItemClickListener = new EasyTransferRegisterListView.IListItemClickListener() {

            final EasyTransferRegisterActivity this$0;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Result result;
                if (mEasyTransferEngine != null)
                {
                    if ((result = mEasyTransferEngine.getEasyTransfer(l)) != null)
                    {
                        if (result.serverState == 1)
                        {
                            Intent intent = new Intent(EasyTransferRegisterActivity.this, com/arcsoft/mediaplus/setting/EasyTransferSettingActivity);
                            intent.putExtra("easytransfer", "edit");
                            intent.putExtra("server_udn", result.request.serverudn);
                            intent.putExtra("server_name", result.request.servername);
                            intent.putExtra("recordday", result.request.recordDay);
                            intent.putExtra("hour", result.request.startHour);
                            intent.putExtra("minute", result.request.startMinute);
                            intent.putExtra("allow", result.request.enableAllow);
                            intent.putExtra("plugin", result.request.enablePlugIn);
                            ((Activity)mContext).startActivityForResult(intent, 1);
                            return;
                        }
                        if (result.serverState == 4)
                        {
                            Toast.makeText(mContext, 0x7f0b0108, 1).show();
                            return;
                        } else
                        {
                            adapterview.setTag(result.request.serverudn);
                            showDialog(4);
                            return;
                        }
                    }
                }
            }

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Result result;
                if (mEasyTransferEngine != null)
                {
                    if ((result = mEasyTransferEngine.getEasyTransfer(l)) != null && result.serverState == 1)
                    {
                        adapterview.setTag(result.request.serverudn);
                        showDialog(1);
                        return true;
                    }
                }
                return true;
            }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
        };
        mLisener = new com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.IOnEasyTransferEngineListener() {

            final EasyTransferRegisterActivity this$0;

            public void onEasyTransferFinish(String s, long l)
            {
                if (mHandler != null && !mHandler.hasMessages(0))
                {
                    mHandler.sendEmptyMessage(0);
                }
            }

            public void onEasyTransferStart(String s, long l, int i)
            {
                if (mHandler != null && !mHandler.hasMessages(0))
                {
                    mHandler.sendEmptyMessage(0);
                }
            }

            public void onEasyTransfering(String s, long l, int i, int j)
            {
            }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
        };
        mUpDownloadServiceBinder = null;
        mEasyTransferEngine = null;
        mUpdownloadConnection = new ServiceConnection() {

            final EasyTransferRegisterActivity this$0;

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.i("", "DLNAService connected");
                mUpDownloadServiceBinder = ((com.arcsoft.mediaplus.service.util.DLNAService.LocalBinder)ibinder).getService();
                mEasyTransferEngine = mUpDownloadServiceBinder.getUpDownloadEngine();
                if (mEasyTransferEngine != null)
                {
                    mListView.init(mItemClickListener);
                    mListView.setEasyTransferEngine(mEasyTransferEngine);
                    mEasyTransferEngine.registerListener(mLisener);
                    mEasyTransferEngine.start();
                }
                updateView();
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.i("", "DLNAService disconnected");
                mUpDownloadServiceBinder = null;
            }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
        };
    }

    private int getDestinationPosition()
    {
        String s = Settings.instance().getDefaultDownloadDestination();
        String as[] = getResources().getStringArray(0x7f060007);
        for (int i = 0; i < as.length; i++)
        {
            if (s.equalsIgnoreCase(as[i]))
            {
                return i;
            }
        }

        return 0;
    }

    private void initUpdownloadService()
    {
        Intent intent = new Intent();
        intent.setAction("ArcSoft.DLNA.UPDWONLOAD.SERVICE");
        intent.setClass(this, com/arcsoft/mediaplus/service/util/DLNAService);
        bindService(intent, mUpdownloadConnection, 1);
    }

    private void saveDestination(int i)
    {
        String s = getResources().getStringArray(0x7f060007)[i];
        Settings.instance().setDefaultDownloadDestination(s);
    }

    private void uninitUpdownloadService()
    {
        if (mUpDownloadServiceBinder != null)
        {
            unbindService(mUpdownloadConnection);
        }
        mEasyTransferEngine = null;
        mUpDownloadServiceBinder = null;
    }

    private void updateView()
    {
        if (mEasyTransferEngine != null && mEasyTransferEngine.getCount() > 0)
        {
            mNoRegisterView.setVisibility(8);
            return;
        } else
        {
            mNoRegisterView.setVisibility(0);
            return;
        }
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        while (i != 1 || j != -1) 
        {
            return;
        }
        mListView.invalidateViews();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030014);
        mContext = this;
        mListView = (EasyTransferRegisterListView)findViewById(0x7f09006c);
        mListView.setCacheColorHint(0);
        mBtnRegister = (Button)findViewById(0x7f09006d);
        mBtnRegister.setOnClickListener(mRegisterNewOnClickListener);
        mNoRegisterView = (RelativeLayout)findViewById(0x7f09006e);
        initUpdownloadService();
    }

    protected Dialog onCreateDialog(int i, Bundle bundle)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            int j = getDestinationPosition();
            return (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b00fe).setSingleChoiceItems(0x7f060007, j, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    saveDestination(k);
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();

        case 1: // '\001'
            return (new android.app.AlertDialog.Builder(this)).setTitle(Settings.instance().getDefaultDMSName()).setItems(0x7f06000b, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    if (k == 0)
                    {
                        showDialog(2);
                        return;
                    } else
                    {
                        showDialog(3);
                        return;
                    }
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();

        case 4: // '\004'
            return (new android.app.AlertDialog.Builder(this)).setTitle(Settings.instance().getDefaultDMSName()).setItems(0x7f06000c, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    showDialog(5);
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();

        case 2: // '\002'
            String s = getString(0x7f0b0104, new Object[] {
                Settings.instance().getDefaultDownloadDestination()
            });
            return (new android.app.AlertDialog.Builder(this)).setMessage(s).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
label0:
                    {
                        if (mEasyTransferEngine != null)
                        {
                            String s1 = (String)mListView.getTag();
                            if (mEasyTransferEngine.getServerState(s1) != 1)
                            {
                                break label0;
                            }
                            mEasyTransferEngine.executeEasyTransfer(s1);
                            if (mListView != null)
                            {
                                mListView.invalidateViews();
                            }
                        }
                        return;
                    }
                    Toast.makeText(mContext, 0x7f0b0105, 1).show();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();

        case 3: // '\003'
            return (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b0106).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    if (mEasyTransferEngine != null)
                    {
                        String s1 = (String)mListView.getTag();
                        mEasyTransferEngine.deleteEasyTransfer(s1);
                        updateView();
                        if (mListView != null)
                        {
                            mListView.invalidateViews();
                        }
                    }
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();

        case 5: // '\005'
            return (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b0107).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    if (mEasyTransferEngine != null)
                    {
                        String s1 = (String)mListView.getTag();
                        mEasyTransferEngine.cancelEasyTransfer(s1);
                        if (mListView != null)
                        {
                            mListView.invalidateViews();
                        }
                    }
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferRegisterActivity this$0;

                public void onClick(DialogInterface dialoginterface, int k)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = EasyTransferRegisterActivity.this;
                super();
            }
            }).create();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.clear();
        menu.add(0, 0, 0, 0x7f0b00fe);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy()
    {
        uninitUpdownloadService();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if (menuitem != null && menuitem.getItemId() == 0)
        {
            showDialog(0);
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }

    protected void onPause()
    {
        super.onPause();
        if (mEasyTransferEngine != null)
        {
            mEasyTransferEngine.unregisterListener(mLisener);
        }
        mListView.setEasyTransferEngine(null);
    }

    protected void onResume()
    {
        super.onResume();
        mListView.setEasyTransferEngine(mEasyTransferEngine);
        if (mEasyTransferEngine != null)
        {
            mEasyTransferEngine.registerListener(mLisener);
        }
        updateView();
    }





/*
    static IEasyTransferEngine access$202(EasyTransferRegisterActivity easytransferregisteractivity, IEasyTransferEngine ieasytransferengine)
    {
        easytransferregisteractivity.mEasyTransferEngine = ieasytransferengine;
        return ieasytransferengine;
    }

*/





/*
    static DLNAService access$502(EasyTransferRegisterActivity easytransferregisteractivity, DLNAService dlnaservice)
    {
        easytransferregisteractivity.mUpDownloadServiceBinder = dlnaservice;
        return dlnaservice;
    }

*/

}
