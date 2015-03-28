// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import com.arcsoft.util.debug.Log;
import java.util.List;

public class ProgressAnimation extends AnimationSet
{

    public static final int PROGRESS_MAX = 100;
    long mCurrentTime;

    public ProgressAnimation(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCurrentTime = 0L;
    }

    public ProgressAnimation(boolean flag)
    {
        super(flag);
        mCurrentTime = 0L;
    }

    public static ProgressAnimation create(Animation animation)
    {
        ProgressAnimation progressanimation = new ProgressAnimation(true);
        if (animation instanceof AnimationSet)
        {
            List list = ((AnimationSet)animation).getAnimations();
            int i = list.size();
            for (int j = 0; j < i; j++)
            {
                progressanimation.addAnimation((Animation)list.get(j));
            }

        } else
        {
            progressanimation.addAnimation(animation);
        }
        return progressanimation;
    }

    public long getTotalDuration()
    {
        List list = getAnimations();
        int i = list.size();
        long l = 0L;
        for (int j = 0; j < i; j++)
        {
            Animation animation = (Animation)list.get(j);
            l = Math.max(l, animation.getDuration() + animation.getStartOffset());
        }

        return l;
    }

    public boolean getTransformation(long l, Transformation transformation)
    {
        if (mCurrentTime >= 0L && mCurrentTime <= getTotalDuration())
        {
            return super.getTransformation(mCurrentTime, transformation);
        } else
        {
            return false;
        }
    }

    public void reset()
    {
        super.reset();
        mCurrentTime = 0L;
    }

    public void setProgress(int i)
    {
        if (i < 0)
        {
            i = 0;
        }
        if (i > 100)
        {
            i = 100;
        }
        Log.d("Animation", (new StringBuilder()).append("setProgress = ").append(i).toString());
        mCurrentTime = (getTotalDuration() * (long)i) / 100L;
    }

    public void setStartTime(long l)
    {
        mCurrentTime = 0L;
        super.setStartTime(l);
    }
}
