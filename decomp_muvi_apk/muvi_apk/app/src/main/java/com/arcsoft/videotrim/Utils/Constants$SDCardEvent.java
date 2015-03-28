// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.videotrim.Utils;


// Referenced classes of package com.arcsoft.videotrim.Utils:
//            Constants

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BAD_REMOVAL;
    public static final .VALUES EJECT;
    public static final .VALUES MOUNTED;
    public static final .VALUES REMOVED;
    public static final .VALUES SCANNER_FINISHED;
    public static final .VALUES SCANNER_STARTED;
    public static final .VALUES SHARED;
    public static final .VALUES UNMOUNTED;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/videotrim/Utils/Constants$SDCardEvent, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        MOUNTED = new <init>("MOUNTED", 0);
        UNMOUNTED = new <init>("UNMOUNTED", 1);
        REMOVED = new <init>("REMOVED", 2);
        BAD_REMOVAL = new <init>("BAD_REMOVAL", 3);
        SHARED = new <init>("SHARED", 4);
        EJECT = new <init>("EJECT", 5);
        SCANNER_STARTED = new <init>("SCANNER_STARTED", 6);
        SCANNER_FINISHED = new <init>("SCANNER_FINISHED", 7);
        t_3B_.clone aclone[] = new <init>[8];
        aclone[0] = MOUNTED;
        aclone[1] = UNMOUNTED;
        aclone[2] = REMOVED;
        aclone[3] = BAD_REMOVAL;
        aclone[4] = SHARED;
        aclone[5] = EJECT;
        aclone[6] = SCANNER_STARTED;
        aclone[7] = SCANNER_FINISHED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
