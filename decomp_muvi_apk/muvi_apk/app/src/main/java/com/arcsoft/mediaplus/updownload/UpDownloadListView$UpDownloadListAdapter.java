// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UpDownloadListView, AbsTaskItem, DownloadTaskItem, UpDownloadUtils, 
//            UploadTaskItem

public class mInflater extends BaseAdapter
{

    private LayoutInflater mInflater;
    final UpDownloadListView this$0;

    public int getCount()
    {
        if (UpDownloadListView.access$000(UpDownloadListView.this) == null)
        {
            return 0;
        } else
        {
            return UpDownloadListView.access$000(UpDownloadListView.this).getAllTaskCount();
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
        this._cls0 _lcls0;
        AbsTaskItem abstaskitem;
label0:
        {
            if (view == null)
            {
                view = mInflater.inflate(0x7f030036, null);
                _lcls0 = new mInflater();
                _lcls0.mInflater = (ImageView)view.findViewById(0x7f09010b);
                _lcls0.mInflater = (TextView)view.findViewById(0x7f09010d);
                _lcls0.mInflater = (TextView)view.findViewById(0x7f09010e);
                _lcls0. = (ProgressBar)view.findViewById(0x7f090111);
                _lcls0. = (TextView)view.findViewById(0x7f0900d4);
                _lcls0. = (ImageButton)view.findViewById(0x7f090113);
                _lcls0..setOnClickListener(new android.view.View.OnClickListener() {

                    final UpDownloadListView.UpDownloadListAdapter this$1;

                    public void onClick(View view1)
                    {
                        UpDownloadListView.access$100(this$0, ((Integer)view1.getTag()).intValue());
                    }

            
            {
                this$1 = UpDownloadListView.UpDownloadListAdapter.this;
                super();
            }
                });
                _lcls0.n = (ImageButton)view.findViewById(0x7f090114);
                _lcls0.n.setOnClickListener(new android.view.View.OnClickListener() {

                    final UpDownloadListView.UpDownloadListAdapter this$1;

                    public void onClick(View view1)
                    {
                        UpDownloadListView.access$200(this$0, ((Integer)view1.getTag()).intValue());
                    }

            
            {
                this$1 = UpDownloadListView.UpDownloadListAdapter.this;
                super();
            }
                });
                _lcls0. = (TextView)view.findViewById(0x7f090112);
                _lcls0..setOnClickListener(new android.view.View.OnClickListener() {

                    final UpDownloadListView.UpDownloadListAdapter this$1;

                    public void onClick(View view1)
                    {
                        UpDownloadListView.access$100(this$0, ((Integer)view1.getTag()).intValue());
                    }

            
            {
                this$1 = UpDownloadListView.UpDownloadListAdapter.this;
                super();
            }
                });
                view.setTag(_lcls0);
            } else
            {
                _lcls0 = (this._cls0)view.getTag();
            }
            if (_lcls0 != null && UpDownloadListView.access$000(UpDownloadListView.this) != null)
            {
                abstaskitem = UpDownloadListView.access$000(UpDownloadListView.this).getTaskItemByPos(i);
                if (abstaskitem != null)
                {
                    break label0;
                }
            }
            return view;
        }
        _lcls0. = abstaskitem.dms_uuid;
        if (abstaskitem.itemtype == 0)
        {
            if (((DownloadTaskItem)abstaskitem).title != null)
            {
                _lcls0..setText(((DownloadTaskItem)abstaskitem).title);
            } else
            {
                _lcls0..setText(UpDownloadUtils.getFileNameFromURL(((DownloadTaskItem)abstaskitem).path));
            }
            _lcls0..setTag(((DownloadTaskItem)abstaskitem).uri);
        } else
        {
            _lcls0..setText(((UploadTaskItem)abstaskitem).title);
            _lcls0..setTag(((UploadTaskItem)abstaskitem).uri);
        }
        _lcls0..setText(FileUtils.formatSize(abstaskitem.totalbytes));
        UpDownloadListView.access$300(UpDownloadListView.this, _lcls0, abstaskitem);
        UpDownloadListView.access$400(UpDownloadListView.this, _lcls0, abstaskitem);
        _lcls0..setClickable(true);
        _lcls0..setTag(Integer.valueOf(i));
        _lcls0.n.setClickable(true);
        _lcls0.n.setTag(Integer.valueOf(i));
        _lcls0..setClickable(true);
        _lcls0..setTag(Integer.valueOf(i));
        return view;
    }

    public _cls3.this._cls1(LayoutInflater layoutinflater)
    {
        this$0 = UpDownloadListView.this;
        super();
        mInflater = null;
        mInflater = layoutinflater;
    }
}
