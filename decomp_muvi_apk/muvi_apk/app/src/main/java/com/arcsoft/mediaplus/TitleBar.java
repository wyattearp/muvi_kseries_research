// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            ViewManager, MediaPlusActivity

public class TitleBar extends RelativeLayout
{

    public static final int SWITCHER_LOCAL = 1;
    public static final int SWITCHER_REMOTE = 0;
    public static final int SWITCHER_UNKNOWN = -1;
    private Context mContext;
    private TextView mGeneralTextView;
    private boolean mIsSwitchFromUser;
    private ImageView mLeftDivider;
    private ImageView mLocalBtn;
    private TextView mMediaCountView;
    private ImageView mRemoteBtn;
    private TextView mSelectedCountView;
    private SeekBar mSwitcher;
    private RelativeLayout mSwitcherLayout;
    private ImageView mTitleLeftBtn;
    private ImageView mTitleRightBtn;

    public TitleBar(Context context)
    {
        super(context);
        mContext = null;
        mSwitcherLayout = null;
        mTitleLeftBtn = null;
        mTitleRightBtn = null;
        mLeftDivider = null;
        mRemoteBtn = null;
        mLocalBtn = null;
        mSwitcher = null;
        mIsSwitchFromUser = false;
        mMediaCountView = null;
        mSelectedCountView = null;
        mGeneralTextView = null;
        mContext = context;
    }

    public TitleBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = null;
        mSwitcherLayout = null;
        mTitleLeftBtn = null;
        mTitleRightBtn = null;
        mLeftDivider = null;
        mRemoteBtn = null;
        mLocalBtn = null;
        mSwitcher = null;
        mIsSwitchFromUser = false;
        mMediaCountView = null;
        mSelectedCountView = null;
        mGeneralTextView = null;
        mContext = context;
    }

    public TitleBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mContext = null;
        mSwitcherLayout = null;
        mTitleLeftBtn = null;
        mTitleRightBtn = null;
        mLeftDivider = null;
        mRemoteBtn = null;
        mLocalBtn = null;
        mSwitcher = null;
        mIsSwitchFromUser = false;
        mMediaCountView = null;
        mSelectedCountView = null;
        mGeneralTextView = null;
        mContext = context;
    }

    private void ctrlLeftResourceVis(boolean flag, boolean flag1)
    {
        if (mTitleLeftBtn == null || mLeftDivider == null)
        {
            return;
        }
        if (flag1)
        {
            mTitleLeftBtn.setImageResource(0x7f0201d3);
            mTitleLeftBtn.setVisibility(8);
            mLeftDivider.setVisibility(8);
            return;
        }
        mTitleLeftBtn.setImageResource(0x7f0201ca);
        ImageView imageview = mTitleLeftBtn;
        int i;
        ImageView imageview1;
        int j;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 8;
        }
        imageview.setVisibility(i);
        imageview1 = mLeftDivider;
        j = 0;
        if (!flag)
        {
            j = 8;
        }
        imageview1.setVisibility(j);
    }

    private void initListener()
    {
        mTitleLeftBtn.setOnClickListener(new android.view.View.OnClickListener() {

            final TitleBar this$0;

            public void onClick(View view)
            {
                if (ViewManager.getViewStatus() == 0)
                {
                    ((MediaPlusActivity)mContext).refreshRemoteView();
                    return;
                } else
                {
                    ((MediaPlusActivity)mContext).onBackPressed();
                    return;
                }
            }

            
            {
                this$0 = TitleBar.this;
                super();
            }
        });
        mTitleRightBtn.setOnClickListener(new android.view.View.OnClickListener() {

            final TitleBar this$0;

            public void onClick(View view)
            {
                switch (ViewManager.getViewStatus())
                {
                default:
                    return;

                case 0: // '\0'
                case 1: // '\001'
                    Log.e("FENG", "FENG mTitleRightBtn click");
                    ((MediaPlusActivity)mContext).startSettingActivity();
                    return;

                case 2: // '\002'
                    ((MediaPlusActivity)mContext).refreshMarkView();
                    break;
                }
            }

            
            {
                this$0 = TitleBar.this;
                super();
            }
        });
        mSwitcher.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {

            final TitleBar this$0;

            public void onProgressChanged(SeekBar seekbar, int i, boolean flag)
            {
                Log.v("zdf", (new StringBuilder()).append("onProgressChanged, progress = ").append(i).append(", fromUser = ").append(flag).toString());
                mIsSwitchFromUser = flag;
            }

            public void onStartTrackingTouch(SeekBar seekbar)
            {
                Log.v("zdf", "onStartTrackingTouch");
                mIsSwitchFromUser = false;
            }

            public void onStopTrackingTouch(SeekBar seekbar)
            {
                Log.v("zdf", (new StringBuilder()).append("onStopTrackingTouch, progress = ").append(seekbar.getProgress()).toString());
                if (!mIsSwitchFromUser)
                {
                    seekbar.setProgress(seekbar.getMax() - seekbar.getProgress());
                }
                MediaPlusActivity mediaplusactivity = (MediaPlusActivity)mContext;
                int i;
                if (seekbar.getProgress() == 0)
                {
                    i = 0;
                } else
                {
                    i = 1;
                }
                mediaplusactivity.switchView(i);
            }

            
            {
                this$0 = TitleBar.this;
                super();
            }
        });
        mRemoteBtn.setOnClickListener(new android.view.View.OnClickListener() {

            final TitleBar this$0;

            public void onClick(View view)
            {
                if (mSwitcher.getProgress() != 0)
                {
                    ((MediaPlusActivity)mContext).switchView(0);
                    mSwitcher.setProgress(0);
                    return;
                } else
                {
                    ((MediaPlusActivity)mContext).switchView(1);
                    mSwitcher.setProgress(1);
                    return;
                }
            }

            
            {
                this$0 = TitleBar.this;
                super();
            }
        });
        mLocalBtn.setOnClickListener(new android.view.View.OnClickListener() {

            final TitleBar this$0;

            public void onClick(View view)
            {
                if (mSwitcher.getProgress() != 1)
                {
                    ((MediaPlusActivity)mContext).switchView(1);
                    mSwitcher.setProgress(1);
                    return;
                } else
                {
                    ((MediaPlusActivity)mContext).switchView(0);
                    mSwitcher.setProgress(0);
                    return;
                }
            }

            
            {
                this$0 = TitleBar.this;
                super();
            }
        });
    }

    public void InitUI()
    {
        mTitleLeftBtn = (ImageView)findViewById(0x7f090101);
        mTitleRightBtn = (ImageView)findViewById(0x7f090109);
        mTitleLeftBtn.setImageResource(0x7f0201d3);
        mSwitcherLayout = (RelativeLayout)findViewById(0x7f090103);
        mRemoteBtn = (ImageView)findViewById(0x7f090104);
        ImageView imageview = mRemoteBtn;
        int i;
        ImageView imageview1;
        int j;
        if (ViewManager.getViewStatus() == 0)
        {
            i = 0x7f02007e;
        } else
        {
            i = 0x7f02007d;
        }
        imageview.setImageResource(i);
        mSwitcher = (SeekBar)findViewById(0x7f090105);
        mSwitcher.setMax(1);
        mLocalBtn = (ImageView)findViewById(0x7f090106);
        imageview1 = mLocalBtn;
        if (ViewManager.getViewStatus() == 0)
        {
            j = 0x7f020069;
        } else
        {
            j = 0x7f02006a;
        }
        imageview1.setImageResource(j);
        mLeftDivider = (ImageView)findViewById(0x7f090020);
        mGeneralTextView = (TextView)findViewById(0x7f090102);
        mMediaCountView = (TextView)findViewById(0x7f090107);
        mSelectedCountView = (TextView)findViewById(0x7f090108);
        updateSelectCount(0);
        initListener();
    }

    public void destroy()
    {
        mContext = null;
        if (mTitleLeftBtn != null)
        {
            mTitleLeftBtn = null;
        }
        if (mTitleRightBtn != null)
        {
            mTitleRightBtn = null;
        }
        if (mSwitcherLayout != null)
        {
            mSwitcherLayout = null;
        }
        if (mRemoteBtn != null)
        {
            mRemoteBtn = null;
        }
        if (mSwitcher != null)
        {
            mSwitcher = null;
        }
        if (mLocalBtn != null)
        {
            mLocalBtn = null;
        }
        if (mGeneralTextView != null)
        {
            mGeneralTextView = null;
        }
        if (mMediaCountView != null)
        {
            mMediaCountView = null;
        }
        if (mSelectedCountView != null)
        {
            mSelectedCountView = null;
        }
    }

    public int getCurrentViewType()
    {
        if (mSwitcher == null)
        {
            return -1;
        } else
        {
            return mSwitcher.getProgress();
        }
    }

    public void setAllBtnEnable(boolean flag)
    {
        switch (ViewManager.getViewStatus())
        {
        case 0: // '\0'
        case 1: // '\001'
        default:
            return;

        case 2: // '\002'
            mTitleLeftBtn.setEnabled(flag);
            break;
        }
        mTitleRightBtn.setEnabled(flag);
    }

    public void switchTitle(int i)
    {
        Log.v("zdf", (new StringBuilder()).append("switchTitle, status = ").append(i).toString());
        mGeneralTextView.setVisibility(8);
        mTitleRightBtn.setVisibility(0);
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            if (mSwitcher.getProgress() != 0)
            {
                mSwitcher.setProgress(0);
            }
            mSwitcherLayout.setVisibility(0);
            mMediaCountView.setVisibility(8);
            mSelectedCountView.setVisibility(8);
            mRemoteBtn.setImageResource(0x7f02007e);
            mLocalBtn.setImageResource(0x7f020069);
            ctrlLeftResourceVis(false, true);
            mTitleRightBtn.setImageResource(0x7f0201d8);
            return;

        case 1: // '\001'
            if (1 != mSwitcher.getProgress())
            {
                mSwitcher.setProgress(1);
            }
            mSwitcherLayout.setVisibility(0);
            mMediaCountView.setVisibility(8);
            mSelectedCountView.setVisibility(8);
            mRemoteBtn.setImageResource(0x7f02007d);
            mLocalBtn.setImageResource(0x7f02006a);
            mTitleRightBtn.setImageResource(0x7f0201d8);
            ctrlLeftResourceVis(false, false);
            return;

        case 2: // '\002'
            Log.d("FENG", "FENG title VIEW_STATUS_SELECTOR = ");
            mSwitcherLayout.setVisibility(8);
            mMediaCountView.setVisibility(8);
            mSelectedCountView.setVisibility(0);
            ctrlLeftResourceVis(true, false);
            mRemoteBtn.setImageResource(0x7f02007d);
            mLocalBtn.setImageResource(0x7f02006a);
            mTitleRightBtn.setImageResource(0x7f0201ce);
            return;

        case 3: // '\003'
            mSwitcherLayout.setVisibility(8);
            mMediaCountView.setVisibility(0);
            mSelectedCountView.setVisibility(8);
            mRemoteBtn.setImageResource(0x7f02007d);
            mLocalBtn.setImageResource(0x7f02006a);
            mTitleRightBtn.setImageResource(0x7f0201d1);
            return;

        case 4: // '\004'
            mSwitcherLayout.setVisibility(8);
            mMediaCountView.setVisibility(8);
            mSelectedCountView.setVisibility(8);
            mTitleRightBtn.setVisibility(8);
            ctrlLeftResourceVis(true, false);
            updateGeneralTextView(mContext.getResources().getString(0x7f0b002c));
            mGeneralTextView.setVisibility(0);
            return;
        }
    }

    public void updateGeneralTextView(String s)
    {
        if (mGeneralTextView != null)
        {
            mGeneralTextView.setText(s);
        }
    }

    public void updateSelectCount(int i)
    {
        String s;
        if (i == 0)
        {
            s = mContext.getResources().getString(0x7f0b0117);
        } else
        {
            Resources resources = mContext.getResources();
            int j;
            Object aobj[];
            if (i > 1)
            {
                j = 0x7f0b0118;
            } else
            {
                j = 0x7f0b0119;
            }
            aobj = new Object[1];
            aobj[0] = Integer.valueOf(i);
            s = resources.getString(j, aobj);
        }
        Log.d("FENG", (new StringBuilder()).append("FENG updateSelectCount IN title = ").append(s).toString());
        mSelectedCountView.setText(s);
    }

    public void updateSelectIconStatus(int i, int j)
    {
        if (i == j && i != 0)
        {
            mTitleRightBtn.setImageResource(0x7f0201d6);
            return;
        }
        if (i == 0)
        {
            mTitleRightBtn.setImageResource(0x7f0201ce);
            return;
        } else
        {
            mTitleRightBtn.setImageResource(0x7f0201d7);
            return;
        }
    }




/*
    static boolean access$102(TitleBar titlebar, boolean flag)
    {
        titlebar.mIsSwitchFromUser = flag;
        return flag;
    }

*/

}
