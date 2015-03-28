// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;
import com.arcsoft.util.TimeUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.rtsp.RtspUtils;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient

class this._cls0 extends Handler
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void handleMessage(Message message)
    {
        message.what;
        JVM INSTR tableswitch 0 14: default 80
    //                   0 141
    //                   1 159
    //                   2 1144
    //                   3 1212
    //                   4 311
    //                   5 804
    //                   6 1415
    //                   7 814
    //                   8 968
    //                   9 1111
    //                   10 1339
    //                   11 1683
    //                   12 273
    //                   13 123
    //                   14 86;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L1:
        super.handleMessage(message);
_L18:
        return;
_L16:
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity1 = AEEVideoStreamFullScreenActivity.this;
        boolean flag2;
        if (message.arg2 == 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        AEEVideoStreamFullScreenActivity.access$2200(aeevideostreamfullscreenactivity1, flag2, message.arg1);
        continue; /* Loop/switch isn't completed */
_L15:
        Toast.makeText(AEEVideoStreamFullScreenActivity.this, message.arg1, 0).show();
        continue; /* Loop/switch isn't completed */
_L2:
        AEEVideoStreamFullScreenActivity.access$2300(AEEVideoStreamFullScreenActivity.this, message.arg1, message.arg2);
        continue; /* Loop/switch isn't completed */
_L3:
        if (!AEEVideoStreamFullScreenActivity.access$2400(AEEVideoStreamFullScreenActivity.this) || AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this)) goto _L18; else goto _L17
_L17:
        AEEVideoStreamFullScreenActivity.access$2600(AEEVideoStreamFullScreenActivity.this, true);
        mSocketClient = AEESocketClient.getInstanceClient();
        if (AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this) == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        IOException ioexception4;
        if (AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).getVisibility() != 0)
        {
            AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).setVisibility(0);
            continue; /* Loop/switch isn't completed */
        }
        try
        {
            if (!AEEVideoStreamFullScreenActivity.access$000(AEEVideoStreamFullScreenActivity.this))
            {
                showStream();
                AEEVideoStreamFullScreenActivity.access$002(AEEVideoStreamFullScreenActivity.this, true);
            }
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception4)
        {
            ioexception4.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L14:
        if (AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this) != null && AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).getVisibility() == 0)
        {
            AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).setVisibility(8);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        String s;
        int i1;
        int j1;
        message.arg2;
        s = (String)message.obj;
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("MSG_SEND_COMMAND_FAILED param = ").append(s).toString());
        i1 = message.arg1;
        j1 = 0;
        i1;
        JVM INSTR tableswitch -19 1: default 460
    //                   -19 662
    //                   -18 797
    //                   -17 698
    //                   -16 790
    //                   -15 460
    //                   -14 783
    //                   -13 776
    //                   -12 769
    //                   -11 460
    //                   -10 460
    //                   -9 762
    //                   -8 755
    //                   -7 748
    //                   -6 460
    //                   -5 460
    //                   -4 741
    //                   -3 734
    //                   -2 460
    //                   -1 460
    //                   0 460
    //                   1 481;
           goto _L19 _L20 _L21 _L22 _L23 _L19 _L24 _L25 _L26 _L19 _L19 _L27 _L28 _L29 _L19 _L19 _L30 _L31 _L19 _L19 _L19 _L32
_L19:
        if (j1 != 0)
        {
            Toast.makeText(AEEVideoStreamFullScreenActivity.this, j1, 0).show();
        }
        continue; /* Loop/switch isn't completed */
_L32:
        j1 = 0x7f0b018e;
        if (s != null && s.contains("16777218"))
        {
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$2700(AEEVideoStreamFullScreenActivity.this);
                AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
                if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
                {
                    switchTo(2, -1);
                    showSurfaceView(0);
                }
            }
            switchTo(1, 19);
            return;
        }
        if (s != null && s.contains("16777220"))
        {
            j1 = 0x7f0b0198;
        }
        if (j1 != 0x7f0b018e && AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$2700(AEEVideoStreamFullScreenActivity.this);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, false, AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this));
            if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
            {
                switchTo(2, -1);
                showSurfaceView(0);
            }
        }
        continue; /* Loop/switch isn't completed */
_L20:
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
        {
            switchTo(2, -1);
            showSurfaceView(0);
        }
        j1 = 0x7f0b019c;
        continue; /* Loop/switch isn't completed */
_L22:
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
        {
            switchTo(2, -1);
            showSurfaceView(0);
        }
        j1 = 0x7f0b0198;
        continue; /* Loop/switch isn't completed */
_L31:
        j1 = 0x7f0b018f;
        continue; /* Loop/switch isn't completed */
_L30:
        j1 = 0x7f0b0190;
        continue; /* Loop/switch isn't completed */
_L29:
        j1 = 0x7f0b0191;
        continue; /* Loop/switch isn't completed */
_L28:
        j1 = 0x7f0b0192;
        continue; /* Loop/switch isn't completed */
_L27:
        j1 = 0x7f0b0193;
        continue; /* Loop/switch isn't completed */
_L26:
        j1 = 0x7f0b0194;
        continue; /* Loop/switch isn't completed */
_L25:
        j1 = 0x7f0b0195;
        continue; /* Loop/switch isn't completed */
_L24:
        j1 = 0x7f0b0196;
        continue; /* Loop/switch isn't completed */
_L23:
        j1 = 0x7f0b0197;
        continue; /* Loop/switch isn't completed */
_L21:
        j1 = 0x7f0b0199;
        if (true) goto _L19; else goto _L7
_L7:
        resumePlayback();
        continue; /* Loop/switch isn't completed */
_L9:
        if (!RtspUtils.isAmbar())
        {
            break MISSING_BLOCK_LABEL_948;
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        IOException ioexception3;
        if (mSocketClient != null && mSocketClient.isConnected())
        {
            AEEVideoStreamFullScreenActivity.access$1702(AEEVideoStreamFullScreenActivity.this, true);
            if (mSocketClient.sendCommand(0x10000001, null) != 1)
            {
                AEEVideoStreamFullScreenActivity.access$1702(AEEVideoStreamFullScreenActivity.this, false);
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
        if (k == -1 && l == -1) goto _L18; else goto _L33
_L33:
        mSocketClient = AEESocketClient.getInstanceClient();
        if (mSocketClient == null || !mSocketClient.isConnected())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k == -1)
        {
            try
            {
                mSocketClient.sendCommand(l, null);
                mSocketClient.startRespondParams(l);
            }
            catch (IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            continue; /* Loop/switch isn't completed */
        }
        mSocketClient.sendCommand(k, null);
        mSocketClient.startRespondParams(k);
        mSocketClient.setNextCMD(l);
        continue; /* Loop/switch isn't completed */
_L11:
        AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity = AEEVideoStreamFullScreenActivity.this;
        boolean flag1;
        if (message.arg1 == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        AEEVideoStreamFullScreenActivity.access$2800(aeevideostreamfullscreenactivity, flag1);
        continue; /* Loop/switch isn't completed */
_L4:
        if (AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this) > 0)
        {
            AEEVideoStreamFullScreenActivity.access$3002(AEEVideoStreamFullScreenActivity.this, AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this));
            AEEVideoStreamFullScreenActivity.access$2902(AEEVideoStreamFullScreenActivity.this, 0);
        }
        if (AEEVideoStreamFullScreenActivity.access$3100(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$3100(AEEVideoStreamFullScreenActivity.this).setText(TimeUtils.convertSecToTimeStringWithoutHour(AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this)));
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (AEEVideoStreamFullScreenActivity.access$3100(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$3100(AEEVideoStreamFullScreenActivity.this).setText(TimeUtils.convertSecToTimeStringWithoutHour(AEEVideoStreamFullScreenActivity.access$2904(AEEVideoStreamFullScreenActivity.this)));
            Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("mFileSize.getText = ").append(AEEVideoStreamFullScreenActivity.access$3100(AEEVideoStreamFullScreenActivity.this).getText()).toString());
        }
        if (AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this) != null)
        {
            AEEVideoStreamFullScreenActivity.access$900(AEEVideoStreamFullScreenActivity.this).sendEmptyMessageDelayed(3, 1000L);
        }
        if (AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this) % 60 == 0 && AEEVideoStreamFullScreenActivity.access$2900(AEEVideoStreamFullScreenActivity.this) > 0)
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000026, -1, 0L);
        }
        continue; /* Loop/switch isn't completed */
_L12:
        Log.e("AEEVideoStreamFullScreenActivity", "MSG_UPDATE_SURFACEVIEW");
        if (!AEEVideoStreamFullScreenActivity.access$2400(AEEVideoStreamFullScreenActivity.this) || AEEVideoStreamFullScreenActivity.access$2500(AEEVideoStreamFullScreenActivity.this)) goto _L18; else goto _L34
_L34:
        if (AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this) != null && AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).isShown())
        {
            AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).setVisibility(4);
            AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).setVisibility(0);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        boolean flag;
        int i;
        int j;
        IOException ioexception1;
        try
        {
            mSocketClient = AEESocketClient.getInstanceClient();
            flag = mSocketClient.isConnected();
        }
        catch (IOException ioexception)
        {
            return;
        }
        if (!flag) goto _L18; else goto _L35
_L35:
        i = -1;
        j = message.arg1;
        j;
        JVM INSTR tableswitch 268435494 268435506: default 1516
    //                   268435494 1610
    //                   268435495 1571
    //                   268435496 1516
    //                   268435497 1579
    //                   268435498 1603
    //                   268435499 1516
    //                   268435500 1516
    //                   268435501 1618
    //                   268435502 1634
    //                   268435503 1642
    //                   268435504 1650
    //                   268435505 1658
    //                   268435506 1626;
           goto _L36 _L37 _L38 _L36 _L39 _L40 _L36 _L36 _L41 _L42 _L43 _L44 _L45 _L46
_L36:
        if (!mSocketClient.sendCommandSuc(j, null))
        {
            break; /* Loop/switch isn't completed */
        }
        mSocketClient.startRespondParams(j);
        mSocketClient.setNextCMD(i);
        continue; /* Loop/switch isn't completed */
_L38:
        i = 0x1000002a;
        continue; /* Loop/switch isn't completed */
_L39:
        AEEVideoStreamFullScreenActivity.access$3300(AEEVideoStreamFullScreenActivity.this, j, String.valueOf(AEEVideoStreamFullScreenActivity.access$3200(AEEVideoStreamFullScreenActivity.this)));
        j = 0x1000002a;
_L40:
        i = 0x10000026;
        continue; /* Loop/switch isn't completed */
_L37:
        i = 0x1000002d;
        continue; /* Loop/switch isn't completed */
_L41:
        i = 0x10000032;
        continue; /* Loop/switch isn't completed */
_L46:
        i = 0x1000002e;
        continue; /* Loop/switch isn't completed */
_L42:
        i = 0x1000002f;
        continue; /* Loop/switch isn't completed */
_L43:
        i = 0x10000030;
        continue; /* Loop/switch isn't completed */
_L44:
        i = 0x10000031;
        continue; /* Loop/switch isn't completed */
_L45:
        i = -1;
        if (true) goto _L36; else goto _L47
_L47:
        try
        {
            AEEVideoStreamFullScreenActivity.access$3400(AEEVideoStreamFullScreenActivity.this, 6, j, -1, null, 500L);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        continue; /* Loop/switch isn't completed */
_L13:
        AEEVideoStreamFullScreenActivity.access$3300(AEEVideoStreamFullScreenActivity.this, message.arg1, (String)message.obj);
        if (true) goto _L1; else goto _L48
_L48:
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
