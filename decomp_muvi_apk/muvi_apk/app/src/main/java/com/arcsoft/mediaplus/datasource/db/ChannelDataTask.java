// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.net.Uri;
import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.SafeBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

class ChannelDataTask extends Thread
    implements Recyclable
{
    static class ChannelDataInfo
    {

        int mChannelID;
        com.arcsoft.adk.atv.UPnP.RemoteItemDesc mData;
        boolean mFinishFlag;
        long mRefreshItemID;
        boolean mRefreshMeta;

        public static ChannelDataInfo getFinishFlagInstance(int i)
        {
            ChannelDataInfo channeldatainfo = new ChannelDataInfo(i, null, false, 0L);
            channeldatainfo.mFinishFlag = true;
            return channeldatainfo;
        }

        ChannelDataInfo(int i, com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc, boolean flag, long l)
        {
            mChannelID = i;
            mData = remoteitemdesc;
            mFinishFlag = false;
            mRefreshItemID = l;
            mRefreshMeta = flag;
        }
    }

    public static interface IOnChannelDataUpdateListener
    {

        public abstract void OnChannelDataUpdated(String s, Set set);

        public abstract void OnChannelItemRefreshed(String s, int i, long l);

        public abstract void OnDataTransmittedBegin(String s, Set set);

        public abstract void OnDataTransmittedFinish(String s, Set set);

        public abstract void OnDigaActionFinish(int i, int j);
    }

    private class ServerContentUpdater
        implements com.arcsoft.adk.atv.ServerManager.IContentUpdatedListener
    {

        private final ArrayList mBrowseInfos;
        private final boolean mBrowseOneByOne;
        private final ArrayList mBrowseWaitList;
        private boolean mCanneledFlag;
        private final HashSet mCurrentChannel;
        private final HashMap mDirInfoCache;
        final ChannelDataTask this$0;

        private void addBrowseRequest(String s, Integer integer, boolean flag, long l, boolean flag1)
        {
            this;
            JVM INSTR monitorenter ;
            if (s != null && integer != null) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            ListIterator listiterator = mBrowseWaitList.listIterator();
            do
            {
                if (!listiterator.hasNext())
                {
                    break;
                }
                BrowseInfo browseinfo1 = (BrowseInfo)listiterator.next();
                if (browseinfo1.serverUDN.equals(getServerUDN()) && browseinfo1.objID.equals(s))
                {
                    listiterator.remove();
                }
            } while (true);
            break MISSING_BLOCK_LABEL_91;
            Exception exception;
            exception;
            throw exception;
            BrowseInfo browseinfo = new BrowseInfo();
            browseinfo.channelID = integer;
            browseinfo.objID = s;
            browseinfo.serverUDN = getServerUDN();
            browseinfo.UpdateID = 0;
            browseinfo.refreshMeta = flag;
            browseinfo.refreshItemID = l;
            mBrowseWaitList.add(browseinfo);
              goto _L1
        }

        private void browseNext()
        {
            this;
            JVM INSTR monitorenter ;
            ServerManager servermanager = DLNA.instance().getServerManager();
            if (!servermanager.isDigaDMS(getServerUDN())) goto _L2; else goto _L1
_L1:
            Iterator iterator = mBrowseInfos.iterator();
_L6:
            if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
            boolean flag = ((BrowseInfo)iterator.next()).serverUDN.equals(getServerUDN());
            if (!flag) goto _L6; else goto _L5
_L5:
            this;
            JVM INSTR monitorexit ;
            return;
_L4:
            int j = 1;
_L9:
            int k = 0;
_L10:
            if (k >= j) goto _L5; else goto _L7
_L7:
            if (mBrowseWaitList.size() <= 0) goto _L5; else goto _L8
_L8:
            BrowseInfo browseinfo = (BrowseInfo)mBrowseWaitList.remove(0);
            browseinfo.UpdateID = servermanager.browse(getServerUDN(), browseinfo.objID, browseinfo.refreshMeta);
            if (browseinfo.UpdateID >= 0)
            {
                mBrowseInfos.add(browseinfo);
            }
            break MISSING_BLOCK_LABEL_177;
_L2:
            int i = mBrowseWaitList.size();
            j = i;
              goto _L9
            Exception exception;
            exception;
            throw exception;
            k++;
              goto _L10
        }

        private void cancelAllGetContent()
        {
            this;
            JVM INSTR monitorenter ;
            mCanneledFlag = true;
            mCurrentChannel.clear();
            ServerManager servermanager = DLNA.instance().getServerManager();
            servermanager.unregisterContentUpdatedListener(mContentListenr);
            mBrowseWaitList.clear();
            for (ListIterator listiterator = mBrowseInfos.listIterator(); listiterator.hasNext(); listiterator.remove())
            {
                servermanager.cancelBrowseSearch(((BrowseInfo)listiterator.next()).UpdateID);
            }

            break MISSING_BLOCK_LABEL_86;
            Exception exception;
            exception;
            throw exception;
            mBuffer.clear();
            this;
            JVM INSTR monitorexit ;
        }

        private void cancelAllInvalidBrowseInfo()
        {
            this;
            JVM INSTR monitorenter ;
            ServerManager servermanager;
            servermanager = DLNA.instance().getServerManager();
            ListIterator listiterator = mBrowseInfos.listIterator();
            do
            {
                if (!listiterator.hasNext())
                {
                    break;
                }
                BrowseInfo browseinfo1 = (BrowseInfo)listiterator.next();
                if (browseinfo1.channelID.intValue() == 0)
                {
                    servermanager.cancelBrowseSearch(browseinfo1.UpdateID);
                    listiterator.remove();
                }
            } while (true);
            break MISSING_BLOCK_LABEL_71;
            Exception exception;
            exception;
            throw exception;
            ListIterator listiterator1 = mBrowseWaitList.listIterator();
            do
            {
                if (!listiterator1.hasNext())
                {
                    break;
                }
                BrowseInfo browseinfo = (BrowseInfo)listiterator1.next();
                if (browseinfo.channelID.intValue() == 0)
                {
                    servermanager.cancelBrowseSearch(browseinfo.UpdateID);
                    listiterator1.remove();
                }
            } while (true);
            this;
            JVM INSTR monitorexit ;
        }

        private void getChannelContents(String s)
        {
            this;
            JVM INSTR monitorenter ;
            mCanneledFlag = false;
            if (s == null) goto _L2; else goto _L1
_L1:
            HashMap hashmap = mDataCacheList;
            if (hashmap != null) goto _L3; else goto _L2
_L2:
            this;
            JVM INSTR monitorexit ;
            return;
_L3:
            ServerManager servermanager;
            servermanager = DLNA.instance().getServerManager();
            servermanager.registerContentUpdatedListener(mContentListenr);
            if (!servermanager.isServerOnline(s)) goto _L2; else goto _L4
_L4:
            boolean flag = false;
            int ai[];
            int i;
            mCurrentChannel.clear();
            ai = ChannelDataMgr.CHANNEL_LIST;
            i = ai.length;
            int j = 0;
_L5:
            if (j >= i)
            {
                break MISSING_BLOCK_LABEL_147;
            }
            int k;
            com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc;
            k = ai[j];
            mCurrentChannel.add(Integer.valueOf(k));
            remoteitemdesc = (com.arcsoft.adk.atv.UPnP.RemoteItemDesc)mDirInfoCache.get(Integer.valueOf(k));
            if (remoteitemdesc == null)
            {
                break MISSING_BLOCK_LABEL_183;
            }
            addBrowseRequest(remoteitemdesc.m_strObjId, Integer.valueOf(k), false, 0L, false);
            break MISSING_BLOCK_LABEL_177;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_165;
            }
            addBrowseRequest("0", Integer.valueOf(0), false, 0L, true);
            browseNext();
              goto _L2
            Exception exception;
            exception;
            throw exception;
_L6:
            j++;
              goto _L5
            flag = true;
              goto _L6
        }

        private Integer getDirChannelID(com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc)
        {
            this;
            JVM INSTR monitorenter ;
            long l = remoteitemdesc.m_lProperty;
            if (l == 2L) goto _L2; else goto _L1
_L1:
            Integer integer = null;
_L4:
            this;
            JVM INSTR monitorexit ;
            return integer;
_L2:
            String s = remoteitemdesc.m_strAribObjectType;
            integer = null;
            if (s == null)
            {
                break MISSING_BLOCK_LABEL_52;
            }
            integer = (Integer)OEMConfig.CHANNEL_DIR_ARIB.get(remoteitemdesc.m_strAribObjectType);
            if (integer != null)
            {
                continue; /* Loop/switch isn't completed */
            }
            Integer integer1 = Integer.valueOf(0);
            integer = integer1;
            if (true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            throw exception;
        }

        private ChannelDataMgr.ChannelData getPresentItemInfo(int i, long l)
        {
            this;
            JVM INSTR monitorenter ;
            HashMap hashmap = mDataCacheList;
            hashmap;
            JVM INSTR monitorenter ;
            Iterator iterator = ((ArrayList)mDataCacheList.get(Integer.valueOf(i))).iterator();
_L4:
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            ChannelDataMgr.ChannelData channeldata = (ChannelDataMgr.ChannelData)iterator.next();
            if (channeldata == null) goto _L4; else goto _L3
_L3:
            if (channeldata.itemDesc == null || channeldata._id != l) goto _L4; else goto _L5
_L5:
            hashmap;
            JVM INSTR monitorexit ;
_L7:
            this;
            JVM INSTR monitorexit ;
            return channeldata;
_L2:
            hashmap;
            JVM INSTR monitorexit ;
            channeldata = null;
            if (true) goto _L7; else goto _L6
_L6:
            Exception exception1;
            exception1;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private ChannelDataMgr.ChannelData getPresentItemInfo(String s)
        {
            this;
            JVM INSTR monitorenter ;
            HashMap hashmap = mDataCacheList;
            hashmap;
            JVM INSTR monitorenter ;
            Iterator iterator = mDataCacheList.entrySet().iterator();
_L4:
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            ArrayList arraylist = (ArrayList)((java.util.Map.Entry)iterator.next()).getValue();
            if (arraylist == null) goto _L4; else goto _L3
_L3:
            Iterator iterator1 = arraylist.iterator();
_L7:
            if (!iterator1.hasNext()) goto _L4; else goto _L5
_L5:
            ChannelDataMgr.ChannelData channeldata = (ChannelDataMgr.ChannelData)iterator1.next();
            if (channeldata == null) goto _L7; else goto _L6
_L6:
            if (channeldata.itemDesc == null || !channeldata.itemDesc.m_strObjId.equals(s)) goto _L7; else goto _L8
_L8:
            hashmap;
            JVM INSTR monitorexit ;
_L10:
            this;
            JVM INSTR monitorexit ;
            return channeldata;
_L2:
            hashmap;
            JVM INSTR monitorexit ;
            channeldata = null;
            if (true) goto _L10; else goto _L9
_L9:
            Exception exception1;
            exception1;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void reset()
        {
            this;
            JVM INSTR monitorenter ;
            mDirInfoCache.clear();
            cancelAllGetContent();
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
            HashSet hashset = new HashSet(mCurrentChannel);
            this;
            JVM INSTR monitorexit ;
            return hashset;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean isReceivingData()
        {
            boolean flag = true;
            this;
            JVM INSTR monitorenter ;
            Iterator iterator = mBrowseInfos.iterator();
_L4:
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            BrowseInfo browseinfo1 = (BrowseInfo)iterator.next();
            if (!browseinfo1.serverUDN.equals(getServerUDN())) goto _L4; else goto _L3
_L3:
            boolean flag2 = browseinfo1.refreshMeta;
            if (flag2) goto _L4; else goto _L5
_L5:
            this;
            JVM INSTR monitorexit ;
            return flag;
_L2:
            Iterator iterator1 = mBrowseWaitList.iterator();
_L6:
            boolean flag1;
            BrowseInfo browseinfo;
            do
            {
                if (!iterator1.hasNext())
                {
                    break MISSING_BLOCK_LABEL_130;
                }
                browseinfo = (BrowseInfo)iterator1.next();
            } while (!browseinfo.serverUDN.equals(getServerUDN()));
            flag1 = browseinfo.refreshMeta;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
              goto _L6
            flag = false;
            if (true) goto _L5; else goto _L7
_L7:
            Exception exception;
            exception;
            throw exception;
        }

        public void markAllChannelFinish()
        {
            this;
            JVM INSTR monitorenter ;
            mCurrentChannel.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void markChannelFinish(int i)
        {
            this;
            JVM INSTR monitorenter ;
            mCurrentChannel.remove(Integer.valueOf(i));
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onDirContentUpdated(com.arcsoft.adk.atv.MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1)
        {
            this;
            JVM INSTR monitorenter ;
            String s2 = getServerUDN();
            if (s2 == null)
            {
                break MISSING_BLOCK_LABEL_29;
            }
            boolean flag = s.equals(s2);
              goto _L1
_L3:
            this;
            JVM INSTR monitorexit ;
            return;
_L1:
            if (!flag || dataondircontentupdated == null) goto _L3; else goto _L2
_L2:
            if (dataondircontentupdated.m_nRes != 0)
            {
                dataondircontentupdated.m_nTotalSize = 0;
                dataondircontentupdated.m_nCount = 0;
                dataondircontentupdated.m_nIndex = 0;
            }
            Exception exception;
            boolean flag1;
            ListIterator listiterator;
            boolean flag2;
            BrowseInfo browseinfo;
            BrowseInfo browseinfo1;
            boolean flag3;
            Iterator iterator;
            com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc;
            Integer integer;
            if (dataondircontentupdated.m_nIndex + dataondircontentupdated.m_nCount == dataondircontentupdated.m_nTotalSize)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            listiterator = mBrowseInfos.listIterator();
_L5:
            flag2 = listiterator.hasNext();
            browseinfo = null;
            if (!flag2)
            {
                break MISSING_BLOCK_LABEL_143;
            }
            browseinfo1 = (BrowseInfo)listiterator.next();
            if (browseinfo1.UpdateID != dataondircontentupdated.m_nUpdateId) goto _L5; else goto _L4
_L4:
            browseinfo = browseinfo1;
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_143;
            }
            listiterator.remove();
            if (browseinfo == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            flag3 = browseinfo.refreshMeta;
            iterator = dataondircontentupdated.m_Contents.iterator();
_L9:
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_395;
            }
            remoteitemdesc = (com.arcsoft.adk.atv.UPnP.RemoteItemDesc)iterator.next();
            if (remoteitemdesc.m_lProperty != 2L) goto _L7; else goto _L6
_L6:
            integer = getDirChannelID(remoteitemdesc);
            if (integer == null) goto _L9; else goto _L8
_L8:
            if (integer.intValue() == 0)
            {
                break MISSING_BLOCK_LABEL_281;
            }
            if (!mDirInfoCache.containsKey(integer))
            {
                remoteitemdesc.m_lChildCount = 1L;
                mDirInfoCache.put(integer, remoteitemdesc);
                addBrowseRequest(remoteitemdesc.m_strObjId, integer, false, 0L, false);
            }
              goto _L9
            exception;
            throw exception;
            boolean flag5;
            if (mDirInfoCache.size() < ChannelDataMgr.CHANNEL_LIST.length)
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            if (!flag5) goto _L9; else goto _L10
_L10:
            addBrowseRequest(remoteitemdesc.m_strObjId, integer, false, 0L, false);
              goto _L9
_L7:
            if (remoteitemdesc.m_lProperty != 1L || remoteitemdesc.m_PresentItem.m_iItemClass != 4 && remoteitemdesc.m_PresentItem.m_iItemClass != 1) goto _L9; else goto _L11
_L11:
            mBuffer.append(new ChannelDataInfo(browseinfo.channelID.intValue(), remoteitemdesc, browseinfo.refreshMeta, browseinfo.refreshItemID));
              goto _L9
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_441;
            }
            if (browseinfo.channelID.intValue() != 0 && !browseinfo.refreshMeta)
            {
                mBuffer.append(ChannelDataInfo.getFinishFlagInstance(browseinfo.channelID.intValue()));
            }
            boolean flag4;
            if (mDirInfoCache.size() < ChannelDataMgr.CHANNEL_LIST.length)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if (flag4)
            {
                break MISSING_BLOCK_LABEL_467;
            }
            cancelAllInvalidBrowseInfo();
            browseNext();
            if (isReceivingData() || flag3)
            {
                break MISSING_BLOCK_LABEL_505;
            }
            mBuffer.append(ChannelDataInfo.getFinishFlagInstance(0));
            if (mCanneledFlag);
            synchronized (mBuffer)
            {
                mBuffer.notify();
            }
            if (true) goto _L3; else goto _L12
_L12:
            exception1;
            safebuffer;
            JVM INSTR monitorexit ;
            throw exception1;
        }

        public void refreshChannelDetail(int i, long l, String s)
        {
            this;
            JVM INSTR monitorenter ;
            Iterator iterator = mBrowseWaitList.iterator();
_L4:
            if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            BrowseInfo browseinfo1 = (BrowseInfo)iterator.next();
            if (!browseinfo1.refreshMeta) goto _L4; else goto _L3
_L3:
            long l1 = browseinfo1.refreshItemID;
            if (l1 != l) goto _L4; else goto _L5
_L5:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
label0:
            {
                Iterator iterator1 = mBrowseInfos.iterator();
                BrowseInfo browseinfo;
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break label0;
                    }
                    browseinfo = (BrowseInfo)iterator1.next();
                } while (!browseinfo.refreshMeta || browseinfo.refreshItemID != l);
                continue; /* Loop/switch isn't completed */
            }
            addBrowseRequest(s, Integer.valueOf(i), true, l, true);
            browseNext();
            if (true) goto _L5; else goto _L6
_L6:
            Exception exception;
            exception;
            throw exception;
        }






        private ServerContentUpdater()
        {
            this$0 = ChannelDataTask.this;
            super();
            mCurrentChannel = new HashSet();
            mBrowseInfos = new ArrayList();
            mBrowseWaitList = new ArrayList();
            mDirInfoCache = new HashMap();
            mCanneledFlag = false;
            mBrowseOneByOne = false;
        }

    }

    class ServerContentUpdater.BrowseInfo
    {

        int UpdateID;
        Integer channelID;
        String objID;
        long refreshItemID;
        boolean refreshMeta;
        String serverUDN;
        final ServerContentUpdater this$1;

        ServerContentUpdater.BrowseInfo()
        {
            this$1 = ServerContentUpdater.this;
            super();
        }
    }

    private class ServerListener
        implements com.arcsoft.adk.atv.ServerManager.IServerStatusListener
    {

        final ChannelDataTask this$0;

        public void OnDestroyObject(String s, int i)
        {
        }

        public void OnDigaBrowseRecordTasks(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordTasks dataonrecordtasks, int i)
        {
            int j;
            Log.v("ChannelTask", (new StringBuilder()).append("OnDigaBrowseRecordTasks errorcode = ").append(i).append(" serverUdn=").append(mServerUDN).append(" mDigaAction=").append(mDigaAction).toString());
            j = mDigaAction;
            if (mServerUDN != null) goto _L2; else goto _L1
_L1:
            mDigaAction = 0;
_L8:
            if ((4 & mDigaAction) == 0) goto _L4; else goto _L3
_L3:
            ChannelDataMgr.ChannelData channeldata = mContentListenr.getPresentItemInfo(mCreateRecordScheduleObjID);
            Log.v("ChannelTask", (new StringBuilder()).append("play: step 3 / 3 channelData = ").append(channeldata).toString());
            ChannelDataTask channeldatatask;
            if (channeldata != null)
            {
                requestChannelItemData(channeldata.channelid, channeldata._id, ((com.arcsoft.adk.atv.MSCPCallback.DataOnRecordTaskItem)dataonrecordtasks.m_TaskItem.get(0)).m_strRecordedCDSObjectID);
            } else
            {
                mDigaAction = 0;
            }
_L6:
            if (mDigaAction == 0)
            {
                synchronized (mListener)
                {
                    mListener.OnDigaActionFinish(j, 7);
                }
            }
            return;
_L2:
            if (i != 0 || dataonrecordtasks.m_TaskItem == null)
            {
                channeldatatask = ChannelDataTask.this;
                channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if ((1 & mDigaAction) != 0)
            {
                uninitChannelItem();
            }
            if (true) goto _L6; else goto _L5
_L5:
            exception;
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public void OnDigaCreateRecordSchedule(String s, com.arcsoft.adk.atv.MSCPCallback.DataOnRecordSchedule dataonrecordschedule, int i)
        {
            int j;
            Log.v("ChannelTask", (new StringBuilder()).append("OnDigaCreateRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(mServerUDN).append(" mNextCreateRecordScheduleObjID=").append(mNextCreateRecordScheduleObjID).append(" mDigaAction=").append(mDigaAction).toString());
            j = mDigaAction;
            if (mServerUDN != null) goto _L2; else goto _L1
_L1:
            mDigaAction = 0;
_L8:
            if (i == 0)
            {
                Settings.instance().setRecordScheduleID(dataonrecordschedule.m_strRecordScheduleID);
            }
            if ((4 & mDigaAction) == 0) goto _L4; else goto _L3
_L3:
            DLNA.instance().getServerManager().digaBrowseRecordTasks(mServerUDN, dataonrecordschedule.m_strRecordScheduleID, "*:*", 0, 1, null);
_L6:
            if (mDigaAction == 0)
            {
                synchronized (mListener)
                {
                    mListener.OnDigaActionFinish(j, 6);
                }
            }
            return;
_L2:
            if (i != 0)
            {
                ChannelDataTask channeldatatask = ChannelDataTask.this;
                channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if ((8 & mDigaAction) != 0)
            {
                mCreateRecordScheduleObjID = mNextCreateRecordScheduleObjID;
                DLNA.instance().getServerManager().digaEnableRecordSchedule(mServerUDN, dataonrecordschedule.m_strRecordScheduleID);
            } else
            if ((1 & mDigaAction) != 0)
            {
                uninitChannelItem();
            }
            if (true) goto _L6; else goto _L5
_L5:
            exception;
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
            if (true) goto _L8; else goto _L7
_L7:
        }

        public void OnDigaDeleteRecordSchedule(String s, int i)
        {
            int j;
            Log.v("ChannelTask", (new StringBuilder()).append("OnDigaDeleteRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(mServerUDN).append(" mDigaAction=").append(mDigaAction).toString());
            j = mDigaAction;
            ChannelDataTask channeldatatask = ChannelDataTask.this;
            channeldatatask.mDigaAction = -4 & channeldatatask.mDigaAction;
            if (mServerUDN == null)
            {
                mDigaAction = 0;
            }
            if (i == 0 || i == 703) goto _L2; else goto _L1
_L1:
            boolean flag = false;
            if (i != 704) goto _L3; else goto _L2
_L2:
            Settings.instance().setRecordScheduleID("");
            flag = true;
            if (i != 0 || (j & 2) == 0) goto _L5; else goto _L4
_L4:
            ChannelDataTask channeldatatask1 = ChannelDataTask.this;
            channeldatatask1.mDigaAction = 2 | channeldatatask1.mDigaAction;
            requestChannelData();
_L3:
            if (mDigaAction != 0) goto _L7; else goto _L6
_L6:
            IOnChannelDataUpdateListener ionchanneldataupdatelistener = mListener;
            ionchanneldataupdatelistener;
            JVM INSTR monitorenter ;
            IOnChannelDataUpdateListener ionchanneldataupdatelistener1 = mListener;
            int k = 0;
            if (!flag)
            {
                k = 4;
            }
            ionchanneldataupdatelistener1.OnDigaActionFinish(j, k);
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
_L7:
            return;
_L5:
            if ((4 & mDigaAction) != 0)
            {
                DLNA.instance().getServerManager().digaCreateRecordSchedule(mServerUDN, "", mCreateRecordScheduleObjID);
            }
            if (true) goto _L3; else goto _L8
_L8:
            Exception exception;
            exception;
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnDigaDisableRecordSchedule(String s, int i)
        {
            Log.v("ChannelTask", (new StringBuilder()).append("OnDigaDisableRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(mServerUDN).append(" mDigaAction=").append(mDigaAction).toString());
            int j = mDigaAction;
            if (mServerUDN == null)
            {
                mDigaAction = 0;
            }
            if (i == 0 && (8 & mDigaAction) != 0)
            {
                ChannelDataMgr.ChannelData channeldata1 = mContentListenr.getPresentItemInfo(mCreateRecordScheduleObjID);
                Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 data = ").append(channeldata1).toString());
                ChannelDataMgr.ChannelData channeldata;
                if (channeldata1 != null)
                {
                    requestChannelItemData(channeldata1.channelid, channeldata1._id, channeldata1.m_strVideoItemObjId);
                } else
                {
                    mDigaAction = 0;
                }
            }
            if ((1 & mDigaAction) != 0)
            {
                uninitChannelItem();
            }
            if (i != 0 && (8 & mDigaAction) != 0)
            {
                channeldata = mContentListenr.getPresentItemInfo(mNextCreateRecordScheduleObjID);
                Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 retry delete/create data = ").append(channeldata).toString());
                if (channeldata != null)
                {
                    mDigaAction = 4;
                    initChannelItem(channeldata.channelid, channeldata._id);
                }
            }
            if (mDigaAction == 0)
            {
                synchronized (mListener)
                {
                    mListener.OnDigaActionFinish(j, 8);
                }
            }
            return;
            exception;
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnDigaEnableRecordSchedule(String s, int i)
        {
            IOnChannelDataUpdateListener ionchanneldataupdatelistener;
            int k;
            Log.v("ChannelTask", (new StringBuilder()).append("OnDigaEnableRecordSchedule errorcode = ").append(i).append(" serverUdn=").append(mServerUDN).append(" mDigaAction=").append(mDigaAction).toString());
            int j = mDigaAction;
            IOnChannelDataUpdateListener ionchanneldataupdatelistener1;
            if (mServerUDN == null)
            {
                mDigaAction = 0;
            } else
            {
                ChannelDataTask channeldatatask = ChannelDataTask.this;
                channeldatatask.mDigaAction = 1 & channeldatatask.mDigaAction;
            }
            if (i != 0 && mServerUDN != null && (j & 8) != 0)
            {
                ChannelDataMgr.ChannelData channeldata = mContentListenr.getPresentItemInfo(mNextCreateRecordScheduleObjID);
                Log.v("ChannelTask", (new StringBuilder()).append("switch: step 2 / 4 retry delete/create data = ").append(channeldata).toString());
                if (channeldata != null)
                {
                    mDigaAction = 4;
                    initChannelItem(channeldata.channelid, channeldata._id);
                }
            }
            if ((1 & mDigaAction) != 0)
            {
                uninitChannelItem();
            }
            if (mDigaAction != 0) goto _L2; else goto _L1
_L1:
            ionchanneldataupdatelistener = mListener;
            ionchanneldataupdatelistener;
            JVM INSTR monitorenter ;
            ionchanneldataupdatelistener1 = mListener;
            if (mServerUDN == null) goto _L4; else goto _L3
_L3:
            k = 0;
            if (i == 0) goto _L5; else goto _L4
_L5:
            ionchanneldataupdatelistener1.OnDigaActionFinish(j, k);
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
_L2:
            return;
            Exception exception;
            exception;
            ionchanneldataupdatelistener;
            JVM INSTR monitorexit ;
            throw exception;
_L4:
            k = 9;
            if (true) goto _L5; else goto _L6
_L6:
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
            this$0 = ChannelDataTask.this;
            super();
        }

    }


    private static final int PARSE_DATA_EVERY_TIME = 20;
    private static final int STATUS_DOING = 1;
    private static final int STATUS_NONE = 0;
    private static final int STATUS_QUIT = 2;
    private static final String TAG = "ChannelTask";
    private final SafeBuffer mBuffer = new SafeBuffer();
    private final ServerContentUpdater mContentListenr = new ServerContentUpdater();
    String mCreateRecordScheduleObjID;
    private HashMap mDataCacheList;
    int mDigaAction;
    private IOnChannelDataUpdateListener mListener;
    private final Object mMutex = new Object();
    String mNextCreateRecordScheduleObjID;
    private final ServerListener mServerListener = new ServerListener();
    private String mServerUDN;
    private int mStatus;

    ChannelDataTask()
    {
        super("ChannelDataTask");
        mCreateRecordScheduleObjID = null;
        mNextCreateRecordScheduleObjID = null;
        mDigaAction = 0;
        mDataCacheList = null;
        mServerUDN = null;
        mStatus = 0;
        mListener = null;
        DLNA.instance().getServerManager().registerServerStatusListener(mServerListener);
    }

    private boolean checkAndClearData()
    {
        if (mDataCacheList == null || mServerUDN == null)
        {
            return false;
        }
        HashMap hashmap = mDataCacheList;
        hashmap;
        JVM INSTR monitorenter ;
        Iterator iterator = mDataCacheList.entrySet().iterator();
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = (ArrayList)((java.util.Map.Entry)iterator.next()).getValue();
        if (arraylist == null) goto _L4; else goto _L3
_L3:
        arraylist.clear();
          goto _L4
_L6:
        hashmap;
        JVM INSTR monitorexit ;
        Exception exception;
        throw exception;
_L2:
        HashSet hashset = new HashSet(mDataCacheList.keySet());
        hashmap;
        JVM INSTR monitorexit ;
        if (mListener != null)
        {
            synchronized (mListener)
            {
                mListener.OnChannelDataUpdated(getServerUDN(), hashset);
            }
        }
        break; /* Loop/switch isn't completed */
        exception1;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        continue; /* Loop/switch isn't completed */
        exception;
        if (true) goto _L6; else goto _L5
_L5:
        return true;
    }

    private boolean checkDigaAction(int i)
    {
        if ((i & 1) == 0) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (mDigaAction > 0)
        {
            synchronized (mListener)
            {
                mListener.OnDigaActionFinish(i, 3);
            }
            return false;
        }
        continue; /* Loop/switch isn't completed */
        exception1;
        ionchanneldataupdatelistener1;
        JVM INSTR monitorexit ;
        throw exception1;
        if (!isReceivingData()) goto _L1; else goto _L3
_L3:
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(i, 2);
        }
        return false;
        exception;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private String getServerUDN()
    {
        return mServerUDN;
    }

    private boolean initChannelItem(int i, long l)
    {
        ChannelDataMgr.ChannelData channeldata = mContentListenr.getPresentItemInfo(i, l);
        ServerManager servermanager = DLNA.instance().getServerManager();
        Log.v("ChannelTask", (new StringBuilder()).append("initChannelItem mDigaAction = ").append(mDigaAction).append(" data=").append(channeldata).append(" mServerUDN=").append(mServerUDN).toString());
        if (channeldata != null)
        {
            mCreateRecordScheduleObjID = channeldata.itemDesc.m_strObjId;
            mNextCreateRecordScheduleObjID = null;
            mDigaAction = 3 | mDigaAction;
            if (!uninitChannelItem())
            {
                servermanager.digaCreateRecordSchedule(mServerUDN, "", mCreateRecordScheduleObjID);
            }
            return true;
        }
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(mDigaAction, 1);
        }
        mDigaAction = 0;
        return false;
        exception;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private long insertMediaItem(ChannelDataInfo channeldatainfo)
    {
        if (channeldatainfo.mData == null || channeldatainfo.mData.m_PresentItem == null || channeldatainfo.mData.m_PresentItem.m_bIppltvEnable != 1)
        {
            return 0L;
        }
        HashMap hashmap = mDataCacheList;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mDataCacheList.get(Integer.valueOf(channeldatainfo.mChannelID));
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return 0L;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        ChannelDataMgr.ChannelData channeldata;
        Iterator iterator;
        channeldata = new ChannelDataMgr.ChannelData();
        channeldata._id = channeldatainfo.mData.m_strObjId.hashCode();
        channeldata.itemDesc = channeldatainfo.mData;
        channeldata.channelid = channeldatainfo.mChannelID;
        iterator = channeldata.itemDesc.m_PresentItem.m_ResourceList.iterator();
_L3:
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_246;
        }
        presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
        if (presentitem_resource.m_strResolution == null || presentitem_resource.m_strResolution.equals("")) goto _L2; else goto _L1
_L1:
        if (channeldata.sdRes == null)
        {
            channeldata.sdRes = Uri.parse(presentitem_resource.m_strUri);
        }
          goto _L3
_L2:
        if (presentitem_resource.m_strResolution != null && !presentitem_resource.m_strResolution.equals("") || channeldata.hdRes != null) goto _L3; else goto _L4
_L4:
        channeldata.hdRes = Uri.parse(presentitem_resource.m_strUri);
          goto _L3
        long l;
        if (channeldata.sdRes == null && !OEMConfig.DEVICE_SUPPORT_MPEG2)
        {
            break MISSING_BLOCK_LABEL_280;
        }
        arraylist.add(channeldata);
        l = channeldata._id;
        return l;
        hashmap;
        JVM INSTR monitorexit ;
        return 0L;
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

    private boolean switchChannelItem(int i, long l, long l1)
    {
        ChannelDataMgr.ChannelData channeldata = mContentListenr.getPresentItemInfo(i, l);
        ChannelDataMgr.ChannelData channeldata1 = mContentListenr.getPresentItemInfo(i, l1);
        ServerManager servermanager = DLNA.instance().getServerManager();
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc = servermanager.getServerDesc(mServerUDN);
        Log.v("ChannelTask", (new StringBuilder()).append("switchChannelItem mDigaAction = ").append(mDigaAction).append(" olddata=").append(channeldata).append(" newdata=").append(channeldata1).append(" mServerUDN=").append(mServerUDN).toString());
        if (channeldata != null && channeldata1 != null && mediaserverdesc != null)
        {
            if (mediaserverdesc.m_strXIppltvCap.contains("REC_PAUSE"))
            {
                mCreateRecordScheduleObjID = channeldata.itemDesc.m_strObjId;
                mNextCreateRecordScheduleObjID = channeldata1.itemDesc.m_strObjId;
                String s = Settings.instance().getRecordScheduleID();
                if (s != null && s.length() != 0)
                {
                    servermanager.digaDisableRecordSchedule(mServerUDN, s);
                    return true;
                }
            } else
            {
                mDigaAction = 4;
                initChannelItem(i, l1);
                return true;
            }
        }
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(mDigaAction, 1);
        }
        mDigaAction = 0;
        return false;
        exception;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean uninitChannelItem()
    {
        String s = Settings.instance().getRecordScheduleID();
        if (s == null || s.length() == 0) goto _L2; else goto _L1
_L1:
        boolean flag;
        int k = DLNA.instance().getServerManager().digaDeleteRecordSchedule(mServerUDN, s);
        flag = false;
        if (k == 0)
        {
            flag = true;
        }
_L4:
        return flag;
_L2:
        int i;
        int j;
        i = mDigaAction;
        mDigaAction = -4 & mDigaAction;
        j = mDigaAction;
        flag = false;
        if (j != 0) goto _L4; else goto _L3
_L3:
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(i, 0);
        }
        return false;
        exception;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private long updateMediaItem(ChannelDataInfo channeldatainfo)
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
        int i;
        int j;
        i = -1;
        j = 0;
_L20:
        if (j >= arraylist.size())
        {
            break MISSING_BLOCK_LABEL_90;
        }
        if (((ChannelDataMgr.ChannelData)arraylist.get(j))._id != channeldatainfo.mRefreshItemID)
        {
            break MISSING_BLOCK_LABEL_884;
        }
        i = j;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_151;
        }
        if (channeldatainfo.mData.m_PresentItem.m_ResourceList != null && !channeldatainfo.mData.m_PresentItem.m_ResourceList.isEmpty() && ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_strUri != null)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        if (mDigaAction <= 0)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        if ((1 & mDigaAction) == 0) goto _L2; else goto _L1
_L1:
        uninitChannelItem();
_L3:
        hashmap;
        JVM INSTR monitorexit ;
        return 0L;
_L2:
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(mDigaAction, 5);
        }
        mDigaAction = 0;
          goto _L3
        exception2;
        ionchanneldataupdatelistener1;
        JVM INSTR monitorexit ;
        throw exception2;
        if ((8 & mDigaAction) == 0) goto _L5; else goto _L4
_L4:
        long l1;
        long l2;
        l1 = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_lVliPlayitemNum;
        l2 = ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_lDuration;
        Log.v("ChannelTask", (new StringBuilder()).append("updateMediaItem m_lVliPlayitemNum = ").append(l1).append("m_lDuration = ").append(l2).toString());
        if (l1 >= 252L || l2 >= 252L) goto _L7; else goto _L6
_L6:
        ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_strUri = (new StringBuilder()).append(((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_strUri).append("&PI=").append(l1).toString();
_L11:
        ChannelDataMgr.ChannelData channeldata;
        Iterator iterator;
        channeldata = (ChannelDataMgr.ChannelData)arraylist.get(i);
        channeldata.itemDesc.m_PresentItem.m_ResourceList = channeldatainfo.mData.m_PresentItem.m_ResourceList;
        channeldata.itemDesc.m_strTitle = channeldatainfo.mData.m_strTitle;
        channeldata.m_strVideoItemObjId = channeldatainfo.mData.m_strObjId;
        iterator = channeldata.itemDesc.m_PresentItem.m_ResourceList.iterator();
_L10:
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_682;
        }
        presentitem_resource = (com.arcsoft.adk.atv.UPnP.PresentItem_Resource)iterator.next();
        if (presentitem_resource.m_strResolution == null || presentitem_resource.m_strResolution.equals("")) goto _L9; else goto _L8
_L8:
        if (channeldata.sdRes == null)
        {
            channeldata.sdRes = Uri.parse(presentitem_resource.m_strUri);
        }
          goto _L10
_L7:
        mDigaAction = 4;
        initChannelItem(channeldatainfo.mChannelID, channeldatainfo.mRefreshItemID);
        hashmap;
        JVM INSTR monitorexit ;
        return 0L;
_L5:
        ((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_strUri = (new StringBuilder()).append(((com.arcsoft.adk.atv.UPnP.PresentItem_Resource)channeldatainfo.mData.m_PresentItem.m_ResourceList.get(0)).m_strUri).append("&PI=").append(0).toString();
          goto _L11
_L9:
        if (presentitem_resource.m_strResolution != null && !presentitem_resource.m_strResolution.equals("") || channeldata.hdRes != null) goto _L10; else goto _L12
_L12:
        channeldata.hdRes = Uri.parse(presentitem_resource.m_strUri);
          goto _L10
        if ((1 & mDigaAction) == 0) goto _L14; else goto _L13
_L13:
        uninitChannelItem();
_L17:
        long l = channeldata._id;
        hashmap;
        JVM INSTR monitorexit ;
        return l;
_L14:
        Log.v("ChannelTask", (new StringBuilder()).append("updateMediaItem mCreateRecordScheduleObjID = ").append(mCreateRecordScheduleObjID).append(" mNextCreateRecordScheduleObjID = ").append(mNextCreateRecordScheduleObjID).append(" mDigaAction = ").append(mDigaAction).toString());
        IOnChannelDataUpdateListener ionchanneldataupdatelistener = mListener;
        ionchanneldataupdatelistener;
        JVM INSTR monitorenter ;
        if ((4 & mDigaAction) == 0 || !channeldata.itemDesc.m_strObjId.equals(mCreateRecordScheduleObjID)) goto _L16; else goto _L15
_L15:
        mListener.OnDigaActionFinish(mDigaAction, 0);
        mDigaAction = 0;
          goto _L17
        Exception exception1;
        exception1;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception1;
_L16:
        if ((8 & mDigaAction) != 0 && channeldata.itemDesc.m_strObjId.equals(mNextCreateRecordScheduleObjID)) goto _L17; else goto _L18
_L18:
        mListener.OnChannelItemRefreshed(getServerUDN(), channeldatainfo.mChannelID, channeldatainfo.mRefreshItemID);
          goto _L17
        j++;
        if (true) goto _L20; else goto _L19
_L19:
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

    public void digaDoAction(int i, int j, long l, long l1)
    {
        if (checkDigaAction(i)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!DLNA.instance().getServerManager().isServerOnline(mServerUDN))
        {
            if (mDigaAction > 0 && (i & 1) != 0)
            {
                mDigaAction = i;
            }
            synchronized (mListener)
            {
                mListener.OnDigaActionFinish(i, 10);
            }
            return;
        }
        break MISSING_BLOCK_LABEL_76;
        exception;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception;
        int k = mDigaAction;
        mDigaAction = i;
        if ((i & 1) == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (k == 0)
        {
            uninitChannelItem();
            return;
        }
        if ((k & 2) != 0 && (k & 1) == 0)
        {
            mDigaAction = 2;
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if ((i & 4) != 0)
        {
            initChannelItem(j, l);
            return;
        }
        if ((i & 8) != 0)
        {
            switchChannelItem(j, l1, l);
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
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

            final ChannelDataTask this$0;

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
                this$0 = ChannelDataTask.this;
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

    public void refreshChannelDetail(int i, long l)
    {
        this;
        JVM INSTR monitorenter ;
        ChannelDataMgr.ChannelData channeldata = mContentListenr.getPresentItemInfo(i, l);
        if (channeldata == null) goto _L2; else goto _L1
_L1:
        String s = channeldata.m_strVideoItemObjId;
        if (s != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        mContentListenr.refreshChannelDetail(i, l, channeldata.m_strVideoItemObjId);
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public void requestChannelData()
    {
        this;
        JVM INSTR monitorenter ;
        cancelTask();
        checkAndClearData();
        mContentListenr.getChannelContents(getServerUDN());
        resumeTask();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void requestChannelItemData(int i, long l, String s)
    {
        this;
        JVM INSTR monitorenter ;
        mContentListenr.refreshChannelDetail(i, l, s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
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
_L27:
        if (mStatus == 2)
        {
            break MISSING_BLOCK_LABEL_816;
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
        Exception exception6;
        exception6;
        exception6.printStackTrace();
          goto _L2
        Exception exception1;
        exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        throw exception1;
        safebuffer;
        JVM INSTR monitorexit ;
        Iterator iterator = arraylist.iterator();
_L12:
        if (!iterator.hasNext()) goto _L4; else goto _L3
_L3:
        ChannelDataInfo channeldatainfo = (ChannelDataInfo)iterator.next();
        long l = 0L;
        if (mStatus != 2) goto _L5; else goto _L4
_L4:
        if (hashset.size() > 0)
        {
            synchronized (mListener)
            {
                IOnChannelDataUpdateListener ionchanneldataupdatelistener5 = mListener;
                String s1 = getServerUDN();
                HashSet hashset3 = new HashSet(hashset);
                ionchanneldataupdatelistener5.OnChannelDataUpdated(s1, hashset3);
            }
        }
        if (hashset1.size() <= 0 || getCurrentUpdateChannels().size() != 0) goto _L7; else goto _L6
_L6:
        synchronized (mListener)
        {
            IOnChannelDataUpdateListener ionchanneldataupdatelistener1 = mListener;
            String s = getServerUDN();
            HashSet hashset2 = new HashSet(hashset1);
            ionchanneldataupdatelistener1.OnDataTransmittedFinish(s, hashset2);
        }
        if ((1 & mDigaAction) == 0) goto _L9; else goto _L8
_L8:
        uninitChannelItem();
_L7:
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
_L5:
        if (!channeldatainfo.mFinishFlag) goto _L11; else goto _L10
_L10:
        if (channeldatainfo.mChannelID != 0)
        {
            break MISSING_BLOCK_LABEL_378;
        }
        hashset1.addAll(getCurrentUpdateChannels());
        mContentListenr.markAllChannelFinish();
          goto _L12
        hashset1.add(Integer.valueOf(channeldatainfo.mChannelID));
        mContentListenr.markChannelFinish(channeldatainfo.mChannelID);
          goto _L12
_L11:
        if (channeldatainfo.mData.m_lProperty != 1L) goto _L12; else goto _L13
_L13:
        if ((8 & mDigaAction) == 0) goto _L15; else goto _L14
_L14:
        ChannelDataMgr.ChannelData channeldata;
        channeldata = mContentListenr.getPresentItemInfo(mNextCreateRecordScheduleObjID);
        Log.v("ChannelTask", (new StringBuilder()).append("switch: step 3 / 4 channelData = ").append(channeldata).append(" mediaid=").append(l).append(" mDigaAction = ").append(mDigaAction).toString());
        if (channeldata == null) goto _L17; else goto _L16
_L16:
        channeldatainfo.mRefreshItemID = channeldata._id;
        l = updateMediaItem(channeldatainfo);
        if ((8 & mDigaAction) != 0)
        {
            DLNA.instance().getServerManager().digaCreateRecordSchedule(getServerUDN(), Settings.instance().getRecordScheduleID(), mNextCreateRecordScheduleObjID);
        }
_L19:
        if (l == 0L) goto _L12; else goto _L18
_L18:
        hashset.add(Integer.valueOf(channeldatainfo.mChannelID));
          goto _L12
_L17:
        synchronized (mListener)
        {
            mListener.OnDigaActionFinish(mDigaAction, 5);
        }
        mDigaAction = 0;
          goto _L19
        exception5;
        ionchanneldataupdatelistener6;
        JVM INSTR monitorexit ;
        throw exception5;
_L15:
        if (!channeldatainfo.mRefreshMeta) goto _L21; else goto _L20
_L20:
        l = updateMediaItem(channeldatainfo);
          goto _L19
_L21:
        long l1 = insertMediaItem(channeldatainfo);
        l = l1;
          goto _L19
        exception4;
        ionchanneldataupdatelistener4;
        JVM INSTR monitorexit ;
        throw exception4;
        exception2;
        ionchanneldataupdatelistener;
        JVM INSTR monitorexit ;
        throw exception2;
_L9:
        if ((2 & mDigaAction) == 0) goto _L7; else goto _L22
_L22:
        int j;
        Log.v("ChannelTask", (new StringBuilder()).append("all finished mDigaAction = ").append(mDigaAction).toString());
        j = mDigaAction;
        mDigaAction = 0;
        if ((j & 4) == 0) goto _L24; else goto _L23
_L23:
        if (getServerUDN() != null)
        {
            mDigaAction = 4;
            DLNA.instance().getServerManager().digaCreateRecordSchedule(getServerUDN(), "", mCreateRecordScheduleObjID);
        }
_L24:
        if (mDigaAction != 0) goto _L7; else goto _L25
_L25:
        IOnChannelDataUpdateListener ionchanneldataupdatelistener2 = mListener;
        ionchanneldataupdatelistener2;
        JVM INSTR monitorenter ;
        IOnChannelDataUpdateListener ionchanneldataupdatelistener3 = mListener;
        Exception exception3;
        int k;
        if (getServerUDN() != null)
        {
            k = 0;
        } else
        {
            k = 5;
        }
        ionchanneldataupdatelistener3.OnDigaActionFinish(j, k);
        ionchanneldataupdatelistener2;
        JVM INSTR monitorexit ;
          goto _L7
        exception3;
        ionchanneldataupdatelistener2;
        JVM INSTR monitorexit ;
        throw exception3;
        return;
        if (true) goto _L27; else goto _L26
_L26:
    }

    public void setChannelDataCache(String s, HashMap hashmap)
    {
        this;
        JVM INSTR monitorenter ;
        cancelTask();
        mDataCacheList = hashmap;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        if (mServerUDN != null && mServerUDN.equals(s))
        {
            break MISSING_BLOCK_LABEL_40;
        }
        mContentListenr.reset();
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








}
