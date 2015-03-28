// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.updownload.db.UpDownloadDBMgr;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload:
//            AbsPoolDriver, IPoolDriver, AbsTaskItem, MyUPnPUtils

public class DownloadPoolDriver extends AbsPoolDriver
    implements IPoolDriver
{
    public class DownloadTask
        implements Runnable
    {

        protected ReadWriteLock mLock;
        protected IPoolDriver.DownloadRequest mRequest;
        final DownloadPoolDriver this$0;

        private int realErrorcode()
        {
            if (mRequest.abortflag != 1);
            return mRequest.cancelflag != 1 ? 817 : 816;
        }

        protected void cancel()
        {
            mRequest.cancelflag = 1;
        }

        protected void controlSpeed()
        {
        }

        public void run()
        {
            com.arcsoft.adk.atv.UPnP.PresentItem_Resource presentitem_resource;
            String s1;
            File file;
            int i;
            mLock.writeLock().lock();
            mWorks.add(this);
            mLock.writeLock().unlock();
            Process.setThreadPriority(10);
            if (mRequest.listener != null)
            {
                mRequest.listener.onDownloadStarted(mRequest, mRequest.tableid);
            }
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("url=").append(mRequest.uri).toString());
            presentitem_resource = MyUPnPUtils.getItemResource(mRequest.dms_uuid, mRequest.item_uuid);
            if (presentitem_resource != null && DLNA.instance().getServerManager().isDigaDMS(mRequest.dms_uuid) && presentitem_resource.m_strPxnVgaContentProtocolInfo != null && presentitem_resource.m_strPxnVgaContentProtocolInfo.length() != 0)
            {
                presentitem_resource.m_strProtocolInfo = presentitem_resource.m_strPxnVgaContentProtocolInfo;
            }
            if (mRequest.videoOrImage && mRequest.upnp_class != -1 && mRequest.upnp_class != 1)
            {
                mRequest.upnp_class = 1;
            }
            String s = AbsPoolDriver.getDownloadPath(mRequest.upnp_class, mRequest.parentdir, mRequest.title, mRequest.uri, presentitem_resource);
            s1 = getDownloadTmpPath(mRequest, "_tmp", presentitem_resource);
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("temp path=").append(s1).toString());
            file = null;
            Log.d("zdf", (new StringBuilder()).append("****** [DownloadPoolDriver] run(), mRequest.title = ").append(mRequest.title).toString());
            Log.d("zdf", (new StringBuilder()).append("****** [DownloadPoolDriver] run(), dwonloadPath = ").append(s).append(", tempPath = ").append(s1).toString());
            if (s1 == null)
            {
                Log.e("DownloadPoolDriver", "path is null!");
                i = 803;
            } else
            {
                file = new File(s1);
                if (mRequest.cancelflag > 0 || mRequest.abortflag > 0)
                {
                    i = realErrorcode();
                } else
                {
label0:
                    {
                        File file1 = file.getParentFile();
                        Log.i("DownloadPoolDriver", (new StringBuilder()).append("temp dir=").append(file1.getAbsolutePath()).toString());
                        mkDownloadDir(file1);
                        if (file1.exists())
                        {
                            break label0;
                        }
                        Log.e("DownloadPoolDriver", "create directory fail!");
                        i = 802;
                    }
                }
            }
_L1:
            InputStream inputstream;
            RandomAccessFile randomaccessfile;
            Exception exception1;
            Exception exception2;
            IOException ioexception3;
            SocketTimeoutException sockettimeoutexception;
            MalformedURLException malformedurlexception;
            NullPointerException nullpointerexception;
            RandomAccessFile randomaccessfile1;
            int k3;
            int i4;
            mLock.writeLock().lock();
            mWorks.remove(this);
            mLock.writeLock().unlock();
            Log.d("zdf", (new StringBuilder()).append("********* [DownloadPoolDriver] run(), errorcode = ").append(i).toString());
            Exception exception;
            SecurityException securityexception;
            IOException ioexception;
            HttpURLConnection httpurlconnection;
            boolean flag;
            long l;
            IOException ioexception1;
            IOException ioexception2;
            boolean flag1;
            String s2;
            IOException ioexception4;
            IOException ioexception5;
            IOException ioexception6;
            IOException ioexception7;
            IOException ioexception8;
            IOException ioexception9;
            String s3;
            URL url;
            int j;
            IOException ioexception10;
            int k;
            IOException ioexception11;
            int i1;
            IOException ioexception12;
            int j1;
            int k1;
            IOException ioexception13;
            int l1;
            IOException ioexception14;
            String s4;
            int i2;
            IOException ioexception15;
            long l2;
            int j2;
            IOException ioexception16;
            byte abyte0[];
            int k2;
            IOException ioexception17;
            int i3;
            IOException ioexception18;
            int j3;
            int l3;
            IOException ioexception19;
            int j4;
            int k4;
            IOException ioexception20;
            IOException ioexception21;
            boolean flag2;
            if (i == 800)
            {
                processDownloadSuccess(mRequest, s1, s);
            } else
            {
                if (i == 804)
                {
                    cancelAllTask(false);
                    i = 817;
                }
                processDownloadError(mRequest, i);
            }
            if (file != null)
            {
                file.delete();
            }
            return;
            file.delete();
            file.createNewFile();
            inputstream = null;
            httpurlconnection = null;
            randomaccessfile = null;
            flag = false;
            l = 0L;
            s3 = mRequest.uri.replaceAll("\\+", "%20").replaceAll("%3A", ":").replaceAll("%2F", "/");
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("encode url=").append(s3).toString());
            url = new URL(s3);
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_897;
            }
            j = realErrorcode();
            i = j;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_800;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_800;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (true)
            {
                break MISSING_BLOCK_LABEL_808;
            }
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_816;
            }
            null.close();
            if (false)
            {
                try
                {
                    null.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception10)
                {
                    ioexception10.printStackTrace();
                }
            }
              goto _L1
            ioexception;
            ioexception.printStackTrace();
            Log.e("DownloadPoolDriver", "create temp file io fail!");
            if (!FileUtils.hasStorage(false))
            {
                i = 807;
            } else
            {
                i = 808;
            }
              goto _L1
            securityexception;
            securityexception.printStackTrace();
            Log.e("DownloadPoolDriver", "delete or create temp file fail!");
            i = 802;
              goto _L1
            exception;
            throw exception;
            httpurlconnection = (HttpURLConnection)url.openConnection();
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_1057;
            }
            k = realErrorcode();
            i = k;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1018;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1018;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (true)
            {
                break MISSING_BLOCK_LABEL_1026;
            }
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_1034;
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
              goto _L1
            httpurlconnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpurlconnection.setReadTimeout(READ_TIMEOUT);
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_1231;
            }
            i1 = realErrorcode();
            i = i1;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1192;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1192;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (true)
            {
                break MISSING_BLOCK_LABEL_1200;
            }
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_1208;
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
              goto _L1
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit22_tmb) != 1) goto _L3; else goto _L2
_L2:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Background");
_L9:
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0) goto _L5; else goto _L4
_L4:
            k1 = realErrorcode();
            i = k1;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1364;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1364;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (true)
            {
                break MISSING_BLOCK_LABEL_1372;
            }
            null.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_1380;
            }
            null.close();
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
              goto _L1
_L3:
            j1 = mRequest.upnp_class;
            inputstream = null;
            randomaccessfile = null;
            if (j1 != 3) goto _L7; else goto _L6
_L6:
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit23_tmi) != 1) goto _L9; else goto _L8
_L8:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Interactive");
              goto _L9
            nullpointerexception;
_L33:
            nullpointerexception.printStackTrace();
            Log.e("DownloadPoolDriver", "encode url fail!");
            i = 803;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1551;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1551;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_1561;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1571;
            }
            inputstream.close();
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
              goto _L1
_L7:
            if (MyUPnPUtils.getFlagByBitFilter(presentitem_resource, com.arcsoft.adk.atv.UPnPFlagsParameterUtils.Flags.bit24_tms) != 1) goto _L9; else goto _L10
_L10:
            httpurlconnection.setRequestProperty("transferMode.dlna.org", "Streaming");
              goto _L9
            malformedurlexception;
_L32:
            malformedurlexception.printStackTrace();
            Log.e("DownloadPoolDriver", "parse url fail!");
            i = 803;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1721;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1721;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_1731;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_1741;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception8)
                {
                    ioexception8.printStackTrace();
                }
            }
              goto _L1
_L5:
            Log.e("FENG", (new StringBuilder()).append("FENG DownloadPoolDriver tempPath1 = ").append(s1).toString());
            randomaccessfile1 = new RandomAccessFile(file, "rw");
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_1958;
            }
            l1 = realErrorcode();
            i = l1;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_1917;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_1917;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_1927;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_1935;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception14)
                {
                    ioexception14.printStackTrace();
                }
            }
              goto _L1
            s4 = httpurlconnection.getHeaderField("Connection");
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_2120;
            }
            i2 = realErrorcode();
            i = i2;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_2079;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2079;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_2089;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_2097;
            }
            null.close();
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
              goto _L1
            flag = false;
            if (s4 == null)
            {
                break MISSING_BLOCK_LABEL_2149;
            }
            flag2 = s4.equalsIgnoreCase("close");
            flag = false;
            if (flag2)
            {
                flag = true;
            }
            l2 = httpurlconnection.getContentLength();
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("request size =").append(mRequest.fileSize).append(", getContentLength =").append(l2).toString());
            if (l2 <= 0L)
            {
                break MISSING_BLOCK_LABEL_2216;
            }
            mRequest.fileSize = l2;
            if (mRequest.fileSize < 0L)
            {
                Log.e("DownloadPoolDriver", (new StringBuilder()).append("file size is bad =").append(mRequest.fileSize).toString());
            }
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_2412;
            }
            j2 = realErrorcode();
            i = j2;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_2371;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2371;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_2381;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_2389;
            }
            null.close();
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
              goto _L1
            if (mRequest.fileSize <= 4L * 0x40000000L)
            {
                break MISSING_BLOCK_LABEL_2589;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("file size excel 4G =").append(mRequest.fileSize).toString());
            i = 923;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_2548;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2548;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_2558;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_2566;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception21)
                {
                    ioexception21.printStackTrace();
                }
            }
              goto _L1
            if (1024L * FileUtils.getSDCardAvailableSize() >= 0xa00000L + mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2747;
            }
            Log.e("DownloadPoolDriver", "not enough sdcard size!");
            i = 805;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_2706;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2706;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_2716;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_2724;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception20)
                {
                    ioexception20.printStackTrace();
                }
            }
              goto _L1
            abyte0 = new byte[0x80000];
            httpurlconnection.connect();
            Log.i("DownloadPoolDriver", "connect");
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
            {
                break MISSING_BLOCK_LABEL_2920;
            }
            k2 = realErrorcode();
            i = k2;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_2879;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_2879;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_2889;
            }
            randomaccessfile1.close();
            if (true)
            {
                break MISSING_BLOCK_LABEL_2897;
            }
            null.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception17)
                {
                    ioexception17.printStackTrace();
                }
            }
              goto _L1
            inputstream = httpurlconnection.getInputStream();
            Log.i("DownloadPoolDriver", "getInputStream");
            if (mRequest.cancelflag > 0)
            {
                break MISSING_BLOCK_LABEL_2963;
            }
            j3 = mRequest.abortflag;
            k3 = 0;
            if (j3 <= 0)
            {
                break MISSING_BLOCK_LABEL_3097;
            }
            i3 = realErrorcode();
            i = i3;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_3054;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_3054;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_3064;
            }
            randomaccessfile1.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_3074;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception18)
                {
                    ioexception18.printStackTrace();
                }
            }
              goto _L1
_L21:
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0) goto _L12; else goto _L11
_L11:
            l3 = realErrorcode();
            i = l3;
_L13:
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_3208;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_3208;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile1 == null)
            {
                break MISSING_BLOCK_LABEL_3218;
            }
            randomaccessfile1.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_3228;
            }
            inputstream.close();
            if (httpurlconnection != null)
            {
                try
                {
                    httpurlconnection.disconnect();
                }
                // Misplaced declaration of an exception variable
                catch (IOException ioexception19)
                {
                    ioexception19.printStackTrace();
                }
            }
              goto _L1
_L12:
            if (1024L * FileUtils.getSDCardAvailableSize() >= (0xa00000L + mRequest.fileSize) - mRequest.downloadSize)
            {
                break MISSING_BLOCK_LABEL_3298;
            }
            Log.e("DownloadPoolDriver", "not enough internal sdcard size!");
            i = 805;
              goto _L13
label1:
            {
                if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
                {
                    break label1;
                }
                i = realErrorcode();
            }
              goto _L13
            if (k3 + mBandWidth >= 0x80000) goto _L15; else goto _L14
_L14:
            i4 = mBandWidth;
_L34:
            j4 = inputstream.read(abyte0, k3, i4);
            if (j4 != -1) goto _L17; else goto _L16
_L16:
            if (k3 <= 0) goto _L19; else goto _L18
_L18:
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("write begin... latest size=").append(k3).append(", total=").append(mRequest.fileSize).toString());
            randomaccessfile1.write(abyte0, 0, k3);
              goto _L19
_L17:
label2:
            {
                if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
                {
                    break label2;
                }
                i = realErrorcode();
            }
              goto _L13
            k3 += j4;
            l += j4;
            mRequest.downloadSize = l;
            if (k3 + mBandWidth < 0x80000)
            {
                break MISSING_BLOCK_LABEL_3568;
            }
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("write begin... size=").append(k3).append(",download sizes=").append(l).append(", total=").append(mRequest.fileSize).toString());
            randomaccessfile1.write(abyte0, 0, k3);
            k3 = 0;
label3:
            {
                if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0)
                {
                    break label3;
                }
                i = realErrorcode();
            }
              goto _L13
            if (mRequest.listener != null)
            {
                mRequest.listener.onDownloadProgress(mRequest, mRequest.tableid);
            }
            if (mRequest.cancelflag <= 0 && mRequest.abortflag <= 0) goto _L21; else goto _L20
_L20:
            k4 = realErrorcode();
            i = k4;
              goto _L13
            sockettimeoutexception;
_L31:
            Log.d("zdf", (new StringBuilder()).append("********* [DownloadPoolDriver] run(), e1 = ").append(sockettimeoutexception).toString());
            if (!flag) goto _L23; else goto _L22
_L22:
            if (mRequest.fileSize <= 0L || mRequest.fileSize <= l) goto _L25; else goto _L24
_L24:
            Log.e("DownloadPoolDriver", "socket time out and not all data been download so give up it");
            l = 0L;
            i = 801;
_L26:
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_3820;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_3820;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_3830;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_3840;
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
              goto _L1
_L25:
            Log.e("DownloadPoolDriver", "socket time out but all data been download");
            i = 800;
              goto _L26
_L23:
            Log.e("DownloadPoolDriver", "socket time out and with out no close connect header");
            l = 0L;
            i = 801;
              goto _L26
_L30:
            Log.d("zdf", (new StringBuilder()).append("********* [DownloadPoolDriver] run(), e2 = ").append(ioexception3).toString());
            ioexception3.printStackTrace();
            Log.e("DownloadPoolDriver", "open/read/write fail!");
            flag1 = FileUtils.hasStorage(false);
            if (flag1)
            {
                break MISSING_BLOCK_LABEL_4081;
            }
            i = 807;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_4038;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_4038;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_4048;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_4058;
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
              goto _L1
            s2 = NetworkUtil.getLocalIpViaWiFi(mContext);
            if (s2 != null)
            {
                break MISSING_BLOCK_LABEL_4227;
            }
            i = 804;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_4184;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_4184;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_4194;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_4204;
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
              goto _L1
            i = 808;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_4313;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_4313;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_4323;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_4333;
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
              goto _L1
            exception2;
_L29:
            Log.d("zdf", (new StringBuilder()).append("********* [DownloadPoolDriver] run(), e3 = ").append(exception2).toString());
            exception2.printStackTrace();
            Log.e("DownloadPoolDriver", "unknown exception!");
            i = 801;
            if (i != 800)
            {
                break MISSING_BLOCK_LABEL_4485;
            }
            if (mRequest.fileSize <= 0L || l == mRequest.fileSize)
            {
                break MISSING_BLOCK_LABEL_4485;
            }
            Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            i = 909;
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_4495;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_4505;
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
              goto _L1
            exception1;
_L28:
            if (801 != 800)
            {
                break MISSING_BLOCK_LABEL_4607;
            }
            if (mRequest.fileSize > 0L && l != mRequest.fileSize)
            {
                Log.e("DownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l).append(", totalsize=").append(mRequest.fileSize).toString());
            }
            if (randomaccessfile == null)
            {
                break MISSING_BLOCK_LABEL_4617;
            }
            randomaccessfile.close();
            if (inputstream == null)
            {
                break MISSING_BLOCK_LABEL_4627;
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
            throw exception1;
            exception1;
            randomaccessfile = randomaccessfile1;
            if (true) goto _L28; else goto _L27
_L27:
            exception2;
            randomaccessfile = randomaccessfile1;
              goto _L29
            ioexception3;
            randomaccessfile = randomaccessfile1;
              goto _L30
            sockettimeoutexception;
            randomaccessfile = randomaccessfile1;
              goto _L31
            malformedurlexception;
            randomaccessfile = randomaccessfile1;
              goto _L32
            nullpointerexception;
            randomaccessfile = randomaccessfile1;
              goto _L33
_L19:
            i = 800;
              goto _L13
_L15:
            i4 = 0x80000 - k3;
              goto _L34
            ioexception3;
            inputstream = null;
            randomaccessfile = null;
              goto _L30
        }

        protected DownloadTask(IPoolDriver.DownloadRequest downloadrequest, ReadWriteLock readwritelock)
        {
            this$0 = DownloadPoolDriver.this;
            super();
            mRequest = null;
            mLock = null;
            mRequest = downloadrequest;
            mLock = readwritelock;
        }
    }


    public static final int MAX_THREAD_POOL_SIZE = 1;
    private final String TAG = "DownloadPoolDriver";
    protected ThreadPoolExecutor mThreadPool;
    protected ArrayList mWorks;

    public DownloadPoolDriver()
    {
        mThreadPool = null;
        mWorks = new ArrayList();
    }

    public void abortAllTask()
    {
        Log.i("DownloadPoolDriver", "abortAllTask");
        mLock.writeLock().lock();
        for (Iterator iterator = mWorks.iterator(); iterator.hasNext();)
        {
            ((DownloadTask)(Runnable)iterator.next()).mRequest.abortflag = 2;
        }

        for (Iterator iterator1 = mThreadPool.getQueue().iterator(); iterator1.hasNext();)
        {
            ((DownloadTask)(Runnable)iterator1.next()).mRequest.abortflag = 2;
        }

        mLock.writeLock().unlock();
    }

    public int abortTask(String s)
    {
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("abortTask =").append(s).toString());
        int i = 0;
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Runnable runnable1 = (Runnable)iterator.next();
            if (((DownloadTask)runnable1).mRequest.dms_uuid.equalsIgnoreCase(s))
            {
                ((DownloadTask)runnable1).mRequest.abortflag = 1;
                i++;
            }
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DownloadTask)runnable).mRequest.dms_uuid.equalsIgnoreCase(s))
            {
                ((DownloadTask)runnable).mRequest.abortflag = 1;
                i++;
            }
        } while (true);
        mLock.writeLock().unlock();
        return i;
    }

    public void cancelAllTask(boolean flag)
    {
        Log.i("DownloadPoolDriver", "cancelAllTask");
        mLock.writeLock().lock();
        for (Iterator iterator = mWorks.iterator(); iterator.hasNext();)
        {
            Runnable runnable1 = (Runnable)iterator.next();
            if (flag)
            {
                ((DownloadTask)runnable1).mRequest.listener = null;
            }
            ((DownloadTask)runnable1).mRequest.cancelflag = 2;
        }

        for (Iterator iterator1 = mThreadPool.getQueue().iterator(); iterator1.hasNext();)
        {
            Runnable runnable = (Runnable)iterator1.next();
            if (flag)
            {
                ((DownloadTask)runnable).mRequest.listener = null;
            }
            ((DownloadTask)runnable).mRequest.cancelflag = 2;
        }

        mLock.writeLock().unlock();
    }

    public int cancelTask(String s)
    {
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("cancelTask=").append(s).toString());
        int i = -1;
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Runnable runnable1 = (Runnable)iterator.next();
            if (((DownloadTask)runnable1).mRequest.dms_uuid.equalsIgnoreCase(s))
            {
                ((DownloadTask)runnable1).mRequest.cancelflag = 1;
                i++;
            }
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DownloadTask)runnable).mRequest.dms_uuid.equalsIgnoreCase(s))
            {
                ((DownloadTask)runnable).mRequest.cancelflag = 1;
                i++;
            }
        } while (true);
        mLock.writeLock().unlock();
        return i;
    }

    public boolean cancelTask(long l)
    {
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("cancelTask=").append(l).toString());
        mLock.writeLock().lock();
        Iterator iterator = mWorks.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            Runnable runnable1 = (Runnable)iterator.next();
            if (((DownloadTask)runnable1).mRequest.tableid != l)
            {
                continue;
            }
            ((DownloadTask)runnable1).mRequest.cancelflag = 1;
            flag1 = true;
            break;
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (((DownloadTask)runnable).mRequest.tableid != l)
            {
                continue;
            }
            ((DownloadTask)runnable).mRequest.cancelflag = 1;
            break;
        } while (true);
        mLock.writeLock().unlock();
        return flag1;
    }

    public boolean cancelTask(Uri uri, boolean flag)
    {
        String s = uri.toString();
        if (flag)
        {
            mLock.writeLock().lock();
        }
        Iterator iterator = mWorks.iterator();
        boolean flag2;
        do
        {
            boolean flag1 = iterator.hasNext();
            flag2 = false;
            if (!flag1)
            {
                break;
            }
            Runnable runnable1 = (Runnable)iterator.next();
            if (!((DownloadTask)runnable1).mRequest.uri.equals(s))
            {
                continue;
            }
            ((DownloadTask)runnable1).mRequest.cancelflag = 1;
            flag2 = true;
            break;
        } while (true);
        Iterator iterator1 = mThreadPool.getQueue().iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Runnable runnable = (Runnable)iterator1.next();
            if (!((DownloadTask)runnable).mRequest.uri.equals(s))
            {
                continue;
            }
            ((DownloadTask)runnable).mRequest.cancelflag = 1;
            break;
        } while (true);
        if (flag)
        {
            mLock.writeLock().unlock();
        }
        return flag2;
    }

    public void controlSpeed(int i, int j)
    {
        if (i > 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        mPercent = i;
        mLock.writeLock().lock();
        for (Iterator iterator = mWorks.iterator(); iterator.hasNext(); ((DownloadTask)(Runnable)iterator.next()).controlSpeed()) { }
        break; /* Loop/switch isn't completed */
_L2:
        if (i > 100)
        {
            i = 100;
        }
        if (true) goto _L4; else goto _L3
_L3:
        mLock.writeLock().unlock();
        return;
    }

    public boolean download(IPoolDriver.DownloadRequest downloadrequest)
    {
        boolean flag;
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("download id = ").append(downloadrequest.tableid).toString());
        flag = true;
        mLock.writeLock().lock();
        DownloadTask downloadtask = new DownloadTask(downloadrequest, mLock);
        mThreadPool.execute(downloadtask);
        mLock.writeLock().unlock();
_L1:
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("download result=").append(flag).toString());
        return flag;
        RejectedExecutionException rejectedexecutionexception;
        rejectedexecutionexception;
        Log.e("DownloadPoolDriver", "RejectedExecutionException at discretion of RejectedExecutionHandler, if the task cannot be accepted for execution");
        mLock.writeLock().unlock();
        flag = false;
          goto _L1
        NullPointerException nullpointerexception;
        nullpointerexception;
        Log.e("DownloadPoolDriver", "NullPointerException - request is null");
        mLock.writeLock().unlock();
        flag = false;
          goto _L1
        Exception exception;
        exception;
        mLock.writeLock().unlock();
        throw exception;
    }

    public boolean getTask(long l, AbsTaskItem abstaskitem)
    {
        boolean flag1;
label0:
        {
            Log.i("DownloadPoolDriver", (new StringBuilder()).append("getTask=").append(l).toString());
            if (abstaskitem == null)
            {
                return false;
            }
            mLock.readLock().lock();
            Iterator iterator = mWorks.iterator();
            do
            {
                boolean flag = iterator.hasNext();
                flag1 = false;
                if (!flag)
                {
                    break;
                }
                Runnable runnable1 = (Runnable)iterator.next();
                if (((DownloadTask)runnable1).mRequest.tableid != l)
                {
                    continue;
                }
                IPoolDriver.DownloadRequest downloadrequest1 = ((DownloadTask)runnable1).mRequest;
                abstaskitem.currentbytes = downloadrequest1.downloadSize;
                abstaskitem.totalbytes = downloadrequest1.fileSize;
                flag1 = true;
                break;
            } while (true);
            if (flag1)
            {
                break label0;
            }
            Iterator iterator1 = mThreadPool.getQueue().iterator();
            Runnable runnable;
            do
            {
                if (!iterator1.hasNext())
                {
                    break label0;
                }
                runnable = (Runnable)iterator1.next();
            } while (((DownloadTask)runnable).mRequest.tableid != l);
            IPoolDriver.DownloadRequest downloadrequest = ((DownloadTask)runnable).mRequest;
            abstaskitem.currentbytes = downloadrequest.downloadSize;
            abstaskitem.totalbytes = downloadrequest.fileSize;
            flag1 = true;
        }
        mLock.readLock().unlock();
        return flag1;
    }

    public void init(Context context, UpDownloadDBMgr updownloaddbmgr)
    {
        Log.i("DownloadPoolDriver", "init");
        if (mThreadPool != null)
        {
            throw new IllegalStateException("Already initialized.");
        } else
        {
            mContext = context;
            mUpDownloadDBMgr = updownloaddbmgr;
            mThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
            mLock = new ReentrantReadWriteLock(false);
            return;
        }
    }

    public boolean isTaskCanceling()
    {
        mLock.readLock().lock();
        Iterator iterator = mWorks.iterator();
        boolean flag1;
        do
        {
            boolean flag = iterator.hasNext();
            flag1 = false;
            if (!flag)
            {
                break;
            }
            Runnable runnable = (Runnable)iterator.next();
            Log.v("zdf", (new StringBuilder()).append("[DownloadPoolDriver] isTaskCanceling, cancelflag = ").append(((DownloadTask)runnable).mRequest.cancelflag).append(", abortflag = ").append(((DownloadTask)runnable).mRequest.abortflag).toString());
            if (((DownloadTask)runnable).mRequest.cancelflag <= 0 && ((DownloadTask)runnable).mRequest.abortflag <= 0)
            {
                continue;
            }
            flag1 = true;
            break;
        } while (true);
        mLock.readLock().unlock();
        return flag1;
    }

    public boolean isThreadPoolActive()
    {
        mLock.readLock().lock();
        boolean flag;
        if (mWorks.size() > 0 || mThreadPool.getActiveCount() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mLock.readLock().unlock();
        Log.i("DownloadPoolDriver", (new StringBuilder()).append("isThreadPoolActive = ").append(flag).toString());
        return flag;
    }

    public void recycle()
    {
    }

    public void uninit()
    {
        Log.i("DownloadPoolDriver", "uninit");
        if (mThreadPool == null)
        {
            throw new IllegalStateException("Has Uninitialized.");
        } else
        {
            cancelAllTask(true);
            mThreadPool.shutdown();
            mThreadPool = null;
            mLock = null;
            mUpDownloadDBMgr = null;
            return;
        }
    }
}
