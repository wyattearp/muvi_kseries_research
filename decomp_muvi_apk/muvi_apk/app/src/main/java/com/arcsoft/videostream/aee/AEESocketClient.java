// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.Context;
import com.arcsoft.mediaplus.socket.PlainSocket;
import com.arcsoft.mediaplus.socket.StreamSocket;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEUtilDef

public class AEESocketClient
{
    public class ConfigDATA
    {

        public static final int TYPE_INDEX_BOTTOM = 3;
        public static final int TYPE_INDEX_MID = 2;
        public static final int TYPE_INDEX_TOP = 1;
        public boolean isTypeTitle;
        public String name;
        public String options[];
        public String permission;
        public int stateIndex;
        public String stateVal;
        final AEESocketClient this$0;
        public String type;
        public int typeIndex;

        public String toString()
        {
            return (new StringBuilder()).append("name = ").append(name).append("; stateVal = ").append(stateVal).toString();
        }

        public ConfigDATA()
        {
            this$0 = AEESocketClient.this;
            super();
            type = null;
            name = null;
            stateVal = null;
            stateIndex = -1;
            isTypeTitle = false;
            options = null;
            permission = null;
            typeIndex = -1;
        }
    }

    public static interface OnRequestConfigListener
    {

        public abstract void onRequestConfigFinished(String s);
    }

    public static interface OnRequestRespondsListener
    {

        public abstract void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2);
    }

    private class RequestConfigThread extends Thread
    {

        private boolean stop;
        final AEESocketClient this$0;

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
            int i;
            int j;
            i = 0;
            j = 0;
_L8:
            if (!stop)
            {
                break MISSING_BLOCK_LABEL_18;
            }
            this;
            JVM INSTR monitorexit ;
            j;
            return;
            HttpURLConnection httpurlconnection = null;
            URL url;
            Log.e("SocketClient", "RequestConfigThread *******************");
            url = new URL("http://192.168.42.1/pref/config");
            InputStream inputstream;
            httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setConnectTimeout(3000);
            httpurlconnection.setReadTimeout(3000);
            httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
            httpurlconnection.setRequestMethod("GET");
            httpurlconnection.connect();
            inputstream = httpurlconnection.getInputStream();
            if (inputstream == null) goto _L2; else goto _L1
_L1:
            StringBuilder stringbuilder;
            InputStreamReader inputstreamreader;
            stringbuilder = new StringBuilder();
            inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
_L5:
            int l = inputstreamreader.read();
            if (l == -1) goto _L4; else goto _L3
_L3:
            stringbuilder.append((char)l);
              goto _L5
            FileNotFoundException filenotfoundexception1;
            filenotfoundexception1;
            j;
_L13:
            if (httpurlconnection == null)
            {
                break MISSING_BLOCK_LABEL_160;
            }
            httpurlconnection.disconnect();
            if (mOnRequestConfigListener != null)
            {
                mOnRequestConfigListener.onRequestConfigFinished(null);
            }
            this;
            JVM INSTR monitorexit ;
            return;
_L10:
            this;
            JVM INSTR monitorexit ;
            Exception exception;
            throw exception;
_L4:
            String s;
            inputstreamreader.close();
            inputstream.close();
            httpurlconnection.disconnect();
            s = stringbuilder.toString();
            Log.e("SocketClient", (new StringBuilder()).append("config.toString() = ").append(s).toString());
            if (s == null) goto _L2; else goto _L6
_L6:
            if (s.trim().equals("")) goto _L2; else goto _L7
_L7:
            decodeData(s);
            if (mOnRequestConfigListener != null)
            {
                mOnRequestConfigListener.onRequestConfigFinished(s);
            }
            this;
            JVM INSTR monitorexit ;
            j;
            return;
_L2:
            int k;
            k = j + 1;
            if (j != 5)
            {
                break MISSING_BLOCK_LABEL_335;
            }
            httpurlconnection.disconnect();
            if (mOnRequestConfigListener != null)
            {
                mOnRequestConfigListener.onRequestConfigFinished(null);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            Thread.sleep(500L);
_L9:
            j = k;
              goto _L8
            SocketTimeoutException sockettimeoutexception;
            sockettimeoutexception;
            j;
_L12:
            sockettimeoutexception.printStackTrace();
            if (httpurlconnection == null)
            {
                break MISSING_BLOCK_LABEL_366;
            }
            httpurlconnection.disconnect();
            if (mOnRequestConfigListener != null)
            {
                mOnRequestConfigListener.onRequestConfigFinished(null);
            }
            this;
            JVM INSTR monitorexit ;
            return;
_L11:
            Exception exception1;
            exception1.printStackTrace();
            if (i != 1)
            {
                break MISSING_BLOCK_LABEL_438;
            }
            if (httpurlconnection == null)
            {
                break MISSING_BLOCK_LABEL_412;
            }
            httpurlconnection.disconnect();
            if (mOnRequestConfigListener != null)
            {
                mOnRequestConfigListener.onRequestConfigFinished(null);
            }
            this;
            JVM INSTR monitorexit ;
            return;
            i++;
              goto _L9
            exception;
            j;
              goto _L10
            exception1;
            k = j;
              goto _L11
            exception1;
              goto _L11
            sockettimeoutexception;
            j;
              goto _L12
            sockettimeoutexception;
              goto _L12
            FileNotFoundException filenotfoundexception;
            filenotfoundexception;
            j;
            httpurlconnection = null;
              goto _L13
            FileNotFoundException filenotfoundexception2;
            filenotfoundexception2;
              goto _L13
            exception;
              goto _L10
            exception1;
            k = j;
            httpurlconnection = null;
              goto _L11
        }

        public void stopRequest()
        {
            stop = true;
        }

        private RequestConfigThread()
        {
            this$0 = AEESocketClient.this;
            super();
            stop = false;
        }

    }

    private class RequestRespondsThread extends Thread
    {

        private static final int MAX_GET_CURRENT_COUNT = 5;
        private int countException;
        private int curCmd;
        private boolean isRecting;
        private boolean stop;
        final AEESocketClient this$0;

        public void run()
        {
            this;
            JVM INSTR monitorenter ;
            Log.e("SocketClient", "testP RequestRespondsThread start --------------");
_L17:
            if (!stop) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
_L2:
            int i;
            String s;
            int j;
            String s1;
            i = 1;
            s = null;
            j = 0;
            s1 = null;
            String s2;
            if (mCurCmdType == -1 && curCmd != -1)
            {
                setCurCMD(-1);
            }
            Log.e("SocketClient", (new StringBuilder()).append("RequestRespondsThread mCurCmdType = ").append(mCurCmdType).toString());
            s2 = readLine();
            Log.e("SocketClient", (new StringBuilder()).append("testP RequestRespondsThread respondLine = ").append(s2).toString());
            if (s2 == null) goto _L4; else goto _L3
_L3:
            JSONObject jsonobject = new JSONObject(s2);
            Log.e("SocketClient", (new StringBuilder()).append("testP RequestRespondsThread jsonObj = ").append(jsonobject).toString());
            if (!jsonobject.has("rval")) goto _L6; else goto _L5
_L5:
            i = jsonobject.getInt("rval");
_L21:
            Log.e("SocketClient", (new StringBuilder()).append("testP getRespondParams() errNum = ").append(i).toString());
            mCurCmdType;
            JVM INSTR tableswitch 268435457 268435512: default 452
        //                       268435457 691
        //                       268435458 452
        //                       268435459 1348
        //                       268435460 1383
        //                       268435461 452
        //                       268435462 1295
        //                       268435463 452
        //                       268435464 1328
        //                       268435465 452
        //                       268435466 452
        //                       268435467 452
        //                       268435468 452
        //                       268435469 452
        //                       268435470 452
        //                       268435471 452
        //                       268435472 452
        //                       268435473 452
        //                       268435474 452
        //                       268435475 452
        //                       268435476 1310
        //                       268435477 452
        //                       268435478 452
        //                       268435479 452
        //                       268435480 452
        //                       268435481 452
        //                       268435482 452
        //                       268435483 452
        //                       268435484 452
        //                       268435485 452
        //                       268435486 452
        //                       268435487 452
        //                       268435488 979
        //                       268435489 979
        //                       268435490 979
        //                       268435491 979
        //                       268435492 979
        //                       268435493 1218
        //                       268435494 979
        //                       268435495 979
        //                       268435496 979
        //                       268435497 979
        //                       268435498 979
        //                       268435499 979
        //                       268435500 979
        //                       268435501 979
        //                       268435502 979
        //                       268435503 979
        //                       268435504 979
        //                       268435505 979
        //                       268435506 979
        //                       268435507 979
        //                       268435508 979
        //                       268435509 1135
        //                       268435510 1135
        //                       268435511 1135
        //                       268435512 1135;
               goto _L7 _L8 _L7 _L9 _L10 _L7 _L11 _L7 _L12 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L13 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L7 _L14 _L14 _L14 _L14 _L14 _L15 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L14 _L16 _L16 _L16 _L16
_L7:
            mCurCmdType = -1;
_L26:
            if (mOnRequestRespondsListener != null)
            {
                countException = 0;
                mOnRequestRespondsListener.onRequestRespondsFinished(curCmd, s2, i, s, j, s1);
            }
_L36:
            Exception exception;
            Exception exception1;
            IOException ioexception;
            AEESocketClient aeesocketclient;
            JSONException jsonexception;
            boolean flag;
            boolean flag1;
            long l;
            boolean flag2;
            boolean flag3;
            JSONArray jsonarray;
            if (mCurCmdType == -1)
            {
                l = 300L;
            } else
            {
                l = 100L;
            }
            Thread.sleep(l);
              goto _L17
            jsonexception;
_L40:
            jsonexception.printStackTrace();
            mCurCmdType = -1;
            if (mOnRequestRespondsListener != null)
            {
                mOnRequestRespondsListener.onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
            }
              goto _L17
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
_L6:
            flag = jsonobject.has("msg_id");
            s = null;
            if (!flag) goto _L19; else goto _L18
_L18:
            s = jsonobject.getString("msg_id");
_L19:
            flag1 = jsonobject.has("param_size");
            j = 0;
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            j = jsonobject.getInt("param_size");
            if (!s.contains("16777217") && !s.contains("16777218")) goto _L21; else goto _L20
_L20:
            if (mOnRequestRespondsListener != null)
            {
                countException = 0;
                mOnRequestRespondsListener.onRequestRespondsFinished(-1, s2, i, s, j, null);
            }
              goto _L17
_L8:
            if (i != 0) goto _L23; else goto _L22
_L22:
            flag3 = jsonobject.has("param");
            jsonarray = null;
            if (!flag3) goto _L25; else goto _L24
_L24:
            jsonarray = jsonobject.getJSONArray("param");
_L25:
            if (jsonarray.length() >= 1)
            {
                mTokenId = ((Integer)jsonarray.get(0)).intValue();
            }
            Log.e("FENG", (new StringBuilder()).append("FENG token = ").append(jsonarray).toString());
            Log.e("FENG", (new StringBuilder()).append("FENG mTokenId = ").append(mTokenId).toString());
            s = (new StringBuilder()).append(mTokenId).append("").toString();
_L23:
            mCurCmdType = -1;
            s1 = null;
              goto _L26
            ioexception;
_L37:
            ioexception.printStackTrace();
            if (mCurCmdType != -1 && !isRecting)
            {
                countException = 1 + countException;
            }
            if (mOnRequestRespondsListener == null || countException != 5) goto _L28; else goto _L27
_L27:
            mCurCmdType = -1;
            aeesocketclient = AEESocketClient.mSocketClient;
            if (aeesocketclient == null) goto _L30; else goto _L29
_L29:
            AEESocketClient.mSocketClient.close();
_L38:
            AEESocketClient.mSocketClient.stopRespondParams();
            AEESocketClient.mSocketClient.releaseCurTokenId();
            AEESocketClient.mSocketClient.realseClient();
            AEESocketClient.mSocketClient = null;
_L30:
            mOnRequestRespondsListener.onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
            countException = 0;
              goto _L17
_L14:
            if (i != 0) goto _L32; else goto _L31
_L31:
            if (jsonobject.has("param"))
            {
                s = jsonobject.getString("param");
                if (s.contains("record"))
                {
                    isRecting = true;
                }
            }
            if (jsonobject.has("param_size"))
            {
                j = jsonobject.getInt("param_size");
            }
_L32:
            mCurCmdType = -1;
            s1 = null;
              goto _L26
            exception1;
_L39:
            exception1.printStackTrace();
            if (mCurCmdType != -1)
            {
                countException = 1 + countException;
            }
            if (mOnRequestRespondsListener != null && countException == 5)
            {
                mCurCmdType = -1;
                mOnRequestRespondsListener.onRequestRespondsFinished(curCmd, null, 2, null, 0, null);
                countException = 0;
            }
              goto _L17
_L16:
            s1 = null;
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_1206;
            }
            if (jsonobject.has("param"))
            {
                s = jsonobject.getString("param");
            }
            if (jsonobject.has("param_size"))
            {
                j = jsonobject.getInt("param_size");
            }
            flag2 = jsonobject.has("settable");
            s1 = null;
            if (!flag2)
            {
                break MISSING_BLOCK_LABEL_1206;
            }
            s1 = jsonobject.getString("settable");
            mCurCmdType = -1;
              goto _L26
_L15:
            if (i != 0)
            {
                break MISSING_BLOCK_LABEL_1280;
            }
            if (jsonobject.has("param"))
            {
                s = jsonobject.getString("param");
            }
            if (jsonobject.has("param_size"))
            {
                j = jsonobject.getInt("param_size");
            }
            if (s == null)
            {
                break MISSING_BLOCK_LABEL_1280;
            }
            if (s.contains("record"))
            {
                isRecting = true;
            }
            mCurCmdType = -1;
            s1 = null;
              goto _L26
_L11:
            mCurCmdType = -1;
            s1 = null;
              goto _L26
_L13:
            s1 = null;
            if (i != 0) goto _L26; else goto _L33
_L33:
            isRecting = true;
            s1 = null;
              goto _L26
_L12:
            mCurCmdType = -1;
            isRecting = false;
            s1 = null;
              goto _L26
_L9:
            if (i != 0) goto _L35; else goto _L34
_L34:
            isRecting = true;
            s1 = null;
              goto _L26
_L35:
            mCurCmdType = -1;
            isRecting = false;
            s1 = null;
              goto _L26
_L10:
            mCurCmdType = -1;
            isRecting = false;
            s1 = null;
              goto _L26
_L4:
            mCurCmdType = -1;
            if (mOnRequestRespondsListener != null)
            {
                mOnRequestRespondsListener.onRequestRespondsFinished(curCmd, s2, 1, null, 0, null);
            }
              goto _L36
            ioexception;
              goto _L37
            IOException ioexception1;
            ioexception1;
            ioexception1.printStackTrace();
              goto _L38
_L28:
            SystemUtils.safeSleep(10L);
              goto _L17
            exception1;
              goto _L39
            jsonexception;
              goto _L40
        }

        public void setCurCMD(int i)
        {
            curCmd = i;
        }

        public void stopRequest()
        {
            stop = true;
        }

        private RequestRespondsThread()
        {
            this$0 = AEESocketClient.this;
            super();
            stop = false;
            curCmd = -1;
            countException = 0;
            isRecting = false;
        }

    }


    private static final String ARG_AEE_STR_MSG_ID = "msg_id";
    private static final String ARG_AEE_STR_PARAM = "param";
    private static final String ARG_AEE_STR_PARAM_SIZE = "param_size";
    private static final String ARG_AEE_STR_READONLY = "readonly";
    private static final String ARG_AEE_STR_RVAL = "rval";
    private static final String ARG_AEE_STR_SETTABLE = "settable";
    private static final String ARG_AEE_STR_TOKEN = "token";
    private static final String ARG_AEE_STR_TYPE = "type";
    public static final int CODE_AEE_CMD_DEFAULT = 31;
    public static final int CODE_AEE_CMD_DV_MODE = 18;
    public static final int CODE_AEE_CMD_DZOOM_TELE = 28;
    public static final int CODE_AEE_CMD_DZOOM_WIDE = 27;
    public static final int CODE_AEE_CMD_FORMAT = 11;
    public static final int CODE_AEE_CMD_FORMAT_SD = 11;
    public static final int CODE_AEE_CMD_GET_CONFIG = 8;
    public static final int CODE_AEE_CMD_GET_DV_SETTING = 7;
    public static final int CODE_AEE_CMD_PHOTO_MODE = 19;
    public static final int CODE_AEE_CMD_POWEROFF = 32;
    public static final int CODE_AEE_CMD_POWERON = 33;
    public static final int CODE_AEE_CMD_RECORD_START = 3;
    public static final int CODE_AEE_CMD_RECORD_STOP = 4;
    public static final int CODE_AEE_CMD_RECORD_VOICE = 17;
    public static final int CODE_AEE_CMD_RESET_VF = 9;
    public static final int CODE_AEE_CMD_ROTATE_OFF = 30;
    public static final int CODE_AEE_CMD_ROTATE_ON = 29;
    public static final int CODE_AEE_CMD_SESSION_START = 1;
    public static final int CODE_AEE_CMD_SESSION_STOP = 2;
    public static final int CODE_AEE_CMD_SET_DV_SETTING = 6;
    public static final int CODE_AEE_CMD_START_ENCODING = 12;
    public static final int CODE_AEE_CMD_STOP_ENCODING = 13;
    public static final int CODE_AEE_CMD_STOP_LAPSEPHOTO = 26;
    public static final int CODE_AEE_CMD_TAKE_FASTPHOTO = 24;
    public static final int CODE_AEE_CMD_TAKE_LAPSEPHOTO = 23;
    public static final int CODE_AEE_CMD_TAKE_PHOTO = 5;
    public static final int CODE_AEE_CMD_VOICE_MODE = 20;
    private static final String CONFIG_HTTP_URL = "http://192.168.42.1/pref/config";
    private static final int DEFAULT_VAL = 0;
    private static final String ENCODEING = "UTF-8";
    private static final String EOL = "\r\n";
    public static final String ERR_MSG_CAMERA_NOTIFY = "16777218";
    public static final String ERR_MSG_CARD_FULL = "16777220";
    public static final String ERR_MSG_HDMI_CABLE = "16777219";
    public static final String ERR_MSG_TAKE_PHOTO_CONS = "16777217";
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
    public static final int ERR_TYPE_NO_SDCARD = -19;
    public static final int ERR_TYPE_NULL = 1;
    public static final int ERR_TYPE_START_SEESION_FAIL = -3;
    public static final int ERR_TYPE_SUC = 0;
    public static final int ERR_TYPE_UNKNOWN = -1;
    public static final int ERR_TYPE_WRONG_TOKEN_ID = -4;
    private static final int INVALID_VAL = -1;
    private static final char LEFT_BRACE = 123;
    private static final int LONG_TIMEOUT = 6000;
    public static final int PARAMS_SEND_CMD_DOU = 3;
    public static final int PARAMS_SEND_CMD_NO_TOKEN = 4;
    public static final int PARAMS_SEND_CMD_SUC = 1;
    public static final int PARAMS_SEND_CMD_SYN = 2;
    private static final String REMOTE_HOST = "192.168.42.1";
    private static final int REQUEST_RES_TIME_MAX = 300;
    private static final int REQUEST_RES_TIME_SPACE = 100;
    private static final int REQUEST_RES_TIME_SPACE_LONG = 300;
    private static final int REQUEST_TIME_MAX = 2500;
    private static final int REQUEST_TIME_OUT = 3000;
    private static final int REQUEST_TIME_SPACE = 500;
    private static final char RIGHT_BRACE = 125;
    private static final int SHORT_TIMEOUT = 500;
    public static final int SOCKET_PORT = 7878;
    public static final int TYPE_DV_INFO_BATTERY = 2;
    public static final int TYPE_DV_INFO_CUSTOMER_DEVICE = 18;
    public static final int TYPE_DV_INFO_DEVICE = 11;
    public static final int TYPE_DV_INFO_NAME = 4;
    public static final int TYPE_DV_INFO_NUM = 0;
    public static final int TYPE_DV_INFO_PHOTO_CON_FAST = 16;
    public static final int TYPE_DV_INFO_PHOTO_NUM = 9;
    public static final int TYPE_DV_INFO_PHOTO_SHOT_MODE = 15;
    public static final int TYPE_DV_INFO_PHOTO_SIZE = 14;
    public static final int TYPE_DV_INFO_PHOTO_SPACE = 7;
    public static final int TYPE_DV_INFO_PHOTO_TLM = 17;
    public static final int TYPE_DV_INFO_SPACE = 3;
    public static final int TYPE_DV_INFO_VERSION = 5;
    public static final int TYPE_DV_INFO_VIDEO_FOV = 13;
    public static final int TYPE_DV_INFO_VIDEO_NUM = 8;
    public static final int TYPE_DV_INFO_VIDEO_RESOLUTION = 12;
    public static final int TYPE_DV_INFO_VIDEO_SPACE = 6;
    public static final int TYPE_DV_INFO_VOICE_NUM = 10;
    public static final int TYPE_DV_INFO_WIFI = 1;
    private static AEESocketClient mSocketClient = null;
    private static final String strUrl = "rtsp://192.168.42.1/live";
    private final int REQUEST_EXCETION_COUNT_MAX = 1;
    private final String TAG = "SocketClient";
    private final int currentMode = -1;
    private boolean isConnected;
    private boolean isExcuting;
    private boolean isNeedFreshFiles;
    private boolean isNeedStartEncoding;
    private boolean isPreviewClosed;
    private boolean isPreviewNotSupport;
    private boolean isRolloverON;
    private boolean isStreamConnected;
    private HashMap mArrayData;
    protected StreamSocket mControlSock;
    private String mCurCamMode;
    private int mCurCmdType;
    private int mCurConParams;
    private int mCurEXEState;
    private String mCurFOVMode;
    private int mCurState;
    private int mCurTimer;
    private String mDVInfoStrings[];
    private boolean mHasConfig;
    private boolean mIsFirstStartPreview;
    private int mNextCmdTYpe;
    private OnRequestConfigListener mOnRequestConfigListener;
    private OnRequestRespondsListener mOnRequestRespondsListener;
    private String mParamStr;
    protected Reader mReader;
    protected InetAddress mRemoteAddr;
    private RequestConfigThread mRequestConfigThread;
    private RequestRespondsThread mRequestRespondsThread;
    private int mTokenId;
    protected Writer mWriter;

    public AEESocketClient()
        throws UnknownHostException
    {
        mControlSock = null;
        mWriter = null;
        mReader = null;
        isConnected = false;
        mCurCamMode = null;
        mCurFOVMode = null;
        mCurCmdType = -1;
        mNextCmdTYpe = 0;
        mTokenId = 0;
        mParamStr = null;
        mArrayData = null;
        mOnRequestConfigListener = null;
        mOnRequestRespondsListener = null;
        mRequestRespondsThread = null;
        mRequestConfigThread = null;
        isNeedStartEncoding = false;
        isRolloverON = false;
        isExcuting = false;
        isStreamConnected = false;
        isPreviewClosed = false;
        isPreviewNotSupport = false;
        mCurState = -1;
        mCurEXEState = -1;
        mCurConParams = -1;
        mDVInfoStrings = null;
        mCurTimer = 0;
        isNeedFreshFiles = false;
        mHasConfig = true;
        mIsFirstStartPreview = true;
        mRemoteAddr = InetAddress.getByName("192.168.42.1");
        Log.e("testP", (new StringBuilder()).append("mRemoteAddr:").append(mRemoteAddr.toString()).toString());
    }

    private JSONObject createAEECMD(int i, int j, int k, String s, String s1)
    {
        if (s == null && k == -1)
        {
            k = 0;
        }
        if (k == -1)
        {
            k = s.length();
        }
        JSONObject jsonobject = new JSONObject();
        try
        {
            jsonobject.put("msg_id", i);
            jsonobject.put("token", j);
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return jsonobject;
        }
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        jsonobject.put("type", s1);
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_80;
        }
        jsonobject.put("param", s);
        jsonobject.put("param_size", k);
        return jsonobject;
    }

    private void decodeData(String s)
    {
        String as[];
        Log.v("zdf", (new StringBuilder()).append("decodeData, configStr = ").append(s).toString());
        as = s.split("\n");
        if (as.length <= 0) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        mArrayData.clear();
        i = 0;
        j = -1;
_L9:
        ConfigDATA configdata1;
        int l;
        if (i >= as.length)
        {
            break; /* Loop/switch isn't completed */
        }
        String s1 = as[i];
        Log.e("SocketClient", (new StringBuilder()).append("typeStr = ").append(s1).toString());
        if (!s1.contains("="))
        {
            ConfigDATA configdata = new ConfigDATA();
            configdata.name = s1;
            configdata.isTypeTitle = true;
            mArrayData.put(configdata.name, configdata);
            i++;
            j = 1;
            continue; /* Loop/switch isn't completed */
        }
        if (!s1.contains("#"))
        {
            continue; /* Loop/switch isn't completed */
        }
        configdata1 = new ConfigDATA();
        configdata1.isTypeTitle = false;
        int k;
        String s2;
        int i1;
        if (s1.contains("="))
        {
            k = s1.indexOf("=");
        } else
        {
            k = s1.indexOf(":");
        }
        configdata1.name = s1.substring(1, k);
        Log.e("SocketClient", (new StringBuilder()).append("data.name = ").append(configdata1.name).toString());
        if (k + 1 >= s1.length())
        {
            configdata1.stateVal = null;
        } else
        {
            configdata1.stateVal = s1.substring(k + 1);
            configdata1.stateVal = configdata1.stateVal.trim();
        }
        Log.e("SocketClient", (new StringBuilder()).append("data.stateVal = ").append(configdata1.stateVal).toString());
        l = i + 1;
        if (l >= as.length) goto _L4; else goto _L3
_L3:
        if (as[l].contains("#"))
        {
            break MISSING_BLOCK_LABEL_757;
        }
        s2 = as[l];
        configdata1.options = s2.substring(1 + s2.indexOf("=")).split(";");
        for (i1 = 0; i1 < configdata1.options.length; i1++)
        {
            configdata1.options[i1] = configdata1.options[i1].trim();
        }

        if (configdata1.stateVal != null) goto _L6; else goto _L5
_L5:
        configdata1.stateVal = configdata1.options[0];
        configdata1.stateIndex = 0;
_L7:
        String s3 = as[i + 2];
        configdata1.permission = s3.substring(1 + s3.indexOf("="));
        Log.e("SocketClient", (new StringBuilder()).append("data.permission = ").append(configdata1.permission).toString());
        int k1 = i + 3;
        int j1;
        if (k1 >= as.length || !as[k1].contains("="))
        {
            configdata1.typeIndex = 3;
            j = -1;
        } else
        {
            configdata1.typeIndex = j;
            j = 2;
        }
        mArrayData.put(configdata1.name, configdata1);
        i += 3;
_L4:
        if (configdata1.name.contains("video_fov"))
        {
            setDVInfo(13, configdata1.stateVal);
        } else
        if (configdata1.name.contains("video_resolution"))
        {
            setDVInfo(12, configdata1.stateVal);
        } else
        if (configdata1.name.contains("photo_size"))
        {
            setDVInfo(14, configdata1.stateVal);
        } else
        if (configdata1.name.contains("photo_shot_mode"))
        {
            setDVInfo(15, configdata1.stateVal);
        } else
        if (configdata1.name.contains("photo_continue_fast"))
        {
            setDVInfo(16, configdata1.stateVal);
        } else
        if (configdata1.name.contains("photo_tlm"))
        {
            setDVInfo(17, configdata1.stateVal);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        j1 = 0;
_L8:
label0:
        {
            if (j1 < configdata1.options.length)
            {
                Log.e("SocketClient", (new StringBuilder()).append("data.options[").append(j1).append("]=").append(configdata1.options[j1]).toString());
                if (!configdata1.options[j1].equals(configdata1.stateVal))
                {
                    break label0;
                }
                configdata1.stateIndex = j1;
                Log.e("SocketClient", (new StringBuilder()).append("data.stateIndex = ").append(j1).toString());
            }
            if (j1 == configdata1.options.length)
            {
                configdata1.stateVal = configdata1.options[0];
                configdata1.stateIndex = 0;
            }
        }
          goto _L7
        j1++;
          goto _L8
        if (configdata1.stateVal == null)
        {
            configdata1.stateIndex = 0;
        }
        if (l >= as.length || !as[l].contains("="))
        {
            configdata1.typeIndex = 3;
            j = -1;
        } else
        {
            configdata1.typeIndex = j;
            j = 2;
        }
        if (configdata1.name.contains("manfacturer"))
        {
            setDVInfo(4, configdata1.stateVal);
        } else
        if (configdata1.name.contains("module"))
        {
            setDVInfo(11, configdata1.stateVal);
        } else
        if (configdata1.name.contains("version"))
        {
            setDVInfo(5, configdata1.stateVal);
        }
        mArrayData.put(configdata1.name, configdata1);
        i++;
          goto _L4
        if (true) goto _L9; else goto _L2
_L2:
    }

    public static String getAEEUrl()
    {
        return "rtsp://192.168.42.1/live";
    }

    private String getCommand(int i, String s)
    {
        JSONObject jsonobject;
        Log.e("SocketClient", (new StringBuilder()).append("getCommand commandType = ").append(i).append(" param = ").append(s).toString());
        jsonobject = null;
        i;
        JVM INSTR tableswitch 268435457 268435513: default 284
    //                   268435457 290
    //                   268435458 304
    //                   268435459 813
    //                   268435460 830
    //                   268435461 864
    //                   268435462 847
    //                   268435463 882
    //                   268435464 900
    //                   268435465 936
    //                   268435466 954
    //                   268435467 972
    //                   268435468 1008
    //                   268435469 1026
    //                   268435470 1044
    //                   268435471 1062
    //                   268435472 1080
    //                   268435473 1098
    //                   268435474 1152
    //                   268435475 1175
    //                   268435476 918
    //                   268435477 990
    //                   268435478 1116
    //                   268435479 284
    //                   268435480 284
    //                   268435481 284
    //                   268435482 284
    //                   268435483 284
    //                   268435484 284
    //                   268435485 284
    //                   268435486 284
    //                   268435487 1134
    //                   268435488 569
    //                   268435489 713
    //                   268435490 733
    //                   268435491 753
    //                   268435492 773
    //                   268435493 793
    //                   268435494 429
    //                   268435495 449
    //                   268435496 469
    //                   268435497 489
    //                   268435498 509
    //                   268435499 529
    //                   268435500 549
    //                   268435501 569
    //                   268435502 569
    //                   268435503 569
    //                   268435504 569
    //                   268435505 569
    //                   268435506 569
    //                   268435507 613
    //                   268435508 633
    //                   268435509 321
    //                   268435510 343
    //                   268435511 365
    //                   268435512 387
    //                   268435513 409;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35 _L36 _L37 _L25 _L25 _L25 _L25 _L25 _L25 _L38 _L39 _L40 _L41 _L42 _L43 _L44
_L1:
        return jsonobject.toString();
_L2:
        jsonobject = createAEECMD(1, 0, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L3:
        jsonobject = createAEECMD(2, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L40:
        jsonobject = createAEECMD(6, mTokenId, 2, "on", "dual streams");
        continue; /* Loop/switch isn't completed */
_L41:
        jsonobject = createAEECMD(6, mTokenId, 4, "rtsp", "stream type");
        continue; /* Loop/switch isn't completed */
_L42:
        jsonobject = createAEECMD(6, mTokenId, 2, "on", "streaming");
        continue; /* Loop/switch isn't completed */
_L43:
        jsonobject = createAEECMD(6, mTokenId, 3, "off", "video_stamp");
        continue; /* Loop/switch isn't completed */
_L44:
        jsonobject = createAEECMD(6, mTokenId, 3, s, "setup_time");
        continue; /* Loop/switch isn't completed */
_L31:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_dv_fs");
        continue; /* Loop/switch isn't completed */
_L32:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_dv_info");
        continue; /* Loop/switch isn't completed */
_L33:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_dv_pid");
        continue; /* Loop/switch isn't completed */
_L34:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_wifi_kit");
        continue; /* Loop/switch isn't completed */
_L35:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_dv_bat");
        continue; /* Loop/switch isn't completed */
_L36:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_dv_mode");
        continue; /* Loop/switch isn't completed */
_L37:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "get_video_time");
        continue; /* Loop/switch isn't completed */
_L25:
        Log.e("SocketClient", (new StringBuilder()).append("getCommand PARAM_AEE_CMD_SET_DV_SETTING params = ").append(s).toString());
        jsonobject = createAEECMD(7, mTokenId, 0, null, s);
        continue; /* Loop/switch isn't completed */
_L38:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "video_flip_rotate");
        continue; /* Loop/switch isn't completed */
_L39:
        Log.e("SocketClient", (new StringBuilder()).append("getCommand PARAM_AEE_CMD_SET_DV_SETTING params = ").append(s).toString());
        String as[] = s.split(";");
        int j = s.length();
        jsonobject = null;
        if (j > 1)
        {
            jsonobject = createAEECMD(6, mTokenId, as[1].length(), as[1], as[0]);
        }
        continue; /* Loop/switch isn't completed */
_L26:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "dual streams");
        continue; /* Loop/switch isn't completed */
_L27:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "stream type");
        continue; /* Loop/switch isn't completed */
_L28:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "streaming");
        continue; /* Loop/switch isn't completed */
_L29:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "video_stamp");
        continue; /* Loop/switch isn't completed */
_L30:
        jsonobject = createAEECMD(7, mTokenId, 0, null, "app status");
        continue; /* Loop/switch isn't completed */
_L4:
        jsonobject = createAEECMD(3, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L5:
        jsonobject = createAEECMD(4, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L7:
        jsonobject = createAEECMD(5, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L6:
        jsonobject = createAEECMD(17, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L8:
        jsonobject = createAEECMD(24, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L9:
        jsonobject = createAEECMD(26, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L21:
        jsonobject = createAEECMD(23, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L10:
        jsonobject = createAEECMD(27, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L11:
        jsonobject = createAEECMD(28, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L12:
        jsonobject = createAEECMD(18, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L22:
        jsonobject = createAEECMD(19, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L13:
        jsonobject = createAEECMD(20, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L14:
        jsonobject = createAEECMD(29, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L15:
        jsonobject = createAEECMD(30, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L16:
        jsonobject = createAEECMD(11, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L17:
        jsonobject = createAEECMD(31, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L18:
        jsonobject = createAEECMD(32, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L23:
        jsonobject = createAEECMD(33, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L24:
        jsonobject = createAEECMD(8, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L19:
        isNeedStartEncoding = false;
        jsonobject = createAEECMD(12, mTokenId, 0, null, null);
        continue; /* Loop/switch isn't completed */
_L20:
        isNeedStartEncoding = true;
        jsonobject = createAEECMD(13, mTokenId, 0, null, null);
        if (true) goto _L1; else goto _L45
_L45:
    }

    public static AEESocketClient getInstanceClient()
        throws IOException
    {
        if (mSocketClient == null)
        {
            mSocketClient = new AEESocketClient();
        }
        return mSocketClient;
    }

    private void setTimeout()
        throws IOException
    {
        if (mControlSock == null)
        {
            throw new IllegalStateException("Failed to set timeout - no control socket");
        } else
        {
            mControlSock.setSoTimeout(6000);
            return;
        }
    }

    public void close()
        throws IOException
    {
        isConnected = false;
        Writer writer = mWriter;
        IOException ioexception = null;
        if (writer != null)
        {
            try
            {
                mWriter.close();
                mWriter = null;
            }
            catch (IOException ioexception3)
            {
                ioexception = ioexception3;
            }
        }
        if (mReader != null)
        {
            try
            {
                mReader.close();
                mReader = null;
            }
            catch (IOException ioexception2)
            {
                ioexception = ioexception2;
            }
        }
        if (mControlSock != null)
        {
            try
            {
                mControlSock.close();
                mControlSock = null;
            }
            catch (IOException ioexception1)
            {
                ioexception = ioexception1;
            }
        }
        if (ioexception != null)
        {
            throw ioexception;
        } else
        {
            return;
        }
    }

    public void connect()
        throws IOException
    {
        Log.e("SocketClient", (new StringBuilder()).append("FENG connect() mControlSock = ").append(mControlSock).toString());
        if (mControlSock != null)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        mControlSock = PlainSocket.createPlainSocket(mRemoteAddr, 7878, 6000);
        setTimeout();
        initStreams();
        isConnected = true;
        return;
        IOException ioexception;
        ioexception;
        Log.e("SocketClient", "Failed to initialize socket", ioexception);
        if (mControlSock != null)
        {
            mControlSock.close();
        }
        mControlSock = null;
        isConnected = false;
        throw ioexception;
    }

    public int getCurCMDType()
    {
        return mCurCmdType;
    }

    public int getCurConParams()
    {
        return mCurConParams;
    }

    public int getCurConState()
    {
        return mCurState;
    }

    public int getCurEXEState()
    {
        return mCurEXEState;
    }

    public int getCurShareId(Context context)
    {
        int i = AEEUtilDef.getSharedTokenId(context);
        Log.e("SocketClient", (new StringBuilder()).append("getCurTokenId shareId = ").append(i).append(" mTokenId = ").append(mTokenId).toString());
        if (mTokenId == 0 || i == 0)
        {
            mTokenId = i;
        }
        return mTokenId;
    }

    public int getCurTimer()
    {
        return mCurTimer;
    }

    public int getCurTokenId()
    {
        return mTokenId;
    }

    public String getDVInfo(int i)
    {
        if (mDVInfoStrings == null)
        {
            initDVInfo();
        }
        if (i < 0 || i >= mDVInfoStrings.length)
        {
            return null;
        } else
        {
            return mDVInfoStrings[i];
        }
    }

    public boolean getIsExcuting()
    {
        return isExcuting;
    }

    public boolean getIsFirstStartPreview()
    {
        return mIsFirstStartPreview;
    }

    public boolean getIsNeedEncoding()
    {
        return isNeedStartEncoding;
    }

    public boolean getIsNeedFreshFiles()
    {
        return isNeedFreshFiles;
    }

    public boolean getIsPreviewClosed()
    {
        return isPreviewClosed;
    }

    public boolean getIsPreviewNotSupport()
    {
        return isPreviewNotSupport;
    }

    public boolean getIsRolloverOn()
    {
        return isRolloverON;
    }

    public boolean getIsStreamConnected()
    {
        return isStreamConnected;
    }

    public int getNextCMD()
        int i = mNextCmdTYpe;
        mNextCmdTYpe = -1;
        return i;
    }

    public int getRespondParams()
    {
        String s;
        Log.e("SocketClient", (new StringBuilder()).append("getRespondParams mCurCmdType = ").append(mCurCmdType).toString());
        try
        {
            s = readLine();
            Log.e("SocketClient", (new StringBuilder()).append("getRespondParams respondLine = ").append(s).toString());
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return 1;
        }
        if (s == null)
        {
            return 1;
        }
        mParamStr = null;
        JSONObject jsonobject = new JSONObject(s);
        int i;
        Log.e("SocketClient", (new StringBuilder()).append("getRespondParams() jsonObj = ").append(jsonobject).toString());
        i = jsonobject.getInt("rval");
        mCurCmdType;
        JVM INSTR lookupswitch 8: default 204
    //                   268435457: 244
    //                   268435474: 204
    //                   268435475: 204
    //                   268435488: 204
    //                   268435508: 204
    //                   268435509: 204
    //                   268435510: 204
    //                   268435511: 204;
           goto _L1; _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1
_L1:
        Log.e("SocketClient", (new StringBuilder()).append("getRespondParams() errNum = ").append(i).toString());
        if (i != 0) goto _L4; else goto _L3
_L3:
        mCurCmdType = -1;
          goto _L4
_L2:
        if (i != 0) goto _L1; else goto _L5
_L5:
        JSONArray jsonarray = jsonobject.getJSONArray("param");
        mTokenId = ((Integer)jsonarray.get(0)).intValue();
        Log.e("FENG", (new StringBuilder()).append("FENG token = ").append(jsonarray).toString());
        Log.e("FENG", (new StringBuilder()).append("FENG mTokenId = ").append(mTokenId).toString());
        mParamStr = (new StringBuilder()).append(mTokenId).append("").toString();
          goto _L1
        JSONException jsonexception;
        jsonexception;
_L6:
        jsonexception.printStackTrace();
        return 1;
        jsonexception;
        if (true) goto _L6; else goto _L4
_L4:
        return i;
    }

    public String getRespondParamsP()
    {
        return mParamStr;
    }

    public HashMap getSettingData()
    {
        if (mArrayData != null)
        {
            return mArrayData;
        } else
        {
            return null;
        }
    }

    public int getSettingDataNum()
    {
        if (mArrayData != null)
        {
            return mArrayData.size();
        } else
        {
            return 0;
        }
    }

    public ConfigDATA getSttingDataByKey(String s)
    {
        if (mArrayData != null && mArrayData.containsKey(s))
        {
            return (ConfigDATA)mArrayData.get(s);
        } else
        {
            return null;
        }
    }

    public boolean hasConfig()
    {
        return mHasConfig;
    }

    public void initDVInfo()
    {
        if (mDVInfoStrings == null)
        {
            mDVInfoStrings = (new String[] {
                "0", "0", "0", "0", "", "", "0H0", "0", "0", "0", 
                "0", "", "", "", "", "0", "0SEC", "0SEC", ""
            });
        }
    }

    protected void initStreams()
        throws IOException
    {
        if (mControlSock != null)
        {
            if (mReader == null)
            {
                mReader = new InputStreamReader(mControlSock.getInputStream(), "UTF-8");
            }
            if (mWriter == null)
            {
                mWriter = new OutputStreamWriter(mControlSock.getOutputStream(), "UTF-8");
            }
        }
    }

    public boolean isCmdSuc(String s)
        throws IOException
    {
        Log.e("SocketClient", (new StringBuilder()).append("isCmdSuc : ").append(s).toString());
        while (s == null || s.compareTo("ready") != 0 && s.compareTo("fail") == 0) 
        {
            return false;
        }
        return true;
    }

    public boolean isConnected()
    {
        return isConnected;
    }

    public void pauseRespondParams()
    {
        if (mRequestRespondsThread != null)
        {
            mRequestRespondsThread.setCurCMD(-1);
        }
    }

    public String readLine()
        throws IOException
    {
        int i;
        boolean flag;
        StringBuffer stringbuffer;
        StringBuffer stringbuffer1;
        i = 0;
        flag = false;
        stringbuffer = new StringBuffer();
        stringbuffer1 = new StringBuffer();
        do
        {
            if (mReader == null)
            {
                return null;
            }
            int j;
            try
            {
                j = mReader.read();
            }
            catch (IOException ioexception)
            {
                Log.e("SocketClient", (new StringBuilder()).append("Read failed ('").append(stringbuffer1.toString()).append("' read so far)").toString());
                throw new IOException(ioexception.toString());
            }
            if (j < 0)
            {
                String s = (new StringBuilder()).append("Control channel unexpectedly closed ('").append(stringbuffer1.toString()).append("' read so far)").toString();
                Log.e("SocketClient", s);
                throw new IOException(s);
            }
            if (j != 13)
            {
                stringbuffer.append((char)j);
                stringbuffer1.append((char)j);
            }
            if (123 == j)
            {
                i++;
                flag = true;
            } else
            if (125 == j)
            {
                i--;
            }
        } while (!flag || i != 0);
        Log.e("SocketClient", (new StringBuilder()).append("readLine finished!!!! str=").append(stringbuffer.toString().trim()).toString());
        return stringbuffer.toString().trim();
    }

    public void realseClient()
    {
        if (mSocketClient != null)
        {
            mSocketClient = null;
        }
    }

    public void releaseCurShareId(Context context)
    {
        AEEUtilDef.setSharedTokenId(context, 0);
        mTokenId = 0;
    }

    public void releaseCurTokenId()
    {
        mTokenId = 0;
    }

    public void requestConfig()
    {
        if (mArrayData == null)
        {
            mArrayData = new HashMap();
        }
        mArrayData.clear();
        stopRequestConfig();
        mRequestConfigThread = new RequestConfigThread();
        mRequestConfigThread.start();
    }

    public int sendCommand(int i, String s)
        throws IOException
    {
        byte byte0 = 3;
        Log.e("SocketClient", (new StringBuilder()).append("sendCommand commandType = ").append(i).append(" mCurCmdType = ").append(mCurCmdType).toString());
        if (mTokenId == 0 && i != 0x10000001)
        {
            byte0 = 4;
        } else
        if (mCurCmdType != i)
        {
            if (mCurCmdType != -1 && mCurCmdType != 0x10000003 && mCurCmdType != 0x10000005 && mCurCmdType != 0x10000014)
            {
                return 2;
            }
            mCurCmdType = i;
            String s1 = getCommand(i, s);
            Log.e("SocketClient", (new StringBuilder()).append("sendCommand command : ").append(s1).append(" mCurCmdType = ").append(mCurCmdType).toString());
            if (mRequestRespondsThread != null)
            {
                mRequestRespondsThread.setCurCMD(mCurCmdType);
            }
            if (s1 != null && s1.length() > byte0)
            {
                writeCommand(s1);
            }
            return 1;
        }
        return byte0;
    }

    public boolean sendCommandSuc(int i, String s)
        throws IOException
    {
        Log.e("SocketClient", (new StringBuilder()).append("sendCommandSuc commandType = ").append(i).append(" mCurCmdType = ").append(mCurCmdType).toString());
        break MISSING_BLOCK_LABEL_39;
        if (mCurCmdType != i && isConnected)
        {
            mCurCmdType = i;
            String s1 = getCommand(i, s);
            Log.e("SocketClient", (new StringBuilder()).append("sendCommandSuc command : ").append(s1).append(" mCurCmdType = ").append(mCurCmdType).toString());
            if (s1 != null && s1.length() > 3)
            {
                writeCommand(s1);
                return true;
            }
        }
        return false;
    }

    public void setCurConParams(int i)
    {
        mCurConParams = i;
    }

    public void setCurConState(int i)
    {
        mCurState = i;
    }

    public void setCurEXEState(int i)
    {
        mCurEXEState = i;
    }

    public void setCurTimer(int i)
    {
        mCurTimer = i;
    }

    public void setDVInfo(int i, String s)
    {
        if (mDVInfoStrings == null)
        {
            initDVInfo();
        }
        if (i < 0 || i >= mDVInfoStrings.length)
        {
            return;
        }
        if (s == null)
        {
            mDVInfoStrings[i] = s;
            return;
        }
        i;
        JVM INSTR tableswitch 12 17: default 76
    //                   12 84
    //                   13 240
    //                   14 295
    //                   15 349
    //                   16 393
    //                   17 454;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        mDVInfoStrings[i] = s;
        return;
_L2:
        int k = s.indexOf(" ");
        int l = s.indexOf('x');
        int i1;
        if (s.charAt(l + 1) == '0')
        {
            i1 = l + 2;
        } else
        {
            i1 = l + 1;
        }
        if (s.charAt(k + 1) == '0')
        {
            s = (new StringBuilder()).append(s.substring(i1, k)).append("/").append(s.substring(k + 2, s.lastIndexOf(" "))).toString();
        } else
        {
            s = (new StringBuilder()).append(s.substring(i1, k)).append("/").append(s.substring(k + 1, s.lastIndexOf(" "))).toString();
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if ("video_fov_zom".equals(s))
        {
            s = "S";
        } else
        {
            String as3[] = s.split("_");
            if (as3.length > 1)
            {
                s = as3[-1 + as3.length];
            }
            s = s.substring(0, 1).toUpperCase();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int j = s.indexOf(".");
        if (j == -1)
        {
            j = s.indexOf("M");
        }
        s = (new StringBuilder()).append(s.substring(0, j)).append("MP").toString();
        continue; /* Loop/switch isn't completed */
_L5:
        String as2[] = s.split("_");
        if (as2.length > 1)
        {
            s = as2[-1 + as2.length];
        }
        if (s.charAt(0) == '0')
        {
            s = s.substring(1);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        String as1[] = s.split("_");
        if (as1.length > 1)
        {
            s = as1[-1 + as1.length];
        }
        if (s.length() >= 4)
        {
            if (s.charAt(2) == '0')
            {
                s = s.substring(3);
            } else
            {
                s = s.substring(2);
            }
        }
        continue; /* Loop/switch isn't completed */
_L7:
        String as[] = s.split("_");
        if (as.length > 1)
        {
            s = as[-1 + as.length];
        }
        if (s.charAt(0) == '0')
        {
            s = (new StringBuilder()).append(s.substring(1, -1 + s.length())).append("SEC").toString();
        } else
        {
            s = (new StringBuilder()).append(s.substring(0, -1 + s.length())).append("SEC").toString();
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    public void setHasConfig(boolean flag)
    {
        mHasConfig = flag;
    }

    public void setIsExcuting(boolean flag)
    {
        isExcuting = flag;
    }

    public void setIsFirstStartPreview(boolean flag)
    {
        mIsFirstStartPreview = flag;
    }

    public void setIsNeedEncoding(boolean flag)
    {
        if (isNeedStartEncoding != flag)
        {
            isNeedStartEncoding = flag;
        }
    }

    public void setIsNeedFreshFiles(boolean flag)
    {
        if (isNeedFreshFiles != flag)
        {
            isNeedFreshFiles = flag;
        }
    }

    public void setIsPreviewClosed(boolean flag)
    {
        isPreviewClosed = flag;
    }

    public void setIsPreviewNotSupport(boolean flag)
    {
        isPreviewNotSupport = flag;
    }

    public void setIsRolloverOn(boolean flag)
    {
        isRolloverON = flag;
    }

    public void setIsStreamConnected(boolean flag)
    {
        isStreamConnected = flag;
    }

    public void setNextCMD(int i)
    {
        mNextCmdTYpe = i;
    }

    public void setOnRequestConfigListener(OnRequestConfigListener onrequestconfiglistener)
    {
        mOnRequestConfigListener = onrequestconfiglistener;
    }

    public void setOnRequestRespondsListener(OnRequestRespondsListener onrequestrespondslistener)
    {
        mOnRequestRespondsListener = onrequestrespondslistener;
    }

    public void startRespondParams(int i)
    {
        if (mRequestRespondsThread == null)
        {
            mRequestRespondsThread = new RequestRespondsThread();
            mRequestRespondsThread.start();
        }
        mRequestRespondsThread.setCurCMD(i);
    }

    public void stopRequestConfig()
    {
        if (mRequestConfigThread != null)
        {
            mRequestConfigThread.stopRequest();
            mRequestConfigThread = null;
        }
    }

    public void stopRespondParams()
    {
        if (mRequestRespondsThread != null)
        {
            mRequestRespondsThread.stopRequest();
            mRequestRespondsThread = null;
        }
    }

    public void uninitDVInfo()
    {
        if (mDVInfoStrings != null)
        {
            mDVInfoStrings = null;
        }
    }

    public void writeCommand(String s)
        throws IOException
    {
        Log.e("SocketClient", (new StringBuilder()).append("FENG writeCommand ---> ").append(s).toString());
        Log.e("SocketClient", (new StringBuilder()).append("FENG mWriter = ").append(mWriter).toString());
        if (mWriter == null)
        {
            return;
        }
        try
        {
            mWriter.write((new StringBuilder()).append(s).append("\r\n").toString());
            mWriter.flush();
            return;
        }
        catch (IOException ioexception)
        {
            throw new IOException(ioexception.getMessage());
        }
    }






/*
    static int access$302(AEESocketClient aeesocketclient, int i)
    {
        aeesocketclient.mCurCmdType = i;
        return i;
    }

*/




/*
    static int access$502(AEESocketClient aeesocketclient, int i)
    {
        aeesocketclient.mTokenId = i;
        return i;
    }

*/



/*
    static AEESocketClient access$602(AEESocketClient aeesocketclient)
    {
        mSocketClient = aeesocketclient;
        return aeesocketclient;
    }

*/
}
