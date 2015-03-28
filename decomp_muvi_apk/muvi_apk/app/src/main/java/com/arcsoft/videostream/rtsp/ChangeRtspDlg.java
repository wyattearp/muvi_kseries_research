// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.rtsp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

// Referenced classes of package com.arcsoft.videostream.rtsp:
//            RtspUtils

public class ChangeRtspDlg
{

    private Context mContext;
    private AlertDialog mDialog;

    public ChangeRtspDlg(Context context)
    {
        mContext = null;
        mDialog = null;
        mContext = context;
    }

    public void showDialog()
    {
        final EditText editText = new EditText(mContext);
        editText.setText(RtspUtils.getRtspUrl());
        mDialog = (new android.app.AlertDialog.Builder(mContext)).setView(editText).setPositiveButton(0x7f0b012b, new android.content.DialogInterface.OnClickListener() {

            final ChangeRtspDlg this$0;
            final EditText val$editText;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                RtspUtils.changeRtspUrl(editText.getText().toString());
            }

            
            {
                this$0 = ChangeRtspDlg.this;
                editText = edittext;
                super();
            }
        }).setNegativeButton(0x7f0b012a, new android.content.DialogInterface.OnClickListener() {

            final ChangeRtspDlg this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

            
            {
                this$0 = ChangeRtspDlg.this;
                super();
            }
        }).create();
        if (mDialog != null)
        {
            mDialog.show();
        }
    }
}
