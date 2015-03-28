// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import powermobia.ve.utils.MPoint;
import powermobia.videoeditor.MEngine;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            RuntimeConfig, UtilFunc

public class AppContext
{

    private static final String TAG = "AppContext";
    private static AppContext s_AppContext = null;
    private boolean m_bInited;
    private MEngine m_veEngine;

    private AppContext()
    {
        m_bInited = false;
        m_veEngine = null;
        Init();
    }

    private int CreateAMVEEngine()
    {
        int i;
        if (m_veEngine != null)
        {
            i = 0;
        } else
        {
            m_veEngine = new MEngine();
            if (m_veEngine == null)
            {
                return 3;
            }
            i = m_veEngine.create();
            if (i == 0)
            {
                Boolean boolean1 = new Boolean(false);
                m_veEngine.setProperty(7, boolean1);
                Integer integer = new Integer(100);
                m_veEngine.setProperty(6, integer);
                Integer integer1 = new Integer(4);
                m_veEngine.setProperty(2, integer1);
                Integer integer2 = new Integer(4);
                m_veEngine.setProperty(3, integer2);
                Integer integer3 = new Integer(1);
                m_veEngine.setProperty(4, integer3);
                Integer integer4 = new Integer(0x10001);
                m_veEngine.setProperty(5, integer4);
                m_veEngine.setProperty(1, "/data/data/com.MUVI.MediaPlus/");
                MPoint mpoint = new MPoint(1920, 1088);
                m_veEngine.setProperty(9, mpoint);
                m_veEngine.setProperty(19, Integer.valueOf(10000));
                if (RuntimeConfig.IFRAME_SOLUTION)
                {
                    m_veEngine.setProperty(20, Integer.valueOf(3));
                    return i;
                } else
                {
                    m_veEngine.setProperty(20, Integer.valueOf(0));
                    return i;
                }
            }
        }
        return i;
    }

    private void DestroyAMVEEngine()
    {
        if (m_veEngine != null)
        {
            m_veEngine.destory();
            m_veEngine = null;
        }
    }

    public static void DestroyInstatce()
    {
        UtilFunc.Log("AppContext", "DestroyInstatce in");
        if (s_AppContext != null)
        {
            s_AppContext.UnInit();
            s_AppContext = null;
        }
        UtilFunc.Log("AppContext", "DestroyInstatce out");
    }

    public static AppContext getAppContext()
    {
        if (s_AppContext == null)
        {
            s_AppContext = new AppContext();
        }
        return s_AppContext;
    }

    public int Init()
    {
        if (m_bInited)
        {
            return 0;
        }
        int i = CreateAMVEEngine();
        if (i != 0)
        {
            DestroyAMVEEngine();
            return i;
        } else
        {
            m_bInited = true;
            return i;
        }
    }

    protected void UnInit()
    {
        if (!m_bInited)
        {
            return;
        } else
        {
            DestroyAMVEEngine();
            m_bInited = false;
            return;
        }
    }

    public MEngine getVEEngine()
    {
        return m_veEngine;
    }

}
