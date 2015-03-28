// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;

import android.view.View;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;
import com.arcsoft.util.debug.Log;

// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView, MediaPlusActivity

class this._cls0
    implements com.arcsoft.mediaplus.datasource.angeListener
{

    final MediaPlusListView this$0;

    public void onCountChanged(int i, int j)
    {
        Log.e("FENG", (new StringBuilder()).append("FENG onCountChanged() newCount = ").append(i).append(", oldCount = ").append(j).toString());
        Log.v("MediaPlusListView", (new StringBuilder()).append("=== onCountChanged start === oldCount ").append(j).append(" newCount ").append(i).toString());
        if (i > 0 && i != j)
        {
            ((MediaPlusActivity)mContext).clearMarkView();
        }
        onListCountChanged(i, j);
        clearListView(false);
        MediaPlusListView.access$000(MediaPlusListView.this, efetchType.Prefetch_ScrollStop);
        setFocusable(true);
        requestFocus();
    }

    public void onDataChanged(int i, Property property)
    {
        Log.d("MediaPlusListView", (new StringBuilder()).append("onDataChanged : index = ").append(i).append(", property = ").append(property).toString());
        if (i >= getFirstVisiblePosition() && i <= getLastVisiblePosition() && property == PictureDataSource.PROP_BITMAP)
        {
            int j = getChildCount();
            int k = 0;
            while (k < j) 
            {
                View view = getChildAt(k);
                Integer integer;
                if (view == null)
                {
                    integer = null;
                } else
                {
                    integer = (Integer)view.getTag();
                }
                if (integer == null || integer.intValue() != i)
                {
                    k++;
                } else
                {
                    Log.d("MediaPlusListView", (new StringBuilder()).append("onDataChanged index = ").append(i).toString());
                    fillHolder(createHolder(view), i);
                    return;
                }
            }
        }
    }

    taSource()
    {
        this$0 = MediaPlusListView.this;
        super();
    }
}
