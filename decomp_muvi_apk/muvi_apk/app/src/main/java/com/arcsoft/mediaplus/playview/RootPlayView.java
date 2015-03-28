// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.arcsoft.util.ImageUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.util.os.HandlerTimer;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ProgressAnimationSet

public class RootPlayView extends RelativeLayout
{
    private class AnimationTimerListener
        implements com.arcsoft.util.os.HandlerTimer.IOnTimerListener
    {

        private boolean bForward;
        private int iStartProgress;
        private long lStartTime;
        final RootPlayView this$0;

        public boolean getForward()
        {
            return bForward;
        }

        public void onTimer()
        {
            long l;
            ProgressAnimationSet progressanimationset;
            int i;
            int j;
            int k;
            if (bForward)
            {
                l = System.currentTimeMillis() - lStartTime;
            } else
            {
                l = lStartTime - System.currentTimeMillis();
            }
            progressanimationset = mAnimationGroups[mCurrentGroup];
            i = progressanimationset.calcuProgress(l) + iStartProgress;
            j = progressanimationset.getProgress();
            k = progressanimationset.getPausedProgress();
            setProgress(i);
            if (mCurrentGroup == 0 && k > 0 && j <= k && i > k)
            {
                mProgressTimer.pause();
                boolean flag;
                if (mListener == null || mListener.onAnimationGroupPaused(mCurrentGroup))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    mProgressTimer.resume();
                } else
                {
                    mAutoUpdate.restart(false);
                    mProgressTimer.resume();
                }
            }
            if (!bForward && i <= 0 && mCurrentGroup == 0)
            {
                cancelAnimation();
            }
        }

        public void restart()
        {
            lStartTime = System.currentTimeMillis();
            iStartProgress = mAnimationGroups[mCurrentGroup].getProgress();
        }

        public void restart(boolean flag)
        {
            lStartTime = System.currentTimeMillis();
            iStartProgress = mAnimationGroups[mCurrentGroup].getProgress();
            bForward = flag;
        }

        public void setForward(boolean flag)
        {
            bForward = flag;
        }

        private AnimationTimerListener()
        {
            this$0 = RootPlayView.this;
            super();
            lStartTime = 0L;
            iStartProgress = 0;
            bForward = true;
        }

    }

    public static final class DragDirection extends Enum
    {

        private static final DragDirection $VALUES[];
        public static final DragDirection DirDown;
        public static final DragDirection DirUp;

        public static DragDirection valueOf(String s)
        {
            return (DragDirection)Enum.valueOf(com/arcsoft/mediaplus/playview/RootPlayView$DragDirection, s);
        }

        public static DragDirection[] values()
        {
            return (DragDirection[])$VALUES.clone();
        }

        static 
        {
            DirUp = new DragDirection("DirUp", 0);
            DirDown = new DragDirection("DirDown", 1);
            DragDirection adragdirection[] = new DragDirection[2];
            adragdirection[0] = DirUp;
            adragdirection[1] = DirDown;
            $VALUES = adragdirection;
        }

        private DragDirection(String s, int i)
        {
            super(s, i);
        }
    }

    static final class DragStatus extends Enum
    {

        private static final DragStatus $VALUES[];
        public static final DragStatus Disable;
        public static final DragStatus Dragging;
        public static final DragStatus Flicking;
        public static final DragStatus NoFuture;
        public static final DragStatus Waitting;

        public static DragStatus valueOf(String s)
        {
            return (DragStatus)Enum.valueOf(com/arcsoft/mediaplus/playview/RootPlayView$DragStatus, s);
        }

        public static DragStatus[] values()
        {
            return (DragStatus[])$VALUES.clone();
        }

        static 
        {
            Waitting = new DragStatus("Waitting", 0);
            Dragging = new DragStatus("Dragging", 1);
            Flicking = new DragStatus("Flicking", 2);
            NoFuture = new DragStatus("NoFuture", 3);
            Disable = new DragStatus("Disable", 4);
            DragStatus adragstatus[] = new DragStatus[5];
            adragstatus[0] = Waitting;
            adragstatus[1] = Dragging;
            adragstatus[2] = Flicking;
            adragstatus[3] = NoFuture;
            adragstatus[4] = Disable;
            $VALUES = adragstatus;
        }

        private DragStatus(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface IAnimationComponent
    {

        public abstract View getControlView();

        public abstract Drawable getDefaultDrawable();

        public abstract Bitmap getDisplayBitmap(Point point);

        public abstract Rect getDisplayLayout();

        public abstract boolean isActive();
    }

    public static interface IAnimationSetListener
    {

        public abstract void onAnimationEnd(int i, Animation animation, View view);

        public abstract void onAnimationGroupCanceled(int i);

        public abstract boolean onAnimationGroupEnd(int i);

        public abstract boolean onAnimationGroupPaused(int i);

        public abstract void onAnimationGroupStart(int i);

        public abstract void onAnimationStart(int i, Animation animation, View view);

        public abstract boolean onCreateAnimation();

        public abstract boolean onDispatchTouchEvent();

        public abstract void onPrepareAnimationGroup(int i);
    }

    public static interface IOnDragListener
    {

        public abstract void onDragCancel(float f, float f1);

        public abstract void onDragEnd(float f, float f1);

        public abstract void onDragStart(float f, float f1);

        public abstract void onDragging(float f, float f1);

        public abstract void onFlickLeft(float f, float f1);

        public abstract void onFlickRight(float f, float f1);
    }


    public static int ANIM_DURATION_STEP = 0;
    public static final float ANIM_ROTATE = 10F;
    public static final float ANIM_SCALE = 0.8F;
    public static final int GROUP_DISMISS = 0;
    public static final int GROUP_SHOW = 1;
    public static final int PROGRESS_IGNORE = 8;
    public static final String TAG = "RootView";
    public static final int UPDATE_PROGRESS_INTERVAL = 15;
    private ProgressAnimationSet mAnimationGroups[];
    private android.view.animation.Animation.AnimationListener mAnimationListener;
    private AnimationTimerListener mAutoUpdate;
    private int mCurrentGroup;
    private float mDownX;
    private float mDownY;
    private DragDirection mDragDir;
    private IOnDragListener mDragListener;
    private DragStatus mDragStatus;
    private Rect mFromLayout;
    private View mHotView;
    private ImageView mImageView;
    private IAnimationSetListener mListener;
    private HandlerTimer mProgressTimer;
    private Rect mToLayout;

    public RootPlayView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mDragStatus = DragStatus.Waitting;
        mDragDir = DragDirection.DirUp;
        mDragListener = null;
        mHotView = null;
        mProgressTimer = new HandlerTimer(Looper.myLooper());
        mAutoUpdate = new AnimationTimerListener();
        mAnimationGroups = new ProgressAnimationSet[2];
        mCurrentGroup = 0;
        mAnimationListener = new android.view.animation.Animation.AnimationListener() {

            final RootPlayView this$0;

            public void onAnimationEnd(Animation animation)
            {
                ProgressAnimationSet progressanimationset = mAnimationGroups[mCurrentGroup];
                List list = progressanimationset.getAnimations();
                int i = list.indexOf(animation);
                if (mListener != null)
                {
                    Log.i("RootView", (new StringBuilder()).append("onAnimationEnd(), group = ").append(mCurrentGroup).toString());
                    mListener.onAnimationEnd(mCurrentGroup, animation, (View)progressanimationset.getViews().get(i));
                }
                if (mDragDir == DragDirection.DirUp && mCurrentGroup == 0)
                {
                    restrictImageView(mToLayout);
                }
                progressanimationset.mbEnded = 1 + progressanimationset.mbEnded;
                int j = list.size();
                if (progressanimationset.mbEnded >= j)
                {
                    onAnimatationGroupEnd(mCurrentGroup);
                }
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

            public void onAnimationStart(Animation animation)
            {
                ProgressAnimationSet progressanimationset = mAnimationGroups[mCurrentGroup];
                int i = progressanimationset.getAnimations().indexOf(animation);
                if (mListener != null)
                {
                    mListener.onAnimationStart(mCurrentGroup, animation, (View)progressanimationset.getViews().get(i));
                }
                Log.i("RootView", (new StringBuilder()).append("onAnimationStart(), group = ").append(mCurrentGroup).toString());
                if (!progressanimationset.mbStarted)
                {
                    onAnimationGroupStart(mCurrentGroup);
                    progressanimationset.mbStarted = true;
                }
            }

            
            {
                this$0 = RootPlayView.this;
                super();
            }
        };
        mImageView = null;
        mFromLayout = new Rect();
        mToLayout = new Rect();
    }

    private void cancelAnimation()
    {
        if (mListener != null)
        {
            Log.i("RootView", (new StringBuilder()).append("onAnimationCanceled(), group = ").append(mCurrentGroup).toString());
            mListener.onAnimationGroupCanceled(mCurrentGroup);
        }
        uninitAnimation();
    }

    private void checkStartDrag(float f, float f1)
    {
        boolean flag = true;
        if (mDragStatus == DragStatus.Waitting)
        {
            float f2 = f - mDownX;
            float f3 = f1 - mDownY;
            int i = ViewConfiguration.get(getContext()).getScaledTouchSlop();
            float f4 = Math.abs(f2);
            float f5 = Math.abs(f3);
            boolean flag1;
            if (f5 > (float)i)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            if (f4 <= (float)i)
            {
                flag = false;
            }
            if (flag1 && (double)(f4 / f5) < 0.5D)
            {
                if (f3 > 0.0F && mDragDir == DragDirection.DirDown || f3 < 0.0F && mDragDir == DragDirection.DirUp)
                {
                    mDragStatus = DragStatus.Dragging;
                    return;
                } else
                {
                    mDownX = f;
                    mDownY = f1;
                    return;
                }
            }
            if (flag && (double)(f5 / f4) < 0.5D)
            {
                mDragStatus = DragStatus.Flicking;
                return;
            }
            if (flag || flag1)
            {
                mDragStatus = DragStatus.NoFuture;
                return;
            }
        }
    }

    private void onDragCancel(float f, float f1)
    {
        if (mDragListener != null)
        {
            mDragListener.onDragCancel(f, f1);
        }
        Log.e("RootView", "Drag Canceled");
    }

    private void onDragEnd(float f, float f1)
    {
        if (mDragListener != null)
        {
            mDragListener.onDragEnd(f, f1);
        }
        int i;
        if (mDragDir == DragDirection.DirUp)
        {
            i = (int)(mDownY - f1);
        } else
        {
            i = (int)(f1 - mDownY);
        }
        if (i <= 0)
        {
            startAnimation();
            return;
        }
        int j = mAnimationGroups[mCurrentGroup].getProgress();
        int k = mAnimationGroups[mCurrentGroup].getPausedProgress();
        Log.d("RootView", (new StringBuilder()).append("onDragEnd, curProgress = ").append(j).toString());
        if (j <= 8)
        {
            cancelAnimation();
        } else
        {
            if (j <= k)
            {
                mAutoUpdate.setForward(false);
            }
            startAnimation();
        }
        Log.e("RootView", (new StringBuilder()).append("Drag End : ").append((int)f).append(", ").append((int)f1).toString());
    }

    private void onDragStart(float f, float f1)
    {
        if (mDragListener != null)
        {
            mDragListener.onDragStart(f, f1);
        }
        reset(0);
        Log.e("RootView", (new StringBuilder()).append("Drag Start : ").append((int)f).append(", ").append((int)f1).toString());
    }

    private void onDragging(float f, float f1)
    {
        if (mDragListener != null)
        {
            mDragListener.onDragging(f, f1);
        }
        break MISSING_BLOCK_LABEL_18;
        if (mAnimationGroups.length != 0 && !mProgressTimer.isRunning())
        {
            int i;
            if (mDragDir == DragDirection.DirUp)
            {
                i = (int)(mDownY - f1);
            } else
            {
                i = (int)(f1 - mDownY);
            }
            if (i >= 0)
            {
                int j = getHeight() / 2;
                int k = (i * 100) / j;
                int l = mAnimationGroups[mCurrentGroup].getProgress();
                int i1 = mAnimationGroups[mCurrentGroup].getPausedProgress();
                Log.d("RootView", (new StringBuilder()).append("onDragging, setProgress = ").append(k).toString());
                Log.d("RootView", (new StringBuilder()).append("onDragging, curProgress = ").append(l).toString());
                setProgress(k);
                if (mCurrentGroup == 0 && i1 > 0 && l <= i1 && k > i1)
                {
                    boolean flag;
                    if (mListener == null || mListener.onAnimationGroupPaused(mCurrentGroup))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        setProgress(k);
                    } else
                    {
                        setProgress(i1);
                        mAutoUpdate.setForward(false);
                    }
                }
                Log.w("RootView", (new StringBuilder()).append("Drag Doing : ").append((int)f).append(", ").append((int)f1).toString());
                return;
            }
        }
        return;
    }

    private void onFlickingEnd(float f, float f1)
    {
        int i = (int)(f - mDownX);
        if (mDragListener == null) goto _L2; else goto _L1
_L1:
        if (i >= 0) goto _L4; else goto _L3
_L3:
        mDragListener.onFlickLeft(f, f1);
_L2:
        Log.e("RootView", (new StringBuilder()).append("Flicking End : ").append((int)f).append(", ").append((int)f1).toString());
        return;
_L4:
        if (i > 0)
        {
            mDragListener.onFlickRight(f, f1);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    private void restrictImageView(Rect rect)
    {
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(rect.width(), rect.height());
        layoutparams.setMargins(rect.left, rect.top, 0, 0);
        mImageView.setLayoutParams(layoutparams);
    }

    public void addAnimation(int i, Animation animation, View view)
    {
        if (mAnimationGroups[i] == null)
        {
            mAnimationGroups[i] = new ProgressAnimationSet(mAnimationListener);
        }
        mAnimationGroups[i].addAnimation(animation, view);
    }

    public boolean checkAnimation()
    {
        if (mAnimationGroups.length == 0)
        {
            Log.e("RootView", "ERROR: Animation has not been created!!!");
            return false;
        }
        if (mProgressTimer.geTimerListener() == null)
        {
            Log.e("RootView", "ERROR: Aniamtion has not been initilized!!!");
            return false;
        } else
        {
            return true;
        }
    }

    public void enableTouchIntercept(boolean flag)
    {
        if (flag)
        {
            if (mDragStatus != DragStatus.Disable)
            {
                mDragStatus = DragStatus.Waitting;
            }
        } else
        {
            if (mDragStatus == DragStatus.Dragging)
            {
                mDragStatus = DragStatus.Disable;
                onDragCancel(mDownX, mDownY);
                return;
            }
            if (mDragStatus == DragStatus.Flicking)
            {
                mDragStatus = DragStatus.Disable;
                return;
            }
        }
    }

    public Animation getAnimation(int i, View view)
    {
        return view.getAnimation();
    }

    public int getCurrentGroup()
    {
        if (checkAnimation())
        {
            return mCurrentGroup;
        } else
        {
            return -1;
        }
    }

    public boolean initAnimation(IAnimationComponent ianimationcomponent, IAnimationComponent ianimationcomponent1, boolean flag)
    {
        mImageView = (ImageView)((View)getParent()).findViewById(0x7f0900ac);
        Animation animation = AnimationUtils.loadAnimation(getContext(), 0x7f040009);
        Animation animation1 = AnimationUtils.loadAnimation(getContext(), 0x7f04000a);
        Animation animation2 = AnimationUtils.loadAnimation(getContext(), 0x7f040004);
        ProgressAnimationSet aprogressanimationset[] = mAnimationGroups;
        int i = aprogressanimationset.length;
        for (int j = 0; j < i; j++)
        {
            ProgressAnimationSet progressanimationset = aprogressanimationset[j];
            if (progressanimationset != null)
            {
                progressanimationset.clear();
            }
        }

        Point point = new Point();
        Bitmap bitmap;
        boolean flag1;
        Rect rect;
        Rect rect1;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        Object aobj[];
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        Object aobj1[];
        if (ianimationcomponent != null)
        {
            bitmap = ianimationcomponent.getDisplayBitmap(point);
            flag1 = false;
            if (bitmap == null)
            {
                bitmap = ((BitmapDrawable)ianimationcomponent.getDefaultDrawable()).getBitmap();
                flag1 = true;
            }
        } else
        if (ianimationcomponent1 != null)
        {
            bitmap = ianimationcomponent1.getDisplayBitmap(point);
            flag1 = false;
            if (bitmap == null)
            {
                bitmap = ((BitmapDrawable)ianimationcomponent1.getDefaultDrawable()).getBitmap();
                flag1 = true;
            }
        } else
        {
            return false;
        }
        rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rect1 = new Rect();
        if (point.x != -1)
        {
            if (rect.width() >= rect.height())
            {
                rect.right = Math.max(point.x, point.y);
                rect.bottom = Math.min(point.x, point.y);
            } else
            {
                rect.right = Math.min(point.x, point.y);
                rect.bottom = Math.max(point.x, point.y);
            }
        }
        if (ianimationcomponent != null)
        {
            ImageUtils.getFitInRect(ianimationcomponent.getDisplayLayout(), rect, rect1, flag);
            mFromLayout = new Rect(rect1);
        } else
        {
            getWindowVisibleDisplayFrame(mFromLayout);
        }
        if (ianimationcomponent1 != null)
        {
            ImageUtils.getFitInRect(ianimationcomponent1.getDisplayLayout(), rect, rect1, flag);
            mToLayout = new Rect(rect1);
        } else
        {
            getWindowVisibleDisplayFrame(mToLayout);
        }
        mImageView.setImageBitmap(bitmap);
        if (ianimationcomponent != null)
        {
            restrictImageView(mFromLayout);
        } else
        if (ianimationcomponent1 != null)
        {
            restrictImageView(mToLayout);
        }
        if (flag1)
        {
            mImageView.setScaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE);
        }
        mImageView.setVisibility(0);
        k = mFromLayout.left;
        l = mFromLayout.top;
        i1 = mFromLayout.width();
        j1 = mFromLayout.height();
        k1 = mFromLayout.centerX();
        l1 = mFromLayout.centerY();
        aobj = new Object[6];
        aobj[0] = Integer.valueOf(k);
        aobj[1] = Integer.valueOf(l);
        aobj[2] = Integer.valueOf(i1);
        aobj[3] = Integer.valueOf(j1);
        aobj[4] = Integer.valueOf(k1);
        aobj[5] = Integer.valueOf(l1);
        Log.d("Animation", String.format("[%d, %d], [%d, %d], [%d, %d]", aobj));
        i2 = mToLayout.left;
        j2 = mToLayout.top;
        k2 = mToLayout.width();
        l2 = mToLayout.height();
        i3 = mToLayout.centerX();
        j3 = mToLayout.centerY();
        aobj1 = new Object[6];
        aobj1[0] = Integer.valueOf(i2);
        aobj1[1] = Integer.valueOf(j2);
        aobj1[2] = Integer.valueOf(k2);
        aobj1[3] = Integer.valueOf(l2);
        aobj1[4] = Integer.valueOf(i3);
        aobj1[5] = Integer.valueOf(j3);
        Log.d("Animation", String.format("[%d, %d], [%d, %d], [%d, %d]", aobj1));
        if (mDragDir == DragDirection.DirUp)
        {
            if (ianimationcomponent != null)
            {
                float f6 = -l1 / 2;
                float f7 = getWidth() / 2 - k1;
                ScaleAnimation scaleanimation2 = new ScaleAnimation(1.0F, 0.8F, 1.0F, 0.8F, 1, 0.5F, 1, 0.5F);
                RotateAnimation rotateanimation2 = new RotateAnimation(0.0F, 10F, 1, 0.5F, 1, 0.5F);
                TranslateAnimation translateanimation2 = new TranslateAnimation(0.0F, f7, 0.0F, f6);
                scaleanimation2.setDuration(ANIM_DURATION_STEP);
                rotateanimation2.setDuration(ANIM_DURATION_STEP);
                translateanimation2.setDuration(ANIM_DURATION_STEP);
                TranslateAnimation translateanimation3 = new TranslateAnimation(0.0F, 0.0F, 0.0F, 4F * f6);
                translateanimation3.setStartOffset(ANIM_DURATION_STEP);
                translateanimation3.setDuration(ANIM_DURATION_STEP);
                AnimationSet animationset2 = new AnimationSet(true);
                animationset2.addAnimation(scaleanimation2);
                animationset2.addAnimation(rotateanimation2);
                animationset2.addAnimation(translateanimation2);
                animationset2.addAnimation(translateanimation3);
                addAnimation(0, animationset2, mImageView);
                mAnimationGroups[0].setPausedProgress(50);
            }
            if (ianimationcomponent1 != null)
            {
                addAnimation(1, animation, ianimationcomponent1.getControlView());
                addAnimation(1, animation, mImageView);
            }
        } else
        {
            float f = (float)k2 / (float)i1;
            float f1 = 1.0F + (f - 1.0F) / 2.0F;
            float f2 = (getHeight() - l1) / 2;
            float _tmp = (float)(j3 - l1) - f2;
            float f3 = i3 - k1;
            if (ianimationcomponent != null)
            {
                ScaleAnimation scaleanimation = new ScaleAnimation(1.0F, f1, 1.0F, f1, 1, 0.5F, 1, 0.5F);
                RotateAnimation rotateanimation = new RotateAnimation(0.0F, -10F, 1, 0.5F, 1, 0.5F);
                TranslateAnimation translateanimation = new TranslateAnimation(0.0F, f3 / 2.0F, 0.0F, f2);
                scaleanimation.setDuration(ANIM_DURATION_STEP);
                rotateanimation.setDuration(ANIM_DURATION_STEP);
                translateanimation.setDuration(ANIM_DURATION_STEP);
                AnimationSet animationset = new AnimationSet(true);
                animationset.addAnimation(scaleanimation);
                animationset.addAnimation(rotateanimation);
                animationset.addAnimation(translateanimation);
                addAnimation(0, animationset, mImageView);
                addAnimation(0, animation1, ianimationcomponent.getControlView());
                mAnimationGroups[0].setPausedProgress(100);
            }
            float f4;
            float f5;
            ScaleAnimation scaleanimation1;
            RotateAnimation rotateanimation1;
            TranslateAnimation translateanimation1;
            AnimationSet animationset1;
            if (ianimationcomponent1 != null)
            {
                f4 = f;
            } else
            {
                f4 = f / f1;
            }
            if (ianimationcomponent1 != null)
            {
                f5 = f;
            } else
            {
                f5 = f / f1;
            }
            scaleanimation1 = new ScaleAnimation(f1, f4, f1, f5, 1, 0.5F, 1, 0.5F);
            rotateanimation1 = new RotateAnimation(-10F, 0.0F, 1, 0.5F, 1, 0.5F);
            translateanimation1 = new TranslateAnimation(f3 / 2.0F, f3, f2, j3 - l1);
            scaleanimation1.setDuration(ANIM_DURATION_STEP);
            rotateanimation1.setDuration(ANIM_DURATION_STEP);
            translateanimation1.setDuration(ANIM_DURATION_STEP);
            animationset1 = new AnimationSet(true);
            animationset1.addAnimation(scaleanimation1);
            animationset1.addAnimation(rotateanimation1);
            animationset1.addAnimation(translateanimation1);
            addAnimation(1, animationset1, mImageView);
            if (ianimationcomponent1 != null)
            {
                addAnimation(1, animation2, ianimationcomponent1.getControlView());
            }
        }
        return true;
    }

    public void onAnimatationGroupEnd(int i)
    {
        Log.i("RootView", (new StringBuilder()).append("onAnimationGroupEnd(), group = ").append(i).toString());
        if (i != 0) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (mListener == null || mListener.onAnimationGroupEnd(i))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        mCurrentGroup = i + 1;
        if (mAnimationGroups[mCurrentGroup] != null) goto _L6; else goto _L5
_L5:
        uninitAnimation();
_L8:
        return;
_L6:
        if (mListener != null)
        {
            mListener.onPrepareAnimationGroup(mCurrentGroup);
        }
        mAnimationGroups[mCurrentGroup].start();
        mAutoUpdate.restart(true);
        return;
_L4:
        mAutoUpdate.restart(false);
        return;
_L2:
        uninitAnimation();
        if (mListener != null)
        {
            mListener.onAnimationGroupEnd(i);
            return;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void onAnimationGroupStart(int i)
    {
        if (mListener != null)
        {
            mListener.onAnimationGroupStart(i);
        }
        Log.i("RootView", (new StringBuilder()).append("onAnimationGroupStart(), group = ").append(i).toString());
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if (mListener == null || !mListener.onDispatchTouchEvent())
        {
            int i = motionevent.getAction();
            if (mDragStatus == DragStatus.Dragging || mDragStatus == DragStatus.Flicking)
            {
                return true;
            }
            if (motionevent.getPointerCount() > 1)
            {
                mDragStatus = DragStatus.NoFuture;
            }
            float f = motionevent.getX();
            float f1 = motionevent.getY();
            switch (i)
            {
            default:
                return false;

            case 0: // '\0'
                mDownX = f;
                mDownY = f1;
                return false;

            case 2: // '\002'
                checkStartDrag(f, f1);
                return false;

            case 1: // '\001'
            case 3: // '\003'
                break;
            }
            if (mDragStatus != DragStatus.Disable)
            {
                mDragStatus = DragStatus.Waitting;
                return false;
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i = motionevent.getAction();
        if (i != 0) goto _L2; else goto _L1
_L1:
        mDownX = motionevent.getX();
        mDownY = motionevent.getY();
_L4:
        return true;
_L2:
        if (i != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (mDragStatus != DragStatus.Dragging && mDragStatus != DragStatus.Flicking)
        {
            checkStartDrag(motionevent.getX(), motionevent.getY());
            return true;
        }
        break; /* Loop/switch isn't completed */
        if (i != 1 && i != 3) goto _L4; else goto _L3
_L3:
        while (false) 
        {
            if (mDragStatus != DragStatus.Dragging && mDragStatus == DragStatus.Flicking)
            {
                onFlickingEnd(motionevent.getX(), motionevent.getY());
            }
            if (mDragStatus != DragStatus.Disable)
            {
                mDragStatus = DragStatus.Waitting;
                return true;
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void pauseAnimation()
    {
        mProgressTimer.pause();
    }

    public boolean reset(int i)
    {
        if (mListener != null)
        {
            mListener.onCreateAnimation();
        }
        if (mAnimationGroups.length != 0)
        {
            ProgressAnimationSet aprogressanimationset[] = mAnimationGroups;
            int j = aprogressanimationset.length;
            for (int k = 0; k < j; k++)
            {
                ProgressAnimationSet progressanimationset = aprogressanimationset[k];
                if (progressanimationset != null)
                {
                    progressanimationset.reset();
                }
            }

            for (mCurrentGroup = 0; mAnimationGroups[mCurrentGroup] == null; mCurrentGroup = 1 + mCurrentGroup)
            {
                mListener.onPrepareAnimationGroup(mCurrentGroup);
                mListener.onAnimationGroupStart(mCurrentGroup);
                mListener.onAnimationGroupEnd(mCurrentGroup);
            }

            if (mCurrentGroup <= 1)
            {
                if (mListener != null)
                {
                    mListener.onPrepareAnimationGroup(mCurrentGroup);
                }
                mAnimationGroups[mCurrentGroup].start();
                setGroupProgress(i);
                mProgressTimer.set(15, true, mAutoUpdate);
                mAutoUpdate.setForward(true);
                mProgressTimer.pause();
                return true;
            }
        }
        return false;
    }

    public void resumeAnimation()
    {
        if (!checkAnimation())
        {
            return;
        } else
        {
            mProgressTimer.resume();
            return;
        }
    }

    public void reverseAnimation()
    {
        if (!checkAnimation())
        {
            return;
        } else
        {
            mAutoUpdate.restart(false);
            return;
        }
    }

    public void setAnimationSetListener(IAnimationSetListener ianimationsetlistener)
    {
        mListener = ianimationsetlistener;
    }

    public void setDragDirection(DragDirection dragdirection)
    {
        mDragDir = dragdirection;
    }

    public void setDragParams(View view)
    {
        mHotView = view;
    }

    public boolean setGroupProgress(int i)
    {
        for (int j = 0; j < i; j++)
        {
            if (mAnimationGroups[j] != null)
            {
                mAnimationGroups[j].setProgress(100);
            }
        }

        return true;
    }

    public void setOnDragListener(IOnDragListener iondraglistener)
    {
        mDragListener = iondraglistener;
    }

    public void setProgress(int i)
    {
        if (mAnimationGroups[mCurrentGroup] != null)
        {
            mAnimationGroups[mCurrentGroup].setProgress(i);
        }
    }

    public void startAnimation()
    {
        if (!checkAnimation())
        {
            return;
        } else
        {
            mAutoUpdate.restart();
            mProgressTimer.resume();
            return;
        }
    }

    public void uninitAnimation()
    {
        ProgressAnimationSet aprogressanimationset[] = mAnimationGroups;
        int i = aprogressanimationset.length;
        for (int j = 0; j < i; j++)
        {
            ProgressAnimationSet progressanimationset = aprogressanimationset[j];
            if (progressanimationset != null)
            {
                progressanimationset.clear();
            }
        }

        mProgressTimer.cancel();
        if (mImageView != null)
        {
            mImageView.setVisibility(4);
        }
    }

    static 
    {
        ANIM_DURATION_STEP = 500;
    }









}
