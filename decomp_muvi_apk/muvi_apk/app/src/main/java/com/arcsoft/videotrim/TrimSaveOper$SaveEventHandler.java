// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.arcsoft.videotrim.Utils.UtilFunc;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimSaveOper

private class this._cls0 extends Handler
{

    final TrimSaveOper this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 4: // '\004'
        default:
            return;

        case 3: // '\003'
            UtilFunc.RenameFile(TrimSaveOper.access$300(TrimSaveOper.this), TrimSaveOper.access$400(TrimSaveOper.this));
            TrimSaveOper.access$200(TrimSaveOper.this, 0);
            return;

        case 5: // '\005'
            TrimSaveOper.access$500(TrimSaveOper.this);
            UtilFunc.DeleteFileByName(TrimSaveOper.access$300(TrimSaveOper.this));
            TrimSaveOper.access$200(TrimSaveOper.this, 7);
            TrimSaveOper.access$402(TrimSaveOper.this, null);
            return;

        case 6: // '\006'
            TrimSaveOper.access$200(TrimSaveOper.this, 3);
            break;
        }
        UtilFunc.DeleteFileByName(TrimSaveOper.access$300(TrimSaveOper.this));
        TrimSaveOper.access$402(TrimSaveOper.this, null);
    }

    public _cls9(Looper looper)
    {
        this$0 = TrimSaveOper.this;
        super(looper);
    }
}
