// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            RemoteDataDBHelper

public class DataTask extends Thread
    implements Recyclable
{
    class BrowseInfo
    {

        int mDelayRetryTimes;
        final String mDirObjID;
        int mRetryCnt;
        long mRetryStartTick;
        int mRetryType;
        final String mServerUDN;
        final int mUpdateType;
        boolean refreshMeta;
        final DataTask this$0;

        BrowseInfo(String s, String s1, int i, boolean flag)
        {
            this$0 = DataTask.this;
            super();
            mDelayRetryTimes = 0;
            mRetryType = 1;
            mRetryCnt = 0;
            mRetryStartTick = 0L;
            refreshMeta = false;
            mServerUDN = s;
            mUpdateType = i;
            mDirObjID = s1;
            refreshMeta = flag;
        }
    }

    private static final class GetDataMode extends Enum
    {

        private static final GetDataMode $VALUES[];
        public static final GetDataMode Browse;
        public static final GetDataMode Search;

        public static GetDataMode valueOf(String s)
        {
            return (GetDataMode)Enum.valueOf(com/arcsoft/mediaplus/datasource/db/DataTask$GetDataMode, s);
        }

        public static GetDataMode[] values()
        {
            return (GetDataMode[])$VALUES.clone();
        }

        static 
        {
            Search = new GetDataMode("Search", 0);
            Browse = new GetDataMode("Browse", 1);
            GetDataMode agetdatamode[] = new GetDataMode[2];
            agetdatamode[0] = Search;
            agetdatamode[1] = Browse;
            $VALUES = agetdatamode;
        }

        private GetDataMode(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface IOnDataUpdateListener
    {

        public abstract void OnDataIncreased(String s, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4);

        public abstract void OnDataTransmittedBegin(String s);

        public abstract void OnDataTransmittedFinish(String s, int i);

        public abstract void OnDataUpdated(String s);
    }

    static class RemoteItemDataInfo
    {

        com.arcsoft.adk.atv.UPnP.RemoteItemDesc mData;
        boolean mRefreshMeta;

        RemoteItemDataInfo(com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc, boolean flag)
        {
            mData = remoteitemdesc;
            mRefreshMeta = flag;
        }
    }

    private class ServerContentUpdater
        implements com.arcsoft.adk.atv.ServerManager.IContentUpdatedListener
    {

        private final HashMap mCurrentQueue;
        private boolean mGetContentAfterGetSort;
        Handler mRetryHandler = new _cls1();
        private final ArrayList mRetryQueue;
        final DataTask this$0;

        private boolean addRetryBrowseInfo(BrowseInfo browseinfo)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = false;
            if (browseinfo != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            String s = browseinfo.mServerUDN;
            flag = false;
            if (s == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            String s1 = getServerUDN();
            flag = false;
            if (s1 == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            boolean flag1 = browseinfo.mServerUDN.equals(getServerUDN());
            flag = false;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            int i = browseinfo.mUpdateType;
            flag = false;
            if (i == 3)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (browseinfo.mRetryCnt < 2)
            {
                Log.w("DataTask", (new StringBuilder()).append("Add retry item dirID = ").append(browseinfo.mDirObjID).toString());
                browseinfo.mRetryCnt = 1 + browseinfo.mRetryCnt;
                mRetryQueue.add(browseinfo);
                break MISSING_BLOCK_LABEL_235;
            }
            int j = browseinfo.mRetryType;
            flag = false;
            if (j != 2)
            {
                continue; /* Loop/switch isn't completed */
            }
            int k = browseinfo.mDelayRetryTimes;
            flag = false;
            if (k >= 4)
            {
                continue; /* Loop/switch isn't completed */
            }
            Log.w("DataTask", (new StringBuilder()).append("Retry item later dirID = ").append(browseinfo.mDirObjID).toString());
            browseinfo.mDelayRetryTimes = 1 + browseinfo.mDelayRetryTimes;
            browseinfo.mRetryCnt = 0;
            mRetryHandler.sendMessageDelayed(mRetryHandler.obtainMessage(0, browseinfo), 5000L);
            break MISSING_BLOCK_LABEL_235;
            Exception exception;
            exception;
            throw exception;
            flag = true;
            if (true) goto _L1; else goto _L3
_L3:
        }

        private void browseChildDirectory(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnDirContentUpdated dataondircontentupdated)
        {
            this;
            JVM INSTR monitorenter ;
            if (dataondircontentupdated != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            if ((BrowseInfo)mCurrentQueue.get(Integer.valueOf(dataondircontentupdated.m_nUpdateId)) != null)
            {
                Iterator iterator = dataondircontentupdated.m_Contents.iterator();
                while (iterator.hasNext()) 
                {
                    com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc = (com.arcsoft.adk.atv.UPnP.RemoteItemDesc)iterator.next();
                    if (remoteitemdesc.m_lProperty == 2L && (remoteitemdesc.m_strAribObjectType == null || !OEMConfig.CHANNEL_DIR_ARIB.containsKey(remoteitemdesc.m_strAribObjectType)))
                    {
                        doBrowse(new BrowseInfo(s, remoteitemdesc.m_strObjId, 1, false));
                    }
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        private void cancelAllGetContent()
        {
            this;
            JVM INSTR monitorenter ;
            mRetryHandler.removeMessages(0);
            DLNA.instance().getServerManager().unregisterContentUpdatedListener(mContentListenr);
            Set set = mCurrentQueue.keySet();
            mBuffer.clear();
            mGetContentAfterGetSort = false;
            ServerManager servermanager = DLNA.instance().getServerManager();
            for (Iterator iterator = set.iterator(); iterator.hasNext(); servermanager.cancelBrowseSearch(((Integer)iterator.next()).intValue())) { }
            break MISSING_BLOCK_LABEL_99;
            Exception exception;
            exception;
            throw exception;
            mCurrentQueue.clear();
            this;
            JVM INSTR monitorexit ;
        }

        private void checkGetContentOnGetSort(String s)
        {
            this;
            JVM INSTR monitorenter ;
            String s1 = getServerUDN();
            if (s1 == null)
            {
                break MISSING_BLOCK_LABEL_35;
            }
            if (s.equals(s1) && mGetContentAfterGetSort)
            {
                getAllServerContents(s1);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private boolean checkRetryQueue()
        {
            boolean flag = false;
            this;
            JVM INSTR monitorenter ;
            if (mRetryQueue.size() > 0) goto _L2; else goto _L1
_L1:
            Log.e("DataTask", "checkRetryQueue : no retry item");
_L4:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            if (mCurrentQueue.size() <= 0)
            {
                break MISSING_BLOCK_LABEL_56;
            }
            Log.e("DataTask", "checkRetryQueue : current queue not null");
            flag = false;
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            throw exception;
            BrowseInfo browseinfo;
            ListIterator listiterator = mRetryQueue.listIterator();
            do
            {
                if (!listiterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_183;
                }
                browseinfo = (BrowseInfo)listiterator.next();
                listiterator.remove();
                if (browseinfo.mRetryCnt <= 2 && browseinfo.mUpdateType != 3)
                {
                    break;
                }
                Log.e("DataTask", (new StringBuilder()).append("checkRetryQueue : remove retry item : retry cnt(").append(browseinfo.mRetryCnt).append(" > 2").toString());
            } while (true);
            Log.w("DataTask", (new StringBuilder()).append("Retry item : obj id = ").append(browseinfo.mDirObjID).toString());
            doBrowse(browseinfo);
            int i = mCurrentQueue.size();
            flag = false;
            if (i > 0)
            {
                flag = true;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        private void doBrowse(BrowseInfo browseinfo)
        {
            this;
            JVM INSTR monitorenter ;
            int i = DLNA.instance().getServerManager().browse(browseinfo.mServerUDN, browseinfo.mDirObjID, browseinfo.refreshMeta);
            if (i < 0) goto _L2; else goto _L1
_L1:
            mCurrentQueue.put(Integer.valueOf(i), browseinfo);
            Log.i("DataTask", (new StringBuilder()).append("Browse start(").append(mCurDirectories).append(") : update id = ").append(i).toString());
_L4:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            Log.e("DataTask", "Browse error");
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        private boolean getAllServerContents(String s)
        {
            this;
            JVM INSTR monitorenter ;
            if (s == null) goto _L2; else goto _L1
_L1:
            RemoteDataDBHelper remotedatadbhelper = mDBHelper;
            if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
            boolean flag = false;
_L4:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L3:
            ServerManager servermanager;
            mGetContentAfterGetSort = false;
            servermanager = DLNA.instance().getServerManager();
            servermanager.registerContentUpdatedListener(mContentListenr);
            if (servermanager.isServerOnline(s))
            {
                break MISSING_BLOCK_LABEL_65;
            }
            flag = false;
              goto _L4
            if (mDataMode != GetDataMode.Search) goto _L6; else goto _L5
_L5:
            com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
            mediaserverdesc = servermanager.getServerDesc(s);
            Log.v("zdf", (new StringBuilder()).append("DataTask, DMSName = ").append(mediaserverdesc.m_strFriendlyName).toString());
            if (!mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ActionCam") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("IRONX") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ActionCam DMS") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ArcSoft DMS for AEE") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("DMS for DV") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ArcSoft DMS for DXG") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ArcSoft DMS for Salix") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("Salix ActionCam DMS") && !mediaserverdesc.m_strFriendlyName.equalsIgnoreCase("ArcSoft DMS") && !mediaserverdesc.m_strFriendlyName.startsWith("ArcSoft DMS")) goto _L8; else goto _L7
_L7:
            String as[] = {
                "upnp:class derivedfrom \"object.item.imageItem\" or upnp:class derivedfrom \"object.item.videoItem\""
            };
_L18:
            int ai[] = servermanager.search(s, "0", as, "-dc:date", Boolean.valueOf(true).booleanValue());
            boolean flag1 = false;
            if (ai == null) goto _L10; else goto _L9
_L9:
            int i = ai.length;
            int j = 0;
_L20:
            if (j >= i) goto _L10; else goto _L11
_L11:
            if (ai[j] >= 0) goto _L13; else goto _L12
_L12:
            flag1 = false;
_L10:
            if (!flag1) goto _L15; else goto _L14
_L14:
            int k = ai.length;
            int l = 0;
_L17:
            if (l >= k)
            {
                break; /* Loop/switch isn't completed */
            }
            int i1 = ai[l];
            BrowseInfo browseinfo = new BrowseInfo(s, "0", 3, false);
            mCurrentQueue.put(Integer.valueOf(i1), browseinfo);
            l++;
            if (true) goto _L17; else goto _L16
_L8:
            as = (new String[] {
                "upnp:class derivedfrom \"object.item.imageItem\"", "upnp:class derivedfrom \"object.item.videoItem\""
            });
              goto _L18
_L19:
            byte byte0;
            if (byte0 == 3)
            {
                break MISSING_BLOCK_LABEL_446;
            }
            doBrowse(new BrowseInfo(s, mCurDirectories, byte0, false));
            flag = true;
              goto _L4
_L15:
            byte0 = 1;
              goto _L19
_L6:
            byte0 = 2;
              goto _L19
            Exception exception;
            exception;
            throw exception;
_L13:
            flag1 = true;
            j++;
              goto _L20
_L16:
            byte0 = 3;
              goto _L19
        }

        private boolean getDigaXP9eGetContainerIds(String s)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = false;
            if (s != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            ServerManager servermanager;
            boolean flag1;
            servermanager = DLNA.instance().getServerManager();
            flag1 = servermanager.isServerOnline(s);
            flag = false;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            int i = servermanager.digaXP9eGetContainerIds(s, "VGA-TS");
            flag = false;
            if (i == 0)
            {
                flag = true;
            }
            if (true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        private boolean getPlayItemContents(com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
        {
            this;
            JVM INSTR monitorenter ;
            String s = getServerUDN();
            boolean flag = false;
            if (s == null) goto _L2; else goto _L1
_L1:
            RemoteDataDBHelper remotedatadbhelper = mDBHelper;
            flag = false;
            if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L3:
            boolean flag1;
            ServerManager servermanager = DLNA.instance().getServerManager();
            servermanager.registerContentUpdatedListener(mContentListenr);
            flag1 = servermanager.isServerOnline(getServerUDN());
            flag = false;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            doBrowse(new BrowseInfo(getServerUDN(), remoteitemdesc.m_strObjId, 2, true));
            flag = true;
            if (true) goto _L2; else goto _L4
_L4:
            Exception exception;
            exception;
            throw exception;
        }

        private boolean getServerEncodedFolder(String s)
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = false;
            if (s == null) goto _L2; else goto _L1
_L1:
            RemoteDataDBHelper remotedatadbhelper = mDBHelper;
            flag = false;
            if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L3:
            boolean flag1;
            mGetContentAfterGetSort = false;
            ServerManager servermanager = DLNA.instance().getServerManager();
            servermanager.registerContentUpdatedListener(mContentListenr);
            flag1 = servermanager.isServerOnline(s);
            flag = false;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            doBrowse(new BrowseInfo(s, mCurDirectories, 2, false));
            flag = true;
            if (true) goto _L2; else goto _L4
_L4:
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isReceivingData()
        {
            this;
            JVM INSTR monitorenter ;
            if (mRetryQueue.size() > 0) goto _L2; else goto _L1
_L1:
            int i = mCurrentQueue.size();
            if (i <= 0) goto _L3; else goto _L2
_L2:
            boolean flag = true;
_L5:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L3:
            flag = false;
            if (true) goto _L5; else goto _L4
_L4:
            Exception exception;
            exception;
            throw exception;
        }

        public void onDirContentUpdated(com.arcsoft.adk.atv.MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1)
        {
            boolean flag = true;
            this;
            JVM INSTR monitorenter ;
            if (dataondircontentupdated != null) goto _L2; else goto _L1
_L1:
            Log.e("DataTask", "onDirContentUpdated data is NULL");
_L3:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            String s2 = getServerUDN();
            if (s2 == null)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            if (s.equals(s2))
            {
                break MISSING_BLOCK_LABEL_78;
            }
            Log.e("DataTask", "onDirContentUpdated serverudn is invalid");
            mCurrentQueue.remove(Integer.valueOf(dataondircontentupdated.m_nUpdateId));
              goto _L3
            Exception exception;
            exception;
            throw exception;
            BrowseInfo browseinfo;
            Log.w("DataTask", (new StringBuilder()).append("onDirContentUpdated res = ").append(dataondircontentupdated.m_nRes).append(", update id = ").append(dataondircontentupdated.m_nUpdateId).append(" Count/AllCnt=").append(dataondircontentupdated.m_nCount).append("/").append(dataondircontentupdated.m_nTotalSize).toString());
            if (dataondircontentupdated.m_Contents != null)
            {
                Log.w("DataTask", (new StringBuilder()).append(" Content = ").append(dataondircontentupdated.m_Contents.size()).toString());
            }
            browseinfo = (BrowseInfo)mCurrentQueue.get(Integer.valueOf(dataondircontentupdated.m_nUpdateId));
            if (browseinfo != null)
            {
                break MISSING_BLOCK_LABEL_223;
            }
            Log.e("DataTask", "Update id not in queue");
              goto _L3
            int i = dataondircontentupdated.m_nCount;
            boolean flag1;
            flag1 = false;
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_269;
            }
            int j = dataondircontentupdated.m_nTotalSize;
            flag1 = false;
            if (j != 0)
            {
                break MISSING_BLOCK_LABEL_269;
            }
            browseinfo.mRetryType = 1;
            Log.w("DataTask", "Retry null dir");
            flag1 = true;
            if (dataondircontentupdated.m_nRes != 0)
            {
                browseinfo.mRetryType = 2;
                Log.w("DataTask", "Retry failed dir");
            }
            if (dataondircontentupdated.m_nRes == 0 && !flag1) goto _L5; else goto _L4
_L4:
            dataondircontentupdated.m_Contents.clear();
            dataondircontentupdated.m_nTotalSize = 0;
            dataondircontentupdated.m_nCount = 0;
            dataondircontentupdated.m_nIndex = 0;
            addRetryBrowseInfo(browseinfo);
_L7:
            DataTask datatask = DataTask.this;
            boolean flag2;
            if (dataondircontentupdated.m_nIndex + dataondircontentupdated.m_nCount == dataondircontentupdated.m_nTotalSize)
            {
                flag2 = flag;
            } else
            {
                flag2 = false;
            }
            datatask.mIsCurrentFinish = flag2;
            if (mIsCurrentFinish)
            {
                Log.i("DataTask", (new StringBuilder()).append(" ").append(dataondircontentupdated.m_nUpdateId).append(" Finished").toString());
                mCurrentQueue.remove(Integer.valueOf(dataondircontentupdated.m_nUpdateId));
            }
            checkRetryQueue();
            Iterator iterator;
            com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc;
            int k;
            com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc1;
            if (isReceivingData())
            {
                flag = false;
            }
            for (iterator = dataondircontentupdated.m_Contents.iterator(); iterator.hasNext(); mBuffer.append(new RemoteItemDataInfo(remoteitemdesc1, browseinfo.refreshMeta)))
            {
                remoteitemdesc1 = (com.arcsoft.adk.atv.UPnP.RemoteItemDesc)iterator.next();
                Log.e("DataTask", (new StringBuilder()).append("desc.m_lProperty : ").append(remoteitemdesc1.m_lProperty).toString());
                if (remoteitemdesc1.m_lProperty == 2L)
                {
                    int i = 
// JavaClassFileOutputException: get_constant: invalid tag









        private ServerContentUpdater()
        {
            this$0 = DataTask.this;
            super();
            mGetContentAfterGetSort = false;
            mCurrentQueue = new HashMap();
            mRetryQueue = new ArrayList();
        }

    }

    private class ServerListener
        implements com.arcsoft.adk.atv.ServerManager.IServerStatusListener
    {

        final DataTask this$0;

        public void OnDestroyObject(String s, int i)
        {
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
            Log.v("DataTask", (new StringBuilder()).append("OnDigaXP9eGetContainerIds errorcode = ").append(i).toString());
            if (i == 0)
            {
                mCurDirectories = s1;
                boolean flag = mContentListenr.getServerEncodedFolder(s);
                resumeTask();
                if (!flag && mListener != null)
                {
                    synchronized (mListener)
                    {
                        mListener.OnDataTransmittedFinish(getServerUDN(), -1);
                    }
                    return;
                }
            } else
            {
                Log.w("DataTask", (new StringBuilder()).append("OnDigaXP9eGetContainerIds errorcode = ").append(i).toString());
            }
            break MISSING_BLOCK_LABEL_142;
            exception;
            iondataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onGetSearchCapabilities(String s, String s1, int i)
        {
        }

        public void onGetSortCapabilities(String s, String s1, int i)
        {
            mContentListenr.checkGetContentOnGetSort(s);
        }

        public void onServerAdded(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        private ServerListener()
        {
            this$0 = DataTask.this;
            super();
        }

    }


    private static final String EOF_STRING = "SCAN EOF";
    private static final int RETRY_DELAY = 2;
    private static final int RETRY_DELAY_COUNT = 4;
    private static final int RETRY_INTERVAL = 5000;
    private static final int RETRY_MAX_COUNT = 2;
    private static final int RETRY_ONCE = 1;
    private static final int STATUS_DOING = 1;
    private static final int STATUS_NONE = 0;
    private static final int STATUS_QUIT = 2;
    private static final String TAG = "DataTask";
    private static final int UPDATE_BROWSE_CHILD = 1;
    private static final int UPDATE_BROWSE_NONE = 3;
    private static final int UPDATE_BROWSE_ONCE = 2;
    private static final int WRITE_DB_EVERY_TIME = 8;
    private final SafeBuffer mBuffer = new SafeBuffer();
    private boolean mClearData;
    private final ServerContentUpdater mContentListenr = new ServerContentUpdater();
    private String mCurDirectories;
    private boolean mCurFolderOrContainer;
    private RemoteDataDBHelper mDBHelper;
    private GetDataMode mDataMode;
    private int mDirCount;
    boolean mIsCurrentFinish;
    private IOnDataUpdateListener mListener;
    private final Object mMutex = new Object();
    private final ServerListener mServerListener = new ServerListener();
    private int mStatus;
    private ArrayList mVideoRootFolderCacheList;
    private boolean retreiveCompleted;
    int timeIndex;

    DataTask()
    {
        super("DataTask");
        retreiveCompleted = false;
        mDirCount = 0;
        mDBHelper = null;
        mStatus = 0;
        mClearData = false;
        mListener = null;
        mVideoRootFolderCacheList = null;
        mDataMode = GetDataMode.Search;
        mCurDirectories = "0";
        mCurFolderOrContainer = true;
        timeIndex = 0;
        mIsCurrentFinish = false;
        DLNA.instance().getServerManager().registerServerStatusListener(mServerListener);
    }

    private boolean checkAndClearDBData(SQLiteDatabase sqlitedatabase)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mClearData;
        boolean flag1 = false;
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        if (sqlitedatabase != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        if (mDBHelper != null)
        {
            sqlitedatabase = mDBHelper.getManagedDatabase();
        }
        flag1 = false;
        if (sqlitedatabase == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        mClearData = false;
        sqlitedatabase.beginTransaction();
        sqlitedatabase.delete("ResourceTable", null, null);
        sqlitedatabase.delete("MediaTable", null, null);
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataUpdated(getServerUDN());
            }
        }
        break MISSING_BLOCK_LABEL_128;
        exception1;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        flag1 = true;
        if (true) goto _L1; else goto _L3
_L3:
    }

    private int getAmbaVideoTNResource(com.arcsoft.adk.atv.UPnP.PresentItem presentitem)
    {
        Iterator iterator = presentitem.m_ResourceList.iterator();
        int i = -1;
        while (iterator.hasNext()) 
        {
            i++;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
            if (DLNA.matchLocalImageProtocol(presentitem_resource.m_strProtocolInfo) && presentitem_resource.m_strProtocolInfo.indexOf("DLNA.ORG_PN=JPEG_TN") >= 0)
            {
                return i;
            }
        }
        return -1;
    }

    private int getAmbaVideoVGAResource(com.arcsoft.adk.atv.UPnP.PresentItem presentitem)
    {
        Iterator iterator = presentitem.m_ResourceList.iterator();
        int i = -1;
        while (iterator.hasNext()) 
        {
            i++;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
            if (DLNA.matchLocalImageProtocol(presentitem_resource.m_strProtocolInfo))
            {
                int j = presentitem_resource.m_strProtocolInfo.indexOf("DLNA.ORG_CI=1");
                int k = presentitem_resource.m_strProtocolInfo.indexOf("DLNA.ORG_PN=JPEG");
                if (j >= 0 && k < 0)
                {
                    Log.e("DataTask", (new StringBuilder()).append("getAmbaVideoVGAResource: index = ").append(i).toString());
                    return i;
                }
            }
        }
        return -1;
    }

    private int getAudioLocalResIndex(com.arcsoft.adk.atv.UPnP.PresentItem presentitem)
    {
        Iterator iterator = presentitem.m_ResourceList.iterator();
        int i = 0;
        int j = -1;
        long l = 0L;
        long l1 = 0L;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            j++;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
            if (DLNA.matchLocalAudioProtocol(presentitem_resource.m_strProtocolInfo))
            {
                if (presentitem_resource.m_lDuration > l1)
                {
                    l1 = presentitem_resource.m_lDuration;
                    l = presentitem_resource.m_lSize;
                    i = j;
                } else
                if (presentitem_resource.m_lDuration == 0L && presentitem_resource.m_lSize > l)
                {
                    l = presentitem_resource.m_lSize;
                    i = j;
                }
            }
        } while (true);
        return i;
    }

    private void getImgLargeAndTNResource(com.arcsoft.adk.atv.UPnP.PresentItem presentitem, int ai[])
    {
        Iterator iterator;
        int i;
        int j;
        int k;
        long l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        iterator = presentitem.m_ResourceList.iterator();
        i = -1;
        j = 0;
        k = -1;
        l = 0L;
        i1 = -1;
        j1 = 4;
        k1 = 0;
        l1 = -1;
        i2 = -1;
_L11:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
        int j2;
        i++;
        presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
        if (!DLNA.matchLocalImageProtocol(presentitem_resource.m_strProtocolInfo))
        {
            continue; /* Loop/switch isn't completed */
        }
        j2 = presentitem_resource.m_strProtocolInfo.indexOf("DLNA.ORG_PN=");
        if (j2 < 0) goto _L4; else goto _L3
_L3:
        int k2 = presentitem_resource.m_strProtocolInfo.indexOf('_', j2 + "DLNA.ORG_PN=".length());
        int l2 = presentitem_resource.m_strProtocolInfo.indexOf(';', k2);
        char c;
        if (l2 > 0)
        {
            c = presentitem_resource.m_strProtocolInfo.charAt(l2 - 1);
        } else
        {
            c = '\0';
        }
        c;
        JVM INSTR lookupswitch 4: default 188
    //                   68: 290
    //                   71: 300
    //                   77: 284
    //                   78: 274;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        if (presentitem_resource.m_strProtocolInfo.indexOf("DLNA.ORG_CI=0") >= 0)
        {
            l = presentitem_resource.m_lSize;
            j = i;
            i1 = k1;
        }
        if (k1 < j1)
        {
            k = i;
            j1 = k1;
        }
_L4:
        if (presentitem_resource.m_lSize > l && i1 < 0)
        {
            l = presentitem_resource.m_lSize;
            if (l1 != -1)
            {
                j = l1;
            } else
            {
                j = i;
            }
        }
        continue; /* Loop/switch isn't completed */
_L9:
        k = i;
        k1 = 0;
        continue; /* Loop/switch isn't completed */
_L8:
        k1 = 1;
        continue; /* Loop/switch isn't completed */
_L6:
        i2 = i;
        k1 = 2;
        continue; /* Loop/switch isn't completed */
_L7:
        l1 = i;
        k1 = 3;
        if (true) goto _L5; else goto _L2
_L2:
        if (i2 < 0)
        {
            i2 = j;
        }
        if (k < 0)
        {
            k = j;
        }
        ai[0] = j;
        ai[1] = i2;
        ai[2] = k;
        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    private String getServerUDN()
    {
        if (mDBHelper == null)
        {
            return null;
        } else
        {
            return mDBHelper.getServerUDN();
        }
    }

    private int getVideoLocalResIndex(com.arcsoft.adk.atv.UPnP.PresentItem presentitem)
    {
        Iterator iterator = presentitem.m_ResourceList.iterator();
        int i = -1;
        int j = -1;
        long l = -1L;
        long l1 = -1L;
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            j++;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
            if (DLNA.matchLocalVideoProtocol(presentitem_resource.m_strProtocolInfo))
            {
                if (presentitem_resource.m_lDuration > l1)
                {
                    l1 = presentitem_resource.m_lDuration;
                    l = presentitem_resource.m_lSize;
                    i = j;
                } else
                if (presentitem_resource.m_lDuration <= 0L && presentitem_resource.m_lSize > l)
                {
                    l = presentitem_resource.m_lSize;
                    i = j;
                }
            }
        } while (true);
        return i;
    }

    private boolean hasData()
    {
        SQLiteDatabase sqlitedatabase;
        Cursor cursor;
        sqlitedatabase = mDBHelper.getManagedDatabase();
        cursor = null;
        int i;
        cursor = sqlitedatabase.rawQuery("select count(*) from MediaTable", null);
        cursor.moveToFirst();
        i = cursor.getInt(0);
        boolean flag;
        flag = false;
        if (i > 0)
        {
            flag = true;
        }
        if (cursor != null)
        {
            cursor.close();
        }
_L2:
        return flag;
        Exception exception1;
        exception1;
        flag = false;
        if (cursor == null) goto _L2; else goto _L1
_L1:
        cursor.close();
        return false;
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        throw exception;
    }

    private long insertContainerItem(SQLiteDatabase sqlitedatabase, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("PARENT_ID", remoteitemdesc.m_strParentId);
        contentvalues.put("PROPERTY", Integer.valueOf(2));
        contentvalues.put("ITEM_UUID", remoteitemdesc.m_PresentItem.m_strPxnGroupId);
        contentvalues.put("GROUP_ID", remoteitemdesc.m_PresentItem.m_strPxnGroupId);
        contentvalues.put("TITLE", remoteitemdesc.m_PresentItem.m_strPxnGroupTitle);
        return sqlitedatabase.insert("MediaTable", null, contentvalues);
    }

    private long insertDirectoryItem(SQLiteDatabase sqlitedatabase, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        if (remoteitemdesc.m_strAribObjectType != null && OEMConfig.CHANNEL_DIR_ARIB.containsKey(remoteitemdesc.m_strAribObjectType))
        {
            return 0L;
        } else
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("PARENT_ID", remoteitemdesc.m_strParentId);
            contentvalues.put("ITEM_UUID", remoteitemdesc.m_strObjId);
            contentvalues.put("TITLE", remoteitemdesc.m_strTitle);
            contentvalues.put("PROPERTY", Long.valueOf(remoteitemdesc.m_lProperty));
            return sqlitedatabase.insert("MediaTable", null, contentvalues);
        }
    }

    private long insertMediaItem(SQLiteDatabase sqlitedatabase, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        if (remoteitemdesc.m_PresentItem.m_iItemClass != 4) goto _L2; else goto _L1
_L1:
        long l = 0L;
_L8:
        return l;
_L2:
        ContentValues contentvalues;
        String s;
        String s1;
        int i;
        contentvalues = new ContentValues();
        contentvalues.put("PARENT_ID", remoteitemdesc.m_strParentId);
        contentvalues.put("ITEM_UUID", remoteitemdesc.m_strObjId);
        contentvalues.put("UPNP_CLASS", Integer.valueOf(remoteitemdesc.m_PresentItem.m_iItemClass));
        contentvalues.put("TITLE", remoteitemdesc.m_strTitle);
        contentvalues.put("DATE", remoteitemdesc.m_PresentItem.m_strDate);
        contentvalues.put("ARTIST", remoteitemdesc.m_PresentItem.m_strArtist);
        contentvalues.put("ALBUM", remoteitemdesc.m_PresentItem.m_strAlbum);
        contentvalues.put("GENRE", remoteitemdesc.m_PresentItem.m_strGenre);
        contentvalues.put("CREATOR", remoteitemdesc.m_PresentItem.m_strCreator);
        contentvalues.put("PROPERTY", Long.valueOf(remoteitemdesc.m_lProperty));
        contentvalues.put("CHANNEL_NAME", remoteitemdesc.m_PresentItem.m_strChannelName);
        contentvalues.put("GROUP_ID", remoteitemdesc.m_PresentItem.m_strPxnGroupId);
        contentvalues.put("GROUP_TOPFLAG", remoteitemdesc.m_PresentItem.m_strPxnGroupTopFlag);
        contentvalues.put("GROUP_TITLE", remoteitemdesc.m_PresentItem.m_strPxnGroupTitle);
        contentvalues.put("GROUP_MEMBERNUMBER", remoteitemdesc.m_PresentItem.m_strPxnGroupMemberNum);
        s = "";
        s1 = "";
        i = -1;
        if (remoteitemdesc.m_PresentItem.m_AlbumArtUriList != null && remoteitemdesc.m_PresentItem.m_AlbumArtUriList.size() > 0)
        {
            s = ((com.arcsoft.adk.atv.UPnP.PresentItem_AlbumArtUri)remoteitemdesc.m_PresentItem.m_AlbumArtUriList.get(0)).m_strAlbumArtUri;
        }
        Log.e("DataTask", (new StringBuilder()).append("item.m_PresentItem.m_iItemClass: ").append(remoteitemdesc.m_PresentItem.m_iItemClass).toString());
        if (remoteitemdesc.m_PresentItem.m_iItemClass != 3) goto _L4; else goto _L3
_L3:
        int ai[] = {
            -1, -1, -1
        };
        getImgLargeAndTNResource(remoteitemdesc.m_PresentItem, ai);
        i = ai[0];
        int j = remoteitemdesc.m_PresentItem.m_ResourceList.size();
        if (ai[1] >= 0 && ai[1] < j)
        {
            s1 = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)remoteitemdesc.m_PresentItem.m_ResourceList.get(ai[1])).m_strUri;
        }
        if (ai[2] >= 0 && ai[2] < j)
        {
            s = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)remoteitemdesc.m_PresentItem.m_ResourceList.get(ai[2])).m_strUri;
        }
_L6:
        contentvalues.put("ALBUM_URL", s);
        if (-1 == i)
        {
            contentvalues.clear();
            return 0L;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (remoteitemdesc.m_PresentItem.m_iItemClass == 2)
        {
            i = getAudioLocalResIndex(remoteitemdesc.m_PresentItem);
        } else
        if (remoteitemdesc.m_PresentItem.m_iItemClass == 1)
        {
            i = getVideoLocalResIndex(remoteitemdesc.m_PresentItem);
            if (s == null || "".equals(s))
            {
                boolean flag = false;
                Iterator iterator1 = remoteitemdesc.m_PresentItem.m_ResourceList.iterator();
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break;
                    }
                    com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource1 = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator1.next();
                    Log.e("DataTask", (new StringBuilder()).append("insertMediaItem: res.m_strProtocolInfo = ").append(presentitem_resource1.m_strProtocolInfo).toString());
                    if (presentitem_resource1.m_strUri.toLowerCase().contains(".jpg") || presentitem_resource1.m_strUri.contains("jpeg"))
                    {
                        s = presentitem_resource1.m_strUri;
                        flag = true;
                    }
                } while (true);
                if (!flag)
                {
                    int i1 = remoteitemdesc.m_PresentItem.m_ResourceList.size();
                    if (i1 > 0)
                    {
                        int j1 = getAmbaVideoTNResource(remoteitemdesc.m_PresentItem);
                        if (j1 < 0 || j1 >= i1)
                        {
                            j1 = i1 - 1;
                        }
                        s = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)remoteitemdesc.m_PresentItem.m_ResourceList.get(j1)).m_strUri;
                        Log.e("DataTask", (new StringBuilder()).append("insertMediaItem: albumurl = ").append(s).append(", resindex = ").append(j1).toString());
                        int k1 = getAmbaVideoVGAResource(remoteitemdesc.m_PresentItem);
                        if (k1 < 0 || k1 >= i1)
                        {
                            k1 = Math.min(1, i1 - 1);
                        }
                        s1 = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)remoteitemdesc.m_PresentItem.m_ResourceList.get(k1)).m_strUri;
                        Log.e("DataTask", (new StringBuilder()).append("insertMediaItem: largurl = ").append(s1).append(", vgaresindex = ").append(k1).toString());
                    }
                }
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
        l = sqlitedatabase.insert("MediaTable", null, contentvalues);
        if (l != 0L)
        {
            int k = -1;
            Iterator iterator = remoteitemdesc.m_PresentItem.m_ResourceList.iterator();
            while (iterator.hasNext()) 
            {
                contentvalues.clear();
                com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
                if (++k == i)
                {
                    contentvalues.put("LOCAL_FLAG", Integer.valueOf(1));
                }
                contentvalues.put("MEDIA_ID", Long.valueOf(l));
                contentvalues.put("SIZE", Long.valueOf(presentitem_resource.m_lSize));
                contentvalues.put("PROTOCOL_INFO", presentitem_resource.m_strProtocolInfo);
                contentvalues.put("DURATION", Long.valueOf(presentitem_resource.m_lDuration));
                contentvalues.put("RESOLUTION", presentitem_resource.m_strResolution);
                contentvalues.put("PROTECTION", presentitem_resource.m_strProtection);
                contentvalues.put("BITRATE", Long.valueOf(presentitem_resource.m_lBitrate));
                contentvalues.put("BITSPERSAMPLE", Long.valueOf(presentitem_resource.m_lBitsPerSample));
                contentvalues.put("SAMPLFREQUENCY", Long.valueOf(presentitem_resource.m_lSampleFrequency));
                contentvalues.put("NOAUDIOCHANNEL", Long.valueOf(presentitem_resource.m_lNbAudioChannels));
                contentvalues.put("COLORDEPTH", Long.valueOf(presentitem_resource.m_lColorDepth));
                contentvalues.put("RES_URL", presentitem_resource.m_strUri);
                contentvalues.put("PXNCOPYCOUNT", Long.valueOf(presentitem_resource.m_lPxnCopyCount));
                contentvalues.put("VGABITRATE", Long.valueOf(presentitem_resource.m_lPxnVgaContentBitrate));
                contentvalues.put("RES_VGAURL", s1);
                contentvalues.put("VGAPROTOCOL_INFO", presentitem_resource.m_strPxnVgaContentProtocolInfo);
                contentvalues.put("CHAPTERLIST_URL", presentitem_resource.m_strPxnChapterList);
                contentvalues.put("RESUME_POINT", Long.valueOf(presentitem_resource.m_lPxnResumePoint));
                contentvalues.put("CLEARTEXT_SIZE", Long.valueOf(presentitem_resource.m_lCleartextSize));
                contentvalues.put("VGACLEARTEXT_SIZE", Long.valueOf(presentitem_resource.m_lPxnVgaContentCleartextSize));
                sqlitedatabase.insert("ResourceTable", null, contentvalues);
                Log.e("DataTask", (new StringBuilder()).append("insertMediaItem[").append(k).append("]: uri = ").append(presentitem_resource.m_strUri).append(", vgaurl = ").append(s1).toString());
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    private long insertVideoRootDirItem(com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        if (remoteitemdesc.m_strAribObjectType != null && OEMConfig.CHANNEL_DIR_ARIB.containsKey(remoteitemdesc.m_strAribObjectType))
        {
            return 0L;
        } else
        {
            RemoteDBMgr.RootFolderData rootfolderdata = new RemoteDBMgr.RootFolderData();
            rootfolderdata._id = remoteitemdesc.m_strObjId.hashCode();
            rootfolderdata.desc = remoteitemdesc;
            mVideoRootFolderCacheList.add(rootfolderdata);
            return rootfolderdata._id;
        }
    }

    private long updateMediaItem(SQLiteDatabase sqlitedatabase, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        if (remoteitemdesc.m_PresentItem.m_iItemClass != 4) goto _L2; else goto _L1
_L1:
        long l = 0L;
_L4:
        return l;
_L2:
        ContentValues contentvalues = new ContentValues();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(remoteitemdesc.m_strObjId).toString();
        Cursor cursor = sqlitedatabase.query("MediaTable", null, "ITEM_UUID=?", as, null, null, null);
        if (cursor == null)
        {
            return 0L;
        }
        l = 0L;
        if (cursor.moveToNext())
        {
            l = cursor.getInt(cursor.getColumnIndex("_ID"));
        }
        cursor.close();
        if (l != 0L)
        {
            Iterator iterator = remoteitemdesc.m_PresentItem.m_ResourceList.iterator();
            while (iterator.hasNext()) 
            {
                contentvalues.clear();
                com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
                contentvalues.put("RESUME_POINT", Long.valueOf(presentitem_resource.m_lPxnResumePoint));
                String as1[] = new String[1];
                as1[0] = (new StringBuilder()).append("").append(presentitem_resource.m_strUri).toString();
                sqlitedatabase.update("ResourceTable", contentvalues, "RES_URL=?", as1);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void cancelTask()
    {
        this;
        JVM INSTR monitorenter ;
        pauseTask();
        mContentListenr.cancelAllGetContent();
        mBuffer.clear();
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish(getServerUDN(), -1);
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getCurrentFolder()
    {
        if (mDataMode != GetDataMode.Browse)
        {
            return null;
        } else
        {
            return mCurDirectories;
        }
    }

    public boolean isCurrentFinish()
    {
        return mIsCurrentFinish;
    }

    public boolean isCurrentFolderOrContainer()
    {
        if (mDataMode != GetDataMode.Browse)
        {
            return true;
        } else
        {
            return mCurFolderOrContainer;
        }
    }

    public boolean isReceivingData()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mContentListenr.isReceivingData();
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void pauseTask()
    {
        this;
        JVM INSTR monitorenter ;
        mStatus = 0;
        synchronized (mBuffer)
        {
            mBuffer.notify();
        }
        synchronized (mMutex) { }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception2;
        obj;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public void recycle()
    {
        this;
        JVM INSTR monitorenter ;
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerListener);
        cancelTask();
        mStatus = 2;
        synchronized (mMutex)
        {
            mMutex.notify();
        }
        synchronized (mBuffer)
        {
            mBuffer.notify();
        }
        mBuffer.clear();
        (new Thread(new Runnable() {

            final DataTask this$0;

            public void run()
            {
                try
                {
                    join(3000L);
                    return;
                }
                catch (InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
            }

            
            {
                this$0 = DataTask.this;
                super();
            }
        })).start();
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception2;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public boolean requestPlayItemData(com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        pauseTask();
        flag = mContentListenr.getPlayItemContents(remoteitemdesc);
        resumeTask();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_56;
        }
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish(getServerUDN(), -1);
            }
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        exception1;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void requestServerData(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if (mDBHelper == null)
        {
            Log.w("DataTask", "DataBaseHelper not set");
        }
        Log.e("FENG", (new StringBuilder()).append("FENG requestServerData : retreiveCompleteds = ").append(retreiveCompleted).toString());
        if (!retreiveCompleted)
        {
            flag = true;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        boolean flag2 = hasData();
        if (!flag2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        cancelTask();
        mClearData = flag | mClearData;
        checkAndClearDBData(null);
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        mContentListenr.getDigaXP9eGetContainerIds(getServerUDN());
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        boolean flag3;
        flag3 = mContentListenr.getAllServerContents(getServerUDN());
        resumeTask();
        if (flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish(getServerUDN(), -1);
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        exception1;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public void resumeTask()
    {
        this;
        JVM INSTR monitorenter ;
        mStatus = 1;
        synchronized (mBuffer)
        {
            mBuffer.notify();
        }
        synchronized (mMutex)
        {
            mMutex.notify();
        }
        retreiveCompleted = false;
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mDirCount = 0;
                mListener.OnDataTransmittedBegin(getServerUDN());
            }
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception2;
        obj;
        JVM INSTR monitorexit ;
        throw exception2;
        exception3;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception3;
    }

    public void run()
    {
        ArrayList arraylist = new ArrayList();
_L3:
        if (mStatus == 2) goto _L2; else goto _L1
_L1:
        Object obj = mMutex;
        obj;
        JVM INSTR monitorenter ;
        RemoteDataDBHelper remotedatadbhelper;
        if (mStatus != 1)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        remotedatadbhelper = mDBHelper;
        if (remotedatadbhelper != null)
        {
            break MISSING_BLOCK_LABEL_69;
        }
        mMutex.wait();
_L4:
        obj;
        JVM INSTR monitorexit ;
          goto _L3
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L4
        SQLiteDatabase sqlitedatabase = mDBHelper.getManagedDatabase();
        if (!checkAndClearDBData(sqlitedatabase)) goto _L6; else goto _L5
_L5:
        obj;
        JVM INSTR monitorexit ;
          goto _L3
_L6:
        SafeBuffer safebuffer = mBuffer;
        safebuffer;
        JVM INSTR monitorenter ;
        int i;
        arraylist.clear();
        mBuffer.get(8, arraylist);
        i = arraylist.size();
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        mBuffer.wait();
_L7:
        safebuffer;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorexit ;
          goto _L3
        Exception exception4;
        exception4;
        exception4.printStackTrace();
          goto _L7
        Exception exception1;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        sqlitedatabase.beginTransaction();
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        flag = false;
        flag1 = false;
        flag2 = false;
        flag3 = false;
        flag4 = false;
        flag5 = false;
        Iterator iterator = arraylist.iterator();
_L23:
        if (!iterator.hasNext()) goto _L9; else goto _L8
_L8:
        RemoteItemDataInfo remoteitemdatainfo = (RemoteItemDataInfo)iterator.next();
        if (mStatus != 2) goto _L10; else goto _L9
_L9:
        sqlitedatabase.setTransactionSuccessful();
        sqlitedatabase.endTransaction();
        if (!flag5) goto _L12; else goto _L11
_L11:
        synchronized (mListener)
        {
            mListener.OnDataUpdated(getServerUDN());
        }
_L35:
        obj;
        JVM INSTR monitorexit ;
          goto _L3
_L10:
        if (remoteitemdatainfo.mData.m_lProperty != 2L || mDataMode != GetDataMode.Browse) goto _L14; else goto _L13
_L13:
        if (mDataMode != GetDataMode.Browse || !"AV".equals(remoteitemdatainfo.mData.m_strParentId) || !DLNA.instance().getServerManager().isDigaDMS(getServerUDN())) goto _L16; else goto _L15
_L15:
        long l1 = insertVideoRootDirItem(remoteitemdatainfo.mData);
        flag4 = true;
          goto _L17
_L16:
        l1 = insertDirectoryItem(sqlitedatabase, remoteitemdatainfo.mData);
          goto _L17
_L14:
        if (remoteitemdatainfo.mData.m_lProperty != 1L) goto _L19; else goto _L18
_L18:
        if (!remoteitemdatainfo.mRefreshMeta) goto _L21; else goto _L20
_L20:
        long l = updateMediaItem(sqlitedatabase, remoteitemdatainfo.mData);
_L30:
        if (l == 0L) goto _L23; else goto _L22
_L22:
        remoteitemdatainfo.mData.m_PresentItem.m_iItemClass;
        JVM INSTR tableswitch 1 3: default 626
    //                   1 629
    //                   2 635
    //                   3 641;
           goto _L24 _L25 _L26 _L27
_L24:
        if (true) goto _L23; else goto _L28
_L28:
_L21:
        l = insertMediaItem(sqlitedatabase, remoteitemdatainfo.mData);
        if (l == 0L) goto _L30; else goto _L29
_L29:
        if (remoteitemdatainfo.mData.m_PresentItem.m_strPxnGroupTopFlag == null || remoteitemdatainfo.mData.m_PresentItem.m_strPxnGroupTopFlag.length() == 0) goto _L30; else goto _L31
_L31:
        insertContainerItem(sqlitedatabase, remoteitemdatainfo.mData);
        flag3 = true;
          goto _L30
_L19:
        if (remoteitemdatainfo.mData.m_strObjId != "SCAN EOF") goto _L23; else goto _L32
_L32:
        GetDataMode getdatamode;
        GetDataMode getdatamode1;
        getdatamode = mDataMode;
        getdatamode1 = GetDataMode.Browse;
        if (getdatamode == getdatamode1)
        {
            flag5 = true;
        }
          goto _L23
        exception2;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception2;
_L34:
        synchronized (mListener)
        {
            mListener.OnDataIncreased(getServerUDN(), flag4, flag3, flag, flag1, flag2);
        }
        break; /* Loop/switch isn't completed */
        exception3;
        iondataupdatelistener1;
        JVM INSTR monitorexit ;
        throw exception3;
_L2:
        return;
_L17:
        if (l1 == 0L) goto _L23; else goto _L33
_L33:
        flag3 = true;
          goto _L23
_L25:
        flag2 = true;
          goto _L23
_L26:
        flag = true;
          goto _L23
_L27:
        flag1 = true;
          goto _L23
_L12:
        if (!flag && !flag1 && !flag2 && !flag3) goto _L35; else goto _L34
    }

    public void setBrowseFolderByFolder(boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if (!flag) goto _L2; else goto _L1
_L1:
        GetDataMode getdatamode = GetDataMode.Browse;
_L5:
        GetDataMode getdatamode1 = mDataMode;
        if (getdatamode != getdatamode1) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        getdatamode = GetDataMode.Search;
          goto _L5
_L4:
        mDataMode = getdatamode;
        requestServerData(true, flag1);
          goto _L3
        Exception exception;
        exception;
        throw exception;
          goto _L5
    }

    public boolean setCurrentFolder(String s, boolean flag, boolean flag1)
    {
        if (mDataMode != GetDataMode.Browse)
        {
            return false;
        }
        mCurDirectories = s;
        if (mCurFolderOrContainer != flag && mVideoRootFolderCacheList.size() > 0)
        {
            mCurFolderOrContainer = flag;
            synchronized (mListener)
            {
                mListener.OnDataUpdated(getServerUDN());
            }
            return true;
        }
        break MISSING_BLOCK_LABEL_75;
        exception;
        iondataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
        mCurFolderOrContainer = flag;
        if ("AV".equals(s))
        {
            mVideoRootFolderCacheList.clear();
        }
        requestServerData(true, flag1);
        return true;
    }

    public void setDatabaseHelper(RemoteDataDBHelper remotedatadbhelper, String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        cancelTask();
        mClearData = false;
        mDBHelper = remotedatadbhelper;
        if (s == null)
        {
            s = "0";
        }
        mCurDirectories = s;
        mCurFolderOrContainer = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnDataUpdateListener(IOnDataUpdateListener iondataupdatelistener)
    {
        mListener = iondataupdatelistener;
    }

    public void setVideoRootFolderDataCache(ArrayList arraylist)
    {
        this;
        JVM INSTR monitorenter ;
        cancelTask();
        mVideoRootFolderCacheList = arraylist;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }



/*
    static int access$1002(DataTask datatask, int i)
    {
        datatask.mDirCount = i;
        return i;
    }

*/


/*
    static int access$1008(DataTask datatask)
    {
        int i = datatask.mDirCount;
        datatask.mDirCount = i + 1;
        return i;
    }

*/








/*
    static String access$1602(DataTask datatask, String s)
    {
        datatask.mCurDirectories = s;
        return s;
    }

*/



/*
    static boolean access$802(DataTask datatask, boolean flag)
    {
        datatask.retreiveCompleted = flag;
        return flag;
    }

*/


    // Unreferenced inner class com/arcsoft/mediaplus/datasource/db/DataTask$ServerContentUpdater$1

/* anonymous class */
    class ServerContentUpdater._cls1 extends Handler
    {

        final ServerContentUpdater this$1;

        public void handleMessage(Message message)
        {
_L2:
            return;
            BrowseInfo browseinfo;
            if (message.what != 0 || (browseinfo = (BrowseInfo)message.obj) == null) goto _L2; else goto _L1
_L1:
            browseinfo.mRetryCnt = 0;
            if (browseinfo.mUpdateType == 3) goto _L2; else goto _L3
_L3:
            boolean flag;
            Log.w("DataTask", (new StringBuilder()).append("Handle retry item , do retry").append(browseinfo.mDirObjID).toString());
            flag = isReceivingData();
            addRetryBrowseInfo(browseinfo);
            checkRetryQueue();
            if (!isReceivingData() || flag) goto _L2; else goto _L4
_L4:
            retreiveCompleted = false;
            if (mListener == null) goto _L2; else goto _L5
_L5:
            synchronized (mListener)
            {
                mDirCount = 0;
                mListener.OnDataTransmittedBegin(getServerUDN());
            }
            return;
            exception;
            iondataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                this$1 = ServerContentUpdater.this;
                super();
            }
    }

}
