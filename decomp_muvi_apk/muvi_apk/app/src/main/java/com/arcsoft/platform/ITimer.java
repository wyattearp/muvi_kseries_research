// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.platform;


public interface ITimer
{

    public static final int MSG_TIMERPROC = 0xfffe0000;

    public abstract int TimerCancel();

    public abstract int TimerSet(int i, int j, int k);

    public abstract int TimerSetEx(int i, boolean flag, int j, int k);
}
