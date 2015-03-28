// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.mediaplus.updownload.service.UpDownloadEngine;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.util.tool.atedDeleteList
{

    final PlayActivity this$0;

    public void delete(com.arcsoft.util.tool.teData tedata)
    {
        if (PlayActivity.access$000(PlayActivity.this) != null && tedata != null)
        {
            PlayActivity.access$000(PlayActivity.this).deleteDownloadedRecode(tedata.filePath);
        }
    }

    nloadEngine()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
