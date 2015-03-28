// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

// Referenced classes of package com.arcsoft.videotrim.Utils:
//            SDCardManager

private static final class mInterestEventSet
{

    private final Set mInterestEventSet = new CopyOnWriteArraySet();
    private final ener mSDCardEventListener;

    public ener getSDCardEventListener()
    {
        return mSDCardEventListener;
    }

    public boolean isIntersetInEvent(mSDCardEventListener msdcardeventlistener)
    {
        return mInterestEventSet.contains(msdcardeventlistener);
    }

    public ener(ener ener, ener aener[])
    {
        mSDCardEventListener = ener;
        for (int i = 0; i < aener.length; i++)
        {
            mInterestEventSet.add(aener[i]);
        }

    }
}
