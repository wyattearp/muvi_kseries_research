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
import com.arcsoft.mediaplus.DownloadFacade;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.picture.opengl.BitmapTexture;
import com.arcsoft.mediaplus.picture.opengl.GLCanvasImpl;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

public class LocalListViewGL extends ListViewGL
{

    boolean mIsDownloadStateError;
    int mProgress;

    public LocalListViewGL(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mProgress = 0;
        mIsDownloadStateError = false;
    }

    void decorateItem(Rect rect, MediaItem mediaitem, int i, boolean flag)
    {
        Rect rect1;
        super.decorateItem(rect, mediaitem, i, flag);
        BitmapTexture bitmaptexture = getBitmapTextureByType(mContext, 5);
        int j = bitmaptexture.getWidth();
        int k = bitmaptexture.getHeight();
        int l = (rect.width() - j) / 2;
        if (l <= 0)
        {
            l = 4;
        }
        rect1 = new Rect(l + rect.left, rect.bottom - k - 5, rect.right - l, rect.bottom - 5);
        if (!mediaitem.isDownloadingFile || 3L == mediaitem.status) goto _L2; else goto _L1
_L1:
        if (mediaitem.status != 2L) goto _L4; else goto _L3
_L3:
        int j1 = mProgress;
_L6:
        renderDownloadProgress(mCanvas, j1, rect1);
_L2:
        return;
_L4:
        int i1 = mediaitem.status != 3L;
        j1 = 0;
        if (i1 == 0)
        {
            j1 = 100;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    int downloadUriToIndex(String s)
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
        if (mediaitem == null || mediaitem.uri == null || !s.equals(mediaitem.uri.toString())) goto _L5; else goto _L4
_L4:
        return j;
_L2:
        return -1;
    }

    int getRealPosition(int i)
    {
        return i - mDataSource.getOutSideDataCount();
    }

    public void init(CacheMgr cachemgr)
    {
        super.init(cachemgr);
    }

    void insertVectorToDataSource()
    {
        if (mDataSource == null || mDownloadFacade == null)
        {
            return;
        }
        boolean flag = mIsDownloadStateError;
        java.util.Vector vector = null;
        if (!flag)
        {
            vector = mDownloadFacade.getDownloadingItems();
        }
        mDataSource.setOutSideItems(vector);
        requestRender();
    }

    protected boolean isRemote()
    {
        return false;
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
        }
    }

    public void onListCountChanged(int i, int j)
    {
        if (i > 0)
        {
            updateVectorToDataSource(true);
        }
    }

    void onProgress(String s, String s1, long l, long l1)
    {
        mProgress = (int)(100F * ((float)l / (float)l1));
        requestRender();
    }

    public void onResume()
    {
        super.onResume();
        if (mDownloadFacade != null)
        {
            int i;
            int j;
            boolean flag;
            if (!mDownloadFacade.isDownloading())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            mIsDownloadStateError = flag;
            mProgress = mDownloadFacade.getCurrentDownloadProgress();
        }
        i = mDataSource.getCount();
        updateVectorToDataSource(true);
        j = mDataSource.getCount();
        ListViewManager.decreaseSelectedItemKey(i - j);
        Log.v("zdf", (new StringBuilder()).append("[LocalListViewGL] onResume, countBeforeUpdate = ").append(i).append(", countAfterUpdate = ").append(j).toString());
    }

    void onUpDownloadFinish(String s, String s1, Object obj, int i)
    {
        Log.e("zdf", (new StringBuilder()).append("[LocalListViewGL] onUpDownloadFinish, errorcode = ").append(i).toString());
        if (911 == i)
        {
            mIsDownloadStateError = false;
            mProgress = 100;
            if (mDataSource != null)
            {
                mDataSource.updateOutSideItemStatus(s1, 3L);
            }
            if (obj != null)
            {
                MediaItem mediaitem = requestToMediaItem(((com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadResult)obj).request);
                if (mediaitem != null && mCacheMgr != null)
                {
                    mCacheMgr.submitTask(this, mediaitem, 0);
                }
                if (mediaitem != null)
                {
                    Log.e("zdf", (new StringBuilder()).append("onUpDownloadFinish, item.uri = ").append(mediaitem.uri).toString());
                    String s2 = mediaitem.uri.toString();
                    String s3 = FileUtils.getExtension(s2);
                    if (!FileUtils.isMediaStoreSupported(s2) && s3.equalsIgnoreCase("mov"))
                    {
                        if (mHandler != null)
                        {
                            Context context = mContext;
                            Object aobj[] = new Object[1];
                            aobj[0] = (new StringBuilder()).append(".").append(s3).toString();
                            String s4 = context.getString(0x7f0b019d, aobj);
                            Message message = mHandler.obtainMessage(1543);
                            message.obj = s4;
                            mHandler.sendMessage(message);
                        }
                        mDataSource.updateOutSideItemStatus(s1, 4L);
                        int j1 = mDataSource.getCount();
                        updateVectorToDataSource(true);
                        int k1 = mDataSource.getCount();
                        ListViewManager.decreaseSelectedItemKey(j1 - k1);
                        Log.v("zdf", (new StringBuilder()).append("onUpDownloadFinish, countBeforeUpdate = ").append(j1).append(", countAfterUpdate = ").append(k1).toString());
                        refresh(false);
                    }
                }
            }
            return;
        }
        if (819 == i || 817 == i)
        {
            mIsDownloadStateError = true;
            mProgress = 0;
            int j = mDataSource.getCount();
            updateVectorToDataSource(true);
            int k = mDataSource.getCount();
            ListViewManager.decreaseSelectedItemKey(j - k);
            Log.v("zdf", (new StringBuilder()).append("[LocalListViewGL] onUpDownloadFinish, countBeforeUpdate = ").append(j).append(", countAfterUpdate = ").append(k).toString());
            refresh(false);
            return;
        } else
        {
            int l = mDataSource.getCount();
            updateVectorToDataSource(true);
            int i1 = mDataSource.getCount();
            ListViewManager.decreaseSelectedItemKey(l - i1);
            Log.v("zdf", (new StringBuilder()).append("[LocalListViewGL] onUpDownloadFinish, countBeforeUpdate = ").append(l).append(", countAfterUpdate = ").append(i1).toString());
            return;
        }
    }

    void onUpDownloadStart(String s, String s1)
    {
        mIsDownloadStateError = false;
        mProgress = 0;
        updateVectorToDataSource(false);
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
        Log.v("zdf", (new StringBuilder()).append("######## [LocalListViewGL] refreshTextView, bShowEmptyText = ").append(flag).toString());
        sendMsgToUpdateTextStatus(mContext.getResources().getString(0x7f0b001b), flag, 0);
    }

    protected void renderDownloadProgress(GLCanvasImpl glcanvasimpl, int i, Rect rect)
    {
        if (mContext == null)
        {
            return;
        } else
        {
            BitmapTexture bitmaptexture = getBitmapTextureByType(mContext, 5);
            BitmapTexture bitmaptexture1 = getBitmapTextureByType(mContext, 6);
            bitmaptexture.setOpaque(false);
            bitmaptexture1.setOpaque(false);
            bitmaptexture.draw(glcanvasimpl, rect.left, rect.top, rect.width(), rect.height());
            bitmaptexture1.draw(glcanvasimpl, 1 + rect.left, 1 + rect.top, (i * (-2 + rect.width())) / 100, -2 + rect.height());
            return;
        }
    }

    MediaItem requestToMediaItem(com.arcsoft.mediaplus.updownload.IPoolDriver.DownloadRequest downloadrequest)
    {
        if (downloadrequest == null)
        {
            return null;
        } else
        {
            MediaItem mediaitem = new MediaItem();
            mediaitem.videoOrImage = downloadrequest.videoOrImage;
            String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, downloadrequest.title, downloadrequest.uri.toString(), MyUPnPUtils.getItemResource(RemoteDBMgr.instance().getCurrentServer(), RemoteDBMgr.instance().getRemoteItemUUID(downloadrequest.media_id)));
            mediaitem.uri = Uri.parse((new StringBuilder()).append("file://").append(s).toString());
            mediaitem.title = downloadrequest.title;
            mediaitem.size = downloadrequest.fileSize;
            mediaitem._id = downloadrequest.media_id;
            return mediaitem;
        }
    }

    protected void resume(boolean flag)
    {
        super.resume(flag);
    }

    void updateVectorToDataSource(boolean flag)
    {
        if (mDataSource == null || mDownloadFacade == null)
        {
            return;
        }
        boolean flag1 = mIsDownloadStateError;
        java.util.Vector vector = null;
        if (!flag1)
        {
            vector = mDownloadFacade.getDownloadingItems();
        }
        mDataSource.updateOutSideItems(vector, flag);
        requestRender();
    }
}
