// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.MediaPlusApplication;
import com.arcsoft.mediaplus.ViewManager;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.picture.opengl.BitmapTexture;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

public class RemoteListViewGL extends ListViewGL
    implements com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IServerChangedListener
{

    String mLastDMSString;
    private final com.arcsoft.mediaplus.DownloadFacade.IPreDownloadListener mPreDownloadListener = new com.arcsoft.mediaplus.DownloadFacade.IPreDownloadListener() {

        final RemoteListViewGL this$0;

        public void onDownloadBegin()
        {
            requestRender();
            ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(isFileDownloading());
        }

        public void onDownloadFinish(ArrayList arraylist)
        {
        }

            
            {
                this$0 = RemoteListViewGL.this;
                super();
            }
    };

    public RemoteListViewGL(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastDMSString = "";
        RemoteDBMgr.instance().setServerStatusListener(this);
        if (mDownloadFacade != null)
        {
            mDownloadFacade.setPreDownloadListener(mPreDownloadListener);
        }
    }

    private String GetDmsName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
        if (s != null)
        {
            if ((mediaserverdesc = DLNA.instance().getServerManager().getServerDesc(s)) != null)
            {
                return mediaserverdesc.m_strFriendlyName;
            }
        }
        return null;
    }

    private void fillDownloadIcon(MediaItem mediaitem, Rect rect, int i)
    {
        byte byte0;
        int j;
        int k;
        byte0 = -1;
        j = 0;
        k = 0;
        i;
        JVM INSTR tableswitch 1 4: default 40
    //                   1 47
    //                   2 60
    //                   3 86
    //                   4 73;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        if (-1 != byte0) goto _L7; else goto _L6
_L6:
        return;
_L2:
        byte0 = 7;
        j = 0;
        k = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        byte0 = 8;
        j = 0;
        k = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 9;
        j = 0;
        k = 0;
        continue; /* Loop/switch isn't completed */
_L4:
        BitmapTexture bitmaptexture;
        byte0 = 10;
        j = (int)getResources().getDimension(0x7f0800e5);
        k = j;
        continue; /* Loop/switch isn't completed */
_L7:
        if ((bitmaptexture = getBitmapTextureByType(mContext, byte0)) == null) goto _L6; else goto _L8
_L8:
        if (j == 0)
        {
            j = bitmaptexture.getWidth();
            k = bitmaptexture.getHeight();
        }
        bitmaptexture.setOpaque(false);
        bitmaptexture.draw(mCanvas, rect.right - j - 4, 4 + rect.top, j, k);
        return;
        if (true) goto _L1; else goto _L9
_L9:
    }

    private String getConnectedToDMSString(String s)
    {
        String s1 = GetDmsName(s);
        if (s1 == null && mContext != null)
        {
            return "";
        } else
        {
            return String.format(getResources().getString(0x7f0b0185), new Object[] {
                s1
            });
        }
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

    public void cancelAll()
    {
        if (mDownloadFacade == null)
        {
            return;
        } else
        {
            mDownloadFacade.cancelAll();
            ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(false);
            requestRender();
            return;
        }
    }

    void decorateItem(Rect rect, MediaItem mediaitem, int i, boolean flag)
    {
        super.decorateItem(rect, mediaitem, i, flag);
        if (4 == ViewManager.getViewStatus())
        {
            fillDownloadIcon(mediaitem, rect, (int)mDownloadFacade.getDownloadStatus(mDataSource, i));
        }
    }

    boolean doSelectItem(int i)
    {
        Log.e("ThumbGlView", (new StringBuilder()).append("doSelectItem position = ").append(i).toString());
        if (mDataSource == null || mDataSource.getDataSource() == null)
        {
            return false;
        }
        if (mDataSource.getDataSource().getItem(getRealPosition(i)) != null && ViewManager.getViewStatus() == 4)
        {
            download(i);
            ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(isFileDownloading());
            return true;
        } else
        {
            return super.doSelectItem(i);
        }
    }

    void download(int i)
    {
        if (mDownloadFacade == null || mDataSource == null)
        {
            return;
        } else
        {
            mDownloadFacade.download(mDataSource.makeDownloadTask(i));
            return;
        }
    }

    public void downloadAll()
    {
        if (mDownloadFacade == null || mDataSource == null)
        {
            return;
        } else
        {
            mDownloadFacade.downloadAll(mDataSource, 0);
            ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(isFileDownloading());
            return;
        }
    }

    void handleMessage(Message message)
    {
        switch (message.what)
        {
        case 1539: 
        case 1540: 
        case 1541: 
        default:
            return;

        case 1537: 
            updateUpdownloadState(getViewFromUri(Uri.parse((String)message.obj)), 2);
            return;

        case 1538: 
            updateUpdownloadProgress(getViewFromUri(Uri.parse((String)message.obj)), message.arg1);
            return;

        case 1542: 
            com.arcsoft.mediaplus.updownload.UpDownloadUtils.ErrorCode.showUpDownloadError(mContext, 0, message.arg1, new Object[0]);
            break;
        }
    }

    public void init(CacheMgr cachemgr)
    {
        super.init(cachemgr);
        REFRESH_INFO_FONT_SIZE = getResources().getInteger(0x7f0a000d);
        mMaxRefreshViewHeight = getResources().getInteger(0x7f0a0010);
        mMinReleaseHeight = getResources().getInteger(0x7f0a000f);
        mRefreshViewBottomOffset = getResources().getInteger(0x7f0a000e);
        mLastUpdateTime = System.currentTimeMillis();
    }

    protected boolean isRemote()
    {
        return true;
    }

    protected void onDataBuiltFinish(int i)
    {
        Log.w("ThumbGlView", "onDataBuiltFinish");
    }

    public void onListCountChanged(int i, int j)
    {
        Log.w("ThumbGlView", "onListCountChanged");
    }

    public void onResume()
    {
        super.onResume();
        if (mContext != null && ((MediaPlusApplication)mContext.getApplicationContext()).isCacheCleared())
        {
            if (mDataSourceController != null)
            {
                mDataSourceController.refresh();
            }
            ((MediaPlusApplication)mContext.getApplicationContext()).setCacheCleared(false);
        }
    }

    public void onServerBeginLoadingData()
    {
        Log.e("ThumbGlView", "onServerBeginLoadingData");
        String s;
        if (mContext == null)
        {
            s = "";
        } else
        {
            s = mContext.getResources().getString(0x7f0b0188);
        }
        Log.d("zdf", (new StringBuilder()).append("######## [RemoteListViewGL] onServerBeginLoadingData, strText = ").append(s).toString());
        sendMsgToUpdateTextStatus(s, true, 0);
    }

    public void onServerChanged(String s)
    {
        Log.v("zdf", (new StringBuilder()).append("[RemoteListViewGL] onServerChanged, serverudn = ").append(s).toString());
        String s1 = "";
        if (s == null)
        {
            if (mContext == null)
            {
                s1 = "";
            } else
            {
                s1 = mContext.getResources().getString(0x7f0b005c);
            }
        }
        if (s1 != null && s1.length() > 0 && !s1.equals(mLastDMSString))
        {
            sendMsgToShowToast(s1, 0);
            mLastDMSString = s1;
        }
        Log.d("zdf", (new StringBuilder()).append("######## [RemoteListViewGL] onServerChanged, strText = ").append(s1).toString());
        sendMsgToUpdateTextStatus(s1, true, 0);
    }

    public void onServerEndLoadingData(int i)
    {
        Log.v("zdf", (new StringBuilder()).append("######## [RemoteListViewGL] onServerEndLoadingData, count = ").append(i).toString());
        if (i == 0)
        {
            String s;
            if (mContext == null)
            {
                s = "";
            } else
            {
                s = mContext.getResources().getString(0x7f0b001b);
            }
            Log.d("zdf", (new StringBuilder()).append("######## [RemoteListViewGL] onServerEndLoadingData, strText = ").append(s).toString());
            sendMsgToUpdateTextStatus(s, true, 0);
            mIsRefreshRemote = false;
        } else
        if (i > 0)
        {
            sendMsgToUpdateTextStatus("", false, 2000);
            if (mContext != null)
            {
                sendMsgToShowToast(mContext.getString(0x7f0b0189), 2000);
            }
            mIsRefreshRemote = false;
            return;
        }
    }

    void onUpDownloadFinish(String s, String s1, Object obj, int i)
    {
        sendMsgToShowDownloadError(i);
        onDecodeThumbnail(urlToIndex(s1));
        if (ViewManager.mCurrentViewStatus == 4)
        {
            ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(isFileDownloading());
        }
        requestRender();
    }

    void onUpDownloadStart(String s, String s1)
    {
        requestRender();
    }

    public void refreshTextView()
    {
        PictureDataSource picturedatasource = mDataSource;
        int i = 0;
        if (picturedatasource != null)
        {
            i = mDataSource.getCount();
        }
        boolean flag;
        if (i == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Log.v("zdf", (new StringBuilder()).append("######## [RemoteListViewGL] refreshTextView, bShowEmptyText = ").append(flag).toString());
        sendMsgToUpdateTextStatus(mContext.getResources().getString(0x7f0b001b), flag, 0);
    }

    protected void renderRefreshView(int i)
    {
        int j;
        int k;
        int l;
        while (i >= 0 || mContext == null || !((MediaPlusActivity)mContext).isWifiConnected() || !((MediaPlusActivity)mContext).hasDefaultDMS()) 
        {
            return;
        }
        Rect rect = new Rect(0, -i - mRefreshViewBottomOffset - REFRESH_INFO_FONT_SIZE, getWidth(), -i);
        j = renderLastUpdateTime(mCanvas, mLastUpdateTime, rect);
        k = 60;
        l = 60;
        if (getDataBuildState() <= 0 && !mIsRefreshRemote) goto _L2; else goto _L1
_L1:
        String s;
        BitmapTexture bitmaptexture;
        s = getResources().getString(0x7f0b01dd);
        bitmaptexture = getBitmapTextureByType(mContext, 14);
_L4:
        Rect rect1 = new Rect(0, -i - 2 * (mRefreshViewBottomOffset + REFRESH_INFO_FONT_SIZE), getWidth(), -i);
        int i1 = Math.max(j, renderRefreshInfo(mCanvas, s, rect1));
        int j1 = Math.max((getWidth() - i1) / 2 - 40 - k / 2, 0);
        int k1 = -i - mRefreshViewBottomOffset - l;
        Rect rect2 = new Rect(j1, k1, j1 + k, k1 + l);
        if (bitmaptexture != null)
        {
            bitmaptexture.setOpaque(false);
        }
        drawTexture(bitmaptexture, rect2);
        return;
_L2:
        if (isFileDownloading())
        {
            s = getResources().getString(0x7f0b0181);
            bitmaptexture = getBitmapTextureByType(mContext, 14);
        } else
        {
            boolean flag;
            Resources resources;
            int l1;
            Context context;
            byte byte0;
            if (-i < mMinReleaseHeight)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            resources = getResources();
            if (flag)
            {
                l1 = 0x7f0b01db;
            } else
            {
                l1 = 0x7f0b01dc;
            }
            s = resources.getString(l1);
            context = mContext;
            if (flag)
            {
                byte0 = 12;
            } else
            {
                byte0 = 13;
            }
            bitmaptexture = getBitmapTextureByType(context, byte0);
            if (bitmaptexture != null)
            {
                k = bitmaptexture.getWidth();
                l = bitmaptexture.getHeight();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void resume(boolean flag)
    {
        super.resume(flag);
    }

    void sendMsgToShowDownloadError(int i)
    {
        if (mHandler == null)
        {
            return;
        } else
        {
            Message message = new Message();
            message.what = 1542;
            message.arg1 = i;
            mHandler.sendMessage(message);
            return;
        }
    }

    public void uninit()
    {
        super.uninit();
        if (mDownloadFacade != null)
        {
            mDownloadFacade.destroy();
            mDownloadFacade = null;
        }
    }

    public void updateServerStatusListener(boolean flag)
    {
        if (flag)
        {
            RemoteDBMgr.instance().setServerStatusListener(null);
            return;
        } else
        {
            RemoteDBMgr.instance().setServerStatusListener(this);
            return;
        }
    }

    int urlToIndex(String s)
    {
        int i;
        int j;
        if (mDataSource == null)
        {
            return -1;
        }
        i = mDataSource.getCount();
        j = 0;
_L3:
        MediaItem mediaitem;
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        mediaitem = mDataSource.getItem(j);
          goto _L1
_L5:
        j++;
        if (true) goto _L3; else goto _L2
_L1:
        if (mediaitem == null || mediaitem.uri == null || !mediaitem.uri.toString().equals(s)) goto _L5; else goto _L4
_L4:
        return j;
_L2:
        return -1;
    }
}
