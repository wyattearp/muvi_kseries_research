// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.MediaPlayer.SecureMediaPlayer;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamActivity, AEESocketClient

class this._cls0 extends Handler
{

    final AEEVideoStreamActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 18: default 96
    //                   0 120
    //                   1 138
    //                   2 1391
    //                   3 1459
    //                   4 374
    //                   5 828
    //                   6 260
    //                   7 838
    //                   8 992
    //                   9 1266
    //                   10 1553
    //                   11 1567
    //                   12 1896
    //                   13 1917
    //                   14 1994
    //                   15 298
    //                   16 1358
    //                   17 102
    //                   18 2026;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L1:
        super.handleMessage(message);
_L22:
        return;
_L19:
        Toast.makeText(AEEVideoStreamActivity.this, message.arg1, 0).show();
        continue; /* Loop/switch isn't completed */
_L2:
        AEEVideoStreamActivity.access$2400(AEEVideoStreamActivity.this, message.arg1, message.arg2);
        continue; /* Loop/switch isn't completed */
_L3:
        Log.e("AEEVideoStreamActivity", "MSG_SHOW_SURFACEVIEW");
        if (!AEEVideoStreamActivity.access$2500(AEEVideoStreamActivity.this) || AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this)) goto _L22; else goto _L21
_L21:
        AEEVideoStreamActivity.access$2700(AEEVideoStreamActivity.this, true);
        mSocketClient = AEESocketClient.getInstanceClient();
        if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        IOException ioexception4;
        if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).getVisibility() != 0)
        {
            AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).setVisibility(0);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            if (!AEEVideoStreamActivity.access$500(AEEVideoStreamActivity.this))
            {
                showStream();
                AEEVideoStreamActivity.access$502(AEEVideoStreamActivity.this, true);
            }
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception4)
        {
            ioexception4.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this) != null && AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).getVisibility() == 0)
        {
            AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).setVisibility(8);
        }
        continue; /* Loop/switch isn't completed */
_L17:
        if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this) != null && AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).getVisibility() == 0)
        {
            AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).setVisibility(8);
        }
        if (AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$000(AEEVideoStreamActivity.this).reset();
        }
        AEEVideoStreamActivity.access$502(AEEVideoStreamActivity.this, false);
        AEEVideoStreamActivity.access$202(AEEVideoStreamActivity.this, false);
        continue; /* Loop/switch isn't completed */
_L6:
        String s1;
        int j1;
        int k1;
        message.arg2;
        s1 = (String)message.obj;
        j1 = message.arg1;
        k1 = 0;
        j1;
        JVM INSTR tableswitch -19 1: default 496
    //                   -19 686
    //                   -18 821
    //                   -17 722
    //                   -16 814
    //                   -15 496
    //                   -14 807
    //                   -13 800
    //                   -12 793
    //                   -11 496
    //                   -10 496
    //                   -9 786
    //                   -8 779
    //                   -7 772
    //                   -6 496
    //                   -5 496
    //                   -4 765
    //                   -3 758
    //                   -2 496
    //                   -1 496
    //                   0 496
    //                   1 517;
           goto _L23 _L24 _L25 _L26 _L27 _L23 _L28 _L29 _L30 _L23 _L23 _L31 _L32 _L33 _L23 _L23 _L34 _L35 _L23 _L23 _L23 _L36
_L23:
        if (k1 != 0)
        {
            Toast.makeText(AEEVideoStreamActivity.this, k1, 0).show();
        }
        continue; /* Loop/switch isn't completed */
_L36:
        k1 = 0x7f0b018e;
        if (s1 != null && s1.contains("16777218"))
        {
            if (AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
            {
                AEEVideoStreamActivity.access$2800(AEEVideoStreamActivity.this);
                AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
                if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
                }
            }
            switchTo(1, 19);
            return;
        }
        if (s1 != null && s1.contains("16777220"))
        {
            k1 = 0x7f0b0198;
        }
        if (k1 != 0x7f0b018e && AEEVideoStreamActivity.access$900(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$2800(AEEVideoStreamActivity.this);
            AEEVideoStreamActivity.access$2900(AEEVideoStreamActivity.this, false, true);
            if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
        }
        continue; /* Loop/switch isn't completed */
_L24:
        if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
        {
            switchTo(2, -1);
            showSurfaceView(0);
        }
        k1 = 0x7f0b019c;
        continue; /* Loop/switch isn't completed */
_L26:
        if (AEEVideoStreamActivity.access$1000(AEEVideoStreamActivity.this) == 18)
        {
            switchTo(2, -1);
            showSurfaceView(0);
        }
        k1 = 0x7f0b0198;
        continue; /* Loop/switch isn't completed */
_L35:
        k1 = 0x7f0b018f;
        continue; /* Loop/switch isn't completed */
_L34:
        k1 = 0x7f0b0190;
        continue; /* Loop/switch isn't completed */
_L33:
        k1 = 0x7f0b0191;
        continue; /* Loop/switch isn't completed */
_L32:
        k1 = 0x7f0b0192;
        continue; /* Loop/switch isn't completed */
_L31:
        k1 = 0x7f0b0193;
        continue; /* Loop/switch isn't completed */
_L30:
        k1 = 0x7f0b0194;
        continue; /* Loop/switch isn't completed */
_L29:
        k1 = 0x7f0b0195;
        continue; /* Loop/switch isn't completed */
_L28:
        k1 = 0x7f0b0196;
        continue; /* Loop/switch isn't completed */
_L27:
        k1 = 0x7f0b0197;
        continue; /* Loop/switch isn't completed */
_L25:
        k1 = 0x7f0b0199;
        if (true) goto _L23; else goto _L7
_L7:
        resumePlayback();
        continue; /* Loop/switch isn't completed */
_L9:
        if (!RtspUtils.isAmbar())
        {
            break MISSING_BLOCK_LABEL_972;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        IOException ioexception3;
        if (mSocketClient != null && mSocketClient.isConnected())
        {
            AEEVideoStreamActivity.access$1602(AEEVideoStreamActivity.this, true);
            if (mSocketClient.sendCommand(0x10000001, null) != 1)
            {
                AEEVideoStreamActivity.access$1602(AEEVideoStreamActivity.this, false);
            }
            mSocketClient.startRespondParams(0x10000001);
            mSocketClient.setNextCMD(0x1000002b);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            switchTo(1, 12);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception3)
        {
            switchTo(1, 12);
            ioexception3.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
        switchTo(2, -1);
        showSurfaceView(0);
        continue; /* Loop/switch isn't completed */
_L10:
        int k;
        int l;
        k = message.arg1;
        l = message.arg2;
        if (k == -1 && l == -1) goto _L22; else goto _L37
_L37:
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient == null || !mSocketClient.isConnected()) goto _L39; else goto _L38
_L38:
        if (k == -1)
        {
            k = l;
            l = -1;
        }
        int i1;
        i1 = mSocketClient.sendCommand(k, null);
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("MSG_SEND_COMMAND val = ").append(i1).append(" msgId = ").append(k).toString());
        i1;
        JVM INSTR tableswitch 2 4: default 1140
    //                   2 1177
    //                   3 1194
    //                   4 1194;
           goto _L40 _L41 _L42 _L42
_L40:
        try
        {
            mSocketClient.startRespondParams(k);
            mSocketClient.setNextCMD(l);
        }
        catch (IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L41:
        AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, k, l, 200L);
        continue; /* Loop/switch isn't completed */
_L42:
        if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
            AEEVideoStreamActivity.access$400(AEEVideoStreamActivity.this, 18, 22, -1, null, 0L);
        }
        continue; /* Loop/switch isn't completed */
_L39:
        if (AEEVideoStreamActivity.access$1500(AEEVideoStreamActivity.this))
        {
            AEEVideoStreamActivity.access$1502(AEEVideoStreamActivity.this, false);
            AEEVideoStreamActivity.access$400(AEEVideoStreamActivity.this, 18, 22, -1, null, 0L);
        }
        continue; /* Loop/switch isn't completed */
_L11:
        Log.e("AEEVideoStreamActivity", (new StringBuilder()).append("MSG_UPDATE_EXE_SELECTED msg.arg1 = ").append(message.arg1).append(" msg.arg2 = ").append(message.arg2).toString());
        AEEVideoStreamActivity aeevideostreamactivity2 = AEEVideoStreamActivity.this;
        boolean flag3;
        boolean flag4;
        if (message.arg1 == 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (message.arg2 == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        AEEVideoStreamActivity.access$3000(aeevideostreamactivity2, flag3, flag4);
        continue; /* Loop/switch isn't completed */
_L18:
        AEEVideoStreamActivity aeevideostreamactivity1 = AEEVideoStreamActivity.this;
        boolean flag2;
        if (message.arg1 == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        AEEVideoStreamActivity.access$3100(aeevideostreamactivity1, flag2);
        continue; /* Loop/switch isn't completed */
_L4:
        if (AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this) > 0)
        {
            AEEVideoStreamActivity.access$3202(AEEVideoStreamActivity.this, AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this));
            AEEVideoStreamActivity.access$2102(AEEVideoStreamActivity.this, 0);
        }
        if (AEEVideoStreamActivity.access$2000(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$2000(AEEVideoStreamActivity.this).setText(TimeUtils.convertSecToTimeStringWithoutHour(AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this)));
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (AEEVideoStreamActivity.access$2000(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$2000(AEEVideoStreamActivity.this).setText(TimeUtils.convertSecToTimeStringWithoutHour(AEEVideoStreamActivity.access$2104(AEEVideoStreamActivity.this)));
        }
        if (AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this) != null)
        {
            AEEVideoStreamActivity.access$600(AEEVideoStreamActivity.this).sendEmptyMessageDelayed(3, 1000L);
        }
        if (AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this) % 60 == 0 && AEEVideoStreamActivity.access$2100(AEEVideoStreamActivity.this) > 0)
        {
            AEEVideoStreamActivity.access$1700(AEEVideoStreamActivity.this, 0x10000026, -1, 0L);
        }
        continue; /* Loop/switch isn't completed */
_L12:
        AEEVideoStreamActivity.access$3300(AEEVideoStreamActivity.this, message.arg1);
        continue; /* Loop/switch isn't completed */
_L13:
        boolean flag1;
        int i;
        int j;
        String s;
        IOException ioexception1;
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            flag1 = mSocketClient.isConnected();
        }
        catch (IOException ioexception)
        {
            return;
        }
        if (!flag1) goto _L22; else goto _L43
_L43:
        i = -1;
        j = message.arg1;
        s = null;
        j;
        JVM INSTR tableswitch 268435494 268435506: default 1676
    //                   268435494 1790
    //                   268435495 1733
    //                   268435496 1744
    //                   268435497 1755
    //                   268435498 1779
    //                   268435499 1676
    //                   268435500 1676
    //                   268435501 1801
    //                   268435502 1827
    //                   268435503 1840
    //                   268435504 1853
    //                   268435505 1866
    //                   268435506 1814;
           goto _L44 _L45 _L46 _L47 _L48 _L49 _L44 _L44 _L50 _L51 _L52 _L53 _L54 _L55
_L44:
        if (!mSocketClient.sendCommandSuc(j, s))
        {
            break; /* Loop/switch isn't completed */
        }
        mSocketClient.startRespondParams(j);
        mSocketClient.setNextCMD(i);
        continue; /* Loop/switch isn't completed */
_L46:
        i = 0x10000028;
        s = null;
        continue; /* Loop/switch isn't completed */
_L47:
        i = 0x10000029;
        s = null;
        continue; /* Loop/switch isn't completed */
_L48:
        AEEVideoStreamActivity.access$3400(AEEVideoStreamActivity.this, j, String.valueOf(AEEVideoStreamActivity.access$300(AEEVideoStreamActivity.this)));
        j = 0x1000002a;
_L49:
        i = 0x10000026;
        s = null;
        continue; /* Loop/switch isn't completed */
_L45:
        i = 0x1000002d;
        s = null;
        continue; /* Loop/switch isn't completed */
_L50:
        s = "video_resolution";
        i = 0x10000032;
        continue; /* Loop/switch isn't completed */
_L55:
        s = "video_fov";
        i = 0x1000002e;
        continue; /* Loop/switch isn't completed */
_L51:
        s = "photo_size";
        i = 0x1000002f;
        continue; /* Loop/switch isn't completed */
_L52:
        s = "photo_shot_mode";
        i = 0x10000030;
        continue; /* Loop/switch isn't completed */
_L53:
        s = "photo_continue_fast";
        i = 0x10000031;
        continue; /* Loop/switch isn't completed */
_L54:
        s = "photo_tlm";
        i = -1;
        if (true) goto _L44; else goto _L56
_L56:
        try
        {
            AEEVideoStreamActivity.access$400(AEEVideoStreamActivity.this, 11, j, -1, null, 500L);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L14:
        AEEVideoStreamActivity.access$3400(AEEVideoStreamActivity.this, message.arg1, (String)message.obj);
        continue; /* Loop/switch isn't completed */
_L15:
        Log.e("AEEVideoStreamActivity", "MSG_UPDATE_SURFACEVIEW");
        if (!AEEVideoStreamActivity.access$2500(AEEVideoStreamActivity.this) || AEEVideoStreamActivity.access$2600(AEEVideoStreamActivity.this)) goto _L22; else goto _L57
_L57:
        if (AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this) != null && AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).isShown())
        {
            AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).setVisibility(4);
            AEEVideoStreamActivity.access$1100(AEEVideoStreamActivity.this).setVisibility(0);
        }
        continue; /* Loop/switch isn't completed */
_L16:
        AEEVideoStreamActivity aeevideostreamactivity = AEEVideoStreamActivity.this;
        boolean flag;
        if (message.arg1 == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        AEEVideoStreamActivity.access$3500(aeevideostreamactivity, flag, message.arg2);
        continue; /* Loop/switch isn't completed */
_L20:
        AEEVideoStreamActivity.access$3600(AEEVideoStreamActivity.this, message.arg1);
        if (true) goto _L1; else goto _L58
_L58:
    }

    ()
    {
        this$0 = AEEVideoStreamActivity.this;
        super();
    }
}
