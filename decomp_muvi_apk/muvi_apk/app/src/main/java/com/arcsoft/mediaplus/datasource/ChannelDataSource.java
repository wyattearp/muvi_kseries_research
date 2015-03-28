// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import com.arcsoft.mediaplus.datasource.db.ChannelDataMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.DoubleBufferList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource, MediaItem, Property

public class ChannelDataSource extends AbsListDataSource
    implements DataSourceFactory.IProduct
{
    private class ChannelMediaItem extends MediaItem
    {

        int mChannelID;
        String mChannelName;
        com.arcsoft.mediaplus.datasource.db.ChannelDataMgr.ChannelData mData;
        String mParsedChannelNr;
        final ChannelDataSource this$0;

        private ChannelMediaItem()
        {
            this$0 = ChannelDataSource.this;
            super();
        }

    }


    private static final String TAG = "ChannelDataSource";
    protected int mChannelID;
    protected Context mContext;
    private final com.arcsoft.mediaplus.datasource.db.ChannelDataMgr.IOnChannelDataListener mDataListener = new com.arcsoft.mediaplus.datasource.db.ChannelDataMgr.IOnChannelDataListener() {

        final ChannelDataSource this$0;

        public void OnChannelDataMounted(String s)
        {
            OnDBDataMounted(s);
        }

        public void OnChannelDataTransmittedBegin(String s, Set set)
        {
            if (ChannelDataMgr.isChannleSetInteract(set, mChannelID))
            {
                NotifyOnDataBuiltBegin();
            }
        }

        public void OnChannelDataTransmittedFinish(String s, Set set)
        {
            if (ChannelDataMgr.isChannleSetInteract(set, mChannelID))
            {
                NotifyOnDataBuiltFinish();
            }
        }

        public void OnChannelDataUnMounted(String s)
        {
            OnDBDataUnMounted(s);
        }

        public void OnChannelDataUpdated(String s, Set set)
        {
            OnDBDataUpdated(s, set);
        }

        public void OnChannelItemRefresh(String s, int j, long l)
        {
            OnDBItemRefresh(s, j, l);
        }

        public void OnDigaActionFinish(int j, int k)
        {
        }

            
            {
                this$0 = ChannelDataSource.this;
                super();
            }
    };
    protected ChannelDataMgr mDbMgr;
    private final com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener mSettingListner = new com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener() {

        final ChannelDataSource this$0;

        public void OnDefaultDownloadDestinationChanged(String s)
        {
        }

        public void OnDefaultRenderChanged(String s)
        {
        }

        public void OnDefaultServerChanged(String s)
        {
        }

        public void onBrowseModeChanged(boolean flag)
        {
        }

        public void onSortModeChanged(boolean flag)
        {
        }

        public void onTVStreamingResolutionChange(boolean flag)
        {
            mUseHDContent = flag;
        }

            
            {
                this$0 = ChannelDataSource.this;
                super();
            }
    };
    private boolean mUseHDContent;

    public ChannelDataSource(Context context, ChannelDataMgr channeldatamgr, int i)
    {
        mDbMgr = null;
        mChannelID = -1;
        mContext = null;
        mUseHDContent = true;
        mChannelID = i;
        mDbMgr = channeldatamgr;
        mContext = context;
        mUseHDContent = Settings.instance().isUseHDResource();
    }

    private MediaItem createMediaItem(com.arcsoft.mediaplus.datasource.db.ChannelDataMgr.ChannelData channeldata)
    {
        if (channeldata == null || channeldata.itemDesc == null)
        {
            return null;
        } else
        {
            ChannelMediaItem channelmediaitem = new ChannelMediaItem();
            channelmediaitem._id = channeldata._id;
            channelmediaitem.title = channeldata.itemDesc.m_strTitle;
            channelmediaitem.isDir = false;
            channelmediaitem.mData = channeldata;
            channelmediaitem.uri = channelmediaitem.mData.hdRes;
            channelmediaitem.mChannelID = channeldata.channelid;
            return channelmediaitem;
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

    protected void OnDBDataUpdated(String s, Set set)
    {
        if (ChannelDataMgr.isChannleSetInteract(set, mChannelID))
        {
            refreshList();
        }
    }

    protected void OnDBItemRefresh(String s, int i, long l)
    {
        if ((i & mChannelID) == 0)
        {
            return;
        }
        int j = -1;
        int k = 0;
        do
        {
label0:
            {
                if (k < getCount())
                {
                    MediaItem mediaitem = getItem(k);
                    if (mediaitem == null || mediaitem._id != l)
                    {
                        break label0;
                    }
                    j = k;
                }
                notifyOnDataChanged(j, Property.PROP_TITLE);
                return;
            }
            k++;
        } while (true);
    }

    public void create()
    {
        super.init();
    }

    public void destory()
    {
        super.unInit();
    }

    public Object getObjectProp(int i, Property property, Object obj)
    {
        ChannelMediaItem channelmediaitem = (ChannelMediaItem)mList.get(i);
        if (property.equals(Property.PROP_CHANNELNR))
        {
            if (channelmediaitem.mParsedChannelNr == null)
            {
                StringBuilder stringbuilder = new StringBuilder("");
                int j = (channelmediaitem.mData.itemDesc.m_PresentItem.m_lChannelNr % 10000) / 10;
                stringbuilder.append(j);
                if (j < 100)
                {
                    stringbuilder.insert(0, '0');
                }
                if (j < 10)
                {
                    stringbuilder.insert(0, '0');
                }
                channelmediaitem.mParsedChannelNr = stringbuilder.toString();
            }
            return channelmediaitem.mParsedChannelNr;
        }
        if (property.equals(Property.PROP_URI))
        {
            if (!OEMConfig.DEVICE_SUPPORT_MPEG2)
            {
                Log.d("ChannelDataSource", "can not support mpeg2, just return sd res");
                return channelmediaitem.mData.sdRes;
            }
            if (mUseHDContent)
            {
                if (channelmediaitem.mData.hdRes != null)
                {
                    return channelmediaitem.mData.hdRes;
                } else
                {
                    return channelmediaitem.mData.sdRes;
                }
            }
            if (channelmediaitem.mData.sdRes != null)
            {
                return channelmediaitem.mData.sdRes;
            } else
            {
                return channelmediaitem.mData.hdRes;
            }
        }
        if (property.equals(Property.PROP_CHANNELID))
        {
            return Integer.valueOf(channelmediaitem.mChannelID);
        }
        if (property.equals(Property.PROP_CHANNELNAME))
        {
            channelmediaitem.mChannelName = channelmediaitem.mData.itemDesc.m_PresentItem.m_strChannelName;
            return channelmediaitem.mChannelName;
        }
        if (property.equals(Property.PROP_RESUMEPOINT))
        {
            return Integer.valueOf(0);
        } else
        {
            return super.getObjectProp(i, property, obj);
        }
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        return ((ChannelMediaItem)mList.get(i)).mData.itemDesc;
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
            ArrayList arraylist = mDbMgr.queryChannelDatas(mChannelID);
            if (arraylist != null)
            {
                do
                {
                    if (arraylist.size() <= 0 || options.mIsCanceled)
                    {
                        break;
                    }
                    MediaItem mediaitem = createMediaItem((com.arcsoft.mediaplus.datasource.db.ChannelDataMgr.ChannelData)arraylist.remove(0));
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

    protected void onDisable()
    {
        super.onDisable();
    }

    protected void onEnable()
    {
        super.onEnable();
    }

    protected void onInit()
    {
        mDbMgr.registerOnDataUpdateListener(mDataListener);
        Settings.instance().registerSettingChangeListener(mSettingListner);
        super.onInit();
    }

    protected void onPause()
    {
        super.onPause();
    }

    protected void onResume()
    {
        super.onResume();
    }

    protected void onUninit()
    {
        Settings.instance().unregisterSettingChangeListener(mSettingListner);
        mDbMgr.unregisterOnDataUpdateListener(mDataListener);
        super.onUninit();
    }

    protected void refreshList()
    {
        mList.abortBuilding();
        mList.invalide();
    }


/*
    static boolean access$002(ChannelDataSource channeldatasource, boolean flag)
    {
        channeldatasource.mUseHDContent = flag;
        return flag;
    }

*/
}
