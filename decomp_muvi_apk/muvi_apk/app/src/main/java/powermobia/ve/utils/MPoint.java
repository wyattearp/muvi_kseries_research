// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


public class MPoint
{

    public int x;
    public int y;

    public MPoint()
    {
        x = 0;
        y = 0;
    }

    public MPoint(int i, int j)
    {
        x = i;
        y = j;
    }

    public MPoint(MPoint mpoint)
    {
        x = mpoint.x;
        y = mpoint.y;
    }

    public boolean equals(int i, int j)
    {
        return x == i && y == j;
    }

    public boolean equals(Object obj)
    {
        boolean flag = obj instanceof MPoint;
        boolean flag1 = false;
        if (flag)
        {
            MPoint mpoint = (MPoint)obj;
            int i = x;
            int j = mpoint.x;
            flag1 = false;
            if (i == j)
            {
                int k = y;
                int l = mpoint.y;
                flag1 = false;
                if (k == l)
                {
                    flag1 = true;
                }
            }
        }
        return flag1;
    }

    public void negate()
    {
        x = -x;
        y = -y;
    }

    public void offset(int i, int j)
    {
        x = i + x;
        y = j + y;
    }

    public void set(int i, int j)
    {
        x = i;
        y = j;
    }
}
