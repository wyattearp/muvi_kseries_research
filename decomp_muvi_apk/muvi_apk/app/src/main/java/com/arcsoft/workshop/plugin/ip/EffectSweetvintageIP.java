// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectSweetvintageIP extends IPAdaptor
{

    public EffectSweetvintageIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectSweetvintageGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectSweetvintageGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectSweetvintageGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectSweetvintageGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectSweetvintageGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectSweetvintageGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectSweetvintageGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectSweetvintageGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectSweetvintageGetSetPropMethod();
    }

    public native int jniEffectSweetvintageGetBeginMethod();

    public native int jniEffectSweetvintageGetCalcMethod();

    public native int jniEffectSweetvintageGetCreateMethod();

    public native int jniEffectSweetvintageGetDestroyMethod();

    public native int jniEffectSweetvintageGetEndMethod();

    public native int jniEffectSweetvintageGetGetPropMethod();

    public native int jniEffectSweetvintageGetIsSupportedMethod();

    public native int jniEffectSweetvintageGetProcessMethod();

    public native int jniEffectSweetvintageGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectvintage");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
