// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.adk.atv.UploadManager;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.mediaplus.listview.IItemListView;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.AbsTaskItem;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager, MediaListAdapter, ItemHolder

public class MediaPlusListView extends GridView
    implements IItemListView
{
    private static final class PrefetchType extends Enum
    {

        private static final PrefetchType $VALUES[];
        public static final PrefetchType Prefetch_ScrollDown;
        public static final PrefetchType Prefetch_ScrollStop;
        public static final PrefetchType Prefetch_ScrollUp;

        public static PrefetchType valueOf(String s)
        {
            return (PrefetchType)Enum.valueOf(com/arcsoft/mediaplus/MediaPlusListView$PrefetchType, s);
        }

        public static PrefetchType[] values()
        {
            return (PrefetchType[])$VALUES.clone();
        }

        static 
        {
            Prefetch_ScrollDown = new PrefetchType("Prefetch_ScrollDown", 0);
            Prefetch_ScrollUp = new PrefetchType("Prefetch_ScrollUp", 1);
            Prefetch_ScrollStop = new PrefetchType("Prefetch_ScrollStop", 2);
            PrefetchType aprefetchtype[] = new PrefetchType[3];
            aprefetchtype[0] = Prefetch_ScrollDown;
            aprefetchtype[1] = Prefetch_ScrollUp;
            aprefetchtype[2] = Prefetch_ScrollStop;
            $VALUES = aprefetchtype;
        }

        private PrefetchType(String s, int i)
        {
            super(s, i);
        }
    }


    private final String TAG;
    protected boolean bAdded;
    private final int mAllowShowCount;
    protected Context mContext;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataBuildListener mDataBuildListener;
    protected int mDataBuilding;
    private final com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener mDataChangeListener;
    protected PictureDataSource mDataSource;
    protected com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController mDataSourceController;
    protected Drawable mDefaultDrawable;
    protected TextView mEmptyText;
    protected Handler mHandler = new Handler() {

        final MediaPlusListView this$0;

        public void handleMessage(Message message)
        {
            super.handleMessage(message);
            switch (message.what)
            {
            default:
                return;

            case 1537: 
                updownloadStart(message.obj);
                return;

            case 1538: 
                updownloadProgress(message.arg1, message.obj);
                return;

            case 1539: 
                updownloadFinish(message.arg1, message.obj);
                return;

            case 1540: 
                updownloadExist();
                break;
            }
        }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
    };
    private int mLastFirstVisibleIndex;
    private int mLastVisibleCount;
    protected MediaListAdapter mListAdapter;
    protected com.arcsoft.mediaplus.listview.IItemListView.IListOpListener mOpListener;
    private final PrefetchType mPrefetchType;
    protected ViewGroup mRootView;
    private final com.arcsoft.adk.atv.ServerManager.IServerDLNAUploadListener mServerUploadListener;
    private final com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener mSettingChangedListener;
    private final boolean mShowMoreBtn;
    protected UpDownloadEngine mUpDownloadEngine;
    private final com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener mUpdownloadListener;

    public MediaPlusListView(Context context)
    {
        super(context);
        TAG = "MediaPlusListView";
        mContext = null;
        mListAdapter = null;
        mDataBuilding = 1;
        mDataSourceController = null;
        mDataSource = null;
        mEmptyText = null;
        bAdded = false;
        mRootView = null;
        mOpListener = null;
        mLastFirstVisibleIndex = -1;
        mLastVisibleCount = -1;
        mAllowShowCount = 65535;
        mShowMoreBtn = false;
        mPrefetchType = PrefetchType.Prefetch_ScrollStop;
        mUpDownloadEngine = null;
        mDefaultDrawable = null;
        mUpdownloadListener = new com.arcsoft.mediaplus.updownload.service.UpDownloadEngine.IOnUpDownloadListener() {

            final MediaPlusListView this$0;

            public void onProgress(String s, String s1, long l, long l1)
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

            public void onUpDownloadFinish(String s, String s1, Object obj, int i)
            {
                Message message = mHandler.obtainMessage(1539);
                message.arg1 = i;
                message.obj = obj;
                mHandler.sendMessage(message);
            }

            public void onUpDownloadStart(String s, String s1)
            {
                Message message = mHandler.obtainMessage(1537);
                message.obj = s1;
                mHandler.sendMessage(message);
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        };
        mDataChangeListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataChangeListener() {

            final MediaPlusListView this$0;

            public void onCountChanged(int i, int j)
            {
                Log.e("FENG", (new StringBuilder()).append("FENG onCountChanged() newCount = ").append(i).append(", oldCount = ").append(j).toString());
                Log.v("MediaPlusListView", (new StringBuilder()).append("=== onCountChanged start === oldCount ").append(j).append(" newCount ").append(i).toString());
                if (i > 0 && i != j)
                {
                    ((MediaPlusActivity)mContext).clearMarkView();
                }
                onListCountChanged(i, j);
                clearListView(false);
                prefetchInScroll(PrefetchType.Prefetch_ScrollStop);
                setFocusable(true);
                requestFocus();
            }

            public void onDataChanged(int i, Property property)
            {
                Log.d("MediaPlusListView", (new StringBuilder()).append("onDataChanged : index = ").append(i).append(", property = ").append(property).toString());
                if (i >= getFirstVisiblePosition() && i <= getLastVisiblePosition() && property == PictureDataSource.PROP_BITMAP)
                {
                    int j = getChildCount();
                    int k = 0;
                    while (k < j) 
                    {
                        View view = getChildAt(k);
                        Integer integer;
                        if (view == null)
                        {
                            integer = null;
                        } else
                        {
                            integer = (Integer)view.getTag();
                        }
                        if (integer == null || integer.intValue() != i)
                        {
                            k++;
                        } else
                        {
                            Log.d("MediaPlusListView", (new StringBuilder()).append("onDataChanged index = ").append(i).toString());
                            fillHolder(createHolder(view), i);
                            return;
                        }
                    }
                }
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        };
        mDataBuildListener = new com.arcsoft.mediaplus.datasource.IDataSource.OnDataBuildListener() {

            final MediaPlusListView this$0;

            public void onDataBuiltBegin()
            {
                mDataBuilding = 0;
                Log.w("MediaPlusListView", "=== onDataBuiltBegin === ");
                ((MediaPlusActivity)mContext).showLoadingDialog();
            }

            public void onDataBuiltFinish()
            {
                Log.w("MediaPlusListView", "=== onDataBuiltFinish === ");
                ((MediaPlusActivity)mContext).dismissLoadingDialog();
                mDataBuilding = 1;
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        };
        mServerUploadListener = new com.arcsoft.adk.atv.ServerManager.IServerDLNAUploadListener() {

            final MediaPlusListView this$0;

            public void onServerGetProtocolInfo(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, int i)
            {
                if (s == Settings.instance().getDefaultDMSUDN());
            }

            public void onXGetDLNAUploadProfiles(String s, String s1, int i)
            {
                if (s == Settings.instance().getDefaultDMSUDN());
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        };
        mSettingChangedListener = new com.arcsoft.mediaplus.setting.Settings.IOnSettingChangedListener() {

            final MediaPlusListView this$0;

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
                this$0 = MediaPlusListView.this;
                super();
            }
        };
        mContext = context;
    }

    public MediaPlusListView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "MediaPlusListView";
        mContext = null;
        mListAdapter = null;
        mDataBuilding = 1;
        mDataSourceController = null;
        mDataSource = null;
        mEmptyText = null;
        bAdded = false;
        mRootView = null;
        mOpListener = null;
        mLastFirstVisibleIndex = -1;
        mLastVisibleCount = -1;
        mAllowShowCount = 65535;
        mShowMoreBtn = false;
        mPrefetchType = PrefetchType.Prefetch_ScrollStop;
        mUpDownloadEngine = null;
        mDefaultDrawable = null;
        mUpdownloadListener = new _cls1();
        mDataChangeListener = new _cls2();
        mDataBuildListener = new _cls3();
        mServerUploadListener = new _cls4();
        mSettingChangedListener = new _cls8();
        mContext = context;
    }

    public MediaPlusListView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        TAG = "MediaPlusListView";
        mContext = null;
        mListAdapter = null;
        mDataBuilding = 1;
        mDataSourceController = null;
        mDataSource = null;
        mEmptyText = null;
        bAdded = false;
        mRootView = null;
        mOpListener = null;
        mLastFirstVisibleIndex = -1;
        mLastVisibleCount = -1;
        mAllowShowCount = 65535;
        mShowMoreBtn = false;
        mPrefetchType = PrefetchType.Prefetch_ScrollStop;
        mUpDownloadEngine = null;
        mDefaultDrawable = null;
        mUpdownloadListener = new _cls1();
        mDataChangeListener = new _cls2();
        mDataBuildListener = new _cls3();
        mServerUploadListener = new _cls4();
        mSettingChangedListener = new _cls8();
        mContext = context;
    }

    private boolean isDMSContainDtcp(Uri uri, int i)
    {
        ArrayList arraylist;
        boolean flag;
        arraylist = mDataSource.getRemoteItemResourceDesc(i);
        flag = false;
        if (arraylist == null) goto _L2; else goto _L1
_L1:
        int j;
        j = arraylist.size();
        flag = false;
        if (j <= 0) goto _L2; else goto _L3
_L3:
        int k = 0;
_L8:
        int l;
        l = arraylist.size();
        flag = false;
        if (k >= l) goto _L2; else goto _L4
_L4:
        String s = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)arraylist.get(k)).m_strProtocolInfo;
        if (!s.contains("DTCP1HOST") || !s.contains("DTCP1PORT")) goto _L6; else goto _L5
_L5:
        flag = true;
_L2:
        return flag;
_L6:
        k++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private void prefetchInScroll(PrefetchType prefetchtype)
    {
        if (mDataSourceController != null)
        {
            int i = getFirstVisiblePosition();
            int j = getLastVisiblePosition();
            int k = j - i;
            int l;
            int i1;
            if (prefetchtype == PrefetchType.Prefetch_ScrollUp)
            {
                i -= k;
            } else
            if (prefetchtype == PrefetchType.Prefetch_ScrollDown)
            {
                j += k;
            }
            l = Math.max(i, 0);
            i1 = Math.min(j, -1 + getCount());
            if (i1 >= l)
            {
                com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController picturedatasourcecontroller = mDataSourceController;
                Property aproperty[] = new Property[1];
                aproperty[0] = PictureDataSource.PROP_BITMAP;
                picturedatasourcecontroller.prefetch(l, i1, aproperty);
                return;
            }
        }
    }

    public boolean addListView()
    {
        if (mRootView != null && !bAdded)
        {
            mRootView.addView(this, 0);
            bAdded = true;
        }
        if (mEmptyText != null)
        {
            mEmptyText.setVisibility(0);
        }
        Settings.instance().registerSettingChangeListener(mSettingChangedListener);
        return false;
    }

    public void addUpdownload(boolean flag, int i)
    {
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
            Uri uri = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
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

    public void changeGridLayoutParams()
    {
    }

    protected void clearListView(boolean flag)
    {
        int i = getChildCount();
        int j = 0;
        while (j < i) 
        {
            View view = getChildAt(j);
            if (view != null)
            {
                view.setTag(null);
            }
            j++;
        }
        if (flag)
        {
            mLastFirstVisibleIndex = -1;
            mLastVisibleCount = 0;
            invalidateViews();
            return;
        } else
        {
            invalidateViews();
            return;
        }
    }

    protected void construct(Context context)
    {
        setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            final MediaPlusListView this$0;

            public void onItemSelected(AdapterView adapterview, View view, int i, long l)
            {
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        });
        setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {

            final MediaPlusListView this$0;

            public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
            {
                if (i == 65535)
                {
                    return true;
                }
                if (mOpListener != null)
                {
                    mOpListener.OnItemLongPress(i);
                }
                return false;
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        });
        setOnScrollListener(new android.widget.AbsListView.OnScrollListener() {

            final MediaPlusListView this$0;

            public void onScroll(AbsListView abslistview, int i, int j, int k)
            {
                while (mLastFirstVisibleIndex == i && mLastVisibleCount == j || j <= 0) 
                {
                    return;
                }
                PrefetchType prefetchtype;
                if (mLastFirstVisibleIndex > i)
                {
                    prefetchtype = PrefetchType.Prefetch_ScrollUp;
                } else
                {
                    prefetchtype = PrefetchType.Prefetch_ScrollDown;
                }
                mLastFirstVisibleIndex = i;
                prefetchInScroll(prefetchtype);
            }

            public void onScrollStateChanged(AbsListView abslistview, int i)
            {
                if (i == 0)
                {
                    if (mDataSourceController != null && mDataSource != null && mDataSource.isEnable())
                    {
                        mDataSourceController.slowDown(false);
                    }
                    prefetchInScroll(PrefetchType.Prefetch_ScrollStop);
                } else
                if (mDataSourceController != null && mDataSource != null && mDataSource.isResume())
                {
                    mDataSourceController.slowDown(true);
                    return;
                }
            }

            
            {
                this$0 = MediaPlusListView.this;
                super();
            }
        });
    }

    protected ItemHolder createHolder(View view)
    {
        return null;
    }

    public void doRotate(Configuration configuration)
    {
        if (configuration.orientation == 2)
        {
            setNumColumns(5);
            return;
        } else
        {
            setNumColumns(-1);
            return;
        }
    }

    protected void fillHolder(ItemHolder itemholder, int i)
    {
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

    public int getUpdownloadState(boolean flag, int i)
    {
        Uri uri;
        String s;
        if (mUpDownloadEngine == null || mDataSource == null)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        uri = (Uri)mDataSource.getObjectProp(i, Property.PROP_URI, null);
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

    protected View getViewFromUri(Uri uri)
    {
        return null;
    }

    public void init(ViewGroup viewgroup, boolean flag)
    {
    }

    protected void initDefault()
    {
        if (mDefaultDrawable != null)
        {
            mDefaultDrawable = null;
        }
        mDefaultDrawable = mContext.getResources().getDrawable(0x7f0202be);
    }

    public void invalidateViews()
    {
        super.invalidateViews();
    }

    public boolean isNeedConfirm(int i)
    {
        return false;
    }

    public boolean isOpenGl()
    {
        return false;
    }

    public void onAlphabet(String s, int i)
    {
        if (i < 0 || i >= getCount())
        {
            return;
        } else
        {
            setSelection(i);
            return;
        }
    }

    protected void onListCountChanged(int i, int j)
    {
    }

    public void onPause()
    {
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
        smoothScrollBy(0, 0);
    }

    public void onResume()
    {
        if (mDataSourceController != null)
        {
            return;
        }
        if (mDataSource != null)
        {
            mDataSourceController = (com.arcsoft.mediaplus.datasource.PictureDataSource.PictureDataSourceController)mDataSource.getController();
            mDataSourceController.setEnable(true);
            mDataSourceController.resume();
        }
        mDataBuilding = 1;
        prefetchInScroll(PrefetchType.Prefetch_ScrollStop);
        clearListView(false);
    }

    protected void releaseHolder(ItemHolder itemholder, int i)
    {
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

    public void setDataSource(IDataSource idatasource)
    {
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
            mDataSource.init();
            com.arcsoft.mediaplus.datasource.IDataSource.IController icontroller = mDataSource.getController();
            icontroller.loadMore(-1);
            icontroller.sort(Property.PROP_MODIFIED_TIME, true);
            icontroller.release();
            mDataSource.registerOnDataChangeListener(mDataChangeListener);
            mDataSource.registerOnDataBuildListener(mDataBuildListener);
            return;
        }
    }

    public void setItemVisibleInScreen(int i)
    {
        if (i < 0 || i >= getCount())
        {
            return;
        }
        int j = getFirstVisiblePosition();
        int k = getLastVisiblePosition();
        if (i >= j || i <= k)
        {
            smoothScrollToPosition(j, 0);
            return;
        } else
        {
            smoothScrollToPosition(i, 0);
            return;
        }
    }

    public void setListItemOpListener(com.arcsoft.mediaplus.listview.IItemListView.IListOpListener ilistoplistener)
    {
        mOpListener = ilistoplistener;
    }

    public void setUpDownloadContext(UpDownloadEngine updownloadengine)
    {
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
            mLastFirstVisibleIndex = -1;
            mLastVisibleCount = 0;
            return;
        }
    }

    public void switchUpdownloadMode(boolean flag)
    {
        if (mDataSourceController == null);
    }

    public void uninit()
    {
        mContext = null;
        onPause();
        if (mDataSource != null)
        {
            mDataSource.unregisterOnDataChangeListener(mDataChangeListener);
            mDataSource.unregisterOnDataBuildListener(mDataBuildListener);
            mDataSource.unInit();
            mDataSource = null;
            mDataSourceController = null;
        }
        mRootView = null;
        mListAdapter = null;
        setAdapter(null);
        if (mDefaultDrawable != null)
        {
            mDefaultDrawable = null;
        }
        if (mEmptyText != null)
        {
            mEmptyText = null;
        }
        destroyDrawingCache();
    }

    protected void updateSelector(View view, int i)
    {
        MediaItem mediaitem = mDataSource.getDataSource().getItem(i);
        if (ListViewManager.isSelectedItem(Integer.valueOf(i)))
        {
            ListViewManager.removeSelectedItem(Integer.valueOf(i));
            ((ImageView)view.findViewById(0x7f0900c7)).setVisibility(8);
        } else
        {
            ListViewManager.putSelectedItem(Integer.valueOf(i), mediaitem);
            ((ImageView)view.findViewById(0x7f0900c7)).setVisibility(0);
        }
        ((MediaPlusActivity)mContext).refreshSelectorTitle(ListViewManager.getSelectedItemsNum());
    }

    protected void updownloadExist()
    {
    }

    protected void updownloadFinish(int i, Object obj)
    {
        if (i == 817 || i == 819)
        {
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, i, new Object[0]);
        } else
        if (obj != null)
        {
            if (i == 911)
            {
                Context context1 = mContext;
                Object aobj1[] = new Object[1];
                aobj1[0] = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)obj).filePath;
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context1, 0, i, aobj1);
            } else
            if (i == 1015)
            {
                String s1 = Uri.decode(((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)obj).request.uri);
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 1, i, new Object[] {
                    s1
                });
            } else
            {
                Context context = mContext;
                int j;
                Object aobj[];
                String s;
                if (obj instanceof com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                aobj = new Object[1];
                if (obj instanceof com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)
                {
                    s = ((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)obj).request.title;
                } else
                {
                    s = ((com.arcsoft.mediaplus.updownload.UploadPoolDriver.UploadResult)obj).request.title;
                }
                aobj[0] = s;
                com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(context, j, i, aobj);
            }
            if (mUpDownloadEngine != null && mUpDownloadEngine.getAllTaskCount() == 0)
            {
                Toast.makeText(mContext, mContext.getString(0x7f0b00ae), 0).show();
            }
            invalidateViews();
            return;
        }
    }

    protected void updownloadProgress(int i, Object obj)
    {
    }

    protected void updownloadStart(Object obj)
    {
    }




/*
    static int access$102(MediaPlusListView mediapluslistview, int i)
    {
        mediapluslistview.mLastFirstVisibleIndex = i;
        return i;
    }

*/

}
