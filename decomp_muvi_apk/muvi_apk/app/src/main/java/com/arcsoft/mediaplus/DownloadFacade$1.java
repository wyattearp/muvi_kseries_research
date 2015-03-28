// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus:
//            DownloadFacade

class this._cls0 extends Handler
{

    final DownloadFacade this$0;

    public void handleMessage(Message message)
    {
        switch (message.what)
        {
        default:
            return;

        case 1: // '\001'
            showDownloadExistDlg();
            return;

        case 2: // '\002'
            showDownloadConfirmDlg(message.arg1);
            return;

        case 3: // '\003'
            notifyDownloadStart();
            showWaitingDlg();
            return;

        case 4: // '\004'
            hideWaitingDlg();
            toastDownloadFailedIfNeeded();
            notifyDownloadFinish();
            return;

        case 5: // '\005'
            hideWaitingDlg();
            showCancelWaitingDlg();
            return;

        case 6: // '\006'
            hideCancelWaitingDlg();
            break;
        }
    }

    ()
    {
        this$0 = DownloadFacade.this;
        super();
    }
}
