// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.view.View;
import android.widget.PopupWindow;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

class this._cls0
    implements android.view.MDListActivity._cls7
{

    final AEESettingCMDListActivity this$0;

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131296481 2131296484: default 36
    //                   2131296481 59
    //                   2131296482 223
    //                   2131296483 36
    //                   2131296484 37;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return;
_L4:
        setResult(AEESettingCMDListActivity.access$800(AEESettingCMDListActivity.this));
        finish();
        return;
_L2:
        if (AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this) == null || AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this) >= AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this).length)
        {
            continue; /* Loop/switch isn't completed */
        }
        AEESettingCMDListActivity.access$100(AEESettingCMDListActivity.this)[AEESettingCMDListActivity.access$300(AEESettingCMDListActivity.this)];
        JVM INSTR tableswitch 33 35: default 128
    //                   33 175
    //                   34 149
    //                   35 162;
           goto _L5 _L6 _L7 _L8
_L5:
        break; /* Loop/switch isn't completed */
_L7:
        break; /* Loop/switch isn't completed */
_L10:
        if (AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this) != null)
        {
            AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this).dismiss();
            return;
        }
        if (true) goto _L1; else goto _L9
_L9:
        AEESettingCMDListActivity.access$900(AEESettingCMDListActivity.this, 0x1000000f, null);
          goto _L10
_L8:
        AEESettingCMDListActivity.access$900(AEESettingCMDListActivity.this, 0x10000010, null);
          goto _L10
_L6:
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        AEESettingCMDListActivity.access$1002(AEESettingCMDListActivity.this, simpledateformat.format(new Date()));
        AEESettingCMDListActivity.access$900(AEESettingCMDListActivity.this, 0x10000039, AEESettingCMDListActivity.access$1000(AEESettingCMDListActivity.this));
          goto _L10
_L3:
        if (AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this) != null)
        {
            AEESettingCMDListActivity.access$600(AEESettingCMDListActivity.this).dismiss();
            return;
        }
        if (true) goto _L1; else goto _L11
_L11:
    }

    ()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
