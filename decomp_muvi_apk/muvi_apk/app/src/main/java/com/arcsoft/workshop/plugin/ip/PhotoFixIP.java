// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.plugin.ip;

import powermobia.photoeditor.IPAdaptor;

public class PhotoFixIP extends IPAdaptor
{

    private static final String LOG_TAG = "PhotFixIP";

    public PhotoFixIP()
    {
    }

    public int getBeginMethod()
    {
        return jniPhotofixGetBeginMethod();
    }

    public int getCalcMethod()
    {
        return jniPhotofixGetCalcMethod();
    }

    public int getCreateMethod()
    {
        return jniPhotofixGetCreateMethod();
    }

    public int getDestroyMethod()
    {
        return jniPhotofixGetDestroyMethod();
    }

    public int getEndMethod()
    {
        return jniPhotofixGetEndMethod();
    }

    public int getGetPropMethod()
    {
        return jniPhotofixGetGetPropMethod();
    }

    public int getIsSupportedMethod()
    {
        return jniPhotofixGetIsSupportedMethod();
    }

    public int getProcessMethod()
    {
        return jniPhotofixGetProcessMethod();
    }

    public int getSetPropMethod()
    {
        return jniPhotofixGetSetPropMethod();
    }

    public native int jniPhotofixGetBeginMethod();

    public native int jniPhotofixGetCalcMethod();

    public native int jniPhotofixGetCreateMethod();

    public native int jniPhotofixGetDestroyMethod();

    public native int jniPhotofixGetEndMethod();

    public native int jniPhotofixGetGetPropMethod();

    public native int jniPhotofixGetIsSupportedMethod();

    public native int jniPhotofixGetProcessMethod();

    public native int jniPhotofixGetSetPropMethod();

    static 
    {
        try
        {
            System.loadLibrary("photofix");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
