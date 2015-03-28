// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.content.Intent;
import android.os.Handler;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.setting.AEESettingCMDListActivity;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity, AEESocketClient, AEEVideoStreamFullScreenActivity

class this._cls0
    implements android.view.reamActivity._cls6
{

    final AEEVideoStreamActivity this$0;

    public void onClick(View view)
    {
        AEEVideoStreamActivity.access$702(AEEVideoStreamActivity.this, false);
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("onClick v = ").append(view).toString());
        view.getId();
        JVM INSTR tableswitch 2131296563 2131296590: default 164
    //                   2131296563 165
    //                   2131296564 164
    //                   2131296565 772
    //                   2131296566 395
    //                   2131296567 1062
    //                   2131296568 1170
    //                   2131296569 1116
    //                   2131296570 1284
    //                   2131296571 164
    //                   2131296572 1509
    //                   2131296573 164
    //                   2131296574 164
    //                   2131296575 1593
    //                   2131296576 164
    //                   2131296577 164
    //                   2131296578 1677
    //                   2131296579 164
    //                   2131296580 164
    //                   2131296581 1761
    //                   2131296582 164
    //                   2131296583 164
    //                   2131296584 1846
    //                   2131296585 164
    //                   2131296586 164
    //                   2131296587 1965
    //                   2131296588 164
    //                   2131296589 164
    //                   2131296590 2050;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L9 _L1 _L1 _L10 _L1 _L1 _L11 _L1 _L1 _L12 _L1 _L1 _L13 _L1 _L1 _L14 _L1 _L1 _L15
_L1:
        return;
_L2:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("mOnClickListener video_view mCurConnectStatus = ").append(AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this)).append(", isRecting = ").append(AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this)).toString());
        if (AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 2 || AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 1 && (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 20 || AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18))
        {
            boolean flag;
            AEEVideoStreamActivity aeevideostreamactivity;
            boolean flag1;
            if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this) != null && AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            aeevideostreamactivity = AEEVideoStreamActivity.this;
            if (flag)
            {
                flag1 = true;
            } else
            if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            AEEVideoStreamActivity.access$1202(aeevideostreamactivity, flag1);
            if (AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this))
            {
                switchTo(1, 20);
                return;
            }
            if (AEEVideoStreamActivity.access$1300(AEEVideoStreamActivity.this))
            {
                switchTo(1, 18);
                return;
            } else
            {
                showSurfaceView(0);
                switchTo(2, -1);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate mCurExecuteStatus = ").append(AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this)).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate isExecuting = ").append(AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this)).toString());
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("stream_execute_btn_operate mIsSetParams = ").append(AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this)).toString());
        if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) || AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 0 || AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19 || AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            continue; /* Loop/switch isn't completed */
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (!mSocketClient.isConnected())
        {
            break MISSING_BLOCK_LABEL_643;
        }
        mSocketClient.setIsNeedFreshFiles(true);
_L16:
        AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
        IOException ioexception3;
        switch (AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this))
        {
        case 7: // '\007'
        default:
            return;

        case 3: // '\003'
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000004, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000003, 0x10000021, 0L);
                return;
            }

        case 4: // '\004'
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000006, -1, 0L);
            return;

        case 5: // '\005'
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000007, -1, 0L);
            return;

        case 6: // '\006'
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000008, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000014, -1, 0L);
                return;
            }

        case 8: // '\b'
            break;
        }
        break MISSING_BLOCK_LABEL_738;
        try
        {
            Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b015e, 0).show();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
        }
          goto _L16
        if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000004, -1, 0L);
            return;
        } else
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000005, -1, 0L);
            return;
        }
_L3:
        if (AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) == -1 && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1802(AEEVideoStreamActivity.this, 0x10000066);
            Intent intent2;
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
            }
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            mSocketClient.setOnRequestRespondsListener(null);
            intent2 = new Intent();
            intent2.setClass(getApplicationContext(), com/arcsoft/videostream/aee/AEEVideoStreamFullScreenActivity);
            intent2.putExtra("is_cur_executing", AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this));
            intent2.putExtra("is_stream_connected", AEEVideoStreamActivity.access$1900(AEEVideoStreamActivity.this));
            intent2.putExtra("connect_failed_params", AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this));
            intent2.putExtra("cur_exe_state", AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this));
            intent2.putExtra("is_preview_closed", AEEVideoStreamActivity.access$1200(AEEVideoStreamActivity.this));
            intent2.putExtra("is_preview_not_support", AEEVideoStreamActivity.access$1300(AEEVideoStreamActivity.this));
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$2000(AEEVideoStreamActivity.this) != null)
            {
                intent2.putExtra("get_recting_time", AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this));
                synchronized (AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this))
                {
                    AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).removeMessages(2);
                    AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).removeMessages(3);
                }
            }
            startActivityForResult(intent2, 0x10000066);
            return;
        }
        continue; /* Loop/switch isn't completed */
        exception;
        handler;
        JVM INSTR monitorexit ;
        throw exception;
_L5:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x1000000a, -1, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000009, -1, 0L);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if (AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 2)
            {
                AEEVideoStreamActivity.access$2200(AEEVideoStreamActivity.this).setImageResource(0x7f02013a);
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000011, -1, 0L);
                return;
            }
            if (AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 1 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 16)
            {
                AEEVideoStreamActivity.access$2200(AEEVideoStreamActivity.this).setImageResource(0x7f02013c);
                AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000016, -1, 0L);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) || AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) != -1 || AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) == 0 || AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 19 || AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            continue; /* Loop/switch isn't completed */
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (!mSocketClient.isConnected()) goto _L18; else goto _L17
_L17:
        if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
            return;
        }
          goto _L19
        IOException ioexception1;
        ioexception1;
_L20:
        ioexception1.printStackTrace();
        return;
_L19:
        if (!mSocketClient.hasConfig())
        {
            Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0163, 0).show();
            return;
        }
        Intent intent1;
        AEEVideoStreamActivity.access$1802(AEEVideoStreamActivity.this, 0x10000065);
        mSocketClient.setOnRequestRespondsListener(null);
        mSocketClient.setCurEXEState(AEEVideoStreamActivity.access$1400(AEEVideoStreamActivity.this));
        intent1 = new Intent();
        try
        {
            intent1.setClass(getApplicationContext(), com/arcsoft/mediaplus/setting/AEESettingCMDListActivity);
            startActivityForResult(intent1, 0x10000065);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1) { }
        if (true) goto _L20; else goto _L18
_L18:
        Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b015f, 0).show();
        return;
_L9:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                AEEVideoStreamActivity.access$2300(AEEVideoStreamActivity.this, 3);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                AEEVideoStreamActivity.access$2300(AEEVideoStreamActivity.this, 4);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                AEEVideoStreamActivity.access$2300(AEEVideoStreamActivity.this, 5);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L12:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                AEEVideoStreamActivity.access$2300(AEEVideoStreamActivity.this, 6);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L13:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            }
            try
            {
                mSocketClient = AEESocketClient.getInstanceClient();
                if (mSocketClient.isConnected())
                {
                    AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                    AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000033, -1, 0L);
                    return;
                }
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return;
            }
        }
        if (true) goto _L1; else goto _L14
_L14:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$800(AEEVideoStreamActivity.this) != 0 && AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) != 19 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, true);
                AEEVideoStreamActivity.access$2300(AEEVideoStreamActivity.this, 8);
                return;
            }
        }
        continue; /* Loop/switch isn't completed */
_L15:
        if (!AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this) && AEEVideoStreamActivity.access$1800(AEEVideoStreamActivity.this) == -1 && !AEEVideoStreamActivity.access$1600(AEEVideoStreamActivity.this))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                Toast.makeText(AEEVideoStreamActivity.this, 0x7f0b0164, 0).show();
                return;
            } else
            {
                AEEVideoStreamActivity.access$1802(AEEVideoStreamActivity.this, 0x10000067);
                mSocketClient.setOnRequestRespondsListener(null);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), com/arcsoft/mediaplus/MediaPlusActivity);
                startActivityForResult(intent, 0x10000067);
                return;
            }
        }
        if (true) goto _L1; else goto _L21
_L21:
    }

    ty()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
