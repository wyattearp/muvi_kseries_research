// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.datasource.db;


// Referenced classes of package com.arcsoft.mediaplus.datasource.db:
//            DataTask

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES Browse;
    public static final .VALUES Search;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/datasource/db/DataTask$GetDataMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        Search = new <init>("Search", 0);
        Browse = new <init>("Browse", 1);
        e_3B_.clone aclone[] = new <init>[2];
        aclone[0] = Search;
        aclone[1] = Browse;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
