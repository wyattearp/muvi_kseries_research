// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


public class Line
{

    public int mEndX;
    public int mEndY;
    public int mStartX;
    public int mStartY;

    public Line()
    {
        mStartX = 0;
        mStartY = 0;
        mEndX = 0;
        mEndY = 0;
    }

    public Line(int i, int j, int k, int l)
    {
        mStartX = 0;
        mStartY = 0;
        mEndX = 0;
        mEndY = 0;
        mStartX = i;
        mStartY = j;
        mEndX = k;
        mEndY = l;
    }
}
