// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.os.Handler;
import android.os.Message;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.FileDownloader;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource

private class <init> extends Handler
{

    static final long DOWNLOADINTERVAL = 500L;
    boolean bAutoDownloadFinish;
    boolean bDownloadThumb;
    int currentIndex;
    int firstIndex;
    final AbsRemoteDataSource this$0;

    public void handleMessage(Message message)
    {
        if (message.what == 1)
        {
            ((Runnable)message.obj).run();
        } else
        if (message.what == 0)
        {
            removeMessages(0);
            if (message.what == 0)
            {
                com.arcsoft.util.network.Handler handler = getDownloadRequest(currentIndex, bDownloadThumb);
                if (handler != null)
                {
                    FileDownloader.instance().download(handler);
                }
                if (getCount() > 0)
                {
                    int i = (1 + currentIndex) % getCount();
                    currentIndex = i;
                    if (i != firstIndex)
                    {
                        sendEmptyMessageDelayed(0, 500L);
                        return;
                    }
                }
                Log.w("auto", "audo download finish");
                bAutoDownloadFinish = true;
                return;
            }
        }
    }

    public void pause()
    {
        removeMessages(0);
    }

    public void reset()
    {
        firstIndex = 0;
        currentIndex = 0;
        bAutoDownloadFinish = false;
        resume();
    }

    public void resume()
    {
        if (bAutoDownloadFinish)
        {
            return;
        } else
        {
            removeMessages(0);
            sendEmptyMessageDelayed(0, 500L);
            return;
        }
    }

    public void setDownloadType(boolean flag, int i)
    {
        bAutoDownloadFinish = false;
        bDownloadThumb = flag;
        firstIndex = i;
        currentIndex = i;
        resume();
    }

    private ()
    {
        this$0 = AbsRemoteDataSource.this;
        super();
        bDownloadThumb = true;
        bAutoDownloadFinish = false;
        currentIndex = 0;
        firstIndex = 0;
    }

    firstIndex(firstIndex firstindex)
    {
        this();
    }
}
