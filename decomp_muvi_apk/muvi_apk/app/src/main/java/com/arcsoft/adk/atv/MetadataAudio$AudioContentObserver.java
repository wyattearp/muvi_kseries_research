// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.database.ContentObserver;
import android.os.Handler;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.adk.atv:
//            MetadataAudio

static final class  extends ContentObserver
{

    public void onChange(boolean flag)
    {
        super.onChange(flag);
        Log.d("MetadataAudio", "AudioContentObserver.onChange");
    }

    public (Handler handler)
    {
        super(handler);
    }
}
