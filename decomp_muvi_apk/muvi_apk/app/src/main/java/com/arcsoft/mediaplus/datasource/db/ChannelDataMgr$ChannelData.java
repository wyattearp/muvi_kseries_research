// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.net.Uri;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            ChannelDataMgr

public static class m_strVideoItemObjId
{

    public long _id;
    public int channelid;
    public Uri hdRes;
    public com.arcsoft.adk.atv.Mgr.ChannelData itemDesc;
    public String m_strVideoItemObjId;
    public Uri sdRes;

    public ()
    {
        _id = 0L;
        hdRes = null;
        sdRes = null;
        channelid = 0;
        itemDesc = null;
        m_strVideoItemObjId = null;
    }
}
