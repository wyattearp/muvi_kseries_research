// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMCPlayView

class this._cls0
    implements android.view.r
{

    final ImageDMCPlayView this$0;

    public void onClick(View view)
    {
        ImageDMCPlayView imagedmcplayview = ImageDMCPlayView.this;
        boolean flag;
        if (!ImageDMCPlayView.access$000(ImageDMCPlayView.this))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ImageDMCPlayView.access$002(imagedmcplayview, flag);
        if (ImageDMCPlayView.access$000(ImageDMCPlayView.this))
        {
            startSlideShow();
            return;
        } else
        {
            stopSlideShow();
            return;
        }
    }

    ()
    {
        this$0 = ImageDMCPlayView.this;
        super();
    }
}
