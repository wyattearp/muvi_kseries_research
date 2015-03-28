// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SlideShowSettingActivity extends PreferenceActivity
    implements android.preference.Preference.OnPreferenceChangeListener
{

    private ListPreference mPrefEffect;
    private ListPreference mPrefInterval;

    public SlideShowSettingActivity()
    {
        mPrefEffect = null;
        mPrefInterval = null;
    }

    public static int getSlideShowEffect(Context context)
    {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(getSlideShowEffectKey(context), "-1")).intValue();
    }

    public static String getSlideShowEffectKey(Context context)
    {
        return context.getString(0x7f0b010a);
    }

    public static int getSlideShowInterval(Context context)
    {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(getSlideShowIntervalKey(context), "-1")).intValue();
    }

    public static String getSlideShowIntervalKey(Context context)
    {
        return context.getString(0x7f0b010b);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        addPreferencesFromResource(0x7f050002);
        Context context = getApplicationContext();
        mPrefEffect = (ListPreference)findPreference(getSlideShowEffectKey(context));
        mPrefEffect.setOnPreferenceChangeListener(this);
        mPrefEffect.setSummary(mPrefEffect.getEntry());
        mPrefInterval = (ListPreference)findPreference(getSlideShowIntervalKey(context));
        mPrefInterval.setOnPreferenceChangeListener(this);
        mPrefInterval.setSummary(mPrefInterval.getEntry());
    }

    public boolean onPreferenceChange(Preference preference, Object obj)
    {
        if (preference.equals(mPrefEffect))
        {
            mPrefEffect.setValue(obj.toString());
            mPrefEffect.setSummary(mPrefEffect.getEntry());
            return true;
        }
        if (preference.equals(mPrefInterval))
        {
            mPrefInterval.setValue(obj.toString());
            mPrefInterval.setSummary(mPrefInterval.getEntry());
            return true;
        } else
        {
            return false;
        }
    }
}
