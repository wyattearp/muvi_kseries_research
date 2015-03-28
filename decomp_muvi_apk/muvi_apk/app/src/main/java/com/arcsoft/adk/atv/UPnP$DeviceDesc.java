// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.Bitmap;
import java.util.List;

// Referenced classes of package com.arcsoft.adk.atv:
//            UPnP

public static class m_DeviceIcon
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
        m_DeviceIcon m_deviceicon;
        if (obj instanceof m_DeviceIcon)
        {
            if (m_strUuid.equals((m_deviceicon = (m_strUuid)obj).m_strUuid))
            {
                return true;
            }
        }
        return false;
    }

    public ()
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
