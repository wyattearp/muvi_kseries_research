// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            IDmcDecoder, RenderDevSelector, DmcDecoder, DmcPlayingDataProvider, 
//            DmcRemotePrefetcher, IDmcPlaylistUpdater

public class DmcCacheManager
    implements DmcRemotePrefetcher.IPrefetchDone, IDmcDecoder
{

    private final String TAG = "Dmc";
    ArrayList mCanDecodeRemoteIdxs;
    private Context mContext;
    private DmcPlayingDataProvider mDataProvider;
    private IDataSource mDataSource;
    private DmcDecoder mDecoder;
    private boolean mDestroyed;
    private Bitmap mImageDefault;
    ArrayList mNeedPrefetchIdxs;
    private IDmcPlaylistUpdater mPlaylistUpdater;
    private DmcRemotePrefetcher mPrefetcher;
    protected HashMap mThumbnailCache;
    protected HashMap mTvCoverCache;
    private Bitmap mTvImageDefault;
    private Bitmap mVideoDefault;

    public DmcCacheManager(Context context, DmcPlayingDataProvider dmcplayingdataprovider, IDataSource idatasource)
    {
        mThumbnailCache = new HashMap();
        mTvCoverCache = new HashMap();
        mDecoder = null;
        mPrefetcher = null;
        mDataProvider = null;
        mPlaylistUpdater = null;
        mDataSource = null;
        mContext = null;
        mTvImageDefault = null;
        mImageDefault = null;
        mVideoDefault = null;
        mCanDecodeRemoteIdxs = new ArrayList();
        mNeedPrefetchIdxs = new ArrayList();
        mDestroyed = false;
        mDataProvider = dmcplayingdataprovider;
        mDataSource = idatasource;
        mContext = context;
        init();
    }

    private void bootDecoder()
    {
        if (RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
        {
            mDecoder.decode();
            return;
        }
        int i = 0;
        while (i < mDataProvider.getCount()) 
        {
            if (mDataProvider.getRemoteThumbnaiPath(i) != null)
            {
                mCanDecodeRemoteIdxs.add(Integer.valueOf(i));
            } else
            {
                mNeedPrefetchIdxs.add(Integer.valueOf(i));
            }
            i++;
        }
        mDecoder.decode(mCanDecodeRemoteIdxs);
    }

    private void createDefaultBitmap()
    {
        int ai[] = mContext.getResources().getIntArray(0x7f060005);
        if (ai != null)
        {
            android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
            options.outWidth = ai[0];
            options.outHeight = ai[1];
            mImageDefault = BitmapFactory.decodeResource(mContext.getResources(), 0x7f020293, options);
            mVideoDefault = BitmapFactory.decodeResource(mContext.getResources(), 0x7f020295, options);
        }
        mTvImageDefault = BitmapFactory.decodeResource(mContext.getResources(), 0x7f0200ed);
    }

    private void destroyDefaultBitmap()
    {
        this;
        JVM INSTR monitorenter ;
        if (mImageDefault != null && !mImageDefault.isRecycled())
        {
            Log.d("Dmc", "recycle mImageDefault");
            mImageDefault.recycle();
            mImageDefault = null;
        }
        if (mVideoDefault != null && !mVideoDefault.isRecycled())
        {
            Log.d("Dmc", "recycle mVideoDefault");
            mVideoDefault.recycle();
            mVideoDefault = null;
        }
        if (mTvImageDefault != null && !mTvImageDefault.isRecycled())
        {
            Log.d("Dmc", "recycle mTvImageDefault");
            mTvImageDefault.recycle();
            mTvImageDefault = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void doPrefetch(int i)
    {
        if (mPrefetcher != null && !isPrefetchDone(i))
        {
            mPrefetcher.prefetch(i);
        }
    }

    private void init()
    {
        createPrefetcher();
        createDefaultBitmap();
        mDecoder = new DmcDecoder(mContext, mDataProvider);
        mDecoder.setDecoderListener(this);
        bootDecoder();
    }

    private boolean isEverDecoded(int i)
    {
        if (mCanDecodeRemoteIdxs == null)
        {
            return false;
        } else
        {
            return mCanDecodeRemoteIdxs.contains(Integer.valueOf(i));
        }
    }

    private boolean isPrefetchDone(int i)
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mNeedPrefetchIdxs;
        boolean flag = false;
        if (arraylist != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        boolean flag1 = mNeedPrefetchIdxs.contains(Integer.valueOf(i));
        flag = false;
        if (!flag1)
        {
            flag = true;
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private void removePrefetcheDoneIdx(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (mNeedPrefetchIdxs != null && mNeedPrefetchIdxs.contains(Integer.valueOf(i)))
        {
            mNeedPrefetchIdxs.remove(Integer.valueOf(i));
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void clearCoverCache()
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator;
        if (mTvCoverCache == null)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        Log.d("Dmc", "clearCoverCache begin");
        iterator = mTvCoverCache.entrySet().iterator();
        if (iterator == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
_L2:
        java.util.Map.Entry entry;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_97;
            }
            entry = (java.util.Map.Entry)iterator.next();
        } while (entry == null);
        Bitmap bitmap = (Bitmap)entry.getValue();
        if (bitmap == null) goto _L2; else goto _L1
_L1:
        if (bitmap.isRecycled()) goto _L2; else goto _L3
_L3:
        bitmap.recycle();
          goto _L2
        Exception exception;
        exception;
        throw exception;
        Log.d("Dmc", "clearCoverCache end");
        this;
        JVM INSTR monitorexit ;
    }

    protected void clearThumbnailCache()
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator;
        if (mThumbnailCache == null)
        {
            break MISSING_BLOCK_LABEL_105;
        }
        Log.d("Dmc", "clearThumbnailCache begin");
        iterator = mThumbnailCache.entrySet().iterator();
        if (iterator == null)
        {
            break MISSING_BLOCK_LABEL_97;
        }
_L2:
        java.util.Map.Entry entry;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_97;
            }
            entry = (java.util.Map.Entry)iterator.next();
        } while (entry == null);
        Bitmap bitmap = (Bitmap)entry.getValue();
        if (bitmap == null) goto _L2; else goto _L1
_L1:
        if (bitmap.isRecycled()) goto _L2; else goto _L3
_L3:
        bitmap.recycle();
          goto _L2
        Exception exception;
        exception;
        throw exception;
        Log.d("Dmc", "clearThumbnailCache end");
        this;
        JVM INSTR monitorexit ;
    }

    protected void createPrefetcher()
    {
        if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
        {
            mPrefetcher = new DmcRemotePrefetcher(mDataProvider, mDataSource);
            mPrefetcher.setListener(this);
            mPrefetcher.resume();
        }
    }

    public void decodeFinish(int i, Bitmap bitmap, DmcUtils.COVER_TYPE cover_type)
    {
        Log.d("Dmc", (new StringBuilder()).append("decodeFinish index = ").append(i).append(" bmp = ").append(bitmap).toString());
        save(i, bitmap, cover_type);
        if (mPlaylistUpdater != null)
        {
            mPlaylistUpdater.updateListItem(i, cover_type);
        }
    }

    public void destroy()
    {
        Log.d("Dmc", "DmcCacheManager :\u3000destroy begin");
        mDestroyed = true;
        destroyPrefetcher();
        if (mDecoder != null)
        {
            mDecoder.destroy();
            mDecoder = null;
            Log.d("Dmc", "DmcCacheManager :\u3000destroy mDecoder");
        }
        clearCoverCache();
        clearThumbnailCache();
        destroyDefaultBitmap();
        Log.d("Dmc", "DmcCacheManager :\u3000destroy end");
    }

    protected void destroyPrefetcher()
    {
        if (mPrefetcher != null)
        {
            mPrefetcher.destroy();
            mPrefetcher = null;
        }
    }

    public Bitmap getThumbnail(int i)
    {
        if (mThumbnailCache != null && mThumbnailCache.containsKey(Integer.valueOf(i)))
        {
            return (Bitmap)mThumbnailCache.get(Integer.valueOf(i));
        }
        if (!RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL)
        {
            doPrefetch(i);
        }
        if (mDataProvider == null)
        {
            return mImageDefault;
        }
        if (mDataProvider.isVideoFile(i))
        {
            return mVideoDefault;
        } else
        {
            return mImageDefault;
        }
    }

    public Bitmap getTvCover(int i)
    {
        if (i < 0)
        {
            return mTvImageDefault;
        }
        if (mTvCoverCache != null && mTvCoverCache.containsKey(Integer.valueOf(i)))
        {
            return (Bitmap)mTvCoverCache.get(Integer.valueOf(i));
        }
        if (RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL || mDataProvider == null || mDataProvider.getRemoteThumbnaiPath(i) != null) goto _L2; else goto _L1
_L1:
        doPrefetch(i);
_L4:
        return mTvImageDefault;
_L2:
        if (mDecoder != null)
        {
            mDecoder.decode(i, DmcUtils.COVER_TYPE.TYPE_TV_COVER);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void prefetchDone(int i)
    {
        removePrefetcheDoneIdx(i);
        if (mDecoder != null)
        {
            Log.d("Dmc", (new StringBuilder()).append("prefetchDone listIndex = ").append(i).toString());
            if (!mThumbnailCache.containsKey(Integer.valueOf(i)))
            {
                mDecoder.decode(i, DmcUtils.COVER_TYPE.TYPE_THUMBNAIL);
            }
            if (!mTvCoverCache.containsKey(Integer.valueOf(i)))
            {
                mDecoder.decode(i, DmcUtils.COVER_TYPE.TYPE_TV_COVER);
            }
        }
    }

    public void save(int i, Bitmap bitmap, DmcUtils.COVER_TYPE cover_type)
    {
        Log.d("Dmc", (new StringBuilder()).append("save index = ").append(i).append(" bmp = ").append(bitmap).toString());
        if (!mDestroyed) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (cover_type != DmcUtils.COVER_TYPE.TYPE_THUMBNAIL)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mThumbnailCache == null) goto _L1; else goto _L3
_L3:
        mThumbnailCache.put(Integer.valueOf(i), bitmap);
        return;
        if (mTvCoverCache == null) goto _L1; else goto _L4
_L4:
        mTvCoverCache.put(Integer.valueOf(i), bitmap);
        return;
    }

    public void setPlaylistUpdater(IDmcPlaylistUpdater idmcplaylistupdater)
    {
        mPlaylistUpdater = idmcplaylistupdater;
    }
}
