// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.ve.utils;


public class MRect
{

    public int bottom;
    public int left;
    public int right;
    public int top;

    public MRect()
    {
        left = 0;
        top = 0;
        right = 0;
        bottom = 0;
    }

    public MRect(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public MRect(MRect mrect)
    {
        left = mrect.left;
        top = mrect.top;
        right = mrect.right;
        bottom = mrect.bottom;
    }

    public boolean equals(int i, int j, int k, int l)
    {
        return left == i && top == j && right == k && bottom == l;
    }

    public boolean equals(Object obj)
    {
        boolean flag = obj instanceof MRect;
        boolean flag1 = false;
        if (flag)
        {
            MRect mrect = (MRect)obj;
            int i = left;
            int j = mrect.left;
            flag1 = false;
            if (i == j)
            {
                int k = top;
                int l = mrect.top;
                flag1 = false;
                if (k == l)
                {
                    int i1 = right;
                    int j1 = mrect.right;
                    flag1 = false;
                    if (i1 == j1)
                    {
                        int k1 = bottom;
                        int l1 = mrect.bottom;
                        flag1 = false;
                        if (k1 == l1)
                        {
                            flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public void negate()
    {
        left = -left;
        top = -top;
        right = -right;
        bottom = -bottom;
    }

    public void offset(int i, int j)
    {
        left = i + left;
        right = i + right;
        top = j + top;
        bottom = j + bottom;
    }

    public void set(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }
}
