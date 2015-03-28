// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class ReversalFilmIP extends IPAdaptor
{

    private static final String LOG_TAG = "ReversalFilmIP";

    public ReversalFilmIP()
    {
    }

    public int getBeginMethod()
    {
        return jniReversalFilmGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniReversalFilmGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniReversalFilmGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniReversalFilmGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniReversalFilmGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniReversalFilmGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniReversalFilmGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniReversalFilmGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniReversalFilmGetSetPropMethod();
    }

    public native int jniReversalFilmGetBeginMethod();

    public native int jniReversalFilmGetCalcMethod();

    public native int jniReversalFilmGetCreateMethod();

    public native int jniReversalFilmGetDestroyMethod();

    public native int jniReversalFilmGetEndMethod();

    public native int jniReversalFilmGetGetPropMethod();

    public native int jniReversalFilmGetIsSupportedMethod();

    public native int jniReversalFilmGetProcessMethod();

    public native int jniReversalFilmGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("reversalfilm");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
