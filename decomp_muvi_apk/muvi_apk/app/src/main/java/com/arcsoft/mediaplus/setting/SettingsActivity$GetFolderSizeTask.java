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

    protected transient String doInBackground(String as[])
    {
        if (as == null || as.length == 0)
        {
            return null;
        } else
        {
            SettingsActivity.access$502(SettingsActivity.this, FileUtils.getFolderSizeFormated(as[0]));
            return FileUtils.formatSize(SettingsActivity.access$500(SettingsActivity.this));
        }
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((String)obj);
    }

    protected void onPostExecute(String s)
    {
        if (SettingsActivity.access$600(SettingsActivity.this) == null)
        {
            return;
        } else
        {
            updateCacheSize(s);
            return;
        }
    }

    ()
    {
        this$0 = SettingsActivity.this;
        super();
    }
}
