// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.FileDownloader;
import com.arcsoft.util.tool.DoubleBufferList;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource, Property, MediaItem

public abstract class AbsRemoteDataSource extends AbsListDataSource
{
    private class DownloadItemInfo
    {

        int index;
        MediaItem item;
        final AbsRemoteDataSource this$0;
        boolean thumbnail;

        private DownloadItemInfo()
        {
            this$0 = AbsRemoteDataSource.this;
            super();
        }

    }

    private class TaskHandler extends Handler
    {

        static final long DOWNLOADINTERVAL = 500L;
        boolean bAutoDownloadFinish;
        boolean bDownloadThumb;
        int currentIndex;
        int firstIndex;
        final AbsRemoteDataSource this$0;

        public void handleMessage(Message message)
        {
            if (message.what == 1)
            {
                ((Runnable)message.obj).run();
            } else
            if (message.what == 0)
            {
                removeMessages(0);
                if (message.what == 0)
                {
                    com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest = getDownloadRequest(currentIndex, bDownloadThumb);
                    if (downloadrequest != null)
                    {
                        FileDownloader.instance().download(downloadrequest);
                    }
                    if (getCount() > 0)
                    {
                        int i = (1 + currentIndex) % getCount();
                        currentIndex = i;
                        if (i != firstIndex)
                        {
                            sendEmptyMessageDelayed(0, 500L);
                            return;
                        }
                    }
                    Log.w("auto", "audo download finish");
                    bAutoDownloadFinish = true;
                    return;
                }
            }
        }

        public void pause()
        {
            removeMessages(0);
        }

        public void reset()
        {
            firstIndex = 0;
            currentIndex = 0;
            bAutoDownloadFinish = false;
            resume();
        }

        public void resume()
        {
            if (bAutoDownloadFinish)
            {
                return;
            } else
            {
                removeMessages(0);
                sendEmptyMessageDelayed(0, 500L);
                return;
            }
        }

        public void setDownloadType(boolean flag, int i)
        {
            bAutoDownloadFinish = false;
            bDownloadThumb = flag;
            firstIndex = i;
            currentIndex = i;
            resume();
        }

        private TaskHandler()
        {
            this$0 = AbsRemoteDataSource.this;
            super();
            bDownloadThumb = true;
            bAutoDownloadFinish = false;
            currentIndex = 0;
            firstIndex = 0;
        }

    }


    private static final boolean AUTO_DOWNLOAD_FILE = true;
    protected static final String DOWNLOAD_PATH = "/";
    protected static final String IMAGE_FILE_PATH = ".l";
    private static final int MSG_AUTO_PREFETCH = 0;
    private static final int MSG_RUNNABLE = 1;
    protected static final String TAG = "AbsRemoteDataSource";
    protected static final String THUMB_FILE_PATH = ".t";
    private final com.arcsoft.util.network.FileDownloader.ICompressStrategy mCompressor = new com.arcsoft.util.network.FileDownloader.ICompressStrategy() {

        final AbsRemoteDataSource this$0;

        public boolean compressFile(String s, String s1)
        {
            return compressFile(s, s1);
        }

            
            {
                this$0 = AbsRemoteDataSource.this;
                super();
            }
    };
    protected Context mContext;
    private final com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener mDBListener = new com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.IOnDBDataListener() {

        final AbsRemoteDataSource this$0;

        public void OnDBDataMounted(String s)
        {
            AbsRemoteDataSource.this.OnDBDataMounted(s);
        }

        public void OnDBDataTransmittedBegin(String s)
        {
            NotifyOnDataBuiltBegin();
        }

        public void OnDBDataTransmittedFinish(String s)
        {
            NotifyOnDataBuiltFinish();
        }

        public void OnDBDataUnMounted(String s)
        {
            AbsRemoteDataSource.this.OnDBDataUnMounted(s);
        }

        public void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
        {
            AbsRemoteDataSource.this.OnDBDataUpdated(s, updateinfo);
        }

            
            {
                this$0 = AbsRemoteDataSource.this;
                super();
            }
    };
    protected RemoteDBMgr mDbMgr;
    private final com.arcsoft.util.network.FileDownloader.IDownloadListener mDownloadListener = new com.arcsoft.util.network.FileDownloader.IDownloadListener() {

        final AbsRemoteDataSource this$0;

        public void onDownloadFinished(com.arcsoft.util.network.FileDownloader.DownloadResult downloadresult)
        {
            if (downloadresult.request.requestcode != mRequestCode)
            {
                return;
            } else
            {
                DownloadItemInfo _tmp = (DownloadItemInfo)downloadresult.request.userdata;
                Message message = Message.obtain();
                message.what = 1;
                message.obj = downloadresult. new Runnable() {

                    final _cls1 this$1;
                    final com.arcsoft.util.network.FileDownloader.DownloadResult val$fr;

                    public void run()
                    {
                        onDownloadFinished(fr);
                    }

            
            {
                this$1 = final__pcls1;
                fr = com.arcsoft.util.network.FileDownloader.DownloadResult.this;
                super();
            }
                };
                mTaskHandler.sendMessage(message);
                return;
            }
        }

        public void onDownloadStarted(final com.arcsoft.util.network.FileDownloader.DownloadRequest fr, long l)
        {
            if (fr.requestcode != mRequestCode)
            {
                return;
            } else
            {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = l. new Runnable() {

                    final _cls1 this$1;
                    final long val$fid;
                    final com.arcsoft.util.network.FileDownloader.DownloadRequest val$fr;

                    public void run()
                    {
                        onDownloadStarted(fr, fid);
                    }

            
            {
                this$1 = final__pcls1;
                fr = downloadrequest;
                fid = J.this;
                super();
            }
                };
                mTaskHandler.sendMessage(message);
                return;
            }
        }

            
            {
                this$0 = AbsRemoteDataSource.this;
                super();
            }
    };
    protected int mLastPrefetchEnd;
    protected int mLastPrefetchStart;
    private int mRequestCode;
    private final TaskHandler mTaskHandler = new TaskHandler();
    private final ArrayList mTmpDownloadRequests = new ArrayList();

    public AbsRemoteDataSource(Context context, RemoteDBMgr remotedbmgr)
    {
        mDbMgr = null;
        mContext = null;
        mLastPrefetchStart = -1;
        mLastPrefetchEnd = -1;
        mRequestCode = (new Long(System.currentTimeMillis())).intValue();
        mDbMgr = remotedbmgr;
        mContext = context;
    }

    private boolean buildMediaList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        return buildMediaList(openCursor(), list, options);
    }

    protected void OnDBDataMounted(String s)
    {
        refreshList();
    }

    protected void OnDBDataUnMounted(String s)
    {
        mList.clear();
        refreshList();
    }

    protected void OnDBDataUpdated(String s, com.arcsoft.mediaplus.datasource.db.RemoteDBMgr.UpdateInfo updateinfo)
    {
        refreshList();
    }

    boolean buildMediaList(Cursor cursor, List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        int i;
        long l;
        boolean flag;
        if (cursor == null)
        {
            return true;
        }
        i = 0;
        l = System.currentTimeMillis();
        flag = false;
_L6:
        if (!cursor.moveToNext() || options.mIsCanceled) goto _L2; else goto _L1
_L1:
        MediaItem mediaitem;
        int j;
        mediaitem = createMediaItem(cursor);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        j = 0;
_L5:
        if (j >= 2) goto _L4; else goto _L3
_L3:
        boolean flag1;
        String s;
        boolean flag2;
        String s1;
        String s2;
        File file;
        if (j == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        s = getDownloadUrlIfNeed(mediaitem, flag1);
        if (j == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        s1 = getDownloadPath(flag2);
        s2 = FileDownloader.instance().getParsedFilePath(s1, s);
        file = new File(s2);
        l = System.currentTimeMillis();
        if (file.exists())
        {
            boolean flag3;
            if (j == 1)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            try
            {
                updateDownloadedPath(mediaitem, s2, flag3);
            }
            catch (Exception exception) { }
        }
        j++;
          goto _L5
_L4:
        if (System.currentTimeMillis() - l > 1000L)
        {
            flag = true;
        }
        list.add(mediaitem);
        i++;
          goto _L6
_L2:
        cursor.close();
        quickIndexSort(list);
        return !options.mIsCanceled;
    }

    protected void cancelAllDownloads()
    {
        FileDownloader.instance().cancelDownloads(mRequestCode);
    }

    protected void cancelDownload(int i)
    {
        FileDownloader.instance().cancelDownload(i);
    }

    protected void cancelDownload(String s)
    {
        FileDownloader.instance().cancelDownload(mRequestCode, s);
    }

    protected boolean check(com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest)
    {
        boolean flag = false;
        Iterator iterator = mTmpDownloadRequests.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (((com.arcsoft.util.network.FileDownloader.DownloadRequest)iterator.next()).url.equals(downloadrequest.url))
            {
                flag = true;
            }
        } while (true);
        return flag;
    }

    protected void clearRequests()
    {
        if (mTmpDownloadRequests != null)
        {
            mTmpDownloadRequests.clear();
        }
    }

    protected boolean compressFile(String s, String s1)
    {
        return false;
    }

    protected abstract MediaItem createMediaItem(Cursor cursor);

    protected abstract void destoryItem(MediaItem mediaitem);

    protected String getDownloadPath(boolean flag)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("/.").append(getServerUDN()).append("/");
        String s;
        if (flag)
        {
            s = ".t";
        } else
        {
            s = ".l";
        }
        return stringbuilder.append(s).toString();
    }

    protected com.arcsoft.util.network.FileDownloader.DownloadRequest getDownloadRequest(int i, boolean flag)
    {
        if (i >= 0 && i < getCount()) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        MediaItem mediaitem;
        String s;
        mediaitem = getItem(i);
        s = getDownloadUrlIfNeed(mediaitem, flag);
        if (s == null) goto _L1; else goto _L3
_L3:
        com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest;
        String s1;
        int j;
        DownloadItemInfo downloaditeminfo = new DownloadItemInfo();
        downloaditeminfo.index = i;
        downloaditeminfo.thumbnail = flag;
        downloaditeminfo.item = mediaitem;
        downloadrequest = new com.arcsoft.util.network.FileDownloader.DownloadRequest();
        downloadrequest.requestcode = mRequestCode;
        downloadrequest.url = s;
        downloadrequest.parentdir = getDownloadPath(flag);
        downloadrequest.listener = mDownloadListener;
        downloadrequest.userdata = downloaditeminfo;
        downloadrequest.index = i;
        downloadrequest.dms_uuid = Settings.instance().getDefaultDMSUDN();
        downloadrequest.item_uuid = getRemoteItemUUID(i);
        s1 = getStringProp(downloadrequest.index, Property.PROP_MIMETYPE, "").toLowerCase();
        j = -1;
        if (!s1.startsWith("audio/")) goto _L5; else goto _L4
_L4:
        j = 2;
_L7:
        downloadrequest.upnp_class = j;
        return downloadrequest;
_L5:
        if (s1.startsWith("video/"))
        {
            j = 1;
        } else
        if (s1.startsWith("image/"))
        {
            j = 3;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected abstract String getDownloadUrlIfNeed(MediaItem mediaitem, boolean flag);

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(int i)
    {
        long l = getLongProp(i, Property.PROP_ID, 0L);
        return RemoteDBMgr.instance().getRemoteItemDesc(l);
    }

    public ArrayList getRemoteItemResourceDesc(int i)
    {
        long l = getLongProp(i, Property.PROP_ID, 0L);
        return RemoteDBMgr.instance().getRemoteItemResourceDesc(l);
    }

    public String getRemoteItemUUID(int i)
    {
        long l = getLongProp(i, Property.PROP_ID, 0L);
        return RemoteDBMgr.instance().getRemoteItemUUID(l);
    }

    protected String getServerUDN()
    {
        return mDbMgr.getCurrentServer();
    }

    public boolean isImageDownloaded(int i)
    {
        File file;
label0:
        {
            MediaItem mediaitem = getItem(i);
            if (mediaitem != null)
            {
                String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, mediaitem.title, mediaitem.strDecodePath, MyUPnPUtils.getItemResource(RemoteDBMgr.instance().getCurrentServer(), getRemoteItemUUID(i)));
                if (s != null)
                {
                    file = new File(s);
                    if (file != null)
                    {
                        break label0;
                    }
                }
            }
            return false;
        }
        return file.exists();
    }

    protected void notifyOnCountChanged(int i, int j)
    {
        mTaskHandler.reset();
        mLastPrefetchStart = -1;
        mLastPrefetchEnd = -1;
        super.notifyOnCountChanged(i, j);
    }

    protected boolean onBuildList(List list, com.arcsoft.util.tool.DoubleBufferList.Options options)
    {
        mLastPrefetchStart = -1;
        for (mLastPrefetchEnd = -1; options.mIsCanceled || !buildMediaList(list, options) || options.mIsCanceled;)
        {
            return false;
        }

        return true;
    }

    protected void onDestoryList(List list)
    {
        for (Iterator iterator = list.iterator(); iterator.hasNext(); destoryItem((MediaItem)iterator.next())) { }
        list.clear();
    }

    protected void onDisable()
    {
        super.onDisable();
        mLastPrefetchStart = -1;
        mLastPrefetchEnd = -1;
        FileDownloader.instance().cancelDownloads(mRequestCode);
    }

    protected abstract boolean onDownloadError(MediaItem mediaitem, boolean flag);

    protected void onDownloadFinished(com.arcsoft.util.network.FileDownloader.DownloadResult downloadresult)
    {
        DownloadItemInfo downloaditeminfo = (DownloadItemInfo)downloadresult.request.userdata;
        if (downloaditeminfo != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        MediaItem mediaitem;
        try
        {
            mediaitem = getItem(downloaditeminfo.index);
        }
        catch (Exception exception)
        {
            return;
        }
        if (mediaitem == null || mediaitem.uri == null || downloaditeminfo.item.uri == null || !mediaitem.uri.equals(downloaditeminfo.item.uri)) goto _L1; else goto _L3
_L3:
        if (downloadresult.errorcode == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (downloadresult.errorcode != 5 && onDownloadError(mediaitem, downloaditeminfo.thumbnail))
        {
            int j = downloaditeminfo.index;
            Property property1;
            if (downloaditeminfo.thumbnail)
            {
                property1 = Property.PROP_THUMBNAIL_FILEPATH;
            } else
            {
                property1 = Property.PROP_IMAGE_FILEPATH;
            }
            notifyOnDataChanged(j, property1);
            return;
        }
        if (downloadresult.errorcode != 6) goto _L1; else goto _L4
_L4:
        notifyOnDataChanged(downloaditeminfo.index, Property.PROP_STORAGE_FULL);
        return;
        if (!updateDownloadedPath(mediaitem, downloadresult.filePath, downloaditeminfo.thumbnail) || mDataChangeListeners == null) goto _L1; else goto _L5
_L5:
        int i = downloaditeminfo.index;
        Property property;
        if (downloaditeminfo.thumbnail)
        {
            property = Property.PROP_THUMBNAIL_FILEPATH;
        } else
        {
            property = Property.PROP_IMAGE_FILEPATH;
        }
        notifyOnDataChanged(i, property);
        return;
    }

    protected void onDownloadStarted(com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest, long l)
    {
    }

    protected void onEnable()
    {
        mTaskHandler.reset();
        super.onEnable();
    }

    protected void onInit()
    {
        mDbMgr.registerOnDataUpdateListener(mDBListener);
        super.onInit();
    }

    protected void onPause()
    {
        super.onPause();
        mTaskHandler.pause();
    }

    protected void onResume()
    {
        super.onResume();
        mTaskHandler.resume();
    }

    protected void onUninit()
    {
        mDbMgr.unregisterOnDataUpdateListener(mDBListener);
        FileDownloader.instance().cancelDownloads(mRequestCode);
        mRequestCode = 0;
        mTaskHandler.removeMessages(1);
        mTaskHandler.removeMessages(0);
        super.onUninit();
    }

    protected abstract Cursor openCursor();

    public transient void prefetch(int i, int j, Property aproperty[])
    {
        boolean flag = true;
        if (aproperty != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag1;
        int k;
        int l;
        int i1;
        com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest;
        if (Property.PROP_IMAGE_FILEPATH.isBelongsTo(aproperty))
        {
            flag1 = false;
        } else
        {
            if (!Property.PROP_THUMBNAIL_FILEPATH.isBelongsTo(aproperty))
            {
                continue; /* Loop/switch isn't completed */
            }
            flag1 = true;
        }
        k = Math.max(i, 0);
        l = Math.min(j, -1 + getCount());
        if (k >= mLastPrefetchStart && l <= mLastPrefetchEnd)
        {
            continue; /* Loop/switch isn't completed */
        }
        mLastPrefetchStart = k;
        mLastPrefetchEnd = l;
        clearRequests();
        break MISSING_BLOCK_LABEL_74;
        if (!flag1 && l != k)
        {
            flag = false;
        }
        for (i1 = k; i1 <= l; i1++)
        {
            downloadrequest = getDownloadRequest(i1, flag1);
            if (downloadrequest != null)
            {
                mTmpDownloadRequests.add(downloadrequest);
            }
        }

        break MISSING_BLOCK_LABEL_150;
        FileDownloader.instance().cancelNormalTaskDownloads(mRequestCode, getDownloadPath(flag1));
        FileDownloader.instance().download(mTmpDownloadRequests, flag);
        if (isResume())
        {
            mTaskHandler.setDownloadType(flag1, l);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected transient void prefetchEx(int ai[], Property aproperty[])
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
_L2:
        boolean flag1;
        do
        {
            return;
        } while (aproperty == null || ai == null || ai.length <= 0);
        int i;
        com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest;
        if (Property.PROP_IMAGE_FILEPATH.isBelongsTo(aproperty))
        {
            flag1 = false;
        } else
        {
            if (!Property.PROP_THUMBNAIL_FILEPATH.isBelongsTo(aproperty))
            {
                continue; /* Loop/switch isn't completed */
            }
            flag1 = true;
        }
        clearRequests();
        break MISSING_BLOCK_LABEL_33;
        if (!flag1 && ai.length != 0)
        {
            flag = false;
        }
        for (i = 0; i < ai.length; i++)
        {
            downloadrequest = getDownloadRequest(ai[i], flag1);
            if (downloadrequest != null)
            {
                mTmpDownloadRequests.add(downloadrequest);
            }
        }

        break MISSING_BLOCK_LABEL_107;
        FileDownloader.instance().cancelNormalTaskDownloads(mRequestCode, getDownloadPath(flag1));
        FileDownloader.instance().download(mTmpDownloadRequests, flag);
        if (isResume())
        {
            mTaskHandler.setDownloadType(flag1, ai[0]);
            return;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected void refreshList()
    {
        mList.abortBuilding();
        mList.invalide();
    }

    protected abstract boolean updateDownloadedPath(MediaItem mediaitem, String s, boolean flag);


}
