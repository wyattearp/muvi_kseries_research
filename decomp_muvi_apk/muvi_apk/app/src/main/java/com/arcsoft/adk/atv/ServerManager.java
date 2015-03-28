// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.FileDownloader;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.oem.SharpUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

// Referenced classes of package com.arcsoft.adk.atv:
//            DLNA, DeviceInfoCache, DLNAException, MSCPCallback

public final class ServerManager
{
    private class DLNAUploadProfilesAndServerProtocol
    {

        MSCPCallback.DataOnServerGetProtocolInfo protocolinfos;
        final ServerManager this$0;
        String uploadprofiles;

        private DLNAUploadProfilesAndServerProtocol()
        {
            this$0 = ServerManager.this;
            super();
        }

    }

    public static interface IContentUpdatedListener
    {

        public abstract void onDirContentUpdated(MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1);
    }

    public static interface IServerDLNAUploadListener
    {

        public abstract void onServerGetProtocolInfo(String s, MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, int i);

        public abstract void onXGetDLNAUploadProfiles(String s, String s1, int i);
    }

    public static interface IServerStatusListener
    {

        public abstract void OnDestroyObject(String s, int i);

        public abstract void OnDigaBrowseRecordTasks(String s, MSCPCallback.DataOnRecordTasks dataonrecordtasks, int i);

        public abstract void OnDigaCreateRecordSchedule(String s, MSCPCallback.DataOnRecordSchedule dataonrecordschedule, int i);

        public abstract void OnDigaDeleteRecordSchedule(String s, int i);

        public abstract void OnDigaDisableRecordSchedule(String s, int i);

        public abstract void OnDigaEnableRecordSchedule(String s, int i);

        public abstract void OnDigaXP9eGetContainerIds(String s, String s1, int i);

        public abstract void onGetSearchCapabilities(String s, String s1, int i);

        public abstract void onGetSortCapabilities(String s, String s1, int i);

        public abstract void onServerAdded(UPnP.MediaServerDesc mediaserverdesc);

        public abstract void onServerMetaChanged(UPnP.MediaServerDesc mediaserverdesc);

        public abstract void onServerRemoved(UPnP.MediaServerDesc mediaserverdesc);
    }

    private class JudgeDMSSupportUPAsyncTask extends AsyncTask
    {

        final ServerManager this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((UPnP.MediaServerDesc[])aobj);
        }

        protected transient Void doInBackground(UPnP.MediaServerDesc amediaserverdesc[])
        {
            UPnP.MediaServerDesc mediaserverdesc = amediaserverdesc[0];
            if (mediaserverdesc != null)
            {
                mediaserverdesc.m_bSupportUpload = DLNA.JNI_IsSupportUploader(mDLNA.mNativeUPnP, mediaserverdesc.m_strUuid);
            }
            return null;
        }

        private JudgeDMSSupportUPAsyncTask()
        {
            this$0 = ServerManager.this;
            super();
        }

    }

    private class ServerCapability
    {

        String searchcaps;
        String sortcaps;
        final ServerManager this$0;

        private ServerCapability()
        {
            this$0 = ServerManager.this;
            super();
        }

    }


    public static final int DMS_BUFFALO = 768;
    public static final int DMS_DIGA = 513;
    public static final int DMS_DIGA_1_00 = 514;
    public static final int DMS_DIGA_1_01 = 515;
    public static final String DMS_DIGA_VER_1_00 = "IPPLTV-1.00";
    public static final String DMS_DIGA_VER_1_01 = "IPPLTV-1.01";
    public static final String DMS_FRIENDLYNAME_SHARP_BD = "AQUOS BD";
    public static final int DMS_IO_DATA = 1024;
    public static final String DMS_MANUFACTURE_IO_DATA = "I-O DATA DEVICE, INC.";
    public static final String DMS_MANUFACTURE_PANA = "Panasonic";
    public static final String DMS_MANUFACTURE_SHARP = "Sharp Corporation";
    public static final String DMS_MODELNAME_SHARP_PHONE = "Sharp Media Server";
    public static final int DMS_NONE = 0;
    public static final int DMS_PANA = 512;
    public static final int DMS_SHARP = 256;
    public static final int DMS_SHARP_BD = 258;
    public static final int DMS_SHARP_PHONE = 257;
    private static final String ICON_DOWNLOAD_PATH;
    public static final String OBJUDN_ROOT = "0";
    public static final String SEARCH_ALL_IMAGE = "upnp:class derivedfrom \"object.item.imageItem\"";
    public static final String SEARCH_ALL_IMAGE_VIDEO = "upnp:class derivedfrom \"object.item.imageItem\" or upnp:class derivedfrom \"object.item.videoItem\"";
    public static final String SEARCH_ALL_MEDIA = "*";
    public static final String SEARCH_ALL_MUSIC = "upnp:class derivedfrom \"object.item.audioItem\"";
    public static final String SEARCH_ALL_VIDEO = "upnp:class derivedfrom \"object.item.videoItem\"";
    public static final String SORT_BYDATE_ASC = "dc:date";
    public static final String SORT_BYDATE_DESC = "-dc:date";
    public static final String SORT_CAPS_BYDATE = "dc:date";
    private static final String TAG = "ServerManager";
    private Application mApplication;
    HashMap mCapabilities;
    private DLNA mDLNA;
    private final DLNA.IOnDLNAStatusChangeListener mDLNAStatusListener = new DLNA.IOnDLNAStatusChangeListener() {

        final ServerManager this$0;

        public void OnDLNAConnected()
        {
        }

        public void OnDLNADisconnected()
        {
            IServerStatusListener aiserverstatuslistener[];
            if (Settings.instance().getDefaultDMSUDN() != null)
            {
                Settings.instance().setDefaultDMSUDN(null);
                Settings.instance().setDefaultDMSName(null);
            }
            aiserverstatuslistener = getServerListenersCopy();
            if (aiserverstatuslistener == null) goto _L2; else goto _L1
_L1:
            List list = mServerCache;
            list;
            JVM INSTR monitorenter ;
            if (mServerCache.isEmpty()) goto _L4; else goto _L3
_L3:
            Iterator iterator = mServerCache.iterator();
_L7:
            UPnP.MediaServerDesc mediaserverdesc;
            int i;
            if (!iterator.hasNext())
            {
                break; /* Loop/switch isn't completed */
            }
            mediaserverdesc = (UPnP.MediaServerDesc)iterator.next();
            i = aiserverstatuslistener.length;
            int j = 0;
_L6:
            if (j >= i)
            {
                break; /* Loop/switch isn't completed */
            }
            aiserverstatuslistener[j].onServerRemoved(mediaserverdesc);
            j++;
            if (true) goto _L6; else goto _L5
_L5:
            if (true) goto _L7; else goto _L4
_L4:
            list;
            JVM INSTR monitorexit ;
_L2:
            clearServerCache();
            return;
            Exception exception;
            exception;
            list;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnDLNAInternalError(int i)
        {
        }

            
            {
                this$0 = ServerManager.this;
                super();
            }
    };
    private final List mDirContentListeners = new ArrayList();
    private final int mDownloadRequestID = (new Long(System.currentTimeMillis())).intValue();
    private final HashMap mGetContentInfo = new HashMap();
    private final com.arcsoft.util.network.FileDownloader.IDownloadListener mIconDownloadListener = new com.arcsoft.util.network.FileDownloader.IDownloadListener() {

        final ServerManager this$0;

        public void onDownloadFinished(com.arcsoft.util.network.FileDownloader.DownloadResult downloadresult)
        {
            if (downloadresult == null || downloadresult.filePath == null)
            {
                return;
            } else
            {
                mDLNA.postRunnable(downloadresult. new Runnable() {

                    final _cls3 this$1;
                    final com.arcsoft.util.network.FileDownloader.DownloadResult val$downloadresult;

                    public void run()
                    {
                        DeviceInfoCache.ServerCacheInfo servercacheinfo;
                        UPnP.MediaServerDesc mediaserverdesc;
                        servercacheinfo = (DeviceInfoCache.ServerCacheInfo)downloadresult.request.userdata;
                        mediaserverdesc = getServerDesc(servercacheinfo.udn);
                        if (mediaserverdesc != null) goto _L2; else goto _L1
_L1:
                        return;
_L2:
                        File file;
                        byte abyte0[];
                        FileInputStream fileinputstream;
                        file = new File(downloadresult.filePath);
                        mediaserverdesc.m_DeviceIcon = BitmapFactory.decodeFile(downloadresult.filePath);
                        if (mediaserverdesc.m_DeviceIcon == null)
                        {
                            file.delete();
                            return;
                        }
                        abyte0 = new byte[(int)file.length()];
                        fileinputstream = null;
                        FileInputStream fileinputstream1 = new FileInputStream(file);
                        if (fileinputstream1 == null)
                        {
                            break MISSING_BLOCK_LABEL_135;
                        }
                        fileinputstream1.read(abyte0);
                        servercacheinfo.icondata = abyte0;
                        mDLNA.getCacheManager().saveServerCacheInfo(servercacheinfo);
                        file.delete();
                        IServerStatusListener aiserverstatuslistener[];
                        int i;
                        int j;
                        if (fileinputstream1 != null)
                        {
                            try
                            {
                                fileinputstream1.close();
                            }
                            catch (Exception exception) { }
                        }
_L4:
                        aiserverstatuslistener = getServerListenersCopy();
                        if (aiserverstatuslistener != null)
                        {
                            i = aiserverstatuslistener.length;
                            j = 0;
                            while (j < i) 
                            {
                                aiserverstatuslistener[j].onServerMetaChanged(mediaserverdesc);
                                j++;
                            }
                        }
                        if (true) goto _L1; else goto _L3
_L3:
                        Exception exception5;
                        exception5;
_L7:
                        file.delete();
                        if (fileinputstream != null)
                        {
                            try
                            {
                                fileinputstream.close();
                            }
                            catch (Exception exception2) { }
                        }
                          goto _L4
                        Exception exception3;
                        exception3;
_L6:
                        file.delete();
                        if (fileinputstream != null)
                        {
                            try
                            {
                                fileinputstream.close();
                            }
                            catch (Exception exception4) { }
                        }
                        throw exception3;
                        exception3;
                        fileinputstream = fileinputstream1;
                        if (true) goto _L6; else goto _L5
_L5:
                        Exception exception1;
                        exception1;
                        fileinputstream = fileinputstream1;
                          goto _L7
                    }

            
            {
                this$1 = final__pcls3;
                downloadresult = com.arcsoft.util.network.FileDownloader.DownloadResult.this;
                super();
            }
                }, 0L);
                return;
            }
        }

        public void onDownloadStarted(com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest, long l)
        {
        }

            
            {
                this$0 = ServerManager.this;
                super();
            }
    };
    private final HashMap mProtocolAndProfiles = new HashMap();
    private final List mServerCache = new ArrayList();
    private final MSCPCallback mServerCallback = new MSCPCallback() {

        final ServerManager this$0;

        public void OnDestroyObject(int i, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDestroyObject(s, i);
                }

            }
        }

        public void OnDigaBrowseRecordTasks(int i, MSCPCallback.DataOnRecordTasks dataonrecordtasks, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaBrowseRecordTasks(s, dataonrecordtasks, i);
                }

            }
        }

        public void OnDigaCreateRecordSchedule(int i, MSCPCallback.DataOnRecordSchedule dataonrecordschedule, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaCreateRecordSchedule(s, dataonrecordschedule, i);
                }

            }
        }

        public void OnDigaDeleteRecordSchedule(int i, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaDeleteRecordSchedule(s, i);
                }

            }
        }

        public void OnDigaDisableRecordSchedule(int i, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaDisableRecordSchedule(s, i);
                }

            }
        }

        public void OnDigaEnableRecordSchedule(int i, String s)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaEnableRecordSchedule(s, i);
                }

            }
        }

        public void OnDigaXP9eGetContainerIds(int i, String s, String s1)
        {
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].OnDigaXP9eGetContainerIds(s1, s, i);
                }

            }
        }

        public void OnDirContentUpdated(MSCPCallback.DataOnDirContentUpdated dataondircontentupdated, String s, String s1)
        {
            IContentUpdatedListener aicontentupdatedlistener[] = getContentListenersCopy();
            if (aicontentupdatedlistener != null)
            {
                int i = aicontentupdatedlistener.length;
                for (int j = 0; j < i; j++)
                {
                    aicontentupdatedlistener[j].onDirContentUpdated(dataondircontentupdated, s, s1);
                }

            }
        }

        public void OnGetSearchCapabilities(int i, String s, String s1)
        {
            if (s == null)
            {
                s = " ";
            }
            Log.e("ServerManager", (new StringBuilder()).append("OnGetSearchCapabilities: serverudn = ").append(s1).toString());
            Log.i("ServerManager", (new StringBuilder()).append("OnGetSearchCapabilities: searchcaps = ").append(s).toString());
            Log.i("ServerManager", (new StringBuilder()).append("OnGetSearchCapabilities: errorCode = ").append(i).toString());
            HashMap hashmap = mCapabilities;
            hashmap;
            JVM INSTR monitorenter ;
            ServerCapability servercapability = (ServerCapability)mCapabilities.get(s1);
            if (servercapability != null)
            {
                break MISSING_BLOCK_LABEL_143;
            }
            servercapability = new ServerCapability();
            mCapabilities.put(s1, servercapability);
            servercapability.searchcaps = s;
            hashmap;
            JVM INSTR monitorexit ;
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].onGetSearchCapabilities(s1, s, i);
                }

            }
            break MISSING_BLOCK_LABEL_208;
            Exception exception;
            exception;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnGetSortCapabilities(int i, String s, String s1)
        {
            if (s == null)
            {
                s = " ";
            }
            HashMap hashmap = mCapabilities;
            hashmap;
            JVM INSTR monitorenter ;
            ServerCapability servercapability = (ServerCapability)mCapabilities.get(s1);
            if (servercapability != null)
            {
                break MISSING_BLOCK_LABEL_68;
            }
            servercapability = new ServerCapability();
            mCapabilities.put(s1, servercapability);
            servercapability.sortcaps = s;
            hashmap;
            JVM INSTR monitorexit ;
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int j = aiserverstatuslistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverstatuslistener[k].onGetSortCapabilities(s1, s, i);
                }

            }
            break MISSING_BLOCK_LABEL_133;
            Exception exception;
            exception;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnServerAdded(MSCPCallback.DataOnServerAdded dataonserveradded)
        {
_L2:
            return;
            if (dataonserveradded == null || dataonserveradded.m_ServerDesc == null || isLocalServer(dataonserveradded.m_ServerDesc)) goto _L2; else goto _L1
_L1:
            Log.e("ServerManager", (new StringBuilder()).append("OnServerAdded: ").append(dataonserveradded.m_ServerDesc.m_strUuid).toString());
            SaveServerInfoCache(dataonserveradded.m_ServerDesc);
            addServerCache(dataonserveradded.m_ServerDesc);
            if (getServerSortCaps(dataonserveradded.m_ServerDesc.m_strUuid) == null)
            {
                getServerSortCapsAsync(dataonserveradded.m_ServerDesc.m_strUuid);
            }
_L3:
            if (getServerSearchCaps(dataonserveradded.m_ServerDesc.m_strUuid) == null)
            {
                getServerSearchCapsAsync(dataonserveradded.m_ServerDesc.m_strUuid);
            }
_L4:
            getDLNAUploadProfilesAndServerProtocolInfo(dataonserveradded.m_ServerDesc.m_strUuid);
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int i = aiserverstatuslistener.length;
                int j = 0;
                while (j < i) 
                {
                    aiserverstatuslistener[j].onServerAdded(dataonserveradded.m_ServerDesc);
                    j++;
                }
            }
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            HashMap hashmap = mCapabilities;
            hashmap;
            JVM INSTR monitorenter ;
            ServerCapability servercapability = (ServerCapability)mCapabilities.get(dataonserveradded.m_ServerDesc.m_strUuid);
            if (servercapability != null)
            {
                break MISSING_BLOCK_LABEL_278;
            }
            servercapability = new ServerCapability();
            mCapabilities.put(dataonserveradded.m_ServerDesc.m_strUuid, servercapability);
            servercapability.sortcaps = "";
            hashmap;
            JVM INSTR monitorexit ;
              goto _L3
            Exception exception1;
            exception1;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception2;
            exception2;
            HashMap hashmap1 = mCapabilities;
            hashmap1;
            JVM INSTR monitorenter ;
            ServerCapability servercapability1 = (ServerCapability)mCapabilities.get(dataonserveradded.m_ServerDesc.m_strUuid);
            if (servercapability1 != null)
            {
                break MISSING_BLOCK_LABEL_374;
            }
            servercapability1 = new ServerCapability();
            mCapabilities.put(dataonserveradded.m_ServerDesc.m_strUuid, servercapability1);
            servercapability1.searchcaps = "";
            hashmap1;
            JVM INSTR monitorexit ;
              goto _L4
            Exception exception3;
            exception3;
            hashmap1;
            JVM INSTR monitorexit ;
            throw exception3;
            if (true) goto _L2; else goto _L5
_L5:
        }

        public void OnServerGetProtocolInfo(int i, MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, String s)
        {
            HashMap hashmap = mProtocolAndProfiles;
            hashmap;
            JVM INSTR monitorenter ;
            DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s);
            if (dlnauploadprofilesandserverprotocol != null)
            {
                break MISSING_BLOCK_LABEL_61;
            }
            dlnauploadprofilesandserverprotocol = new DLNAUploadProfilesAndServerProtocol();
            mProtocolAndProfiles.put(s, dlnauploadprofilesandserverprotocol);
            dlnauploadprofilesandserverprotocol.protocolinfos = dataonservergetprotocolinfo;
            hashmap;
            JVM INSTR monitorexit ;
            IServerDLNAUploadListener aiserverdlnauploadlistener[] = getServerDLNAUploadListenersCopy();
            if (aiserverdlnauploadlistener != null)
            {
                int j = aiserverdlnauploadlistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverdlnauploadlistener[k].onServerGetProtocolInfo(s, dataonservergetprotocolinfo, i);
                }

            }
            break MISSING_BLOCK_LABEL_126;
            Exception exception;
            exception;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void OnServerRemoved(MSCPCallback.DataOnServerRemoved dataonserverremoved)
        {
            if (dataonserverremoved != null && dataonserverremoved.m_ServerDesc != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            Log.e("zdf", (new StringBuilder()).append("OnServerRemoved: ").append(dataonserverremoved.m_ServerDesc.m_strUuid).append(", ").append(dataonserverremoved.m_ServerDesc.m_strFriendlyName).toString());
            removeServerCache(dataonserverremoved.m_ServerDesc);
            cancelBrowseSearch(dataonserverremoved.m_ServerDesc.m_strUuid);
            IServerStatusListener aiserverstatuslistener[] = getServerListenersCopy();
            if (aiserverstatuslistener != null)
            {
                int i = aiserverstatuslistener.length;
                int j = 0;
                while (j < i) 
                {
                    aiserverstatuslistener[j].onServerRemoved(dataonserverremoved.m_ServerDesc);
                    j++;
                }
            }
            if (true) goto _L1; else goto _L3
_L3:
        }

        public void OnXGetDLNAUploadProfiles(int i, String s, String s1)
        {
            if (s != null);
            HashMap hashmap = mProtocolAndProfiles;
            hashmap;
            JVM INSTR monitorenter ;
            DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s1);
            if (dlnauploadprofilesandserverprotocol != null)
            {
                break MISSING_BLOCK_LABEL_65;
            }
            dlnauploadprofilesandserverprotocol = new DLNAUploadProfilesAndServerProtocol();
            mProtocolAndProfiles.put(s1, dlnauploadprofilesandserverprotocol);
            dlnauploadprofilesandserverprotocol.uploadprofiles = s;
            hashmap;
            JVM INSTR monitorexit ;
            IServerDLNAUploadListener aiserverdlnauploadlistener[] = getServerDLNAUploadListenersCopy();
            if (aiserverdlnauploadlistener != null)
            {
                int j = aiserverdlnauploadlistener.length;
                for (int k = 0; k < j; k++)
                {
                    aiserverdlnauploadlistener[k].onXGetDLNAUploadProfiles(s1, s, i);
                }

            }
            break MISSING_BLOCK_LABEL_130;
            Exception exception;
            exception;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception;
        }

            
            {
                this$0 = ServerManager.this;
                super();
            }
    };
    private final List mServerDLNAUploadListeners = new ArrayList();
    private final List mServerStatusListeners = new ArrayList();

    ServerManager(Application application, DLNA dlna)
    {
        mDLNA = null;
        mApplication = null;
        mCapabilities = new HashMap();
        mDLNA = dlna;
        mApplication = application;
        mDLNA.registerDLNAStatusListener(mDLNAStatusListener);
    }

    private void SaveServerInfoCache(UPnP.MediaServerDesc mediaserverdesc)
    {
        DeviceInfoCache deviceinfocache = mDLNA.getCacheManager();
        DeviceInfoCache.ServerCacheInfo servercacheinfo = deviceinfocache.loadServerCacheInfo(mediaserverdesc.m_strUuid);
        if (servercacheinfo == null)
        {
            servercacheinfo = new DeviceInfoCache.ServerCacheInfo();
        }
        servercacheinfo.name = mediaserverdesc.m_strFriendlyName;
        servercacheinfo.udn = mediaserverdesc.m_strUuid;
        List list = mediaserverdesc.m_DeviceIconList;
        UPnP.DeviceIcon deviceicon = null;
        if (list != null)
        {
            int i = mediaserverdesc.m_DeviceIconList.size();
            deviceicon = null;
            if (i > 0)
            {
                deviceicon = (UPnP.DeviceIcon)mediaserverdesc.m_DeviceIconList.get(0);
            }
        }
        boolean flag;
        if (servercacheinfo.icondata != null && servercacheinfo.iconurl != null && deviceicon != null && deviceicon.m_strUrl != null && servercacheinfo.iconurl.equals(deviceicon.m_strUrl))
        {
            flag = false;
            mediaserverdesc.m_DeviceIcon = BitmapFactory.decodeByteArray(servercacheinfo.icondata, 0, servercacheinfo.icondata.length);
        } else
        {
            flag = true;
            servercacheinfo.icondata = null;
            String s = null;
            if (deviceicon != null)
            {
                s = deviceicon.m_strUrl;
            }
            servercacheinfo.iconurl = s;
        }
        deviceinfocache.saveServerCacheInfo(servercacheinfo);
        if (flag)
        {
            downloadServerIcon(servercacheinfo);
        }
    }

    private void addDirUpdateID(String s, int i)
    {
        HashMap hashmap = mGetContentInfo;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mGetContentInfo.get(s);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        arraylist = new ArrayList();
        mGetContentInfo.put(s, arraylist);
        arraylist.add(Integer.valueOf(i));
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void addServerCache(UPnP.MediaServerDesc mediaserverdesc)
    {
        if (mediaserverdesc == null || mediaserverdesc.m_strUuid == null)
        {
            return;
        }
        synchronized (mServerCache)
        {
            removeServerCache(mediaserverdesc);
            mServerCache.add(mediaserverdesc);
        }
        (new JudgeDMSSupportUPAsyncTask()).execute(new UPnP.MediaServerDesc[] {
            mediaserverdesc
        });
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void checkDLNAorThrow()
    {
        if (mDLNA == null)
        {
            throw new IllegalStateException("Invalide ServerManager State - DLNA obj is null");
        } else
        {
            return;
        }
    }

    private void clearServerCache()
    {
        if (mServerCache == null)
        {
            return;
        }
        List list = mServerCache;
        list;
        JVM INSTR monitorenter ;
        for (Iterator iterator = mServerCache.iterator(); iterator.hasNext(); recycleServerDesc((UPnP.MediaServerDesc)iterator.next())) { }
        break MISSING_BLOCK_LABEL_55;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
        mServerCache.clear();
        list;
        JVM INSTR monitorexit ;
    }

    private void downloadServerIcon(DeviceInfoCache.ServerCacheInfo servercacheinfo)
    {
        if (servercacheinfo == null || servercacheinfo.iconurl == null)
        {
            return;
        } else
        {
            com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest = new com.arcsoft.util.network.FileDownloader.DownloadRequest();
            downloadrequest.parentdir = (new StringBuilder()).append(ICON_DOWNLOAD_PATH).append(servercacheinfo.udn).toString();
            downloadrequest.requestcode = mDownloadRequestID;
            downloadrequest.url = servercacheinfo.iconurl;
            downloadrequest.userdata = servercacheinfo;
            downloadrequest.listener = mIconDownloadListener;
            FileDownloader.instance().download(downloadrequest);
            return;
        }
    }

    private IContentUpdatedListener[] getContentListenersCopy()
    {
        List list = mDirContentListeners;
        list;
        JVM INSTR monitorenter ;
        int i = mDirContentListeners.size();
        IContentUpdatedListener aicontentupdatedlistener[];
        aicontentupdatedlistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IContentUpdatedListener aicontentupdatedlistener1[] = new IContentUpdatedListener[mDirContentListeners.size()];
        aicontentupdatedlistener = (IContentUpdatedListener[])mDirContentListeners.toArray(aicontentupdatedlistener1);
        list;
        JVM INSTR monitorexit ;
        return aicontentupdatedlistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private IServerDLNAUploadListener[] getServerDLNAUploadListenersCopy()
    {
        List list = mServerDLNAUploadListeners;
        list;
        JVM INSTR monitorenter ;
        int i = mServerDLNAUploadListeners.size();
        IServerDLNAUploadListener aiserverdlnauploadlistener[];
        aiserverdlnauploadlistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IServerDLNAUploadListener aiserverdlnauploadlistener1[] = new IServerDLNAUploadListener[mServerDLNAUploadListeners.size()];
        aiserverdlnauploadlistener = (IServerDLNAUploadListener[])mServerDLNAUploadListeners.toArray(aiserverdlnauploadlistener1);
        list;
        JVM INSTR monitorexit ;
        return aiserverdlnauploadlistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private IServerStatusListener[] getServerListenersCopy()
    {
        List list = mServerStatusListeners;
        list;
        JVM INSTR monitorenter ;
        int i = mServerStatusListeners.size();
        IServerStatusListener aiserverstatuslistener[];
        aiserverstatuslistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        IServerStatusListener aiserverstatuslistener1[] = new IServerStatusListener[mServerStatusListeners.size()];
        aiserverstatuslistener = (IServerStatusListener[])mServerStatusListeners.toArray(aiserverstatuslistener1);
        list;
        JVM INSTR monitorexit ;
        return aiserverstatuslistener;
        Exception exception;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean isLocalServer(UPnP.MediaServerDesc mediaserverdesc)
    {
        if (mediaserverdesc.m_strSrcIp == null) goto _L2; else goto _L1
_L1:
        if (!mediaserverdesc.m_strSrcIp.equalsIgnoreCase("127.0.0.1")) goto _L4; else goto _L3
_L3:
        String s;
        return true;
_L4:
        if ((s = NetworkUtil.getLocalIpViaWiFi(mApplication)) != null && mediaserverdesc.m_strSrcIp.equalsIgnoreCase(s)) goto _L3; else goto _L2
_L2:
        return false;
    }

    private void recycleServerDesc(UPnP.MediaServerDesc mediaserverdesc)
    {
        if (mediaserverdesc != null && mediaserverdesc.m_DeviceIcon != null)
        {
            mediaserverdesc.m_DeviceIcon.recycle();
        }
    }

    private void removeServerCache(UPnP.MediaServerDesc mediaserverdesc)
    {
        if (mediaserverdesc == null || mediaserverdesc.m_strUuid == null)
        {
            return;
        }
        synchronized (mServerCache)
        {
            Iterator iterator = mServerCache.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                UPnP.MediaServerDesc mediaserverdesc1 = (UPnP.MediaServerDesc)iterator.next();
                if (!mediaserverdesc1.m_strUuid.equalsIgnoreCase(mediaserverdesc.m_strUuid))
                {
                    continue;
                }
                recycleServerDesc(mediaserverdesc1);
                iterator.remove();
                break;
            } while (true);
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int browse(String s, String s1, String s2, int i, int j, boolean flag)
    {
        checkDLNAorThrow();
        int ai[] = new int[1];
        boolean flag1;
        if (DLNA.JNI_BrowseDirectory(mDLNA.mNativeUPnP, s, s1, s2, i, j, "", flag, ai) == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            return -1;
        } else
        {
            addDirUpdateID(s, ai[0]);
            return ai[0];
        }
    }

    public int browse(String s, String s1, boolean flag)
    {
        int i = 1;
        checkDLNAorThrow();
        int ai[] = new int[i];
        if (DLNA.JNI_BrowseDirectory(mDLNA.mNativeUPnP, s, s1, "*", 0, -1, "", flag, ai) != 0)
        {
            i = 0;
        }
        if (i == 0)
        {
            return -1;
        } else
        {
            addDirUpdateID(s, ai[0]);
            return ai[0];
        }
    }

    public void cancelAllBrowseSearch()
    {
        HashMap hashmap = mGetContentInfo;
        hashmap;
        JVM INSTR monitorenter ;
        for (Iterator iterator = (new HashSet(mGetContentInfo.keySet())).iterator(); iterator.hasNext(); cancelBrowseSearch((String)iterator.next())) { }
        break MISSING_BLOCK_LABEL_57;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        hashmap;
        JVM INSTR monitorexit ;
    }

    public void cancelBrowseSearch(int i)
    {
        checkDLNAorThrow();
        DLNA.JNI_CancelBrowseorSearch(mDLNA.mNativeUPnP, i);
        HashMap hashmap = mGetContentInfo;
        hashmap;
        JVM INSTR monitorenter ;
        Iterator iterator = mGetContentInfo.entrySet().iterator();
_L6:
        ArrayList arraylist;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_145;
        }
        arraylist = (ArrayList)((java.util.Map.Entry)iterator.next()).getValue();
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Iterator iterator1 = arraylist.iterator();
_L4:
        if (!iterator1.hasNext()) goto _L2; else goto _L1
_L1:
        if (((Integer)iterator1.next()).intValue() == i)
        {
            iterator1.remove();
        }
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        if (arraylist.size() > 0) goto _L6; else goto _L5
_L5:
        iterator.remove();
          goto _L6
        hashmap;
        JVM INSTR monitorexit ;
    }

    public void cancelBrowseSearch(String s)
    {
        checkDLNAorThrow();
        HashMap hashmap = mGetContentInfo;
        hashmap;
        JVM INSTR monitorenter ;
        ArrayList arraylist = (ArrayList)mGetContentInfo.get(s);
        if (arraylist != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return;
        Integer integer;
        for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); DLNA.JNI_CancelBrowseorSearch(mDLNA.mNativeUPnP, integer.intValue()))
        {
            integer = (Integer)iterator.next();
        }

        break MISSING_BLOCK_LABEL_86;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        mGetContentInfo.remove(s);
        hashmap;
        JVM INSTR monitorexit ;
    }

    public int destroyObeject(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_DestroyObeject(mDLNA.mNativeUPnP, s, s1, ai);
        Log.v("diga", (new StringBuilder()).append("testP JNI_DestroyObeject szObjId =").append(s1).append(" ret = ").append(i).append(" lUpdateId = ").append(ai[0]).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            ListViewManager.putUpdateItem(String.valueOf(ai[0]), s1);
            return i;
        }
    }

    public int digaBrowseRecordTasks(String s, String s1, String s2, int i, int j, String s3)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int k = DLNA.JNI_DigaBrowseRecordTasks(mDLNA.mNativeUPnP, s, s1, s2, i, j, s3, ai);
        Log.v("diga", (new StringBuilder()).append("JNI_DigaBrowseRecordTasks =").append(s).append(" ret = ").append(k).toString());
        if (k != 0)
        {
            throw new DLNAException(k);
        } else
        {
            return k;
        }
    }

    public int digaCreateRecordSchedule(String s, String s1, String s2)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        String s3 = "MS";
        if (getServerDesc(s).m_strXIppltvRecmode.contains("MVGA"))
        {
            s3 = "MVGA";
        }
        int i = DLNA.JNI_DigaCreateRecordSchedule(mDLNA.mNativeUPnP, s, s1, s2, s3, 1, String.valueOf(28200L), String.valueOf(30L), ai);
        Log.v("diga", (new StringBuilder()).append("JNI_DigaCreateRecordSchedule =").append(s).append(" ret = ").append(i).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return i;
        }
    }

    public int digaDeleteRecordSchedule(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_DigaDeleteRecordSchedule(mDLNA.mNativeUPnP, s, s1, ai);
        Log.v("diga", (new StringBuilder()).append("JNI_DigaDeleteRecordSchedule =").append(s).append(" ret = ").append(i).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return i;
        }
    }

    public int digaDisableRecordSchedule(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_DigaDisableRecordSchedule(mDLNA.mNativeUPnP, s, s1, ai);
        Log.v("diga", (new StringBuilder()).append("JNI_DigaDisableRecordSchedule =").append(s).append(" ret = ").append(i).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return i;
        }
    }

    public int digaEnableRecordSchedule(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_DigaEnableRecordSchedule(mDLNA.mNativeUPnP, s, s1, ai);
        Log.v("diga", (new StringBuilder()).append("JNI_DigaEnableRecordSchedule =").append(s).append(" ret = ").append(i).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return i;
        }
    }

    public int digaXP9eGetContainerIds(String s, String s1)
    {
        if (s == null)
        {
            throw new NullPointerException();
        } else
        {
            int ai[] = {
                0
            };
            int i = DLNA.JNI_DigaXP9eGetContainerIds(mDLNA.mNativeUPnP, s, s1, ai);
            Log.v("diga", (new StringBuilder()).append("JNI_DigaXP9eGetContainerIds =").append(s).append(" ret = ").append(i).toString());
            if (i == 0);
            return i;
        }
    }

    public void getDLNAUploadProfilesAndServerProtocolInfo(String s)
    {
        boolean flag = true;
        try
        {
            if (getServerXDLNAUploadProfiles(s) == null)
            {
                getServerXDLNAUploadProfilesAsync(s);
            }
        }
        catch (Exception exception)
        {
            DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s);
            if (dlnauploadprofilesandserverprotocol == null)
            {
                dlnauploadprofilesandserverprotocol = new DLNAUploadProfilesAndServerProtocol();
                mProtocolAndProfiles.put(s, dlnauploadprofilesandserverprotocol);
            }
            dlnauploadprofilesandserverprotocol.uploadprofiles = null;
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        if (getServerProtocolInfo(s) == null)
        {
            getServerProtocolInfoAsync(s);
        }
        return;
        Exception exception1;
        exception1;
        DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol1 = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s);
        if (dlnauploadprofilesandserverprotocol1 == null)
        {
            dlnauploadprofilesandserverprotocol1 = new DLNAUploadProfilesAndServerProtocol();
            mProtocolAndProfiles.put(s, dlnauploadprofilesandserverprotocol1);
        }
        dlnauploadprofilesandserverprotocol1.protocolinfos = null;
        return;
    }

    public List getMediaServerList()
    {
        return mServerCache;
    }

    MSCPCallback getProxyCallback()
    {
        return mServerCallback;
    }

    public UPnP.MediaServerDesc getServerDesc(String s)
    {
        if (s == null)
        {
            return null;
        }
        UPnP.MediaServerDesc mediaserverdesc;
        synchronized (mServerCache)
        {
            Iterator iterator = mServerCache.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break MISSING_BLOCK_LABEL_68;
                }
                mediaserverdesc = (UPnP.MediaServerDesc)iterator.next();
            } while (!s.equalsIgnoreCase(mediaserverdesc.m_strUuid));
        }
        return mediaserverdesc;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
        list;
        JVM INSTR monitorexit ;
        return null;
    }

    public MSCPCallback.DataOnServerGetProtocolInfo getServerProtocolInfo(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mProtocolAndProfiles;
        hashmap;
        JVM INSTR monitorenter ;
        DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s);
        MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo;
        dataonservergetprotocolinfo = null;
        if (dlnauploadprofilesandserverprotocol == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        dataonservergetprotocolinfo = dlnauploadprofilesandserverprotocol.protocolinfos;
        hashmap;
        JVM INSTR monitorexit ;
        return dataonservergetprotocolinfo;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void getServerProtocolInfoAsync(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        long al[] = {
            0L
        };
        int i = DLNA.JNI_Server_GetProtocolInfo(mDLNA.mNativeUPnP, s, al);
        if (i != 0)
        {
            Log.e("upload", (new StringBuilder()).append("JNI_Server_GetProtocolInfo error =").append(s).toString());
            throw new DLNAException(i);
        } else
        {
            return;
        }
    }

    public String getServerSearchCaps(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mCapabilities;
        hashmap;
        JVM INSTR monitorenter ;
        ServerCapability servercapability = (ServerCapability)mCapabilities.get(s);
        String s1;
        s1 = null;
        if (servercapability == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        s1 = servercapability.searchcaps;
        hashmap;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void getServerSearchCapsAsync(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_GetSearchCapabilities(mDLNA.mNativeUPnP, s, ai);
        Log.e("ServerManager", (new StringBuilder()).append("getServerSearchCapsAsync: serverudn = ").append(s).toString());
        Log.i("ServerManager", (new StringBuilder()).append("getServerSearchCapsAsync: ret = ").append(i).toString());
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return;
        }
    }

    public String getServerSortCaps(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mCapabilities;
        hashmap;
        JVM INSTR monitorenter ;
        ServerCapability servercapability = (ServerCapability)mCapabilities.get(s);
        String s1;
        s1 = null;
        if (servercapability == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        s1 = servercapability.sortcaps;
        hashmap;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void getServerSortCapsAsync(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_GetSortCapabilities(mDLNA.mNativeUPnP, s, ai);
        if (i != 0)
        {
            throw new DLNAException(i);
        } else
        {
            return;
        }
    }

    public String getServerXDLNAUploadProfiles(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        HashMap hashmap = mProtocolAndProfiles;
        hashmap;
        JVM INSTR monitorenter ;
        DLNAUploadProfilesAndServerProtocol dlnauploadprofilesandserverprotocol = (DLNAUploadProfilesAndServerProtocol)mProtocolAndProfiles.get(s);
        String s1;
        s1 = null;
        if (dlnauploadprofilesandserverprotocol == null)
        {
            break MISSING_BLOCK_LABEL_47;
        }
        s1 = dlnauploadprofilesandserverprotocol.uploadprofiles;
        hashmap;
        JVM INSTR monitorexit ;
        return s1;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void getServerXDLNAUploadProfilesAsync(String s)
    {
        if (s == null)
        {
            throw new NullPointerException();
        }
        int ai[] = {
            0
        };
        int i = DLNA.JNI_XGetDLNAUploadProfiles(mDLNA.mNativeUPnP, s, ai);
        if (i != 0)
        {
            Log.e("upload", (new StringBuilder()).append("JNI_XGetDLNAUploadProfiles error =").append(s).toString());
            throw new DLNAException(i);
        } else
        {
            return;
        }
    }

    public int getSpecialDMSType()
    {
        return getSpecialDMSType(null);
    }

    public int getSpecialDMSType(String s)
    {
        String s1;
        s1 = s;
        if (s == null)
        {
            s1 = Settings.instance().getDefaultDMSUDN();
        }
        if (s1 != null) goto _L2; else goto _L1
_L1:
        char c = '\u0101';
_L4:
        return c;
_L2:
        UPnP.MediaServerDesc mediaserverdesc;
        String s2;
        mediaserverdesc = getServerDesc(s1);
        c = '\0';
        if (mediaserverdesc == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        s2 = mediaserverdesc.m_strManufacturer;
        String s3 = mediaserverdesc.m_strModelName;
        String s4 = mediaserverdesc.m_strFriendlyName;
        Log.i("ServerManager", (new StringBuilder()).append("Manufacturer = ").append(s2).toString());
        Log.i("ServerManager", (new StringBuilder()).append("ModelName = ").append(s3).toString());
        Log.i("ServerManager", (new StringBuilder()).append("FriendlyName = ").append(s4).toString());
        if (s2 == null || !s2.contains("Sharp Corporation"))
        {
            break; /* Loop/switch isn't completed */
        }
        c = '\u0100';
        if (s3 != null && s3.contains("Sharp Media Server"))
        {
            Log.i("ServerManager", "DMS = DMS_SHARP_PHONE");
            return 257;
        }
        if (s4 != null && s4.contains("AQUOS BD"))
        {
            Log.i("ServerManager", "DMS = DMS_SHARP_BD");
            return 258;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (s2 != null && s2.contains("I-O DATA DEVICE, INC."))
        {
            return 1024;
        }
        c = '\0';
        if (s2 != null)
        {
            boolean flag = s2.contains("Panasonic");
            c = '\0';
            if (flag)
            {
                c = '\u0201';
                if (mediaserverdesc.m_strXIppltvVersion != null)
                {
                    if (mediaserverdesc.m_strXIppltvVersion.contains("IPPLTV-1.00"))
                    {
                        return 514;
                    }
                    if (mediaserverdesc.m_strXIppltvVersion.contains("IPPLTV-1.01"))
                    {
                        return 515;
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public boolean isDMSSupportUpload(String s)
    {
        UPnP.MediaServerDesc mediaserverdesc = getServerDesc(s);
        if (mediaserverdesc != null)
        {
            Log.i("ServerManager", (new StringBuilder()).append("is support upload = ").append(mediaserverdesc.m_bSupportUpload).toString());
            Log.i("ServerManager", (new StringBuilder()).append("ModelName = ").append(mediaserverdesc.m_strModelName).toString());
            return mediaserverdesc.m_bSupportUpload;
        } else
        {
            return false;
        }
    }

    public boolean isDigaDMS(String s)
    {
        return (0x200 & getSpecialDMSType(s)) != 0;
    }

    public boolean isServerOnline(String s)
    {
        return getServerDesc(s) != null;
    }

    public boolean isSharpDMS(String s)
    {
        return (0x100 & getSpecialDMSType(s)) != 0;
    }

    public boolean matchMediaSupportDLNAUpload(String s, String s1, String s2)
    {
        String s3;
        String s5;
        String as[];
        int i;
        int j;
        if (s1 == null)
        {
            return false;
        }
        s3 = SharpUtils.getValue(s1, "DLNA.ORG_PN=", null, ";");
        String s4 = getServerXDLNAUploadProfiles(s);
        if (s4 != null && s3 != null)
        {
            return s4.contains(s3);
        }
        MSCPCallback.DataOnServerGetProtocolInfo dataonservergetprotocolinfo = getServerProtocolInfo(s);
        if (dataonservergetprotocolinfo == null || dataonservergetprotocolinfo.m_strSourceValues == null)
        {
            break MISSING_BLOCK_LABEL_256;
        }
        s5 = null;
        if (s2 != null)
        {
            s2 = s2.toLowerCase();
            int k = 1 + s2.indexOf('/');
            s5 = s2.substring(0, k);
        }
        as = dataonservergetprotocolinfo.m_strSourceValues;
        i = as.length;
        j = 0;
_L7:
        if (j >= i) goto _L2; else goto _L1
_L1:
        String s6;
        s6 = as[j];
        if (s3 != null)
        {
            String s8 = SharpUtils.getValue(s6, "DLNA.ORG_PN=", null, ";");
            if (s8 != null && s8.contains(s3))
            {
                return true;
            }
        }
        if (s2 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s6.indexOf("CONTENTFORMAT=") <= 0) goto _L4; else goto _L3
_L3:
        String s7 = SharpUtils.getValue(s6, "CONTENTFORMAT=\"", null, "\"");
_L6:
        if (s7 != null && s7.toLowerCase().contains(s2))
        {
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        s7 = SharpUtils.getValue(s6, null, s5, ":");
        if (s7 == null || s7 == "")
        {
            s7 = SharpUtils.getValue(s6, null, s5, ";");
        }
        if (true) goto _L6; else goto _L5
_L5:
        j++;
          goto _L7
_L2:
        return false;
    }

    public void registerContentUpdatedListener(IContentUpdatedListener icontentupdatedlistener)
    {
        synchronized (mDirContentListeners)
        {
            if (!mDirContentListeners.contains(icontentupdatedlistener))
            {
                mDirContentListeners.add(icontentupdatedlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerServerDLNAUploadListener(IServerDLNAUploadListener iserverdlnauploadlistener)
    {
        synchronized (mServerDLNAUploadListeners)
        {
            if (!mServerDLNAUploadListeners.contains(iserverdlnauploadlistener))
            {
                mServerDLNAUploadListeners.add(iserverdlnauploadlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerServerStatusListener(IServerStatusListener iserverstatuslistener)
    {
        synchronized (mServerStatusListeners)
        {
            if (!mServerStatusListeners.contains(iserverstatuslistener))
            {
                mServerStatusListeners.add(iserverstatuslistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    void release()
    {
        reset();
        mDLNA.unregisterDLNAStatusListener(mDLNAStatusListener);
        mDLNA = null;
    }

    void reset()
    {
        cancelAllBrowseSearch();
        clearServerCache();
    }

    public int[] search(String s, String s1, String as[], String s2, boolean flag)
    {
        checkDLNAorThrow();
        if (as != null) goto _L2; else goto _L1
_L1:
        int ai[] = null;
_L4:
        return ai;
_L2:
        int ai1[];
        int i;
        ai = new int[as.length];
        Arrays.fill(ai, -1);
        ai1 = new int[1];
        i = 0;
_L7:
        if (i >= as.length) goto _L4; else goto _L3
_L3:
        if (as[i] != null) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
_L6:
        String s3;
        s3 = as[i];
        if (flag)
        {
            s3 = (new StringBuilder()).append(s3).append(" and @refID exists false").toString();
        }
        if (DLNA.JNI_Search(mDLNA.mNativeUPnP, s, s1, s3, "*", 0, -1, s2, ai1) != 0) goto _L4; else goto _L8
_L8:
        ai[i] = ai1[0];
        addDirUpdateID(s, ai1[0]);
          goto _L5
    }

    public void unregisterContentUpdatedListener(IContentUpdatedListener icontentupdatedlistener)
    {
        synchronized (mDirContentListeners)
        {
            if (mDirContentListeners.contains(icontentupdatedlistener))
            {
                mDirContentListeners.remove(icontentupdatedlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void unregisterServerDLNAUploadListener(IServerDLNAUploadListener iserverdlnauploadlistener)
    {
        synchronized (mServerDLNAUploadListeners)
        {
            if (mServerDLNAUploadListeners.contains(iserverdlnauploadlistener))
            {
                mServerDLNAUploadListeners.remove(iserverdlnauploadlistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void unregisterServerStatusListener(IServerStatusListener iserverstatuslistener)
    {
        synchronized (mServerStatusListeners)
        {
            if (mServerStatusListeners.contains(iserverstatuslistener))
            {
                mServerStatusListeners.remove(iserverstatuslistener);
            }
        }
        return;
        exception;
        list;
        JVM INSTR monitorexit ;
        throw exception;
    }

    static 
    {
        ICON_DOWNLOAD_PATH = (new StringBuilder()).append(OEMConfig.CACHE_BASE_PATH).append("icons/").toString();
    }











}
