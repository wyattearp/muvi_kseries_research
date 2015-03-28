// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.Context;
import android.content.SharedPreferences;

public class AEEUtilDef
{

    public static final String BOOL_IS_CUR_EXECUTING = "is_cur_executing";
    public static final String BOOL_IS_PREVIEW_CLOSED = "is_preview_closed";
    public static final String BOOL_IS_PREVIEW_NOT_SUPPORT = "is_preview_not_support";
    public static final String BOOL_IS_STREAM_CONNECTED = "is_stream_connected";
    public static final int ERR_TYPE_CARD_FULL = -17;
    public static final int ERR_TYPE_CARD_WRITE_PROTECTED = -18;
    public static final int ERR_TYPE_CONFIG_MISSING = -12;
    public static final int ERR_TYPE_EXCEPTION = 2;
    public static final int ERR_TYPE_FORMAT_ERR = -9;
    public static final int ERR_TYPE_HDMI_CABLE = -16;
    public static final int ERR_TYPE_INVAILD_OPERATION = -14;
    public static final int ERR_TYPE_INVAILD_OPTION = -13;
    public static final int ERR_TYPE_JSON_PACKAGE_ERR = -7;
    public static final int ERR_TYPE_JSON_PACKAGE_TIMEOUT = -8;
    public static final int ERR_TYPE_NULL = 1;
    public static final int ERR_TYPE_START_SEESION_FAIL = -3;
    public static final int ERR_TYPE_SUC = 0;
    public static final int ERR_TYPE_UNKNOWN = -1;
    public static final int ERR_TYPE_WRONG_TOKEN_ID = -4;
    public static final String INT_CONNECT_FAILED_PARAMS = "connect_failed_params";
    public static final String INT_CUR_EXE_STATE = "cur_exe_state";
    public static final String KEY_TOKEN_ID_STRING = "session_token_id";
    public static final int PARAM_AEE_CMD_BASE = 0x10000000;
    public static final int PARAM_AEE_CMD_DEFAULT = 0x10000010;
    public static final int PARAM_AEE_CMD_DV_MODE = 0x1000000b;
    public static final int PARAM_AEE_CMD_DZOOM_TELE = 0x1000000a;
    public static final int PARAM_AEE_CMD_DZOOM_WIDE = 0x10000009;
    public static final int PARAM_AEE_CMD_FORMAT = 0x1000000f;
    public static final int PARAM_AEE_CMD_GET_CONFIG = 0x1000001f;
    public static final int PARAM_AEE_CMD_GET_DV_RECORD_TIME = 0x1000002c;
    public static final int PARAM_AEE_CMD_GET_DV_SETTING = 0x10000020;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_APP_STATUS = 0x10000025;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DUAL_STREAM = 0x10000021;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DV_BAT = 0x1000002a;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DV_FS = 0x10000026;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DV_INFO = 0x10000027;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DV_MODE = 0x1000002b;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_DV_PID = 0x10000028;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_STREAMING = 0x10000023;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_STREAM_TYPE = 0x10000022;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_VIDEO_STAMP = 0x10000024;
    public static final int PARAM_AEE_CMD_GET_DV_SETTINGS_WIFI_KIT = 0x10000029;
    public static final int PARAM_AEE_CMD_GET_PHOTO_CON_FAST = 0x10000030;
    public static final int PARAM_AEE_CMD_GET_PHOTO_SHOT_MODE = 0x1000002f;
    public static final int PARAM_AEE_CMD_GET_PHOTO_SIZE = 0x1000002e;
    public static final int PARAM_AEE_CMD_GET_PHOTO_TLM = 0x10000031;
    public static final int PARAM_AEE_CMD_GET_ROTATE_STATE = 0x10000033;
    public static final int PARAM_AEE_CMD_GET_VIDEO_FOV = 0x10000032;
    public static final int PARAM_AEE_CMD_GET_VIDEO_RESOLUTION = 0x1000002d;
    public static final int PARAM_AEE_CMD_PHOTO_MODE = 0x10000015;
    public static final int PARAM_AEE_CMD_POWEROFF = 0x10000011;
    public static final int PARAM_AEE_CMD_POWERON = 0x10000016;
    public static final int PARAM_AEE_CMD_RECORD_START = 0x10000003;
    public static final int PARAM_AEE_CMD_RECORD_STOP = 0x10000004;
    public static final int PARAM_AEE_CMD_RECORD_VOICE = 0x10000005;
    public static final int PARAM_AEE_CMD_ROTATE_OFF = 0x1000000e;
    public static final int PARAM_AEE_CMD_ROTATE_ON = 0x1000000d;
    public static final int PARAM_AEE_CMD_SESSION_START = 0x10000001;
    public static final int PARAM_AEE_CMD_SESSION_STOP = 0x10000002;
    public static final int PARAM_AEE_CMD_SET_DV_SETTING = 0x10000034;
    public static final int PARAM_AEE_CMD_SET_DV_SETTINGS_DUAL_STREAM = 0x10000035;
    public static final int PARAM_AEE_CMD_SET_DV_SETTINGS_STREAMING = 0x10000037;
    public static final int PARAM_AEE_CMD_SET_DV_SETTINGS_STREAM_TYPE = 0x10000036;
    public static final int PARAM_AEE_CMD_SET_DV_SETTINGS_TIME = 0x10000039;
    public static final int PARAM_AEE_CMD_SET_DV_SETTINGS_VIDEO_STAMP = 0x10000038;
    public static final int PARAM_AEE_CMD_START_ENCODING = 0x10000012;
    public static final int PARAM_AEE_CMD_STOP_ENCODING = 0x10000013;
    public static final int PARAM_AEE_CMD_STOP_LAPSEPHOTO = 0x10000008;
    public static final int PARAM_AEE_CMD_TAKE_FASTPHOTO = 0x10000007;
    public static final int PARAM_AEE_CMD_TAKE_LAPSEPHOTO = 0x10000014;
    public static final int PARAM_AEE_CMD_TAKE_PHOTO = 0x10000006;
    public static final int PARAM_AEE_CMD_VOICE_MODE = 0x1000000c;
    public static final int REQUEST_CODE_STREAM_TO_FULL_SCREEN = 0x10000066;
    public static final int REQUEST_CODE_STREAM_TO_PLUS = 0x10000067;
    public static final int REQUEST_CODE_STREAM_TO_SETTING = 0x10000065;
    public static final String STRING_RECTING_TIME = "get_recting_time";
    public static final String STR_APP_DEVICE_TITLE = "module";
    public static final String STR_APP_NAME_TITLE = "manfacturer";
    public static final String STR_APP_STATUS_IDLE = "idle";
    public static final String STR_APP_STATUS_RECORD = "record";
    public static final String STR_APP_STATUS_ROTATE_OFF = "video_flip_rotate_off";
    public static final String STR_APP_STATUS_ROTATE_ON = "video_flip_rotate_on_";
    public static final String STR_APP_VERSION_TITLE = "version";
    public static final String STR_STREAMING_OFF = "off";
    public static final String STR_STREAMING_ON = "on";
    public static final String STR_STREAMING_RTSP = "rtsp";
    public static final String TYPE_AEE_DEVICE_5S_WIFI = "5S";
    public static final String TYPE_AEE_DEVICE_NAVCAM = "Navcam";
    public static final String TYPE_AEE_DEVICE_S25W = "S25W";
    public static final String TYPE_AEE_DEVICE_S50 = "S50";
    public static final String TYPE_AEE_DEVICE_S51 = "S51";
    public static final String TYPE_AEE_DEVICE_S60_WIFI = "S60";
    public static final String TYPE_AEE_DEVICE_S70 = "S70";
    public static final String TYPE_AEE_DEVICE_SD21W = "SD21W";
    public static final String TYPE_AEE_DEVICE_SD22W = "SD22W";
    public static final String TYPE_AEE_DEVICE_SD23W = "SD23W";
    public static final String TYPE_AEE_STR_GET_DV_BAT = "get_dv_bat";
    public static final String TYPE_AEE_STR_GET_DV_FS = "get_dv_fs";
    public static final String TYPE_AEE_STR_GET_DV_INFO = "get_dv_info";
    public static final String TYPE_AEE_STR_GET_DV_MODE = "get_dv_mode";
    public static final String TYPE_AEE_STR_GET_DV_PID = "get_dv_pid";
    public static final String TYPE_AEE_STR_GET_RECORD_TIME = "get_video_time";
    public static final String TYPE_AEE_STR_GET_WIFI_KIT = "get_wifi_kit";
    public static final String TYPE_AEE_STR_SET_LANGUAGE = "language";
    public static final String TYPE_AEE_STR_SET_PHOTO_CAP_MODE = "photo_cap_mode";
    public static final String TYPE_AEE_STR_SET_PHOTO_SELFTIMER = "photo_selftimer";
    public static final String TYPE_AEE_STR_SET_PHOTO_SHOT_MODE = "photo_shot_mode";
    public static final String TYPE_AEE_STR_SET_PHOTO_SIZE = "photo_size";
    public static final String TYPE_AEE_STR_SET_PHOTO_STAMP = "photo_stamp";
    public static final String TYPE_AEE_STR_SET_PHOTO_TLM = "photo_tlm";
    public static final String TYPE_AEE_STR_SET_RECORD_MODE = "record_mode";
    public static final String TYPE_AEE_STR_SET_SETUP_KEY_TONE = "setup_key_tone";
    public static final String TYPE_AEE_STR_SET_SETUP_LOOP_BACK = "setup_loop_back";
    public static final String TYPE_AEE_STR_SET_SETUP_OSD = "setup_osd";
    public static final String TYPE_AEE_STR_SET_SETUP_POWEROFF = "setup_poweroff";
    public static final String TYPE_AEE_STR_SET_SETUP_SELFLAMP = "setup_selflamp";
    public static final String TYPE_AEE_STR_SET_SETUP_SYSTEM_TYPE = "setup_system_type";
    public static final String TYPE_AEE_STR_SET_SETUP_TIME = "setup_time";
    public static final String TYPE_AEE_STR_SET_VIDEO_FLIP_ROTATE = "video_flip_rotate";
    public static final String TYPE_AEE_STR_SET_VIDEO_FOV = "video_fov";
    public static final String TYPE_AEE_STR_SET_VIDEO_QUALITY = "video_quality";
    public static final String TYPE_AEE_STR_SET_VIDEO_RESOLUTION = "video_resolution";
    public static final String TYPE_AEE_STR_SET_VIDEO_SELFTIMER = "video_selftimer";
    public static final String TYPE_AEE_STR_SET_VIDEO_STAMP = "video_stamp";
    public static final String TYPE_AEE_STR_SET_VIDEO_TIME_LAPSE = "video_time_lapse";

    public AEEUtilDef()
    {
    }

    public static int getSharedTokenId(Context context)
    {
        int i;
        try
        {
            i = context.getSharedPreferences(context.getPackageName(), 1).getInt("session_token_id", 0);
        }
        catch (Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static void setSharedTokenId(Context context, int i)
    {
        try
        {
            context.getSharedPreferences(context.getPackageName(), 1).edit().putInt("session_token_id", i).commit();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
