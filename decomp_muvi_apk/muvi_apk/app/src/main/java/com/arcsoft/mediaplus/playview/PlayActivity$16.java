// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.adk.atv.enderStatusListener
{

    final PlayActivity this$0;

    public void onGetProtocolInfo(String s, com.arcsoft.adk.atv.aOnGetProtocolInfo aongetprotocolinfo, int i)
    {
    }

    public void onRenderAdded(com.arcsoft.adk.atv.Desc desc)
    {
    }

    public void onRenderInstalled(com.arcsoft.adk.atv.Desc desc, boolean flag, boolean flag1, boolean flag2)
    {
        Log.w("PlayActivity", (new StringBuilder()).append("IRenderStatusListener.onRenderInstalled(), cm = ").append(flag).append(", avt = ").append(flag1).append(", rcl = ").append(flag2).toString());
    }

    public void onRenderRemoved(com.arcsoft.adk.atv.Desc desc)
    {
    }

    colInfo()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
