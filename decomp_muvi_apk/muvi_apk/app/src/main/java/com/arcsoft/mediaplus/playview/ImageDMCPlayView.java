// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.UploadManager;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.playengine.DMCPlayer;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.setting.SlideShowSettingActivity;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.util.Date;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayView, DataSourcePlayList, PlayActivity

public class ImageDMCPlayView extends PlayView
{
    private class ImageTagInfo
    {

        Bitmap bitmap;
        int index;
        final ImageDMCPlayView this$0;

        private ImageTagInfo()
        {
            this$0 = ImageDMCPlayView.this;
            super();
            index = -1;
            bitmap = null;
        }
    }


    protected static final int SLIDE_SHOW_MESSAGE = 1000;
    private static final String TAG = "ImageDMCPlayView";
    private ImageView mBtnNext;
    private ImageView mBtnPrev;
    private TextView mDMRName;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

        final ImageDMCPlayView this$0;

        public void onCountChanged(int i, int j)
        {
            if (i > 0)
            {
                prefetch(mPlayList.getCurrentIndex());
            }
        }

        public void onDataChanged(int i, Property property)
        {
            if (property == PictureDataSource.PROP_BITMAP)
            {
                Integer integer = (Integer)mDefaultPic.getTag();
                if (integer != null && integer.intValue() == i)
                {
                    setImageInfo(i);
                }
            }
        }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
    };
    private com.arcsoft.mediaplus.datasource.IDataSource.IController mDataSourceController;
    private ImageView mDefaultPic;
    private final Handler mHandler = new Handler() {

        final ImageDMCPlayView this$0;

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
                this$0 = ImageDMCPlayView.this;
                super();
            }
    };
    private PictureDataSource mPictureDataSource;
    private DMCPlayer mPlayer;
    private final com.arcsoft.mediaplus.playengine.IPlayer.IPlayerListener mPlayerListener = new com.arcsoft.mediaplus.playengine.IPlayer.IPlayerListener() {

        final ImageDMCPlayView this$0;

        public void onBuffering()
        {
            mRootView.findViewById(0x7f090083).setVisibility(0);
        }

        public void onCompleted()
        {
            mRootView.findViewById(0x7f090083).setVisibility(8);
        }

        public void onError(com.arcsoft.mediaplus.playengine.IPlayer.PlayerError playererror)
        {
            static class _cls9
            {

                static final int $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[];

                static 
                {
                    $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError = new int[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.values().length];
                    try
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_RENDER_DISCONNECTED.ordinal()] = 1;
                    }
                    catch (NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_UNSUPPORT.ordinal()] = 2;
                    }
                    catch (NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_OPENFILE.ordinal()] = 3;
                    }
                    catch (NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_PLAY.ordinal()] = 4;
                    }
                    catch (NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$arcsoft$mediaplus$playengine$IPlayer$PlayerError[com.arcsoft.mediaplus.playengine.IPlayer.PlayerError.ERROR_PLAYLIST_URI_NULL.ordinal()] = 5;
                    }
                    catch (NoSuchFieldError nosuchfielderror4)
                    {
                        return;
                    }
                }
            }

            switch (_cls9..SwitchMap.com.arcsoft.mediaplus.playengine.IPlayer.PlayerError[playererror.ordinal()])
            {
            default:
                return;

            case 1: // '\001'
                requestQuit();
                return;

            case 2: // '\002'
                Toast.makeText(mContext, 0x7f0b005d, 0).show();
                return;

            case 3: // '\003'
            case 4: // '\004'
                Toast.makeText(mContext, 0x7f0b005e, 0).show();
                return;

            case 5: // '\005'
                Toast.makeText(mContext, 0x7f0b005e, 0).show();
                break;
            }
            requestQuit();
        }

        public void onMute(boolean flag)
        {
        }

        public void onPaused()
        {
            mRootView.findViewById(0x7f090083).setVisibility(8);
        }

        public void onPlayIndexChanged(int i)
        {
            if (mPictureDataSource == null)
            {
                return;
            }
            if (mDataSourceController != null)
            {
                prefetch(i);
            }
            setImageInfo(i);
            updateBtnsEnableStatus(false);
        }

        public void onPlayStarted()
        {
            mRootView.findViewById(0x7f090083).setVisibility(8);
            mSlideShowHandler.sendEmptyMessageDelayed(1000, mSlideShowInterval);
        }

        public void onProgressChanged(long l, long l1)
        {
        }

        public void onSeeked()
        {
        }

        public void onStopped()
        {
            mRootView.findViewById(0x7f090083).setVisibility(8);
        }

        public void onVolumeChanged(int i)
        {
        }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
    };
    private int mSlideShowEffect;
    private final Handler mSlideShowHandler = new Handler() {

        final ImageDMCPlayView this$0;

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
                mPlayer.playnext();
            }
            mSlideShowHandler.removeMessages(1000);
            if (true) goto _L1; else goto _L3
_L3:
        }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
    };
    private int mSlideShowInterval;
    private TextView mTextDate;
    private TextView mTextDateHead;
    private TextView mTextSize;
    private TextView mTextSizeHead;
    private TextView mTextTitle;
    private TextView mTextTitleHead;
    private UpDownloadEngine mUpDownloadEngine;
    private final com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener mUpdownloadListener = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener() {

        final ImageDMCPlayView this$0;

        public void onProgress(String s, String s1, long l, long l1)
        {
            Message message = mHandler.obtainMessage(1538);
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
            mHandler.sendMessage(message);
        }

        public void onUpDownloadFinish(String s, String s1, Object obj, int i)
        {
            if (s1 == null || obj == null)
            {
                return;
            } else
            {
                Message message = mHandler.obtainMessage(1539);
                message.arg1 = i;
                message.obj = obj;
                mHandler.sendMessage(message);
                return;
            }
        }

        public void onUpDownloadStart(String s, String s1)
        {
            Message message = mHandler.obtainMessage(1537);
            message.obj = s1;
            mHandler.sendMessage(message);
        }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
    };
    private boolean mbSlideShow;
    private ImageView mbtnPlay;

    public ImageDMCPlayView(Context context, ViewGroup viewgroup)
    {
        super(context, viewgroup);
        mbtnPlay = null;
        mBtnPrev = null;
        mBtnNext = null;
        mTextTitle = null;
        mTextDate = null;
        mTextSize = null;
        mTextTitleHead = null;
        mTextSizeHead = null;
        mTextDateHead = null;
        mDMRName = null;
        mDefaultPic = null;
        mPictureDataSource = null;
        mDataSourceController = null;
        mPlayer = null;
        mbSlideShow = false;
        mSlideShowEffect = 1;
        mSlideShowInterval = 1000;
        mUpDownloadEngine = null;
    }

    private void initPictureDatasource(DataSourcePlayList datasourceplaylist)
    {
        if (mPictureDataSource != null)
        {
            mPictureDataSource.unregisterOnDataChangeListener(mDataChangeListener);
            mPictureDataSource.unInit();
            mDataSourceController = null;
            mPictureDataSource = null;
        }
        if (datasourceplaylist != null && datasourceplaylist.getDataSource() != null)
        {
            mPictureDataSource = new PictureDataSource(datasourceplaylist.getDataSource(), OEMConfig.DMC_DECODEPARAM, true);
            mPictureDataSource.init();
            mPictureDataSource.registerOnDataChangeListener(mDataChangeListener);
        }
    }

    private void initPlayerPlayList()
    {
        if (mPlayList == null || mPlayer == null)
        {
            return;
        } else
        {
            mPlayer.setPlaylist(mPlayList);
            mPlayer.setPlayerListener(mPlayerListener);
            return;
        }
    }

    private void prefetch(int i)
    {
        if (mDataSourceController == null)
        {
            return;
        } else
        {
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSourceController;
            int j = i - 1;
            int k = i + 1;
            Property aproperty[] = new Property[1];
            aproperty[0] = PictureDataSource.PROP_BITMAP;
            icontroller.prefetch(j, k, aproperty);
            return;
        }
    }

    private void resumeDataSource()
    {
        if (mPictureDataSource != null && mDataSourceController == null)
        {
            mDataSourceController = mPictureDataSource.getController();
            mDataSourceController.setEnable(true);
            mDataSourceController.resume();
        }
    }

    private void setImageInfo(int i)
    {
        if (i == -1)
        {
            mDefaultPic.setVisibility(4);
            mDefaultPic.setTag(null);
            mTextTitle.setText("");
            mTextSize.setText("");
            mTextDate.setText("");
            mDMRName.setText("");
            mTextTitleHead.setVisibility(8);
            mTextSizeHead.setVisibility(8);
            mTextDateHead.setVisibility(8);
            mTextTitle.setVisibility(8);
            mTextSize.setVisibility(8);
            mTextDate.setVisibility(8);
        } else
        if (mPictureDataSource != null)
        {
            mDefaultPic.setVisibility(0);
            mDefaultPic.setTag(Integer.valueOf(i));
            String s = mPictureDataSource.getStringProp(i, Property.PROP_TITLE, null);
            if (s != null && !s.equalsIgnoreCase(""))
            {
                mTextTitle.setText(s);
                mTextTitle.setVisibility(0);
                mTextTitleHead.setVisibility(0);
            } else
            {
                mTextTitleHead.setVisibility(8);
                mTextTitle.setVisibility(8);
            }
            if (((PlayActivity)mContext).isLocalContent())
            {
                long l1 = mPictureDataSource.getLongProp(i, Property.PROP_MODIFIED_TIME, 0L);
                int j = l1 != 0L;
                String s3 = null;
                if (j > 0)
                {
                    s3 = (new Date(1000L * l1)).toLocaleString();
                }
                long l;
                String s2;
                if (s3 != null && !s3.equalsIgnoreCase(""))
                {
                    mTextDate.setText(s3);
                    mTextDateHead.setVisibility(0);
                    mTextDate.setVisibility(0);
                } else
                {
                    mTextDateHead.setVisibility(8);
                    mTextDate.setVisibility(8);
                }
            } else
            {
                String s1 = mPictureDataSource.getStringProp(i, Property.PROP_MODIFIED_TIME, null);
                if (s1 != null && !s1.equalsIgnoreCase(""))
                {
                    mTextDate.setText(s1);
                    mTextDateHead.setVisibility(0);
                    mTextDate.setVisibility(0);
                } else
                {
                    mTextDateHead.setVisibility(8);
                    mTextDate.setVisibility(8);
                }
            }
            l = mPictureDataSource.getLongProp(i, Property.PROP_SIZE, 0L);
            if (l > 0L)
            {
                mTextSize.setText(FileUtils.formatSize(l));
                mTextSize.setVisibility(0);
                mTextSizeHead.setVisibility(0);
            } else
            {
                mTextSizeHead.setVisibility(8);
                mTextSize.setVisibility(8);
            }
            s2 = Settings.instance().getDefaultDMRName();
            if (s2 != null)
            {
                mDMRName.setText(s2);
                return;
            }
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

    public void addUpdownload(boolean flag)
    {
        if (NetworkUtil.getLocalIpViaWiFi(mContext) != null) goto _L2; else goto _L1
_L1:
        com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showDefaultError(mContext, 804);
_L4:
        return;
_L2:
        int i;
        if (mUpDownloadEngine == null || mPictureDataSource == null)
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
        uploadtask.mediaId = mPictureDataSource.getLongProp(i, Property.PROP_ID, -1L);
        uploadtask.title = mPictureDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri = (Uri)mPictureDataSource.getObjectProp(i, Property.PROP_URI, null);
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
        downloadtask.mediaId = mPictureDataSource.getLongProp(i, Property.PROP_ID, -1L);
        downloadtask.title = mPictureDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri1 = (Uri)mPictureDataSource.getObjectProp(i, Property.PROP_URI, null);
        String s1 = null;
        if (uri1 != null)
        {
            s1 = uri1.toString();
        }
        downloadtask.uri = s1;
        downloadtask.fileSize = mPictureDataSource.getLongProp(i, Property.PROP_SIZE, 0L);
        downloadtask.item_uuid = mPictureDataSource.getRemoteItemUUID(i);
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

    public View getControlView()
    {
        return mRootView.findViewById(0x7f090073);
    }

    public Drawable getDefaultDrawable()
    {
        return mContext.getResources().getDrawable(0x7f0201c3);
    }

    public Bitmap getDisplayBitmap(Point point)
    {
        return mPictureDataSource.getBitmap(mPlayList.getCurrentIndex(), point);
    }

    public Rect getDisplayLayout()
    {
        return new Rect();
    }

    protected int getLayoutID()
    {
        return 0x7f030018;
    }

    public int getUpdownloadState(boolean flag)
    {
        int i;
        if (mUpDownloadEngine == null || mPlayList == null)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        i = mPlayList.getCurrentIndex();
        if (i >= 0) goto _L2; else goto _L1
_L1:
        byte byte0 = -1;
_L4:
        return byte0;
_L2:
        Uri uri = mPlayList.getUri(i);
        String s = (String)mPictureDataSource.getObjectProp(i, Property.PROP_MIMETYPE, "image/*");
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
        return false;
    }

    protected boolean isSlideView()
    {
        return mbSlideShow;
    }

    void onActivityPause()
    {
        stopDataSource();
        mPlayer.setActivate(false);
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(mUpdownloadListener);
            mUpDownloadEngine = null;
        }
    }

    void onActivityResume()
    {
        resumeDataSource();
        mPlayer.setActivate(true);
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
        i;
        JVM INSTR tableswitch 21 22: default 24
    //                   21 26
    //                   22 46;
           goto _L1 _L2 _L3
_L1:
        return false;
_L2:
        if (mBtnPrev.isEnabled())
        {
            mBtnPrev.setPressed(true);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mBtnNext.isEnabled())
        {
            mBtnNext.setPressed(true);
            return true;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR tableswitch 21 22: default 24
    //                   21 26
    //                   22 56;
           goto _L1 _L2 _L3
_L1:
        return false;
_L2:
        if (mBtnPrev.isEnabled())
        {
            boolean flag1 = mBtnPrev.performClick();
            mBtnPrev.setPressed(false);
            return flag1;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mBtnNext.isEnabled())
        {
            boolean flag = mBtnNext.performClick();
            mBtnNext.setPressed(false);
            return flag;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    protected void onSetPlayList(DataSourcePlayList datasourceplaylist)
    {
        initPictureDatasource(datasourceplaylist);
        initPlayerPlayList();
        updateBtnsEnableStatus(false);
    }

    void onStart()
    {
        if (mPlayer == null)
        {
            mPlayer = new DMCPlayer();
            mPlayer.init();
            mPlayer.setRender(Settings.instance().getDefaultDMRUDN());
            mPlayer.setPlayerListener(mPlayerListener);
            mPlayer.setPlaylist(mPlayList);
        }
        resumeDataSource();
        prefetch(mPlayList.getCurrentIndex());
        setImageInfo(mPlayList.getCurrentIndex());
        updateBtnsEnableStatus(false);
    }

    void onStarted(long l)
    {
        if (mPlayer != null && mPlayList != null)
        {
            Log.d("ImageDMCPlayView", (new StringBuilder()).append("dmcplayview shown pl index = ").append(mPlayList.getCurrentIndex()).toString());
            mPlayer.play(mPlayList.getCurrentIndex(), l, com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_START_PLAY_SLIDE);
        }
        updateBtnsEnableStatus(false);
    }

    void onStop()
    {
        if (mPlayer != null)
        {
            mPlayer.pause();
        }
        updateBtnsEnableStatus(true);
    }

    void onStopCanceled()
    {
        if (mPlayer != null)
        {
            mPlayer.resume();
        }
        updateBtnsEnableStatus(false);
    }

    PlayView.PlaySession onStopped(boolean flag)
    {
        int i;
        long l;
        PlayView.PlaySession playsession;
        if (mPlayList == null)
        {
            i = 0;
        } else
        {
            i = mPlayList.getCurrentIndex();
        }
        if (mPlayer == null)
        {
            l = 0L;
        } else
        {
            l = mPlayer.getPosition();
        }
        playsession = new PlayView.PlaySession(this, i, l);
        stopDataSource();
        if (mPlayer != null)
        {
            DMCPlayer dmcplayer = mPlayer;
            com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT effect;
            if (flag)
            {
                effect = com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_STOP_PLAY_SLIDE;
            } else
            {
                effect = com.arcsoft.mediaplus.playengine.IDMCPlayEffect.EFFECT.EFFECT_STOP_PLAY_FADE;
            }
            dmcplayer.stop(effect);
            mPlayer.uninit();
            mPlayer = null;
        }
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(mUpdownloadListener);
            mUpDownloadEngine = null;
        }
        return playsession;
    }

    protected void onUninit()
    {
        onStopped(false);
        setImageInfo(-1);
        mSlideShowHandler.removeMessages(1000);
    }

    public void prepareOptionMenu(Menu menu)
    {
    }

    public void refreshThumbnail()
    {
    }

    protected void resetHideControlTimer(boolean flag)
    {
    }

    protected boolean samePortLandLayout()
    {
        return false;
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
        mbtnPlay = (ImageView)view.findViewById(0x7f090076);
        mbtnPlay.setOnClickListener(new android.view.View.OnClickListener() {

            final ImageDMCPlayView this$0;

            public void onClick(View view1)
            {
                ImageDMCPlayView imagedmcplayview = ImageDMCPlayView.this;
                boolean flag;
                if (!mbSlideShow)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                imagedmcplayview.mbSlideShow = flag;
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

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
        });
        mbtnPlay.setEnabled(true);
        mBtnPrev = (ImageView)view.findViewById(0x7f090075);
        mBtnPrev.setOnClickListener(new android.view.View.OnClickListener() {

            final ImageDMCPlayView this$0;

            public void onClick(View view1)
            {
                mPlayer.playprev();
            }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
        });
        mBtnNext = (ImageView)view.findViewById(0x7f090077);
        mBtnNext.setOnClickListener(new android.view.View.OnClickListener() {

            final ImageDMCPlayView this$0;

            public void onClick(View view1)
            {
                mPlayer.playnext();
            }

            
            {
                this$0 = ImageDMCPlayView.this;
                super();
            }
        });
        mDefaultPic = (ImageView)view.findViewById(0x7f090082);
        mTextTitle = (TextView)view.findViewById(0x7f09007d);
        mTextDate = (TextView)view.findViewById(0x7f09007f);
        mTextSize = (TextView)view.findViewById(0x7f090081);
        mTextTitleHead = (TextView)view.findViewById(0x7f09007c);
        mTextDateHead = (TextView)view.findViewById(0x7f09007e);
        mTextSizeHead = (TextView)view.findViewById(0x7f090080);
        mDMRName = (TextView)view.findViewById(0x7f090065);
        view.findViewById(0x7f090078).setVisibility(8);
        view.findViewById(0x7f090079).setVisibility(8);
        if (mPlayList != null)
        {
            setImageInfo(mPlayList.getCurrentIndex());
        }
        updateBtnsEnableStatus(false);
    }

    protected void startSlideShow()
    {
        mSlideShowEffect = SlideShowSettingActivity.getSlideShowEffect(mContext);
        mSlideShowInterval = SlideShowSettingActivity.getSlideShowInterval(mContext);
        mSlideShowHandler.removeMessages(1000);
        mSlideShowHandler.sendEmptyMessageDelayed(1000, mSlideShowInterval);
        updateBtnsEnableStatus(false);
    }

    protected void stopSlideShow()
    {
        mSlideShowHandler.removeMessages(1000);
        updateBtnsEnableStatus(false);
    }

    void updateBtnsEnableStatus(boolean flag)
    {
        boolean flag1 = true;
        Log.v("ImageDMCPlayView", (new StringBuilder()).append("updateBtnsEnableStatus(), forceDisable = ").append(flag).toString());
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
        imageview1 = mBtnPrev;
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
        imageview2 = mBtnNext;
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
        playactivity = (PlayActivity)mContext;
        if (mbSlideShow)
        {
            flag1 = false;
        }
        playactivity.setPushBackVisable(flag1);
    }



/*
    static boolean access$002(ImageDMCPlayView imagedmcplayview, boolean flag)
    {
        imagedmcplayview.mbSlideShow = flag;
        return flag;
    }

*/









}
