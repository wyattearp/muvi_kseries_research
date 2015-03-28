// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.os.Bundle;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager

class val.args
    implements android.content.ckListener
{

    final MediaPlusActivity this$0;
    final Bundle val$args;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        int j = val$args.getInt("dtcp_download_index");
        MediaPlusActivity.access$500(MediaPlusActivity.this).addUpdownload(Boolean.valueOf(false), j);
    }

    ener()
    {
        this$0 = final_mediaplusactivity;
        val$args = Bundle.this;
        super();
    }
}
