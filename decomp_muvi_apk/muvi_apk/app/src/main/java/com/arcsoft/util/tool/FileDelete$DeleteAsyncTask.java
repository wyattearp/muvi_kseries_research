// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.tool;

import android.os.AsyncTask;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.util.tool:
//            FileDelete

private class <init> extends AsyncTask
{

    final FileDelete this$0;

    protected transient Integer doInBackground(Integer ainteger[])
    {
        Log.e("FileDelete", "doDeleteRemote doInBackground");
        if (FileDelete.access$100(FileDelete.this))
        {
            return Integer.valueOf(FileDelete.access$200(FileDelete.this, ainteger[0].intValue()));
        } else
        {
            return Integer.valueOf(FileDelete.access$300(FileDelete.this, ainteger[0].intValue()));
        }
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((Integer[])aobj);
    }

    protected void onPostExecute(Integer integer)
    {
        Log.e("FileDelete", (new StringBuilder()).append("DeleteAsyncTask onPostExecute sucNum = ").append(integer).append(" deleteNum = ").append(FileDelete.access$400(FileDelete.this)).toString());
        FileDelete.access$500(FileDelete.this).onDeleted(integer.intValue(), FileDelete.access$400(FileDelete.this));
        FileDelete.access$600(FileDelete.this);
        super.onPostExecute(integer);
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((Integer)obj);
    }

    private ()
    {
        this$0 = FileDelete.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
