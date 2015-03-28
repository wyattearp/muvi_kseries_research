// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;

public class EasyTransferRegisterListView extends ListView
{
    private class EasyTransferAdapter extends BaseAdapter
    {

        final EasyTransferRegisterListView this$0;

        public int getCount()
        {
            if (mEasyTransferEngine != null)
            {
                return mEasyTransferEngine.getCount();
            } else
            {
                return 0;
            }
        }

        public Object getItem(int i)
        {
            return null;
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if (view == null)
            {
                view = LayoutInflater.from(mContext).inflate(0x7f030013, null);
            }
            setServerInfo(view, i);
            return view;
        }

        private EasyTransferAdapter()
        {
            this$0 = EasyTransferRegisterListView.this;
            super();
        }

    }

    public static interface IListItemClickListener
    {

        public abstract void onItemClick(AdapterView adapterview, View view, int i, long l);

        public abstract boolean onItemLongClick(AdapterView adapterview, View view, int i, long l);
    }

    private static class ViewHolder
    {

        int position;
        long tableid;

        private ViewHolder()
        {
            position = -1;
            tableid = -1L;
        }

    }


    private final String TAG;
    private BaseAdapter mAdapter;
    private Context mContext;
    IEasyTransferEngine mEasyTransferEngine;

    public EasyTransferRegisterListView(Context context)
    {
        super(context);
        TAG = "EasyTransferRegisterListView";
        mContext = null;
        mAdapter = null;
        mEasyTransferEngine = null;
        mContext = context;
    }

    public EasyTransferRegisterListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "EasyTransferRegisterListView";
        mContext = null;
        mAdapter = null;
        mEasyTransferEngine = null;
        mContext = context;
        if (mAdapter == null)
        {
            mAdapter = new EasyTransferAdapter();
            setAdapter(mAdapter);
        }
    }

    public EasyTransferRegisterListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TAG = "EasyTransferRegisterListView";
        mContext = null;
        mAdapter = null;
        mEasyTransferEngine = null;
        mContext = context;
        if (mAdapter == null)
        {
            mAdapter = new EasyTransferAdapter();
            setAdapter(mAdapter);
        }
    }

    private void setServerInfo(View view, int i)
    {
        if (mEasyTransferEngine != null)
        {
            ViewHolder viewholder = (ViewHolder)view.getTag();
            if (viewholder == null)
            {
                viewholder = new ViewHolder();
                view.setTag(viewholder);
            }
            long l = mEasyTransferEngine.getTableid(i);
            viewholder.position = i;
            viewholder.tableid = l;
            com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine.Result result = mEasyTransferEngine.getEasyTransfer(l);
            if (result != null)
            {
                ProgressBar progressbar = (ProgressBar)view.findViewById(0x7f09006a);
                TextView textview = (TextView)view.findViewById(0x7f090067);
                TextView textview1 = (TextView)view.findViewById(0x7f090069);
                Object aobj[];
                if (result.serverState == 1)
                {
                    view.setEnabled(true);
                    progressbar.setVisibility(4);
                } else
                {
                    view.setEnabled(false);
                    progressbar.setVisibility(0);
                }
                textview.setText(result.request.servername);
                aobj = new Object[2];
                aobj[0] = Integer.valueOf(result.request.startHour);
                aobj[1] = Integer.valueOf(result.request.startMinute);
                textview1.setText(String.format("%02d:%02d", aobj));
                return;
            }
        }
    }

    public void init(final IListItemClickListener itemListener)
    {
        setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final EasyTransferRegisterListView this$0;
            final IListItemClickListener val$itemListener;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                ViewHolder viewholder = (ViewHolder)view.getTag();
                if (itemListener != null)
                {
                    itemListener.onItemClick(adapterview, view, i, viewholder.tableid);
                }
            }

            
            {
                this$0 = EasyTransferRegisterListView.this;
                itemListener = ilistitemclicklistener;
                super();
            }
        });
        setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            final EasyTransferRegisterListView this$0;
            final IListItemClickListener val$itemListener;

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                ViewHolder viewholder = (ViewHolder)view.getTag();
                if (itemListener != null)
                {
                    return itemListener.onItemLongClick(adapterview, view, i, viewholder.tableid);
                } else
                {
                    return false;
                }
            }

            
            {
                this$0 = EasyTransferRegisterListView.this;
                itemListener = ilistitemclicklistener;
                super();
            }
        });
    }

    public void setEasyTransferEngine(IEasyTransferEngine ieasytransferengine)
    {
        mEasyTransferEngine = ieasytransferengine;
        invalidateViews();
    }

    public void uninit()
    {
    }


}
