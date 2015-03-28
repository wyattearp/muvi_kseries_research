// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar, MediaPlusActivity

class this._cls0
    implements android.view.istener
{

    final ControlBar this$0;

    public void onClick(View view)
    {
        (new android.app.uilder(ControlBar.access$000(ControlBar.this))).setTitle(0x7f0b011e).setMessage(0x7f0b011f).setPositiveButton(0x7f0b001f, new android.content.DialogInterface.OnClickListener() {

            final ControlBar._cls8 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                ((MediaPlusActivity)ControlBar.access$000(this$0)).deleteFiles();
                dialoginterface.dismiss();
            }

            
            {
                this$1 = ControlBar._cls8.this;
                super();
            }
        }).setNegativeButton(0x7f0b0020, new android.content.DialogInterface.OnClickListener() {

            final ControlBar._cls8 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

            
            {
                this$1 = ControlBar._cls8.this;
                super();
            }
        }).show();
    }

    _cls2.this._cls1()
    {
        this$0 = ControlBar.this;
        super();
    }
}
