// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import java.util.Comparator;

// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngineService

class this._cls1
    implements Comparator
{

    final compare this$1;

    public int compare(this._cls1 _pcls1, this._cls1 _pcls1_1)
    {
        return _pcls1._fld1 - _pcls1_1._fld1;
    }

    public volatile int compare(Object obj, Object obj1)
    {
        return compare((compare)obj, (compare)obj1);
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
    }
}
