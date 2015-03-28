// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Process;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.picture.data.LocalCacheProvider;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.picture.controller:
//            CacheMgr, LocalFrameDecoder, DecodeTask

public class LocalCacheMgr
    implements com.arcsoft.mediaplus.picture.data.LocalCacheProvider.DecodeTaskStatus, CacheMgr
{

    private final String TAG = "LocalCacheMgr";
    private final LocalCacheProvider mCacheProvider;
    private final Context mContext;
    IDataSource mDataSource;
    private volatile boolean mDecodeExit;
    private final DecodeTask mDecodeTask;
    private Thread mDecodeThread;
    com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mOnDataChangeListener;
    private volatile boolean mRomoteFlag;
    private final Object mSyncDataObject = new Object();
    private volatile boolean mTaskBusy;

    private LocalCacheMgr(Context context)
    {
        mTaskBusy = false;
        mDecodeExit = false;
        mDecodeThread = null;
        mDataSource = null;
        mRomoteFlag = false;
        mOnDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

            final LocalCacheMgr this$0;

            public void onCountChanged(int i, int j)
            {
                Log.e("LocalCacheMgr", "onCountChanged");
            }

            public void onDataChanged(int i, Property property)
            {
                Log.e("LocalCacheMgr", "onDataChanged");
            }

            
            {
                this$0 = LocalCacheMgr.this;
                super();
            }
        };
        mContext = context;
        mCacheProvider = LocalCacheProvider.create(mContext, this);
        mCacheProvider.setThumbnailDecoder(new LocalFrameDecoder());
        mDecodeTask = new DecodeTask(mCacheProvider);
    }

    public static LocalCacheMgr create(Context context)
    {
        return new LocalCacheMgr(context);
    }

    private void destroy()
    {
        if (mDecodeThread != null)
        {
            mDecodeExit = true;
            try
            {
                mDecodeThread.join();
            }
            catch (InterruptedException interruptedexception)
            {
                mDecodeThread = null;
                interruptedexception.printStackTrace();
            }
            mDecodeThread = null;
        }
        if (mDataSource != null)
        {
            mDataSource.unregisterOnDataChangeListener(mOnDataChangeListener);
            mDataSource = null;
        }
    }

    public static void destroy(LocalCacheMgr localcachemgr)
    {
        if (localcachemgr != null)
        {
            localcachemgr.destroy();
        }
    }

    private void syncCache()
    {
        mDecodeExit = false;
        mDecodeThread = new Thread("thread_decode") {

            final LocalCacheMgr this$0;

            public void run()
            {
                int i;
                int j;
                Intent intent = new Intent("com.arcsoft.picture.sync_cache_begin");
                mContext.sendBroadcast(intent);
                Process.setThreadPriority(10);
                i = mDataSource.getCount();
                j = 0;
_L3:
                if (j >= i || mDecodeExit)
                {
                    mCacheProvider.removeInvalidFiles();
                    mDecodeExit = false;
                    Intent intent1 = new Intent("com.arcsoft.picture.sync_cache_end");
                    mContext.sendBroadcast(intent1);
                    return;
                }
                if (!mTaskBusy)
                {
                    break MISSING_BLOCK_LABEL_133;
                }
                Object obj = mSyncDataObject;
                obj;
                JVM INSTR monitorenter ;
                mSyncDataObject.wait();
_L1:
                Exception exception;
                InterruptedException interruptedexception;
                if (j < mDataSource.getCount())
                {
                    MediaItem mediaitem = mDataSource.getItem(j);
                    if (!mCacheProvider.isThumbCached(mediaitem))
                    {
                        if (mRomoteFlag)
                        {
                            mediaitem.strDecodePath = mDataSource.getStringProp(j, Property.PROP_THUMBNAIL_FILEPATH, null);
                        }
                        Bitmap bitmap = mCacheProvider.createThumbnail(mediaitem, 128, 128);
                        if (bitmap != null)
                        {
                            android.opengl.ETC1Util.ETC1Texture etc1texture = mCacheProvider.getETC1Texture(bitmap);
                            mCacheProvider.saveETCToDatabase(mediaitem, etc1texture);
                            bitmap.recycle();
                        }
                    }
                }
                j++;
                continue; /* Loop/switch isn't completed */
                interruptedexception;
                interruptedexception.printStackTrace();
                  goto _L1
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
                if (true) goto _L3; else goto _L2
_L2:
            }

            
            {
                this$0 = LocalCacheMgr.this;
                super(s);
            }
        };
        mDecodeThread.start();
    }

    public void closeCacheDB()
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.closeDB();
        }
    }

    public Bitmap getAlbum(MediaItem mediaitem)
    {
        return mCacheProvider.getAlbum(mediaitem);
    }

    public void onTaskBusy()
    {
        mTaskBusy = true;
    }

    public void onTaskFree()
    {
        mTaskBusy = false;
        synchronized (mSyncDataObject)
        {
            mSyncDataObject.notify();
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void recreateCache()
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.createCache();
        }
    }

    public void removeAllTask()
    {
        mDecodeTask.removeAllTask();
    }

    public void setDataSource(IDataSource idatasource)
    {
        Log.e("LocalCacheMgr", (new StringBuilder()).append("setDataSource: ").append(idatasource).toString());
        if (idatasource == null)
        {
            destroy();
        }
        mDataSource = idatasource;
        if (idatasource != null)
        {
            idatasource.registerOnDataChangeListener(mOnDataChangeListener);
            if (idatasource.isEnable() && idatasource.isResume() && idatasource.isReady())
            {
                Log.d("LocalCacheMgr", "SyncCache() dataSource is ready");
                syncCache();
            }
        }
    }

    public void setRomoteFlag(boolean flag)
    {
        mRomoteFlag = flag;
        if (mCacheProvider != null)
        {
            mCacheProvider.setRomoteFlag(flag);
        }
    }

    public void submitTask(DecodeTask.DecodeListener decodelistener, MediaItem mediaitem, int i)
    {
        mDecodeTask.submitTask(decodelistener, mediaitem, i);
    }

    public void updateListForDelete(int ai[])
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.updateListForDelete(ai);
        }
    }

    public void updateListForDeleteDownLoad(String s)
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.updateListForDeleteDownload(s);
        }
    }

    public void updateListForDownload(String s)
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.updateListForDownloadOrEdit(s, true);
        }
    }

    public void updateListForEdit(String s)
    {
        if (mCacheProvider != null)
        {
            mCacheProvider.updateListForDownloadOrEdit(s, false);
        }
    }




/*
    static boolean access$102(LocalCacheMgr localcachemgr, boolean flag)
    {
        localcachemgr.mDecodeExit = flag;
        return flag;
    }

*/




}
