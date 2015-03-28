// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectFisheyeIP extends IPAdaptor
{

    public EffectFisheyeIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectFisheyeGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectFisheyeGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectFisheyeGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectFisheyeGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectFisheyeGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectFisheyeGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectFisheyeGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectFisheyeGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectFisheyeGetSetPropMethod();
    }

    public native int jniEffectFisheyeGetBeginMethod();

    public native int jniEffectFisheyeGetCalcMethod();

    public native int jniEffectFisheyeGetCreateMethod();

    public native int jniEffectFisheyeGetDestroyMethod();

    public native int jniEffectFisheyeGetEndMethod();

    public native int jniEffectFisheyeGetGetPropMethod();

    public native int jniEffectFisheyeGetIsSupportedMethod();

    public native int jniEffectFisheyeGetProcessMethod();

    public native int jniEffectFisheyeGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectfisheye");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
