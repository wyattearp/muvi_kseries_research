// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity, ListViewManager

class this._cls0
    implements android.content.ickListener
{

    final MediaPlusActivity this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        MediaPlusActivity.access$500(MediaPlusActivity.this).cancelAllUpdownload();
    }

    tener()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
