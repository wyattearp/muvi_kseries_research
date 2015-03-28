// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.database.ContentObserver;
import android.os.Handler;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsLocalDataSource

class this._cls0 extends ContentObserver
{

    final AbsLocalDataSource this$0;

    public void onChange(boolean flag)
    {
        if (AbsLocalDataSource.access$000(AbsLocalDataSource.this) < 0)
        {
            AbsLocalDataSource.access$002(AbsLocalDataSource.this, 0);
        }
        if (AbsLocalDataSource.access$000(AbsLocalDataSource.this) == 0)
        {
            AbsLocalDataSource.access$100(AbsLocalDataSource.this).sendEmptyMessageDelayed(0, 5000L);
            super.onChange(flag);
        } else
        if (AbsLocalDataSource.access$000(AbsLocalDataSource.this) > 0)
        {
            int _tmp = AbsLocalDataSource.access$006(AbsLocalDataSource.this);
            return;
        }
    }

    (Handler handler)
    {
        this$0 = AbsLocalDataSource.this;
        super(handler);
    }
}
