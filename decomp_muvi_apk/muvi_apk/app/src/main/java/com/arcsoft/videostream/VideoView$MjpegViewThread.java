// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videostream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;
import android.view.SurfaceHolder;
import com.arcsoft.util.debug.Log;
import com.arcsoft.videostream.aee.AEEVideoStreamActivity;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

// Referenced classes of package com.arcsoft.videostream:
//            VideoView, StreamSplit

public class mSurfaceHolder extends Thread
{

    private static final int IMG_FLUFF_FACTOR = 1;
    long counter;
    private int frameCounter;
    private final SurfaceHolder mSurfaceHolder;
    private boolean m_collecting;
    private String m_imageType;
    private final int m_imgidx = 0;
    private boolean m_isDefunct;
    private byte m_rawImage[];
    private final int m_retryCount = 1;
    private final int m_retryDelay = 1000;
    private long m_startTime;
    private final int m_videoDelay = 60000;
    private Bitmap ovl;
    private long start;
    private boolean stop;
    final VideoView this$0;

    private Rect destRect(int i, int j)
    {
        if (VideoView.access$000(VideoView.this) == 1)
        {
            int k1 = VideoView.access$100(VideoView.this) / 2 - i / 2;
            int l1 = VideoView.access$200(VideoView.this) / 2 - j / 2;
            return new Rect(k1, l1, i + k1, j + l1);
        }
        if (VideoView.access$000(VideoView.this) == 4)
        {
            float f = (float)i / (float)j;
            int k = VideoView.access$100(VideoView.this);
            int l = (int)((float)VideoView.access$100(VideoView.this) / f);
            if (l > VideoView.access$200(VideoView.this))
            {
                l = VideoView.access$200(VideoView.this);
                k = (int)(f * (float)VideoView.access$200(VideoView.this));
            }
            int i1 = VideoView.access$100(VideoView.this) / 2 - k / 2;
            int j1 = VideoView.access$200(VideoView.this) / 2 - l / 2;
            return new Rect(i1, j1, k + i1, l + j1);
        }
        if (VideoView.access$000(VideoView.this) == 8)
        {
            return new Rect(0, 0, VideoView.access$100(VideoView.this), VideoView.access$200(VideoView.this));
        } else
        {
            return null;
        }
    }

    private Bitmap makeFpsOverlay(Paint paint, String s)
    {
        Rect rect = new Rect();
        paint.getTextBounds(s, 0, s.length(), rect);
        int i = 2 + rect.width();
        int j = 2 + rect.height();
        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Thread);
        Canvas canvas = new Canvas(bitmap);
        paint.setColor(VideoView.access$300(VideoView.this));
        canvas.drawRect(0.0F, 0.0F, i, j, paint);
        paint.setColor(VideoView.access$400(VideoView.this));
        canvas.drawText(s, 1 + -rect.left, 1.0F + ((float)(j / 2) - (paint.ascent() + paint.descent()) / 2.0F), paint);
        return bitmap;
    }

    private void updateImage(String s, byte abyte0[])
    {
        this;
        JVM INSTR monitorenter ;
        if (abyte0 != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (VideoView.access$1300(VideoView.this) != null)
        {
            VideoView.access$1300(VideoView.this).recycle();
            VideoView.access$1302(VideoView.this, null);
        }
        VideoView.access$1302(VideoView.this, BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length));
        if (VideoView.access$1300(VideoView.this) == null) goto _L1; else goto _L3
_L3:
        PorterDuffXfermode porterduffxfermode;
        m_imageType = s;
        start = System.currentTimeMillis();
        porterduffxfermode = new PorterDuffXfermode(android.graphics.Thread.start);
        Canvas canvas = null;
        Paint paint;
        boolean flag;
        paint = new Paint();
        flag = VideoView.access$1400(VideoView.this);
        if (!flag) goto _L1; else goto _L4
_L4:
        Canvas canvas1 = mSurfaceHolder.lockCanvas();
        canvas = canvas1;
        if (canvas != null)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        if (canvas == null) goto _L1; else goto _L5
_L5:
        mSurfaceHolder.unlockCanvasAndPost(canvas);
          goto _L1
        Exception exception;
        exception;
        throw exception;
        synchronized (mSurfaceHolder)
        {
            if (VideoView.access$1300(VideoView.this) != null)
            {
                break MISSING_BLOCK_LABEL_198;
            }
        }
        if (canvas == null) goto _L1; else goto _L6
_L6:
        mSurfaceHolder.unlockCanvasAndPost(canvas);
          goto _L1
        Rect rect;
        rect = destRect(VideoView.access$1300(VideoView.this).getWidth(), VideoView.access$1300(VideoView.this).getHeight());
        canvas.drawColor(0xff000000);
        canvas.drawBitmap(VideoView.access$1300(VideoView.this), null, rect, paint);
        if (!VideoView.access$1500(VideoView.this)) goto _L8; else goto _L7
_L7:
        paint.setXfermode(porterduffxfermode);
        if (ovl == null) goto _L10; else goto _L9
_L9:
        if ((1 & VideoView.access$1600(VideoView.this)) != 1) goto _L12; else goto _L11
_L11:
        int i = rect.top;
_L14:
        int j;
        if ((8 & VideoView.access$1600(VideoView.this)) != 8)
        {
            break MISSING_BLOCK_LABEL_459;
        }
        j = rect.left;
_L15:
        canvas.drawBitmap(ovl, j, i, null);
_L10:
        paint.setXfermode(null);
        frameCounter = 1 + frameCounter;
        if (System.currentTimeMillis() - start >= 1000L)
        {
            String s1 = (new StringBuilder()).append(String.valueOf(frameCounter)).append("fps").toString();
            frameCounter = 0;
            start = System.currentTimeMillis();
            ovl = makeFpsOverlay(VideoView.access$1700(VideoView.this), s1);
        }
_L8:
        surfaceholder;
        JVM INSTR monitorexit ;
        if (canvas == null) goto _L1; else goto _L13
_L13:
        mSurfaceHolder.unlockCanvasAndPost(canvas);
          goto _L1
_L12:
        i = rect.bottom - ovl.getHeight();
          goto _L14
        j = rect.right - ovl.getWidth();
          goto _L15
        exception2;
        surfaceholder;
        JVM INSTR monitorexit ;
        throw exception2;
        Exception exception1;
        exception1;
        if (canvas == null)
        {
            break MISSING_BLOCK_LABEL_503;
        }
        mSurfaceHolder.unlockCanvasAndPost(canvas);
        throw exception1;
          goto _L1
    }

    public void run()
    {
        if (VideoView.access$500(VideoView.this) <= 13)
        {
            break MISSING_BLOCK_LABEL_101;
        }
        ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).showSurfaceView(0);
_L1:
        if (!VideoView.access$700(VideoView.this))
        {
            break MISSING_BLOCK_LABEL_859;
        }
        if (stop)
        {
            return;
        }
        try
        {
            byte abyte1[] = VideoView.access$800(VideoView.this);
            updateImage(m_imageType, abyte1);
            sleep(20L);
        }
        catch (InterruptedException interruptedexception1)
        {
            Log.e("Thread", "Thread  run()  InterruptedException ");
            ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).switchTo(1, 14);
            return;
        }
          goto _L1
        int i = 0;
        Log.e("FENG", "FENG  run 1 ---------- IN");
        VideoView.access$902(VideoView.this, new URL("http://10.10.1.1:8196"));
        VideoView.access$1002(VideoView.this, VideoView.access$900(VideoView.this).openConnection());
        VideoView.access$1000(VideoView.this).setConnectTimeout(60000);
        VideoView.access$1102(VideoView.this, new DataInputStream(new BufferedInputStream(VideoView.access$1000(VideoView.this).getInputStream())));
        Log.e("FENG", "FENG  run 2  ---------- IN");
        ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).showSurfaceView(0);
_L9:
        boolean flag;
        Log.e("FENG", "FENG  run 3  ---------- IN");
        flag = stop;
        if (flag)
        {
            unhook();
            return;
        }
        i++;
        String s;
        Hashtable hashtable = StreamSplit.readHeaders(VideoView.access$1000(VideoView.this));
        VideoView.access$1202(VideoView.this, new StreamSplit(VideoView.access$1100(VideoView.this)));
        m_collecting = true;
        s = (String)hashtable.get("content-type");
        if (s != null) goto _L3; else goto _L2
_L2:
        String s1 = "No main content type";
_L8:
        if (s1 != null) goto _L5; else goto _L4
_L4:
        if (s1 != null)
        {
            unhook();
            return;
        }
          goto _L6
_L3:
        int j = s.indexOf("text");
        s1 = null;
        if (j == -1) goto _L8; else goto _L7
_L7:
        String s2 = VideoView.access$1100(VideoView.this).readLine();
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_403;
        }
        System.out.println(s2);
          goto _L7
        IOException ioexception;
        ioexception;
        Log.e("FENG", "FENG  run()  IOException ");
        ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).switchTo(1, 14);
        unhook();
        return;
        s1 = "Failed to connect to server (denied?)";
          goto _L8
_L5:
        boolean flag1 = m_isDefunct;
        long l;
        if (flag1)
        {
            unhook();
            return;
        }
        l = 1000;
        sleep(l);
        if (i < 1) goto _L9; else goto _L4
_L6:
        int k;
        k = s.indexOf("boundary=");
        Log.d("FENG", (new StringBuilder()).append("FENG  run() ---------------bidx = ").append(k).toString());
        String s3;
        s3 = "--";
        if (k == -1)
        {
            break MISSING_BLOCK_LABEL_588;
        }
        s3 = s.substring(k + 9);
        s = s.substring(0, k);
        if (s3.startsWith("\"") && s3.endsWith("\""))
        {
            s3 = s3.substring(1, -1 + s3.length());
        }
        if (!s3.startsWith("--"))
        {
            s3 = (new StringBuilder()).append("--").append(s3).toString();
        }
        if (s.startsWith("multipart/x-mixed-replace"))
        {
            VideoView.access$1200(VideoView.this).skipToBoundary(s3);
        }
_L19:
        boolean flag2 = stop;
        if (flag2)
        {
            unhook();
            return;
        }
        if (!m_collecting) goto _L11; else goto _L10
_L10:
        if (s3 == null) goto _L13; else goto _L12
_L12:
        Hashtable hashtable1;
        boolean flag3;
        hashtable1 = VideoView.access$1200(VideoView.this).readHeaders();
        flag3 = VideoView.access$1200(VideoView.this).isAtStreamEnd();
        if (!flag3) goto _L15; else goto _L14
_L14:
        unhook();
        return;
_L15:
        s = (String)hashtable1.get("content-type");
        if (s != null) goto _L13; else goto _L16
_L16:
        throw new Exception("No part content type");
        InterruptedException interruptedexception;
        interruptedexception;
        Log.e("FENG", "FENG  run()  InterruptedException ");
        ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).switchTo(1, 14);
        unhook();
        return;
_L13:
        if (!s.startsWith("multipart/x-mixed-replace")) goto _L18; else goto _L17
_L17:
        s3 = s.substring(9 + s.indexOf("boundary="));
        VideoView.access$1200(VideoView.this).skipToBoundary(s3);
_L11:
        if (VideoView.access$700(VideoView.this)) goto _L19; else goto _L14
_L18:
        byte abyte0[] = VideoView.access$1200(VideoView.this).readToBoundary(s3);
        if (abyte0.length == 0) goto _L14; else goto _L20
_L20:
        updateImage(s, abyte0);
          goto _L11
        Exception exception1;
        exception1;
        Log.e("FENG", "FENG  run()  Exception ");
        ((AEEVideoStreamActivity)VideoView.access$600(VideoView.this)).switchTo(1, 14);
        unhook();
        return;
        Exception exception;
        exception;
        unhook();
        throw exception;
    }

    public void saveToSDCard(byte abyte0[])
    {
        StringBuilder stringbuilder = new StringBuilder();
        long l = 1L + counter;
        counter = l;
        String s = stringbuilder.append(l).append(".jpg").toString();
        File file = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/temp_jpg").toString(), s);
        if (file.exists())
        {
            file.delete();
        }
        FileOutputStream fileoutputstream;
        FileNotFoundException filenotfoundexception;
        IOException ioexception;
        try
        {
            file.createNewFile();
        }
        catch (Exception exception) { }
        fileoutputstream = new FileOutputStream(file);
        try
        {
            fileoutputstream.write(abyte0);
            fileoutputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            try
            {
                ioexception.printStackTrace();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (FileNotFoundException filenotfoundexception)
            {
                filenotfoundexception.printStackTrace();
            }
        }
        return;
    }

    public void setSurfaceSize(int i, int j)
    {
        synchronized (mSurfaceHolder)
        {
            VideoView.access$102(VideoView.this, i);
            VideoView.access$202(VideoView.this, j);
        }
        return;
        exception;
        surfaceholder;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void stopThread()
    {
        stop = true;
    }

    public void unhook()
    {
        m_collecting = false;
        m_isDefunct = true;
        try
        {
            if (VideoView.access$1100(VideoView.this) != null)
            {
                VideoView.access$1100(VideoView.this).close();
            }
            VideoView.access$1102(VideoView.this, null);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public (SurfaceHolder surfaceholder, Context context)
    {
        this$0 = VideoView.this;
        super();
        frameCounter = 0;
        m_isDefunct = false;
        m_collecting = false;
        m_imageType = "image/jpeg";
        m_startTime = 0L;
        stop = false;
        counter = 0L;
        mSurfaceHolder = surfaceholder;
    }
}
