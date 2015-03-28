// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.workshop.ui;


// Referenced classes of package com.arcsoft.workshop.ui:
//            UIMiniatureLineView

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES None;
    public static final .VALUES Pan;
    public static final .VALUES Rotate;
    public static final .VALUES Zoom;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/workshop/ui/UIMiniatureLineView$TouchMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        None = new <init>("None", 0);
        Pan = new <init>("Pan", 1);
        Zoom = new <init>("Zoom", 2);
        Rotate = new <init>("Rotate", 3);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = None;
        aclone[1] = Pan;
        aclone[2] = Zoom;
        aclone[3] = Rotate;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
