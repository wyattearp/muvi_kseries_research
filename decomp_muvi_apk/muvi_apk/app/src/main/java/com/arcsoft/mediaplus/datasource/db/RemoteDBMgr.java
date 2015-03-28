// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Looper;
import com.arcsoft.Recyclable;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.HandlerTimer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask, RemoteDataDBHelper

public class RemoteDBMgr
{
    public static interface IOnDBDataListener
    {

        public abstract void OnDBDataMounted(String s);

        public abstract void OnDBDataTransmittedBegin(String s);

        public abstract void OnDBDataTransmittedFinish(String s);

        public abstract void OnDBDataUnMounted(String s);

        public abstract void OnDBDataUpdated(String s, UpdateInfo updateinfo);
    }

    public static interface IServerChangedListener
    {

        public abstract void onServerBeginLoadingData();

        public abstract void onServerChanged(String s);

        public abstract void onServerEndLoadingData(int i);
    }

    protected class NotifyTimer extends HandlerTimer
        implements com.arcsoft.util.os.HandlerTimer.IOnTimerListener, Recyclable
    {

        private static final int CHECK_INTERVAL_MS = 3000;
        private final Map mUpdateInfo = new HashMap();
        final RemoteDBMgr this$0;

        public void clearFlags()
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

        public void notifyChanges()
        {
            Map map = mUpdateInfo;
            map;
            JVM INSTR monitorenter ;
            Iterator iterator = mUpdateInfo.entrySet().iterator();
_L4:
            UpdateInfo updateinfo;
            String s;
            IOnDBDataListener aiondbdatalistener[];
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_212;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            updateinfo = (UpdateInfo)entry.getValue();
            s = (String)entry.getKey();
            Log.e("DB", (new StringBuilder()).append("Notify : videorootfolder = ").append(updateinfo.videorootfolderadded).append(" folder = ").append(updateinfo.folderadded).append(" audio = ").append(updateinfo.audioadded).append(" image = ").append(updateinfo.imageadded).append(" video = ").append(updateinfo.videoadded).toString());
            aiondbdatalistener = getDataUpdateListenersCopy();
            if (aiondbdatalistener == null) goto _L2; else goto _L1
_L1:
            int i = aiondbdatalistener.length;
            int j = 0;
_L3:
            if (j >= i)
            {
                break; /* Loop/switch isn't completed */
            }
            aiondbdatalistener[j].OnDBDataUpdated(s, updateinfo);
            j++;
            if (true) goto _L3; else goto _L2
_L2:
            Log.e("DB", "Notify End ");
              goto _L4
            Exception exception;
            exception;
            map;
            JVM INSTR monitorexit ;
            throw exception;
            clearFlags();
            map;
            JVM INSTR monitorexit ;
        }

        public void onTimer()
        {
            notifyChanges();
        }

        public void recycle()
        {
            cancel();
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

        public void setAllUpdateFlag(String s)
        {
            this;
            JVM INSTR monitorenter ;
            post(s. new Runnable() {

                final NotifyTimer this$1;
                final String val$serverudn;

                public void run()
                {
                    Map map = mUpdateInfo;
                    map;
                    JVM INSTR monitorenter ;
                    UpdateInfo updateinfo = (UpdateInfo)mUpdateInfo.get(serverudn);
                    if (updateinfo != null)
                    {
                        break MISSING_BLOCK_LABEL_67;
                    }
                    updateinfo = new UpdateInfo();
                    mUpdateInfo.put(serverudn, updateinfo);
                    updateinfo.videorootfolderadded = "AV".equals(mDataTask.getCurrentFolder());
                    updateinfo.videoadded = true;
                    updateinfo.imageadded = true;
                    updateinfo.audioadded = true;
                    updateinfo.folderadded = true;
                    map;
                    JVM INSTR monitorexit ;
                    notifyChanges();
                    return;
                    Exception exception;
                    exception;
                    map;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = String.this;
                super();
            }
            });
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void setDataTransmittedBeginFlag(String s)
        {
            post(s. new Runnable() {

                final NotifyTimer this$1;
                final String val$serverudn;

                public void run()
                {
                    IOnDBDataListener aiondbdatalistener[] = getDataUpdateListenersCopy();
                    if (aiondbdatalistener != null)
                    {
                        int i = aiondbdatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aiondbdatalistener[j].OnDBDataTransmittedBegin(serverudn);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = String.this;
                super();
            }
            });
        }

        public void setDataTransmittedFinishFlag(String s)
        {
            post(s. new Runnable() {

                final NotifyTimer this$1;
                final String val$serverudn;

                public void run()
                {
                    IOnDBDataListener aiondbdatalistener[] = getDataUpdateListenersCopy();
                    if (aiondbdatalistener != null)
                    {
                        int i = aiondbdatalistener.length;
                        for (int j = 0; j < i; j++)
                        {
                            aiondbdatalistener[j].OnDBDataTransmittedFinish(serverudn);
                        }

                    }
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = String.this;
                super();
            }
            });
        }

        public void setMediaAddedFlag(final String serverudn, boolean flag, final boolean folderadded, final boolean audioadded, final boolean imageadded, final boolean videoadded)
        {
            post(flag. new Runnable() {

                final NotifyTimer this$1;
                final boolean val$audioadded;
                final boolean val$folderadded;
                final boolean val$imageadded;
                final String val$serverudn;
                final boolean val$videoadded;
                final boolean val$videorootfolderadded;

                public void run()
                {
                    Map map = mUpdateInfo;
                    map;
                    JVM INSTR monitorenter ;
                    UpdateInfo updateinfo = (UpdateInfo)mUpdateInfo.get(serverudn);
                    if (updateinfo != null)
                    {
                        break MISSING_BLOCK_LABEL_67;
                    }
                    updateinfo = new UpdateInfo();
                    mUpdateInfo.put(serverudn, updateinfo);
                    updateinfo.audioadded = updateinfo.audioadded | audioadded;
                    updateinfo.videoadded = updateinfo.videoadded | videoadded;
                    updateinfo.imageadded = updateinfo.imageadded | imageadded;
                    updateinfo.folderadded = updateinfo.folderadded | folderadded;
                    updateinfo.videorootfolderadded = updateinfo.videorootfolderadded | videorootfolderadded;
                    resume();
                    map;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception;
                    exception;
                    map;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

            
            {
                this$1 = final_notifytimer;
                serverudn = s;
                audioadded = flag;
                videoadded = flag1;
                imageadded = flag2;
                folderadded = flag3;
                videorootfolderadded = Z.this;
                super();
            }
            });
        }

        public void setServerChangeFlag(String s, final boolean fmount)
        {
            post(s. new Runnable() {

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
            this$0 = RemoteDBMgr.this;
            super(looper);
            set(3000, true, this);
        }
    }

    public static class RootFolderData
    {

        public long _id;
        public com.arcsoft.adk.atv.UPnP.RemoteItemDesc desc;

        public RootFolderData()
        {
            _id = 0L;
            desc = null;
        }
    }

    public class UpdateInfo
    {

        public boolean audioadded;
        public boolean folderadded;
        public boolean imageadded;
        String serverudn;
        final RemoteDBMgr this$0;
        public boolean videoadded;
        public boolean videorootfolderadded;

        public UpdateInfo()
        {
            this$0 = RemoteDBMgr.this;
            super();
            serverudn = null;
            audioadded = false;
            imageadded = false;
            videoadded = false;
            folderadded = false;
            videorootfolderadded = false;
        }
    }


    public static final String DIGA_VIDEO_ROOT = "AV";
    private static RemoteDBMgr sInstance = null;
    protected Application mApplication;
    protected RemoteDataDBHelper mDataDBHelper;
    protected DataTask mDataTask;
    private final DataTask.IOnDataUpdateListener mDataUpdateListener;
    protected boolean mEncodedFolder;
    private final ArrayList mListeners;
    protected Looper mLooper;
    protected NotifyTimer mNotifyTimer;
    IServerChangedListener mServerChangedListener;
    private final com.arcsoft.adk.atv.ServerManager.IServerStatusListener mServerListener;
    protected String mServerUDN;
    private final ArrayList mVideoRootFolderDatas;

    public RemoteDBMgr()
    {
        mDataDBHelper = null;
        mApplication = null;
        mLooper = null;
        mDataTask = null;
        mNotifyTimer = null;
        mServerUDN = null;
        mListeners = new ArrayList();
        mEncodedFolder = false;
        mVideoRootFolderDatas = new ArrayList();
        mServerChangedListener = null;
        mDataUpdateListener = new DataTask.IOnDataUpdateListener() {

            final RemoteDBMgr this$0;

            public void OnDataIncreased(String s, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4)
            {
                mNotifyTimer.setMediaAddedFlag(s, flag, flag1, flag2, flag3, flag4);
            }

            public void OnDataTransmittedBegin(String s)
            {
                mNotifyTimer.setDataTransmittedBeginFlag(s);
                if (mServerChangedListener != null)
                {
                    mServerChangedListener.onServerBeginLoadingData();
                }
            }

            public void OnDataTransmittedFinish(String s, int i)
            {
                mNotifyTimer.setDataTransmittedFinishFlag(s);
                if (mServerChangedListener != null)
                {
                    mServerChangedListener.onServerEndLoadingData(i);
                }
            }

            public void OnDataUpdated(String s)
            {
                mNotifyTimer.setAllUpdateFlag(s);
            }

            
            {
                this$0 = RemoteDBMgr.this;
                super();
            }
        };
        mServerListener = new com.arcsoft.adk.atv.ServerManager.IServerStatusListener() {

            final RemoteDBMgr this$0;

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
                synchronized (RemoteDBMgr.this)
                {
                    if (mServerUDN != null && mServerUDN.equals(mediaserverdesc.m_strUuid))
                    {
                        startDBData(mDataTask.getCurrentFolder(), true);
                    }
                }
                return;
                exception;
                remotedbmgr;
                JVM INSTR monitorexit ;
                throw exception;
            }

            public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
            {
            }

            public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
            {
                synchronized (RemoteDBMgr.this)
                {
                    if (mServerUDN != null && mServerUDN.equals(mediaserverdesc.m_strUuid))
                    {
                        stopDBData();
                    }
                }
                return;
                exception;
                remotedbmgr;
                JVM INSTR monitorexit ;
                throw exception;
            }

            
            {
                this$0 = RemoteDBMgr.this;
                super();
            }
        };
    }

    protected RemoteDBMgr(Application application, Looper looper)
    {
        mDataDBHelper = null;
        mApplication = null;
        mLooper = null;
        mDataTask = null;
        mNotifyTimer = null;
        mServerUDN = null;
        mListeners = new ArrayList();
        mEncodedFolder = false;
        mVideoRootFolderDatas = new ArrayList();
        mServerChangedListener = null;
        mDataUpdateListener = new _cls1();
        mServerListener = new _cls2();
        mApplication = application;
        mLooper = looper;
    }

    private IOnDBDataListener[] getDataUpdateListenersCopy()
    {
        ArrayList arraylist = mListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mListeners.size();
        IOnDBDataListener aiondbdatalistener[];
        aiondbdatalistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IOnDBDataListener aiondbdatalistener1[] = new IOnDBDataListener[mListeners.size()];
        aiondbdatalistener = (IOnDBDataListener[])mListeners.toArray(aiondbdatalistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aiondbdatalistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static void initSingleton(Application application, Looper looper)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new RemoteDBMgr(application, looper);
            sInstance.init();
            return;
        }
    }

    public static RemoteDBMgr instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    private void notifyServerMount(boolean flag, String s)
    {
        IOnDBDataListener aiondbdatalistener[] = getDataUpdateListenersCopy();
        if (aiondbdatalistener != null)
        {
            int i = aiondbdatalistener.length;
            int j = 0;
            while (j < i) 
            {
                IOnDBDataListener iondbdatalistener = aiondbdatalistener[j];
                if (flag)
                {
                    iondbdatalistener.OnDBDataMounted(s);
                } else
                {
                    iondbdatalistener.OnDBDataUnMounted(s);
                }
                j++;
            }
        }
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

    public void cancelServerDataRequest()
    {
        mDataTask.cancelTask();
    }

    public int delteRemoteItem(String s)
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
        if (s == null) goto _L2; else goto _L1
_L1:
        String s1 = mServerUDN;
        i = 0;
        if (s1 == null) goto _L2; else goto _L3
_L3:
        RemoteDataDBHelper remotedatadbhelper = mDataDBHelper;
        i = 0;
        if (remotedatadbhelper != null) goto _L4; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return i;
_L4:
        int j;
        int k;
        SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String as[] = {
            s
        };
        Cursor cursor = sqlitedatabase.query("MediaTable", null, "ITEM_UUID=?", as, null, null, null);
        cursor.moveToFirst();
        String s2 = cursor.getString(cursor.getColumnIndex("_ID"));
        cursor.close();
        j = sqlitedatabase.delete("MediaTable", "ITEM_UUID=?", as);
        k = sqlitedatabase.delete("ResourceTable", "MEDIA_ID=?", new String[] {
            s2
        });
        i = j | k;
        if (true) goto _L2; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public String getCurrentFolder()
    {
        return mDataTask.getCurrentFolder();
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

    public int getRemoteItemCopyCount(String s, String s1)
    {
        if (s != null && s1 != null && mServerUDN != null && mDataDBHelper != null && mServerUDN.equals(s))
        {
            SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
            SQLiteQueryBuilder sqlitequerybuilder = new SQLiteQueryBuilder();
            String as[] = {
                "PXNCOPYCOUNT"
            };
            sqlitequerybuilder.setTables("MediaTable, ResourceTable");
            sqlitequerybuilder.appendWhere("MediaTable._ID=MEDIA_ID");
            Cursor cursor = sqlitequerybuilder.query(sqlitedatabase, as, "ITEM_UUID=?", new String[] {
                s1
            }, null, null, null);
            if (cursor != null)
            {
                boolean flag = cursor.moveToFirst();
                int i = 0;
                if (flag)
                {
                    i = cursor.getInt(cursor.getColumnIndex("PXNCOPYCOUNT"));
                }
                cursor.close();
                return i;
            }
        }
        return 0;
    }

    public com.arcsoft.adk.atv.UPnP.RemoteItemDesc getRemoteItemDesc(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN == null) goto _L2; else goto _L1
_L1:
        RemoteDataDBHelper remotedatadbhelper = mDataDBHelper;
        if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
        com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc = null;
_L5:
        this;
        JVM INSTR monitorexit ;
        return remoteitemdesc;
_L3:
        remoteitemdesc = new com.arcsoft.adk.atv.UPnP.RemoteItemDesc();
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        String s;
        sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        cursor = sqlitedatabase.query("MediaTable", null, "_ID=?", as, null, null, null);
        cursor.moveToFirst();
        remoteitemdesc.m_strObjId = cursor.getString(cursor.getColumnIndex("ITEM_UUID"));
        remoteitemdesc.m_strParentId = cursor.getString(cursor.getColumnIndex("PARENT_ID"));
        remoteitemdesc.m_strTitle = cursor.getString(cursor.getColumnIndex("TITLE"));
        remoteitemdesc.m_lProperty = cursor.getInt(cursor.getColumnIndex("PROPERTY"));
        remoteitemdesc.m_lChildCount = -1L;
        s = cursor.getString(cursor.getColumnIndex("GROUP_ID"));
        if (remoteitemdesc.m_lProperty == 1L)
        {
            break MISSING_BLOCK_LABEL_263;
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_243;
        }
        int i = s.length();
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_263;
        }
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        cursor.close();
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        long l1;
        remoteitemdesc.m_PresentItem = new com.arcsoft.adk.atv.UPnP.PresentItem();
        remoteitemdesc.m_PresentItem.m_strCreator = cursor.getString(cursor.getColumnIndex("CREATOR"));
        remoteitemdesc.m_PresentItem.m_strDate = cursor.getString(cursor.getColumnIndex("DATE"));
        remoteitemdesc.m_PresentItem.m_strAlbum = cursor.getString(cursor.getColumnIndex("ALBUM"));
        remoteitemdesc.m_PresentItem.m_strGenre = cursor.getString(cursor.getColumnIndex("GENRE"));
        remoteitemdesc.m_PresentItem.m_strArtist = cursor.getString(cursor.getColumnIndex("ARTIST"));
        remoteitemdesc.m_PresentItem.m_iItemClass = cursor.getInt(cursor.getColumnIndex("UPNP_CLASS"));
        remoteitemdesc.m_PresentItem.m_strChannelName = cursor.getString(cursor.getColumnIndex("CHANNEL_NAME"));
        remoteitemdesc.m_PresentItem.m_strPxnGroupId = cursor.getString(cursor.getColumnIndex("GROUP_ID"));
        remoteitemdesc.m_PresentItem.m_strPxnGroupTopFlag = cursor.getString(cursor.getColumnIndex("GROUP_TOPFLAG"));
        remoteitemdesc.m_PresentItem.m_strPxnGroupTitle = cursor.getString(cursor.getColumnIndex("GROUP_TITLE"));
        remoteitemdesc.m_PresentItem.m_strPxnGroupMemberNum = cursor.getString(cursor.getColumnIndex("GROUP_MEMBERNUMBER"));
        l1 = remoteitemdesc.m_lProperty;
        if (l1 == 1L)
        {
            break MISSING_BLOCK_LABEL_579;
        }
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        cursor.close();
        continue; /* Loop/switch isn't completed */
        remoteitemdesc.m_PresentItem.m_AlbumArtUriList = new ArrayList();
        if (remoteitemdesc.m_PresentItem.m_iItemClass != 3)
        {
            (new com.arcsoft.adk.atv.UPnP.PresentItem_AlbumArtUri()).m_strAlbumArtUri = cursor.getString(cursor.getColumnIndex("ALBUM_URL"));
        }
        remoteitemdesc.m_PresentItem.m_ResourceList = new ArrayList();
        cursor.close();
        Exception exception1;
        cursor = null;
        try
        {
            String as1[] = new String[1];
            as1[0] = (new StringBuilder()).append("").append(l).toString();
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
            for (cursor = sqlitedatabase.query("ResourceTable", null, "MEDIA_ID=?", as1, null, null, null); cursor.moveToNext(); remoteitemdesc.m_PresentItem.m_ResourceList.add(presentitem_resource))
            {
                presentitem_resource = new com.arcsoft.adk.atv.UPnP.PresentItem_Resource();
                presentitem_resource.m_lDuration = cursor.getInt(cursor.getColumnIndex("DURATION"));
                presentitem_resource.m_lSize = cursor.getInt(cursor.getColumnIndex("SIZE"));
                presentitem_resource.m_strProtection = cursor.getString(cursor.getColumnIndex("PROTECTION"));
                presentitem_resource.m_lBitsPerSample = cursor.getInt(cursor.getColumnIndex("BITSPERSAMPLE"));
                presentitem_resource.m_lSampleFrequency = cursor.getInt(cursor.getColumnIndex("SAMPLFREQUENCY"));
                presentitem_resource.m_lNbAudioChannels = cursor.getInt(cursor.getColumnIndex("NOAUDIOCHANNEL"));
                presentitem_resource.m_strResolution = cursor.getString(cursor.getColumnIndex("RESOLUTION"));
                presentitem_resource.m_lColorDepth = cursor.getInt(cursor.getColumnIndex("COLORDEPTH"));
                presentitem_resource.m_lPxnCopyCount = cursor.getInt(cursor.getColumnIndex("PXNCOPYCOUNT"));
                presentitem_resource.m_lPxnVgaContentBitrate = cursor.getInt(cursor.getColumnIndex("VGABITRATE"));
                presentitem_resource.m_strPxnVgaContentUri = cursor.getString(cursor.getColumnIndex("RES_VGAURL"));
                presentitem_resource.m_strPxnVgaContentProtocolInfo = cursor.getString(cursor.getColumnIndex("VGAPROTOCOL_INFO"));
                presentitem_resource.m_strPxnChapterList = cursor.getString(cursor.getColumnIndex("CHAPTERLIST_URL"));
                presentitem_resource.m_lBitrate = cursor.getInt(cursor.getColumnIndex("BITRATE"));
                presentitem_resource.m_strUri = cursor.getString(cursor.getColumnIndex("RES_URL"));
                presentitem_resource.m_strProtocolInfo = cursor.getString(cursor.getColumnIndex("PROTOCOL_INFO"));
                presentitem_resource.m_lPxnResumePoint = cursor.getInt(cursor.getColumnIndex("RESUME_POINT"));
            }

            break MISSING_BLOCK_LABEL_1148;
        }
        catch (Exception exception2)
        {
            remoteitemdesc = null;
            if (cursor == null)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        finally
        {
            if (cursor == null)
            {
                break MISSING_BLOCK_LABEL_1177;
            }
        }
        cursor.close();
        remoteitemdesc = null;
        continue; /* Loop/switch isn't completed */
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        cursor.close();
        if (true) goto _L5; else goto _L4
_L4:
        cursor.close();
        throw exception1;
    }

    public int getRemoteItemDuration(String s, String s1)
    {
        if (s != null && s1 != null && mServerUDN != null && mDataDBHelper != null && mServerUDN.equals(s))
        {
            SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
            SQLiteQueryBuilder sqlitequerybuilder = new SQLiteQueryBuilder();
            String as[] = {
                "CLEARTEXT_SIZE"
            };
            sqlitequerybuilder.setTables("MediaTable, ResourceTable");
            sqlitequerybuilder.appendWhere("MediaTable._ID=MEDIA_ID");
            Cursor cursor = sqlitequerybuilder.query(sqlitedatabase, as, "ITEM_UUID=?", new String[] {
                s1
            }, null, null, null);
            if (cursor != null)
            {
                boolean flag = cursor.moveToFirst();
                int i = 0;
                if (flag)
                {
                    i = cursor.getInt(cursor.getColumnIndex("CLEARTEXT_SIZE"));
                }
                cursor.close();
                return i;
            }
        }
        return 0;
    }

    public com.arcsoft.adk.atv.UPnP.PresentItem_Resource getRemoteItemProtocolInfo(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource = null;
        if (s == null) goto _L2; else goto _L1
_L1:
        presentitem_resource = null;
        if (s1 == null) goto _L2; else goto _L3
_L3:
        String s2 = mServerUDN;
        presentitem_resource = null;
        if (s2 == null) goto _L2; else goto _L4
_L4:
        RemoteDataDBHelper remotedatadbhelper = mDataDBHelper;
        presentitem_resource = null;
        if (remotedatadbhelper != null) goto _L5; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return presentitem_resource;
_L5:
        boolean flag = mServerUDN.equals(s);
        presentitem_resource = null;
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        Cursor cursor;
        SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
        SQLiteQueryBuilder sqlitequerybuilder = new SQLiteQueryBuilder();
        String as[] = {
            "PROTOCOL_INFO", "VGAPROTOCOL_INFO", "DURATION", "CLEARTEXT_SIZE", "VGACLEARTEXT_SIZE"
        };
        sqlitequerybuilder.setTables("MediaTable, ResourceTable");
        sqlitequerybuilder.appendWhere("MediaTable._ID=MEDIA_ID");
        cursor = sqlitequerybuilder.query(sqlitedatabase, as, "ITEM_UUID=?", new String[] {
            s1
        }, null, null, null);
        presentitem_resource = null;
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag1 = cursor.moveToFirst();
        presentitem_resource = null;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_289;
        }
        presentitem_resource = new com.arcsoft.adk.atv.UPnP.PresentItem_Resource();
        presentitem_resource.m_strProtocolInfo = cursor.getString(cursor.getColumnIndex("PROTOCOL_INFO"));
        presentitem_resource.m_strPxnVgaContentProtocolInfo = cursor.getString(cursor.getColumnIndex("VGAPROTOCOL_INFO"));
        presentitem_resource.m_lDuration = cursor.getLong(cursor.getColumnIndex("DURATION"));
        presentitem_resource.m_lCleartextSize = cursor.getLong(cursor.getColumnIndex("CLEARTEXT_SIZE"));
        presentitem_resource.m_lPxnVgaContentCleartextSize = cursor.getLong(cursor.getColumnIndex("VGACLEARTEXT_SIZE"));
        cursor.close();
        if (true) goto _L2; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    public ArrayList getRemoteItemResourceDesc(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN == null) goto _L2; else goto _L1
_L1:
        RemoteDataDBHelper remotedatadbhelper = mDataDBHelper;
        if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
        ArrayList arraylist = null;
_L5:
        this;
        JVM INSTR monitorexit ;
        return arraylist;
_L3:
        Cursor cursor = null;
        SQLiteDatabase sqlitedatabase;
        int i;
        sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        cursor = sqlitedatabase.query("MediaTable", new String[] {
            "PROPERTY"
        }, "_ID=?", as, null, null, null);
        cursor.moveToFirst();
        i = cursor.getInt(cursor.getColumnIndex("PROPERTY"));
        if (i == 1)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        arraylist = null;
        if (cursor == null) goto _L5; else goto _L4
_L4:
        cursor.close();
        arraylist = null;
          goto _L5
        Exception exception;
        exception;
        throw exception;
        arraylist = new ArrayList();
        cursor.close();
        cursor = null;
        String as1[] = new String[1];
        as1[0] = (new StringBuilder()).append("").append(l).toString();
        com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
        for (cursor = sqlitedatabase.query("ResourceTable", null, "MEDIA_ID=?", as1, null, null, null); cursor.moveToNext(); arraylist.add(presentitem_resource))
        {
            presentitem_resource = new com.arcsoft.adk.atv.UPnP.PresentItem_Resource();
            presentitem_resource.m_lDuration = cursor.getInt(cursor.getColumnIndex("DURATION"));
            presentitem_resource.m_lSize = cursor.getInt(cursor.getColumnIndex("SIZE"));
            presentitem_resource.m_strProtection = cursor.getString(cursor.getColumnIndex("PROTECTION"));
            presentitem_resource.m_lBitsPerSample = cursor.getInt(cursor.getColumnIndex("BITSPERSAMPLE"));
            presentitem_resource.m_lSampleFrequency = cursor.getInt(cursor.getColumnIndex("SAMPLFREQUENCY"));
            presentitem_resource.m_lNbAudioChannels = cursor.getInt(cursor.getColumnIndex("NOAUDIOCHANNEL"));
            presentitem_resource.m_strResolution = cursor.getString(cursor.getColumnIndex("RESOLUTION"));
            presentitem_resource.m_lColorDepth = cursor.getInt(cursor.getColumnIndex("COLORDEPTH"));
            presentitem_resource.m_lPxnCopyCount = cursor.getInt(cursor.getColumnIndex("PXNCOPYCOUNT"));
            presentitem_resource.m_lPxnVgaContentBitrate = cursor.getInt(cursor.getColumnIndex("VGABITRATE"));
            presentitem_resource.m_strPxnVgaContentUri = cursor.getString(cursor.getColumnIndex("RES_VGAURL"));
            presentitem_resource.m_strPxnVgaContentProtocolInfo = cursor.getString(cursor.getColumnIndex("VGAPROTOCOL_INFO"));
            presentitem_resource.m_strPxnChapterList = cursor.getString(cursor.getColumnIndex("CHAPTERLIST_URL"));
            presentitem_resource.m_lBitrate = cursor.getInt(cursor.getColumnIndex("BITRATE"));
            presentitem_resource.m_strUri = cursor.getString(cursor.getColumnIndex("RES_URL"));
            presentitem_resource.m_strProtocolInfo = cursor.getString(cursor.getColumnIndex("PROTOCOL_INFO"));
            presentitem_resource.m_lPxnResumePoint = cursor.getInt(cursor.getColumnIndex("RESUME_POINT"));
        }

          goto _L6
        Exception exception3;
        exception3;
        arraylist;
_L10:
        arraylist = null;
        if (cursor == null) goto _L5; else goto _L7
_L7:
        cursor.close();
        arraylist = null;
          goto _L5
_L6:
        if (cursor == null) goto _L5; else goto _L8
_L8:
        cursor.close();
          goto _L5
_L9:
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_685;
        }
        cursor.close();
        Exception exception2;
        throw exception2;
        exception2;
        arraylist;
          goto _L9
        Exception exception1;
        exception1;
          goto _L10
        exception2;
          goto _L9
    }

    public String getRemoteItemUUID(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN == null) goto _L2; else goto _L1
_L1:
        RemoteDataDBHelper remotedatadbhelper = mDataDBHelper;
        if (remotedatadbhelper != null) goto _L3; else goto _L2
_L2:
        String s = null;
_L5:
        this;
        JVM INSTR monitorexit ;
        return s;
_L3:
        Cursor cursor = null;
        String s1;
        SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String as[] = new String[1];
        as[0] = (new StringBuilder()).append("").append(l).toString();
        cursor = sqlitedatabase.query("MediaTable", new String[] {
            "ITEM_UUID"
        }, "_ID=?", as, null, null, null);
        cursor.moveToFirst();
        s1 = cursor.getString(cursor.getColumnIndex("ITEM_UUID"));
        s = s1;
        if (cursor == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        cursor.close();
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        Exception exception2;
        exception2;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_178;
        }
        cursor.close();
        break MISSING_BLOCK_LABEL_178;
        Exception exception1;
        exception1;
        if (cursor == null)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        cursor.close();
        throw exception1;
        s = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    protected void init()
    {
        mNotifyTimer = new NotifyTimer(mLooper);
        mDataTask = new DataTask();
        mDataTask.setOnDataUpdateListener(mDataUpdateListener);
        DLNA.instance().getServerManager().registerServerStatusListener(mServerListener);
        mDataTask.start();
    }

    public boolean initPlayItem(long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN != null)
        {
            break MISSING_BLOCK_LABEL_13;
        }
        this;
        JVM INSTR monitorexit ;
        return false;
        this;
        JVM INSTR monitorexit ;
        com.arcsoft.adk.atv.UPnP.RemoteItemDesc remoteitemdesc = getRemoteItemDesc(l);
        Exception exception;
        if (remoteitemdesc != null)
        {
            return mDataTask.requestPlayItemData(remoteitemdesc);
        } else
        {
            return false;
        }
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean isReceivingData()
    {
        return mDataTask.isReceivingData();
    }

    public void pauseServerDataRequest()
    {
        mDataTask.pauseTask();
    }

    public Cursor queryAudio(String as[], String s, String as1[], String s1, String s2, String s3, String s4)
    {
        this;
        JVM INSTR monitorenter ;
        Cursor cursor1 = mDataDBHelper.getManagedDatabase().query("AudioDBView", as, s, as1, s1, s2, s3, s4);
        Cursor cursor = cursor1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception1;
        exception1;
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryFolder(String as[], String s)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mDataTask.isCurrentFolderOrContainer();
        if (flag) goto _L2; else goto _L1
_L1:
        Cursor cursor = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return cursor;
_L2:
        Cursor cursor1 = mDataDBHelper.getManagedDatabase().query("FolderDBView", as, null, null, null, null, null, s);
        cursor = cursor1;
        continue; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        cursor = null;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Cursor queryImage(String as[], String s, String as1[], String s1, String s2, String s3, String s4)
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String s5;
        s5 = null;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        int i = s.length();
        s5 = null;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        s5 = (new StringBuilder()).append(s).append(" AND ").append(null).toString();
        Cursor cursor1 = sqlitedatabase.query("ImageDBView", as, s5, as1, s1, s2, s3, s4);
        Cursor cursor = cursor1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception1;
        exception1;
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public ArrayList queryRootFolderDatas()
    {
        return mVideoRootFolderDatas;
    }

    public Cursor queryVideo(String as[], String s, String as1[], String s1, String s2, String s3, String s4)
    {
        this;
        JVM INSTR monitorenter ;
        SQLiteDatabase sqlitedatabase = mDataDBHelper.getManagedDatabase();
        String s5;
        s5 = null;
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        int i = s.length();
        s5 = null;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_58;
        }
        s5 = (new StringBuilder()).append(s).append(" AND ").append(null).toString();
        Cursor cursor1 = sqlitedatabase.query("VideoDBView", as, s5, as1, s1, s2, s3, s4);
        Cursor cursor = cursor1;
_L2:
        this;
        JVM INSTR monitorexit ;
        return cursor;
        Exception exception1;
        exception1;
        cursor = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public void registerOnDataUpdateListener(IOnDBDataListener iondbdatalistener)
    {
label0:
        {
            synchronized (mListeners)
            {
                if (!mListeners.contains(iondbdatalistener))
                {
                    break label0;
                }
            }
            return;
        }
        mListeners.add(iondbdatalistener);
        arraylist;
        JVM INSTR monitorexit ;
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void requestServerData(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if (mServerUDN == null)
        {
            throw new IllegalStateException("Server has not been set");
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
        mDataTask.requestServerData(flag, mEncodedFolder);
        return;
    }

    public void resumeServerDataRequest()
    {
        mDataTask.resumeTask();
    }

    public void setBrowseByFolder(boolean flag)
    {
        if (mDataTask != null)
        {
            mDataTask.setBrowseFolderByFolder(flag, mEncodedFolder);
        }
    }

    public void setContentParam(boolean flag)
    {
        mEncodedFolder = flag;
    }

    public boolean setCurrentFolder(String s, boolean flag)
    {
        mDataTask.setVideoRootFolderDataCache(mVideoRootFolderDatas);
        return mDataTask.setCurrentFolder(s, flag, mEncodedFolder);
    }

    public void setCurrentServer(String s, String s1, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Log.e("FENG", (new StringBuilder()).append("FENG setCurrentServer forceRetrieve = ").append(flag).append(", serverudn = ").append(s).toString());
        if (mDataDBHelper != null)
        {
            if (!mServerUDN.equals(s));
            stopDBData();
        }
        mNotifyTimer.clearFlags();
        mServerUDN = s;
        Log.e("FENG", (new StringBuilder()).append("FENG setCurrentServer DLNA.instance().getServerManager().isServerOnline(serverudn) = ").append(DLNA.instance().getServerManager().isServerOnline(s)).toString());
        if (mServerChangedListener != null)
        {
            mServerChangedListener.onServerChanged(s);
        }
        if (DLNA.instance().getServerManager().isServerOnline(s))
        {
            startDBData(s1, flag);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setServerStatusListener(IServerChangedListener iserverchangedlistener)
    {
        mServerChangedListener = iserverchangedlistener;
    }

    protected void startDBData(String s, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Log.e("FENG", (new StringBuilder()).append("FENG startDBData : folderid = ").append(s).toString());
        Log.e("FENG", (new StringBuilder()).append("FENG startDBData : forceRetrieve = ").append(flag).toString());
        if (mDataDBHelper != null) goto _L2; else goto _L1
_L1:
        String s1 = mServerUDN;
        if (s1 != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        mDataDBHelper = new RemoteDataDBHelper(mApplication, mServerUDN);
        mDataTask.setDatabaseHelper(mDataDBHelper, s, flag);
        mNotifyTimer.setServerChangeFlag(mServerUDN, true);
        mDataTask.setVideoRootFolderDataCache(mVideoRootFolderDatas);
        mDataTask.requestServerData(flag, mEncodedFolder);
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    protected void stopDBData()
    {
        this;
        JVM INSTR monitorenter ;
        RemoteDataDBHelper remotedatadbhelper;
        cancelServerDataRequest();
        remotedatadbhelper = mDataDBHelper;
        if (remotedatadbhelper != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mDataDBHelper.close();
        mDataDBHelper = null;
        mNotifyTimer.clearFlags();
        mNotifyTimer.setServerChangeFlag(mServerUDN, false);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    protected void uninit()
    {
        this;
        JVM INSTR monitorenter ;
        DLNA.instance().getServerManager().unregisterServerStatusListener(mServerListener);
        mDataTask.setOnDataUpdateListener(null);
        mDataTask.recycle();
        mNotifyTimer.recycle();
        if (mDataDBHelper != null)
        {
            mDataDBHelper.close();
        }
        mDataDBHelper = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterOnDataUpdateListener(IOnDBDataListener iondbdatalistener)
    {
        synchronized (mListeners)
        {
            mListeners.remove(iondbdatalistener);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }



}
