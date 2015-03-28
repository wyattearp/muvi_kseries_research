// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.arcsoft.util.ImageUtils;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.util.debug.DebugUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.tool.CachePathManager;
import com.arcsoft.util.tool.DynamicDataStateMachine;
import java.io.File;
import java.io.IOException;

// Referenced classes of package com.arcsoft.adk.image:
//            MultiTouchGestureDetector, IFileList

public class PhotoViewer extends SurfaceView
{
    public class AMPV_State
    {

        public int mImageOrientationToScreen;
        public Rect mImgDisplayRect;
        public int mImgHeight;
        public int mImgIndex;
        public int mImgWidth;
        public int mZoom;
        public boolean mbIsFitIn;
        public boolean mbTransColor;
        final PhotoViewer this$0;

        public AMPV_State()
        {
            this$0 = PhotoViewer.this;
            super();
            mImgDisplayRect = new Rect();
        }
    }

    class GestureListener
        implements android.view.GestureDetector.OnGestureListener, MultiTouchGestureDetector.OnPinchListener, android.view.GestureDetector.OnDoubleTapListener
    {

        private boolean isStartDrag;
        final PhotoViewer this$0;

        public void endTouch(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", (new StringBuilder()).append("GestureListener, endTouch, isStartDrag = ").append(isStartDrag).toString());
            if (isStartDrag)
            {
                native_dragEnd(mNativeContext, (int)motionevent.getX(), (int)motionevent.getY());
                isStartDrag = false;
            }
        }

        public boolean onDoubleTap(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onDoubleTap");
            while (mListener == null || mListener.isVideo(getCurrentIndex()) || !mListener.isSupportZoom(getCurrentIndex()) || mIsDragging && !mIsFirstInZoomInCase) 
            {
                return true;
            }
            mIsFirstInZoomInCase = false;
            char c;
            if (!isFitIn())
            {
                c = '\uFFF1';
                setMode(Mode.Normal);
            } else
            {
                if (getZoomLevel() >= 10000)
                {
                    c = '\u4E20';
                } else
                {
                    c = '\u2710';
                }
                setMode(Mode.Zoom);
            }
            native_zoomAsync(mNativeContext, c, (int)motionevent.getX(), (int)motionevent.getY());
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onDoubleTapEvent");
            if (mListener != null)
            {
                mListener.resetControlTimer();
            }
            return true;
        }

        public boolean onDown(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onDown");
            return false;
        }

        public boolean onEndPinch(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onEndPinch");
            while (mListener == null || mListener.isVideo(getCurrentIndex()) || !mListener.isSupportZoom(getCurrentIndex())) 
            {
                return true;
            }
            int i = getZoomLevel();
            int j = mCurrentFitInZoomLevel / 100;
            if (i < j + mCurrentFitInZoomLevel && i > mCurrentFitInZoomLevel - j)
            {
                native_zoom(mNativeContext, -15, 0, 0);
            }
            PhotoViewer photoviewer = PhotoViewer.this;
            Mode mode;
            if (isFitIn())
            {
                mode = Mode.Normal;
            } else
            {
                mode = Mode.Zoom;
            }
            photoviewer.setMode(mode);
            return true;
        }

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if (mListener != null)
            {
                mListener.resetControlTimer();
            }
            Log.v("PhotoViewer", "GestureListener, onFling");
            return false;
        }

        public void onLongPress(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onLongPress");
            if (mListener != null)
            {
                mListener.resetControlTimer();
            }
        }

        public boolean onPinch(MotionEvent motionevent, MotionEvent motionevent1, PointF pointf, float f)
        {
            Log.v("PhotoViewer", "GestureListener, onPinch");
            if (mListener != null)
            {
                mListener.resetControlTimer();
            }
            while (mListener == null || mListener.isVideo(getCurrentIndex()) || !mListener.isSupportZoom(getCurrentIndex())) 
            {
                return true;
            }
            long l = System.currentTimeMillis();
            int i = (int)(f * (float)getZoomLevel());
            native_zoom(mNativeContext, i, (int)pointf.x, (int)pointf.y);
            long l1 = System.currentTimeMillis() - l;
            Log.d("PhotoViewer", (new StringBuilder()).append("native_zoom cost: ").append(l1).toString());
            return true;
        }

        public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            Log.v("PhotoViewer", (new StringBuilder()).append("GestureListener, onScroll, isStartDrag = ").append(isStartDrag).toString());
            if (mListener != null)
            {
                mListener.resetControlTimer();
            }
            long l = System.currentTimeMillis();
            if (!isStartDrag)
            {
                if (mListener != null)
                {
                    mListener.onSliding(true);
                }
                int i = (int)motionevent.getX();
                int j = (int)motionevent.getY();
                native_dragStart(mNativeContext, i, j);
                isStartDrag = true;
            }
            native_pan(mNativeContext, -(int)f, -(int)f1);
            long l1 = System.currentTimeMillis() - l;
            Log.d("PhotoViewer", (new StringBuilder()).append("native_pan cost: ").append(l1).toString());
            return true;
        }

        public void onShowPress(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onShowPress");
        }

        public boolean onSingleTapConfirmed(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onSingleTapConfirmed");
            if (mListener != null)
            {
                mListener.onClick();
            }
            return false;
        }

        public boolean onSingleTapUp(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onSingleTapUp");
            return false;
        }

        public boolean onStartPinch(MotionEvent motionevent)
        {
            Log.v("PhotoViewer", "GestureListener, onStartPinch");
            setMode(Mode.Zoom);
            return true;
        }

        GestureListener()
        {
            this$0 = PhotoViewer.this;
            super();
            isStartDrag = false;
        }
    }

    public static final class Mode extends Enum
    {

        private static final Mode $VALUES[];
        public static final Mode Normal;
        public static final Mode Zoom;

        public static Mode valueOf(String s)
        {
            return (Mode)Enum.valueOf(com/arcsoft/adk/image/PhotoViewer$Mode, s);
        }

        public static Mode[] values()
        {
            return (Mode[])$VALUES.clone();
        }

        static 
        {
            Normal = new Mode("Normal", 0);
            Zoom = new Mode("Zoom", 1);
            Mode amode[] = new Mode[2];
            amode[0] = Normal;
            amode[1] = Zoom;
            $VALUES = amode;
        }

        private Mode(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface PhotoViewerListener
    {

        public abstract boolean isPreparingFilePath(int i);

        public abstract boolean isSupportZoom(int i);

        public abstract boolean isVideo(int i);

        public abstract void onClick();

        public abstract void onCurrentIndexChanged(int i);

        public abstract void onModeChanged(Mode mode);

        public abstract void onPrefetch(int i, int j);

        public abstract void onPrefetchEx(int ai[]);

        public abstract void onSliding(boolean flag);

        public abstract void resetControlTimer();
    }


    public static final int AMPV_ITEM_EDIT = 0xa92702;
    public static final int AMPV_ITEM_INSERT = 0xa92700;
    public static final int AMPV_ITEM_RANGE = 0xa92703;
    public static final int AMPV_ITEM_REDEC = 0xa92705;
    public static final int AMPV_ITEM_REMOVE = 0xa92701;
    public static final int AMPV_ITEM_RENAME = 0xa92704;
    private static final int AMPV_MSG_AFTER_CLOSE_FILE = 0xa72004;
    private static final int AMPV_MSG_BEFORE_OPEN_FILE = 0xa72003;
    private static final int AMPV_MSG_BROWSING = 0xa72000;
    private static final int AMPV_MSG_DRAGING = 0xa72005;
    private static final int AMPV_MSG_FILMSTRIP_BROWSING = 0xa72002;
    private static final int AMPV_MSG_IMG_THUMB_READY = 0xa72006;
    private static final int AMPV_MSG_INDEX_CHANGED = 0xf0a72000;
    private static final int AMPV_MSG_REFRESH_CLEARDATA = 0xa72001;
    private static String CACHE_UID = com/arcsoft/adk/image/PhotoViewer.getSimpleName();
    private static final int MSG_DOSTEP = 0;
    private static final int MSG_SURFACE_CHANGED = 1;
    private static final int PREFETCH_RANGE = 4;
    private static final String TAG = "PhotoViewer";
    private static final int TIME_DO_STEP_DELAY = 3;
    private static final int TIME_DO_STEP_NO_MORE_DELAY = 50;
    private static final int ZOOM_FITIN = -15;
    private static final int ZOOM_MAX = 20000;
    private static final int ZOOM_NORMAL = 10000;
    private final com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions mActions;
    private final com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener mCacheClearListener;
    private String mCachePath;
    private Context mContext;
    private int mCurrentFitInZoomLevel;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private Bitmap mErrorBitmap;
    private IFileList mFileList;
    private boolean mFlick2Next;
    private MultiTouchGestureDetector mGestureDetector;
    GestureListener mGestureListner;
    private final Handler mHandler;
    private boolean mIsDragging;
    private boolean mIsFirstInZoomInCase;
    private boolean mIsRotating;
    private PhotoViewerListener mListener;
    private Bitmap mLoadingBitmap;
    private Mode mMode;
    private int mNativeContext;
    private boolean mNativeInit;
    private int mPreIndex;
    private DynamicDataStateMachine mStateMachine;
    private final android.view.SurfaceHolder.Callback mSurfaceCallback;
    private boolean mSurfaceCreated;

    public PhotoViewer(Context context)
    {
        super(context);
        mFlick2Next = true;
        mPreIndex = -1;
        mSurfaceCreated = false;
        mActions = new com.arcsoft.util.tool.DynamicDataStateMachine.OnStateChangeActions() {

            final PhotoViewer this$0;

            public void onDisable()
            {
                PhotoViewer.this.onDisable();
            }

            public void onEnable()
            {
                PhotoViewer.this.onEnable();
            }

            public void onInit()
            {
                PhotoViewer.this.onInit();
            }

            public void onPause()
            {
                PhotoViewer.this.onPause();
            }

            public void onResume()
            {
                PhotoViewer.this.onResume();
            }

            public void onUninit()
            {
                PhotoViewer.this.onUninit();
            }

            
            {
                this$0 = PhotoViewer.this;
                super();
            }
        };
        mGestureListner = new GestureListener();
        mGestureDetector = null;
        mHandler = new Handler() {

            final PhotoViewer this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 0 1: default 28
            //                           0 34
            //                           1 89;
                   goto _L1 _L2 _L3
_L1:
                super.handleMessage(message);
                return;
_L2:
                if (native_doStep(mNativeContext) == 0x80002)
                {
                    mHandler.sendEmptyMessageDelayed(0, 50L);
                } else
                {
                    mHandler.sendEmptyMessageDelayed(0, 3L);
                }
                continue; /* Loop/switch isn't completed */
_L3:
                int i = message.arg1;
                int j = message.arg2;
                int k = getDisplayRotation();
                native_setDisplaySurface(mNativeContext, getHolder().getSurface(), SystemUtils.getSDKVersion());
                if (!mListener.isVideo(getCurrentIndex()))
                {
                    mIsRotating = true;
                }
                native_rotateScreen(mNativeContext, k, i, j);
                if (true) goto _L1; else goto _L4
_L4:
            }

            
            {
                this$0 = PhotoViewer.this;
                super();
            }
        };
        mCacheClearListener = new com.arcsoft.util.tool.CachePathManager.IOnCacheClearCacheListener() {

            final PhotoViewer this$0;

            public void onClearCacheFinished(String s)
            {
                if (!s.equals(PhotoViewer.CACHE_UID))
                {
                    return;
                } else
                {
                    lockCachePath(false);
                    initNativeContext(mDisplayWidth, mDisplayHeight);
                    return;
                }
            }

            public void onPrepareToClearCache(String s)
            {
                if (!s.equals(PhotoViewer.CACHE_UID))
                {
                    return;
                } else
                {
                    uninitNativeContext();
                    releaseCachePath(false);
                    return;
                }
            }

            
            {
                this$0 = PhotoViewer.this;
                super();
            }
        };
        mSurfaceCallback = new android.view.SurfaceHolder.Callback() {

            final PhotoViewer this$0;

            public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
            {
                if (mDisplayWidth == 0 || mDisplayHeight == 0)
                {
                    setDisplaySize(j, k);
                }
                if (mListener != null)
                {
                    mListener.resetControlTimer();
                }
                if (!isSurfaceAndScreenOrientationMatch(j, k))
                {
                    Log.e("PhotoViewer", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("Surface size not match display orientation.").toString());
                    return;
                } else
                {
                    Message message = mHandler.obtainMessage(1);
                    message.arg1 = j;
                    message.arg2 = k;
                    mHandler.sendMessageDelayed(message, 10L);
                    return;
                }
            }

            public void surfaceCreated(SurfaceHolder surfaceholder)
            {
                Log.e("PhotoViewer", "surfaceCreated:");
                PhotoViewer photoviewer = PhotoViewer.this;
                boolean flag;
                if (!isFitIn())
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                photoviewer.mIsFirstInZoomInCase = flag;
                mSurfaceCreated = true;
            }

            public void surfaceDestroyed(SurfaceHolder surfaceholder)
            {
                native_setDisplaySurface(mNativeContext, null, SystemUtils.getSDKVersion());
            }

            
            {
                this$0 = PhotoViewer.this;
                super();
            }
        };
        mIsRotating = false;
        mIsDragging = false;
        mIsFirstInZoomInCase = false;
        mNativeContext = 0;
        mContext = null;
        mNativeInit = false;
        construct(context);
    }

    public PhotoViewer(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFlick2Next = true;
        mPreIndex = -1;
        mSurfaceCreated = false;
        mActions = new _cls1();
        mGestureListner = new GestureListener();
        mGestureDetector = null;
        mHandler = new _cls2();
        mCacheClearListener = new _cls3();
        mSurfaceCallback = new _cls4();
        mIsRotating = false;
        mIsDragging = false;
        mIsFirstInZoomInCase = false;
        mNativeContext = 0;
        mContext = null;
        mNativeInit = false;
        construct(context);
    }

    public PhotoViewer(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mFlick2Next = true;
        mPreIndex = -1;
        mSurfaceCreated = false;
        mActions = new _cls1();
        mGestureListner = new GestureListener();
        mGestureDetector = null;
        mHandler = new _cls2();
        mCacheClearListener = new _cls3();
        mSurfaceCallback = new _cls4();
        mIsRotating = false;
        mIsDragging = false;
        mIsFirstInZoomInCase = false;
        mNativeContext = 0;
        mContext = null;
        mNativeInit = false;
        construct(context);
    }

    private void construct(Context context)
    {
        mNativeContext = native_create();
        SurfaceHolder surfaceholder = getHolder();
        surfaceholder.addCallback(mSurfaceCallback);
        surfaceholder.setType(0);
        surfaceholder.setFormat(4);
        mStateMachine = new DynamicDataStateMachine("PhotoViewer");
        mStateMachine.setOnStateChangeActions(mActions);
        mMode = Mode.Normal;
        mContext = context;
        mGestureDetector = new MultiTouchGestureDetector(getContext(), mGestureListner);
    }

    private void destoryNativeContext()
    {
        if (mNativeContext != 0)
        {
            native_destroy(mNativeContext);
            mNativeContext = 0;
        }
        if (mHandler != null)
        {
            mHandler.removeMessages(0);
            mHandler.removeMessages(1);
        }
    }

    private void doPrefetch()
    {
        if (mListener == null) goto _L2; else goto _L1
_L1:
        int i;
        int k;
        int l;
        int i1;
        i = getCurrentIndex();
        int j = onGetFileCount();
        k = i - 4;
        if (k < 0)
        {
            k = 0;
        }
        l = i + 4;
        if (l >= j)
        {
            l = j - 1;
        }
        Log.e("PhotoViewer", (new StringBuilder()).append("doPrefetch: start/end/currentIndex = ").append(k).append(", ").append(l).append(", ").append(i).toString());
        i1 = 1 + (l - k);
        if (i1 <= 1) goto _L4; else goto _L3
_L3:
        int ai[] = new int[i1];
        if (!mFlick2Next) goto _L6; else goto _L5
_L5:
        int k2 = i;
        int l2;
        int k3;
        for (l2 = 0; k2 <= l && l2 < i1; l2 = k3)
        {
            k3 = l2 + 1;
            ai[l2] = k2;
            k2++;
        }

        int j3;
        for (int i3 = i - 1; i3 >= k && l2 < i1; l2 = j3)
        {
            j3 = l2 + 1;
            ai[l2] = i3;
            i3--;
        }

        l2;
_L8:
        mListener.onPrefetchEx(ai);
_L2:
        return;
_L6:
        int j1 = i;
        int k1;
        int j2;
        for (k1 = 0; j1 >= k && k1 < i1; k1 = j2)
        {
            j2 = k1 + 1;
            ai[k1] = j1;
            j1--;
        }

        int i2;
        for (int l1 = i + 1; l1 <= l && k1 < i1; k1 = i2)
        {
            i2 = k1 + 1;
            ai[k1] = l1;
            l1++;
        }

        k1;
        continue; /* Loop/switch isn't completed */
_L4:
        mListener.onPrefetch(i, i);
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private int getDisplayRotation()
    {
        return ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getRotation();
    }

    private int getZoomLevel()
    {
        AMPV_State ampv_state = new AMPV_State();
        native_getState(mNativeContext, ampv_state);
        return ampv_state.mZoom;
    }

    private void initNativeContext(int i, int j)
    {
        if (mNativeInit)
        {
            Log.w("PhotoViewer", "Native context had already existed");
            return;
        }
        if (mCachePath == null)
        {
            Log.e("PhotoViewer", "Please set cache path!");
            return;
        }
        String s = Environment.getExternalStorageDirectory().getPath();
        File file = new File(s);
        if (!file.isDirectory() && !file.mkdirs())
        {
            Log.e("PhotoViewer", (new StringBuilder()).append("Unable to create cache directory ").append(s).toString());
        }
        if (i == 0 || j == 0)
        {
            throw new IllegalStateException("Invalid display size.");
        }
        int k = native_init(mNativeContext, i, j, mCachePath, s, mErrorBitmap, mLoadingBitmap);
        if (k != 0)
        {
            Log.e("PhotoViewer", (new StringBuilder()).append("result =").append(k).append(", cache =").append(mCachePath).append(", folder =").append(s).toString());
            throw new IllegalStateException("native_create fail.");
        } else
        {
            mCurrentFitInZoomLevel = getZoomLevel();
            doPrefetch();
            mNativeInit = true;
            return;
        }
    }

    private boolean isFitIn()
    {
        AMPV_State ampv_state = new AMPV_State();
        native_getState(mNativeContext, ampv_state);
        return ampv_state.mbIsFitIn;
    }

    private boolean isSurfaceAndScreenOrientationMatch(int i, int j)
    {
        boolean flag;
        flag = isTabletDevice();
        int k = getDisplayRotation();
        if (k == 1 || k == 3)
        {
            int l = j;
            j = i;
            i = l;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (i < j) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (i > j)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    private boolean isTabletDevice()
    {
        Display display = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
        int i = display.getRotation();
        int j = display.getWidth();
        int k = display.getHeight();
        return i != 0 && i != 2 ? j < k : j > k;
    }

    static void loadLibrarys()
    {
        SystemUtils.loadLibrary("platform");
        SystemUtils.loadLibrary("arcplatform");
        SystemUtils.loadLibrary("arcimgutilsbase");
        int i = SystemUtils.getSDKVersion();
        if (i < 11)
        {
            SystemUtils.loadLibrary("arcthumbengine_2_2");
            SystemUtils.loadLibrary("arcphotoviewer_2_2");
            return;
        }
        if (i < 19)
        {
            SystemUtils.loadLibrary("arcthumbengine_4_0");
            SystemUtils.loadLibrary("arcphotoviewer_4_0");
            return;
        } else
        {
            SystemUtils.loadLibrary("arcthumbengine_4_4");
            SystemUtils.loadLibrary("arcphotoviewer_4_4");
            return;
        }
    }

    private void lockCachePath(boolean flag)
    {
        String s = null;
        CachePathManager cachepathmanager;
        cachepathmanager = CachePathManager.instance();
        s = cachepathmanager.lockCachePath(CACHE_UID, 0);
        if (flag)
        {
            try
            {
                cachepathmanager.registerClearCacheListener(CACHE_UID, mCacheClearListener);
            }
            catch (IOException ioexception)
            {
                Log.e("PhotoViewer", (new StringBuilder()).append(DebugUtils.currentTraceInfo()).append("lock cache path from cache manager failed.").toString());
                ioexception.printStackTrace();
            }
            catch (IllegalStateException illegalstateexception)
            {
                Log.e("PhotoViewer", "lock cache path failed for IllegalStateException");
                illegalstateexception.printStackTrace();
            }
        }
        mCachePath = s;
        return;
    }

    private native int native_create();

    private native void native_destroy(int i);

    private native int native_doStep(int i);

    private native int native_dragEnd(int i, int j, int k);

    private native int native_dragStart(int i, int j, int k);

    private native int native_enable(int i, boolean flag);

    private native Bitmap native_getCurrentBitmap(int i, int j, int k);

    private native int native_getCurrentBitmap2(int i, Bitmap bitmap);

    private native int native_getCurrentIndex(int i);

    private native int native_getState(int i, AMPV_State ampv_state);

    private native int native_init(int i, int j, int k, String s, String s1, Bitmap bitmap, Bitmap bitmap1);

    private native int native_next(int i);

    private native int native_pan(int i, int j, int k);

    private native int native_previous(int i);

    private native int native_refreshDisplay(int i);

    private native int native_rotateScreen(int i, int j, int k, int l);

    private native int native_setCurrentIndex(int i, int j);

    private native int native_setDisplaySurface(int i, Surface surface, int j);

    private native void native_unInit(int i);

    private native int native_updateItem(int i, int j, int k);

    private native int native_zoom(int i, int j, int k, int l);

    private native int native_zoomAsync(int i, int j, int k, int l);

    private native int native_zoomIn(int i, boolean flag, int j, int k);

    private native int native_zoomOut(int i, boolean flag, int j, int k);

    private void onDisable()
    {
        setMode(Mode.Normal);
    }

    private void onEnable()
    {
    }

    private void onInit()
    {
        Log.e("PhotoViewer", "PhotoViewer onInit");
        lockCachePath(true);
    }

    private void onPause()
    {
        mHandler.removeMessages(0);
    }

    private void onResume()
    {
        mHandler.sendEmptyMessage(0);
    }

    private void onUninit()
    {
        Log.e("PhotoViewer", "PhotoViewer onUninit");
        uninitNativeContext();
        releaseCachePath(true);
        setMode(Mode.Normal);
    }

    private void releaseCachePath(boolean flag)
    {
        CachePathManager cachepathmanager = CachePathManager.instance();
        if (flag)
        {
            cachepathmanager.unregisterClearCacheListener(CACHE_UID, mCacheClearListener);
        }
        cachepathmanager.unlockCachePath(CACHE_UID);
        mCachePath = null;
    }

    private void setMode(Mode mode)
    {
        if (mode != mMode)
        {
            mMode = mode;
            if (mListener != null)
            {
                mListener.onModeChanged(mMode);
            }
        }
    }

    private void uninitNativeContext()
    {
        native_unInit(mNativeContext);
        mNativeInit = false;
        File file = new File(mCachePath);
        if (file.exists() && file.isDirectory())
        {
            File afile[] = file.listFiles();
            int i = afile.length;
            for (int j = 0; j < i; j++)
            {
                afile[j].delete();
            }

        }
    }

    public void editItem(int i)
    {
        native_updateItem(mNativeContext, 0xa92702, i);
        Log.v("PhotoViewer", (new StringBuilder()).append("edit item: index = ").append(i).toString());
        doPrefetch();
    }

    protected void finalize()
        throws Throwable
    {
        recycle();
        super.finalize();
    }

    public Rect getBitmapRect()
    {
        AMPV_State ampv_state = new AMPV_State();
        if (native_getState(mNativeContext, ampv_state) != 0)
        {
            return null;
        }
        int i = ampv_state.mImgWidth;
        int j = ampv_state.mImgHeight;
        if (ampv_state.mImageOrientationToScreen == 90 || ampv_state.mImageOrientationToScreen == 270)
        {
            i = ampv_state.mImgHeight;
            j = ampv_state.mImgWidth;
        }
        return new Rect(0, 0, i, j);
    }

    public Bitmap getCurrentBitmap()
    {
        AMPV_State ampv_state = new AMPV_State();
        Bitmap bitmap;
        if (native_getState(mNativeContext, ampv_state) != 0)
        {
            bitmap = null;
        } else
        {
            int i = ampv_state.mImgWidth;
            int j = ampv_state.mImgHeight;
            if (ampv_state.mImageOrientationToScreen == 90 || ampv_state.mImageOrientationToScreen == 270)
            {
                i = ampv_state.mImgHeight;
                j = ampv_state.mImgWidth;
            }
            Rect rect = new Rect(0, 0, i, j);
            int k = mDisplayWidth;
            int l = mDisplayHeight;
            int i1 = getDisplayRotation();
            if (i1 == 1 || i1 == 3)
            {
                k = mDisplayHeight;
                l = mDisplayWidth;
            }
            Rect rect1 = new Rect(0, 0, k, l);
            Rect rect2 = new Rect();
            ImageUtils.getFitInRect(rect1, rect, rect2, false);
            if (rect2.width() > 0 && rect2.height() > 0)
            {
                bitmap = Bitmap.createBitmap(rect2.width(), rect2.height(), android.graphics.Bitmap.Config.RGB_565);
                if (native_getCurrentBitmap2(mNativeContext, bitmap) != 0)
                {
                    bitmap.recycle();
                    return null;
                }
            } else
            {
                return null;
            }
        }
        return bitmap;
    }

    public int getCurrentIndex()
    {
        return native_getCurrentIndex(mNativeContext);
    }

    public Mode getMode()
    {
        return mMode;
    }

    public void init()
    {
        mStateMachine.init();
    }

    public void insertItem(int i)
    {
        native_updateItem(mNativeContext, 0xa92700, i);
    }

    public boolean isEnabled()
    {
        return mStateMachine.isEnable();
    }

    public boolean isNormalMode()
    {
        return isFitIn();
    }

    boolean isPreparingFilePath(int i)
    {
        if (mListener != null)
        {
            return mListener.isPreparingFilePath(i);
        } else
        {
            return false;
        }
    }

    public boolean isResume()
    {
        return mStateMachine.isResume();
    }

    public boolean isZoomed()
    {
        AMPV_State ampv_state = new AMPV_State();
        native_getState(mNativeContext, ampv_state);
        Log.v("PhotoViewer", (new StringBuilder()).append("slide, isZoomed, native_getState.....").append(ampv_state.mZoom).toString());
        return ampv_state.mZoom < 10000;
    }

    public void next()
    {
        native_next(mNativeContext);
    }

    int onGetDisplayRotation()
    {
        return getDisplayRotation();
    }

    int onGetFileCount()
    {
        if (mFileList == null)
        {
            return 0;
        } else
        {
            return mFileList.getCount();
        }
    }

    String onGetFilePath(int i)
    {
        if (mFileList == null)
        {
            return null;
        } else
        {
            return mFileList.getFilePath(i);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4 && !isFitIn())
        {
            Log.d("PhotoViewer", DebugUtils.currentTraceInfo());
            Log.e("Touch", "(Back)native_zoom = ZOOM_FITIN");
            int j = native_zoomAsync(mNativeContext, -15, 0, 0);
            boolean flag = false;
            if (j == 0)
            {
                flag = true;
            }
            return flag;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    void onNotify(int i, int j)
    {
        boolean flag = true;
        if (i != 0xf0a72000) goto _L2; else goto _L1
_L1:
        Log.i("PhotoViewer", (new StringBuilder()).append("onCurrentIndexChanged: ").append(j).toString());
        if (j == mPreIndex) goto _L4; else goto _L3
_L3:
        if (j == 0 && mPreIndex > flag)
        {
            mFlick2Next = flag;
        } else
        if (j > flag && mPreIndex == 0)
        {
            mFlick2Next = false;
        } else
        {
            if (j <= mPreIndex)
            {
                flag = false;
            }
            mFlick2Next = flag;
        }
_L8:
        mPreIndex = j;
_L4:
        if (mListener != null)
        {
            mListener.onCurrentIndexChanged(j);
            doPrefetch();
            mCurrentFitInZoomLevel = getZoomLevel();
            setMode(Mode.Normal);
        }
        mIsDragging = false;
_L6:
        return;
_L2:
        if (i == 0xa72005)
        {
            Log.i("PhotoViewer", (new StringBuilder()).append("AMPV_MSG_DRAGING: ").append(j).toString());
            boolean flag1;
            if (j != 0)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (mListener != null)
            {
                mListener.onSliding(flag1);
            }
            mIsDragging = flag1;
            mIsRotating = false;
            return;
        }
        if (i != 0xa72001) goto _L6; else goto _L5
_L5:
        Log.i("PhotoViewer", (new StringBuilder()).append("AMPV_MSG_REFRESH_CLEARDATA: ").append(j).toString());
        if (mListener != null)
        {
            mListener.onSliding(false);
        }
        mIsDragging = false;
        mIsFirstInZoomInCase = false;
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        mGestureDetector.onTouchEvent(motionevent);
        if (motionevent.getAction() == 1 || motionevent.getAction() == 3)
        {
            mGestureListner.endTouch(motionevent);
        }
        return true;
    }

    public void pause()
    {
        mStateMachine.pause();
    }

    public void prev()
    {
        native_previous(mNativeContext);
    }

    public void recycle()
    {
        mStateMachine.unInit();
        destoryNativeContext();
    }

    public void refresh()
    {
        if (mStateMachine.isEnable())
        {
            native_enable(mNativeContext, false);
            native_enable(mNativeContext, true);
        }
    }

    public void removeItem(int i)
    {
        native_updateItem(mNativeContext, 0xa92701, i);
    }

    public void resume()
    {
        mStateMachine.resume();
    }

    public void setCurrentIndex(int i)
    {
        native_setCurrentIndex(mNativeContext, i);
    }

    public void setDisplaySize(int i, int j)
    {
        boolean flag = isTabletDevice();
        int k = Math.max(i, j);
        int l = Math.min(i, j);
        int i1;
        if (flag)
        {
            i1 = k;
        } else
        {
            i1 = l;
        }
        mDisplayWidth = i1;
        if (!flag)
        {
            l = k;
        }
        mDisplayHeight = l;
    }

    public void setEnabled(boolean flag)
    {
        if (flag)
        {
            mStateMachine.enable();
            return;
        } else
        {
            mStateMachine.disable();
            return;
        }
    }

    public void setErrorIcon(int i)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
        setErrorIcon(BitmapFactory.decodeResource(getResources(), i, options));
    }

    public void setErrorIcon(Bitmap bitmap)
    {
        mErrorBitmap = bitmap;
    }

    public void setFileList(IFileList ifilelist)
    {
        mFileList = ifilelist;
        refresh();
    }

    public void setLoadingIcon(int i)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inPreferredConfig = android.graphics.Bitmap.Config.RGB_565;
        setLoadingIcon(BitmapFactory.decodeResource(getResources(), i, options));
    }

    public void setLoadingIcon(Bitmap bitmap)
    {
        mLoadingBitmap = bitmap;
    }

    public void setPhotoViewerListener(PhotoViewerListener photoviewerlistener)
    {
        mListener = photoviewerlistener;
    }

    public void svcDisable()
    {
        Log.e("PhotoViewer", "PhotoViewer svcDisable");
        uninitNativeContext();
    }

    public void svcReady()
    {
        Log.e("PhotoViewer", "PhotoViewer svcReady");
        initNativeContext(mDisplayWidth, mDisplayHeight);
    }

    public void zoomIn(boolean flag)
    {
        native_zoomIn(mNativeContext, flag, 0, 0);
    }

    public void zoomOut(boolean flag)
    {
        native_zoomOut(mNativeContext, flag, 0, 0);
    }

    static 
    {
        loadLibrarys();
    }












/*
    static boolean access$1702(PhotoViewer photoviewer, boolean flag)
    {
        photoviewer.mIsFirstInZoomInCase = flag;
        return flag;
    }

*/








/*
    static boolean access$2302(PhotoViewer photoviewer, boolean flag)
    {
        photoviewer.mIsRotating = flag;
        return flag;
    }

*/












/*
    static boolean access$3302(PhotoViewer photoviewer, boolean flag)
    {
        photoviewer.mSurfaceCreated = flag;
        return flag;
    }

*/






}
