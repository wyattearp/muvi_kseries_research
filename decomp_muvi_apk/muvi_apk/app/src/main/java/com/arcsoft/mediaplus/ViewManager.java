// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.app.Activity;
import android.widget.RelativeLayout;
import com.arcsoft.mediaplus.datasource.IDataSource;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            TitleBar, ControlBar, ListViewManager

public class ViewManager
{

    public static int mCurrentViewStatus = 0;
    private Activity mContext;
    private ControlBar mControlBar;
    private ListViewManager mListViewManager;
    private RelativeLayout mMainLayout;
    private TitleBar mTitleBar;

    public ViewManager(Activity activity, RelativeLayout relativelayout)
    {
        mContext = null;
        mMainLayout = null;
        mListViewManager = null;
        mTitleBar = null;
        mControlBar = null;
        if (activity != null && relativelayout != null)
        {
            mContext = activity;
            mMainLayout = relativelayout;
            mTitleBar = (TitleBar)mMainLayout.findViewById(0x7f0900a3);
            if (mTitleBar != null)
            {
                mTitleBar.InitUI();
            }
            mControlBar = (ControlBar)mMainLayout.findViewById(0x7f09002b);
            if (mControlBar != null)
            {
                mControlBar.InitUI();
                return;
            }
        }
    }

    public static int getViewStatus()
    {
        return mCurrentViewStatus;
    }

    public static void setViewStatus(int i)
    {
        mCurrentViewStatus = i;
    }

    public void onConfigurationChanged()
    {
        if (mControlBar != null)
        {
            mControlBar.onConfigurationChanged();
        }
    }

    public void refreshControlBar(int i)
    {
        if (i == 0)
        {
            mControlBar.setAllBtnEnable(true);
            return;
        } else
        {
            mControlBar.setAllBtnEnable(false);
            return;
        }
    }

    public void refreshSelectorTitle(int i)
    {
        if (getViewStatus() == 2)
        {
            mTitleBar.updateSelectCount(i);
            TitleBar titlebar = mTitleBar;
            ListViewManager _tmp = mListViewManager;
            titlebar.updateSelectIconStatus(ListViewManager.getSelectedItemsNum(), mListViewManager.getTotalMediaCount());
        }
    }

    public void refreshTitleAndControlbar(int i, int j)
    {
        if (mTitleBar != null)
        {
            mTitleBar.switchTitle(j);
        }
        if (mControlBar != null)
        {
            mControlBar.switchControlBar(j);
            if (mListViewManager != null && mListViewManager.getDataSource() != null && mListViewManager.getDataSource().getCount() <= 0)
            {
                mControlBar.setAllBtnEnable(false);
            }
        }
        if (i == 0 && j == 1 || i == 1 && j == 0)
        {
            refreshControlBar(8);
        }
    }

    public void release()
    {
        mContext = null;
        if (mTitleBar != null)
        {
            mTitleBar.destroy();
            mTitleBar = null;
        }
        if (mControlBar != null)
        {
            mControlBar.destroy();
            mControlBar = null;
        }
    }

    public void setAllBtnEnable(boolean flag)
    {
        mTitleBar.setAllBtnEnable(flag);
        mControlBar.setAllBtnEnable(flag);
    }

    public void setCancelAllBtnEnabled(boolean flag)
    {
        mControlBar.setCancelAllBtnEnabled(flag);
    }

    public void setControlBarEnable(boolean flag)
    {
        mControlBar.setAllBtnEnable(flag);
    }

    public void setControlBarExecuting(boolean flag)
    {
        if (mControlBar != null)
        {
            mControlBar.setExcuting(flag);
        }
    }

    public void setListViewManager(ListViewManager listviewmanager)
    {
        mListViewManager = listviewmanager;
        if (mControlBar != null)
        {
            mControlBar.setListViewManager(mListViewManager);
        }
    }

    public void showControlBar(int i)
    {
        mControlBar.setVisibility(i);
    }

    public void switchToView(int i, int j)
    {
        Log.d("FENG", (new StringBuilder()).append("FENG ViewManager switchToView fromView = ").append(i).append(", status = ").append(j).toString());
        mCurrentViewStatus = j;
        if (mListViewManager != null)
        {
            mListViewManager.switchView(i, j);
        }
        refreshTitleAndControlbar(i, j);
    }

}
