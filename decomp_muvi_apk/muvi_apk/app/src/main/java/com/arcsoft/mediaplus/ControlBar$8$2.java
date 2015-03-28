// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.DialogInterface;
import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus:
//            ControlBar, MediaPlusActivity

class this._cls1
    implements android.content..OnClickListener
{

    final ss this$1;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        ((MediaPlusActivity)ControlBar.access$000(_fld0)).deleteFiles();
        dialoginterface.dismiss();
    }

    is._cls0()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/mediaplus/ControlBar$8

/* anonymous class */
    class ControlBar._cls8
        implements android.view.View.OnClickListener
    {

        final ControlBar this$0;

        public void onClick(View view)
        {
            (new android.app.AlertDialog.Builder(ControlBar.access$000(ControlBar.this))).setTitle(0x7f0b011e).setMessage(0x7f0b011f).setPositiveButton(0x7f0b001f, new ControlBar._cls8._cls2()).setNegativeButton(0x7f0b0020, new ControlBar._cls8._cls1()).show();
        }

            
            {
                this$0 = ControlBar.this;
                super();
            }

        // Unreferenced inner class com/arcsoft/mediaplus/ControlBar$8$1

/* anonymous class */
        class ControlBar._cls8._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            final ControlBar._cls8 this$1;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
            }

                    
                    {
                        this$1 = ControlBar._cls8.this;
                        super();
                    }
        }

    }

}
