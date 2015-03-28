// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.MimeTypeUtils;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            UpDownloadUtils

public class AbsPoolDriver
{

    public static final int HASHCODE_LEN = 10;
    public static final int MAX_PATH = 260;
    public int CONNECTION_TIMEOUT;
    public int READ_TIMEOUT;
    public final String TEMP_DIR = ".tmpdir";
    public final String TEMP_SUBFIX = "_tmp";
    public int mBandWidth;
    protected Context mContext;
    protected ReadWriteLock mLock;
    public int mPercent;
    protected UpDownloadDBMgr mUpDownloadDBMgr;

    public AbsPoolDriver()
    {
        CONNECTION_TIMEOUT = 60000;
        READ_TIMEOUT = 60000;
        mContext = null;
        mUpDownloadDBMgr = null;
        mBandWidth = 20480;
        mPercent = 0;
        mLock = null;
    }

    public static String getDownloadPath(int i, String s, String s1, String s2, com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource)
    {
        String s3;
        if (s == null || s2 == null)
        {
            return null;
        }
        s3 = (new StringBuilder()).append(s).append("/").toString();
        if (s1 == null || !s1.contains(".")) goto _L2; else goto _L1
_L1:
        String s4 = s1;
_L4:
        String s7 = s4;
        return processSuffix((new File((new StringBuilder()).append(s3).append(s7).toString())).getAbsolutePath());
_L2:
        s4 = Uri.parse(s2).getLastPathSegment();
        if (s4 == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("url is valid:").append(s2).toString());
        }
        int j = s4.lastIndexOf(".");
        String s5;
        if (j < 0)
        {
            Log.i("AbsPoolDriver", (new StringBuilder()).append("file extension not find:").append(s4).toString());
            if (presentitem_resource == null)
            {
                Log.e("AbsPoolDriver", "protocol is null!");
                return null;
            }
            Log.i("AbsPoolDriver", (new StringBuilder()).append("download protocol =").append(presentitem_resource.m_strProtocolInfo).toString());
            String s8 = getFileExtension(i, presentitem_resource.m_strProtocolInfo);
            if (s8 == null)
            {
                Log.e("AbsPoolDriver", (new StringBuilder()).append("can not get extension based on protocol =").append(presentitem_resource.m_strProtocolInfo).toString());
                s8 = "";
            }
            s5 = (new StringBuilder()).append(".").append(s8).toString();
        } else
        {
            s5 = s4.substring(j);
        }
        if (s1 != null)
        {
            s4 = (new StringBuilder()).append(s1).append(s5).toString();
            if (s4.indexOf('/') > -1)
            {
                s4 = s4.replace('/', '_');
            }
            if (s4.indexOf('\\') > -1)
            {
                s4 = s4.replace('\\', '_');
            }
            if (s4.indexOf(':') > -1)
            {
                s4 = s4.replace(':', '_');
            }
            if (s4.indexOf('#') > -1)
            {
                s4 = s4.replace('#', ' ');
            }
        }
        if (s3.length() + s4.length() > 260)
        {
            int k = 10 + (-260 + s4.length()) + s3.length();
            String s6 = (new StringBuilder()).append("").append(s4.hashCode()).toString();
            s4 = (new StringBuilder()).append(s6).append(s4.substring(k)).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected static String getFileExtension(int i, String s)
    {
        return MimeTypeUtils.getExtensionMapMimeType(UpDownloadUtils.getProtocolMimeType(i, s));
    }

    static String processSuffix(String s)
    {
        String s1;
        int i;
        if (s != null)
        {
            if ((s1 = FileUtils.getExtension(s)) != null && (i = s1.length()) != 0)
            {
                String s2 = s.substring(0, -1 + (s.length() - i));
                return (new StringBuilder()).append(s2).append(".").append(s1.toLowerCase()).toString();
            }
        }
        return s;
    }

    public boolean cancelTask(Uri uri, boolean flag)
    {
        return false;
    }

    protected String getDownloadTmpPath(IPoolDriver.DownloadRequest downloadrequest, String s, com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource)
    {
        String s1 = getDownloadPath(downloadrequest.upnp_class, OEMConfig.TEMP_BASE_PATH, downloadrequest.title, downloadrequest.uri, presentitem_resource);
        if (s1 != null)
        {
            return (new StringBuilder()).append(s1).append(s).toString();
        } else
        {
            return null;
        }
    }

    protected boolean mkDownloadDir(File file)
    {
        mLock.writeLock().lock();
        boolean flag;
        if (!file.exists() && !file.mkdir())
        {
            flag = false;
        } else
        {
            flag = true;
        }
        mLock.writeLock().unlock();
        return flag;
    }

    protected void notifyDownloadError(IPoolDriver.DownloadRequest downloadrequest, int i)
    {
        if (downloadrequest.listener != null)
        {
            IPoolDriver.DownloadResult downloadresult = new IPoolDriver.DownloadResult();
            downloadresult.request = downloadrequest;
            downloadresult.tableid = downloadrequest.tableid;
            downloadresult.filePath = null;
            downloadresult.errorcode = i;
            downloadrequest.listener.onDownloadFinished(downloadresult);
        }
    }

    protected void notifyDownloadSuccess(IPoolDriver.DownloadRequest downloadrequest)
    {
        if (downloadrequest.listener != null)
        {
            IPoolDriver.DownloadResult downloadresult = new IPoolDriver.DownloadResult();
            downloadresult.request = downloadrequest;
            downloadresult.tableid = downloadrequest.tableid;
            downloadresult.filePath = downloadrequest.filepath;
            downloadresult.errorcode = 911;
            downloadrequest.listener.onDownloadFinished(downloadresult);
        }
    }

    protected void processDownloadCancel(IPoolDriver.DownloadRequest downloadrequest, int i)
    {
        if (downloadrequest.listener != null)
        {
            IPoolDriver.DownloadResult downloadresult = new IPoolDriver.DownloadResult();
            downloadresult.request = downloadrequest;
            downloadresult.tableid = downloadrequest.tableid;
            downloadresult.filePath = null;
            downloadresult.errorcode = i;
            downloadrequest.listener.onDownloadFinished(downloadresult);
        }
    }

    protected void processDownloadError(IPoolDriver.DownloadRequest downloadrequest, int i)
    {
        if (i == 816 || i == 819)
        {
            processDownloadCancel(downloadrequest, i);
            return;
        } else
        {
            downloadrequest.filepath = null;
            updateDownloadFilePath(downloadrequest, null);
            notifyDownloadError(downloadrequest, i);
            return;
        }
    }

    protected void processDownloadSuccess(IPoolDriver.DownloadRequest downloadrequest, String s, String s1)
    {
        if (FileUtils.getFileSize(s) <= 0L)
        {
            FileUtils.deleteFile(s);
            return;
        } else
        {
            downloadrequest.filepath = processTmpFile(downloadrequest, s, s1);
            notifyDownloadSuccess(downloadrequest);
            return;
        }
    }

    protected String processTmpFile(IPoolDriver.DownloadRequest downloadrequest, String s, String s1)
    {
        if (s == null)
        {
            return null;
        }
        File file = new File(s);
        File file1 = new File(s1);
        boolean flag = file.renameTo(file1);
        String s2 = null;
        if (flag)
        {
            s2 = file1.getAbsolutePath();
        }
        file.delete();
        return s2;
    }

    protected void updateDownloadFilePath(IPoolDriver.DownloadRequest downloadrequest, String s)
    {
        mLock.writeLock().lock();
        if (mUpDownloadDBMgr != null)
        {
            String s1 = Long.toString(downloadrequest.tableid);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("file_path", s);
            String as[] = {
                s1
            };
            mUpDownloadDBMgr.updateDownload(contentvalues, "_id=?", as);
        }
        mLock.writeLock().unlock();
    }

    protected void updateDownloadSize(IPoolDriver.DownloadRequest downloadrequest, long l)
    {
        mLock.writeLock().lock();
        if (mUpDownloadDBMgr != null)
        {
            String s = Long.toString(downloadrequest.tableid);
            String s1 = Long.toString(l);
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("current_size", s1);
            String as[] = {
                s
            };
            mUpDownloadDBMgr.updateDownload(contentvalues, "_id=?", as);
        }
        mLock.writeLock().unlock();
    }
}
