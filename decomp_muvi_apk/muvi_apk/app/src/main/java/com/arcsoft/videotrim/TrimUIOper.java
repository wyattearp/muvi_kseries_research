// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.arcsoft.videotrim.Utils.ThumbManagerList;
import com.arcsoft.videotrim.Utils.UtilFunc;
import java.util.ArrayList;
import java.util.List;
import powermobia.videoeditor.base.MRange;
import powermobia.videoeditor.clip.MClip;

// Referenced classes of package com.arcsoft.videotrim:
//            videoTrimActivity

public class TrimUIOper
{
    private class ThumbDecodingRunnable
        implements Runnable
    {

        final TrimUIOper this$0;

        public void run()
        {
            Object obj = m_ObjDecodeFrameSync;
            obj;
            JVM INSTR monitorenter ;
_L5:
            if (!m_IsDecodeThreadRuning) goto _L2; else goto _L1
_L1:
            int i = getCurDecodedIdentifier();
            if (i >= m_VideoDuration || i < 0)
            {
                break MISSING_BLOCK_LABEL_145;
            }
            Bitmap bitmap = (Bitmap)UtilFunc.getClipThumbnail(m_VideoClip, i, m_iThumbWidth, m_iThumbHeight, false, false);
            if (bitmap == null)
            {
                break MISSING_BLOCK_LABEL_131;
            }
            Message message = new Message();
            message.what = 1;
            message.arg1 = i;
            message.obj = bitmap;
            setDecodedBitmap(i, bitmap);
            mhandler.sendMessage(message);
            try
            {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedexception) { }
            continue; /* Loop/switch isn't completed */
            int j = 0;
_L3:
            if (j >= 10)
            {
                continue; /* Loop/switch isn't completed */
            }
            boolean flag;
            Thread.sleep(100L);
            flag = m_IsDecodeThreadRuning;
            if (!flag)
            {
                continue; /* Loop/switch isn't completed */
            }
            j++;
            if (true) goto _L3; else goto _L2
_L2:
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
            InterruptedException interruptedexception1;
            interruptedexception1;
            if (true) goto _L5; else goto _L4
_L4:
        }

        private ThumbDecodingRunnable()
        {
            this$0 = TrimUIOper.this;
            super();
        }

    }

    private class TouchParentLayoutListener
        implements android.view.View.OnTouchListener
    {

        int TouchX;
        boolean isControlIndicator;
        boolean isMoving;
        boolean isZoomMode;
        final TrimUIOper this$0;
        int touchDownPos;
        float touchDownSpace;
        long touchDownTime;
        int touchMovePos;
        float touchUpSpace;
        long touchUpTime;

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            if (!m_IsSeeking) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            int i;
            TouchX = (int)motionevent.getX();
            i = 0xff & motionevent.getAction();
            if (i != 0) goto _L4; else goto _L3
_L3:
            touchDownTime = System.currentTimeMillis();
            isMoving = false;
            touchDownPos = TouchX;
            touchMovePos = TouchX;
            if (TouchX <= m_TrimMainLayoutMarginLeftPos + m_TrimLRViewWidth || TouchX >= m_TrimMainLayoutMarginRightPos) goto _L6; else goto _L5
_L5:
            isControlIndicator = true;
            m_bTouchPlayerStatus = getPlayerStatus();
            setPlayerStatus(false);
_L7:
            return true;
_L6:
            isControlIndicator = false;
            return false;
_L4:
            if (i == 5)
            {
                isZoomMode = true;
                touchDownSpace = spacing(motionevent);
            } else
            {
label0:
                {
                    if (i != 6)
                    {
                        break label0;
                    }
                    touchUpSpace = spacing(motionevent);
                }
            }
              goto _L7
            if (i != 2) goto _L9; else goto _L8
_L8:
            isMoving = true;
            if (!isControlIndicator) goto _L1; else goto _L10
_L10:
            if (isZoomMode)
            {
                return true;
            }
            int l = TouchX - touchMovePos;
            TouchParentLayoutMoving(l, isMoving);
            touchMovePos = TouchX;
              goto _L7
_L9:
            if (i != 1) goto _L7; else goto _L11
_L11:
            touchUpTime = System.currentTimeMillis();
            isMoving = false;
            if (!isControlIndicator) goto _L1; else goto _L12
_L12:
            if (!isZoomMode) goto _L14; else goto _L13
_L13:
            isZoomMode = false;
            if (touchDownSpace - touchUpSpace <= 100F) goto _L16; else goto _L15
_L15:
            UtilFunc.Log("TrimUIOper", "TouchParentLayoutListener --- > ZOOM: detachTrim()");
            detachTrim();
_L17:
            return true;
_L16:
            if (touchUpSpace - touchDownSpace > 100F)
            {
                UtilFunc.Log("TrimUIOper", "TouchParentLayoutListener --- > ZOOM: attachTrim()");
                attachTrim();
            }
            if (true) goto _L17; else goto _L14
_L14:
            if (touchDownPos - TouchX > -30 && touchDownPos - TouchX < 30 && (float)Math.abs(touchUpTime - touchDownTime) < 500F)
            {
                UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchParentLayoutListener --- > onClick, moveToCenterByPos: ").append(TouchX).toString());
                int k = TouchX - m_TrimMainLayoutMarginLeftPos - m_TrimLRViewWidth;
                moveToCenterByPos(TouchX);
                notifySeekToValueByPos(k);
                return true;
            }
            int j = TouchX - touchMovePos;
            UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchParentLayoutListener --- > ACTION_UP, TouchParentLayoutMoving: ").append(TouchX).toString());
            TouchParentLayoutMoving(j, isMoving);
              goto _L7
        }

        private TouchParentLayoutListener()
        {
            this$0 = TrimUIOper.this;
            super();
            TouchX = 0;
            isControlIndicator = false;
            touchDownPos = 0;
            touchMovePos = 0;
            isZoomMode = false;
            touchDownSpace = 0.0F;
            touchUpSpace = 0.0F;
            isMoving = false;
        }

    }

    private class TouchTrimIndicateListener
        implements android.view.View.OnTouchListener
    {

        int TouchX;
        boolean isControlIndicator;
        boolean isControlIndicatorEnd;
        boolean isControlIndicatorStart;
        final TrimUIOper this$0;

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            if (m_IsSeeking)
            {
                return false;
            }
            TouchX = (int)motionevent.getX();
            if (motionevent.getAction() != 0) goto _L2; else goto _L1
_L1:
            if (TouchX - (mTrimLeftPos + m_TrimLRViewWidth) < 30 && TouchX - mTrimLeftPos > -30)
            {
                m_TrimLeftImageView.setBackgroundResource(0x7f0202a3);
                m_TrimLeftMaskView.setBackgroundResource(0x7f0202a0);
                isControlIndicatorStart = true;
            } else
            {
                isControlIndicatorStart = false;
            }
            if (TouchX - mTrimRightPos < 30 && TouchX - (mTrimRightPos - m_TrimLRViewWidth) > -30)
            {
                m_TrimRightImageView.setBackgroundResource(0x7f0202a7);
                m_TrimRightMaskView.setBackgroundResource(0x7f0202a0);
                isControlIndicatorEnd = true;
            } else
            {
                isControlIndicatorEnd = false;
            }
            if (!isControlIndicatorStart && !isControlIndicatorEnd)
            {
                isControlIndicator = false;
                return false;
            }
            isControlIndicator = true;
            setPlayerStatus(false);
            UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchTrimIndicateListener --- > ACTION_DOWN: ").append(TouchX).toString());
_L4:
            return true;
_L2:
            if (motionevent.getAction() == 2)
            {
                if (!isControlIndicator)
                {
                    return false;
                }
                TouchX = (int)motionevent.getX();
                if (isControlIndicatorStart && TouchX > 0 && TouchX < mTrimRightPos - m_iThumbWidth / 2)
                {
                    android.widget.AbsoluteLayout.LayoutParams layoutparams2 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimLeftImageView.getLayoutParams();
                    layoutparams2.x = layoutparams2.x + (TouchX - mTrimLeftPos);
                    m_TrimLeftImageView.setLayoutParams(layoutparams2);
                    mTrimLeftPos = TouchX;
                    android.widget.AbsoluteLayout.LayoutParams layoutparams3 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimLeftMaskView.getLayoutParams();
                    layoutparams3.x = m_TrimLRViewWidth;
                    layoutparams3.width = mTrimLeftPos;
                    m_TrimLeftMaskView.setLayoutParams(layoutparams3);
                }
                if (isControlIndicatorEnd)
                {
                    int k = m_TrimMainLayoutWidth + m_TrimLRViewWidth;
                    if (TouchX - m_iThumbWidth / 2 > mTrimLeftPos && TouchX < k)
                    {
                        android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimRightImageView.getLayoutParams();
                        layoutparams.x = layoutparams.x + (TouchX - mTrimRightPos);
                        m_TrimRightImageView.setLayoutParams(layoutparams);
                        mTrimRightPos = TouchX;
                        android.widget.AbsoluteLayout.LayoutParams layoutparams1 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimRightMaskView.getLayoutParams();
                        layoutparams1.x = mTrimRightPos;
                        layoutparams1.width = (m_TrimMainLayoutWidth - mTrimRightPos) + m_TrimLRViewWidth;
                        m_TrimRightMaskView.setLayoutParams(layoutparams1);
                    }
                }
            } else
            if (motionevent.getAction() == 1)
            {
                if (!isControlIndicator)
                {
                    return false;
                }
                isControlIndicator = false;
                if (isControlIndicatorStart)
                {
                    m_TrimLeftImageView.setBackgroundResource(0x7f0202a2);
                    m_TrimLeftMaskView.setBackgroundResource(0x7f0202a4);
                    int j = m_TrimMainLayoutMarginLeftPos + mTrimLeftPos + m_TrimLRViewWidth;
                    moveToCenterByPos(j);
                    long l1 = ((long)m_VideoDuration * (long)mTrimLeftPos) / (long)m_TrimMainLayoutWidth;
                    mTrimLeftValue = (int)l1;
                    if (mTrimUIOperListener != null)
                    {
                        mTrimUIOperListener.notifyValueChanged(true, mTrimLeftValue);
                    }
                }
                if (isControlIndicatorEnd)
                {
                    m_TrimRightImageView.setBackgroundResource(0x7f0202a6);
                    m_TrimRightMaskView.setBackgroundResource(0x7f0202a4);
                    int i = m_TrimMainLayoutMarginLeftPos + mTrimRightPos;
                    moveToCenterByPos(i);
                    long l = ((long)m_VideoDuration * (long)(mTrimRightPos - m_TrimLRViewWidth)) / (long)m_TrimMainLayoutWidth;
                    mTrimRightValue = (int)l;
                    if (mTrimUIOperListener != null)
                    {
                        mTrimUIOperListener.notifyValueChanged(false, mTrimRightValue);
                    }
                }
                UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("TouchTrimIndicateListener --- > ACTION_UP: ").append(TouchX).toString());
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        private TouchTrimIndicateListener()
        {
            this$0 = TrimUIOper.this;
            super();
            isControlIndicator = false;
            isControlIndicatorStart = false;
            isControlIndicatorEnd = false;
        }

    }

    public static interface TrimUIOperListener
    {

        public abstract void notifyCurScaleLevelChanged();

        public abstract void notifyScaleLevelChanged(int i, int j);

        public abstract void notifySeekToValue(int i);

        public abstract void notifyValueChanged(boolean flag, int i);

        public abstract void onTrimThumbManagerList(ThumbManagerList thumbmanagerlist);
    }


    private static final float ATTACH_DETCH_TRIM_SPACE = 100F;
    private static final int INDICATEMOVE_EFFECTIVE_DIS = 30;
    private static final int MAX_Identifier_Approximate = 256;
    private static final int MSG_SEEKTOVALUE_WHAT = 0;
    private static final int MSG_THUMB_DECODETHREAD = 1;
    private static final int SPACE_BETWEEN_THUMBNAIL = 6;
    private static final int THUMBNAIL_CACHE_COUNT_LIMIT = 200;
    private static final int THUMBNAIL_CACHE_TIME_MIN = 1000;
    private static final int THUMBNAIL_CLICK_DISTANCE = 30;
    private static final float THUMB_CLICK_TIME_OFFSET = 500F;
    private final String LOG_TAG = "TrimUIOper";
    private BitmapDrawable mDrawableMainFrame;
    private BitmapDrawable mDrawableTrimBar;
    private final Handler mSeekToValueHandler = new Handler() {

        final TrimUIOper this$0;

        public void handleMessage(Message message)
        {
            removeMessages(0);
            if (message.what == 0 && mTrimUIOperListener != null)
            {
                mTrimUIOperListener.notifySeekToValue(message.arg1);
            }
        }

            
            {
                this$0 = TrimUIOper.this;
                super();
            }
    };
    private int mTrimLeftPos;
    private int mTrimLeftValue;
    private int mTrimRightPos;
    private int mTrimRightValue;
    private TrimUIOperListener mTrimUIOperListener;
    private Activity m_Activity;
    private int m_CurLevelScale;
    private int m_DisplayWidth;
    private int m_IdentifierStep;
    private boolean m_IsDecodeThreadRuning;
    private boolean m_IsSeeking;
    private int m_MaxLevelScale;
    private int m_MinLevelScale;
    private final Object m_ObjDecodeFrameSync = new Object();
    private Thread m_ThreadThumbDecode;
    private List m_ThumbImageViewList;
    private ThumbManagerList m_ThumbManagerList;
    private AbsoluteLayout m_TrimIndicateOperLayout;
    private int m_TrimLRViewWidth;
    private ImageView m_TrimLeftImageView;
    private ImageView m_TrimLeftMaskView;
    private ImageView m_TrimLocateBar;
    private int m_TrimMainLayoutMarginLeftPos;
    private int m_TrimMainLayoutMarginRightPos;
    private int m_TrimMainLayoutWidth;
    private AbsoluteLayout m_TrimOperMainLayout;
    private AbsoluteLayout m_TrimParentLayout;
    private ImageView m_TrimRightImageView;
    private ImageView m_TrimRightMaskView;
    private int m_TrimThumbBaseCount;
    private int m_TrimThumbCount;
    private AbsoluteLayout m_TrimThumbnailOperLayout;
    private final android.view.animation.Animation.AnimationListener m_TrimZoomAnimationListener = new android.view.animation.Animation.AnimationListener() {

        final TrimUIOper this$0;

        public void onAnimationEnd(Animation animation)
        {
            TrimTachChangeZoomByLevel(m_CurLevelScale);
        }

        public void onAnimationRepeat(Animation animation)
        {
        }

        public void onAnimationStart(Animation animation)
        {
            setPlayerStatus(false);
        }

            
            {
                this$0 = TrimUIOper.this;
                super();
            }
    };
    private MClip m_VideoClip;
    private int m_VideoDuration;
    private boolean m_bTouchPlayerStatus;
    private int m_iThumbHeight;
    private int m_iThumbWidth;
    Handler mhandler;

    public TrimUIOper(Activity activity, MClip mclip)
    {
        m_VideoClip = null;
        m_VideoDuration = 0;
        m_TrimLocateBar = null;
        m_TrimLRViewWidth = 0;
        mDrawableMainFrame = null;
        mDrawableTrimBar = null;
        m_iThumbWidth = 0;
        m_iThumbHeight = 0;
        mTrimLeftValue = 0;
        mTrimRightValue = 0;
        mTrimLeftPos = 0;
        mTrimRightPos = 0;
        m_TrimThumbCount = 0;
        m_TrimThumbBaseCount = 0;
        m_IdentifierStep = 0;
        m_TrimMainLayoutMarginLeftPos = 0;
        m_TrimMainLayoutMarginRightPos = 0;
        m_TrimMainLayoutWidth = 0;
        m_DisplayWidth = 0;
        m_ThumbImageViewList = null;
        m_ThumbManagerList = null;
        m_ThreadThumbDecode = null;
        m_IsDecodeThreadRuning = true;
        m_IsSeeking = false;
        m_bTouchPlayerStatus = false;
        mTrimUIOperListener = null;
        mhandler = new Handler() {

            final TrimUIOper this$0;

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                if (m_IsDecodeThreadRuning)
                {
                    int i = message.arg1;
                    onBitmapDecoded(i);
                    return;
                }
                if (true) goto _L1; else goto _L3
_L3:
            }

            
            {
                this$0 = TrimUIOper.this;
                super();
            }
        };
        m_Activity = activity;
        m_VideoClip = mclip;
    }

    private void AddEmptyThumbViewToLayout(int i)
    {
        if (i > 0)
        {
            int j = 0;
            while (j < i) 
            {
                ImageView imageview = new ImageView(m_Activity);
                int k = j * (6 + m_iThumbWidth);
                android.widget.AbsoluteLayout.LayoutParams layoutparams = new android.widget.AbsoluteLayout.LayoutParams(m_iThumbWidth, m_iThumbHeight, k, 0);
                imageview.setVisibility(0);
                com.arcsoft.videotrim.Utils.ThumbManagerList.ThumbLinkList thumblinklist = m_ThumbManagerList.find(j * m_IdentifierStep);
                Bitmap bitmap;
                if (thumblinklist != null)
                {
                    bitmap = m_ThumbManagerList.getThumbBitmap(thumblinklist);
                } else
                {
                    m_ThumbManagerList.insert(j * m_IdentifierStep);
                    bitmap = null;
                }
                if (bitmap == null)
                {
                    imageview.setImageBitmap(BitmapFactory.decodeResource(m_Activity.getResources(), 0x7f0202a9));
                    imageview.setTag("false");
                } else
                {
                    imageview.setImageBitmap(bitmap);
                    imageview.setTag("true");
                }
                m_ThumbImageViewList.add(j, imageview);
                m_TrimThumbnailOperLayout.addView(imageview, layoutparams);
                j++;
            }
        }
    }

    private void TouchParentLayoutMoving(int i, boolean flag)
    {
        boolean flag1 = true;
        if (i >= 0)
        {
            int k = m_TrimMainLayoutMarginLeftPos + m_TrimLRViewWidth;
            if (k + i >= m_DisplayWidth / 2)
            {
                moveToCenterByPos(k);
                flag1 = false;
                if (!flag)
                {
                    notifySeekToValue(0);
                }
            }
        }
        if (i <= 0)
        {
            int j = m_TrimMainLayoutMarginRightPos;
            if (j + i <= m_DisplayWidth / 2)
            {
                moveToCenterByPos(j);
                flag1 = false;
                if (!flag)
                {
                    notifySeekToValue(m_VideoDuration);
                }
            }
        }
        if (flag1 && m_TrimOperMainLayout != null)
        {
            android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimOperMainLayout.getLayoutParams();
            layoutparams.x = i + layoutparams.x;
            m_TrimOperMainLayout.setLayoutParams(layoutparams);
            m_TrimMainLayoutMarginLeftPos = i + m_TrimMainLayoutMarginLeftPos;
            m_TrimMainLayoutMarginRightPos = i + m_TrimMainLayoutMarginRightPos;
            if (!flag)
            {
                notifySeekToValueByPos(m_DisplayWidth / 2 - m_TrimMainLayoutMarginLeftPos - m_TrimLRViewWidth);
            }
        }
    }

    private void TrimTachChangeZoomByLevel(int i)
    {
        int l;
        if (m_CurLevelScale > m_MaxLevelScale || m_CurLevelScale < m_MinLevelScale)
        {
            return;
        }
        int j = m_TrimThumbBaseCount;
        for (; i > 0; i--)
        {
            j *= 2;
        }

        m_TrimThumbCount = j;
        m_TrimMainLayoutWidth = -6 + m_TrimThumbCount * (6 + m_iThumbWidth);
        int k = getCurPlayTime();
        l = (int)(((long)m_TrimMainLayoutWidth * (long)k) / (long)m_VideoDuration);
        mTrimLeftPos = (int)(((long)m_TrimMainLayoutWidth * (long)mTrimLeftValue) / (long)m_VideoDuration);
        mTrimRightPos = (int)(((long)m_TrimMainLayoutWidth * (long)mTrimRightValue) / (long)m_VideoDuration) + m_TrimLRViewWidth;
        if (l > 0) goto _L2; else goto _L1
_L1:
        l = 0;
_L4:
        refreshLeftRightView();
        m_TrimMainLayoutMarginLeftPos = m_DisplayWidth / 2 - l - m_TrimLRViewWidth;
        m_TrimMainLayoutMarginRightPos = m_TrimMainLayoutMarginLeftPos + m_TrimLRViewWidth + m_TrimMainLayoutWidth;
        android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimOperMainLayout.getLayoutParams();
        layoutparams.x = m_TrimMainLayoutMarginLeftPos;
        layoutparams.width = m_TrimMainLayoutWidth + 2 * m_TrimLRViewWidth;
        m_TrimOperMainLayout.setLayoutParams(layoutparams);
        android.widget.AbsoluteLayout.LayoutParams layoutparams1 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimThumbnailOperLayout.getLayoutParams();
        layoutparams1.x = m_TrimLRViewWidth;
        m_TrimThumbnailOperLayout.setLayoutParams(layoutparams1);
        clearAllThumbnailView();
        exitDecodingThread();
        startDecodeThumbnail();
        return;
_L2:
        if (l >= m_TrimMainLayoutWidth)
        {
            l = m_TrimMainLayoutWidth + m_TrimLRViewWidth;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void attachTrim()
    {
        if (m_CurLevelScale >= m_MaxLevelScale)
        {
            Toast.makeText(m_Activity, m_Activity.getString(0x7f0b01ca), 0).show();
        } else
        {
            m_CurLevelScale = 1 + m_CurLevelScale;
            if (mTrimUIOperListener != null)
            {
                mTrimUIOperListener.notifyCurScaleLevelChanged();
            }
            AnimationSet animationset = getAnimationSet(1.0F, 2.0F);
            if (animationset != null)
            {
                animationset.setAnimationListener(m_TrimZoomAnimationListener);
                m_TrimOperMainLayout.startAnimation(animationset);
                return;
            }
        }
    }

    private void clearAllThumbnailView()
    {
        if (m_ThumbImageViewList != null)
        {
            m_ThumbImageViewList.clear();
        }
        if (m_TrimThumbnailOperLayout != null)
        {
            m_TrimThumbnailOperLayout.removeAllViews();
        }
    }

    private void detachTrim()
    {
        if (m_CurLevelScale <= m_MinLevelScale)
        {
            Toast.makeText(m_Activity, m_Activity.getString(0x7f0b01cb), 0).show();
        } else
        {
            m_CurLevelScale = -1 + m_CurLevelScale;
            if (mTrimUIOperListener != null)
            {
                mTrimUIOperListener.notifyCurScaleLevelChanged();
            }
            AnimationSet animationset = getAnimationSet(1.0F, 0.5F);
            if (animationset != null)
            {
                animationset.setAnimationListener(m_TrimZoomAnimationListener);
                m_TrimOperMainLayout.startAnimation(animationset);
                return;
            }
        }
    }

    private AnimationSet getAnimationSet(float f, float f1)
    {
        AnimationSet animationset = new AnimationSet(true);
        AlphaAnimation alphaanimation = new AlphaAnimation(1.0F, 0.3F);
        alphaanimation.setDuration(700L);
        animationset.addAnimation(alphaanimation);
        int i = getCurPlayTime();
        int j = (int)(((long)m_TrimMainLayoutWidth * (long)i) / (long)m_VideoDuration);
        float f2 = (float)(j + m_TrimLRViewWidth) / (float)(m_TrimMainLayoutWidth + 2 * m_TrimLRViewWidth);
        UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("getAnimationSet --- > fromX: ").append(f).append(", toX: ").append(f1).append(", pivotXValue: ").append(f2).append(", curPlayPos: ").append(j).append(", m_TrimLayoutWidth: ").append(m_TrimMainLayoutWidth).append(", getWidth: ").append(m_TrimOperMainLayout.getWidth()).toString());
        ScaleAnimation scaleanimation = new ScaleAnimation(f, f1, 1.0F, 1.0F, 1, f2, 1, 0.5F);
        scaleanimation.setDuration(1000L);
        animationset.addAnimation(scaleanimation);
        return animationset;
    }

    private int getClipDuration()
    {
        MClip mclip = m_VideoClip;
        int i = 0;
        if (mclip != null)
        {
            MRange mrange = (MRange)m_VideoClip.getProperty(12292);
            i = 0;
            if (mrange != null)
            {
                i = mrange.get(1);
            }
        }
        return i;
    }

    private int getCurDecodedIdentifier()
    {
        if (m_ThumbManagerList == null)
        {
            return -1;
        } else
        {
            return m_ThumbManagerList.getCurDecodedIdentifier();
        }
    }

    private int getCurPlayTime()
    {
        if (m_Activity instanceof videoTrimActivity)
        {
            return ((videoTrimActivity)m_Activity).getCurPlayTime();
        } else
        {
            return 0;
        }
    }

    private boolean getPlayerStatus()
    {
        boolean flag = m_Activity instanceof videoTrimActivity;
        boolean flag1 = false;
        if (flag)
        {
            flag1 = ((videoTrimActivity)m_Activity).getPlayerStatus();
        }
        return flag1;
    }

    private void moveToCenterByPos(int i)
    {
        if (m_TrimOperMainLayout == null)
        {
            return;
        } else
        {
            int j = m_DisplayWidth / 2 - i;
            android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimOperMainLayout.getLayoutParams();
            layoutparams.x = j + layoutparams.x;
            m_TrimOperMainLayout.setLayoutParams(layoutparams);
            m_TrimMainLayoutMarginLeftPos = j + m_TrimMainLayoutMarginLeftPos;
            m_TrimMainLayoutMarginRightPos = j + m_TrimMainLayoutMarginRightPos;
            return;
        }
    }

    private void notifySeekToValue(int i)
    {
        if (i < 0 || i > m_VideoDuration || mSeekToValueHandler == null)
        {
            return;
        } else
        {
            Message message = mSeekToValueHandler.obtainMessage(0);
            message.arg1 = i;
            mSeekToValueHandler.sendMessage(message);
            return;
        }
    }

    private void notifySeekToValueByPos(int i)
    {
        notifySeekToValue((int)(((long)m_VideoDuration * (long)i) / (long)m_TrimMainLayoutWidth));
    }

    private void onBitmapDecoded(int i)
    {
        int j = i / m_IdentifierStep;
        if (m_ThumbImageViewList != null && j < m_ThumbImageViewList.size())
        {
            Bitmap bitmap = m_ThumbManagerList.getThumbBitmap(i);
            if (i >= 0 && bitmap != null && !bitmap.isRecycled())
            {
                ImageView imageview = (ImageView)m_ThumbImageViewList.get(j);
                if (imageview != null && imageview.getTag().equals("false"))
                {
                    imageview.setImageBitmap(bitmap);
                    imageview.setTag("true");
                    return;
                }
            }
        }
    }

    private void refreshLeftRightView()
    {
        android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimLeftImageView.getLayoutParams();
        layoutparams.x = mTrimLeftPos;
        layoutparams.width = m_TrimLRViewWidth;
        m_TrimLeftImageView.setLayoutParams(layoutparams);
        m_TrimLeftImageView.setVisibility(0);
        android.widget.AbsoluteLayout.LayoutParams layoutparams1 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimLeftMaskView.getLayoutParams();
        layoutparams1.x = m_TrimLRViewWidth;
        layoutparams1.width = mTrimLeftPos;
        m_TrimLeftMaskView.setLayoutParams(layoutparams1);
        m_TrimLeftMaskView.setVisibility(0);
        android.widget.AbsoluteLayout.LayoutParams layoutparams2 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimRightImageView.getLayoutParams();
        layoutparams2.x = mTrimRightPos;
        layoutparams2.width = m_TrimLRViewWidth;
        m_TrimRightImageView.setLayoutParams(layoutparams2);
        m_TrimRightImageView.setVisibility(0);
        android.widget.AbsoluteLayout.LayoutParams layoutparams3 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimRightMaskView.getLayoutParams();
        layoutparams3.x = mTrimRightPos;
        layoutparams3.width = (m_TrimMainLayoutWidth - mTrimRightPos) + m_TrimLRViewWidth;
        m_TrimRightMaskView.setLayoutParams(layoutparams3);
        m_TrimRightMaskView.setVisibility(0);
    }

    private void setDecodedBitmap(int i, Bitmap bitmap)
    {
        if (m_ThumbManagerList != null)
        {
            m_ThumbManagerList.setDecodedBitmap(i, bitmap);
        }
    }

    private void setLevelScale()
    {
        m_CurLevelScale = 0;
        m_MinLevelScale = 0;
        m_MaxLevelScale = 0;
        int i = 2 * m_TrimThumbBaseCount;
        for (int j = m_VideoDuration / m_TrimThumbBaseCount; i < 200 && j / 2 >= 1000; j /= 2)
        {
            m_MaxLevelScale = 1 + m_MaxLevelScale;
            i *= 2;
        }

        if (mTrimUIOperListener != null)
        {
            mTrimUIOperListener.notifyScaleLevelChanged(m_MinLevelScale, m_MaxLevelScale);
        }
    }

    private void setPlayerStatus(boolean flag)
    {
        if (m_Activity instanceof videoTrimActivity)
        {
            ((videoTrimActivity)m_Activity).setPlayerPlay(flag);
        }
    }

    private float spacing(MotionEvent motionevent)
    {
        float f = motionevent.getX(0) - motionevent.getX(1);
        float f1 = motionevent.getY(0) - motionevent.getY(1);
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    public void InitRes()
    {
        m_TrimParentLayout = (AbsoluteLayout)m_Activity.findViewById(0x7f090162);
        m_TrimOperMainLayout = (AbsoluteLayout)m_Activity.findViewById(0x7f090163);
        m_TrimThumbnailOperLayout = (AbsoluteLayout)m_Activity.findViewById(0x7f090164);
        m_TrimIndicateOperLayout = (AbsoluteLayout)m_Activity.findViewById(0x7f090165);
        m_TrimLeftImageView = (ImageView)m_Activity.findViewById(0x7f090167);
        m_TrimRightImageView = (ImageView)m_Activity.findViewById(0x7f090169);
        m_TrimLeftMaskView = (ImageView)m_Activity.findViewById(0x7f090166);
        m_TrimRightMaskView = (ImageView)m_Activity.findViewById(0x7f090168);
        m_TrimLocateBar = (ImageView)m_Activity.findViewById(0x7f09016a);
        m_TrimParentLayout.setOnTouchListener(new TouchParentLayoutListener());
        m_TrimIndicateOperLayout.setOnTouchListener(new TouchTrimIndicateListener());
        m_TrimLRViewWidth = ((BitmapDrawable)m_Activity.getResources().getDrawable(0x7f0202a2)).getIntrinsicWidth();
        android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimThumbnailOperLayout.getLayoutParams();
        layoutparams.x = m_TrimLRViewWidth;
        m_TrimThumbnailOperLayout.setLayoutParams(layoutparams);
        m_VideoDuration = getClipDuration();
        mTrimLeftValue = 0;
        mTrimRightValue = m_VideoDuration;
        DisplayMetrics displaymetrics = new DisplayMetrics();
        m_Activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        m_DisplayWidth = displaymetrics.widthPixels;
        UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("InitRes --- m_DisplayWidth: ").append(m_DisplayWidth).toString());
        mDrawableTrimBar = (BitmapDrawable)m_Activity.getResources().getDrawable(0x7f0201c5);
        android.widget.AbsoluteLayout.LayoutParams layoutparams1 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimLocateBar.getLayoutParams();
        layoutparams1.x = (m_DisplayWidth - mDrawableTrimBar.getIntrinsicWidth()) / 2;
        m_TrimLocateBar.setLayoutParams(layoutparams1);
        mDrawableMainFrame = (BitmapDrawable)m_Activity.getResources().getDrawable(0x7f0202a8);
        mDrawableMainFrame.setBounds(0, 0, mDrawableMainFrame.getIntrinsicWidth(), mDrawableMainFrame.getIntrinsicHeight());
        m_iThumbWidth = mDrawableMainFrame.getIntrinsicWidth();
        m_iThumbHeight = mDrawableMainFrame.getIntrinsicHeight();
        m_TrimThumbBaseCount = (m_DisplayWidth - 2 * m_TrimLRViewWidth) / (6 + m_iThumbWidth);
        m_TrimThumbCount = m_TrimThumbBaseCount;
        mTrimLeftPos = 0;
        mTrimRightPos = -6 + (m_TrimLRViewWidth + m_TrimThumbCount * (6 + m_iThumbWidth));
        m_TrimMainLayoutWidth = -6 + m_TrimThumbCount * (6 + m_iThumbWidth);
        refreshLeftRightView();
        android.widget.AbsoluteLayout.LayoutParams layoutparams2 = (android.widget.AbsoluteLayout.LayoutParams)m_TrimOperMainLayout.getLayoutParams();
        layoutparams2.x = m_DisplayWidth / 2 - m_TrimLRViewWidth;
        layoutparams2.width = m_TrimMainLayoutWidth + 2 * m_TrimLRViewWidth;
        m_TrimMainLayoutMarginLeftPos = m_DisplayWidth / 2 - m_TrimLRViewWidth;
        m_TrimMainLayoutMarginRightPos = -6 + (m_DisplayWidth / 2 + m_TrimThumbCount * (6 + m_iThumbWidth));
        m_TrimOperMainLayout.setLayoutParams(layoutparams2);
        setLevelScale();
        clearAllThumbnailView();
        if (m_ThumbImageViewList == null)
        {
            m_ThumbImageViewList = new ArrayList();
        }
        if (m_ThumbManagerList == null)
        {
            m_ThumbManagerList = new ThumbManagerList(m_iThumbWidth, m_iThumbHeight);
        }
        if (m_VideoDuration / m_TrimThumbBaseCount > 1000)
        {
            m_ThumbManagerList.setIdentifierApproximate(256);
        }
        startDecodeThumbnail();
    }

    public void changeTrimZoomSize(boolean flag)
    {
        if (flag)
        {
            attachTrim();
            return;
        } else
        {
            detachTrim();
            return;
        }
    }

    public void destroy()
    {
        if (m_IsDecodeThreadRuning)
        {
            exitDecodingThread();
        }
        if (mTrimUIOperListener != null)
        {
            mTrimUIOperListener.onTrimThumbManagerList(m_ThumbManagerList);
        }
        m_ThumbManagerList = null;
        if (m_ThumbImageViewList != null)
        {
            m_ThumbImageViewList.clear();
            m_ThumbImageViewList = null;
        }
        m_VideoClip = null;
        m_Activity = null;
    }

    public void exitDecodingThread()
    {
        m_IsDecodeThreadRuning = false;
        synchronized (m_ObjDecodeFrameSync)
        {
            mhandler.removeMessages(1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getCurScaleLevel()
    {
        return m_CurLevelScale;
    }

    public int getMaxScaleLevel()
    {
        return m_MaxLevelScale;
    }

    public int getMinScaleLevel()
    {
        return m_MinLevelScale;
    }

    public boolean getTouchPlayerStatus()
    {
        return m_bTouchPlayerStatus;
    }

    public void scrollToTrimLeftPos()
    {
        int i = getCurPlayTime();
        int j = (int)(((long)m_TrimMainLayoutWidth * (long)i) / (long)m_VideoDuration);
        if (j < 0)
        {
            return;
        } else
        {
            mTrimLeftPos = j;
            mTrimLeftValue = i;
            refreshLeftRightView();
            return;
        }
    }

    public void scrollToTrimRightPos()
    {
        int i = getCurPlayTime();
        int j = (int)(((long)m_TrimMainLayoutWidth * (long)i) / (long)m_VideoDuration);
        if (j > m_TrimMainLayoutWidth)
        {
            return;
        } else
        {
            mTrimRightPos = j + m_TrimLRViewWidth;
            mTrimRightValue = i;
            refreshLeftRightView();
            return;
        }
    }

    public void seekToPos(int i)
    {
        int j;
        if (i < 0 || i > m_VideoDuration)
        {
            return;
        }
        j = (int)(((long)m_TrimMainLayoutWidth * (long)i) / (long)m_VideoDuration) + m_TrimLRViewWidth;
        if (i > 0 && j > m_TrimLRViewWidth) goto _L2; else goto _L1
_L1:
        j = m_TrimLRViewWidth;
_L4:
        int k = (j + m_TrimMainLayoutMarginLeftPos) - m_DisplayWidth / 2;
        android.widget.AbsoluteLayout.LayoutParams layoutparams = (android.widget.AbsoluteLayout.LayoutParams)m_TrimOperMainLayout.getLayoutParams();
        layoutparams.x = layoutparams.x - k;
        m_TrimOperMainLayout.setLayoutParams(layoutparams);
        m_TrimMainLayoutMarginLeftPos = m_TrimMainLayoutMarginLeftPos - k;
        m_TrimMainLayoutMarginRightPos = m_TrimMainLayoutMarginRightPos - k;
        return;
_L2:
        if (i >= m_VideoDuration || j >= m_TrimMainLayoutWidth)
        {
            j = m_TrimMainLayoutWidth + m_TrimLRViewWidth;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setSeekToStatus(boolean flag)
    {
        m_IsSeeking = flag;
    }

    public void setTouchPlayerStatus(boolean flag)
    {
        m_bTouchPlayerStatus = flag;
    }

    public void setTrimUIOperListener(TrimUIOperListener trimuioperlistener)
    {
        mTrimUIOperListener = trimuioperlistener;
    }

    public void startDecodeThumbnail()
    {
        if (m_TrimThumbCount > 0 && mTrimLeftValue >= 0 && mTrimRightValue > mTrimLeftValue && m_ThumbManagerList != null)
        {
            m_IdentifierStep = m_VideoDuration / m_TrimThumbCount;
            UtilFunc.Log("TrimUIOper", (new StringBuilder()).append("startDecodeThumbnail --- > m_IdentifierStep: ").append(m_IdentifierStep).append(", m_VideoDuration: ").append(m_VideoDuration).append(", m_TrimThumbCount: ").append(m_TrimThumbCount).toString());
            m_ThumbManagerList.setIdentifierStep(m_IdentifierStep);
            AddEmptyThumbViewToLayout(m_TrimThumbCount);
            m_ThreadThumbDecode = new Thread(new ThumbDecodingRunnable());
            m_IsDecodeThreadRuning = true;
            if (m_ThreadThumbDecode != null && !m_ThreadThumbDecode.isAlive())
            {
                m_ThreadThumbDecode.start();
                return;
            }
        }
    }








/*
    static int access$1502(TrimUIOper trimuioper, int i)
    {
        trimuioper.mTrimLeftPos = i;
        return i;
    }

*/





/*
    static int access$1802(TrimUIOper trimuioper, int i)
    {
        trimuioper.mTrimRightPos = i;
        return i;
    }

*/









/*
    static int access$2402(TrimUIOper trimuioper, int i)
    {
        trimuioper.mTrimLeftValue = i;
        return i;
    }

*/




/*
    static int access$2602(TrimUIOper trimuioper, int i)
    {
        trimuioper.mTrimRightValue = i;
        return i;
    }

*/














/*
    static boolean access$602(TrimUIOper trimuioper, boolean flag)
    {
        trimuioper.m_bTouchPlayerStatus = flag;
        return flag;
    }

*/



}
