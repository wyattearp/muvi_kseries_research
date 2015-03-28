// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.listview;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

public interface IItemListView
{
    public static interface IListOpListener
    {

        public abstract void OnItemClick(int i, long l);

        public abstract void OnItemLongPress(int i);
    }

    public static class ListImageTagInfo
    {

        Bitmap bitmap;
        int index;

        public ListImageTagInfo()
        {
            index = -1;
            bitmap = null;
        }
    }


    public static final int BUILD_STATE_BEGIN = 0;
    public static final int BUILD_STATE_FINISH = 1;
    public static final String DEFAULT_DURATION = "";
    public static final int MAX_LISTITEM_COUNT = 65535;
    public static final int MORE_LIMIT_COUNT = 30;
    public static final int MSG_SHOW_TOAST = 1543;
    public static final int MSG_UPDATE_STATUS_TEXT = 1541;
    public static final int MSG_UPDOWNLOAD_ERROR = 1542;
    public static final int MSG_UPDOWNLOAD_EXIST = 1540;
    public static final int MSG_UPDOWNLOAD_FINISH = 1539;
    public static final int MSG_UPDOWNLOAD_PROGRESS = 1538;
    public static final int MSG_UPDOWNLOAD_START = 1537;

    public abstract boolean addListView();

    public abstract void addUpdownload(boolean flag, int i);

    public abstract void cancelAllUpdownload();

    public abstract void cancelUpdownload(boolean flag, int i);

    public abstract void doRotate(Configuration configuration);

    public abstract IDataSource getDataSource();

    public abstract int getUpdownloadState(boolean flag, int i);

    public abstract void init(ViewGroup viewgroup, boolean flag);

    public abstract boolean isNeedConfirm(int i);

    public abstract boolean isOpenGl();

    public abstract void onAlphabet(String s, int i);

    public abstract void onPause();

    public abstract void onResume();

    public abstract boolean removeListView();

    public abstract void setDataSource(IDataSource idatasource);

    public abstract void setItemVisibleInScreen(int i);

    public abstract void setListItemOpListener(IListOpListener ilistoplistener);

    public abstract void setUpDownloadContext(UpDownloadEngine updownloadengine);

    public abstract void sort(Property property, boolean flag);

    public abstract void switchUpdownloadMode(boolean flag);

    public abstract void uninit();
}
