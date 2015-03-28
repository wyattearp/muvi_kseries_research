// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            MediaItem, Property

public interface IDataSource
{
    public static interface IController
    {

        public abstract void loadMore(int i);

        public abstract void pause();

        public transient abstract void prefetch(int i, int j, Property aproperty[]);

        public transient abstract void prefetchEx(int ai[], Property aproperty[]);

        public abstract void refresh();

        public abstract void release();

        public abstract void resume();

        public abstract void setEnable(boolean flag);

        public abstract void setResourceType(boolean flag);

        public abstract void sort(Property property, boolean flag);
    }

    public static interface OnDataBuildListener
    {

        public abstract void onDataBuiltBegin();

        public abstract void onDataBuiltFinish();
    }

    public static interface OnDataChangeListener
    {

        public abstract void onCountChanged(int i, int j);

        public abstract void onDataChanged(int i, Property property);
    }


    public static final int COUNT_UNKNOWN = -1;
    public static final int LOAD_ALL = -1;
    public static final String PREPARING_PATH = "?";

    public abstract boolean delete(int i);

    public abstract boolean delete(MediaItem mediaitem);

    public abstract boolean getBooleanProp(int i, Property property, boolean flag);

    public abstract byte getByteProp(int i, Property property, byte byte0);

    public abstract IController getController();

    public abstract int getCount();

    public abstract int getIntProp(int i, Property property, int j);

    public abstract MediaItem getItem(int i);

    public abstract long getLongProp(int i, Property property, long l);

    public abstract Object getObjectProp(int i, Property property, Object obj);

    public abstract com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i);

    public abstract ArrayList getRemoteItemResourceDesc(int i);

    public abstract String getRemoteItemUUID(int i);

    public abstract Property[] getSortCapability();

    public abstract Property getSortProperty();

    public abstract String getStringProp(int i, Property property, String s);

    public abstract Property[] getSupportedProperties();

    public abstract int getTotalCount();

    public abstract boolean hasMore();

    public abstract boolean isEnable();

    public abstract boolean isReady();

    public abstract boolean isResume();

    public abstract com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i);

    public abstract void registerOnDataBuildListener(OnDataBuildListener ondatabuildlistener);

    public abstract void registerOnDataChangeListener(OnDataChangeListener ondatachangelistener);

    public abstract void unregisterOnDataBuildListener(OnDataBuildListener ondatabuildlistener);

    public abstract void unregisterOnDataChangeListener(OnDataChangeListener ondatachangelistener);
}
