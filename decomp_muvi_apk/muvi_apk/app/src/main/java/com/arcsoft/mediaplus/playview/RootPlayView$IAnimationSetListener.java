// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;

import android.view.View;
import android.view.animation.Animation;

// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView

public static interface 
{

    public abstract void onAnimationEnd(int i, Animation animation, View view);

    public abstract void onAnimationGroupCanceled(int i);

    public abstract boolean onAnimationGroupEnd(int i);

    public abstract boolean onAnimationGroupPaused(int i);

    public abstract void onAnimationGroupStart(int i);

    public abstract void onAnimationStart(int i, Animation animation, View view);

    public abstract boolean onCreateAnimation();

    public abstract boolean onDispatchTouchEvent();

    public abstract void onPrepareAnimationGroup(int i);
}
