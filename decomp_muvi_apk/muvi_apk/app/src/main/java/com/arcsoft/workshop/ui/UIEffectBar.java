// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import com.arcsoft.workshop.OnCommandListener;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICallBack

public class UIEffectBar extends HorizontalScrollView
{

    private static int mEffectItem[] = {
        0x7f090190, 0x7f02012c, 0x7f090192, 0x7f020128, 0x7f090194, 0x7f020127, 0x7f090196, 0x7f02012b, 0x7f090198, 0x7f020129, 
        0x7f09019a, 0x7f02012a, 0x7f09019c, 0x7f02012f, 0x7f09019e, 0x7f02012d, 0x7f0901a0, 0x7f02012e, 0x7f0901a2, 0x7f020130, 
        0x7f0901a4, 0x7f020134, 0x7f0901a6, 0x7f020126, 0x7f0901a8, 0x7f020124, 0x7f0901aa, 0x7f020121, 0x7f0901ac, 0x7f020120, 
        0x7f0901ae, 0x7f02011f, 0x7f0901b0, 0x7f020122, 0x7f0901b2, 0x7f020123, 0x7f0901b4, 0x7f020133, 0x7f0901b6, 0x7f02011d, 
        0x7f0901b8, 0x7f02011e, 0x7f0901ba, 0x7f020132, 0x7f0901bc, 0x7f020131, 0x7f0901be, 0x7f020125
    };
    private UICallBack mActionBarCallBack;
    private final android.view.View.OnClickListener mClickListener;
    private OnCommandListener mCommandListener;
    private View mCurSelView;
    private HorizontalScrollView mHorScrollView;

    public UIEffectBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCommandListener = null;
        mActionBarCallBack = null;
        mCurSelView = null;
        mHorScrollView = null;
        mClickListener = new android.view.View.OnClickListener() {

            final UIEffectBar this$0;

            public void onClick(View view)
            {
                mActionBarCallBack.callback();
                if (mCurSelView == null || mCurSelView.getId() != view.getId()) goto _L2; else goto _L1
_L1:
                return;
_L2:
                if (mCurSelView != null)
                {
                    mCurSelView.setSelected(false);
                }
                mCommandListener.onCommand(0, Integer.valueOf(1), null);
                view.getId();
                JVM INSTR tableswitch 2131296656 2131296702: default 288
            //                           2131296656 381
            //                           2131296657 288
            //                           2131296658 461
            //                           2131296659 288
            //                           2131296660 433
            //                           2131296661 288
            //                           2131296662 405
            //                           2131296663 288
            //                           2131296664 489
            //                           2131296665 288
            //                           2131296666 517
            //                           2131296667 288
            //                           2131296668 599
            //                           2131296669 288
            //                           2131296670 545
            //                           2131296671 288
            //                           2131296672 572
            //                           2131296673 288
            //                           2131296674 626
            //                           2131296675 288
            //                           2131296676 653
            //                           2131296677 288
            //                           2131296678 677
            //                           2131296679 288
            //                           2131296680 701
            //                           2131296681 288
            //                           2131296682 779
            //                           2131296683 288
            //                           2131296684 752
            //                           2131296685 288
            //                           2131296686 725
            //                           2131296687 288
            //                           2131296688 806
            //                           2131296689 288
            //                           2131296690 833
            //                           2131296691 288
            //                           2131296692 860
            //                           2131296693 288
            //                           2131296694 884
            //                           2131296695 288
            //                           2131296696 908
            //                           2131296697 288
            //                           2131296698 932
            //                           2131296699 288
            //                           2131296700 956
            //                           2131296701 288
            //                           2131296702 980;
                   goto _L3 _L4 _L3 _L5 _L3 _L6 _L3 _L7 _L3 _L8 _L3 _L9 _L3 _L10 _L3 _L11 _L3 _L12 _L3 _L13 _L3 _L14 _L3 _L15 _L3 _L16 _L3 _L17 _L3 _L18 _L3 _L19 _L3 _L20 _L3 _L21 _L3 _L22 _L3 _L23 _L3 _L24 _L3 _L25 _L3 _L26 _L3 _L27
_L3:
                int j;
                int l;
                mCurSelView = view;
                mCurSelView.setSelected(true);
                int ai[] = new int[2];
                mCurSelView.getLocationOnScreen(ai);
                int i = mCurSelView.getWidth();
                j = mHorScrollView.getWidth();
                int k = ai[0];
                l = k + i;
                if (k < 0)
                {
                    mHorScrollView.smoothScrollBy(k, 0);
                    return;
                }
                break; /* Loop/switch isn't completed */
_L4:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad300), null);
                continue; /* Loop/switch isn't completed */
_L7:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e0));
                continue; /* Loop/switch isn't completed */
_L6:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e1));
                continue; /* Loop/switch isn't completed */
_L5:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e2));
                continue; /* Loop/switch isn't completed */
_L8:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e3));
                continue; /* Loop/switch isn't completed */
_L9:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad32c), Integer.valueOf(0xaad4e4));
                continue; /* Loop/switch isn't completed */
_L11:
                mCommandListener.onCommand(22, Integer.valueOf(23), Integer.valueOf(1));
                continue; /* Loop/switch isn't completed */
_L12:
                mCommandListener.onCommand(22, Integer.valueOf(23), Integer.valueOf(2));
                continue; /* Loop/switch isn't completed */
_L10:
                mCommandListener.onCommand(22, Integer.valueOf(23), Integer.valueOf(0));
                continue; /* Loop/switch isn't completed */
_L13:
                mCommandListener.onCommand(22, Integer.valueOf(23), Integer.valueOf(3));
                continue; /* Loop/switch isn't completed */
_L14:
                mCommandListener.onCommand(22, Integer.valueOf(35), null);
                continue; /* Loop/switch isn't completed */
_L15:
                mCommandListener.onCommand(22, Integer.valueOf(24), null);
                continue; /* Loop/switch isn't completed */
_L16:
                mCommandListener.onCommand(22, Integer.valueOf(25), null);
                continue; /* Loop/switch isn't completed */
_L19:
                mCommandListener.onCommand(22, Integer.valueOf(36), Integer.valueOf(1));
                continue; /* Loop/switch isn't completed */
_L18:
                mCommandListener.onCommand(22, Integer.valueOf(36), Integer.valueOf(0));
                continue; /* Loop/switch isn't completed */
_L17:
                mCommandListener.onCommand(22, Integer.valueOf(36), Integer.valueOf(2));
                continue; /* Loop/switch isn't completed */
_L20:
                mCommandListener.onCommand(22, Integer.valueOf(36), Integer.valueOf(3));
                continue; /* Loop/switch isn't completed */
_L21:
                mCommandListener.onCommand(22, Integer.valueOf(36), Integer.valueOf(4));
                continue; /* Loop/switch isn't completed */
_L22:
                mCommandListener.onCommand(22, Integer.valueOf(33), null);
                continue; /* Loop/switch isn't completed */
_L23:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad301), null);
                continue; /* Loop/switch isn't completed */
_L24:
                mCommandListener.onCommand(9, Integer.valueOf(0xaad31f), null);
                continue; /* Loop/switch isn't completed */
_L25:
                mCommandListener.onCommand(7, Integer.valueOf(7), null);
                continue; /* Loop/switch isn't completed */
_L26:
                mCommandListener.onCommand(22, Integer.valueOf(34), null);
                continue; /* Loop/switch isn't completed */
_L27:
                mCommandListener.onCommand(22, Integer.valueOf(37), null);
                if (true) goto _L3; else goto _L28
_L28:
                if (l <= j) goto _L1; else goto _L29
_L29:
                mHorScrollView.smoothScrollBy(l - j, 0);
                return;
            }

            
            {
                this$0 = UIEffectBar.this;
                super();
            }
        };
    }

    public UIEffectBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mCommandListener = null;
        mActionBarCallBack = null;
        mCurSelView = null;
        mHorScrollView = null;
        mClickListener = new _cls1();
    }

    private static Bitmap scaleBitmap(Bitmap bitmap, int i, int j)
    {
        if (bitmap == null)
        {
            return null;
        }
        float f = (float)((1.0D * (double)i) / (double)bitmap.getWidth());
        float f1 = (float)((1.0D * (double)j) / (double)bitmap.getHeight());
        if (f == 0.0F || f1 == 0.0F)
        {
            return null;
        } else
        {
            Matrix matrix = new Matrix();
            matrix.postScale(f, f1);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
    }

    public void effectUIProcess(boolean flag, int i, int j, int k)
    {
        if (flag && mCurSelView != null && mCurSelView.getId() != 0x7f090190)
        {
            mCurSelView.setSelected(false);
            mCurSelView = null;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mHorScrollView = (HorizontalScrollView)findViewById(0x7f09018e);
        Drawable drawable = getResources().getDrawable(0x7f0202d3);
        if (drawable != null)
        {
            byte byte0;
            int i;
            if (drawable.getMinimumWidth() > 78)
            {
                byte0 = 102;
            } else
            {
                byte0 = 68;
            }
            for (i = 0; i < mEffectItem.length / 2; i++)
            {
                int j = mEffectItem[i * 2];
                ImageView imageview = (ImageView)findViewById(j);
                if (imageview == null)
                {
                    continue;
                }
                if (j == 0x7f090190)
                {
                    mCurSelView = imageview;
                    mCurSelView.setSelected(true);
                }
                imageview.setOnClickListener(mClickListener);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mEffectItem[1 + i * 2]);
                if (bitmap != null)
                {
                    imageview.setImageBitmap(scaleBitmap(bitmap, byte0, byte0));
                    bitmap.recycle();
                }
            }

        }
    }

    public void setActionBarCallBack(UICallBack uicallback)
    {
        mActionBarCallBack = uicallback;
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }





/*
    static View access$102(UIEffectBar uieffectbar, View view)
    {
        uieffectbar.mCurSelView = view;
        return view;
    }

*/


}
