// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream.aee;

import com.arcsoft.util.debug.Log;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

// Referenced classes of package com.arcsoft.videostream.aee:
//            AEESocketClient

private class <init> extends Thread
{

    private boolean stop;
    final AEESocketClient this$0;

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = 0;
        j = 0;
_L8:
        if (!stop)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        this;
        JVM INSTR monitorexit ;
        j;
        return;
        HttpURLConnection httpurlconnection = null;
        URL url;
        Log.e("SocketClient", "RequestConfigThread *******************");
        url = new URL("http://192.168.42.1/pref/config");
        InputStream inputstream;
        httpurlconnection = (HttpURLConnection)url.openConnection();
        httpurlconnection.setConnectTimeout(3000);
        httpurlconnection.setReadTimeout(3000);
        httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
        httpurlconnection.setRequestMethod("GET");
        httpurlconnection.connect();
        inputstream = httpurlconnection.getInputStream();
        if (inputstream == null) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder;
        InputStreamReader inputstreamreader;
        stringbuilder = new StringBuilder();
        inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
_L5:
        int l = inputstreamreader.read();
        if (l == -1) goto _L4; else goto _L3
_L3:
        stringbuilder.append((char)l);
          goto _L5
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        j;
_L13:
        if (httpurlconnection == null)
        {
            break MISSING_BLOCK_LABEL_160;
        }
        httpurlconnection.disconnect();
        if (AEESocketClient.access$200(AEESocketClient.this) != null)
        {
            AEESocketClient.access$200(AEESocketClient.this).onRequestConfigFinished(null);
        }
        this;
        JVM INSTR monitorexit ;
        return;
_L10:
        this;
        JVM INSTR monitorexit ;
        Exception exception;
        throw exception;
_L4:
        String s;
        inputstreamreader.close();
        inputstream.close();
        httpurlconnection.disconnect();
        s = stringbuilder.toString();
        Log.e("SocketClient", (new StringBuilder()).append("config.toString() = ").append(s).toString());
        if (s == null) goto _L2; else goto _L6
_L6:
        if (s.trim().equals("")) goto _L2; else goto _L7
_L7:
        AEESocketClient.access$100(AEESocketClient.this, s);
        if (AEESocketClient.access$200(AEESocketClient.this) != null)
        {
            AEESocketClient.access$200(AEESocketClient.this).onRequestConfigFinished(s);
        }
        this;
        JVM INSTR monitorexit ;
        j;
        return;
_L2:
        int k;
        k = j + 1;
        if (j != 5)
        {
            break MISSING_BLOCK_LABEL_335;
        }
        httpurlconnection.disconnect();
        if (AEESocketClient.access$200(AEESocketClient.this) != null)
        {
            AEESocketClient.access$200(AEESocketClient.this).onRequestConfigFinished(null);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Thread.sleep(500L);
_L9:
        j = k;
          goto _L8
        SocketTimeoutException sockettimeoutexception;
        sockettimeoutexception;
        j;
_L12:
        sockettimeoutexception.printStackTrace();
        if (httpurlconnection == null)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        httpurlconnection.disconnect();
        if (AEESocketClient.access$200(AEESocketClient.this) != null)
        {
            AEESocketClient.access$200(AEESocketClient.this).onRequestConfigFinished(null);
        }
        this;
        JVM INSTR monitorexit ;
        return;
_L11:
        Exception exception1;
        exception1.printStackTrace();
        if (i != 1)
        {
            break MISSING_BLOCK_LABEL_438;
        }
        if (httpurlconnection == null)
        {
            break MISSING_BLOCK_LABEL_412;
        }
        httpurlconnection.disconnect();
        if (AEESocketClient.access$200(AEESocketClient.this) != null)
        {
            AEESocketClient.access$200(AEESocketClient.this).onRequestConfigFinished(null);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        i++;
          goto _L9
        exception;
        j;
          goto _L10
        exception1;
        k = j;
          goto _L11
        exception1;
          goto _L11
        sockettimeoutexception;
        j;
          goto _L12
        sockettimeoutexception;
          goto _L12
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        j;
        httpurlconnection = null;
          goto _L13
        FileNotFoundException filenotfoundexception2;
        filenotfoundexception2;
          goto _L13
        exception;
          goto _L10
        exception1;
        k = j;
        httpurlconnection = null;
          goto _L11
    }

    public void stopRequest()
    {
        stop = true;
    }

    private ner()
    {
        this$0 = AEESocketClient.this;
        super();
        stop = false;
    }

    stop(stop stop1)
    {
        this();
    }
}
