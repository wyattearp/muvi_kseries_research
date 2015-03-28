// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.arcsoft.util.debug.Log;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import powermobia.utils.MRect;

public class UIMiniatureLineView extends View
    implements EditorView.ITouchDistribution
{
    public static final class DrawType extends Enum
    {

        private static final DrawType $VALUES[];
        public static final DrawType CIRCLE;
        public static final DrawType NONE;
        public static final DrawType RECT;

        public static DrawType valueOf(String s)
        {
            return (DrawType)Enum.valueOf(com/arcsoft/workshop/ui/UIMiniatureLineView$DrawType, s);
        }

        public static DrawType[] values()
        {
            return (DrawType[])$VALUES.clone();
        }

        static 
        {
            NONE = new DrawType("NONE", 0);
            CIRCLE = new DrawType("CIRCLE", 1);
            RECT = new DrawType("RECT", 2);
            DrawType adrawtype[] = new DrawType[3];
            adrawtype[0] = NONE;
            adrawtype[1] = CIRCLE;
            adrawtype[2] = RECT;
            $VALUES = adrawtype;
        }

        private DrawType(String s, int i)
        {
            super(s, i);
        }
    }

    private static final class TouchMode extends Enum
    {

        private static final TouchMode $VALUES[];
        public static final TouchMode None;
        public static final TouchMode Pan;
        public static final TouchMode Rotate;
        public static final TouchMode Zoom;

        public static TouchMode valueOf(String s)
        {
            return (TouchMode)Enum.valueOf(com/arcsoft/workshop/ui/UIMiniatureLineView$TouchMode, s);
        }

        public static TouchMode[] values()
        {
            return (TouchMode[])$VALUES.clone();
        }

        static 
        {
            None = new TouchMode("None", 0);
            Pan = new TouchMode("Pan", 1);
            Zoom = new TouchMode("Zoom", 2);
            Rotate = new TouchMode("Rotate", 3);
            TouchMode atouchmode[] = new TouchMode[4];
            atouchmode[0] = None;
            atouchmode[1] = Pan;
            atouchmode[2] = Zoom;
            atouchmode[3] = Rotate;
            $VALUES = atouchmode;
        }

        private TouchMode(String s, int i)
        {
            super(s, i);
        }
    }


    private static int MAXLENGTH = 0;
    private static int MINLENGTH = 0;
    private static final String TAG = "UIMiniatureLineView";
    private static int mLineWidth = 2;
    private Animation mAlphaDisappearAnim;
    private int mAngle;
    private int mAngleLastZeroCount;
    private int mAngleTmp;
    private final Point mCircleCenter;
    private final Point mCircleCenterTmp;
    private int mCircleRadius;
    private int mCircleRadiusTmp;
    private OnCommandListener mCommandListener;
    private DrawType mCurDrawType;
    private final Point mCurPoint;
    private int mDistanceX;
    private int mDistanceY;
    private final Rect mImageRect;
    private final int mMiniatureParam[];
    private final Point mPrePoint;
    private final Point mPt1Cur;
    private final Point mPt1Pre;
    private final Point mPt2Cur;
    private final Point mPt2Pre;
    private final Point mRectCenter;
    private final Point mRectCenterTmp;
    private int mRectLength;
    private int mRectLengthTmp;
    private float mScaleFactor;
    private TouchMode mTouchMode;
    private WorkShop mWorkShop;

    public UIMiniatureLineView(Context context)
    {
        super(context);
        mWorkShop = null;
        mCommandListener = null;
        mMiniatureParam = new int[10];
        mCurDrawType = DrawType.NONE;
        mTouchMode = TouchMode.None;
        mImageRect = new Rect();
        mCircleCenter = new Point();
        mCircleCenterTmp = new Point();
        mCircleRadius = 0;
        mCircleRadiusTmp = 0;
        mRectCenter = new Point();
        mRectCenterTmp = new Point();
        mRectLength = 0;
        mRectLengthTmp = 0;
        mPrePoint = new Point();
        mCurPoint = new Point();
        mDistanceX = 0;
        mDistanceY = 0;
        mPt1Pre = new Point();
        mPt2Pre = new Point();
        mPt1Cur = new Point();
        mPt2Cur = new Point();
        mScaleFactor = 1.0F;
        mAngle = 0;
        mAngleTmp = 0;
        mAngleLastZeroCount = 0;
        mAlphaDisappearAnim = null;
        init(context);
    }

    public UIMiniatureLineView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWorkShop = null;
        mCommandListener = null;
        mMiniatureParam = new int[10];
        mCurDrawType = DrawType.NONE;
        mTouchMode = TouchMode.None;
        mImageRect = new Rect();
        mCircleCenter = new Point();
        mCircleCenterTmp = new Point();
        mCircleRadius = 0;
        mCircleRadiusTmp = 0;
        mRectCenter = new Point();
        mRectCenterTmp = new Point();
        mRectLength = 0;
        mRectLengthTmp = 0;
        mPrePoint = new Point();
        mCurPoint = new Point();
        mDistanceX = 0;
        mDistanceY = 0;
        mPt1Pre = new Point();
        mPt2Pre = new Point();
        mPt1Cur = new Point();
        mPt2Cur = new Point();
        mScaleFactor = 1.0F;
        mAngle = 0;
        mAngleTmp = 0;
        mAngleLastZeroCount = 0;
        mAlphaDisappearAnim = null;
        init(context);
    }

    public UIMiniatureLineView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mWorkShop = null;
        mCommandListener = null;
        mMiniatureParam = new int[10];
        mCurDrawType = DrawType.NONE;
        mTouchMode = TouchMode.None;
        mImageRect = new Rect();
        mCircleCenter = new Point();
        mCircleCenterTmp = new Point();
        mCircleRadius = 0;
        mCircleRadiusTmp = 0;
        mRectCenter = new Point();
        mRectCenterTmp = new Point();
        mRectLength = 0;
        mRectLengthTmp = 0;
        mPrePoint = new Point();
        mCurPoint = new Point();
        mDistanceX = 0;
        mDistanceY = 0;
        mPt1Pre = new Point();
        mPt2Pre = new Point();
        mPt1Cur = new Point();
        mPt2Cur = new Point();
        mScaleFactor = 1.0F;
        mAngle = 0;
        mAngleTmp = 0;
        mAngleLastZeroCount = 0;
        mAlphaDisappearAnim = null;
        init(context);
    }

    private void circileModeProcess()
    {
        if (mCurDrawType == DrawType.CIRCLE)
        {
            if (mTouchMode == TouchMode.Pan && (mDistanceX != 0 || mDistanceY != 0))
            {
                int i;
                int k;
                int i1;
                if (mDistanceX != 0)
                {
                    if (mDistanceX > 0 && mCircleCenter.x + mCircleRadiusTmp + mDistanceX + mLineWidth / 2 > mImageRect.right)
                    {
                        mDistanceX = mImageRect.right - mCircleCenter.x - mCircleRadiusTmp - mLineWidth / 2;
                    } else
                    if (mDistanceX < 0 && ((mCircleCenter.x - mCircleRadiusTmp) + mDistanceX) - mLineWidth / 2 < mImageRect.left)
                    {
                        mDistanceX = -(mCircleCenter.x - mCircleRadiusTmp - mLineWidth / 2 - mImageRect.left);
                    }
                }
                if (mDistanceY != 0)
                {
                    if (mDistanceY > 0 && mCircleCenter.y + mCircleRadiusTmp + mDistanceY + mLineWidth / 2 > mImageRect.bottom)
                    {
                        mDistanceY = mImageRect.bottom - mCircleCenter.y - mCircleRadiusTmp - mLineWidth / 2;
                    } else
                    if (mDistanceY < 0 && ((mCircleCenter.y - mCircleRadiusTmp) + mDistanceY) - mLineWidth / 2 < mImageRect.top)
                    {
                        mDistanceY = -(mCircleCenter.y - mCircleRadiusTmp - mLineWidth / 2 - mImageRect.top);
                    }
                }
                mCircleCenterTmp.x = mCircleCenter.x + mDistanceX;
                mCircleCenterTmp.y = mCircleCenter.y + mDistanceY;
            }
            i = Float.compare(mScaleFactor, 1.0F);
            if (mTouchMode == TouchMode.Zoom && i != 0 && mScaleFactor > 0.0F)
            {
                if (mScaleFactor > 1.0F)
                {
                    int j = mCircleCenterTmp.x - mLineWidth / 2 - mImageRect.left;
                    k = mImageRect.right - mCircleCenterTmp.x - mLineWidth / 2;
                    int l = mCircleCenterTmp.y - mLineWidth / 2 - mImageRect.top;
                    i1 = mImageRect.bottom - mCircleCenterTmp.y - mLineWidth / 2;
                    int j1;
                    int k1;
                    int l1;
                    if (j > k)
                    {
                        j1 = k;
                    } else
                    {
                        j1 = j;
                    }
                    if (l > i1)
                    {
                        k1 = i1;
                    } else
                    {
                        k1 = l;
                    }
                    if (j1 > k1)
                    {
                        l1 = k1;
                    } else
                    {
                        l1 = j1;
                    }
                    if ((float)mCircleRadius * mScaleFactor < (float)l1)
                    {
                        mCircleRadiusTmp = (int)((float)mCircleRadius * mScaleFactor);
                        return;
                    } else
                    {
                        mCircleRadiusTmp = l1;
                        return;
                    }
                }
                mCircleRadiusTmp = (int)(mScaleFactor * (float)mCircleRadius);
                if (mCircleRadiusTmp < MINLENGTH)
                {
                    mCircleRadiusTmp = MINLENGTH;
                    return;
                }
            }
        }
    }

    private float distance(float f, float f1, float f2, float f3)
    {
        float f4 = f1 - f;
        float f5 = f3 - f2;
        return FloatMath.sqrt(f4 * f4 + f5 * f5);
    }

    private int distance(Point point, Point point1)
    {
        float f = point.x - point1.x;
        float f1 = point.y - point1.y;
        return (int)FloatMath.sqrt(f * f + f1 * f1);
    }

    private float[] getMaxMovableDistance()
    {
        float af[] = new float[4];
        if (mAngleTmp == 0 || mAngleTmp == 180)
        {
            af[0] = mImageRect.bottom - mRectCenter.y - mLineWidth / 2;
            af[1] = mRectCenter.y - mImageRect.top - mLineWidth / 2;
            return af;
        }
        if (mAngleTmp == 90 || mAngleTmp == 270)
        {
            af[0] = mImageRect.right - mRectCenter.x - mLineWidth / 2;
            af[1] = mRectCenter.x - mImageRect.left - mLineWidth / 2;
            return af;
        } else
        {
            af[0] = mImageRect.bottom - mRectCenter.y - mLineWidth / 2;
            af[1] = mRectCenter.y - mImageRect.top - mLineWidth / 2;
            af[2] = mImageRect.right - mRectCenter.x - mLineWidth / 2;
            af[3] = mRectCenter.x - mImageRect.left - mLineWidth / 2;
            return af;
        }
    }

    private int getRotateAngle()
    {
        float f = distance(mPt1Pre, mPt1Cur);
        float f1 = distance(mPt2Pre, mPt2Cur);
        Point point = new Point();
        int i;
        byte byte0;
        float f2;
        if (f < f1)
        {
            point.x = (mPt1Pre.x + mPt1Cur.x) / 2;
            point.y = (mPt1Pre.y + mPt1Cur.y) / 2;
            float f6 = distance(point.x, mPt2Pre.x, point.y, mPt2Pre.y);
            float f7 = distance(point.x, mPt2Cur.x, point.y, mPt2Cur.y);
            float f8 = distance(mPt2Pre.x, mPt2Cur.x, mPt2Pre.y, mPt2Cur.y);
            i = (int)((180D * Math.acos(((f6 * f6 + f7 * f7) - f8 * f8) / (f7 * (2.0F * f6)))) / 3.1400000000000001D);
        } else
        if (f > f1)
        {
            point.x = (mPt2Pre.x + mPt2Cur.x) / 2;
            point.y = (mPt2Pre.y + mPt2Cur.y) / 2;
            float f3 = distance(mPt1Pre.x, point.x, mPt1Pre.y, point.y);
            float f4 = distance(mPt1Cur.x, point.x, mPt1Cur.y, point.y);
            float f5 = distance(mPt1Pre.x, mPt1Cur.x, mPt1Pre.y, mPt1Cur.y);
            i = (int)((180D * Math.acos(((f3 * f3 + f4 * f4) - f5 * f5) / (f4 * (2.0F * f3)))) / 3.1400000000000001D);
        } else
        {
            i = 0;
        }
        byte0 = 1;
        f2 = 0.0F;
        if (i != 0)
        {
            if (f < f1)
            {
                f2 = (float)((point.x * (mPt2Pre.y - mPt2Cur.y) - point.y * (mPt2Pre.x - mPt2Cur.x)) + (mPt2Pre.x * mPt2Cur.y - mPt2Pre.y * mPt2Cur.x)) / 2.0F;
            } else
            {
                f2 = (float)((point.x * (mPt1Pre.y - mPt1Cur.y) - point.y * (mPt1Pre.x - mPt1Cur.x)) + (mPt1Pre.x * mPt1Cur.y - mPt1Pre.y * mPt1Cur.x)) / 2.0F;
            }
        }
        if (f2 < 0.0F)
        {
            byte0 = -1;
        }
        Log.v("MiniatureAngle", (new StringBuilder()).append("angle111 : ").append(i).toString());
        Log.v("MiniatureAngle", (new StringBuilder()).append("angle111 direction : ").append(byte0).toString());
        if (byte0 == 1)
        {
            i = -i;
        }
        return i;
    }

    private int getRotateAngleUsingVector()
    {
        float f;
        float f1;
        float f2;
        float f3;
        int i;
        float f4;
        float f5;
        float f6;
        float f7;
        byte byte0;
        if ((float)distance(mPt1Pre, mPt1Cur) < (float)distance(mPt2Pre, mPt2Cur))
        {
            f = mPt2Pre.x - mPt1Pre.x;
            f1 = mPt2Pre.y - mPt1Pre.y;
            f2 = mPt2Cur.x - mPt1Cur.x;
            f3 = mPt2Cur.y - mPt1Cur.y;
        } else
        {
            f = mPt1Pre.x - mPt2Pre.x;
            f1 = mPt1Pre.y - mPt2Pre.y;
            f2 = mPt1Cur.x - mPt2Cur.x;
            f3 = mPt1Cur.y - mPt2Cur.y;
        }
        i = (int)((180D * Math.acos((float)((double)(f * f2 + f1 * f3) / (Math.sqrt(f * f + f1 * f1) * Math.sqrt(f2 * f2 + f3 * f3))))) / 3.1400000000000001D);
        Log.v("UIMiniatureLineView", (new StringBuilder()).append("getRotateAngleUsingVector : ").append(i).toString());
        f4 = (float)((double)(f * 1.0F + f1 * 0.0F) / (Math.sqrt(f * f + f1 * f1) * Math.sqrt(1.0F * 1.0F + 0.0F * 0.0F)));
        f5 = (float)((double)(f2 * 1.0F + f3 * 0.0F) / (Math.sqrt(f2 * f2 + f3 * f3) * Math.sqrt(1.0F * 1.0F + 0.0F * 0.0F)));
        f6 = (int)((180D * Math.acos(f4)) / 3.1400000000000001D);
        f7 = (int)((180D * Math.acos(f5)) / 3.1400000000000001D);
        byte0 = 1;
        if (f6 - f7 < 0.0F)
        {
            byte0 = -1;
        }
        if (byte0 == 1)
        {
            i = -i;
        }
        return i;
    }

    private void getZoomFactor()
    {
        float f = distance(mPt1Pre.x, mPt2Pre.x, mPt1Pre.y, mPt2Pre.y);
        float f1 = distance(mPt1Cur.x, mPt2Cur.x, mPt1Cur.y, mPt2Cur.y);
        if (Float.compare(f, 0.0F) != 0)
        {
            mScaleFactor = f1 / f;
        }
        if (mScaleFactor > 1.0F && mScaleFactor < 1.05F)
        {
            mScaleFactor = 1.05F;
        }
        if (mScaleFactor < 1.0F && mScaleFactor > 0.95F)
        {
            mScaleFactor = 0.95F;
        }
    }

    private void init(Context context)
    {
        mWorkShop = (WorkShop)context;
        mLineWidth = 0;
        mAlphaDisappearAnim = AnimationUtils.loadAnimation(getContext(), 0x7f040000);
    }

    private void initMiniatureParam()
    {
        powermobia.photoeditor.EditorEngine.State state = new powermobia.photoeditor.EditorEngine.State();
        mWorkShop.getEditorEngineWrapper().getState(state);
        int i = state.iImgWidth;
        int j = state.iImgHeight;
        double d = (double)i / (double)mImageRect.width();
        double d1 = (double)j / (double)mImageRect.height();
        double d2;
        int k;
        int l;
        if (d > d1)
        {
            d2 = d1;
        } else
        {
            d2 = d;
        }
        if (mCurDrawType == DrawType.CIRCLE)
        {
            mMiniatureParam[1] = 1;
            mMiniatureParam[5] = (int)(d2 * (double)((2 * mCircleRadiusTmp) / 3));
            mMiniatureParam[8] = (int)(d2 * (double)(mCircleCenterTmp.x - mImageRect.left));
            mMiniatureParam[9] = (int)(d2 * (double)(mCircleCenterTmp.y - mImageRect.top));
            mMiniatureParam[6] = (int)(d2 * (double)mCircleRadiusTmp - (double)mMiniatureParam[5]);
        } else
        if (mCurDrawType == DrawType.RECT)
        {
            mMiniatureParam[1] = 0;
            mMiniatureParam[5] = (int)(d2 * (double)((2 * mRectLengthTmp) / 3));
            mMiniatureParam[8] = (int)(d2 * (double)(mRectCenterTmp.x - mImageRect.left));
            mMiniatureParam[9] = (int)(d2 * (double)(mRectCenterTmp.y - mImageRect.top));
            mMiniatureParam[6] = (int)(d2 * (double)mRectLengthTmp - (double)mMiniatureParam[5]);
        }
        if (mImageRect.width() > mImageRect.height())
        {
            k = mImageRect.height();
        } else
        {
            k = mImageRect.width();
        }
        l = (int)(d2 * (double)(1 + k / 140));
        if (l < 5)
        {
            l = (int)(5D * d2);
        }
        mMiniatureParam[0] = -mAngleTmp;
        mMiniatureParam[2] = 1;
        mMiniatureParam[3] = 0;
        mMiniatureParam[4] = 0;
        mMiniatureParam[7] = l;
    }

    private void rectModeProcess()
    {
        if (mCurDrawType == DrawType.RECT) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (mTouchMode != TouchMode.Pan || mDistanceX == 0 && mDistanceY == 0) goto _L4; else goto _L3
_L3:
        float af1[] = getMaxMovableDistance();
        if (mAngleTmp != 90 && mAngleTmp != 270) goto _L6; else goto _L5
_L5:
        mDistanceY = 0;
        int i;
        if (mDistanceX > 0 && (float)(mRectLengthTmp + mDistanceX) > af1[0])
        {
            mDistanceX = (int)(af1[0] - (float)mRectLengthTmp);
        } else
        if (mDistanceX < 0 && (float)(mRectLengthTmp - mDistanceX) > af1[1])
        {
            mDistanceX = -(int)(af1[1] - (float)mRectLengthTmp);
        }
        mRectCenterTmp.x = mRectCenter.x + mDistanceX;
_L4:
        i = Float.compare(mScaleFactor, 1.0F);
        if (mTouchMode == TouchMode.Zoom && i != 0)
        {
            float af[] = getMaxMovableDistance();
            float f;
            if (af[0] > af[1])
            {
                f = af[1];
            } else
            {
                f = af[0];
            }
            if ((float)mRectLength * mScaleFactor > f)
            {
                mRectLengthTmp = (int)f;
            } else
            if ((float)mRectLength * mScaleFactor < (float)MINLENGTH)
            {
                mRectLengthTmp = MINLENGTH;
            } else
            {
                mRectLengthTmp = (int)((float)mRectLength * mScaleFactor);
            }
            if (mRectLengthTmp > MAXLENGTH)
            {
                mRectLengthTmp = MAXLENGTH;
            }
            if (mRectLengthTmp < MINLENGTH)
            {
                mRectLengthTmp = MINLENGTH;
                return;
            }
        }
        if (true) goto _L1; else goto _L6
_L6:
        if (mAngleTmp == 0 || mAngleTmp == 180)
        {
            mDistanceX = 0;
            if (mDistanceY > 0 && (float)(mRectLengthTmp + mDistanceY) > af1[0])
            {
                mDistanceY = (int)(af1[0] - (float)mRectLengthTmp);
            } else
            if (mDistanceY < 0 && (float)(mRectLengthTmp - mDistanceY) > af1[1])
            {
                mDistanceY = -(int)(af1[1] - (float)mRectLengthTmp);
            }
            mRectCenterTmp.y = mRectCenter.y + mDistanceY;
        } else
        {
            if (mDistanceY > 0 && (float)(mRectLengthTmp + mDistanceY) > af1[0])
            {
                mDistanceY = (int)(af1[0] - (float)mRectLengthTmp);
            } else
            if (mDistanceY < 0 && (float)(mRectLengthTmp - mDistanceY) > af1[1])
            {
                mDistanceY = -(int)(af1[1] - (float)mRectLengthTmp);
            }
            mRectCenterTmp.y = mRectCenter.y + mDistanceY;
            if (mDistanceX > 0 && (float)(mRectLengthTmp + mDistanceX) > af1[2])
            {
                mDistanceX = (int)(af1[2] - (float)mRectLengthTmp);
            } else
            if (mDistanceX < 0 && (float)(mRectLengthTmp - mDistanceX) > af1[3])
            {
                mDistanceX = -(int)(af1[3] - (float)mRectLengthTmp);
            }
            mRectCenterTmp.x = mRectCenter.x + mDistanceX;
        }
          goto _L4
    }

    private void resetParam()
    {
        mCircleCenter.set(mImageRect.centerX(), mImageRect.centerY());
        mCircleCenterTmp.set(mCircleCenter.x, mCircleCenter.y);
        int i;
        int j;
        if (mImageRect.width() > mImageRect.height())
        {
            i = mImageRect.height() / 4;
        } else
        {
            i = mImageRect.width() / 4;
        }
        mCircleRadiusTmp = i;
        mCircleRadius = i;
        mAngleTmp = 0;
        mAngle = 0;
        mScaleFactor = 1.0F;
        mRectCenter.set(mImageRect.centerX(), mImageRect.centerY());
        mRectCenterTmp.set(mRectCenter.x, mRectCenter.y);
        if (mImageRect.width() > mImageRect.height())
        {
            j = mImageRect.height() / 4;
        } else
        {
            j = mImageRect.width() / 4;
        }
        mRectLengthTmp = j;
        mRectLength = j;
    }

    public DrawType getDrawType()
    {
        return mCurDrawType;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (mCurDrawType == DrawType.CIRCLE)
        {
            int j1 = mImageRect.right - mImageRect.left;
            int k1 = mImageRect.bottom - mImageRect.top;
            int l1 = 2 * (int)(0.5D + Math.sqrt(j1 * j1 + k1 * k1));
            int i2 = mCircleRadiusTmp;
            RadialGradient radialgradient = null;
            if (i2 > 0)
            {
                radialgradient = new RadialGradient(mCircleCenterTmp.x - mImageRect.left, mCircleCenterTmp.y - mImageRect.top, mCircleRadiusTmp, new int[] {
                    0xffffff, 0xd0ffffff
                }, null, android.graphics.Shader.TileMode.CLAMP);
            }
            Paint paint1 = new Paint();
            paint1.setStyle(android.graphics.Paint.Style.FILL);
            paint1.setAntiAlias(true);
            paint1.setShader(radialgradient);
            canvas.drawCircle(mCircleCenterTmp.x - mImageRect.left, mCircleCenterTmp.y - mImageRect.top, l1 + mCircleRadiusTmp, paint1);
        } else
        if (mCurDrawType == DrawType.RECT)
        {
            canvas.save();
            int i = mRectCenterTmp.x - mImageRect.left;
            int j = mRectCenterTmp.y - mImageRect.top;
            int k = mImageRect.right - mImageRect.left;
            int l = mImageRect.bottom - mImageRect.top;
            int i1 = (int)(0.5D + Math.sqrt(k * k + l * l));
            canvas.translate(i, j);
            canvas.rotate(-mAngleTmp);
            LinearGradient lineargradient = new LinearGradient(0.0F, -mRectLengthTmp, 0.0F, mRectLengthTmp, new int[] {
                0xd0ffffff, 0xffffff, 0xd0ffffff
            }, null, android.graphics.Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setStyle(android.graphics.Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setShader(lineargradient);
            Rect rect = new Rect(2 * -i1, -i1, i1 * 2, i1);
            canvas.drawRect(rect, paint);
            canvas.restore();
            return;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return false;
    }

    public void resumeProcess()
    {
        if (mAlphaDisappearAnim.hasStarted())
        {
            clearAnimation();
            mAlphaDisappearAnim.reset();
        }
        setVisibility(4);
        mAlphaDisappearAnim.setDuration(2000L);
        startAnimation(mAlphaDisappearAnim);
    }

    public void setDrawType(DrawType drawtype)
    {
        mCurDrawType = drawtype;
        resetParam();
        if (mCurDrawType != DrawType.NONE)
        {
            initMiniatureParam();
            mCommandListener.onCommand(0, Integer.valueOf(1), null);
            mCommandListener.onCommand(20, mMiniatureParam, null);
            if (mAlphaDisappearAnim.hasStarted())
            {
                clearAnimation();
                mAlphaDisappearAnim.reset();
            }
            setVisibility(4);
            mAlphaDisappearAnim.setDuration(2000L);
            startAnimation(mAlphaDisappearAnim);
        } else
        {
            clearAnimation();
            mAlphaDisappearAnim.reset();
        }
        invalidate();
    }

    public void setImageRect(MRect mrect)
    {
        mImageRect.set(mrect.left, mrect.top, mrect.right, mrect.bottom);
        Log.v("Miniature", (new StringBuilder()).append("mImageRect : (").append(mrect.left).append(",").append(mrect.top).append(",").append(mrect.right).append(",").append(mrect.bottom).append(")").toString());
        mCircleCenter.set(mImageRect.centerX(), mImageRect.centerY());
        int i;
        int j;
        int k;
        int l;
        if (mImageRect.width() > mImageRect.height())
        {
            i = mImageRect.height() / 4;
        } else
        {
            i = mImageRect.width() / 4;
        }
        mCircleRadiusTmp = i;
        mCircleRadius = i;
        mRectCenter.set(mImageRect.centerX(), mImageRect.centerY());
        if (mImageRect.width() > mImageRect.height())
        {
            j = mImageRect.height() / 4;
        } else
        {
            j = mImageRect.width() / 4;
        }
        mRectLengthTmp = j;
        mRectLength = j;
        if (mImageRect.width() > mImageRect.height())
        {
            k = mImageRect.height() / 10;
        } else
        {
            k = mImageRect.width() / 10;
        }
        MINLENGTH = k;
        if (mImageRect.width() > mImageRect.height())
        {
            l = mImageRect.height() / 2;
        } else
        {
            l = mImageRect.width() / 2;
        }
        MAXLENGTH = l;
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }

    public boolean touchProcess(MotionEvent motionevent)
    {
        int i;
        int j;
        int k;
        if (mCurDrawType == DrawType.NONE)
        {
            return false;
        }
        i = motionevent.getAction();
        j = (int)motionevent.getX();
        k = (int)motionevent.getY();
        i & 0xff;
        JVM INSTR tableswitch 0 6: default 76
    //                   0 297
    //                   1 1581
    //                   2 518
    //                   3 1581
    //                   4 76
    //                   5 355
    //                   6 1414;
           goto _L1 _L2 _L3 _L4 _L3 _L1 _L5 _L6
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_1581;
_L7:
        int l;
        if (mTouchMode != TouchMode.None)
        {
            if (mCurDrawType == DrawType.CIRCLE)
            {
                circileModeProcess();
            } else
            if (mCurDrawType == DrawType.RECT)
            {
                rectModeProcess();
            }
        }
        if ((i & 0xff) == 6)
        {
            mAngle = mAngleTmp;
            mRectLength = mRectLengthTmp;
            mCircleRadius = mCircleRadiusTmp;
        }
        if (i == 1 || i == 3)
        {
            initMiniatureParam();
            mCommandListener.onCommand(0, Integer.valueOf(1), null);
            mCommandListener.onCommand(20, mMiniatureParam, null);
            if (mAlphaDisappearAnim.hasStarted())
            {
                clearAnimation();
                mAlphaDisappearAnim.reset();
            }
            setVisibility(4);
            mAlphaDisappearAnim.setDuration(400L);
            startAnimation(mAlphaDisappearAnim);
            mRectCenter.set(mRectCenterTmp.x, mRectCenterTmp.y);
            mCircleCenter.set(mCircleCenterTmp.x, mCircleCenterTmp.y);
            mAngle = mAngleTmp;
            mRectLength = mRectLengthTmp;
            mCircleRadius = mCircleRadiusTmp;
        }
        invalidate();
        return true;
_L2:
        clearAnimation();
        mAlphaDisappearAnim.reset();
        if (getVisibility() != 0)
        {
            setVisibility(0);
        }
        mTouchMode = TouchMode.Pan;
        mDistanceX = 0;
        mDistanceY = 0;
        mAngleLastZeroCount = 0;
        mPrePoint.set(j, k);
          goto _L7
_L5:
        mTouchMode = TouchMode.None;
        mAngleLastZeroCount = 0;
        mPt1Pre.x = (int)motionevent.getX(0);
        mPt2Pre.x = (int)motionevent.getX(1);
        mPt1Pre.y = (int)motionevent.getY(0);
        mPt2Pre.y = (int)motionevent.getY(1);
        Log.v("UIMiniatureLineView", (new StringBuilder()).append("mPt1Pre : ").append(mPt1Pre.x).append(",").append(mPt1Pre.y).toString());
        Log.v("UIMiniatureLineView", (new StringBuilder()).append("mPt1Pre : ").append(mPt2Pre.x).append(",").append(mPt2Pre.y).toString());
          goto _L7
_L4:
        if (motionevent.getPointerCount() == 1)
        {
            if (mTouchMode == TouchMode.Rotate || mTouchMode == TouchMode.Zoom)
            {
                if (mCurDrawType == DrawType.RECT)
                {
                    rectModeProcess();
                } else
                if (mCurDrawType == DrawType.CIRCLE && mTouchMode == TouchMode.Zoom)
                {
                    circileModeProcess();
                }
                mAngle = mAngleTmp;
                mRectLength = mRectLengthTmp;
                mCircleRadius = mCircleRadiusTmp;
                mTouchMode = TouchMode.None;
                mDistanceX = 0;
                mDistanceY = 0;
                mAngleLastZeroCount = 0;
                mPrePoint.set(j, k);
            } else
            if (mTouchMode == TouchMode.Pan)
            {
                mCurPoint.set(j, k);
                mDistanceX = mCurPoint.x - mPrePoint.x;
                mDistanceY = mCurPoint.y - mPrePoint.y;
            } else
            {
                mTouchMode = TouchMode.Pan;
                mDistanceX = 0;
                mDistanceY = 0;
                mPrePoint.set(j, k);
            }
        } else
        if (motionevent.getPointerCount() > 1)
        {
            if (mTouchMode == TouchMode.Pan)
            {
                if (mCurDrawType == DrawType.RECT)
                {
                    rectModeProcess();
                } else
                if (mCurDrawType == DrawType.CIRCLE)
                {
                    circileModeProcess();
                }
                mDistanceX = 0;
                mDistanceY = 0;
                mAngleLastZeroCount = 0;
                mScaleFactor = 1.0F;
                mAngle = mAngleTmp;
                mRectLength = mRectLengthTmp;
                mCircleRadius = mCircleRadiusTmp;
                mPt1Pre.x = (int)motionevent.getX(0);
                mPt2Pre.x = (int)motionevent.getX(1);
                mPt1Pre.y = (int)motionevent.getY(0);
                mPt2Pre.y = (int)motionevent.getY(1);
            } else
            {
                if (mCurDrawType == DrawType.CIRCLE)
                {
                    mTouchMode = TouchMode.Zoom;
                }
                if (mTouchMode == TouchMode.None)
                {
                    mPt1Cur.x = (int)motionevent.getX(0);
                    mPt2Cur.x = (int)motionevent.getX(1);
                    mPt1Cur.y = (int)motionevent.getY(0);
                    mPt2Cur.y = (int)motionevent.getY(1);
                    Log.v("UIMiniatureLineView", (new StringBuilder()).append("mPt1Cur : ").append(mPt1Cur.x).append(",").append(mPt1Cur.y).toString());
                    Log.v("UIMiniatureLineView", (new StringBuilder()).append("mPt2Cur : ").append(mPt2Cur.x).append(",").append(mPt2Cur.y).toString());
                    if (mPt1Cur.x != mPt1Pre.x || mPt1Cur.y != mPt1Pre.y || mPt2Cur.x != mPt2Pre.x || mPt2Cur.y != mPt2Pre.y)
                    {
                        l = getRotateAngleUsingVector();
                        Log.v("UIMiniatureLineView", (new StringBuilder()).append("angletmp : ").append(l).toString());
                        if (Math.abs(l) <= 1)
                        {
                            mAngleLastZeroCount = 1 + mAngleLastZeroCount;
                            if (mAngleLastZeroCount >= 3)
                            {
                                mTouchMode = TouchMode.Zoom;
                            }
                        }
                        if (Math.abs(l) > 1)
                        {
                            mTouchMode = TouchMode.Rotate;
                            mAngleTmp = mAngle + getRotateAngle();
                            mAngleTmp = (360 + mAngleTmp % 360) % 360;
                        }
                    }
                } else
                if (mTouchMode == TouchMode.Zoom)
                {
                    mPt1Cur.x = (int)motionevent.getX(0);
                    mPt2Cur.x = (int)motionevent.getX(1);
                    mPt1Cur.y = (int)motionevent.getY(0);
                    mPt2Cur.y = (int)motionevent.getY(1);
                    getZoomFactor();
                } else
                if (mTouchMode == TouchMode.Rotate)
                {
                    mPt1Cur.x = (int)motionevent.getX(0);
                    mPt2Cur.x = (int)motionevent.getX(1);
                    mPt1Cur.y = (int)motionevent.getY(0);
                    mPt2Cur.y = (int)motionevent.getY(1);
                    mAngleTmp = mAngle + getRotateAngle();
                    mAngleTmp = (360 + mAngleTmp % 360) % 360;
                }
            }
        }
          goto _L7
_L6:
        if (mTouchMode == TouchMode.Zoom)
        {
            mPt1Cur.x = (int)motionevent.getX(0);
            mPt2Cur.x = (int)motionevent.getX(1);
            mPt1Cur.y = (int)motionevent.getY(0);
            mPt2Cur.y = (int)motionevent.getY(1);
            getZoomFactor();
        } else
        if (mTouchMode == TouchMode.Rotate)
        {
            mPt1Cur.x = (int)motionevent.getX(0);
            mPt2Cur.x = (int)motionevent.getX(1);
            mPt1Cur.y = (int)motionevent.getY(0);
            mPt2Cur.y = (int)motionevent.getY(1);
            mAngleTmp = mAngle + getRotateAngle();
            mAngleTmp = (360 + mAngleTmp % 360) % 360;
        }
          goto _L7
        if (mTouchMode == TouchMode.Pan)
        {
            mCurPoint.set(j, k);
            mDistanceX = mCurPoint.x - mPrePoint.x;
            mDistanceY = mCurPoint.y - mPrePoint.y;
        }
          goto _L7
    }

    public void uninit()
    {
        mCurDrawType = DrawType.NONE;
        clearAnimation();
        mAlphaDisappearAnim.reset();
    }

    static 
    {
        MINLENGTH = 10;
        MAXLENGTH = 10;
    }
}
