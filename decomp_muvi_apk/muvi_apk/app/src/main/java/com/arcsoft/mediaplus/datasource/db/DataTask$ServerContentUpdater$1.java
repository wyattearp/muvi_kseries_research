// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask

class this._cls1 extends Handler
{

    final is._cls0 this$1;

    public void handleMessage(Message message)
    {
_L2:
        return;
        this._cls1 _lcls1;
        if (message.what != 0 || (_lcls1 = (this._cls1)message.obj) == null) goto _L2; else goto _L1
_L1:
        _lcls1._fld1 = 0;
        if (_lcls1._fld1 == 3) goto _L2; else goto _L3
_L3:
        boolean flag;
        Log.w("DataTask", (new StringBuilder()).append("Handle retry item , do retry").append(_lcls1._fld1).toString());
        flag = ReceivingData();
        cess._mth600(this._cls1.this, _lcls1);
        cess._mth700(this._cls1.this);
        if (!ReceivingData() || flag) goto _L2; else goto _L4
_L4:
        DataTask.access$802(_fld0, false);
        if (DataTask.access$900(_fld0) == null) goto _L2; else goto _L5
_L5:
        synchronized (DataTask.access$900(_fld0))
        {
            DataTask.access$1002(_fld0, 0);
            DataTask.access$900(_fld0).nDataTransmittedBegin(DataTask.access$1100(_fld0));
        }
        return;
        exception;
        _lcls1_1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    I()
    {
        this$1 = this._cls1.this;
        super();
    }
}
