// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            Settings, About, SettingListActivity

public class SettingActivity extends PreferenceActivity
    implements android.preference.Preference.OnPreferenceChangeListener
{

    private static final int DIALOG_ID_DOWNLOAD_DESTINATION = 103;
    private static final int DIALOG_ID_LOCAL_FILTER = 102;
    public static final String SETTING_NEED_MATCH_DMS = "need_match_dms";
    public static final String TAG = "Setting";
    public static final int TYPE_DMR = 1;
    public static final int TYPE_DMS;
    private Preference mDMRPref;
    private Preference mDMSPref;
    private Preference mDownloadDestinationPref;
    private boolean mNeedMatchDMS;

    public SettingActivity()
    {
        mDMSPref = null;
        mDMRPref = null;
        mDownloadDestinationPref = null;
        mNeedMatchDMS = false;
    }

    private Dialog createFilterDialog()
    {
        String as[] = new String[3];
        as[0] = getString(0x7f0b0037);
        as[1] = getString(0x7f0b0038);
        as[2] = getString(0x7f0b0039);
        return (new android.app.AlertDialog.Builder(this)).setTitle(getString(0x7f0b008e)).setMultiChoiceItems(as, new boolean[] {
            1, 1, 1
        }, new android.content.DialogInterface.OnMultiChoiceClickListener() {

            final SettingActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i, boolean flag)
            {
            }

            
            {
                this$0 = SettingActivity.this;
                super();
            }
        }).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

            final SettingActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = SettingActivity.this;
                super();
            }
        }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

            final SettingActivity this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = SettingActivity.this;
                super();
            }
        }).create();
    }

    private void updateSummary(int i, String s)
    {
        Preference preference;
        String s1;
        if (i == 1)
        {
            preference = mDMRPref;
        } else
        {
            preference = mDMSPref;
        }
        s1 = s;
        if (s1 == null)
        {
            if (i == 1)
            {
                s1 = Settings.instance().getDefaultDMRName();
            } else
            {
                s1 = Settings.instance().getDefaultDMSName();
            }
        }
        preference.setSummary(s1);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        if (j == -1)
        {
            if (1 == i)
            {
                updateSummary(1, intent.getStringExtra("current_dmr"));
            } else
            if (i == 0)
            {
                updateSummary(0, intent.getStringExtra("current_dms"));
                return;
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mNeedMatchDMS = getIntent().getBooleanExtra("need_match_dms", false);
        addPreferencesFromResource(0x7f050001);
        mDMRPref = findPreference(getString(0x7f0b0073));
        mDMRPref.setSummary(Settings.instance().getDefaultDMRName());
        mDMSPref = findPreference(getString(0x7f0b007a));
        String s;
        if (!mNeedMatchDMS || DLNA.instance().getServerManager().isDigaDMS(Settings.instance().getDefaultDMSUDN()))
        {
            mDMSPref.setSummary(Settings.instance().getDefaultDMSName());
        } else
        {
            mDMSPref.setSummary(getString(0x7f0b001a));
        }
        mDownloadDestinationPref = findPreference(getString(0x7f0b00a1));
        mDownloadDestinationPref.setOnPreferenceChangeListener(this);
        s = Settings.instance().getDefaultDownloadDestination();
        mDownloadDestinationPref.setSummary(s);
        ((PreferenceScreen)findPreference(getString(0x7f0b0090))).setIntent(new Intent(getApplicationContext(), com/arcsoft/mediaplus/setting/About));
    }

    protected Dialog onCreateDialog(int i)
    {
        if (102 == i)
        {
            return createFilterDialog();
        } else
        {
            return super.onCreateDialog(i);
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    public boolean onPreferenceChange(Preference preference, Object obj)
    {
        for (String s = preference.getKey(); s == null || !s.equalsIgnoreCase(getString(0x7f0b00a1));)
        {
            return false;
        }

        Settings.instance().setDefaultDownloadDestination(obj.toString());
        mDownloadDestinationPref.setSummary(obj.toString());
        return true;
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        String s;
        s = preference.getKey();
        if (s == null)
        {
            return false;
        }
        if (!s.equals(getString(0x7f0b0080))) goto _L2; else goto _L1
_L1:
        startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
_L4:
        return super.onPreferenceTreeClick(preferencescreen, preference);
_L2:
        if (s.equals(getString(0x7f0b007a)))
        {
            Intent intent = new Intent(this, com/arcsoft/mediaplus/setting/SettingListActivity);
            intent.putExtra("settingtype", 0);
            intent.putExtra("need_match_dms", mNeedMatchDMS);
            startActivityForResult(intent, 0);
        } else
        if (s.equals(getString(0x7f0b0073)))
        {
            Intent intent1 = new Intent(this, com/arcsoft/mediaplus/setting/SettingListActivity);
            intent1.putExtra("settingtype", 1);
            startActivityForResult(intent1, 1);
        } else
        if (s.equals(getString(0x7f0b008c)))
        {
            showDialog(102);
        } else
        if (s.equals(getString(0x7f0b0083)))
        {
            Intent intent2 = new Intent("com.android.arcsoft.Dms_Setting_Widget.ACTION_WIDGET_CONFIGURE");
            intent2.setClassName("com.android.arcsoft.ArcSoftDMS", "com.android.arcsoft.ArcSoftDMS.ArcSoftDMS");
            startActivity(intent2);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onPrepareDialog(int i, Dialog dialog)
    {
        if (102 != i);
        super.onPrepareDialog(i, dialog);
    }
}
