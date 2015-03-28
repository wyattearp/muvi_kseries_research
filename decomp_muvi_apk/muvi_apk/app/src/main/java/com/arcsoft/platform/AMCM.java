// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.platform;

import com.arcsoft.Recyclable;

public class AMCM
    implements Recyclable
{

    private int m_hCMgr;

    private AMCM(int i)
    {
        m_hCMgr = 0;
        m_hCMgr = i;
    }

    public static AMCM CreateAMCM()
    {
        int i = JNI_AMCM_Create();
        AMCM amcm = null;
        if (i != 0)
        {
            amcm = new AMCM(i);
        }
        return amcm;
    }

    public static void DestoryAMCM(int i)
    {
        if (i != 0)
        {
            JNI_AMCM_Destroy(i);
        }
    }

    private static native int JNI_AMCM_Create();

    private static native void JNI_AMCM_Destroy(int i);

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        recycle();
    }

    public void recycle()
    {
        if (m_hCMgr != 0)
        {
            JNI_AMCM_Destroy(m_hCMgr);
            m_hCMgr = 0;
        }
    }
}
