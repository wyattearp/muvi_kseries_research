// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES DirDown;
    public static final .VALUES DirUp;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playview/RootPlayView$DragDirection, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        DirUp = new <init>("DirUp", 0);
        DirDown = new <init>("DirDown", 1);
        n_3B_.clone aclone[] = new <init>[2];
        aclone[0] = DirUp;
        aclone[1] = DirDown;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
