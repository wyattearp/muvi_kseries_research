// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.arcsoft.util.SystemUtils;
import com.arcsoft.workshop.OnCommandListener;
import com.arcsoft.workshop.OnImageEventListener;
import com.arcsoft.workshop.WorkShop;
import powermobia.utils.MBitmap;
import powermobia.utils.MRect;

// Referenced classes of package com.arcsoft.workshop.ui:
//            UICropStyleBar, UICropBar, EditorView, UIActionBar, 
//            UIEffectBar, IManagerViewProcess

public class UIManagerConsole
    implements OnImageEventListener
{
    public static interface IUIMethodForTools
    {

        public abstract void autofixUIProcess(boolean flag);

        public abstract void effectUIProcess(boolean flag, int i, int j, int k);

        public abstract void errorForReset();

        public abstract void facebeautifyFailed();

        public abstract void miniatureUIProcess(int i);

        public abstract void resetUI();
    }


    private UIActionBar mActionBar;
    private UICropBar mCropBar;
    private UICropStyleBar mCropStyleBar;
    private EditorView mEditorView;
    private UIEffectBar mEffectBar;
    private ViewGroup mMainViewGroup;
    private final IManagerViewProcess mManagerViewProcess = new IManagerViewProcess() {

        final UIManagerConsole this$0;

        public void addView(View view)
        {
            if (view != null)
            {
                mMainViewGroup.addView(view);
                if (mCropBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mCropBar);
                }
                if (mCropStyleBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mCropStyleBar);
                }
                if (mActionBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mActionBar);
                }
                if (mEffectBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mEffectBar);
                }
            }
        }

        public void addView(View view, android.widget.RelativeLayout.LayoutParams layoutparams)
        {
            if (view != null)
            {
                mMainViewGroup.addView(view, layoutparams);
                if (mCropBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mCropBar);
                }
                if (mCropStyleBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mCropStyleBar);
                }
                if (mActionBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mActionBar);
                }
                if (mEffectBar.getVisibility() == 0)
                {
                    mMainViewGroup.bringChildToFront(mEffectBar);
                }
            }
        }

        public void registerTouchDistribution(EditorView.ITouchDistribution itouchdistribution)
        {
            if (mEditorView != null)
            {
                mEditorView.registerITouchDistribution(itouchdistribution);
            }
        }

        public void removeView(View view)
        {
            if (view != null)
            {
                mMainViewGroup.removeView(view);
            }
        }

            
            {
                this$0 = UIManagerConsole.this;
                super();
            }
    };
    private OnCommandListener mUICommandListener;
    private final IUIMethodForTools mUIMethodForTools = new IUIMethodForTools() {

        final UIManagerConsole this$0;

        public void autofixUIProcess(boolean flag)
        {
            if (mActionBar != null)
            {
                mActionBar.autofixUIProcess(flag);
            }
        }

        public void effectUIProcess(boolean flag, int i, int j, int k)
        {
            if (mEffectBar != null)
            {
                mEffectBar.effectUIProcess(flag, i, j, k);
            }
        }

        public void errorForReset()
        {
            if (mActionBar != null)
            {
                mActionBar.autofixUIProcess(false);
            }
            if (mActionBar != null)
            {
                mActionBar.miniatureUIProcess(-1);
            }
            if (mEffectBar != null)
            {
                mEffectBar.effectUIProcess(true, 0, 0, 0);
            }
            Toast toast = Toast.makeText(mWorkShop, 0x7f0b0156, 1);
            int i = mWorkShop.getResources().getInteger(0x7f0a001b);
            float f = mWorkShop.getResources().getDisplayMetrics().density;
            toast.setGravity(1, 0, (int)((float)i + 10F * f));
            toast.show();
        }

        public void facebeautifyFailed()
        {
        }

        public void miniatureUIProcess(int i)
        {
            if (mActionBar != null)
            {
                mActionBar.miniatureUIProcess(i);
            }
        }

        public void resetUI()
        {
            if (mActionBar != null)
            {
                mActionBar.autofixUIProcess(false);
            }
            if (mActionBar != null)
            {
                mActionBar.miniatureUIProcess(-1);
            }
            if (mEffectBar != null)
            {
                mEffectBar.effectUIProcess(true, 0, 0, 0);
            }
            Toast toast = Toast.makeText(mWorkShop, 0x7f0b0157, 1);
            int i = mWorkShop.getResources().getInteger(0x7f0a001b);
            float f = mWorkShop.getResources().getDisplayMetrics().density;
            toast.setGravity(1, 0, (int)((float)i + 10F * f));
            toast.show();
        }

            
            {
                this$0 = UIManagerConsole.this;
                super();
            }
    };
    private WorkShop mWorkShop;

    public UIManagerConsole(Context context, OnCommandListener oncommandlistener)
    {
        mWorkShop = null;
        mUICommandListener = null;
        mMainViewGroup = null;
        mEditorView = null;
        mActionBar = null;
        mEffectBar = null;
        mCropBar = null;
        mCropStyleBar = null;
        mWorkShop = (WorkShop)context;
        mUICommandListener = oncommandlistener;
    }

    public void doDraw(MBitmap mbitmap, Bitmap bitmap, MRect mrect)
    {
        if (mCropStyleBar.getVisibility() == 0 && mCropBar.isNeedToDrawCrop())
        {
            mCropStyleBar.doDraw(bitmap);
        }
        if (mEditorView != null)
        {
            mEditorView.doDraw(mbitmap, mrect);
        }
    }

    public IUIMethodForTools getIUIMethodForTools()
    {
        return mUIMethodForTools;
    }

    public void initCropView()
    {
        mCropStyleBar.init(mEditorView.getEditorViewMultiTouchListener());
    }

    public void initUI(com.arcsoft.workshop.ZoomPanController.OnZoomGestureListener onzoomgesturelistener, com.arcsoft.workshop.ZoomPanController.OnPanGestureListener onpangesturelistener)
    {
        View view = mWorkShop.getLayoutInflater().inflate(0x7f030047, null);
        mWorkShop.setContentView(view);
        mMainViewGroup = (ViewGroup)view.findViewById(0x7f0901bf);
        mEditorView = (EditorView)view.findViewById(0x7f0901c0);
        mEditorView.setOnZoomGestureListener(onzoomgesturelistener);
        mEditorView.setOnPanGestureListener(onpangesturelistener);
        mEditorView.setOnCommandListener(mUICommandListener);
        mEditorView.setPanZoomEnabeState(true);
        mCropStyleBar = (UICropStyleBar)view.findViewById(0x7f09018b);
        mCropStyleBar.setOnCommandListener(mUICommandListener);
        mCropStyleBar.setManagerViewProcess(mManagerViewProcess);
        mCropBar = (UICropBar)view.findViewById(0x7f090188);
        mCropBar.setOnCommandListener(mUICommandListener);
        mCropBar.setCropRectProp(mCropStyleBar.getCropRectProp());
        mCropBar.setSwitchToEdit(new UICropBar.ICropSwitchEdit() {

            final UIManagerConsole this$0;

            public void switchToEdit()
            {
                if (mEditorView != null)
                {
                    mEditorView.setPanZoomEnabeState(false);
                }
                mCropStyleBar.uninit();
                mCropBar.setVisibility(8);
                mCropStyleBar.setVisibility(8);
                mActionBar.setVisibility(0);
                mEffectBar.setVisibility(0);
            }

            
            {
                this$0 = UIManagerConsole.this;
                super();
            }
        });
        mActionBar = (UIActionBar)view.findViewById(0x7f090183);
        mActionBar.setOnCommandListener(mUICommandListener);
        mActionBar.setManagerViewProcess(mManagerViewProcess);
        mEffectBar = (UIEffectBar)view.findViewById(0x7f09018e);
        mEffectBar.setOnCommandListener(mUICommandListener);
        mEffectBar.setActionBarCallBack(mActionBar.getActionBarCallBack());
    }

    public boolean isSurfaceDestory()
    {
        if (mEditorView != null)
        {
            mEditorView.isSurfaceDestory();
        }
        return false;
    }

    public void onChange(int i, Object obj, Object obj1)
    {
        i;
        JVM INSTR tableswitch 2 4: default 28
    //                   2 29
    //                   3 54
    //                   4 79;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if (mCropBar != null && mCropBar.getVisibility() == 0)
        {
            mCropStyleBar.onPreCoordChanged();
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (mCropBar != null && mCropBar.getVisibility() == 0)
        {
            mCropStyleBar.onPostCoordChanged();
            return;
        }
        if (true) goto _L1; else goto _L4
_L4:
        mUIMethodForTools.autofixUIProcess(false);
        mUIMethodForTools.miniatureUIProcess(-1);
        mUIMethodForTools.effectUIProcess(true, 0, 0, 0);
        return;
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
        if (mActionBar != null)
        {
            mActionBar.onPrepareOptionsMenu(menu);
        }
    }

    public void resetEditorViewState()
    {
        if (mEditorView != null)
        {
            mEditorView.resetState();
        }
    }

    public void resumeProcess()
    {
        if (mActionBar != null)
        {
            mActionBar.resumeProcess();
        }
    }

    public void uninitUI()
    {
        if (mMainViewGroup != null)
        {
            SystemUtils.unbindDrawables(mMainViewGroup);
            mMainViewGroup = null;
        }
    }







}
