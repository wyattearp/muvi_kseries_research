// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.widget.Toast;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            PlayActivity

class this._cls0
    implements com.arcsoft.util.os.StorageStatusChangeListener
{

    final PlayActivity this$0;

    public void onStorageChecking()
    {
    }

    public void onStorageMounted()
    {
    }

    public void onStorageUnmounted()
    {
        Toast.makeText(PlayActivity.this, 0x7f0b0056, 0).show();
    }

    usChangeListener()
    {
        this$0 = PlayActivity.this;
        super();
    }
}
