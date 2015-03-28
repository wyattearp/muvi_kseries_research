// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class ChannelDataTask_Search extends Thread
    implements Recyclable
{
    static class ChannelDataInfo
    {

        int mChannelID;
        com.arcsoft.adk.atv.UPnP.RemoteItemDesc mData;
        boolean mFinishFlag;

        public static ChannelDataInfo getFinishFlagInstance(int i)
        {
            ChannelDataInfo channeldatainfo = new ChannelDataInfo(i, null);
            channeldatainfo.mFinishFlag = true;
            return channeldatainfo;
        }

        ChannelDataInfo(int i, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
        {
            mChannelID = i;
            mData = remoteitemdesc;
            mFinishFlag = false;
        }
    }

    public static interface IOnChannelDataUpdateListener
    {

        public abstract void OnChannelDataUpdated(String s, Set set);

        public abstract void OnDataTransmittedBegin(String s, Set set);

        public abstract void OnDataTransmittedFinish(String s, Set set);
    }

    private class ServerContentUpdater
        implements com.arcsoft.adk.atv.ServerManager.IContentUpdatedListener
    {

        private final HashMap mGetContentInfos;
        final ChannelDataTask_Search this$0;

        private void cancelAllGetContent()
        {
            this;
            JVM INSTR monitorenter ;
            DLNA.instance().getServerManager().unregisterContentUpdatedListener(mContentListenr);
            ServerManager servermanager = DLNA.instance().getServerManager();
            for (Iterator iterator = mGetContentInfos.values().iterator(); iterator.hasNext(); servermanager.cancelBrowseSearch(((Integer)iterator.next()).intValue())) { }
            break MISSING_BLOCK_LABEL_71;
            Exception exception;
            exception;
            throw exception;
            mGetContentInfos.clear();
            mBuffer.clear();
            this;
            JVM INSTR monitorexit ;
        }

        private void cancelGetContent(int i)
        {
            this;
            JVM INSTR monitorenter ;
            ServerManager servermanager;
            Integer integer;
            servermanager = DLNA.instance().getServerManager();
            integer = (Integer)mGetContentInfos.get(Integer.valueOf(i));
            if (integer == null)
            {
                break MISSING_BLOCK_LABEL_83;
            }
            mGetContentInfos.remove(Integer.valueOf(i));
            servermanager.cancelBrowseSearch(integer.intValue());
            Comparator comparator = new Comparator() {

                final ServerContentUpdater this$1;

                public int compare(ChannelDataInfo channeldatainfo, ChannelDataInfo channeldatainfo1)
                {
                    return channeldatainfo == null || channeldatainfo1 == null || channeldatainfo.mChannelID != channeldatainfo1.mChannelID ? 1 : 0;
                }

                public volatile int compare(Object obj, Object obj1)
                {
                    return compare((ChannelDataInfo)obj, (ChannelDataInfo)obj1);
                }

            
            {
                this$1 = ServerContentUpdater.this;
                super();
            }
            };
            mBuffer.removeSame(new ChannelDataInfo(i, null), comparator);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private void getChannelContents(String s, HashSet hashset)
        {
            this;
            JVM INSTR monitorenter ;
            if (s == null)
            {
                break MISSING_BLOCK_LABEL_20;
            }
            HashMap hashmap = mDataCacheList;
              goto _L1
_L3:
            this;
            JVM INSTR monitorexit ;
            return;
_L1:
            if (hashmap == null || hashset == null) goto _L3; else goto _L2
_L2:
            ServerManager servermanager;
            HashMap hashmap1;
            Iterator iterator;
            servermanager = DLNA.instance().getServerManager();
            servermanager.registerContentUpdatedListener(mContentListenr);
            if (!servermanager.isServerOnline(s))
            {
                continue; /* Loop/switch isn't completed */
            }
            hashmap1 = new HashMap();
            iterator = hashset.iterator();
_L5:
            Integer integer;
            String s1;
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_129;
                }
                integer = (Integer)iterator.next();
                s1 = (String)ChannelDataTask_Search.SEARCH_STRING_MAP.get(integer);
            } while (s1 == null);
            hashmap1.put(integer, s1);
            if (true) goto _L5; else goto _L4
_L4:
            Exception exception;
            exception;
            throw exception;
            String as[];
            int ai[];
            Set set;
            as = new String[hashmap1.size()];
            ai = new int[hashmap1.size()];
            set = hashmap1.entrySet();
            int i = 0;
            Iterator iterator1 = set.iterator();
_L6:
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_228;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            ai[i] = ((Integer)entry.getKey()).intValue();
            as[i] = (String)entry.getValue();
            i++;
              goto _L6
            int ai1[] = servermanager.search(s, "0", as, "", true);
            int j;
            if (ai1 == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            j = 0;
_L8:
            if (j >= ai1.length)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (ai1[j] >= 0)
            {
                mGetContentInfos.put(Integer.valueOf(ai[j]), Integer.valueOf(ai1[j]));
            }
            j++;
            if (true) goto _L8; else goto _L7
_L7:
            if (true) goto _L3; else goto _L9
_L9:
        }

        public Set getCurrentUpdateChannels()
        {
            this;
            JVM INSTR monitorenter ;
            HashSet hashset = new HashSet(mGetContentInfos.keySet());
            this;
            JVM INSTR monitorexit ;
            return hashset;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isReceivingData()
        {
            this;
            JVM INSTR monitorenter ;
            int i = mGetContentInfos.size();
            boolean flag;
            if (i > 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            this;
            JVM INSTR monitorexit ;
            return flag;
            Exception exception;
            exception;
            throw exception;
        }

        public void onDirContentUpdated(com.arcsoft.adk.atv.MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1)
        {
            this;
            JVM INSTR monitorenter ;
            if (dataondircontentupdated != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            String s2 = getServerUDN();
            if (s2 == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!s.equals(s2))
            {
                continue; /* Loop/switch isn't completed */
            }
            Exception exception;
            boolean flag;
            Set set;
            Integer integer;
            if (dataondircontentupdated.m_nIndex + dataondircontentupdated.m_nCount == dataondircontentupdated.m_nTotalSize)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            set = mGetContentInfos.entrySet();
            integer = null;
            Iterator iterator = set.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                if (((Integer)entry.getValue()).intValue() == dataondircontentupdated.m_nUpdateId)
                {
                    integer = (Integer)entry.getKey();
                }
            } while (true);
            if (integer == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_149;
            }
            mGetContentInfos.remove(integer);
            com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc;
            for (Iterator iterator1 = dataondircontentupdated.m_Contents.iterator(); iterator1.hasNext(); mBuffer.append(new ChannelDataInfo(integer.intValue(), remoteitemdesc)))
            {
                remoteitemdesc = (com.arcsoft.adk.atv.UPnP.RemoteItemDesc)iterator1.next();
            }

            break MISSING_BLOCK_LABEL_217;
            exception;
            throw exception;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_241;
            }
            mBuffer.append(ChannelDataInfo.getFinishFlagInstance(integer.intValue()));
            synchronized (mBuffer)
            {
                mBuffer.notify();
            }
            if (true) goto _L1; else goto _L3
_L3:
            exception1;
            safebuffer;
            JVM INSTR monitorexit ;
            throw exception1;
        }




        private ServerContentUpdater()
        {
            this$0 = ChannelDataTask_Search.this;
            super();
            mGetContentInfos = new HashMap();
        }

    }

    private class ServerListener
        implements com.arcsoft.adk.atv.ServerManager.IServerStatusListener
    {

        final ChannelDataTask_Search this$0;

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
        }

        private ServerListener()
        {
            this$0 = ChannelDataTask_Search.this;
            super();
        }

    }


    private static final int PARSE_DATA_EVERY_TIME = 20;
    static HashMap SEARCH_STRING_MAP;
    private static final int STATUS_DOING = 1;
    private static final int STATUS_NONE = 0;
    private static final int STATUS_QUIT = 2;
    private final SafeBuffer mBuffer = new SafeBuffer();
    private final ServerContentUpdater mContentListenr = new ServerContentUpdater();
    private HashMap mDataCacheList;
    private IOnChannelDataUpdateListener mListener;
    private final Object mMutex = new Object();
    private final ServerListener mServerListener = new ServerListener();
    private String mServerUDN;
    private int mStatus;

    ChannelDataTask_Search()
    {
        super("ChannelDataTask");
        mDataCacheList = null;
        mServerUDN = null;
        mStatus = 0;
        mListener = null;
        DLNA.instance().getServerManager().registerServerStatusListener(mServerListener);
    }

    private boolean checkAndClearData(HashSet hashset)
    {
        if (mDataCacheList == null || mServerUDN == null)
        {
            return false;
        }
        HashMap hashmap = mDataCacheList;
        hashmap;
        JVM INSTR monitorenter ;
        Iterator iterator = hashset.iterator();
_L2:
        ArrayList arraylist;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_83;
            }
            Integer integer = (Integer)iterator.next();
            arraylist = (ArrayList)mDataCacheList.get(integer);
        } while (arraylist == null);
        arraylist.clear();
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        hashmap;
        JVM INSTR monitorexit ;
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnChannelDataUpdated(getServerUDN(), hashset);
            }
        }
        break MISSING_BLOCK_LABEL_129;
        exception1;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        return true;
    }

    private String getServerUDN()
    {
        return mServerUDN;
    }

    private long insertMediaItem(ChannelDataInfo channeldatainfo)
    {
        if (channeldatainfo.mData == null)
        {
            return 0L;
        }
        HashMap hashmap = mDataCacheList;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mDataCacheList.get(Integer.valueOf(channeldatainfo.mChannelID));
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return 0L;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        long l;
        ChannelDataMgr.ChannelData channeldata = new ChannelDataMgr.ChannelData();
        channeldata._id = System.currentTimeMillis();
        channeldata.itemDesc = channeldatainfo.mData;
        channeldata.hdRes = null;
        arraylist.add(channeldata);
        l = channeldata._id;
        hashmap;
        JVM INSTR monitorexit ;
        return l;
    }

    static int[] integerSetToArray(Set set)
    {
        int ai[];
        if (set.size() <= 0)
        {
            ai = null;
        } else
        {
            ai = new int[set.size()];
            int i = 0;
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) 
            {
                ai[i] = ((Integer)iterator.next()).intValue();
                i++;
            }
        }
        return ai;
    }

    public void cancelTask()
    {
        this;
        JVM INSTR monitorenter ;
        pauseTask();
        mContentListenr.cancelAllGetContent();
        mBuffer.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void cancelTask(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mContentListenr.cancelGetContent(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Set getCurrentUpdateChannels()
    {
        this;
        JVM INSTR monitorenter ;
        Set set = mContentListenr.getCurrentUpdateChannels();
        this;
        JVM INSTR monitorexit ;
        return set;
        Exception exception;
        exception;
        throw exception;
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
        Set set = getCurrentUpdateChannels();
        if (set.size() > 0 && mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish(getServerUDN(), set);
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
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception3;
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

            final ChannelDataTask_Search this$0;

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
                this$0 = ChannelDataTask_Search.this;
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

    public void requestChannelData(HashSet hashset)
    {
        this;
        JVM INSTR monitorenter ;
        if (mDataCacheList == null)
        {
            Log.w("ChannelDataTask", "mDataCacheList not set");
        }
        pauseTask();
        for (Iterator iterator = hashset.iterator(); iterator.hasNext(); cancelTask(((Integer)iterator.next()).intValue())) { }
        break MISSING_BLOCK_LABEL_59;
        Exception exception;
        exception;
        throw exception;
        checkAndClearData(hashset);
        mContentListenr.getChannelContents(getServerUDN(), hashset);
        resumeTask();
        this;
        JVM INSTR monitorexit ;
    }

    public void resumeTask()
    {
        this;
        JVM INSTR monitorenter ;
        Set set = getCurrentUpdateChannels();
        if (set.size() > 0 && mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedBegin(getServerUDN(), set);
            }
        }
        mStatus = 1;
        synchronized (mBuffer)
        {
            mBuffer.notify();
        }
        synchronized (mMutex)
        {
            mMutex.notify();
        }
        this;
        JVM INSTR monitorexit ;
        return;
        exception3;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception3;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        exception2;
        obj;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public void run()
    {
        ArrayList arraylist;
        HashSet hashset;
        HashSet hashset1;
        arraylist = new ArrayList();
        hashset = new HashSet();
        hashset1 = new HashSet();
_L11:
        if (mStatus == 2)
        {
            break MISSING_BLOCK_LABEL_380;
        }
        Object obj = mMutex;
        obj;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        if (mStatus != 1)
        {
            break MISSING_BLOCK_LABEL_60;
        }
        hashmap = mDataCacheList;
        if (hashmap != null)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        mMutex.wait();
_L1:
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        SafeBuffer safebuffer = mBuffer;
        safebuffer;
        JVM INSTR monitorenter ;
        int i;
        arraylist.clear();
        mBuffer.get(20, arraylist);
        i = arraylist.size();
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_163;
        }
        mBuffer.wait();
_L2:
        safebuffer;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        Exception exception4;
        exception4;
        exception4.printStackTrace();
          goto _L2
        Exception exception1;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        Iterator iterator = arraylist.iterator();
_L8:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        ChannelDataInfo channeldatainfo = (ChannelDataInfo)iterator.next();
        if (mStatus != 2) goto _L5; else goto _L4
_L4:
        Log.e("DB", "Insert channel....");
        if (hashset.size() > 0)
        {
            synchronized (mListener)
            {
                mListener.OnChannelDataUpdated(getServerUDN(), new HashSet(hashset));
            }
        }
        if (hashset1.size() > 0)
        {
            synchronized (mListener)
            {
                mListener.OnDataTransmittedFinish(getServerUDN(), new HashSet(hashset1));
            }
        }
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
_L5:
        if (!channeldatainfo.mFinishFlag) goto _L7; else goto _L6
_L6:
        hashset1.add(Integer.valueOf(channeldatainfo.mChannelID));
          goto _L8
_L7:
        if (channeldatainfo.mData.m_lProperty != 1L || insertMediaItem(channeldatainfo) == 0L) goto _L8; else goto _L9
_L9:
        hashset.add(Integer.valueOf(channeldatainfo.mChannelID));
          goto _L8
        exception3;
        ionchanneldataupdatelistener1;
        JVM INSTR monitorexit ;
        throw exception3;
        exception2;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception2;
        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public void setChannelDataCache(String s, HashMap hashmap)
    {
        this;
        JVM INSTR monitorenter ;
        cancelTask();
        mDataCacheList = hashmap;
        mServerUDN = s;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setOnDataUpdateListener(IOnChannelDataUpdateListener ionchanneldataupdatelistener)
    {
        mListener = ionchanneldataupdatelistener;
    }

    static 
    {
        SEARCH_STRING_MAP = new HashMap();
        SEARCH_STRING_MAP.put(Integer.valueOf(2), "upnp:class derivedfrom \"object.item.audioItem\"");
        SEARCH_STRING_MAP.put(Integer.valueOf(4), "upnp:class derivedfrom \"object.item.imageItem\"");
        SEARCH_STRING_MAP.put(Integer.valueOf(8), "upnp:class derivedfrom \"object.item.videoItem\"");
    }




}
