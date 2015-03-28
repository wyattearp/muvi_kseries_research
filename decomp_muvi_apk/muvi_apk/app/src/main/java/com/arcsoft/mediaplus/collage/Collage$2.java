// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import com.arcsoft.mediaplus.widget.PopupMenuWindow;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage

class this._cls0
    implements com.arcsoft.mediaplus.widget.indow.OnMenuClickedListener
{

    final Collage this$0;

    public void onClicked(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        setResult(-1);
        Collage.access$600(Collage.this, false);
_L4:
        if (Collage.access$700(Collage.this) != null)
        {
            Collage.access$700(Collage.this).hidePopopMenuWindow();
        }
        return;
_L2:
        if (i == 1)
        {
            setResult(-1);
            Collage.access$600(Collage.this, true);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    ndow()
    {
        this$0 = Collage.this;
        super();
    }
}
