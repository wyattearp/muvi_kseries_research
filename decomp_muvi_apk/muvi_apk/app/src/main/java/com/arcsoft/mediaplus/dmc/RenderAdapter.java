// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.arcsoft.mediaplus.service.util.IDlnaSettingBinder;
import com.arcsoft.mediaplus.setting.DigitalMediaAdapter;
import com.arcsoft.mediaplus.setting.DigitalMediaItem;
import com.arcsoft.util.debug.Log;

public class RenderAdapter extends DigitalMediaAdapter
{
    public class ViewHolder
    {

        public ImageView icon;
        final RenderAdapter this$0;
        public TextView title;

        public ViewHolder()
        {
            this$0 = RenderAdapter.this;
            super();
        }
    }


    private final boolean FilterLinkPlusVirRender = true;
    private final String LinkPlusVirRender = "arcsoft link+ virtual renderer";

    public RenderAdapter(Context context, int i, IDlnaSettingBinder idlnasettingbinder)
    {
        super(context, i, idlnasettingbinder);
        TAG = "Dmc";
    }

    protected void add(DigitalMediaItem digitalmediaitem)
    {
label0:
        {
            if (digitalmediaitem != null)
            {
                String s = digitalmediaitem.name.toLowerCase();
                if (s == null || !s.startsWith("arcsoft link+ virtual renderer"))
                {
                    break label0;
                }
            }
            return;
        }
        super.add(digitalmediaitem);
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        DigitalMediaItem digitalmediaitem;
        if (view == null)
        {
            view = mInflater.inflate(0x7f03002c, null);
            viewholder = new ViewHolder();
            viewholder.title = (TextView)view.findViewById(0x7f0900d9);
            viewholder.icon = (ImageView)view.findViewById(0x7f0900d8);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        digitalmediaitem = (DigitalMediaItem)getItem(i);
        Log.d(TAG, (new StringBuilder()).append("mBindServiceSuccessful = ").append(mBindServiceSuccessful).toString());
        if (mBindServiceSuccessful && digitalmediaitem.state == 500)
        {
            Bitmap bitmap = getServerIcon(digitalmediaitem.udn);
            if (bitmap != null && !bitmap.isRecycled())
            {
                viewholder.icon.setImageBitmap(bitmap);
            } else
            {
                viewholder.icon.setImageResource(0x7f02021e);
            }
        } else
        {
            viewholder.icon.setImageResource(0x7f02021e);
        }
        viewholder.title.setText(digitalmediaitem.name);
        return view;
    }
}
