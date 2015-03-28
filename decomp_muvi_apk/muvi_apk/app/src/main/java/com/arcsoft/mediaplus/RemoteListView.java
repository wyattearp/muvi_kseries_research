// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
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
import com.arcsoft.mediaplus.datasource.ImageDataSourceHelper;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.VideoItem;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.mediaplus.widget.AutoRecycleImageView;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.util.ArrayList;
import java.util.Hashtable;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView, GridItemHolder, ListItemHolder, ViewManager, 
//            ItemHolder, MediaListAdapter, MediaPlusActivity

public class RemoteListView extends MediaPlusListView
{
    public class RemoteListAdapter extends MediaListAdapter
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
            if (isActive)
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

        public RemoteListAdapter(Context context)
        {
            this$0 = RemoteListView.this;
            super(context);
        }
    }


    private final String TAG;
    private boolean isActive;
    protected boolean isGridView;
    private final int mAllowShowCount;
    private int mCurProgress;
    private com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask mNeedToDownloadTask;

    public RemoteListView(Context context)
    {
        super(context);
        TAG = "RemoteListView";
        isGridView = false;
        mNeedToDownloadTask = null;
        isActive = true;
        mCurProgress = 0;
        mAllowShowCount = 65535;
    }

    public RemoteListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "RemoteListView";
        isGridView = false;
        mNeedToDownloadTask = null;
        isActive = true;
        mCurProgress = 0;
        mAllowShowCount = 65535;
    }

    public RemoteListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TAG = "RemoteListView";
        isGridView = false;
        mNeedToDownloadTask = null;
        isActive = true;
        mCurProgress = 0;
        mAllowShowCount = 65535;
    }

    private void fillDownloadIcon(ItemHolder itemholder, int i)
    {
        if (itemholder == null)
        {
            return;
        }
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            if (isGridView)
            {
                ((GridItemHolder)itemholder).mDownloadIcon.setImageResource(0x7f020108);
                return;
            } else
            {
                ((ListItemHolder)itemholder).mDownloadStatus.setImageResource(0x7f020103);
                ((ListItemHolder)itemholder).mProgress.setProgress(0);
                ((ListItemHolder)itemholder).mProgress.setVisibility(0);
                return;
            }

        case 2: // '\002'
            if (isGridView)
            {
                ((GridItemHolder)itemholder).mDownloadIcon.setImageResource(0x7f020104);
                return;
            } else
            {
                ((ListItemHolder)itemholder).mDownloadStatus.setImageResource(0x7f020109);
                ((ListItemHolder)itemholder).mProgress.setProgress(mCurProgress);
                ((ListItemHolder)itemholder).mProgress.setVisibility(0);
                return;
            }

        case 4: // '\004'
            if (isGridView)
            {
                ((GridItemHolder)itemholder).mDownloadIcon.setImageResource(0x7f020100);
                return;
            } else
            {
                ((ListItemHolder)itemholder).mDownloadStatus.setImageResource(0x7f020100);
                ((ListItemHolder)itemholder).mProgress.setVisibility(8);
                ((ListItemHolder)itemholder).mProgress.setProgress(0);
                return;
            }

        case 3: // '\003'
            break;
        }
        if (isGridView)
        {
            ((GridItemHolder)itemholder).mDownloadIcon.setImageResource(0x7f020105);
            return;
        } else
        {
            ((ListItemHolder)itemholder).mDownloadStatus.setImageResource(0x7f020102);
            ((ListItemHolder)itemholder).mProgress.setProgress(100);
            ((ListItemHolder)itemholder).mProgress.setVisibility(8);
            return;
        }
    }

    private ArrayList makeAllDownloadTasks()
    {
        ArrayList arraylist = new ArrayList();
        int i = mDataSource.getCount();
        for (int j = 0; j < i; j++)
        {
            if (!checkalreadyDownloaded(j))
            {
                arraylist.add(makeDownloadTask(j));
            }
        }

        return arraylist;
    }

    private void updateDownloadStatus()
    {
        invalidateViews();
    }

    void addDownloadByTask(com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask)
    {
        if (mUpDownloadEngine.downloadTask(downloadtask))
        {
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 0);
        }
    }

    public void addUpdownload(boolean flag, int i)
    {
        if (NetworkUtil.getLocalIpViaWiFi(mContext) == null)
        {
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showDefaultError(mContext, 804);
        } else
        if (mUpDownloadEngine != null && mDataSource != null)
        {
            if (flag)
            {
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
            } else
            {
                addDownloadByTask(makeDownloadTask(i));
                return;
            }
        }
    }

    void alreadyDownloaded(int i)
    {
        Message message = new Message();
        message.what = 1540;
        mNeedToDownloadTask = makeDownloadTask(i);
        mHandler.sendMessage(message);
    }

    public void changeGridLayoutParams()
    {
        if (SystemUtils.isLandscape(mContext))
        {
            if (isGridView)
            {
                setPadding(getResources().getDimensionPixelSize(0x7f080128), 0, getResources().getDimensionPixelSize(0x7f080128), 0);
                setNumColumns(6);
                return;
            } else
            {
                setPadding(0, 0, 0, 0);
                setNumColumns(1);
                return;
            }
        }
        if (isGridView)
        {
            setPadding(0, 0, 0, 0);
            setNumColumns(4);
            return;
        } else
        {
            setPadding(0, 0, 0, 0);
            setNumColumns(1);
            return;
        }
    }

    boolean checkalreadyDownloaded(int i)
    {
        if (mUpDownloadEngine != null)
        {
            Hashtable hashtable = mUpDownloadEngine.getDownloadStates();
            Uri uri = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
            String s = (String)mDataSource.getObjectProp(i, Property.PROP_TITLE, null);
            if (hashtable.containsKey(uri.toString()) || hashtable.containsKey(s))
            {
                return true;
            }
        }
        return false;
    }

    protected void construct(Context context)
    {
        super.construct(context);
        setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            final RemoteListView this$0;

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                if (!isGridView)
                {
                    break MISSING_BLOCK_LABEL_102;
                }
                if (ViewManager.getViewStatus() != 2) goto _L2; else goto _L1
_L1:
                updateSelector(view, i);
_L4:
                return;
_L2:
                if (ViewManager.getViewStatus() == 4)
                {
                    if (checkalreadyDownloaded(i))
                    {
                        alreadyDownloaded(i);
                        return;
                    } else
                    {
                        addUpdownload(false, i);
                        updateDownloadStatus();
                        return;
                    }
                }
                if (ViewManager.getViewStatus() != 0 || mOpListener == null) goto _L4; else goto _L3
_L3:
                mOpListener.OnItemClick(i, 0L);
                return;
                if (checkalreadyDownloaded(i))
                {
                    alreadyDownloaded(i);
                    return;
                } else
                {
                    addUpdownload(false, i);
                    updateDownloadStatus();
                    return;
                }
            }

            
            {
                this$0 = RemoteListView.this;
                super();
            }
        });
    }

    protected ItemHolder createHolder(View view)
    {
        if (isGridView)
        {
            GridItemHolder griditemholder = new GridItemHolder();
            ((GridItemHolder)griditemholder).mThumb = (AutoRecycleImageView)view.findViewById(0x7f0900c4);
            ((GridItemHolder)griditemholder).mVideoInfo = (RelativeLayout)view.findViewById(0x7f0900c5);
            ((GridItemHolder)griditemholder).mFileDuration = (TextView)view.findViewById(0x7f0900c6);
            ((GridItemHolder)griditemholder).mSelectIcon = (ImageView)view.findViewById(0x7f0900c7);
            ((GridItemHolder)griditemholder).mDownloadIcon = (ImageView)view.findViewById(0x7f0900cb);
            return griditemholder;
        } else
        {
            ListItemHolder listitemholder = new ListItemHolder();
            ((ListItemHolder)listitemholder).mMediaType = (ImageView)view.findViewById(0x7f0900cc);
            ((ListItemHolder)listitemholder).mFileName = (TextView)view.findViewById(0x7f0900cd);
            ((ListItemHolder)listitemholder).mFileSize = (TextView)view.findViewById(0x7f0900cf);
            ((ListItemHolder)listitemholder).mFileDate = (TextView)view.findViewById(0x7f0900d0);
            ((ListItemHolder)listitemholder).mDownloadStatus = (ImageView)view.findViewById(0x7f0900ce);
            ((ListItemHolder)listitemholder).mProgress = (ProgressBar)view.findViewById(0x7f0900d1);
            return listitemholder;
        }
    }

    public void downloadAll()
    {
        ArrayList arraylist;
        if (mDataSource != null && mUpDownloadEngine != null && mContext != null)
        {
            if ((arraylist = makeAllDownloadTasks()) != null && arraylist.size() != 0)
            {
                int i = mUpDownloadEngine.downloadTask(arraylist);
                if (i == 1)
                {
                    com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadStarted(mContext, 0);
                    return;
                } else
                {
                    Context context = mContext;
                    Resources resources = mContext.getResources();
                    Object aobj[] = new Object[1];
                    aobj[0] = String.valueOf(i);
                    Toast.makeText(context, resources.getString(0x7f0b00b2, aobj), 0).show();
                    return;
                }
            }
        }
    }

    protected void fillHolder(ItemHolder itemholder, int i)
    {
        if (!isGridView) goto _L2; else goto _L1
_L1:
        MediaItem mediaitem1;
        PictureDataSource picturedatasource = mDataSource;
        android.graphics.Bitmap bitmap = null;
        if (picturedatasource != null)
        {
            bitmap = mDataSource.getBitmap(i);
        }
        if (bitmap != null)
        {
            Log.d("RemoteListView", (new StringBuilder()).append("fillHolder Set Image position = ").append(i).toString());
            ((GridItemHolder)itemholder).mThumb.setImageBitmap(bitmap);
        } else
        {
            Log.d("RemoteListView", (new StringBuilder()).append("fillHolder no Image position = ").append(i).toString());
            ((GridItemHolder)itemholder).mThumb.setImageDrawable(mDefaultDrawable);
        }
        mediaitem1 = mDataSource.getDataSource().getItem(i);
        if (mediaitem1 != null) goto _L4; else goto _L3
_L3:
        return;
_L4:
        if (mediaitem1.videoOrImage)
        {
            if (((GridItemHolder)itemholder).mVideoInfo != null)
            {
                ((GridItemHolder)itemholder).mVideoInfo.setVisibility(0);
            }
            if (((GridItemHolder)itemholder).mFileDuration != null)
            {
                ((GridItemHolder)itemholder).mFileDuration.setText(TimeUtils.convertSecToTimeString(((VideoItem)mediaitem1).duration / 1000L, false));
            }
        } else
        if (((GridItemHolder)itemholder).mVideoInfo != null)
        {
            ((GridItemHolder)itemholder).mVideoInfo.setVisibility(8);
        }
        if (((GridItemHolder)itemholder).mDownloadIcon != null)
        {
            ((GridItemHolder)itemholder).mDownloadIcon.setVisibility(8);
        }
        if (ViewManager.getViewStatus() == 4)
        {
            Hashtable hashtable1 = mUpDownloadEngine.getDownloadStates();
            String s6;
            if (mediaitem1.uri == null)
            {
                s6 = null;
            } else
            {
                s6 = mediaitem1.uri.toString();
            }
            if (hashtable1 != null && (hashtable1.containsKey(s6) || hashtable1.containsKey(mediaitem1.title)))
            {
                com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask1 = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)hashtable1.get(s6);
                if (downloadtask1 == null)
                {
                    downloadtask1 = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)hashtable1.get(mediaitem1.title);
                }
                fillDownloadIcon(itemholder, (int)downloadtask1.status);
                if (((GridItemHolder)itemholder).mDownloadIcon != null)
                {
                    ((GridItemHolder)itemholder).mDownloadIcon.setVisibility(0);
                    return;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        String s = ImageDataSourceHelper.getTitle(i, mDataSource);
        long l = ImageDataSourceHelper.getSize(i, mDataSource);
        long l1 = ImageDataSourceHelper.getAddedTime(i, mDataSource);
        String s1 = TimeUtils.second2String("MMM d,yyyy", l1);
        Boolean boolean1 = ImageDataSourceHelper.getMimeType(i, mDataSource);
        if (((ListItemHolder)itemholder).mFileName != null)
        {
            ((ListItemHolder)itemholder).mFileName.setText(s);
        }
        if (((ListItemHolder)itemholder).mFileSize != null)
        {
            ((ListItemHolder)itemholder).mFileSize.setText(FileUtils.formatSize(l));
        }
        if (((ListItemHolder)itemholder).mMediaType != null)
        {
            ImageView imageview = ((ListItemHolder)itemholder).mMediaType;
            UpDownloadEngine updownloadengine;
            Hashtable hashtable;
            MediaItem mediaitem;
            Uri uri;
            String s2;
            com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask;
            String as[];
            String s5;
            int j;
            if (boolean1.booleanValue())
            {
                j = 0x7f0201c0;
            } else
            {
                j = 0x7f0201b4;
            }
            imageview.setImageResource(j);
        }
        if (((ListItemHolder)itemholder).mFileDate != null && 0L != l1)
        {
            as = s1.split(" ");
            String s3 = (new StringBuilder()).append("").append(as[0]).append(" ").toString();
            String as1[] = as[1].split(",");
            String s4;
            if (as1[0].compareTo("1") == 0)
            {
                s4 = (new StringBuilder()).append(s3).append(as1[0]).append("st").toString();
            } else
            if (as1[0].compareTo("2") == 0)
            {
                s4 = (new StringBuilder()).append(s3).append(as1[0]).append("nd").toString();
            } else
            if (as1[0].compareTo("3") == 0)
            {
                s4 = (new StringBuilder()).append(s3).append(as1[0]).append("rd").toString();
            } else
            {
                s4 = (new StringBuilder()).append(s3).append(as1[0]).append("th").toString();
            }
            s5 = (new StringBuilder()).append(s4).append(",").append(as1[1]).toString();
            ((ListItemHolder)itemholder).mFileDate.setText(s5);
        }
        if (((ListItemHolder)itemholder).mDownloadStatus != null)
        {
            ((ListItemHolder)itemholder).mDownloadStatus.setVisibility(8);
        }
        if (((ListItemHolder)itemholder).mProgress != null)
        {
            ((ListItemHolder)itemholder).mProgress.setVisibility(8);
        }
        updownloadengine = mUpDownloadEngine;
        hashtable = null;
        if (updownloadengine != null)
        {
            hashtable = mUpDownloadEngine.getDownloadStates();
        }
        mediaitem = mDataSource.getDataSource().getItem(i);
        if (mediaitem != null)
        {
            uri = mediaitem.uri;
            s2 = null;
            if (uri != null)
            {
                s2 = mediaitem.uri.toString();
            }
            if (hashtable != null && (hashtable.containsKey(s2) || hashtable.containsKey(mediaitem.title)))
            {
                downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)hashtable.get(s2);
                if (downloadtask == null)
                {
                    downloadtask = (com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask)hashtable.get(mediaitem.title);
                }
                fillDownloadIcon(itemholder, (int)downloadtask.status);
                if (((ListItemHolder)itemholder).mDownloadStatus != null)
                {
                    ((ListItemHolder)itemholder).mDownloadStatus.setVisibility(0);
                    return;
                }
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    protected View getViewFromUri(Uri uri)
    {
        for (int i = getFirstVisiblePosition(); i < getLastVisiblePosition(); i++)
        {
            int j = i - getFirstVisiblePosition();
            if (j < 0)
            {
                continue;
            }
            View view = getChildAt(j);
            if (view == null)
            {
                continue;
            }
            Uri uri1 = mDataSource.getDataSource().getItem(i).uri;
            if (uri1 != null && uri.equals(uri1))
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
        }
        isGridView = true;
        mRootView = viewgroup;
        mEmptyText = (TextView)mRootView.findViewById(0x7f0900a5);
        if (isGridView)
        {
            initDefault();
        }
        mListAdapter = new RemoteListAdapter(mContext);
        changeGridLayoutParams();
        setAdapter(mListAdapter);
        construct(mContext);
    }

    com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.makeDownloadTask(i);
        }
    }

    protected void onListCountChanged(int i, int j)
    {
        if (j == 0)
        {
            if (j == i);
        }
    }

    public void onPause()
    {
        super.onPause();
        isActive = false;
    }

    public void onResume()
    {
        super.onResume();
        isActive = true;
    }

    protected void updownloadExist()
    {
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(mContext)).setTitle(0x7f0b017e).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

            final RemoteListView this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                addDownloadByTask(mNeedToDownloadTask);
                updateDownloadStatus();
            }

            
            {
                this$0 = RemoteListView.this;
                super();
            }
        }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

            final RemoteListView this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = RemoteListView.this;
                super();
            }
        }).setCancelable(false).create();
        if (alertdialog != null)
        {
            alertdialog.show();
        }
    }

    protected void updownloadFinish(int i, Object obj)
    {
        Log.d("FENG", (new StringBuilder()).append("FENG remote updownloadFinish IN arg1 = ").append(i).toString());
        invalidateViews();
        super.updownloadFinish(i, obj);
    }

    protected void updownloadProgress(int i, Object obj)
    {
        Log.d("RemoteListView", (new StringBuilder()).append("cxj remote updownloadProgress arg1 = ").append(i).append(" obj = ").append(obj).toString());
        mCurProgress = i;
        String s = (String)obj;
        if (!isGridView)
        {
            View view = getViewFromUri(Uri.parse(s));
            if (view != null)
            {
                ProgressBar progressbar = (ProgressBar)view.findViewById(0x7f0900d1);
                if (progressbar != null)
                {
                    progressbar.setVisibility(0);
                    progressbar.setProgress(mCurProgress);
                }
            }
        }
    }

    protected void updownloadStart(Object obj)
    {
        invalidateViews();
    }



}
