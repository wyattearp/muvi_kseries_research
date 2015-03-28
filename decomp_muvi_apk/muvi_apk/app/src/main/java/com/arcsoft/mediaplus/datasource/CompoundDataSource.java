// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.net.Uri;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsDataSource, MediaItem, IDataSource, Property

public class CompoundDataSource extends AbsDataSource
{

    protected final IDataSource mDataSource;
    protected IDataSource.IController mDataSourceController;
    protected Vector mOutSideItems;
    protected final Object mSyncOutSideItems = new Object();

    public CompoundDataSource(IDataSource idatasource)
    {
        mOutSideItems = null;
        mDataSource = idatasource;
    }

    void compareDataSourceAndOutSideItems()
    {
label0:
        {
            synchronized (mSyncOutSideItems)
            {
                if (mOutSideItems != null && mDataSource != null)
                {
                    break label0;
                }
            }
            return;
        }
        Vector vector = getDownloadSuccessItems();
        if (vector == null)
        {
            break MISSING_BLOCK_LABEL_40;
        }
        if (vector.size() != 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Iterator iterator = vector.iterator();
_L8:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        MediaItem mediaitem = (MediaItem)iterator.next();
        if (mediaitem == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i = mDataSource.getCount();
        int j = 0;
_L6:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        MediaItem mediaitem1 = mDataSource.getItem(j);
        if (mediaitem1 != null) goto _L4; else goto _L3
_L4:
        if (mediaitem.title.startsWith(mediaitem1.title) || mediaitem1.title.startsWith(mediaitem.title))
        {
            mOutSideItems.remove(mediaitem);
        }
          goto _L3
_L2:
        vector.clear();
        obj;
        JVM INSTR monitorexit ;
        return;
_L3:
        j++;
        if (true) goto _L6; else goto _L5
_L5:
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected IDataSource.IController createController(int i)
    {
        return super.createController(i);
    }

    public boolean delete(int i)
    {
        if (mDataSource != null)
        {
            return mDataSource.delete(i);
        } else
        {
            return super.delete(i);
        }
    }

    public boolean delete(MediaItem mediaitem)
    {
        if (mDataSource != null)
        {
            return mDataSource.delete(mediaitem);
        } else
        {
            return super.delete(mediaitem);
        }
    }

    public int getCount()
    {
label0:
        {
            int j;
            synchronized (mSyncOutSideItems)
            {
                if (mOutSideItems == null)
                {
                    break label0;
                }
                j = mDataSource.getCount() + mOutSideItems.size();
            }
            return j;
        }
        int i = mDataSource.getCount();
        obj;
        JVM INSTR monitorexit ;
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public IDataSource getDataSource()
    {
        return mDataSource;
    }

    Vector getDownloadSuccessItems()
    {
label0:
        {
            synchronized (mSyncOutSideItems)
            {
                if (mOutSideItems != null)
                {
                    break label0;
                }
            }
            return null;
        }
        int i = mOutSideItems.size();
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_39;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Vector vector;
        int j;
        vector = null;
        j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_110;
        }
        MediaItem mediaitem = (MediaItem)mOutSideItems.get(j);
        if (mediaitem == null)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        if (3L != mediaitem.status)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        if (vector != null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        vector = new Vector();
        vector.add(mediaitem);
        break MISSING_BLOCK_LABEL_115;
        obj;
        JVM INSTR monitorexit ;
        return vector;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public MediaItem getItem(int i)
    {
        Object obj = mSyncOutSideItems;
        obj;
        JVM INSTR monitorenter ;
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_15;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        MediaItem mediaitem2;
        if (mOutSideItems == null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        if (i >= mOutSideItems.size())
        {
            break MISSING_BLOCK_LABEL_56;
        }
        mediaitem2 = (MediaItem)mOutSideItems.get(i);
        obj;
        JVM INSTR monitorexit ;
        return mediaitem2;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        MediaItem mediaitem1;
        if (mDataSource == null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        mediaitem1 = mDataSource.getItem(i - mOutSideItems.size());
        obj;
        JVM INSTR monitorexit ;
        return mediaitem1;
        if (mDataSource != null)
        {
            break MISSING_BLOCK_LABEL_99;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        MediaItem mediaitem = mDataSource.getItem(i);
        obj;
        JVM INSTR monitorexit ;
        return mediaitem;
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        if (mOutSideItems != null)
        {
            Log.e("test", "CompoundDataSource getObjectProp error please use getItem instead of this");
        }
        return mDataSource.getObjectProp(i, property, obj);
    }

    public int getOutSideDataCount()
    {
label0:
        {
            synchronized (mSyncOutSideItems)
            {
                if (mOutSideItems != null)
                {
                    break label0;
                }
            }
            return 0;
        }
        int i = mOutSideItems.size();
        obj;
        JVM INSTR monitorexit ;
        return i;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        return mDataSource.getRemoteItemDesc(i);
    }

    public ArrayList getRemoteItemResourceDesc(int i)
    {
        return mDataSource.getRemoteItemResourceDesc(i);
    }

    public String getRemoteItemUUID(int i)
    {
        return mDataSource.getRemoteItemUUID(i);
    }

    public Property[] getSortCapability()
    {
        return mDataSource.getSortCapability();
    }

    public Property getSortProperty()
    {
        return mDataSource.getSortProperty();
    }

    public Property[] getSupportedProperties()
    {
        return mDataSource.getSupportedProperties();
    }

    public int getTotalCount()
    {
        return mDataSource.getTotalCount();
    }

    public boolean hasMore()
    {
        return mDataSource.hasMore();
    }

    public void init()
    {
        super.init();
    }

    protected void loadMore(int i)
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.loadMore(i);
        }
    }

    public com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.makeDownloadTask(i);
        }
    }

    protected IDataSource.IController onCreateController(int i)
    {
        mDataSourceController = mDataSource.getController();
        return super.onCreateController(i);
    }

    protected void onDisable()
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.setEnable(false);
        }
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
        if (mDataSourceController != null)
        {
            mDataSourceController.setEnable(true);
        }
    }

    protected void onPause()
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.pause();
        }
        super.onPause();
    }

    protected void onReleaseController()
    {
        super.onReleaseController();
        if (mDataSourceController != null)
        {
            mDataSourceController.release();
            mDataSourceController = null;
        }
    }

    protected void onResume()
    {
        super.onResume();
        if (mDataSourceController != null)
        {
            mDataSourceController.resume();
        }
    }

    protected transient void prefetch(int i, int j, Property aproperty[])
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.prefetch(i, j, aproperty);
        }
    }

    protected transient void prefetchEx(int ai[], Property aproperty[])
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.prefetchEx(ai, aproperty);
        }
    }

    protected void refresh()
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.refresh();
        }
    }

    public boolean setOutSideItems(Vector vector)
    {
        synchronized (mSyncOutSideItems)
        {
            if (mOutSideItems != null)
            {
                mOutSideItems.clear();
                mOutSideItems = null;
            }
            mOutSideItems = vector;
        }
        return true;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setResourceType(boolean flag)
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.setResourceType(flag);
        }
    }

    protected void sort(Property property, boolean flag)
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.sort(property, flag);
        }
    }

    public void unInit()
    {
        super.unInit();
    }

    public void updateOutSideItemStatus(String s, long l)
    {
        Object obj = mSyncOutSideItems;
        obj;
        JVM INSTR monitorenter ;
        if (mOutSideItems == null || mOutSideItems.size() <= 0) goto _L2; else goto _L1
_L1:
        int i = -1 + mOutSideItems.size();
_L8:
        if (i < 0) goto _L2; else goto _L3
_L3:
        MediaItem mediaitem = (MediaItem)mOutSideItems.get(i);
        if (mediaitem == null) goto _L5; else goto _L4
_L4:
        if (mediaitem.uri == null || !mediaitem.uri.toString().equals(s)) goto _L5; else goto _L6
_L6:
        mediaitem.status = l;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L5:
        i--;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void updateOutSideItems(Vector vector, boolean flag)
    {
        Object obj = mSyncOutSideItems;
        obj;
        JVM INSTR monitorenter ;
        if (vector != null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        vector = new Vector();
        if (mOutSideItems != null) goto _L2; else goto _L1
_L1:
        mOutSideItems = vector;
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        int i;
        if (i >= mOutSideItems.size())
        {
            break MISSING_BLOCK_LABEL_108;
        }
        if (((MediaItem)mOutSideItems.get(i)).status != 3L)
        {
            mOutSideItems.remove(i);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_88;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        vector.add(mOutSideItems.get(i));
        i++;
        continue; /* Loop/switch isn't completed */
        if (mOutSideItems != null)
        {
            mOutSideItems.clear();
            mOutSideItems = null;
        }
        mOutSideItems = vector;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_140;
        }
        compareDataSourceAndOutSideItems();
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        i = 0;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
