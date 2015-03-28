// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus;


// Referenced classes of package com.arcsoft.mediaplus:
//            MediaPlusListView

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Prefetch_ScrollDown;
    public static final .VALUES Prefetch_ScrollStop;
    public static final .VALUES Prefetch_ScrollUp;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/MediaPlusListView$PrefetchType, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Prefetch_ScrollDown = new <init>("Prefetch_ScrollDown", 0);
        Prefetch_ScrollUp = new <init>("Prefetch_ScrollUp", 1);
        Prefetch_ScrollStop = new <init>("Prefetch_ScrollStop", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = Prefetch_ScrollDown;
        aclone[1] = Prefetch_ScrollUp;
        aclone[2] = Prefetch_ScrollStop;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
