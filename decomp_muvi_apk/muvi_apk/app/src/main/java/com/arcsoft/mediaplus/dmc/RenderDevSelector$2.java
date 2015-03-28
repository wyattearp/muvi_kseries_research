// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.view.View;
import android.widget.CheckBox;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class this._cls0
    implements android.view.
{

    final RenderDevSelector this$0;

    public void onClick(View view)
    {
        if (RenderDevSelector.access$200(RenderDevSelector.this) != null)
        {
            RenderDevSelector renderdevselector = RenderDevSelector.this;
            boolean flag;
            if (!RenderDevSelector.access$200(RenderDevSelector.this).isChecked())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            RenderDevSelector.access$300(renderdevselector, flag);
        }
    }

    ()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
