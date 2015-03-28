// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop;


public interface OnImageEventListener
{

    public static final int IMGEVENT_LOAD_IMAGE_FAILED = 1;
    public static final int IMGEVENT_LOAD_IMAGE_SUCCESS = 0;
    public static final int IMGEVENT_POSTCOORDCHANGED = 3;
    public static final int IMGEVENT_PRECOORDCHANGED = 2;
    public static final int IMGEVENT_SAVEDONE = 4;

    public abstract void onChange(int i, Object obj, Object obj1);
}
