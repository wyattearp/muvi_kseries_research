// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.network;

import android.os.Handler;
import android.os.Process;
import com.arcsoft.adk.atv.DLNA;
import com.arcsoft.adk.atv.ServerManager;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.FileUtils;
import com.arcsoft.util.debug.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package com.arcsoft.util.network:
//            FileDownloader

private class mStatus extends Thread
{

    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int READ_TIMEOUT = 10000;
    private int index;
    private  mRequest;
    private final AtomicInteger mStatus = new AtomicInteger();
    final FileDownloader this$0;

    private String getTmpDownloadPath( , String s)
    {
        String s1 = getParsedFilePath(.parentdir, .url);
        if (s1 != null)
        {
            s1 = (new StringBuilder()).append(s1).append(s).toString();
        }
        return s1;
    }

    private void processDownloadError( , int i)
    {
        if (i != 5)
        {
            FileDownloader.access$3300(FileDownloader.this, .requestcode, .url);
            FileDownloader.access$3000(FileDownloader.this, , i);
        }
    }

    private void processDownloadSuccess( , String s)
    {
        FileDownloader.access$3300(FileDownloader.this, .requestcode, .url);
        .access._mth2802(, processTmpFile(, s));
        FileDownloader.access$2900(FileDownloader.this, );
    }

    private String processTmpFile( , String s)
    {
        String s1 = getTmpDownloadPath(, "_c");
        final File oldfile = new File(s);
        File file = new File(s.substring(0, s.length() - "_t".length()));
        if (.compressor != null && .compressor.compressFile(s, s1))
        {
            s = s1;
        }
        final File tmpfile = new File(s);
        String s2;
        if (tmpfile.renameTo(file))
        {
            s2 = file.getAbsolutePath();
        } else
        {
            s2 = null;
        }
        if (FileDownloader.access$3400(FileDownloader.this) != null)
        {
            FileDownloader.access$3400(FileDownloader.this).postDelayed(new Runnable() {

                final FileDownloader.DownloadThread this$1;
                final File val$oldfile;
                final File val$tmpfile;

                public void run()
                {
                    oldfile.delete();
                    tmpfile.delete();
                }

            
            {
                this$1 = FileDownloader.DownloadThread.this;
                oldfile = file;
                tmpfile = file1;
                super();
            }
            }, 500L);
        }
        return s2;
    }

    private void requestQuit()
    {
        mStatus.set(2);
        FileDownloader.access$2200(FileDownloader.this, this);
    }

    long getDownloadID()
    {
        long l;
        synchronized (FileDownloader.access$3100())
        {
            l = FileDownloader.access$3100().get();
            FileDownloader.access$3100().set(1L + l);
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
        FileDownloader.access$2500(FileDownloader.this, this);
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
        com.arcsoft.adk.atv.quest quest;
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
        FileDownloader.access$2200(FileDownloader.this, FileDownloader.access$2300(FileDownloader.this));
        if (FileDownloader.access$1500(FileDownloader.this))
        {
            FileDownloader.access$3200(FileDownloader.this);
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
        flag = .access._mth600(mRequest);
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
        flag1 = .access._mth600(mRequest);
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
        flag2 = .access._mth600(mRequest);
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
        quest = MyUPnPUtils.getItemResource(mRequest.dms_uuid, mRequest.item_uuid);
        inputstream = null;
        fileoutputstream = null;
        if (quest == null) goto _L10; else goto _L9
_L9:
        flag3 = DLNA.instance().getServerManager().isDigaDMS(mRequest.dms_uuid);
        inputstream = null;
        fileoutputstream = null;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        s1 = quest.rPxnVgaContentProtocolInfo;
        inputstream = null;
        fileoutputstream = null;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        i = quest.rPxnVgaContentProtocolInfo.length();
        inputstream = null;
        fileoutputstream = null;
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        quest.rProtocolInfo = quest.rPxnVgaContentProtocolInfo;
        if (MyUPnPUtils.getFlagByBitFilter(quest, com.arcsoft.adk.atv.bit22_tmb) != 1) goto _L12; else goto _L11
_L11:
        httpurlconnection.setRequestProperty("transferMode.dlna.org", "Background");
_L10:
        if (mStatus.get() == 2) goto _L14; else goto _L13
_L13:
        flag4 = .access._mth600(mRequest);
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
        if (MyUPnPUtils.getFlagByBitFilter(quest, com.arcsoft.adk.atv.bit23_tmi) != 1) goto _L10; else goto _L18
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
        if (MyUPnPUtils.getFlagByBitFilter(quest, com.arcsoft.adk.atv.bit24_tms) != 1) goto _L10; else goto _L19
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
        .access._mth402(mRequest, Long.parseLong(s2));
        if (mStatus.get() == 2)
        {
            break MISSING_BLOCK_LABEL_926;
        }
        flag5 = .access._mth600(mRequest);
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
        flag6 = .access._mth600(mRequest);
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
        flag7 = .access._mth600(mRequest);
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
        flag8 = .access._mth600(mRequest);
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
        .access._mth514(mRequest, k);
        if (mStatus.get() == 2 || .access._mth600(mRequest))
        {
            break MISSING_BLOCK_LABEL_1670;
        }
        if (mStatus.get() == 2 || .access._mth600(mRequest))
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

    long startDownload( )
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
        mRequest = ;
        .access._mth902(mRequest, getDownloadID());
        if (mRequest.listener != null)
        {
            mRequest.listener.onDownloadStarted(mRequest, .access._mth900(mRequest));
        }
        this;
        JVM INSTR monitorenter ;
        long l;
        mStatus.set(1);
        FileDownloader.access$2200(FileDownloader.this, this);
        l = .access._mth900(mRequest);
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
    static int access$2102( , int i)
    {
        .index = i;
        return i;
    }

*/


    _cls1.val.tmpfile()
    {
        this$0 = FileDownloader.this;
        super();
        index = 0;
        mRequest = null;
        mStatus.set(0);
    }
}
