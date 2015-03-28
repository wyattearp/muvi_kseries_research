// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            ThumbEngine

public static final class nativeValue extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES DECODE_PAGE;
    public static final .VALUES DO_ALL_TASK;
    public static final .VALUES RET_QUICKLY;
    final int nativeValue;

    public static nativeValue valueOf(String s)
    {
        return (nativeValue)Enum.valueOf(com/arcsoft/adk/image/ThumbEngine$StepType, s);
    }

    public static nativeValue[] values()
    {
        return (nativeValue[])$VALUES.clone();
    }

    static 
    {
        DO_ALL_TASK = new <init>("DO_ALL_TASK", 0, 0xa71100);
        DECODE_PAGE = new <init>("DECODE_PAGE", 1, 0xa71101);
        RET_QUICKLY = new <init>("RET_QUICKLY", 2, 0xa71102);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = DO_ALL_TASK;
        aclone[1] = DECODE_PAGE;
        aclone[2] = RET_QUICKLY;
        $VALUES = aclone;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        nativeValue = j;
    }
}
