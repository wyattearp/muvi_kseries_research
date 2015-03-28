// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.arcsoft.mediaplus.listview.LoadingDialog;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0 extends Handler
{

    final AEESettingCMDListActivity this$0;

    public void handleMessage(Message message)
    {
        super.handleMessage(message);
        message.what;
        JVM INSTR tableswitch 17825793 17825801: default 60
    //                   17825793 61
    //                   17825794 84
    //                   17825795 107
    //                   17825796 156
    //                   17825797 305
    //                   17825798 329
    //                   17825799 380
    //                   17825800 414
    //                   17825801 562;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        return;
_L2:
        AEESettingCMDListActivity.access$2200(AEESettingCMDListActivity.this, (String)message.obj, message.arg1, message.arg2);
        return;
_L3:
        AEESettingCMDListActivity.access$2300(AEESettingCMDListActivity.this, (String)message.obj, message.arg1, message.arg2);
        return;
_L4:
        Toast.makeText(AEESettingCMDListActivity.this, message.arg1, 0).show();
        if (AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this) != null && AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).isShowing())
        {
            AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).dismiss();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        int k;
        int l;
        String s1;
        k = message.arg1;
        l = message.arg2;
        s1 = (String)message.obj;
        if (k == -1 && l == -1)
        {
            continue; /* Loop/switch isn't completed */
        }
        AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        if (AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this) == null || !AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).isConnected())
        {
            continue; /* Loop/switch isn't completed */
        }
        if (k == -1)
        {
            try
            {
                AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).startRespondParams(l);
                AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(l, s1);
                return;
            }
            catch (IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            return;
        }
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).startRespondParams(k);
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(k, s1);
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).setNextCMD(l);
        return;
_L6:
        if (message.arg1 == 1)
        {
            AEESettingCMDListActivity.access$2500(AEESettingCMDListActivity.this);
            return;
        } else
        {
            AEESettingCMDListActivity.access$2600(AEESettingCMDListActivity.this);
            return;
        }
_L7:
        try
        {
            AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).stopRespondParams();
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).setOnRequestRespondsListener(null);
            AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).requestConfig();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return;
_L8:
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100005, 0, null, 0, -1);
        if (AEESettingCMDListActivity.access$2700(AEESettingCMDListActivity.this) != null)
        {
            AEESettingCMDListActivity.access$2700(AEESettingCMDListActivity.this).notifyDataSetChanged();
            return;
        }
        if (true) goto _L1; else goto _L9
_L9:
        String s;
        int i;
        int j;
        message.arg2;
        s = (String)message.obj;
        i = message.arg1;
        j = 0;
        i;
        JVM INSTR tableswitch 1 1: default 456
    //                   1 508;
           goto _L11 _L12
_L11:
        if (j != 0)
        {
            Toast.makeText(AEESettingCMDListActivity.this, j, 0).show();
        }
        if (AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this) != null && AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).isShowing())
        {
            AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).dismiss();
            return;
        }
        break; /* Loop/switch isn't completed */
_L12:
        j = 0x7f0b018e;
        if (s != null && s.contains("16777218"))
        {
            j = 0x7f0b018d;
        } else
        if (s != null && s.contains("16777220"))
        {
            j = 0x7f0b0198;
        }
        AEESettingCMDListActivity.access$802(AEESettingCMDListActivity.this, j);
        if (true) goto _L11; else goto _L13
_L13:
        if (false)
        {
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if (AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this) != null && AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).isShowing())
        {
            AEESettingCMDListActivity.access$2400(AEESettingCMDListActivity.this).dismiss();
            return;
        }
        if (true) goto _L1; else goto _L14
_L14:
    }

    ttingCMDAdapter()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
