// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectWashedawayIP extends IPAdaptor
{

    public EffectWashedawayIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectNostalgicGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectNostalgicGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectNostalgicGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectNostalgicGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectNostalgicGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectNostalgicGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectNostalgicGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectNostalgicGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectNostalgicGetSetPropMethod();
    }

    public native int jniEffectNostalgicGetBeginMethod();

    public native int jniEffectNostalgicGetCalcMethod();

    public native int jniEffectNostalgicGetCreateMethod();

    public native int jniEffectNostalgicGetDestroyMethod();

    public native int jniEffectNostalgicGetEndMethod();

    public native int jniEffectNostalgicGetGetPropMethod();

    public native int jniEffectNostalgicGetIsSupportedMethod();

    public native int jniEffectNostalgicGetProcessMethod();

    public native int jniEffectNostalgicGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectnostalgic");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
