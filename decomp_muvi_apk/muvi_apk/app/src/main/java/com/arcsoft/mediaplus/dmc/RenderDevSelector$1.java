// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;

import android.widget.CompoundButton;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            RenderDevSelector

class this._cls0
    implements android.widget.ckedChangeListener
{

    final RenderDevSelector this$0;

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        Log.d("Dmc", (new StringBuilder()).append("onCheckedChanged  isChecked = ").append(flag).toString());
        RenderDevSelector.access$002(RenderDevSelector.this, flag);
        if (RenderDevSelector.access$000(RenderDevSelector.this))
        {
            Log.d("Dmc", (new StringBuilder()).append("RenderDev  mUseTempRender = ").append(RenderDevSelector.access$100(RenderDevSelector.this)).toString());
            RenderDevSelector.access$102(RenderDevSelector.this, false);
        }
    }

    istener()
    {
        this$0 = RenderDevSelector.this;
        super();
    }
}
