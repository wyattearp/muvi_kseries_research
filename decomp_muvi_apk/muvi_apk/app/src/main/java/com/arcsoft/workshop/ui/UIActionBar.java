// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.LevelListDrawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.arcsoft.mediaplus.widget.PopupMenuWindow;
import com.arcsoft.workshop.OnCommandListener;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UIMiniatureLineView, IManagerViewProcess, UICallBack

public class UIActionBar extends LinearLayout
{

    private static final int CIRCLESTATE = 1;
    private static final int NONESTATE = 0;
    private static final int RECTSTATE = 2;
    private final UICallBack mActionBarCallBack;
    private View mAutoFixView;
    private boolean mAutoFixViewSelected;
    private View mCancelView;
    private final android.view.View.OnClickListener mClickListener;
    private OnCommandListener mCommandListener;
    private Activity mContext;
    private int mCurState;
    private IManagerViewProcess mManagerViewProcess;
    private UIMiniatureLineView mMiniatureLineView;
    private ImageButton mMiniatureView;
    private final com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener mOnMenuClickedListener;
    private PopupMenuWindow mPopupMenuWindow;
    private View mShareView;

    public UIActionBar(Context context)
    {
        super(context);
        mCancelView = null;
        mMiniatureView = null;
        mAutoFixView = null;
        mShareView = null;
        mMiniatureLineView = null;
        mCurState = 0;
        mAutoFixViewSelected = false;
        mManagerViewProcess = null;
        mCommandListener = null;
        mContext = null;
        mPopupMenuWindow = null;
        mClickListener = new android.view.View.OnClickListener() {

            final UIActionBar this$0;

            public void onClick(View view)
            {
                view.getId();
                JVM INSTR tableswitch 2131296644 2131296647: default 36
            //                           2131296644 37
            //                           2131296645 476
            //                           2131296646 54
            //                           2131296647 611;
                   goto _L1 _L2 _L3 _L4 _L5
_L1:
                return;
_L2:
                mCommandListener.onCommand(4, null, null);
                return;
_L4:
                if (mMiniatureLineView == null)
                {
                    mMiniatureLineView = new UIMiniatureLineView(getContext());
                    MRect mrect = new MRect();
                    mCommandListener.onCommand(18, mrect, null);
                    android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(mrect.width(), mrect.height());
                    layoutparams.leftMargin = mrect.left;
                    layoutparams.topMargin = mrect.top;
                    mManagerViewProcess.addView(mMiniatureLineView, layoutparams);
                    mManagerViewProcess.registerTouchDistribution(mMiniatureLineView);
                    mMiniatureLineView.setOnCommandListener(mCommandListener);
                    mMiniatureLineView.setImageRect(mrect);
                    mMiniatureLineView.setDrawType(UIMiniatureLineView.DrawType.NONE);
                }
                if (mCurState == 0)
                {
                    mCurState = 2;
                    ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(2);
                    mMiniatureLineView.setDrawType(UIMiniatureLineView.DrawType.RECT);
                    return;
                }
                if (mCurState == 2)
                {
                    mCurState = 1;
                    ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(1);
                    mMiniatureLineView.setDrawType(UIMiniatureLineView.DrawType.CIRCLE);
                    return;
                }
                if (mCurState == 1)
                {
                    mCurState = 0;
                    ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(0);
                    mMiniatureLineView.setDrawType(UIMiniatureLineView.DrawType.NONE);
                    mMiniatureLineView.uninit();
                    mManagerViewProcess.removeView(mMiniatureLineView);
                    mManagerViewProcess.registerTouchDistribution(null);
                    mMiniatureLineView = null;
                    mCommandListener.onCommand(0, Integer.valueOf(1), null);
                    mCommandListener.onCommand(21, null, null);
                    return;
                }
                  goto _L1
_L3:
                mActionBarCallBack.callback();
                if (mAutoFixViewSelected)
                {
                    mAutoFixView.setSelected(false);
                    mAutoFixViewSelected = false;
                    mCommandListener.onCommand(0, Integer.valueOf(1), null);
                    mCommandListener.onCommand(19, null, null);
                    return;
                } else
                {
                    mAutoFixView.setSelected(true);
                    mAutoFixViewSelected = true;
                    mCommandListener.onCommand(0, Integer.valueOf(1), null);
                    mCommandListener.onCommand(1, null, null);
                    return;
                }
_L5:
                showPopupMenu(view);
                return;
            }

            
            {
                this$0 = UIActionBar.this;
                super();
            }
        };
        mOnMenuClickedListener = new com.arcsoft.mediaplus.widget.PopupMenuWindow.OnMenuClickedListener() {

            final UIActionBar this$0;

            public void onClicked(int i)
            {
                if (i != 0) goto _L2; else goto _L1
_L1:
                mCommandListener.onCommand(38, null, null);
_L4:
                if (mPopupMenuWindow != null)
                {
                    mPopupMenuWindow.hidePopopMenuWindow();
                }
                return;
_L2:
                if (i == 1)
                {
                    mCommandListener.onCommand(39, null, null);
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = UIActionBar.this;
                super();
            }
        };
        mActionBarCallBack = new UICallBack() {

            final UIActionBar this$0;

            public void callback()
            {
                if (mMiniatureLineView != null)
                {
                    mMiniatureLineView.setVisibility(4);
                }
            }

            
            {
                this$0 = UIActionBar.this;
                super();
            }
        };
        mContext = (Activity)context;
    }

    public UIActionBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCancelView = null;
        mMiniatureView = null;
        mAutoFixView = null;
        mShareView = null;
        mMiniatureLineView = null;
        mCurState = 0;
        mAutoFixViewSelected = false;
        mManagerViewProcess = null;
        mCommandListener = null;
        mContext = null;
        mPopupMenuWindow = null;
        mClickListener = new _cls1();
        mOnMenuClickedListener = new _cls2();
        mActionBarCallBack = new _cls3();
        mContext = (Activity)context;
    }

    private void showPopupMenu(View view)
    {
        if (mContext != null)
        {
            if (mPopupMenuWindow == null)
            {
                mPopupMenuWindow = new PopupMenuWindow(mContext);
                mPopupMenuWindow.setWindowParameter(mContext.getResources().getDimensionPixelSize(0x7f08001b), mContext.getResources().getDimensionPixelSize(0x7f08001a));
                mPopupMenuWindow.setOnMenuClickedListener(mOnMenuClickedListener);
            }
            if (!mPopupMenuWindow.isShowing())
            {
                mPopupMenuWindow.showPopupMenuWindow(view, 0, mContext.getResources().getInteger(0x7f0a0021));
                return;
            }
        }
    }

    public void autofixUIProcess(boolean flag)
    {
        if (flag)
        {
            mAutoFixView.setSelected(true);
            mAutoFixViewSelected = true;
            return;
        } else
        {
            mAutoFixView.setSelected(false);
            mAutoFixViewSelected = false;
            return;
        }
    }

    public UICallBack getActionBarCallBack()
    {
        return mActionBarCallBack;
    }

    public void miniatureUIProcess(int i)
    {
        if (i != 0) goto _L2; else goto _L1
_L1:
        mCurState = 1;
        ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(1);
        if (mMiniatureLineView != null)
        {
            mMiniatureLineView.setVisibility(8);
        }
_L4:
        return;
_L2:
        if (i != 1)
        {
            continue; /* Loop/switch isn't completed */
        }
        mCurState = 2;
        ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(2);
        if (mMiniatureLineView != null)
        {
            mMiniatureLineView.setVisibility(8);
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (i != -1) goto _L4; else goto _L3
_L3:
        mCurState = 0;
        ((LevelListDrawable)mMiniatureView.getBackground()).setLevel(0);
        if (mMiniatureLineView != null)
        {
            mMiniatureLineView.uninit();
            mManagerViewProcess.removeView(mMiniatureLineView);
            mManagerViewProcess.registerTouchDistribution(null);
            mMiniatureLineView = null;
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        mCancelView = findViewById(0x7f090184);
        mCancelView.setOnClickListener(mClickListener);
        mMiniatureView = (ImageButton)findViewById(0x7f090186);
        mMiniatureView.setOnClickListener(mClickListener);
        mAutoFixView = findViewById(0x7f090185);
        mAutoFixView.setOnClickListener(mClickListener);
        mShareView = findViewById(0x7f090187);
        mShareView.setOnClickListener(mClickListener);
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        if (mPopupMenuWindow != null && mPopupMenuWindow.isShowing())
        {
            mPopupMenuWindow.hidePopopMenuWindow();
        }
    }

    public void resumeProcess()
    {
        if (mMiniatureLineView != null)
        {
            mMiniatureLineView.setVisibility(0);
            mMiniatureLineView.resumeProcess();
        }
    }

    public void setManagerViewProcess(IManagerViewProcess imanagerviewprocess)
    {
        mManagerViewProcess = imanagerviewprocess;
    }

    public void setOnCommandListener(OnCommandListener oncommandlistener)
    {
        mCommandListener = oncommandlistener;
    }




/*
    static UIMiniatureLineView access$102(UIActionBar uiactionbar, UIMiniatureLineView uiminiaturelineview)
    {
        uiactionbar.mMiniatureLineView = uiminiaturelineview;
        return uiminiaturelineview;
    }

*/




/*
    static int access$302(UIActionBar uiactionbar, int i)
    {
        uiactionbar.mCurState = i;
        return i;
    }

*/





/*
    static boolean access$602(UIActionBar uiactionbar, boolean flag)
    {
        uiactionbar.mAutoFixViewSelected = flag;
        return flag;
    }

*/



}
