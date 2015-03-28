// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class CachePathManager
{
    private class CacheDirInfo
    {

        int cachetype;
        boolean flagclear;
        boolean flaglocked;
        final CachePathManager this$0;

        private CacheDirInfo()
        {
            this$0 = CachePathManager.this;
            super();
            cachetype = 0;
            flaglocked = false;
            flagclear = false;
        }

    }

    private class ClearCacheThread extends Thread
    {

        Handler mHandler;
        AtomicBoolean quitFlag;
        final CachePathManager this$0;

        private boolean deleteRecurisive(String s)
        {
            File file;
            for (file = new File(s); !file.exists() || file.isFile();)
            {
                return false;
            }

            File afile[] = file.listFiles();
            int i = afile.length;
            int j = 0;
            while (j < i) 
            {
                File file1 = afile[j];
                if (file1.isDirectory())
                {
                    deleteRecurisive(file1.getAbsolutePath());
                } else
                if (!file1.delete())
                {
                    Log.e("CacheManager", (new StringBuilder()).append(file1.getAbsolutePath()).append(" can't deleted!").toString());
                }
                j++;
            }
            if (!file.delete())
            {
                Log.e("CacheManager", (new StringBuilder()).append(file.getAbsolutePath()).append(" can't delete!").toString());
                return false;
            } else
            {
                return true;
            }
        }

        public void quit()
        {
            this;
            JVM INSTR monitorenter ;
            notify();
            this;
            JVM INSTR monitorexit ;
            mHandler.removeMessages(0);
            quitFlag.set(true);
            Exception exception;
            try
            {
                join(2000L);
                return;
            }
            catch (Exception exception1)
            {
                return;
            }
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void run()
        {
            File file = new File(OEMConfig.CACHE_BASE_PATH);
_L2:
            if (quitFlag.get())
            {
                break; /* Loop/switch isn't completed */
            }
            File afile[] = file.listFiles(new FilenameFilter() {

                final ClearCacheThread this$1;

                public boolean accept(File file, String s)
                {
                    return s != null && s.endsWith("_delete");
                }

            
            {
                this$1 = ClearCacheThread.this;
                super();
            }
            });
            this;
            JVM INSTR monitorenter ;
            if (afile == null)
            {
                break MISSING_BLOCK_LABEL_49;
            }
            int k = afile.length;
            if (k > 0)
            {
                break MISSING_BLOCK_LABEL_74;
            }
            Log.e("CleatThread", "Wait for task");
            wait();
            this;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            this;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            setClearing(true);
            int i = afile.length;
            for (int j = 0; j < i; j++)
            {
                File file1 = afile[j];
                Log.e("CleatThread", (new StringBuilder()).append("Delete Cache directory : ").append(file1.getAbsolutePath()).toString());
                deleteRecurisive(file1.getAbsolutePath());
            }

            setClearing(false);
            if (true) goto _L2; else goto _L1
_L1:
        }

        ClearCacheThread()
        {
            this$0 = CachePathManager.this;
            super();
            quitFlag = new AtomicBoolean(false);
            mHandler = new _cls1();
            mHandler.sendEmptyMessageDelayed(0, 60000L);
        }
    }

    public static interface IOnCacheClearCacheListener
    {

        public abstract void onClearCacheFinished(String s);

        public abstract void onPrepareToClearCache(String s);
    }


    public static final int CACHE_PATH_DATA = 1;
    public static final int CACHE_PATH_STORAGE = 0;
    private static final String TAG = "CacheManager";
    private static CachePathManager sInstance = null;
    private Application mApp;
    private final HashMap mCacheInfoMap = new HashMap();
    private final ClearCacheThread mClearCacheThread = new ClearCacheThread();
    private final Handler mHandler = new Handler();
    private boolean mIsCacheClearing;
    private final HashMap mListeners = new HashMap();

    private CachePathManager(Application application)
    {
        mApp = null;
        mIsCacheClearing = false;
        mApp = application;
        mClearCacheThread.start();
    }

    private void callFinishedListeners(final String uid)
    {
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mListeners.get(uid);
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        final IOnCacheClearCacheListener listener;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); mHandler.post(new Runnable() {

        final CachePathManager this$0;
        final IOnCacheClearCacheListener val$listener;
        final String val$uid;

        public void run()
        {
            listener.onClearCacheFinished(uid);
        }

            
            {
                this$0 = CachePathManager.this;
                listener = ioncacheclearcachelistener;
                uid = s;
                super();
            }
    }))
        {
            listener = (IOnCacheClearCacheListener)iterator.next();
        }

        break MISSING_BLOCK_LABEL_81;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        hashmap;
        JVM INSTR monitorexit ;
    }

    private void callPrepareListeners(final String uid)
    {
        this;
        JVM INSTR monitorenter ;
        if (!isClearing())
        {
            setClearing(true);
        }
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mListeners.get(uid);
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        final IOnCacheClearCacheListener listener;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); mHandler.post(new Runnable() {

        final CachePathManager this$0;
        final IOnCacheClearCacheListener val$listener;
        final String val$uid;

        public void run()
        {
            listener.onPrepareToClearCache(uid);
        }

            
            {
                this$0 = CachePathManager.this;
                listener = ioncacheclearcachelistener;
                uid = s;
                super();
            }
    }))
        {
            listener = (IOnCacheClearCacheListener)iterator.next();
        }

        break MISSING_BLOCK_LABEL_102;
        Exception exception1;
        exception1;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        hashmap;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorexit ;
    }

    private String getDirNameByUID(String s, int i)
    {
        if (i != 0)
        {
            throw new UnsupportedOperationException("Only support CACHE_PATH_STORAGE yet");
        } else
        {
            String s1 = (new StringBuilder()).append(".").append(s).toString();
            return (new File(OEMConfig.CACHE_BASE_PATH, s1)).getAbsolutePath();
        }
    }

    public static void initSingleton(Application application)
    {
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorenter ;
        if (sInstance != null)
        {
            throw new IllegalStateException("Call initSingleton MultiTimes");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
        throw exception;
        sInstance = new CachePathManager(application);
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
    }

    public static CachePathManager instance()
    {
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            throw new IllegalStateException("Has not call initSingleton");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
        throw exception;
        CachePathManager cachepathmanager = sInstance;
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
        return cachepathmanager;
    }

    private void logCacheInfo(String s, CacheDirInfo cachedirinfo)
    {
        if (cachedirinfo == null)
        {
            return;
        }
        StringBuilder stringbuilder = (new StringBuilder()).append("Cache info : uid = ").append(s).append("Cache Type = ");
        String s1;
        if (cachedirinfo.cachetype == 0)
        {
            s1 = "Storage";
        } else
        {
            s1 = "Data";
        }
        Log.i("CacheManager", stringbuilder.append(s1).append(", LockFlag = ").append(cachedirinfo.flaglocked).append(", ClearFlag = ").append(cachedirinfo.flagclear).toString());
    }

    private void logCacheType(String s, int i)
    {
        Log.i("CacheManager", (new StringBuilder()).append("UID = ").append(s).append(" type = ").append(i).toString());
    }

    private String renametodelete(String s, int i)
    {
        String s1 = null;
        this;
        JVM INSTR monitorenter ;
        File file = new File(getDirNameByUID(s, i));
        if (file.isDirectory()) goto _L2; else goto _L1
_L1:
        Log.e("CacheManager", "Remame Failed : not a dir");
_L4:
        this;
        JVM INSTR monitorexit ;
        return s1;
_L2:
        File file1 = new File(file.getParent(), (new StringBuilder()).append(file.getName()).append("_").append(System.currentTimeMillis()).append("_delete").toString());
        if (file.renameTo(file1))
        {
            Log.e("CacheManager", "Remame Suc");
            s1 = file1.getAbsolutePath();
            continue; /* Loop/switch isn't completed */
        }
        Log.e("CacheManager", "Remame Failed : Can not rename");
        s1 = null;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private void setClearing(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mIsCacheClearing = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void uninitSingleton()
    {
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorenter ;
        if (sInstance == null)
        {
            throw new IllegalStateException("Has not call initSingleton");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
        throw exception;
        sInstance.mClearCacheThread.quit();
        sInstance = null;
        com/arcsoft/util/tool/CachePathManager;
        JVM INSTR monitorexit ;
    }

    public void clearAllCache()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        Iterator iterator;
        Log.w("CacheManager", "Clear cache Start..............");
        arraylist = new ArrayList();
        Set set = mCacheInfoMap.keySet();
        Log.w("CacheManager", "view all cache info start..");
        iterator = set.iterator();
_L1:
        String s;
        CacheDirInfo cachedirinfo;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_256;
        }
        s = (String)iterator.next();
        Log.w("CacheManager", "view all cache info next----------------------------");
        cachedirinfo = (CacheDirInfo)mCacheInfoMap.get(s);
        if (cachedirinfo != null)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        Log.e("CacheManager", (new StringBuilder()).append("Cache info == null, UID = ").append(s).toString());
          goto _L1
        Exception exception;
        exception;
        throw exception;
label0:
        {
            logCacheInfo(s, cachedirinfo);
            if (!cachedirinfo.flagclear)
            {
                Log.w("CacheManager", "Cache info clear flag is true, call prepare to clear");
                callPrepareListeners(s);
            }
            if (!cachedirinfo.flaglocked)
            {
                break label0;
            }
            Log.e("CacheManager", "Cache is locked, set Clear Flag");
            cachedirinfo.flagclear = true;
        }
          goto _L1
        String s1;
        Log.e("CacheManager", "Cache is not locked, clear dir");
        s1 = renametodelete(s, cachedirinfo.cachetype);
        cachedirinfo.flagclear = false;
        callFinishedListeners(s);
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_244;
        }
        Log.e("CacheManager", "Call finish listners");
        arraylist.add(s1);
          goto _L1
        Log.e("CacheManager", "Rename Failed!!!!!!!!!!!!!!!");
          goto _L1
        Log.e("CacheManager", "Search all cache finish, create thread to clear all cache!!!");
        if (arraylist.size() > 0)
        {
            synchronized (mClearCacheThread)
            {
                mClearCacheThread.notify();
            }
        }
        Log.w("CacheManager", "Clear cache End.............");
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        clearcachethread;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void clearCache(String s)
    {
        this;
        JVM INSTR monitorenter ;
        CacheDirInfo cachedirinfo = (CacheDirInfo)mCacheInfoMap.get(s);
        if (cachedirinfo != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!cachedirinfo.flagclear)
        {
            callPrepareListeners(s);
        }
        if (cachedirinfo.flaglocked)
        {
            cachedirinfo.flagclear = true;
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_53;
        Exception exception;
        exception;
        throw exception;
        String s1;
        s1 = renametodelete(s, cachedirinfo.cachetype);
        cachedirinfo.flagclear = false;
        callFinishedListeners(s);
        if (s1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        synchronized (mClearCacheThread)
        {
            mClearCacheThread.notify();
        }
        if (true) goto _L1; else goto _L3
_L3:
        exception1;
        clearcachethread;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public boolean isClearing()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsCacheClearing;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public String lockCachePath(String s, int i)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        CacheDirInfo cachedirinfo;
        Log.w("CacheManager", "lock Cache....");
        logCacheType(s, i);
        cachedirinfo = (CacheDirInfo)mCacheInfoMap.get(s);
        if (cachedirinfo == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        Log.i("CacheManager", "Find cache info not null");
        logCacheInfo(s, cachedirinfo);
        flag = cachedirinfo.flaglocked;
        if (!flag) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException((new StringBuilder()).append("Cache path (uid = ").append(s).append(" already in use").toString());
        Exception exception2;
        exception2;
        exception2.printStackTrace();
_L2:
        File file;
        file = new File(getDirNameByUID(s, i));
        if (file.exists() && !file.isDirectory())
        {
            throw new IOException((new StringBuilder()).append("Corresponded with the uid=").append(s).append(" has exists but not a dir").toString());
        }
        break MISSING_BLOCK_LABEL_226;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
_L4:
        if (cachedirinfo.cachetype == i || renametodelete(s, cachedirinfo.cachetype) == null) goto _L2; else goto _L5
_L5:
        synchronized (mClearCacheThread)
        {
            mClearCacheThread.notify();
        }
          goto _L2
        exception1;
        clearcachethread;
        JVM INSTR monitorexit ;
        throw exception1;
        file.mkdirs();
        if (file.isDirectory())
        {
            break MISSING_BLOCK_LABEL_325;
        }
        Log.e("CacheManager", "Make Dir Failed!!!!!!!");
_L6:
        if (cachedirinfo != null)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        cachedirinfo = new CacheDirInfo();
        mCacheInfoMap.put(s, cachedirinfo);
        String s1;
        cachedirinfo.cachetype = i;
        cachedirinfo.flaglocked = true;
        Log.i("CacheManager", "Set cache info");
        logCacheInfo(s, cachedirinfo);
        Log.w("CacheManager", "Lock Cache end..............");
        s1 = file.getAbsolutePath();
        this;
        JVM INSTR monitorexit ;
        return s1;
        Log.e("CacheManager", "Make Dir Success");
          goto _L6
    }

    public void registerClearCacheListener(String s, IOnCacheClearCacheListener ioncacheclearcachelistener)
    {
        if (s == null || ioncacheclearcachelistener == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mListeners.get(s);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_71;
        }
        ArrayList arraylist1 = new ArrayList();
        arraylist1.add(ioncacheclearcachelistener);
        mListeners.put(s, arraylist1);
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return;
        arraylist.add(ioncacheclearcachelistener);
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void unlockCachePath(String s)
    {
        this;
        JVM INSTR monitorenter ;
        CacheDirInfo cachedirinfo;
        Log.w("CacheManager", (new StringBuilder()).append("UnLock Cache Start... UID = ").append(s).toString());
        cachedirinfo = (CacheDirInfo)mCacheInfoMap.get(s);
        if (cachedirinfo == null) goto _L2; else goto _L1
_L1:
        if (cachedirinfo.flaglocked) goto _L3; else goto _L2
_L2:
        if (cachedirinfo != null) goto _L5; else goto _L4
_L4:
        Log.e("CacheManager", "UnLock Cache Exception (null Cache info)!!!!!!!!!!!!!!!!!");
_L6:
        throw new IllegalStateException((new StringBuilder()).append("uid=").append(s).append(" not  locked").toString());
        Exception exception1;
        exception1;
        exception1.printStackTrace();
_L7:
        this;
        JVM INSTR monitorexit ;
        return;
_L5:
        Log.e("CacheManager", "UnLock Cache Exception (unlocked flag)!!!!!!!!!!!!!!!!!");
          goto _L6
        Exception exception;
        exception;
        throw exception;
_L3:
        String s1;
        logCacheInfo(s, cachedirinfo);
        cachedirinfo.flaglocked = false;
        if (!cachedirinfo.flagclear)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        Log.w("CacheManager", "Clear Flag is true, rename to delete");
        s1 = renametodelete(s, cachedirinfo.cachetype);
        cachedirinfo.flagclear = false;
        Log.e("CacheManager", "Call finish listners");
        callFinishedListeners(s);
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        synchronized (mClearCacheThread)
        {
            mClearCacheThread.notify();
        }
        Log.w("CacheManager", "Unlock Cache end..............");
          goto _L7
        exception2;
        clearcachethread;
        JVM INSTR monitorexit ;
        throw exception2;
          goto _L6
    }

    public void unregisterClearCacheListener(String s, IOnCacheClearCacheListener ioncacheclearcachelistener)
    {
        if (s == null || ioncacheclearcachelistener == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mListeners;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mListeners.get(s);
        if (arraylist == null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        arraylist.remove(ioncacheclearcachelistener);
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }



    // Unreferenced inner class com/arcsoft/util/tool/CachePathManager$ClearCacheThread$1

/* anonymous class */
    class ClearCacheThread._cls1 extends Handler
    {

        final ClearCacheThread this$1;

        public void handleMessage(Message message)
        {
            synchronized (ClearCacheThread.this)
            {
                notify();
            }
            sendEmptyMessageDelayed(0, 60000L);
            return;
            exception;
            clearcachethread;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                this$1 = ClearCacheThread.this;
                super();
            }
    }

}
