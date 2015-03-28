// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.view.KeyEvent;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements android.content.Listener
{

    final MediaPlusActivity this$0;

    public boolean onKey(DialogInterface dialoginterface, int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            MediaPlusActivity.access$3900(MediaPlusActivity.this);
            return true;
        } else
        {
            return false;
        }
    }

    er()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
