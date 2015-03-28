// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.setting;

import android.os.AsyncTask;
import com.arcsoft.util.FileUtils;

// Referenced classes of package com.arcsoft.mediaplus.setting:
//            SettingsActivity

class this._cls0 extends AsyncTask
{

    final SettingsActivity this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient Void doInBackground(String as[])
    {
        if (as == null || as.length == 0)
        {
            return null;
        } else
        {
            FileUtils.closeCacheDB(SettingsActivity.this);
            FileUtils.deleteFolder(as[0]);
            return null;
        }
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Void)obj);
    }

    protected void onPostExecute(Void void1)
    {
        dismissDeletingDlg();
        updateFolderSize();
        toastClearCacheOk();
        FileUtils.recreateCacheMgr(SettingsActivity.this);
    }

    ()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
