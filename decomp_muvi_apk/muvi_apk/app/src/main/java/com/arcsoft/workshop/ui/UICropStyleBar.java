// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.arcsoft.workshop.EditorEngineWrapper;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import powermobia.utils.MPoint;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropSizeWindow, UIRegionCrop, IManagerViewProcess

public class UICropStyleBar extends LinearLayout
    implements UICropSizeWindow.IModeChangeListener
{

    private static final int CROP_16v9_MODE = 5;
    private static final int CROP_1v1_MODE = 0;
    private static final int CROP_2v3_MODE = 1;
    private static final int CROP_3v2_MODE = 2;
    private static final int CROP_3v4_MODE = 4;
    private static final int CROP_4v3_MODE = 3;
    private static final int CROP_9v16_MODE = 6;
    private static final int CROP_FREE_MODE = 7;
    boolean bModifyH;
    boolean bModifyW;
    private final android.view.View.OnClickListener mClickListener;
    private OnCommandListener mCommandListener;
    private final MRect mCropRectDis;
    private final UICropBar.ICropRectProp mCropRectProp;
    private UIRegionCrop mCropView;
    private IManagerViewProcess mManagerViewProcess;
    private final MRect mMaxCropRectDis;
    UICropSizeWindow mPopWindow;
    private int mRatioType;
    private Button mResetBtn;
    private Button mSetBtn;
    private final boolean mShoudPreProcess;
    private WorkShop mWorkShop;
    private boolean mbPreCoordChanged;

    public UICropStyleBar(Context context)
    {
        super(context);
        mWorkShop = null;
        mCommandListener = null;
        mManagerViewProcess = null;
        mCropView = null;
        mRatioType = 7;
        mMaxCropRectDis = new MRect();
        mCropRectDis = new MRect();
        mShoudPreProcess = true;
        mSetBtn = null;
        mResetBtn = null;
        mCropRectProp = new UICropBar.ICropRectProp() {

            final UICropStyleBar this$0;

            public MRect getCropRect()
            {
                MRect mrect = new MRect();
                mCropView.getRegion(mrect);
                return mrect;
            }

            
            {
                this$0 = UICropStyleBar.this;
                super();
            }
        };
        bModifyW = false;
        bModifyH = false;
        mPopWindow = null;
        mClickListener = new android.view.View.OnClickListener() {

            final UICropStyleBar this$0;

            public void onClick(View view)
            {
                view.getId();
                JVM INSTR tableswitch 2131296652 2131296653: default 28
            //                           2131296652 29
            //                           2131296653 64;
                   goto _L1 _L2 _L3
_L1:
                return;
_L2:
                if (!mPopWindow.isShowing())
                {
                    mPopWindow.showPopupMenuWindow();
                    return;
                } else
                {
                    mPopWindow.hide();
                    return;
                }
_L3:
                process(7, true);
                if (mPopWindow != null)
                {
                    mPopWindow.retsetMode();
                    mPopWindow.hide();
                    return;
                }
                if (true) goto _L1; else goto _L4
_L4:
            }

            
            {
                this$0 = UICropStyleBar.this;
                super();
            }
        };
        mWorkShop = (WorkShop)context;
    }

    public UICropStyleBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWorkShop = null;
        mCommandListener = null;
        mManagerViewProcess = null;
        mCropView = null;
        mRatioType = 7;
        mMaxCropRectDis = new MRect();
        mCropRectDis = new MRect();
        mShoudPreProcess = true;
        mSetBtn = null;
        mResetBtn = null;
        mCropRectProp = new _cls1();
        bModifyW = false;
        bModifyH = false;
        mPopWindow = null;
        mClickListener = new _cls2();
        mWorkShop = (WorkShop)context;
    }

    private void initPopWindow()
    {
        if (mPopWindow == null)
        {
            mPopWindow = new UICropSizeWindow(mWorkShop);
            mPopWindow.initPopupMenuWindow(mSetBtn);
        }
        mPopWindow.setModeChangeListener(this);
        refreshSetBtn(mPopWindow.getCurrentMode());
        process(mPopWindow.getCurrentIndex(), true);
    }

    private boolean needAdjustCropRect(int i, int j)
    {
        int k;
        int l;
        boolean flag;
        bModifyW = false;
        bModifyH = false;
        k = mCropRectDis.right - mCropRectDis.left;
        l = mCropRectDis.bottom - mCropRectDis.top;
        int i1 = mRatioType;
        flag = false;
        if (i1 == 7)
        {
            flag = true;
        }
        if (flag && !mCropView.isEverDragged())
        {
            mCropRectDis.left = mMaxCropRectDis.left;
            mCropRectDis.right = mMaxCropRectDis.right;
            mCropRectDis.top = mMaxCropRectDis.top;
            mCropRectDis.bottom = mMaxCropRectDis.bottom;
            return true;
        }
        if (mCropRectDis.left >= mMaxCropRectDis.left && mCropRectDis.right <= mMaxCropRectDis.right) goto _L2; else goto _L1
_L1:
        boolean flag1;
        if (k <= mMaxCropRectDis.right - mMaxCropRectDis.left)
        {
            if (mCropRectDis.left < mMaxCropRectDis.left)
            {
                mCropRectDis.left = mMaxCropRectDis.left;
                mCropRectDis.right = k + mCropRectDis.left;
            } else
            {
                mCropRectDis.right = mMaxCropRectDis.right;
                mCropRectDis.left = mCropRectDis.right - k;
            }
        } else
        {
            mMaxCropRectDis.right - mMaxCropRectDis.left;
            mCropRectDis.left = mMaxCropRectDis.left;
            mCropRectDis.right = mMaxCropRectDis.right;
            bModifyW = true;
        }
        flag1 = true;
_L4:
        if (mCropRectDis.top < mMaxCropRectDis.top || mCropRectDis.bottom > mMaxCropRectDis.bottom)
        {
            int j1;
            int k1;
            int l1;
            if (l <= mMaxCropRectDis.bottom - mMaxCropRectDis.top)
            {
                if (mCropRectDis.top < mMaxCropRectDis.top)
                {
                    mCropRectDis.top = mMaxCropRectDis.top;
                    mCropRectDis.bottom = l + mCropRectDis.top;
                } else
                {
                    mCropRectDis.bottom = mMaxCropRectDis.bottom;
                    mCropRectDis.top = mCropRectDis.bottom - l;
                }
            } else
            {
                mMaxCropRectDis.bottom - mMaxCropRectDis.top;
                mCropRectDis.top = mMaxCropRectDis.top;
                mCropRectDis.bottom = mMaxCropRectDis.bottom;
                bModifyH = true;
            }
            flag1 = true;
        }
        return flag1;
_L2:
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        if (mCropRectDis.left > mMaxCropRectDis.left) goto _L6; else goto _L5
_L5:
        k1 = mCropRectDis.right;
        l1 = mMaxCropRectDis.right;
        flag1 = false;
        if (k1 >= l1) goto _L4; else goto _L6
_L6:
        j1 = mCropView.getOriginRect().width();
        flag1 = false;
        if (k < j1)
        {
            if (mCropRectDis.left > mMaxCropRectDis.left)
            {
                mCropRectDis.left = mMaxCropRectDis.left;
                mCropRectDis.right = mMaxCropRectDis.right;
            } else
            {
                mCropRectDis.right = mMaxCropRectDis.right;
                mCropRectDis.left = mMaxCropRectDis.right - k;
            }
            flag1 = true;
        }
          goto _L4
    }

    private int preZoomPan()
    {
        int i;
        int j;
        i = mCropRectDis.right - mCropRectDis.left;
        j = mCropRectDis.bottom - mCropRectDis.top;
        if (!needAdjustCropRect(i, j)) goto _L2; else goto _L1
_L1:
        int k;
        int l;
        int i1;
        int j1;
        MRect mrect = new MRect();
        MRect mrect1 = new MRect();
        MPoint mpoint = new MPoint();
        k = 40;
        l = 40;
        i1 = mMaxCropRectDis.right - mMaxCropRectDis.left;
        j1 = mMaxCropRectDis.bottom - mMaxCropRectDis.top;
        mWorkShop.getEditorEngineWrapper().getImgSize(mpoint);
        mrect.left = 0;
        mrect.right = mrect.left + Math.min(40, mpoint.x);
        mrect.top = 0;
        mrect.bottom = mrect.top + Math.min(40, mpoint.y);
        mWorkShop.getEditorEngineWrapper().rectImgToScreen(mrect, mrect1);
        if (true)
        {
            k = mrect1.right - mrect1.left;
            l = mrect1.bottom - mrect1.top;
        }
        mRatioType;
        JVM INSTR tableswitch 0 7: default 264
    //                   0 447
    //                   1 456
    //                   2 465
    //                   3 483
    //                   4 474
    //                   5 492
    //                   6 503
    //                   7 514;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_514;
_L3:
        byte byte0;
        byte byte1;
        byte0 = -1;
        byte1 = -1;
_L12:
        if (byte0 != -1 && byte1 != -1)
        {
            int k1 = (mCropRectDis.left + mCropRectDis.right) / 2;
            int l1 = (mCropRectDis.top + mCropRectDis.bottom) / 2;
            if (bModifyW)
            {
                int j2 = (i * byte1) / byte0;
                if (j2 < l || j2 > j1)
                {
                    i = (j * byte0) / byte1;
                    if (i < k)
                    {
                        i = k;
                    }
                    if (i > i1)
                    {
                        i = i1;
                    }
                } else
                {
                    j = j2;
                }
            } else
            {
                int i2 = (j * byte0) / byte1;
                if (i2 < k || i2 > i1)
                {
                    j = (i * byte1) / byte0;
                    if (j < l)
                    {
                        j = l;
                    }
                    if (j > j1)
                    {
                        j = j1;
                    }
                } else
                {
                    i = i2;
                }
            }
            mCropRectDis.top = l1 - j / 2;
            mCropRectDis.bottom = j + mCropRectDis.top;
            mCropRectDis.left = k1 - i / 2;
            mCropRectDis.right = i + mCropRectDis.left;
        }
_L2:
        mCropView.setRegion(mCropRectDis);
        return 0;
_L4:
        byte0 = 1;
        byte1 = 1;
          goto _L12
_L5:
        byte0 = 2;
        byte1 = 3;
          goto _L12
_L6:
        byte0 = 3;
        byte1 = 2;
          goto _L12
_L8:
        byte0 = 3;
        byte1 = 4;
          goto _L12
_L7:
        byte0 = 4;
        byte1 = 3;
          goto _L12
_L9:
        byte0 = 16;
        byte1 = 9;
          goto _L12
_L10:
        byte0 = 9;
        byte1 = 16;
          goto _L12
        byte0 = -1;
        byte1 = -1;
          goto _L12
    }

    public void changeCurrentModeName(String s)
    {
        refreshSetBtn(s);
    }

    public void doDraw(Bitmap bitmap)
    {
        if (mCropView != null && mCropView.getVisibility() == 0)
        {
            onPostCoordChanged();
            mCropView.doDraw(bitmap);
        }
    }

    public UICropBar.ICropRectProp getCropRectProp()
    {
        return mCropRectProp;
    }

    public void init(MultiTouchDetector.OnMultiTouchListener onmultitouchlistener)
    {
        mCropView = new UIRegionCrop(getContext());
        mManagerViewProcess.addView(mCropView);
        mCropView.initMultiTouch(onmultitouchlistener);
        mCropView.setAspect(1, 1);
        mRatioType = 7;
        MRect mrect = new MRect();
        mCommandListener.onCommand(18, mrect, null);
        mCropView.setMaxRegion(mrect);
        mCropView.resetRegion();
        initPopWindow();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mSetBtn = (Button)findViewById(0x7f09018c);
        mResetBtn = (Button)findViewById(0x7f09018d);
        if (mSetBtn != null)
        {
            mSetBtn.setOnClickListener(mClickListener);
        }
        if (mResetBtn != null)
        {
            mResetBtn.setOnClickListener(mClickListener);
        }
    }

    public void onPostCoordChanged()
    {
        if (mbPreCoordChanged && mCropView != null && mCropView.getVisibility() == 0)
        {
            mCommandListener.onCommand(18, mMaxCropRectDis, null);
            mCropView.setMaxRegion(mMaxCropRectDis);
            preZoomPan();
            mbPreCoordChanged = false;
        }
    }

    public void onPreCoordChanged()
    {
        if (mCropView != null && mCropView.getVisibility() == 0)
        {
            mCropView.getRegion(mCropRectDis);
            mbPreCoordChanged = true;
        }
    }

    protected void process(int i, boolean flag)
    {
        mRatioType = i;
        mRatioType;
        JVM INSTR tableswitch 0 7: default 56
    //                   0 120
    //                   1 132
    //                   2 144
    //                   3 156
    //                   4 168
    //                   5 180
    //                   6 194
    //                   7 208;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
        if (mRatioType == 7 && !flag) goto _L11; else goto _L10
_L10:
        MRect mrect = new MRect();
        mCommandListener.onCommand(18, mrect, null);
        mCropView.setMaxRegion(mrect);
        mCropView.resetRegion();
        mWorkShop.getEditorEngineWrapper().refreshDisplay(null, false);
_L13:
        return;
_L2:
        mCropView.setAspect(1, 1);
        continue; /* Loop/switch isn't completed */
_L3:
        mCropView.setAspect(2, 3);
        continue; /* Loop/switch isn't completed */
_L4:
        mCropView.setAspect(3, 2);
        continue; /* Loop/switch isn't completed */
_L5:
        mCropView.setAspect(4, 3);
        continue; /* Loop/switch isn't completed */
_L6:
        mCropView.setAspect(3, 4);
        continue; /* Loop/switch isn't completed */
_L7:
        mCropView.setAspect(16, 9);
        continue; /* Loop/switch isn't completed */
_L8:
        mCropView.setAspect(9, 16);
        continue; /* Loop/switch isn't completed */
_L9:
        mCropView.setAspect(-1, -1);
        continue; /* Loop/switch isn't completed */
_L11:
        if (mRatioType != 7 || flag) goto _L13; else goto _L12
_L12:
        mCropView.setOriginRect();
        return;
        if (true) goto _L1; else goto _L14
_L14:
    }

    protected void refreshSetBtn(String s)
    {
        if (mSetBtn != null)
        {
            mSetBtn.setText(s);
        }
    }

    public void setManagerViewProcess(IManagerViewProcess imanagerviewprocess)
    {
        mManagerViewProcess = imanagerviewprocess;
    }

    public void setModeIndex(int i)
    {
        process(i, false);
        mPopWindow.hide();
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }

    public void uninit()
    {
        mManagerViewProcess.removeView(mCropView);
        mCropView.releaseCropMBitmap();
    }

}
