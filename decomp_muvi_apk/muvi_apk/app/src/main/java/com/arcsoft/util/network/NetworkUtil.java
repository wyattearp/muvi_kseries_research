// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.BitSet;
import java.util.List;

public class NetworkUtil
{

    public NetworkUtil()
    {
    }

    public static String getLocalIpViaWiFi(Context context)
    {
        WifiManager wifimanager = (WifiManager)context.getSystemService("wifi");
        if (wifimanager != null) goto _L2; else goto _L1
_L1:
        WifiInfo wifiinfo;
        return null;
_L2:
        byte abyte0[];
        int i;
        if ((wifiinfo = wifimanager.getConnectionInfo()) == null || (i = wifiinfo.getIpAddress()) == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        abyte0 = new byte[4];
        abyte0[0] = (byte)(i & 0xff);
        abyte0[1] = (byte)(0xff & i >> 8);
        abyte0[2] = (byte)(0xff & i >> 16);
        abyte0[3] = (byte)(0xff & i >> 24);
        InetAddress inetaddress1 = InetAddress.getByAddress(abyte0);
        InetAddress inetaddress = inetaddress1;
_L4:
        if (inetaddress != null)
        {
            return inetaddress.toString().substring(1);
        }
        if (true) goto _L1; else goto _L3
_L3:
        UnknownHostException unknownhostexception;
        unknownhostexception;
        unknownhostexception.printStackTrace();
        inetaddress = null;
          goto _L4
    }

    public static String getLocalMacAddress(Context context)
    {
        WifiManager wifimanager = (WifiManager)context.getSystemService("wifi");
        WifiInfo wifiinfo;
        if (wifimanager != null)
        {
            if ((wifiinfo = wifimanager.getConnectionInfo()) != null)
            {
                return wifiinfo.getMacAddress();
            }
        }
        return null;
    }

    public static boolean isWifiEncryped(Context context)
    {
        List list;
        boolean flag;
        WifiManager wifimanager = (WifiManager)context.getSystemService("wifi");
        list = null;
        if (wifimanager != null)
        {
            list = wifimanager.getConfiguredNetworks();
        }
        flag = false;
        if (list == null) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        i = list.size();
        j = 0;
_L9:
        flag = false;
        if (j >= i) goto _L2; else goto _L3
_L3:
        WifiConfiguration wificonfiguration = (WifiConfiguration)list.get(j);
        if (wificonfiguration.status != 0) goto _L5; else goto _L4
_L4:
        String as[] = wificonfiguration.wepKeys;
        if (!wificonfiguration.allowedKeyManagement.get(0) || as[0] != null) goto _L7; else goto _L6
_L6:
        boolean flag1;
        flag1 = wificonfiguration.allowedAuthAlgorithms.get(1);
        flag = false;
        if (!flag1) goto _L2; else goto _L7
_L7:
        flag = true;
_L2:
        return flag;
_L5:
        j++;
        if (true) goto _L9; else goto _L8
_L8:
    }
}
