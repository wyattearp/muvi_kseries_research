// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.updownload.easytransfer;


// Referenced classes of package com.arcsoft.mediaplus.updownload.easytransfer:
//            EasyTransferStateMachine

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES BROWSE;
    public static final .VALUES BUILD;
    public static final .VALUES CANCEL;
    public static final .VALUES COMPLETE;
    public static final .VALUES DELETE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/updownload/easytransfer/EasyTransferStateMachine$Action, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        BROWSE = new <init>("BROWSE", 0);
        BUILD = new <init>("BUILD", 1);
        COMPLETE = new <init>("COMPLETE", 2);
        CANCEL = new <init>("CANCEL", 3);
        DELETE = new <init>("DELETE", 4);
        n_3B_.clone aclone[] = new <init>[5];
        aclone[0] = BROWSE;
        aclone[1] = BUILD;
        aclone[2] = COMPLETE;
        aclone[3] = CANCEL;
        aclone[4] = DELETE;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
