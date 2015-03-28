// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.content.res.Resources;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.ViewManager;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

class this._cls0
    implements com.arcsoft.mediaplus.datasource.nDataChangeListener
{

    final ListViewGL this$0;

    public void onCountChanged(int i, int j)
    {
        Log.v("ThumbGlView", (new StringBuilder()).append("onCountChanged: oldCount ").append(j).append(" newCount ").append(i).toString());
        onListCountChanged(i, j);
        if (mContext == null)
        {
            return;
        }
        if (mDataSource != null)
        {
            i = mDataSource.getCount();
        }
        if (i > 0 && i != j && ViewManager.mCurrentViewStatus == 2)
        {
            ((MediaPlusActivity)mContext).refreshSelectorTitle(ListViewManager.getSelectedItemsNum());
        }
        Log.v("zdf", (new StringBuilder()).append("######## [ListViewGL] onCountChanged, oldCount = ").append(j).append(", newCount = ").append(i).toString());
        ((MediaPlusActivity)mContext).showHideViews();
        boolean flag;
        boolean flag1;
        if (j == 0 && i > 0)
        {
            sendMsgToUpdateTextStatus("", false, 0);
            ((MediaPlusActivity)mContext).refreshControlBar(0);
        } else
        if (i == 0 && j >= 0)
        {
            if (!isRemote())
            {
                sendMsgToUpdateTextStatus(mContext.getResources().getString(0x7f0b001b), true, 0);
            }
            ((MediaPlusActivity)mContext).refreshControlBar(8);
        }
        flag = ListViewGL.access$000(ListViewGL.this);
        flag1 = false;
        if (flag)
        {
            if (j == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        }
        refresh(flag1);
        setFocusable(true);
        requestFocus();
    }

    public void onDataChanged(int i, Property property)
    {
        Log.d("ThumbGlView", (new StringBuilder()).append("onDataChanged : index = ").append(i).append(", property = ").append(property).toString());
        if (Property.PROP_THUMBNAIL_FILEPATH == property)
        {
            onDecodeThumbnail(i);
        } else
        if (Property.PROP_STORAGE_FULL == property && !ListViewGL.access$100(ListViewGL.this) && isRemote())
        {
            if (mContext != null)
            {
                sendMsgToShowToast(mContext.getString(0x7f0b0198), 0);
            }
            ListViewGL.access$102(ListViewGL.this, true);
            return;
        }
    }

    urce()
    {
        this$0 = ListViewGL.this;
        super();
    }
}
