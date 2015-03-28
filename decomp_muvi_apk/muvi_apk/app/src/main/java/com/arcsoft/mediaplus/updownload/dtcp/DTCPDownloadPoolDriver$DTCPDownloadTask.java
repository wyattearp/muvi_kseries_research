// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.dtcp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.adk.atv.dtcp.DtcpSink;
import com.arcsoft.adk.atv.dtcp.DtcpSinkMove;
import com.arcsoft.mediaplus.setting.Settings;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.network.NetworkUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// Referenced classes of package com.arcsoft.mediaplus.updownload.dtcp:
//            DTCPDownloadPoolDriver

protected class mLock
    implements Runnable
{

    private int dest;
    private boolean mCancel;
    protected ReadWriteLock mLock;
    protected boolean mNeedControlSpeed;
    protected com.arcsoft.mediaplus.updownload.Task.closeStream mRequest;
    private boolean mbCopyFlag;
    final DTCPDownloadPoolDriver this$0;

    private void closeStream(boolean flag)
    {
        if (mbCopyFlag)
        {
            Log.i("DTCPDownloadPoolDriver", "close stream : copy");
            st.access._mth1900((st)mRequest).CloseStream(flag);
            Log.i("DTCPDownloadPoolDriver", "after close stream : copy");
            return;
        } else
        {
            Log.i("DTCPDownloadPoolDriver", "close stream : move");
            equest.access._mth1100((equest)mRequest).CloseStream();
            Log.i("DTCPDownloadPoolDriver", "after close stream : move");
            return;
        }
    }

    private int openStream(int i)
    {
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("open stream : drm_type = ").append(dest).toString());
        if (mbCopyFlag)
        {
            return st.access._mth1900((st)mRequest).OpenStream(dest, i, mRequest.mRequest);
        } else
        {
            return equest.access._mth1100((equest)mRequest).OpenStream(dest, i, mRequest.mRequest);
        }
    }

    private int realErrorcode()
    {
        if (mRequest.mRequest != 1);
        return mRequest.mRequest != 1 ? 817 : 816;
    }

    private boolean setConnProp(HttpURLConnection httpurlconnection)
    {
        if (!mbCopyFlag)
        {
            byte abyte0[] = {
                0
            };
            if (equest.access._mth1100((equest)mRequest).GetKxmLable(abyte0) != 0)
            {
                Log.i("DTCP-Move", "GetKxmLable failed");
                return false;
            }
            int i = (new Byte(abyte0[0])).intValue();
            if (i < 0)
            {
                i += 256;
            }
            Log.i("DTCP-Move", (new StringBuilder()).append("BLKMove.dtcp.com: ").append(Integer.toHexString(i)).toString());
            httpurlconnection.setRequestProperty("BLKMove.dtcp.com", Integer.toHexString(i));
        }
        return true;
    }

    private int writeStream(byte abyte0[], int i, int ai[])
    {
        if (mbCopyFlag)
        {
            return st.access._mth1900((st)mRequest).WriteStream(abyte0, i, ai);
        } else
        {
            return equest.access._mth1100((equest)mRequest).WriteStream(abyte0, i, ai);
        }
    }

    public void abortTask(int i)
    {
        mRequest.mRequest = i;
    }

    public void cancelTask(int i)
    {
        mRequest.mRequest = i;
    }

    public void controlSpeed()
    {
        mNeedControlSpeed = true;
    }

    public com.arcsoft.mediaplus.updownload.Task getDownloadRequest()
    {
        return mRequest;
    }

    public void run()
    {
        int i;
        com.arcsoft.adk.atv. ;
        int l;
        InputStream inputstream;
        HttpURLConnection httpurlconnection;
        boolean flag;
        long l1;
        URL url;
        mLock.writeLock().lock();
        mWorks.add(this);
        mLock.writeLock().unlock();
        Process.setThreadPriority(10);
        String s;
        String as[];
        int j;
        int k;
        String s2;
        int i1;
        int k4;
        if (mRequest instanceof st)
        {
            mbCopyFlag = true;
        } else
        {
            mbCopyFlag = false;
        }
        s = Settings.instance().getDefaultDownloadDestination();
        as = DTCPDownloadPoolDriver.access$2000(DTCPDownloadPoolDriver.this).getResources().getStringArray(0x7f060007);
        i = 0;
_L37:
        j = as.length;
        if (i >= j) goto _L2; else goto _L1
_L1:
        if (!as[i].equalsIgnoreCase(s)) goto _L4; else goto _L3
_L3:
        if (i == 0)
        {
            dest = 0;
        } else
        {
            dest = 1;
        }
_L2:
         = MyUPnPUtils.getItemResource(mRequest.mRequest, mRequest.mRequest);
        if ( != null && DLNA.instance().getServerManager().isDigaDMS(mRequest.mRequest) && .tentProtocolInfo != null && .tentProtocolInfo.length() != 0)
        {
            .nfo = .tentProtocolInfo;
        }
        k = 0;
        if ( != null)
        {
            k4 = .tentProtocolInfo != 0L;
            k = 0;
            if (k4 > 0)
            {
                k = (int).tentProtocolInfo;
            }
        }
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("mFlag = ").append(mbCopyFlag).append("; dest = ").append(s).append("; duration = ").append(k).toString());
        if (mRequest.mRequest != null)
        {
            mRequest.mRequest.Started(mRequest, mRequest.mRequest);
        }
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("url=").append(mRequest.mRequest).toString());
        l = 801;
        if (mCancel) goto _L6; else goto _L5
_L5:
        inputstream = null;
        httpurlconnection = null;
        flag = false;
        l1 = 0L;
        s2 = mRequest.mRequest.replaceAll("\\+", "%20").replaceAll("%3A", ":").replaceAll("%2F", "/");
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("encode url=").append(s2).toString());
        url = new URL(s2);
        i1 = openStream(k * 1000);
        if (i1 == 0) goto _L8; else goto _L7
_L7:
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("openstream = ").append(i1).toString());
        l = 922;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_566;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_566;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_586;
        }
        null.close();
        if (false)
        {
            try
            {
                null.disconnect();
            }
            catch (IOException ioexception9)
            {
                ioexception9.printStackTrace();
            }
        }
_L6:
        mLock.writeLock().lock();
        mWorks.remove(this);
        mLock.writeLock().unlock();
        Exception exception;
        IOException ioexception;
        Exception exception1;
        IOException ioexception1;
        IOException ioexception2;
        boolean flag1;
        String s1;
        IOException ioexception3;
        IOException ioexception4;
        IOException ioexception5;
        SocketTimeoutException sockettimeoutexception;
        IOException ioexception6;
        MalformedURLException malformedurlexception;
        IOException ioexception7;
        NullPointerException nullpointerexception;
        IOException ioexception8;
        String s3;
        boolean flag2;
        long l2;
        int j1;
        byte abyte0[];
        int ai[];
        int k1;
        boolean flag3;
        IOException ioexception10;
        InterruptedException interruptedexception;
        int i2;
        int j2;
        int k2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        IOException ioexception11;
        if (l == 800)
        {
            DTCPDownloadPoolDriver.access$2200(DTCPDownloadPoolDriver.this, mRequest, null, null);
            return;
        } else
        {
            DTCPDownloadPoolDriver.access$2300(DTCPDownloadPoolDriver.this, mRequest, l);
            return;
        }
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L8:
        if (mRequest.mRequest > 0 || mRequest.mRequest > 0)
        {
            l = realErrorcode();
            mCancel = true;
            Log.i("DTCPDownloadPoolDriver", "cancel task on run");
        }
        Log.i("DTCPDownloadPoolDriver", "before open conn");
        httpurlconnection = (HttpURLConnection)url.openConnection();
        Log.i("DTCPDownloadPoolDriver", "after open conn");
        if (setConnProp(httpurlconnection))
        {
            break MISSING_BLOCK_LABEL_899;
        }
        closeStream(true);
        l = 801;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_856;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_856;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (true)
        {
            break MISSING_BLOCK_LABEL_876;
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
          goto _L6
        httpurlconnection.setConnectTimeout(CONNECTION_TIMEOUT);
        httpurlconnection.setReadTimeout(READ_TIMEOUT);
        if (mRequest.mRequest > 0 || mRequest.mRequest > 0)
        {
            mCancel = true;
            l = realErrorcode();
            Log.i("DTCPDownloadPoolDriver", "cancel task on run");
        }
        s3 = httpurlconnection.getHeaderField("Connection");
        flag = false;
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_1002;
        }
        flag2 = s3.equalsIgnoreCase("close");
        flag = false;
        if (flag2)
        {
            flag = true;
        }
        l2 = httpurlconnection.getContentLength();
        if (l2 <= 0L) goto _L10; else goto _L9
_L9:
        mRequest.mRequest = l2;
_L20:
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("request size =").append(mRequest.mRequest).append(", getContentLength =").append(l2).append("resource size = ").append(.ntCleartextSize).toString());
        if (mRequest.mRequest > 0 || mRequest.mRequest > 0)
        {
            l = realErrorcode();
            mCancel = true;
            Log.i("DTCPDownloadPoolDriver", "cancel task on run");
        }
        if (mRequest.mRequest <= 4L * 0x40000000L) goto _L12; else goto _L11
_L11:
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("file size excel 4G =").append(mRequest.mRequest).toString());
        l = 923;
        mCancel = true;
_L12:
        if (1024L * FileUtils.getSDCardAvailableSize() >= 0xa00000L + mRequest.mRequest) goto _L14; else goto _L13
_L13:
        Log.e("DTCPDownloadPoolDriver", "not enough sdcard size!");
        l = 805;
        mCancel = true;
_L14:
        j1 = mBandWidth;
        abyte0 = new byte[j1];
        ai = (new int[] {
            0
        });
        ai[0] = j1;
        k1 = 0;
        Log.i("DTCPDownloadPoolDriver", "before conn");
        httpurlconnection.connect();
        Log.i("DTCPDownloadPoolDriver", "connect");
        if (mRequest.mRequest > 0 || mRequest.mRequest > 0)
        {
            l = realErrorcode();
            mCancel = true;
            Log.i("DTCPDownloadPoolDriver", "cancel task on run");
        }
        inputstream = httpurlconnection.getInputStream();
        Log.i("DTCPDownloadPoolDriver", "getInputStream");
        if (mRequest.mRequest > 0)
        {
            break MISSING_BLOCK_LABEL_1363;
        }
        j4 = mRequest.mRequest;
        k1 = 0;
        if (j4 <= 0)
        {
            break MISSING_BLOCK_LABEL_1383;
        }
        l = realErrorcode();
        mCancel = true;
        Log.i("DTCPDownloadPoolDriver", "cancel task on run");
_L34:
        flag3 = mCancel;
        if (flag3) goto _L16; else goto _L15
_L15:
        if (mPercent > 0)
        {
            Thread.sleep(1000 * mPercent);
        }
_L21:
        if (mRequest.mRequest <= 0 && mRequest.mRequest <= 0) goto _L18; else goto _L17
_L17:
        l = realErrorcode();
        mCancel = true;
        Log.i("DTCPDownloadPoolDriver", "cancel task on run write");
_L16:
        Log.i("DTCPDownloadPoolDriver", "after write loop");
        if (mCancel)
        {
            Log.i("DTCPDownloadPoolDriver", "cancel");
            closeStream(true);
        }
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_1570;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_1570;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_1592;
        }
        inputstream.close();
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
          goto _L6
_L10:
        if (mRequest.mRequest > 0L) goto _L20; else goto _L19
_L19:
        mRequest.mRequest = .ntCleartextSize;
        if (mRequest.mRequest <= 0L)
        {
            mRequest.mRequest = .ze;
        }
          goto _L20
        nullpointerexception;
        nullpointerexception.printStackTrace();
        Log.e("DTCPDownloadPoolDriver", "encode url fail!");
        l = 803;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_1768;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_1768;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_1790;
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
          goto _L6
        interruptedexception;
        interruptedexception.printStackTrace();
          goto _L21
        malformedurlexception;
        malformedurlexception.printStackTrace();
        Log.e("DTCPDownloadPoolDriver", "parse url fail!");
        l = 803;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_1925;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_1925;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_1947;
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
          goto _L6
_L18:
        if (k1 >= j1) goto _L16; else goto _L22
_L22:
        i2 = j1 - k1;
        j2 = inputstream.read(abyte0, k1, i2);
        if (j2 != -1)
        {
            break MISSING_BLOCK_LABEL_2020;
        }
        Log.i("DTCPDownloadPoolDriver", "download done!");
        l = 800;
          goto _L16
        if (mRequest.mRequest <= 0 && mRequest.mRequest <= 0) goto _L24; else goto _L23
_L23:
        l = realErrorcode();
        mCancel = true;
        Log.i("DTCPDownloadPoolDriver", "cancel task on run write");
          goto _L16
        sockettimeoutexception;
        if (!flag) goto _L26; else goto _L25
_L25:
        if (mRequest.mRequest <= 0L || mRequest.mRequest <= l1) goto _L28; else goto _L27
_L27:
        Log.e("DTCPDownloadPoolDriver", "socket time out and not all data been download so give up it");
        l1 = 0L;
        l = 801;
_L35:
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_2193;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_2193;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_2215;
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
          goto _L6
_L24:
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("cnt = ").append(j2).append("; offset = ").append(k1).toString());
        Log.i("DTCPDownloadPoolDriver", "before write stream");
        k2 = writeStream(abyte0, j2 + k1, ai);
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("write stream result = ").append(k2).toString());
label0:
        {
            if (k2 == 0)
            {
                break label0;
            }
            l = 820;
        }
          goto _L16
        Log.i("DTCPDownloadPoolDriver", "after write stream");
        i3 = ai[0];
        l1 += i3;
        mRequest. = l1;
        Log.i("DTCPDownloadPoolDriver", (new StringBuilder()).append("downloadSize = ").append(l1).toString());
        j3 = i3;
        k3 = 0;
_L30:
        if ((j2 + k1) - j3 <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        l3 = k3 + 1;
        i4 = j3 + 1;
        abyte0[k3] = abyte0[j3];
        j3 = i4;
        k3 = l3;
        if (true) goto _L30; else goto _L29
_L29:
        k1 = k3;
        if (mRequest.mRequest <= 0 && mRequest.mRequest <= 0) goto _L32; else goto _L31
_L31:
        l = realErrorcode();
        mCancel = true;
        Log.i("DTCPDownloadPoolDriver", "cancel task on run write");
          goto _L16
        ioexception2;
        ioexception2.printStackTrace();
        Log.e("DTCPDownloadPoolDriver", "open/read/write fail!");
        flag1 = FileUtils.hasStorage(false);
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_2875;
        }
        l = 807;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_2610;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_2610;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_2632;
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
          goto _L6
_L32:
        if (mRequest.mRequest == null) goto _L34; else goto _L33
_L33:
        mRequest.mRequest.Progress(mRequest, mRequest.mRequest);
          goto _L34
        exception1;
        exception1.printStackTrace();
        Log.e("DTCPDownloadPoolDriver", "unknown exception!");
        l = 801;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_2793;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_2793;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_2815;
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
          goto _L6
_L28:
        Log.e("DTCPDownloadPoolDriver", "socket time out but all data been download");
        l = 800;
          goto _L35
_L26:
        Log.e("DTCPDownloadPoolDriver", "socket time out and with out no close connect header");
        l1 = 0L;
        l = 801;
          goto _L35
        s1 = NetworkUtil.getLocalIpViaWiFi(DTCPDownloadPoolDriver.access$2100(DTCPDownloadPoolDriver.this));
        if (s1 != null)
        {
            break MISSING_BLOCK_LABEL_3023;
        }
        l = 804;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_2978;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_2978;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_3000;
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
          goto _L6
        l = 808;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_3109;
        }
        if (mRequest.mRequest <= 0L || l1 == mRequest.mRequest)
        {
            break MISSING_BLOCK_LABEL_3109;
        }
        Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        l = 909;
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_3131;
        }
        inputstream.close();
        if (httpurlconnection != null)
        {
            try
            {
                httpurlconnection.disconnect();
            }
            // Misplaced declaration of an exception variable
            catch (IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
        }
          goto _L6
        exception;
        if (l != 800)
        {
            break MISSING_BLOCK_LABEL_3232;
        }
        if (mRequest.mRequest > 0L && l1 != mRequest.mRequest)
        {
            Log.e("DTCPDownloadPoolDriver", (new StringBuilder()).append("download size error =").append(l1).append(", totalsize=").append(mRequest.mRequest).toString());
        }
        if (mbCopyFlag)
        {
            closeStream(false);
        }
        if (inputstream == null)
        {
            break MISSING_BLOCK_LABEL_3254;
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
        throw exception;
        if (true) goto _L37; else goto _L36
_L36:
    }

    protected equest(com.arcsoft.mediaplus.updownload.Task task, ReadWriteLock readwritelock)
    {
        this$0 = DTCPDownloadPoolDriver.this;
        super();
        mRequest = null;
        mLock = null;
        dest = 1;
        mNeedControlSpeed = false;
        mCancel = false;
        mRequest = task;
        mLock = readwritelock;
    }
}
