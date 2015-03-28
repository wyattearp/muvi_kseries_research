// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import android.content.Context;
import android.net.wifi.WifiManager;
import java.util.ArrayList;

public class WifiMulticast
{

    private Context m_Context;
    private android.net.wifi.WifiManager.MulticastLock m_Lock;
    private android.net.wifi.WifiManager.WifiLock m_LockWifi;
    private ArrayList wifi_lock_list;

    public WifiMulticast(Context context)
    {
        wifi_lock_list = new ArrayList();
        m_Context = null;
        m_Lock = null;
        m_LockWifi = null;
        m_Context = context;
    }

    public void Lock()
    {
        this;
        JVM INSTR monitorenter ;
        android.net.wifi.WifiManager.MulticastLock multicastlock = m_Lock;
        if (multicastlock == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        m_Lock = ((WifiManager)m_Context.getSystemService("wifi")).createMulticastLock("ArcSoft DMC Lock");
        m_Lock.acquire();
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void UnLock()
    {
        this;
        JVM INSTR monitorenter ;
        if (m_Lock != null)
        {
            m_Lock.release();
            m_Lock = null;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void wifiLock(String s)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = wifi_lock_list.contains(s);
        if (!flag) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (m_LockWifi == null)
        {
            m_LockWifi = ((WifiManager)m_Context.getSystemService("wifi")).createWifiLock("ArcSoft DMC Lock");
        }
        m_LockWifi.acquire();
        wifi_lock_list.add(s);
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void wifiUnLock(String s)
    {
        this;
        JVM INSTR monitorenter ;
        android.net.wifi.WifiManager.WifiLock wifilock = m_LockWifi;
        if (wifilock != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (s != null) goto _L4; else goto _L3
_L3:
        for (; m_LockWifi.isHeld(); m_LockWifi.release()) { }
        break MISSING_BLOCK_LABEL_43;
        Exception exception;
        exception;
        throw exception;
        wifi_lock_list.clear();
        m_LockWifi = null;
          goto _L1
_L4:
        if (!wifi_lock_list.contains(s) || !m_LockWifi.isHeld()) goto _L1; else goto _L5
_L5:
        m_LockWifi.release();
        wifi_lock_list.remove(s);
        if (!m_LockWifi.isHeld())
        {
            wifi_lock_list.clear();
            m_LockWifi = null;
        }
          goto _L1
    }
}
