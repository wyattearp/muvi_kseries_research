// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.widget.Toast;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

class this._cls0
    implements com.arcsoft.util.os.eStatusChangeListener
{

    final MediaPlusActivity this$0;

    public void onStorageChecking()
    {
    }

    public void onStorageMounted()
    {
    }

    public void onStorageUnmounted()
    {
        Toast.makeText(mContext, 0x7f0b0056, 1).show();
    }

    tatusChangeListener()
    {
        this$0 = MediaPlusActivity.this;
        super();
    }
}
