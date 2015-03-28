// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import android.net.Uri;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            AbsTaskItem

public interface IPoolDriver
{
    public static class DownloadRequest
    {

        public int abortflag;
        public int cancelflag;
        public String dms_uuid;
        public long downloadSize;
        public long fileSize;
        public String filepath;
        public String item_uuid;
        public IDownloadListener listener;
        public long media_id;
        public String parentdir;
        public String protocolInfo;
        public long tableid;
        public String title;
        public int upnp_class;
        public String uri;
        public Object userdata;
        public boolean videoOrImage;

        public DownloadRequest()
        {
            downloadSize = 0L;
            cancelflag = 0;
            abortflag = 0;
            protocolInfo = null;
            videoOrImage = false;
        }
    }

    public static class DownloadResult
    {

        public int errorcode;
        public String filePath;
        public DownloadRequest request;
        public long tableid;

        public DownloadResult()
        {
        }
    }

    public static interface IDownloadListener
    {

        public abstract void onDownloadFinished(DownloadResult downloadresult);

        public abstract void onDownloadProgress(DownloadRequest downloadrequest, long l);

        public abstract void onDownloadStarted(DownloadRequest downloadrequest, long l);
    }


    public abstract void abortAllTask();

    public abstract int abortTask(String s);

    public abstract void cancelAllTask(boolean flag);

    public abstract int cancelTask(String s);

    public abstract boolean cancelTask(long l);

    public abstract boolean cancelTask(Uri uri, boolean flag);

    public abstract void controlSpeed(int i, int j);

    public abstract boolean getTask(long l, AbsTaskItem abstaskitem);

    public abstract void init(Context context, UpDownloadDBMgr updownloaddbmgr);

    public abstract boolean isThreadPoolActive();

    public abstract void recycle();

    public abstract void uninit();
}
