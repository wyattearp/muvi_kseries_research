// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.collage;


// Referenced classes of package com.arcsoft.mediaplus.collage:
//            MultiTouchDetector

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES None;
    public static final .VALUES Rotate;
    public static final .VALUES Zoom;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/collage/MultiTouchDetector$TouchMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        None = new <init>("None", 0);
        Zoom = new <init>("Zoom", 1);
        Rotate = new <init>("Rotate", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = None;
        aclone[1] = Zoom;
        aclone[2] = Rotate;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
