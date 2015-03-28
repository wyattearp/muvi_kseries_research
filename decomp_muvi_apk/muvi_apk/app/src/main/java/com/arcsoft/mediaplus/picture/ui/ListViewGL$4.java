// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import com.arcsoft.mediaplus.setting.Settings;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

class this._cls0
    implements com.arcsoft.adk.atv..IServerDLNAUploadListener
{

    final ListViewGL this$0;

    public void onServerGetProtocolInfo(String s, com.arcsoft.adk.atv.DataOnServerGetProtocolInfo dataonservergetprotocolinfo, int i)
    {
        if (s == Settings.instance().getDefaultDMSUDN());
    }

    public void onXGetDLNAUploadProfiles(String s, String s1, int i)
    {
        if (s == Settings.instance().getDefaultDMSUDN());
    }

    etProtocolInfo()
    {
        this$0 = ListViewGL.this;
        super();
    }
}
