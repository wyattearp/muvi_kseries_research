// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


public class MError
{
    public final class MImgError extends MError
    {

        public static final int MERR_COMPONENT_NOTFOUND = 0x80000;
        public static final int MERR_DECODE_FAILED = 0x80009;
        public static final int MERR_IMAGE_BASE = 0x80000;
        public static final int MERR_ITEM_NO_EXIST = 0x80008;
        public static final int MERR_NOT_IMPLEMENTED = 0x80001;
        public static final int MERR_NOT_READY = 0x80003;
        public static final int MERR_NO_MORE = 0x80002;
        public static final int MERR_OUT_OF_RANGE = 0x80005;
        public static final int MERR_REACH_LIMIT = 0x80007;
        public static final int MERR_SAME_STATE = 0x80004;
        public static final int MERR_VERSION_MISMATCH = 0x80006;
        final MError this$0;

        public MImgError()
        {
            this$0 = MError.this;
            super();
        }
    }


    public static final int MERR_AUDIO_BASE = 24576;
    public static final int MERR_AUDIO_GENERAL = 24576;
    public static final int MERR_AUDIO_INPUT_BASE = 24626;
    public static final int MERR_AUDIO_INPUT_CLOSE = 24636;
    public static final int MERR_AUDIO_INPUT_OPEN = 24635;
    public static final int MERR_AUDIO_INPUT_PAUSE = 24637;
    public static final int MERR_AUDIO_INPUT_RECORDING = 24639;
    public static final int MERR_AUDIO_INPUT_RESUME = 24640;
    public static final int MERR_AUDIO_INPUT_STOP = 24638;
    public static final int MERR_AUDIO_OUTPUT_CLOSE = 24580;
    public static final int MERR_AUDIO_OUTPUT_GETVOL = 24579;
    public static final int MERR_AUDIO_OUTPUT_OPEN = 24577;
    public static final int MERR_AUDIO_OUTPUT_PAUSE = 24581;
    public static final int MERR_AUDIO_OUTPUT_PLAYING = 24583;
    public static final int MERR_AUDIO_OUTPUT_RESUME = 24584;
    public static final int MERR_AUDIO_OUTPUT_SETVOL = 24578;
    public static final int MERR_AUDIO_OUTPUT_STOP = 24582;
    public static final int MERR_BAD_STATE = 5;
    public static final int MERR_BASIC_BASE = 1;
    public static final int MERR_BUFFER_OVERFLOW = 9;
    public static final int MERR_BUFFER_UNDERFLOW = 10;
    public static final int MERR_CAM_BASE = 16384;
    public static final int MERR_CAM_CAPTURE_START = 16392;
    public static final int MERR_CAM_CAPTURE_STOP = 16393;
    public static final int MERR_CAM_FRAME_GET = 16388;
    public static final int MERR_CAM_GENERAL = 16384;
    public static final int MERR_CAM_INIT = 16385;
    public static final int MERR_CAM_PARAM_SET = 16391;
    public static final int MERR_CAM_PREVIEW_START = 16386;
    public static final int MERR_CAM_PREVIEW_STOP = 16387;
    public static final int MERR_CAM_PROPERTY_GET = 16390;
    public static final int MERR_CAM_PROPERTY_SET = 16389;
    public static final int MERR_COMPONENT_NOT_EXIST = 12;
    public static final int MERR_DISPLAY_ALREADY_INIT = 20481;
    public static final int MERR_DISPLAY_BASE = 20480;
    public static final int MERR_DISPLAY_DEVICE_FAIL = 20485;
    public static final int MERR_DISPLAY_DRAW_FAIL = 20486;
    public static final int MERR_DISPLAY_GENERAL = 20480;
    public static final int MERR_DISPLAY_INIT_FAIL = 20482;
    public static final int MERR_DISPLAY_NOT_INIT = 20484;
    public static final int MERR_DISPLAY_SHOWOVERLAY_FAIL = 20487;
    public static final int MERR_DISPLAY_UNINIT_FAIL = 20483;
    public static final int MERR_EXPIRED = 7;
    public static final int MERR_FILE_BASE = 4096;
    public static final int MERR_FILE_DELETE = 4105;
    public static final int MERR_FILE_EOF = 4099;
    public static final int MERR_FILE_EXIST = 4098;
    public static final int MERR_FILE_FULL = 4100;
    public static final int MERR_FILE_GENERAL = 4096;
    public static final int MERR_FILE_NOT_EXIST = 4097;
    public static final int MERR_FILE_OPEN = 4104;
    public static final int MERR_FILE_READ = 4102;
    public static final int MERR_FILE_RENAME = 4106;
    public static final int MERR_FILE_SEEK = 4101;
    public static final int MERR_FILE_WRITE = 4103;
    public static final int MERR_GLOBAL_DATA_NOT_EXIST = 13;
    public static final int MERR_HTTP_DATA_NOT_READY = 12293;
    public static final int MERR_HTTP_EOF = 12294;
    public static final int MERR_HTTP_GENERAL = 12292;
    public static final int MERR_HTTP_NOBUFFERS = 12297;
    public static final int MERR_HTTP_REQUEST_FAIL = 12296;
    public static final int MERR_HTTP_RESPONSE_300 = 12596;
    public static final int MERR_HTTP_RESPONSE_301 = 12597;
    public static final int MERR_HTTP_RESPONSE_302 = 12598;
    public static final int MERR_HTTP_RESPONSE_303 = 12599;
    public static final int MERR_HTTP_RESPONSE_304 = 12600;
    public static final int MERR_HTTP_RESPONSE_305 = 12601;
    public static final int MERR_HTTP_RESPONSE_307 = 12603;
    public static final int MERR_HTTP_RESPONSE_400 = 12696;
    public static final int MERR_HTTP_RESPONSE_401 = 12697;
    public static final int MERR_HTTP_RESPONSE_402 = 12698;
    public static final int MERR_HTTP_RESPONSE_403 = 12699;
    public static final int MERR_HTTP_RESPONSE_404 = 12700;
    public static final int MERR_HTTP_RESPONSE_405 = 12701;
    public static final int MERR_HTTP_RESPONSE_406 = 12702;
    public static final int MERR_HTTP_RESPONSE_407 = 12703;
    public static final int MERR_HTTP_RESPONSE_408 = 12704;
    public static final int MERR_HTTP_RESPONSE_409 = 12705;
    public static final int MERR_HTTP_RESPONSE_410 = 12706;
    public static final int MERR_HTTP_RESPONSE_411 = 12707;
    public static final int MERR_HTTP_RESPONSE_412 = 12708;
    public static final int MERR_HTTP_RESPONSE_413 = 12709;
    public static final int MERR_HTTP_RESPONSE_414 = 12710;
    public static final int MERR_HTTP_RESPONSE_415 = 12711;
    public static final int MERR_HTTP_RESPONSE_417 = 12713;
    public static final int MERR_HTTP_RESPONSE_500 = 12796;
    public static final int MERR_HTTP_RESPONSE_501 = 12797;
    public static final int MERR_HTTP_RESPONSE_502 = 12798;
    public static final int MERR_HTTP_RESPONSE_503 = 12799;
    public static final int MERR_HTTP_RESPONSE_504 = 12800;
    public static final int MERR_HTTP_RESPONSE_505 = 12801;
    public static final int MERR_HTTP_TIMEOUT = 12295;
    public static final int MERR_INVALID_PARAM = 2;
    public static final int MERR_KERNEL_BASE = 8192;
    public static final int MERR_KERNEL_GENERAL = 8192;
    public static final int MERR_NET_BASE = 12288;
    public static final int MERR_NET_GENERAL = 12288;
    public static final int MERR_NONE = 0;
    public static final int MERR_NO_DISKSPACE = 11;
    public static final int MERR_NO_MEMORY = 4;
    public static final int MERR_SOCKET_CONNECT = 12291;
    public static final int MERR_SOCKET_HOST_NOT_FOUND = 12300;
    public static final int MERR_SOCKET_MSGSIZE = 12298;
    public static final int MERR_SOCKET_OPEN = 12301;
    public static final int MERR_SOCKET_READ = 12289;
    public static final int MERR_SOCKET_TIMEDOUT = 12299;
    public static final int MERR_SOCKET_WRITE = 12290;
    public static final int MERR_THREAD_CREATE = 8193;
    public static final int MERR_THREAD_SET_PRIORITY = 8194;
    public static final int MERR_UNKNOWN = 1;
    public static final int MERR_UNSUPPORTED = 3;
    public static final int MERR_USER_CANCEL = 6;
    public static final int MERR_USER_PAUSE = 8;
    public static final int MOK;

    public MError()
    {
    }
}
