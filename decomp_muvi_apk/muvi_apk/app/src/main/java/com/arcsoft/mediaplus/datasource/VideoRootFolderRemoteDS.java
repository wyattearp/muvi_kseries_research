// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource, MediaItem

public class VideoRootFolderRemoteDS extends AbsListDataSource
    implements DataSourceFactory.IProduct
{
    private class RootFolderMediaItem extends MediaItem
    {

        com.arcsoft.adk.atv.UPnP.RemoteItemDesc mData;
        final VideoRootFolderRemoteDS this$0;

        private RootFolderMediaItem()
        {
            this$0 = VideoRootFolderRemoteDS.this;
            super();
        }

    }


    private static String PROJECTIONS[] = {
        "_ID", "TITLE"
    };
    protected Context mContext;
    private final com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener mDBListener = new com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener() {

        final VideoRootFolderRemoteDS this$0;

        public void OnDBDataMounted(String s)
        {
            VideoRootFolderRemoteDS.this.OnDBDataMounted(s);
        }

        public void OnDBDataTransmittedBegin(String s)
        {
            NotifyOnDataBuiltBegin();
        }

        public void OnDBDataTransmittedFinish(String s)
        {
            NotifyOnDataBuiltFinish();
        }

        public void OnDBDataUnMounted(String s)
        {
            VideoRootFolderRemoteDS.this.OnDBDataUnMounted(s);
        }

        public void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
        {
            if (updateinfo.videorootfolderadded)
            {
                VideoRootFolderRemoteDS.this.OnDBDataUpdated(s, updateinfo);
            }
        }

            
            {
                this$0 = VideoRootFolderRemoteDS.this;
                super();
            }
    };
    protected RemoteDBMgr mDbMgr;

    VideoRootFolderRemoteDS(Context context, RemoteDBMgr remotedbmgr)
    {
        mDbMgr = null;
        mContext = null;
        mDbMgr = remotedbmgr;
        mContext = context;
    }

    private MediaItem createMediaItem(com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.RootFolderData rootfolderdata)
    {
        if (rootfolderdata == null)
        {
            return null;
        } else
        {
            RootFolderMediaItem rootfoldermediaitem = new RootFolderMediaItem();
            rootfoldermediaitem._id = rootfolderdata._id;
            rootfoldermediaitem.title = rootfolderdata.desc.m_strTitle;
            rootfoldermediaitem.isDir = true;
            rootfoldermediaitem.mData = rootfolderdata.desc;
            return rootfoldermediaitem;
        }
    }

    protected void OnDBDataMounted(String s)
    {
        refreshList();
    }

    protected void OnDBDataUnMounted(String s)
    {
        mList.clear();
        refreshList();
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        refreshList();
    }

    public void create()
    {
        super.init();
    }

    public void destory()
    {
        super.unInit();
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        return ((RootFolderMediaItem)mList.get(i)).mData;
    }

    protected String getServerUDN()
    {
        return mDbMgr.getCurrentServer();
    }

    protected boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        boolean flag = true;
        if (options.mIsCanceled)
        {
            flag = false;
        } else
        {
            ArrayList arraylist = mDbMgr.queryRootFolderDatas();
            if (arraylist != null)
            {
                do
                {
                    if (arraylist.size() <= 0 || options.mIsCanceled)
                    {
                        break;
                    }
                    MediaItem mediaitem = createMediaItem((com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.RootFolderData)arraylist.remove(0));
                    if (mediaitem != null)
                    {
                        list.add(mediaitem);
                    }
                } while (true);
                if (options.mIsCanceled)
                {
                    return false;
                }
            }
        }
        return flag;
    }

    protected void onDestoryList(List list)
    {
        list.clear();
    }

    protected void onInit()
    {
        mDbMgr.registerOnDataUpdateListener(mDBListener);
        super.onInit();
    }

    protected void onUninit()
    {
        mDbMgr.unregisterOnDataUpdateListener(mDBListener);
        super.onUninit();
    }

    protected void refreshList()
    {
        mList.abortBuilding();
        mList.invalide();
    }

}
