// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.oem;

import android.os.Environment;
import java.io.File;
import java.util.HashMap;

public class OEMConfig
{
    public static final class OEMName extends Enum
    {

        private static final OEMName $VALUES[];
        public static final OEMName GENERIC;

        public static OEMName valueOf(String s)
        {
            return (OEMName)Enum.valueOf(com/arcsoft/mediaplus/oem/OEMConfig$OEMName, s);
        }

        public static OEMName[] values()
        {
            return (OEMName[])$VALUES.clone();
        }

        static 
        {
            GENERIC = new OEMName("GENERIC", 0);
            OEMName aoemname[] = new OEMName[1];
            aoemname[0] = GENERIC;
            $VALUES = aoemname;
        }

        private OEMName(String s, int i)
        {
            super(s, i);
        }
    }


    public static final String ACTION_START_CHANNEL_LIST = "com.MUVI.start.mediaplus.broadcast";
    public static final String ACTION_START_LOCAL_LIST = "com.MUVI.start.mediaplus.local";
    public static final String ACTION_START_REMOTE_LIST = "com.MUVI.start.mediaplus.online";
    public static final String APP_DATA_PATH_INNER = "/data/data/com.MUVI.MediaPlus/";
    public static final String APP_PACKAGE_NAME = "com.MUVI.MediaPlus";
    public static final String AUTO_CONNECTION_DMS_NAME = "DMS for DV";
    public static final String BASE_PATH = (new StringBuilder()).append(Environment.getExternalStorageDirectory().getPath()).append("/Media+/MUVI/").toString();
    public static final String CACHE_BASE_PATH = (new StringBuilder()).append(BASE_PATH).append(".Cache/").toString();
    public static HashMap CHANNEL_DIR_ARIB;
    public static final String CLEAR_CACHE_ACTION = "com.MUVI.MediaPlus.clear.cache";
    public static final String DCIM_SRC = (new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/DCIM").toString();
    public static final com.arcsoft.adk.image.ThumbEngine.DecodeParam DECODEPARAMS[];
    public static boolean DEVICE_SUPPORT_MPEG2 = false;
    public static final String DLNA_SERVICE = "MUVI.MediaPlus.DLNA.SERVICE";
    public static final com.arcsoft.adk.image.ThumbEngine.DecodeParam DMC_DECODEPARAM;
    public static final com.arcsoft.adk.image.ThumbEngine.DecodeParam DMP_DECODEPARAM;
    public static final String DMS_NAME_AEE1 = "ArcSoft DMS for AEE";
    public static final String DMS_NAME_AEE2 = "DMS for DV";
    public static final String DMS_NAME_ARCSOFT_DMS = "ArcSoft DMS";
    public static final String DMS_NAME_DXG = "ArcSoft DMS for DXG";
    public static final String DMS_NAME_DXG_ACTIONCAM = "ActionCam";
    public static final String DMS_NAME_DXG_IRONX = "IRONX";
    public static final String DMS_NAME_SALIX1 = "ArcSoft DMS for Salix";
    public static final String DMS_NAME_SALIX2 = "Salix ActionCam DMS";
    public static final String DMS_NAME_SALIX_ACTIONCAM = "ActionCam DMS";
    public static final String DOWNLOADED_DB_PATH = (new StringBuilder()).append(BASE_PATH).append(".Downloaded_db/").toString();
    public static final String DOWNLOAD_PATH = (new StringBuilder()).append(BASE_PATH).append("Contents/").toString();
    public static final boolean FOLDER_BROWSE = false;
    public static boolean INDEPENDENT_SVP = false;
    public static final int INTENT_INTERVAL = 1000;
    public static final String INTENT_PUSHTO = "MUVI.mediaplus.intent.action.PUSHTO";
    public static final String INTENT_UPDOWNLOAD = "MUVI.mediaplus.intent.action.UpDownload";
    public static final String INTENT_VIEW = "MUVI.mediaplus.intent.action.VIEW";
    public static final boolean LIBS_IN_APK = true;
    public static final String LIBS_PATH = "/system/arcsoft/mediaplus/lib/";
    public static final long MIN_SIZE_ANDROID_DATA = 5120L;
    public static final long MIN_SIZE_MEMORY = 5120L;
    public static final long MIN_SIZE_SDCARD_FOR_CACHE = 0x32000L;
    public static final long MIN_SIZE_SDCARD_FOR_LAUNCH = 10240L;
    public static boolean OPENGL_OPTIMIZE = false;
    public static final OEMName PROJECT_NAME;
    public static final boolean SHOW_SELECT_DMS = false;
    public static final boolean SUPPORT_ALPHABET_BAR = false;
    public static final boolean SUPPORT_DMC = false;
    public static final boolean SUPPORT_DOWNLOAD = true;
    public static final boolean SUPPORT_EDIT_COLLAGE = false;
    public static final boolean SUPPORT_PRINT_LOG = false;
    public static boolean SUPPORT_PV_HIGH_VERSION = false;
    public static boolean SUPPORT_REFRESH_VIEW = false;
    public static final boolean SUPPORT_ROLLOVER = false;
    public static final boolean SUPPORT_SORT = false;
    public static final boolean SUPPORT_UPLOAD = true;
    public static final boolean SUPPORT_VIDEO_TRIM = true;
    public static final boolean SUPPORT_ZOOM_BTN;
    public static final String TEMP_BASE_PATH = (new StringBuilder()).append(BASE_PATH).append(".Temp/").toString();
    public static final com.arcsoft.adk.image.ThumbEngine.DecodeParam THUMBLIST_DECODEPARAM;

    public OEMConfig()
    {
    }

    static 
    {
        THUMBLIST_DECODEPARAM = new com.arcsoft.adk.image.ThumbEngine.DecodeParam(144, 144, com.arcsoft.adk.image.Const.DisplayMode.FIT_OUT, 24576);
        DMC_DECODEPARAM = new com.arcsoft.adk.image.ThumbEngine.DecodeParam(320, 320, com.arcsoft.adk.image.Const.DisplayMode.FIT_IN, 24576);
        DMP_DECODEPARAM = new com.arcsoft.adk.image.ThumbEngine.DecodeParam(600, 600, com.arcsoft.adk.image.Const.DisplayMode.FIT_IN, 24576);
        com.arcsoft.adk.image.ThumbEngine.DecodeParam adecodeparam[] = new com.arcsoft.adk.image.ThumbEngine.DecodeParam[3];
        adecodeparam[0] = THUMBLIST_DECODEPARAM;
        adecodeparam[1] = DMC_DECODEPARAM;
        adecodeparam[2] = DMP_DECODEPARAM;
        DECODEPARAMS = adecodeparam;
        PROJECT_NAME = OEMName.GENERIC;
        DEVICE_SUPPORT_MPEG2 = true;
        INDEPENDENT_SVP = false;
        OPENGL_OPTIMIZE = true;
        SUPPORT_PV_HIGH_VERSION = false;
        SUPPORT_REFRESH_VIEW = true;
        CHANNEL_DIR_ARIB = new HashMap();
        CHANNEL_DIR_ARIB.put("ARIB_TB", Integer.valueOf(2));
        CHANNEL_DIR_ARIB.put("ARIB_BS", Integer.valueOf(4));
        CHANNEL_DIR_ARIB.put("ARIB_CS", Integer.valueOf(8));
        CHANNEL_DIR_ARIB.put("SPTV_CS", Integer.valueOf(16));
    }
}
