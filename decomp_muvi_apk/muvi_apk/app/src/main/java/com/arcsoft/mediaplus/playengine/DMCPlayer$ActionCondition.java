// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayer

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ENGINESTABEL;
    public static final .VALUES OPENED;
    public static final .VALUES PAUSED;
    public static final .VALUES SEEKED;
    public static final .VALUES STARTEDPLAY;
    public static final .VALUES STOPPED;
    public static final .VALUES URLREADY;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayer$ActionCondition, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        URLREADY = new <init>("URLREADY", 0);
        ENGINESTABEL = new <init>("ENGINESTABEL", 1);
        STOPPED = new <init>("STOPPED", 2);
        OPENED = new <init>("OPENED", 3);
        STARTEDPLAY = new <init>("STARTEDPLAY", 4);
        SEEKED = new <init>("SEEKED", 5);
        PAUSED = new <init>("PAUSED", 6);
        n_3B_.clone aclone[] = new <init>[7];
        aclone[0] = URLREADY;
        aclone[1] = ENGINESTABEL;
        aclone[2] = STOPPED;
        aclone[3] = OPENED;
        aclone[4] = STARTEDPLAY;
        aclone[5] = SEEKED;
        aclone[6] = PAUSED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
