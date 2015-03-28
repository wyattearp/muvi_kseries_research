// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.playengine;


// Referenced classes of package com.arcsoft.mediaplus.playengine:
//            DMCPlayEngine

static final class A extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES MSG_GETACTION;
    public static final .VALUES MSG_GETALLOWACTIONS;
    public static final .VALUES MSG_GETMETADATA;
    public static final .VALUES MSG_GETMUTE;
    public static final .VALUES MSG_GETPOSITION;
    public static final .VALUES MSG_GETVOLUME;
    public static final .VALUES MSG_OPEN;
    public static final .VALUES MSG_PAUSE;
    public static final .VALUES MSG_PLAY;
    public static final .VALUES MSG_SEEK;
    public static final .VALUES MSG_STOP;

    public static A valueOf(String s)
    {
        return (A)Enum.valueOf(com/arcsoft/mediaplus/playengine/DMCPlayEngine$ASyncMsgType, s);
    }

    public static A[] values()
    {
        return (A[])$VALUES.clone();
    }

    static 
    {
        MSG_OPEN = new <init>("MSG_OPEN", 0);
        MSG_PLAY = new <init>("MSG_PLAY", 1);
        MSG_STOP = new <init>("MSG_STOP", 2);
        MSG_PAUSE = new <init>("MSG_PAUSE", 3);
        MSG_SEEK = new <init>("MSG_SEEK", 4);
        MSG_GETVOLUME = new <init>("MSG_GETVOLUME", 5);
        MSG_GETMUTE = new <init>("MSG_GETMUTE", 6);
        MSG_GETACTION = new <init>("MSG_GETACTION", 7);
        MSG_GETPOSITION = new <init>("MSG_GETPOSITION", 8);
        MSG_GETMETADATA = new <init>("MSG_GETMETADATA", 9);
        MSG_GETALLOWACTIONS = new <init>("MSG_GETALLOWACTIONS", 10);
        e_3B_.clone aclone[] = new <init>[11];
        aclone[0] = MSG_OPEN;
        aclone[1] = MSG_PLAY;
        aclone[2] = MSG_STOP;
        aclone[3] = MSG_PAUSE;
        aclone[4] = MSG_SEEK;
        aclone[5] = MSG_GETVOLUME;
        aclone[6] = MSG_GETMUTE;
        aclone[7] = MSG_GETACTION;
        aclone[8] = MSG_GETPOSITION;
        aclone[9] = MSG_GETMETADATA;
        aclone[10] = MSG_GETALLOWACTIONS;
        $VALUES = aclone;
    }

    private A(String s, int i)
    {
        super(s, i);
    }
}
