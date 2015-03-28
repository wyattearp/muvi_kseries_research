// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.arcsoft.mediaplus.dmc;


// Referenced classes of package com.arcsoft.mediaplus.dmc:
//            DmcUtils

public static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES TYPE_THUMBNAIL;
    public static final .VALUES TYPE_TV_COVER;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/arcsoft/mediaplus/dmc/DmcUtils$COVER_TYPE, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        TYPE_THUMBNAIL = new <init>("TYPE_THUMBNAIL", 0);
        TYPE_TV_COVER = new <init>("TYPE_TV_COVER", 1);
        E_3B_.clone aclone[] = new <init>[2];
        aclone[0] = TYPE_THUMBNAIL;
        aclone[1] = TYPE_TV_COVER;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
