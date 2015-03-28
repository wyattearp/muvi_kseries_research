// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            IPlayer

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES STATUS_BUFFERING;
    public static final .VALUES STATUS_IDLE;
    public static final .VALUES STATUS_OPENING;
    public static final .VALUES STATUS_PAUSED;
    public static final .VALUES STATUS_PAUSING;
    public static final .VALUES STATUS_PLAYING;
    public static final .VALUES STATUS_SEEKING;
    public static final .VALUES STATUS_STARTSEEK;
    public static final .VALUES STATUS_STOPPED;
    public static final .VALUES STATUS_STOPPING;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/playengine/IPlayer$Status, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        STATUS_IDLE = new <init>("STATUS_IDLE", 0);
        STATUS_OPENING = new <init>("STATUS_OPENING", 1);
        STATUS_PLAYING = new <init>("STATUS_PLAYING", 2);
        STATUS_BUFFERING = new <init>("STATUS_BUFFERING", 3);
        STATUS_PAUSING = new <init>("STATUS_PAUSING", 4);
        STATUS_PAUSED = new <init>("STATUS_PAUSED", 5);
        STATUS_STOPPING = new <init>("STATUS_STOPPING", 6);
        STATUS_STOPPED = new <init>("STATUS_STOPPED", 7);
        STATUS_STARTSEEK = new <init>("STATUS_STARTSEEK", 8);
        STATUS_SEEKING = new <init>("STATUS_SEEKING", 9);
        s_3B_.clone aclone[] = new <init>[10];
        aclone[0] = STATUS_IDLE;
        aclone[1] = STATUS_OPENING;
        aclone[2] = STATUS_PLAYING;
        aclone[3] = STATUS_BUFFERING;
        aclone[4] = STATUS_PAUSING;
        aclone[5] = STATUS_PAUSED;
        aclone[6] = STATUS_STOPPING;
        aclone[7] = STATUS_STOPPED;
        aclone[8] = STATUS_STARTSEEK;
        aclone[9] = STATUS_SEEKING;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
