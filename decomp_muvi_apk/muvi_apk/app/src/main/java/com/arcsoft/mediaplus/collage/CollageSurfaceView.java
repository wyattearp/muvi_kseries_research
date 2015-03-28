// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import powermobia.utils.MBitmap;
import powermobia.utils.MBitmapFactory;
import powermobia.utils.MColorSpace;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.mediaplus.collage:
//            Collage, MultiTouchDetector, Config

public class CollageSurfaceView extends SurfaceView
    implements android.view.View.OnTouchListener, android.view.SurfaceHolder.Callback, MultiTouchDetector.OnMultiTouchListener, android.view.GestureDetector.OnGestureListener
{

    private Collage mCollage;
    private String mCurrentFilePath;
    private int mDownX;
    private int mDownY;
    private boolean mFirstTime;
    private GestureDetector mGestureDetector;
    private SurfaceHolder mHolder;
    private boolean mIsOriginalTemp;
    private boolean mIsPanBegin;
    private boolean mIsPanStarted;
    private boolean mIsRotateStarted;
    private boolean mIsZoomStarted;
    private int mLongClickedRectIndex;
    private boolean mLongPress;
    private ZoomPanController.OnPanGestureListener mOnPanGestureListener;
    private ZoomPanController.OnRotateGestureListener mOnRotateGestureListener;
    private ZoomPanController.OnZoomGestureListener mOnZoomGestureListener;
    private int mPointerNum;
    private ArrayList mSaveTemplateIds;
    private MultiTouchDetector mScaleGestureDetector;
    private Surface mSurface;
    private boolean mViewContentChanged;
    private int mX;
    private int mY;

    public CollageSurfaceView(Context context)
    {
        super(context);
        mScaleGestureDetector = null;
        mIsZoomStarted = false;
        mIsRotateStarted = false;
        mIsPanStarted = false;
        mIsPanBegin = false;
        mDownX = 0;
        mDownY = 0;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mOnRotateGestureListener = null;
        mPointerNum = -1;
        mFirstTime = true;
        mViewContentChanged = false;
        mCurrentFilePath = null;
        mIsOriginalTemp = false;
        mSaveTemplateIds = null;
        mGestureDetector = null;
        mLongPress = false;
        mX = -1;
        mY = -1;
        mLongClickedRectIndex = -1;
        init(context);
    }

    public CollageSurfaceView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mScaleGestureDetector = null;
        mIsZoomStarted = false;
        mIsRotateStarted = false;
        mIsPanStarted = false;
        mIsPanBegin = false;
        mDownX = 0;
        mDownY = 0;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mOnRotateGestureListener = null;
        mPointerNum = -1;
        mFirstTime = true;
        mViewContentChanged = false;
        mCurrentFilePath = null;
        mIsOriginalTemp = false;
        mSaveTemplateIds = null;
        mGestureDetector = null;
        mLongPress = false;
        mX = -1;
        mY = -1;
        mLongClickedRectIndex = -1;
        init(context);
    }

    public CollageSurfaceView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mScaleGestureDetector = null;
        mIsZoomStarted = false;
        mIsRotateStarted = false;
        mIsPanStarted = false;
        mIsPanBegin = false;
        mDownX = 0;
        mDownY = 0;
        mOnZoomGestureListener = null;
        mOnPanGestureListener = null;
        mOnRotateGestureListener = null;
        mPointerNum = -1;
        mFirstTime = true;
        mViewContentChanged = false;
        mCurrentFilePath = null;
        mIsOriginalTemp = false;
        mSaveTemplateIds = null;
        mGestureDetector = null;
        mLongPress = false;
        mX = -1;
        mY = -1;
        mLongClickedRectIndex = -1;
        init(context);
    }

    private void addCurrentRecordById(int i)
    {
        if (mSaveTemplateIds == null)
        {
            mSaveTemplateIds = new ArrayList();
        }
        mSaveTemplateIds.add(Integer.valueOf(i));
    }

    private void drawPressRect(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (mHolder == null) goto _L2; else goto _L1
_L1:
        int j = mCollage.getTemplateThumbnailRects().size();
        if (j >= i && i >= 0) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        Rect rect;
        Canvas canvas;
        if (mFirstTime)
        {
            mFirstTime = false;
        }
        MRect mrect = (MRect)mCollage.getTemplateThumbnailRects().get(i);
        int k = mCollage.getOffsetX();
        int l = mCollage.getOffsetY();
        rect = new Rect(mrect.left, mrect.top, mrect.right, mrect.bottom);
        Collage.translateRect(rect, k, l);
        doDraw4Collage(mCollage.getTemplateThumbnailRects(), mCollage.getBitmapList());
        doDraw4Collage(mCollage.getTemplateThumbnailRects(), mCollage.getBitmapList());
        canvas = mHolder.lockCanvas(rect);
        if (canvas == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Paint paint = new Paint();
        paint.setColor(0xffff0000);
        paint.setAntiAlias(true);
        paint.setStyle(android.graphics.Paint.Style.STROKE);
        paint.setStrokeWidth(10F);
        canvas.drawRect(rect, paint);
        mHolder.unlockCanvasAndPost(canvas);
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private void init(Context context)
    {
        mCollage = (Collage)context;
        mHolder = getHolder();
        mHolder.addCallback(this);
        setOnTouchListener(this);
        mScaleGestureDetector = new MultiTouchDetector(context, this);
        mHolder.setType(0);
        mHolder.setFormat(1);
        mSurface = mHolder.getSurface();
        mGestureDetector = new GestureDetector(context, this);
        mGestureDetector.setIsLongpressEnabled(true);
        setLongClickable(true);
    }

    private boolean isCurrentTempSaved()
    {
        if (mIsOriginalTemp)
        {
            if (mSaveTemplateIds == null)
            {
                return false;
            } else
            {
                return mSaveTemplateIds.contains(Integer.valueOf(mCollage.getType()));
            }
        } else
        {
            return true;
        }
    }

    private void refreshPart(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (mFirstTime)
        {
            doDraw4Collage(mCollage.getTemplateThumbnailRects(), mCollage.getBitmapList());
            doDraw4Collage(mCollage.getTemplateThumbnailRects(), mCollage.getBitmapList());
            mFirstTime = false;
        }
        if (mSurface == null) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        MRect mrect;
        j = mCollage.getOffsetX();
        k = mCollage.getOffsetY();
        mrect = (MRect)mCollage.getTemplateThumbnailRects().get(i);
        if (mrect != null) goto _L3; else goto _L2
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L3:
        MRect mrect1 = new MRect(mrect);
        Collage.translateRect(mrect1, j, k);
        MRect amrect[] = new MRect[1];
        amrect[0] = (MRect)mCollage.getDisRects2Img().get(i);
        MRect amrect1[] = {
            mrect1
        };
        MBitmap ambitmap[] = new MBitmap[1];
        ambitmap[0] = (MBitmap)mCollage.getBitmapList().get(i);
        WorkShopUtils.doDraw4Collage(mSurface, mrect1, amrect, amrect1, ambitmap);
        if (true) goto _L2; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    private boolean rotateBeginOperation()
    {
        if (mIsPanBegin)
        {
            mIsPanBegin = false;
            if (mIsPanStarted)
            {
                mOnRotateGestureListener.OnRotateEnd();
                mIsPanStarted = false;
            }
        }
        if (mOnRotateGestureListener.OnRotateBegin() == 0)
        {
            mIsRotateStarted = true;
            return true;
        } else
        {
            return false;
        }
    }

    private void switchPictures(int i)
    {
        this;
        JVM INSTR monitorenter ;
        String as[] = mCollage.getPicPaths();
        String s = as[i];
        as[i] = as[mLongClickedRectIndex];
        as[mLongClickedRectIndex] = s;
        if (i == -1) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        Rect rect;
        MRect mrect;
        int l;
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCollage.getPicPaths()[mLongClickedRectIndex], options);
        j = options.outWidth;
        k = options.outHeight;
        rect = new Rect();
        mrect = new MRect();
        l = Config.getThumbFromExif(mCollage.getPicPaths()[mLongClickedRectIndex]);
        int i1;
        int j1;
        int k1;
        String s1;
        int l1;
        int i2;
        MBitmap mbitmap;
        MBitmap mbitmap1;
        if (l == 0)
        {
            i1 = j;
        } else
        {
            i1 = k;
        }
          goto _L3
_L23:
        j1 = ((MRect)mCollage.getTemplateThumbnailRects().get(mLongClickedRectIndex)).width();
        k1 = ((MRect)mCollage.getTemplateThumbnailRects().get(mLongClickedRectIndex)).height();
        WorkShopUtils.getFitoutRect(i1, k, j1, k1, rect);
        mrect.set(rect.left, rect.top, rect.right, rect.bottom);
        s1 = mCollage.getPicPaths()[mLongClickedRectIndex];
        if (l != 0) goto _L5; else goto _L4
_L4:
        l1 = rect.width();
_L10:
        if (l != 0) goto _L7; else goto _L6
_L6:
        i2 = rect.height();
_L11:
        int j2 = MColorSpace.MPAF_RGB32_B8G8R8A8;
        mbitmap = MBitmapFactory.createMBitmapFromFile(s1, l1, i2, j2);
        mbitmap1 = mbitmap;
        if (mbitmap1 != null) goto _L9; else goto _L8
_L8:
        this;
        JVM INSTR monitorexit ;
        return;
_L24:
        k = j;
        break; /* Loop/switch isn't completed */
_L5:
        l1 = rect.height();
          goto _L10
_L7:
        i2 = rect.width();
          goto _L11
_L9:
        if (l == 0)
        {
            break MISSING_BLOCK_LABEL_329;
        }
        mbitmap1 = mbitmap1.rotate(l);
        MBitmap mbitmap2;
        mbitmap2 = MBitmapFactory.createMBitmapBlank(mbitmap1.getWidth(), mbitmap1.getHeight(), mbitmap1.getColorSpace());
        WorkShopUtils.copyBitmap(mbitmap2, mbitmap1, new MPoint(0, 0));
        if (mbitmap2 != null)
        {
            break MISSING_BLOCK_LABEL_389;
        }
        if (mbitmap1 == null) goto _L8; else goto _L12
_L12:
        mbitmap1.recycle();
          goto _L8
        Exception exception;
        exception;
        throw exception;
        if (l == 0)
        {
            break MISSING_BLOCK_LABEL_403;
        }
        mbitmap2 = mbitmap2.rotate(l);
        int k2;
        int l2;
        Rect rect1;
        MRect mrect1;
        int i3;
        android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCollage.getPicPaths()[i], options1);
        k2 = options1.outWidth;
        l2 = options1.outHeight;
        rect1 = new Rect();
        mrect1 = new MRect();
        i3 = Config.getThumbFromExif(mCollage.getPicPaths()[i]);
        break MISSING_BLOCK_LABEL_479;
_L25:
        int k3 = ((MRect)mCollage.getTemplateThumbnailRects().get(i)).width();
        int l3 = ((MRect)mCollage.getTemplateThumbnailRects().get(i)).height();
        WorkShopUtils.getFitoutRect(j3, l2, k3, l3, rect1);
        mrect1.set(rect1.left, rect1.top, rect1.right, rect1.bottom);
        ((MRect)mCollage.getFitoutImgRects().get(i)).set(mrect1);
        mCollage.getOriFitoutRect(i).set(mrect1);
        s2 = mCollage.getPicPaths()[i];
        if (i3 != 0) goto _L14; else goto _L13
_L13:
        i4 = mrect1.width();
_L18:
        if (i3 != 0)
        {
            break MISSING_BLOCK_LABEL_690;
        }
        j4 = mrect1.height();
_L19:
        int k4 = MColorSpace.MPAF_RGB32_B8G8R8A8;
        mbitmap3 = MBitmapFactory.createMBitmapFromFile(s2, i4, j4, k4);
        if (mbitmap3 != null)
        {
            break MISSING_BLOCK_LABEL_700;
        }
        if (mbitmap1 == null) goto _L16; else goto _L15
_L15:
        mbitmap1.recycle();
_L16:
        if (mbitmap2 == null) goto _L8; else goto _L17
_L17:
        mbitmap2.recycle();
          goto _L8
_L14:
        i4 = mrect1.height();
          goto _L18
        j4 = mrect1.width();
          goto _L19
        if (i3 == 0)
        {
            break MISSING_BLOCK_LABEL_714;
        }
        mbitmap3 = mbitmap3.rotate(i3);
        mbitmap4 = MBitmapFactory.createMBitmapBlank(mbitmap3.getWidth(), mbitmap3.getHeight(), mbitmap3.getColorSpace());
        WorkShopUtils.copyBitmap(mbitmap4, mbitmap3, new MPoint(0, 0));
        if (mbitmap4 != null)
        {
            break MISSING_BLOCK_LABEL_789;
        }
        if (mbitmap1 == null)
        {
            break MISSING_BLOCK_LABEL_766;
        }
        mbitmap1.recycle();
        if (mbitmap2 == null) goto _L21; else goto _L20
_L20:
        mbitmap2.recycle();
_L21:
        if (mbitmap3 == null) goto _L8; else goto _L22
_L22:
        mbitmap3.recycle();
          goto _L8
        if (i3 == 0)
        {
            break MISSING_BLOCK_LABEL_803;
        }
        mbitmap4 = mbitmap4.rotate(i3);
        ((MRect)mCollage.getFitoutImgRects().get(mLongClickedRectIndex)).set(mrect);
        mCollage.getOriFitoutRect(mLongClickedRectIndex).set(mrect);
        ((MBitmap)mCollage.getBitmapList().get(mLongClickedRectIndex)).recycle();
        mCollage.getBitmapList().remove(mLongClickedRectIndex);
        mCollage.getBitmapList().add(mLongClickedRectIndex, mbitmap1);
        ((MBitmap)mCollage.getOriBitmapList().get(mLongClickedRectIndex)).recycle();
        mCollage.getOriBitmapList().remove(mLongClickedRectIndex);
        mCollage.getOriBitmapList().add(mLongClickedRectIndex, mbitmap2);
        mCollage.setDis2ImgRect(mLongClickedRectIndex);
        ((MBitmap)mCollage.getBitmapList().get(i)).recycle();
        mCollage.getBitmapList().remove(i);
        mCollage.getBitmapList().add(i, mbitmap3);
        ((MBitmap)mCollage.getOriBitmapList().get(i)).recycle();
        mCollage.getOriBitmapList().remove(i);
        mCollage.getOriBitmapList().add(i, mbitmap4);
        mCollage.setDis2ImgRect(i);
        doDraw4Collage(mCollage.getTemplateThumbnailRects(), mCollage.getBitmapList());
        mViewContentChanged = true;
        mIsOriginalTemp = false;
_L2:
        mLongClickedRectIndex = -1;
          goto _L8
_L3:
        if (l != 0) goto _L24; else goto _L23
        int j3;
        String s2;
        int i4;
        int j4;
        MBitmap mbitmap3;
        MBitmap mbitmap4;
        if (i3 == 0)
        {
            j3 = k2;
        } else
        {
            j3 = l2;
        }
        if (i3 != 0)
        {
            l2 = k2;
        }
          goto _L25
    }

    private void updateMediaStore(String s)
    {
        File file = new File(s);
        ContentResolver contentresolver = mCollage.getContentResolver();
        if (file != null && contentresolver != null)
        {
            String s1 = file.getName();
            int i = s1.lastIndexOf(".");
            if (i >= 0)
            {
                ContentValues contentvalues = new ContentValues(6);
                contentvalues.put("title", s1.substring(0, i));
                contentvalues.put("_display_name", file.getName());
                contentvalues.put("_data", file.getPath());
                contentvalues.put("date_modified", Long.valueOf(System.currentTimeMillis() / 1000L));
                contentvalues.put("_size", Long.valueOf(file.length()));
                contentvalues.put("mime_type", "image/jpeg");
                contentresolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentvalues);
                return;
            }
        }
    }

    private boolean zoomBeginOperation()
    {
        if (mIsPanBegin)
        {
            mIsPanBegin = false;
            if (mIsPanStarted)
            {
                mOnPanGestureListener.OnPanEnd();
                mIsPanStarted = false;
            }
        }
        if (mOnZoomGestureListener.OnZoomBegin() == 0)
        {
            mIsZoomStarted = true;
            return true;
        } else
        {
            return false;
        }
    }

    public boolean OnMultiTouch(MultiTouchDetector multitouchdetector)
    {
        if (!mCollage.isThreadRunning()) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (MultiTouchDetector.TouchMode.Zoom == multitouchdetector.getTouchMode())
        {
            if (!mIsZoomStarted && mOnZoomGestureListener != null)
            {
                zoomBeginOperation();
            }
            if (!mIsZoomStarted || mOnZoomGestureListener == null)
            {
                break; /* Loop/switch isn't completed */
            }
            float f = multitouchdetector.getPreviousSpan();
            multitouchdetector.getCurrentSpan();
            if (Float.compare(f, 0.0F) != 0);
            if (mOnZoomGestureListener.OnZoom(multitouchdetector.getZoomScale()) != 0)
            {
                return false;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (MultiTouchDetector.TouchMode.Rotate != multitouchdetector.getTouchMode())
        {
            break; /* Loop/switch isn't completed */
        }
        if (!mIsRotateStarted && mOnRotateGestureListener != null)
        {
            rotateBeginOperation();
        }
        if (!mIsRotateStarted || mOnRotateGestureListener == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (mOnRotateGestureListener.OnRotate(multitouchdetector.getAngle()) != 0)
        {
            return false;
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public boolean OnMultiTouchBegin(MultiTouchDetector multitouchdetector)
    {
        if (mCollage.isThreadRunning())
        {
            return true;
        }
        if (MultiTouchDetector.TouchMode.Rotate == multitouchdetector.getTouchMode())
        {
            if (mOnRotateGestureListener != null)
            {
                return rotateBeginOperation();
            }
        } else
        if (MultiTouchDetector.TouchMode.Zoom == multitouchdetector.getTouchMode() && mOnZoomGestureListener != null)
        {
            return zoomBeginOperation();
        }
        return false;
    }

    public void OnMultiTouchEnd(MultiTouchDetector multitouchdetector)
    {
        if (!mCollage.isThreadRunning())
        {
            if (mIsZoomStarted)
            {
                mIsZoomStarted = false;
                if (mOnZoomGestureListener != null)
                {
                    mOnZoomGestureListener.OnZoomEnd();
                }
            }
            if (mIsRotateStarted)
            {
                mIsRotateStarted = false;
                if (mOnRotateGestureListener != null)
                {
                    mOnRotateGestureListener.OnRotateEnd();
                    return;
                }
            }
        }
    }

    public void changeTemplete()
    {
        mViewContentChanged = false;
        mIsOriginalTemp = true;
    }

    public int doDraw4Collage(ArrayList arraylist, ArrayList arraylist1)
    {
        this;
        JVM INSTR monitorenter ;
        Surface surface = mSurface;
        if (surface == null) goto _L2; else goto _L1
_L1:
        MRect mrect;
        int j;
        int k;
        MRect amrect[];
        mrect = new MRect();
        MRect mrect1 = mCollage.getTemplateDirtyRect();
        mrect.set(mrect1.left, mrect1.top, mrect1.right, mrect1.bottom);
        j = mCollage.getOffsetX();
        k = mCollage.getOffsetY();
        amrect = new MRect[arraylist.size()];
        int l = 0;
_L4:
        if (l >= amrect.length)
        {
            break; /* Loop/switch isn't completed */
        }
        MRect mrect3 = (MRect)arraylist.get(l);
        amrect[l] = new MRect(j + mrect3.left, k + mrect3.top, j + mrect3.right, k + mrect3.bottom);
        l++;
        if (true) goto _L4; else goto _L3
_L3:
        MRect amrect1[] = new MRect[amrect.length];
        int i1 = 0;
_L6:
        if (i1 >= amrect.length)
        {
            break; /* Loop/switch isn't completed */
        }
        MRect mrect2 = (MRect)mCollage.getDisRects2Img().get(i1);
        amrect1[i1] = new MRect(mrect2.left, mrect2.top, mrect2.right, mrect2.bottom);
        i1++;
        if (true) goto _L6; else goto _L5
_L5:
        int j1;
        MBitmap ambitmap[] = new MBitmap[amrect.length];
        j1 = WorkShopUtils.doDraw4Collage(mSurface, mrect, amrect1, amrect, (MBitmap[])arraylist1.toArray(ambitmap));
        int i = j1;
_L8:
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception1;
        exception1;
        exception1.printStackTrace();
        i = -1;
        continue; /* Loop/switch isn't completed */
_L2:
        i = -1;
        if (true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        throw exception;
    }

    public void drawPart(int i)
    {
        refreshPart(i);
        mViewContentChanged = true;
        mIsOriginalTemp = false;
    }

    public Point getZoomFirstPointerPos()
    {
        return mScaleGestureDetector.getZoomFirstPointerPos();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onLongPress(MotionEvent motionevent)
    {
        mX = (int)motionevent.getX();
        mY = (int)motionevent.getY();
        int i;
        if (mPointerNum <= 1)
        {
            if ((i = mCollage.getCurrRectIndex(mX, mY)) != -1)
            {
                mLongPress = true;
                mLongClickedRectIndex = i;
                drawPressRect(i);
                return;
            }
        }
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        if (!mCollage.isThreadRunning()) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (motionevent.getAction() == 0)
        {
            resetState();
        }
        mPointerNum = motionevent.getPointerCount();
        if (mIsZoomStarted || mIsRotateStarted || mOnPanGestureListener == null) goto _L4; else goto _L3
_L3:
        0xff & motionevent.getAction();
        JVM INSTR tableswitch 0 6: default 104
    //                   0 147
    //                   1 308
    //                   2 173
    //                   3 308
    //                   4 104
    //                   5 461
    //                   6 448;
           goto _L5 _L6 _L7 _L8 _L7 _L5 _L9 _L10
_L5:
        break; /* Loop/switch isn't completed */
_L9:
        break MISSING_BLOCK_LABEL_461;
_L4:
        Exception exception1;
        try
        {
            if (mScaleGestureDetector != null)
            {
                mScaleGestureDetector.onTouchEvent(motionevent);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        if (mGestureDetector == null) goto _L1; else goto _L11
_L11:
        mGestureDetector.onTouchEvent(motionevent);
        return true;
        exception1;
        exception1.printStackTrace();
        return true;
_L6:
        mIsPanBegin = true;
        mDownX = (int)motionevent.getX();
        mDownY = (int)motionevent.getY();
          goto _L4
_L8:
        if (!mLongPress)
        {
            if (!mIsPanBegin && motionevent.getPointerCount() < 2 && mOnPanGestureListener.OnPanBegin((int)motionevent.getX(), (int)motionevent.getY()) == 0)
            {
                mIsPanBegin = true;
                mIsPanStarted = true;
            } else
            if (mIsPanBegin && motionevent.getPointerCount() < 2)
            {
label0:
                {
                    if (!mIsPanStarted)
                    {
                        if (mOnPanGestureListener.OnPanBegin(mDownX, mDownY) != 0)
                        {
                            break label0;
                        }
                        mIsPanStarted = true;
                    }
                    mOnPanGestureListener.OnPan((int)motionevent.getX(), (int)motionevent.getY());
                }
            }
        }
          goto _L4
        mIsPanBegin = false;
          goto _L4
_L7:
        if (!mLongPress)
        {
            if (mIsPanBegin)
            {
                mIsPanBegin = false;
                if (mIsPanStarted)
                {
                    mOnPanGestureListener.OnPanEnd();
                    mIsPanStarted = false;
                }
            }
        } else
        {
            mX = (int)motionevent.getX();
            mY = (int)motionevent.getY();
            int i = mCollage.getCurrRectIndex(mX, mY);
            if (i == mLongClickedRectIndex || i == -1)
            {
                refreshPart(mLongClickedRectIndex);
                mLongPress = false;
                mLongClickedRectIndex = -1;
            } else
            {
                if (i != -1)
                {
                    switchPictures(i);
                }
                mLongPress = false;
                mLongClickedRectIndex = -1;
            }
        }
          goto _L4
_L10:
        Log.e("po", "ACTION_POINTER_UP");
          goto _L4
        Log.e("po", "ACTION_POINTER_DOWN");
        if (mLongPress)
        {
            mLongPress = false;
            if (mLongClickedRectIndex != -1)
            {
                refreshPart(mLongClickedRectIndex);
            }
            mLongClickedRectIndex = -1;
        }
          goto _L4
    }

    public void resetState()
    {
        mIsRotateStarted = false;
        mIsZoomStarted = false;
        mIsPanBegin = false;
        mIsPanStarted = false;
        if (mScaleGestureDetector != null)
        {
            mScaleGestureDetector.resetState();
        }
    }

    public String save(ArrayList arraylist, ArrayList arraylist1, boolean flag)
    {
        if (arraylist == null || arraylist.size() == 0 || arraylist1 == null || arraylist1.size() == 0)
        {
            return null;
        }
        if (!mViewContentChanged && isCurrentTempSaved())
        {
            if (!flag)
            {
                WorkShop.toastForSave(12, mCollage);
            }
            return mCurrentFilePath;
        }
        MRect mrect = mCollage.getTemplateDirtyRect();
        if (mrect == null || mrect.height() < 1 || mrect.width() < 1)
        {
            return null;
        }
        MBitmap mbitmap = MBitmapFactory.createMBitmapBlank(mrect.width(), mrect.height(), MColorSpace.MPAF_RGB32_B8G8R8A8);
        mbitmap.fillColor(0xffffff, new MRect(0, 0, mrect.width(), mrect.height()), null, 100);
        int i = arraylist.size();
        int j = arraylist1.size();
        for (int k = 0; k < i && k < j; k++)
        {
            MRect mrect1 = (MRect)arraylist.get(k);
            WorkShopUtils.copyBitmap(mbitmap, (MBitmap)arraylist1.get(k), new MPoint(mrect1.left, mrect1.top), (MRect)mCollage.getDisRects2Img().get(k));
        }

        mCurrentFilePath = (new StringBuilder()).append(Config.COLLAGE_DIR).append(File.separator).append("Collage_").append((new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss")).format(Calendar.getInstance().getTime())).append(".jpg").toString();
        if (Config.checkSaveDir())
        {
            int l = mbitmap.save(2, mCurrentFilePath, 100);
            if (mIsOriginalTemp)
            {
                addCurrentRecordById(mCollage.getType());
            }
            mViewContentChanged = false;
            if (l == 0)
            {
                if (!flag)
                {
                    WorkShop.toastForSave(10, mCollage);
                }
                updateMediaStore(mCurrentFilePath);
            } else
            {
                WorkShop.toastForSave(11, mCollage);
                mCurrentFilePath = null;
            }
        }
        mbitmap.recycle();
        return mCurrentFilePath;
    }

    public void setOnPanGestureListener(ZoomPanController.OnPanGestureListener onpangesturelistener)
    {
        mOnPanGestureListener = onpangesturelistener;
    }

    public void setOnRotateGestureListener(ZoomPanController.OnRotateGestureListener onrotategesturelistener)
    {
        mOnRotateGestureListener = onrotategesturelistener;
    }

    public void setOnZoomGestureListener(ZoomPanController.OnZoomGestureListener onzoomgesturelistener)
    {
        mOnZoomGestureListener = onzoomgesturelistener;
    }

    public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        mFirstTime = true;
    }

    public void surfaceCreated(SurfaceHolder surfaceholder)
    {
        int i;
        if (mCollage.getType() == 0)
        {
            i = 1;
        } else
        {
            i = mCollage.getType();
        }
        mCollage.changeTemplate(mCollage.getPicNum(), i, true);
        mFirstTime = true;
    }

    public void surfaceDestroyed(SurfaceHolder surfaceholder)
    {
        mFirstTime = true;
    }
}
