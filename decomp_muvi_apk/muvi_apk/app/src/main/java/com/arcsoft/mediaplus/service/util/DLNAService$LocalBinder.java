// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.service.util;

import android.os.Binder;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.service.util:
//            DLNAService

public class this._cls0 extends Binder
{

    final DLNAService this$0;

    public DLNAService getService()
    {
        Log.v("DLNAService", "LocalBinder getService~~~");
        return DLNAService.this;
    }

    public ()
    {
        this$0 = DLNAService.this;
        super();
    }
}
