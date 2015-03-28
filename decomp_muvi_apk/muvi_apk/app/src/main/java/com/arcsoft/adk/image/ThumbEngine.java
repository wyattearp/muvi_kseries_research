// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.CachePathManager;
import com.arcsoft.util.tool.DynamicDataStateMachine;
import java.io.File;
import java.io.IOException;

// Referenced classes of package com.arcsoft.adk.image:
//            CacheInfo, IFileList

public class ThumbEngine
{
    public static class DecodeParam
    {

        public final Const.DisplayMode displaymode;
        public final int height;
        public final int maxcachesizeKB;
        public final int width;

        public boolean equals(Object obj)
        {
            DecodeParam decodeparam;
            int i;
            int j;
            boolean flag;
            int k;
            int l;
            Const.DisplayMode displaymode1;
            Const.DisplayMode displaymode2;
            try
            {
                decodeparam = (DecodeParam)obj;
                i = decodeparam.width;
                j = width;
            }
            catch (Exception exception)
            {
                return false;
            }
            flag = false;
            if (i != j)
            {
                break MISSING_BLOCK_LABEL_74;
            }
            k = decodeparam.height;
            l = height;
            flag = false;
            if (k != l)
            {
                break MISSING_BLOCK_LABEL_74;
            }
            displaymode1 = decodeparam.displaymode;
            displaymode2 = displaymode;
            flag = false;
            if (displaymode1 == displaymode2)
            {
                flag = true;
            }
            return flag;
        }

        public DecodeParam(int i, int j, Const.DisplayMode displaymode1, int k)
        {
            width = i;
            height = j;
            displaymode = displaymode1;
            maxcachesizeKB = k;
        }

        public DecodeParam(DecodeParam decodeparam)
        {
            width = decodeparam.width;
            height = decodeparam.height;
            displaymode = decodeparam.displaymode;
            maxcachesizeKB = decodeparam.maxcachesizeKB;
        }
    }

    private class HandlerDriver extends Handler
        implements ThumbEngineDriver
    {

        private static final int DOSTEP_IDLE_INTERVAL = 100;
        private static final int DOSTEP_INTERVAL = 10;
        private static final int MSG_DOSTEP = 1;
        private static final int MSG_EDITITEM = 2;
        final ThumbEngine this$0;

        public void close()
        {
            stop();
        }

        public void editItem(int i)
        {
            Message message = Message.obtain();
            message.what = 2;
            message.arg1 = i;
            sendMessage(message);
        }

        public void handleMessage(Message message)
        {
            if (message.what != 1) goto _L2; else goto _L1
_L1:
            if (mStateMachine.isResume()) goto _L4; else goto _L3
_L3:
            return;
_L4:
            if (doStep() == 0x80002)
            {
                sendEmptyMessageDelayed(1, 100L);
                return;
            } else
            {
                sendEmptyMessageDelayed(1, 10L);
                return;
            }
_L2:
            if (message.what == 2)
            {
                int i = message.arg1;
                native_editItem(mNativeContext, i);
                if (i >= mPrefetchStart && i <= mPrefetchEnd)
                {
                    prefetItem(mPrefetchStart, mPrefetchEnd);
                    return;
                }
            }
            if (true) goto _L3; else goto _L5
_L5:
        }

        public void open()
        {
        }

        public void pause()
        {
            removeMessages(1);
        }

        public void prefetItem(int i, int j)
        {
            native_prefetch(mNativeContext, i, j);
        }

        public void resume()
        {
            removeMessages(1);
            sendEmptyMessageDelayed(1, 10L);
        }

        public void start()
        {
            sendEmptyMessageDelayed(1, 10L);
        }

        public void stop()
        {
            removeMessages(1);
        }

        private HandlerDriver()
        {
            this$0 = ThumbEngine.this;
            super();
        }

    }

    public static class InitParam
    {

        public DecodeParam allowparams[];
        public Const.ColorSpace colorSpace;
        public String pluginIniPath;

        public InitParam()
        {
        }
    }

    public static final class StepType extends Enum
    {

        private static final StepType $VALUES[];
        public static final StepType DECODE_PAGE;
        public static final StepType DO_ALL_TASK;
        public static final StepType RET_QUICKLY;
        final int nativeValue;

        public static StepType valueOf(String s)
        {
            return (StepType)Enum.valueOf(com/arcsoft/adk/image/ThumbEngine$StepType, s);
        }

        public static StepType[] values()
        {
            return (StepType[])$VALUES.clone();
        }

        static 
        {
            DO_ALL_TASK = new StepType("DO_ALL_TASK", 0, 0xa71100);
            DECODE_PAGE = new StepType("DECODE_PAGE", 1, 0xa71101);
            RET_QUICKLY = new StepType("RET_QUICKLY", 2, 0xa71102);
            StepType asteptype[] = new StepType[3];
            asteptype[0] = DO_ALL_TASK;
            asteptype[1] = DECODE_PAGE;
            asteptype[2] = RET_QUICKLY;
            $VALUES = asteptype;
        }

        private StepType(String s, int i, int j)
        {
            super(s, i);
            nativeValue = j;
        }
    }

    private static interface ThumbEngineDriver
    {

        public abstract void close();

        public abstract void editItem(int i);

        public abstract void open();

        public abstract void pause();

        public abstract void prefetItem(int i, int j);

        public abstract void resume();

        public abstract void start();

        public abstract void stop();
    }

    public static interface ThumbEngineListener
    {

        public abstract void onThumbReady(ThumbEngine thumbengine, int i);
    }

    public static class ThumbnailInfo
    {

        public boolean drmImg;
        public int exifRotate;
        public boolean fromExif;
        public boolean gif89a;
        public int imgFormat;
        public int orgHeight;
        public int orgWidth;
        public int zoomRatio;

        public ThumbnailInfo()
        {
        }
    }


    private static String CACHE_UID = com/arcsoft/adk/image/ThumbEngine.getSimpleName();
    private static final String TAG = "ThumbEngine";
    private static ThumbEngine sInstance = null;
    private static boolean sIsLocked = false;
    private final com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions mActions = new com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions() {

        final ThumbEngine this$0;

        public void onDisable()
        {
            ThumbEngine.this.onDisable();
        }

        public void onEnable()
        {
            ThumbEngine.this.onEnable();
        }

        public void onInit()
        {
            ThumbEngine.this.onInit();
        }

        public void onPause()
        {
            ThumbEngine.this.onPause();
        }

        public void onResume()
        {
            ThumbEngine.this.onResume();
        }

        public void onUninit()
        {
            ThumbEngine.this.onUninit();
        }

            
            {
                this$0 = ThumbEngine.this;
                super();
            }
    };
    private Application mApplication;
    private final com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener mCacheClearListener = new com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener() {

        final ThumbEngine this$0;

        public void onClearCacheFinished(String s)
        {
            if (!s.equals(ThumbEngine.CACHE_UID))
            {
                return;
            }
            Log.w("ThumbEngine", "TE: onClearCacheFinished");
            lockCachePath(false);
            createAMTE();
            if (mCurrentDecodeParam != null)
            {
                setOutputFormat(mCurrentDecodeParam);
            }
            if (mStateMachine.isEnable())
            {
                prefetch(mPrefetchStart, mPrefetchEnd);
                return;
            } else
            {
                native_enable(mNativeContext, false);
                return;
            }
        }

        public void onPrepareToClearCache(String s)
        {
            if (!s.equals(ThumbEngine.CACHE_UID))
            {
                return;
            } else
            {
                Log.w("ThumbEngine", "TE: onPrepareToClearCache");
                destroyAMTE();
                releaseCachePath(false);
                return;
            }
        }

            
            {
                this$0 = ThumbEngine.this;
                super();
            }
    };
    private String mCachePath;
    private DecodeParam mCurrentDecodeParam;
    private ThumbEngineDriver mDriver;
    private IFileList mFileList;
    private final Handler mHandler = new Handler() {

        final ThumbEngine this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 10948608 10948608: default 28
        //                       10948608 29;
               goto _L1 _L2
_L1:
            return;
_L2:
            int k = message.arg1;
            if (mThumbEngineListener != null)
            {
                mThumbEngineListener.onThumbReady(ThumbEngine.this, k);
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

            
            {
                this$0 = ThumbEngine.this;
                super();
            }
    };
    private final InitParam mInitParam = new InitParam();
    private volatile boolean mIsSlowDown;
    private int mNativeContext;
    private int mPrefetchEnd;
    private int mPrefetchStart;
    private final DynamicDataStateMachine mStateMachine = new DynamicDataStateMachine("ThumbEngine");
    private ThumbEngineListener mThumbEngineListener;

    private ThumbEngine(Application application, InitParam initparam)
    {
        mApplication = null;
        mFileList = null;
        mThumbEngineListener = null;
        mPrefetchStart = -1;
        mPrefetchEnd = -1;
        mIsSlowDown = false;
        mNativeContext = 0;
        mApplication = application;
        if (initparam == null)
        {
            throw new NullPointerException();
        }
        if (initparam.allowparams == null)
        {
            throw new IllegalArgumentException("Must pass at least 1 allowparam to thumbengine");
        }
        DecodeParam adecodeparam[] = initparam.allowparams;
        int i = adecodeparam.length;
        for (int j = 0; j < i; j++)
        {
            DecodeParam decodeparam = adecodeparam[j];
            if (decodeparam.width <= 0 || decodeparam.height <= 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid thumb size. thumbWidth: ").append(decodeparam.width).append("thumbHeight: ").append(decodeparam.height).toString());
            }
        }

        mInitParam.allowparams = initparam.allowparams;
        mInitParam.colorSpace = initparam.colorSpace;
        mInitParam.pluginIniPath = initparam.pluginIniPath;
        mStateMachine.setOnStateChangeActions(mActions);
    }

    private void createAMTE()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mNativeContext;
        if (i == 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        CacheInfo acacheinfo[];
        File file = new File(mCachePath);
        if (!file.isDirectory())
        {
            if (file.mkdirs());
        }
        acacheinfo = new CacheInfo[mInitParam.allowparams.length];
        int j = 0;
_L4:
        if (j >= acacheinfo.length)
        {
            break; /* Loop/switch isn't completed */
        }
        acacheinfo[j] = new CacheInfo();
        acacheinfo[j].width = mInitParam.allowparams[j].width;
        acacheinfo[j].height = mInitParam.allowparams[j].height;
        acacheinfo[j].type = mInitParam.allowparams[j].displaymode.nativeValue;
        acacheinfo[j].isUseExifThumb = true;
        acacheinfo[j].FolderNumUsed = 1;
        CacheInfo cacheinfo = acacheinfo[j];
        String as[] = new String[1];
        as[0] = Environment.getExternalStorageDirectory().getPath();
        cacheinfo.srcFolders = as;
        CacheInfo cacheinfo1 = acacheinfo[j];
        String as1[] = new String[1];
        as1[0] = mCachePath;
        cacheinfo1.cacheFolders = as1;
        CacheInfo cacheinfo2 = acacheinfo[j];
        int ai[] = new int[1];
        ai[0] = mInitParam.allowparams[j].maxcachesizeKB;
        cacheinfo2.cacheLimiteds = ai;
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        mNativeContext = native_create(acacheinfo, acacheinfo[0].width, acacheinfo[0].height, mInitParam.colorSpace.nativeValue, mInitParam.pluginIniPath);
        native_setMaxRam(mNativeContext, 10240);
        if (true) goto _L1; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    private static Bitmap create_lock_bitmap(int i, int j)
    {
        return Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.RGB_565);
    }

    private void destroyAMTE()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mNativeContext;
        if (i != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        native_destroy(mNativeContext);
        mNativeContext = 0;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private void init()
    {
        mStateMachine.init();
    }

    public static void initSingleton(Application application, InitParam initparam)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new ThumbEngine(application, initparam);
            sInstance.init();
            return;
        }
    }

    static void loadLibrarys()
    {
        SystemUtils.loadLibrary("platform");
        SystemUtils.loadLibrary("arcplatform");
        SystemUtils.loadLibrary("arcimgutilsbase");
        int i = SystemUtils.getSDKVersion();
        if (i < 11)
        {
            SystemUtils.loadLibrary("arcthumbengine_2_2");
            return;
        }
        if (i < 19)
        {
            SystemUtils.loadLibrary("arcthumbengine_4_0");
            return;
        } else
        {
            SystemUtils.loadLibrary("arcthumbengine_4_4");
            return;
        }
    }

    public static ThumbEngine lock()
    {
        if (sInstance == null)
        {
            return null;
        }
        if (sIsLocked)
        {
            return sInstance;
        } else
        {
            sIsLocked = true;
            return sInstance;
        }
    }

    private void lockCachePath(boolean flag)
    {
        String s;
        Log.i("ThumbEngine", (new StringBuilder()).append("TE: lock Cache Path, register listener = ").append(flag).toString());
        s = null;
        CachePathManager cachepathmanager;
        StringBuilder stringbuilder;
        cachepathmanager = CachePathManager.instance();
        s = cachepathmanager.lockCachePath(CACHE_UID, 0);
        stringbuilder = (new StringBuilder()).append("TE: Lock Cache Path from Cache Manager, dir = ");
        String s1;
        if (s == null)
        {
            s1 = "NULL";
        } else
        {
            s1 = s;
        }
        Log.w("ThumbEngine", stringbuilder.append(s1).toString());
        if (flag)
        {
            try
            {
                Log.w("ThumbEngine", "TE: Register listenern to cache manager");
                cachepathmanager.registerClearCacheListener(CACHE_UID, mCacheClearListener);
            }
            catch (IOException ioexception)
            {
                Log.e("ThumbEngine", "TE: Exception occurred when lock cache path");
                ioexception.printStackTrace();
            }
        }
        mCachePath = s;
        Log.w("ThumbEngine", (new StringBuilder()).append("TE: set Cache folder : ").append(mCachePath).toString());
        return;
    }

    private synchronized native void native_EnableCache(int i, boolean flag);

    private synchronized native int native_GetThumbEngineHandle(int i);

    private synchronized native ThumbnailInfo native_GetThumbnailInfo(int i, int j);

    private synchronized native int native_create(CacheInfo acacheinfo[], int i, int j, int k, String s);

    private synchronized native void native_destroy(int i);

    private synchronized native int native_doStep(int i, int j);

    private synchronized native void native_editItem(int i, int j);

    private synchronized native void native_enable(int i, boolean flag);

    private synchronized native void native_insertItem(int i, int j);

    private synchronized native Bitmap native_lock(int i, int j);

    private synchronized native Bitmap native_lock2(int i, int j);

    private synchronized native int native_prefetch(int i, int j, int k);

    private synchronized native void native_refresh(int i);

    private synchronized native void native_removeItem(int i, int j);

    private synchronized native void native_setDispMode(int i, int j);

    private synchronized native void native_setMaxRam(int i, int j);

    private synchronized native void native_setOutputThumbSize(int i, int j, int k);

    private synchronized native void native_setSyncLock(int i, boolean flag);

    private synchronized native void native_unlock(int i, int j);

    private void onDisable()
    {
        if (mDriver != null)
        {
            mDriver.stop();
        }
        native_enable(mNativeContext, false);
        mHandler.removeMessages(0xa71000);
        mPrefetchStart = -1;
        mPrefetchEnd = -1;
    }

    private void onEnable()
    {
        native_enable(mNativeContext, true);
        mPrefetchStart = -1;
        mPrefetchEnd = -1;
        if (mDriver != null)
        {
            mDriver.start();
        }
    }

    private void onInit()
    {
        lockCachePath(true);
        createAMTE();
        native_enable(mNativeContext, false);
        if (mDriver == null)
        {
            mDriver = new HandlerDriver();
            mDriver.open();
        }
    }

    private void onPause()
    {
        if (mDriver != null)
        {
            mDriver.pause();
        }
    }

    private void onResume()
    {
        slowDown(false);
        if (mDriver != null)
        {
            mDriver.resume();
        }
    }

    private void onUninit()
    {
        if (mDriver != null)
        {
            mDriver.close();
        }
        destroyAMTE();
        releaseCachePath(true);
    }

    private void releaseCachePath(boolean flag)
    {
        Log.w("ThumbEngine", (new StringBuilder()).append("TE: release cache path, unregister = ").append(flag).toString());
        CachePathManager cachepathmanager = CachePathManager.instance();
        if (flag)
        {
            Log.w("ThumbEngine", "TE: UnRegister cache path");
            cachepathmanager.unregisterClearCacheListener(CACHE_UID, mCacheClearListener);
        }
        Log.w("ThumbEngine", "TE: UnLock Cache Path");
        cachepathmanager.unlockCachePath(CACHE_UID);
        mCachePath = null;
    }

    private void unInit()
    {
        mStateMachine.unInit();
    }

    public static void unLock(ThumbEngine thumbengine)
    {
        while (sInstance == null || !sIsLocked || sInstance != thumbengine) 
        {
            return;
        }
        sIsLocked = false;
    }

    public static void uninitSingleton()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.unInit();
            sInstance = null;
            return;
        }
    }

    int doStep()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        System.currentTimeMillis();
        if (!mIsSlowDown)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        i = StepType.RET_QUICKLY.nativeValue;
_L1:
        int j = native_doStep(mNativeContext, i);
        this;
        JVM INSTR monitorexit ;
        return j;
        i = StepType.DO_ALL_TASK.nativeValue;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void editItem(int i)
    {
        if (mDriver != null)
        {
            mDriver.editItem(i);
        }
    }

    public void enable(boolean flag)
    {
        if (flag)
        {
            mStateMachine.enable();
            return;
        } else
        {
            mStateMachine.disable();
            return;
        }
    }

    public Bitmap getBitmap(int i)
    {
        long l = System.currentTimeMillis();
        Bitmap bitmap = native_lock2(mNativeContext, i);
        long l1 = System.currentTimeMillis();
        if (l1 - l > 0L)
        {
            Log.w("ThumbEngine", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("[ ").append(i).append(" ] cost: ").append(l1 - l).toString());
        }
        return bitmap;
    }

    int getFileCount()
    {
        if (mFileList == null)
        {
            return 0;
        } else
        {
            return mFileList.getCount();
        }
    }

    String getFilePath(int i)
    {
        String s;
        if (mFileList == null)
        {
            s = null;
        } else
        {
            s = mFileList.getFilePath(i);
            if (s != null && s.lastIndexOf(".") == -1)
            {
                return null;
            }
        }
        return s;
    }

    public ThumbnailInfo getThumbnailInfo(int i)
    {
        return native_GetThumbnailInfo(mNativeContext, i);
    }

    public void insertItem(int i)
    {
        native_insertItem(mNativeContext, i);
    }

    public boolean isSlowDown()
    {
        return mIsSlowDown;
    }

    void onNotify(int i, int j)
    {
        if (i == 0xa71000)
        {
            Message message = mHandler.obtainMessage();
            message.what = i;
            message.arg1 = j;
            mHandler.sendMessage(message);
        }
    }

    public void pause()
    {
        mStateMachine.pause();
    }

    public void prefetch(int i, int j)
    {
        if (!mStateMachine.isEnable())
        {
            throw new IllegalStateException("Not enable");
        }
        if (i > j)
        {
            throw new IllegalArgumentException("Invalid param");
        }
        int k = getFileCount();
        if (k > 0)
        {
            if (i < 0)
            {
                i = 0;
            }
            if (j > k - 1)
            {
                j = k - 1;
            }
            if (i < mPrefetchStart || j > mPrefetchEnd)
            {
                mPrefetchStart = i;
                mPrefetchEnd = j;
                native_prefetch(mNativeContext, i, j);
                return;
            }
        }
    }

    public void refresh()
    {
        native_refresh(mNativeContext);
        mPrefetchStart = -1;
        mPrefetchEnd = -1;
    }

    public void removeItem(int i)
    {
        native_removeItem(mNativeContext, i);
    }

    public void resume()
    {
        mStateMachine.resume();
    }

    public void setFileList(IFileList ifilelist)
    {
        if (mFileList == ifilelist)
        {
            return;
        } else
        {
            mFileList = ifilelist;
            refresh();
            return;
        }
    }

    public void setOutputFormat(DecodeParam decodeparam)
    {
        DecodeParam adecodeparam[] = mInitParam.allowparams;
        int i = adecodeparam.length;
        for (int j = 0; j < i; j++)
        {
            if (adecodeparam[j].equals(decodeparam))
            {
                mCurrentDecodeParam = new DecodeParam(decodeparam);
                native_setOutputThumbSize(mNativeContext, decodeparam.width, decodeparam.height);
                native_setDispMode(mNativeContext, decodeparam.displaymode.nativeValue);
                return;
            }
        }

        throw new IllegalArgumentException("Must pass a decode param ThumbEngine Supported, See ThumbEngine.initSingleton()");
    }

    public void setThumbEngineListener(ThumbEngineListener thumbenginelistener)
    {
        mThumbEngineListener = thumbenginelistener;
    }

    public void slowDown(boolean flag)
    {
        mIsSlowDown = flag;
    }

    static 
    {
        loadLibrarys();
    }




















}
