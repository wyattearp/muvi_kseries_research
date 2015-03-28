// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arcsoft.mediaplus.datasource.PictureDataSource;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaListAdapter, RemoteListView, MediaPlusActivity

public class this._cls0 extends MediaListAdapter
{

    final RemoteListView this$0;

    public int getCount()
    {
        int i;
        int j;
        if (mDataSource != null)
        {
            i = mDataSource.getCount();
        } else
        {
            i = 0;
        }
        if (i > 65535)
        {
            j = 0x10000;
        } else
        {
            j = i;
        }
        if (RemoteListView.access$200(RemoteListView.this))
        {
            if (j == 0)
            {
                if (mEmptyText != null)
                {
                    mEmptyText.setVisibility(0);
                }
                ((MediaPlusActivity)mContext).refreshControlBar(8);
            } else
            if (j > 0)
            {
                if (mEmptyText != null)
                {
                    mEmptyText.setVisibility(8);
                }
                ((MediaPlusActivity)mContext).refreshControlBar(0);
                return j;
            }
        }
        return j;
    }

    public Object getItem(int i)
    {
        return Integer.valueOf(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if (mInflater == null || mDataSource == null)
        {
            return view;
        }
        if (view == null)
        {
            if (isGridView)
            {
                view = mInflater.inflate(0x7f030025, null);
            } else
            {
                view = mInflater.inflate(0x7f030026, null);
            }
        }
        view.setTag(Integer.valueOf(i));
        fillHolder(createHolder(view), i);
        return view;
    }

    public (Context context)
    {
        this$0 = RemoteListView.this;
        super(context);
    }
}
