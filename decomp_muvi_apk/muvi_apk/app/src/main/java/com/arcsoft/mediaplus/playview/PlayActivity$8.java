// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.util.tool.leteListener
{

    final PlayActivity this$0;

    public void onDeleteStart(int i)
    {
        Log.v("zdf1", (new StringBuilder()).append("onDeleteStart, fileNum = ").append(i).toString());
        PlayActivity.access$302(PlayActivity.this, false);
        showDeletingWaitDialog();
    }

    public void onDeleted(int i, int j)
    {
        Log.v("zdf1", (new StringBuilder()).append("onDeleted, sucNum = ").append(i).append(", fileNum = ").append(j).toString());
        if (isLocalContent())
        {
            PlayActivity.access$400(PlayActivity.this, i, j);
            return;
        }
        PlayActivity playactivity = PlayActivity.this;
        int k;
        if (!PlayActivity.access$300(PlayActivity.this))
        {
            k = 1;
        } else
        {
            k = 0;
        }
        PlayActivity.access$400(playactivity, k, 1);
    }

    public void onDeleting(MediaItem mediaitem, int i, int j, boolean flag)
    {
        Log.v("zdf1", (new StringBuilder()).append("onDeleting, filePros = ").append(i).append(", isSuc = ").append(flag).toString());
    }

    public void onDeletingRemote(int i)
    {
    }

    ner()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
