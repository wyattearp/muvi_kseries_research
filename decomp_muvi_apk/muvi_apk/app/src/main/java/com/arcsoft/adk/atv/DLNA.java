// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.app.Application;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Looper;
import com.arcsoft.platform.AMCM;
import com.arcsoft.platform.MPTimer;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import com.arcsoft.util.network.WifiMulticast;
import com.arcsoft.util.os.NetworkTool;
import com.arcsoft.util.os.ScreenTool;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            UPnP, CallbackThreadBridge, DeviceInfoCache, ServerManager, 
//            RenderManager, UploadManager

public class DLNA extends UPnP
{
    public static interface IDlnaFileServer
    {

        public abstract void closeFileServerFailed();

        public abstract void closeFileServerSucess();

        public abstract void enableFileServerSuccess();

        public abstract void enalbleFileServerFailed();

        public abstract void fileServerDismiss();
    }

    public static interface IOnDLNAStatusChangeListener
    {

        public abstract void OnDLNAConnected();

        public abstract void OnDLNADisconnected();

        public abstract void OnDLNAInternalError(int i);
    }

    private static class ResSortInfo
    {

        int ci;
        long dur;
        int index;
        int pn;
        long size;

        private ResSortInfo()
        {
            index = -1;
            size = 0L;
            dur = 0L;
            pn = 0;
            ci = -1;
        }

    }

    public class UserToken
    {

        long id;
        final DLNA this$0;

        public UserToken()
        {
            this$0 = DLNA.this;
            super();
        }
    }


    public static final int ERROR_BAD_INSTANCEID = 718;
    public static final int ERROR_BAD_PLAY_FORMAT = 704;
    public static final int ERROR_BAD_PLAY_MODE = 712;
    public static final int ERROR_BAD_PLAY_SPEED = 717;
    public static final int ERROR_BAD_RECORDING_FORMAT = 708;
    public static final int ERROR_BAD_SEEK_MODE = 710;
    public static final int ERROR_CONTENT_BUSY = 715;
    public static final int ERROR_DETERMINE_ALLOW_USES = 722;
    public static final int ERROR_DEVICE_AUTHENTICATION_FAILED = 724;
    public static final int ERROR_DEVICE_REVOCATION = 725;
    public static final int ERROR_DRM_ERROR = 719;
    public static final int ERROR_EXHAUSTED_ALLOW_USES = 723;
    public static final int ERROR_EXPIRED_CONTENT = 720;
    public static final int ERROR_ILLEAGAL_MIMETYPE = 714;
    public static final int ERROR_ILLEGAL_SEEK_TARGET = 711;
    public static final int ERROR_MEDIA_FULL = 709;
    public static final int ERROR_MEDIA_PROTECETED = 707;
    public static final int ERROR_NONALLOWED_USE = 721;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NO_CONTENT = 702;
    public static final int ERROR_READ_FAILED = 703;
    public static final int ERROR_RECORDING_QUALITY = 713;
    public static final int ERROR_RESOURCE_NOT_FOUND = 716;
    public static final int ERROR_TRANSITION_NOT_AVAILABLE = 701;
    public static final int ERROR_TRANSPORT_LOCKED = 705;
    public static final int ERROR_WRITE_FAILED = 706;
    private static DLNA mInstance = null;
    private static Comparator sDefaultComparator = new Comparator() {

        public int compare(ResSortInfo ressortinfo, ResSortInfo ressortinfo1)
        {
            byte byte0 = -1;
            long l = ressortinfo.ci - ressortinfo1.ci;
            if (l != 0L)
            {
                if (l > 0L)
                {
                    return 1;
                } else
                {
                    return byte0;
                }
            }
            long l1 = ressortinfo.size - ressortinfo1.size;
            if (l1 != 0L)
            {
                if (l1 <= 0L)
                {
                    byte0 = 1;
                }
                return byte0;
            } else
            {
                return 0;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ResSortInfo)obj, (ResSortInfo)obj1);
        }

    };
    private static Comparator s_PN_Comparator = new Comparator() {

        public int compare(ResSortInfo ressortinfo, ResSortInfo ressortinfo1)
        {
            byte byte0;
            byte byte1;
            long l;
            byte0 = 1;
            byte1 = -1;
            l = ressortinfo.ci - ressortinfo1.ci;
            if (l == 0L) goto _L2; else goto _L1
_L1:
            if (l <= 0L)
            {
                byte0 = byte1;
            }
            byte1 = byte0;
_L4:
            return byte1;
_L2:
            long l1 = ressortinfo.pn - ressortinfo1.pn;
            if (l1 == 0L)
            {
                break; /* Loop/switch isn't completed */
            }
            if (l1 <= 0L)
            {
                return byte0;
            }
            if (true) goto _L4; else goto _L3
_L3:
            long l2 = ressortinfo.size - ressortinfo1.size;
            if (l2 != 0L)
            {
                if (l2 <= 0L)
                {
                    return byte0;
                }
            } else
            {
                return 0;
            }
            if (true) goto _L4; else goto _L5
_L5:
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ResSortInfo)obj, (ResSortInfo)obj1);
        }

    };
    private static Comparator s_Size_Dur_Comparator = new Comparator() {

        public int compare(ResSortInfo ressortinfo, ResSortInfo ressortinfo1)
        {
            byte byte0;
            byte byte1;
            long l;
            byte0 = 1;
            byte1 = -1;
            l = ressortinfo.ci - ressortinfo1.ci;
            if (l == 0L) goto _L2; else goto _L1
_L1:
            if (l <= 0L)
            {
                byte0 = byte1;
            }
            byte1 = byte0;
_L4:
            return byte1;
_L2:
            long l1 = ressortinfo.dur - ressortinfo1.dur;
            if (l1 == 0L)
            {
                break; /* Loop/switch isn't completed */
            }
            if (l1 <= 0L)
            {
                return byte0;
            }
            if (true) goto _L4; else goto _L3
_L3:
            long l2 = ressortinfo.size - ressortinfo1.size;
            if (l2 != 0L)
            {
                if (l2 <= 0L)
                {
                    return byte0;
                }
            } else
            {
                return 0;
            }
            if (true) goto _L4; else goto _L5
_L5:
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ResSortInfo)obj, (ResSortInfo)obj1);
        }

    };
    private boolean bEnableDMS;
    private final boolean bEnablePU = false;
    protected Application mApplication;
    private CallbackThreadBridge mCallbackBridge;
    private final ArrayList mCurrentUsers = new ArrayList();
    private DeviceInfoCache mDeviceInfoCache;
    private boolean mFileServerAlready;
    private IDlnaFileServer mFileServerListener;
    int mNativeUPnP;
    private final com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener mNetworkConnectivityListener = new com.arcsoft.util.os.NetworkTool.IOnConnectivityChangeListener() {

        private boolean bFirst;
        final DLNA this$0;

        public void OnConnectivityChanged(com.arcsoft.util.os.NetworkTool.NetworkStateInfo networkstateinfo)
        {
            if (networkstateinfo.networkInfo != null && networkstateinfo.networkInfo.getType() == 1)
            {
                if (bFirst)
                {
                    bFirst = false;
                    return;
                }
                Log.d("UPNP", networkstateinfo.networkInfo.toString());
                android.net.NetworkInfo.State state = networkstateinfo.networkInfo.getState();
                if (state == android.net.NetworkInfo.State.CONNECTED)
                {
                    Log.d("UPNP", "DLNA NetworkInfo.State.CONNECTED  uninitUpnp");
                    uninitUpnp();
                    mRenderMgr.reset();
                    mServerMgr.reset();
                    Log.d("UPNP", "DLNA NetworkInfo.State.CONNECTED  initUpnp");
                    initUpnp();
                    if (mCallbackBridge != null)
                    {
                        mCallbackBridge.post(new Runnable() {

                            final _cls5 this$1;

                            public void run()
                            {
                                notifyDLNAConnectStatus(true);
                            }

            
            {
                this$1 = _cls5.this;
                super();
            }
                        }, 0L);
                        return;
                    } else
                    {
                        notifyDLNAConnectStatus(true);
                        return;
                    }
                }
                if (state == android.net.NetworkInfo.State.DISCONNECTED)
                {
                    if (mCallbackBridge != null)
                    {
                        mCallbackBridge.post(new Runnable() {

                            final _cls5 this$1;

                            public void run()
                            {
                                notifyDLNAConnectStatus(false);
                            }

            
            {
                this$1 = _cls5.this;
                super();
            }
                        }, 0L);
                    } else
                    {
                        notifyDLNAConnectStatus(false);
                    }
                    Log.d("UPNP", "DLNA NetworkInfo.State.DISCONNECTED uninitUpnp");
                    uninitUpnp();
                    return;
                }
            }
        }

            
            {
                this$0 = DLNA.this;
                super();
                bFirst = true;
            }
    };
    private final com.arcsoft.util.os.NetworkTool.IOnSettingChangeListener mNetworkSettingListener = new com.arcsoft.util.os.NetworkTool.IOnSettingChangeListener() {

        final DLNA this$0;

        public void OnBackgroundDataSettingChanged()
        {
            Log.e("Network", "OnBackgroundDataSettingChanged");
        }

            
            {
                this$0 = DLNA.this;
                super();
            }
    };
    private NetworkTool mNetworkTool;
    private RenderManager mRenderMgr;
    private ServerManager mServerMgr;
    private final ArrayList mStatusListeners = new ArrayList();
    private UploadManager mUploadMgr;
    private WifiMulticast mWifiMulticast;
    private AMCM m_CMgr;
    private int nRefFileServer;

    protected DLNA(Application application, Looper looper)
    {
        mFileServerListener = null;
        mFileServerAlready = false;
        m_CMgr = null;
        mCallbackBridge = null;
        mDeviceInfoCache = null;
        mApplication = null;
        mWifiMulticast = null;
        mServerMgr = null;
        mRenderMgr = null;
        mUploadMgr = null;
        bEnableDMS = false;
        nRefFileServer = 0;
        mNetworkTool = null;
        mNativeUPnP = 0;
        mApplication = application;
        mNetworkTool = new NetworkTool(application);
        if (looper != null)
        {
            mCallbackBridge = new CallbackThreadBridge(looper);
        }
    }

    private static int getCIValueFromProtocol(String s)
    {
        byte byte0;
        if (s == null)
        {
            byte0 = -1;
        } else
        {
            byte0 = -1;
            int i = s.indexOf("DLNA.ORG_CI=");
            if (i >= 0)
            {
                int j = i + "DLNA.ORG_CI=".length();
                int k = s.indexOf(';', j);
                if (k > 0)
                {
                    int l;
                    try
                    {
                        l = Integer.decode(s.substring(j, k)).intValue();
                    }
                    catch (Exception exception)
                    {
                        return -1;
                    }
                    return l;
                }
            }
        }
        return byte0;
    }

    private IOnDLNAStatusChangeListener[] getDLNAStatusListenersCopy()
    {
        ArrayList arraylist = mStatusListeners;
        arraylist;
        JVM INSTR monitorenter ;
        int i = mStatusListeners.size();
        IOnDLNAStatusChangeListener aiondlnastatuschangelistener[];
        aiondlnastatuschangelistener = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        IOnDLNAStatusChangeListener aiondlnastatuschangelistener1[] = new IOnDLNAStatusChangeListener[mStatusListeners.size()];
        aiondlnastatuschangelistener = (IOnDLNAStatusChangeListener[])mStatusListeners.toArray(aiondlnastatuschangelistener1);
        arraylist;
        JVM INSTR monitorexit ;
        return aiondlnastatuschangelistener;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static String getErrorDescription(int i)
    {
        switch (i)
        {
        default:
            return "unknow upnp error!";

        case 701: 
            return "Transition not available";

        case 702: 
            return "No contents";

        case 703: 
            return "Read error";

        case 704: 
            return "Format not supported for playback";

        case 705: 
            return "Transport is locked";

        case 706: 
            return "Write error";

        case 707: 
            return "Media is protected or not writable";

        case 708: 
            return "Format not supported for recording";

        case 709: 
            return "Media is full";

        case 710: 
            return "Seek mode not supported";

        case 711: 
            return "Illegal seek target";

        case 712: 
            return "Play mode not supported";

        case 713: 
            return "Record quality not supported";

        case 714: 
            return "Illegal MIME-type";

        case 715: 
            return "Content \u2018BUSY\u2019";

        case 716: 
            return "Resource not found";

        case 717: 
            return "Play speed not supported";

        case 718: 
            return "Invalid InstanceID";

        case 719: 
            return "DRM error";

        case 720: 
            return "Expired content";

        case 721: 
            return "Non-allowed use";

        case 722: 
            return "Cannot determine allowed uses";

        case 723: 
            return "Exhausted allowed use";

        case 724: 
            return "Device authentication failure";

        case 725: 
            return "Device revocation";
        }
    }

    private static int getPNValueFromProtocol(String s)
    {
        byte byte0;
        if (s == null)
        {
            byte0 = -1;
        } else
        {
            byte0 = -1;
            int i = s.indexOf("DLNA.ORG_PN=");
            if (i >= 0)
            {
                int j = s.indexOf(';', s.indexOf('_', i + "DLNA.ORG_PN=".length()));
                char c;
                if (j > 0)
                {
                    c = s.charAt(j - 1);
                } else
                {
                    c = '\0';
                }
                switch (c)
                {
                default:
                    return byte0;

                case 68: // 'D'
                    return 3;

                case 78: // 'N'
                    return 1;

                case 77: // 'M'
                    return 2;

                case 71: // 'G'
                    return 4;
                }
            }
        }
        return byte0;
    }

    public static int[] getSortedResources(int i, List list)
    {
        Comparator comparator;
        boolean flag;
        comparator = sDefaultComparator;
        flag = false;
        i;
        JVM INSTR tableswitch 1 3: default 32
    //                   1 60
    //                   2 60
    //                   3 51;
           goto _L1 _L2 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_60;
_L4:
        int ai[];
        if (list == null || list.size() <= 0)
        {
            ai = null;
        } else
        {
            ArrayList arraylist = new ArrayList();
            ai = new int[list.size()];
            for (int j = 0; j < list.size(); j++)
            {
                ai[j] = j;
                UPnP.PresentItem_Resource presentitem_resource = (UPnP.PresentItem_Resource)list.get(j);
                ResSortInfo ressortinfo = new ResSortInfo();
                ressortinfo.index = j;
                ressortinfo.size = presentitem_resource.m_lSize;
                ressortinfo.dur = presentitem_resource.m_lDuration;
                ressortinfo.ci = getCIValueFromProtocol(presentitem_resource.m_strProtocolInfo);
                if (flag)
                {
                    ressortinfo.pn = getPNValueFromProtocol(presentitem_resource.m_strProtocolInfo);
                }
                arraylist.add(ressortinfo);
            }

            Collections.sort(arraylist, comparator);
            int k = 0;
            while (k < ai.length) 
            {
                ai[k] = ((ResSortInfo)arraylist.get(k)).index;
                k++;
            }
        }
        return ai;
_L3:
        comparator = s_PN_Comparator;
        flag = true;
          goto _L4
        comparator = s_Size_Dur_Comparator;
        flag = false;
          goto _L4
    }

    private void init()
    {
        m_CMgr = AMCM.CreateAMCM();
        if (m_CMgr == null)
        {
            throw new UnknownError("Can not initialize ArcSoft Component Manager");
        } else
        {
            mDeviceInfoCache = new DeviceInfoCache(mApplication);
            mServerMgr = new ServerManager(mApplication, this);
            mRenderMgr = new RenderManager(mApplication, this);
            mUploadMgr = new UploadManager(mApplication, this);
            initUpnp();
            mNetworkTool.setOnSettingChangeListener(mNetworkSettingListener);
            mNetworkTool.setOnConnectivityChangeListener(mNetworkConnectivityListener);
            return;
        }
    }

    public static void initSingleton(Application application, Looper looper)
    {
        if (mInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            mInstance = new DLNA(application, looper);
            mInstance.init();
            return;
        }
    }

    private void initUpnp()
    {
        mWifiMulticast = new WifiMulticast(mApplication);
        mWifiMulticast.Lock();
        String s = NetworkUtil.getLocalIpViaWiFi(mApplication);
        if (s == null)
        {
            return;
        }
        if (mNativeUPnP != 0)
        {
            Log.d("UPNP", "initUpnp return 0");
            return;
        }
        mNativeUPnP = JNI_CreateUPnP(mInstance.m_CMgr);
        Log.d("UPNP", (new StringBuilder()).append("initUpnp JNI_CreateUPnP mNativeUPnP = ").append(mNativeUPnP).toString());
        if (mNativeUPnP == 0)
        {
            throw new UnknownError("Can not initialize DLNA");
        }
        UPnP.UPnPInitParam upnpinitparam = new UPnP.UPnPInitParam();
        upnpinitparam.m_strLogPath = "";
        upnpinitparam.m_RenderCallback = mRenderMgr.getProxyCallback();
        upnpinitparam.m_ServerCallback = mServerMgr.getProxyCallback();
        upnpinitparam.m_UpCPCallback = mUploadMgr.getProxyCallback();
        upnpinitparam.m_strCallbackIP = s;
        upnpinitparam.m_iPort = 4869;
        if ((new File(Environment.getExternalStorageDirectory(), "logcat.txt")).exists())
        {
            upnpinitparam.m_nLogLevel = 0;
        } else
        {
            upnpinitparam.m_nLogLevel = 6;
        }
        if (mCallbackBridge != null)
        {
            mCallbackBridge.RedirectCallbacks(upnpinitparam);
        }
        if (JNI_InitStack(mInstance.mNativeUPnP, upnpinitparam) != 0)
        {
            Log.d("UPNP", "initUpnp  destroy JNI_DestroyStack");
            mFileServerAlready = false;
            JNI_DestroyStack(mInstance.mNativeUPnP);
            uninit();
            throw new UnknownError("Can not initialize DLNA");
        }
        boolean flag = EnableMRCP(true);
        boolean flag1 = EnableMSCP(true);
        if (!flag || !flag1)
        {
            try
            {
                Thread.sleep(1000L);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            EnableMRCP(true);
            EnableMSCP(true);
        }
        EnableUploader(true);
        Log.d("UPNP", "setFileServerEnable : DLNA");
        setFileServerEnable(true);
        Log.d("UPNP", (new StringBuilder()).append("initUpnp(), mNativeUPnP = ").append(mNativeUPnP).toString());
    }

    public static DLNA instance()
    {
        if (mInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return mInstance;
        }
    }

    public static boolean isMpeg(String s)
    {
        int i;
        int j;
        if (s != null)
        {
            if ((i = s.indexOf("DLNA.ORG_PN=")) > -1 && (j = s.indexOf(';', i)) > i)
            {
                String s1 = s.substring(i + "DLNA.ORG_PN=".length(), j);
                Log.d("UPNP", (new StringBuilder()).append("DLNA.ORG_PN=").append(s1).toString());
                if (s1.contains("MPEG_"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isProtocolMatched(String s, String s1)
    {
        String as[];
        String as1[];
        if (s == null || s1 == null)
        {
            return false;
        }
        as = s.replace("&quot;", "\"").split(":");
        as1 = s1.replace("&quot;", "\"").split(":");
        if (as.length < 4 || as1.length < 4)
        {
            return false;
        }
        boolean flag;
        if (as[0].compareToIgnoreCase(as1[0]) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (as[1].compareToIgnoreCase("*") != 0 && as1[1].compareToIgnoreCase("*") != 0)
            {
                if (as[1].compareToIgnoreCase(as1[1]) == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            } else
            {
                flag = true;
            }
        }
        if (!flag)
        {
            return false;
        }
        if (as[2].contains("application/")) goto _L2; else goto _L1
_L1:
        String s5;
        String s6;
        s5 = as[2];
        s6 = as1[2];
        if (!as[2].contains("x-wav") && !as[2].contains("X-wav")) goto _L4; else goto _L3
_L3:
        String as4[];
        int k;
        as4 = as[2].split(";");
        k = 0;
_L7:
        String as5[];
        int l;
        if (k < as4.length)
        {
            if (as4[k].contains("video/"))
            {
                s5 = as4[k];
            } else
            if (as4[k].contains("audio/"))
            {
                s5 = as4[k];
            } else
            {
label0:
                {
                    if (!as4[k].contains("image/"))
                    {
                        break label0;
                    }
                    s5 = as4[k];
                }
            }
        }
_L13:
        if (s5 != null && s5.contains("wav"))
        {
            s5 = s5.replace("x-", "").replace("X-", "");
        }
_L4:
        if (!as1[2].contains("x-wav") && !as1[2].contains("X-wav")) goto _L6; else goto _L5
_L5:
        as5 = as1[2].split(";");
        l = 0;
_L8:
        if (l < as5.length)
        {
            if (as5[l].contains("video/"))
            {
                s6 = as5[l];
            } else
            if (as5[l].contains("audio/"))
            {
                s6 = as5[l];
            } else
            {
label1:
                {
                    if (!as5[l].contains("image/"))
                    {
                        break label1;
                    }
                    s6 = as5[l];
                }
            }
        }
        if (s6 != null && s6.contains("wav"))
        {
            s6 = s6.replace("x-", "").replace("X-", "");
        }
_L6:
        if (s5 == null || s6 == null)
        {
            break MISSING_BLOCK_LABEL_542;
        }
        if (s5.length() == s6.length())
        {
            return s5.equalsIgnoreCase(s6);
        }
        break MISSING_BLOCK_LABEL_513;
        k++;
          goto _L7
        l++;
          goto _L8
        if (s5.length() > s6.length())
        {
            return s5.startsWith(s6);
        } else
        {
            return s6.startsWith(s5);
        }
          goto _L7
        return false;
_L2:
        if (!as[2].contains("video/")) goto _L10; else goto _L9
_L9:
        String s2;
        if (!as1[2].contains("video/"))
        {
            return false;
        }
        s2 = "video/";
_L12:
        String as2[];
        String as3[];
        as2 = as[2].split(s2);
        as3 = as1[2].split(s2);
        if (as3.length < 2 || as2.length < 2)
        {
            return false;
        }
        break MISSING_BLOCK_LABEL_695;
_L10:
        if (as[2].contains("audio/"))
        {
            if (!as1[2].contains("audio/"))
            {
                return false;
            }
            s2 = "audio/";
            continue; /* Loop/switch isn't completed */
        }
        if (!as[2].contains("image/"))
        {
            break; /* Loop/switch isn't completed */
        }
        if (!as1[2].contains("image/"))
        {
            return false;
        }
        s2 = "image/";
        if (true) goto _L12; else goto _L11
_L11:
        return as[2].compareToIgnoreCase(as1[2]) == 0;
        int i = as2[1].indexOf("\"");
        int j = as3[1].indexOf("\"");
        if (i != -1)
        {
            String s3 = as2[1].substring(0, i);
            if (j != -1)
            {
                String s4 = as3[1].substring(0, j);
                if (s3 != null && s4 != null)
                {
                    return s3.equalsIgnoreCase(s4);
                }
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
        return false;
          goto _L13
    }

    public static boolean matchLocalAudioProtocol(String s)
    {
        return true;
    }

    public static boolean matchLocalImageProtocol(String s)
    {
        return true;
    }

    public static boolean matchLocalVideoProtocol(String s)
    {
        return true;
    }

    private void notifyDLNAConnectStatus(boolean flag)
    {
        IOnDLNAStatusChangeListener aiondlnastatuschangelistener[] = getDLNAStatusListenersCopy();
        if (aiondlnastatuschangelistener != null)
        {
            int i = aiondlnastatuschangelistener.length;
            int j = 0;
            while (j < i) 
            {
                IOnDLNAStatusChangeListener iondlnastatuschangelistener = aiondlnastatuschangelistener[j];
                if (flag)
                {
                    iondlnastatuschangelistener.OnDLNAConnected();
                } else
                {
                    iondlnastatuschangelistener.OnDLNADisconnected();
                }
                j++;
            }
        }
    }

    private void notifyDLNAInternalError(int i)
    {
        IOnDLNAStatusChangeListener aiondlnastatuschangelistener[] = getDLNAStatusListenersCopy();
        if (aiondlnastatuschangelistener != null)
        {
            int j = aiondlnastatuschangelistener.length;
            int k = 0;
            while (k < j) 
            {
                aiondlnastatuschangelistener[k].OnDLNAInternalError(i);
                k++;
            }
        }
    }

    private void notifyFileServerStatusChanged(boolean flag, boolean flag1)
    {
        mFileServerAlready = false;
        if (!flag || !flag1) goto _L2; else goto _L1
_L1:
        mFileServerAlready = true;
        if (mFileServerListener != null)
        {
            mFileServerListener.enableFileServerSuccess();
        }
_L4:
        return;
_L2:
        if (!flag || flag1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mFileServerListener != null)
        {
            mFileServerListener.enalbleFileServerFailed();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (flag || !flag1)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mFileServerListener == null) goto _L4; else goto _L5
_L5:
        mFileServerListener.closeFileServerSucess();
        return;
        if (flag || flag1 || mFileServerListener == null) goto _L4; else goto _L6
_L6:
        mFileServerListener.closeFileServerFailed();
        return;
    }

    private void uninit()
    {
        mNetworkTool.setOnSettingChangeListener(null);
        mNetworkTool.setOnConnectivityChangeListener(null);
        uninitUpnp();
        mServerMgr.release();
        mRenderMgr.release();
        mUploadMgr.release();
        mServerMgr = null;
        mRenderMgr = null;
        mUploadMgr = null;
        mWifiMulticast.UnLock();
        mWifiMulticast.wifiUnLock(null);
        mWifiMulticast = null;
        mCallbackBridge = null;
        mDeviceInfoCache.release();
        if (m_CMgr != null)
        {
            m_CMgr.recycle();
            m_CMgr = null;
        }
    }

    public static void uninitSingleton()
    {
        if (mInstance == null)
        {
            throw new IllegalStateException("Not initialized.");
        } else
        {
            mInstance.uninit();
            mInstance = null;
            return;
        }
    }

    private void uninitUpnp()
    {
        if (mNativeUPnP != 0)
        {
            Log.d("UPNP", (new StringBuilder()).append("uninitUpnp(), JNI_DestroyStack mNativeUPnP = ").append(mNativeUPnP).toString());
            JNI_DestroyStack(mNativeUPnP);
            mNativeUPnP = 0;
            mFileServerAlready = false;
            if (mFileServerListener != null)
            {
                mFileServerListener.fileServerDismiss();
            }
        }
        Log.d("UPNP", (new StringBuilder()).append("uninitUpnp(), mNativeUPnP = ").append(mNativeUPnP).toString());
        mWifiMulticast.UnLock();
    }

    boolean EnableMRCP(boolean flag)
    {
        if (JNI_EnableMRCP(mNativeUPnP, flag) == 0)
        {
            if (!flag && mRenderMgr != null)
            {
                mRenderMgr.reset();
            }
            return true;
        } else
        {
            return false;
        }
    }

    boolean EnableMSCP(boolean flag)
    {
        if (JNI_EnableMSCP(mNativeUPnP, flag) == 0)
        {
            if (!flag && mServerMgr != null)
            {
                mServerMgr.reset();
            }
            return true;
        } else
        {
            return false;
        }
    }

    boolean EnableUploader(boolean flag)
    {
        if (JNI_EnableUploader(mNativeUPnP, flag) == 0)
        {
            if (!flag)
            {
                if (mUploadMgr == null);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public UPnP.RemoteItemDesc _sharpExtractMediaItem(String s)
    {
        return JNI_Sharp5906ExtractMediaItem(mNativeUPnP, s);
    }

    public boolean canDmcWork()
    {
        return mFileServerAlready;
    }

    DeviceInfoCache getCacheManager()
    {
        return mDeviceInfoCache;
    }

    public String getFileProtocolInfo(String s)
    {
        return JNI_GetFileProtocolInfo(mNativeUPnP, s);
    }

    public String getLocalMediaDidlData(String s)
    {
        return JNI_GetLocalMediaDidlData(mNativeUPnP, s);
    }

    public String getLocalPath(String s)
    {
        return JNI_GetLocalPath(mNativeUPnP, s);
    }

    public String getRemoteMediaDidlData(UPnP.RemoteItemDesc remoteitemdesc)
    {
        return JNI_GetRemoteMediaDidlData(mNativeUPnP, remoteitemdesc);
    }

    public RenderManager getRenderManager()
    {
        return mRenderMgr;
    }

    public ServerManager getServerManager()
    {
        return mServerMgr;
    }

    public UploadManager getUploadManager()
    {
        return mUploadMgr;
    }

    public String getUri(String s)
    {
        String s1 = JNI_GetUri(mNativeUPnP, s);
        Log.d("UPNP", (new StringBuilder()).append("native getUri szLocPath =  ").append(s).append(" res = ").append(s1).toString());
        return s1;
    }

    public int getUserToken()
    {
        return mCurrentUsers.size();
    }

    public UserToken lockUserToken()
    {
        UserToken usertoken = new UserToken();
        usertoken.id = System.currentTimeMillis();
        mCurrentUsers.add(usertoken);
        return usertoken;
    }

    void postRunnable(Runnable runnable, long l)
    {
        if (mCallbackBridge != null)
        {
            mCallbackBridge.post(runnable, l);
            return;
        } else
        {
            runnable.run();
            return;
        }
    }

    public void registerDLNAStatusListener(IOnDLNAStatusChangeListener iondlnastatuschangelistener)
    {
        synchronized (mStatusListeners)
        {
            if (!mStatusListeners.contains(iondlnastatuschangelistener))
            {
                mStatusListeners.add(iondlnastatuschangelistener);
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void registerDlnaFileServerListener(IDlnaFileServer idlnafileserver)
    {
        mFileServerListener = idlnafileserver;
    }

    public void releaseUserToken(UserToken usertoken)
    {
        if (mCurrentUsers.contains(usertoken))
        {
            mCurrentUsers.remove(usertoken);
        }
        if (mCurrentUsers.size() <= 0 && ScreenTool.instance().isScreenOff())
        {
            Log.w("DLNA Service", "All user idled and screen off, pause all timer");
            MPTimer.pause();
        }
    }

    public boolean setDMSEnable(boolean flag, UPnP.DeviceDesc devicedesc)
    {
        Log.d("UPNP", "setDMSEnable");
        if (bEnableDMS == flag)
        {
            Log.d("UPNP", "setDMSEnable return");
            return true;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        boolean flag1;
        if (JNI_EnableFileServer(mNativeUPnP, true) == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L4; else goto _L3
_L3:
        if (nRefFileServer == 0)
        {
            JNI_EnableFileServer(mNativeUPnP, false);
        }
        bEnableDMS = flag;
        return flag1;
_L4:
        if (JNI_EnableDMS(mNativeUPnP, true, devicedesc) == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            nRefFileServer = 1 + nRefFileServer;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        JNI_EnableDMS(mNativeUPnP, false, devicedesc);
        nRefFileServer = -1 + nRefFileServer;
        flag1 = true;
        if (true) goto _L3; else goto _L5
_L5:
    }

    public boolean setFileServerEnable(boolean flag)
    {
        boolean flag1 = true;
        if (mFileServerAlready)
        {
            Log.d("UPNP", (new StringBuilder()).append("setFileServerEnable return mFileServerAlready = ").append(mFileServerAlready).append(" bEnable = ").append(flag).toString());
            return flag1;
        }
        if (JNI_EnableFileServer(mNativeUPnP, flag) != 0)
        {
            flag1 = false;
        }
        notifyFileServerStatusChanged(flag1, flag);
        Log.d("UPNP", (new StringBuilder()).append(" setFileServerEnable\uFF1A bEnable = ").append(flag).append(" retVal = ").append(flag1).toString());
        return flag1;
    }

    public void unRegisterDlnaFileServerListener()
    {
        if (mFileServerListener != null)
        {
            mFileServerListener = null;
        }
    }

    public void unregisterDLNAStatusListener(IOnDLNAStatusChangeListener iondlnastatuschangelistener)
    {
        synchronized (mStatusListeners)
        {
            if (mStatusListeners.contains(iondlnastatuschangelistener))
            {
                mStatusListeners.remove(iondlnastatuschangelistener);
            }
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean validateBrowseReq(int i, boolean flag)
    {
        int j = mNativeUPnP;
        int k;
        if (flag)
        {
            k = 1;
        } else
        {
            k = 2;
        }
        return JNI_ValidateBrowseReq(j, i, k) == 0;
    }

    public void wifiLock(String s)
    {
        if (mWifiMulticast != null)
        {
            mWifiMulticast.wifiLock(s);
        }
    }

    public void wifiUnLock(String s)
    {
        if (mWifiMulticast != null)
        {
            mWifiMulticast.wifiUnLock(s);
        }
    }

    static 
    {
        SystemUtils.loadLibrary("m2tslib");
        SystemUtils.loadLibrary("platform");
        SystemUtils.loadLibrary("AES_infoEncrypt");
        SystemUtils.loadLibrary("cprm_recording");
        SystemUtils.loadLibrary("dmc");
    }






}
