// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.VideoItem;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.mediaplus.widget.AutoRecycleImageView;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView, GridItemHolder, ViewManager, ListViewManager, 
//            ItemHolder, MediaListAdapter, MediaPlusActivity

public class LocalListView extends MediaPlusListView
{
    public class LocalListAdapter extends MediaListAdapter
    {

        final LocalListView this$0;

        public int getCount()
        {
            int i;
            int j;
            int k;
            if (mDataSource != null)
            {
                i = mDataSource.getCount();
            } else
            {
                i = 0;
            }
            j = i + (mDownloadingCount + mDownloadFinishedCount);
            if (j > 65535)
            {
                k = 0x10000;
            } else
            {
                k = j;
            }
            if (isActive)
            {
                if (k == 0)
                {
                    mEmptyText.setVisibility(0);
                    ((MediaPlusActivity)mContext).refreshControlBar(8);
                } else
                if (k > 0)
                {
                    mEmptyText.setVisibility(8);
                    ((MediaPlusActivity)mContext).refreshControlBar(0);
                    return k;
                }
            }
            return k;
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
                view = mInflater.inflate(0x7f030025, null);
            }
            view.setTag(Integer.valueOf(i));
            fillHolder(createHolder(view), i);
            return view;
        }

        public LocalListAdapter(Context context)
        {
            this$0 = LocalListView.this;
            super(context);
        }
    }


    private final String TAG;
    private boolean isActive;
    private final int mAllowShowCount;
    private int mDownloadFinishedCount;
    private int mDownloadingCount;

    public LocalListView(Context context)
    {
        super(context);
        TAG = "LocalListView";
        mDownloadingCount = -1;
        mDownloadFinishedCount = -1;
        isActive = true;
        mAllowShowCount = 65535;
    }

    public LocalListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "LocalListView";
        mDownloadingCount = -1;
        mDownloadFinishedCount = -1;
        isActive = true;
        mAllowShowCount = 65535;
    }

    public LocalListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TAG = "LocalListView";
        mDownloadingCount = -1;
        mDownloadFinishedCount = -1;
        isActive = true;
        mAllowShowCount = 65535;
    }

    private void updateUpdownloadProgress(View view, int i)
    {
        if (view == null || i < 0 || 100 < i)
        {
            return;
        } else
        {
            ((ProgressBar)view.findViewById(0x7f0900ca)).setProgress(i);
            return;
        }
    }

    private void updateUpdownloadState(View view, int i)
    {
        if (view == null)
        {
            return;
        }
        RelativeLayout relativelayout = (RelativeLayout)view.findViewById(0x7f0900c3);
        RelativeLayout relativelayout1 = (RelativeLayout)view.findViewById(0x7f0900c8);
        ProgressBar progressbar = (ProgressBar)view.findViewById(0x7f0900ca);
        switch (i)
        {
        default:
            return;

        case 2: // '\002'
            relativelayout.setVisibility(8);
            break;
        }
        relativelayout1.setVisibility(0);
        progressbar.setProgress(0);
        progressbar.setMax(100);
    }

    public void addUpdownload(boolean flag, int i)
    {
        if (NetworkUtil.getLocalIpViaWiFi(mContext) != null) goto _L2; else goto _L1
_L1:
        com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showDefaultError(mContext, 804);
_L4:
        return;
_L2:
        if (mUpDownloadEngine == null || mDataSource == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.UploadTask uploadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.UploadTask();
        uploadtask.mediaClass = 3L;
        uploadtask.dms_uuid = Settings.instance().getDefaultDMSUDN();
        if (uploadtask.dms_uuid == null)
        {
            Toast.makeText(mContext, 0x7f0b00c9, 0).show();
            return;
        }
        uploadtask.mediaId = mDataSource.getLongProp(i, Property.PROP_ID, -1L);
        uploadtask.title = mDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
        String s = null;
        if (uri != null)
        {
            s = uri.toString();
        }
        uploadtask.uri = s;
        uploadtask.is3D = mDataSource.getBooleanProp(i, Property.PROP_3D, false);
        if (mUpDownloadEngine.uploadTask(uploadtask))
        {
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 1);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
        downloadtask.mediaClass = 3L;
        downloadtask.dms_uuid = RemoteDBMgr.instance().getCurrentServer();
        downloadtask.mediaId = mDataSource.getLongProp(i, Property.PROP_ID, -1L);
        downloadtask.title = mDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri1 = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
        String s1 = null;
        if (uri1 != null)
        {
            s1 = uri1.toString();
        }
        downloadtask.uri = s1;
        downloadtask.fileSize = mDataSource.getLongProp(i, Property.PROP_SIZE, 0L);
        downloadtask.item_uuid = mDataSource.getRemoteItemUUID(i);
        if (mUpDownloadEngine.downloadTask(downloadtask))
        {
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 0);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void changeGridLayoutParams()
    {
        if (SystemUtils.isLandscape(mContext))
        {
            setPadding(0, 0, 0, 0);
            setNumColumns(6);
            return;
        } else
        {
            setPadding(0, 0, 0, 0);
            setNumColumns(4);
            return;
        }
    }

    protected void construct(Context context)
    {
        super.construct(context);
        setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final LocalListView this$0;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                int j;
                if (isClickable())
                {
                    if ((j = i - (mDownloadingCount + mDownloadFinishedCount)) >= 0)
                    {
                        if (ViewManager.getViewStatus() == 2)
                        {
                            updateSelector(view, j);
                            return;
                        }
                        if (mOpListener != null)
                        {
                            mOpListener.OnItemClick(j, 0L);
                            return;
                        }
                    }
                }
            }

            
            {
                this$0 = LocalListView.this;
                super();
            }
        });
    }

    protected ItemHolder createHolder(View view)
    {
        GridItemHolder griditemholder = new GridItemHolder();
        ((GridItemHolder)griditemholder).mThumb = (AutoRecycleImageView)view.findViewById(0x7f0900c4);
        ((GridItemHolder)griditemholder).mVideoInfo = (RelativeLayout)view.findViewById(0x7f0900c5);
        ((GridItemHolder)griditemholder).mFileDuration = (TextView)view.findViewById(0x7f0900c6);
        ((GridItemHolder)griditemholder).mSelectIcon = (ImageView)view.findViewById(0x7f0900c7);
        ((GridItemHolder)griditemholder).mProgress = (ProgressBar)view.findViewById(0x7f0900ca);
        ((GridItemHolder)griditemholder).mNormalLayout = (RelativeLayout)view.findViewById(0x7f0900c3);
        ((GridItemHolder)griditemholder).mDownloadLayout = (RelativeLayout)view.findViewById(0x7f0900c8);
        return griditemholder;
    }

    protected void fillHolder(ItemHolder itemholder, int i)
    {
        if (i < mDownloadFinishedCount + mDownloadingCount)
        {
            ((GridItemHolder)itemholder).mNormalLayout.setVisibility(8);
            ((GridItemHolder)itemholder).mDownloadLayout.setVisibility(0);
            ((GridItemHolder)itemholder).mProgress.setMax(100);
        } else
        {
            int j = i - (mDownloadFinishedCount + mDownloadingCount);
            ((GridItemHolder)itemholder).mNormalLayout.setVisibility(0);
            ((GridItemHolder)itemholder).mDownloadLayout.setVisibility(8);
            MediaItem mediaitem = mDataSource.getDataSource().getItem(j);
            PictureDataSource picturedatasource = mDataSource;
            android.graphics.Bitmap bitmap = null;
            if (picturedatasource != null)
            {
                bitmap = mDataSource.getBitmap(j);
            }
            if (bitmap != null)
            {
                Log.d("LocalListView", (new StringBuilder()).append("fillHolder Set Image position = ").append(j).toString());
                ((GridItemHolder)itemholder).mThumb.setImageBitmap(bitmap);
            } else
            {
                Log.d("LocalListView", (new StringBuilder()).append("fillHolder no Image position = ").append(j).toString());
                ((GridItemHolder)itemholder).mThumb.setImageDrawable(mDefaultDrawable);
            }
            if (mediaitem != null)
            {
                byte byte0 = 8;
                if (mediaitem.mime_type.contains("video"))
                {
                    TextView textview = ((GridItemHolder)itemholder).mFileDuration;
                    byte0 = 0;
                    if (textview != null)
                    {
                        ((GridItemHolder)itemholder).mFileDuration.setText(TimeUtils.convertSecToTimeString(((VideoItem)mediaitem).duration / 1000L, false));
                    }
                }
                if (((GridItemHolder)itemholder).mVideoInfo != null)
                {
                    ((GridItemHolder)itemholder).mVideoInfo.setVisibility(byte0);
                }
                byte byte1 = 8;
                if (ViewManager.getViewStatus() == 2 && ListViewManager.isSelectedItem(Integer.valueOf(j)))
                {
                    byte1 = 0;
                }
                if (((GridItemHolder)itemholder).mSelectIcon != null)
                {
                    ((GridItemHolder)itemholder).mSelectIcon.setVisibility(byte1);
                    return;
                }
            }
        }
    }

    protected View getViewFromUri(Uri uri)
    {
        int i = mDownloadingCount;
        for (int j = 0; j < i; j++)
        {
            View view = getChildAt(-1 + (i - j));
            if (view != null)
            {
                return view;
            }
        }

        return null;
    }

    public void init(ViewGroup viewgroup, boolean flag)
    {
        if (viewgroup == null)
        {
            throw new NullPointerException("rootview can not be null");
        } else
        {
            mRootView = viewgroup;
            mEmptyText = (TextView)mRootView.findViewById(0x7f0900a5);
            initDefault();
            mListAdapter = new LocalListAdapter(mContext);
            changeGridLayoutParams();
            setAdapter(mListAdapter);
            construct(mContext);
            return;
        }
    }

    public void invalidateViews()
    {
        super.invalidateViews();
    }

    public void onDeleted(ArrayList arraylist)
    {
        Log.d("FENG", "FENG local onDeleted --------------------");
        if (mDataSource != null)
        {
            int i = 0;
            do
            {
                if (i >= arraylist.size())
                {
                    break;
                }
                MediaItem mediaitem = (MediaItem)arraylist.get(i);
                Log.d("FENG", (new StringBuilder()).append("FENG local onDeleted ----- item.title = ").append(mediaitem.title).toString());
                try
                {
                    boolean flag = mDataSource.delete((MediaItem)arraylist.get(i));
                    Log.d("FENG", (new StringBuilder()).append("FENG local onDeleted ----- ires = ").append(flag).toString());
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    Log.e("FENG", (new StringBuilder()).append("FENG onDeleted ----- ExceptionS = ").append(exception).toString());
                }
                i++;
            } while (true);
            mDataSource.refreshThumbEngine();
        }
    }

    public void onListCountChanged(int i, int j)
    {
        if (i > j)
        {
            int k = i - j;
            int l = mDownloadFinishedCount - k;
            if (l < 0)
            {
                l = 0;
            }
            mDownloadFinishedCount = l;
            return;
        } else
        {
            mDownloadFinishedCount = 0;
            return;
        }
    }

    public void onPause()
    {
        super.onPause();
        isActive = false;
        mDownloadingCount = -1;
        mDownloadFinishedCount = -1;
    }

    public void onResume()
    {
        super.onResume();
        isActive = true;
        mDownloadingCount = getDownLoadCount();
        mDownloadFinishedCount = 0;
    }

    protected void releaseHolder(ItemHolder itemholder, int i)
    {
        if (itemholder != null)
        {
            ((GridItemHolder)itemholder).mThumb.destroyDrawingCache();
            ((GridItemHolder)itemholder).mThumb = null;
            ((GridItemHolder)itemholder).mDownloadIcon = null;
            ((GridItemHolder)itemholder).mDownloadLayout = null;
            ((GridItemHolder)itemholder).mFileDuration = null;
            ((GridItemHolder)itemholder).mNormalLayout = null;
            ((GridItemHolder)itemholder).mProgress = null;
            ((GridItemHolder)itemholder).mSelectIcon = null;
            ((GridItemHolder)itemholder).mVideoInfo = null;
        }
    }

    protected void updownloadFinish(int i, Object obj)
    {
        Log.d("FENG", (new StringBuilder()).append("FENG local updownloadFinish IN arg1 = ").append(i).toString());
        int j = getDownLoadCount();
        mDownloadFinishedCount = mDownloadingCount - j;
        mDownloadingCount = j;
        super.updownloadFinish(i, obj);
    }

    protected void updownloadProgress(int i, Object obj)
    {
        updateUpdownloadProgress(getViewFromUri(Uri.parse((String)obj)), i);
    }

    protected void updownloadStart(Object obj)
    {
        mDownloadingCount = getDownLoadCount();
        updateUpdownloadState(getViewFromUri(Uri.parse((String)obj)), 2);
    }



}
