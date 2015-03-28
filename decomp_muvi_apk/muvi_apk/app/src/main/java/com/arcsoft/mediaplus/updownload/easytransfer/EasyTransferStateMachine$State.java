// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferStateMachine

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BROWSED;
    public static final .VALUES BROWSING;
    public static final .VALUES BUILDING;
    public static final .VALUES BUILT;
    public static final .VALUES CANCELLED;
    public static final .VALUES CANCELLING;
    public static final .VALUES COMPLETED;
    public static final .VALUES COMPLETING;
    public static final .VALUES DELETED;
    public static final .VALUES DELETING;
    public static final .VALUES FAULT;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferStateMachine$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        FAULT = new <init>("FAULT", 0);
        BROWSING = new <init>("BROWSING", 1);
        BROWSED = new <init>("BROWSED", 2);
        BUILDING = new <init>("BUILDING", 3);
        BUILT = new <init>("BUILT", 4);
        COMPLETING = new <init>("COMPLETING", 5);
        COMPLETED = new <init>("COMPLETED", 6);
        CANCELLED = new <init>("CANCELLED", 7);
        CANCELLING = new <init>("CANCELLING", 8);
        DELETED = new <init>("DELETED", 9);
        DELETING = new <init>("DELETING", 10);
        e_3B_.clone aclone[] = new <init>[11];
        aclone[0] = FAULT;
        aclone[1] = BROWSING;
        aclone[2] = BROWSED;
        aclone[3] = BUILDING;
        aclone[4] = BUILT;
        aclone[5] = COMPLETING;
        aclone[6] = COMPLETED;
        aclone[7] = CANCELLED;
        aclone[8] = CANCELLING;
        aclone[9] = DELETED;
        aclone[10] = DELETING;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
