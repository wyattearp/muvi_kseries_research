// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.os;

import android.content.Context;

public class OrientationTool
{
    public static interface IOnOrientationChangeListener
    {

        public abstract void OnOrientationChanged(int i);
    }


    public OrientationTool()
    {
    }

    public static int getCurrentOrientation(Context context)
    {
        return 0;
    }

    public void setOnOrientationChangeListener(IOnOrientationChangeListener ionorientationchangelistener)
    {
    }
}
