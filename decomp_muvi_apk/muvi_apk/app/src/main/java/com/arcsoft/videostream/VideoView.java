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
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.arcsoft.util.SystemUtils;
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
//            StreamSplit

public class VideoView extends SurfaceView
    implements android.view.SurfaceHolder.Callback
{
    public class MjpegViewThread extends Thread
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
            if (displayMode == 1)
            {
                int k1 = dispWidth / 2 - i / 2;
                int l1 = dispHeight / 2 - j / 2;
                return new Rect(k1, l1, i + k1, j + l1);
            }
            if (displayMode == 4)
            {
                float f = (float)i / (float)j;
                int k = dispWidth;
                int l = (int)((float)dispWidth / f);
                if (l > dispHeight)
                {
                    l = dispHeight;
                    k = (int)(f * (float)dispHeight);
                }
                int i1 = dispWidth / 2 - k / 2;
                int j1 = dispHeight / 2 - l / 2;
                return new Rect(i1, j1, k + i1, l + j1);
            }
            if (displayMode == 8)
            {
                return new Rect(0, 0, dispWidth, dispHeight);
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
            Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            paint.setColor(overlayBackgroundColor);
            canvas.drawRect(0.0F, 0.0F, i, j, paint);
            paint.setColor(overlayTextColor);
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
            if (bm != null)
            {
                bm.recycle();
                bm = null;
            }
            bm = BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length);
            if (bm == null) goto _L1; else goto _L3
_L3:
            PorterDuffXfermode porterduffxfermode;
            m_imageType = s;
            start = System.currentTimeMillis();
            porterduffxfermode = new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OVER);
            Canvas canvas = null;
            Paint paint;
            boolean flag;
            paint = new Paint();
            flag = surfaceDone;
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
                if (bm != null)
                {
                    break MISSING_BLOCK_LABEL_198;
                }
            }
            if (canvas == null) goto _L1; else goto _L6
_L6:
            mSurfaceHolder.unlockCanvasAndPost(canvas);
              goto _L1
            Rect rect;
            rect = destRect(bm.getWidth(), bm.getHeight());
            canvas.drawColor(0xff000000);
            canvas.drawBitmap(bm, null, rect, paint);
            if (!showFps) goto _L8; else goto _L7
_L7:
            paint.setXfermode(porterduffxfermode);
            if (ovl == null) goto _L10; else goto _L9
_L9:
            if ((1 & ovlPos) != 1) goto _L12; else goto _L11
_L11:
            int i = rect.top;
_L14:
            int j;
            if ((8 & ovlPos) != 8)
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
                ovl = makeFpsOverlay(overlayPaint, s1);
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
            if (lSDKVersion <= 13)
            {
                break MISSING_BLOCK_LABEL_101;
            }
            ((AEEVideoStreamActivity)mContext).showSurfaceView(0);
_L1:
            if (!mRun)
            {
                break MISSING_BLOCK_LABEL_859;
            }
            if (stop)
            {
                return;
            }
            try
            {
                byte abyte1[] = Native_Arc_get_raw_data();
                updateImage(m_imageType, abyte1);
                sleep(20L);
            }
            catch (InterruptedException interruptedexception1)
            {
                Log.e("Thread", "Thread  run()  InterruptedException ");
                ((AEEVideoStreamActivity)mContext).switchTo(1, 14);
                return;
            }
              goto _L1
            int i = 0;
            Log.e("FENG", "FENG  run 1 ---------- IN");
            m_stream = new URL("http://10.10.1.1:8196");
            mConn = m_stream.openConnection();
            mConn.setConnectTimeout(60000);
            mIn = new DataInputStream(new BufferedInputStream(mConn.getInputStream()));
            Log.e("FENG", "FENG  run 2  ---------- IN");
            ((AEEVideoStreamActivity)mContext).showSurfaceView(0);
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
            Hashtable hashtable = StreamSplit.readHeaders(mConn);
            mSsplit = new StreamSplit(mIn);
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
            String s2 = mIn.readLine();
            if (s2 == null)
            {
                break MISSING_BLOCK_LABEL_403;
            }
            System.out.println(s2);
              goto _L7
            IOException ioexception;
            ioexception;
            Log.e("FENG", "FENG  run()  IOException ");
            ((AEEVideoStreamActivity)mContext).switchTo(1, 14);
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
                mSsplit.skipToBoundary(s3);
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
            hashtable1 = mSsplit.readHeaders();
            flag3 = mSsplit.isAtStreamEnd();
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
            ((AEEVideoStreamActivity)mContext).switchTo(1, 14);
            unhook();
            return;
_L13:
            if (!s.startsWith("multipart/x-mixed-replace")) goto _L18; else goto _L17
_L17:
            s3 = s.substring(9 + s.indexOf("boundary="));
            mSsplit.skipToBoundary(s3);
_L11:
            if (mRun) goto _L19; else goto _L14
_L18:
            byte abyte0[] = mSsplit.readToBoundary(s3);
            if (abyte0.length == 0) goto _L14; else goto _L20
_L20:
            updateImage(s, abyte0);
              goto _L11
            Exception exception1;
            exception1;
            Log.e("FENG", "FENG  run()  Exception ");
            ((AEEVideoStreamActivity)mContext).switchTo(1, 14);
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
                dispWidth = i;
                dispHeight = j;
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
                if (mIn != null)
                {
                    mIn.close();
                }
                mIn = null;
                return;
            }
            catch (Exception exception)
            {
                return;
            }
        }

        public MjpegViewThread(SurfaceHolder surfaceholder, Context context)
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

    public class RecvMjpgTask extends AsyncTask
    {

        final VideoView this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient Void doInBackground(String as[])
        {
            int i = Native_Arc_client_receive();
            Log.e("RecvMjpgTask", (new StringBuilder()).append("RecvMjpgTask  doInBackground() nRes: ").append(i).toString());
            if (i < 0)
            {
                mRun = false;
                ((AEEVideoStreamActivity)mContext).switchTo(1, 14);
            }
            return null;
        }

        public RecvMjpgTask()
        {
            this$0 = VideoView.this;
            super();
        }
    }


    public static final int POSITION_LOWER_LEFT = 12;
    public static final int POSITION_LOWER_RIGHT = 6;
    public static final int POSITION_UPPER_LEFT = 9;
    public static final int POSITION_UPPER_RIGHT = 3;
    public static final int SIZE_BEST_FIT = 4;
    public static final int SIZE_FULLSCREEN = 8;
    public static final int SIZE_STANDARD = 1;
    private final String TAG;
    private Bitmap bm;
    private int dispHeight;
    private int dispWidth;
    private int displayMode;
    private final int lSDKVersion;
    private URLConnection mConn;
    private Context mContext;
    private DataInputStream mIn;
    private boolean mRun;
    private StreamSplit mSsplit;
    private android.os.PowerManager.WakeLock mWakeLock;
    private URL m_stream;
    private int overlayBackgroundColor;
    private Paint overlayPaint;
    private int overlayTextColor;
    private int ovlPos;
    private PowerManager pManager;
    private boolean showFps;
    private boolean surfaceDone;
    private MjpegViewThread thread;

    public VideoView(Context context)
    {
        super(context);
        TAG = "ArcVideoView";
        mContext = null;
        lSDKVersion = SystemUtils.getSDKVersion();
        mIn = null;
        mConn = null;
        m_stream = null;
        mSsplit = null;
        showFps = false;
        mRun = false;
        surfaceDone = false;
        pManager = null;
        mWakeLock = null;
        mContext = context;
        init(context);
    }

    public VideoView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        TAG = "ArcVideoView";
        mContext = null;
        lSDKVersion = SystemUtils.getSDKVersion();
        mIn = null;
        mConn = null;
        m_stream = null;
        mSsplit = null;
        showFps = false;
        mRun = false;
        surfaceDone = false;
        pManager = null;
        mWakeLock = null;
        mContext = context;
        init(context);
    }

    private native int Native_Arc_client_close();

    private native int Native_Arc_client_connect();

    private native int Native_Arc_client_receive();

    private native int Native_Arc_get_one_frame();

    private native byte[] Native_Arc_get_raw_data();

    private void init(Context context)
    {
        getHolder().addCallback(this);
        setFocusable(true);
        overlayPaint = new Paint();
        overlayPaint.setTextAlign(android.graphics.Paint.Align.LEFT);
        overlayPaint.setTextSize(12F);
        overlayPaint.setTypeface(Typeface.DEFAULT);
        overlayTextColor = -1;
        overlayBackgroundColor = 0xff000000;
        ovlPos = 6;
        displayMode = 1;
        dispWidth = getWidth();
        dispHeight = getHeight();
    }

    public void destroy()
    {
        Log.d("11111111", "videoview destroy");
        if (mIn != null)
        {
            try
            {
                mIn.close();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            mIn = null;
        }
        if (bm != null)
        {
            bm.recycle();
            bm = null;
        }
        if (mConn != null)
        {
            mConn = null;
        }
        if (m_stream != null)
        {
            m_stream = null;
        }
        if (mSsplit != null)
        {
            mSsplit = null;
        }
    }

    public void setDisplayMode(int i)
    {
        displayMode = i;
    }

    public void setOverlayBackgroundColor(int i)
    {
        overlayBackgroundColor = i;
    }

    public void setOverlayPaint(Paint paint)
    {
        overlayPaint = paint;
    }

    public void setOverlayPosition(int i)
    {
        ovlPos = i;
    }

    public void setOverlayTextColor(int i)
    {
        overlayTextColor = i;
    }

    public void setSource(DataInputStream datainputstream)
    {
        startPlayback();
    }

    public void showFps(boolean flag)
    {
        showFps = flag;
    }

    public void startPlayback()
    {
        Log.d("FENG", "FENG  startPlayback() --------------------------------------- ");
        mRun = true;
        if (lSDKVersion > 13)
        {
            (new RecvMjpgTask()).execute(new String[] {
                ""
            });
        }
        if (thread != null)
        {
            thread.stopThread();
            thread = null;
        }
        thread = new MjpegViewThread(getHolder(), mContext);
        thread.start();
    }

    public void stopPlayback()
    {
        Log.d("FENG", "FENG  stopPlayback() --------- ");
        mRun = false;
        if (thread != null)
        {
            for (boolean flag = true; flag; flag = false)
            {
                if (thread != null)
                {
                    thread.stopThread();
                    thread = null;
                }
            }

        }
        if (lSDKVersion > 13)
        {
            Native_Arc_client_close();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        Log.d("111111111", (new StringBuilder()).append("surfaceChanged() --------- thread = ").append(thread).toString());
        if (thread != null)
        {
            thread.setSurfaceSize(j, k);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        Log.d("FENG", "FENG  surfaceCreated() ------- ");
        surfaceDone = true;
        pManager = (PowerManager)mContext.getSystemService("power");
        mWakeLock = pManager.newWakeLock(0x2000000a, "ArcVideoView");
        mWakeLock.acquire();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        Log.d("FENG", "FENG  surfaceDestroyed() ------- ");
        surfaceDone = false;
        if (mWakeLock != null)
        {
            mWakeLock.acquire();
        }
    }





/*
    static URLConnection access$1002(VideoView videoview, URLConnection urlconnection)
    {
        videoview.mConn = urlconnection;
        return urlconnection;
    }

*/


/*
    static int access$102(VideoView videoview, int i)
    {
        videoview.dispWidth = i;
        return i;
    }

*/



/*
    static DataInputStream access$1102(VideoView videoview, DataInputStream datainputstream)
    {
        videoview.mIn = datainputstream;
        return datainputstream;
    }

*/



/*
    static StreamSplit access$1202(VideoView videoview, StreamSplit streamsplit)
    {
        videoview.mSsplit = streamsplit;
        return streamsplit;
    }

*/



/*
    static Bitmap access$1302(VideoView videoview, Bitmap bitmap)
    {
        videoview.bm = bitmap;
        return bitmap;
    }

*/








/*
    static int access$202(VideoView videoview, int i)
    {
        videoview.dispHeight = i;
        return i;
    }

*/







/*
    static boolean access$702(VideoView videoview, boolean flag)
    {
        videoview.mRun = flag;
        return flag;
    }

*/




/*
    static URL access$902(VideoView videoview, URL url)
    {
        videoview.m_stream = url;
        return url;
    }

*/
}
