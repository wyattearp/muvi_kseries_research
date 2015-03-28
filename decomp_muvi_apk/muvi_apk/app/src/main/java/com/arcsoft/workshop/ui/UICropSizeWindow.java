// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.arcsoft.workshop.WorkShop;

public class UICropSizeWindow
{
    public static interface IModeChangeListener
    {

        public abstract void changeCurrentModeName(String s);

        public abstract void setModeIndex(int i);
    }


    private static int IDS[] = {
        0x7f090038, 0x7f090039, 0x7f09003a, 0x7f09003b, 0x7f09003c, 0x7f09003d, 0x7f09003e, 0x7f09003f
    };
    private final int MSG_HIDE = 0;
    private final int MSG_INIT = 1;
    private String mAllModeNames[];
    protected View mBaseView;
    private int mClickBtnHeight;
    private int mClickBtnX;
    private int mClickBtnY;
    private int mClickBtnbtnWidth;
    private int mCurrentModeId;
    private int mCurrentModeIndex;
    private final Handler mHandler = new Handler() {

        final UICropSizeWindow this$0;

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 29
        //                       1 63;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            if (mPopupMenuWindow != null && mPopupMenuWindow.isShowing())
            {
                mPopupMenuWindow.dismiss();
                return;
            }
              goto _L1
_L3:
            mHandler.removeMessages(1);
            if (mBaseView.getHeight() != 0)
            {
                showPopupMenuWindow();
                return;
            } else
            {
                mHandler.sendEmptyMessageDelayed(1, 200L);
                return;
            }
        }

            
            {
                this$0 = UICropSizeWindow.this;
                super();
            }
    };
    private int mHeightLightColor;
    protected View mMainView;
    protected IModeChangeListener mModeChangedListener;
    protected android.view.View.OnClickListener mOnClickListener;
    private PopupWindow mPopupMenuWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;
    private WorkShop mWorkShop;

    public UICropSizeWindow(Context context)
    {
        mPopupMenuWindow = null;
        mWorkShop = null;
        mPopupWindowHeight = 132;
        mPopupWindowWidth = 376;
        mMainView = null;
        mBaseView = null;
        mCurrentModeIndex = 7;
        mCurrentModeId = IDS[7];
        mAllModeNames = null;
        mHeightLightColor = 0;
        mClickBtnX = 0;
        mClickBtnY = 0;
        mClickBtnHeight = 0;
        mClickBtnbtnWidth = 0;
        mModeChangedListener = null;
        mOnClickListener = new android.view.View.OnClickListener() {

            final UICropSizeWindow this$0;

            public void onClick(View view)
            {
                mCurrentModeId = view.getId();
                process();
            }

            
            {
                this$0 = UICropSizeWindow.this;
                super();
            }
        };
        mWorkShop = (WorkShop)context;
    }

    private boolean isClickInvalidOutside(int i, int j)
    {
        return i > mClickBtnX && i < mClickBtnX + mClickBtnbtnWidth && j > mClickBtnY && j < mClickBtnY + mClickBtnHeight;
    }

    private void refreshStatus()
    {
        int i = IDS.length;
        int j = 0;
        while (j < i) 
        {
            TextView textview = (TextView)mMainView.findViewById(IDS[j]);
            if (textview != null)
            {
                if (IDS[j] == mCurrentModeId)
                {
                    mCurrentModeIndex = j;
                    textview.setTextColor(mHeightLightColor);
                } else
                {
                    textview.setTextColor(-1);
                }
            }
            j++;
        }
    }

    private void updateClickInfo(int i, int j, int k, int l)
    {
        if (i > 0 && j > 0 && k > 0 && l > 0)
        {
            if (i != mClickBtnX)
            {
                mClickBtnX = i;
            }
            if (j != mClickBtnY)
            {
                mClickBtnY = j;
            }
            if (k != mClickBtnbtnWidth)
            {
                mClickBtnbtnWidth = k;
            }
            if (l != mClickBtnHeight)
            {
                mClickBtnHeight = l;
                return;
            }
        }
    }

    public void destory()
    {
        if (mPopupMenuWindow != null)
        {
            mPopupMenuWindow.dismiss();
            mPopupMenuWindow = null;
        }
    }

    public int getCurrentIndex()
    {
        return mCurrentModeIndex;
    }

    public String getCurrentMode()
    {
        if (mAllModeNames == null)
        {
            return "";
        } else
        {
            return mAllModeNames[mCurrentModeIndex];
        }
    }

    public void hide()
    {
        mHandler.sendEmptyMessageDelayed(0, 20L);
    }

    public void initPopupMenuWindow(View view)
    {
        if (mWorkShop != null)
        {
            mMainView = mWorkShop.getLayoutInflater().inflate(0x7f030009, null);
            mBaseView = view;
            if (mMainView != null)
            {
                initVIP();
                mPopupMenuWindow = new PopupWindow(mMainView, mPopupWindowWidth, mPopupWindowHeight);
                mPopupMenuWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopupMenuWindow.setOutsideTouchable(true);
                mPopupMenuWindow.setTouchable(true);
                mPopupMenuWindow.setTouchInterceptor(new android.view.View.OnTouchListener() {

                    final UICropSizeWindow this$0;

                    public boolean onTouch(View view1, MotionEvent motionevent)
                    {
                        if (isClickInvalidOutside((int)motionevent.getRawX(), (int)motionevent.getRawY()))
                        {
                            return true;
                        }
                        if (mBaseView != null)
                        {
                            ((CheckBox)mBaseView).setChecked(false);
                        }
                        return false;
                    }

            
            {
                this$0 = UICropSizeWindow.this;
                super();
            }
                });
                if (mHandler != null)
                {
                    mHandler.sendEmptyMessage(1);
                    return;
                }
            }
        }
    }

    protected void initVIP()
    {
        mAllModeNames = mWorkShop.getResources().getStringArray(0x7f06001e);
        int i = IDS.length;
        for (int j = 0; j < i; j++)
        {
            TextView textview = (TextView)mMainView.findViewById(IDS[j]);
            if (textview != null)
            {
                textview.setOnClickListener(mOnClickListener);
                textview.setText(mAllModeNames[j]);
            }
        }

        int ai[] = mWorkShop.getResources().getIntArray(0x7f060003);
        if (ai != null)
        {
            mPopupWindowWidth = ai[0];
            mPopupWindowHeight = ai[1];
        }
        mHeightLightColor = mWorkShop.getResources().getColor(0x7f070011);
    }

    public boolean isShowing()
    {
        if (mPopupMenuWindow == null)
        {
            return false;
        } else
        {
            return mPopupMenuWindow.isShowing();
        }
    }

    protected void process()
    {
        refreshStatus();
        if (mAllModeNames != null && mModeChangedListener != null)
        {
            mModeChangedListener.changeCurrentModeName(mAllModeNames[mCurrentModeIndex]);
            mModeChangedListener.setModeIndex(mCurrentModeIndex);
        }
    }

    public void retsetMode()
    {
        mCurrentModeIndex = 7;
        if (mModeChangedListener != null && mAllModeNames != null)
        {
            mCurrentModeId = IDS[mCurrentModeIndex];
            mModeChangedListener.changeCurrentModeName(mAllModeNames[mCurrentModeIndex]);
        }
    }

    public void setModeChangeListener(IModeChangeListener imodechangelistener)
    {
        mModeChangedListener = imodechangelistener;
    }

    public void showPopupMenuWindow()
    {
        if (mPopupMenuWindow == null || mPopupMenuWindow.isShowing() || mWorkShop == null || mBaseView == null)
        {
            return;
        } else
        {
            refreshStatus();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            mWorkShop.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int ai[] = new int[2];
            mBaseView.getLocationOnScreen(ai);
            updateClickInfo(ai[0], ai[1], mBaseView.getWidth(), mBaseView.getHeight());
            int i = displaymetrics.heightPixels - ai[1];
            int j = displaymetrics.widthPixels - (ai[0] + (mPopupWindowWidth + mBaseView.getWidth()) / 2);
            mPopupMenuWindow.showAtLocation(mBaseView, 85, j, i);
            ((CheckBox)mBaseView).setChecked(true);
            return;
        }
    }






/*
    static int access$302(UICropSizeWindow uicropsizewindow, int i)
    {
        uicropsizewindow.mCurrentModeId = i;
        return i;
    }

*/
}
