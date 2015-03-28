// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import com.arcsoft.util.debug.Log;
import java.io.IOException;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEEVideoStreamFullScreenActivity, AEESocketClient

class this._cls0
    implements android.view.reenActivity._cls6
{

    final AEEVideoStreamFullScreenActivity this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR lookupswitch 3: default 40
    //                   2131296539: 632
    //                   2131296542: 41
    //                   2131296555: 271;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L3:
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("mOnClickListener video_view mCurConnectStatus = ").append(AEEVideoStreamFullScreenActivity.access$1000(AEEVideoStreamFullScreenActivity.this)).append(", isRecting = ").append(AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this)).toString());
        if (AEEVideoStreamFullScreenActivity.access$1000(AEEVideoStreamFullScreenActivity.this) == 2 || AEEVideoStreamFullScreenActivity.access$1000(AEEVideoStreamFullScreenActivity.this) == 1 && (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 20 || AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18))
        {
            boolean flag;
            AEEVideoStreamFullScreenActivity aeevideostreamfullscreenactivity;
            boolean flag1;
            if (AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this) != null && AEEVideoStreamFullScreenActivity.access$1300(AEEVideoStreamFullScreenActivity.this).getVisibility() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            aeevideostreamfullscreenactivity = AEEVideoStreamFullScreenActivity.this;
            if (flag)
            {
                flag1 = true;
            } else
            if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 18)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            AEEVideoStreamFullScreenActivity.access$1402(aeevideostreamfullscreenactivity, flag1);
            if (AEEVideoStreamFullScreenActivity.access$1400(AEEVideoStreamFullScreenActivity.this))
            {
                switchTo(1, 20);
                return;
            }
            if (AEEVideoStreamFullScreenActivity.access$1500(AEEVideoStreamFullScreenActivity.this))
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
        Log.e("AEEVideoStreamFullScreenActivity", (new StringBuilder()).append("stream_execute_btn isExecuting = ").append(AEEVideoStreamFullScreenActivity.access$1600(AEEVideoStreamFullScreenActivity.this)).append(" mCurConnectStatus = ").append(AEEVideoStreamFullScreenActivity.access$1000(AEEVideoStreamFullScreenActivity.this)).append(" mCurConnectParams = ").append(AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this)).toString());
        if (AEEVideoStreamFullScreenActivity.access$1600(AEEVideoStreamFullScreenActivity.this) || AEEVideoStreamFullScreenActivity.access$1000(AEEVideoStreamFullScreenActivity.this) == 0 || AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) == 19 || AEEVideoStreamFullScreenActivity.access$1700(AEEVideoStreamFullScreenActivity.this))
        {
            continue; /* Loop/switch isn't completed */
        }
        mSocketClient = AEESocketClient.getInstanceClient();
        if (!mSocketClient.isConnected())
        {
            break MISSING_BLOCK_LABEL_487;
        }
        mSocketClient.setIsNeedFreshFiles(true);
_L5:
        AEEVideoStreamFullScreenActivity.access$1602(AEEVideoStreamFullScreenActivity.this, true);
        IOException ioexception;
        switch (AEEVideoStreamFullScreenActivity.access$1800(AEEVideoStreamFullScreenActivity.this))
        {
        case 7: // '\007'
        default:
            return;

        case 3: // '\003'
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000004, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000003, 0x10000021, 0L);
                return;
            }

        case 4: // '\004'
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000006, -1, 0L);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 4);
            return;

        case 5: // '\005'
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000007, -1, 0L);
            AEEVideoStreamFullScreenActivity.access$2000(AEEVideoStreamFullScreenActivity.this, true, 5);
            return;

        case 6: // '\006'
            if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000008, -1, 0L);
                return;
            } else
            {
                AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000014, -1, 0L);
                return;
            }

        case 8: // '\b'
            break;
        }
        break MISSING_BLOCK_LABEL_598;
        try
        {
            Toast.makeText(AEEVideoStreamFullScreenActivity.this, 0x7f0b015e, 0).show();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
          goto _L5
        if (AEEVideoStreamFullScreenActivity.access$1100(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000004, -1, 0L);
            return;
        } else
        {
            AEEVideoStreamFullScreenActivity.access$1900(AEEVideoStreamFullScreenActivity.this, 0x10000005, -1, 0L);
            return;
        }
_L2:
        if (AEEVideoStreamFullScreenActivity.access$1200(AEEVideoStreamFullScreenActivity.this) != 19 && !AEEVideoStreamFullScreenActivity.access$1700(AEEVideoStreamFullScreenActivity.this))
        {
            AEEVideoStreamFullScreenActivity.access$2100(AEEVideoStreamFullScreenActivity.this);
            return;
        }
        if (true) goto _L1; else goto _L6
_L6:
    }

    ()
    {
        this$0 = AEEVideoStreamFullScreenActivity.this;
        super();
    }
}
