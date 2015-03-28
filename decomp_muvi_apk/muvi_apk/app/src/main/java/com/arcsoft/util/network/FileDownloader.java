// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import android.app.Application;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.CachePathManager;
import com.arcsoft.util.tool.SafeBuffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FileDownloader
{
    private class DownloadDriver extends Thread
    {

        private final AtomicInteger mStatus;
        final FileDownloader this$0;

        private boolean isResumed()
        {
            return mStatus.get() == 1;
        }

        private void pauseDriver()
        {
label0:
            {
                synchronized (mStatus)
                {
                    if (mStatus.get() != 2)
                    {
                        break label0;
                    }
                }
                return;
            }
            mStatus.set(0);
            atomicinteger;
            JVM INSTR monitorexit ;
            return;
            exception;
            atomicinteger;
            JVM INSTR monitorexit ;
            throw exception;
        }

        private void requestQuit()
        {
            mStatus.set(2);
            wake(mThreadPool);
            wake(mTaskQueue);
            wake(this);
        }

        private void resumeDriver()
        {
            synchronized (mStatus)
            {
                if (mStatus.get() == 0)
                {
                    mStatus.set(1);
                    wake(this);
                }
            }
            return;
            exception;
            atomicinteger;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void run()
        {
            Process.setThreadPriority(10);
_L3:
            if (mStatus.get() == 2)
            {
                break; /* Loop/switch isn't completed */
            }
            this;
            JVM INSTR monitorenter ;
            if (mStatus.get() == 1)
            {
                break MISSING_BLOCK_LABEL_47;
            }
            waitForTask(this);
            this;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            this;
            JVM INSTR monitorexit ;
            ArrayList arraylist = mThreadPool;
            arraylist;
            JVM INSTR monitorenter ;
            DownloadThread downloadthread = getDownloadThread();
            if (downloadthread != null)
            {
                break MISSING_BLOCK_LABEL_97;
            }
            waitForTask(mThreadPool);
            arraylist;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception1;
            exception1;
            arraylist;
            JVM INSTR monitorexit ;
            throw exception1;
            arraylist;
            JVM INSTR monitorexit ;
            TaskQueue taskqueue = mTaskQueue;
            taskqueue;
            JVM INSTR monitorenter ;
            DownloadRequest downloadrequest = (DownloadRequest)mTaskQueue.mUrgentQueue.get();
            if (downloadrequest != null)
            {
                break MISSING_BLOCK_LABEL_305;
            }
            downloadrequest = (DownloadRequest)mTaskQueue.mNormalQueue.get();
            if (downloadrequest != null)
            {
                break MISSING_BLOCK_LABEL_185;
            }
            waitForTask(mTaskQueue);
            taskqueue;
            JVM INSTR monitorexit ;
            continue; /* Loop/switch isn't completed */
            Exception exception2;
            exception2;
            taskqueue;
            JVM INSTR monitorexit ;
            throw exception2;
            downloadrequest.bUrgent = false;
_L1:
            taskqueue;
            JVM INSTR monitorexit ;
            if (isDownloading(downloadrequest))
            {
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                String s = getParsedFilePath(downloadrequest.parentdir, downloadrequest.url);
                if ((new File(s)).exists())
                {
                    Log.i("FileDownloader", (new StringBuilder()).append("DownloadThread file exist: ").append(downloadrequest.index).toString());
                    downloadrequest.filepath = s;
                    notifyDownloadSuccess(downloadrequest);
                    continue; /* Loop/switch isn't completed */
                }
            }
            catch (Exception exception3)
            {
                notifyDownloadError(downloadrequest, 4);
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_315;
            downloadrequest.bUrgent = true;
              goto _L1
            downloadthread.startDownload(downloadrequest);
            if (true) goto _L3; else goto _L2
_L2:
        }





        private DownloadDriver()
        {
            this$0 = FileDownloader.this;
            super();
            mStatus = new AtomicInteger();
            mStatus.set(1);
        }

    }

    public static class DownloadRequest
    {

        private boolean bUrgent;
        private boolean cancelflag;
        public ICompressStrategy compressor;
        public String dms_uuid;
        private long downloadSize;
        private long fileSize;
        private String filepath;
        public int index;
        public String item_uuid;
        public IDownloadListener listener;
        public String parentdir;
        public int requestcode;
        private long taskid;
        public int upnp_class;
        public String url;
        public Object userdata;



/*
        static boolean access$2002(DownloadRequest downloadrequest, boolean flag)
        {
            downloadrequest.bUrgent = flag;
            return flag;
        }

*/



/*
        static String access$2802(DownloadRequest downloadrequest, String s)
        {
            downloadrequest.filepath = s;
            return s;
        }

*/



/*
        static long access$402(DownloadRequest downloadrequest, long l)
        {
            downloadrequest.fileSize = l;
            return l;
        }

*/



/*
        static long access$514(DownloadRequest downloadrequest, long l)
        {
            long l1 = l + downloadrequest.downloadSize;
            downloadrequest.downloadSize = l1;
            return l1;
        }

*/



/*
        static boolean access$602(DownloadRequest downloadrequest, boolean flag)
        {
            downloadrequest.cancelflag = flag;
            return flag;
        }

*/



/*
        static long access$902(DownloadRequest downloadrequest, long l)
        {
            downloadrequest.taskid = l;
            return l;
        }

*/

        public DownloadRequest()
        {
            index = -1;
            cancelflag = false;
            bUrgent = false;
        }
    }

    public class DownloadResult
    {

        public int errorcode;
        public String filePath;
        public DownloadRequest request;
        public long taskid;
        final FileDownloader this$0;

        public DownloadResult()
        {
            this$0 = FileDownloader.this;
            super();
        }
    }

    private class DownloadThread extends Thread
    {

        private static final int CONNECTION_TIMEOUT = 15000;
        private static final int READ_TIMEOUT = 10000;
        private int index;
        private DownloadRequest mRequest;
        private final AtomicInteger mStatus = new AtomicInteger();
        final FileDownloader this$0;

        private String getTmpDownloadPath(DownloadRequest downloadrequest, String s)
        {
            String s1 = getParsedFilePath(downloadrequest.parentdir, downloadrequest.url);
            if (s1 != null)
            {
                s1 = (new StringBuilder()).append(s1).append(s).toString();
            }
            return s1;
        }

        private void processDownloadError(DownloadRequest downloadrequest, int i)
        {
            if (i != 5)
            {
                removeWaittingTask(downloadrequest.requestcode, downloadrequest.url);
                notifyDownloadError(downloadrequest, i);
            }
        }

        private void processDownloadSuccess(DownloadRequest downloadrequest, String s)
        {
            removeWaittingTask(downloadrequest.requestcode, downloadrequest.url);
            downloadrequest.filepath = processTmpFile(downloadrequest, s);
            notifyDownloadSuccess(downloadrequest);
        }

        private String processTmpFile(DownloadRequest downloadrequest, String s)
        {
            String s1 = getTmpDownloadPath(downloadrequest, "_c");
            final File oldfile = new File(s);
            File file = new File(s.substring(0, s.length() - "_t".length()));
            if (downloadrequest.compressor != null && downloadrequest.compressor.compressFile(s, s1))
            {
                s = s1;
            }
            File file1 = new File(s);
            String s2;
            if (file1.renameTo(file))
            {
                s2 = file.getAbsolutePath();
            } else
            {
                s2 = null;
            }
            if (mHandler != null)
            {
                mHandler.postDelayed(file1. new Runnable() {

                    final DownloadThread this$1;
                    final File val$oldfile;
                    final File val$tmpfile;

                    public void run()
                    {
                        oldfile.delete();
                        tmpfile.delete();
                    }

            
            {
                this$1 = final_downloadthread;
                oldfile = file;
                tmpfile = File.this;
                super();
            }
                }, 500L);
            }
            return s2;
        }

        private void requestQuit()
        {
            mStatus.set(2);
            wake(this);
        }

        long getDownloadID()
        {
            long l;
            synchronized (FileDownloader.sDownloadID)
            {
                l = FileDownloader.sDownloadID.get();
                FileDownloader.sDownloadID.set(1L + l);
            }
            return l;
            exception;
            atomiclong;
            JVM INSTR monitorexit ;
            throw exception;
        }

        boolean isIdle()
        {
            return mStatus.get() == 0;
        }

        public void run()
        {
            Process.setThreadPriority(10);
_L3:
            if (mStatus.get() == 2) goto _L2; else goto _L1
_L1:
            this;
            JVM INSTR monitorenter ;
            if (mStatus.get() == 1)
            {
                break MISSING_BLOCK_LABEL_47;
            }
            waitForTask(this);
            this;
            JVM INSTR monitorexit ;
              goto _L3
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            this;
            JVM INSTR monitorexit ;
            String s;
            File file;
            s = getTmpDownloadPath(mRequest, "_t");
            file = new File(s);
            if (FileUtils.getSDCardAvailableSize() >= 20480L) goto _L5; else goto _L4
_L4:
            byte byte0 = 6;
_L7:
            FileOutputStream fileoutputstream;
            FileOutputStream fileoutputstream1;
            File file1;
            InputStream inputstream;
            HttpURLConnection httpurlconnection;
            URL url;
            IOException ioexception;
            NumberFormatException numberformatexception;
            IOException ioexception1;
            IllegalStateException illegalstateexception;
            IOException ioexception2;
            IOException ioexception3;
            IOException ioexception4;
            SecurityException securityexception;
            IOException ioexception5;
            FileNotFoundException filenotfoundexception;
            IOException ioexception6;
            MalformedURLException malformedurlexception;
            IOException ioexception7;
            IOException ioexception8;
            boolean flag;
            IOException ioexception9;
            boolean flag1;
            IOException ioexception10;
            boolean flag2;
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
            boolean flag3;
            String s1;
            int i;
            int j;
            IOException ioexception11;
            boolean flag4;
            String s2;
            IOException ioexception12;
            boolean flag5;
            IOException ioexception13;
            boolean flag6;
            MalformedURLException malformedurlexception1;
            IOException ioexception15;
            boolean flag7;
            byte abyte0[];
            IOException ioexception16;
            boolean flag8;
            int k;
            if (byte0 != 0)
            {
                processDownloadError(mRequest, byte0);
            } else
            {
                processDownloadSuccess(mRequest, s);
            }
            file.delete();
            synchronized (mStatus)
            {
                mRequest = null;
                if (mStatus.get() != 2)
                {
                    mStatus.set(0);
                }
            }
            wake(mThreadPool);
            if (isAllDownloadThreadIdle())
            {
                onAllDownloadThreadIdle();
            }
              goto _L3
_L5:
label0:
            {
                if (s != null)
                {
                    break label0;
                }
                byte0 = 4;
            }
            if (true) goto _L7; else goto _L6
_L6:
label1:
            {
                file1 = file.getParentFile();
                mkDownloadDir(file1);
                if (file1.exists())
                {
                    break label1;
                }
                byte0 = 2;
            }
            if (true) goto _L7; else goto _L8
_L8:
            if (file.exists())
            {
                file.delete();
            }
            inputstream = null;
            fileoutputstream = null;
            httpurlconnection = null;
            url = new URL(mRequest.url);
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_275;
            }
            flag = mRequest.cancelflag;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_319;
            }
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_290;
            }
            null.flush();
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_298;
            }
            null.close();
            if (false)
            {
                try
                {
                    null.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception8)
                {
                    ioexception8.printStackTrace();
                }
            }
              goto _L7
            httpurlconnection = (HttpURLConnection)url.openConnection();
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_354;
            }
            flag1 = mRequest.cancelflag;
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_400;
            }
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_369;
            }
            null.flush();
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_377;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception9)
                {
                    ioexception9.printStackTrace();
                }
            }
              goto _L7
            httpurlconnection.setConnectTimeout(15000);
            httpurlconnection.setReadTimeout(10000);
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_441;
            }
            flag2 = mRequest.cancelflag;
            if (!flag2)
            {
                break MISSING_BLOCK_LABEL_487;
            }
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_456;
            }
            null.flush();
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_464;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception10)
                {
                    ioexception10.printStackTrace();
                }
            }
              goto _L7
            presentitem_resource = MyUPnPUtils.getItemResource(mRequest.dms_uuid, mRequest.item_uuid);
            inputstream = null;
            fileoutputstream = null;
            if (presentitem_resource == null) goto _L10; else goto _L9
_L9:
            flag3 = DLNA.instance().getServerManager().isDigaDMS(mRequest.dms_uuid);
            inputstream = null;
            fileoutputstream = null;
            if (!flag3)
            {
                break MISSING_BLOCK_LABEL_595;
            }
            s1 = presentitem_resource.m_strPxnVgaContentProtocolInfo;
            inputstream = null;
            fileoutputstream = null;
            if (s1 == null)
            {
                break MISSING_BLOCK_LABEL_595;
            }
            i = presentitem_resource.m_strPxnVgaContentProtocolInfo.length();
            inputstream = null;
            fileoutputstream = null;
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_595;
            }
            presentitem_resource.m_strProtocolInfo = presentitem_resource.m_strPxnVgaContentProtocolInfo;
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit22_tmb) != 1) goto _L12; else goto _L11
_L11:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Background");
_L10:
            if (mStatus.get() == 2) goto _L14; else goto _L13
_L13:
            flag4 = mRequest.cancelflag;
            if (!flag4) goto _L15; else goto _L14
_L14:
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_658;
            }
            null.flush();
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_666;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception11)
                {
                    ioexception11.printStackTrace();
                }
            }
              goto _L7
_L12:
            j = mRequest.upnp_class;
            inputstream = null;
            fileoutputstream = null;
            if (j != 3) goto _L17; else goto _L16
_L16:
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit23_tmi) != 1) goto _L10; else goto _L18
_L18:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Interactive");
              goto _L10
            malformedurlexception;
_L24:
            byte0 = 4;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_756;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_766;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception7)
                {
                    ioexception7.printStackTrace();
                }
            }
              goto _L7
_L17:
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit24_tms) != 1) goto _L10; else goto _L19
_L19:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Streaming");
              goto _L10
            filenotfoundexception;
_L31:
            byte0 = 2;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_835;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_845;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception6)
                {
                    ioexception6.printStackTrace();
                }
            }
              goto _L7
_L15:
            httpurlconnection.connect();
            s2 = httpurlconnection.getHeaderField("Content-Length");
            if (s2 == null)
            {
                break MISSING_BLOCK_LABEL_901;
            }
            mRequest.fileSize = Long.parseLong(s2);
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_926;
            }
            flag5 = mRequest.cancelflag;
            if (!flag5)
            {
                break MISSING_BLOCK_LABEL_972;
            }
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_941;
            }
            null.flush();
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_949;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception12)
                {
                    ioexception12.printStackTrace();
                }
            }
              goto _L7
            inputstream = httpurlconnection.getInputStream();
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_1004;
            }
            flag6 = mRequest.cancelflag;
            if (!flag6)
            {
                break MISSING_BLOCK_LABEL_1052;
            }
            byte0 = 5;
            if (true)
            {
                break MISSING_BLOCK_LABEL_1019;
            }
            null.flush();
            null.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1029;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception13)
                {
                    ioexception13.printStackTrace();
                }
            }
              goto _L7
            fileoutputstream1 = new FileOutputStream(file);
            if (mStatus.get() == 2)
            {
                break MISSING_BLOCK_LABEL_1087;
            }
            flag7 = mRequest.cancelflag;
            if (!flag7)
            {
                break MISSING_BLOCK_LABEL_1138;
            }
            byte0 = 5;
            if (fileoutputstream1 == null)
            {
                break MISSING_BLOCK_LABEL_1105;
            }
            fileoutputstream1.flush();
            fileoutputstream1.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1115;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception15)
                {
                    ioexception15.printStackTrace();
                }
            }
              goto _L7
            abyte0 = new byte[20480];
_L23:
            if (mStatus.get() == 2) goto _L21; else goto _L20
_L20:
            flag8 = mRequest.cancelflag;
            if (!flag8) goto _L22; else goto _L21
_L21:
            Exception exception1;
            FileNotFoundException filenotfoundexception1;
            SecurityException securityexception1;
            IOException ioexception14;
            IllegalStateException illegalstateexception1;
            NumberFormatException numberformatexception1;
            for (byte0 = 5; fileoutputstream1 == null; byte0 = 5)
            {
                break MISSING_BLOCK_LABEL_1188;
            }

            fileoutputstream1.flush();
            fileoutputstream1.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1198;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception16)
                {
                    ioexception16.printStackTrace();
                }
            }
              goto _L7
_L22:
            k = inputstream.read(abyte0);
label2:
            {
                if (k != -1)
                {
                    break label2;
                }
                byte0 = 0;
            }
              goto _L21
            long l1 = <no variable> + this.url;
            if (mStatus.get() == 2 || mRequest.cancelflag)
            {
                break MISSING_BLOCK_LABEL_1670;
            }
            if (mStatus.get() == 2 || mRequest.cancelflag)
            {
                break MISSING_BLOCK_LABEL_1676;
            }
            fileoutputstream1.write(abyte0, 0, k);
              goto _L23
            malformedurlexception1;
            fileoutputstream = fileoutputstream1;
              goto _L24
            securityexception;
_L30:
            byte0 = 2;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_1343;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1353;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception5)
                {
                    ioexception5.printStackTrace();
                }
            }
              goto _L7
            ioexception3;
_L29:
            byte0 = 2;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_1396;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1406;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception4)
                {
                    ioexception4.printStackTrace();
                }
            }
              goto _L7
            illegalstateexception;
_L28:
            byte0 = 2;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_1449;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1459;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception2)
                {
                    ioexception2.printStackTrace();
                }
            }
              goto _L7
            numberformatexception;
_L27:
            Log.e("FileDownloader", "NumberFormatException");
            byte0 = 2;
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_1512;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1522;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception1)
                {
                    ioexception1.printStackTrace();
                }
            }
              goto _L7
            exception1;
_L26:
            if (fileoutputstream == null)
            {
                break MISSING_BLOCK_LABEL_1562;
            }
            fileoutputstream.flush();
            fileoutputstream.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1572;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception)
                {
                    ioexception.printStackTrace();
                }
            }
            throw exception1;
            exception2;
            atomicinteger;
            JVM INSTR monitorexit ;
            throw exception2;
_L2:
            return;
            exception1;
            fileoutputstream = fileoutputstream1;
            if (true) goto _L26; else goto _L25
_L25:
            numberformatexception1;
            fileoutputstream = fileoutputstream1;
              goto _L27
            illegalstateexception1;
            fileoutputstream = fileoutputstream1;
              goto _L28
            ioexception14;
            fileoutputstream = fileoutputstream1;
              goto _L29
            securityexception1;
            fileoutputstream = fileoutputstream1;
              goto _L30
            filenotfoundexception1;
            fileoutputstream = fileoutputstream1;
              goto _L31
            byte0 = 5;
              goto _L21
        }

        long startDownload(DownloadRequest downloadrequest)
        {
            if (mStatus.get() == 0)
            {
                break MISSING_BLOCK_LABEL_30;
            }
            throw new IllegalStateException("Start download thread which is not in idle state");
            Exception exception2;
            exception2;
            exception2.printStackTrace();
            return 0L;
            mRequest = downloadrequest;
            mRequest.taskid = getDownloadID();
            if (mRequest.listener != null)
            {
                mRequest.listener.onDownloadStarted(mRequest, mRequest.taskid);
            }
            this;
            JVM INSTR monitorenter ;
            long l;
            mStatus.set(1);
            wake(this);
            l = mRequest.taskid;
            this;
            JVM INSTR monitorexit ;
            return l;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            Exception exception1;
            exception1;
            return 0L;
        }



/*
        static int access$2102(DownloadThread downloadthread, int i)
        {
            downloadthread.index = i;
            return i;
        }

*/


        DownloadThread()
        {
            this$0 = FileDownloader.this;
            super();
            index = 0;
            mRequest = null;
            mStatus.set(0);
        }
    }

    public static interface ICompressStrategy
    {

        public abstract boolean compressFile(String s, String s1);
    }

    public static interface IDownloadListener
    {

        public abstract void onDownloadFinished(DownloadResult downloadresult);

        public abstract void onDownloadStarted(DownloadRequest downloadrequest, long l);
    }

    private class TaskQueue
    {

        private final SafeBuffer mNormalQueue;
        private final SafeBuffer mUrgentQueue;
        final FileDownloader this$0;



        private TaskQueue()
        {
            this$0 = FileDownloader.this;
            super();
            mUrgentQueue = new SafeBuffer();
            mNormalQueue = new SafeBuffer();
        }

    }


    private static String CACHE_UID = "media";
    private static final String COMPRESS_SUBFIX = "_c";
    public static final int ERROR_DOWNLOAD_FAILED = 3;
    public static final int ERROR_FILE_ERROR = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_STORAGE_FULL = 6;
    public static final int ERROR_UNKOWN = 1;
    public static final int ERROR_URL_ERROR = 4;
    public static final int ERROR_USER_CANCEL = 5;
    private static final int HASHCODE_LEN = 10;
    private static final int MAX_PATH = 260;
    private static final int STATUS_DOING = 1;
    private static final int STATUS_IDLE = 0;
    private static final int STATUS_QUIT = 2;
    private static final String TAG = "FileDownloader";
    private static final String TEMP_SUBFIX = "_t";
    private static AtomicLong sDownloadID = new AtomicLong();
    private static FileDownloader sInstance;
    private final int MAX_WORKING_NUM;
    private final com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener mCacheClearListener = new com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener() {

        final FileDownloader this$0;

        public void onClearCacheFinished(String s)
        {
            if (s.equals(FileDownloader.CACHE_UID))
            {
                Log.w("FileDownloader", "FD: onClearCacheFinished");
                lockCachePath(false);
                mIsClearingCache = false;
                if (mIsDoPauseWhenClearCache)
                {
                    resume();
                    mIsDoPauseWhenClearCache = false;
                    return;
                }
            }
        }

        public void onPrepareToClearCache(String s)
        {
            if (!s.equals(FileDownloader.CACHE_UID))
            {
                return;
            }
            Log.w("FileDownloader", "FD: onPrepareToClearCache");
            mIsClearingCache = true;
            mIsDoPauseWhenClearCache = false;
            if (isResumed())
            {
                mIsDoPauseWhenClearCache = true;
                pause();
            }
            if (isAllDownloadThreadIdle())
            {
                releaseCachePath(false);
            }
            cancelAllDownloads();
        }

            
            {
                this$0 = FileDownloader.this;
                super();
            }
    };
    private String mCachePath;
    private Application mContext;
    private DownloadDriver mDownloadDriver;
    private Handler mHandler;
    private boolean mIsClearingCache;
    private boolean mIsDoPauseWhenClearCache;
    private final Comparator mTaskCmp = new Comparator() {

        final FileDownloader this$0;

        public int compare(DownloadRequest downloadrequest, DownloadRequest downloadrequest1)
        {
            return downloadrequest.requestcode != downloadrequest1.requestcode || !downloadrequest.url.equals(downloadrequest1.url) ? 1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((DownloadRequest)obj, (DownloadRequest)obj1);
        }

            
            {
                this$0 = FileDownloader.this;
                super();
            }
    };
    private final Comparator mTaskCmpParentDir = new Comparator() {

        final FileDownloader this$0;

        public int compare(DownloadRequest downloadrequest, DownloadRequest downloadrequest1)
        {
            return downloadrequest.requestcode != downloadrequest1.requestcode || !downloadrequest.parentdir.equals(downloadrequest1.parentdir) ? 1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((DownloadRequest)obj, (DownloadRequest)obj1);
        }

            
            {
                this$0 = FileDownloader.this;
                super();
            }
    };
    private final Comparator mTaskCmpRequstid = new Comparator() {

        final FileDownloader this$0;

        public int compare(DownloadRequest downloadrequest, DownloadRequest downloadrequest1)
        {
            return downloadrequest.requestcode != downloadrequest1.requestcode ? 1 : 0;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((DownloadRequest)obj, (DownloadRequest)obj1);
        }

            
            {
                this$0 = FileDownloader.this;
                super();
            }
    };
    private final TaskQueue mTaskQueue = new TaskQueue();
    private final ArrayList mThreadPool = new ArrayList();

    private FileDownloader(Application application, int i)
    {
        mHandler = null;
        mDownloadDriver = null;
        mIsDoPauseWhenClearCache = false;
        mIsClearingCache = false;
        MAX_WORKING_NUM = i;
        mContext = application;
        mHandler = new Handler();
        lockCachePath(true);
        mDownloadDriver = new DownloadDriver();
        mDownloadDriver.start();
    }

    public static String errorCodeToString(int i)
    {
        switch (i)
        {
        default:
            return "Error undefined";

        case 0: // '\0'
            return "ERROR_NONE";

        case 1: // '\001'
            return "ERROR_UNKOWN";

        case 2: // '\002'
            return "ERROR_FILE_ERROR";

        case 3: // '\003'
            return "ERROR_DOWNLOAD_FAILED";

        case 4: // '\004'
            return "ERROR_URL_ERROR";

        case 5: // '\005'
            return "ERROR_USER_CANCEL";
        }
    }

    private DownloadThread getDownloadThread()
    {
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        DownloadThread downloadthread2;
        boolean flag;
        downloadthread2 = (DownloadThread)iterator.next();
        flag = downloadthread2.isIdle();
        if (!flag) goto _L4; else goto _L3
_L3:
        DownloadThread downloadthread = downloadthread2;
_L10:
        if (downloadthread != null) goto _L6; else goto _L5
_L5:
        if (mThreadPool.size() >= MAX_WORKING_NUM) goto _L6; else goto _L7
_L7:
        DownloadThread downloadthread1 = new DownloadThread();
        mThreadPool.add(downloadthread1);
        downloadthread1.index = -1 + mThreadPool.size();
        downloadthread1.start();
_L9:
        arraylist;
        JVM INSTR monitorexit ;
        return downloadthread1;
_L8:
        arraylist;
        JVM INSTR monitorexit ;
        Exception exception;
        throw exception;
        exception;
        downloadthread;
          goto _L8
_L6:
        downloadthread1 = downloadthread;
          goto _L9
_L2:
        downloadthread = null;
          goto _L10
        exception;
          goto _L8
    }

    public static void initSingleton(Application application, int i)
    {
        if (sInstance != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            sInstance = new FileDownloader(application, i);
            return;
        }
    }

    public static FileDownloader instance()
    {
        if (sInstance == null)
        {
            throw new IllegalStateException("Uninitialized.");
        } else
        {
            return sInstance;
        }
    }

    private boolean isAllDownloadThreadIdle()
    {
        boolean flag = true;
        Iterator iterator = mThreadPool.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            if (!((DownloadThread)iterator.next()).isIdle())
            {
                flag = false;
            }
        } while (true);
        return flag;
    }

    private boolean isDownloading(DownloadRequest downloadrequest)
    {
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest1;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_65;
            }
            downloadrequest1 = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest1 == null);
        if (mTaskCmp.compare(downloadrequest, downloadrequest1) != 0) goto _L2; else goto _L1
_L1:
        arraylist;
        JVM INSTR monitorexit ;
        return true;
        arraylist;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private boolean isDownloadingUrgentTask()
    {
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_55;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        if (!downloadrequest.bUrgent) goto _L2; else goto _L1
_L1:
        arraylist;
        JVM INSTR monitorexit ;
        return true;
        arraylist;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void lockCachePath(boolean flag)
    {
        String s;
        Log.i("FileDownloader", (new StringBuilder()).append("FD: lock Cache Path, register listener = ").append(flag).toString());
        s = null;
        CachePathManager cachepathmanager;
        StringBuilder stringbuilder;
        cachepathmanager = CachePathManager.instance();
        s = cachepathmanager.lockCachePath(CACHE_UID, 0);
        stringbuilder = (new StringBuilder()).append("FD: Lock Cache Path from Cache Manager, dir = ");
        String s1;
        if (s == null)
        {
            s1 = "NULL";
        } else
        {
            s1 = s;
        }
        Log.w("FileDownloader", stringbuilder.append(s1).toString());
        if (flag)
        {
            try
            {
                Log.w("FileDownloader", "FD: Register listenern to cache manager");
                cachepathmanager.registerClearCacheListener(CACHE_UID, mCacheClearListener);
            }
            catch (IOException ioexception)
            {
                Log.e("FileDownloader", "FD: Exception occurred when lock cache path");
                ioexception.printStackTrace();
            }
        }
        mCachePath = s;
        return;
    }

    private void notifyDownloadError(DownloadRequest downloadrequest, int i)
    {
        if (downloadrequest.listener == null)
        {
            return;
        } else
        {
            DownloadResult downloadresult = new DownloadResult();
            downloadresult.request = downloadrequest;
            downloadresult.filePath = null;
            downloadresult.taskid = downloadrequest.taskid;
            downloadresult.errorcode = i;
            downloadrequest.listener.onDownloadFinished(downloadresult);
            return;
        }
    }

    private void notifyDownloadSuccess(DownloadRequest downloadrequest)
    {
        if (downloadrequest.listener == null)
        {
            return;
        } else
        {
            DownloadResult downloadresult = new DownloadResult();
            downloadresult.request = downloadrequest;
            downloadresult.filePath = downloadrequest.filepath;
            downloadresult.taskid = downloadrequest.taskid;
            downloadresult.errorcode = 0;
            downloadrequest.listener.onDownloadFinished(downloadresult);
            return;
        }
    }

    private void onAllDownloadThreadIdle()
    {
        if (mIsClearingCache)
        {
            releaseCachePath(false);
        }
    }

    private void releaseCachePath(boolean flag)
    {
        Log.w("FileDownloader", (new StringBuilder()).append("FD: release cache path, unregister = ").append(flag).toString());
        CachePathManager cachepathmanager = CachePathManager.instance();
        if (flag)
        {
            Log.w("FileDownloader", "FD: UnRegister cache path");
            cachepathmanager.unregisterClearCacheListener(CACHE_UID, mCacheClearListener);
        }
        Log.w("FileDownloader", "FD: UnLock Cache Path");
        cachepathmanager.unlockCachePath(CACHE_UID);
        mCachePath = null;
    }

    private int removeWaittingTask(int i, String s)
    {
        DownloadRequest downloadrequest = new DownloadRequest();
        downloadrequest.url = s;
        downloadrequest.requestcode = i;
        Comparator comparator;
        int j;
        if (s == null)
        {
            comparator = mTaskCmpRequstid;
        } else
        {
            comparator = mTaskCmp;
        }
        synchronized (mTaskQueue)
        {
            j = 0 + mTaskQueue.mUrgentQueue.removeSame(downloadrequest, comparator) + mTaskQueue.mNormalQueue.removeSame(downloadrequest, comparator);
        }
        return j;
        exception;
        taskqueue;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void stop()
    {
        cancelAllDownloads();
        mDownloadDriver.requestQuit();
        ArrayList arraylist;
        Exception exception1;
        Iterator iterator;
        DownloadThread downloadthread;
        Exception exception2;
        Exception exception3;
        try
        {
            mDownloadDriver.join(1L);
        }
        catch (Exception exception) { }
        arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        iterator = mThreadPool.iterator();
_L1:
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_77;
        }
        downloadthread = (DownloadThread)iterator.next();
        downloadthread.requestQuit();
        try
        {
            downloadthread.join(1L);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception3) { }
        finally { }
          goto _L1
        arraylist;
        JVM INSTR monitorexit ;
        mContext = null;
        mHandler = null;
        mDownloadDriver = null;
        releaseCachePath(true);
        return;
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public static void uninitSingleton()
    {
        if (sInstance != null);
        sInstance.stop();
        sInstance = null;
    }

    private void waitForTask(Object obj)
    {
        obj;
        JVM INSTR monitorenter ;
        obj.wait();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        InterruptedException interruptedexception;
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L1
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void wake(Object obj)
    {
        obj;
        JVM INSTR monitorenter ;
        obj.notify();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int cancelAllDownloads()
    {
        int i;
        synchronized (mTaskQueue)
        {
            i = 0 + mTaskQueue.mUrgentQueue.getCount() + mTaskQueue.mNormalQueue.getCount();
            mTaskQueue.mUrgentQueue.clear();
            mTaskQueue.mNormalQueue.clear();
        }
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_119;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        downloadrequest.cancelflag = true;
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        taskqueue;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        return i;
        Exception exception1;
        exception1;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public boolean cancelDownload(int i, String s)
    {
        int j = removeWaittingTask(i, s);
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_89;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        if (downloadrequest.requestcode != i || !downloadrequest.url.equals(s))
        {
            continue; /* Loop/switch isn't completed */
        }
        downloadrequest.cancelflag = true;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        arraylist;
        JVM INSTR monitorexit ;
        Exception exception;
        return j > 0;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean cancelDownload(long l)
    {
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_73;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        if (downloadrequest.taskid != l) goto _L2; else goto _L1
_L1:
        downloadrequest.cancelflag = true;
          goto _L2
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        return false;
    }

    public int cancelDownloads(int i)
    {
        int j = removeWaittingTask(i, null);
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_75;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        if (downloadrequest.requestcode != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        downloadrequest.cancelflag = true;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        arraylist;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean cancelNormalTaskDownloads(int i, String s)
    {
        DownloadRequest downloadrequest = new DownloadRequest();
        downloadrequest.requestcode = i;
        downloadrequest.parentdir = s;
        Comparator comparator = mTaskCmpParentDir;
        int j;
        synchronized (mTaskQueue)
        {
            j = 0 + mTaskQueue.mNormalQueue.removeSame(downloadrequest, comparator);
        }
        return j > 0;
        exception;
        taskqueue;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void download(DownloadRequest downloadrequest)
    {
        synchronized (mTaskQueue)
        {
            mTaskQueue.mNormalQueue.append(downloadrequest);
        }
        wake(mTaskQueue);
        return;
        exception;
        taskqueue;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void download(List list, boolean flag)
    {
        ArrayList arraylist = mThreadPool;
        arraylist;
        JVM INSTR monitorenter ;
        Iterator iterator = mThreadPool.iterator();
_L2:
        DownloadRequest downloadrequest;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_93;
            }
            downloadrequest = ((DownloadThread)iterator.next()).mRequest;
        } while (downloadrequest == null);
        if (downloadrequest.fileSize > 0L && (double)downloadrequest.downloadSize > 0.69999999999999996D * (double)downloadrequest.fileSize) goto _L2; else goto _L1
_L1:
        downloadrequest.cancelflag = true;
          goto _L2
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        TaskQueue taskqueue = mTaskQueue;
        taskqueue;
        JVM INSTR monitorenter ;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_143;
        }
        mTaskQueue.mUrgentQueue.clear();
        mTaskQueue.mUrgentQueue.insert(list, 0);
_L3:
        taskqueue;
        JVM INSTR monitorexit ;
        arraylist;
        JVM INSTR monitorexit ;
        wake(mTaskQueue);
        return;
        mTaskQueue.mNormalQueue.append(list);
          goto _L3
        Exception exception1;
        exception1;
        taskqueue;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public String getParsedFilePath(String s, String s1)
        throws IllegalArgumentException
    {
        if (s1 == null || s == null)
        {
            return null;
        }
        if (mCachePath == null)
        {
            try
            {
                throw new IllegalStateException("Cache path is empty");
            }
            catch (Exception exception1)
            {
                exception1.printStackTrace();
            }
            return null;
        }
        Uri uri = Uri.parse(s1);
        String s2 = (new StringBuilder()).append(mCachePath).append("/").append(s).append("/").toString();
        String s3 = uri.toString();
        String s4;
        int i;
        String s5;
        String s6;
        if (s3 != null)
        {
            s4 = (new StringBuilder()).append(s3.hashCode()).append("").toString();
            i = s4.length();
        } else
        {
            s4 = "";
            i = 0;
        }
        s5 = "";
        s6 = uri.getLastPathSegment();
        if (s6 == null)
        {
            try
            {
                throw new IllegalArgumentException((new StringBuilder()).append("url is valid:").append(s1).toString());
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            return null;
        }
        String s7 = Uri.encode(s6);
        if (i + (s2.length() + s7.length()) > 260)
        {
            int j = 10 + (i + (-260 + s7.length())) + s2.length();
            s7 = s7.substring(j);
            s5 = (new StringBuilder()).append("").append(s7.substring(0, j).hashCode()).toString();
        }
        String s8 = (new StringBuilder()).append(s5).append(s4).append(s7).toString();
        return (new File((new StringBuilder()).append(s2).append(s8).toString())).getAbsolutePath();
    }

    public double getProgress(long l)
    {
        return 0.0D;
    }

    public boolean isResumed()
    {
        return mDownloadDriver.isResumed();
    }

    boolean mkDownloadDir(File file)
    {
        this;
        JVM INSTR monitorenter ;
        if (file.exists()) goto _L2; else goto _L1
_L1:
        boolean flag1 = file.mkdirs();
        if (flag1) goto _L2; else goto _L3
_L3:
        boolean flag = false;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = true;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public void pause()
    {
        mDownloadDriver.pauseDriver();
    }

    public void resume()
    {
        mDownloadDriver.resumeDriver();
    }




/*
    static boolean access$1302(FileDownloader filedownloader, boolean flag)
    {
        filedownloader.mIsClearingCache = flag;
        return flag;
    }

*/



/*
    static boolean access$1402(FileDownloader filedownloader, boolean flag)
    {
        filedownloader.mIsDoPauseWhenClearCache = flag;
        return flag;
    }

*/















}
