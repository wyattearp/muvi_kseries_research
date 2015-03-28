// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements com.arcsoft.videostream.aee.ondsListener
{

    final AEESettingCMDListActivity this$0;

    public void onRequestRespondsFinished(int i, String s, int j, String s1, int k, String s2)
    {
        int l;
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("onRequestRespondsFinished: curCmdType = ").append(i).append(" num = ").append(j).append(", param = ").append(s1).append(" respond = ").append(s).append(" others = ").append(s2).toString());
        l = -1;
        i;
        JVM INSTR lookupswitch 7: default 132
    //                   268435471: 913
    //                   268435472: 980
    //                   268435474: 631
    //                   268435475: 173
    //                   268435487: 726
    //                   268435495: 777
    //                   268435508: 268;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        if (j != 0)
        {
            Log.e("testP", (new StringBuilder()).append("sendCommandFailed curCmdType = ").append(i).toString());
            AEESettingCMDListActivity.access$3300(AEESettingCMDListActivity.this, j, i, s1);
        }
_L12:
        return;
_L5:
        int k1;
        AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        k1 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getNextCMD();
        if (k1 != 0x1100006)
        {
            break MISSING_BLOCK_LABEL_255;
        }
        synchronized (AEESettingCMDListActivity.access$3100(AEESettingCMDListActivity.this))
        {
            AEESettingCMDListActivity.access$3100(AEESettingCMDListActivity.this).sendEmptyMessage(0x1100006);
        }
        return;
        exception1;
        handler1;
        JVM INSTR monitorexit ;
        try
        {
            throw exception1;
        }
        catch (IOException ioexception3)
        {
            ioexception3.printStackTrace();
        }
        AEESettingCMDListActivity.access$3000(AEESettingCMDListActivity.this);
        return;
        AEESettingCMDListActivity.access$3200(AEESettingCMDListActivity.this, k1, -1, null, 0L);
        return;
_L8:
        int j1;
        AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        j1 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getNextCMD();
        l = j1;
_L10:
        if (j != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        AEESettingCMDListActivity.access$3200(AEESettingCMDListActivity.this, l, -1, null, 0L);
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("video_fov"))
        {
            AEESocketClient aeesocketclient5 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient5.setDVInfo(13, s1);
            return;
        }
        break; /* Loop/switch isn't completed */
        IOException ioexception2;
        ioexception2;
        ioexception2.printStackTrace();
        AEESettingCMDListActivity.access$3000(AEESettingCMDListActivity.this);
        if (true) goto _L10; else goto _L9
_L9:
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("video_resolution"))
        {
            AEESocketClient aeesocketclient4 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient4.setDVInfo(12, s1);
            return;
        }
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("photo_size"))
        {
            AEESocketClient aeesocketclient3 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient3.setDVInfo(14, s1);
            return;
        }
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("photo_shot_mode"))
        {
            AEESocketClient aeesocketclient2 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient2.setDVInfo(15, s1);
            return;
        }
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("photo_continue_fast"))
        {
            AEESocketClient aeesocketclient1 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient1.setDVInfo(16, s1);
            return;
        }
        if (AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this).contains("photo_tlm"))
        {
            AEESocketClient aeesocketclient = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this);
            if (s1 == null)
            {
                s1 = AEESettingCMDListActivity.access$700(AEESettingCMDListActivity.this);
            }
            aeesocketclient.setDVInfo(17, s1);
            return;
        }
        if (true) goto _L12; else goto _L11
_L11:
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b018e, -1);
        return;
_L4:
        int i1;
        AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        i1 = AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).getNextCMD();
        if (i1 != 0x1100006)
        {
            break MISSING_BLOCK_LABEL_713;
        }
        synchronized (AEESettingCMDListActivity.access$3100(AEESettingCMDListActivity.this))
        {
            AEESettingCMDListActivity.access$3100(AEESettingCMDListActivity.this).sendEmptyMessage(0x1100006);
        }
        return;
        exception;
        handler;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        AEESettingCMDListActivity.access$3000(AEESettingCMDListActivity.this);
        return;
        AEESettingCMDListActivity.access$3200(AEESettingCMDListActivity.this, i1, -1, null, 0L);
        return;
_L6:
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100001, 50, AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this), AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this), AEESettingCMDListActivity.access$500(AEESettingCMDListActivity.this));
        if (j != 0)
        {
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b018e, -1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (j == 0 && s1 != null)
        {
            String as[] = s1.split(";");
            String s3 = as[-2 + as.length];
            String as1[] = as[-1 + as.length].split(":");
            String s4 = as1[-1 + as1.length];
            AEESettingCMDListActivity.access$1302(AEESettingCMDListActivity.this, s3);
            AEESettingCMDListActivity.access$1202(AEESettingCMDListActivity.this, s4);
            Log.v("zdf", (new StringBuilder()).append("onRequestRespondsFinished: mDeviceName = ").append(AEESettingCMDListActivity.access$1300(AEESettingCMDListActivity.this)).append(", mStrVersion = ").append(AEESettingCMDListActivity.access$1200(AEESettingCMDListActivity.this)).toString());
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100007, 200, null, -1, -1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (j == 0 || j == 2)
        {
            try
            {
                AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
                AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).setIsNeedFreshFiles(true);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                AEESettingCMDListActivity.access$3000(AEESettingCMDListActivity.this);
            }
        }
        if (j != 0)
        {
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b018e, -1);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (j != 0)
        {
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b018e, -1);
            return;
        }
        if (true) goto _L12; else goto _L13
_L13:
    }

    dsListener()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
