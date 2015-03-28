// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import android.view.animation.Animation;
import com.arcsoft.util.debug.Log;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView, ProgressAnimationSet

class this._cls0
    implements android.view.animation.tionListener
{

    final RootPlayView this$0;

    public void onAnimationEnd(Animation animation)
    {
        ProgressAnimationSet progressanimationset = RootPlayView.access$100(RootPlayView.this)[RootPlayView.access$200(RootPlayView.this)];
        List list = progressanimationset.getAnimations();
        int i = list.indexOf(animation);
        if (RootPlayView.access$400(RootPlayView.this) != null)
        {
            Log.i("RootView", (new StringBuilder()).append("onAnimationEnd(), group = ").append(RootPlayView.access$200(RootPlayView.this)).toString());
            RootPlayView.access$400(RootPlayView.this).onAnimationEnd(RootPlayView.access$200(RootPlayView.this), animation, (View)progressanimationset.getViews().get(i));
        }
        if (RootPlayView.access$700(RootPlayView.this) == agDirection.DirUp && RootPlayView.access$200(RootPlayView.this) == 0)
        {
            RootPlayView.access$900(RootPlayView.this, RootPlayView.access$800(RootPlayView.this));
        }
        progressanimationset.mbEnded = 1 + progressanimationset.mbEnded;
        int j = list.size();
        if (progressanimationset.mbEnded >= j)
        {
            onAnimatationGroupEnd(RootPlayView.access$200(RootPlayView.this));
        }
    }

    public void onAnimationRepeat(Animation animation)
    {
    }

    public void onAnimationStart(Animation animation)
    {
        ProgressAnimationSet progressanimationset = RootPlayView.access$100(RootPlayView.this)[RootPlayView.access$200(RootPlayView.this)];
        int i = progressanimationset.getAnimations().indexOf(animation);
        if (RootPlayView.access$400(RootPlayView.this) != null)
        {
            RootPlayView.access$400(RootPlayView.this).onAnimationStart(RootPlayView.access$200(RootPlayView.this), animation, (View)progressanimationset.getViews().get(i));
        }
        Log.i("RootView", (new StringBuilder()).append("onAnimationStart(), group = ").append(RootPlayView.access$200(RootPlayView.this)).toString());
        if (!progressanimationset.mbStarted)
        {
            onAnimationGroupStart(RootPlayView.access$200(RootPlayView.this));
            progressanimationset.mbStarted = true;
        }
    }

    agDirection()
    {
        this$0 = RootPlayView.this;
        super();
    }
}
