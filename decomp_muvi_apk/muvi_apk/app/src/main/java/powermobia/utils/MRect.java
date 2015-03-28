// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package powermobia.utils;


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

    public static boolean intersects(MRect mrect, MRect mrect1)
    {
        return mrect.left < mrect1.right && mrect1.left < mrect.right && mrect.top < mrect1.bottom && mrect1.top < mrect.bottom;
    }

    public final int centerX()
    {
        return left + right >> 1;
    }

    public final int centerY()
    {
        return top + bottom >> 1;
    }

    public boolean contains(int i, int j)
    {
        return left < right && top < bottom && i >= left && i < right && j >= top && j < bottom;
    }

    public boolean contains(int i, int j, int k, int l)
    {
        return left < right && top < bottom && left <= i && top <= j && right >= k && bottom >= l;
    }

    public boolean contains(MRect mrect)
    {
        return left < right && top < bottom && left <= mrect.left && top <= mrect.top && right >= mrect.right && bottom >= mrect.bottom;
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

    public final float exactCenterX()
    {
        return 0.5F * (float)(left + right);
    }

    public final float exactCenterY()
    {
        return 0.5F * (float)(top + bottom);
    }

    public final int height()
    {
        return bottom - top;
    }

    public void inset(int i, int j)
    {
        left = i + left;
        top = j + top;
        right = right - i;
        bottom = bottom - j;
    }

    public boolean intersect(int i, int j, int k, int l)
    {
        if (left < k && i < right && top < l && j < bottom)
        {
            if (left < i)
            {
                left = i;
            }
            if (top < j)
            {
                top = j;
            }
            if (right > k)
            {
                right = k;
            }
            if (bottom > l)
            {
                bottom = l;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean intersect(MRect mrect)
    {
        return intersect(mrect.left, mrect.top, mrect.right, mrect.bottom);
    }

    public boolean intersects(int i, int j, int k, int l)
    {
        return left < k && i < right && top < l && j < bottom;
    }

    public final boolean isEmpty()
    {
        return left >= right || top >= bottom;
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

    public void offsetTo(int i, int j)
    {
        right = right + (i - left);
        bottom = bottom + (j - top);
        left = i;
        top = j;
    }

    public void set(int i, int j, int k, int l)
    {
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public void set(MRect mrect)
    {
        left = mrect.left;
        top = mrect.top;
        right = mrect.right;
        bottom = mrect.bottom;
    }

    public void setEmpty()
    {
        bottom = 0;
        top = 0;
        right = 0;
        left = 0;
    }

    public boolean setIntersect(MRect mrect, MRect mrect1)
    {
        if (mrect.left < mrect1.right && mrect1.left < mrect.right && mrect.top < mrect1.bottom && mrect1.top < mrect.bottom)
        {
            left = Math.max(mrect.left, mrect1.left);
            top = Math.max(mrect.top, mrect1.top);
            right = Math.min(mrect.right, mrect1.right);
            bottom = Math.min(mrect.bottom, mrect1.bottom);
            return true;
        } else
        {
            return false;
        }
    }

    public void sort()
    {
        if (left > right)
        {
            int j = left;
            left = right;
            right = j;
        }
        if (top > bottom)
        {
            int i = top;
            top = bottom;
            bottom = i;
        }
    }

    public void union(int i, int j)
    {
        if (i >= left) goto _L2; else goto _L1
_L1:
        left = i;
_L8:
        if (j >= top) goto _L4; else goto _L3
_L3:
        top = j;
_L6:
        return;
_L2:
        if (i > right)
        {
            right = i;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (j <= bottom) goto _L6; else goto _L5
_L5:
        bottom = j;
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void union(int i, int j, int k, int l)
    {
label0:
        {
            if (i < k && j < l)
            {
                if (left >= right || top >= bottom)
                {
                    break label0;
                }
                if (left > i)
                {
                    left = i;
                }
                if (top > j)
                {
                    top = j;
                }
                if (right < k)
                {
                    right = k;
                }
                if (bottom < l)
                {
                    bottom = l;
                }
            }
            return;
        }
        left = i;
        top = j;
        right = k;
        bottom = l;
    }

    public void union(MRect mrect)
    {
        union(mrect.left, mrect.top, mrect.right, mrect.bottom);
    }

    public final int width()
    {
        return right - left;
    }
}
