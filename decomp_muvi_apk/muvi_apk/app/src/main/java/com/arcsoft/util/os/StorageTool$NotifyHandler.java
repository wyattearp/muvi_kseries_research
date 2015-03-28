// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.util.os:
//            StorageTool

private class <init> extends Handler
{

    final StorageTool this$0;

    public void handleMessage(Message message)
    {
        String s = (String)message.obj;
        if (StorageTool.access$200(StorageTool.this) == null) goto _L2; else goto _L1
_L1:
        if (!s.equals("android.intent.action.MEDIA_MOUNTED")) goto _L4; else goto _L3
_L3:
        StorageTool.access$200(StorageTool.this).onStorageMounted();
_L2:
        if (StorageTool.access$300(StorageTool.this) == null) goto _L6; else goto _L5
_L5:
        if (!s.equals("android.intent.action.MEDIA_SCANNER_STARTED")) goto _L8; else goto _L7
_L7:
        StorageTool.access$300(StorageTool.this).onMediaScanStarted();
_L6:
        return;
_L4:
        if (s.equals("android.intent.action.MEDIA_UNMOUNTED"))
        {
            StorageTool.access$200(StorageTool.this).onStorageUnmounted();
        } else
        if (s.equals("android.intent.action.MEDIA_CHECKING"))
        {
            StorageTool.access$200(StorageTool.this).onStorageChecking();
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (!s.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) goto _L6; else goto _L9
_L9:
        StorageTool.access$300(StorageTool.this).onMediaScanFinished();
        return;
        if (true) goto _L2; else goto _L10
_L10:
    }

    private stener()
    {
        this$0 = StorageTool.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
