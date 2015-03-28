// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.rtsp;

import android.content.DialogInterface;
import android.widget.EditText;

// Referenced classes of package com.arcsoft.videostream.rtsp:
//            ChangeRtspDlg, RtspUtils

class val.editText
    implements android.content.OnClickListener
{

    final ChangeRtspDlg this$0;
    final EditText val$editText;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        RtspUtils.changeRtspUrl(val$editText.getText().toString());
    }

    er()
    {
        this$0 = final_changertspdlg;
        val$editText = EditText.this;
        super();
    }
}
