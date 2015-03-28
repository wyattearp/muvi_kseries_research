// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import com.arcsoft.util.os.HandlerTimer;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView, ProgressAnimationSet

private class <init>
    implements com.arcsoft.util.os.erListener
{

    private boolean bForward;
    private int iStartProgress;
    private long lStartTime;
    final RootPlayView this$0;

    public boolean getForward()
    {
        return bForward;
    }

    public void onTimer()
    {
        long l;
        ProgressAnimationSet progressanimationset;
        int i;
        int j;
        int k;
        if (bForward)
        {
            l = System.currentTimeMillis() - lStartTime;
        } else
        {
            l = lStartTime - System.currentTimeMillis();
        }
        progressanimationset = RootPlayView.access$100(RootPlayView.this)[RootPlayView.access$200(RootPlayView.this)];
        i = progressanimationset.calcuProgress(l) + iStartProgress;
        j = progressanimationset.getProgress();
        k = progressanimationset.getPausedProgress();
        setProgress(i);
        if (RootPlayView.access$200(RootPlayView.this) == 0 && k > 0 && j <= k && i > k)
        {
            RootPlayView.access$300(RootPlayView.this).pause();
            boolean flag;
            if (RootPlayView.access$400(RootPlayView.this) == null || RootPlayView.access$400(RootPlayView.this).nAnimationGroupPaused(RootPlayView.access$200(RootPlayView.this)))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                RootPlayView.access$300(RootPlayView.this).resume();
            } else
            {
                RootPlayView.access$500(RootPlayView.this).restart(false);
                RootPlayView.access$300(RootPlayView.this).resume();
            }
        }
        if (!bForward && i <= 0 && RootPlayView.access$200(RootPlayView.this) == 0)
        {
            RootPlayView.access$600(RootPlayView.this);
        }
    }

    public void restart()
    {
        lStartTime = System.currentTimeMillis();
        iStartProgress = RootPlayView.access$100(RootPlayView.this)[RootPlayView.access$200(RootPlayView.this)].getProgress();
    }

    public void restart(boolean flag)
    {
        lStartTime = System.currentTimeMillis();
        iStartProgress = RootPlayView.access$100(RootPlayView.this)[RootPlayView.access$200(RootPlayView.this)].getProgress();
        bForward = flag;
    }

    public void setForward(boolean flag)
    {
        bForward = flag;
    }

    private ()
    {
        this$0 = RootPlayView.this;
        super();
        lStartTime = 0L;
        iStartProgress = 0;
        bForward = true;
    }

    bForward(bForward bforward)
    {
        this();
    }
}
