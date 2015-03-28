// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import com.arcsoft.util.debug.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UploadPoolDriver

class this._cls0
    implements com.arcsoft.adk.atv.adResultListener
{

    final UploadPoolDriver this$0;

    public void OnUploadResult(int i, int j)
    {
        Log.i("UploadPoolDriver", (new StringBuilder()).append("OnUploadResult upload_id=").append(i).append(", errorcode=").append(j).toString());
        UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().lock();
        Iterator iterator = UploadPoolDriver.access$300(UploadPoolDriver.this).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            loadRequest loadrequest = (loadRequest)iterator.next();
            if (loadrequest.uploadId != i)
            {
                continue;
            }
            loadrequest.state = 3;
            loadRequest.access._mth702(loadrequest, j);
            break;
        } while (true);
        UploadPoolDriver.access$200(UploadPoolDriver.this).writeLock().unlock();
    }

    loadRequest()
    {
        this$0 = UploadPoolDriver.this;
        super();
    }
}
