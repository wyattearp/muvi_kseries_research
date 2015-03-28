// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.net.Uri;
import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar

class this._cls0
    implements .IPreDownloadListener
{

    final ControlBar this$0;

    public void onDownloadBegin()
    {
    }

    public void onDownloadFinish(ArrayList arraylist)
    {
        if (arraylist != null && arraylist.size() != 0)
        {
            if (1 == getRemoteBtnClickType())
            {
                if (ControlBar.access$500(ControlBar.this) == null)
                {
                    ControlBar.access$502(ControlBar.this, new ArrayList());
                }
                ControlBar.access$500(ControlBar.this).clear();
                Uri uri;
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); ControlBar.access$500(ControlBar.this).add(ControlBar.access$600(ControlBar.this, uri)))
                {
                    uri = (Uri)iterator.next();
                }

                ControlBar.access$702(ControlBar.this, arraylist.size());
                ControlBar.access$802(ControlBar.this, ControlBar.access$700(ControlBar.this));
                ControlBar.access$400(ControlBar.this);
                return;
            }
            if (2 == getRemoteBtnClickType())
            {
                for (int i = 0; i < arraylist.size(); i++)
                {
                    Log.e("zdf", (new StringBuilder()).append("####### onDownloadFinish, Share uri(").append(i).append(") = ").append(arraylist.get(i)).toString());
                }

                ControlBar.share(ControlBar.access$000(ControlBar.this), arraylist, getShareMimeType());
                return;
            }
        }
    }

    .IPreDownloadListener()
    {
        this$0 = ControlBar.this;
        super();
    }
}
