// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playview;


// Referenced classes of package com.arcsoft.mediaplus.playview:
//            RootPlayView

static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Disable;
    public static final .VALUES Dragging;
    public static final .VALUES Flicking;
    public static final .VALUES NoFuture;
    public static final .VALUES Waitting;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playview/RootPlayView$DragStatus, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Waitting = new <init>("Waitting", 0);
        Dragging = new <init>("Dragging", 1);
        Flicking = new <init>("Flicking", 2);
        NoFuture = new <init>("NoFuture", 3);
        Disable = new <init>("Disable", 4);
        s_3B_.clone aclone[] = new <init>[5];
        aclone[0] = Waitting;
        aclone[1] = Dragging;
        aclone[2] = Flicking;
        aclone[3] = NoFuture;
        aclone[4] = Disable;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
