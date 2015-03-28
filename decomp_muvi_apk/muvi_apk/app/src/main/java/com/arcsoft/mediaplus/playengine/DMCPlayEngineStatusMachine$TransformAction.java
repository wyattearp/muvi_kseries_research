// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

public static final class Q extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES COMPLETE;
    public static final .VALUES OPEN;
    public static final .VALUES PAUSE;
    public static final .VALUES PLAY;
    public static final .VALUES SEEK;
    public static final .VALUES STOP;

    public static Q valueOf(String s)
    {
        return (Q)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngineStatusMachine$TransformAction, s);
    }

    public static Q[] values()
    {
        return (Q[])$VALUES.clone();
    }

    static 
    {
        OPEN = new <init>("OPEN", 0);
        PLAY = new <init>("PLAY", 1);
        STOP = new <init>("STOP", 2);
        COMPLETE = new <init>("COMPLETE", 3);
        PAUSE = new <init>("PAUSE", 4);
        SEEK = new <init>("SEEK", 5);
        n_3B_.clone aclone[] = new <init>[6];
        aclone[0] = OPEN;
        aclone[1] = PLAY;
        aclone[2] = STOP;
        aclone[3] = COMPLETE;
        aclone[4] = PAUSE;
        aclone[5] = SEEK;
        $VALUES = aclone;
    }

    private Q(String s, int i)
    {
        super(s, i);
    }
}
