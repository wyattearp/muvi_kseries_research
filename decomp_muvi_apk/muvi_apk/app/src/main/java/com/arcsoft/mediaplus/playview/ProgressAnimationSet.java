// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import android.view.animation.Animation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            ProgressAnimation

public class ProgressAnimationSet
{

    private android.view.animation.Animation.AnimationListener mAnimationListener;
    private ArrayList mAnimations;
    private long mDuration;
    int mPausedProgress;
    private int mProgress;
    private ArrayList mViews;
    int mbEnded;
    boolean mbStarted;

    public ProgressAnimationSet(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mAnimations = new ArrayList();
        mViews = new ArrayList();
        mAnimationListener = null;
        mbStarted = false;
        mbEnded = 0;
        mPausedProgress = 0;
        mProgress = 0;
        mDuration = 0L;
        mAnimationListener = animationlistener;
    }

    public void addAnimation(Animation animation, View view)
    {
        ProgressAnimation progressanimation = ProgressAnimation.create(animation);
        mAnimations.add(progressanimation);
        mViews.add(view);
        progressanimation.setAnimationListener(mAnimationListener);
        mDuration = Math.max(mDuration, progressanimation.getTotalDuration());
    }

    public void addAnimation(Animation animation, View view, int i)
    {
        ProgressAnimation progressanimation = ProgressAnimation.create(animation);
        mAnimations.remove(i);
        mAnimations.add(i, progressanimation);
        mViews.remove(i);
        mViews.add(i, view);
        progressanimation.setAnimationListener(mAnimationListener);
        mDuration = Math.max(mDuration, progressanimation.getTotalDuration());
    }

    public int calcuProgress(long l)
    {
        return (int)((100L * l) / mDuration);
    }

    public void clear()
    {
        for (Iterator iterator = mViews.iterator(); iterator.hasNext(); ((View)iterator.next()).setAnimation(null)) { }
        mAnimations.clear();
        mViews.clear();
    }

    public List getAnimations()
    {
        return mAnimations;
    }

    public long getDuration()
    {
        return mDuration;
    }

    public int getPausedProgress()
    {
        return mPausedProgress;
    }

    public int getProgress()
    {
        return mProgress;
    }

    public List getViews()
    {
        return mViews;
    }

    public void reset()
    {
        mbStarted = false;
        mbEnded = 0;
        mProgress = 0;
        for (Iterator iterator = mAnimations.iterator(); iterator.hasNext(); ((ProgressAnimation)iterator.next()).reset()) { }
    }

    public void setPausedProgress(int i)
    {
        mPausedProgress = i;
    }

    public void setProgress(int i)
    {
        mProgress = i;
        List list = getAnimations();
        int j = list.size();
        for (int k = 0; k < j; k++)
        {
            ((ProgressAnimation)list.get(k)).setProgress(i);
        }

    }

    public void start()
    {
        List list = getAnimations();
        int i = list.size();
        ArrayList arraylist = mViews;
        for (int j = 0; j < i; j++)
        {
            ProgressAnimation progressanimation = (ProgressAnimation)list.get(j);
            ((View)arraylist.get(j)).startAnimation(progressanimation);
        }

    }
}
