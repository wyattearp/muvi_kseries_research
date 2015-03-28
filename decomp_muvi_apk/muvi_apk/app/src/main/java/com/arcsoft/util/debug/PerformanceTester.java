// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.util.debug;


// Referenced classes of package com.arcsoft.util.debug:
//            Log

public class PerformanceTester
{

    private long mEndTime;
    private long mStartTime;
    private final String mTag;

    public PerformanceTester(String s)
    {
        mStartTime = 0L;
        mEndTime = 0L;
        mTag = s;
    }

    public void end()
    {
        mEndTime = System.currentTimeMillis();
    }

    public void print()
    {
        print(null);
    }

    public void print(String s)
    {
        String s1;
        if (s != null)
        {
            s1 = (new StringBuilder()).append("[").append(s).append("] ").toString();
        } else
        {
            s1 = "";
        }
        Log.d(mTag, (new StringBuilder()).append(s1).append("cost: ").append(mEndTime - mStartTime).toString());
    }

    public void printEnd()
    {
        printEnd(null);
    }

    public void printEnd(String s)
    {
        String s1;
        if (s != null)
        {
            s1 = (new StringBuilder()).append("[").append(s).append("] ").toString();
        } else
        {
            s1 = "";
        }
        Log.d(mTag, (new StringBuilder()).append(s1).append("end: ").append(mStartTime).toString());
    }

    public void printStart()
    {
        printStart(null);
    }

    public void printStart(String s)
    {
        String s1;
        if (s != null)
        {
            s1 = (new StringBuilder()).append("[").append(s).append("] ").toString();
        } else
        {
            s1 = "";
        }
        Log.d(mTag, (new StringBuilder()).append(s1).append("start: ").append(mStartTime).toString());
    }

    public void start()
    {
        mStartTime = System.currentTimeMillis();
    }
}
