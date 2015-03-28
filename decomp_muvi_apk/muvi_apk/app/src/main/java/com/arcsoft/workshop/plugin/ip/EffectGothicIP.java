// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectGothicIP extends IPAdaptor
{

    public EffectGothicIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectGothicGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectGothicGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectGothicGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectGothicGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectGothicGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectGothicGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectGothicGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectGothicGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectGothicGetSetPropMethod();
    }

    public native int jniEffectGothicGetBeginMethod();

    public native int jniEffectGothicGetCalcMethod();

    public native int jniEffectGothicGetCreateMethod();

    public native int jniEffectGothicGetDestroyMethod();

    public native int jniEffectGothicGetEndMethod();

    public native int jniEffectGothicGetGetPropMethod();

    public native int jniEffectGothicGetIsSupportedMethod();

    public native int jniEffectGothicGetProcessMethod();

    public native int jniEffectGothicGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectgothic");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
