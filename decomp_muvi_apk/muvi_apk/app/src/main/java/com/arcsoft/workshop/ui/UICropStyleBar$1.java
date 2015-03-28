// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropStyleBar, UIRegionCrop

class this._cls0
    implements ctProp
{

    final UICropStyleBar this$0;

    public MRect getCropRect()
    {
        MRect mrect = new MRect();
        UICropStyleBar.access$000(UICropStyleBar.this).getRegion(mrect);
        return mrect;
    }

    ctProp()
    {
        this$0 = UICropStyleBar.this;
        super();
    }
}
