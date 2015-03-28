// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;


// Referenced classes of package com.arcsoft.workshop:
//            WorkShop, AsyncTaskRunner

private class <init>
    implements powermobia.photoeditor.nit>
{

    final WorkShop this$0;

    public int cbFaceDecteted(int i, int j, int k, int l)
    {
        return 0;
    }

    public int cbNotify(int i, int j)
    {
        if (i != 0xa7e001 || !isNeedDoAsyncTask())
        {
            return 0;
        } else
        {
            WorkShop.access$000(WorkShop.this).notifyAsyncTask();
            return 0;
        }
    }

    private ()
    {
        this$0 = WorkShop.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
