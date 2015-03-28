// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            AbsTaskItem, DownloadTaskItem, UploadTaskItem, UpDownloadUtils

public class UpDownloadListView extends ListView
{
    public class UpDownloadListAdapter extends BaseAdapter
    {

        private LayoutInflater mInflater;
        final UpDownloadListView this$0;

        public int getCount()
        {
            if (mUpDownloadEngine == null)
            {
                return 0;
            } else
            {
                return mUpDownloadEngine.getAllTaskCount();
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
            ViewHolder viewholder;
            AbsTaskItem abstaskitem;
label0:
            {
                if (view == null)
                {
                    view = mInflater.inflate(0x7f030036, null);
                    viewholder = new ViewHolder();
                    viewholder.ivTypeIcon = (ImageView)view.findViewById(0x7f09010b);
                    viewholder.tvFileName = (TextView)view.findViewById(0x7f09010d);
                    viewholder.tvFileSize = (TextView)view.findViewById(0x7f09010e);
                    viewholder.progressBar = (ProgressBar)view.findViewById(0x7f090111);
                    viewholder.tvPercent = (TextView)view.findViewById(0x7f0900d4);
                    viewholder.ivRetryIcon = (ImageButton)view.findViewById(0x7f090113);
                    viewholder.ivRetryIcon.setOnClickListener(new android.view.View.OnClickListener() {

                        final UpDownloadListAdapter this$1;

                        public void onClick(View view)
                        {
                            retryUpdownload(((Integer)view.getTag()).intValue());
                        }

            
            {
                this$1 = UpDownloadListAdapter.this;
                super();
            }
                    });
                    viewholder.ivCancelIcon = (ImageButton)view.findViewById(0x7f090114);
                    viewholder.ivCancelIcon.setOnClickListener(new android.view.View.OnClickListener() {

                        final UpDownloadListAdapter this$1;

                        public void onClick(View view)
                        {
                            cancelUpdownload(((Integer)view.getTag()).intValue());
                        }

            
            {
                this$1 = UpDownloadListAdapter.this;
                super();
            }
                    });
                    viewholder.tvStateHint = (TextView)view.findViewById(0x7f090112);
                    viewholder.tvStateHint.setOnClickListener(new android.view.View.OnClickListener() {

                        final UpDownloadListAdapter this$1;

                        public void onClick(View view)
                        {
                            retryUpdownload(((Integer)view.getTag()).intValue());
                        }

            
            {
                this$1 = UpDownloadListAdapter.this;
                super();
            }
                    });
                    view.setTag(viewholder);
                } else
                {
                    viewholder = (ViewHolder)view.getTag();
                }
                if (viewholder != null && mUpDownloadEngine != null)
                {
                    abstaskitem = mUpDownloadEngine.getTaskItemByPos(i);
                    if (abstaskitem != null)
                    {
                        break label0;
                    }
                }
                return view;
            }
            viewholder.serveruuid = abstaskitem.dms_uuid;
            if (abstaskitem.itemtype == 0)
            {
                if (((DownloadTaskItem)abstaskitem).title != null)
                {
                    viewholder.tvFileName.setText(((DownloadTaskItem)abstaskitem).title);
                } else
                {
                    viewholder.tvFileName.setText(UpDownloadUtils.getFileNameFromURL(((DownloadTaskItem)abstaskitem).path));
                }
                viewholder.tvFileName.setTag(((DownloadTaskItem)abstaskitem).uri);
            } else
            {
                viewholder.tvFileName.setText(((UploadTaskItem)abstaskitem).title);
                viewholder.tvFileName.setTag(((UploadTaskItem)abstaskitem).uri);
            }
            viewholder.tvFileSize.setText(FileUtils.formatSize(abstaskitem.totalbytes));
            updateUpdownloadState(viewholder, abstaskitem);
            updateUpdownloadProgress(viewholder, abstaskitem);
            viewholder.ivRetryIcon.setClickable(true);
            viewholder.ivRetryIcon.setTag(Integer.valueOf(i));
            viewholder.ivCancelIcon.setClickable(true);
            viewholder.ivCancelIcon.setTag(Integer.valueOf(i));
            viewholder.tvStateHint.setClickable(true);
            viewholder.tvStateHint.setTag(Integer.valueOf(i));
            return view;
        }

        public UpDownloadListAdapter(LayoutInflater layoutinflater)
        {
            this$0 = UpDownloadListView.this;
            super();
            mInflater = null;
            mInflater = layoutinflater;
        }
    }

    public static class ViewHolder
    {

        ImageButton ivCancelIcon;
        ImageButton ivRetryIcon;
        ImageView ivTypeIcon;
        ProgressBar progressBar;
        String serveruuid;
        TextView tvFileName;
        TextView tvFileSize;
        TextView tvPercent;
        TextView tvStateHint;

        public ViewHolder()
        {
        }
    }


    private BaseAdapter mAdapter;
    private Context mContext;
    private UpDownloadEngine mUpDownloadEngine;

    public UpDownloadListView(Context context)
    {
        super(context);
        mUpDownloadEngine = null;
        mAdapter = null;
        mContext = context;
    }

    public UpDownloadListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mUpDownloadEngine = null;
        mAdapter = null;
        mContext = context;
        if (mAdapter == null)
        {
            mAdapter = new UpDownloadListAdapter(LayoutInflater.from(mContext));
        }
        setAdapter(mAdapter);
    }

    private void cancelUpdownload(int i)
    {
        if (mUpDownloadEngine != null && i > -1)
        {
            AbsTaskItem abstaskitem = mUpDownloadEngine.getTaskItemByPos(i);
            if (abstaskitem != null)
            {
                Log.i("UpDownloadListView", "cancelUpdownload...");
                if (abstaskitem.itemtype == 0)
                {
                    mUpDownloadEngine.cancelTask(abstaskitem.itemtype, abstaskitem.dms_uuid, Uri.parse(((DownloadTaskItem)abstaskitem).uri));
                } else
                {
                    mUpDownloadEngine.cancelTask(abstaskitem.itemtype, abstaskitem.dms_uuid, Uri.parse(((UploadTaskItem)abstaskitem).uri));
                }
                if (abstaskitem.state != 2)
                {
                    invalidateViews();
                }
            }
        }
    }

    private ViewHolder getViewFromUri(String s, Uri uri)
    {
        int i = getChildCount();
        View view;
        ViewHolder viewholder;
        Uri uri1;
        for (int j = 0; j < i; j++)
        {
            view = getChildAt(j);
            if (view == null)
            {
                viewholder = null;
            } else
            {
                viewholder = (ViewHolder)view.getTag();
            }
            if (viewholder == null)
            {
                uri1 = null;
            } else
            {
                uri1 = Uri.parse((String)viewholder.tvFileName.getTag());
            }
            if (s != null && uri1 != null && s.compareTo(viewholder.serveruuid) == 0 && uri1.compareTo(uri) == 0)
            {
                return viewholder;
            }
        }

        return null;
    }

    private void retryUpdownload(int i)
    {
        AbsTaskItem abstaskitem;
label0:
        {
            if (mUpDownloadEngine != null && i > -1)
            {
                abstaskitem = mUpDownloadEngine.getTaskItemByPos(i);
                if (abstaskitem != null && abstaskitem.state == 4)
                {
                    Log.i("UpDownloadListView", "retryUpdownload...");
                    if (abstaskitem.itemtype != 0)
                    {
                        break label0;
                    }
                    mUpDownloadEngine.retryTask(abstaskitem.itemtype, abstaskitem.dms_uuid, Uri.parse(((DownloadTaskItem)abstaskitem).uri));
                }
            }
            return;
        }
        mUpDownloadEngine.retryTask(abstaskitem.itemtype, abstaskitem.dms_uuid, Uri.parse(((UploadTaskItem)abstaskitem).uri));
    }

    private void updateUpdownloadProgress(ViewHolder viewholder, AbsTaskItem abstaskitem)
    {
        long l = 0L;
        if (viewholder == null || abstaskitem == null)
        {
            return;
        }
        int i;
        if (abstaskitem.totalbytes != l)
        {
            l = (100L * abstaskitem.currentbytes) / abstaskitem.totalbytes;
        }
        i = (int)l;
        if (abstaskitem.state == 3)
        {
            i = 100;
        }
        viewholder.progressBar.setProgress(i);
        viewholder.tvPercent.setText((new StringBuilder()).append(Integer.toString(i)).append("%").toString());
    }

    private void updateUpdownloadState(ViewHolder viewholder, AbsTaskItem abstaskitem)
    {
        int i;
        if (viewholder == null || abstaskitem == null)
        {
            return;
        }
        if (abstaskitem.itemtype == 0)
        {
            if (abstaskitem.state == 4)
            {
                viewholder.ivTypeIcon.setImageResource(0x7f0200ff);
            } else
            {
                viewholder.ivTypeIcon.setImageResource(0x7f0200f8);
            }
        } else
        if (abstaskitem.state == 4)
        {
            viewholder.ivTypeIcon.setImageResource(0x7f0202ad);
        } else
        {
            viewholder.ivTypeIcon.setImageResource(0x7f0202ac);
        }
        if (abstaskitem.state == 4)
        {
            viewholder.ivRetryIcon.setVisibility(0);
        } else
        {
            viewholder.ivRetryIcon.setVisibility(8);
        }
        i = 0x7f0b00ce;
        if (abstaskitem.itemtype != 0 || abstaskitem.state != 2) goto _L2; else goto _L1
_L1:
        i = 0x7f0b00cf;
_L4:
        viewholder.tvStateHint.setText(i);
        return;
_L2:
        if (abstaskitem.itemtype == 1 && abstaskitem.state == 2)
        {
            i = 0x7f0b00d0;
        } else
        if (abstaskitem.state == 3)
        {
            i = 0x7f0b00d1;
        } else
        if (abstaskitem.state == 4)
        {
            i = 0x7f0b00d2;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void cancelAllUpdownload()
    {
        Log.i("UpDownloadListView", "cancelAllUpdownload...");
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
    }

    public void onUpDownloadFinish(Message message)
    {
        invalidateViews();
        if (message.arg1 == 817 || message.arg1 == 819)
        {
            UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[0]);
        } else
        if (message.obj != null)
        {
            Bundle bundle = message.getData();
            String s;
            ViewHolder viewholder;
            Object obj;
            int i;
            UpDownloadEngine updownloadengine;
            AbsTaskItem abstaskitem;
            if (message.obj instanceof IPoolDriver.DownloadResult)
            {
                s = ((IPoolDriver.DownloadResult)message.obj).request.uri;
            } else
            {
                s = ((UploadPoolDriver.UploadResult)message.obj).request.uri;
            }
            viewholder = getViewFromUri(bundle.getString("serveruuid"), Uri.parse(s));
            if (viewholder == null)
            {
                obj = Integer.valueOf(0);
            } else
            {
                obj = viewholder.ivCancelIcon.getTag();
            }
            i = ((Integer)obj).intValue();
            updownloadengine = mUpDownloadEngine;
            abstaskitem = null;
            if (updownloadengine != null)
            {
                abstaskitem = mUpDownloadEngine.getTaskItemByPos(i);
            }
            if (message.arg1 == 911)
            {
                Context context1 = mContext;
                int l;
                if (message.obj instanceof UploadPoolDriver.UploadResult)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
                UpDownloadUtils.ErrorCode.showUpDownloadError(context1, l, message.arg1, new Object[] {
                    s
                });
            } else
            {
                Context context = mContext;
                int j;
                int k;
                Object aobj[];
                String s1;
                if (message.obj instanceof UploadPoolDriver.UploadResult)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                k = message.arg1;
                aobj = new Object[1];
                if (message.obj instanceof IPoolDriver.DownloadResult)
                {
                    s1 = ((IPoolDriver.DownloadResult)message.obj).request.title;
                } else
                {
                    s1 = ((UploadPoolDriver.UploadResult)message.obj).request.title;
                }
                aobj[0] = s1;
                UpDownloadUtils.ErrorCode.showUpDownloadError(context, j, k, aobj);
            }
            updateUpdownloadState(viewholder, abstaskitem);
            updateUpdownloadProgress(viewholder, abstaskitem);
            return;
        }
    }

    public void onUpDownloadProgress(Message message)
    {
        Bundle bundle = message.getData();
        String s = (String)message.obj;
        ViewHolder viewholder = getViewFromUri(bundle.getString("serveruuid"), Uri.parse(s));
        Object obj;
        int i;
        if (viewholder == null)
        {
            obj = Integer.valueOf(0);
        } else
        {
            obj = viewholder.ivCancelIcon.getTag();
        }
        i = ((Integer)obj).intValue();
        if (mUpDownloadEngine != null)
        {
            updateUpdownloadProgress(viewholder, mUpDownloadEngine.getTaskItemByPos(i));
        }
    }

    public void onUpDownloadStart(Message message)
    {
        Bundle bundle = message.getData();
        String s = (String)message.obj;
        ViewHolder viewholder = getViewFromUri(bundle.getString("serveruuid"), Uri.parse(s));
        Object obj;
        int i;
        if (viewholder == null)
        {
            obj = Integer.valueOf(0);
        } else
        {
            obj = viewholder.ivCancelIcon.getTag();
        }
        i = ((Integer)obj).intValue();
        if (mUpDownloadEngine != null)
        {
            updateUpdownloadState(viewholder, mUpDownloadEngine.getTaskItemByPos(i));
        }
    }

    public void setUpDownloadEngine(UpDownloadEngine updownloadengine)
    {
        mUpDownloadEngine = updownloadengine;
        invalidateViews();
    }





}
