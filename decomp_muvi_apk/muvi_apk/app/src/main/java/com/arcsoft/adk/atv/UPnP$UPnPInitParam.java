// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            UPnP, BrowseCallback, MRCPCallback, MSCPCallback, 
//            UPCPCallback

public static class m_nLogLevel
{

    public BrowseCallback m_BrowseCallback;
    public MRCPCallback m_RenderCallback;
    public MSCPCallback m_ServerCallback;
    public UPCPCallback m_UpCPCallback;
    public boolean m_bEnableDms;
    public boolean m_bEnableFileServer;
    public boolean m_bEnableMrcp;
    public boolean m_bEnableMscp;
    public boolean m_bEnableUploader;
    public int m_iPort;
    public int m_nLogLevel;
    public String m_strCallbackIP;
    public String m_strDmsName;
    public String m_strLogPath;

    public ()
    {
        m_bEnableMrcp = false;
        m_bEnableMscp = false;
        m_bEnableFileServer = false;
        m_bEnableDms = false;
        m_bEnableUploader = false;
        m_strDmsName = null;
        m_ServerCallback = null;
        m_RenderCallback = null;
        m_UpCPCallback = null;
        m_BrowseCallback = null;
        m_strCallbackIP = null;
        m_strLogPath = null;
        m_iPort = 0;
        m_nLogLevel = 0;
    }
}
