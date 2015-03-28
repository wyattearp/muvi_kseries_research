// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.content.res.Resources;
import android.preference.Preference;
import android.widget.TimePicker;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            EasyTransferSettingActivity

class this._cls0
    implements android.app.ener
{

    final EasyTransferSettingActivity this$0;

    public void onTimeSet(TimePicker timepicker, int i, int j)
    {
        EasyTransferSettingActivity.access$002(EasyTransferSettingActivity.this, i);
        EasyTransferSettingActivity.access$102(EasyTransferSettingActivity.this, j);
        android.content.ingActivity ingactivity = EasyTransferSettingActivity.access$200(EasyTransferSettingActivity.this).getEditor();
        String s = getResources().getString(0x7f0b00f9);
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(EasyTransferSettingActivity.access$000(EasyTransferSettingActivity.this));
        aobj[1] = Integer.valueOf(EasyTransferSettingActivity.access$100(EasyTransferSettingActivity.this));
        String s1 = String.format("%02d:%02d", aobj);
        ingactivity.ring(s, s1);
        ingactivity.t();
        onPreferenceChange(EasyTransferSettingActivity.access$200(EasyTransferSettingActivity.this), s1);
    }

    A()
    {
        this$0 = EasyTransferSettingActivity.this;
        super();
    }
}
