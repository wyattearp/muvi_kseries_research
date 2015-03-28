// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            AbsPlayList

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES NoRepeat;
    public static final .VALUES RepeatAll;
    public static final .VALUES RepeatOne;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/AbsPlayList$RepeatMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        NoRepeat = new <init>("NoRepeat", 0);
        RepeatOne = new <init>("RepeatOne", 1);
        RepeatAll = new <init>("RepeatAll", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = NoRepeat;
        aclone[1] = RepeatOne;
        aclone[2] = RepeatAll;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
