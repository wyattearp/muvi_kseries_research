// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.MediaPlusBaseActivity;
import com.arcsoft.mediaplus.datasource.DataSourceFactory;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.ImageDataSourceHelper;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.datasource.VideoDataSourceHelper;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.dmc.RenderDevSelector;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.service.util.DLNAService;
import com.arcsoft.mediaplus.setting.SettingActivity;
import com.arcsoft.mediaplus.setting.SettingListActivity;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.setting.SlideShowSettingActivity;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.os.BatteryTool;
import com.arcsoft.util.os.StorageTool;
import com.arcsoft.util.tool.FileDelete;
import com.arcsoft.util.tool.ToastMgr;
import com.arcsoft.videotrim.videoTrimActivity;
import com.arcsoft.workshop.WorkShop;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayView, RootPlayView, ImageDMPPlayView, DataSourcePlayList

public abstract class PlayActivity extends MediaPlusBaseActivity
    implements com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener
{
    protected class ActivityResumeInfo
    {

        protected boolean mIsActivityResume;
        protected long mPlayPosition;
        protected boolean mStartDMPAfterResume;
        final PlayActivity this$0;

        protected ActivityResumeInfo()
        {
            this$0 = PlayActivity.this;
            super();
            mIsActivityResume = false;
            mStartDMPAfterResume = false;
            mPlayPosition = 0L;
        }
    }


    private static final int CONTROL_BAR_ANIMATION_DURATION = 400;
    protected static final int DMC_VIEW = 1;
    protected static final int DMP_VIEW = 0;
    public static final int DTCP_DOWNLOAD_CONFIRM = 12293;
    public static final String EXTRA_APPTYPE = "Param.isDiga";
    public static final String EXTRA_CURINDEX = "Param.PushTo.CurIndex";
    public static final String EXTRA_CURPOSITION = "Param.PushTo.CurPosition";
    public static final String EXTRA_FILTER = "datasource_filter";
    public static final String EXTRA_IDLIST = "Param.PushTo.IdList";
    public static final String EXTRA_LOCALFLAG = "Param.isLocalContent";
    public static final String EXTRA_MEDIATYPELIST = "Param.PushTo.MediaTypeList";
    public static final String EXTRA_OSDNAME = "Param.PushTo.OSDName";
    public static final String EXTRA_SOURCETYPELIST = "Param.PushTo.SourceTypeList";
    public static final String INTENT_EXTERNAL_PUSHTO = "Action.DLNA.PushTo";
    public static final int MEDIATYPE_AUDIO = 2;
    public static final int MEDIATYPE_IMAGE = 0;
    public static final int MEDIATYPE_VIDEO = 1;
    public static final int OPTIONS_MENU_CHANNEL = 402;
    public static final int OPTIONS_MENU_CLEARALL = 202;
    public static final int OPTIONS_MENU_DOWNLOAD = 306;
    public static final int OPTIONS_MENU_DOWNLOADING = 307;
    public static final int OPTIONS_MENU_MANAGER = 206;
    public static final int OPTIONS_MENU_RENDERER = 601;
    public static final int OPTIONS_MENU_SETTING = 308;
    public static final int OPTIONS_MENU_SLIDESHOW_SETTING = 501;
    public static final int OPTIONS_MENU_TRACK = 403;
    public static final int OPTIONS_MENU_UPLOAD = 304;
    public static final int OPTIONS_MENU_UPLOADING = 305;
    public static final int PLAYER_CHANNEL_DIALOG_ID = 8194;
    public static final int PLAYER_TRACK_DIALOG_ID = 8195;
    public static final int REQUEST_CODE_EDIT_PHOTO = 1;
    public static final int SOURCETYPE_SDCARD = 0;
    private static final String TAG = "PlayActivity";
    public static final int UPDOWNLOAD_BATTERYLOW_DIALOG_ID = 12292;
    public static final int UPDOWNLOAD_CANCELSINGLE_DIALOG_ID = 12290;
    public static final int UPDOWNLOAD_CLEARALL_DIALOG_ID = 12291;
    private final int MESSAGE_DELETE_FILES = 2;
    private final int MESSAGE_EDIT_PHOTO_FINISH = 1;
    private final int MESSAGE_START_EDIT_ACTIVITY = 0;
    private final boolean enableDMC = false;
    private final RootPlayView.IAnimationSetListener mAnimationSetListener = new RootPlayView.IAnimationSetListener() {

        private PlayView.PlaySession mTempSession;
        final PlayActivity this$0;

        public void onAnimationEnd(int i, Animation animation, View view)
        {
        }

        public void onAnimationGroupCanceled(int i)
        {
            if (i == 0)
            {
                cancelDismiss(0);
            }
        }

        public boolean onAnimationGroupEnd(int i)
        {
            Log.d("PlayActivity", (new StringBuilder()).append("onAnimationGroupEnd -----> group = ").append(i).toString());
            if (i == 0)
            {
                mTempSession = dismissed();
            } else
            if (i == 1)
            {
                if (mPlayViews[mCurViewType] != null)
                {
                    PlayActivity playactivity = PlayActivity.this;
                    long l;
                    if (mTempSession == null)
                    {
                        l = 0L;
                    } else
                    {
                        l = mTempSession.position;
                    }
                    playactivity.shown(l);
                    return true;
                } else
                {
                    release(true);
                    finish();
                    return true;
                }
            }
            return true;
        }

        public boolean onAnimationGroupPaused(int i)
        {
            Log.d("PlayActivity", "onAnimationGroupPaused");
            boolean flag;
            if (mCurViewType == 1)
            {
                mBtnPushBack.setPressed(true);
                flag = true;
            } else
            {
                flag = true;
                String s = Settings.instance().getDefaultDMRUDN();
                if (!DLNA.instance().getRenderManager().isRenderOnline(s))
                {
                    flag = false;
                }
                int j = mPlayList.getCurrentIndex();
                Uri uri;
                if (j < 0)
                {
                    uri = null;
                } else
                {
                    uri = mPlayList.getUri(j);
                }
                if (uri == null)
                {
                    flag = false;
                }
                if (flag)
                {
                    mBtnPushTo.setPressed(true);
                    return flag;
                }
            }
            return flag;
        }

        public void onAnimationGroupStart(int i)
        {
            while (i == 0 || i != 1) 
            {
                return;
            }
        }

        public void onAnimationStart(int i, Animation animation, View view)
        {
        }

        public boolean onCreateAnimation()
        {
            boolean flag = true;
            String s = Settings.instance().getDefaultDMRUDN();
            if (!DLNA.instance().getRenderManager().isRenderOnline(s))
            {
                Toast.makeText(PlayActivity.this, 0x7f0b0058, flag).show();
            }
            int i = getMediaType();
            RootPlayView rootplayview = mRootPlayView;
            PlayView playview = mPlayViews[mCurViewType];
            PlayView playview1 = mPlayViews[1 ^ mCurViewType];
            if (i == 2)
            {
                flag = false;
            }
            return rootplayview.initAnimation(playview, playview1, flag);
        }

        public boolean onDispatchTouchEvent()
        {
            return false;
        }

        public void onPrepareAnimationGroup(int i)
        {
            if (i == 0)
            {
                startDismiss();
            } else
            if (i == 1)
            {
                mCurViewType = 1 ^ mCurViewType;
                startShow();
                return;
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.BatteryTool.IOnBatteryChangeListener mBatteryChangeListener = new com.arcsoft.util.os.BatteryTool.IOnBatteryChangeListener() {

        final PlayActivity this$0;

        public void OnBatteryLevelChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

        public void OnBatteryPlugedChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

        public void OnBatteryStatusChanged(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo)
        {
            checkBattery(batteryinfo, 15);
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private BatteryTool mBatteryTool;
    private LinearLayout mBottomBar;
    private ImageView mBtnBack;
    private ImageView mBtnCenterPlay;
    private ImageView mBtnDelete;
    private ImageView mBtnDownload;
    private ImageView mBtnEdit;
    private ImageView mBtnInfo;
    private Button mBtnInfoOK;
    private ImageView mBtnPlayTo;
    private ImageView mBtnPushBack;
    private ImageView mBtnPushTo;
    private ImageView mBtnShare;
    private BroadcastReceiver mClearCacheReceiver;
    private long mClickedEditTime;
    private long mClickedTime;
    private int mCurViewType;
    private int mCurrentIndex;
    protected IDataSource mDataSource;
    private AlertDialog mDeleteDlg;
    private final com.arcsoft.util.tool.FileDelete.onDeleteListener mDeleteListener = new com.arcsoft.util.tool.FileDelete.onDeleteListener() {

        final PlayActivity this$0;

        public void onDeleteStart(int i)
        {
            Log.v("zdf1", (new StringBuilder()).append("onDeleteStart, fileNum = ").append(i).toString());
            mIsDMSDeleteFailed = false;
            showDeletingWaitDialog();
        }

        public void onDeleted(int i, int j)
        {
            Log.v("zdf1", (new StringBuilder()).append("onDeleted, sucNum = ").append(i).append(", fileNum = ").append(j).toString());
            if (isLocalContent())
            {
                onDeletedEnd(i, j);
                return;
            }
            PlayActivity playactivity = PlayActivity.this;
            int k;
            if (!mIsDMSDeleteFailed)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            playactivity.onDeletedEnd(k, 1);
        }

        public void onDeleting(MediaItem mediaitem, int i, int j, boolean flag)
        {
            Log.v("zdf1", (new StringBuilder()).append("onDeleting, filePros = ").append(i).append(", isSuc = ").append(flag).toString());
        }

        public void onDeletingRemote(int i)
        {
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private LoadingDialog mDeletingWaitDialog;
    private RenderDevSelector mDevSelector;
    private DownloadFacade mDownloadFacade;
    private FileDelete mFileDelMgr;
    private final Handler mHandler = new Handler() {

        final PlayActivity this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 0: // '\0'
                startPhotoEditAct();
                return;

            case 1: // '\001'
                Log.e("FENG", (new StringBuilder()).append("FENG MESSAGE_EDIT_PHOTO_FINISH: mCurrentIndex = ").append(mCurrentIndex).append(", msg.arg1 = ").append(message.arg1).toString());
                return;

            case 2: // '\002'
                deleteFile();
                break;
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private RelativeLayout mInfoView;
    private boolean mIsBackPressed;
    private boolean mIsDMSDeleteFailed;
    private boolean mIsStartingEdit;
    private ImageView mIvInfoClose;
    private int mLeavingOrientation;
    private final android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        final PlayActivity this$0;

        public void onClick(View view)
        {
            while (view.getId() != 0x7f0900b4 && view.getId() != 0x7f0900b6 && isInfoViewShown() || System.currentTimeMillis() - mClickedTime <= 1000L) 
            {
                return;
            }
            mPlayViews[mCurViewType].stopHideControlTimer();
            view.getId();
            JVM INSTR lookupswitch 10: default 160
        //                       2131296306: 275
        //                       2131296392: 206
        //                       2131296393: 246
        //                       2131296394: 265
        //                       2131296395: 216
        //                       2131296396: 172
        //                       2131296398: 182
        //                       2131296432: 236
        //                       2131296436: 226
        //                       2131296438: 226;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L10
_L1:
            mClickedTime = System.currentTimeMillis();
            return;
_L7:
            doBack();
            continue; /* Loop/switch isn't completed */
_L8:
            showInfoView(mCurrentIndex, mDataSource);
            continue; /* Loop/switch isn't completed */
_L3:
            showShareIntent();
            continue; /* Loop/switch isn't completed */
_L6:
            showDeleteFileDialog();
            continue; /* Loop/switch isn't completed */
_L10:
            closeInfoView();
            continue; /* Loop/switch isn't completed */
_L9:
            startPlay();
            continue; /* Loop/switch isn't completed */
_L4:
            mIsStartingEdit = true;
            startPhotoEditAct();
            continue; /* Loop/switch isn't completed */
_L5:
            downloadCurrentFile();
            continue; /* Loop/switch isn't completed */
_L2:
            if (mDevSelector == null)
            {
                mDevSelector = new RenderDevSelector(PlayActivity.this);
            }
            RenderDevSelector.CURRENT_PROVIDER_FROM_LOCAL = isLocalContent();
            RenderDevSelector.CURRENT_PROVIDER_TYPE = com.arcsoft.mediaplus.dmc.DmcUtils.PROVIDER_TYPE.TYPE_FROM_LARGE_VIEW;
            mDevSelector.setDataSourceFilter(getDataSourceFilter());
            mDevSelector.setCurrentIndex(mCurrentIndex);
            mDevSelector.start();
            if (true) goto _L1; else goto _L11
_L11:
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private final android.view.View.OnClickListener mOnPushBackListener = new android.view.View.OnClickListener() {

        final PlayActivity this$0;

        public void onClick(View view)
        {
            switchView();
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private final android.view.View.OnClickListener mOnPushToListener = new android.view.View.OnClickListener() {

        final PlayActivity this$0;

        public void onClick(View view)
        {
            String s = Settings.instance().getDefaultDMRUDN();
            if (!DLNA.instance().getRenderManager().isRenderOnline(s))
            {
                Toast.makeText(PlayActivity.this, 0x7f0b0058, 1).show();
            } else
            if (mPlayList.getCurrentIndex() >= 0)
            {
                switchView();
                return;
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    protected DataSourcePlayList mPlayList;
    private final PlayView.IPlayViewContorller mPlayViewContorller = new PlayView.IPlayViewContorller() {

        final PlayActivity this$0;

        public void requestQuit(PlayView playview)
        {
            if (playview != mPlayViews[mCurViewType])
            {
                return;
            }
            if (isDMPView() || mPlayViews[0] == null)
            {
                release(true);
                finish();
                return;
            }
            startDismiss();
            PlayView.PlaySession playsession = dismissed();
            mCurViewType = 1 ^ mCurViewType;
            if (mResumeInfo.mIsActivityResume)
            {
                startShow();
                shown(playsession.position);
                return;
            } else
            {
                mResumeInfo.mPlayPosition = playsession.position;
                mResumeInfo.mStartDMPAfterResume = true;
                return;
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private final PlayView mPlayViews[] = new PlayView[1];
    com.arcsoft.mediaplus.DownloadFacade.IPreDownloadListener mPreDownloadListener;
    private final com.arcsoft.adk.atv.RenderManager.IRenderStatusListener mRenderStatusListener = new com.arcsoft.adk.atv.RenderManager.IRenderStatusListener() {

        final PlayActivity this$0;

        public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, int i)
        {
        }

        public void onRenderAdded(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
        {
        }

        public void onRenderInstalled(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
        {
            Log.w("PlayActivity", (new StringBuilder()).append("IRenderStatusListener.onRenderInstalled(), cm = ").append(flag).append(", avt = ").append(flag1).append(", rcl = ").append(flag2).toString());
        }

        public void onRenderRemoved(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
        {
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    protected ActivityResumeInfo mResumeInfo;
    private RootPlayView mRootPlayView;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerStatusListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final PlayActivity this$0;

        public void OnDestroyObject(String s, int i)
        {
            Log.e("PlayActivity", (new StringBuilder()).append("testP OnDestroyObeject updateid = ").append(s).append(" errorcode = ").append(i).toString());
            if (i == 0)
            {
                String s1 = ListViewManager.getObjId(s);
                int j = RemoteDBMgr.instance().delteRemoteItem(s1);
                Log.e("PlayActivity", (new StringBuilder()).append("testP OnDestroyObject del suc = ").append(j).toString());
            } else
            {
                mIsDMSDeleteFailed = true;
            }
            ListViewManager.clearUpdateItems();
            if (mFileDelMgr != null)
            {
                mFileDelMgr.stopDelete();
            }
        }

        public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordTasks dataonrecordtasks, int i)
        {
        }

        public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordSchedule dataonrecordschedule, int i)
        {
        }

        public void OnDigaDeleteRecordSchedule(String s, int i)
        {
        }

        public void OnDigaDisableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaEnableRecordSchedule(String s, int i)
        {
        }

        public void OnDigaXP9eGetContainerIds(String s, String s1, int i)
        {
        }

        public void onGetSearchCapabilities(String s, String s1, int i)
        {
        }

        public void onGetSortCapabilities(String s, String s1, int i)
        {
        }

        public void onServerAdded(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            String s = Settings.instance().getDefaultDMSUDN();
            if (mediaserverdesc.m_strUuid.equals(s))
            {
                String s1 = getResources().getString(0x7f0b0018);
                Object aobj[] = new Object[1];
                aobj[0] = mediaserverdesc.m_strFriendlyName;
                String s2 = String.format(s1, aobj);
                Toast.makeText(PlayActivity.this, s2, 0).show();
                if (!isLocalContent())
                {
                    finish();
                    return;
                }
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private final com.arcsoft.util.os.StorageTool.IOnStorageStatusChangeListener mStorageListener = new com.arcsoft.util.os.StorageTool.IOnStorageStatusChangeListener() {

        final PlayActivity this$0;

        public void onStorageChecking()
        {
        }

        public void onStorageMounted()
        {
        }

        public void onStorageUnmounted()
        {
            Toast.makeText(PlayActivity.this, 0x7f0b0056, 0).show();
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    protected StorageTool mStorageTool;
    private RelativeLayout mTitleBar;
    private com.arcsoft.adk.atv.DLNA.UserToken mToken;
    private TextView mTvCurIndex;
    private TextView mTvInfoContent;
    private UpDownloadEngine mUpDownloadEngine;
    private final com.arcsoft.util.tool.FileDelete.IRelatedDeleteList mUpDownloadEngineDeleteLis = new com.arcsoft.util.tool.FileDelete.IRelatedDeleteList() {

        final PlayActivity this$0;

        public void delete(com.arcsoft.util.tool.FileDelete.DeleteData deletedata)
        {
            if (mUpDownloadEngine != null && deletedata != null)
            {
                mUpDownloadEngine.deleteDownloadedRecode(deletedata.filePath);
            }
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private DLNAService mUpDownloadServiceBinder;
    private final ServiceConnection mUpdownloadConnection = new ServiceConnection() {

        final PlayActivity this$0;

        public void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            Log.i("PlayActivity", "DLNAService connected");
            mUpDownloadServiceBinder = ((com.arcsoft.mediaplus.service.util.DLNAService.LocalBinder)ibinder).getService();
            mUpDownloadEngine = mUpDownloadServiceBinder.getUpDownloadEngine();
            svcReady();
            mDownloadFacade.setUpDownloadEngine(mUpDownloadEngine);
        }

        public void onServiceDisconnected(ComponentName componentname)
        {
            Log.i("PlayActivity", "DLNAService disconnected");
            mUpDownloadServiceBinder = null;
            svcDisable();
        }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
    };
    private TextView mVideoTitle;
    private ViewGroup mVolumeView;

    public PlayActivity()
    {
        mBatteryTool = null;
        mStorageTool = null;
        mBtnPushTo = null;
        mBtnPushBack = null;
        mVideoTitle = null;
        mTitleBar = null;
        mBottomBar = null;
        mTvCurIndex = null;
        mBtnBack = null;
        mBtnInfo = null;
        mBtnShare = null;
        mBtnDelete = null;
        mBtnEdit = null;
        mBtnDownload = null;
        mBtnPlayTo = null;
        mInfoView = null;
        mIvInfoClose = null;
        mTvInfoContent = null;
        mBtnCenterPlay = null;
        mCurrentIndex = 0;
        mFileDelMgr = null;
        mDeletingWaitDialog = null;
        mIsDMSDeleteFailed = false;
        mDownloadFacade = null;
        mResumeInfo = new ActivityResumeInfo();
        mToken = null;
        mPreDownloadListener = new com.arcsoft.mediaplus.DownloadFacade.IPreDownloadListener() {

            final PlayActivity this$0;

            public void onDownloadBegin()
            {
            }

            public void onDownloadFinish(ArrayList arraylist)
            {
                if (arraylist == null || arraylist.size() != 1)
                {
                    return;
                }
                for (int i = 0; i < arraylist.size(); i++)
                {
                    Log.e("zdf", (new StringBuilder()).append("####### onDownloadFinish, Share uri(").append(i).append(") = ").append(arraylist.get(i)).toString());
                }

                showShareIntent((Uri)arraylist.get(0));
            }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
        };
        mDeleteDlg = null;
        mLeavingOrientation = -1;
        mClickedTime = 0L;
        mClickedEditTime = 0L;
        mIsBackPressed = false;
        mIsStartingEdit = false;
        mDevSelector = null;
        mClearCacheReceiver = null;
        mCurViewType = 0;
        mDataSource = null;
        mPlayList = null;
        mRootPlayView = null;
        mVolumeView = null;
        mUpDownloadServiceBinder = null;
        mUpDownloadEngine = null;
    }

    private void cancelDismiss(int i)
    {
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onStopCanceled();
        }
        mBtnPushTo.setEnabled(true);
        mBtnPushBack.setEnabled(true);
        mBtnPushTo.setPressed(false);
        mBtnPushBack.setPressed(false);
    }

    private void checkBattery(com.arcsoft.util.os.BatteryTool.BatteryInfo batteryinfo, int i)
    {
        if (batteryinfo.pluged == 0 && batteryinfo.maxlevel != 0 && (100 * batteryinfo.level) / batteryinfo.maxlevel < i && mUpDownloadEngine != null && mUpDownloadEngine.getAllTaskCount() > 0)
        {
            removeDialog(12292);
            showDialog(12292);
        }
    }

    private void closeInfoView()
    {
        if (mInfoView != null)
        {
            mInfoView.setVisibility(8);
        }
        resetHideControlTimerEx();
    }

    private void createAndInitUI()
    {
        byte byte0 = 8;
        boolean flag = isLocalContent();
        setContentView(0x7f030022);
        mVideoTitle = (TextView)findViewById(0x7f0900ad);
        mTitleBar = (RelativeLayout)findViewById(0x7f090005);
        mBottomBar = (LinearLayout)findViewById(0x7f090087);
        mTvCurIndex = (TextView)findViewById(0x7f090007);
        updateCurrentIndexBar(mCurrentIndex);
        mBtnBack = (ImageView)findViewById(0x7f09008c);
        if (mBtnBack != null)
        {
            mBtnBack.setOnClickListener(mOnClickListener);
        }
        mBtnInfo = (ImageView)findViewById(0x7f09008e);
        if (mBtnInfo != null)
        {
            mBtnInfo.setOnClickListener(mOnClickListener);
            mBtnInfo.setEnabled(flag);
            ImageView imageview = mBtnInfo;
            int i;
            View view;
            if (flag)
            {
                i = 0;
            } else
            {
                i = byte0;
            }
            imageview.setVisibility(i);
            view = findViewById(0x7f09008d);
            if (flag)
            {
                byte0 = 0;
            }
            view.setVisibility(byte0);
        }
        mBtnShare = (ImageView)findViewById(0x7f090088);
        if (mBtnShare != null)
        {
            mBtnShare.setOnClickListener(mOnClickListener);
        }
        mBtnDelete = (ImageView)findViewById(0x7f09008b);
        if (mBtnDelete != null)
        {
            mBtnDelete.setOnClickListener(mOnClickListener);
            mBtnDelete.setEnabled(true);
            mBtnDelete.setVisibility(0);
        }
        mBtnEdit = (ImageView)findViewById(0x7f090089);
        if (mBtnEdit != null)
        {
            mBtnEdit.setOnClickListener(mOnClickListener);
            mBtnEdit.setEnabled(flag);
        }
        mBtnDownload = (ImageView)findViewById(0x7f09008a);
        if (mBtnDownload != null)
        {
            mBtnDownload.setOnClickListener(mOnClickListener);
            refreshDownloadBtnVisible();
        }
        mBtnPlayTo = (ImageView)findViewById(0x7f090032);
        if (mBtnPlayTo == null);
        mInfoView = (RelativeLayout)findViewById(0x7f0900b1);
        if (mInfoView != null)
        {
            mInfoView.setOnTouchListener(new android.view.View.OnTouchListener() {

                final PlayActivity this$0;

                public boolean onTouch(View view1, MotionEvent motionevent)
                {
                    return isInfoViewShown();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            });
        }
        mIvInfoClose = (ImageView)findViewById(0x7f0900b4);
        if (mIvInfoClose != null)
        {
            mIvInfoClose.setOnClickListener(mOnClickListener);
        }
        mTvInfoContent = (TextView)findViewById(0x7f0900b5);
        if (mTvInfoContent != null && mTvInfoContent != null)
        {
            if (getResources().getConfiguration().orientation == 2)
            {
                mTvInfoContent.setMaxLines(getResources().getInteger(0x7f0a000a));
                mTvInfoContent.setVerticalScrollBarEnabled(true);
                mTvInfoContent.setMovementMethod(ScrollingMovementMethod.getInstance());
            } else
            {
                mTvInfoContent.setMaxLines(10);
                mTvInfoContent.setVerticalScrollBarEnabled(false);
                mTvInfoContent.setMovementMethod(null);
            }
        }
        mBtnInfoOK = (Button)findViewById(0x7f0900b6);
        if (mBtnInfoOK != null)
        {
            mBtnInfoOK.setOnClickListener(mOnClickListener);
        }
        mBtnCenterPlay = (ImageView)findViewById(0x7f0900b0);
        if (mBtnCenterPlay != null)
        {
            mBtnCenterPlay.setOnClickListener(mOnClickListener);
            setCenterPlayBtnVisible(false);
        }
        mRootPlayView = (RootPlayView)findViewById(0x7f0900ab);
        mRootPlayView.setAnimationSetListener(mAnimationSetListener);
        mVolumeView = (ViewGroup)findViewById(0x7f09017a);
        mPlayViews[0] = createDMPPlayView(mRootPlayView);
        if (mPlayViews[0] != null)
        {
            mPlayViews[0].init();
            mPlayViews[0].setPlayList(mPlayList);
            mPlayViews[0].setPlayviewContorller(mPlayViewContorller);
            mPlayViews[0].setVisibility(4);
        }
        mBtnPushTo = (ImageView)findViewById(0x7f0900af);
        mBtnPushBack = (ImageView)findViewById(0x7f0900ae);
        mBtnPushTo.setOnClickListener(mOnPushToListener);
        mBtnPushBack.setOnClickListener(mOnPushBackListener);
    }

    private void deleteFile()
    {
        String s;
label0:
        {
            MediaItem mediaitem = mDataSource.getItem(mCurrentIndex);
            if (mFileDelMgr != null && mediaitem != null)
            {
                s = mDataSource.getRemoteItemUUID(mCurrentIndex);
                if (!isLocalContent())
                {
                    break label0;
                }
                mFileDelMgr.onDelete(new MediaItem[] {
                    mediaitem
                });
            }
            return;
        }
        if (mUpDownloadEngine != null && mUpDownloadEngine.isFileDownloadingOrWaiting(s))
        {
            ToastMgr.showToast(this, 0x7f0b0181, 0);
            return;
        } else
        {
            mFileDelMgr.onDelete(s);
            return;
        }
    }

    private PlayView.PlaySession dismissed()
    {
        PlayView playview = mPlayViews[mCurViewType];
        PlayView.PlaySession playsession = null;
        if (playview != null)
        {
            playsession = mPlayViews[mCurViewType].onStopped(true);
            mPlayViews[mCurViewType].setVisibility(4);
        }
        mBtnPushTo.setEnabled(true);
        mBtnPushBack.setEnabled(true);
        mBtnPushTo.setVisibility(4);
        mBtnPushBack.setVisibility(4);
        return playsession;
    }

    private void doBack()
    {
        Intent intent = new Intent();
        intent.putExtra("Param.PushTo.CurIndex", mCurrentIndex);
        Log.e("PlayActivity", (new StringBuilder()).append("doBack(): mCurrentIndex = ").append(mCurrentIndex).toString());
        setResult(-1, intent);
        finish();
    }

    private com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter getDataSourceFilter()
    {
        if (getIntent() == null)
        {
            return null;
        } else
        {
            return (com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter)getIntent().getParcelableExtra("datasource_filter");
        }
    }

    private String getDatailInfo(int i, IDataSource idatasource)
    {
        Log.e("PlayActivity", "getDatailInfo -----------------");
        if (isVideoFile(mCurrentIndex)) goto _L2; else goto _L1
_L1:
        String s13;
        int i1 = ImageDataSourceHelper.getWidth(i, idatasource);
        int j1 = ImageDataSourceHelper.getHeight(i, idatasource);
        long l3 = ImageDataSourceHelper.getSize(i, idatasource);
        String s1 = null;
        String s2 = null;
        String s3 = null;
        Uri uri1 = ImageDataSourceHelper.getUri(i, idatasource);
        StringBuilder stringbuilder1 = (new StringBuilder()).append(getString(0x7f0b003b));
        String s4;
        String s5;
        String s6;
        long l4;
        StringBuilder stringbuilder2;
        Date date1;
        String s7;
        String s8;
        String s9;
        String s10;
        String s11;
        String s12;
        ExifInterface exifinterface;
        String s15;
        String as[];
        String s16;
        String as1[];
        Object aobj[];
        String s17;
        String s18;
        String s19;
        if (uri1 == null)
        {
            s4 = null;
        } else
        {
            s4 = uri1.getLastPathSegment();
        }
        s5 = stringbuilder1.append(s4).toString();
        s6 = (new StringBuilder()).append(s5).append("\n").toString();
        l4 = ImageDataSourceHelper.getTakenTime(i, idatasource);
        stringbuilder2 = (new StringBuilder()).append(getString(0x7f0b003f));
        date1 = new Date(l4);
        s7 = stringbuilder2.append(date1.toLocaleString()).toString();
        s8 = (new StringBuilder()).append(s6).append(s7).append("\n").toString();
        s9 = (new StringBuilder()).append(getString(0x7f0b0040)).append(i1).append("x").append(j1).toString();
        s10 = (new StringBuilder()).append(s8).append(s9).append("\n").toString();
        s11 = (new StringBuilder()).append(getString(0x7f0b0041)).append(FileUtils.formatSize(l3)).toString();
        s12 = (new StringBuilder()).append(s10).append(s11).append("\n").toString();
        exifinterface = new ExifInterface(uri1.getEncodedPath());
        s1 = exifinterface.getAttribute("FNumber");
        s2 = exifinterface.getAttribute("FocalLength");
        s3 = exifinterface.getAttribute("ISOSpeedRatings");
        s19 = exifinterface.getAttribute("ExposureTime");
        s13 = s19;
_L4:
        if (s2 != null)
        {
            as1 = s2.split("/");
            aobj = new Object[1];
            aobj[0] = Float.valueOf(Float.parseFloat(as1[0]) / Float.parseFloat(as1[1]));
            String.format("%.1f", aobj);
            s17 = String.valueOf(Integer.parseInt(as1[0]) / Integer.parseInt(as1[1]));
            Log.e("PlayActivity", (new StringBuilder()).append("focalLength str : ").append(s17).toString());
            s18 = (new StringBuilder()).append(getString(0x7f0b0044)).append(s17).append("mm").toString();
            s12 = (new StringBuilder()).append(s12).append(s18).append("\n").toString();
        }
        if (s1 != null)
        {
            as = s1.split("\\.");
            if (as != null && as.length >= 2 && Integer.parseInt(as[1]) == 0)
            {
                s1 = as[0];
            }
            s16 = (new StringBuilder()).append(getString(0x7f0b0043)).append("F").append(s1).toString();
            s12 = (new StringBuilder()).append(s12).append(s16).append("\n").toString();
        }
        if (s3 != null)
        {
            s15 = (new StringBuilder()).append(getString(0x7f0b0045)).append(s3).toString();
            s12 = (new StringBuilder()).append(s12).append(s15).append("\n").toString();
        }
        if (s13 != null)
        {
            float f = 1000F * Float.parseFloat(s13);
            String s14;
            if (f >= 1000F)
            {
                if (f / 1000F == (float)((int)f / 1000))
                {
                    s14 = (new StringBuilder()).append(getString(0x7f0b0046)).append((int)f / 1000).append("s").toString();
                } else
                {
                    DecimalFormat decimalformat = new DecimalFormat(".0");
                    s14 = (new StringBuilder()).append(getString(0x7f0b0046)).append(decimalformat.format(f / 1000F)).append("s").toString();
                }
            } else
            {
                int k1 = 1000F / f != (float)(int)(1000F / f);
                int i2 = 0;
                if (k1 > 0)
                {
                    i2 = 1;
                }
                s14 = (new StringBuilder()).append(getString(0x7f0b0046)).append("1/").append((int)((float)i2 + 1000F / f)).append("s").toString();
            }
            s12 = (new StringBuilder()).append(s12).append(s14).toString();
        }
        return s12;
_L2:
        int j = VideoDataSourceHelper.getWidth(i, idatasource);
        int k = VideoDataSourceHelper.getHeight(i, idatasource);
        long l = VideoDataSourceHelper.getSize(i, idatasource);
        long l1 = VideoDataSourceHelper.getDuration(i, idatasource);
        long l2 = VideoDataSourceHelper.getTakenTime(i, idatasource);
        Uri uri = VideoDataSourceHelper.getUri(i, idatasource);
        String s;
        StringBuilder stringbuilder;
        Date date;
        if (uri == null)
        {
            s = null;
        } else
        {
            s = uri.getLastPathSegment();
        }
        stringbuilder = (new StringBuilder()).append(getString(0x7f0b003b)).append(s).append("\n").append(getString(0x7f0b003f));
        date = new Date(l2);
        return stringbuilder.append(date.toLocaleString()).append("\n").append(getString(0x7f0b0040)).append(j).append("x").append(k).append("\n").append(getString(0x7f0b0041)).append(FileUtils.formatSize(l)).append("\n").append(getString(0x7f0b004b)).append(TimeUtils.formatDuration("HH:mm:ss", l1)).toString();
        IOException ioexception;
        ioexception;
        s13 = null;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private Uri getLocalContentUri(MediaItem mediaitem)
    {
        if (mediaitem == null)
        {
            return null;
        } else
        {
            return FileUtils.filePathToContentUri(mediaitem.uri, mediaitem._id, mediaitem.videoOrImage);
        }
    }

    private void initCurViewType()
    {
        long l = getIntent().getLongExtra("Param.PushTo.CurPosition", 0L);
        String s = getIntent().getAction();
        if (s.equals("Action.DLNA.PushTo"))
        {
            mRootPlayView.reset(0);
            mRootPlayView.startAnimation();
            return;
        }
        if (s.equals("MUVI.mediaplus.intent.action.PUSHTO"))
        {
            mCurViewType = 1;
            startShow();
            shown(l);
            return;
        } else
        {
            startShow();
            shown(l);
            return;
        }
    }

    private void initSystemEvent()
    {
        mStorageTool = new StorageTool(this);
    }

    private void initUpdownloadService()
    {
        mDownloadFacade = new DownloadFacade(this);
        Intent intent = new Intent();
        intent.setAction("ArcSoft.DLNA.UPDWONLOAD.SERVICE");
        intent.setClass(this, com/arcsoft/mediaplus/service/util/DLNAService);
        bindService(intent, mUpdownloadConnection, 1);
    }

    private void jumpToListIfNeeded()
    {
        if (!isSDCardAvailable())
        {
            Intent intent = new Intent();
            intent.setClass(this, com/arcsoft/mediaplus/MediaPlusActivity);
            intent.setFlags(0x4000000);
            startActivity(intent);
        }
    }

    private void onDeletedEnd(int i, int j)
    {
        if (i == j && mDataSource != null && mPlayViews != null)
        {
            mDataSource.delete(mCurrentIndex);
            ((ImageDMPPlayView)mPlayViews[mCurViewType]).deleteCurrentFileFromPV();
            Log.e("FENG", (new StringBuilder()).append("FENG largeview onDeleted, mDataSource.getCount() = ").append(mDataSource.getCount()).toString());
        }
        cancelDeletingWaitDialog();
        if (mIsDMSDeleteFailed)
        {
            ToastMgr.showToast(this, 0x7f0b016d, 1);
        }
        if (mDataSource != null && mDataSource.getCount() == 0)
        {
            finish();
        }
    }

    private void pause3rdMusic()
    {
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        intent.putExtra("isFromMediaSee", true);
        sendBroadcast(intent);
    }

    private void registerClearCacheReceiver()
    {
        if (mClearCacheReceiver != null)
        {
            return;
        } else
        {
            Log.v("PlayActivity", "registerClearCacheReceiver()");
            mClearCacheReceiver = new BroadcastReceiver() {

                final PlayActivity this$0;

                public void onReceive(Context context, Intent intent)
                {
                    Log.v("PlayActivity", "onReceive() clear cache action");
                    if (PlayActivity.this != null && !isFinishing())
                    {
                        finish();
                    }
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            };
            IntentFilter intentfilter = new IntentFilter();
            intentfilter.addAction("com.MUVI.MediaPlus.clear.cache");
            registerReceiver(mClearCacheReceiver, intentfilter);
            return;
        }
    }

    private void registerSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.setOnStorageStatusChangeListener(mStorageListener);
        }
    }

    private void releaseUI()
    {
        for (int i = 0; i < mPlayViews.length; i++)
        {
            if (mPlayViews[i] != null)
            {
                mPlayViews[i].uninit();
                mPlayViews[i] = null;
            }
        }

        if (mRootPlayView != null)
        {
            mRootPlayView.uninitAnimation();
        }
        if (mVideoTitle != null)
        {
            mVideoTitle = null;
        }
        if (mTitleBar != null)
        {
            mTitleBar = null;
        }
        if (mBottomBar != null)
        {
            mBottomBar = null;
        }
        if (mTvCurIndex != null)
        {
            mTvCurIndex = null;
        }
        if (mBtnBack != null)
        {
            mBtnBack = null;
        }
        if (mBtnInfo != null)
        {
            mBtnInfo = null;
        }
        if (mBtnShare != null)
        {
            mBtnShare = null;
        }
        if (mBtnDelete != null)
        {
            mBtnDelete = null;
        }
        if (mBtnEdit != null)
        {
            mBtnEdit = null;
        }
        if (mBtnDownload != null)
        {
            mBtnDownload = null;
        }
        if (mBtnPlayTo != null)
        {
            mBtnPlayTo = null;
        }
        if (mInfoView != null)
        {
            mInfoView = null;
        }
        if (mIvInfoClose != null)
        {
            mIvInfoClose = null;
        }
        if (mTvInfoContent != null)
        {
            mTvInfoContent = null;
        }
        if (mBtnInfoOK != null)
        {
            mBtnInfoOK = null;
        }
        if (mBtnCenterPlay != null)
        {
            mBtnCenterPlay = null;
        }
        if (mVolumeView != null)
        {
            mVolumeView = null;
        }
        if (mBtnPushTo != null)
        {
            mBtnPushTo = null;
        }
        if (mBtnPushBack != null)
        {
            mBtnPushBack = null;
        }
        if (mDeleteDlg != null)
        {
            mDeleteDlg.dismiss();
            mDeleteDlg = null;
        }
    }

    private void setControlBarClickable(boolean flag)
    {
        if (mBtnShare != null)
        {
            mBtnShare.setClickable(flag);
        }
        if (mBtnPlayTo != null)
        {
            mBtnPlayTo.setClickable(flag);
        }
        if (mBtnEdit != null)
        {
            mBtnEdit.setClickable(flag);
        }
        if (mBtnDelete != null)
        {
            mBtnDelete.setClickable(flag);
        }
    }

    private void showDeleteFileDialog()
    {
        if (mDeleteDlg == null)
        {
            mDeleteDlg = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b011e).setMessage(0x7f0b011f).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    synchronized (mHandler)
                    {
                        mHandler.removeMessages(2);
                        mHandler.sendEmptyMessage(2);
                    }
                    dialoginterface.dismiss();
                    return;
                    exception;
                    handler;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).create();
            mDeleteDlg.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {

                final PlayActivity this$0;

                public void onDismiss(DialogInterface dialoginterface)
                {
                    resetHideControlTimerEx();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            });
        }
        if (mDeleteDlg != null && !mDeleteDlg.isShowing())
        {
            mDeleteDlg.show();
        }
    }

    private void showInfoView(int i, IDataSource idatasource)
    {
        String s;
        if (idatasource != null)
        {
            if ((s = getDatailInfo(i, idatasource)) != null)
            {
                if (mTvInfoContent != null)
                {
                    mTvInfoContent.setText(s);
                }
                if (mTvInfoContent != null)
                {
                    mInfoView.setVisibility(0);
                    mTvInfoContent.scrollTo(0, 0);
                    return;
                }
            }
        }
    }

    private void showShareIntent()
    {
        if (mPlayList != null)
        {
            if (isLocalContent())
            {
                if (mPlayList.getCurrentIndex() < 0)
                {
                    mPlayList.setCurrentIndex(0);
                }
                showShareIntent(getLocalContentUri(mPlayList.getMediaItem(mPlayList.getCurrentIndex())));
                return;
            }
            if (mDownloadFacade != null)
            {
                if (mPlayList.getCurrentIndex() < 0)
                {
                    mPlayList.setCurrentIndex(0);
                }
                mDownloadFacade.setPreDownloadListener(mPreDownloadListener);
                com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = DownloadFacade.mediaItemToDownloadTask(mPlayList.getMediaItem(mPlayList.getCurrentIndex()), 1, mPlayList.getUri(mPlayList.getCurrentIndex()));
                if (downloadtask != null)
                {
                    ArrayList arraylist = new ArrayList();
                    arraylist.add(downloadtask);
                    Log.e("zdf", "####### showShareIntent(Share)");
                    mDownloadFacade.downloadAllWithConfirmDlg(0x7f0b017f, arraylist);
                    return;
                }
            }
        }
    }

    private void showShareIntent(Uri uri)
    {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uri);
        if (isVideoFile(mCurrentIndex))
        {
            intent.setType("video/*");
        } else
        {
            intent.setType("image/*");
        }
        startActivity(Intent.createChooser(intent, getString(0x7f0b011d)));
    }

    private void shown(long l)
    {
        byte byte0 = 8;
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onStarted(l);
            mPlayViews[mCurViewType].setUpDownloadEngine(mUpDownloadEngine);
        }
        mBtnPushTo.setEnabled(true);
        mBtnPushBack.setEnabled(true);
        mBtnPushTo.setPressed(false);
        mBtnPushBack.setPressed(false);
        ImageView imageview;
        byte byte1;
        ImageView imageview1;
        if (getIntent().getType().equalsIgnoreCase("video/*") && isDMPView())
        {
            mVideoTitle.setVisibility(0);
            String s = VideoDataSourceHelper.getTitle(mPlayList.getCurrentIndex(), mDataSource);
            mVideoTitle.setText(s);
        } else
        {
            mVideoTitle.setVisibility(byte0);
        }
        imageview = mBtnPushTo;
        if (isDMPView())
        {
            if (isRealtimePlayView())
            {
                byte1 = byte0;
            } else
            {
                byte1 = 0;
            }
        } else
        {
            byte1 = byte0;
        }
        imageview.setVisibility(byte1);
        imageview1 = mBtnPushBack;
        if (!isDMPView())
        {
            byte0 = 0;
        }
        imageview1.setVisibility(byte0);
    }

    private void startDismiss()
    {
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onStop();
        }
        mBtnPushTo.setEnabled(false);
        mBtnPushBack.setEnabled(false);
    }

    private void startPhotoEditAct()
    {
        mIsStartingEdit = false;
        if (mPlayViews != null && mPlayViews[0] != null && mPlayList != null)
        {
            if (mIsBackPressed)
            {
                mIsBackPressed = false;
                return;
            }
            if (System.currentTimeMillis() - mClickedEditTime > 1000L)
            {
                mClickedEditTime = System.currentTimeMillis();
                Intent intent;
                boolean flag;
                if (isVideoFile(mCurrentIndex))
                {
                    flag = true;
                    intent = new Intent(this, com/arcsoft/videotrim/videoTrimActivity);
                } else
                {
                    intent = new Intent(this, com/arcsoft/workshop/WorkShop);
                    flag = false;
                }
                if (intent != null)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("INPUTFILENAME", mPlayList.getCurrentFilePathByIndex(mPlayViews[0].getCurrentIndexByPV()));
                    intent.putExtras(bundle);
                    if (flag)
                    {
                        startActivity(intent);
                        return;
                    } else
                    {
                        startActivityForResult(intent, 1);
                        return;
                    }
                }
            }
        }
    }

    private void startPlay()
    {
        if (!isLocalContent())
        {
            Toast toast = Toast.makeText(this, 0x7f0b01b5, 1);
            toast.setDuration(5000);
            toast.show();
        }
        Uri uri = mPlayList.getUri(mPlayList.getCurrentIndex());
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setDataAndType(uri, "video/*");
        startActivity(intent);
    }

    private void startSettingActivity()
    {
        Log.d("PlayActivity", "startSettingActivity");
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/setting/SettingActivity);
        startActivity(intent);
    }

    private void startShow()
    {
        RootPlayView.DragDirection dragdirection;
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onStart();
            PlayView playview = mPlayViews[mCurViewType];
            RootPlayView rootplayview;
            byte byte0;
            if (isThirdPartyPlayer() && isDMPView())
            {
                byte0 = 4;
            } else
            {
                byte0 = 0;
            }
            playview.setVisibility(byte0);
        }
        mBtnPushTo.setEnabled(false);
        mBtnPushBack.setEnabled(false);
        rootplayview = mRootPlayView;
        if (isDMPView())
        {
            if (isRealtimePlayView())
            {
                dragdirection = null;
            } else
            {
                dragdirection = RootPlayView.DragDirection.DirUp;
            }
        } else
        {
            dragdirection = RootPlayView.DragDirection.DirDown;
        }
        rootplayview.setDragDirection(dragdirection);
    }

    private void uninitSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.recycle();
            mStorageTool = null;
        }
    }

    private void uninitUpdownloadService()
    {
        if (mUpDownloadServiceBinder != null)
        {
            unbindService(mUpdownloadConnection);
        }
        mUpDownloadEngine = null;
        mUpDownloadServiceBinder = null;
    }

    private void unregisterClearCacheReceiver()
    {
        Log.v("PlayActivity", "unregisterClearCacheReceiver()");
        if (mClearCacheReceiver != null)
        {
            unregisterReceiver(mClearCacheReceiver);
        }
        mClearCacheReceiver = null;
    }

    private void unregisterSystemEvent()
    {
        if (mStorageTool != null)
        {
            mStorageTool.setOnStorageStatusChangeListener(null);
        }
    }

    private void updateCurrentIndexBar(int i)
    {
        if (mTvCurIndex != null && mPlayList != null)
        {
            mTvCurIndex.setText((new StringBuilder()).append(i + 1).append(" / ").append(mPlayList.getCount()).toString());
        }
    }

    private void updateInfoDialog(int i, IDataSource idatasource)
    {
        String s;
        if (idatasource != null && mInfoView != null && mInfoView.isShown())
        {
            if ((s = getDatailInfo(i, idatasource)) != null && mTvInfoContent != null)
            {
                mTvInfoContent.setText(s);
                return;
            }
        }
    }

    public void OnDefaultDownloadDestinationChanged(String s)
    {
    }

    public void OnDefaultRenderChanged(String s)
    {
    }

    public void OnDefaultServerChanged(String s)
    {
        if (!isLocalContent())
        {
            onBackPressed();
        }
    }

    protected void SDCardUnmount()
    {
        super.SDCardUnmount();
        jumpToListIfNeeded();
    }

    public void cancelDeletingWaitDialog()
    {
        if (mDeletingWaitDialog != null)
        {
            mDeletingWaitDialog.dismiss();
            mDeletingWaitDialog = null;
        }
    }

    protected abstract PlayView createDMCPlayView(ViewGroup viewgroup);

    protected abstract PlayView createDMPPlayView(ViewGroup viewgroup);

    protected abstract DataSourcePlayList createPlayList(IDataSource idatasource);

    protected abstract void destoryPlayList(DataSourcePlayList datasourceplaylist);

    public void doCtrlBarAnimation(boolean flag)
    {
        if (mTitleBar == null || mBottomBar == null)
        {
            return;
        }
        int i = mTitleBar.getHeight();
        int j = mBottomBar.getHeight();
        if (flag)
        {
            TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, -i);
            translateanimation.setDuration(400);
            mTitleBar.startAnimation(translateanimation);
            TranslateAnimation translateanimation1 = new TranslateAnimation(0.0F, 0.0F, 0.0F, j);
            translateanimation1.setDuration(400);
            mBottomBar.startAnimation(translateanimation1);
            return;
        } else
        {
            TranslateAnimation translateanimation2 = new TranslateAnimation(0.0F, 0.0F, -i, 0.0F);
            translateanimation2.setDuration(400);
            mTitleBar.startAnimation(translateanimation2);
            TranslateAnimation translateanimation3 = new TranslateAnimation(0.0F, 0.0F, j, 0.0F);
            translateanimation3.setDuration(400);
            mBottomBar.startAnimation(translateanimation3);
            return;
        }
    }

    void downloadCurrentFile()
    {
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask;
        if (mDataSource != null && mDownloadFacade != null && mPlayList != null)
        {
            if ((downloadtask = mDataSource.makeDownloadTask(mPlayList.getCurrentIndex())) != null)
            {
                downloadtask.priority = 1;
                mDownloadFacade.download(downloadtask);
                return;
            }
        }
    }

    protected IDataSource getDataSource()
        throws IllegalStateException
    {
        com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter datasourcefilter = (com.arcsoft.mediaplus.datasource.DataSourceFactory.DataSourceFilter)getIntent().getParcelableExtra("datasource_filter");
        if (datasourcefilter == null)
        {
            throw new IllegalStateException("Only Support Use filter yet");
        } else
        {
            return DataSourceFactory.instance().getDataSource(datasourcefilter);
        }
    }

    protected abstract int getMediaType();

    protected DataSourcePlayList getPlayList()
    {
        return mPlayList;
    }

    public void hideControlBar()
    {
        while (mTitleBar == null || mBottomBar == null || !isControlBarShown()) 
        {
            return;
        }
        mTitleBar.setVisibility(4);
        mBottomBar.setVisibility(4);
        doCtrlBarAnimation(true);
    }

    public boolean isControlBarShown()
    {
        while (mTitleBar == null || mBottomBar == null || !mTitleBar.isShown() && !mBottomBar.isShown()) 
        {
            return false;
        }
        return true;
    }

    public boolean isDMPView()
    {
        return mCurViewType == 0;
    }

    public boolean isDialogShown()
    {
        return mDeleteDlg != null && mDeleteDlg.isShowing();
    }

    public boolean isDigaActivity()
    {
        return getIntent().getBooleanExtra("Param.isDiga", false);
    }

    public boolean isInfoViewShown()
    {
        if (mInfoView == null)
        {
            return false;
        } else
        {
            return mInfoView.isShown();
        }
    }

    public boolean isLocalContent()
    {
        return getIntent().getIntExtra("Param.isLocalContent", 1) == 1;
    }

    public boolean isRealtimePlayView()
    {
        return false;
    }

    protected abstract boolean isThirdPartyPlayer();

    public boolean isVideoFile(int i)
    {
        if (mDataSource != null)
        {
            MediaItem mediaitem = mDataSource.getItem(i);
            Log.e("FENG", (new StringBuilder()).append("FENG isVideoFile curIndex = ").append(i).toString());
            if (mediaitem != null)
            {
                return mediaitem.videoOrImage;
            }
        }
        return false;
    }

    com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask makeDownloadTask(int i)
    {
        com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask downloadtask = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.DownloadTask();
        downloadtask.mediaClass = 3L;
        downloadtask.dms_uuid = RemoteDBMgr.instance().getCurrentServer();
        downloadtask.mediaId = mDataSource.getLongProp(i, Property.PROP_ID, -1L);
        downloadtask.title = mDataSource.getStringProp(i, Property.PROP_TITLE, null);
        Uri uri = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
        String s = null;
        if (uri != null)
        {
            s = uri.toString();
        }
        downloadtask.uri = s;
        downloadtask.fileSize = mDataSource.getLongProp(i, Property.PROP_SIZE, 0L);
        downloadtask.item_uuid = mDataSource.getRemoteItemUUID(i);
        return downloadtask;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        switch (i)
        {
        case 1: // '\001'
        default:
            super.onActivityResult(i, j, intent);
            break;
        }
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        getWindow().addFlags(0x20000);
    }

    public void onBackPressed()
    {
        if (isInfoViewShown())
        {
            closeInfoView();
        } else
        {
            mIsBackPressed = true;
            if (!mIsStartingEdit)
            {
                static class _cls28
                {

                    static final int $SwitchMap$com$arcsoft$mediaplus$oem$OEMConfig$OEMName[] = new int[com.arcsoft.mediaplus.oem.OEMConfig.OEMName.values().length];

                }

                int _tmp = _cls28..SwitchMap.com.arcsoft.mediaplus.oem.OEMConfig.OEMName[OEMConfig.PROJECT_NAME.ordinal()];
                if (mPlayViews[mCurViewType] != null)
                {
                    mPlayViews[mCurViewType].requestQuit();
                    return;
                }
            }
        }
    }

    public void onBrowseModeChanged(boolean flag)
    {
    }

    public void onConfigurationChanged()
    {
        if (mDevSelector != null)
        {
            mDevSelector.onConfigurationChanged();
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        int i;
        super.onConfigurationChanged(configuration);
        PlayView aplayview[];
        int j;
        if (mTvInfoContent != null)
        {
            if (getResources().getConfiguration().orientation == 2)
            {
                mTvInfoContent.setMaxLines(getResources().getInteger(0x7f0a000a));
                mTvInfoContent.setVerticalScrollBarEnabled(true);
                mTvInfoContent.setMovementMethod(ScrollingMovementMethod.getInstance());
            } else
            {
                mTvInfoContent.setMaxLines(10);
                mTvInfoContent.setVerticalScrollBarEnabled(false);
                mTvInfoContent.setMovementMethod(null);
            }
        }
        i = mRootPlayView.getCurrentGroup();
        if (i >= 0)
        {
            mRootPlayView.uninitAnimation();
        }
        if (i == 0)
        {
            mPlayViews[mCurViewType].setVisibility(4);
        }
        aplayview = mPlayViews;
        j = aplayview.length;
        for (int k = 0; k < j; k++)
        {
            PlayView playview = aplayview[k];
            if (playview != null)
            {
                playview.setConfigurationChanged(configuration);
            }
        }

        if (i != 0) goto _L2; else goto _L1
_L1:
        mRootPlayView.reset(0);
        mRootPlayView.onAnimatationGroupEnd(0);
        mRootPlayView.startAnimation();
_L4:
        ImageView imageview = mBtnPushBack;
        boolean flag = isDMPView();
        byte byte0 = 0;
        if (flag)
        {
            byte0 = 8;
        }
        imageview.setVisibility(byte0);
        onConfigurationChanged();
        return;
_L2:
        if (i == 1)
        {
            mAnimationSetListener.onAnimationGroupEnd(1);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        int i;
        String s;
        try
        {
            mDataSource = getDataSource();
        }
        catch (IllegalStateException illegalstateexception)
        {
            Toast.makeText(this, illegalstateexception.getMessage(), 0).show();
            release(false);
            finish();
            return;
        }
        setVolumeControlStream(3);
        registerClearCacheReceiver();
        initSystemEvent();
        try
        {
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSource.getController();
            icontroller.loadMore(-1);
            icontroller.release();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        mPlayList = createPlayList(mDataSource);
        i = getIntent().getIntExtra("Param.PushTo.CurIndex", 0);
        mCurrentIndex = i;
        mPlayList.setCurrentIndex(i);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerStatusListener);
        DLNA.instance().getRenderManager().registerRenderStatusListener(mRenderStatusListener);
        s = Settings.instance().getDefaultDMRUDN();
        DLNA.instance().getRenderManager().installRender(s);
        Settings.instance().registerSettingChangeListener(this);
        initUpdownloadService();
        mBatteryTool = new BatteryTool(this);
        createAndInitUI();
        initCurViewType();
        if (mFileDelMgr == null)
        {
            mFileDelMgr = new FileDelete(this, mDeleteListener);
            mFileDelMgr.setRelatedDeleteLis(mUpDownloadEngineDeleteLis);
        }
    }

    protected Dialog onCreateDialog(int i, Bundle bundle)
    {
        Log.v("MediaSeeActivity", "onCreateDialog()");
        switch (i)
        {
        default:
            return null;

        case 12291: 
            return (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b00a5).setMessage(0x7f0b00a9).setCancelable(true).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    mPlayViews[mCurViewType].cancelAllUpdownload();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).create();

        case 12292: 
            return (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0b00a6).setMessage(0x7f0b00af).setCancelable(false).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    mPlayViews[mCurViewType].cancelAllUpdownload();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).create();

        case 8194: 
            String as2[] = getResources().getStringArray(0x7f060008);
            String s1 = getString(0x7f0b00d5);
            int i1 = mPlayViews[mCurViewType].getCurrentAudioChannelIndex();
            return (new android.app.AlertDialog.Builder(this)).setSingleChoiceItems(as2, i1 - 1, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    mPlayViews[mCurViewType].selectAudioChannel(j1 + 1);
                    dialoginterface.cancel();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setTitle(s1).create();

        case 8195: 
            String as[] = getResources().getStringArray(0x7f060009);
            String s = getString(0x7f0b00d6);
            int j = mPlayViews[mCurViewType].getAudioTrackNum();
            String as1[] = new String[j];
            for (int k = 0; k < j; k++)
            {
                as1[k] = (new StringBuilder()).append(as[0]).append(k + 1).toString();
            }

            int l = mPlayViews[mCurViewType].getCurrentAudioTrackIndex();
            return (new android.app.AlertDialog.Builder(this)).setSingleChoiceItems(as1, l, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    mPlayViews[mCurViewType].setCurrentAudioTrackIndex(j1);
                    dialoginterface.cancel();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setTitle(s).create();

        case 12293: 
            return (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0b00b0).setPositiveButton(0x7f0b001d, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    mPlayViews[mCurViewType].addUpdownload(false);
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).setNegativeButton(0x7f0b001e, new android.content.DialogInterface.OnClickListener() {

                final PlayActivity this$0;

                public void onClick(DialogInterface dialoginterface, int j1)
                {
                    dialoginterface.cancel();
                }

            
            {
                this$0 = PlayActivity.this;
                super();
            }
            }).create();
        }
    }

    public final boolean onCreateOptionsMenu(Menu menu)
    {
        return false;
    }

    protected void onDestroy()
    {
        String s = Settings.instance().getDefaultDMRUDN();
        DLNA.instance().getRenderManager().uninstallRender(s);
        release(true);
        unregisterClearCacheReceiver();
        uninitUpdownloadService();
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerStatusListener);
        DLNA.instance().getRenderManager().unregisterRenderStatusListener(mRenderStatusListener);
        uninitSystemEvent();
        Settings.instance().unregisterSettingChangeListener(this);
        android.content.SharedPreferences.Editor editor = getSharedPreferences("position", 0).edit();
        editor.clear();
        editor.commit();
        if (mDevSelector != null)
        {
            mDevSelector.destroy();
        }
        if (mDownloadFacade != null)
        {
            mDownloadFacade.destroy();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR lookupswitch 5: default 52
    //                   4: 227
    //                   19: 73
    //                   20: 132
    //                   24: 191
    //                   25: 209;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
        break MISSING_BLOCK_LABEL_227;
_L1:
        boolean flag = mPlayViews[mCurViewType].onKeyDown(i, keyevent);
_L7:
        boolean flag6;
        if (flag)
        {
            return flag;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
_L3:
        flag6 = isRealtimePlayView();
        flag = false;
        if (!flag6)
        {
            boolean flag7 = isDMPView();
            flag = false;
            if (flag7)
            {
                boolean flag8 = mBtnPushTo.isEnabled();
                flag = false;
                if (flag8)
                {
                    mBtnPushTo.setPressed(true);
                    resetHideControlTimerEx();
                    flag = true;
                }
            }
        }
          goto _L7
_L4:
        boolean flag3 = isRealtimePlayView();
        flag = false;
        if (!flag3)
        {
            boolean flag4 = isDMPView();
            flag = false;
            if (!flag4)
            {
                boolean flag5 = mBtnPushBack.isEnabled();
                flag = false;
                if (flag5)
                {
                    mBtnPushBack.setPressed(true);
                    resetHideControlTimerEx();
                    flag = true;
                }
            }
        }
          goto _L7
_L5:
        boolean flag2 = isDMPView();
        flag = false;
        if (!flag2)
        {
            flag = true;
        }
          goto _L7
_L6:
        boolean flag1 = isDMPView();
        flag = false;
        if (!flag1)
        {
            flag = true;
        }
          goto _L7
        if (isInfoViewShown())
        {
            closeInfoView();
            return true;
        }
        doBack();
        flag = true;
          goto _L7
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        i;
        JVM INSTR tableswitch 19 25: default 44
    //                   19 112
    //                   20 177
    //                   21 44
    //                   22 44
    //                   23 44
    //                   24 68
    //                   25 90;
           goto _L1 _L2 _L3 _L1 _L1 _L1 _L4 _L5
_L3:
        break MISSING_BLOCK_LABEL_177;
_L1:
        boolean flag1 = mPlayViews[mCurViewType].onKeyUp(i, keyevent);
_L6:
        if (flag1)
        {
            return flag1;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
_L4:
        flag1 = mPlayViews[mCurViewType].setVolume(mVolumeView, true);
          goto _L6
_L5:
        flag1 = mPlayViews[mCurViewType].setVolume(mVolumeView, false);
          goto _L6
_L2:
        boolean flag4 = isRealtimePlayView();
        flag1 = false;
        if (!flag4)
        {
            boolean flag5 = isDMPView();
            flag1 = false;
            if (flag5)
            {
                boolean flag6 = mBtnPushTo.isEnabled();
                flag1 = false;
                if (flag6)
                {
                    flag1 = mBtnPushTo.performClick();
                    mBtnPushTo.setPressed(false);
                }
            }
        }
          goto _L6
        boolean flag = isRealtimePlayView();
        flag1 = false;
        if (!flag)
        {
            boolean flag2 = isDMPView();
            flag1 = false;
            if (!flag2)
            {
                boolean flag3 = mBtnPushBack.isEnabled();
                flag1 = false;
                if (flag3)
                {
                    flag1 = mBtnPushBack.performClick();
                    mBtnPushBack.setPressed(false);
                }
            }
        }
          goto _L6
    }

    public final boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch (menuitem.getItemId())
        {
        default:
            return super.onOptionsItemSelected(menuitem);

        case 304: 
            mPlayViews[mCurViewType].addUpdownload(true);
            return true;

        case 305: 
            mPlayViews[mCurViewType].cancelUpdownload(true);
            return true;

        case 306: 
            if (mPlayViews[mCurViewType].isNeedConfirm())
            {
                showDialog(12293);
                return true;
            } else
            {
                mPlayViews[mCurViewType].addUpdownload(false);
                return true;
            }

        case 307: 
            mPlayViews[mCurViewType].cancelUpdownload(false);
            return true;

        case 206: 
            startActivity(new Intent("MUVI.mediaplus.intent.action.UpDownload"));
            return true;

        case 202: 
            removeDialog(12291);
            showDialog(12291);
            return true;

        case 402: 
            removeDialog(8194);
            showDialog(8194);
            return true;

        case 403: 
            removeDialog(8195);
            showDialog(8195);
            return true;

        case 308: 
            startSettingActivity();
            return true;

        case 501: 
            startActivity(new Intent(this, com/arcsoft/mediaplus/setting/SlideShowSettingActivity));
            return true;

        case 601: 
            Intent intent = new Intent(this, com/arcsoft/mediaplus/setting/SettingListActivity);
            intent.putExtra("settingtype", 1);
            startActivity(intent);
            return true;
        }
    }

    protected void onPause()
    {
        mLeavingOrientation = getResources().getConfiguration().orientation;
        mResumeInfo.mIsActivityResume = false;
        mResumeInfo.mStartDMPAfterResume = false;
        mResumeInfo.mPlayPosition = 0L;
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onActivityPause();
        }
        mRootPlayView.pauseAnimation();
        if (mBatteryTool != null)
        {
            mBatteryTool.setOnBatteryChangeListener(null);
        }
        unregisterSystemEvent();
        if (mToken != null)
        {
            Log.w("DLNA Service", "Release Token in Play activity");
            DLNA.instance().releaseUserToken(mToken);
            mToken = null;
        }
        super.onPause();
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.clear();
        if (mCurViewType == 0)
        {
            menu.add(0, 601, 0, 0x7f0b0078).setIcon(0x7f02018b);
        }
        if (mCurViewType != 0 || isRealtimePlayView()) goto _L2; else goto _L1
_L1:
        if (!isLocalContent()) goto _L4; else goto _L3
_L3:
        mPlayViews[mCurViewType].getUpdownloadState(true);
        JVM INSTR tableswitch 0 2: default 96
    //                   0 141
    //                   1 167
    //                   2 167;
           goto _L2 _L5 _L6 _L6
_L2:
        if (mCurViewType == 0)
        {
            menu.add(0, 206, 0, 0x7f0b00a7).setIcon(0x7f020199);
        }
        mPlayViews[mCurViewType].prepareOptionMenu(menu);
        return true;
_L5:
        menu.add(0, 304, 0, 0x7f0b002d).setIcon(0x7f02018f);
        continue; /* Loop/switch isn't completed */
_L6:
        menu.add(0, 305, 0, 0x7f0b002e).setIcon(0x7f02018f);
        if (true) goto _L2; else goto _L4
_L4:
        switch (mPlayViews[mCurViewType].getUpdownloadState(false))
        {
        case 0: // '\0'
            menu.add(0, 306, 0, 0x7f0b002c).setIcon(0x7f02018f);
            break;

        case 1: // '\001'
        case 2: // '\002'
            menu.add(0, 307, 0, 0x7f0b001e).setIcon(0x7f02018f);
            break;
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    protected void onResume()
    {
        jumpToListIfNeeded();
        super.onResume();
        Log.w("DLNA Service", "Create Token in Play activity");
        mToken = DLNA.instance().lockUserToken();
        mResumeInfo.mIsActivityResume = true;
        if (!isDMPView() && NetworkUtil.getLocalIpViaWiFi(this) == null)
        {
            Toast.makeText(this, 0x7f0b0016, 0).show();
            finish();
            return;
        }
        registerSystemEvent();
        if (mBatteryTool != null)
        {
            mBatteryTool.setOnBatteryChangeListener(mBatteryChangeListener);
        }
        if (mRootPlayView.getCurrentGroup() == 0)
        {
            mRootPlayView.reverseAnimation();
        }
        mRootPlayView.resumeAnimation();
        if (mPlayViews[mCurViewType] != null)
        {
            if (mResumeInfo.mStartDMPAfterResume)
            {
                if (!isDMPView())
                {
                    Log.w("PlayActivity", "Start a non - DMP View when playactivity resumed");
                }
                startShow();
                shown(mResumeInfo.mPlayPosition);
            } else
            {
                mPlayViews[mCurViewType].setUpDownloadEngine(mUpDownloadEngine);
                mPlayViews[mCurViewType].onActivityResume();
            }
        }
        resetHideControlTimerEx();
    }

    public void onSortModeChanged(boolean flag)
    {
    }

    protected void onStart()
    {
        super.onStart();
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onActivityStart();
        }
        if (mLeavingOrientation != -1 && getResources().getConfiguration().orientation != mLeavingOrientation)
        {
            setRequestedOrientation(mLeavingOrientation);
            setRequestedOrientation(-1);
            mLeavingOrientation = -1;
        }
    }

    protected void onStop()
    {
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onActivityStop();
        }
        super.onStop();
    }

    public void onTVStreamingResolutionChange(boolean flag)
    {
    }

    public void onWindowFocusChanged(boolean flag)
    {
        Log.v("PlayActivity", (new StringBuilder()).append("onWindowFocusChanged =").append(flag).toString());
        if (mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].onWindowFocusChanged(flag);
        }
        super.onWindowFocusChanged(flag);
    }

    protected void playAnimation()
    {
        if (isThirdPartyPlayer() && isDMPView())
        {
            mRootPlayView.reset(1);
        } else
        {
            mRootPlayView.reset(0);
        }
        mRootPlayView.startAnimation();
    }

    void refreshDownloadBtnVisible()
    {
        if (mBtnDownload != null)
        {
            ImageView imageview = mBtnDownload;
            byte byte0;
            if (isLocalContent())
            {
                byte0 = 8;
            } else
            {
                byte0 = 0;
            }
            imageview.setVisibility(byte0);
        }
    }

    protected void release(boolean flag)
    {
        releaseUI();
        if (mPlayList != null)
        {
            destoryPlayList(mPlayList);
            mPlayList = null;
        }
        if (mDataSource != null)
        {
            releaseDataSource(mDataSource);
            mDataSource = null;
        }
    }

    protected void releaseDataSource(IDataSource idatasource)
    {
        DataSourceFactory.instance().releaseDataSource(idatasource);
    }

    protected void resetHideControlTimerEx()
    {
        if (isControlBarShown() && mPlayViews[mCurViewType] != null)
        {
            mPlayViews[mCurViewType].resetHideControlTimerEx();
        }
    }

    public void setCenterPlayBtnVisible(boolean flag)
    {
        boolean flag1 = true;
        boolean flag2 = isVideoFile(mCurrentIndex);
        if (mBtnCenterPlay != null)
        {
            boolean flag3;
            ImageView imageview;
            int i;
            if (flag2 && !flag)
            {
                flag3 = flag1;
            } else
            {
                flag3 = false;
            }
            imageview = mBtnCenterPlay;
            if (flag3)
            {
                i = 0;
            } else
            {
                i = 4;
            }
            imageview.setVisibility(i);
        }
        setEditBtnVisible(flag2);
        if (flag)
        {
            flag1 = false;
        }
        setControlBarClickable(flag1);
    }

    void setEditBtnVisible(boolean flag)
    {
        if (mBtnEdit != null)
        {
            boolean flag1 = isLocalContent();
            boolean flag2 = false;
            if (flag1)
            {
                flag2 = false;
                if (flag)
                {
                    flag2 = true;
                    mBtnEdit.setImageResource(0x7f020119);
                }
            }
            ImageView imageview = mBtnEdit;
            int i;
            if (flag2)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            imageview.setVisibility(i);
        }
    }

    protected void setOnDragListener(RootPlayView.IOnDragListener iondraglistener)
    {
        if (mRootPlayView != null)
        {
            mRootPlayView.setOnDragListener(iondraglistener);
        }
    }

    public void setPushBackVisable(boolean flag)
    {
        if (mBtnPushBack == null)
        {
            return;
        }
        ImageView imageview = mBtnPushBack;
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        imageview.setVisibility(i);
        mBtnPushBack.setEnabled(flag);
    }

    public void setPushtoVisable(boolean flag)
    {
        if (mBtnPushTo == null)
        {
            return;
        }
        ImageView imageview = mBtnPushTo;
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        imageview.setVisibility(i);
        mBtnPushTo.setEnabled(flag);
    }

    public void showControlBar()
    {
        while (mTitleBar == null || mBottomBar == null || isControlBarShown()) 
        {
            return;
        }
        mTitleBar.setVisibility(0);
        mBottomBar.setVisibility(0);
        doCtrlBarAnimation(false);
    }

    public void showDeletingWaitDialog()
    {
        cancelDeletingWaitDialog();
        if (mDeletingWaitDialog == null)
        {
            mDeletingWaitDialog = new LoadingDialog(this, 0x7f0d0004);
            mDeletingWaitDialog.setText(0x7f0b0120);
        }
        try
        {
            mDeletingWaitDialog.setCancelable(false);
            mDeletingWaitDialog.show();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        Log.e("zdf1", "showDeletingWaitDialog ERROR");
    }

    protected void svcDisable()
    {
        if (mPlayViews[0] != null)
        {
            mPlayViews[0].svcDisable();
        }
    }

    protected void svcReady()
    {
        if (mPlayViews[0] != null)
        {
            mPlayViews[0].setUpDownloadEngine(mUpDownloadEngine);
            mPlayViews[0].svcReady();
        }
    }

    protected void switchView()
    {
        startDismiss();
        PlayView.PlaySession playsession = dismissed();
        mCurViewType = 1 ^ mCurViewType;
        startShow();
        long l;
        if (playsession == null)
        {
            l = 0L;
        } else
        {
            l = playsession.position;
        }
        shown(l);
    }

    public void updateCurrentUI(int i, boolean flag)
    {
        mCurrentIndex = i;
        updateCurrentIndexBar(i);
        updateInfoDialog(i, mDataSource);
        refreshDownloadBtnVisible();
        if (flag)
        {
            setCenterPlayBtnVisible(false);
        }
    }



/*
    static UpDownloadEngine access$002(PlayActivity playactivity, UpDownloadEngine updownloadengine)
    {
        playactivity.mUpDownloadEngine = updownloadengine;
        return updownloadengine;
    }

*/








/*
    static boolean access$1502(PlayActivity playactivity, boolean flag)
    {
        playactivity.mIsStartingEdit = flag;
        return flag;
    }

*/




/*
    static RenderDevSelector access$1702(PlayActivity playactivity, RenderDevSelector renderdevselector)
    {
        playactivity.mDevSelector = renderdevselector;
        return renderdevselector;
    }

*/















/*
    static DLNAService access$2902(PlayActivity playactivity, DLNAService dlnaservice)
    {
        playactivity.mUpDownloadServiceBinder = dlnaservice;
        return dlnaservice;
    }

*/




/*
    static boolean access$302(PlayActivity playactivity, boolean flag)
    {
        playactivity.mIsDMSDeleteFailed = flag;
        return flag;
    }

*/





/*
    static long access$502(PlayActivity playactivity, long l)
    {
        playactivity.mClickedTime = l;
        return l;
    }

*/




/*
    static int access$702(PlayActivity playactivity, int i)
    {
        playactivity.mCurViewType = i;
        return i;
    }

*/


}
