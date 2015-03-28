// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class EffectPolaroidIP extends IPAdaptor
{

    public EffectPolaroidIP()
    {
    }

    public int getBeginMethod()
    {
        return jniEffectPolaroidIPGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniEffectPolaroidIPGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniEffectPolaroidIPGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniEffectPolaroidIPGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniEffectPolaroidIPGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniEffectPolaroidIPGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniEffectPolaroidIPGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniEffectPolaroidIPGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniEffectPolaroidIPGetSetPropMethod();
    }

    public native int jniEffectPolaroidIPGetBeginMethod();

    public native int jniEffectPolaroidIPGetCalcMethod();

    public native int jniEffectPolaroidIPGetCreateMethod();

    public native int jniEffectPolaroidIPGetDestroyMethod();

    public native int jniEffectPolaroidIPGetEndMethod();

    public native int jniEffectPolaroidIPGetGetPropMethod();

    public native int jniEffectPolaroidIPGetIsSupportedMethod();

    public native int jniEffectPolaroidIPGetProcessMethod();

    public native int jniEffectPolaroidIPGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("effectpolaroid");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
