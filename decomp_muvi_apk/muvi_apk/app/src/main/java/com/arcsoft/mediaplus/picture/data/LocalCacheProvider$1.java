// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.data;

import android.database.ContentObserver;
import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.picture.data:
//            LocalCacheProvider

class this._cls0 extends ContentObserver
{

    final LocalCacheProvider this$0;

    public void onChange(boolean flag)
    {
        super.onChange(flag);
        Log.e("media observer", "media changed");
    }

    (Handler handler)
    {
        this$0 = LocalCacheProvider.this;
        super(handler);
    }
}
