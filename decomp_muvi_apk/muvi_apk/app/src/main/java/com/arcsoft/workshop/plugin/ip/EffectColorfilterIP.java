// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectColorfilterIP extends IPAdaptor
{

    public EffectColorfilterIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectColorfilterGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectColorfilterGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectColorfilterGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectColorfilterGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectColorfilterGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectColorfilterGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectColorfilterGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectColorfilterGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectColorfilterGetSetPropMethod();
    }

    public native int jniEffectColorfilterGetBeginMethod();

    public native int jniEffectColorfilterGetCalcMethod();

    public native int jniEffectColorfilterGetCreateMethod();

    public native int jniEffectColorfilterGetDestroyMethod();

    public native int jniEffectColorfilterGetEndMethod();

    public native int jniEffectColorfilterGetGetPropMethod();

    public native int jniEffectColorfilterGetIsSupportedMethod();

    public native int jniEffectColorfilterGetProcessMethod();

    public native int jniEffectColorfilterGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectsinglecolor");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
