// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource;

import android.os.Message;

// Referenced classes of package com.arcsoft.mediaplus.datasource:
//            AbsRemoteDataSource

class val.fid
    implements Runnable
{

    final val.fid this$1;
    final long val$fid;
    final com.arcsoft.util.network.equest val$fr;

    public void run()
    {
        onDownloadStarted(val$fr, val$fid);
    }

    is._cls0()
    {
        this$1 = final__pcls0;
        val$fr = equest;
        val$fid = J.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/mediaplus/datasource/AbsRemoteDataSource$1

/* anonymous class */
    class AbsRemoteDataSource._cls1
        implements com.arcsoft.util.network.FileDownloader.IDownloadListener
    {

        final AbsRemoteDataSource this$0;

        public void onDownloadFinished(final com.arcsoft.util.network.FileDownloader.DownloadResult fr)
        {
            if (fr.request.requestcode != AbsRemoteDataSource.access$100(AbsRemoteDataSource.this))
            {
                return;
            } else
            {
                AbsRemoteDataSource.DownloadItemInfo _tmp = (AbsRemoteDataSource.DownloadItemInfo)fr.request.userdata;
                Message message = Message.obtain();
                message.what = 1;
                message.obj = new AbsRemoteDataSource._cls1._cls1();
                AbsRemoteDataSource.access$200(AbsRemoteDataSource.this).sendMessage(message);
                return;
            }
        }

        public void onDownloadStarted(final com.arcsoft.util.network.FileDownloader.DownloadRequest fr, long l)
        {
            if (fr.requestcode != AbsRemoteDataSource.access$100(AbsRemoteDataSource.this))
            {
                return;
            } else
            {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = l. new AbsRemoteDataSource._cls1._cls2();
                AbsRemoteDataSource.access$200(AbsRemoteDataSource.this).sendMessage(message);
                return;
            }
        }

            
            {
                this$0 = AbsRemoteDataSource.this;
                super();
            }

        // Unreferenced inner class com/arcsoft/mediaplus/datasource/AbsRemoteDataSource$1$1

/* anonymous class */
        class AbsRemoteDataSource._cls1._cls1
            implements Runnable
        {

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
        }

    }

}
