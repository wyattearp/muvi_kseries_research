// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.AsyncTask;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEESocketClient;
import java.io.IOException;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            AEESettingCMDListActivity

private class this._cls0 extends AsyncTask
{

    final AEESettingCMDListActivity this$0;

    protected transient Boolean doInBackground(Integer ainteger[])
    {
        boolean flag;
        AEESettingCMDListActivity.access$1402(AEESettingCMDListActivity.this, AEESocketClient.getInstanceClient());
        if (!AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).isConnected())
        {
            break MISSING_BLOCK_LABEL_323;
        }
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(0x10000013, (String)null);
        if (!getRespond())
        {
            break MISSING_BLOCK_LABEL_309;
        }
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 2 respond = ").append(false).toString());
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(0x10000034, AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this));
        flag = getRespond();
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 3 respond = ").append(flag).toString());
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(0x10000012, (String)null);
        getRespond();
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 4 respond = ").append(flag).toString());
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_303;
        }
        AEESettingCMDListActivity.access$1400(AEESettingCMDListActivity.this).sendCommand(0x1000001f, null);
        getRespond();
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 5 respond = ").append(flag).toString());
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100001, 50, AEESettingCMDListActivity.access$2800(AEESettingCMDListActivity.this), ainteger[0].intValue(), ainteger[1].intValue());
        Log.e("AEESettingCMDListActivity", (new StringBuilder()).append("UpdateAsyncTask 5 params[0] = ").append(ainteger[0]).append(" params[1] = ").append(ainteger[1]).toString());
        return Boolean.valueOf(flag);
        Boolean boolean1 = Boolean.valueOf(false);
        return boolean1;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        return Boolean.valueOf(false);
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Integer[])aobj);
    }

    protected void onPostExecute(Boolean boolean1)
    {
        AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100005, 0, null, 0, -1);
        if (!boolean1.booleanValue())
        {
            AEESettingCMDListActivity.access$000(AEESettingCMDListActivity.this, 0x1100003, 0, null, 0x7f0b018e, -1);
        }
        super.onPostExecute(boolean1);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Boolean)obj);
    }

    private ()
    {
        this$0 = AEESettingCMDListActivity.this;
        super();
    }
}
