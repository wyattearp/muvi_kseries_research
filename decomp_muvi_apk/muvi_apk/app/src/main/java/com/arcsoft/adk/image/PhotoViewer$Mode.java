// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.adk.image;


// Referenced classes of package com.arcsoft.adk.image:
//            PhotoViewer

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Normal;
    public static final .VALUES Zoom;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/adk/image/PhotoViewer$Mode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Normal = new <init>("Normal", 0);
        Zoom = new <init>("Zoom", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = Normal;
        aclone[1] = Zoom;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
