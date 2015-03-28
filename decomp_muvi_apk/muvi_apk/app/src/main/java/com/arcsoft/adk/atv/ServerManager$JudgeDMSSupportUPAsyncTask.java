// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.os.AsyncTask;

// Referenced classes of package com.arcsoft.adk.atv:
//            ServerManager, DLNA

private class <init> extends AsyncTask
{

    final ServerManager this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((doInBackground[])aobj);
    }

    protected transient Void doInBackground(doInBackground adoinbackground[])
    {
        doInBackground doinbackground = adoinbackground[0];
        if (doinbackground != null)
        {
            doinbackground.doInBackground = DLNA.JNI_IsSupportUploader(ServerManager.access$1300(ServerManager.this).mNativeUPnP, doinbackground._fld0);
        }
        return null;
    }

    private ()
    {
        this$0 = ServerManager.this;
        super();
    }

    this._cls0(this._cls0 _pcls0)
    {
        this();
    }
}
