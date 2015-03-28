// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;

import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;

// Referenced classes of package com.arcsoft.adk.atv:
//            ServerManager, DLNA, DeviceInfoCache

class this._cls0
    implements com.arcsoft.util.network.DownloadListener
{

    final ServerManager this$0;

    public void onDownloadFinished(final com.arcsoft.util.network.ownloadResult downloadresult)
    {
        if (downloadresult == null || downloadresult.filePath == null)
        {
            return;
        } else
        {
            ServerManager.access$1300(ServerManager.this).postRunnable(new Runnable() {

                final ServerManager._cls3 this$1;
                final com.arcsoft.util.network.FileDownloader.DownloadResult val$downloadresult;

                public void run()
                {
                    DeviceInfoCache.ServerCacheInfo servercacheinfo;
                    UPnP.MediaServerDesc mediaserverdesc;
                    servercacheinfo = (DeviceInfoCache.ServerCacheInfo)downloadresult.request.userdata;
                    mediaserverdesc = getServerDesc(servercacheinfo.udn);
                    if (mediaserverdesc != null) goto _L2; else goto _L1
_L1:
                    return;
_L2:
                    File file;
                    byte abyte0[];
                    FileInputStream fileinputstream;
                    file = new File(downloadresult.filePath);
                    mediaserverdesc.m_DeviceIcon = BitmapFactory.decodeFile(downloadresult.filePath);
                    if (mediaserverdesc.m_DeviceIcon == null)
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
                    servercacheinfo.icondata = abyte0;
                    ServerManager.access$1300(this$0).getCacheManager().saveServerCacheInfo(servercacheinfo);
                    file.delete();
                    ServerManager.IServerStatusListener aiserverstatuslistener[];
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
                    aiserverstatuslistener = ServerManager.access$400(this$0);
                    if (aiserverstatuslistener != null)
                    {
                        i = aiserverstatuslistener.length;
                        j = 0;
                        while (j < i) 
                        {
                            aiserverstatuslistener[j].onServerMetaChanged(mediaserverdesc);
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

            
            {
                this$1 = ServerManager._cls3.this;
                downloadresult = downloadresult1;
                super();
            }
            }, 0L);
            return;
        }
    }

    public void onDownloadStarted(com.arcsoft.util.network.ownloadRequest ownloadrequest, long l)
    {
    }

    _cls1.val.downloadresult()
    {
        this$0 = ServerManager.this;
        super();
    }
}
