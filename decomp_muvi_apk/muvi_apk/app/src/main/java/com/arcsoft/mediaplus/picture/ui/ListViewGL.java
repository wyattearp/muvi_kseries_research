// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.adk.atv.UploadManager;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.MediaPlusApplication;
import com.arcsoft.mediaplus.MediaPlusLaucher;
import com.arcsoft.mediaplus.ViewManager;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.listview.IItemListView;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.tool.ToastMgr;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ThumbGlView

public class ListViewGL extends ThumbGlView
    implements IItemListView
{

    private boolean mAddedToView;
    Context mContext;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataBuildListener mDataBuildListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataBuildListener() {

        final ListViewGL this$0;

        public void onDataBuiltBegin()
        {
            Log.w("ThumbGlView", "=== onDataBuiltBegin === ");
            ((MediaPlusActivity)mContext).showLoadingDialog();
            onDataBuildBegine();
            setDataBuildState(1);
        }

        public void onDataBuiltFinish()
        {
            Log.w("ThumbGlView", "=== onDataBuiltFinish === ");
            if (mDataSource != null)
            {
                ((MediaPlusActivity)mContext).showHideViews();
                boolean flag;
                if (mDataSource.getCount() == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ListViewGL.this.onDataBuiltFinish(0);
                }
            }
            ((MediaPlusActivity)mContext).dismissLoadingDialog();
            setDataBuildState(0);
            setLastUpdateTime(System.currentTimeMillis());
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

        final ListViewGL this$0;

        public void onCountChanged(int i, int j)
        {
            Log.v("ThumbGlView", (new StringBuilder()).append("onCountChanged: oldCount ").append(j).append(" newCount ").append(i).toString());
            onListCountChanged(i, j);
            if (mContext == null)
            {
                return;
            }
            if (mDataSource != null)
            {
                i = mDataSource.getCount();
            }
            if (i > 0 && i != j && ViewManager.mCurrentViewStatus == 2)
            {
                ((MediaPlusActivity)mContext).refreshSelectorTitle(ListViewManager.getSelectedItemsNum());
            }
            Log.v("zdf", (new StringBuilder()).append("######## [ListViewGL] onCountChanged, oldCount = ").append(j).append(", newCount = ").append(i).toString());
            ((MediaPlusActivity)mContext).showHideViews();
            boolean flag;
            boolean flag1;
            if (j == 0 && i > 0)
            {
                sendMsgToUpdateTextStatus("", false, 0);
                ((MediaPlusActivity)mContext).refreshControlBar(0);
            } else
            if (i == 0 && j >= 0)
            {
                if (!isRemote())
                {
                    sendMsgToUpdateTextStatus(mContext.getResources().getString(0x7f0b001b), true, 0);
                }
                ((MediaPlusActivity)mContext).refreshControlBar(8);
            }
            flag = mShowMoreBtn;
            flag1 = false;
            if (flag)
            {
                if (j == 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            refresh(flag1);
            setFocusable(true);
            requestFocus();
        }

        public void onDataChanged(int i, Property property)
        {
            Log.d("ThumbGlView", (new StringBuilder()).append("onDataChanged : index = ").append(i).append(", property = ").append(property).toString());
            if (Property.PROP_THUMBNAIL_FILEPATH == property)
            {
                onDecodeThumbnail(i);
            } else
            if (Property.PROP_STORAGE_FULL == property && !mToasted && isRemote())
            {
                if (mContext != null)
                {
                    sendMsgToShowToast(mContext.getString(0x7f0b0198), 0);
                }
                mToasted = true;
                return;
            }
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };
    protected com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController mDataSourceController;
    protected DownloadFacade mDownloadFacade;
    protected final Handler mHandler = new Handler() {

        final ListViewGL this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            message.what;
            JVM INSTR tableswitch 1539 1543: default 44
        //                       1539 53
        //                       1540 44
        //                       1541 392
        //                       1542 44
        //                       1543 435;
               goto _L1 _L2 _L1 _L3 _L1 _L4
_L1:
            ListViewGL.this.handleMessage(message);
_L6:
            return;
_L2:
            Log.d("FENG", (new StringBuilder()).append("FENG local MSG_UPDOWNLOAD_FINISH IN msg.arg1 = ").append(message.arg1).toString());
            if (message.arg1 != 817 && message.arg1 != 819)
            {
                continue; /* Loop/switch isn't completed */
            }
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[0]);
            continue; /* Loop/switch isn't completed */
            if (message.obj == null) goto _L6; else goto _L5
_L5:
            if (message.arg1 == 911)
            {
                Context context2 = mContext;
                int l = message.arg1;
                Object aobj1[] = new Object[1];
                aobj1[0] = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)message.obj).filePath;
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context2, 0, l, aobj1);
            } else
            if (message.arg1 == 1015)
            {
                String s2 = Uri.decode(((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)message.obj).request.uri);
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, message.arg1, new Object[] {
                    s2
                });
            } else
            {
                Context context1 = mContext;
                int j;
                int k;
                Object aobj[];
                String s1;
                if (message.obj instanceof com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                k = message.arg1;
                aobj = new Object[1];
                if (message.obj instanceof com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)
                {
                    s1 = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)message.obj).request.title;
                } else
                {
                    s1 = ((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)message.obj).request.title;
                }
                aobj[0] = s1;
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context1, j, k, aobj);
            }
            if (mUpDownloadEngine != null && mUpDownloadEngine.getAllTaskCount() == 0)
            {
                ToastMgr.showToast(mContext, mContext.getString(0x7f0b00ae), 0);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            ListViewGL listviewgl = ListViewGL.this;
            String s = (String)message.obj;
            int i = message.arg1;
            boolean flag = false;
            if (i != 0)
            {
                flag = true;
            }
            listviewgl.updateTextStatus(s, flag);
            continue; /* Loop/switch isn't completed */
_L4:
            if (message.obj != null && (message.obj instanceof String))
            {
                Toast.makeText(mContext, (String)message.obj, 1).show();
            }
            if (true) goto _L1; else goto _L7
_L7:
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };
    private ViewGroup mRootView;
    private final com.arcsoft.adk.atv.ServerManager.IServerDLNAUploadListener mServerUploadListener = new com.arcsoft.adk.atv.ServerManager.IServerDLNAUploadListener() {

        final ListViewGL this$0;

        public void onServerGetProtocolInfo(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, int i)
        {
            if (s == Settings.instance().getDefaultDMSUDN());
        }

        public void onXGetDLNAUploadProfiles(String s, String s1, int i)
        {
            if (s == Settings.instance().getDefaultDMSUDN());
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };
    private final com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener mSettingChangedListener = new com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener() {

        final ListViewGL this$0;

        public void OnDefaultDownloadDestinationChanged(String s)
        {
        }

        public void OnDefaultRenderChanged(String s)
        {
        }

        public void OnDefaultServerChanged(String s)
        {
        }

        public void onBrowseModeChanged(boolean flag)
        {
        }

        public void onSortModeChanged(boolean flag)
        {
            mDataSourceController.sort(Property.PROP_MODIFIED_TIME, true);
        }

        public void onTVStreamingResolutionChange(boolean flag)
        {
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };
    private boolean mShowMoreBtn;
    private boolean mToasted;
    private UpDownloadEngine mUpDownloadEngine;
    private final com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener mUpdownloadListener = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener() {

        final ListViewGL this$0;

        public void onProgress(String s, String s1, long l, long l1)
        {
            ListViewGL.this.onProgress(s, s1, l, l1);
        }

        public void onUpDownloadFinish(String s, String s1, Object obj, int i)
        {
            ListViewGL.this.onUpDownloadFinish(s, s1, obj, i);
        }

        public void onUpDownloadStart(String s, String s1)
        {
            ListViewGL.this.onUpDownloadStart(s, s1);
        }

            
            {
                this$0 = ListViewGL.this;
                super();
            }
    };

    public ListViewGL(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = null;
        mDataSourceController = null;
        mUpDownloadEngine = null;
        mRootView = null;
        mShowMoreBtn = false;
        mAddedToView = false;
        mToasted = false;
        mDownloadFacade = null;
        mContext = context;
        mDownloadFacade = new DownloadFacade(context);
    }

    private boolean isDMSContainDtcp(Uri uri, int i)
    {
        if (mDataSource != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        return false;
_L2:
        if ((arraylist = mDataSource.getRemoteItemResourceDesc(i)) != null && arraylist.size() > 0)
        {
            int j = 0;
            while (j < arraylist.size()) 
            {
                String s = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)arraylist.get(j)).m_strProtocolInfo;
                if (s.contains("DTCP1HOST") && s.contains("DTCP1PORT"))
                {
                    return true;
                }
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public boolean addListView()
    {
        Log.i("ThumbGlView", (new StringBuilder()).append("addListView: mRootView = ").append(mRootView).toString());
        if ((OEMConfig.OPENGL_OPTIMIZE && !mAddedToView || !OEMConfig.OPENGL_OPTIMIZE) && mRootView != null)
        {
            mAddedToView = true;
            mRootView.addView(this, 0);
            RelativeLayout relativelayout = (RelativeLayout)(RelativeLayout)mRootView.findViewById(0x7f0900a4);
            if (relativelayout != null)
            {
                android.widget.RelativeLayout.LayoutParams layoutparams = (android.widget.RelativeLayout.LayoutParams)relativelayout.getLayoutParams();
                layoutparams.bottomMargin = 0;
                if (MediaPlusLaucher.mSupportLivingView)
                {
                    layoutparams.bottomMargin = (int)mContext.getResources().getDimension(0x7f080057);
                }
                relativelayout.setLayoutParams(layoutparams);
            }
            mRootView.findViewById(0x7f0900a5).setVisibility(0);
            Settings.instance().registerSettingChangeListener(mSettingChangedListener);
            return true;
        } else
        {
            return false;
        }
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
            if (mContext != null)
            {
                sendMsgToShowToast(mContext.getString(0x7f0b00c9), 0);
                return;
            }
        } else
        {
            uploadtask.mediaId = mDataSource.getLongProp(i, Property.PROP_ID, -1L);
            uploadtask.title = mDataSource.getStringProp(i, Property.PROP_TITLE, null);
            Uri uri = getUriByIndex(i);
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
        }
        if (true) goto _L4; else goto _L3
_L3:
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
        downloadtask.mediaClass = 3L;
        downloadtask.dms_uuid = RemoteDBMgr.instance().getCurrentServer();
        downloadtask.mediaId = mDataSource.getLongProp(i, Property.PROP_ID, -1L);
        downloadtask.title = mDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri1 = getUriByIndex(i);
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

    boolean canMark(int i)
    {
        return getRealPosition(i) >= 0;
    }

    public void cancelAllUpdownload()
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.cancelAllTask();
        }
    }

    public void cancelUpdownload(boolean flag, int i)
    {
        if (mUpDownloadEngine != null && mDataSource != null && i >= 0)
        {
            String s = Settings.instance().getDefaultDMSUDN();
            Uri uri = getUriByIndex(i);
            UpDownloadEngine updownloadengine = mUpDownloadEngine;
            int j;
            if (flag)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            updownloadengine.cancelTask(j, s, uri);
        }
    }

    public void doRotate(Configuration configuration)
    {
    }

    boolean doSelectItem(int i)
    {
        MediaItem mediaitem;
        if (i < 0 || mDataSource == null || mDataSource.getDataSource() == null)
        {
            return false;
        }
        mediaitem = mDataSource.getDataSource().getItem(getRealPosition(i));
        if (mediaitem == null) goto _L2; else goto _L1
_L1:
        if (ViewManager.getViewStatus() != 2) goto _L4; else goto _L3
_L3:
        if (canMark(i))
        {
            if (!ListViewManager.isSelectedItem(Integer.valueOf(i)))
            {
                ListViewManager.putSelectedItem(Integer.valueOf(i), mediaitem);
            } else
            {
                ListViewManager.removeSelectedItem(Integer.valueOf(i));
            }
            requestRender();
            ((MediaPlusActivity)mContext).refreshSelectorTitle(ListViewManager.getSelectedItemsNum());
        }
_L5:
        return true;
_L4:
        if (ViewManager.getViewStatus() != 4 && mOpListener != null)
        {
            mOpListener.OnItemClick(getRealPosition(i), 0L);
        }
        if (true) goto _L5; else goto _L2
_L2:
        return super.doSelectItem(i);
    }

    public IDataSource getDataSource()
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return mDataSource.getDataSource();
        }
    }

    public int getDownLoadCount()
    {
        if (mUpDownloadEngine == null)
        {
            return 0;
        } else
        {
            return mUpDownloadEngine.getAllDowloadingTaskCount();
        }
    }

    public int getTotalMediaCount()
    {
        int i;
        if (mDataSource == null)
        {
            i = 0;
        } else
        {
            i = mDataSource.getCount();
            if (!((MediaPlusActivity)mContext).isRemote())
            {
                return i - getDownLoadCount();
            }
        }
        return i;
    }

    protected UpDownloadEngine getUpDownloadEngine()
    {
        return mUpDownloadEngine;
    }

    public int getUpdownloadState(boolean flag, int i)
    {
        Uri uri;
        String s;
        if (mUpDownloadEngine == null || mDataSource == null)
        {
            break MISSING_BLOCK_LABEL_132;
        }
        uri = getUriByIndex(i);
        s = (String)mDataSource.getObjectProp(i, Property.PROP_MIMETYPE, "image/*");
        if (!flag || DLNA.instance().getUploadManager().matchDLNAUploadProfile(Settings.instance().getDefaultDMSUDN(), uri, s, Integer.valueOf(-1))) goto _L2; else goto _L1
_L1:
        byte byte0 = -1;
_L4:
        return byte0;
_L2:
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

    public Uri getUriByIndex(int i)
    {
        if (mDataSource == null)
        {
            return null;
        } else
        {
            return (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
        }
    }

    View getViewFromUri(Uri uri)
    {
        return null;
    }

    void handleMessage(Message message)
    {
    }

    public void init(ViewGroup viewgroup, boolean flag)
    {
        if (viewgroup == null)
        {
            throw new NullPointerException("rootview can not be null");
        } else
        {
            mRootView = viewgroup;
            mShowMoreBtn = flag;
            init(((MediaPlusApplication)((MediaPlusActivity)mContext).getApplication()).getLocalCacheManager());
            Log.e("ThumbGlView", "list view init");
            resume(true);
            return;
        }
    }

    public boolean isFileDownloading()
    {
        while (mUpDownloadEngine == null || mUpDownloadEngine.getAllDowloadingTaskCount() <= 0) 
        {
            return false;
        }
        return true;
    }

    public boolean isNeedConfirm(int i)
    {
        return false;
    }

    public boolean isOpenGl()
    {
        return true;
    }

    public void onAlphabet(String s, int i)
    {
    }

    void onDataBuildBegine()
    {
    }

    protected void onDataBuiltFinish(int i)
    {
    }

    void onDecodeThumbnail(int i)
    {
        if (mDataSource != null && mediaItemInCurrentView(mDataSource.getItem(i)))
        {
            preDecode();
            if (mDataSource != null && mCacheMgr != null)
            {
                return;
            }
        }
    }

    protected void onListCountChanged(int i, int j)
    {
    }

    public void onPause()
    {
        super.onPause();
        if (mDataSourceController == null)
        {
            return;
        }
        if (mDataSourceController != null)
        {
            mDataSourceController.pause();
            mDataSourceController.setEnable(false);
            mDataSourceController.release();
            mDataSourceController = null;
        }
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.unregisterUpDownloadListener(mUpdownloadListener);
            mUpDownloadEngine = null;
        }
        DLNA.instance().getServerManager().unregisterServerDLNAUploadListener(mServerUploadListener);
        pause(true);
    }

    void onProgress(String s, String s1, long l, long l1)
    {
        Message message = mHandler.obtainMessage(1538);
        int i;
        if (l1 <= 0L)
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

    public void onResume()
    {
        super.onResume();
        if (mDataSourceController != null)
        {
            return;
        }
        if (mDataSource != null)
        {
            mDataSourceController = (com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController)mDataSource.getController();
            mDataSourceController.setEnable(true);
            mDataSourceController.resume();
            int i = Settings.instance().getDefaultContentAccess();
            com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController picturedatasourcecontroller = mDataSourceController;
            boolean flag;
            if (i == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            picturedatasourcecontroller.setResourceType(flag);
        }
        if (mIsRefreshRemote)
        {
            setDataBuildState(1);
        } else
        {
            setDataBuildState(0);
        }
        resume(false);
        mKeepPosition = false;
    }

    void onUpDownloadFinish(String s, String s1, Object obj, int i)
    {
        Message message = mHandler.obtainMessage(1539);
        message.arg1 = i;
        message.obj = obj;
        mHandler.sendMessage(message);
    }

    void onUpDownloadStart(String s, String s1)
    {
        Message message = mHandler.obtainMessage(1537);
        message.obj = s1;
        mHandler.sendMessage(message);
    }

    protected void prefetch(int i, int j)
    {
        while (mDataSourceController == null || mDataSource == null || j < i) 
        {
            return;
        }
        com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController picturedatasourcecontroller = mDataSourceController;
        Property aproperty[] = new Property[1];
        aproperty[0] = Property.PROP_THUMBNAIL_FILEPATH;
        picturedatasourcecontroller.prefetch(i, j, aproperty);
    }

    public boolean removeListView()
    {
        if (mRootView != null)
        {
            mRootView.removeView(this);
            Settings.instance().unregisterSettingChangeListener(mSettingChangedListener);
            return true;
        } else
        {
            return false;
        }
    }

    public void selectAll()
    {
        if (mDataSource != null)
        {
            int i = mDataSource.getCount();
            int j = 0;
            while (j < i) 
            {
                MediaItem mediaitem = mDataSource.getItem(j);
                if (mediaitem != null && !mediaitem.isDownloadingFile)
                {
                    ListViewManager.putSelectedItem(Integer.valueOf(j), mediaitem);
                }
                j++;
            }
        }
    }

    public void sendMsgToShowToast(String s, int i)
    {
        Log.d("zdf", (new StringBuilder()).append("[ListViewGL] sendMsgToShowToast, strText = ").append(s).append(", delay = ").append(i).toString());
        if (mHandler != null)
        {
            Message message = new Message();
            message.what = 1543;
            message.obj = s;
            mHandler.sendMessageDelayed(message, i);
        }
    }

    public void sendMsgToUpdateTextStatus(String s, boolean flag, int i)
    {
        Log.d("zdf", (new StringBuilder()).append("######## [ListViewGL] sendMsgToUpdateTextStatus, strText = ").append(s).append(", bVisible = ").append(flag).append(", delay = ").append(i).toString());
        if (mHandler != null)
        {
            Message message = new Message();
            message.what = 1541;
            message.obj = s;
            int j;
            if (flag)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            message.arg1 = j;
            mHandler.sendMessageDelayed(message, i);
        }
    }

    public void setDataSource(IDataSource idatasource)
    {
        Log.e("ThumbGlView", (new StringBuilder()).append("setDataSource : datasource = ").append(idatasource).toString());
        if (idatasource == null)
        {
            mCacheMgr.setDataSource(null);
        }
        if (mDataSource != null)
        {
            mDataSource.unregisterOnDataChangeListener(mDataChangeListener);
            mDataSource.unregisterOnDataBuildListener(mDataBuildListener);
            mDataSource.unInit();
            mDataSource = null;
            mDataSourceController = null;
        }
        if (idatasource == null)
        {
            return;
        } else
        {
            mDataSource = new PictureDataSource(idatasource, OEMConfig.THUMBLIST_DECODEPARAM, true);
            updateVectorToDataSource(true);
            mDataSource.init();
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSource.getController();
            icontroller.loadMore(-1);
            icontroller.sort(Property.PROP_MODIFIED_TIME, true);
            icontroller.release();
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
            mDataSource.registerOnDataBuildListener(mDataBuildListener);
            mCacheMgr.setDataSource(mDataSource);
            mCacheMgr.setRomoteFlag(((MediaPlusActivity)mContext).isRemote());
            return;
        }
    }

    public void setItemVisibleInScreen(int i)
    {
        setItemVisibleInScreenEx(i);
        mKeepPosition = true;
    }

    public void setListItemOpListener(com.arcsoft.mediaplus.listview.IItemListView.IListOpListener ilistoplistener)
    {
        mOpListener = ilistoplistener;
    }

    void setTextStatusViewVisible(boolean flag)
    {
        View view = mRootView.findViewById(0x7f0900a5);
        if (view == null)
        {
            return;
        }
        if (flag)
        {
            view.setVisibility(0);
            return;
        } else
        {
            view.setVisibility(8);
            return;
        }
    }

    public void setUpDownloadContext(UpDownloadEngine updownloadengine)
    {
        mDownloadFacade.setUpDownloadEngine(updownloadengine);
        mUpDownloadEngine = updownloadengine;
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.registerUpDownloadListener(mUpdownloadListener);
        }
        DLNA.instance().getServerManager().registerServerDLNAUploadListener(mServerUploadListener);
    }

    public void sort(Property property, boolean flag)
    {
        if (mDataSourceController == null)
        {
            return;
        } else
        {
            mDataSourceController.sort(property, flag);
            return;
        }
    }

    public void switchUpdownloadMode(boolean flag)
    {
        if (mDataSourceController == null);
    }

    public void uninit()
    {
        super.uninit();
        mRootView = null;
    }

    public void updateTextStatus(String s, boolean flag)
    {
        TextView textview;
        if (mContext != null && mRootView != null)
        {
            if ((textview = (TextView)(TextView)mRootView.findViewById(0x7f0900a5)) != null)
            {
                int i;
                if (flag)
                {
                    if (!isRemote())
                    {
                        s = mContext.getString(0x7f0b001b);
                    }
                } else
                {
                    s = "";
                }
                Log.d("zdf", (new StringBuilder()).append("############ [ListViewGL] updateTextStatus, str = ").append(s).append(", textView.isShown() = ").append(textview.isShown()).append(", bVisible = ").append(flag).toString());
                textview.setText(s);
                if (flag)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                textview.setVisibility(i);
                return;
            }
        }
    }

    void updateVectorToDataSource(boolean flag)
    {
    }




/*
    static boolean access$102(ListViewGL listviewgl, boolean flag)
    {
        listviewgl.mToasted = flag;
        return flag;
    }

*/

}
