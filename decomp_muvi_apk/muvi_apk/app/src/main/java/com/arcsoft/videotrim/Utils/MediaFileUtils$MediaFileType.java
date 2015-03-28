// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;


// Referenced classes of package com.arcsoft.videotrim.Utils:
//            MediaFileUtils

static class m_strMimeType
{

    private final int m_iFileType;
    private final String m_strMimeType;

    public int GetFileType()
    {
        return m_iFileType;
    }

    public String GetMimeType()
    {
        return m_strMimeType;
    }


    (int i, String s)
    {
        m_iFileType = i;
        m_strMimeType = s;
    }
}
