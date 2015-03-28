// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.os.Bundle;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager

class val.args
    implements android.content.ickListener
{

    final MediaPlusActivity this$0;
    final Bundle val$args;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if (val$args.getInt("state") == 0)
        {
            ListViewManager listviewmanager = MediaPlusActivity.access$500(MediaPlusActivity.this);
            boolean flag;
            if (!MediaPlusActivity.access$500(MediaPlusActivity.this).isRemote())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            listviewmanager.cancelUpdownload(Boolean.valueOf(flag), val$args.getInt("position"));
        }
    }

    tener()
    {
        this$0 = final_mediaplusactivity;
        val$args = Bundle.this;
        super();
    }
}
