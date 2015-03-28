// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsDataSource, Property, IDataSource, MediaItem

public class MatrixDataSource extends AbsDataSource
{
    private class DataSourceInfo
    {

        IDataSource.IController controller;
        IDataSource dataSource;
        final MatrixDataSource this$0;

        private DataSourceInfo()
        {
            this$0 = MatrixDataSource.this;
            super();
        }

    }

    private class IndexInfo
    {

        int datasourceIndex;
        int mediaIndex;
        final MatrixDataSource this$0;

        private IndexInfo()
        {
            this$0 = MatrixDataSource.this;
            super();
        }

    }


    protected ArrayList mDataSources;
    private IDataSource.OnDataBuildListener mInternalDataBuildListener;
    private IDataSource.OnDataChangeListener mInternalDataChangeListener;
    Property mSortProperty;
    private final IndexInfo mTmpIndexInfo = new IndexInfo();

    public transient MatrixDataSource(IDataSource aidatasource[])
    {
        mDataSources = new ArrayList();
        mSortProperty = Property.PROP_TITLE;
        int i = aidatasource.length;
        for (int j = 0; j < i; j++)
        {
            IDataSource idatasource = aidatasource[j];
            if (idatasource != null)
            {
                DataSourceInfo datasourceinfo = new DataSourceInfo();
                datasourceinfo.dataSource = idatasource;
                mDataSources.add(datasourceinfo);
            }
        }

    }

    private boolean getIndexInfo(int i, IndexInfo indexinfo)
    {
        int j = 0;
        for (int k = 0; k < mDataSources.size(); k++)
        {
            DataSourceInfo datasourceinfo = (DataSourceInfo)mDataSources.get(k);
            if (i < j + datasourceinfo.dataSource.getCount())
            {
                indexinfo.datasourceIndex = k;
                indexinfo.mediaIndex = i - j;
                return true;
            }
            j += datasourceinfo.dataSource.getCount();
        }

        return false;
    }

    protected IDataSource.IController createController(int i)
    {
        return super.createController(i);
    }

    public boolean delete(int i)
    {
        return super.delete(i);
    }

    public boolean delete(MediaItem mediaitem)
    {
        return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.delete(mediaitem);
    }

    public boolean getBooleanProp(int i, Property property, boolean flag)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return flag;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getBooleanProp(mTmpIndexInfo.mediaIndex, property, flag);
        }
    }

    public byte getByteProp(int i, Property property, byte byte0)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return byte0;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getByteProp(mTmpIndexInfo.mediaIndex, property, byte0);
        }
    }

    public int getCount()
    {
        int i = 0;
        for (Iterator iterator = mDataSources.iterator(); iterator.hasNext();)
        {
            i += ((DataSourceInfo)iterator.next()).dataSource.getCount();
        }

        return i;
    }

    public IDataSource[] getDataSources()
    {
        int i = mDataSources.size();
        IDataSource aidatasource[] = null;
        if (i > 0)
        {
            aidatasource = new IDataSource[mDataSources.size()];
            for (int j = 0; j < aidatasource.length; j++)
            {
                aidatasource[j] = ((DataSourceInfo)mDataSources.get(j)).dataSource;
            }

        }
        return aidatasource;
    }

    public int getIntProp(int i, Property property, int j)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return j;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getIntProp(mTmpIndexInfo.mediaIndex, property, j);
        }
    }

    public MediaItem getItem(int i)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getItem(mTmpIndexInfo.mediaIndex);
        }
    }

    public long getLongProp(int i, Property property, long l)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return l;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getLongProp(mTmpIndexInfo.mediaIndex, property, l);
        }
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getObjectProp(mTmpIndexInfo.mediaIndex, property, obj);
        }
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getRemoteItemDesc(mTmpIndexInfo.mediaIndex);
        }
    }

    public ArrayList getRemoteItemResourceDesc(int i)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getRemoteItemResourceDesc(mTmpIndexInfo.mediaIndex);
        }
    }

    public String getRemoteItemUUID(int i)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getRemoteItemUUID(mTmpIndexInfo.mediaIndex);
        }
    }

    public Property[] getSortCapability()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Property aproperty1[] = ((DataSourceInfo)iterator.next()).dataSource.getSortCapability();
            if (aproperty1 != null)
            {
                int i = aproperty1.length;
                int j = 0;
                while (j < i) 
                {
                    Property property = aproperty1[j];
                    if (!arraylist.contains(property))
                    {
                        arraylist.add(property);
                    }
                    j++;
                }
            }
        } while (true);
        if (arraylist.size() < 0)
        {
            return new Property[0];
        } else
        {
            Property aproperty[] = new Property[arraylist.size()];
            arraylist.toArray(aproperty);
            return aproperty;
        }
    }

    public Property getSortProperty()
    {
        return mSortProperty;
    }

    public String getStringProp(int i, Property property, String s)
    {
        if (!getIndexInfo(i, mTmpIndexInfo))
        {
            return null;
        } else
        {
            return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.getStringProp(mTmpIndexInfo.mediaIndex, property, s);
        }
    }

    public Property[] getSupportedProperties()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Property aproperty1[] = ((DataSourceInfo)iterator.next()).dataSource.getSupportedProperties();
            if (aproperty1 != null)
            {
                int i = aproperty1.length;
                int j = 0;
                while (j < i) 
                {
                    Property property = aproperty1[j];
                    if (!arraylist.contains(property))
                    {
                        arraylist.add(property);
                    }
                    j++;
                }
            }
        } while (true);
        if (arraylist.size() < 0)
        {
            return new Property[0];
        } else
        {
            Property aproperty[] = new Property[arraylist.size()];
            arraylist.toArray(aproperty);
            return aproperty;
        }
    }

    public int getTotalCount()
    {
        int i = 0;
        for (Iterator iterator = mDataSources.iterator(); iterator.hasNext();)
        {
            i += ((DataSourceInfo)iterator.next()).dataSource.getTotalCount();
        }

        return i;
    }

    public boolean hasMore()
    {
        Iterator iterator = mDataSources.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            if (!((DataSourceInfo)iterator.next()).dataSource.hasMore())
            {
                continue;
            }
            flag1 = true;
            break;
        } while (true);
        return flag1;
    }

    public void init()
    {
        super.init();
    }

    protected void loadMore(int i)
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo != null && datasourceinfo.controller != null)
            {
                datasourceinfo.controller.loadMore(i);
            }
        } while (true);
    }

    public com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i)
    {
        return ((DataSourceInfo)mDataSources.get(mTmpIndexInfo.datasourceIndex)).dataSource.makeDownloadTask(i);
    }

    protected IDataSource.IController onCreateController(int i)
    {
        for (Iterator iterator = mDataSources.iterator(); iterator.hasNext();)
        {
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            datasourceinfo.controller = datasourceinfo.dataSource.getController();
        }

        return super.onCreateController(i);
    }

    protected void onDisable()
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo != null && datasourceinfo.controller != null && datasourceinfo.dataSource != null)
            {
                datasourceinfo.dataSource.unregisterOnDataChangeListener(mInternalDataChangeListener);
                datasourceinfo.dataSource.unregisterOnDataBuildListener(mInternalDataBuildListener);
                datasourceinfo.controller.setEnable(false);
            }
        } while (true);
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo != null && datasourceinfo.controller != null && datasourceinfo.dataSource != null)
            {
                datasourceinfo.controller.setEnable(true);
                datasourceinfo.dataSource.registerOnDataChangeListener(mInternalDataChangeListener);
                datasourceinfo.dataSource.registerOnDataBuildListener(mInternalDataBuildListener);
            }
        } while (true);
    }

    protected void onInit()
    {
        super.onInit();
        mInternalDataChangeListener = new IDataSource.OnDataChangeListener() {

            final MatrixDataSource this$0;

            public void onCountChanged(int i, int j)
            {
                notifyOnCountChanged(getCount(), j);
            }

            public void onDataChanged(int i, Property property)
            {
                notifyOnDataChanged(i, property);
            }

            
            {
                this$0 = MatrixDataSource.this;
                super();
            }
        };
        mInternalDataBuildListener = new IDataSource.OnDataBuildListener() {

            final MatrixDataSource this$0;

            public void onDataBuiltBegin()
            {
                NotifyOnDataBuiltBegin();
            }

            public void onDataBuiltFinish()
            {
                NotifyOnDataBuiltFinish();
            }

            
            {
                this$0 = MatrixDataSource.this;
                super();
            }
        };
    }

    protected void onPause()
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.pause();
            }
        } while (true);
        super.onPause();
    }

    protected void onReleaseController()
    {
        super.onReleaseController();
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.release();
                datasourceinfo.controller = null;
            }
        } while (true);
    }

    protected void onResume()
    {
        super.onResume();
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.resume();
            }
        } while (true);
    }

    protected void onUninit()
    {
        mInternalDataChangeListener = null;
        mInternalDataBuildListener = null;
        super.onUninit();
    }

    protected transient void prefetch(int i, int j, Property aproperty[])
    {
        int k = 0;
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                int l = Math.max(0, i - k);
                int i1 = Math.min(-1 + datasourceinfo.dataSource.getCount(), j - k);
                k = datasourceinfo.dataSource.getCount();
                datasourceinfo.controller.prefetch(l, i1, aproperty);
            }
        } while (true);
    }

    protected transient void prefetchEx(int ai[], Property aproperty[])
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.prefetchEx(ai, aproperty);
            }
        } while (true);
    }

    protected void refresh()
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.refresh();
            }
        } while (true);
    }

    protected void setResourceType(boolean flag)
    {
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo.controller != null)
            {
                datasourceinfo.controller.setResourceType(flag);
            }
        } while (true);
    }

    protected void sort(Property property, boolean flag)
    {
        mSortProperty = property;
        Iterator iterator = mDataSources.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            DataSourceInfo datasourceinfo = (DataSourceInfo)iterator.next();
            if (datasourceinfo != null && datasourceinfo.controller != null)
            {
                datasourceinfo.controller.sort(property, flag);
            }
        } while (true);
    }

    public void unInit()
    {
        super.unInit();
    }
}
