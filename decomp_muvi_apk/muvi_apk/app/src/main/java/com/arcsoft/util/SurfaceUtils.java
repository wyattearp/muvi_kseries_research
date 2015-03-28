// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util;


public class SurfaceUtils
{

    public SurfaceUtils()
    {
    }

    public static native boolean native_isQualCommSurface();

    static 
    {
        try
        {
            System.loadLibrary("surfacejudge");
        }
        catch (UnsatisfiedLinkError unsatisfiedlinkerror)
        {
            unsatisfiedlinkerror.printStackTrace();
        }
    }
}
