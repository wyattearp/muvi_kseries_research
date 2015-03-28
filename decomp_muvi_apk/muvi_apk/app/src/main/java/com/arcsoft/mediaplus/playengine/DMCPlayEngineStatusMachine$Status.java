// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngineStatusMachine

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CANCELED;
    public static final .VALUES CANCELING;
    public static final .VALUES FAULT;
    public static final .VALUES OPENED;
    public static final .VALUES OPENING;
    public static final .VALUES PAUSED;
    public static final .VALUES PAUSING;
    public static final .VALUES PLAYING;
    public static final .VALUES SEEKING;
    public static final .VALUES STARTINGPLAY;
    public static final .VALUES STOPED;
    public static final .VALUES STOPPING;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngineStatusMachine$Status, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        FAULT = new <init>("FAULT", 0);
        STOPPING = new <init>("STOPPING", 1);
        STOPED = new <init>("STOPED", 2);
        CANCELING = new <init>("CANCELING", 3);
        CANCELED = new <init>("CANCELED", 4);
        OPENING = new <init>("OPENING", 5);
        OPENED = new <init>("OPENED", 6);
        STARTINGPLAY = new <init>("STARTINGPLAY", 7);
        PLAYING = new <init>("PLAYING", 8);
        PAUSING = new <init>("PAUSING", 9);
        PAUSED = new <init>("PAUSED", 10);
        SEEKING = new <init>("SEEKING", 11);
        s_3B_.clone aclone[] = new <init>[12];
        aclone[0] = FAULT;
        aclone[1] = STOPPING;
        aclone[2] = STOPED;
        aclone[3] = CANCELING;
        aclone[4] = CANCELED;
        aclone[5] = OPENING;
        aclone[6] = OPENED;
        aclone[7] = STARTINGPLAY;
        aclone[8] = PLAYING;
        aclone[9] = PAUSING;
        aclone[10] = PAUSED;
        aclone[11] = SEEKING;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
