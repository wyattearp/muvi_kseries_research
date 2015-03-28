// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource

class this._cls0
    implements com.arcsoft.util.network.adListener
{

    final AbsRemoteDataSource this$0;

    public void onDownloadFinished(final com.arcsoft.util.network.dResult fr)
    {
        if (fr.request.requestcode != AbsRemoteDataSource.access$100(AbsRemoteDataSource.this))
        {
            return;
        } else
        {
            wnloadItemInfo _tmp = (wnloadItemInfo)fr.request.userdata;
            Message message = Message.obtain();
            message.what = 1;
            message.obj = new Runnable() {

                final AbsRemoteDataSource._cls1 this$1;
                final com.arcsoft.util.network.FileDownloader.DownloadResult val$fr;

                public void run()
                {
                    onDownloadFinished(fr);
                }

            
            {
                this$1 = AbsRemoteDataSource._cls1.this;
                fr = downloadresult;
                super();
            }
            };
            AbsRemoteDataSource.access$200(AbsRemoteDataSource.this).sendMessage(message);
            return;
        }
    }

    public void onDownloadStarted(final com.arcsoft.util.network.dRequest fr, final long fid)
    {
        if (fr.requestcode != AbsRemoteDataSource.access$100(AbsRemoteDataSource.this))
        {
            return;
        } else
        {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = new Runnable() {

                final AbsRemoteDataSource._cls1 this$1;
                final long val$fid;
                final com.arcsoft.util.network.FileDownloader.DownloadRequest val$fr;

                public void run()
                {
                    onDownloadStarted(fr, fid);
                }

            
            {
                this$1 = AbsRemoteDataSource._cls1.this;
                fr = downloadrequest;
                fid = l;
                super();
            }
            };
            AbsRemoteDataSource.access$200(AbsRemoteDataSource.this).sendMessage(message);
            return;
        }
    }

    _cls2.val.fid()
    {
        this$0 = AbsRemoteDataSource.this;
        super();
    }
}
