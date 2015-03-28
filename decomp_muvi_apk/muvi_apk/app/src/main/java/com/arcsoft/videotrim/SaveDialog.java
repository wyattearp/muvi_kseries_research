// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

// Referenced classes of package com.arcsoft.videotrim:
//            TrimSaveOper, VideoClip

public class SaveDialog
{

    private static final int MAX_PROGRESS = 100;
    private static final int MSG_INVALID_VIDEOFILE_ERROR = 5;
    private static final int MSG_SDCARD_CHANGED_ERROR = 7;
    private static final int MSG_TRIM_CANCEL = 8;
    private static final int MSG_TRIM_ERROR = 3;
    private static final int MSG_TRIM_FILESIZE_ERROR = 4;
    private static final int MSG_TRIM_FINISHED = 2;
    private static final int MSG_TRIM_NO_SOURCE_FILE_ERROR = 6;
    private static final int MSG_TRIM_RUNNING = 1;
    private static final int MSG_TRIM_START = 0;
    private static final int SEND_MSG_DELAYED = 50;
    private final Handler mHandler = new Handler() {

        final SaveDialog this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 8: default 56
        //                       0 62
        //                       1 159
        //                       2 343
        //                       3 291
        //                       4 317
        //                       5 481
        //                       6 507
        //                       7 533
        //                       8 559;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            super.handleMessage(message);
            return;
_L2:
            removeMessages(0);
            if (m_TrimSaveOper != null && m_TrimDialog != null)
            {
                m_ProgressStatusTxt.setText((new StringBuilder()).append(m_Context.getResources().getString(0x7f0b01c3)).append("0%").toString());
                m_TrimingProgressBar.setProgress(0);
                mHandler.sendEmptyMessageDelayed(1, 50L);
            }
            continue; /* Loop/switch isn't completed */
_L3:
            removeMessages(1);
            if (!m_bShouldCancel && m_TrimSaveOper != null && m_TrimDialog != null)
            {
                int i = m_TrimSaveOper.getCurrentPercent();
                m_ProgressStatusTxt.setText((new StringBuilder()).append(m_Context.getResources().getString(0x7f0b01c3)).append(i).append("%").toString());
                m_TrimingProgressBar.setProgress(i);
                if (i < 100)
                {
                    mHandler.sendEmptyMessageDelayed(1, 50L);
                }
            }
            continue; /* Loop/switch isn't completed */
_L5:
            finishSplite();
            Toast.makeText(m_Context, 0x7f0b01c9, 0).show();
            continue; /* Loop/switch isn't completed */
_L6:
            finishSplite();
            Toast.makeText(m_Context, 0x7f0b01c4, 0).show();
            continue; /* Loop/switch isn't completed */
_L4:
            if (m_TrimSaveOper != null && m_TrimDialog != null)
            {
                m_ProgressStatusTxt.setText((new StringBuilder()).append(m_Context.getResources().getString(0x7f0b01c3)).append(100).append("%").toString());
                m_TrimingProgressBar.setProgress(100);
            }
            if (m_TrimSaveHandler != null)
            {
                Message message1 = m_TrimSaveHandler.obtainMessage(0);
                message1.obj = mStrDstFile;
                m_TrimSaveHandler.sendMessageDelayed(message1, 1000L);
            }
            finishSplite();
            continue; /* Loop/switch isn't completed */
_L7:
            finishSplite();
            Toast.makeText(m_Context, 0x7f0b01c6, 0).show();
            continue; /* Loop/switch isn't completed */
_L8:
            finishSplite();
            Toast.makeText(m_Context, 0x7f0b01c7, 0).show();
            continue; /* Loop/switch isn't completed */
_L9:
            finishSplite();
            Toast.makeText(m_Context, 0x7f0b01c8, 0).show();
            continue; /* Loop/switch isn't completed */
_L10:
            finishSplite();
            if (true) goto _L1; else goto _L11
_L11:
        }

            
            {
                this$0 = SaveDialog.this;
                super();
            }
    };
    private final TrimSaveOper.OnSaveTrimListener mListener = new TrimSaveOper.OnSaveTrimListener() {

        final SaveDialog this$0;

        public void OnTrimResult(int i)
        {
            if (i == 0)
            {
                if (m_TrimSaveOper != null)
                {
                    mStrDstFile = m_TrimSaveOper.getDstFileName();
                }
                Message message6 = mHandler.obtainMessage();
                message6.what = 2;
                mHandler.sendMessage(message6);
                return;
            }
            if (i == 3)
            {
                Message message5 = mHandler.obtainMessage();
                message5.what = 4;
                mHandler.sendMessageDelayed(message5, 50L);
                return;
            }
            if (i == 4)
            {
                Message message4 = mHandler.obtainMessage();
                message4.what = 5;
                mHandler.sendMessageDelayed(message4, 50L);
                return;
            }
            if (i == 5)
            {
                Message message3 = mHandler.obtainMessage();
                message3.what = 6;
                mHandler.sendMessageDelayed(message3, 50L);
                return;
            }
            if (i == 6)
            {
                Message message2 = mHandler.obtainMessage();
                message2.what = 7;
                mHandler.sendMessageDelayed(message2, 50L);
                return;
            }
            if (i == 7)
            {
                Message message1 = mHandler.obtainMessage();
                message1.what = 8;
                mHandler.sendMessageDelayed(message1, 50L);
                return;
            } else
            {
                Message message = mHandler.obtainMessage();
                message.what = 3;
                mHandler.sendMessageDelayed(message, 50L);
                return;
            }
        }

            
            {
                this$0 = SaveDialog.this;
                super();
            }
    };
    private String mStrDstFile;
    private VideoClip mTrimClip;
    private Button m_BtnCancelTrim;
    private Context m_Context;
    private TextView m_ProgressStatusTxt;
    private AlertDialog m_TrimDialog;
    private Handler m_TrimSaveHandler;
    private TrimSaveOper m_TrimSaveOper;
    private ProgressBar m_TrimingProgressBar;
    private boolean m_bShouldCancel;

    public SaveDialog(Context context, VideoClip videoclip, Handler handler)
    {
        m_Context = null;
        mTrimClip = null;
        m_TrimDialog = null;
        m_BtnCancelTrim = null;
        m_TrimingProgressBar = null;
        m_ProgressStatusTxt = null;
        m_TrimSaveOper = null;
        m_bShouldCancel = false;
        m_TrimSaveHandler = null;
        mStrDstFile = null;
        m_Context = context;
        mTrimClip = videoclip;
        m_TrimSaveHandler = handler;
    }

    private void finishSplite()
    {
        if (m_TrimDialog != null)
        {
            m_TrimDialog.dismiss();
            m_ProgressStatusTxt = null;
            m_TrimingProgressBar = null;
            m_BtnCancelTrim = null;
            m_TrimDialog = null;
        }
        mTrimClip = null;
        if (m_TrimSaveOper != null)
        {
            m_TrimSaveOper.destroy();
            m_TrimSaveOper = null;
        }
    }

    private void showProgressDialog()
    {
        View view = LayoutInflater.from(m_Context).inflate(0x7f03003f, null);
        if (view == null)
        {
            return;
        } else
        {
            m_TrimingProgressBar = (ProgressBar)view.findViewById(0x7f090177);
            m_TrimingProgressBar.setMax(100);
            m_ProgressStatusTxt = (TextView)view.findViewById(0x7f090176);
            m_BtnCancelTrim = (Button)view.findViewById(0x7f090178);
            m_TrimDialog = (new android.app.AlertDialog.Builder(m_Context)).create();
            m_TrimDialog.setCancelable(false);
            m_TrimDialog.show();
            m_TrimDialog.getWindow().setContentView(view);
            m_BtnCancelTrim.setOnClickListener(new android.view.View.OnClickListener() {

                final SaveDialog this$0;

                public void onClick(View view1)
                {
                    if (m_TrimDialog != null)
                    {
                        m_TrimDialog.dismiss();
                    }
                    m_bShouldCancel = true;
                    if (m_TrimSaveOper != null)
                    {
                        m_TrimSaveOper.Cancel();
                    }
                }

            
            {
                this$0 = SaveDialog.this;
                super();
            }
            });
            return;
        }
    }

    public void destroy()
    {
        if (m_TrimDialog != null)
        {
            m_TrimDialog.dismiss();
            m_ProgressStatusTxt = null;
            m_TrimingProgressBar = null;
            m_BtnCancelTrim = null;
            m_TrimDialog = null;
        }
        mTrimClip = null;
        m_TrimSaveHandler = null;
        if (m_TrimSaveOper != null)
        {
            m_TrimSaveOper.destroy();
            m_TrimSaveOper = null;
        }
    }

    public void show()
    {
        if (m_TrimSaveOper != null)
        {
            m_TrimSaveOper.destroy();
            m_TrimSaveOper = null;
        }
        if (mTrimClip == null)
        {
            return;
        } else
        {
            m_TrimSaveOper = new TrimSaveOper(mTrimClip, m_Context);
            m_TrimSaveOper.setOnSaveTrimListener(mListener);
            showProgressDialog();
            m_TrimSaveOper.StartSplite();
            mHandler.sendEmptyMessage(0);
            return;
        }
    }









/*
    static boolean access$602(SaveDialog savedialog, boolean flag)
    {
        savedialog.m_bShouldCancel = flag;
        return flag;
    }

*/





/*
    static String access$902(SaveDialog savedialog, String s)
    {
        savedialog.mStrDstFile = s;
        return s;
    }

*/
}
