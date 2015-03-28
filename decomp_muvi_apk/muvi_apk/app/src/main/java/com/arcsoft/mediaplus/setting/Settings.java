// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingActivity

public final class Settings
{
    public static interface IOnSettingChangedListener
    {

        public abstract void OnDefaultDownloadDestinationChanged(String s);

        public abstract void OnDefaultRenderChanged(String s);

        public abstract void OnDefaultServerChanged(String s);

        public abstract void onBrowseModeChanged(boolean flag);

        public abstract void onSortModeChanged(boolean flag);

        public abstract void onTVStreamingResolutionChange(boolean flag);
    }


    private static final String PREFERENCE_SETTING_ACCESS_STRING = "preference_content_access_index";
    private static final int RESUMEPOINT_MAX = 20;
    private static Settings sInstance = null;
    private Application mApplication;
    private final android.content.SharedPreferences.OnSharedPreferenceChangeListener mListener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {

        final Settings this$0;

        public void onSharedPreferenceChanged(SharedPreferences sharedpreferences, String s)
        {
            if (s.equals(mApplication.getString(0x7f0b0074)))
            {
                notifyDefaultDMRChanged();
            } else
            {
                if (s.equals(mApplication.getString(0x7f0b007b)))
                {
                    notifyDefaultDMSChanged();
                    return;
                }
                if (s.equals(mApplication.getString(0x7f0b0094)))
                {
                    notifyBrowseModeChanged(getOnlineContentsListviewMode());
                    return;
                }
                if (!s.equals(mApplication.getString(0x7f0b0097)))
                {
                    if (s.equals(mApplication.getString(0x7f0b00a1)))
                    {
                        notifyDownloadDestinationChanged();
                        return;
                    }
                    if (s.equals(mApplication.getString(0x7f0b009c)))
                    {
                        notifyTVStreamingResolutionChanged(isUseHDResource());
                        return;
                    }
                }
            }
        }

            
            {
                this$0 = Settings.this;
                super();
            }
    };
    private SharedPreferences mPreferences;
    private final ArrayList mSettingListeners = new ArrayList();

    private Settings(Application application)
    {
        mApplication = null;
        mPreferences = null;
        mApplication = application;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(mApplication.getApplicationContext());
    }

    public static void LaunchSettingActivity(Context context)
    {
        Intent intent = new Intent();
        intent.setClass(context.getApplicationContext(), com/arcsoft/mediaplus/setting/SettingActivity);
        context.startActivity(intent);
    }

    private IOnSettingChangedListener[] getSettingChangedListenersCopy()
    {
        ArrayList arraylist = mSettingListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mSettingListeners.size();
        IOnSettingChangedListener aionsettingchangedlistener[];
        aionsettingchangedlistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IOnSettingChangedListener aionsettingchangedlistener1[] = new IOnSettingChangedListener[mSettingListeners.size()];
        aionsettingchangedlistener = (IOnSettingChangedListener[])mSettingListeners.toArray(aionsettingchangedlistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aionsettingchangedlistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void init()
    {
        mPreferences.registerOnSharedPreferenceChangeListener(mListener);
        setDefaltValues();
    }

    public static void initSingleton(Application application)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new Settings(application);
            sInstance.init();
            return;
        }
    }

    public static Settings instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    private void uninit()
    {
        mPreferences.unregisterOnSharedPreferenceChangeListener(mListener);
    }

    public static void uninitSingleton()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.uninit();
            sInstance = null;
            return;
        }
    }

    public int getAudioRepeat()
    {
        String s = mApplication.getString(0x7f0b0091);
        return mPreferences.getInt(s, 0);
    }

    public boolean getAudioShuffle()
    {
        String s = mApplication.getString(0x7f0b0092);
        return mPreferences.getBoolean(s, false);
    }

    public int getDefaultContentAccess()
    {
        return mPreferences.getInt("preference_content_access_index", 0);
    }

    public String getDefaultDMRName()
    {
        String s = mApplication.getString(0x7f0b0075);
        String s1 = mApplication.getString(0x7f0b001a);
        return mPreferences.getString(s, s1);
    }

    public String getDefaultDMRUDN()
    {
        String s = mApplication.getString(0x7f0b0074);
        return mPreferences.getString(s, null);
    }

    public String getDefaultDMSName()
    {
        String s = mApplication.getString(0x7f0b007c);
        String s1 = mApplication.getString(0x7f0b001a);
        return mPreferences.getString(s, s1);
    }

    public String getDefaultDMSUDN()
    {
        String s = mApplication.getString(0x7f0b007b);
        return mPreferences.getString(s, null);
    }

    public String getDefaultDownloadDestination()
    {
        String s = mApplication.getString(0x7f0b00a1);
        return mPreferences.getString(s, "");
    }

    public HashMap getLocalResumePoint()
    {
        HashMap hashmap = new HashMap();
        int i = mPreferences.getInt("Local_Resume_Point_Num", 0);
        int j = mPreferences.getInt("Local_Resume_Point_StartIndex", 0);
        for (int k = 0; k < i; k++)
        {
            String s = mPreferences.getString((new StringBuilder()).append((k + j) % 20).append("_Path").toString(), null);
            long l = mPreferences.getLong((new StringBuilder()).append((k + j) % 20).append("_ResumePoint").toString(), 0L);
            Log.v("Settings", (new StringBuilder()).append("local resume point = ").append(s).append(" = ").append(l).toString());
            hashmap.put(s, Long.valueOf(l));
        }

        return hashmap;
    }

    public boolean getOnlineContentsListviewMode()
    {
        String s = mApplication.getString(0x7f0b0094);
        return mPreferences.getBoolean(s, false);
    }

    public String getRecordScheduleID()
    {
        String s = mApplication.getString(0x7f0b00a2);
        return mPreferences.getString(s, "");
    }

    public boolean getUpDownloadStatusOnBackground()
    {
        String s = mApplication.getString(0x7f0b0098);
        return mPreferences.getBoolean(s, false);
    }

    public boolean isUseHDResource()
    {
        String s = mApplication.getString(0x7f0b009c);
        return mPreferences.getBoolean(s, false);
    }

    void notifyBrowseModeChanged(boolean flag)
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].onBrowseModeChanged(flag);
                j++;
            }
        }
    }

    void notifyDefaultDMRChanged()
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            String s = getDefaultDMRUDN();
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].OnDefaultRenderChanged(s);
                j++;
            }
        }
    }

    void notifyDefaultDMSChanged()
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            String s = getDefaultDMSUDN();
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].OnDefaultServerChanged(s);
                j++;
            }
        }
    }

    void notifyDownloadDestinationChanged()
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            String s = getDefaultDownloadDestination();
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].OnDefaultDownloadDestinationChanged(s);
                j++;
            }
        }
    }

    void notifySortModeChanged(boolean flag)
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].onSortModeChanged(flag);
                j++;
            }
        }
    }

    void notifyTVStreamingResolutionChanged(boolean flag)
    {
        IOnSettingChangedListener aionsettingchangedlistener[] = getSettingChangedListenersCopy();
        if (aionsettingchangedlistener != null)
        {
            int i = aionsettingchangedlistener.length;
            int j = 0;
            while (j < i) 
            {
                aionsettingchangedlistener[j].onTVStreamingResolutionChange(flag);
                j++;
            }
        }
    }

    public void registerSettingChangeListener(IOnSettingChangedListener ionsettingchangedlistener)
    {
        synchronized (mSettingListeners)
        {
            if (!mSettingListeners.contains(ionsettingchangedlistener))
            {
                mSettingListeners.add(ionsettingchangedlistener);
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean removeLocalResumePoint(String s)
    {
        if (s == null)
        {
            return false;
        }
        Log.v("Settings", (new StringBuilder()).append("removeLocalResumePoint start... ").append(s).toString());
        int i = mPreferences.getInt("Local_Resume_Point_Num", 0);
        int j = -1;
        for (int k = 0; k < i; k++)
        {
            if (s.equals(mPreferences.getString((new StringBuilder()).append(k).append("_Path").toString(), null)))
            {
                j = k;
            }
        }

        if (j < 0)
        {
            return false;
        }
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        int l = i - 1;
        editor.putInt("Local_Resume_Point_Num", l);
        int i1 = mPreferences.getInt("Local_Resume_Point_StartIndex", 0);
        if (i1 == j)
        {
            editor.putInt("Local_Resume_Point_StartIndex", i1 + 1);
        }
        int j1 = 0;
        if (i1 < j)
        {
            j1 = l - (j - i1);
        }
        if (i1 > j)
        {
            j1 = i1 - j - (20 - l);
        }
        for (int k1 = 0; k1 < j1; k1++)
        {
            int l1 = (k1 + j) % 20;
            int i2 = (1 + (k1 + j)) % 20;
            String s1 = mPreferences.getString((new StringBuilder()).append(i2).append("_Path").toString(), null);
            long l2 = mPreferences.getLong((new StringBuilder()).append(i2).append("_ResumePoint").toString(), 0L);
            editor.putString((new StringBuilder()).append(l1).append("_Path").toString(), s1);
            editor.putLong((new StringBuilder()).append(l1).append("_ResumePoint").toString(), l2);
        }

        Log.v("Settings", (new StringBuilder()).append("removeLocalResumePoint end... ").append(j1).toString());
        return editor.commit();
    }

    public boolean setAudioRepeat(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(mApplication.getString(0x7f0b0091), i);
        return editor.commit();
    }

    public boolean setAudioShuffle(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(mApplication.getString(0x7f0b0092), flag);
        return editor.commit();
    }

    void setDefaltValues()
    {
        if (!mPreferences.contains(mApplication.getString(0x7f0b009c)))
        {
            setUseHDResource(true);
        }
        if (!mPreferences.contains(mApplication.getString(0x7f0b0094)))
        {
            setOnlineContentsListviewMode(false);
        }
    }

    public boolean setDefaultContentAccess(int i)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("preference_content_access_index", i);
        return editor.commit();
    }

    public boolean setDefaultDMRName(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b0075), s);
        return editor.commit();
    }

    public boolean setDefaultDMRUDN(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b0074), s);
        return editor.commit();
    }

    public boolean setDefaultDMSName(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b007c), s);
        return editor.commit();
    }

    public boolean setDefaultDMSUDN(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b007b), s);
        return editor.commit();
    }

    public boolean setDefaultDownloadDestination(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b00a1), s);
        return editor.commit();
    }

    public boolean setLocalResumePoint(String s, long l)
    {
        if (s == null)
        {
            return false;
        }
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        int i = mPreferences.getInt("Local_Resume_Point_Num", 0);
        int j = -1;
        for (int k = 0; k < i; k++)
        {
            if (s.equals(mPreferences.getString((new StringBuilder()).append(k).append("_Path").toString(), null)))
            {
                j = k;
            }
        }

        if (j < 0)
        {
            int i1 = mPreferences.getInt("Local_Resume_Point_StartIndex", 0);
            if (i >= 20)
            {
                j = i1;
                editor.putInt("Local_Resume_Point_StartIndex", j + 1);
            } else
            {
                j = (i + i1) % 20;
                editor.putInt("Local_Resume_Point_Num", i + 1);
            }
        }
        editor.putString((new StringBuilder()).append(j).append("_Path").toString(), s);
        editor.putLong((new StringBuilder()).append(j).append("_ResumePoint").toString(), l);
        return editor.commit();
    }

    public boolean setOnlineContentsListviewMode(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(mApplication.getString(0x7f0b0094), flag);
        return editor.commit();
    }

    public boolean setRecordScheduleID(String s)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(mApplication.getString(0x7f0b00a2), s);
        return editor.commit();
    }

    public boolean setUpDownloadStatusOnBackground(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(mApplication.getString(0x7f0b0098), flag);
        return editor.commit();
    }

    public boolean setUseHDResource(boolean flag)
    {
        android.content.SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(mApplication.getString(0x7f0b009c), flag);
        return editor.commit();
    }

    public void unregisterSettingChangeListener(IOnSettingChangedListener ionsettingchangedlistener)
    {
        synchronized (mSettingListeners)
        {
            if (mSettingListeners.contains(ionsettingchangedlistener))
            {
                mSettingListeners.remove(ionsettingchangedlistener);
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }


}
