// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

public static class maxcachesizeKB
{

    public final displaymode displaymode;
    public final int height;
    public final int maxcachesizeKB;
    public final int width;

    public boolean equals(Object obj)
    {
        maxcachesizeKB maxcachesizekb;
        int i;
        int j;
        boolean flag;
        int k;
        int l;
        maxcachesizeKB maxcachesizekb1;
        maxcachesizeKB maxcachesizekb2;
        try
        {
            maxcachesizekb = (maxcachesizeKB)obj;
            i = maxcachesizekb.width;
            j = width;
        }
        catch (Exception exception)
        {
            return false;
        }
        flag = false;
        if (i != j)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        k = maxcachesizekb.height;
        l = height;
        flag = false;
        if (k != l)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        maxcachesizekb1 = maxcachesizekb.displaymode;
        maxcachesizekb2 = displaymode;
        flag = false;
        if (maxcachesizekb1 == maxcachesizekb2)
        {
            flag = true;
        }
        return flag;
    }

    public (int i, int j,  , int k)
    {
        width = i;
        height = j;
        displaymode = ;
        maxcachesizeKB = k;
    }

    public maxcachesizeKB(maxcachesizeKB maxcachesizekb)
    {
        width = maxcachesizekb.width;
        height = maxcachesizekb.height;
        displaymode = maxcachesizekb.displaymode;
        maxcachesizeKB = maxcachesizekb.maxcachesizeKB;
    }
}
