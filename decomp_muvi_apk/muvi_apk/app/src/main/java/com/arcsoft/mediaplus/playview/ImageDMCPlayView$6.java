// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.widget.ImageView;
import com.arcsoft.mediaplus.datasource.PictureDataSource;
import com.arcsoft.mediaplus.datasource.Property;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMCPlayView, DataSourcePlayList

class this._cls0
    implements com.arcsoft.mediaplus.datasource.hangeListener
{

    final ImageDMCPlayView this$0;

    public void onCountChanged(int i, int j)
    {
        if (i > 0)
        {
            ImageDMCPlayView.access$500(ImageDMCPlayView.this, mPlayList.getCurrentIndex());
        }
    }

    public void onDataChanged(int i, Property property)
    {
        if (property == PictureDataSource.PROP_BITMAP)
        {
            Integer integer = (Integer)ImageDMCPlayView.access$800(ImageDMCPlayView.this).getTag();
            if (integer != null && integer.intValue() == i)
            {
                ImageDMCPlayView.access$600(ImageDMCPlayView.this, i);
            }
        }
    }

    ()
    {
        this$0 = ImageDMCPlayView.this;
        super();
    }
}
