// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import java.util.LinkedHashMap;

public class AEEParameterMaps
{
    public static class LanguageMapDef
    {

        public static final String VAL_CHS = "language_chs";
        public static final String VAL_CHT = "language_cht";
        public static final String VAL_CZE = "language_cze";
        public static final String VAL_DAN = "language_dan";
        public static final String VAL_DUT = "language_dut";
        public static final String VAL_ENG = "language_eng";
        public static final String VAL_FIN = "language_fin";
        public static final String VAL_FRE = "language_fre";
        public static final String VAL_GER = "language_ger";
        public static final String VAL_GRE = "language_gre";
        public static final String VAL_HUN = "language_hun";
        public static final String VAL_ITA = "language_ita";
        public static final String VAL_JAP = "language_jap";
        public static final String VAL_KOR = "language_kor";
        public static final String VAL_NOR = "language_nor";
        public static final String VAL_POL = "language_pol";
        public static final String VAL_POR = "language_por";
        public static final String VAL_RUS = "language_rus";
        public static final String VAL_SPA = "language_spa";
        public static final String VAL_SWE = "language_swe";
        public static final String VAL_UKR = "language_ukr";

        public LanguageMapDef()
        {
        }
    }

    public static class PhotoCapModeMapDef
    {

        public static final String VAL_FAS = "photo_cap_mode_fas";
        public static final String VAL_NOR = "photo_cap_mode_nor";
        public static final String VAL_SEL = "photo_cap_mode_sel";
        public static final String VAL_TLM = "photo_cap_mode_con";

        public PhotoCapModeMapDef()
        {
        }
    }

    public static class PhotoSelfTimerMapDef
    {

        public static final String VAL_03s = "photo_selftimer_03s";
        public static final String VAL_05s = "photo_selftimer_05s";
        public static final String VAL_10s = "photo_selftimer_10s";

        public PhotoSelfTimerMapDef()
        {
        }
    }

    public static class PhotoShotModeMapDef
    {

        public static final String VAL_03 = "photo_shot_03";
        public static final String VAL_06 = "photo_shot_06";
        public static final String VAL_08 = "photo_shot_08";
        public static final String VAL_10 = "photo_shot_10";

        public PhotoShotModeMapDef()
        {
        }
    }

    public static class PhotoSizeMapDef
    {

        public static final String VAL_12M_3840_2880_4_3 = "12.0M (3840x2880 4:3)";
        public static final String VAL_12M_4000_3000_4_3 = "12.0M (4000x3000 4:3)";
        public static final String VAL_12M_4096_3072_4_3 = "12.0M (4096x3072 4:3)";
        public static final String VAL_16M_4608_3456_4_3 = "16.0M (4608x3456 4:3)";
        public static final String VAL_3M_2048_1536_4_3 = "3.0M (2048x1536 4:3)";
        public static final String VAL_5M_2592_1944_4_3 = "5.0M (2592x1944 4:3)";
        public static final String VAL_8M_3200_2400_4_3 = "8.0M (3200x2400 4:3)";
        public static final String VAL_8M_3264_2448_4_3 = "8.0M (3264x2448 4:3)";

        public PhotoSizeMapDef()
        {
        }
    }

    public static class PhotoStampMapDef
    {

        public static final String VAL_BOT = "photo_stamp_bot";
        public static final String VAL_OFF = "photo_stamp_off";

        public PhotoStampMapDef()
        {
        }
    }

    public static class PhotoTlmLoopDef
    {

        public static final String VAL_OFF = "photo_stamp_off";
        public static final String VAL_ON = "photo_stamp_bat";

        public PhotoTlmLoopDef()
        {
        }
    }

    public static class PhotoTlmMapDef
    {

        public static final String VAL_01s = "photo_tlm_01s";
        public static final String VAL_02s = "photo_tlm_02s";
        public static final String VAL_03s = "photo_tlm_03s";
        public static final String VAL_05s = "photo_tlm_05s";
        public static final String VAL_10s = "photo_tlm_10s";
        public static final String VAL_20s = "photo_tlm_20s";
        public static final String VAL_30s = "photo_tlm_30s";
        public static final String VAL_60s = "photo_tlm_60s";
        public static final String VAL_P5s = "photo_tlm_p5s";

        public PhotoTlmMapDef()
        {
        }
    }

    public static class RecordModeMapDef
    {

        public static final String VAL_LAP = "record_mode_lap";
        public static final String VAL_PRE = "record_mode_pre";
        public static final String VAL_SEL = "record_mode_sel";
        public static final String VAL_VID = "record_mode_vid";
        public static final String VAL_VOI = "record_mode_voi";

        public RecordModeMapDef()
        {
        }
    }

    public static class SettingMapDef
    {

        public static final String VAL_CMD_DEFAULT = "reset default setting";
        public static final String VAL_CMD_FORMAT = "format sdcard";
        public static final String VAL_DEVICE_NAME = "device_name";
        public static final String VAL_LANGUAGE = "language";
        public static final String VAL_PHOTO_CAP_MODE = "photo_cap_mode";
        public static final String VAL_PHOTO_CONTINUE_FAST = "photo_continue_fast";
        public static final String VAL_PHOTO_LOOP = "photo_loop";
        public static final String VAL_PHOTO_SELFTIMER = "photo_selftimer";
        public static final String VAL_PHOTO_SHOT_MODE = "photo_shot_mode";
        public static final String VAL_PHOTO_SIZE = "photo_size";
        public static final String VAL_PHOTO_STAMP = "photo_stamp";
        public static final String VAL_PHOTO_TLM = "photo_tlm";
        public static final String VAL_RECORD_MODE = "record_mode";
        public static final String VAL_SETUP_DETECT_FACE = "setup_detect_face";
        public static final String VAL_SETUP_IMAGE_WB = "setup_image_wb";
        public static final String VAL_SETUP_KEY_TONE = "setup_key_tone";
        public static final String VAL_SETUP_LOOP_BACK = "setup_loop_back";
        public static final String VAL_SETUP_OSD = "setup_osd";
        public static final String VAL_SETUP_POWEROFF = "setup_poweroff";
        public static final String VAL_SETUP_SELFLAMP = "setup_selflamp";
        public static final String VAL_SETUP_SYSTEM_TYPE = "setup_system_type";
        public static final String VAL_SETUP_TIME = "setup_time";
        public static final String VAL_SETUP_VERSION = "version";
        public static final String VAL_SET_GSENSOR = "set_gsensor";
        public static final String VAL_SET_IMAGE_CONTRAST = "set_image_contrast";
        public static final String VAL_SET_IMAGE_ISO = "set_image_iso";
        public static final String VAL_SET_LIGHT_MODE = "set_light_mode";
        public static final String VAL_SET_PROTUNE = "set_protune";
        public static final String VAL_TAB_PHOTO = "tab_photo";
        public static final String VAL_TAB_SETUP = "tab_setup";
        public static final String VAL_TAB_VIDEO = "tab_video";
        public static final String VAL_VIDEO_FLIP_ROTATE = "video_flip_rotate";
        public static final String VAL_VIDEO_FOV = "video_fov";
        public static final String VAL_VIDEO_QUALITY = "video_quality";
        public static final String VAL_VIDEO_RESOLUTION = "video_resolution";
        public static final String VAL_VIDEO_SELFTIMER = "video_selftimer";
        public static final String VAL_VIDEO_STAMP = "video_stamp";
        public static final String VAL_VIDEO_TIME_LAPSE = "video_time_lapse";

        public SettingMapDef()
        {
        }
    }

    public static class SetupDetectFaceMapDef
    {

        public static final String VAL_OFF = "setup_detect_face_off";
        public static final String VAL_ON = "setup_detect_face_on_";

        public SetupDetectFaceMapDef()
        {
        }
    }

    public static class SetupGesensorMapDef
    {

        public static final String VAL_OFF = "set_gsensor_off";
        public static final String VAL_ON = "set_gsensor_on_";

        public SetupGesensorMapDef()
        {
        }
    }

    public static class SetupImageContrastMapDef
    {

        public static final String VAL_HARD = "image_contrast_hard";
        public static final String VAL_STAN = "image_contrast_stan";

        public SetupImageContrastMapDef()
        {
        }
    }

    public static class SetupImageISOMapDef
    {

        public static final String VAL_AUTO = "image_iso_auto";
        public static final String VAL_LOW = "image_iso_lowl";

        public SetupImageISOMapDef()
        {
        }
    }

    public static class SetupImageWbMapDef
    {

        public static final String VAL_3000K = "image_wb_03m0";
        public static final String VAL_5500K = "image_wb_05m5";
        public static final String VAL_6500K = "image_wb_06m5";
        public static final String VAL_AUTO = "image_wb_auto";
        public static final String VAL_Cam_Raw = "image_wb_camr";

        public SetupImageWbMapDef()
        {
        }
    }

    public static class SetupKeyToneMapDef
    {

        public static final String VAL_MED = "setup_key_tone_med";
        public static final String VAL_OFF = "setup_key_tone_off";
        public static final String VAL_STA = "setup_key_tone_sta";

        public SetupKeyToneMapDef()
        {
        }
    }

    public static class SetupLightModeMapDef
    {

        public static final String VAL_AVER = "set_light_aver";
        public static final String VAL_CENP = "set_light_cenp";
        public static final String VAL_CENZ = "set_light_cenz";
        public static final String VAL_OFF = "set_light_off";
        public static final String VAL_ON = "set_light_on_";

        public SetupLightModeMapDef()
        {
        }
    }

    public static class SetupLoopBackMapDef
    {

        public static final String VAL_OFF = "setup_loop_back_off";
        public static final String VAL_ON_ = "setup_loop_back_on_";

        public SetupLoopBackMapDef()
        {
        }
    }

    public static class SetupOsdMapDef
    {

        public static final String VAL_OFF = "setup_osd_off";
        public static final String VAL_ON_ = "setup_osd_on_";

        public SetupOsdMapDef()
        {
        }
    }

    public static class SetupPowerOffMapDef
    {

        public static final String VAL_02M = "setup_poweroff_02m";
        public static final String VAL_05M = "setup_poweroff_05m";
        public static final String VAL_10M = "setup_poweroff_10m";
        public static final String VAL_OFF = "setup_poweroff_off";

        public SetupPowerOffMapDef()
        {
        }
    }

    public static class SetupProtuneMapDef
    {

        public static final String VAL_OFF = "set_protune_off";
        public static final String VAL_ON = "set_protune_on_";

        public SetupProtuneMapDef()
        {
        }
    }

    public static class SetupSystemTypeMapDef
    {

        public static final String VAL_NTS = "setup_system_type_nts";
        public static final String VAL_PAL = "setup_system_type_pal";

        public SetupSystemTypeMapDef()
        {
        }
    }

    public static class SetupselflampeMapDef
    {

        public static final String VAL_OFF = "setup_selflamp_off";
        public static final String VAL_ON2 = "setup_selflamp_on2";
        public static final String VAL_ON_ = "setup_selflamp_on_";

        public SetupselflampeMapDef()
        {
        }
    }

    public static class VideoFlipRotateMapDef
    {

        public static final String VAL_OFF = "video_flip_rotate_off";
        public static final String VAL_ON_ = "video_flip_rotate_on_";

        public VideoFlipRotateMapDef()
        {
        }
    }

    public static class VideoFovMapDef
    {

        public static final String VAL_MED = "video_fov_med";
        public static final String VAL_NAR = "video_fov_nar";
        public static final String VAL_WID = "video_fov_wid";
        public static final String VAL_X = "video_fov_zom";

        public VideoFovMapDef()
        {
        }
    }

    public static class VideoQualityMapDef
    {

        public static final String VAL_FIN = "video_quality_fin";
        public static final String VAL_SFI = "video_quality_sfi";

        public VideoQualityMapDef()
        {
        }
    }

    public static class VideoResolutionMapDef
    {

        public static final String VAL_1280_720_P_100_16_9 = "1280x0720P 100f 16:09";
        public static final String VAL_1280_720_P_120_16_9 = "1280x0720P 120f 16:09";
        public static final String VAL_1280_720_P_25_16_9 = "1280x0720P 025f 16:09";
        public static final String VAL_1280_720_P_30_16_9 = "1280x0720P 030f 16:09";
        public static final String VAL_1280_720_P_50_16_9 = "1280x0720P 050f 16:09";
        public static final String VAL_1280_720_P_60_16_9 = "1280x0720P 060f 16:09";
        public static final String VAL_1280_960_P_25_4_3 = "1280x0960P 025f 04:03";
        public static final String VAL_1280_960_P_30_4_3 = "1280x0960P 030f 04:03";
        public static final String VAL_1280_960_P_48_4_3 = "1280x0960P 048f 04:03";
        public static final String VAL_1280_960_P_50_4_3 = "1280x0960P 050f 04:03";
        public static final String VAL_1280_960_P_60_4_3 = "1280x0960P 060f 04:03";
        public static final String VAL_1920_1080_I_50_16_9 = "1920x1080i 050f 16:09";
        public static final String VAL_1920_1080_I_60_16_9 = "1920x1080i 060f 16:09";
        public static final String VAL_1920_1080_P_24_16_9 = "1920x1080P 024f 16:09";
        public static final String VAL_1920_1080_P_25_16_9 = "1920x1080P 025f 16:09";
        public static final String VAL_1920_1080_P_30_16_9 = "1920x1080P 030f 16:09";
        public static final String VAL_1920_1080_P_48_16_9 = "1920x1080P 048f 16:09";
        public static final String VAL_1920_1080_P_50_16_9 = "1920x1080P 050f 16:09";
        public static final String VAL_1920_1080_P_60_16_9 = "1920x1080P 060f 16:09";
        public static final String VAL_848_480_P_100_16_9 = "0848x0480P 100f 16:09";
        public static final String VAL_848_480_P_120_16_9 = "0848x0480P 120f 16:09";
        public static final String VAL_848_480_P_200_16_9 = "0848x0480P 200f 16:09";
        public static final String VAL_848_480_P_240_16_9 = "0848x0480P 240f 16:09";
        public static final String VAL_848_480_P_50_16_9 = "0848x0480P 050f 16:09";
        public static final String VAL_848_480_P_60_16_9 = "0848x0480P 060f 16:09";

        public VideoResolutionMapDef()
        {
        }
    }

    public static class VideoSelfTimerMapDef
    {

        public static final String VAL_05s = "video_selftimer_05s";
        public static final String VAL_10s = "video_selftimer_10s";
        public static final String VAL_20s = "video_selftimer_20s";

        public VideoSelfTimerMapDef()
        {
        }
    }

    public static class VideoStampMapDef
    {

        public static final String VAL_BOT = "video_stamp_bot";
        public static final String VAL_OFF = "video_stamp_off";

        public VideoStampMapDef()
        {
        }
    }

    public static class VideoTimeLapseMapDef
    {

        public static final String VAL_01s = "video_time_lapse_01s";
        public static final String VAL_02s = "video_time_lapse_02s";
        public static final String VAL_05s = "video_time_lapse_05s";
        public static final String VAL_10s = "video_time_lapse_10s";
        public static final String VAL_30s = "video_time_lapse_30s";
        public static final String VAL_P1s = "video_time_lapse_p1s";
        public static final String VAL_P5s = "video_time_lapse_p5s";

        public VideoTimeLapseMapDef()
        {
        }
    }


    public static LinkedHashMap mAEELanguageMap;
    public static LinkedHashMap mAEEPhotoCapModeMap;
    public static LinkedHashMap mAEEPhotoLoopMap;
    public static LinkedHashMap mAEEPhotoSelfTimerMap;
    public static LinkedHashMap mAEEPhotoShotModeMap;
    public static LinkedHashMap mAEEPhotoSizeMap;
    public static LinkedHashMap mAEEPhotoStampMap;
    public static LinkedHashMap mAEEPhotoTlmMap;
    public static LinkedHashMap mAEERecordModeMap;
    public static LinkedHashMap mAEESettingMap;
    public static LinkedHashMap mAEESetupDetectFaceMap;
    public static LinkedHashMap mAEESetupGesensorMap;
    public static LinkedHashMap mAEESetupImageContrastMap;
    public static LinkedHashMap mAEESetupImageISOMap;
    public static LinkedHashMap mAEESetupImageWbMap;
    public static LinkedHashMap mAEESetupKeyToneMap;
    public static LinkedHashMap mAEESetupLightModeMap;
    public static LinkedHashMap mAEESetupLoopBackMap;
    public static LinkedHashMap mAEESetupOsdMap;
    public static LinkedHashMap mAEESetupPowerOffMap;
    public static LinkedHashMap mAEESetupProtuneMap;
    public static LinkedHashMap mAEESetupSystemTypeMap;
    public static LinkedHashMap mAEESetupTimeMap;
    public static LinkedHashMap mAEESetupselflampeMap;
    public static LinkedHashMap mAEEVideoFlipRotateMap;
    public static LinkedHashMap mAEEVideoFovMap;
    public static LinkedHashMap mAEEVideoQualityMap;
    public static LinkedHashMap mAEEVideoResolutionMap;
    public static LinkedHashMap mAEEVideoSelfTimerMap;
    public static LinkedHashMap mAEEVideoStampMap;
    public static LinkedHashMap mAEEVideoTimeLapseMap;

    public AEEParameterMaps()
    {
    }

    static 
    {
        mAEESettingMap = new LinkedHashMap();
        mAEESettingMap.put("tab_video", Integer.valueOf(0x7f0b01d6));
        mAEESettingMap.put("record_mode", Integer.valueOf(0x7f0b02a0));
        mAEESettingMap.put("video_resolution", Integer.valueOf(0x7f0b02a1));
        mAEESettingMap.put("video_fov", Integer.valueOf(0x7f0b02a2));
        mAEESettingMap.put("video_quality", Integer.valueOf(0x7f0b02a3));
        mAEESettingMap.put("video_time_lapse", Integer.valueOf(0x7f0b02a4));
        mAEESettingMap.put("video_selftimer", Integer.valueOf(0x7f0b02a5));
        mAEESettingMap.put("video_flip_rotate", Integer.valueOf(0x7f0b02a6));
        mAEESettingMap.put("video_stamp", Integer.valueOf(0x7f0b02a7));
        mAEESettingMap.put("tab_photo", Integer.valueOf(0x7f0b01d7));
        mAEESettingMap.put("photo_size", Integer.valueOf(0x7f0b02b2));
        mAEESettingMap.put("photo_cap_mode", Integer.valueOf(0x7f0b02b3));
        mAEESettingMap.put("photo_shot_mode", Integer.valueOf(0x7f0b02b4));
        mAEESettingMap.put("photo_continue_fast", Integer.valueOf(0x7f0b02b5));
        mAEESettingMap.put("photo_tlm", Integer.valueOf(0x7f0b02b6));
        mAEESettingMap.put("photo_loop", Integer.valueOf(0x7f0b02b7));
        mAEESettingMap.put("photo_selftimer", Integer.valueOf(0x7f0b02b8));
        mAEESettingMap.put("photo_stamp", Integer.valueOf(0x7f0b02b9));
        mAEESettingMap.put("tab_setup", Integer.valueOf(0x7f0b01d4));
        mAEESettingMap.put("setup_key_tone", Integer.valueOf(0x7f0b02ba));
        mAEESettingMap.put("setup_selflamp", Integer.valueOf(0x7f0b02bb));
        mAEESettingMap.put("setup_osd", Integer.valueOf(0x7f0b02bc));
        mAEESettingMap.put("setup_loop_back", Integer.valueOf(0x7f0b02bd));
        mAEESettingMap.put("setup_poweroff", Integer.valueOf(0x7f0b02be));
        mAEESettingMap.put("setup_system_type", Integer.valueOf(0x7f0b02bf));
        mAEESettingMap.put("setup_detect_face", Integer.valueOf(0x7f0b02c0));
        mAEESettingMap.put("set_protune", Integer.valueOf(0x7f0b02c1));
        mAEESettingMap.put("setup_image_wb", Integer.valueOf(0x7f0b02c2));
        mAEESettingMap.put("set_image_contrast", Integer.valueOf(0x7f0b02c3));
        mAEESettingMap.put("set_image_iso", Integer.valueOf(0x7f0b02c4));
        mAEESettingMap.put("set_light_mode", Integer.valueOf(0x7f0b02c5));
        mAEESettingMap.put("set_gsensor", Integer.valueOf(0x7f0b02c6));
        mAEESettingMap.put("language", Integer.valueOf(0x7f0b02c7));
        mAEESettingMap.put("setup_time", Integer.valueOf(0x7f0b02c8));
        mAEESettingMap.put("format sdcard", Integer.valueOf(0x7f0b02c9));
        mAEESettingMap.put("reset default setting", Integer.valueOf(0x7f0b02ca));
        mAEESettingMap.put("version", Integer.valueOf(0x7f0b02cb));
        mAEESettingMap.put("device_name", Integer.valueOf(0x7f0b02cc));
        mAEERecordModeMap = new LinkedHashMap();
        mAEERecordModeMap.put("record_mode_vid", Integer.valueOf(0x7f0b02ce));
        mAEERecordModeMap.put("record_mode_voi", Integer.valueOf(0x7f0b02cf));
        mAEERecordModeMap.put("record_mode_lap", Integer.valueOf(0x7f0b02d0));
        mAEERecordModeMap.put("record_mode_pre", Integer.valueOf(0x7f0b02d1));
        mAEERecordModeMap.put("record_mode_sel", Integer.valueOf(0x7f0b02d2));
        mAEEVideoResolutionMap = new LinkedHashMap();
        mAEEVideoResolutionMap.put("1920x1080P 060f 16:09", Integer.valueOf(0x7f0b02d3));
        mAEEVideoResolutionMap.put("1920x1080i 050f 16:09", Integer.valueOf(0x7f0b02d4));
        mAEEVideoResolutionMap.put("1920x1080P 050f 16:09", Integer.valueOf(0x7f0b02d5));
        mAEEVideoResolutionMap.put("1920x1080P 048f 16:09", Integer.valueOf(0x7f0b02d6));
        mAEEVideoResolutionMap.put("1920x1080P 025f 16:09", Integer.valueOf(0x7f0b02d8));
        mAEEVideoResolutionMap.put("1920x1080P 024f 16:09", Integer.valueOf(0x7f0b02d7));
        mAEEVideoResolutionMap.put("1280x0960P 060f 04:03", Integer.valueOf(0x7f0b02d9));
        mAEEVideoResolutionMap.put("1280x0960P 050f 04:03", Integer.valueOf(0x7f0b02da));
        mAEEVideoResolutionMap.put("1280x0960P 025f 04:03", Integer.valueOf(0x7f0b02db));
        mAEEVideoResolutionMap.put("1280x0720P 120f 16:09", Integer.valueOf(0x7f0b02dc));
        mAEEVideoResolutionMap.put("1280x0720P 100f 16:09", Integer.valueOf(0x7f0b02dd));
        mAEEVideoResolutionMap.put("1280x0720P 050f 16:09", Integer.valueOf(0x7f0b02de));
        mAEEVideoResolutionMap.put("1280x0720P 025f 16:09", Integer.valueOf(0x7f0b02df));
        mAEEVideoResolutionMap.put("0848x0480P 240f 16:09", Integer.valueOf(0x7f0b02e0));
        mAEEVideoResolutionMap.put("0848x0480P 200f 16:09", Integer.valueOf(0x7f0b02e1));
        mAEEVideoResolutionMap.put("0848x0480P 100f 16:09", Integer.valueOf(0x7f0b02e2));
        mAEEVideoResolutionMap.put("0848x0480P 050f 16:09", Integer.valueOf(0x7f0b02e3));
        mAEEVideoResolutionMap.put("1920x1080i 060f 16:09", Integer.valueOf(0x7f0b02e4));
        mAEEVideoResolutionMap.put("1920x1080P 030f 16:09", Integer.valueOf(0x7f0b02e5));
        mAEEVideoResolutionMap.put("1280x0960P 048f 04:03", Integer.valueOf(0x7f0b02e6));
        mAEEVideoResolutionMap.put("1280x0960P 030f 04:03", Integer.valueOf(0x7f0b02e7));
        mAEEVideoResolutionMap.put("1280x0720P 060f 16:09", Integer.valueOf(0x7f0b02e8));
        mAEEVideoResolutionMap.put("1280x0720P 030f 16:09", Integer.valueOf(0x7f0b02e9));
        mAEEVideoResolutionMap.put("0848x0480P 120f 16:09", Integer.valueOf(0x7f0b02ea));
        mAEEVideoResolutionMap.put("0848x0480P 060f 16:09", Integer.valueOf(0x7f0b02eb));
        mAEEVideoFovMap = new LinkedHashMap();
        mAEEVideoFovMap.put("video_fov_wid", Integer.valueOf(0x7f0b02ec));
        mAEEVideoFovMap.put("video_fov_med", Integer.valueOf(0x7f0b02ed));
        mAEEVideoFovMap.put("video_fov_nar", Integer.valueOf(0x7f0b02ee));
        mAEEVideoFovMap.put("video_fov_zom", Integer.valueOf(0x7f0b02ef));
        mAEEVideoQualityMap = new LinkedHashMap();
        mAEEVideoQualityMap.put("video_quality_sfi", Integer.valueOf(0x7f0b02f0));
        mAEEVideoQualityMap.put("video_quality_fin", Integer.valueOf(0x7f0b02f1));
        mAEEVideoTimeLapseMap = new LinkedHashMap();
        mAEEVideoTimeLapseMap.put("video_time_lapse_p1s", Integer.valueOf(0x7f0b02f3));
        mAEEVideoTimeLapseMap.put("video_time_lapse_p5s", Integer.valueOf(0x7f0b02f4));
        mAEEVideoTimeLapseMap.put("video_time_lapse_01s", Integer.valueOf(0x7f0b02f5));
        mAEEVideoTimeLapseMap.put("video_time_lapse_02s", Integer.valueOf(0x7f0b02f6));
        mAEEVideoTimeLapseMap.put("video_time_lapse_05s", Integer.valueOf(0x7f0b02f7));
        mAEEVideoTimeLapseMap.put("video_time_lapse_10s", Integer.valueOf(0x7f0b02f8));
        mAEEVideoTimeLapseMap.put("video_time_lapse_30s", Integer.valueOf(0x7f0b02f9));
        mAEEVideoSelfTimerMap = new LinkedHashMap();
        mAEEVideoSelfTimerMap.put("video_selftimer_05s", Integer.valueOf(0x7f0b02fb));
        mAEEVideoSelfTimerMap.put("video_selftimer_10s", Integer.valueOf(0x7f0b02fc));
        mAEEVideoSelfTimerMap.put("video_selftimer_20s", Integer.valueOf(0x7f0b02fc));
        mAEEVideoFlipRotateMap = new LinkedHashMap();
        mAEEVideoFlipRotateMap.put("video_flip_rotate_off", Integer.valueOf(0x7f0b02fe));
        mAEEVideoFlipRotateMap.put("video_flip_rotate_on_", Integer.valueOf(0x7f0b02ff));
        mAEEVideoStampMap = new LinkedHashMap();
        mAEEVideoStampMap.put("video_stamp_off", Integer.valueOf(0x7f0b0300));
        mAEEVideoStampMap.put("video_stamp_bot", Integer.valueOf(0x7f0b0301));
        mAEEPhotoSizeMap = new LinkedHashMap();
        mAEEPhotoSizeMap.put("16.0M (4608x3456 4:3)", Integer.valueOf(0x7f0b0302));
        mAEEPhotoSizeMap.put("12.0M (4096x3072 4:3)", Integer.valueOf(0x7f0b0303));
        mAEEPhotoSizeMap.put("12.0M (4000x3000 4:3)", Integer.valueOf(0x7f0b0304));
        mAEEPhotoSizeMap.put("12.0M (3840x2880 4:3)", Integer.valueOf(0x7f0b0305));
        mAEEPhotoSizeMap.put("8.0M (3264x2448 4:3)", Integer.valueOf(0x7f0b0306));
        mAEEPhotoSizeMap.put("8.0M (3200x2400 4:3)", Integer.valueOf(0x7f0b0307));
        mAEEPhotoSizeMap.put("5.0M (2592x1944 4:3)", Integer.valueOf(0x7f0b0308));
        mAEEPhotoSizeMap.put("3.0M (2048x1536 4:3)", Integer.valueOf(0x7f0b0309));
        mAEEPhotoCapModeMap = new LinkedHashMap();
        mAEEPhotoCapModeMap.put("photo_cap_mode_nor", Integer.valueOf(0x7f0b030a));
        mAEEPhotoCapModeMap.put("photo_cap_mode_fas", Integer.valueOf(0x7f0b030b));
        mAEEPhotoCapModeMap.put("photo_cap_mode_fas", Integer.valueOf(0x7f0b030c));
        mAEEPhotoCapModeMap.put("photo_cap_mode_sel", Integer.valueOf(0x7f0b030e));
        mAEEPhotoShotModeMap = new LinkedHashMap();
        mAEEPhotoShotModeMap.put("photo_shot_03", Integer.valueOf(0x7f0b0310));
        mAEEPhotoShotModeMap.put("photo_shot_06", Integer.valueOf(0x7f0b0311));
        mAEEPhotoShotModeMap.put("photo_shot_08", Integer.valueOf(0x7f0b0312));
        mAEEPhotoShotModeMap.put("photo_shot_10", Integer.valueOf(0x7f0b0313));
        mAEEPhotoTlmMap = new LinkedHashMap();
        mAEEPhotoTlmMap.put("photo_tlm_p5s", Integer.valueOf(0x7f0b0315));
        mAEEPhotoTlmMap.put("photo_tlm_01s", Integer.valueOf(0x7f0b0316));
        mAEEPhotoTlmMap.put("photo_tlm_02s", Integer.valueOf(0x7f0b0317));
        mAEEPhotoTlmMap.put("photo_tlm_03s", Integer.valueOf(0x7f0b0318));
        mAEEPhotoTlmMap.put("photo_tlm_05s", Integer.valueOf(0x7f0b0319));
        mAEEPhotoTlmMap.put("photo_tlm_10s", Integer.valueOf(0x7f0b031a));
        mAEEPhotoTlmMap.put("photo_tlm_20s", Integer.valueOf(0x7f0b031b));
        mAEEPhotoTlmMap.put("photo_tlm_30s", Integer.valueOf(0x7f0b031c));
        mAEEPhotoTlmMap.put("photo_tlm_60s", Integer.valueOf(0x7f0b031d));
        mAEEPhotoLoopMap = new LinkedHashMap();
        mAEEPhotoLoopMap.put("photo_stamp_off", Integer.valueOf(0x7f0b031e));
        mAEEPhotoLoopMap.put("photo_stamp_off", Integer.valueOf(0x7f0b031f));
        mAEEPhotoSelfTimerMap = new LinkedHashMap();
        mAEEPhotoSelfTimerMap.put("photo_selftimer_03s", Integer.valueOf(0x7f0b0321));
        mAEEPhotoSelfTimerMap.put("photo_selftimer_05s", Integer.valueOf(0x7f0b0322));
        mAEEPhotoSelfTimerMap.put("photo_selftimer_10s", Integer.valueOf(0x7f0b0323));
        mAEEPhotoStampMap = new LinkedHashMap();
        mAEEPhotoStampMap.put("photo_stamp_off", Integer.valueOf(0x7f0b0324));
        mAEEPhotoStampMap.put("photo_stamp_bot", Integer.valueOf(0x7f0b0325));
        mAEESetupKeyToneMap = new LinkedHashMap();
        mAEESetupKeyToneMap.put("setup_key_tone_off", Integer.valueOf(0x7f0b0326));
        mAEESetupKeyToneMap.put("setup_key_tone_med", Integer.valueOf(0x7f0b0327));
        mAEESetupKeyToneMap.put("setup_key_tone_sta", Integer.valueOf(0x7f0b0328));
        mAEESetupselflampeMap = new LinkedHashMap();
        mAEESetupselflampeMap.put("setup_selflamp_off", Integer.valueOf(0x7f0b0329));
        mAEESetupselflampeMap.put("setup_selflamp_on2", Integer.valueOf(0x7f0b032a));
        mAEESetupselflampeMap.put("setup_selflamp_on_", Integer.valueOf(0x7f0b032b));
        mAEESetupOsdMap = new LinkedHashMap();
        mAEESetupOsdMap.put("setup_osd_off", Integer.valueOf(0x7f0b032c));
        mAEESetupOsdMap.put("setup_osd_on_", Integer.valueOf(0x7f0b032d));
        mAEESetupLoopBackMap = new LinkedHashMap();
        mAEESetupLoopBackMap.put("setup_loop_back_off", Integer.valueOf(0x7f0b032e));
        mAEESetupLoopBackMap.put("setup_loop_back_on_", Integer.valueOf(0x7f0b032f));
        mAEESetupPowerOffMap = new LinkedHashMap();
        mAEESetupPowerOffMap.put("setup_poweroff_off", Integer.valueOf(0x7f0b0330));
        mAEESetupPowerOffMap.put("setup_poweroff_02m", Integer.valueOf(0x7f0b0331));
        mAEESetupPowerOffMap.put("setup_poweroff_05m", Integer.valueOf(0x7f0b0332));
        mAEESetupPowerOffMap.put("setup_poweroff_10m", Integer.valueOf(0x7f0b0333));
        mAEESetupSystemTypeMap = new LinkedHashMap();
        mAEESetupSystemTypeMap.put("setup_system_type_pal", Integer.valueOf(0x7f0b0334));
        mAEESetupSystemTypeMap.put("setup_system_type_nts", Integer.valueOf(0x7f0b0335));
        mAEESetupDetectFaceMap = new LinkedHashMap();
        mAEESetupDetectFaceMap.put("setup_detect_face_off", Integer.valueOf(0x7f0b0336));
        mAEESetupDetectFaceMap.put("setup_detect_face_on_", Integer.valueOf(0x7f0b0337));
        mAEESetupProtuneMap = new LinkedHashMap();
        mAEESetupProtuneMap.put("set_protune_off", Integer.valueOf(0x7f0b0338));
        mAEESetupProtuneMap.put("set_protune_on_", Integer.valueOf(0x7f0b0339));
        mAEESetupImageWbMap = new LinkedHashMap();
        mAEESetupImageWbMap.put("image_wb_auto", Integer.valueOf(0x7f0b033a));
        mAEESetupImageWbMap.put("image_wb_03m0", Integer.valueOf(0x7f0b033b));
        mAEESetupImageWbMap.put("image_wb_05m5", Integer.valueOf(0x7f0b033c));
        mAEESetupImageWbMap.put("image_wb_06m5", Integer.valueOf(0x7f0b033d));
        mAEESetupImageWbMap.put("image_wb_camr", Integer.valueOf(0x7f0b033e));
        mAEESetupImageContrastMap = new LinkedHashMap();
        mAEESetupImageContrastMap.put("image_contrast_stan", Integer.valueOf(0x7f0b033f));
        mAEESetupImageContrastMap.put("image_contrast_hard", Integer.valueOf(0x7f0b0340));
        mAEESetupImageISOMap = new LinkedHashMap();
        mAEESetupImageISOMap.put("image_iso_auto", Integer.valueOf(0x7f0b0342));
        mAEESetupImageISOMap.put("image_iso_lowl", Integer.valueOf(0x7f0b0343));
        mAEESetupLightModeMap = new LinkedHashMap();
        mAEESetupLightModeMap.put("set_light_aver", Integer.valueOf(0x7f0b0344));
        mAEESetupLightModeMap.put("set_light_cenz", Integer.valueOf(0x7f0b0345));
        mAEESetupLightModeMap.put("set_light_cenp", Integer.valueOf(0x7f0b0346));
        mAEESetupLightModeMap.put("set_light_cenz", Integer.valueOf(0x7f0b0345));
        mAEESetupLightModeMap.put("set_light_cenp", Integer.valueOf(0x7f0b0346));
        mAEESetupGesensorMap = new LinkedHashMap();
        mAEESetupGesensorMap.put("set_gsensor_off", Integer.valueOf(0x7f0b0349));
        mAEESetupGesensorMap.put("set_gsensor_on_", Integer.valueOf(0x7f0b034a));
        mAEELanguageMap = new LinkedHashMap();
        mAEELanguageMap.put("language_chs", Integer.valueOf(0x7f0b0381));
        mAEELanguageMap.put("language_cht", Integer.valueOf(0x7f0b0382));
        mAEELanguageMap.put("language_eng", Integer.valueOf(0x7f0b0383));
        mAEELanguageMap.put("language_rus", Integer.valueOf(0x7f0b0384));
        mAEELanguageMap.put("language_ger", Integer.valueOf(0x7f0b0385));
        mAEELanguageMap.put("language_jap", Integer.valueOf(0x7f0b0386));
        mAEELanguageMap.put("language_fre", Integer.valueOf(0x7f0b0387));
        mAEELanguageMap.put("language_ita", Integer.valueOf(0x7f0b0388));
        mAEELanguageMap.put("language_spa", Integer.valueOf(0x7f0b0389));
        mAEELanguageMap.put("language_por", Integer.valueOf(0x7f0b038a));
        mAEELanguageMap.put("language_dut", Integer.valueOf(0x7f0b038b));
        mAEELanguageMap.put("language_hun", Integer.valueOf(0x7f0b038c));
        mAEELanguageMap.put("language_dan", Integer.valueOf(0x7f0b038d));
        mAEELanguageMap.put("language_fin", Integer.valueOf(0x7f0b038e));
        mAEELanguageMap.put("language_nor", Integer.valueOf(0x7f0b038f));
        mAEELanguageMap.put("language_swe", Integer.valueOf(0x7f0b0390));
        mAEELanguageMap.put("language_gre", Integer.valueOf(0x7f0b0391));
        mAEELanguageMap.put("language_pol", Integer.valueOf(0x7f0b0392));
        mAEELanguageMap.put("language_kor", Integer.valueOf(0x7f0b0393));
        mAEELanguageMap.put("language_ukr", Integer.valueOf(0x7f0b0394));
        mAEELanguageMap.put("language_cze", Integer.valueOf(0x7f0b0395));
    }
}
