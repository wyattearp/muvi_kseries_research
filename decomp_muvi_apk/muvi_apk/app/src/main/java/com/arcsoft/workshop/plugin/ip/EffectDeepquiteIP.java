// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectDeepquiteIP extends IPAdaptor
{

    public EffectDeepquiteIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectDeepquiteGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectDeepquiteGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectDeepquiteGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectDeepquiteGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectDeepquiteGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectDeepquiteGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectDeepquiteGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectDeepquiteGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectDeepquiteGetSetPropMethod();
    }

    public native int jniEffectDeepquiteGetBeginMethod();

    public native int jniEffectDeepquiteGetCalcMethod();

    public native int jniEffectDeepquiteGetCreateMethod();

    public native int jniEffectDeepquiteGetDestroyMethod();

    public native int jniEffectDeepquiteGetEndMethod();

    public native int jniEffectDeepquiteGetGetPropMethod();

    public native int jniEffectDeepquiteGetIsSupportedMethod();

    public native int jniEffectDeepquiteGetProcessMethod();

    public native int jniEffectDeepquiteGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectdeepquite");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
