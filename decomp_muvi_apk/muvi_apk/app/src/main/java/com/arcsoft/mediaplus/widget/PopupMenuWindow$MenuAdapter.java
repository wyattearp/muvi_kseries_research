// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.arcsoft.mediaplus.widget:
//            PopupMenuWindow

class mContext extends BaseAdapter
{
    class MenuViewHolder
    {

        ImageView menuCover;
        TextView menuTitle;
        final PopupMenuWindow.MenuAdapter this$1;

        MenuViewHolder()
        {
            this$1 = PopupMenuWindow.MenuAdapter.this;
            super();
        }
    }


    private Context mContext;
    private LayoutInflater mInflater;
    final PopupMenuWindow this$0;

    protected MenuViewHolder createHolder(View view)
    {
        if (view == null)
        {
            return null;
        }
        if (view.getTag() == null)
        {
            MenuViewHolder menuviewholder = new MenuViewHolder();
            menuviewholder.menuTitle = (TextView)view.findViewById(0x7f090029);
            menuviewholder.menuCover = (ImageView)view.findViewById(0x7f090028);
            view.setTag(menuviewholder);
            return menuviewholder;
        } else
        {
            return (MenuViewHolder)view.getTag();
        }
    }

    protected boolean fillHolder(MenuViewHolder menuviewholder, int i)
    {
        if (mContext == null || menuviewholder == null || PopupMenuWindow.access$200(PopupMenuWindow.this) == null || PopupMenuWindow.access$200(PopupMenuWindow.this).namesIds == null)
        {
            return false;
        } else
        {
            menuviewholder.menuTitle.setText(mContext.getResources().getString(PopupMenuWindow.access$200(PopupMenuWindow.this).namesIds[i]));
            menuviewholder.menuCover.setBackgroundResource(PopupMenuWindow.access$200(PopupMenuWindow.this).iconIds[i]);
            menuviewholder.menuTitle.setTextColor(-1);
            return true;
        }
    }

    public int getCount()
    {
        if (PopupMenuWindow.access$200(PopupMenuWindow.this) == null || PopupMenuWindow.access$200(PopupMenuWindow.this).namesIds == null)
        {
            return 0;
        } else
        {
            return PopupMenuWindow.access$200(PopupMenuWindow.this).namesIds.length;
        }
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
        if (view == null)
        {
            view = mInflater.inflate(0x7f030006, null);
            if (view == null)
            {
                return null;
            }
        }
        fillHolder(createHolder(view), i);
        return view;
    }

    public MenuViewHolder.this._cls1(Context context)
    {
        this$0 = PopupMenuWindow.this;
        super();
        mContext = null;
        mInflater = null;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
}
