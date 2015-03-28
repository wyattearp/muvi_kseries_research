// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.videotrim:
//            SaveDialog, TrimSaveOper

class this._cls0
    implements OnSaveTrimListener
{

    final SaveDialog this$0;

    public void OnTrimResult(int i)
    {
        if (i == 0)
        {
            if (SaveDialog.access$000(SaveDialog.this) != null)
            {
                SaveDialog.access$902(SaveDialog.this, SaveDialog.access$000(SaveDialog.this).getDstFileName());
            }
            Message message6 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message6.what = 2;
            SaveDialog.access$500(SaveDialog.this).sendMessage(message6);
            return;
        }
        if (i == 3)
        {
            Message message5 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message5.what = 4;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message5, 50L);
            return;
        }
        if (i == 4)
        {
            Message message4 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message4.what = 5;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message4, 50L);
            return;
        }
        if (i == 5)
        {
            Message message3 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message3.what = 6;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message3, 50L);
            return;
        }
        if (i == 6)
        {
            Message message2 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message2.what = 7;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message2, 50L);
            return;
        }
        if (i == 7)
        {
            Message message1 = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message1.what = 8;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message1, 50L);
            return;
        } else
        {
            Message message = SaveDialog.access$500(SaveDialog.this).obtainMessage();
            message.what = 3;
            SaveDialog.access$500(SaveDialog.this).sendMessageDelayed(message, 50L);
            return;
        }
    }

    OnSaveTrimListener()
    {
        this$0 = SaveDialog.this;
        super();
    }
}
