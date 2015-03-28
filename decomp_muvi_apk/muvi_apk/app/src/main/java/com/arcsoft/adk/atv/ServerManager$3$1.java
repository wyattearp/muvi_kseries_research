// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;

// Referenced classes of package com.arcsoft.adk.atv:
//            ServerManager, DLNA, DeviceInfoCache

class r.DownloadResult
    implements Runnable
{

    final sc this$1;
    final com.arcsoft.util.network.nloadResult val$downloadresult;

    public void run()
    {
        rverCacheInfo rvercacheinfo;
        sc sc;
        rvercacheinfo = (rverCacheInfo)val$downloadresult.request.userdata;
        sc = getServerDesc(rvercacheinfo.udn);
        if (sc != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        File file;
        byte abyte0[];
        FileInputStream fileinputstream;
        file = new File(val$downloadresult.filePath);
        sc.m_DeviceIcon = BitmapFactory.decodeFile(val$downloadresult.filePath);
        if (sc.m_DeviceIcon == null)
        {
            file.delete();
            return;
        }
        abyte0 = new byte[(int)file.length()];
        fileinputstream = null;
        FileInputStream fileinputstream1 = new FileInputStream(file);
        if (fileinputstream1 == null)
        {
            break MISSING_BLOCK_LABEL_135;
        }
        fileinputstream1.read(abyte0);
        rvercacheinfo.icondata = abyte0;
        ServerManager.access$1300(_fld0).getCacheManager().saveServerCacheInfo(rvercacheinfo);
        file.delete();
        verStatusListener averstatuslistener[];
        int i;
        int j;
        if (fileinputstream1 != null)
        {
            try
            {
                fileinputstream1.close();
            }
            catch (Exception exception) { }
        }
_L4:
        averstatuslistener = ServerManager.access$400(_fld0);
        if (averstatuslistener != null)
        {
            i = averstatuslistener.length;
            j = 0;
            while (j < i) 
            {
                averstatuslistener[j].onServerMetaChanged(sc);
                j++;
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception5;
        exception5;
_L7:
        file.delete();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (Exception exception2) { }
        }
          goto _L4
        Exception exception3;
        exception3;
_L6:
        file.delete();
        if (fileinputstream != null)
        {
            try
            {
                fileinputstream.close();
            }
            catch (Exception exception4) { }
        }
        throw exception3;
        exception3;
        fileinputstream = fileinputstream1;
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception1;
        exception1;
        fileinputstream = fileinputstream1;
          goto _L7
    }

    r.DownloadResult()
    {
        this$1 = final_downloadresult1;
        val$downloadresult = com.arcsoft.util.network.nloadResult.this;
        super();
    }

    // Unreferenced inner class com/arcsoft/adk/atv/ServerManager$3

/* anonymous class */
    class ServerManager._cls3
        implements com.arcsoft.util.network.FileDownloader.IDownloadListener
    {

        final ServerManager this$0;

        public void onDownloadFinished(com.arcsoft.util.network.FileDownloader.DownloadResult downloadresult1)
        {
            if (downloadresult1 == null || downloadresult1.filePath == null)
            {
                return;
            } else
            {
                ServerManager.access$1300(ServerManager.this).postRunnable(downloadresult1. new ServerManager._cls3._cls1(), 0L);
                return;
            }
        }

        public void onDownloadStarted(com.arcsoft.util.network.FileDownloader.DownloadRequest downloadrequest, long l)
        {
        }

            
            {
                this$0 = ServerManager.this;
                super();
            }
    }

}
