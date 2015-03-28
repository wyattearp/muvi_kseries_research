// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UploadPoolDriver

private class this._cls0 extends Handler
{

    final int MESSAGE_REQUEST = 1;
    final UploadPoolDriver this$0;

    public void handleMessage(Message message)
    {
        if (message.what != 1)
        {
            return;
        }
        UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().lock();
        this._cls0 _lcls0 = null;
        int i = -1;
        int j = -1 + UploadPoolDriver.access$300(UploadPoolDriver.this).size();
        while (j >= 0) 
        {
            _lcls0 = (this._cls0)UploadPoolDriver.access$300(UploadPoolDriver.this).get(j);
            if (access._mth000(_lcls0) || access._mth100(_lcls0))
            {
                char c2;
                if (access._mth000(_lcls0))
                {
                    c2 = '\u0330';
                } else
                {
                    c2 = '\u0333';
                }
                UploadPoolDriver.access$400(UploadPoolDriver.this, _lcls0, c2);
                UploadPoolDriver.access$300(UploadPoolDriver.this).remove(_lcls0);
            }
            j--;
        }
        if (UploadPoolDriver.access$300(UploadPoolDriver.this).size() > 0)
        {
            _lcls0 = (this._cls0)UploadPoolDriver.access$300(UploadPoolDriver.this).get(0);
            i = _lcls0.state;
        }
        UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().unlock();
        if (i > -1)
        {
            if (i == 1)
            {
                UploadPoolDriver.access$500(UploadPoolDriver.this, _lcls0);
            } else
            if (i == 2)
            {
                UploadPoolDriver.access$600(UploadPoolDriver.this, _lcls0);
            } else
            if (i == 3)
            {
                if (access._mth700(_lcls0) == 0)
                {
                    UploadPoolDriver.access$800(UploadPoolDriver.this, _lcls0);
                } else
                {
                    char c;
                    if (access._mth700(_lcls0) == 2)
                    {
                        c = '\u03F5';
                    } else
                    {
                        c = '\u03F6';
                    }
                    UploadPoolDriver.access$900(UploadPoolDriver.this, _lcls0, c);
                }
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().lock();
                UploadPoolDriver.access$300(UploadPoolDriver.this).remove(_lcls0);
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().unlock();
            }
            if (access._mth000(_lcls0) || access._mth100(_lcls0))
            {
                char c1;
                if (access._mth000(_lcls0))
                {
                    c1 = '\u0330';
                } else
                {
                    c1 = '\u0333';
                }
                UploadPoolDriver.access$400(UploadPoolDriver.this, _lcls0, c1);
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().lock();
                UploadPoolDriver.access$300(UploadPoolDriver.this).remove(_lcls0);
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().unlock();
            } else
            if (_lcls0.state == 4)
            {
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().lock();
                UploadPoolDriver.access$300(UploadPoolDriver.this).remove(_lcls0);
                UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().unlock();
            }
        }
        UploadPoolDriver.access$200(UploadPoolDriver.this).readLock().lock();
        if (UploadPoolDriver.access$300(UploadPoolDriver.this).size() > 0)
        {
            UploadPoolDriver.access$1000(UploadPoolDriver.this).getClass();
            sendEmptyMessageDelayed(1, 1000 * UploadPoolDriver.access$1100(UploadPoolDriver.this));
        }
        UploadPoolDriver.access$200(UploadPoolDriver.this).readLock().unlock();
    }

    (Looper looper)
    {
        this$0 = UploadPoolDriver.this;
        super(looper);
    }
}
