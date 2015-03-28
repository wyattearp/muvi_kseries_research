// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectColdBlueIP extends IPAdaptor
{

    public EffectColdBlueIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectColdBlueGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectColdBlueGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectColdBlueGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectColdBlueGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectColdBlueGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectColdBlueGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectColdBlueGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectColdBlueGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectColdBlueGetSetPropMethod();
    }

    public native int jniEffectColdBlueGetBeginMethod();

    public native int jniEffectColdBlueGetCalcMethod();

    public native int jniEffectColdBlueGetCreateMethod();

    public native int jniEffectColdBlueGetDestroyMethod();

    public native int jniEffectColdBlueGetEndMethod();

    public native int jniEffectColdBlueGetGetPropMethod();

    public native int jniEffectColdBlueGetIsSupportedMethod();

    public native int jniEffectColdBlueGetProcessMethod();

    public native int jniEffectColdBlueGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectcoldblue");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
