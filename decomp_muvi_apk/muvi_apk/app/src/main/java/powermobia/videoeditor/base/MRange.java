// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.videoeditor.base;


public class MRange
{

    public static final int LENGTH = 1;
    public static final int START;
    private int length;
    private int start;

    public MRange()
    {
        start = 0;
        length = 0;
    }

    public MRange(int i, int j)
    {
        start = 0;
        length = 0;
        start = i;
        length = j;
    }

    public MRange(MRange mrange)
    {
        start = 0;
        length = 0;
        start = mrange.start;
        length = mrange.length;
    }

    public boolean equals(int i, int j)
    {
        return start == i && length == j;
    }

    public boolean equals(MRange mrange)
    {
        return start == mrange.start && length == mrange.length;
    }

    public int get(int i)
    {
        switch (i)
        {
        default:
            return -1;

        case 0: // '\0'
            return start;

        case 1: // '\001'
            return length;
        }
    }

    public void set(int i, int j)
    {
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            start = j;
            return;

        case 1: // '\001'
            length = j;
            break;
        }
    }
}
