// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.controller;

import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.picture.controller:
//            LocalCacheMgr

class this._cls0
    implements com.arcsoft.mediaplus.datasource.taChangeListener
{

    final LocalCacheMgr this$0;

    public void onCountChanged(int i, int j)
    {
        Log.e("LocalCacheMgr", "onCountChanged");
    }

    public void onDataChanged(int i, Property property)
    {
        Log.e("LocalCacheMgr", "onDataChanged");
    }

    Listener()
    {
        this$0 = LocalCacheMgr.this;
        super();
    }
}
