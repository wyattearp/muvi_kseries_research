// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import android.graphics.Bitmap;
import android.os.Process;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.picture.data.CacheProvider;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DecodeTask
{
    public class DecodeJob
        implements Runnable
    {

        private final DecodeListener mDecodeListener;
        private final DecodeTask mDecodeTask;
        private int mIndex;
        private final MediaItem mItem;
        final DecodeTask this$0;

        public void run()
        {
            if (mDecodeListener != null)
            {
                android.opengl.ETC1Util.ETC1Texture etc1texture = mDecodeTask.mCacheProvider.getETC1Texture(mItem);
                if (mDecodeListener != null && etc1texture != null)
                {
                    mDecodeListener.onETCDone(mIndex, mItem, etc1texture);
                }
                int i = ((DecodeJob) (this)).mDecodeTask;
                if (mDecodeTask.mCompletedCount == mDecodeTask.mTotalCount)
                {
                    mDecodeTask.mCacheProvider.onTaskFree();
                }
            }
        }

        public DecodeJob(DecodeTask decodetask1, DecodeListener decodelistener, MediaItem mediaitem, int i)
        {
            this$0 = DecodeTask.this;
            super();
            mIndex = 0;
            mDecodeListener = decodelistener;
            mDecodeTask = decodetask1;
            mItem = mediaitem;
            mIndex = i;
        }
    }

    public static interface DecodeListener
    {

        public abstract void onBitmapDone(MediaItem mediaitem, Bitmap bitmap);

        public abstract void onETCDone(int i, MediaItem mediaitem, android.opengl.ETC1Util.ETC1Texture etc1texture);
    }

    public class PriorityThreadFactory
        implements ThreadFactory
    {

        private final String mName;
        private final AtomicInteger mNumber = new AtomicInteger();
        private final int mPriority;
        final DecodeTask this$0;

        public Thread newThread(Runnable runnable)
        {
            return new Thread(runnable, (new StringBuilder()).append(mName).append('-').append(mNumber.getAndIncrement()).toString()) {

                final PriorityThreadFactory this$1;

                public void run()
                {
                    Process.setThreadPriority(mPriority);
                    super.run();
                }

            
            {
                this$1 = PriorityThreadFactory.this;
                super(runnable, s);
            }
            };
        }


        public PriorityThreadFactory(String s, int i)
        {
            this$0 = DecodeTask.this;
            super();
            mName = s;
            mPriority = i;
        }
    }


    private static final int CORE_POOL_SIZE = 0;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final int MAX_POOL_SIZE = 1;
    private final CacheProvider mCacheProvider;
    private volatile int mCompletedCount;
    private ThreadPoolExecutor mExecutor;
    private volatile int mTotalCount;

    public DecodeTask(CacheProvider cacheprovider)
    {
        mExecutor = null;
        mTotalCount = 0;
        mCompletedCount = 0;
        mCacheProvider = cacheprovider;
        mExecutor = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory("thread-pool-", 10), new java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public boolean isAlive()
    {
        return mCompletedCount < mTotalCount;
    }

    public void removeAllTask()
    {
        mCompletedCount = 0;
        mTotalCount = 0;
        mExecutor.getQueue().clear();
    }

    public void shutDown()
    {
        removeAllTask();
        mExecutor.shutdownNow();
        do
        {
            boolean flag;
            boolean flag1;
            try
            {
                flag = mExecutor.awaitTermination(30L, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException interruptedexception)
            {
                return;
            }
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        } while (flag1);
    }

    public void submitTask(DecodeListener decodelistener, MediaItem mediaitem, int i)
    {
        DecodeJob decodejob = new DecodeJob(this, decodelistener, mediaitem, i);
        mExecutor.execute(decodejob);
        mCacheProvider.onTaskBusy();
        mTotalCount = 1 + mTotalCount;
    }




/*
    static int access$208(DecodeTask decodetask)
    {
        int i = decodetask.mCompletedCount;
        decodetask.mCompletedCount = i + 1;
        return i;
    }

*/

}
