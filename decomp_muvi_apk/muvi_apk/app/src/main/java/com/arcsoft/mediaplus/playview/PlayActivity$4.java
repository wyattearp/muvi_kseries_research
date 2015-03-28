// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.net.Uri;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.mediaplus.IPreDownloadListener
{

    final PlayActivity this$0;

    public void onDownloadBegin()
    {
    }

    public void onDownloadFinish(ArrayList arraylist)
    {
        if (arraylist == null || arraylist.size() != 1)
        {
            return;
        }
        for (int i = 0; i < arraylist.size(); i++)
        {
            Log.e("zdf", (new StringBuilder()).append("####### onDownloadFinish, Share uri(").append(i).append(") = ").append(arraylist.get(i)).toString());
        }

        PlayActivity.access$100(PlayActivity.this, (Uri)arraylist.get(0));
    }

    oadListener()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
