// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.RenderManager;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.easytransfer.IEasyTransferEngine;
import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            DeviceIcon, IDlnaSettingCallback

public class DLNAService extends Service
{
    public class LocalBinder extends Binder
    {

        final DLNAService this$0;

        public DLNAService getService()
        {
            Log.v("DLNAService", "LocalBinder getService~~~");
            return DLNAService.this;
        }

        public LocalBinder()
        {
            this$0 = DLNAService.this;
            super();
        }
    }

    private class RenderStatusListener
        implements com.arcsoft.adk.atv.RenderManager.IRenderStatusListener
    {

        final DLNAService this$0;

        public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo, int i)
        {
        }

        public void onRenderAdded(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
        {
            if (mediarenderdesc != null)
            {
                onDmrOnlineCallback(mediarenderdesc.m_strUuid);
            }
        }

        public void onRenderInstalled(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc, boolean flag, boolean flag1, boolean flag2)
        {
        }

        public void onRenderRemoved(com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc)
        {
            if (mediarenderdesc != null)
            {
                onDmrOfflineCallback(mediarenderdesc.m_strUuid);
            }
        }

        private RenderStatusListener()
        {
            this$0 = DLNAService.this;
            super();
        }

    }

    private class ServerStatusListener
        implements com.arcsoft.adk.atv.ServerManager.IServerStatusListener
    {

        final DLNAService this$0;

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
            if (mediaserverdesc != null)
            {
                onDmsOnlineCallback(mediaserverdesc.m_strUuid);
            }
        }

        public void onServerMetaChanged(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
        }

        public void onServerRemoved(com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc)
        {
            if (mediaserverdesc != null)
            {
                onDmsOfflineCallback(mediaserverdesc.m_strUuid);
            }
        }

        private ServerStatusListener()
        {
            this$0 = DLNAService.this;
            super();
        }

    }


    public static final String LOCAL_ACTION = "ArcSoft.DLNA.UPDWONLOAD.SERVICE";
    private static final String LOG_TAG = "DLNAService";
    private boolean bIsConnectWifi;
    private final IDlnaSettingBinder.Stub mBinder = new IDlnaSettingBinder.Stub() {

        final DLNAService this$0;

        public boolean abortUploadAndDownloadTask(boolean flag)
            throws RemoteException
        {
            if (mUpDownloadEngine == null)
            {
                return false;
            }
            if (flag)
            {
                return mUpDownloadEngine.cancelAllTask();
            } else
            {
                return mUpDownloadEngine.abortAllTask();
            }
        }

        public String getActiveDmrUDN()
            throws RemoteException
        {
            createToken();
            return strSetting_DefaultUDNDMR;
        }

        public String getActiveDmsUDN()
            throws RemoteException
        {
            createToken();
            return strSetting_DefaultUDNDMS;
        }

        public String getDmrFriendlyName(String s)
            throws RemoteException
        {
            String s1 = GetDmrName(s);
            if (s1 == null && s != null && s.equalsIgnoreCase(strSetting_DefaultUDNDMR))
            {
                s1 = strSetting_DefaultNameDMR;
            }
            return s1;
        }

        public String getDmrManufacture(String s)
        {
            return GetDmrManufacture(s);
        }

        public String getDmrModelName(String s)
        {
            return GetDmrModelName(s);
        }

        public List getDmrUDNicon(String s)
        {
            return GetDmrUDNIcon(s);
        }

        public String getDmsFriendlyName(String s)
            throws RemoteException
        {
            String s1 = GetDmsName(s);
            if (s1 == null && s.equalsIgnoreCase(strSetting_DefaultUDNDMS))
            {
                s1 = strSetting_DefaultNameDMS;
            }
            return s1;
        }

        public String getDmsManufacture(String s)
        {
            return GetDmsManufacture(s);
        }

        public String getDmsModelName(String s)
        {
            return GetDmsModelName(s);
        }

        public List getDmsUDNicon(String s)
        {
            return GetDmsUDNIcon(s);
        }

        public boolean getOnlineContentsListviewMode()
            throws RemoteException
        {
            return Settings.instance().getOnlineContentsListviewMode();
        }

        public String[] getOnlineDmrUDN()
            throws RemoteException
        {
            createToken();
            return getOnlineDmrUDNArray();
        }

        public String[] getOnlineDmsUDN()
            throws RemoteException
        {
            createToken();
            return getOnlineDmsUDNArray();
        }

        public boolean getTelevisionStreamingResolution()
            throws RemoteException
        {
            return Settings.instance().isUseHDResource();
        }

        public int getUploadAndDownloadTaskCount()
            throws RemoteException
        {
            if (mUpDownloadEngine != null)
            {
                return mUpDownloadEngine.getAllTaskCount();
            } else
            {
                return 0;
            }
        }

        public boolean isDmrOnline(String s)
            throws RemoteException
        {
            createToken();
            return CheckDmrOnline(s);
        }

        public boolean isDmsOnline(String s)
            throws RemoteException
        {
            createToken();
            return CheckDmsOnline(s);
        }

        public boolean isResAdapttoActiveDMR(String s)
            throws RemoteException
        {
            while (strSetting_DefaultUDNDMR == null || !CheckDmrOnline(strSetting_DefaultUDNDMR)) 
            {
                return false;
            }
            return IsResAdapttoDMR(strSetting_DefaultUDNDMR, s);
        }

        public boolean isResAdapttoDMR(String s, String s1)
            throws RemoteException
        {
            return IsResAdapttoDMR(s, s1);
        }

        public void registerCallback(IDlnaSettingCallback idlnasettingcallback)
            throws RemoteException
        {
            if (idlnasettingcallback != null && mCallbacks != null)
            {
                mCallbacks.register(idlnasettingcallback);
                int i = this.access$300;
            }
        }

        public boolean resumeUploadAndDownloadTask()
            throws RemoteException
        {
            if (mUpDownloadEngine != null)
            {
                return mUpDownloadEngine.resumeAllTask();
            } else
            {
                return false;
            }
        }

        public boolean setActiveDmr(String s)
            throws RemoteException
        {
            createToken();
            if (s == null)
            {
                return false;
            }
            strSetting_DefaultUDNDMR = s;
            Settings.instance().setDefaultDMRUDN(strSetting_DefaultUDNDMR);
            strSetting_DefaultNameDMR = GetDmrName(s);
            if (strSetting_DefaultNameDMR == null)
            {
                strSetting_DefaultNameDMR = getString(0x7f0b001a);
            }
            Settings.instance().setDefaultDMRName(strSetting_DefaultNameDMR);
            return true;
        }

        public boolean setActiveDms(String s)
            throws RemoteException
        {
            createToken();
            if (s == null)
            {
                return false;
            }
            strSetting_DefaultUDNDMS = s;
            Settings.instance().setDefaultDMSUDN(strSetting_DefaultUDNDMS);
            strSetting_DefaultNameDMS = GetDmsName(s);
            if (strSetting_DefaultNameDMS == null)
            {
                strSetting_DefaultNameDMS = getString(0x7f0b001a);
            }
            Settings.instance().setDefaultDMSName(strSetting_DefaultNameDMS);
            return true;
        }

        public boolean setOnlineContentsListviewMode(boolean flag)
            throws RemoteException
        {
            return Settings.instance().setOnlineContentsListviewMode(flag);
        }

        public boolean setTelevisionStreamingResolution(boolean flag)
            throws RemoteException
        {
            return Settings.instance().setUseHDResource(flag);
        }

        public void unregisterCallback(IDlnaSettingCallback idlnasettingcallback)
            throws RemoteException
        {
            if (idlnasettingcallback != null && mCallbacks != null)
            {
                mCallbacks.unregister(idlnasettingcallback);
                int i = this.access$300;
            }
        }

            
            {
                this$0 = DLNAService.this;
                super();
            }
    };
    private final RemoteCallbackList mCallbacks = new RemoteCallbackList();
    private int mClientCount;
    Handler mDelayRleleaseTokenTimer;
    private DLNA mDlnaInst;
    private final LocalBinder mLocalBinder = new LocalBinder();
    com.arcsoft.adk.atv.DLNA.UserToken mToken;
    private UpDownloadEngine mUpDownloadEngine;
    private String strIPAddress;
    private String strSetting_DefaultNameDMR;
    private String strSetting_DefaultNameDMS;
    private String strSetting_DefaultUDNDMR;
    private String strSetting_DefaultUDNDMS;

    public DLNAService()
    {
        mClientCount = 0;
        bIsConnectWifi = false;
        strIPAddress = null;
        mDlnaInst = null;
        strSetting_DefaultUDNDMS = null;
        strSetting_DefaultNameDMS = null;
        strSetting_DefaultUDNDMR = null;
        strSetting_DefaultNameDMR = null;
        mToken = null;
        mUpDownloadEngine = null;
        mDelayRleleaseTokenTimer = new Handler() {

            final DLNAService this$0;

            public void handleMessage(Message message)
            {
                if (mToken != null)
                {
                    Log.w("DLNA Service", "Release Token in service");
                    DLNA.instance().releaseUserToken(mToken);
                    mToken = null;
                }
            }

            
            {
                this$0 = DLNAService.this;
                super();
            }
        };
    }

    private boolean CheckDmrOnline(String s)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return mDlnaInst.getRenderManager().isRenderOnline(s);
        }
    }

    private boolean CheckDmsOnline(String s)
    {
        if (s == null)
        {
            return false;
        } else
        {
            return mDlnaInst.getServerManager().isServerOnline(s);
        }
    }

    private String GetDmrManufacture(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc;
        if (s != null)
        {
            if ((mediarenderdesc = mDlnaInst.getRenderManager().getRenderDesc(s)) != null)
            {
                return mediarenderdesc.m_strManufacturer;
            }
        }
        return null;
    }

    private String GetDmrModelName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc;
        if (s != null)
        {
            if ((mediarenderdesc = mDlnaInst.getRenderManager().getRenderDesc(s)) != null)
            {
                return mediarenderdesc.m_strModelName;
            }
        }
        return null;
    }

    private String GetDmrName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc;
        if (s != null)
        {
            if ((mediarenderdesc = mDlnaInst.getRenderManager().getRenderDesc(s)) != null)
            {
                return mediarenderdesc.m_strFriendlyName;
            }
        }
        return null;
    }

    private List GetDmrUDNIcon(String s)
    {
        if (s != null)
        {
            com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc = mDlnaInst.getRenderManager().getRenderDesc(s);
            List list = null;
            if (mediarenderdesc != null)
            {
                list = mediarenderdesc.m_DeviceIconList;
            }
            if (list != null)
            {
                return getDeviceIcon(list);
            }
        }
        return null;
    }

    private String GetDmsManufacture(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
        if (s != null)
        {
            if ((mediaserverdesc = mDlnaInst.getServerManager().getServerDesc(s)) != null)
            {
                return mediaserverdesc.m_strManufacturer;
            }
        }
        return null;
    }

    private String GetDmsModelName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
        if (s != null)
        {
            if ((mediaserverdesc = mDlnaInst.getServerManager().getServerDesc(s)) != null)
            {
                return mediaserverdesc.m_strModelName;
            }
        }
        return null;
    }

    private String GetDmsName(String s)
    {
        com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc;
        if (s != null)
        {
            if ((mediaserverdesc = mDlnaInst.getServerManager().getServerDesc(s)) != null)
            {
                return mediaserverdesc.m_strFriendlyName;
            }
        }
        return null;
    }

    private List GetDmsUDNIcon(String s)
    {
        if (s != null)
        {
            com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc = mDlnaInst.getServerManager().getServerDesc(s);
            List list = null;
            if (mediaserverdesc != null)
            {
                list = mediaserverdesc.m_DeviceIconList;
            }
            if (list != null)
            {
                return getDeviceIcon(list);
            }
        }
        return null;
    }

    private boolean IsResAdapttoDMR(String s, String s1)
    {
        if (s != null && s1 != null) goto _L2; else goto _L1
_L1:
        com.arcsoft.adk.atv.MetaDataParser.MetaDataRes ametadatares[];
        return false;
_L2:
        com.arcsoft.adk.atv.MRCPCallback.DataOnGetProtocolInfo dataongetprotocolinfo;
        if ((ametadatares = com.arcsoft.adk.atv.MetaDataParser.MetaDataRes.parse(s1)) != null && ametadatares.length > 0 && (dataongetprotocolinfo = mDlnaInst.getRenderManager().getRenderProtocolInfo(s)) != null)
        {
            int i = 0;
            while (i < ametadatares.length) 
            {
                com.arcsoft.adk.atv.MetaDataParser.MetaDataRes metadatares = ametadatares[i];
                for (int j = 0; j < dataongetprotocolinfo.m_SinkValues.length; j++)
                {
                    if (DLNA.isProtocolMatched(dataongetprotocolinfo.m_SinkValues[j], metadatares.strProtocolInfo))
                    {
                        return true;
                    }
                }

                i++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    private void createToken()
    {
        if (mToken == null)
        {
            Log.w("DLNA Service", "Create Token in service");
            mToken = DLNA.instance().lockUserToken();
            mDelayRleleaseTokenTimer.sendEmptyMessageDelayed(0, 30000L);
            return;
        } else
        {
            mDelayRleleaseTokenTimer.removeMessages(0);
            mDelayRleleaseTokenTimer.sendEmptyMessageDelayed(0, 30000L);
            return;
        }
    }

    private List getDeviceIcon(List list)
    {
        Object obj;
        if (list == null || list.size() <= 0)
        {
            obj = null;
        } else
        {
            obj = new ArrayList();
            int i = list.size();
            int j = 0;
            while (j < i) 
            {
                com.arcsoft.adk.atv.UPnP.DeviceIcon deviceicon = (com.arcsoft.adk.atv.UPnP.DeviceIcon)list.get(j);
                if (deviceicon != null)
                {
                    DeviceIcon deviceicon1 = new DeviceIcon();
                    deviceicon1.m_strMimeType = deviceicon.m_strMimeType;
                    deviceicon1.m_lWidth = deviceicon.m_lWidth;
                    deviceicon1.m_lHeight = deviceicon.m_lHeight;
                    deviceicon1.m_lDepth = deviceicon.m_lDepth;
                    deviceicon1.m_strUrl = deviceicon.m_strUrl;
                    ((List) (obj)).add(deviceicon1);
                }
                j++;
            }
        }
        return ((List) (obj));
    }

    private String[] getOnlineDmrUDNArray()
    {
        List list = mDlnaInst.getRenderManager().getRenderList();
        String as[];
        if (list == null || list.size() <= 0)
        {
            as = null;
        } else
        {
            int i = list.size();
            as = new String[i];
            int j = 0;
            while (j < i) 
            {
                com.arcsoft.adk.atv.UPnP.MediaRenderDesc mediarenderdesc = (com.arcsoft.adk.atv.UPnP.MediaRenderDesc)list.get(j);
                if (mediarenderdesc != null)
                {
                    as[j] = mediarenderdesc.m_strUuid;
                } else
                {
                    as[j] = " ";
                }
                j++;
            }
        }
        return as;
    }

    private String[] getOnlineDmsUDNArray()
    {
        List list = mDlnaInst.getServerManager().getMediaServerList();
        String as[];
        if (list == null || list.size() <= 0)
        {
            as = null;
        } else
        {
            int i = list.size();
            as = new String[i];
            int j = 0;
            while (j < i) 
            {
                com.arcsoft.adk.atv.UPnP.MediaServerDesc mediaserverdesc = (com.arcsoft.adk.atv.UPnP.MediaServerDesc)list.get(j);
                if (mediaserverdesc != null)
                {
                    as[j] = mediaserverdesc.m_strUuid;
                } else
                {
                    as[j] = " ";
                }
                j++;
            }
        }
        return as;
    }

    private void onDmrOfflineCallback(String s)
    {
        int i = mCallbacks.beginBroadcast();
        int j = 0;
        while (j < i) 
        {
            try
            {
                ((IDlnaSettingCallback)mCallbacks.getBroadcastItem(j)).onDmrOffline(s);
            }
            catch (RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
            }
            j++;
        }
        mCallbacks.finishBroadcast();
    }

    private void onDmrOnlineCallback(String s)
    {
        int i = mCallbacks.beginBroadcast();
        int j = 0;
        while (j < i) 
        {
            try
            {
                ((IDlnaSettingCallback)mCallbacks.getBroadcastItem(j)).onDmrOnline(s);
            }
            catch (RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
            }
            j++;
        }
        mCallbacks.finishBroadcast();
    }

    private void onDmsOfflineCallback(String s)
    {
        int i = mCallbacks.beginBroadcast();
        int j = 0;
        while (j < i) 
        {
            try
            {
                ((IDlnaSettingCallback)mCallbacks.getBroadcastItem(j)).onDmsOffline(s);
            }
            catch (RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
            }
            j++;
        }
        mCallbacks.finishBroadcast();
    }

    private void onDmsOnlineCallback(String s)
    {
        int i = mCallbacks.beginBroadcast();
        int j = 0;
        while (j < i) 
        {
            try
            {
                ((IDlnaSettingCallback)mCallbacks.getBroadcastItem(j)).onDmsOnline(s);
            }
            catch (RemoteException remoteexception)
            {
                remoteexception.printStackTrace();
            }
            j++;
        }
        mCallbacks.finishBroadcast();
    }

    public UpDownloadEngine getUpDownloadEngine()
    {
        return mUpDownloadEngine;
    }

    public IBinder onBind(Intent intent)
    {
        createToken();
        if ("ArcSoft.DLNA.UPDWONLOAD.SERVICE".equals(intent.getAction()))
        {
            return mLocalBinder;
        } else
        {
            return mBinder;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        mDlnaInst = DLNA.instance();
        strSetting_DefaultUDNDMS = Settings.instance().getDefaultDMSUDN();
        strSetting_DefaultNameDMS = Settings.instance().getDefaultDMSName();
        strSetting_DefaultUDNDMR = Settings.instance().getDefaultDMRUDN();
        strSetting_DefaultNameDMR = Settings.instance().getDefaultDMRName();
        strIPAddress = NetworkUtil.getLocalIpViaWiFi(this);
        if (strIPAddress != null)
        {
            bIsConnectWifi = true;
        }
        mDlnaInst = DLNA.instance();
        if (mDlnaInst != null && bIsConnectWifi)
        {
            mDlnaInst.getRenderManager().registerRenderStatusListener(new RenderStatusListener());
            mDlnaInst.getServerManager().registerServerStatusListener(new ServerStatusListener());
        }
        mUpDownloadEngine = new UpDownloadEngine();
        mUpDownloadEngine.onCreate(getApplication(), this);
        mUpDownloadEngine.controlSpeed(0, 1, 20);
        mUpDownloadEngine.controlSpeed(1, 1, 100);
        createToken();
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d("DLNAService", "onDestroy()");
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.onDestroy();
            mUpDownloadEngine = null;
        }
    }

    public int onStartCommand(Intent intent, int i, int j)
    {
        if (mUpDownloadEngine != null)
        {
            mUpDownloadEngine.start();
        }
        return super.onStartCommand(intent, i, j);
    }

    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }







/*
    static int access$1508(DLNAService dlnaservice)
    {
        int i = dlnaservice.mClientCount;
        dlnaservice.mClientCount = i + 1;
        return i;
    }

*/


/*
    static int access$1510(DLNAService dlnaservice)
    {
        int i = dlnaservice.mClientCount;
        dlnaservice.mClientCount = i - 1;
        return i;
    }

*/















/*
    static String access$302(DLNAService dlnaservice, String s)
    {
        dlnaservice.strSetting_DefaultUDNDMR = s;
        return s;
    }

*/



/*
    static String access$402(DLNAService dlnaservice, String s)
    {
        dlnaservice.strSetting_DefaultUDNDMS = s;
        return s;
    }

*/




/*
    static String access$602(DLNAService dlnaservice, String s)
    {
        dlnaservice.strSetting_DefaultNameDMR = s;
        return s;
    }

*/




/*
    static String access$802(DLNAService dlnaservice, String s)
    {
        dlnaservice.strSetting_DefaultNameDMS = s;
        return s;
    }

*/

}
