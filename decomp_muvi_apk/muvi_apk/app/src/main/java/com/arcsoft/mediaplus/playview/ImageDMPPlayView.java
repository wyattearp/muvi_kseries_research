// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.UploadManager;
import com.arcsoft.adk.image.IFileList;
import com.arcsoft.adk.image.PhotoViewer;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.setting.SlideShowSettingActivity;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.io.File;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayView, PlayActivity, DataSourcePlayList

public class ImageDMPPlayView extends PlayView
    implements android.widget.ViewSwitcher.ViewFactory
{

    private static final int MSG_CURRENT_INDEX_CHANGED = 1;
    protected static final int SLIDE_SHOW_MESSAGE = 1000;
    private static final String TAG = "ImageDMPPlayView";
    private long mCurID;
    private com.arcsoft.adk.image.PhotoViewer.Mode mCurMode;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

        final ImageDMPPlayView this$0;

        public void onCountChanged(int i, int j)
        {
            Log.v("ImageDMPPlayView", (new StringBuilder()).append(" onCountChanged, newCount = ").append(i).append(", oldCount = ").append(j).toString());
            if (mPlayList == null || i <= 0 || i == j) goto _L2; else goto _L1
_L1:
            int k = 0;
_L8:
            if (k >= i) goto _L4; else goto _L3
_L3:
            if (mCurID != mPlayList.getId(k)) goto _L6; else goto _L5
_L5:
            Log.d("FENG", (new StringBuilder()).append("FENG ******************* index: ").append(k).append(", mCurID: ").append(mCurID).toString());
            mPhotoViewer.setCurrentIndex(k);
_L4:
            mPhotoViewer.refresh();
_L2:
            if (mUIHandler != null)
            {
                mUIHandler.sendEmptyMessage(1);
            }
            return;
_L6:
            k++;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public void onDataChanged(int i, Property property)
        {
            if (property == Property.PROP_IMAGE_FILEPATH)
            {
                mPhotoViewer.editItem(i);
            } else
            if (property == Property.PROP_STORAGE_FULL && !mToasted)
            {
                Toast.makeText(mContext, mContext.getString(0x7f0b0198), 1).show();
                mToasted = true;
                return;
            }
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private IDataSource mDataSource;
    protected com.arcsoft.mediaplus.datasource.IDataSource.IController mDataSourceController;
    private IFileList mFileList;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final ImageDMPPlayView this$0;

        public void onClick(View view)
        {
            boolean flag = true;
            resetHideControlTimer(flag);
            if (mPhotoViewer != null)
            {
                if (view == mbtnNext)
                {
                    mPhotoViewer.next();
                } else
                {
                    if (view == mbtnPrev)
                    {
                        mPhotoViewer.prev();
                        return;
                    }
                    if (view == mbtnPlay)
                    {
                        ImageDMPPlayView imagedmpplayview = ImageDMPPlayView.this;
                        if (mbSlideShow)
                        {
                            flag = false;
                        }
                        imagedmpplayview.mbSlideShow = flag;
                        if (mbSlideShow)
                        {
                            startSlideShow();
                            return;
                        } else
                        {
                            stopSlideShow();
                            return;
                        }
                    }
                }
            }
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private PhotoViewer mPhotoViewer;
    private final com.arcsoft.adk.image.PhotoViewer.PhotoViewerListener mPhotoViewerListener = new com.arcsoft.adk.image.PhotoViewer.PhotoViewerListener() {

        final ImageDMPPlayView this$0;

        public boolean isPreparingFilePath(int i)
        {
            return mPlayList.getCurrentFilePathByIndex(i).startsWith("http://");
        }

        public boolean isSupportZoom(int i)
        {
            return mFileList.getFilePath(i) != null;
        }

        public boolean isVideo(int i)
        {
            return isVideoFile(i);
        }

        public void onClick()
        {
            resetHideControlTimer(true);
        }

        public void onCurrentIndexChanged(int i)
        {
            if (mPlayList != null)
            {
                mPlayList.setCurrentIndex(i);
            }
            updateCurrentUI(i, false);
        }

        public void onModeChanged(com.arcsoft.adk.image.PhotoViewer.Mode mode)
        {
            if (mode == com.arcsoft.adk.image.PhotoViewer.Mode.Normal)
            {
                mCurMode = com.arcsoft.adk.image.PhotoViewer.Mode.Normal;
            } else
            if (mode == com.arcsoft.adk.image.PhotoViewer.Mode.Zoom)
            {
                mCurMode = com.arcsoft.adk.image.PhotoViewer.Mode.Zoom;
                return;
            }
        }

        public void onPrefetch(int i, int j)
        {
            if (mDataSourceController != null)
            {
                com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
                Property aproperty[] = new Property[1];
                aproperty[0] = Property.PROP_IMAGE_FILEPATH;
                icontroller.prefetch(i, j, aproperty);
            }
        }

        public void onPrefetchEx(int ai[])
        {
            if (mDataSourceController != null)
            {
                com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
                Property aproperty[] = new Property[1];
                aproperty[0] = Property.PROP_IMAGE_FILEPATH;
                icontroller.prefetchEx(ai, aproperty);
            }
        }

        public void onSliding(boolean flag)
        {
            setCenterPlayBtnVisible(flag);
        }

        public void resetControlTimer()
        {
            resetHideControlTimerEx();
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private View mPhotoViewerMask;
    private int mSlideShowEffect;
    private final Handler mSlideShowHandler = new Handler() {

        final ImageDMPPlayView this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1000 1000: default 24
        //                       1000 30;
               goto _L1 _L2
_L1:
            super.handleMessage(message);
            return;
_L2:
            if (mRootView != null)
            {
                mRootView.setKeepScreenOn(true);
            }
            if (mPlayList.getNextIndex(false) == -1)
            {
                mbSlideShow = false;
                stopSlideShow();
            }
            if (mbSlideShow)
            {
                slideNext();
                mSlideShowHandler.removeMessages(1000);
                mSlideShowHandler.sendEmptyMessageDelayed(1000, mSlideShowInterval);
            } else
            {
                mSlideShowHandler.removeMessages(1000);
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private int mSlideShowInterval;
    private ImageSwitcher mSlideShowView;
    private boolean mToasted;
    private final Handler mUIHandler = new Handler() {

        final ImageDMPPlayView this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 1: // '\001'
                updateCurrentUI(mPhotoViewer.getCurrentIndex(), true);
                break;
            }
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private UpDownloadEngine mUpDownloadEngine;
    private final Handler mUpDownloadHandler = new Handler() {

        final ImageDMPPlayView this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 1537 1539: default 36
        //                       1537 36
        //                       1538 36
        //                       1539 37;
               goto _L1 _L1 _L1 _L2
_L1:
            return;
_L2:
            if (message.arg1 == 817 || message.arg1 == 819)
            {
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[0]);
                return;
            }
            if (message.obj != null)
            {
                String s;
                if (message.obj instanceof com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)
                {
                    s = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)message.obj).request.uri;
                } else
                {
                    s = ((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)message.obj).request.uri;
                }
                if (message.arg1 == 911)
                {
                    Context context2 = mContext;
                    int k = message.arg1;
                    Object aobj1[] = new Object[1];
                    aobj1[0] = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)message.obj).filePath;
                    com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context2, 0, k, aobj1);
                    return;
                }
                if (message.arg1 == 1015)
                {
                    com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[] {
                        s
                    });
                    return;
                }
                Context context1 = mContext;
                int i;
                int j;
                Object aobj[];
                String s1;
                if (FileUtils.isLocalItem(Uri.parse(s)))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = message.arg1;
                aobj = new Object[1];
                if (message.obj instanceof com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)
                {
                    s1 = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)message.obj).request.title;
                } else
                {
                    s1 = ((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)message.obj).request.title;
                }
                aobj[0] = s1;
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context1, i, j, aobj);
                return;
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private final com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener mUpdownloadListener = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener() {

        final ImageDMPPlayView this$0;

        public void onProgress(String s, String s1, long l, long l1)
        {
            Message message = mUpDownloadHandler.obtainMessage(1538);
            int i;
            if (l1 == 0L)
            {
                i = 0;
            } else
            {
                i = (int)((100L * l) / l1);
            }
            message.arg1 = i;
            message.obj = s1;
            mUpDownloadHandler.sendMessage(message);
        }

        public void onUpDownloadFinish(String s, String s1, Object obj, int i)
        {
            if (s1 == null || obj == null)
            {
                return;
            } else
            {
                Message message = mUpDownloadHandler.obtainMessage(1539);
                message.arg1 = i;
                message.obj = obj;
                mUpDownloadHandler.sendMessage(message);
                return;
            }
        }

        public void onUpDownloadStart(String s, String s1)
        {
            Message message = mUpDownloadHandler.obtainMessage(1537);
            message.obj = s1;
            mUpDownloadHandler.sendMessage(message);
        }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
    };
    private boolean mbSlideShow;
    private ImageView mbtnNext;
    private ImageView mbtnPlay;
    private ImageView mbtnPrev;
    private Button mbtnZoomIn;
    private Button mbtnZoomOut;

    public ImageDMPPlayView(Context context, ViewGroup viewgroup)
    {
        super(context, viewgroup);
        mFileList = null;
        mDataSource = null;
        mDataSourceController = null;
        mPhotoViewer = null;
        mPhotoViewerMask = null;
        mSlideShowView = null;
        mbtnPlay = null;
        mbtnPrev = null;
        mbtnNext = null;
        mbtnZoomIn = null;
        mbtnZoomOut = null;
        mbSlideShow = false;
        mSlideShowEffect = 1;
        mSlideShowInterval = 1000;
        mCurMode = com.arcsoft.adk.image.PhotoViewer.Mode.Normal;
        mToasted = false;
        mCurID = 0L;
        mUpDownloadEngine = null;
    }

    private String getImageDownloadPath(int i)
    {
        MediaItem mediaitem = mDataSource.getItem(i);
        String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, mediaitem.title, mediaitem.strDecodePath, MyUPnPUtils.getItemResource(RemoteDBMgr.instance().getCurrentServer(), mDataSource.getRemoteItemUUID(i)));
        if (s != null)
        {
            File file = new File(s);
            if (file == null || !file.exists())
            {
                s = null;
            }
        }
        return s;
    }

    private Animation getInAnimation(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 30
    //                   1 49
    //                   2 63;
           goto _L1 _L2 _L3 _L4
_L1:
        return null;
_L2:
        Animation animation = AnimationUtils.loadAnimation(mContext, 0x10a0000);
_L6:
        animation.setDuration(500L);
        return animation;
_L3:
        animation = AnimationUtils.loadAnimation(mContext, 0x7f04000e);
        continue; /* Loop/switch isn't completed */
_L4:
        animation = AnimationUtils.loadAnimation(mContext, 0x7f04000f);
        if (true) goto _L6; else goto _L5
_L5:
    }

    private Animation getOutAnimation(int i)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 30
    //                   1 50
    //                   2 64;
           goto _L1 _L2 _L3 _L4
_L1:
        return null;
_L2:
        Animation animation = AnimationUtils.loadAnimation(mContext, 0x10a0001);
_L6:
        animation.setDuration(500L);
        return animation;
_L3:
        animation = AnimationUtils.loadAnimation(mContext, 0x7f04000d);
        continue; /* Loop/switch isn't completed */
_L4:
        animation = AnimationUtils.loadAnimation(mContext, 0x7f04000b);
        if (true) goto _L6; else goto _L5
_L5:
    }

    private boolean isVideoFile(int i)
    {
        return ((PlayActivity)mContext).isVideoFile(i);
    }

    private void resumeDataSource()
    {
        boolean flag = true;
        DataSourcePlayList datasourceplaylist = mPlayList;
        IDataSource idatasource = null;
        if (datasourceplaylist != null)
        {
            idatasource = mPlayList.getDataSource();
        }
        if (idatasource != null && mDataSourceController == null)
        {
            mDataSourceController = idatasource.getController();
            mDataSourceController.setEnable(flag);
            mDataSourceController.resume();
            int i = Settings.instance().getDefaultContentAccess();
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
            if (i != 0)
            {
                flag = false;
            }
            icontroller.setResourceType(flag);
        }
    }

    private void setCenterPlayBtnVisible(boolean flag)
    {
        ((PlayActivity)mContext).setCenterPlayBtnVisible(flag);
        Log.e("jj", (new StringBuilder()).append("bSliding ================ ").append(flag).toString());
    }

    private void setPhotoViewerVisible(boolean flag)
    {
        if (flag)
        {
            mPhotoViewerMask.setBackgroundColor(0);
            mPhotoViewerMask.setOnTouchListener(null);
            return;
        } else
        {
            mPhotoViewerMask.setBackgroundColor(0xff000000);
            mPhotoViewerMask.setOnTouchListener(new android.view.View.OnTouchListener() {

                final ImageDMPPlayView this$0;

                public boolean onTouch(View view, MotionEvent motionevent)
                {
                    return true;
                }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
            });
            return;
        }
    }

    private void stopDataSource()
    {
        if (mDataSourceController != null)
        {
            mDataSourceController.pause();
            mDataSourceController.setEnable(false);
            mDataSourceController.release();
            mDataSourceController = null;
        }
    }

    private void updateCurrentUI(int i, boolean flag)
    {
        ((PlayActivity)mContext).updateCurrentUI(i, flag);
        if (mPlayList != null && mPlayList.getCount() > 0)
        {
            mCurID = mPlayList.getId(mPlayList.getCurrentIndex());
        }
    }

    public void addEditedPhoto(int i)
    {
        if (mPhotoViewer != null)
        {
            mPhotoViewer.refresh();
            int j = mPhotoViewer.getCurrentIndex();
            Log.e("FENG", (new StringBuilder()).append("FENG curIndex = ").append(j).toString());
            mPhotoViewer.setCurrentIndex(j + i);
            if (mUIHandler != null)
            {
                mUIHandler.sendEmptyMessage(1);
                return;
            }
        }
    }

    public void addUpdownload(boolean flag)
    {
        if (NetworkUtil.getLocalIpViaWiFi(mContext) != null) goto _L2; else goto _L1
_L1:
        com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showDefaultError(mContext, 804);
_L4:
        return;
_L2:
        int i;
        if (mUpDownloadEngine == null || mDataSource == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        i = mPlayList.getCurrentIndex();
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.UploadTask uploadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.UploadTask();
        uploadtask.mediaClass = 3L;
        uploadtask.dms_uuid = Settings.instance().getDefaultDMSUDN();
        if (uploadtask.dms_uuid == null)
        {
            Toast.makeText(mContext, mContext.getString(0x7f0b00c9), 0).show();
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

    public void cancelAllUpdownload()
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
    }

    public void cancelUpdownload(boolean flag)
    {
        if (mUpDownloadEngine != null && mPlayList != null)
        {
            String s = Settings.instance().getDefaultDMSUDN();
            Uri uri = mPlayList.getUri(mPlayList.getCurrentIndex());
            UpDownloadEngine updownloadengine = mUpDownloadEngine;
            int i;
            if (flag)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            updownloadengine.cancelTask(i, s, uri);
        }
    }

    public void deleteCurrentFileFromPV()
    {
        if (mPhotoViewer != null)
        {
            mPhotoViewer.removeItem(mPhotoViewer.getCurrentIndex());
            ((PlayActivity)mContext).cancelDeletingWaitDialog();
            if (mUIHandler != null)
            {
                mUIHandler.sendEmptyMessage(1);
                return;
            }
        }
    }

    public View getControlView()
    {
        return mRootView.findViewById(0x7f090074);
    }

    public int getCurrentIndexByPV()
    {
        if (mPhotoViewer == null)
        {
            return -1;
        } else
        {
            return mPhotoViewer.getCurrentIndex();
        }
    }

    public Drawable getDefaultDrawable()
    {
        return mContext.getResources().getDrawable(0x7f0201c3);
    }

    public Bitmap getDisplayBitmap(Point point)
    {
        Bitmap bitmap = mPhotoViewer.getCurrentBitmap();
        int i = -1;
        int j = -1;
        if (bitmap != null)
        {
            i = bitmap.getWidth();
            j = bitmap.getHeight();
        }
        if (point != null)
        {
            point.x = i;
            point.y = j;
        }
        return bitmap;
    }

    public Rect getDisplayLayout()
    {
        return new Rect();
    }

    protected int getLayoutID()
    {
        return 0x7f030019;
    }

    public com.arcsoft.adk.image.PhotoViewer.Mode getPhotoViewMode()
    {
        return mCurMode;
    }

    public int getUpdownloadState(boolean flag)
    {
        int i;
        if (mUpDownloadEngine == null || mPlayList == null)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        i = mPlayList.getCurrentIndex();
        if (i >= 0) goto _L2; else goto _L1
_L1:
        byte byte0 = -1;
_L4:
        return byte0;
_L2:
        Uri uri = mPlayList.getUri(i);
        String s = (String)mDataSource.getObjectProp(i, Property.PROP_MIMETYPE, "image/*");
        if (flag && !DLNA.instance().getUploadManager().matchDLNAUploadProfile(Settings.instance().getDefaultDMSUDN(), uri, s, Integer.valueOf(-1)))
        {
            return -1;
        }
        if (!flag && isDMSContainDtcp(uri, i))
        {
            return -1;
        }
        UpDownloadEngine updownloadengine = mUpDownloadEngine;
        int j;
        AbsTaskItem abstaskitem;
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        abstaskitem = updownloadengine.getTaskItem(j, uri);
        byte0 = 0;
        if (abstaskitem == null) goto _L4; else goto _L3
_L3:
        return abstaskitem.state;
        return -1;
    }

    public boolean isActive()
    {
        return mPhotoViewer.getMode() == com.arcsoft.adk.image.PhotoViewer.Mode.Zoom;
    }

    protected boolean isSlideView()
    {
        return mbSlideShow;
    }

    public View makeView()
    {
        ImageView imageview = new ImageView(mContext);
        imageview.setBackgroundColor(0xff000000);
        imageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        imageview.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        return imageview;
    }

    void onActivityPause()
    {
        if (mPlayList != null && mPlayList.getCount() > 0)
        {
            mCurID = mPlayList.getId(mPlayList.getCurrentIndex());
        }
        if (mPhotoViewer.isResume())
        {
            mPhotoViewer.pause();
        }
        mPhotoViewer.setEnabled(false);
        stopDataSource();
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(mUpdownloadListener);
            mUpDownloadEngine = null;
        }
    }

    void onActivityResume()
    {
        resumeDataSource();
        if (!mPhotoViewer.isEnabled())
        {
            mPhotoViewer.setEnabled(true);
        }
        mPhotoViewer.resume();
        if (mbSlideShow)
        {
            startSlideShow();
        }
    }

    void onActivityStart()
    {
    }

    void onActivityStop()
    {
        mSlideShowHandler.removeMessages(1000);
    }

    protected void onDestoryRootView(View view)
    {
        view.destroyDrawingCache();
    }

    protected void onInit()
    {
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (isActive()) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 21 22: default 32
    //                   21 34
    //                   22 59;
           goto _L2 _L3 _L4
_L2:
        return false;
_L3:
        if (mbtnPrev.isEnabled())
        {
            mbtnPrev.setPressed(true);
            resetHideControlTimer(true);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mbtnNext.isEnabled())
        {
            mbtnNext.setPressed(true);
            resetHideControlTimer(true);
            return true;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if (isActive()) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 21 22: default 32
    //                   21 34
    //                   22 64;
           goto _L2 _L3 _L4
_L2:
        return false;
_L3:
        if (mbtnPrev.isEnabled())
        {
            boolean flag1 = mbtnPrev.performClick();
            mbtnPrev.setPressed(false);
            return flag1;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (mbtnNext.isEnabled())
        {
            boolean flag = mbtnNext.performClick();
            mbtnNext.setPressed(false);
            return flag;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    protected void onSetPlayList(DataSourcePlayList datasourceplaylist)
    {
        if (mDataSource != null)
        {
            mDataSource.unregisterOnDataChangeListener(mDataChangeListener);
            mDataSource = null;
        }
        if (datasourceplaylist != null && datasourceplaylist.getDataSource() != null)
        {
            mDataSource = mPlayList.getDataSource();
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
            if (mPlayList != null && mPlayList.getCount() > 0)
            {
                mCurID = mPlayList.getId(mPlayList.getCurrentIndex());
            }
            mFileList = new IFileList() {

                final ImageDMPPlayView this$0;

                public int getCount()
                {
                    if (mPlayList == null)
                    {
                        return 0;
                    } else
                    {
                        return mPlayList.getCount();
                    }
                }

                public String getFilePath(int i)
                {
                    IDataSource idatasource = mDataSource;
                    String s = null;
                    if (idatasource != null)
                    {
                        s = null;
                        if (true)
                        {
                            s = mDataSource.getStringProp(i, Property.PROP_IMAGE_FILEPATH, null);
                        }
                    }
                    return s;
                }

            
            {
                this$0 = ImageDMPPlayView.this;
                super();
            }
            };
        }
    }

    void onStart()
    {
        resumeDataSource();
        mPhotoViewer.setCurrentIndex(mPlayList.getCurrentIndex());
        mPhotoViewer.setVisibility(0);
        mPhotoViewer.setEnabled(true);
        setPhotoViewerVisible(false);
    }

    void onStarted(long l)
    {
        onStopCanceled();
    }

    void onStop()
    {
        Log.d("ImageDMPPlayView", (new StringBuilder()).append("startDismiss pv index = ").append(mPhotoViewer.getCurrentIndex()).toString());
        Log.d("ImageDMPPlayView", (new StringBuilder()).append("startDismiss current bitmap = ").append(mPhotoViewer.getCurrentBitmap()).toString());
        mPlayList.setCurrentIndex(mPhotoViewer.getCurrentIndex());
        if (mPhotoViewer.isResume())
        {
            mPhotoViewer.pause();
        }
        setPhotoViewerVisible(false);
        resetHideControlTimer(false);
    }

    void onStopCanceled()
    {
        if (mPhotoViewer.isEnabled())
        {
            mPhotoViewer.resume();
        }
        setPhotoViewerVisible(true);
        resetHideControlTimer(true);
    }

    PlayView.PlaySession onStopped(boolean flag)
    {
        stopDataSource();
        PlayView.PlaySession playsession = new PlayView.PlaySession(this, 0, 0L);
        playsession.index = mPhotoViewer.getCurrentIndex();
        mPhotoViewer.setEnabled(false);
        mPhotoViewer.setVisibility(8);
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(mUpdownloadListener);
            mUpDownloadEngine = null;
        }
        return playsession;
    }

    protected boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag;
        flag = true;
        resetHideControlTimer(flag);
        if (view != mbtnZoomIn) goto _L2; else goto _L1
_L1:
        PhotoViewer photoviewer1 = mPhotoViewer;
        if (motionevent.getAction() != flag)
        {
            flag = false;
        }
        photoviewer1.zoomIn(flag);
_L4:
        return super.onTouch(view, motionevent);
_L2:
        if (view == mbtnZoomOut)
        {
            PhotoViewer photoviewer = mPhotoViewer;
            if (motionevent.getAction() != flag)
            {
                flag = false;
            }
            photoviewer.zoomOut(flag);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onUninit()
    {
        mPhotoViewer.setPhotoViewerListener(null);
        mPhotoViewer.recycle();
        mPlayList = null;
        mSlideShowHandler.removeMessages(1000);
    }

    public void prepareOptionMenu(Menu menu)
    {
        menu.add(0, 501, 0, 0x7f0b0109).setIcon(0x7f020199);
    }

    public void refreshThumbnail()
    {
    }

    protected void resetHideControlTimer(boolean flag)
    {
        if (((PlayActivity)mContext).isControlBarShown())
        {
            mHandler.removeMessages(1281);
            mHandler.removeMessages(1280);
            mHandler.sendEmptyMessage(1280);
            return;
        } else
        {
            super.resetHideControlTimer(flag);
            return;
        }
    }

    public void resetHideControlTimerEx()
    {
        if (((PlayActivity)mContext).isControlBarShown())
        {
            super.resetHideControlTimer(true);
        }
    }

    protected boolean samePortLandLayout()
    {
        return true;
    }

    public void setUpDownloadEngine(UpDownloadEngine updownloadengine)
    {
        mUpDownloadEngine = updownloadengine;
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.registerUpDownloadListener(mUpdownloadListener);
        }
    }

    protected void setupViewEvents()
    {
        View view = getRootView();
        mPhotoViewer = (PhotoViewer)view.findViewById(0x7f090084);
        if (mPhotoViewer != null)
        {
            mPhotoViewer.setLoadingIcon(0x7f0201f6);
            mPhotoViewer.setErrorIcon(0x7f0201f4);
            mPhotoViewer.setPhotoViewerListener(mPhotoViewerListener);
            Display display = ((WindowManager)mContext.getSystemService("window")).getDefaultDisplay();
            mPhotoViewer.setDisplaySize(display.getWidth(), display.getHeight());
            mPhotoViewer.init();
        }
        mSlideShowView = (ImageSwitcher)view.findViewById(0x7f090085);
        if (mSlideShowView != null)
        {
            mSlideShowView.setFactory(this);
        }
        mbtnPlay = (ImageView)view.findViewById(0x7f090076);
        if (mbtnPlay != null)
        {
            mbtnPlay.setOnClickListener(mOnClickListener);
            mbtnPlay.setEnabled(true);
        }
        mbtnPrev = (ImageView)view.findViewById(0x7f090075);
        if (mbtnPrev != null)
        {
            mbtnPrev.setOnClickListener(mOnClickListener);
        }
        mbtnNext = (ImageView)view.findViewById(0x7f090077);
        if (mbtnNext != null)
        {
            mbtnNext.setOnClickListener(mOnClickListener);
        }
        mbtnZoomIn = (Button)view.findViewById(0x7f090078);
        mbtnZoomOut = (Button)view.findViewById(0x7f090079);
        if (mbtnZoomIn != null && mbtnZoomOut != null)
        {
            mbtnZoomIn.setOnTouchListener(mOnTouchListener);
            mbtnZoomOut.setOnTouchListener(mOnTouchListener);
            mbtnZoomIn.setVisibility(8);
            mbtnZoomOut.setVisibility(8);
        }
        mPhotoViewerMask = view.findViewById(0x7f090086);
    }

    protected void slideNext()
    {
        mPhotoViewer.next();
        mSlideShowView.setImageDrawable(new BitmapDrawable(mPhotoViewer.getCurrentBitmap()));
    }

    protected void startSlideShow()
    {
        mSlideShowEffect = SlideShowSettingActivity.getSlideShowEffect(mContext);
        mSlideShowInterval = SlideShowSettingActivity.getSlideShowInterval(mContext);
        mPhotoViewer.setVisibility(8);
        mSlideShowView.setVisibility(0);
        mSlideShowView.setInAnimation(getInAnimation(mSlideShowEffect));
        mSlideShowView.setOutAnimation(getOutAnimation(mSlideShowEffect));
        mSlideShowView.setImageDrawable(new BitmapDrawable(mPhotoViewer.getCurrentBitmap()));
        mSlideShowHandler.removeMessages(1000);
        mSlideShowHandler.sendEmptyMessageDelayed(1000, mSlideShowInterval);
    }

    protected void stopSlideShow()
    {
        mSlideShowHandler.removeMessages(1000);
        mSlideShowView.setVisibility(8);
        mPhotoViewer.setVisibility(0);
    }

    public void svcDisable()
    {
        if (mPhotoViewer != null)
        {
            mPhotoViewer.svcDisable();
        }
    }

    public void svcReady()
    {
        if (mPhotoViewer != null)
        {
            mPhotoViewer.setFileList(mFileList);
            mPhotoViewer.svcReady();
        }
    }

    void updateBtnsEnableStatus(boolean flag)
    {
        boolean flag1 = true;
        Log.v("ImageDMPPlayView", (new StringBuilder()).append("updateBtnsEnableStatus(), forceDisable = ").append(flag).toString());
        boolean flag2;
        if (flag == flag1)
        {
            flag2 = false;
        } else
        {
            flag2 = flag1;
        }
        if (mPlayList == null)
        {
            return;
        }
        if (mbSlideShow)
        {
            flag2 = false;
        }
        ImageView imageview = mbtnPlay;
        int i;
        ImageView imageview1;
        boolean flag3;
        ImageView imageview2;
        boolean flag4;
        PlayActivity playactivity;
        if (mbSlideShow)
        {
            i = 0x7f02006d;
        } else
        {
            i = 0x7f020070;
        }
        imageview.setImageResource(i);
        imageview1 = mbtnPrev;
        if (flag2)
        {
            if (mPlayList.getPrevIndex(false) != -1)
            {
                flag3 = flag1;
            } else
            {
                flag3 = false;
            }
        } else
        {
            flag3 = false;
        }
        setBtnEnable(imageview1, flag3);
        imageview2 = mbtnNext;
        if (flag2)
        {
            if (mPlayList.getNextIndex(false) != -1)
            {
                flag4 = flag1;
            } else
            {
                flag4 = false;
            }
        } else
        {
            flag4 = false;
        }
        setBtnEnable(imageview2, flag4);
        setBtnEnable(mbtnZoomIn, flag2);
        setBtnEnable(mbtnZoomOut, flag2);
        mbtnZoomIn.setVisibility(8);
        mbtnZoomOut.setVisibility(8);
        playactivity = (PlayActivity)mContext;
        if (mbSlideShow)
        {
            flag1 = false;
        }
        playactivity.setPushtoVisable(flag1);
    }



/*
    static boolean access$002(ImageDMPPlayView imagedmpplayview, boolean flag)
    {
        imagedmpplayview.mbSlideShow = flag;
        return flag;
    }

*/









/*
    static boolean access$1502(ImageDMPPlayView imagedmpplayview, boolean flag)
    {
        imagedmpplayview.mToasted = flag;
        return flag;
    }

*/





/*
    static com.arcsoft.adk.image.PhotoViewer.Mode access$402(ImageDMPPlayView imagedmpplayview, com.arcsoft.adk.image.PhotoViewer.Mode mode)
    {
        imagedmpplayview.mCurMode = mode;
        return mode;
    }

*/





}
