// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.app.AlertDialog;
import android.view.View;

// Referenced classes of package com.arcsoft.videotrim:
//            SaveDialog, TrimSaveOper

class this._cls0
    implements android.view.istener
{

    final SaveDialog this$0;

    public void onClick(View view)
    {
        if (SaveDialog.access$100(SaveDialog.this) != null)
        {
            SaveDialog.access$100(SaveDialog.this).dismiss();
        }
        SaveDialog.access$602(SaveDialog.this, true);
        if (SaveDialog.access$000(SaveDialog.this) != null)
        {
            SaveDialog.access$000(SaveDialog.this).Cancel();
        }
    }

    ()
    {
        this$0 = SaveDialog.this;
        super();
    }
}
