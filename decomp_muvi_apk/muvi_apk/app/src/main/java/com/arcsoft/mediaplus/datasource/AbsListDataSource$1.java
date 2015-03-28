// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsListDataSource

class this._cls0
    implements com.arcsoft.util.tool.llback
{

    final AbsListDataSource this$0;

    public boolean onBuildList(List list, com.arcsoft.util.tool.ions ions)
    {
        return AbsListDataSource.this.onBuildList(list, ions);
    }

    public void onDestoryList(List list)
    {
        AbsListDataSource.this.onDestoryList(list);
    }

    public void onListSwitched(int i, int j)
    {
        AbsListDataSource.access$000(AbsListDataSource.this, i, j);
    }

    ()
    {
        this$0 = AbsListDataSource.this;
        super();
    }
}
