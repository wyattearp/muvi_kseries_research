// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


// Referenced classes of package com.arcsoft.workshop.ui:
//            UIMiniatureLineView

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CIRCLE;
    public static final .VALUES NONE;
    public static final .VALUES RECT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/workshop/ui/UIMiniatureLineView$DrawType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NONE = new <init>("NONE", 0);
        CIRCLE = new <init>("CIRCLE", 1);
        RECT = new <init>("RECT", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = NONE;
        aclone[1] = CIRCLE;
        aclone[2] = RECT;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
