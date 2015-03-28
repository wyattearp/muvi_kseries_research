// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.atv;


public final class DLNAException extends RuntimeException
{

    private static final long serialVersionUID = 0xf1e33bd430f95fdaL;
    private int mErrorCode;

    public DLNAException(int i)
    {
        this("", i);
    }

    public DLNAException(String s)
    {
        this(s, 0);
    }

    public DLNAException(String s, int i)
    {
        super(s);
        mErrorCode = 0;
        mErrorCode = i;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }
}
