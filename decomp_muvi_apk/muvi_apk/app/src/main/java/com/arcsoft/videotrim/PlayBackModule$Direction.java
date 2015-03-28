// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim;


// Referenced classes of package com.arcsoft.videotrim:
//            PlayBackModule

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES NEXT_KEYFRAME;
    public static final .VALUES PREV_KEYFRAME;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/videotrim/PlayBackModule$Direction, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        PREV_KEYFRAME = new <init>("PREV_KEYFRAME", 0);
        NEXT_KEYFRAME = new <init>("NEXT_KEYFRAME", 1);
        n_3B_.clone aclone[] = new <init>[2];
        aclone[0] = PREV_KEYFRAME;
        aclone[1] = NEXT_KEYFRAME;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
