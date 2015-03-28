// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.os.Looper;
import android.text.TextUtils;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.mediaplus.datasource.DataSourceFactory;
import com.arcsoft.mediaplus.datasource.db.ChannelDataMgr;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.datasource.db.SalixRemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.picture.controller.LocalCacheMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.db.DownloadedFileDBMgr;
import com.arcsoft.platform.MPTimer;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.LooperManager;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.FileDownloader;
import com.arcsoft.util.os.ScreenTool;
import com.arcsoft.util.tool.CachePathManager;
import com.arcsoft.util.tool.PlayerMandatoryManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MediaPlusApplication extends Application
{

    private static final String ASSET_FILES[] = {
        "MV2Plugin.ini", "MV2Plugin_SP.ini", "MV3Plugin.ini"
    };
    public static final int ASSET_INI_MV2PLUGIN = 0;
    public static final int ASSET_INI_MV2PLUGIN_SECUREPLAYER = 1;
    public static final int ASSET_INI_MV3PLUGIN = 2;
    private static final com.arcsoft.adk.image.Const.ColorSpace COLOR_SPACE;
    private static final String CONFIG_DIR_NAME = "config";
    private static com.arcsoft.util.os.ScreenTool.IOnScreenStatusChangeListener mScreenListener = new com.arcsoft.util.os.ScreenTool.IOnScreenStatusChangeListener() {

        public void OnScreenStatusTurnOff()
        {
            if (DLNA.instance() != null && DLNA.instance().getUserToken() == 0)
            {
                MPTimer.pause();
            }
            Log.v("mediasee", "========= Screen Off ======");
        }

        public void OnScreenStatusTurnOn()
        {
            MPTimer.resume();
            Log.v("mediasee", "========= Screen On ======");
        }

    };
    private static MediaPlusApplication sInstance = null;
    boolean mCacheCleared;
    private LocalCacheMgr mLocalCacheMgr;
    Activity mSdCardActivity;
    private final BroadcastReceiver mStorageStateReceiver = new BroadcastReceiver() {

        final MediaPlusApplication this$0;

        public void onReceive(Context context, Intent intent)
        {
            String s = intent.getAction();
            if (TextUtils.equals("android.intent.action.MEDIA_EJECT", s))
            {
                SDCardEject();
            } else
            {
                if (TextUtils.equals("android.intent.action.MEDIA_UNMOUNTED", s))
                {
                    SDCardUnmount();
                    return;
                }
                if (TextUtils.equals("android.intent.action.MEDIA_SCANNER_STARTED", s))
                {
                    SDCardScanStart();
                    return;
                }
                if (TextUtils.equals("android.intent.action.MEDIA_SCANNER_FINISHED", s))
                {
                    SDCardScanFinsh();
                    return;
                }
                if (TextUtils.equals("android.intent.action.MEDIA_MOUNTED", s))
                {
                    SDCardMount();
                    return;
                }
            }
        }

            
            {
                this$0 = MediaPlusApplication.this;
                super();
            }
    };

    public MediaPlusApplication()
    {
        mCacheCleared = false;
        mSdCardActivity = null;
    }

    private static void copyAsset(Context context)
    {
        File file = context.getDir("config", 0);
        String as[] = ASSET_FILES;
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            copyFileFromAsset(context, (new StringBuilder()).append(file.getAbsolutePath()).append(File.separator).append(s).toString(), s);
        }

    }

    private static void copyFileFromAsset(Context context, String s, String s1)
    {
        FileOutputStream fileoutputstream;
        InputStream inputstream;
        byte abyte0[];
        File file = new File(s);
        if (file.exists())
        {
            break MISSING_BLOCK_LABEL_86;
        }
        fileoutputstream = new FileOutputStream(file);
        inputstream = context.getAssets().open(s1);
        abyte0 = new byte[1024];
_L1:
        int i = inputstream.read(abyte0);
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        fileoutputstream.write(abyte0, 0, i);
          goto _L1
        try
        {
            inputstream.close();
            fileoutputstream.close();
            return;
        }
        catch (FileNotFoundException filenotfoundexception) { }
        catch (IOException ioexception)
        {
            return;
        }
    }

    public static MediaPlusApplication instance()
    {
        return sInstance;
    }

    protected void SDCardEject()
    {
    }

    protected void SDCardMount()
    {
        FileUtils.recreateCacheMgr(this);
    }

    protected void SDCardScanFinsh()
    {
    }

    protected void SDCardScanStart()
    {
    }

    protected void SDCardUnmount()
    {
    }

    public String getAssetFilePath(int i)
    {
        File file = getApplicationContext().getDir("config", 0);
        return (new StringBuilder()).append(file.getAbsolutePath()).append(File.separator).append(ASSET_FILES[i]).toString();
    }

    public CacheMgr getLocalCacheManager()
    {
        return mLocalCacheMgr;
    }

    public boolean isCacheCleared()
    {
        return mCacheCleared;
    }

    public void onCreate()
    {
        super.onCreate();
        sInstance = this;
        FileUtils.deleteFolder(OEMConfig.TEMP_BASE_PATH);
        FileUtils.createFolder(OEMConfig.TEMP_BASE_PATH);
        registerReceives();
        LooperManager.initSingleton(this);
        CachePathManager.initSingleton(this);
        FileDownloader.initSingleton(this, 5);
        DLNA.initSingleton(this, LooperManager.instance().getLooper(1));
        DLNA.instance().setFileServerEnable(true);
        Settings.initSingleton(this);
        RemoteDBMgr.initSingleton(this, Looper.myLooper());
        SalixRemoteDBMgr.initSingleton(this);
        DataSourceFactory.initSingleton(this);
        ChannelDataMgr.initSingleton(this, Looper.myLooper());
        DownloadedFileDBMgr.initSingleton(this);
        copyAsset(getApplicationContext());
        PlayerMandatoryManager.initSingleton(this);
        ScreenTool.initSingleton(this);
        ScreenTool.instance().registerOnScreenStatusChangeListener(mScreenListener);
        startService(new Intent("MUVI.MediaPlus.DLNA.SERVICE"));
        if (OEMConfig.OPENGL_OPTIMIZE)
        {
            mLocalCacheMgr = LocalCacheMgr.create(this);
        }
    }

    public void onTerminate()
    {
        stopService(new Intent("MUVI.MediaPlus.DLNA.SERVICE"));
        unRegisterReceives();
        PlayerMandatoryManager.uninitSingleton();
        DataSourceFactory.uninitSingleton();
        RemoteDBMgr.uninitSingleton();
        SalixRemoteDBMgr.uninitSingleton();
        DownloadedFileDBMgr.uninitSingleton();
        FileDownloader.uninitSingleton();
        Settings.uninitSingleton();
        DLNA.uninitSingleton();
        ChannelDataMgr.uninitSingleton();
        CachePathManager.uninitSingleton();
        LooperManager.uninitSingleton();
        ScreenTool.uninitSingleton();
        sInstance = null;
        super.onTerminate();
    }

    protected void registerReceives()
    {
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentfilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentfilter.addAction("android.intent.action.MEDIA_SHARED");
        intentfilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentfilter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        intentfilter.addAction("android.intent.action.MEDIA_EJECT");
        intentfilter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        intentfilter.addDataScheme("file");
        if (mStorageStateReceiver != null)
        {
            registerReceiver(mStorageStateReceiver, intentfilter);
        }
    }

    public void setCacheCleared(boolean flag)
    {
        mCacheCleared = flag;
    }

    protected void unRegisterReceives()
    {
        if (mStorageStateReceiver != null)
        {
            unregisterReceiver(mStorageStateReceiver);
        }
    }

    static 
    {
        COLOR_SPACE = com.arcsoft.adk.image.Const.ColorSpace.RGB565;
    }
}
