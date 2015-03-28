// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.service;

import com.arcsoft.mediaplus.updownload.easytransfer.EasyTransferDriver;

// Referenced classes of package com.arcsoft.mediaplus.updownload.service:
//            UpDownloadEngine

private static class mEngine
{

    private UpDownloadEngine mEngine;
    private int mUpDownloadToken;

    public void onStart()
    {
        mUpDownloadToken = 1 + mUpDownloadToken;
        UpDownloadEngine.access$500(mEngine).start();
        if (mUpDownloadToken == 1)
        {
            mEngine.resumeAllTask();
        }
    }

    public void onStop()
    {
        mUpDownloadToken = -1 + mUpDownloadToken;
        if (mUpDownloadToken == 0)
        {
            mEngine.abortAllTask();
            UpDownloadEngine.access$3302(mEngine, null);
        }
        if (mUpDownloadToken < 0)
        {
            mUpDownloadToken = 0;
        }
    }

    public (UpDownloadEngine updownloadengine)
    {
        mEngine = null;
        mUpDownloadToken = 0;
        mEngine = updownloadengine;
    }
}
