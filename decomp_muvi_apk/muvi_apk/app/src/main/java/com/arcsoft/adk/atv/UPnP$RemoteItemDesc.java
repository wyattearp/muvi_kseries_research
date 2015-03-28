// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


// Referenced classes of package com.arcsoft.adk.atv:
//            UPnP

public static class m_strAribObjectType
{

    public m_strTitle m_PresentItem;
    public long m_lChildCount;
    public long m_lProperty;
    public String m_strAribObjectType;
    public String m_strObjId;
    public String m_strParentId;
    public String m_strTitle;

    public boolean equals(Object obj)
    {
        m_strAribObjectType m_straribobjecttype;
        if (obj instanceof m_strAribObjectType)
        {
            if ((m_straribobjecttype = (m_strAribObjectType)obj).m_strObjId.equals(m_strObjId) && m_straribobjecttype.m_strTitle.equals(m_strTitle))
            {
                return true;
            }
        }
        return false;
    }

    public ()
    {
        m_strObjId = null;
        m_strParentId = null;
        m_strTitle = null;
        m_lProperty = 0L;
        m_lChildCount = 0L;
        m_PresentItem = null;
        m_strAribObjectType = null;
    }
}
