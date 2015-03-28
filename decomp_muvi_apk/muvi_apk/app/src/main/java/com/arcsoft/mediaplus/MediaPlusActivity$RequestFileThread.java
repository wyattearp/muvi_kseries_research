// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.os.Handler;
import com.arcsoft.mediaplus.datasource.db.SalixHttpUpdater;
import com.arcsoft.util.debug.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusActivity

private class <init> extends Thread
{

    private boolean stop;
    final MediaPlusActivity this$0;

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = 0;
        j = 0;
_L9:
        if (!stop)
        {
            break MISSING_BLOCK_LABEL_18;
        }
        this;
        JVM INSTR monitorexit ;
        j;
        return;
        HttpURLConnection httpurlconnection;
        InputStream inputstream;
        Log.e("MediaPlusActivity", "FENG RequestFileThread *******************");
        httpurlconnection = (HttpURLConnection)(new URL("http://10.10.1.1/MOUNT.chipsipcmd")).openConnection();
        httpurlconnection.setConnectTimeout(5000);
        httpurlconnection.setReadTimeout(5000);
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
        MalformedURLException malformedurlexception;
        malformedurlexception;
        int k = j;
_L14:
        malformedurlexception.printStackTrace();
        if (i != 12) goto _L7; else goto _L6
_L6:
        MediaPlusActivity.access$4400(MediaPlusActivity.this, 1);
        this;
        JVM INSTR monitorexit ;
        return;
_L11:
        this;
        JVM INSTR monitorexit ;
        Exception exception;
        throw exception;
_L4:
        inputstreamreader.close();
        inputstream.close();
        httpurlconnection.disconnect();
        Log.e("MediaPlusActivity", (new StringBuilder()).append("FENG sb.toString() = ").append(stringbuilder.toString()).toString());
        SalixHttpUpdater.setIsSDA(true);
        if (!stringbuilder.toString().contains("/var/tmp/usb/sdb1"))
        {
            break MISSING_BLOCK_LABEL_277;
        }
        SalixHttpUpdater.setIsSDA(false);
        if (mHandler != null)
        {
            mHandler.removeMessages(775);
            mHandler.sendEmptyMessage(775);
        }
        this;
        JVM INSTR monitorexit ;
        j;
        return;
        if (!stringbuilder.toString().contains("/var/tmp/usb/sda1")) goto _L2; else goto _L8
_L8:
        if (mHandler != null)
        {
            mHandler.removeMessages(775);
            mHandler.sendEmptyMessage(775);
        }
        this;
        JVM INSTR monitorexit ;
        j;
        return;
_L2:
        k = j + 1;
        if (j != 20)
        {
            break MISSING_BLOCK_LABEL_354;
        }
        MediaPlusActivity.access$4400(MediaPlusActivity.this, 1);
        this;
        JVM INSTR monitorexit ;
        return;
        Thread.sleep(500L);
_L10:
        j = k;
          goto _L9
_L7:
        i++;
        Log.e("MediaPlusActivity", "FENG RequestFileThread **** MalformedURLException");
          goto _L10
_L13:
        IOException ioexception;
        ioexception.printStackTrace();
        if (i != 12)
        {
            break MISSING_BLOCK_LABEL_431;
        }
        if (mHandler != null)
        {
            mHandler.removeMessages(775);
            mHandler.sendEmptyMessage(775);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        i++;
        Log.e("MediaPlusActivity", "FENG RequestFileThread **** IOException");
          goto _L10
_L12:
        InterruptedException interruptedexception;
        interruptedexception.printStackTrace();
        if (i != 12)
        {
            break MISSING_BLOCK_LABEL_467;
        }
        MediaPlusActivity.access$4400(MediaPlusActivity.this, 1);
        this;
        JVM INSTR monitorexit ;
        return;
        i++;
        Log.e("MediaPlusActivity", "FENG RequestFileThread **** InterruptedException");
          goto _L10
        exception;
        j;
          goto _L11
        interruptedexception;
          goto _L12
        ioexception;
          goto _L13
        malformedurlexception;
          goto _L14
        exception;
          goto _L11
        ioexception;
        k = j;
          goto _L13
        interruptedexception;
        k = j;
          goto _L12
    }

    public void stopRequest()
    {
        stop = true;
    }

    private ()
    {
        this$0 = MediaPlusActivity.this;
        super();
        stop = false;
    }

    stop(stop stop1)
    {
        this();
    }
}
