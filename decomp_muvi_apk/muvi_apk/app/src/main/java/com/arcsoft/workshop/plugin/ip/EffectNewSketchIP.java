// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectNewSketchIP extends IPAdaptor
{

    public EffectNewSketchIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectNewSketchGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectNewSketchGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectNewSketchGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectNewSketchGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectNewSketchGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectNewSketchGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectNewSketchGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectNewSketchGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectNewSketchGetSetPropMethod();
    }

    public native int jniEffectNewSketchGetBeginMethod();

    public native int jniEffectNewSketchGetCalcMethod();

    public native int jniEffectNewSketchGetCreateMethod();

    public native int jniEffectNewSketchGetDestroyMethod();

    public native int jniEffectNewSketchGetEndMethod();

    public native int jniEffectNewSketchGetGetPropMethod();

    public native int jniEffectNewSketchGetIsSupportedMethod();

    public native int jniEffectNewSketchGetProcessMethod();

    public native int jniEffectNewSketchGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectnewsketch");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
