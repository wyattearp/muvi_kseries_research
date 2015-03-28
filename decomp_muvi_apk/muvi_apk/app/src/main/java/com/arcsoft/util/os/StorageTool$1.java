// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.util.os:
//            StorageTool

class <init> extends BroadcastReceiver
{

    final StorageTool this$0;

    public void onReceive(Context context, Intent intent)
    {
        if (StorageTool.access$000(StorageTool.this) == null)
        {
            StorageTool.access$002(StorageTool.this, new tifyHandler(StorageTool.this, null));
        }
        Message message = Message.obtain();
        message.obj = intent.getAction();
        StorageTool.access$000(StorageTool.this).sendMessage(message);
    }

    tifyHandler()
    {
        this$0 = StorageTool.this;
        super();
    }
}
