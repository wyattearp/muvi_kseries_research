// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            Const

public static final class nativeValue extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BEST_FIT;
    public static final .VALUES FIT_IN;
    public static final .VALUES FIT_OUT;
    final int nativeValue;

    public static nativeValue valueOf(String s)
    {
        return (nativeValue)Enum.valueOf(com/arcsoft/adk/image/Const$DisplayMode, s);
    }

    public static nativeValue[] values()
    {
        return (nativeValue[])$VALUES.clone();
    }

    static 
    {
        FIT_IN = new <init>("FIT_IN", 0, -15);
        FIT_OUT = new <init>("FIT_OUT", 1, -14);
        BEST_FIT = new <init>("BEST_FIT", 2, -13);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = FIT_IN;
        aclone[1] = FIT_OUT;
        aclone[2] = BEST_FIT;
        $VALUES = aclone;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        nativeValue = j;
    }
}
