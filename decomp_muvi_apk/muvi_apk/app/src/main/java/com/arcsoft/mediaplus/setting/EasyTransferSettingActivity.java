// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            Settings

public class EasyTransferSettingActivity extends PreferenceActivity
    implements android.preference.Preference.OnPreferenceChangeListener
{

    private final int DIALOG_ID_SAVE_POPUP = 104;
    private final int DIALOG_ID_TIME_PICKER = 103;
    private final String TAG = "EasyTransferSetting";
    private boolean mAllowed;
    private Button mBtnDone;
    private android.view.View.OnClickListener mBtnDoneOnClickListener;
    private Button mBtnRevert;
    private android.view.View.OnClickListener mBtnRevertOnClickListener;
    private Context mContext;
    private Preference mDigaName;
    private IEasyTransferEngine mEasyTransferEngine;
    private int mHour;
    private boolean mIsEdit;
    private int mMinute;
    private boolean mPlugIn;
    private int mRecordDay;
    private String mServerName;
    private String mServerUDN;
    private Preference mTransferAllow;
    private Preference mTransferPeriod;
    private Preference mTransferPlugIn;
    private Preference mTransferStartTime;
    private DLNAService mUpDownloadServiceBinder;
    private ServiceConnection mUpdownloadConnection;

    public EasyTransferSettingActivity()
    {
        mContext = null;
        mDigaName = null;
        mTransferPeriod = null;
        mTransferStartTime = null;
        mTransferAllow = null;
        mTransferPlugIn = null;
        mServerUDN = null;
        mServerName = null;
        mRecordDay = 0;
        mHour = 0;
        mMinute = 0;
        mAllowed = false;
        mPlugIn = false;
        mIsEdit = false;
        mBtnDone = null;
        mBtnRevert = null;
        mUpDownloadServiceBinder = null;
        mEasyTransferEngine = null;
        mUpdownloadConnection = new ServiceConnection() {

            final EasyTransferSettingActivity this$0;

            public void onServiceConnected(ComponentName componentname, IBinder ibinder)
            {
                Log.i("", "DLNAService connected");
                mUpDownloadServiceBinder = ((com.arcsoft.mediaplus.service.util.DLNAService.LocalBinder)ibinder).getService();
                mEasyTransferEngine = mUpDownloadServiceBinder.getUpDownloadEngine();
                updateBtnEnable(true);
            }

            public void onServiceDisconnected(ComponentName componentname)
            {
                Log.i("", "DLNAService disconnected");
                mUpDownloadServiceBinder = null;
            }

            
            {
                this$0 = EasyTransferSettingActivity.this;
                super();
            }
        };
        mBtnDoneOnClickListener = new android.view.View.OnClickListener() {

            final EasyTransferSettingActivity this$0;

            public void onClick(View view)
            {
                if (!mIsEdit && mEasyTransferEngine.getEasyTransfer(mServerUDN) != null)
                {
                    Toast.makeText(mContext, 0x7f0b0102, 1).show();
                    return;
                }
                saveTransfer();
                if (checkDestinationToSDcard())
                {
                    showDialog(104);
                    return;
                } else
                {
                    setResult(-1);
                    finish();
                    return;
                }
            }

            
            {
                this$0 = EasyTransferSettingActivity.this;
                super();
            }
        };
        mBtnRevertOnClickListener = new android.view.View.OnClickListener() {

            final EasyTransferSettingActivity this$0;

            public void onClick(View view)
            {
                setResult(0);
                finish();
            }

            
            {
                this$0 = EasyTransferSettingActivity.this;
                super();
            }
        };
    }

    private boolean checkDestinationToSDcard()
    {
        return Settings.instance().getDefaultDownloadDestination().equalsIgnoreCase(getResources().getStringArray(0x7f060007)[1]);
    }

    private void initUpdownloadService()
    {
        Intent intent = new Intent();
        intent.setAction("ArcSoft.DLNA.UPDWONLOAD.SERVICE");
        intent.setClass(this, com/arcsoft/mediaplus/service/util/DLNAService);
        bindService(intent, mUpdownloadConnection, 1);
    }

    private void initView()
    {
        mBtnDone = (Button)findViewById(0x7f090070);
        mBtnRevert = (Button)findViewById(0x7f090071);
        mBtnDone.setOnClickListener(mBtnDoneOnClickListener);
        mBtnRevert.setOnClickListener(mBtnRevertOnClickListener);
    }

    private void saveTransfer()
    {
        com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Request request;
label0:
        {
            if (mEasyTransferEngine != null)
            {
                SharedPreferences sharedpreferences = mDigaName.getSharedPreferences();
                request = new com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Request();
                request.servername = mServerName;
                request.serverudn = mServerUDN;
                request.startHour = mHour;
                request.startMinute = mMinute;
                request.recordDay = Integer.parseInt(sharedpreferences.getString(getString(0x7f0b00f7), "0"));
                request.enableAllow = sharedpreferences.getBoolean(getString(0x7f0b00fb), false);
                request.enablePlugIn = sharedpreferences.getBoolean(getString(0x7f0b00fd), false);
                if (!mIsEdit)
                {
                    break label0;
                }
                mEasyTransferEngine.updateEasyTransfer(mServerUDN, request);
            }
            return;
        }
        mEasyTransferEngine.registerEasyTransfer(request);
    }

    private void setTransferPeriod(String s)
    {
        String as[] = getResources().getStringArray(0x7f060000);
        mRecordDay = Integer.valueOf(s).intValue();
        android.content.SharedPreferences.Editor editor = mTransferPeriod.getEditor();
        editor.putString(getResources().getString(0x7f0b00f7), s);
        editor.commit();
        int i = 0;
        do
        {
label0:
            {
                if (i < as.length)
                {
                    if (!s.equalsIgnoreCase(as[i]))
                    {
                        break label0;
                    }
                    String s1 = mContext.getResources().getStringArray(0x7f06000a)[i];
                    mTransferPeriod.setSummary(s1);
                }
                return;
            }
            i++;
        } while (true);
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

    private void updateBtnEnable(boolean flag)
    {
        mBtnDone.setEnabled(flag);
        mBtnRevert.setEnabled(flag);
    }

    public View findViewById(int i)
    {
        if (i == 0x102000a)
        {
            i = 0x7f09006f;
        }
        return getWindow().findViewById(i);
    }

    public ListView getListView()
    {
        return super.getListView();
    }

    public void onContentChanged()
    {
        super.onContentChanged();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String s = intent.getStringExtra("easytransfer");
        if (s == null)
        {
            Log.e("EasyTransferSetting", "Need specify intent!");
            finish();
            return;
        }
        if (s.equalsIgnoreCase("edit"))
        {
            mIsEdit = true;
        }
        mServerUDN = intent.getStringExtra("server_udn");
        if (mServerUDN == null || !DLNA.instance().getServerManager().isDigaDMS(mServerUDN))
        {
            Log.e("EasyTransferSetting", "Need specify one DIGA dms!");
            finish();
            return;
        }
        mServerName = intent.getStringExtra("server_name");
        mRecordDay = intent.getIntExtra("recordday", 0);
        mHour = intent.getIntExtra("hour", 0);
        mMinute = intent.getIntExtra("minute", 0);
        mAllowed = intent.getBooleanExtra("allow", false);
        mPlugIn = intent.getBooleanExtra("plugin", false);
        addPreferencesFromResource(0x7f050000);
        mContext = this;
        mDigaName = findPreference(getString(0x7f0b00f5));
        mTransferPeriod = findPreference(getString(0x7f0b00f7));
        mTransferStartTime = findPreference(getString(0x7f0b00f9));
        mTransferAllow = findPreference(getString(0x7f0b00fb));
        mTransferPlugIn = findPreference(getString(0x7f0b00fd));
        Preference preference = mDigaName;
        boolean flag;
        Object aobj[];
        String s1;
        android.content.SharedPreferences.Editor editor;
        android.content.SharedPreferences.Editor editor1;
        if (mIsEdit)
        {
            flag = false;
        } else
        {
            flag = true;
        }
        preference.setEnabled(flag);
        mDigaName.setSummary(mServerName);
        setTransferPeriod(Integer.toString(mRecordDay));
        aobj = new Object[2];
        aobj[0] = Integer.valueOf(mHour);
        aobj[1] = Integer.valueOf(mMinute);
        s1 = String.format("%02d:%02d", aobj);
        mTransferStartTime.setSummary(s1);
        editor = mTransferAllow.getEditor();
        editor.putBoolean(getString(0x7f0b00fb), mAllowed);
        editor.commit();
        editor1 = mTransferPlugIn.getEditor();
        editor1.putBoolean(getString(0x7f0b00fd), mPlugIn);
        editor1.commit();
        mDigaName.setOnPreferenceChangeListener(this);
        mTransferPeriod.setOnPreferenceChangeListener(this);
        mTransferStartTime.setOnPreferenceChangeListener(this);
        mTransferAllow.setOnPreferenceChangeListener(this);
        mTransferPlugIn.setOnPreferenceChangeListener(this);
        initUpdownloadService();
        initView();
        updateBtnEnable(false);
    }

    protected Dialog onCreateDialog(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 103: // 'g'
            return new TimePickerDialog(this, new android.app.TimePickerDialog.OnTimeSetListener() {

                final EasyTransferSettingActivity this$0;

                public void onTimeSet(TimePicker timepicker, int j, int k)
                {
                    mHour = j;
                    mMinute = k;
                    android.content.SharedPreferences.Editor editor = mTransferStartTime.getEditor();
                    String s = getResources().getString(0x7f0b00f9);
                    Object aobj[] = new Object[2];
                    aobj[0] = Integer.valueOf(mHour);
                    aobj[1] = Integer.valueOf(mMinute);
                    String s1 = String.format("%02d:%02d", aobj);
                    editor.putString(s, s1);
                    editor.commit();
                    onPreferenceChange(mTransferStartTime, s1);
                }

            
            {
                this$0 = EasyTransferSettingActivity.this;
                super();
            }
            }, mHour, mMinute, true);

        case 104: // 'h'
            return (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b0103).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final EasyTransferSettingActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    setResult(-1);
                    finish();
                }

            
            {
                this$0 = EasyTransferSettingActivity.this;
                super();
            }
            }).create();
        }
    }

    protected void onDestroy()
    {
        uninitUpdownloadService();
        super.onDestroy();
    }

    public boolean onPreferenceChange(Preference preference, Object obj)
    {
        String s = preference.getKey();
        if (s != null)
        {
            if (s.equals(getString(0x7f0b00f7)))
            {
                setTransferPeriod(obj.toString());
                return true;
            }
            if (s.equals(getString(0x7f0b00f9)))
            {
                mTransferStartTime.setSummary(obj.toString());
                return true;
            }
            if (s.equals(getString(0x7f0b00fb)))
            {
                mAllowed = ((Boolean)obj).booleanValue();
                return true;
            }
            if (s.equals(getString(0x7f0b00fd)))
            {
                mPlugIn = ((Boolean)obj).booleanValue();
                return true;
            }
        }
        return false;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        String s = preference.getKey();
        if (s == null)
        {
            return false;
        }
        if (s == getString(0x7f0b00f9))
        {
            showDialog(103);
        } else
        if (s.equalsIgnoreCase(getString(0x7f0b001d)))
        {
            saveTransfer();
        } else
        if (!s.equalsIgnoreCase(getString(0x7f0b001e)));
        return super.onPreferenceTreeClick(preferencescreen, preference);
    }

    public void setContentView(int i)
    {
        getWindow().setContentView(0x7f030015);
    }



/*
    static int access$002(EasyTransferSettingActivity easytransfersettingactivity, int i)
    {
        easytransfersettingactivity.mHour = i;
        return i;
    }

*/




/*
    static int access$102(EasyTransferSettingActivity easytransfersettingactivity, int i)
    {
        easytransfersettingactivity.mMinute = i;
        return i;
    }

*/




/*
    static DLNAService access$302(EasyTransferSettingActivity easytransfersettingactivity, DLNAService dlnaservice)
    {
        easytransfersettingactivity.mUpDownloadServiceBinder = dlnaservice;
        return dlnaservice;
    }

*/



/*
    static IEasyTransferEngine access$402(EasyTransferSettingActivity easytransfersettingactivity, IEasyTransferEngine ieasytransferengine)
    {
        easytransfersettingactivity.mEasyTransferEngine = ieasytransferengine;
        return ieasytransferengine;
    }

*/





}
