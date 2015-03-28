// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.WorkShop;
import com.arcsoft.workshop.utils.WorkShopUtils;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UISaveDialog

public class UICropBar extends RelativeLayout
{
    private class CropHandlerThread extends HandlerThread
    {

        private final Handler mUIHandle = new _cls1();
        final UICropBar this$0;

        public void run()
        {
            mWorkShop.setBgState(1);
            new MRect();
            MRect mrect = mCropRectProp.getCropRect();
            MRect mrect1 = new MRect();
            mCommandListener.onCommand(18, mrect1, null);
            mCommandListener.onCommand(0, Integer.valueOf(1), null);
            if (mrect1.width() == mrect.width() && mrect1.height() == mrect.height())
            {
                WorkShopUtils.back2BestFitModeForEdit(mWorkShop);
                mUIHandle.sendEmptyMessage(0);
                return;
            } else
            {
                mCommandListener.onCommand(17, new Rect(mrect.left, mrect.top, mrect.right, mrect.bottom), null);
                mUIHandle.sendEmptyMessage(0);
                return;
            }
        }

        public CropHandlerThread(String s)
        {
            this$0 = UICropBar.this;
            super(s);
        }
    }

    public static interface ICropRectProp
    {

        public abstract MRect getCropRect();
    }

    public static interface ICropSwitchEdit
    {

        public abstract void switchToEdit();
    }


    private final android.view.View.OnClickListener mClickListener;
    private OnCommandListener mCommandListener;
    private ICropRectProp mCropRectProp;
    private ICropSwitchEdit mCropSwitchEdit;
    private boolean mHasClicked;
    private UISaveDialog mProgressDlg;
    private WorkShop mWorkShop;

    public UICropBar(Context context)
    {
        super(context);
        mWorkShop = null;
        mCommandListener = null;
        mCropSwitchEdit = null;
        mCropRectProp = null;
        mHasClicked = false;
        mClickListener = new android.view.View.OnClickListener() {

            final UICropBar this$0;

            public void onClick(View view)
            {
                if (!mHasClicked)
                {
                    mHasClicked = true;
                    switch (view.getId())
                    {
                    default:
                        return;

                    case 2131296649: 
                        mCommandListener.onCommand(4, null, null);
                        return;

                    case 2131296650: 
                        break;
                    }
                    if (mCropRectProp != null)
                    {
                        mProgressDlg = new UISaveDialog(getContext(), 0x7f0d0003);
                        mProgressDlg.show();
                        (new CropHandlerThread("CropThread")).start();
                        return;
                    }
                }
            }

            
            {
                this$0 = UICropBar.this;
                super();
            }
        };
        mProgressDlg = null;
        mWorkShop = (WorkShop)context;
    }

    public UICropBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWorkShop = null;
        mCommandListener = null;
        mCropSwitchEdit = null;
        mCropRectProp = null;
        mHasClicked = false;
        mClickListener = new _cls1();
        mProgressDlg = null;
        mWorkShop = (WorkShop)context;
    }

    public UICropBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mWorkShop = null;
        mCommandListener = null;
        mCropSwitchEdit = null;
        mCropRectProp = null;
        mHasClicked = false;
        mClickListener = new _cls1();
        mProgressDlg = null;
        mWorkShop = (WorkShop)context;
    }

    public boolean isNeedToDrawCrop()
    {
        return !mHasClicked;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        findViewById(0x7f090189).setOnClickListener(mClickListener);
        findViewById(0x7f09018a).setOnClickListener(mClickListener);
    }

    public void setCropRectProp(ICropRectProp icroprectprop)
    {
        mCropRectProp = icroprectprop;
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }

    public void setSwitchToEdit(ICropSwitchEdit icropswitchedit)
    {
        mCropSwitchEdit = icropswitchedit;
    }



/*
    static boolean access$002(UICropBar uicropbar, boolean flag)
    {
        uicropbar.mHasClicked = flag;
        return flag;
    }

*/




/*
    static UISaveDialog access$202(UICropBar uicropbar, UISaveDialog uisavedialog)
    {
        uicropbar.mProgressDlg = uisavedialog;
        return uisavedialog;
    }

*/




    // Unreferenced inner class com/arcsoft/workshop/ui/UICropBar$CropHandlerThread$1

/* anonymous class */
    class CropHandlerThread._cls1 extends Handler
    {

        final CropHandlerThread this$1;

        public void handleMessage(Message message)
        {
            if (message.what == 0)
            {
                mProgressDlg.dismiss();
                mCropSwitchEdit.switchToEdit();
            }
        }

            
            {
                this$1 = CropHandlerThread.this;
                super();
            }
    }

}
