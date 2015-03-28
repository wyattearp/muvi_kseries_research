// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.arcsoft.mediaplus.ListViewManager;
import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.MediaPlusLaucher;
import com.arcsoft.mediaplus.ViewManager;
import com.arcsoft.mediaplus.datasource.MediaItem;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.VideoItem;
import com.arcsoft.mediaplus.datasource.db.RemoteDBMgr;
import com.arcsoft.mediaplus.oem.OEMConfig;
import com.arcsoft.mediaplus.picture.controller.CacheMgr;
import com.arcsoft.mediaplus.picture.opengl.BitmapTexture;
import com.arcsoft.mediaplus.picture.opengl.EtcTexture;
import com.arcsoft.mediaplus.picture.opengl.FadeInTexture;
import com.arcsoft.mediaplus.picture.opengl.GLCanvasImpl;
import com.arcsoft.mediaplus.picture.opengl.GLPaint;
import com.arcsoft.mediaplus.picture.opengl.StringTexture;
import com.arcsoft.mediaplus.picture.opengl.Texture;
import com.arcsoft.mediaplus.picture.opengl.UploadedTexture;
import com.arcsoft.mediaplus.updownload.AbsPoolDriver;
import com.arcsoft.mediaplus.updownload.MyUPnPUtils;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.debug.PerformanceTester;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            LayoutController

public abstract class BaseView extends GLSurfaceView
    implements android.opengl.GLSurfaceView.Renderer, com.arcsoft.mediaplus.picture.controller.DecodeTask.DecodeListener, android.view.GestureDetector.OnGestureListener
{
    class TaskHandle extends Handler
    {

        final BaseView this$0;

        public void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                return;

            case 100: // 'd'
                removeMessages(message.what);
                break;
            }
            prefetch(mLayoutController.getVisibleStart(), mLayoutController.getVisibleEnd());
            BaseView.this.preDecode(getDecodeStart(mDirection), getDecodeEnd(mDirection), mDirection);
        }

        public void preDecode()
        {
            sendEmptyMessage(100);
        }

        public TaskHandle(Looper looper)
        {
            this$0 = BaseView.this;
            super(looper);
        }
    }


    protected static int BITMAP_TEXTURE_ARRAY[] = null;
    private static final int MSG_PRE_DECODE = 100;
    private static final float OUTLINE_WIDTH = 3F;
    private static final int PLACEHOLDER_COLOR = 0xff222222;
    protected static final int SCROLL_BAR_H = 30;
    protected static final int SCROLL_BAR_W = 6;
    protected static final String TAG = "ThumbGlView";
    protected static final int eTexture_DefaultPhoto = 2;
    protected static final int eTexture_DefaultVideo = 3;
    protected static final int eTexture_Loading = 14;
    protected static final int eTexture_Mark = 0;
    protected static final int eTexture_ProgressBg = 5;
    protected static final int eTexture_ProgressValue = 6;
    protected static final int eTexture_PullDown = 12;
    protected static final int eTexture_RefreshViewBg = 15;
    protected static final int eTexture_Release = 13;
    protected static final int eTexture_Scroll = 1;
    protected static final int eTexture_VideoInfo = 4;
    protected static final int eTexture_ViewBg = 11;
    protected static final int eTexture_download_failed = 9;
    protected static final int eTexture_download_in = 8;
    protected static final int eTexture_download_ok = 10;
    protected static final int eTexture_download_wait = 7;
    private static BitmapTexture mArrayBitmapTexture[] = null;
    public static int mSurfaceHeightMax = 0;
    public static int mSurfaceHeightMin = 0;
    public static int mSurfaceWidthMax = 0;
    public static int mSurfaceWidthMin = 0;
    private int DURATION_FONT_SIZE;
    private volatile int PRE_MAX;
    private volatile int PRE_MIN;
    protected int REFRESH_INFO_FONT_SIZE;
    protected CacheMgr mCacheMgr;
    protected GLCanvasImpl mCanvas;
    Rect mClipRect;
    int mClipRetryCount;
    protected Context mContext;
    private int mDataBuildState;
    protected PictureDataSource mDataSource;
    protected volatile int mDirection;
    protected GL11 mGL;
    private GestureDetector mGestureDetector;
    private HandlerThread mHandlerThread;
    protected boolean mIsRefreshRemote;
    protected long mLastUpdateTime;
    protected LayoutController mLayoutController;
    protected int mMaxRefreshViewHeight;
    protected int mMinReleaseHeight;
    PerformanceTester mPerformanceTester;
    LayoutController.ILayoutControllerReadyListener mReadyListener = new LayoutController.ILayoutControllerReadyListener() {

        final BaseView this$0;

        public void ready()
        {
            Log.d("ThumbGlView", "layoutController is ready, notify refersh");
            requestRender();
        }

            
            {
                this$0 = BaseView.this;
                super();
            }
    };
    protected GLPaint mRectPaind;
    protected int mRefreshViewBottomOffset;
    protected int mScroll;
    protected boolean mScrolling;
    private volatile int mStart;
    protected final Object mSyncRefreshObject;
    private TaskHandle mTaskHandler;
    protected HashMap mTextures;
    private int mTouchDownState;

    public BaseView(Context context)
    {
        super(context);
        mContext = null;
        mScroll = 0;
        mHandlerThread = null;
        mTaskHandler = null;
        mDataSource = null;
        DURATION_FONT_SIZE = 28;
        mClipRetryCount = 0;
        mClipRect = new Rect();
        mStart = -1;
        mDirection = 0;
        mScrolling = false;
        mSyncRefreshObject = new Object();
        REFRESH_INFO_FONT_SIZE = 25;
        mDataBuildState = 0;
        mTouchDownState = 0;
        mLastUpdateTime = 0L;
        mMaxRefreshViewHeight = 0;
        mMinReleaseHeight = 0;
        mRefreshViewBottomOffset = 0;
        mIsRefreshRemote = false;
        mPerformanceTester = new PerformanceTester("ThumbGlView");
        PRE_MAX = 200;
        PRE_MIN = 80;
        mTextures = new HashMap();
        mContext = context;
        setRenderer(this);
    }

    public BaseView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = null;
        mScroll = 0;
        mHandlerThread = null;
        mTaskHandler = null;
        mDataSource = null;
        DURATION_FONT_SIZE = 28;
        mClipRetryCount = 0;
        mClipRect = new Rect();
        mStart = -1;
        mDirection = 0;
        mScrolling = false;
        mSyncRefreshObject = new Object();
        REFRESH_INFO_FONT_SIZE = 25;
        mDataBuildState = 0;
        mTouchDownState = 0;
        mLastUpdateTime = 0L;
        mMaxRefreshViewHeight = 0;
        mMinReleaseHeight = 0;
        mRefreshViewBottomOffset = 0;
        mIsRefreshRemote = false;
        mPerformanceTester = new PerformanceTester("ThumbGlView");
        PRE_MAX = 200;
        PRE_MIN = 80;
        mTextures = new HashMap();
        mContext = context;
        setRenderer(this);
    }

    private static void createBitmapTexture()
    {
        if (mArrayBitmapTexture == null)
        {
            mArrayBitmapTexture = new BitmapTexture[BITMAP_TEXTURE_ARRAY.length];
            for (int i = 0; i < BITMAP_TEXTURE_ARRAY.length; i++)
            {
                mArrayBitmapTexture[i] = null;
            }

        }
    }

    private static void destroyBitmapTexture()
    {
        if (mArrayBitmapTexture != null)
        {
            for (int i = 0; i < BITMAP_TEXTURE_ARRAY.length; i++)
            {
                if (mArrayBitmapTexture[i] != null)
                {
                    mArrayBitmapTexture[i].recycle();
                    mArrayBitmapTexture[i] = null;
                }
            }

            mArrayBitmapTexture = null;
        }
    }

    protected static BitmapTexture getBitmapTextureByType(Context context, int i)
    {
        while (mArrayBitmapTexture == null || i < 0 || i >= BITMAP_TEXTURE_ARRAY.length) 
        {
            return null;
        }
        if (mArrayBitmapTexture[i] == null)
        {
            long l = System.currentTimeMillis();
            Log.e("ThumbGlView", (new StringBuilder()).append("FENG : type = ").append(i).toString());
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), BITMAP_TEXTURE_ARRAY[i]);
            if (bitmap != null)
            {
                mArrayBitmapTexture[i] = new BitmapTexture(bitmap);
            }
            long l1 = System.currentTimeMillis() - l;
            Log.e("ThumbGlView", (new StringBuilder()).append("getBitmapTextureByType[").append(i).append("]: cost = ").append(l1).toString());
        }
        return mArrayBitmapTexture[i];
    }

    static void getTextureId()
    {
        if (SystemUtils.getSDKVersion() < 9)
        {
            BITMAP_TEXTURE_ARRAY = (new int[] {
                0x7f02022e, 0x7f020227, 0x7f020294, 0x7f020295, 0x7f02017f, 0x7f020206, 0x7f020202, 0x7f020108, 0x7f020104, 0x7f020101, 
                0x7f020105, 0x7f0201b0, 0x7f02021b, 0x7f02021c, 0x7f0201f5
            });
            return;
        } else
        {
            BITMAP_TEXTURE_ARRAY = (new int[] {
                0x7f02022e, 0x7f020227, 0x7f020293, 0x7f020295, 0x7f02017e, 0x7f020106, 0x7f020107, 0x7f020108, 0x7f020104, 0x7f020100, 
                0x7f020105, 0x7f0201b0, 0x7f02021b, 0x7f02021c, 0x7f0201f5
            });
            return;
        }
    }

    private FadeInTexture getTextureInSpecialSever(String s)
    {
        if (mTextures.size() <= 0 || s == null || s.length() <= 0 || s.indexOf("?formatID") == -1)
        {
            return null;
        }
        Set set = mTextures.keySet();
        String as[] = new String[mTextures.size()];
        set.toArray(as);
        String s1 = s.substring(1 + s.lastIndexOf("/"), s.indexOf("?formatID"));
        int i = as.length;
        int j = 0;
        do
        {
label0:
            {
                String s2 = null;
                if (j < i)
                {
                    String s3 = as[j];
                    if (!s3.contains(s1))
                    {
                        break label0;
                    }
                    s2 = s3;
                }
                return getTexture(s2);
            }
            j++;
        } while (true);
    }

    private void initHandlerThreadIfNeeded()
    {
        if (mHandlerThread == null)
        {
            mHandlerThread = new HandlerThread("DataApt", 10);
            mHandlerThread.start();
            mTaskHandler = new TaskHandle(mHandlerThread.getLooper());
        }
    }

    boolean canDrawFrame()
    {
        return true;
    }

    void decorateItem(Rect rect, MediaItem mediaitem, int i, boolean flag)
    {
        if (mediaitem != null && !mediaitem.isDownloadingFile)
        {
            boolean flag1 = ListViewManager.isSelectedItem(Integer.valueOf(i));
            boolean flag2 = mediaitem.videoOrImage;
            if (flag1)
            {
                BitmapTexture bitmaptexture1 = getBitmapTextureByType(mContext, 0);
                int k = (int)mContext.getResources().getDimension(0x7f0800e6);
                if (bitmaptexture1 != null)
                {
                    bitmaptexture1.setOpaque(false);
                    bitmaptexture1.draw(mCanvas, rect.right - k - 4, 4 + rect.top, k, k);
                }
            }
            if (flag2)
            {
                BitmapTexture bitmaptexture = getBitmapTextureByType(mContext, 4);
                if (bitmaptexture != null)
                {
                    bitmaptexture.getWidth();
                    int j = bitmaptexture.getHeight();
                    bitmaptexture.setOpaque(false);
                    bitmaptexture.draw(mCanvas, rect.left, rect.bottom - j, rect.width(), j);
                    Rect rect1 = new Rect(rect.left + rect.width() / 2, rect.bottom - j, rect.right, rect.bottom);
                    renderDuration(mCanvas, mediaitem, i, rect1);
                    return;
                }
            }
        }
    }

    boolean drawFadeInTexture(FadeInTexture fadeintexture, Rect rect)
    {
        drawTexture(fadeintexture, rect);
        return false | fadeintexture.isAnimating();
    }

    void drawTexture(Texture texture, Rect rect)
    {
        if (texture != null)
        {
            texture.draw(mCanvas, rect.left, rect.top, rect.width(), rect.height());
        }
    }

    public int getDataBuildState()
    {
        return mDataBuildState;
    }

    protected int getDecodeEnd(int i)
    {
        if (mLayoutController == null)
        {
            return 0;
        }
        int j;
        int k;
        if (i >= 0)
        {
            j = PRE_MAX;
        } else
        {
            j = PRE_MIN;
        }
        k = j + mLayoutController.getVisibleEnd();
        if (k > mLayoutController.mCount)
        {
            k = mLayoutController.mCount;
        }
        return k;
    }

    protected int getDecodeStart(int i)
    {
        int k;
        if (mLayoutController == null)
        {
            k = 0;
        } else
        {
            int j;
            if (i > 0)
            {
                j = PRE_MIN;
            } else
            {
                j = PRE_MAX;
            }
            k = mLayoutController.getVisibleStart() - j;
            if (k < 0)
            {
                return 0;
            }
        }
        return k;
    }

    int getRealPosition(int i)
    {
        return i;
    }

    FadeInTexture getTexture(int i)
    {
        MediaItem mediaitem;
        if (mDataSource != null)
        {
            if ((mediaitem = mDataSource.getItem(i)) != null && mediaitem.uri != null)
            {
                return (FadeInTexture)mTextures.get(mediaitem.uri.toString());
            }
        }
        return null;
    }

    FadeInTexture getTexture(String s)
    {
        if (mTextures == null || s == null || s.length() <= 0)
        {
            return null;
        } else
        {
            return (FadeInTexture)mTextures.get(s);
        }
    }

    public void init(CacheMgr cachemgr)
    {
        mGestureDetector = new GestureDetector(getContext(), this);
        mCacheMgr = cachemgr;
        mRectPaind = new GLPaint();
        mRectPaind.setColor(0xff000000);
        mRectPaind.setLineWidth(3F);
        mLayoutController = new LayoutController(getContext());
        mLayoutController.setLayoutControllerReady(mReadyListener);
        createBitmapTexture();
        DURATION_FONT_SIZE = mContext.getResources().getInteger(0x7f0a000b);
    }

    protected boolean isFileDownloading()
    {
        return false;
    }

    protected boolean isInvalidSurface()
    {
        if (mLayoutController != null) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (mLayoutController.getUnitCount() != 6) goto _L4; else goto _L3
_L3:
        if (!SystemUtils.isLandscape(mContext)) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (SystemUtils.isLandscape(mContext))
        {
            return true;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    protected boolean isRemote()
    {
        return false;
    }

    protected boolean mediaItemInCurrentView(MediaItem mediaitem)
    {
        if (mLayoutController != null && mediaitem != null && mDataSource != null)
        {
            int i = mLayoutController.getVisibleStart();
            int j = mLayoutController.getVisibleEnd();
            int k = i;
            while (k < j) 
            {
                MediaItem mediaitem1 = mDataSource.getItem(k);
                if (mediaitem1 == null || mediaitem1.title == null || !mediaitem1.title.equals(mediaitem.title))
                {
                    k++;
                } else
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void onBitmapDone(MediaItem mediaitem, Bitmap bitmap)
    {
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return true;
    }

    public void onDrawFrame(GL10 gl10)
    {
        if (mClipRetryCount > 0)
        {
            mClipRetryCount = -1 + mClipRetryCount;
            Rect rect = mClipRect;
            gl10.glScissor(rect.left, rect.top, rect.width(), rect.height());
        }
        mCanvas.deleteRecycledResources();
        UploadedTexture.resetUploadLimit();
        mCanvas.clearBuffer();
        GLES20.glClearColor(160F, 160F, 160F, 0.0F);
        mCanvas.currentAnimationTimeMillis();
        if (!canDrawFrame())
        {
            return;
        }
        if (renderItems())
        {
            requestRender();
        }
        ((MediaPlusActivity)mContext).showHideViews();
    }

    public void onETCDone(int i, MediaItem mediaitem, android.opengl.ETC1Util.ETC1Texture etc1texture)
    {
        this;
        JVM INSTR monitorenter ;
        if (etc1texture == null) goto _L2; else goto _L1
_L1:
        FadeInTexture fadeintexture = new FadeInTexture(0xff222222, new EtcTexture(etc1texture));
        HashMap hashmap = mTextures;
        hashmap;
        JVM INSTR monitorenter ;
        if (mediaitem == null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        if (mediaitem.uri != null)
        {
            mTextures.put(mediaitem.uri.toString(), fadeintexture);
        }
        hashmap;
        JVM INSTR monitorexit ;
_L2:
        requestRender();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception1;
        exception1;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        int i = mLayoutController.getScrollLimit();
        if (i == 0)
        {
            return false;
        } else
        {
            mLayoutController.fling((int)(-f1), 0, i);
            requestRender();
            return true;
        }
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        int i = ViewManager.getViewStatus();
        int j = 0;
        if (i == 0)
        {
            boolean flag = MediaPlusLaucher.mSupportLivingView;
            int k = 0;
            if (flag)
            {
                k = (int)mContext.getResources().getDimension(0x7f080057);
            }
            j = -(-20 + (getHeight() - k) / 2);
        }
        mLayoutController.startScroll(Math.round(f1), j, mLayoutController.getScrollLimit());
        requestRender();
        return true;
    }

    public void onShowPress(MotionEvent motionevent)
    {
        Log.e("ThumbGlView", "onShowPress");
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return false;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eglconfig)
    {
        Log.e("ThumbGlView", "onSurfaceCreated");
        GL11 gl11 = (GL11)gl10;
        mGL = gl11;
        mCanvas = new GLCanvasImpl(gl11);
        setRenderMode(0);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mGestureDetector.onTouchEvent(motionevent);
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 40
    //                   0 42
    //                   1 90
    //                   2 40;
           goto _L1 _L2 _L3 _L1
_L1:
        return true;
_L2:
        boolean flag;
        if (!mLayoutController.isFinished())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mScrolling = flag;
        mLayoutController.forceFinished();
        if (OEMConfig.SUPPORT_REFRESH_VIEW)
        {
            mTouchDownState = getDataBuildState();
            return true;
        }
          goto _L1
_L3:
        if (OEMConfig.SUPPORT_REFRESH_VIEW)
        {
            int i = mLayoutController.getPosition();
            if (i < 0)
            {
                if (i < -mMinReleaseHeight && mTouchDownState <= 0 && !mIsRefreshRemote && !isFileDownloading())
                {
                    mIsRefreshRemote = true;
                    ((MediaPlusActivity)mContext).refreshRemoteView();
                }
                updateScrollPosition(0, false);
                mLayoutController.setPosition(0);
            }
        }
        requestRender();
        return true;
    }

    protected void pause(boolean flag)
    {
        Log.i("ThumbGlView", "base view: pause");
        if (flag)
        {
            synchronized (mTextures)
            {
                mTextures.clear();
            }
        }
        if (mCacheMgr != null)
        {
            mCacheMgr.removeAllTask();
        }
        if (mHandlerThread != null)
        {
            mHandlerThread.quit();
        }
        mHandlerThread = null;
        mTaskHandler = null;
        mStart = -1;
        return;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void preDecode()
    {
        synchronized (mSyncRefreshObject)
        {
            if (mTaskHandler != null)
            {
                mTaskHandler.preDecode();
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected abstract void preDecode(int i, int j, int k);

    protected void prefetch(int i, int j)
    {
    }

    String remoteUriToLocal(MediaItem mediaitem)
    {
        String s = AbsPoolDriver.getDownloadPath(-1, OEMConfig.DOWNLOAD_PATH, mediaitem.title, mediaitem.uri.toString(), MyUPnPUtils.getItemResource(RemoteDBMgr.instance().getCurrentServer(), RemoteDBMgr.instance().getRemoteItemUUID(mediaitem._id)));
        return Uri.parse((new StringBuilder()).append("file://").append(s).toString()).toString();
    }

    void removeTexture(int i)
    {
        MediaItem mediaitem;
        if (mDataSource != null)
        {
            if ((mediaitem = mDataSource.getItem(i)) != null && mediaitem.uri != null)
            {
                mTextures.remove(mediaitem.uri.toString());
                return;
            }
        }
    }

    protected void renderDuration(GLCanvasImpl glcanvasimpl, MediaItem mediaitem, int i, Rect rect)
    {
        if (mediaitem.isDownloadingFile)
        {
            return;
        }
        long l = 0L;
        if (mediaitem instanceof VideoItem)
        {
            l = ((VideoItem)mediaitem).duration;
        }
        int j = rect.width();
        int k = rect.left;
        int i1 = (int)(l / 0x36ee80L);
        int j1 = (int)((l - (long)(0x36ee80 * i1)) / 60000L);
        int k1 = (int)((999L + (l - (long)(0x36ee80 * i1) - (long)(60000 * j1))) / 1000L);
        DecimalFormat decimalformat = new DecimalFormat("00");
        String s;
        StringTexture stringtexture;
        if (i1 > 0)
        {
            int l1 = (j * 15) / 48;
            if (i1 >= 24)
            {
                i1 %= 24;
            }
            s = (new StringBuilder()).append(decimalformat.format(i1)).append(":").append(decimalformat.format(j1)).append(":").append(decimalformat.format(k1)).toString();
            j += l1;
            k -= l1;
        } else
        {
            s = (new StringBuilder()).append(decimalformat.format(j1)).append(":").append(decimalformat.format(k1)).toString();
        }
        stringtexture = StringTexture.newInstance(s, DURATION_FONT_SIZE, 0xff888888, j, false, android.graphics.Paint.Align.LEFT, true);
        stringtexture.draw(glcanvasimpl, k, 4 + (rect.top + (rect.height() - stringtexture.getHeight()) / 2));
        stringtexture.recycle();
    }

    protected boolean renderItems()
    {
        if (mLayoutController.isReady()) goto _L2; else goto _L1
_L1:
        boolean flag = false;
_L4:
        return flag;
_L2:
        int i;
        int j;
        int k;
        int l;
        int j1;
label0:
        {
            flag = mLayoutController.computeScrollOffset();
            i = mLayoutController.getPosition();
            updateScrollPosition(i, false);
            j = mLayoutController.getUnitCount();
            k = mLayoutController.getVisibleStart();
            l = mLayoutController.getVisibleEnd();
            int i1 = l - k;
            FadeInTexture fadeintexture = null;
            if (OEMConfig.SUPPORT_REFRESH_VIEW)
            {
                renderRefreshView(i);
            }
            PictureDataSource picturedatasource = mDataSource;
            j1 = 0;
            int k1 = 0;
            if (picturedatasource != null)
            {
                boolean flag1 = mDataSource.isEnable();
                j1 = 0;
                k1 = 0;
                if (flag1)
                {
                    boolean flag2 = mDataSource.isResume();
                    j1 = 0;
                    k1 = 0;
                    if (flag2)
                    {
                        j1 = mDataSource.getCount();
                        int j3 = k;
                        while (j3 < l && j3 < j1) 
                        {
                            if (mDataSource == null)
                            {
                                return false;
                            }
                            MediaItem mediaitem = mDataSource.getItem(j3);
                            if (mediaitem != null)
                            {
                                Rect rect = mLayoutController.getSlotRect(j3);
                                boolean flag3;
                                if (mediaitem.isDownloadingFile)
                                {
                                    if (3L == mediaitem.status)
                                    {
                                        fadeintexture = getTexture(remoteUriToLocal(mediaitem));
                                    }
                                    flag3 = false;
                                    if (fadeintexture == null)
                                    {
                                        Context context1 = mContext;
                                        byte byte1;
                                        if (mediaitem.videoOrImage)
                                        {
                                            byte1 = 3;
                                        } else
                                        {
                                            byte1 = 2;
                                        }
                                        drawTexture(getBitmapTextureByType(context1, byte1), rect);
                                    }
                                } else
                                {
                                    String s;
                                    if (mediaitem.uri == null)
                                    {
                                        s = null;
                                    } else
                                    {
                                        s = mediaitem.uri.toString();
                                    }
                                    fadeintexture = getTexture(s);
                                    if (fadeintexture == null && mediaitem.uri != null)
                                    {
                                        fadeintexture = getTextureInSpecialSever(mediaitem.uri.toString());
                                    }
                                    flag3 = false;
                                    if (fadeintexture == null)
                                    {
                                        flag3 = true;
                                        Context context = mContext;
                                        byte byte0;
                                        if (mediaitem.videoOrImage)
                                        {
                                            byte0 = 3;
                                        } else
                                        {
                                            byte0 = 2;
                                        }
                                        drawTexture(getBitmapTextureByType(context, byte0), rect);
                                        k1++;
                                    }
                                }
                                if (fadeintexture != null && mDataSource != null)
                                {
                                    flag |= drawFadeInTexture(fadeintexture, rect);
                                }
                                decorateItem(rect, mediaitem, j3, flag3);
                            }
                            j3++;
                        }
                    }
                }
            }
            if (flag)
            {
                int i3 = i1 / 2;
                if (k1 <= i3)
                {
                    break label0;
                }
            }
            if (mStart != k)
            {
                mStart = k;
                mDirection = i - mScroll;
                preDecode();
            }
        }
        int l1 = mLayoutController.mContentLength;
        int i2 = 0;
        if (l1 != 0)
        {
            int k2 = mLayoutController.mContentLength;
            int l2 = mLayoutController.mHeight;
            i2 = 0;
            if (k2 != l2)
            {
                i2 = -30 + (i * mLayoutController.mHeight) / (mLayoutController.mContentLength - mLayoutController.mHeight);
            }
        }
        if (i2 < 0)
        {
            i2 = 0;
        }
        int j2 = j1 % j;
        if (j1 > l + j2 || j1 == l + j2 && k > 0)
        {
            BitmapTexture bitmaptexture = getBitmapTextureByType(mContext, 1);
            if (bitmaptexture != null)
            {
                bitmaptexture.draw(mCanvas, -6 + mLayoutController.mWidth, i2, 6, 30);
                return flag;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected int renderLastUpdateTime(GLCanvasImpl glcanvasimpl, long l, Rect rect)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        String s = (String)DateFormat.format("MM/dd/yyyy hh:mm:ss", calendar);
        StringTexture stringtexture = StringTexture.newInstance(String.format(getResources().getString(0x7f0b01de), new Object[] {
            s
        }), REFRESH_INFO_FONT_SIZE, 0xff000000, rect.width(), false, android.graphics.Paint.Align.LEFT, false);
        stringtexture.draw(glcanvasimpl, (rect.width() - stringtexture.getWidth()) / 2, rect.top);
        stringtexture.recycle();
        return stringtexture.getWidth();
    }

    protected int renderRefreshInfo(GLCanvasImpl glcanvasimpl, String s, Rect rect)
    {
        StringTexture stringtexture = StringTexture.newInstance(s, REFRESH_INFO_FONT_SIZE, 0xff000000, rect.width(), false, android.graphics.Paint.Align.LEFT, false);
        stringtexture.draw(glcanvasimpl, (rect.width() - stringtexture.getWidth()) / 2, rect.top);
        stringtexture.recycle();
        return stringtexture.getWidth();
    }

    protected void renderRefreshView(int i)
    {
    }

    protected void resume(boolean flag)
    {
        Log.i("ThumbGlView", "base view: resume");
        initHandlerThreadIfNeeded();
        if (flag)
        {
            updateScrollPosition(0, true);
            mLayoutController.setPosition(0);
        }
        if (mLayoutController.getPosition() >= 0 && mLayoutController.getBottomPosition() >= 0 && mLayoutController.getPosition() > mLayoutController.getBottomPosition())
        {
            mLayoutController.setPosition(mLayoutController.getBottomPosition());
            updateScrollPosition(mLayoutController.getBottomPosition(), true);
        }
        requestRender();
    }

    public void setDataBuildState(int i)
    {
        mDataBuildState = i;
    }

    protected void setLastUpdateTime(long l)
    {
        mLastUpdateTime = l;
    }

    public void uninit()
    {
        destroyBitmapTexture();
    }

    protected void updateScrollPosition(int i, boolean flag)
    {
        if (!flag && i == mScroll)
        {
            return;
        } else
        {
            mScroll = i;
            mLayoutController.setScrollPosition(i, flag);
            return;
        }
    }

    static 
    {
        getTextureId();
    }
}
