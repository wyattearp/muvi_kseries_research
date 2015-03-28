// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage

class this._cls0
    implements android.view.ckListener
{

    final Collage this$0;

    public void onClick(View view)
    {
        int i;
        if (System.currentTimeMillis() - Collage.access$000(Collage.this) <= 1000L)
        {
            return;
        }
        i = Collage.access$100(Collage.this);
        view.getId();
        JVM INSTR tableswitch 2131296287 2131296295: default 80
    //                   2131296287 145
    //                   2131296288 80
    //                   2131296289 80
    //                   2131296290 137
    //                   2131296291 80
    //                   2131296292 80
    //                   2131296293 125
    //                   2131296294 80
    //                   2131296295 131;
           goto _L1 _L2 _L1 _L1 _L3 _L1 _L1 _L4 _L1 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break MISSING_BLOCK_LABEL_145;
_L6:
        if (!Collage.access$300(Collage.this))
        {
            changeTemplate(Collage.access$400(Collage.this), i, false);
        }
        Collage.access$500(Collage.this);
        Collage.access$002(Collage.this, System.currentTimeMillis());
        return;
_L4:
        i--;
        continue; /* Loop/switch isn't completed */
_L5:
        i++;
        if (true) goto _L6; else goto _L3
_L3:
        Collage.access$200(Collage.this);
        return;
        finish();
        return;
    }

    ()
    {
        this$0 = Collage.this;
        super();
    }
}
