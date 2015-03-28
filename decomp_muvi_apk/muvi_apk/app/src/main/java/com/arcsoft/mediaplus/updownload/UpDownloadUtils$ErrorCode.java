// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import com.arcsoft.util.tool.ToastMgr;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UpDownloadUtils

public static class 
{

    public static final int ERROR_AKE_ERROR = 820;
    public static final int ERROR_DOWNLOAD = 900;
    public static final int ERROR_DOWNLOAD_COPY_COUNT = 921;
    public static final int ERROR_DOWNLOAD_CPRM = 922;
    public static final int ERROR_DOWNLOAD_FAILED = 910;
    public static final int ERROR_DOWNLOAD_SIZE_BEYOND_ERROR = 923;
    public static final int ERROR_DOWNLOAD_SIZE_ERROR = 909;
    public static final int ERROR_DOWNLOAD_SUCCUSS = 911;
    public static final int ERROR_FILE_ERROR = 802;
    public static final int ERROR_IO_ERROR = 808;
    public static final int ERROR_MEMORY_SIZE = 806;
    public static final int ERROR_NONE = 800;
    public static final int ERROR_SDCARD_UNMOUNT = 807;
    public static final int ERROR_STORAGE_SIZE = 805;
    public static final int ERROR_UNKNOWN = 801;
    public static final int ERROR_UPLOAD = 1000;
    public static final int ERROR_UPLOAD_FAILED = 1014;
    public static final int ERROR_UPLOAD_NOT_SUPPORT = 1013;
    public static final int ERROR_UPLOAD_SIZE_ERROR = 1012;
    public static final int ERROR_UPLOAD_SUCCUSS = 1015;
    public static final int ERROR_URL_ERROR = 803;
    public static final int ERROR_USER_ABORT = 818;
    public static final int ERROR_USER_ABORT_ALL = 819;
    public static final int ERROR_USER_CANCEL = 816;
    public static final int ERROR_USER_CANCEL_ALL = 817;
    public static final int ERROR_WIFI_ERROR = 804;

    public static void showDefaultError(Context context, int i)
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = 800;
        i;
        JVM INSTR lookupswitch 10: default 100
    //                   801: 126
    //                   802: 132
    //                   804: 114
    //                   805: 150
    //                   807: 144
    //                   808: 120
    //                   816: 138
    //                   909: 156
    //                   923: 168
    //                   1012: 162;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L12:
        break MISSING_BLOCK_LABEL_168;
_L3:
        break; /* Loop/switch isn't completed */
_L6:
        break; /* Loop/switch isn't completed */
_L15:
        if (j > '\u0320')
        {
            ToastMgr.showToast(context, j, 0);
            return;
        }
        if (true) goto _L1; else goto _L14
_L14:
        j = 0x7f0b00c3;
          goto _L15
_L9:
        j = 0x7f0b00c5;
          goto _L15
_L4:
        j = 0x7f0b00c6;
          goto _L15
_L5:
        j = 0x7f0b00c7;
          goto _L15
_L10:
        j = 0x7f0b00b3;
          goto _L15
_L8:
        j = 0x7f0b00c1;
          goto _L15
_L7:
        j = 0x7f0b00c2;
          goto _L15
_L11:
        j = 0x7f0b00b7;
          goto _L15
_L13:
        j = 0x7f0b00bc;
          goto _L15
        j = 0x7f0b00b6;
          goto _L15
    }

    public static void showUpDownloadCanceled(Context context, int i, int j)
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int k;
        if (j != 817)
        {
            break; /* Loop/switch isn't completed */
        }
        k = 0x7f0b00bd;
_L4:
        if (k > 0)
        {
            ToastMgr.showToast(context, k, 0);
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        k = 0;
        if (j != 819)
        {
            if (i == 0)
            {
                k = 0x7f0b00b3;
            } else
            {
                k = 0;
                if (i == 1)
                {
                    k = 0x7f0b00b9;
                }
            }
        }
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static transient void showUpDownloadError(Context context, int i, int j, Object aobj[])
    {
        if (context != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int k = 0;
        j;
        JVM INSTR lookupswitch 10: default 100
    //                   816: 149
    //                   817: 149
    //                   819: 149
    //                   820: 156
    //                   910: 106
    //                   911: 110
    //                   922: 163
    //                   1013: 142
    //                   1014: 128
    //                   1015: 135;
           goto _L3 _L4 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L7:
        break; /* Loop/switch isn't completed */
_L3:
        showDefaultError(context, j);
        return;
_L6:
        k = 0x7f0b00b5;
_L15:
        if (k > 0)
        {
            ToastMgr.showToast(context, context.getString(k, aobj), 0);
            return;
        }
          goto _L12
_L10:
        k = 0x7f0b00bb;
          goto _L13
_L11:
        k = 0x7f0b00ba;
          goto _L13
_L9:
        k = 0x7f0b00c8;
          goto _L13
_L4:
        showUpDownloadCanceled(context, i, j);
        return;
_L5:
        k = 0x7f0b00cd;
          goto _L13
_L8:
        k = 0x7f0b00c0;
_L13:
        if (true) goto _L15; else goto _L14
_L14:
_L12:
        if (true) goto _L1; else goto _L16
_L16:
    }

    public static void showUpDownloadStarted(Context context, int i)
    {
        if (context != null)
        {
            if (i == 0)
            {
                return;
            }
            int j = 0;
            if (i == 1)
            {
                j = 0x7f0b00b8;
            }
            if (j > 0)
            {
                ToastMgr.showToast(context, j, 0);
                return;
            }
        }
    }

    String getErrorDes(int i)
    {
        return null;
    }

    public ()
    {
    }
}
