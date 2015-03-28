// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import com.arcsoft.adk.atv.UPnPFlagsParameterUtils;
import com.arcsoft.adk.atv.UPnPOperationsParameterUtils;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;

public class MyUPnPUtils
{

    public MyUPnPUtils()
    {
    }

    public static int getFlagByBitFilter(com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags flags)
    {
        while (flags == null || presentitem_resource == null) 
        {
            return 15;
        }
        return UPnPFlagsParameterUtils.getflagbyBitFilter(presentitem_resource.m_strProtocolInfo, flags);
    }

    public static com.arcsoft.adk.atv.UPnP.PresentItem_Resource getItemResource(String s, String s1)
    {
        if (s == null || s1 == null || RemoteDBMgr.instance() == null)
        {
            return null;
        } else
        {
            return RemoteDBMgr.instance().getRemoteItemProtocolInfo(s, s1);
        }
    }

    public static boolean isSupportByteBasedSeek(com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource)
    {
        if (presentitem_resource != null)
        {
            return UPnPOperationsParameterUtils.isSupportByteBasedSeek(presentitem_resource.m_strProtocolInfo);
        } else
        {
            return false;
        }
    }

    public static boolean isSupportTimeBasedSeek(com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource)
    {
        if (presentitem_resource != null)
        {
            return UPnPOperationsParameterUtils.isSupportTimeBasedSeek(presentitem_resource.m_strProtocolInfo);
        } else
        {
            return false;
        }
    }
}
