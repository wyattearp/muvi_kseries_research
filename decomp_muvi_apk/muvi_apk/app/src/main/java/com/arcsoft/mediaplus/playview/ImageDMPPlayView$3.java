// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import com.arcsoft.adk.image.PhotoViewer;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ImageDMPPlayView

class this._cls0
    implements android.view.r
{

    final ImageDMPPlayView this$0;

    public void onClick(View view)
    {
        boolean flag = true;
        resetHideControlTimer(flag);
        if (ImageDMPPlayView.access$800(ImageDMPPlayView.this) != null)
        {
            if (view == ImageDMPPlayView.access$900(ImageDMPPlayView.this))
            {
                ImageDMPPlayView.access$800(ImageDMPPlayView.this).next();
            } else
            {
                if (view == ImageDMPPlayView.access$1000(ImageDMPPlayView.this))
                {
                    ImageDMPPlayView.access$800(ImageDMPPlayView.this).prev();
                    return;
                }
                if (view == ImageDMPPlayView.access$1100(ImageDMPPlayView.this))
                {
                    ImageDMPPlayView imagedmpplayview = ImageDMPPlayView.this;
                    if (ImageDMPPlayView.access$000(ImageDMPPlayView.this))
                    {
                        flag = false;
                    }
                    ImageDMPPlayView.access$002(imagedmpplayview, flag);
                    if (ImageDMPPlayView.access$000(ImageDMPPlayView.this))
                    {
                        startSlideShow();
                        return;
                    } else
                    {
                        stopSlideShow();
                        return;
                    }
                }
            }
        }
    }

    ()
    {
        this$0 = ImageDMPPlayView.this;
        super();
    }
}
