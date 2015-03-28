// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import com.arcsoft.mediaplus.widget.PopupMenuWindow;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity

class this._cls0
    implements com.arcsoft.mediaplus.widget.nuClickedListener
{

    final videoTrimActivity this$0;

    public void onClicked(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        setResult(-1);
        videoTrimActivity.access$000(videoTrimActivity.this, false);
_L4:
        if (videoTrimActivity.access$100(videoTrimActivity.this) != null)
        {
            videoTrimActivity.access$100(videoTrimActivity.this).hidePopopMenuWindow();
        }
        return;
_L2:
        if (i == 1)
        {
            setResult(-1);
            videoTrimActivity.access$000(videoTrimActivity.this, true);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ow()
    {
        this$0 = videoTrimActivity.this;
        super();
    }
}
