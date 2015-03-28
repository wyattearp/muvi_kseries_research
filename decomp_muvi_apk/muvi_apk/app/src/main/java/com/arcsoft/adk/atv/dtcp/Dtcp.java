// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv.dtcp;

import com.arcsoft.adk.atv.Recycleble;

public class Dtcp
    implements Recycleble
{

    public static final int DTCP_ASYNC_RESULT_FAILURE = -256;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE = -1536;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_BASE = -1536;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_BUSY = -266;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_CONT_KEY_CONF = -2048;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_CONT_KEY_CONF_BASE = -2048;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_CONT_KEY_CONF_FAILED = -2049;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_INVALID_COMMAND_SEQUENCE = -1539;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP = -2304;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_BASE = -2304;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_INVALID_COMMAND_SEQUENCE = -2307;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_CAPABILITY_EXCHANGE_COMMAND = -2319;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_COMMAND_AKE_LABEL_INVALID = -2306;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_COMMAND_INVALID = -2318;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_COMMAND_NOT_IMPLEMENTED = -2305;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_COMMAND_TIMEOUT = -2309;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE = -2310;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_ANY_OTHER_ERROR = -2315;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_AUTH_FAILED = -2312;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_INVALID_COMMAND_SEQUENCE = -2316;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_NOT_IMPLEMENTED = -2314;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_NO_MORE_AUTH = -2311;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_REJECTED = -2313;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_REMOTE_DEVICE_RESPONSE_SYNTAX_ERROR = -2317;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_IP_RTT_FAILED = -2308;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_REMOTE_COMMAND_INVALID = -1537;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_RTT_FAILED = -1540;
    public static final int DTCP_ASYNC_RESULT_FAILURE_AKE_SINK_COUNT_REACHED_LIMIT = -1538;
    public static final int DTCP_ASYNC_RESULT_FAILURE_BASE = -256;
    public static final int DTCP_ASYNC_RESULT_FAILURE_BUFFER_TOO_SMALL = -261;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CANCELED_BY_LOCAL = -257;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CANCELED_BY_REMOTE = -258;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT = -1024;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_BASE = -1024;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_INVALID = -1028;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_LOCAL_CERT_IDU_IS_REQUIRED = -1031;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_LOCAL_CERT_INVALID = -1029;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_LOCAL_CERT_NOT_IMPLEMENTED = -1026;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_NOT_IMPLEMENTED = -1025;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_REMOTE_CERT_INVALID = -1030;
    public static final int DTCP_ASYNC_RESULT_FAILURE_CERT_REMOTE_CERT_NOT_IMPLEMENTED = -1027;
    public static final int DTCP_ASYNC_RESULT_FAILURE_EXCHANGE_KEY = -1792;
    public static final int DTCP_ASYNC_RESULT_FAILURE_EXCHANGE_KEY_BASE = -1792;
    public static final int DTCP_ASYNC_RESULT_FAILURE_EXCHANGE_KEY_EXPIRED = -1794;
    public static final int DTCP_ASYNC_RESULT_FAILURE_EXCHANGE_KEY_SINK_NOT_AQUIRED = -1793;
    public static final int DTCP_ASYNC_RESULT_FAILURE_INVALID_API_SEQUENCE = -264;
    public static final int DTCP_ASYNC_RESULT_FAILURE_INVALID_ARGUMENT = -259;
    public static final int DTCP_ASYNC_RESULT_FAILURE_INVALID_EC_DSA_SIGNATURE = -265;
    public static final int DTCP_ASYNC_RESULT_FAILURE_INVALID_PRIVATE_DATA = -263;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE = -2816;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_BASE = -2816;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_BUSY = -2817;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_FINALIZE = -2819;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_FINALIZE_BUSY = -2820;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_FINALIZE_REMOTE_MAC_INVALID = -2821;
    public static final int DTCP_ASYNC_RESULT_FAILURE_MOVE_TRANSACTION_DOES_NOT_EXIST = -2818;
    public static final int DTCP_ASYNC_RESULT_FAILURE_NOT_IMPLEMENTED = -262;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP = -2560;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_AKE_HAS_NOT_BEEN_DONE = -2567;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_BASE = -2560;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_EXCHANGE_KEY_LABEL_NOT_MATCHED = -2565;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_INVALID_E_EMI = -2566;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_LOCAL_PCP_SIZE_TOO_LARGE = -2563;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_PCP_SIZE_TOO_LARGE = -2562;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_REMOTE_PCP_NOT_IMPLEMENTED = -2561;
    public static final int DTCP_ASYNC_RESULT_FAILURE_PCP_REMOTE_PCP_SIZE_TOO_LARGE = -2564;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET = -512;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_ADDRESS_ALREADY_IN_USE = -517;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_BASE = -512;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_CANCELED = -522;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_CHECK_TTL_TIMED_OUT = -521;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_CONNECTION_REFUSED = -514;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_CONNECTION_TIMED_OUT = -515;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_INVALID_REMOTE_TTL = -523;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_NETIF_NOT_FOUND = -524;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_NETWORK_UNREACHABLE = -516;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_NO_RESOURCE = -513;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_READ_TIMED_OUT = -519;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_TTL = -525;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_WOULD_BLOCK = -518;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SOCKET_WRITE_TIMED_OUT = -520;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM = -768;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_BASE = -768;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_DEVICE_REVOKED = -772;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_INVALID = -769;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_LOCAL_DEVICE_REVOKED = -773;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_LOCAL_SRM_INVALID = -770;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_REMOTE_DEVICE_REVOKED = -774;
    public static final int DTCP_ASYNC_RESULT_FAILURE_SRM_REMOTE_SRM_INVALID = -771;
    public static final int DTCP_ASYNC_RESULT_FAILURE_TO_ALLOCATE_MEMORY = -260;
    public static final int DTCP_ASYNC_RESULT_FAILURE_TRANSPORT = -1280;
    public static final int DTCP_ASYNC_RESULT_FAILURE_TRANSPORT_BASE = -1280;
    public static final int DTCP_ASYNC_RESULT_FAILURE_TRANSPORT_REMOTE_DEVICE_TIMEOUT = -1281;
    public static final int DTCP_ASYNC_RESULT_FAILURE_WLAN_WEP_OFF = -267;
    public static final int DTCP_ASYNC_RESULT_SUCCESS = 0;
    public static final int DTCP_LOCAL_DRM_ENCRYPT_TYPE_AES = 0;
    public static final int DTCP_LOCAL_DRM_ENCRYPT_TYPE_CPRM = 1;
    public static final int DTCP_RET_FAIL = -1;
    public static final int DTCP_RET_INVALID_ARG = -4;
    public static final int DTCP_RET_NOMEM = -2;
    public static final int DTCP_RET_NOT_IMPLEMENT = -3;
    public static final int DTCP_RET_OK;
    private static int m_hDtcp = 0;

    public Dtcp()
    {
    }

    private static native int JNI_Dtcp_Alloc();

    private static native void JNI_Dtcp_Free(int i);

    public static int getInstance()
    {
        if (m_hDtcp == 0)
        {
            m_hDtcp = JNI_Dtcp_Alloc();
        }
        return m_hDtcp;
    }

    public void Recycle()
    {
        JNI_Dtcp_Free(m_hDtcp);
        m_hDtcp = 0;
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        Recycle();
    }

}
