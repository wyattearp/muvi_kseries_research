// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayEngine

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES STATUS_BUFFERING;
    public static final .VALUES STATUS_IDLE;
    public static final .VALUES STATUS_OPENED;
    public static final .VALUES STATUS_OPENING;
    public static final .VALUES STATUS_PAUSED;
    public static final .VALUES STATUS_PAUSING;
    public static final .VALUES STATUS_PLAYING;
    public static final .VALUES STATUS_SEEKING;
    public static final .VALUES STATUS_STARTINGPLAY;
    public static final .VALUES STATUS_STOPPED;
    public static final .VALUES STATUS_STOPPING;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayEngine$PLAYSTATUS, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        STATUS_IDLE = new <init>("STATUS_IDLE", 0);
        STATUS_OPENING = new <init>("STATUS_OPENING", 1);
        STATUS_OPENED = new <init>("STATUS_OPENED", 2);
        STATUS_STARTINGPLAY = new <init>("STATUS_STARTINGPLAY", 3);
        STATUS_PLAYING = new <init>("STATUS_PLAYING", 4);
        STATUS_BUFFERING = new <init>("STATUS_BUFFERING", 5);
        STATUS_PAUSING = new <init>("STATUS_PAUSING", 6);
        STATUS_PAUSED = new <init>("STATUS_PAUSED", 7);
        STATUS_STOPPING = new <init>("STATUS_STOPPING", 8);
        STATUS_STOPPED = new <init>("STATUS_STOPPED", 9);
        STATUS_SEEKING = new <init>("STATUS_SEEKING", 10);
        S_3B_.clone aclone[] = new <init>[11];
        aclone[0] = STATUS_IDLE;
        aclone[1] = STATUS_OPENING;
        aclone[2] = STATUS_OPENED;
        aclone[3] = STATUS_STARTINGPLAY;
        aclone[4] = STATUS_PLAYING;
        aclone[5] = STATUS_BUFFERING;
        aclone[6] = STATUS_PAUSING;
        aclone[7] = STATUS_PAUSED;
        aclone[8] = STATUS_STOPPING;
        aclone[9] = STATUS_STOPPED;
        aclone[10] = STATUS_SEEKING;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
