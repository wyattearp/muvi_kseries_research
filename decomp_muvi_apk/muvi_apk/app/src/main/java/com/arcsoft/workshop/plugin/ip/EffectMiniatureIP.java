// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectMiniatureIP extends IPAdaptor
{

    public EffectMiniatureIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectMiniatureGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectMiniatureGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectMiniatureGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectMiniatureGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectMiniatureGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectMiniatureGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectMiniatureGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectMiniatureGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectMiniatureGetSetPropMethod();
    }

    public native int jniEffectMiniatureGetBeginMethod();

    public native int jniEffectMiniatureGetCalcMethod();

    public native int jniEffectMiniatureGetCreateMethod();

    public native int jniEffectMiniatureGetDestroyMethod();

    public native int jniEffectMiniatureGetEndMethod();

    public native int jniEffectMiniatureGetGetPropMethod();

    public native int jniEffectMiniatureGetIsSupportedMethod();

    public native int jniEffectMiniatureGetProcessMethod();

    public native int jniEffectMiniatureGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectminiature");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
