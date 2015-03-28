// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import com.arcsoft.mediaplus.MediaPlusActivity;
import java.util.ArrayList;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            RemoteListViewGL

class this._cls0
    implements com.arcsoft.mediaplus.DownloadListener
{

    final RemoteListViewGL this$0;

    public void onDownloadBegin()
    {
        requestRender();
        ((MediaPlusActivity)mContext).setCancelAllBtnEnabled(isFileDownloading());
    }

    public void onDownloadFinish(ArrayList arraylist)
    {
    }

    tener()
    {
        this$0 = RemoteListViewGL.this;
        super();
    }
}
