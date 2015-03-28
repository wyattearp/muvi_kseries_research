// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.utils;


class FitParams
{

    private double dScale;
    private int lFitH;
    private int lFitW;

    public FitParams()
    {
        lFitW = 0;
        lFitH = 0;
        dScale = 1.0D;
    }

    public int getFitH()
    {
        return lFitH;
    }

    public int getFitW()
    {
        return lFitW;
    }

    public double getScale()
    {
        return dScale;
    }

    public void setFitH(int i)
    {
        lFitH = i;
    }

    public void setFitW(int i)
    {
        lFitW = i;
    }

    public void setScale(double d)
    {
        dScale = d;
    }
}
