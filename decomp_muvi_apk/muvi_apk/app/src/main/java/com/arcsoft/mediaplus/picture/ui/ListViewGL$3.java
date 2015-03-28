// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.picture.ui;

import com.arcsoft.mediaplus.MediaPlusActivity;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus.picture.ui:
//            ListViewGL

class this._cls0
    implements com.arcsoft.mediaplus.datasource.nDataBuildListener
{

    final ListViewGL this$0;

    public void onDataBuiltBegin()
    {
        Log.w("ThumbGlView", "=== onDataBuiltBegin === ");
        ((MediaPlusActivity)mContext).showLoadingDialog();
        onDataBuildBegine();
        setDataBuildState(1);
    }

    public void onDataBuiltFinish()
    {
        Log.w("ThumbGlView", "=== onDataBuiltFinish === ");
        if (mDataSource != null)
        {
            ((MediaPlusActivity)mContext).showHideViews();
            boolean flag;
            if (mDataSource.getCount() == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                ListViewGL.this.onDataBuiltFinish(0);
            }
        }
        ((MediaPlusActivity)mContext).dismissLoadingDialog();
        setDataBuildState(0);
        setLastUpdateTime(System.currentTimeMillis());
    }

    urce()
    {
        this$0 = ListViewGL.this;
        super();
    }
}
