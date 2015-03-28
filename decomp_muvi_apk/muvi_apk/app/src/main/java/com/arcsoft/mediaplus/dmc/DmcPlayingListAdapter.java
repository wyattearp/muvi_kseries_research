// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcCacheManager, DmcPlayingDataProvider

public class DmcPlayingListAdapter extends BaseAdapter
{
    public class ViewHolder
    {

        public TextView date;
        public ImageView icon;
        final DmcPlayingListAdapter this$0;
        public TextView title;

        public ViewHolder()
        {
            this$0 = DmcPlayingListAdapter.this;
            super();
        }
    }


    protected final String TAG = "Dmc";
    private final String TYPE_IMAGE = "image";
    private final String TYPE_VIDEO = "video";
    private DmcCacheManager mCacheManager;
    private DmcPlayingDataProvider mDataProvider;
    private final LayoutInflater mInflater;

    public DmcPlayingListAdapter(Context context, DmcCacheManager dmccachemanager, DmcPlayingDataProvider dmcplayingdataprovider)
    {
        mDataProvider = null;
        mCacheManager = null;
        mInflater = LayoutInflater.from(context);
        mDataProvider = dmcplayingdataprovider;
        mCacheManager = dmccachemanager;
    }

    private Bitmap getCoverByIndex(int i)
    {
        return mCacheManager.getThumbnail(i);
    }

    public int getCount()
    {
        if (mDataProvider == null)
        {
            return 0;
        } else
        {
            return mDataProvider.getCount();
        }
    }

    public Object getItem(int i)
    {
        if (mDataProvider == null)
        {
            return null;
        } else
        {
            return Long.valueOf(mDataProvider.getId(i));
        }
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        if (view == null)
        {
            view = mInflater.inflate(0x7f030011, null);
            viewholder = new ViewHolder();
            viewholder.title = (TextView)view.findViewById(0x7f090062);
            viewholder.icon = (ImageView)view.findViewById(0x7f090061);
            viewholder.date = (TextView)view.findViewById(0x7f090063);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        if (mDataProvider != null)
        {
            viewholder.title.setText(mDataProvider.getMediaName(i));
            String s = mDataProvider.getTime(i);
            if (s != null && s.endsWith("\n#xA;"))
            {
                s = s.substring(0, s.length() - "\n#xA;".length());
            }
            if (mDataProvider.isVideoFile(i))
            {
                s = (new StringBuilder()).append(s).append(" / ").append(mDataProvider.getDuration(i)).toString();
            }
            viewholder.date.setText(s);
            viewholder.icon.setImageBitmap(getCoverByIndex(i));
        }
        return view;
    }

    public void refreshCover(View view, int i)
    {
        ViewHolder viewholder;
        if (view != null)
        {
            if ((viewholder = (ViewHolder)view.getTag()) != null)
            {
                viewholder.icon.setImageBitmap(getCoverByIndex(i));
                return;
            }
        }
    }
}
