// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.Bitmap;
import com.arcsoft.platform.AMCM;
import java.util.HashMap;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            BrowseCallback, MRCPCallback, MSCPCallback, UPCPCallback

public class UPnP
{
    public static class DeviceDesc
    {

        public Bitmap m_DeviceIcon;
        public List m_DeviceIconList;
        public String m_strFriendlyName;
        public String m_strManufacturer;
        public String m_strManufacturerUrl;
        public String m_strModelDescription;
        public String m_strModelName;
        public String m_strModelNumber;
        public String m_strModelUrl;
        public String m_strSerialNumber;
        public String m_strSrcIp;
        public String m_strUuid;
        public String m_strXIppltvCap;
        public String m_strXIppltvRecmode;
        public String m_strXIppltvVersion;

        public boolean equals(Object obj)
        {
            DeviceDesc devicedesc;
            if (obj instanceof DeviceDesc)
            {
                if (m_strUuid.equals((devicedesc = (DeviceDesc)obj).m_strUuid))
                {
                    return true;
                }
            }
            return false;
        }

        public DeviceDesc()
        {
            m_strUuid = null;
            m_strFriendlyName = null;
            m_strManufacturer = null;
            m_strManufacturerUrl = null;
            m_strModelName = null;
            m_strModelDescription = null;
            m_strModelNumber = null;
            m_strModelUrl = null;
            m_strSerialNumber = null;
            m_strSrcIp = null;
            m_strXIppltvVersion = null;
            m_strXIppltvRecmode = null;
            m_strXIppltvCap = null;
            m_DeviceIconList = null;
            m_DeviceIcon = null;
        }
    }

    public static class DeviceIcon
    {

        public long m_lDepth;
        public long m_lHeight;
        public long m_lWidth;
        public String m_strMimeType;
        public String m_strUrl;

        public DeviceIcon()
        {
            m_strMimeType = null;
            m_lWidth = 0L;
            m_lHeight = 0L;
            m_lDepth = 0L;
            m_strUrl = null;
        }
    }

    public static class MediaRenderDesc extends DeviceDesc
    {

        public MediaRenderDesc()
        {
        }
    }

    public static class MediaServerDesc extends DeviceDesc
    {

        public boolean m_bSupportUpload;

        public MediaServerDesc()
        {
            m_bSupportUpload = false;
        }
    }

    public static class PresentItem
    {

        public List m_AlbumArtUriList;
        public List m_ResourceList;
        public int m_bIppltvEnable;
        public int m_iItemClass;
        public int m_lChannelNr;
        public String m_strAlbum;
        public String m_strArtist;
        public String m_strChannelName;
        public String m_strCreator;
        public String m_strDate;
        public String m_strGenre;
        public String m_strPxnGroupId;
        public String m_strPxnGroupMemberNum;
        public String m_strPxnGroupTitle;
        public String m_strPxnGroupTopFlag;

        public PresentItem()
        {
        }
    }

    public static class PresentItem_AlbumArtUri
    {

        public String m_strAlbumArtUri;

        public PresentItem_AlbumArtUri()
        {
        }
    }

    public static class PresentItem_Resource
    {

        public long m_iPxnEasyTransfer;
        public long m_lBitrate;
        public long m_lBitsPerSample;
        public long m_lCleartextSize;
        public long m_lColorDepth;
        public long m_lDuration;
        public long m_lNbAudioChannels;
        public long m_lPxnCopyCount;
        public long m_lPxnResumePoint;
        public long m_lPxnVgaContentBitrate;
        public long m_lPxnVgaContentCleartextSize;
        public long m_lSampleFrequency;
        public long m_lSize;
        public long m_lVliPlayitemNum;
        public String m_strProtection;
        public String m_strProtocolInfo;
        public String m_strPxnChapterList;
        public String m_strPxnVgaContentProtocolInfo;
        public String m_strPxnVgaContentUri;
        public String m_strResolution;
        public String m_strUri;

        public PresentItem_Resource()
        {
        }
    }

    public static class ProtocolInfo
    {

        public String m_SinkValues;
        public int m_nSinks;
        public int m_nSources;
        public String m_strSourceValues;

        public ProtocolInfo()
        {
        }
    }

    public static class RemoteItemDesc
    {

        public PresentItem m_PresentItem;
        public long m_lChildCount;
        public long m_lProperty;
        public String m_strAribObjectType;
        public String m_strObjId;
        public String m_strParentId;
        public String m_strTitle;

        public boolean equals(Object obj)
        {
            RemoteItemDesc remoteitemdesc;
            if (obj instanceof RemoteItemDesc)
            {
                if ((remoteitemdesc = (RemoteItemDesc)obj).m_strObjId.equals(m_strObjId) && remoteitemdesc.m_strTitle.equals(m_strTitle))
                {
                    return true;
                }
            }
            return false;
        }

        public RemoteItemDesc()
        {
            m_strObjId = null;
            m_strParentId = null;
            m_strTitle = null;
            m_lProperty = 0L;
            m_lChildCount = 0L;
            m_PresentItem = null;
            m_strAribObjectType = null;
        }
    }

    public static class UPnPInitParam
    {

        public BrowseCallback m_BrowseCallback;
        public MRCPCallback m_RenderCallback;
        public MSCPCallback m_ServerCallback;
        public UPCPCallback m_UpCPCallback;
        public boolean m_bEnableDms;
        public boolean m_bEnableFileServer;
        public boolean m_bEnableMrcp;
        public boolean m_bEnableMscp;
        public boolean m_bEnableUploader;
        public int m_iPort;
        public int m_nLogLevel;
        public String m_strCallbackIP;
        public String m_strDmsName;
        public String m_strLogPath;

        public UPnPInitParam()
        {
            m_bEnableMrcp = false;
            m_bEnableMscp = false;
            m_bEnableFileServer = false;
            m_bEnableDms = false;
            m_bEnableUploader = false;
            m_strDmsName = null;
            m_ServerCallback = null;
            m_RenderCallback = null;
            m_UpCPCallback = null;
            m_BrowseCallback = null;
            m_strCallbackIP = null;
            m_strLogPath = null;
            m_iPort = 0;
            m_nLogLevel = 0;
        }
    }


    public static final int DIRECTORY_OBJECT = 2;
    public static final int ERROR_INVALID_PARAM = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_UNKNOWN = 1;
    public static final int ERROR_UNSUPPORTED = 3;
    public static final int INVALID_VALUE_INT = 0x7fffffff;
    public static final long INVALID_VALUE_LONG = 0x7fffffffffffffffL;
    public static final int ITEM_CLASS_AUDIO = 2;
    public static final int ITEM_CLASS_IMAGE = 3;
    public static final int ITEM_CLASS_UNKNOWN = 0;
    public static final int ITEM_CLASS_VIDEO = 1;
    public static final int ITEM_CLASS_VIDEO_BROADCAST = 4;
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 3;
    public static final int LOG_LEVEL_ERROR = 4;
    public static final int LOG_LEVEL_FATAL = 5;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_NO = 6;
    public static final int LOG_LEVEL_WARNING = 2;
    public static final int PRESENTED_OBJECT = 1;
    public static final int RUN_LEVEL_DORMANT = 1;
    public static final int RUN_LEVEL_NORMAL = 0;
    public static final int SEARCHABLE_OBJECT = 4;
    static final String TAG = "UPNP";
    public static final int UNKNOW_OBJECT = 0;
    public static final int UPLOAD_RATE_LEVEL_100KBPS = 2;
    public static final int UPLOAD_RATE_LEVEL_10KBPS = 1;
    public static final int UPLOAD_RATE_LEVEL_FULL = 0;
    public static final String UPNP_MEDIA_CI_0 = "DLNA.ORG_CI=0";
    public static final String UPNP_MEDIA_CI_1 = "DLNA.ORG_CI=1";
    public static HashMap s_mapProtocolInfo = new HashMap();
    public static HashMap s_mapServerProtocolInfo = new HashMap();

    public UPnP()
    {
    }

    protected static native int JNI_BrowseDirectory(int i, String s, String s1, String s2, int j, int k, String s3, boolean flag, 
            int ai[]);

    protected static native int JNI_CancelBrowseorSearch(int i, long l);

    protected static native int JNI_CreateUPnP(AMCM amcm);

    protected static native int JNI_DestroyObeject(int i, String s, String s1, int ai[]);

    protected static native void JNI_DestroyStack(int i);

    protected static native int JNI_DigaBrowseRecordTasks(int i, String s, String s1, String s2, int j, int k, String s3, int ai[]);

    protected static native int JNI_DigaCreateRecordSchedule(int i, String s, String s1, String s2, String s3, int j, String s4, String s5, 
            int ai[]);

    protected static native int JNI_DigaDeleteRecordSchedule(int i, String s, String s1, int ai[]);

    protected static native int JNI_DigaDisableRecordSchedule(int i, String s, String s1, int ai[]);

    protected static native int JNI_DigaEnableRecordSchedule(int i, String s, String s1, int ai[]);

    protected static native int JNI_DigaXP9eGetContainerIds(int i, String s, String s1, int ai[]);

    protected static native int JNI_EnableDMS(int i, boolean flag, DeviceDesc devicedesc);

    protected static native int JNI_EnableFileServer(int i, boolean flag);

    protected static native int JNI_EnableMRCP(int i, boolean flag);

    protected static native int JNI_EnableMSCP(int i, boolean flag);

    protected static native int JNI_EnableUploader(int i, boolean flag);

    protected static native int JNI_GetCurrentTransportActions(int i, String s, int j, long al[]);

    protected static native String JNI_GetFileProtocolInfo(int i, String s);

    protected static native String JNI_GetLocalMediaDidlData(int i, String s);

    protected static native String JNI_GetLocalPath(int i, String s);

    protected static native int JNI_GetMediaInfo(int i, String s, int j, long al[]);

    protected static native List JNI_GetMediaRenders(int i);

    protected static native List JNI_GetMediaServers(int i);

    protected static native int JNI_GetMute(int i, String s, int j, String s1, long al[]);

    protected static native int JNI_GetPositionInfo(int i, String s, int j, long al[]);

    protected static native int JNI_GetProtocolInfo(int i, String s, long al[]);

    protected static native String JNI_GetRemoteMediaDidlData(int i, RemoteItemDesc remoteitemdesc);

    protected static native int JNI_GetSearchCapabilities(int i, String s, int ai[]);

    protected static native int JNI_GetSortCapabilities(int i, String s, int ai[]);

    protected static native int JNI_GetTransportInfo(int i, String s, int j, long al[]);

    protected static native int JNI_GetTransportSettings(int i, String s, int j, long al[]);

    protected static native int JNI_GetUploaderProgress(int i, int j, int ai[], int ai1[]);

    protected static native String JNI_GetUri(int i, String s);

    protected static native int JNI_GetVolume(int i, String s, int j, String s1, long al[]);

    protected static native int JNI_InitStack(int i, UPnPInitParam upnpinitparam);

    protected static native int JNI_InstallRender(int i, String s, boolean aflag[]);

    protected static native boolean JNI_IsSupportUploader(int i, String s);

    protected static native int JNI_NextMedia(int i, String s, int j, long al[]);

    protected static native int JNI_PauseMedia(int i, String s, int j, long al[]);

    protected static native int JNI_PlayMedia(int i, String s, int j, String s1, long al[]);

    protected static native int JNI_PreviousMedia(int i, String s, int j, long al[]);

    protected static native int JNI_Search(int i, String s, String s1, String s2, String s3, int j, int k, String s4, 
            int ai[]);

    protected static native int JNI_SeekMedia(int i, String s, int j, String s1, String s2, long al[]);

    protected static native int JNI_Server_GetProtocolInfo(int i, String s, long al[]);

    protected static native int JNI_SetAVTransportURI(int i, String s, int j, String s1, String s2, long al[]);

    protected static native int JNI_SetAVTransportURI_EX(int i, String s, int j, String s1, RemoteItemDesc remoteitemdesc, long al[]);

    protected static native int JNI_SetMute(int i, String s, int j, String s1, boolean flag, long al[]);

    protected static native int JNI_SetRunLevel(int i, int j);

    protected static native int JNI_SetUploadRateLevel(int i, int j);

    protected static native int JNI_SetVolume(int i, String s, int j, String s1, int k, long al[]);

    protected static native RemoteItemDesc JNI_Sharp5906ExtractMediaItem(int i, String s);

    protected static native int JNI_Sharp5906PlayMedia(int i, String s, int j, String s1, String s2, String as[], long al[]);

    protected static native int JNI_Sharp5906StopMedia(int i, String s, int j, String as[], String s1, long al[]);

    protected static native int JNI_StopMedia(int i, String s, int j, long al[]);

    protected static native int JNI_UninstallRender(int i, String s);

    protected static native int JNI_UploadFile(int i, String s, String s1, String s2, String s3, String s4, int ai[]);

    protected static native int JNI_UploadM2TSFromMp4(int i, String s, String s1, String s2, String s3, int ai[]);

    protected static native int JNI_UploaderCancel(int i, int j);

    protected static native int JNI_ValidateBrowseReq(int i, int j, int k);

    protected static native int JNI_XGetDLNAUploadProfiles(int i, String s, int ai[]);

    public static boolean MatchProtocol(String s, String s1)
    {
        if (s == null || s1 == null)
        {
            return false;
        }
        String as[] = s.split(":");
        String as1[] = s1.replace("&quot;", "\"").split(":");
        if (as.length < 4 || as1.length < 4)
        {
            return false;
        }
        boolean flag;
        if (as[0].compareToIgnoreCase(as1[0]) == 0 && as[1].compareToIgnoreCase(as1[1]) == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        if (!as[2].contains("application/"))
        {
            return as[2].compareToIgnoreCase(as1[2]) == 0;
        }
        String s2;
        String as2[];
        String as3[];
        if (as[2].contains("video/"))
        {
            if (!as1[2].contains("video/"))
            {
                return false;
            }
            s2 = "video/";
        } else
        if (as[2].contains("audio/"))
        {
            if (!as1[2].contains("audio/"))
            {
                return false;
            }
            s2 = "audio/";
        } else
        if (as[2].contains("image/"))
        {
            if (!as1[2].contains("image/"))
            {
                return false;
            }
            s2 = "image/";
        } else
        {
            return false;
        }
        as2 = as[2].split(s2);
        as3 = as1[2].split(s2);
        if (as3.length < 2 || as2.length < 2)
        {
            return false;
        }
        int i = as3[1].indexOf("\"");
        int j = as2[1].indexOf("\"");
        if (i != -1)
        {
            String s3 = as3[1].substring(0, i);
            if (i != -1)
            {
                return s3.compareToIgnoreCase(as3[1].substring(0, j)) == 0;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public static String UPnPErrorCode2String(int i)
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
            return "Content \uFFFD\uFFFDBUSY\uFFFD\uFFFD";

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
            return "Can\uFFFD\uFFFDt determine allowed uses";

        case 723: 
            return "Exhausted allowed use";

        case 724: 
            return "Device authentication failure";

        case 725: 
            return "Device revocation";
        }
    }

}
