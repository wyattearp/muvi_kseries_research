// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.app.Application;
import android.net.Uri;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.util.os.HandlerTimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataTask

public class ChannelDataMgr
{
    public static class ChannelData
    {

        public long _id;
        public int channelid;
        public Uri hdRes;
        public com.arcsoft.adk.atv.UPnP.RemoteItemDesc itemDesc;
        public String m_strVideoItemObjId;
        public Uri sdRes;

        public ChannelData()
        {
            _id = 0L;
            hdRes = null;
            sdRes = null;
            channelid = 0;
            itemDesc = null;
            m_strVideoItemObjId = null;
        }
    }

    public static interface IOnChannelDataListener
    {

        public abstract void OnChannelDataMounted(String s);

        public abstract void OnChannelDataTransmittedBegin(String s, Set set);

        public abstract void OnChannelDataTransmittedFinish(String s, Set set);

        public abstract void OnChannelDataUnMounted(String s);

        public abstract void OnChannelDataUpdated(String s, Set set);

        public abstract void OnChannelItemRefresh(String s, int i, long l);

        public abstract void OnDigaActionFinish(int i, int j);
    }

    private class NotifyTimer extends HandlerTimer
        implements com.arcsoft.util.os.HandlerTimer.IOnTimerListener, Recyclable
    {

        private static final int CHECK_INTERVAL_MS = 3000;
        private static final int MSG_RUNNABLE = 1025;
        private final Map mUpdateInfo = new HashMap();
        final ChannelDataMgr this$0;

        private void clearFlags()
        {
            synchronized (mUpdateInfo)
            {
                mUpdateInfo.clear();
                pause();
            }
            return;
            exception;
            map;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void handleMessage(Message message)
        {
            if (message.what == 1025)
            {
                ((Runnable)message.obj).run();
                return;
            } else
            {
                super.handleMessage(message);
                return;
            }
        }

        public void notifyChanges()
        {
            Map map = mUpdateInfo;
            map;
            JVM INSTR monitorenter ;
            Iterator iterator = mUpdateInfo.entrySet().iterator();
_L4:
            UpdateInfo updateinfo;
            String s;
            IOnChannelDataListener aionchanneldatalistener[];
            if (!iterator.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            updateinfo = (UpdateInfo)entry.getValue();
            s = (String)entry.getKey();
            aionchanneldatalistener = getDataUpdateListenersCopy();
            if (aionchanneldatalistener == null)
            {
                continue; /* Loop/switch isn't completed */
            }
            int i = aionchanneldatalistener.length;
            int j = 0;
_L2:
            if (j >= i)
            {
                break; /* Loop/switch isn't completed */
            }
            aionchanneldatalistener[j].OnChannelDataUpdated(s, updateinfo.updateChannels);
            j++;
            if (true) goto _L2; else goto _L1
_L1:
            if (true) goto _L4; else goto _L3
_L3:
            clearFlags();
            map;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            map;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onTimer()
        {
            notifyChanges();
        }

        public void recycle()
        {
            cancel();
            removeMessages(1025);
            synchronized (mUpdateInfo)
            {
                mUpdateInfo.clear();
            }
            return;
            exception;
            map;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void sendRunnableMsg(Runnable runnable)
        {
            if (runnable != null)
            {
                Message message = Message.obtain();
                message.what = 1025;
                message.obj = runnable;
                sendMessage(message);
            }
        }

        public void setDataAddedFlag(final String serverudn, Set set)
        {
            if (set == null)
            {
                return;
            } else
            {
                sendRunnableMsg(set. new Runnable() {

                    final NotifyTimer this$1;
                    final Set val$channelIDs;
                    final String val$serverudn;

                    public void run()
                    {
                        Map map = mUpdateInfo;
                        map;
                        JVM INSTR monitorenter ;
                        UpdateInfo updateinfo = (UpdateInfo)mUpdateInfo.get(serverudn);
                        if (updateinfo != null)
                        {
                            break MISSING_BLOCK_LABEL_49;
                        }
                        updateinfo = new UpdateInfo();
                        int i;
                        for (Iterator iterator = channelIDs.iterator(); iterator.hasNext(); updateinfo.updateChannels.add(Integer.valueOf(i)))
                        {
                            i = ((Integer)iterator.next()).intValue();
                        }

                        break MISSING_BLOCK_LABEL_106;
                        Exception exception;
                        exception;
                        map;
                        JVM INSTR monitorexit ;
                        throw exception;
                        mUpdateInfo.put(serverudn, updateinfo);
                        resume();
                        map;
                        JVM INSTR monitorexit ;
                    }

            
            {
                this$1 = final_notifytimer;
                serverudn = s;
                channelIDs = Set.this;
                super();
            }
                });
                return;
            }
        }

        public void setDataTransmittedBeginFlag(final String serverudn, Set set)
        {
            sendRunnableMsg(set. new Runnable() {

                final NotifyTimer this$1;
                final Set val$channelIDs;
                final String val$serverudn;

                public void run()
                {
                    IOnChannelDataListener aionchanneldatalistener[] = getDataUpdateListenersCopy();
                    if (aionchanneldatalistener != null)
                    {
                        int i = aionchanneldatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aionchanneldatalistener[j].OnChannelDataTransmittedBegin(serverudn, channelIDs);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = s;
                channelIDs = Set.this;
                super();
            }
            });
        }

        public void setDataTransmittedFinishFlag(final String serverudn, Set set)
        {
            sendRunnableMsg(set. new Runnable() {

                final NotifyTimer this$1;
                final Set val$channelIDs;
                final String val$serverudn;

                public void run()
                {
                    notifyChanges();
                    IOnChannelDataListener aionchanneldatalistener[] = getDataUpdateListenersCopy();
                    if (aionchanneldatalistener != null)
                    {
                        int i = aionchanneldatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aionchanneldatalistener[j].OnChannelDataTransmittedFinish(serverudn, channelIDs);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = s;
                channelIDs = Set.this;
                super();
            }
            });
        }

        public void setDigaActionFinishFlag(final int action, int i)
        {
            sendRunnableMsg(i. new Runnable() {

                final NotifyTimer this$1;
                final int val$action;
                final int val$errorCode;

                public void run()
                {
                    IOnChannelDataListener aionchanneldatalistener[] = getDataUpdateListenersCopy();
                    if (aionchanneldatalistener != null)
                    {
                        int i = aionchanneldatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aionchanneldatalistener[j].OnDigaActionFinish(action, errorCode);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                action = i;
                errorCode = I.this;
                super();
            }
            });
        }

        public void setItemRefreshFlag(final String serverudn, final int channelID, long l)
        {
            sendRunnableMsg(l. new Runnable() {

                final NotifyTimer this$1;
                final int val$channelID;
                final long val$itemid;
                final String val$serverudn;

                public void run()
                {
                    IOnChannelDataListener aionchanneldatalistener[] = getDataUpdateListenersCopy();
                    if (aionchanneldatalistener != null)
                    {
                        int i = aionchanneldatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aionchanneldatalistener[j].OnChannelItemRefresh(serverudn, channelID, itemid);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = s;
                channelID = i;
                itemid = J.this;
                super();
            }
            });
        }

        public void setServerChangeFlag(String s, final boolean fmount)
        {
            sendRunnableMsg(s. new Runnable() {

                final NotifyTimer this$1;
                final boolean val$fmount;
                final String val$fserverudn;

                public void run()
                {
                    notifyServerMount(fmount, fserverudn);
                }

            
            {
                this$1 = final_notifytimer;
                fmount = flag;
                fserverudn = String.this;
                super();
            }
            });
        }



        NotifyTimer(Looper looper)
        {
            this$0 = ChannelDataMgr.this;
            super(looper);
            set(3000, true, this);
        }
    }

    class UpdateInfo
    {

        String serverudn;
        final ChannelDataMgr this$0;
        public HashSet updateChannels;

        UpdateInfo()
        {
            this$0 = ChannelDataMgr.this;
            super();
            serverudn = null;
            updateChannels = new HashSet();
        }
    }


    public static final int CHANNEL_BS = 4;
    public static final int CHANNEL_CS = 8;
    public static final int CHANNEL_HD = 16;
    public static final int CHANNEL_INVALID = 0;
    public static final int CHANNEL_LIST[] = {
        2, 4, 8, 16
    };
    public static final int CHANNEL_TB = 2;
    private static ChannelDataMgr sInstance = null;
    private Application mApplication;
    HashMap mChannelDatas;
    private ChannelDataTask mDataTask;
    private final ChannelDataTask.IOnChannelDataUpdateListener mDataUpdateListener = new ChannelDataTask.IOnChannelDataUpdateListener() {

        final ChannelDataMgr this$0;

        public void OnChannelDataUpdated(String s, Set set)
        {
            mNotifyTimer.setDataAddedFlag(s, set);
        }

        public void OnChannelItemRefreshed(String s, int i, long l)
        {
            mNotifyTimer.setItemRefreshFlag(s, i, l);
        }

        public void OnDataTransmittedBegin(String s, Set set)
        {
            mNotifyTimer.setDataTransmittedBeginFlag(s, set);
        }

        public void OnDataTransmittedFinish(String s, Set set)
        {
            mNotifyTimer.setDataTransmittedFinishFlag(s, set);
        }

        public void OnDigaActionFinish(int i, int j)
        {
            mNotifyTimer.setDigaActionFinishFlag(i, j);
        }

            
            {
                this$0 = ChannelDataMgr.this;
                super();
            }
    };
    private final ArrayList mListeners = new ArrayList();
    private Looper mLooper;
    private NotifyTimer mNotifyTimer;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

        final ChannelDataMgr this$0;

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
            synchronized (ChannelDataMgr.this)
            {
                if (mServerUDN != null && mServerUDN.equals(mediaserverdesc.m_strUuid))
                {
                    startChannelData();
                }
            }
            return;
            exception;
            channeldatamgr;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            synchronized (ChannelDataMgr.this)
            {
                if (mServerUDN != null && mServerUDN.equals(mediaserverdesc.m_strUuid))
                {
                    stopChannelData();
                }
            }
            return;
            exception;
            channeldatamgr;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                this$0 = ChannelDataMgr.this;
                super();
            }
    };
    private String mServerUDN;

    private ChannelDataMgr(Application application, Looper looper)
    {
        mChannelDatas = null;
        mApplication = null;
        mLooper = null;
        mDataTask = null;
        mNotifyTimer = null;
        mServerUDN = null;
        mApplication = application;
        mLooper = looper;
    }

    private IOnChannelDataListener[] getDataUpdateListenersCopy()
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mListeners.size();
        IOnChannelDataListener aionchanneldatalistener[];
        aionchanneldatalistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IOnChannelDataListener aionchanneldatalistener1[] = new IOnChannelDataListener[mListeners.size()];
        aionchanneldatalistener = (IOnChannelDataListener[])mListeners.toArray(aionchanneldatalistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aionchanneldatalistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void init()
    {
        mNotifyTimer = new NotifyTimer(mLooper);
        mDataTask = new ChannelDataTask();
        mDataTask.setOnDataUpdateListener(mDataUpdateListener);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerListener);
        mDataTask.start();
    }

    public static void initSingleton(Application application, Looper looper)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new ChannelDataMgr(application, looper);
            sInstance.init();
            return;
        }
    }

    public static ChannelDataMgr instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    public static boolean isChannleSetInclude(Set set, int i)
    {
        int j = 0;
        for (Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            j |= ((Integer)iterator.next()).intValue();
        }

        return (j & i) == i;
    }

    public static boolean isChannleSetInteract(Set set, int i)
    {
        for (Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            if ((i & ((Integer)iterator.next()).intValue()) != 0)
            {
                return true;
            }
        }

        return false;
    }

    private void notifyServerMount(boolean flag, String s)
    {
        IOnChannelDataListener aionchanneldatalistener[] = getDataUpdateListenersCopy();
        if (aionchanneldatalistener != null)
        {
            int i = aionchanneldatalistener.length;
            int j = 0;
            while (j < i) 
            {
                IOnChannelDataListener ionchanneldatalistener = aionchanneldatalistener[j];
                if (flag)
                {
                    ionchanneldatalistener.OnChannelDataMounted(s);
                } else
                {
                    ionchanneldatalistener.OnChannelDataUnMounted(s);
                }
                j++;
            }
        }
    }

    private void startChannelData()
    {
        this;
        JVM INSTR monitorenter ;
        if (mChannelDatas != null) goto _L2; else goto _L1
_L1:
        String s = mServerUDN;
        if (s != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        mChannelDatas = new HashMap();
        HashMap hashmap = mChannelDatas;
        hashmap;
        JVM INSTR monitorenter ;
        int ai[];
        int i;
        ai = CHANNEL_LIST;
        i = ai.length;
        int j = 0;
_L5:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        int k = ai[j];
        mChannelDatas.put(Integer.valueOf(k), new ArrayList());
        j++;
        if (true) goto _L5; else goto _L4
_L4:
        hashmap;
        JVM INSTR monitorexit ;
        mDataTask.setChannelDataCache(mServerUDN, mChannelDatas);
        mNotifyTimer.setServerChangeFlag(mServerUDN, true);
        requestChannelData();
          goto _L2
        Exception exception;
        exception;
        throw exception;
        Exception exception1;
        exception1;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void stopChannelData()
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        cancelChannelDataRequest();
        hashmap = mChannelDatas;
        if (hashmap != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mChannelDatas = null;
        mNotifyTimer.clearFlags();
        mNotifyTimer.setServerChangeFlag(mServerUDN, false);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    private void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerListener);
        mDataTask.setOnDataUpdateListener(null);
        mDataTask.recycle();
        mNotifyTimer.recycle();
        mChannelDatas = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void uninitSingleton()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            sInstance.uninit();
            sInstance = null;
            return;
        }
    }

    public void cancelChannelDataRequest()
    {
        mDataTask.cancelTask();
    }

    public void digaDoAction(int i, int j, long l, long l1)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN != null)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mDataTask.digaDoAction(i, j, l, l1);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String getCurrentServer()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mServerUDN;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public Set getCurrentUpdateChannels()
    {
        return mDataTask.getCurrentUpdateChannels();
    }

    public boolean isReceivingData()
    {
        return mDataTask.isReceivingData();
    }

    public void pauseChannelDataRequest()
    {
        mDataTask.pauseTask();
    }

    public ArrayList queryChannelDatas(int i)
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap = mChannelDatas;
        if (hashmap != null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return arraylist;
_L2:
        arraylist = new ArrayList();
        HashMap hashmap1 = mChannelDatas;
        hashmap1;
        JVM INSTR monitorenter ;
        int ai[];
        int j;
        ai = CHANNEL_LIST;
        j = ai.length;
        Exception exception;
        Exception exception1;
        int l;
        ArrayList arraylist1;
        for (int k = 0; k >= j; k++)
        {
            break MISSING_BLOCK_LABEL_104;
        }

        l = ai[k];
        if ((l & i) == 0)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        arraylist1 = (ArrayList)mChannelDatas.get(Integer.valueOf(l));
        if (arraylist1 == null)
        {
            break MISSING_BLOCK_LABEL_123;
        }
        arraylist.addAll(arraylist1);
        break MISSING_BLOCK_LABEL_123;
        hashmap1;
        JVM INSTR monitorexit ;
        if (true) goto _L4; else goto _L3
_L3:
        exception1;
        hashmap1;
        JVM INSTR monitorexit ;
        throw exception1;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void refreshChannelDetail(int i, long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN != null)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mDataTask.refreshChannelDetail(i, l);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerOnDataUpdateListener(IOnChannelDataListener ionchanneldatalistener)
    {
label0:
        {
            synchronized (mListeners)
            {
                if (!mListeners.contains(ionchanneldatalistener))
                {
                    break label0;
                }
            }
            return;
        }
        mListeners.add(ionchanneldatalistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void requestChannelData()
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN != null)
        {
            break MISSING_BLOCK_LABEL_12;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mDataTask.requestChannelData();
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void resumeChannelDataRequest()
    {
        mDataTask.resume();
    }

    public void setCurrentServer(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if (mChannelDatas == null) goto _L2; else goto _L1
_L1:
        boolean flag = mServerUDN.equals(s);
        if (!flag) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        stopChannelData();
_L2:
        mNotifyTimer.clearFlags();
        mServerUDN = s;
        if (DLNA.instance().getServerManager().isServerOnline(s))
        {
            startChannelData();
        }
        if (true) goto _L3; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterOnDataUpdateListener(IOnChannelDataListener ionchanneldatalistener)
    {
        synchronized (mListeners)
        {
            mListeners.remove(ionchanneldatalistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }







}
